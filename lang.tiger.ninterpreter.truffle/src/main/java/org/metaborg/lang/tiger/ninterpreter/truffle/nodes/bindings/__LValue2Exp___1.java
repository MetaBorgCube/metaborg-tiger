package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.TigerMemoryRef;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;

@NodeChild(value = "lval", type = LValue.class)
public abstract class __LValue2Exp___1 extends Exp {
	public final static String CONSTRUCTOR = "__LValue2Exp__";

	public final static int ARITY = 1;

	public __LValue2Exp___1() {
	}

	@Specialization(guards = "ref.slot() == cachedSlot")
	public TigerObject evaluateCachedSlot(TigerMemoryRef ref, @Cached("ref.slot()") FrameSlot cachedSlot) {
		try {
			return (TigerObject) ref.frame().getObject(cachedSlot);
		} catch (FrameSlotTypeException e) {
			throw new RuntimeException(e);
		}
	}

	@Specialization(replaces = "evaluateCachedSlot")
	public TigerObject evaluatedUncached(TigerMemoryRef ref) {
		try {
			return (TigerObject) ref.frame().getObject(ref.slot());
		} catch (FrameSlotTypeException e) {
			throw new RuntimeException(e);
		}
	}

	public static __LValue2Exp___1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return __LValue2Exp___1NodeGen.create(LValue.create(term.getSubterm(0)));
	}
}