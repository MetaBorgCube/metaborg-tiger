package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.FinalLocationException;
import com.oracle.truffle.api.object.IncompatibleLocationException;
import com.oracle.truffle.api.object.Property;
import com.oracle.truffle.api.object.Shape;

public abstract class SetFrameSlot extends ScopesAndFramesNode {

	public SetFrameSlot() {
		super();
	}

	public abstract void execute(VirtualFrame frame, DynamicObject frm, Occurrence dec, Object val);

	@Specialization(guards = { "dec == dec_cached", "shape_cached.check(frame)" }, limit = "20")
	public void doSetFrameCached(DynamicObject frame, Occurrence dec, Object val, @Cached("dec") Occurrence dec_cached,
			@Cached("frame.getShape()") Shape shape_cached,
			@Cached("shape_cached.getProperty(dec_cached)") Property slot_property) {
		try {
			slot_property.set(frame, val, shape_cached);
		} catch (IncompatibleLocationException | FinalLocationException e) {
			throw new IllegalStateException(e);
		}
	}

	@Specialization(replaces = "doSetFrameCached")
	public void doSet(DynamicObject frame, Occurrence dec, Object val) {
		frame.set(dec, val);
	}

}
