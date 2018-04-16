package org.metaborg.lang.tiger.interpreter.nativesdts;

import java.util.Arrays;

import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.profiles.ConditionProfile;

public class ListView<T> implements IListView<T> {

	private final T[] dataRef;
	private final int startIdx;
	private final int endIdx;

	public ListView(T[] dataRef, int startIdx, int endIdx) {
		assert startIdx >= 0;
		assert endIdx > startIdx;
		this.dataRef = dataRef;
		this.startIdx = startIdx;
		this.endIdx = endIdx;
	}

	@Override
	public boolean isEmpty() {
		return size() > 0;
	}

	@Override
	public int size() {
		return endIdx - startIdx + 1;
	}

	@Override
	public T head() {
		return dataRef[0];
	}

	/**
	 * Slow path
	 * 
	 * @param numElems
	 * @return
	 */
	public T[] takeRawCopy(int numElems) {
		assert numElems > 1;
		return Arrays.copyOf(dataRef, numElems);
	}

	@Override
	public ListView<T> take(int numElems) {
		assert numElems > 1;
		return new ListView<>(dataRef, startIdx, startIdx + numElems);
	}

	private final ConditionProfile empty_tail = ConditionProfile.createBinaryProfile();

	@Override
	public IListView<T> tail() {
		// determine if the tail is going to be empty or not
		if (empty_tail.profile(endIdx - startIdx > 0)) {
			return new ListView<>(dataRef, startIdx + 1, endIdx);
		} else {
			return EmptyListView.EMPTY();
		}
	}

	/**
	 * Slow. Will actually make a copy of the range
	 */
	@Override
	@ExplodeLoop
	public ListView<T> reverse() {
		@SuppressWarnings("unchecked")
		final T[] data = (T[]) new Object[endIdx - startIdx + 1];
		for (int i = 0; i < data.length; i++) {
			data[i] = dataRef[endIdx - i];
		}
		return new ListView<>(data, 0, data.length - 1);
	}

}
