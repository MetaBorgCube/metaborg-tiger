package org.metaborg.lang.tiger.interp.scopesandframes.nodes;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.lang.tiger.interp.scopesandframes.values.IntV_1;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;

import com.oracle.truffle.api.dsl.Specialization;

public abstract class EqHelper extends TigerTruffleNode {

	public EqHelper() {
	}
	
	public abstract boolean executeGeneric(V v1, V v2);
	
	@Specialization
	public boolean doInt(IntV_1 i1, IntV_1 i2) {
		return i1.get_1() == i2.get_1();
	}

}
