package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers;

import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.IntV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;

@NodeChild(value = "num", type = Exp.class)
public abstract class Uminus_1 extends Exp {
	public final static String CONSTRUCTOR = "Uminus";

	public final static int ARITY = 1;

	@Specialization
	public IntV doMinus(IntV num) {
		return new IntV(num.value() * -1);
	}

	public static Uminus_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return Uminus_1NodeGen.create(Exp.create(term.getSubterm(0)));
	}
}