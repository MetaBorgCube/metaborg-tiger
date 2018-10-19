package org.metaborg.lang.tiger.interpreter.generated.terms;

import java.util.Iterator;

import org.metaborg.meta.lang.dynsem.interpreter.terms.ConsNilIterator;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class List_FArg implements IListTerm<FArg> {
	private final IStrategoTerm strategoTerm;

	protected final int size;

	public final static Nil_FArg EMPTY = new Nil_FArg();

	public List_FArg(IStrategoTerm strategoTerm, int size) {
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
	public abstract FArg elem();

	@Override
	public abstract List_FArg tail();

	@Override
	public abstract List_FArg drop(int numElems);

	@Override
	public Cons_FArg prefix(FArg prefix) {
		return new Cons_FArg(prefix, this, this.getStrategoTerm());
	}

	@Override
	public IListTerm<FArg> prefixAll(IListTerm<FArg> prefix) {
		List_FArg head = this;
		FArg[] prefixElems = prefix.toArray();
		for (int idx = prefixElems.length - 1; idx >= 0; idx--) {
			head = new Cons_FArg(prefixElems[idx], head, head.getStrategoTerm());
		}
		return head;
	}

	public static final List_FArg fromArray(FArg[] elems) {
		List_FArg tail = new Nil_FArg();
		for (int idx = elems.length - 1; idx >= 0; idx--) {
			tail = new Cons_FArg(elems[idx], tail, null);
		}
		return tail;
	}

	@Override
	public FArg[] toArray() {
		if (size == 0) {
			return new FArg[0];
		}
		FArg[] arr = new FArg[size()];
		Cons_FArg head = (Cons_FArg) this;
		for (int idx = 0; idx < arr.length; idx++) {
			arr[idx] = head.elem;
			List_FArg tail = head.tail;
			if (tail instanceof Cons_FArg) {
				head = (Cons_FArg) tail;
			}
		}
		return arr;
	}

	@Override
	public List_FArg reverse() {
		IStrategoTerm sterm = getStrategoTerm();
		List_FArg result = new Nil_FArg();
		FArg[] elems = toArray();
		for (int idx = 0; idx < elems.length; idx++) {
			result = new Cons_FArg(elems[idx], result, sterm);
		}
		return result;
	}

	@Override
	public Iterator<FArg> iterator() {
		return new ConsNilIterator<FArg>(this);
	}

	@Override
	@TruffleBoundary
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		List_FArg head = this;
		while (head instanceof Cons_FArg) {
			str.append(head.elem());
			head = head.tail();
			if (head instanceof Cons_FArg) {
				str.append(", ");
			}
		}
		return str.append("]").toString();
	}

	public static final class Cons_FArg extends List_FArg {
		private final FArg elem;

		private final List_FArg tail;

		public Cons_FArg(FArg elem, List_FArg tail, IStrategoTerm strategoTerm) {
			super(strategoTerm, 1 + tail.size());
			this.elem = elem;
			this.tail = tail;
		}

		@Override
		public FArg elem() {
			return elem;
		}

		@Override
		public List_FArg tail() {
			return tail;
		}

		@Override
		public FArg get(int idx) {
			if (idx == 0) {
				return elem;
			} else {
				return tail.get(idx - 1);
			}
		}

		@Override
		public List_FArg drop(int numElems) {
			if (numElems == 0) {
				return this;
			} else {
				return tail.drop(numElems - 1);
			}
		}
	}

	public static final class Nil_FArg extends List_FArg {
		private Nil_FArg() {
			super(null, 0);
		}

		@Override
		public FArg elem() {
			throw new IllegalStateException("No elem in a Nil");
		}

		public Nil_FArg tail() {
			throw new IllegalStateException("No tail of a Nil");
		}

		@Override
		public List_FArg reverse() {
			return this;
		}

		public List_FArg drop(int idx) {
			if (idx == 0)
				return this;
			throw new IllegalStateException("Nothing to drop from a Nil");
		}

		@Override
		public FArg get(int idx) {
			throw new IllegalStateException("No elems in a Nil");
		}
	}

	@TruffleBoundary
	public static List_FArg create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermList(term);
		IStrategoList l = (IStrategoList) term;
		List_FArg res = EMPTY;
		for (int idx = l.size() - 1; idx >= 0; idx--) {
			final int final_idx = idx;
			res = new Cons_FArg(FArg.create(l.getSubterm(final_idx)), res, l);
		}
		return res;
	}
}