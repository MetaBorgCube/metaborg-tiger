package org.metaborg.lang.tiger.interp.scopesandframes;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.commons.vfs2.FileObject;
import org.metaborg.core.MetaborgException;
import org.metaborg.core.language.ILanguageImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.IWithScopesAndFramesContext;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesContext;
import org.metaborg.spoofax.core.Spoofax;
import org.metaborg.spoofax.core.shell.CLIUtils;

import com.google.inject.Module;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public final class TigerContext implements IWithScopesAndFramesContext {

	private Spoofax S;
	private CLIUtils cli;
	private ILanguageImpl spoofaxLanguage;
	private boolean isInitialized;

	private final InputStream in;
	private final PrintStream out;
	private final PrintStream err;

	private ScopesAndFramesContext scopesAndFramesContext;

	public TigerContext(InputStream in, OutputStream out, OutputStream err) {
		this.in = in;
		this.out = new PrintStream(out);
		this.err = new PrintStream(err);
	}

	@TruffleBoundary
	public void initialize() throws MetaborgException {
		isInitialized = true;
		S = new Spoofax(new TigerRunnerModule(), new Module[0]);
		cli = new CLIUtils(S);
		cli.loadLanguagesFromPath();
		spoofaxLanguage = cli.getLanguage(TigerLanguage.NAME);
	}

	public Spoofax getSpoofax() {
		if (!isInitialized) {
			throw new RuntimeException("Context is not initialized");
		}
		assert S != null;
		return S;
	}

	public CLIUtils getCLI() {
		if (!isInitialized) {
			throw new RuntimeException("Context is not initialized");
		}
		assert cli != null;
		return cli;
	}

	public ILanguageImpl getSpoofaxLanguage() {
		if (!isInitialized) {
			throw new RuntimeException("Context is not initialized");
		}
		assert spoofaxLanguage != null;
		return spoofaxLanguage;
	}

	@TruffleBoundary
	public FileObject resolve(String file) {
		if (!isInitialized) {
			throw new RuntimeException("Context is not initialized");
		}
		return S.resourceService.resolve(file);
	}

	public InputStream in() {
		return in;
	}

	public PrintStream out() {
		return out;
	}

	public PrintStream err() {
		return err;
	}

	public void setScopesAndFramesContext(ScopesAndFramesContext snfCtx) {
		this.scopesAndFramesContext = snfCtx;
	}

	@Override
	public ScopesAndFramesContext getScopesAndFramesContext() {
		return this.scopesAndFramesContext;
	}

	// try {
	//// S = createSpoofax();
	// Context ctx =
	// Context.newBuilder().in(System.in).out(System.out).err(System.err).build();
	//
	//// Source src = Source.newBuilder(TigerLanguage.NAME, new File(file)).build();
	//// Source.newBuilder(language, source)
	// DynSemRunner runner = createRunner(S);
	// for (String fileName : args) {
	// FileObject file = resolve(S, fileName);
	// Object result = runner.run(file);
	// printHelper(result);
	// }
	// } catch (MetaborgException mbe) {
	// printStackTrace(mbe);
	// } finally {
	// closeSpoofax(S);
	// }
	// }

	// @TruffleBoundary
	// private static DynSemRunner createRunner(Spoofax S) throws MetaborgException
	// {
	// return new DynSemRunner(S, "Tiger", TigerMain.createVM());
	// }
	//
	// @TruffleBoundary
	// private static void printStackTrace(MetaborgException mbe) {
	// mbe.printStackTrace(System.err);
	// }
	//
	//
	//
	// @TruffleBoundary
	// private static void closeSpoofax(Spoofax S) {
	// if (S != null) {
	// S.close();
	// }
	// }
	//
	// @TruffleBoundary
	// private static void printHelper(Object result) {
	// System.out.println(result);
	// }
	//
	// @TruffleBoundary
	// private static FileObject resolve(Spoofax S, String fileName) {
	// return S.resourceService.resolve(fileName);
	// }
	//
	// @TruffleBoundary
	// private static Spoofax createSpoofax() throws MetaborgException {
	// return new Spoofax(new TigerRunnerModule(), new Module[0]);
	// }

}
