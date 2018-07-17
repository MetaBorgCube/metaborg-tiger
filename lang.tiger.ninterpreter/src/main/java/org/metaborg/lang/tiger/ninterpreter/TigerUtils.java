package org.metaborg.lang.tiger.ninterpreter;

public class TigerUtils {

	private TigerUtils() {
	}

	public static TigerEnv bindVar(String id, TigerObject v, TigerHeap h, TigerEnv e) {
		return e.bind(id, h.allocate(v));
	}

	public static TigerEnv writeVar(String id, TigerObject v, TigerHeap h, TigerEnv e) {
		return e.bind(id, h.allocate(v));
	}

	public static TigerObject readVar(String id, TigerHeap h, TigerEnv e) {
		return h.read(e.lookup(id));
	}

}
