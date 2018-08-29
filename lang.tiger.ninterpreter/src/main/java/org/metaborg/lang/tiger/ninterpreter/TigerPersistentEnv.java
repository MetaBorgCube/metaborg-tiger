package org.metaborg.lang.tiger.ninterpreter;

import com.github.krukow.clj_lang.IPersistentMap;
import com.github.krukow.clj_lang.PersistentHashMap;

public class TigerPersistentEnv implements TigerEnv {

	private final TigerEnv parent;
	@SuppressWarnings("unchecked")
	private IPersistentMap<String, Integer> env = PersistentHashMap.EMPTY;

	public TigerPersistentEnv(TigerEnv parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.metaborg.lang.tiger.ninterpreter.TigerEnv#lookup(java.lang.String)
	 */
	@Override
	public int lookup(String id) {
		if (env.containsKey(id)) {
			return env.valAt(id);
		} else {
			if (parent == null) {
				throw new RuntimeException("Unresolved: " + id);
			}
			return parent.lookup(id);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.metaborg.lang.tiger.ninterpreter.TigerEnv#bind(java.lang.String,
	 * int)
	 */
	@Override
	public TigerEnv bind(String id, int a) {
		env = env.assoc(id, a);
		return this;
	}

}
