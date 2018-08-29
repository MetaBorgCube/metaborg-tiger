package org.metaborg.lang.tiger.ninterpreter;

import java.util.HashMap;
import java.util.Map;

public class TigerMutableHeap implements TigerHeap {

	private final Map<Integer, TigerObject> heap = new HashMap<>();
	
	private int nextAddress = Integer.MIN_VALUE;
	
	/* (non-Javadoc)
	 * @see org.metaborg.lang.tiger.ninterpreter.TigerHeap#allocate(org.metaborg.lang.tiger.ninterpreter.TigerObject)
	 */
	@Override
	public int allocate(TigerObject v) {
		heap.put(nextAddress, v);
		return nextAddress++;
	}
	
	/* (non-Javadoc)
	 * @see org.metaborg.lang.tiger.ninterpreter.TigerHeap#write(int, org.metaborg.lang.tiger.ninterpreter.TigerObject)
	 */
	@Override
	public void write(int a, TigerObject v) {
		heap.put(a, v);
	}
	
	/* (non-Javadoc)
	 * @see org.metaborg.lang.tiger.ninterpreter.TigerHeap#read(int)
	 */
	@Override
	public TigerObject read(int a) {
		return heap.get(a);
	}
	
}
