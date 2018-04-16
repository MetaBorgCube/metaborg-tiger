package org.metaborg.lang.tiger.interpreter.nativesdts;

import java.util.NoSuchElementException;

public final class EmptyListView<T> implements IListView<T> {

	public EmptyListView() {
	}

	@SuppressWarnings("rawtypes")
	private final static EmptyListView INSTANCE = new EmptyListView<>();

	@SuppressWarnings("unchecked")
	public final static <T> EmptyListView<T> EMPTY() {
		return INSTANCE;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public T head() {
		throw new NoSuchElementException();
	}

	@Override
	public IListView<T> tail() {
		throw new NoSuchElementException();
	}

	@Override
	public IListView<T> take(int numElems) {
		throw new NoSuchElementException();
	}

	@Override
	public T[] takeRawCopy(int numElems) {
		throw new NoSuchElementException();
	}

	@Override
	public IListView<T> reverse() {
		throw new NoSuchElementException();
	}
}
