package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.Addr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.arrays.ArrayAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.Property;
import com.oracle.truffle.api.object.Shape;

public abstract class GetAtAddr extends ScopesAndFramesNode {

	public GetAtAddr() {
		super();
	}

	public abstract Object execute(VirtualFrame frame, Addr addr);

	@Specialization(guards = { "addr.key() == key_cached", "shape_cached.check(addr.frame())" }, limit = "20")
	public Object doFrameGetCached(FrameAddr addr, @Cached("addr.key()") Occurrence key_cached,
			@Cached("addr.frame().getShape()") Shape shape_cached,
			@Cached("shape_cached.getProperty(key_cached)") Property slot_property) {
		return slot_property.get(addr.frame(), shape_cached);
	}

	@Specialization(replaces = "doFrameGetCached")
	public Object doFrameGet(FrameAddr addr) {
		return addr.frame().get(addr.key());
	}

	@Specialization
	public Object doArrayGet(ArrayAddr addr) {
		return addr.arr().get(addr.idx());
	}

}
