package org.metaborg.lang.tiger.interp.scopesandframes.nodes.records;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.Occ;
import org.metaborg.lang.tiger.interpreter.generated.terms.__Occurrence2Occ___1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.Addr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Lookup;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetAtAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetAtAddrNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class InitField_2 extends InitField {
	public final static String CONSTRUCTOR = "InitField";

	public final static int ARITY = 2;

	public InitField_2(Occ _1, Exp _2) {
		this(_1, _2, null);
	}

	private InitField_2(Occ _1, Exp _2, IStrategoTerm strategoTerm) {
		this.fieldRef = ((__Occurrence2Occ___1) _1).get_1();
		this.initExp = _2;
		this.lookupNode = Lookup.create();
		this.setNode = SetAtAddrNodeGen.create();
		this.strategoTerm = strategoTerm;
	}

	private final Occurrence fieldRef;

	@Child
	private Exp initExp;

	@Child
	private Lookup lookupNode;

	@Child
	private SetAtAddr setNode;

	@Override
	public void execute(VirtualFrame frame, DynamicObject record_frame, DynamicObject surrounding_frame) {
		V v = initExp.executeGeneric(frame, surrounding_frame);
		Addr addr = lookupNode.execute(frame, record_frame, fieldRef);
		setNode.execute(frame, addr, v);
	}

	@TruffleBoundary
	public static InitField_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new InitField_2(Occ.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), term);
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
		sb.append(fieldRef);
		sb.append(", ");
		sb.append(initExp);
		sb.append(")");
		return sb.toString();
	}

}