package org.metaborg.lang.tiger.ninterpreter.objects;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerObject;

public class RecordV implements TigerObject {

	private static int nextRecIdx = Integer.MIN_VALUE;

	private final TigerEnv store;
	private final int recIdx;

	public RecordV(TigerEnv env) {
		this.store = env;
		this.recIdx = nextRecIdx++;
	}

	public int idx() {
		return recIdx;
	}

	public int get(String field) {
		return store.lookup(field);
	}

}
