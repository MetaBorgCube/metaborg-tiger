package org.metaborg.lang.tiger.interp.scopesandframes.nodes.bindings;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.bindings.LetDecsFactory.MultiDecNodeGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.bindings.LetDecsFactory.NoDecNodeGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.bindings.LetDecsFactory.SingleDecNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameEdgeLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameEdgeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.AddFrameLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.AddFrameLinkNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Framed;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.P;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class LetDecs extends TigerTruffleNode {

	public LetDecs() {
	}

	public abstract void execute(VirtualFrame frame, DynamicObject f_outer, DynamicObject f_body);

	public static LetDecs create(Dec[] decs) {
		if (decs.length == 0) {
			return NoDecNodeGen.create();
		} else {
			LetDecs headDec = null;
			for (int i = decs.length - 1; i >= 0; i--) {
				if (headDec == null) {
					headDec = SingleDecNodeGen.create(decs[i]);
				} else {
					headDec = MultiDecNodeGen.create(decs[i], headDec);
				}
			}
			assert headDec != null;
			return headDec;
		}
	}

	protected FrameEdgeIdentifier getEdgeIdent(ALabel label, DynamicObject frm) {
		return new FrameEdgeIdentifier(label, FrameLayoutImpl.INSTANCE.getScope(frm));
	}

	protected ALabel label() {
		return P.SINGLETON;
	}

	public static abstract class NoDec extends LetDecs {
		@Child
		private AddFrameLink linkNode;

		public NoDec() {
			this.linkNode = AddFrameLinkNodeGen.create();
		}

		@Specialization
		public void doWithCaching(VirtualFrame frame, DynamicObject f_outer, DynamicObject f_body, @Cached(value = "label()") ALabel linkLabel,
				@Cached("getEdgeIdent(linkLabel, f_outer)") FrameEdgeIdentifier edgeIdent) {
			FrameEdgeLink link = new FrameEdgeLink(linkLabel, f_outer, edgeIdent);
			linkNode.execute(frame, f_body, link);
		}

	}

	public static abstract class SingleDec extends LetDecs {

		@Child
		private Dec dec;
		@Child
		private AddFrameLink linkNode;

		public SingleDec(Dec dec) {
			this.dec = dec;
			this.linkNode = AddFrameLinkNodeGen.create();
		}

		@Specialization
		public void doWithCaching(VirtualFrame frame, DynamicObject f_outer, DynamicObject f_body,
				@Cached(value = "label()") ALabel linkLabel,
				@Cached("getEdgeIdent(linkLabel, f_outer)") FrameEdgeIdentifier edgeIdent) {
			FrameEdgeLink link = new FrameEdgeLink(linkLabel, f_outer, edgeIdent);
			linkNode.execute(frame, f_body, link);
			dec.execute(frame, f_body, f_outer);
		}
	}

	public static abstract class MultiDec extends LetDecs {
		@Child
		private Framed newFrameNode;
		@Child
		private Dec dec;
		@Child
		private LetDecs decs;
		@Child
		private AddFrameLink linkNode;

		public MultiDec(Dec dec, LetDecs rest) {
			this.dec = dec;
			this.decs = rest;
			this.newFrameNode = new Framed();
			this.linkNode = AddFrameLinkNodeGen.create();
		}

		@Specialization
		public void doWithCaching(VirtualFrame frame, DynamicObject f_outer, DynamicObject f_body,
				@Cached(value = "label()") ALabel linkLabel,
				@Cached("getEdgeIdent(linkLabel, f_outer)") FrameEdgeIdentifier edgeIdent) {
			DynamicObject f_dec = newFrameNode.execute(frame, dec,
					new FLink[] { new FrameEdgeLink(linkLabel, f_outer, edgeIdent) });
			dec.execute(frame, f_dec, f_outer);
			decs.execute(frame, f_dec, f_body);
		}

	}

}
