package org.metaborg.lang.tiger.interpreter.generated.terms;

import java.util.Iterator;

import org.metaborg.meta.lang.dynsem.interpreter.terms.ConsNilIterator;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class List_InitField implements IListTerm<InitField> {
	private final IStrategoTerm strategoTerm;

	protected final int size;

	public final static Nil_InitField EMPTY = new Nil_InitField();

	public List_InitField(IStrategoTerm strategoTerm, int size) {
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
	public abstract InitField elem();

	@Override
	public abstract List_InitField tail();

	@Override
	public abstract List_InitField drop(int numElems);

	@Override
	public Cons_InitField prefix(InitField prefix) {
		return new Cons_InitField(prefix, this, this.getStrategoTerm());
	}

	@Override
	public IListTerm<InitField> prefixAll(IListTerm<InitField> prefix) {
		List_InitField head = this;
		InitField[] prefixElems = prefix.toArray();
		for (int idx = prefixElems.length - 1; idx >= 0; idx--) {
			head = new Cons_InitField(prefixElems[idx], head, head.getStrategoTerm());
		}
		return head;
	}

	public static final List_InitField fromArray(InitField[] elems) {
		List_InitField tail = new Nil_InitField();
		for (int idx = elems.length - 1; idx >= 0; idx--) {
			tail = new Cons_InitField(elems[idx], tail, null);
		}
		return tail;
	}

	@Override
	public InitField[] toArray() {
		if (size == 0) {
			return new InitField[0];
		}
		InitField[] arr = new InitField[size()];
		Cons_InitField head = (Cons_InitField) this;
		for (int idx = 0; idx < arr.length; idx++) {
			arr[idx] = head.elem;
			List_InitField tail = head.tail;
			if (tail instanceof Cons_InitField) {
				head = (Cons_InitField) tail;
			}
		}
		return arr;
	}

	@Override
	public List_InitField reverse() {
		IStrategoTerm sterm = getStrategoTerm();
		List_InitField result = new Nil_InitField();
		InitField[] elems = toArray();
		for (int idx = 0; idx < elems.length; idx++) {
			result = new Cons_InitField(elems[idx], result, sterm);
		}
		return result;
	}

	@Override
	public Iterator<InitField> iterator() {
		return new ConsNilIterator<InitField>(this);
	}

	@Override
	@TruffleBoundary
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		List_InitField head = this;
		while (head instanceof Cons_InitField) {
			str.append(head.elem());
			head = head.tail();
			if (head instanceof Cons_InitField) {
				str.append(", ");
			}
		}
		return str.append("]").toString();
	}

	public static final class Cons_InitField extends List_InitField {
		private final InitField elem;

		private final List_InitField tail;

		public Cons_InitField(InitField elem, List_InitField tail, IStrategoTerm strategoTerm) {
			super(strategoTerm, 1 + tail.size());
			this.elem = elem;
			this.tail = tail;
		}

		@Override
		public InitField elem() {
			return elem;
		}

		@Override
		public List_InitField tail() {
			return tail;
		}

		@Override
		public InitField get(int idx) {
			if (idx == 0) {
				return elem;
			} else {
				return tail.get(idx - 1);
			}
		}

		@Override
		public List_InitField drop(int numElems) {
			if (numElems == 0) {
				return this;
			} else {
				return tail.drop(numElems - 1);
			}
		}
	}

	public static final class Nil_InitField extends List_InitField {
		private Nil_InitField() {
			super(null, 0);
		}

		@Override
		public InitField elem() {
			throw new IllegalStateException("No elem in a Nil");
		}

		public Nil_InitField tail() {
			throw new IllegalStateException("No tail of a Nil");
		}

		@Override
		public List_InitField reverse() {
			return this;
		}

		public List_InitField drop(int idx) {
			if (idx == 0)
				return this;
			throw new IllegalStateException("Nothing to drop from a Nil");
		}

		@Override
		public InitField get(int idx) {
			throw new IllegalStateException("No elems in a Nil");
		}
	}

	@TruffleBoundary
	public static List_InitField create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermList(term);
		IStrategoList l = (IStrategoList) term;
		List_InitField res = EMPTY;
		for (int idx = l.size() - 1; idx >= 0; idx--) {
			final int final_idx = idx;
			res = new Cons_InitField(InitField.create(l.getSubterm(final_idx)), res, l);
		}
		return res;
	}
}