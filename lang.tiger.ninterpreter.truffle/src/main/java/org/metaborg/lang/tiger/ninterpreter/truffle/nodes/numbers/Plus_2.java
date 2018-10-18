package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers;

import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.IntV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.dsl.Specialization;

public abstract class Plus_2 extends TigerBinaryExp {
	public final static String CONSTRUCTOR = "Plus";

	public final static int ARITY = 2;

	public Plus_2() {
	}

	@Specialization
	public IntV doAdd(IntV left, IntV right) {
		return new IntV(left.value() + right.value());
	}

	public static Plus_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return Plus_2NodeGen.create(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)));
	}
}