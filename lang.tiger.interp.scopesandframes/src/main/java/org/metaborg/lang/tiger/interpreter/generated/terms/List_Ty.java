package org.metaborg.lang.tiger.interpreter.generated.terms;

import java.util.Iterator;

import org.metaborg.meta.lang.dynsem.interpreter.terms.ConsNilIterator;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class List_Ty implements IListTerm<Ty> {
	private final IStrategoTerm strategoTerm;

	protected final int size;

	public final static Nil_Ty EMPTY = new Nil_Ty();

	public List_Ty(IStrategoTerm strategoTerm, int size) {
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
	public abstract Ty elem();

	@Override
	public abstract List_Ty tail();

	@Override
	public abstract List_Ty drop(int numElems);

	@Override
	public Cons_Ty prefix(Ty prefix) {
		return new Cons_Ty(prefix, this, this.getStrategoTerm());
	}

	@Override
	public IListTerm<Ty> prefixAll(IListTerm<Ty> prefix) {
		List_Ty head = this;
		Ty[] prefixElems = prefix.toArray();
		for (int idx = prefixElems.length - 1; idx >= 0; idx--) {
			head = new Cons_Ty(prefixElems[idx], head, head.getStrategoTerm());
		}
		return head;
	}

	public static final List_Ty fromArray(Ty[] elems) {
		List_Ty tail = new Nil_Ty();
		for (int idx = elems.length - 1; idx >= 0; idx--) {
			tail = new Cons_Ty(elems[idx], tail, null);
		}
		return tail;
	}

	@Override
	public Ty[] toArray() {
		if (size == 0) {
			return new Ty[0];
		}
		Ty[] arr = new Ty[size()];
		Cons_Ty head = (Cons_Ty) this;
		for (int idx = 0; idx < arr.length; idx++) {
			arr[idx] = head.elem;
			List_Ty tail = head.tail;
			if (tail instanceof Cons_Ty) {
				head = (Cons_Ty) tail;
			}
		}
		return arr;
	}

	@Override
	public List_Ty reverse() {
		IStrategoTerm sterm = getStrategoTerm();
		List_Ty result = new Nil_Ty();
		Ty[] elems = toArray();
		for (int idx = 0; idx < elems.length; idx++) {
			result = new Cons_Ty(elems[idx], result, sterm);
		}
		return result;
	}

	@Override
	public Iterator<Ty> iterator() {
		return new ConsNilIterator<Ty>(this);
	}

	@Override
	@TruffleBoundary
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		List_Ty head = this;
		while (head instanceof Cons_Ty) {
			str.append(head.elem());
			head = head.tail();
			if (head instanceof Cons_Ty) {
				str.append(", ");
			}
		}
		return str.append("]").toString();
	}

	public static final class Cons_Ty extends List_Ty {
		private final Ty elem;

		private final List_Ty tail;

		public Cons_Ty(Ty elem, List_Ty tail, IStrategoTerm strategoTerm) {
			super(strategoTerm, 1 + tail.size());
			this.elem = elem;
			this.tail = tail;
		}

		@Override
		public Ty elem() {
			return elem;
		}

		@Override
		public List_Ty tail() {
			return tail;
		}

		@Override
		public Ty get(int idx) {
			if (idx == 0) {
				return elem;
			} else {
				return tail.get(idx - 1);
			}
		}

		@Override
		public List_Ty drop(int numElems) {
			if (numElems == 0) {
				return this;
			} else {
				return tail.drop(numElems - 1);
			}
		}
	}

	public static final class Nil_Ty extends List_Ty {
		private Nil_Ty() {
			super(null, 0);
		}

		@Override
		public Ty elem() {
			throw new IllegalStateException("No elem in a Nil");
		}

		public Nil_Ty tail() {
			throw new IllegalStateException("No tail of a Nil");
		}

		@Override
		public List_Ty reverse() {
			return this;
		}

		public List_Ty drop(int idx) {
			if (idx == 0)
				return this;
			throw new IllegalStateException("Nothing to drop from a Nil");
		}

		@Override
		public Ty get(int idx) {
			throw new IllegalStateException("No elems in a Nil");
		}
	}

	@TruffleBoundary
	public static List_Ty create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermList(term);
		IStrategoList l = (IStrategoList) term;
		List_Ty res = EMPTY;
		for (int idx = l.size() - 1; idx >= 0; idx--) {
			final int final_idx = idx;
			res = new Cons_Ty(Ty.create(l.getSubterm(final_idx)), res, l);
		}
		return res;
	}
}