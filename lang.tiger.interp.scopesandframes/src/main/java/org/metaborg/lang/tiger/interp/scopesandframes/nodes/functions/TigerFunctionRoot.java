package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerLanguage;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameEdgeLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameUtils;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.AddFrameLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.AddFrameLinkNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.NewFrame;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.NewFrameNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetAtAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetAtAddrNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.P;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.object.DynamicObject;

public class TigerFunctionRoot extends RootNode {

	private final ScopeIdentifier functionScope;

	private final Occurrence[] fargs;

	@Child
	private Exp body;

	@Child
	private NewFrame newFrameNode;

	@Child
	private AddFrameLink linkFrameNode;

	@Child
	private SetAtAddr setSlotNode;

	public TigerFunctionRoot(TigerLanguage language, ScopeIdentifier functionScope, Occurrence[] fargs, Exp body) {
		super(language, new FrameDescriptor());
		this.functionScope = functionScope;
		this.fargs = fargs;
		this.body = body;
		this.newFrameNode = NewFrameNodeGen.create();
		this.linkFrameNode = AddFrameLinkNodeGen.create();
		this.setSlotNode = SetAtAddrNodeGen.create();
	}

	@Override
	@ExplodeLoop
	public V execute(VirtualFrame frame) {
		Object[] callArgs = frame.getArguments();
		// create call frame
		DynamicObject callFrame = newFrameNode.execute(frame, functionScope);
		DynamicObject parentFrame = FrameUtils.asFrame(callArgs[0]);
		// link call frame
		this.linkFrameNode.execute(frame, callFrame, new FrameEdgeLink(P.SINGLETON, parentFrame));
		// bind parameters
		Object[] params = (Object[]) callArgs[1];
		assert params.length == fargs.length;
		for (int i = 0; i < params.length; i++) {
			setSlotNode.execute(frame, new FrameAddr(callFrame, fargs[i]), params[i]);
		}
		// evaluate body
		return body.executeGeneric(frame, callFrame);
	}

}
