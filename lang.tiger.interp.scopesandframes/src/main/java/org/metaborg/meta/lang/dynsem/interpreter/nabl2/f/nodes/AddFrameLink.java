package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.IWithScopesAndFramesContext;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameLinkIdentifier;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.FinalLocationException;
import com.oracle.truffle.api.object.IncompatibleLocationException;
import com.oracle.truffle.api.object.Property;
import com.oracle.truffle.api.object.Shape;

public abstract class AddFrameLink extends ScopesAndFramesNode {

	public AddFrameLink(TruffleLanguage<? extends IWithScopesAndFramesContext> language) {
		super(language);
	}

	public abstract void execute(VirtualFrame frame, DynamicObject f, FLink link);
	
	@Specialization(guards = { "shape_cached.check(frm)", "linkId_cached.equals(link.link())" })
	public void doLinkCached(DynamicObject frm, FLink link, @Cached("frm.getShape()") Shape shape_cached,
			@Cached("link.link()") FrameLinkIdentifier linkId_cached,
			@Cached("shape_cached.getProperty(linkId_cached)") Property property_cached) {

		try {
			property_cached.set(frm, link.frame(), shape_cached);
		} catch (IncompatibleLocationException | FinalLocationException e) {
			throw new IllegalStateException(e);
		}
	}

	@Specialization
	public void doLink(DynamicObject frm, FLink link) {
		frm.set(link.link(), link.frame());
	}

}
