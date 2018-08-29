package org.metaborg.lang.tiger.ninterpreter;

public interface TigerEnv {

	int lookup(String id);

	TigerEnv bind(String id, int a);

}