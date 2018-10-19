package org.metaborg.lang.tiger.interpreter.generated.terms;

import java.util.Iterator;

import org.metaborg.meta.lang.dynsem.interpreter.terms.ConsNilIterator;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class List_Dec implements IListTerm<Dec> {
	private final IStrategoTerm strategoTerm;

	protected final int size;

	public final static Nil_Dec EMPTY = new Nil_Dec();

	public List_Dec(IStrategoTerm strategoTerm, int size) {
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
	public abstract Dec elem();

	@Override
	public abstract List_Dec tail();

	@Override
	public abstract List_Dec drop(int numElems);

	@Override
	public Cons_Dec prefix(Dec prefix) {
		return new Cons_Dec(prefix, this, this.getStrategoTerm());
	}

	@Override
	public IListTerm<Dec> prefixAll(IListTerm<Dec> prefix) {
		List_Dec head = this;
		Dec[] prefixElems = prefix.toArray();
		for (int idx = prefixElems.length - 1; idx >= 0; idx--) {
			head = new Cons_Dec(prefixElems[idx], head, head.getStrategoTerm());
		}
		return head;
	}

	public static final List_Dec fromArray(Dec[] elems) {
		List_Dec tail = new Nil_Dec();
		for (int idx = elems.length - 1; idx >= 0; idx--) {
			tail = new Cons_Dec(elems[idx], tail, null);
		}
		return tail;
	}

	@Override
	public Dec[] toArray() {
		if (size == 0) {
			return new Dec[0];
		}
		Dec[] arr = new Dec[size()];
		Cons_Dec head = (Cons_Dec) this;
		for (int idx = 0; idx < arr.length; idx++) {
			arr[idx] = head.elem;
			List_Dec tail = head.tail;
			if (tail instanceof Cons_Dec) {
				head = (Cons_Dec) tail;
			}
		}
		return arr;
	}

	@Override
	public List_Dec reverse() {
		IStrategoTerm sterm = getStrategoTerm();
		List_Dec result = new Nil_Dec();
		Dec[] elems = toArray();
		for (int idx = 0; idx < elems.length; idx++) {
			result = new Cons_Dec(elems[idx], result, sterm);
		}
		return result;
	}

	@Override
	public Iterator<Dec> iterator() {
		return new ConsNilIterator<Dec>(this);
	}

	@Override
	@TruffleBoundary
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		List_Dec head = this;
		while (head instanceof Cons_Dec) {
			str.append(head.elem());
			head = head.tail();
			if (head instanceof Cons_Dec) {
				str.append(", ");
			}
		}
		return str.append("]").toString();
	}

	public static final class Cons_Dec extends List_Dec {
		private final Dec elem;

		private final List_Dec tail;

		public Cons_Dec(Dec elem, List_Dec tail, IStrategoTerm strategoTerm) {
			super(strategoTerm, 1 + tail.size());
			this.elem = elem;
			this.tail = tail;
		}

		@Override
		public Dec elem() {
			return elem;
		}

		@Override
		public List_Dec tail() {
			return tail;
		}

		@Override
		public Dec get(int idx) {
			if (idx == 0) {
				return elem;
			} else {
				return tail.get(idx - 1);
			}
		}

		@Override
		public List_Dec drop(int numElems) {
			if (numElems == 0) {
				return this;
			} else {
				return tail.drop(numElems - 1);
			}
		}
	}

	public static final class Nil_Dec extends List_Dec {
		private Nil_Dec() {
			super(null, 0);
		}

		@Override
		public Dec elem() {
			throw new IllegalStateException("No elem in a Nil");
		}

		public Nil_Dec tail() {
			throw new IllegalStateException("No tail of a Nil");
		}

		@Override
		public List_Dec reverse() {
			return this;
		}

		public List_Dec drop(int idx) {
			if (idx == 0)
				return this;
			throw new IllegalStateException("Nothing to drop from a Nil");
		}

		@Override
		public Dec get(int idx) {
			throw new IllegalStateException("No elems in a Nil");
		}
	}

	@TruffleBoundary
	public static List_Dec create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermList(term);
		IStrategoList l = (IStrategoList) term;
		List_Dec res = EMPTY;
		for (int idx = l.size() - 1; idx >= 0; idx--) {
			final int final_idx = idx;
			res = new Cons_Dec(Dec.create(l.getSubterm(final_idx)), res, l);
		}
		return res;
	}
}