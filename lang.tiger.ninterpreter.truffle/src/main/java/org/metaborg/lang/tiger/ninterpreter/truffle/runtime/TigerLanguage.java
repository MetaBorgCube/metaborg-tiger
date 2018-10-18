package org.metaborg.lang.tiger.ninterpreter.truffle.runtime;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.metaborg.lang.tiger.ninterpreter.terms.Mod_1;
import org.metaborg.lang.tiger.ninterpreter.terms.Module;
import org.metaborg.lang.tiger.ninterpreter.truffle.Parser;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerContext;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerInterpreter;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.TigerRootNode;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;

@TruffleLanguage.Registration(id = TigerLanguage.NAME, name = TigerLanguage.NAME, mimeType = "application/x-tiger")
public final class TigerLanguage extends TruffleLanguage<TigerContext> {

	public static final String NAME = "Tiger";

	public final static String PARSE_TABLE = "src/main/resources/parsetable.tbl";

	public final static String START_SYMBOL = "Module";

	// public final static FrameDescriptor FRAME_DESCRIPTOR = new FrameDescriptor().

	public TigerLanguage() {
	}

	@Override
	protected TigerContext createContext(Env env) {
		return new TigerContext();
	}

	@Override
	protected boolean isObjectOfLanguage(Object object) {
		return false;
	}

	@Override
	protected CallTarget parse(ParsingRequest request) throws Exception {
		Parser parser = new Parser(
				TigerInterpreter.class.getClassLoader().getResourceAsStream(FilenameUtils.getName(PARSE_TABLE)),
				START_SYMBOL);
		IStrategoTerm term = parser.parse(new File(request.getSource().getURI()), null);
		Mod_1 module = (Mod_1) Module.create(term);
		TigerRootNode root = new TigerRootNode(this, getContextReference().get().baseDescriptor.copy(), module.get_1());
		return Truffle.getRuntime().createCallTarget(root);
	}

}
