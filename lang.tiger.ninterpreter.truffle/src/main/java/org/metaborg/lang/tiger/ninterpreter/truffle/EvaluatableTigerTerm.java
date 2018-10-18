package org.metaborg.lang.tiger.ninterpreter.truffle;

import com.oracle.truffle.api.nodes.Node;

public abstract class EvaluatableTigerTerm extends Node {

	public EvaluatableTigerTerm() {

	}

	// private TigerContext ctx;
	//
	// protected final TigerContext context() {
	// if (ctx == null) {
	// ctx = TigerInterpreter.ctx;
	// }
	// return ctx;
	// }
	//
	// public Object evaluate(TigerEnv env) {
	// throw new RuntimeException(this.getClass().getSimpleName() + " not
	// implemented");
	// }
}
