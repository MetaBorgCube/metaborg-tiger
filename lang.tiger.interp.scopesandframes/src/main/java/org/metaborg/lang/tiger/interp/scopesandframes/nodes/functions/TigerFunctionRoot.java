package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerLanguage;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameUtils;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.object.DynamicObject;

public class TigerFunctionRoot extends RootNode {

	@Child
	private Exp body;

	public TigerFunctionRoot(TigerLanguage language, Exp body) {
		super(language, new FrameDescriptor());
		this.body = body;
	}

	@Override
	public V execute(VirtualFrame frame) {
		DynamicObject callFrame = FrameUtils.asFrame(frame.getArguments()[0]);
		return body.executeGeneric(frame, callFrame);
//		Object[] callArgs = frame.getArguments();
//		// create call frame
//		DynamicObject callFrame = newFrameNode.execute(frame, functionScope);
//		DynamicObject parentFrame = FrameUtils.asFrame(callArgs[0]);
//		// link call frame
//		this.linkFrameNode.execute(frame, callFrame, new FrameEdgeLink(P.SINGLETON, parentFrame));
//		// bind parameters
//		Object[] params = (Object[]) callArgs[1];
//		assert params.length == fargs.length;
//		for (int i = 0; i < params.length; i++) {
//			setSlotNode.execute(frame, new FrameAddr(callFrame, fargs[i]), params[i]);
//		}
//		// evaluate body
//		return body.executeGeneric(frame, callFrame);
	}

}
