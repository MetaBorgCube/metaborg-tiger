package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.LookupFactory.LookupUncachedNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.lookup.Path;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.NaBL2LayoutImpl;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.IndirectCallNode;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class Lookup extends ScopesAndFramesNode {

	public Lookup() {
		super();
	}

	public abstract FrameAddr execute(VirtualFrame frame, DynamicObject frm, Occurrence ref);

	public static Lookup create() {
		return LookupUncachedNodeGen.create();
		// return LookupCachedNodeGen.create();
	}

	public static abstract class LookupCached extends Lookup {
		@Child
		private Lookup lookupNode;

		public LookupCached() {
			this.lookupNode = LookupUncachedNodeGen.create();
		}

		@Specialization(guards = { "frm == frm_cached", "ref == ref_cached" }, limit = "1")
		public FrameAddr doCached(VirtualFrame frame, DynamicObject frm, Occurrence ref,
				@Cached("frm") DynamicObject frm_cached, @Cached("ref") Occurrence ref_cached,
				@Cached("doUncached(frame, frm, ref)") FrameAddr cachedAddr) {
			return cachedAddr;
		}

		@Specialization(replaces = "doCached")
		public FrameAddr doUncached(VirtualFrame frame, DynamicObject frm, Occurrence ref) {
			return lookupNode.execute(frame, frm, ref);
		}

	}

	public static abstract class LookupUncached extends Lookup {
		@Specialization(guards = { "ref.equals(ref_cached)" }, limit = "20")
		public FrameAddr doCachedDirect(DynamicObject frm, Occurrence ref, @Cached("ref") Occurrence ref_cached,
				@Cached("create(lookupPathResolver(ref_cached))") DirectCallNode resolverNode) {
			return (FrameAddr) resolverNode.call(new Object[] { frm });
		}

		@Specialization(replaces = "doCachedDirect")
		public FrameAddr doIndirect(DynamicObject frm, Occurrence ref,
				@Cached("create()") IndirectCallNode resolverNode) {
			return (FrameAddr) resolverNode.call(lookupPathResolver(ref), new Object[] { frm });
		}

		protected CallTarget lookupPathResolver(Occurrence ref) {
			Object p = NaBL2LayoutImpl.INSTANCE.getNameResolution(context().getNaBL2Solution()).get(ref);
			if (p == null) {
				throw new RuntimeException("Unresolved reference: " + ref);
			}
			assert p instanceof Path;
			return ((Path) p).getCallTarget();
		}
	}

}
