package org.metaborg.lang.tiger.ninterpreter.objects;

import java.util.HashMap;
import java.util.Map;

import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.metaborg.lang.tiger.ninterpreter.TigerObject;

public class ArrayV implements TigerObject {

	private static int nextArrayIdx = Integer.MIN_VALUE;

	private final Map<Integer, Integer> arrIndices = new HashMap<>();

	private final int idx;

	public ArrayV(int length, TigerObject fillValue, TigerHeap heap) {
		for (int i = 0; i < length; i++) {
			arrIndices.put(i, heap.allocate(fillValue));
		}
		this.idx = nextArrayIdx++;
	}

	public int get(int idx) {
		return arrIndices.get(idx);
	}

	public int idx() {
		return idx;
	}

}
