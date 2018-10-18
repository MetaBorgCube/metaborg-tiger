package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers;

import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.IntV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.dsl.Specialization;

public abstract class Gt_2 extends TigerBinaryExp {
	public final static String CONSTRUCTOR = "Gt";

	public final static int ARITY = 2;

	@Specialization
	public IntV doCompare(IntV left, IntV right) {
		if (left.value() > right.value()) {
			return new IntV(1);
		} else {
			return new IntV(0);
		}
	}

	public static Gt_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return Gt_2NodeGen.create(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)));
	}
}