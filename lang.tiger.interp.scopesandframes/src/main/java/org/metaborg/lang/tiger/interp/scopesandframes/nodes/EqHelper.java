package org.metaborg.lang.tiger.interp.scopesandframes.nodes;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.lang.tiger.interp.scopesandframes.values.IntV_1;
import org.metaborg.lang.tiger.interp.scopesandframes.values.NilV_0;
import org.metaborg.lang.tiger.interp.scopesandframes.values.RecordV_1;
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

	@Specialization
	public boolean doNilNil(NilV_0 nil1, NilV_0 nil2) {
		return true;
	}

	@Specialization
	public boolean doRecordNil(RecordV_1 rec, NilV_0 nil) {
		return false;
	}

	@Specialization
	public boolean doNilRecord(NilV_0 nil, RecordV_1 rec) {
		return false;
	}

	@Specialization
	public boolean doRecordRecord(RecordV_1 rec1, RecordV_1 rec2) {
		return rec1.get_1() == rec2.get_1();
	}
}
