package org.metaborg.lang.tiger.ninterpreter;

import java.util.HashMap;
import java.util.Map;

public class TigerHeap {

	private final Map<Integer, TigerObject> heap = new HashMap<>();

	private int nextAddress = Integer.MIN_VALUE;

	public int allocate(TigerObject v) {
		heap.put(nextAddress, v);
		return nextAddress++;
	}

	public void write(int a, TigerObject v) {
		heap.put(a, v);
	}

	public TigerObject read(int a) {
		return heap.get(a);
	}

}
