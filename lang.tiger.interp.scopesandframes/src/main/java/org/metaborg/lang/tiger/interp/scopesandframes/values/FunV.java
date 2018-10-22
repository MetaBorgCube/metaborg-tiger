package org.metaborg.lang.tiger.interp.scopesandframes.values;

import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.object.DynamicObject;

public final class FunV extends V {

	private DynamicObject parentFrame;
	private CallTarget target;

	public FunV(DynamicObject parentFrame, CallTarget target) {
		this.parentFrame = parentFrame;
		this.target = target;
	}

	public DynamicObject getParentFrame() {
		return parentFrame;
	}
	
	public CallTarget getCallTarget() {
		return target;
	}
	
	@Override
	public int size() {
		return 2;
	}

	@Override
	public boolean hasStrategoTerm() {
		return false;
	}

	@Override
	public IStrategoTerm getStrategoTerm() {
		return null;
	}

}
