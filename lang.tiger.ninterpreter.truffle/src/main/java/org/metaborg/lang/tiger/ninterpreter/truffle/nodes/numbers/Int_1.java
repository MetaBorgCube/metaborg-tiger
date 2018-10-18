package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers;

import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.IntV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.dsl.Specialization;

public abstract class Int_1 extends Exp {
	public final static String CONSTRUCTOR = "Int";

	public final static int ARITY = 1;

	public Int_1(String _1) {
		this._1 = _1;
	}

	private final String _1;

	@Specialization
	public IntV executeInt() {
		return new IntV(Integer.parseInt(_1));
	}

	public static Int_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return Int_1NodeGen.create(Tools.asJavaString(term.getSubterm(0)));
	}
}