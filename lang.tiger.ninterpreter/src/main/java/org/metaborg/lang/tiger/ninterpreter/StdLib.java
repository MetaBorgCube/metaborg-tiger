package org.metaborg.lang.tiger.ninterpreter;

import org.metaborg.lang.tiger.ninterpreter.objects.ClosureV;
import org.metaborg.lang.tiger.ninterpreter.objects.IntV;
import org.metaborg.lang.tiger.ninterpreter.objects.StringV;
import org.metaborg.lang.tiger.ninterpreter.objects.UnitV;
import org.metaborg.lang.tiger.ninterpreter.terms.Exp;
import org.metaborg.lang.tiger.ninterpreter.terms.FArg;
import org.metaborg.lang.tiger.ninterpreter.terms.FArg_2;
import org.metaborg.lang.tiger.ninterpreter.terms.Tid_1;
import org.metaborg.lang.tiger.ninterpreter.terms.__Id2Occ___1;
import org.metaborg.lang.tiger.ninterpreter.terms.__TypeId2Type___1;

public class StdLib extends EvaluatableTigerTerm {

	public StdLib() {
	}

	private static long startTime;

	@Override
	public Object evaluate(TigerEnv env) {
		TigerHeap heap = context().heap();
		ClosureV timeGo = new ClosureV(new FArg[0], new Exp() {
			@Override
			public Object evaluate(TigerEnv env) {
				startTime = System.currentTimeMillis();
				return new UnitV();
			}
		}, env);
		TigerUtils.bindVar("timeGo", timeGo, heap, env);
		ClosureV timeStop = new ClosureV(new FArg[0], new Exp() {
			@Override
			public Object evaluate(TigerEnv env) {
				long et = System.currentTimeMillis();
				System.out.println(et - startTime);
				return new UnitV();
			}
		}, env);
		TigerUtils.bindVar("timeStop", timeStop, heap, env);
		ClosureV flush = new ClosureV(new FArg[0], new Exp() {
			@Override
			public Object evaluate(TigerEnv env) {
				System.out.flush();
				return new UnitV();
			}
		}, env);
		TigerUtils.bindVar("flush", flush, heap, env);
		ClosureV printi = new ClosureV(new FArg[] { mkFArg("x", "int") }, new Exp() {
			@Override
			public Object evaluate(TigerEnv env) {
				System.out.print(((IntV) TigerUtils.readVar("x", heap, env)).value());
				return new UnitV();
			}
		}, env);
		TigerUtils.bindVar("printi", printi, heap, env);
		ClosureV print = new ClosureV(new FArg[] { mkFArg("s", "string") }, new Exp() {
			@Override
			public Object evaluate(TigerEnv env) {
				System.out.print(((StringV) TigerUtils.readVar("s", heap, env)).s());
				return new UnitV();
			}
		}, env);
		TigerUtils.bindVar("print", print, heap, env);

		return new UnitV();
	}

	public static FArg mkFArg(String id, String ty) {
		return new FArg_2(new __Id2Occ___1(id), new __TypeId2Type___1(new Tid_1(new __Id2Occ___1(ty))));
	}
}
