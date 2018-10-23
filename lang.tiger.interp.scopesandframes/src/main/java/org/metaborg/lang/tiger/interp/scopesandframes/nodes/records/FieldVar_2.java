package org.metaborg.lang.tiger.interp.scopesandframes.nodes.records;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.LValue;
import org.metaborg.lang.tiger.interpreter.generated.terms.Occ;
import org.metaborg.lang.tiger.interpreter.generated.terms.__Occurrence2Occ___1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.Addr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameEdgeLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Framed;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddrNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Lookup;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.I;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class FieldVar_2 extends LValue {
	public final static String CONSTRUCTOR = "FieldVar";

	public final static int ARITY = 2;

	public FieldVar_2(LValue _1, Occ _2) {
		this(_1, _2, null);
	}

	private FieldVar_2(LValue _1, Occ _2, IStrategoTerm strategoTerm) {
		this.recordExp = _1;
		this.fieldRef = ((__Occurrence2Occ___1) _2).get_1();
		this.getNode = GetAtAddrNodeGen.create();
		this.newFramedNode = new Framed();
		this.lookupNode = Lookup.create();
		this.strategoTerm = strategoTerm;
	}

	@Child
	private LValue recordExp;

	private final Occurrence fieldRef;

	@Child
	private GetAtAddr getNode;

	@Child
	private Framed newFramedNode;

	@Child
	private Lookup lookupNode;

	@Override
	public Addr execute(VirtualFrame frame, DynamicObject currentFrame) {
		// F F |- fv@FieldVar(e, f : Occurrence) --> addr_field
		// where
		// F F |- e --> addr_rec;
		// get(addr_rec) => RecordV(F_rec);
		// framed(fv, [L(I(), F_rec)]) --> F_use;
		// lookup(F_use, f) => addr_field
		Addr addr_rec = recordExp.execute(frame, currentFrame);
		DynamicObject f_rec = TigerTypesGen.asRecordV_1(getNode.execute(frame, addr_rec)).get_1();
		DynamicObject f_use = newFramedNode.execute(frame, this, new FLink[] { new FrameEdgeLink(I.SINGLETON, f_rec) });
		return lookupNode.execute(frame, f_use, fieldRef);
	}

	@TruffleBoundary
	public static FieldVar_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new FieldVar_2(LValue.create(term.getSubterm(0)), Occ.create(term.getSubterm(1)), term);
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
		sb.append(recordExp);
		sb.append(", ");
		sb.append(fieldRef);
		sb.append(")");
		return sb.toString();
	}

}