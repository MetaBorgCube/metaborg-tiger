package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import org.metaborg.lang.tiger.interpreter.generated.terms.Ty;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.NaBL2LayoutImpl;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class TypeOfDec extends ScopesAndFramesNode {

	public TypeOfDec() {
		super();
	}

	public abstract Ty execute(VirtualFrame frame, Occurrence dec);

	@Specialization(guards = { "dec == dec_cached" }, limit = "20")
	public Ty getTypeCached(Occurrence dec, @Cached("dec") Occurrence dec_cached,
			@Cached("getTypeUncached(dec)") Ty type) {
		return type;
	}

	@Specialization(replaces = "getTypeCached")
	public Ty getTypeUncached(Occurrence dec) {
		return (Ty) NaBL2LayoutImpl.INSTANCE.getTypes(context().getNaBL2Solution()).get(dec);
	}

}
