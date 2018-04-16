package org.metaborg.lang.tiger.interpreter.nativesdts;

public interface IListView<T> {

	public int size();

	public boolean isEmpty();

	public T head();

	public IListView<T> tail();

	public IListView<T> take(int numElems);

	public T[] takeRawCopy(int numElems);

	public IListView<T> reverse();
}
