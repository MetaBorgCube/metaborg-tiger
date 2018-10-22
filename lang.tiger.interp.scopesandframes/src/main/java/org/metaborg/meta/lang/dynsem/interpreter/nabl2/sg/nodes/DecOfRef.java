package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.lookup.Path;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.NaBL2LayoutImpl;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class DecOfRef extends ScopesAndFramesNode {

	public DecOfRef() {
		super();
	}

	public abstract Occurrence execute(VirtualFrame frame, Occurrence ref);
	
	@Specialization(guards = { "ref == ref_cached" })
	public Occurrence getCached(Occurrence ref, @Cached("ref") Occurrence ref_cached,
			@Cached("getUncached(ref_cached)") Occurrence dec) {
		return dec;
	}

	@Specialization(replaces = "getCached")
	public Occurrence getUncached(Occurrence ref) {
		return ((Path) NaBL2LayoutImpl.INSTANCE.getNameResolution(context().getNaBL2Solution()).get(ref))
				.getTargetDec();
	}


}
