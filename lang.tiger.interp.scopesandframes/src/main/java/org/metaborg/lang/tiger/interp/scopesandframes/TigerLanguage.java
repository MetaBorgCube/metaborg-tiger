package org.metaborg.lang.tiger.interp.scopesandframes;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.vfs2.FileObject;
import org.metaborg.core.MetaborgException;
import org.metaborg.core.action.EndNamedGoal;
import org.metaborg.core.action.ITransformGoal;
import org.metaborg.core.context.IContext;
import org.metaborg.core.language.ILanguageImpl;
import org.metaborg.core.project.IProject;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Module;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.NaBL2Context;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesContext;
import org.metaborg.spoofax.core.Spoofax;
import org.metaborg.spoofax.core.context.scopegraph.ISpoofaxScopeGraphContext;
import org.metaborg.spoofax.core.shell.CLIUtils;
import org.metaborg.spoofax.core.unit.ISpoofaxAnalyzeUnit;
import org.metaborg.spoofax.core.unit.ISpoofaxInputUnit;
import org.metaborg.spoofax.core.unit.ISpoofaxParseUnit;
import org.metaborg.util.concurrent.IClosableLock;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.TermTransformer;
import org.spoofax.terms.attachments.OriginAttachment;

import com.google.common.collect.ImmutableMap;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;

@TruffleLanguage.Registration(id = TigerLanguage.NAME, name = TigerLanguage.NAME, mimeType = "application/x-tiger")
public final class TigerLanguage extends TruffleLanguage<TigerContext> {

	public static final String NAME = "Tiger";

	public TigerLanguage() {
	}

	@Override
	protected TigerContext createContext(Env env) {
		return new TigerContext(env.in(), env.out(), env.err());
	}

	@Override
	protected void initializeContext(TigerContext context) throws Exception {
		context.initialize();
	}

	@Override
	protected boolean isObjectOfLanguage(Object object) {
		return false;
	}

	@Override
	protected CallTarget parse(ParsingRequest request) throws Exception {
		URL sourceURL = request.getSource().getURL();
		if (sourceURL == null) {
			throw new RuntimeException("Cannot parse null URL");
		}
		TigerContext ctx = getContextReference().get();
		FileObject file = ctx.resolve(sourceURL.getFile());
		RunConfig runCfg = prepareForEvaluation(file, ctx);
		ctx.setScopesAndFramesContext(new ScopesAndFramesContext(runCfg.nabl2ctx, new TigerDefaultValues()));
		TigerRootNode rootEvalNode = new TigerRootNode(this, new FrameDescriptor(), Module.create(runCfg.program));
		return Truffle.getRuntime().createCallTarget(rootEvalNode);
	}

	@TruffleBoundary
	private RunConfig prepareForEvaluation(FileObject file, TigerContext ctx) throws MetaborgException {
		CompilerAsserts.neverPartOfCompilation();
		Spoofax S = ctx.getSpoofax();
		CLIUtils cli = ctx.getCLI();
		ILanguageImpl language = ctx.getSpoofaxLanguage();
		IStrategoTerm program;
		ImmutableMap<String, Object> props;
		try {
			IProject project = cli.getProject(file);

			String text = S.sourceTextService.text(file);
			ISpoofaxInputUnit input = S.unitService.inputUnit(file, text, language, null);
			ISpoofaxParseUnit parsed = S.syntaxService.parse(input);
			if (!parsed.valid()) {
				throw new MetaborgException("Parsing failed.");
			}
			cli.printMessages(ctx.err(), parsed.messages());
			if (!parsed.success()) {
				throw new MetaborgException("Parsing returned errors.");
			}

			IContext context = S.contextService.get(file, project, language);
			if (S.analysisService.available(language)) {
				ISpoofaxAnalyzeUnit analyzed;
				try (IClosableLock lock = context.write()) {
					analyzed = S.analysisService.analyze(parsed, context).result();
				}
				if (!analyzed.valid()) {
					throw new MetaborgException("Analysis failed.");
				}
				cli.printMessages(ctx.err(), analyzed.messages());
				if (!analyzed.success()) {
					throw new MetaborgException("Analysis returned errors.");
				}
				ImmutableMap.Builder<String, Object> propBuilder = ImmutableMap.builder();
				if (context instanceof ISpoofaxScopeGraphContext) {
					ISpoofaxScopeGraphContext<?> scopeGraphContext = (ISpoofaxScopeGraphContext<?>) context;
					scopeGraphContext.unit(file.getName().getURI()).solution().ifPresent(solution -> {
						propBuilder.put(NaBL2Context.class.getName(),
								new NaBL2Context(solution.findAndLock(), S.termFactoryService.getGeneric()));
					});
				}
				 props = propBuilder.build();
				if (analyzed.hasAst()) {
					ITransformGoal mkoccgoal = new EndNamedGoal("Make Occurrences");
					if (S.transformService.available(context, mkoccgoal)) {
						program = S.transformService.transform(analyzed, context, mkoccgoal).iterator().next().ast();
					} else {
						program = analyzed.ast();
					}
				} else {
					program = parsed.ast();
				}
			} else {
				program = parsed.ast();
				props = ImmutableMap.of();
			}
			ITermFactory factory = new TermFactory();

			// convert origin attachments to annotations
			program = new TermTransformer(factory, false) {

				@Override
				@TruffleBoundary
				public IStrategoTerm preTransform(IStrategoTerm term) {
					OriginAttachment orig = OriginAttachment.get(term);
					IStrategoList annos = null;
					if (orig != null) {
						IStrategoAppl attachmentTerm = ImploderAttachment.TYPE.toTerm(factory,
								ImploderAttachment.get(orig.getOrigin()));
						annos = factory.makeListCons(attachmentTerm, term.getAnnotations());
					}
					if (annos != null) {
						return factory.annotateTerm(term, annos);
					}
					return term;
				}
			}.transform(program);
		} catch (IOException e) {
			throw new MetaborgException("Analysis failed.", e);
		}
		return new RunConfig(program, (NaBL2Context) props.get(NaBL2Context.class.getName()));
	}

	private class RunConfig {
		protected final IStrategoTerm program;
		protected final NaBL2Context nabl2ctx;

		public RunConfig(IStrategoTerm program, NaBL2Context nabl2ctx) {
			this.program = program;
			this.nabl2ctx = nabl2ctx;
		}
	}

}
