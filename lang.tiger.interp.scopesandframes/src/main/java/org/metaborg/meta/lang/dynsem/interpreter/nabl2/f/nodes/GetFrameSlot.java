package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.Property;
import com.oracle.truffle.api.object.Shape;

public abstract class GetFrameSlot extends ScopesAndFramesNode {

	public GetFrameSlot() {
		super();
	}

	public abstract Object execute(VirtualFrame frame, DynamicObject frm, Occurrence dec);

	@Specialization(guards = { "dec == dec_cached", "shape_cached.check(frm)" }, limit = "20")
	public Object doFrameGetCached(DynamicObject frm, Occurrence dec, @Cached("dec") Occurrence dec_cached,
			@Cached("frm.getShape()") Shape shape_cached,
			@Cached("shape_cached.getProperty(dec_cached)") Property slot_property) {
		return slot_property.get(frm, shape_cached);
	}

	@Specialization(replaces = "doFrameGetCached")
	public Object doFrameGet(DynamicObject frm, Occurrence dec) {
		return frm.get(dec);
	}

}
