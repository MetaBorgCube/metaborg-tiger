package org.metaborg.lang.tiger.ninterpreter;

public class TigerContext {

	private final TigerHeap heap;

	public TigerContext(TigerHeap heap) {
		this.heap = heap;
	}

	public TigerHeap heap() {
		return this.heap;
	}

}
