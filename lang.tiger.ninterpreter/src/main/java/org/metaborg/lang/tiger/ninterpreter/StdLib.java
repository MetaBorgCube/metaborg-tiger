package org.metaborg.lang.tiger.ninterpreter;

import org.metaborg.lang.tiger.ninterpreter.objects.ClosureV;
import org.metaborg.lang.tiger.ninterpreter.objects.UnitV;
import org.metaborg.lang.tiger.ninterpreter.terms.Exp;
import org.metaborg.lang.tiger.ninterpreter.terms.FArg;

public class StdLib implements IEvaluatableTerm {

	public StdLib() {
	}

	private static long startTime;

	@Override
	public Object evaluate(TigerHeap heap, TigerEnv env) {
		ClosureV timeGo = new ClosureV(new FArg[0], new Exp() {
			@Override
			public Object evaluate(TigerHeap heap, TigerEnv env) {
				startTime = System.nanoTime();
				return new UnitV();
			}
		}, env);
		TigerUtils.bindVar("timeGo", timeGo, heap, env);
		ClosureV timeStop = new ClosureV(new FArg[0], new Exp() {
			@Override
			public Object evaluate(TigerHeap heap, TigerEnv env) {
				long et = System.nanoTime();
				System.out.println(et - startTime);
				return new UnitV();
			}
		}, env);
		TigerUtils.bindVar("timeStop", timeStop, heap, env);
		ClosureV flush = new ClosureV(new FArg[0], new Exp() {
			@Override
			public Object evaluate(TigerHeap heap, TigerEnv env) {
				System.out.flush();
				return new UnitV();
			}
		}, env);
		TigerUtils.bindVar("flush", flush, heap, env);

		return new UnitV();
	}
}
