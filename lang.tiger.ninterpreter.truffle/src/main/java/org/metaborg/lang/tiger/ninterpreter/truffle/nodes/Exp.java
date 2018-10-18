package org.metaborg.lang.tiger.ninterpreter.truffle.nodes;

import org.metaborg.lang.tiger.ninterpreter.terms.And_2;
import org.metaborg.lang.tiger.ninterpreter.terms.Array_3;
import org.metaborg.lang.tiger.ninterpreter.terms.Break_0;
import org.metaborg.lang.tiger.ninterpreter.terms.Divide_2;
import org.metaborg.lang.tiger.ninterpreter.terms.Eq_2;
import org.metaborg.lang.tiger.ninterpreter.terms.For_4;
import org.metaborg.lang.tiger.ninterpreter.terms.IfThen_2;
import org.metaborg.lang.tiger.ninterpreter.terms.Neq_2;
import org.metaborg.lang.tiger.ninterpreter.terms.NilExp_0;
import org.metaborg.lang.tiger.ninterpreter.terms.Or_2;
import org.metaborg.lang.tiger.ninterpreter.terms.String_1;
import org.metaborg.lang.tiger.ninterpreter.terms.While_2;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings.Assign_2;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings.LValue;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings.Let_2;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings.__LValue2Exp___1;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings.__LValue2Exp___1NodeGen;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.control.If_3;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.control.Seq_1;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.functions.Call_2;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers.Geq_2;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers.Gt_2;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers.Int_1;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers.Leq_2;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers.Lt_2;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers.Minus_2;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers.Plus_2;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers.Times_2;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers.Uminus_1;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.types.Record_2;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class Exp extends TigerNode {

	public abstract TigerObject executeGeneric(VirtualFrame frame);

	public static Exp create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Seq", 1)) {
			return Seq_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "If", 3)) {
			return If_3.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "IfThen", 2)) {
			return IfThen_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "While", 2)) {
			return While_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "For", 4)) {
			return For_4.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Break", 0)) {
			return Break_0.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Array", 3)) {
			return Array_3.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "NilExp", 0)) {
			return NilExp_0.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Record", 2)) {
			return Record_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "String", 1)) {
			return String_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Int", 1)) {
			return Int_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Uminus", 1)) {
			return Uminus_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Times", 2)) {
			return Times_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Divide", 2)) {
			return Divide_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Plus", 2)) {
			return Plus_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Minus", 2)) {
			return Minus_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Eq", 2)) {
			return Eq_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Neq", 2)) {
			return Neq_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Gt", 2)) {
			return Gt_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Lt", 2)) {
			return Lt_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Geq", 2)) {
			return Geq_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Leq", 2)) {
			return Leq_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "And", 2)) {
			return And_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Or", 2)) {
			return Or_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Call", 2)) {
			return Call_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "__LValue2Exp__", 1)) {
			return __LValue2Exp___1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Assign", 2)) {
			return Assign_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Let", 2)) {
			return Let_2.create(term);
		}
		try {
			return __LValue2Exp___1NodeGen.create(LValue.create(term));
		} catch (IllegalStateException l_25) {
			throw new IllegalStateException("Unsupported term: " + term);
		}
	}
}