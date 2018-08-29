package org.metaborg.lang.tiger.ninterpreter;

public interface TigerHeap {

	int allocate(TigerObject v);

	void write(int a, TigerObject v);

	TigerObject read(int a);

}