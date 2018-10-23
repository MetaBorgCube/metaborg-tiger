package org.metaborg.lang.tiger.interp.scopesandframes.nodes;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions.stdlib.StdLib;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Framed;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.GetTopLevelTermIndex;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.GetTopLevelTermIndexNodeGen;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class Mod_1 extends Module {
	public final static String CONSTRUCTOR = "Mod";

	public final static int ARITY = 1;

	@Child
	private Exp _1;

	@Child
	private Framed framedCreationNode;

	@Child
	private StdLib stdLibNode;

	@Child
	private GetTopLevelTermIndex topIndex;

	public Mod_1(Exp _1) {
		this(_1, null);
	}

	private Mod_1(Exp _1, IStrategoTerm strategoTerm) {
		this._1 = _1;
		this.strategoTerm = strategoTerm;
		this.framedCreationNode = new Framed();
		this.stdLibNode = new StdLib();
		this.topIndex = GetTopLevelTermIndexNodeGen.create();
	}

	@Override
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		currentFrame = framedCreationNode.execute(frame, this, new FLink[0]);
		stdLibNode.installBuiltins(frame, currentFrame, topIndex.execute(frame, this));
		return _1.executeGeneric(frame, currentFrame);
	}

	@TruffleBoundary
	public static Mod_1 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Mod_1(Exp.create(term.getSubterm(0)), term);
	}

	private final IStrategoTerm strategoTerm;

	@Override
	public int size() {
		return ARITY;
	}

	@Override
	public boolean hasStrategoTerm() {
		return strategoTerm != null;
	}

	@Override
	public IStrategoTerm getStrategoTerm() {
		return strategoTerm;
	}

	@TruffleBoundary
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(CONSTRUCTOR);
		sb.append("(");
		sb.append(_1);
		sb.append(")");
		return sb.toString();
	}

}