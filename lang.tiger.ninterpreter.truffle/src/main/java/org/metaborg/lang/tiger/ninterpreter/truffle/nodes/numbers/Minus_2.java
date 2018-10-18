package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.IntV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.dsl.Specialization;

public abstract class Minus_2 extends TigerBinaryExp {
	public final static String CONSTRUCTOR = "Minus";

	public final static int ARITY = 2;

	@Specialization
	public TigerObject doMinus(IntV left, IntV right) {
		return new IntV(left.value() - right.value());
	}

	public static Minus_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return Minus_2NodeGen.create(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)));
	}
}