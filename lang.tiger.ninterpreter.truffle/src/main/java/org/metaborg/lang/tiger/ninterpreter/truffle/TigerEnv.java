package org.metaborg.lang.tiger.ninterpreter.truffle;

import java.util.HashMap;
import java.util.Map;

public class TigerEnv {

	private final TigerEnv parent;
	private final Map<String, Integer> env = new HashMap<>();

	public TigerEnv(TigerEnv parent) {
		this.parent = parent;
	}

	public int lookup(String id) {
		if (env.containsKey(id)) {
			return env.get(id);
		} else {
			if (parent == null) {
				throw new RuntimeException("Unresolved: " + id);
			}
			return parent.lookup(id);
		}
	}

	public TigerEnv bind(String id, int a) {
		env.put(id, a);
		return this;
	}

}
