package org.metaborg.lang.tiger.ninterpreter;

public interface IEvaluatableTerm {
	default public Object evaluate(TigerHeap heap, TigerEnv env) {
		throw new RuntimeException(this.getClass().getSimpleName() + " not implemented");
	}
}
