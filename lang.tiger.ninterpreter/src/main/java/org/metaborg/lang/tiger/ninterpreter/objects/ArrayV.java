package org.metaborg.lang.tiger.ninterpreter.objects;

import java.util.HashMap;
import java.util.Map;

import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.metaborg.lang.tiger.ninterpreter.TigerObject;

public class ArrayV implements TigerObject {

	private static int nextArrayIdx = Integer.MIN_VALUE;

	private final Map<Integer, Integer> arraySlots;

	private final int idx;

	public ArrayV(int length, TigerObject fillValue, TigerHeap heap) {
		this.idx = nextArrayIdx++;
		Map<Integer, Integer> arraySlots = new HashMap<>();
		for (int i = 0; i < length; i++) {
			arraySlots.put(i, heap.allocate(fillValue));
		}
		this.arraySlots = arraySlots;
	}

	public int get(int idx) {
		return arraySlots.get(idx);
	}

	public int idx() {
		return idx;
	}

}
