package org.metaborg.lang.tiger.interpreter.generated.terms;

import java.util.Iterator;

import org.metaborg.meta.lang.dynsem.interpreter.terms.ConsNilIterator;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class List_FunDec implements IListTerm<FunDec> {
	private final IStrategoTerm strategoTerm;

	protected final int size;

	public final static Nil_FunDec EMPTY = new Nil_FunDec();

	public List_FunDec(IStrategoTerm strategoTerm, int size) {
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
	public abstract FunDec elem();

	@Override
	public abstract List_FunDec tail();

	@Override
	public abstract List_FunDec drop(int numElems);

	@Override
	public Cons_FunDec prefix(FunDec prefix) {
		return new Cons_FunDec(prefix, this, this.getStrategoTerm());
	}

	@Override
	public IListTerm<FunDec> prefixAll(IListTerm<FunDec> prefix) {
		List_FunDec head = this;
		FunDec[] prefixElems = prefix.toArray();
		for (int idx = prefixElems.length - 1; idx >= 0; idx--) {
			head = new Cons_FunDec(prefixElems[idx], head, head.getStrategoTerm());
		}
		return head;
	}

	public static final List_FunDec fromArray(FunDec[] elems) {
		List_FunDec tail = new Nil_FunDec();
		for (int idx = elems.length - 1; idx >= 0; idx--) {
			tail = new Cons_FunDec(elems[idx], tail, null);
		}
		return tail;
	}

	@Override
	public FunDec[] toArray() {
		if (size == 0) {
			return new FunDec[0];
		}
		FunDec[] arr = new FunDec[size()];
		Cons_FunDec head = (Cons_FunDec) this;
		for (int idx = 0; idx < arr.length; idx++) {
			arr[idx] = head.elem;
			List_FunDec tail = head.tail;
			if (tail instanceof Cons_FunDec) {
				head = (Cons_FunDec) tail;
			}
		}
		return arr;
	}

	@Override
	public List_FunDec reverse() {
		IStrategoTerm sterm = getStrategoTerm();
		List_FunDec result = new Nil_FunDec();
		FunDec[] elems = toArray();
		for (int idx = 0; idx < elems.length; idx++) {
			result = new Cons_FunDec(elems[idx], result, sterm);
		}
		return result;
	}

	@Override
	public Iterator<FunDec> iterator() {
		return new ConsNilIterator<FunDec>(this);
	}

	@Override
	@TruffleBoundary
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		List_FunDec head = this;
		while (head instanceof Cons_FunDec) {
			str.append(head.elem());
			head = head.tail();
			if (head instanceof Cons_FunDec) {
				str.append(", ");
			}
		}
		return str.append("]").toString();
	}

	public static final class Cons_FunDec extends List_FunDec {
		private final FunDec elem;

		private final List_FunDec tail;

		public Cons_FunDec(FunDec elem, List_FunDec tail, IStrategoTerm strategoTerm) {
			super(strategoTerm, 1 + tail.size());
			this.elem = elem;
			this.tail = tail;
		}

		@Override
		public FunDec elem() {
			return elem;
		}

		@Override
		public List_FunDec tail() {
			return tail;
		}

		@Override
		public FunDec get(int idx) {
			if (idx == 0) {
				return elem;
			} else {
				return tail.get(idx - 1);
			}
		}

		@Override
		public List_FunDec drop(int numElems) {
			if (numElems == 0) {
				return this;
			} else {
				return tail.drop(numElems - 1);
			}
		}
	}

	public static final class Nil_FunDec extends List_FunDec {
		private Nil_FunDec() {
			super(null, 0);
		}

		@Override
		public FunDec elem() {
			throw new IllegalStateException("No elem in a Nil");
		}

		public Nil_FunDec tail() {
			throw new IllegalStateException("No tail of a Nil");
		}

		@Override
		public List_FunDec reverse() {
			return this;
		}

		public List_FunDec drop(int idx) {
			if (idx == 0)
				return this;
			throw new IllegalStateException("Nothing to drop from a Nil");
		}

		@Override
		public FunDec get(int idx) {
			throw new IllegalStateException("No elems in a Nil");
		}
	}

	@TruffleBoundary
	public static List_FunDec create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermList(term);
		IStrategoList l = (IStrategoList) term;
		List_FunDec res = EMPTY;
		for (int idx = l.size() - 1; idx >= 0; idx--) {
			final int final_idx = idx;
			res = new Cons_FunDec(FunDec.create(l.getSubterm(final_idx)), res, l);
		}
		return res;
	}
}