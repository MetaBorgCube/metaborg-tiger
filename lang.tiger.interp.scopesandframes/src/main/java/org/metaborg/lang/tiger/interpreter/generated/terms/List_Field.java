package org.metaborg.lang.tiger.interpreter.generated.terms;

import java.util.Iterator;

import org.metaborg.meta.lang.dynsem.interpreter.terms.ConsNilIterator;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class List_Field implements IListTerm<Field> {
	private final IStrategoTerm strategoTerm;

	protected final int size;

	public final static Nil_Field EMPTY = new Nil_Field();

	public List_Field(IStrategoTerm strategoTerm, int size) {
		this.strategoTerm = strategoTerm;
		this.size = size;
	}

	@Override
	public final int size() {
		return this.size;
	}

	@Override
	public final boolean hasStrategoTerm() {
		return strategoTerm != null;
	}

	@Override
	public final IStrategoTerm getStrategoTerm() {
		return strategoTerm;
	}

	@Override
	public abstract Field elem();

	@Override
	public abstract List_Field tail();

	@Override
	public abstract List_Field drop(int numElems);

	@Override
	public Cons_Field prefix(Field prefix) {
		return new Cons_Field(prefix, this, this.getStrategoTerm());
	}

	@Override
	public IListTerm<Field> prefixAll(IListTerm<Field> prefix) {
		List_Field head = this;
		Field[] prefixElems = prefix.toArray();
		for (int idx = prefixElems.length - 1; idx >= 0; idx--) {
			head = new Cons_Field(prefixElems[idx], head, head.getStrategoTerm());
		}
		return head;
	}

	public static final List_Field fromArray(Field[] elems) {
		List_Field tail = new Nil_Field();
		for (int idx = elems.length - 1; idx >= 0; idx--) {
			tail = new Cons_Field(elems[idx], tail, null);
		}
		return tail;
	}

	@Override
	public Field[] toArray() {
		if (size == 0) {
			return new Field[0];
		}
		Field[] arr = new Field[size()];
		Cons_Field head = (Cons_Field) this;
		for (int idx = 0; idx < arr.length; idx++) {
			arr[idx] = head.elem;
			List_Field tail = head.tail;
			if (tail instanceof Cons_Field) {
				head = (Cons_Field) tail;
			}
		}
		return arr;
	}

	@Override
	public List_Field reverse() {
		IStrategoTerm sterm = getStrategoTerm();
		List_Field result = new Nil_Field();
		Field[] elems = toArray();
		for (int idx = 0; idx < elems.length; idx++) {
			result = new Cons_Field(elems[idx], result, sterm);
		}
		return result;
	}

	@Override
	public Iterator<Field> iterator() {
		return new ConsNilIterator<Field>(this);
	}

	@Override
	@TruffleBoundary
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		List_Field head = this;
		while (head instanceof Cons_Field) {
			str.append(head.elem());
			head = head.tail();
			if (head instanceof Cons_Field) {
				str.append(", ");
			}
		}
		return str.append("]").toString();
	}

	public static final class Cons_Field extends List_Field {
		private final Field elem;

		private final List_Field tail;

		public Cons_Field(Field elem, List_Field tail, IStrategoTerm strategoTerm) {
			super(strategoTerm, 1 + tail.size());
			this.elem = elem;
			this.tail = tail;
		}

		@Override
		public Field elem() {
			return elem;
		}

		@Override
		public List_Field tail() {
			return tail;
		}

		@Override
		public Field get(int idx) {
			if (idx == 0) {
				return elem;
			} else {
				return tail.get(idx - 1);
			}
		}

		@Override
		public List_Field drop(int numElems) {
			if (numElems == 0) {
				return this;
			} else {
				return tail.drop(numElems - 1);
			}
		}
	}

	public static final class Nil_Field extends List_Field {
		private Nil_Field() {
			super(null, 0);
		}

		@Override
		public Field elem() {
			throw new IllegalStateException("No elem in a Nil");
		}

		public Nil_Field tail() {
			throw new IllegalStateException("No tail of a Nil");
		}

		@Override
		public List_Field reverse() {
			return this;
		}

		public List_Field drop(int idx) {
			if (idx == 0)
				return this;
			throw new IllegalStateException("Nothing to drop from a Nil");
		}

		@Override
		public Field get(int idx) {
			throw new IllegalStateException("No elems in a Nil");
		}
	}

	@TruffleBoundary
	public static List_Field create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermList(term);
		IStrategoList l = (IStrategoList) term;
		List_Field res = EMPTY;
		for (int idx = l.size() - 1; idx >= 0; idx--) {
			final int final_idx = idx;
			res = new Cons_Field(Field.create(l.getSubterm(final_idx)), res, l);
		}
		return res;
	}
}