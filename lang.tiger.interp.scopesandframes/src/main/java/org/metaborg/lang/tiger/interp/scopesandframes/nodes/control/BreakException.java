package org.metaborg.lang.tiger.interp.scopesandframes.nodes.control;

import com.oracle.truffle.api.nodes.ControlFlowException;

public final class BreakException extends ControlFlowException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7252354674511859587L;

	public static final BreakException SINGLETON = new BreakException();

	private BreakException() {
	}

}
