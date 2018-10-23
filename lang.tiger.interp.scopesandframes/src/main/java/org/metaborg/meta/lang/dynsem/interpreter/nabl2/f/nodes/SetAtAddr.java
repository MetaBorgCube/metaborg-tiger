package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.Addr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.arrays.ArrayAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.FinalLocationException;
import com.oracle.truffle.api.object.IncompatibleLocationException;
import com.oracle.truffle.api.object.Property;
import com.oracle.truffle.api.object.Shape;

public abstract class SetAtAddr extends ScopesAndFramesNode {

	public SetAtAddr() {
		super();
	}

	public abstract void execute(VirtualFrame frame, Addr addr, Object val);

	@Specialization(guards = { "addr.key() == key_cached", "shape_cached.check(addr.frame())" }, limit = "20")
	public void doSetFrameCached(FrameAddr addr, Object val, @Cached("addr.key()") Occurrence key_cached,
			@Cached("addr.frame().getShape()") Shape shape_cached,
			@Cached("shape_cached.getProperty(key_cached)") Property slot_property) {
		try {
			slot_property.set(addr.frame(), val, shape_cached);
		} catch (IncompatibleLocationException | FinalLocationException e) {
			throw new IllegalStateException(e);
		}
	}

	@Specialization(replaces = "doSetFrameCached")
	public void doSet(FrameAddr addr, Object val) {
		addr.frame().set(addr.key(), val);
	}

	@Specialization
	public void doArraySet(ArrayAddr addr, Object val) {
		addr.arr().set(addr.idx(), val);
	}

}
