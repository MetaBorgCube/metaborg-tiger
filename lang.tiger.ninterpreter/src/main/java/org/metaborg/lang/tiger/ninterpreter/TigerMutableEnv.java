package org.metaborg.lang.tiger.ninterpreter;

import java.util.HashMap;
import java.util.Map;

public class TigerMutableEnv implements TigerEnv {

	private final TigerEnv parent;
	private final Map<String, Integer> env = new HashMap<>();

	public TigerMutableEnv(TigerEnv parent) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see org.metaborg.lang.tiger.ninterpreter.TigerEnv#lookup(java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see org.metaborg.lang.tiger.ninterpreter.TigerEnv#bind(java.lang.String, int)
	 */
	@Override
	public TigerEnv bind(String id, int a) {
		env.put(id, a);
		return this;
	}

}
