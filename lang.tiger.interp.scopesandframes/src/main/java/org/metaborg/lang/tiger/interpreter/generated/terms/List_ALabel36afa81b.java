package org.metaborg.lang.tiger.interpreter.generated.terms;

import java.util.Iterator;

import org.metaborg.meta.lang.dynsem.interpreter.terms.ConsNilIterator;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class List_ALabel36afa81b
		implements IListTerm<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel> {
	private final IStrategoTerm strategoTerm;

	protected final int size;

	public final static Nil_ALabel36afa81b EMPTY = new Nil_ALabel36afa81b();

	public List_ALabel36afa81b(IStrategoTerm strategoTerm, int size) {
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
	public abstract org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel elem();

	@Override
	public abstract List_ALabel36afa81b tail();

	@Override
	public abstract List_ALabel36afa81b drop(int numElems);

	@Override
	public Cons_ALabel36afa81b prefix(org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel prefix) {
		return new Cons_ALabel36afa81b(prefix, this, this.getStrategoTerm());
	}

	@Override
	public IListTerm<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel> prefixAll(
			IListTerm<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel> prefix) {
		List_ALabel36afa81b head = this;
		org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel[] prefixElems = prefix.toArray();
		for (int idx = prefixElems.length - 1; idx >= 0; idx--) {
			head = new Cons_ALabel36afa81b(prefixElems[idx], head, head.getStrategoTerm());
		}
		return head;
	}

	public static final List_ALabel36afa81b fromArray(
			org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel[] elems) {
		List_ALabel36afa81b tail = new Nil_ALabel36afa81b();
		for (int idx = elems.length - 1; idx >= 0; idx--) {
			tail = new Cons_ALabel36afa81b(elems[idx], tail, null);
		}
		return tail;
	}

	@Override
	public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel[] toArray() {
		if (size == 0) {
			return new org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel[0];
		}
		org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel[] arr = new org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel[size()];
		Cons_ALabel36afa81b head = (Cons_ALabel36afa81b) this;
		for (int idx = 0; idx < arr.length; idx++) {
			arr[idx] = head.elem;
			List_ALabel36afa81b tail = head.tail;
			if (tail instanceof Cons_ALabel36afa81b) {
				head = (Cons_ALabel36afa81b) tail;
			}
		}
		return arr;
	}

	@Override
	public List_ALabel36afa81b reverse() {
		IStrategoTerm sterm = getStrategoTerm();
		List_ALabel36afa81b result = new Nil_ALabel36afa81b();
		org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel[] elems = toArray();
		for (int idx = 0; idx < elems.length; idx++) {
			result = new Cons_ALabel36afa81b(elems[idx], result, sterm);
		}
		return result;
	}

	@Override
	public Iterator<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel> iterator() {
		return new ConsNilIterator<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel>(this);
	}

	@Override
	@TruffleBoundary
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		List_ALabel36afa81b head = this;
		while (head instanceof Cons_ALabel36afa81b) {
			str.append(head.elem());
			head = head.tail();
			if (head instanceof Cons_ALabel36afa81b) {
				str.append(", ");
			}
		}
		return str.append("]").toString();
	}

	public static final class Cons_ALabel36afa81b extends List_ALabel36afa81b {
		private final org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel elem;

		private final List_ALabel36afa81b tail;

		public Cons_ALabel36afa81b(org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel elem,
				List_ALabel36afa81b tail, IStrategoTerm strategoTerm) {
			super(strategoTerm, 1 + tail.size());
			this.elem = elem;
			this.tail = tail;
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel elem() {
			return elem;
		}

		@Override
		public List_ALabel36afa81b tail() {
			return tail;
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel get(int idx) {
			if (idx == 0) {
				return elem;
			} else {
				return tail.get(idx - 1);
			}
		}

		@Override
		public List_ALabel36afa81b drop(int numElems) {
			if (numElems == 0) {
				return this;
			} else {
				return tail.drop(numElems - 1);
			}
		}
	}

	public static final class Nil_ALabel36afa81b extends List_ALabel36afa81b {
		private Nil_ALabel36afa81b() {
			super(null, 0);
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel elem() {
			throw new IllegalStateException("No elem in a Nil");
		}

		public Nil_ALabel36afa81b tail() {
			throw new IllegalStateException("No tail of a Nil");
		}

		@Override
		public List_ALabel36afa81b reverse() {
			return this;
		}

		public List_ALabel36afa81b drop(int idx) {
			if (idx == 0)
				return this;
			throw new IllegalStateException("Nothing to drop from a Nil");
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel get(int idx) {
			throw new IllegalStateException("No elems in a Nil");
		}
	}

	@TruffleBoundary
	public static List_ALabel36afa81b create(IStrategoTerm term) {
		throw new IllegalStateException("Lists of value terms cannot be created from Stratego terms");
	}
}