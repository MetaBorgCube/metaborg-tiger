package org.metaborg.lang.tiger.ninterpreter.objects;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.terms.Exp;
import org.metaborg.lang.tiger.ninterpreter.terms.FArg;

public class ClosureV implements TigerObject {

	private final FArg[] args;
	private final Exp e;
	private final TigerEnv env;
	
	public ClosureV(FArg[] args, Exp e, TigerEnv env) {
		this.args = args;
		this.e = e;
		this.env = env;
	}
	
	public FArg[] args() {
		return args;
	}
	
	public Exp body() {
		return e;
	}
	
	public TigerEnv env() {
		return env;
	}
	
	

}
