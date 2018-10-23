package org.metaborg.lang.tiger.interp.scopesandframes.nodes.control;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.IntV_1;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.UnitV_0;
import org.metaborg.lang.tiger.interpreter.generated.terms.Var;
import org.metaborg.lang.tiger.interpreter.generated.terms.Var_1;
import org.metaborg.lang.tiger.interpreter.generated.terms.__Occurrence2Occ___1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameEdgeLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameEdgeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Framed;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetFrameSlot;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetFrameSlotNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.P;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class For_4 extends Exp {
	public final static String CONSTRUCTOR = "For";

	public final static int ARITY = 4;

	private final Occurrence boundVar;

	@Child
	private Exp e1;

	@Child
	private Exp e2;

	@Child
	private Exp body;

	@Child
	private Framed newFrameNode;

	@Child
	private SetFrameSlot setNode;

	public For_4(Var _1, Exp _2, Exp _3, Exp _4) {
		this(_1, _2, _3, _4, null);
	}

	public For_4(Var _1, Exp _2, Exp _3, Exp _4, IStrategoTerm strategoTerm) {
		this.boundVar = ((__Occurrence2Occ___1) ((Var_1) _1).get_1()).get_1();
		this.e1 = _2;
		this.e2 = _3;
		this.body = _4;
		this.newFrameNode = new Framed();
		this.setNode = SetFrameSlotNodeGen.create();
		this.strategoTerm = strategoTerm;
	}

	@Specialization
	@ExplodeLoop
	public V executeWithCaching(VirtualFrame frame, DynamicObject currentFrame, @Cached(value = "label()") ALabel linkLabel,
			@Cached("getEdgeIdent(linkLabel, currentFrame)") FrameEdgeIdentifier edgeIdent) {
		int start = TigerTypesGen.asIntV_1(e1.executeGeneric(frame, currentFrame)).get_1();
		int end = TigerTypesGen.asIntV_1(e2.executeGeneric(frame, currentFrame)).get_1();
		DynamicObject f_for = newFrameNode.executeNewFrame(frame, this,
				new FLink[] { new FrameEdgeLink(P.SINGLETON, currentFrame, edgeIdent) });
		// FIXME: reimplement this using RepeatingNode (possibly with an yield)
		V result = UnitV_0.SINGLETON;
		for (int i = start; i <= end; i++) {
			setNode.execute(frame, f_for, boundVar, new IntV_1(i));
			try {
				result = body.executeGeneric(frame, f_for);
			} catch (BreakException brk) {
				break;
			}
		}
		return result;
	}
	
	protected FrameEdgeIdentifier getEdgeIdent(ALabel label, DynamicObject frm) {
		return new FrameEdgeIdentifier(label, FrameLayoutImpl.INSTANCE.getScope(frm));
	}

	protected ALabel label() {
		return P.SINGLETON;
	}

	@TruffleBoundary
	public static For_4 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return For_4NodeGen.create(Var.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), Exp.create(term.getSubterm(2)),
				Exp.create(term.getSubterm(3)), term);
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
		sb.append(boundVar);
		sb.append(", ");
		sb.append(e1);
		sb.append(", ");
		sb.append(e2);
		sb.append(", ");
		sb.append(body);
		sb.append(")");
		return sb.toString();
	}

}