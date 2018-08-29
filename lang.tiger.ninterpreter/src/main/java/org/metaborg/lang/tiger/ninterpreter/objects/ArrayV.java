package org.metaborg.lang.tiger.ninterpreter.objects;

import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.metaborg.lang.tiger.ninterpreter.TigerObject;

import com.github.krukow.clj_lang.IPersistentMap;
import com.github.krukow.clj_lang.PersistentHashMap;

public class ArrayV implements TigerObject {

	private static int nextArrayIdx = Integer.MIN_VALUE;

	private final IPersistentMap<Integer, Integer> arrIndices;

	private final int idx;

	public ArrayV(int length, TigerObject fillValue, TigerHeap heap) {
		IPersistentMap<Integer, Integer> arrPMap = PersistentHashMap.EMPTY;
		for (int i = 0; i < length; i++) {
			arrPMap = arrPMap.assoc(i, heap.allocate(fillValue));
		}
		arrIndices = arrPMap;
		this.idx = nextArrayIdx++;
	}

	public int get(int idx) {
		return arrIndices.valAt(idx);
	}

	public int idx() {
		return idx;
	}

}
