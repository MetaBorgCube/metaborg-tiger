package org.metaborg.lang.tiger.interpreter.generated.terms;

import java.util.Iterator;

import org.metaborg.meta.lang.dynsem.interpreter.terms.ConsNilIterator;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class List_FLink0432532a implements IListTerm<org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink> {
	private final IStrategoTerm strategoTerm;

	protected final int size;

	public final static Nil_FLink0432532a EMPTY = new Nil_FLink0432532a();

	public List_FLink0432532a(IStrategoTerm strategoTerm, int size) {
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
	public abstract org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink elem();

	@Override
	public abstract List_FLink0432532a tail();

	@Override
	public abstract List_FLink0432532a drop(int numElems);

	@Override
	public Cons_FLink0432532a prefix(org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink prefix) {
		return new Cons_FLink0432532a(prefix, this, this.getStrategoTerm());
	}

	@Override
	public IListTerm<org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink> prefixAll(
			IListTerm<org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink> prefix) {
		List_FLink0432532a head = this;
		org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink[] prefixElems = prefix.toArray();
		for (int idx = prefixElems.length - 1; idx >= 0; idx--) {
			head = new Cons_FLink0432532a(prefixElems[idx], head, head.getStrategoTerm());
		}
		return head;
	}

	public static final List_FLink0432532a fromArray(org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink[] elems) {
		List_FLink0432532a tail = new Nil_FLink0432532a();
		for (int idx = elems.length - 1; idx >= 0; idx--) {
			tail = new Cons_FLink0432532a(elems[idx], tail, null);
		}
		return tail;
	}

	@Override
	public org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink[] toArray() {
		if (size == 0) {
			return new org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink[0];
		}
		org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink[] arr = new org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink[size()];
		Cons_FLink0432532a head = (Cons_FLink0432532a) this;
		for (int idx = 0; idx < arr.length; idx++) {
			arr[idx] = head.elem;
			List_FLink0432532a tail = head.tail;
			if (tail instanceof Cons_FLink0432532a) {
				head = (Cons_FLink0432532a) tail;
			}
		}
		return arr;
	}

	@Override
	public List_FLink0432532a reverse() {
		IStrategoTerm sterm = getStrategoTerm();
		List_FLink0432532a result = new Nil_FLink0432532a();
		org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink[] elems = toArray();
		for (int idx = 0; idx < elems.length; idx++) {
			result = new Cons_FLink0432532a(elems[idx], result, sterm);
		}
		return result;
	}

	@Override
	public Iterator<org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink> iterator() {
		return new ConsNilIterator<org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink>(this);
	}

	@Override
	@TruffleBoundary
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		List_FLink0432532a head = this;
		while (head instanceof Cons_FLink0432532a) {
			str.append(head.elem());
			head = head.tail();
			if (head instanceof Cons_FLink0432532a) {
				str.append(", ");
			}
		}
		return str.append("]").toString();
	}

	public static final class Cons_FLink0432532a extends List_FLink0432532a {
		private final org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink elem;

		private final List_FLink0432532a tail;

		public Cons_FLink0432532a(org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink elem, List_FLink0432532a tail,
				IStrategoTerm strategoTerm) {
			super(strategoTerm, 1 + tail.size());
			this.elem = elem;
			this.tail = tail;
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink elem() {
			return elem;
		}

		@Override
		public List_FLink0432532a tail() {
			return tail;
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink get(int idx) {
			if (idx == 0) {
				return elem;
			} else {
				return tail.get(idx - 1);
			}
		}

		@Override
		public List_FLink0432532a drop(int numElems) {
			if (numElems == 0) {
				return this;
			} else {
				return tail.drop(numElems - 1);
			}
		}
	}

	public static final class Nil_FLink0432532a extends List_FLink0432532a {
		private Nil_FLink0432532a() {
			super(null, 0);
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink elem() {
			throw new IllegalStateException("No elem in a Nil");
		}

		public Nil_FLink0432532a tail() {
			throw new IllegalStateException("No tail of a Nil");
		}

		@Override
		public List_FLink0432532a reverse() {
			return this;
		}

		public List_FLink0432532a drop(int idx) {
			if (idx == 0)
				return this;
			throw new IllegalStateException("Nothing to drop from a Nil");
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink get(int idx) {
			throw new IllegalStateException("No elems in a Nil");
		}
	}

	@TruffleBoundary
	public static List_FLink0432532a create(IStrategoTerm term) {
		throw new IllegalStateException("Lists of value terms cannot be created from Stratego terms");
	}
}