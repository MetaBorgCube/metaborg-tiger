package org.metaborg.lang.tiger.interp.scopesandframes.nodes;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameEdgeLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.AddFrameLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.AddFrameLinkNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Framed;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.P;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class LetDecs extends TigerTruffleNode {

	public LetDecs() {
	}

	public abstract void execute(VirtualFrame frame, DynamicObject f_outer, DynamicObject f_body);
	
	public static LetDecs create(Dec[] decs) {
		if (decs.length == 0) {
			return new NoDec();
		} else {
			LetDecs headDec = null;
			for(int i = decs.length - 1; i >= 0; i--) {
				if(headDec == null) {
					headDec = new SingleDec(decs[i]);
				} else {
					headDec = new MultiDec(decs[i], headDec);
				}
			}
			assert headDec != null;
			return headDec;
		}
	}
	
	public final static class NoDec extends LetDecs {
		@Child private AddFrameLink linkNode;

		public NoDec() {
			this.linkNode = AddFrameLinkNodeGen.create();
		}
		
		@Override
		public void execute(VirtualFrame frame, DynamicObject f_outer, DynamicObject f_body) {
			//@formatter:off
			//Frames1 (F_outer, F_body) |- Decs([]) --> U()
			//where
			//  link(F_body, L(P(), F_outer)) => _
			//			
			//@formatter:on
			FrameEdgeLink link = new FrameEdgeLink(P.SINGLETON, f_outer);
			linkNode.execute(frame, f_body, link);
		}
		
	}
	
	public final static class SingleDec extends LetDecs {
		
		@Child private Dec dec;
		@Child private AddFrameLink linkNode;
		
		public SingleDec(Dec dec) {
			this.dec = dec;
			this.linkNode = AddFrameLinkNodeGen.create();
		}
		
		@Override
		public void execute(VirtualFrame frame, DynamicObject f_outer, DynamicObject f_body) {
			//@formatter:off
			//Frames1 (F_outer, F_body) |- Decs([block]) --> U()
			//where
			//  link(F_body, L(P(), F_outer)) => _;
			//  Frames1 (F_body, F_outer) |- Dec(block) --> _
			//@formatter:on
			FrameEdgeLink link = new FrameEdgeLink(P.SINGLETON, f_outer);
			linkNode.execute(frame, f_body, link);
			dec.execute(frame, f_body, f_outer);
		}
	}
	
	public final static class MultiDec extends LetDecs {
		@Child private Framed newFrameNode;
		@Child private Dec dec;
		@Child private LetDecs decs;
		@Child private AddFrameLink linkNode;
		
		public MultiDec(Dec dec, LetDecs rest) {
			this.dec = dec;
			this.decs = rest;
			this.newFrameNode = new Framed();
			this.linkNode = AddFrameLinkNodeGen.create();
		}
		
		@Override
		public void execute(VirtualFrame frame, DynamicObject f_outer, DynamicObject f_body) {
			//@formatter:off
			//
			//Frames1 (F_outer, F_body) |- Decs([block | blocks@[_|_]]) --> U()
			//where
			//  framed(block, [L(P(), F_outer)]) --> F_dec;
			//  Frames1 (F_dec, F_outer) |- Dec(block) --> _;
			//  Frames1 (F_dec, F_body)  |- Decs(blocks) --> _
			//@formatter:on
			DynamicObject f_dec = newFrameNode.executeNewFrame(frame, dec, new FLink[] { new FrameEdgeLink(P.SINGLETON, f_outer) });
			dec.execute(frame, f_dec, f_outer);
			decs.execute(frame, f_dec, f_body);
		}
	}


}
