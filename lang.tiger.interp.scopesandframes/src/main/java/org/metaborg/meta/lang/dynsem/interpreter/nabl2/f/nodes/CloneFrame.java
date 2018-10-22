package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import java.util.List;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.FinalLocationException;
import com.oracle.truffle.api.object.IncompatibleLocationException;
import com.oracle.truffle.api.object.Property;
import com.oracle.truffle.api.object.Shape;

public abstract class CloneFrame extends ScopesAndFramesNode {

	public CloneFrame() {
		super();
	}

	public abstract DynamicObject execute(DynamicObject frm);

	@Specialization(guards = { "shape_cached.check(frm)" }, limit = "30")
	@ExplodeLoop
	public DynamicObject doCloneCached(DynamicObject frm, @Cached("frm.getShape()") Shape shape_cached,
			@Cached(value = "getProperties(shape_cached)", dimensions = 1) Property[] properties) {
		DynamicObject frmClone = shape_cached.newInstance();
		for (int i = 0; i < properties.length; i++) {
			Property p = properties[i];
			try {
				p.set(frmClone, p.get(frm, shape_cached), shape_cached);
			} catch (IncompatibleLocationException | FinalLocationException e) {
				throw new IllegalStateException(e);
			}
		}
		return frmClone;
	}

	@Specialization(replaces = "doCloneCached")
	public DynamicObject doClone(DynamicObject frm) {
		return frm.copy(frm.getShape());
	}

	protected Property[] getProperties(Shape shape) {
		CompilerAsserts.neverPartOfCompilation();
		List<Property> propList = shape.getPropertyListInternal(true);
		return propList.toArray(new Property[0]);
	}

}
