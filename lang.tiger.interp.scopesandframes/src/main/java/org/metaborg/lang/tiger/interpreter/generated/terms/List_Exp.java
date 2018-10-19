package org.metaborg.lang.tiger.interpreter.generated.terms;

import java.util.Iterator;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.meta.lang.dynsem.interpreter.terms.ConsNilIterator;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class List_Exp implements IListTerm<Exp> {
	private final IStrategoTerm strategoTerm;

	protected final int size;

	public final static Nil_Exp EMPTY = new Nil_Exp();

	public List_Exp(IStrategoTerm strategoTerm, int size) {
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
	public abstract Exp elem();

	@Override
	public abstract List_Exp tail();

	@Override
	public abstract List_Exp drop(int numElems);

	@Override
	public Cons_Exp prefix(Exp prefix) {
		return new Cons_Exp(prefix, this, this.getStrategoTerm());
	}

	@Override
	public IListTerm<Exp> prefixAll(IListTerm<Exp> prefix) {
		List_Exp head = this;
		Exp[] prefixElems = prefix.toArray();
		for (int idx = prefixElems.length - 1; idx >= 0; idx--) {
			head = new Cons_Exp(prefixElems[idx], head, head.getStrategoTerm());
		}
		return head;
	}

	public static final List_Exp fromArray(Exp[] elems) {
		List_Exp tail = new Nil_Exp();
		for (int idx = elems.length - 1; idx >= 0; idx--) {
			tail = new Cons_Exp(elems[idx], tail, null);
		}
		return tail;
	}

	@Override
	public Exp[] toArray() {
		if (size == 0) {
			return new Exp[0];
		}
		Exp[] arr = new Exp[size()];
		Cons_Exp head = (Cons_Exp) this;
		for (int idx = 0; idx < arr.length; idx++) {
			arr[idx] = head.elem;
			List_Exp tail = head.tail;
			if (tail instanceof Cons_Exp) {
				head = (Cons_Exp) tail;
			}
		}
		return arr;
	}

	@Override
	public List_Exp reverse() {
		IStrategoTerm sterm = getStrategoTerm();
		List_Exp result = new Nil_Exp();
		Exp[] elems = toArray();
		for (int idx = 0; idx < elems.length; idx++) {
			result = new Cons_Exp(elems[idx], result, sterm);
		}
		return result;
	}

	@Override
	public Iterator<Exp> iterator() {
		return new ConsNilIterator<Exp>(this);
	}

	@Override
	@TruffleBoundary
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		List_Exp head = this;
		while (head instanceof Cons_Exp) {
			str.append(head.elem());
			head = head.tail();
			if (head instanceof Cons_Exp) {
				str.append(", ");
			}
		}
		return str.append("]").toString();
	}

	public static final class Cons_Exp extends List_Exp {
		private final Exp elem;

		private final List_Exp tail;

		public Cons_Exp(Exp elem, List_Exp tail, IStrategoTerm strategoTerm) {
			super(strategoTerm, 1 + tail.size());
			this.elem = elem;
			this.tail = tail;
		}

		@Override
		public Exp elem() {
			return elem;
		}

		@Override
		public List_Exp tail() {
			return tail;
		}

		@Override
		public Exp get(int idx) {
			if (idx == 0) {
				return elem;
			} else {
				return tail.get(idx - 1);
			}
		}

		@Override
		public List_Exp drop(int numElems) {
			if (numElems == 0) {
				return this;
			} else {
				return tail.drop(numElems - 1);
			}
		}
	}

	public static final class Nil_Exp extends List_Exp {
		private Nil_Exp() {
			super(null, 0);
		}

		@Override
		public Exp elem() {
			throw new IllegalStateException("No elem in a Nil");
		}

		public Nil_Exp tail() {
			throw new IllegalStateException("No tail of a Nil");
		}

		@Override
		public List_Exp reverse() {
			return this;
		}

		public List_Exp drop(int idx) {
			if (idx == 0)
				return this;
			throw new IllegalStateException("Nothing to drop from a Nil");
		}

		@Override
		public Exp get(int idx) {
			throw new IllegalStateException("No elems in a Nil");
		}
	}

	@TruffleBoundary
	public static List_Exp create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermList(term);
		IStrategoList l = (IStrategoList) term;
		List_Exp res = EMPTY;
		for (int idx = l.size() - 1; idx >= 0; idx--) {
			final int final_idx = idx;
			res = new Cons_Exp(Exp.create(l.getSubterm(final_idx)), res, l);
		}
		return res;
	}
}