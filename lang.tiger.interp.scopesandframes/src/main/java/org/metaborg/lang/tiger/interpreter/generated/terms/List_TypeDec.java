package org.metaborg.lang.tiger.interpreter.generated.terms;

import java.util.Iterator;

import org.metaborg.meta.lang.dynsem.interpreter.terms.ConsNilIterator;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class List_TypeDec implements IListTerm<TypeDec> {
	private final IStrategoTerm strategoTerm;

	protected final int size;

	public final static Nil_TypeDec EMPTY = new Nil_TypeDec();

	public List_TypeDec(IStrategoTerm strategoTerm, int size) {
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
	public abstract TypeDec elem();

	@Override
	public abstract List_TypeDec tail();

	@Override
	public abstract List_TypeDec drop(int numElems);

	@Override
	public Cons_TypeDec prefix(TypeDec prefix) {
		return new Cons_TypeDec(prefix, this, this.getStrategoTerm());
	}

	@Override
	public IListTerm<TypeDec> prefixAll(IListTerm<TypeDec> prefix) {
		List_TypeDec head = this;
		TypeDec[] prefixElems = prefix.toArray();
		for (int idx = prefixElems.length - 1; idx >= 0; idx--) {
			head = new Cons_TypeDec(prefixElems[idx], head, head.getStrategoTerm());
		}
		return head;
	}

	public static final List_TypeDec fromArray(TypeDec[] elems) {
		List_TypeDec tail = new Nil_TypeDec();
		for (int idx = elems.length - 1; idx >= 0; idx--) {
			tail = new Cons_TypeDec(elems[idx], tail, null);
		}
		return tail;
	}

	@Override
	public TypeDec[] toArray() {
		if (size == 0) {
			return new TypeDec[0];
		}
		TypeDec[] arr = new TypeDec[size()];
		Cons_TypeDec head = (Cons_TypeDec) this;
		for (int idx = 0; idx < arr.length; idx++) {
			arr[idx] = head.elem;
			List_TypeDec tail = head.tail;
			if (tail instanceof Cons_TypeDec) {
				head = (Cons_TypeDec) tail;
			}
		}
		return arr;
	}

	@Override
	public List_TypeDec reverse() {
		IStrategoTerm sterm = getStrategoTerm();
		List_TypeDec result = new Nil_TypeDec();
		TypeDec[] elems = toArray();
		for (int idx = 0; idx < elems.length; idx++) {
			result = new Cons_TypeDec(elems[idx], result, sterm);
		}
		return result;
	}

	@Override
	public Iterator<TypeDec> iterator() {
		return new ConsNilIterator<TypeDec>(this);
	}

	@Override
	@TruffleBoundary
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		List_TypeDec head = this;
		while (head instanceof Cons_TypeDec) {
			str.append(head.elem());
			head = head.tail();
			if (head instanceof Cons_TypeDec) {
				str.append(", ");
			}
		}
		return str.append("]").toString();
	}

	public static final class Cons_TypeDec extends List_TypeDec {
		private final TypeDec elem;

		private final List_TypeDec tail;

		public Cons_TypeDec(TypeDec elem, List_TypeDec tail, IStrategoTerm strategoTerm) {
			super(strategoTerm, 1 + tail.size());
			this.elem = elem;
			this.tail = tail;
		}

		@Override
		public TypeDec elem() {
			return elem;
		}

		@Override
		public List_TypeDec tail() {
			return tail;
		}

		@Override
		public TypeDec get(int idx) {
			if (idx == 0) {
				return elem;
			} else {
				return tail.get(idx - 1);
			}
		}

		@Override
		public List_TypeDec drop(int numElems) {
			if (numElems == 0) {
				return this;
			} else {
				return tail.drop(numElems - 1);
			}
		}
	}

	public static final class Nil_TypeDec extends List_TypeDec {
		private Nil_TypeDec() {
			super(null, 0);
		}

		@Override
		public TypeDec elem() {
			throw new IllegalStateException("No elem in a Nil");
		}

		public Nil_TypeDec tail() {
			throw new IllegalStateException("No tail of a Nil");
		}

		@Override
		public List_TypeDec reverse() {
			return this;
		}

		public List_TypeDec drop(int idx) {
			if (idx == 0)
				return this;
			throw new IllegalStateException("Nothing to drop from a Nil");
		}

		@Override
		public TypeDec get(int idx) {
			throw new IllegalStateException("No elems in a Nil");
		}
	}

	@TruffleBoundary
	public static List_TypeDec create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermList(term);
		IStrategoList l = (IStrategoList) term;
		List_TypeDec res = EMPTY;
		for (int idx = l.size() - 1; idx >= 0; idx--) {
			final int final_idx = idx;
			res = new Cons_TypeDec(TypeDec.create(l.getSubterm(final_idx)), res, l);
		}
		return res;
	}
}