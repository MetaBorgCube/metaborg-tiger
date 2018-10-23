package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerLanguage;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameUtils;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

public class TigerFunctionRoot extends RootNode {

	@Child
	private Exp body;

	public TigerFunctionRoot(TigerLanguage language, Exp body) {
		super(language, new FrameDescriptor());
		this.body = body;
	}

	@Override
	public V execute(VirtualFrame frame) {
		return body.executeGeneric(frame, FrameUtils.asFrame(frame.getArguments()[0]));
	}

}
