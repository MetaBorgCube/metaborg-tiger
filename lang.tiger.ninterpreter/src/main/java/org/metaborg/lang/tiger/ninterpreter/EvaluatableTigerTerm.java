package org.metaborg.lang.tiger.ninterpreter;

public abstract class EvaluatableTigerTerm {

	public EvaluatableTigerTerm() {

	}

	private TigerContext ctx;

	protected final TigerContext context() {
		if (ctx == null) {
			ctx = TigerInterpreter.ctx;
		}
		return ctx;
	}

	public Object evaluate(TigerEnv env) {
		throw new RuntimeException(this.getClass().getSimpleName() + " not implemented");
	}
}
