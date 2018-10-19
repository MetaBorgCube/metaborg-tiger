package org.metaborg.lang.tiger.interpreter.generated.terms;

import java.util.Iterator;

import org.metaborg.meta.lang.dynsem.interpreter.terms.ConsNilIterator;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class List_ScopeIdentifier0060c7a5
		implements IListTerm<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier> {
	private final IStrategoTerm strategoTerm;

	protected final int size;

	public final static Nil_ScopeIdentifier0060c7a5 EMPTY = new Nil_ScopeIdentifier0060c7a5();

	public List_ScopeIdentifier0060c7a5(IStrategoTerm strategoTerm, int size) {
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
	public abstract org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier elem();

	@Override
	public abstract List_ScopeIdentifier0060c7a5 tail();

	@Override
	public abstract List_ScopeIdentifier0060c7a5 drop(int numElems);

	@Override
	public Cons_ScopeIdentifier0060c7a5 prefix(
			org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier prefix) {
		return new Cons_ScopeIdentifier0060c7a5(prefix, this, this.getStrategoTerm());
	}

	@Override
	public IListTerm<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier> prefixAll(
			IListTerm<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier> prefix) {
		List_ScopeIdentifier0060c7a5 head = this;
		org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier[] prefixElems = prefix.toArray();
		for (int idx = prefixElems.length - 1; idx >= 0; idx--) {
			head = new Cons_ScopeIdentifier0060c7a5(prefixElems[idx], head, head.getStrategoTerm());
		}
		return head;
	}

	public static final List_ScopeIdentifier0060c7a5 fromArray(
			org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier[] elems) {
		List_ScopeIdentifier0060c7a5 tail = new Nil_ScopeIdentifier0060c7a5();
		for (int idx = elems.length - 1; idx >= 0; idx--) {
			tail = new Cons_ScopeIdentifier0060c7a5(elems[idx], tail, null);
		}
		return tail;
	}

	@Override
	public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier[] toArray() {
		if (size == 0) {
			return new org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier[0];
		}
		org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier[] arr = new org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier[size()];
		Cons_ScopeIdentifier0060c7a5 head = (Cons_ScopeIdentifier0060c7a5) this;
		for (int idx = 0; idx < arr.length; idx++) {
			arr[idx] = head.elem;
			List_ScopeIdentifier0060c7a5 tail = head.tail;
			if (tail instanceof Cons_ScopeIdentifier0060c7a5) {
				head = (Cons_ScopeIdentifier0060c7a5) tail;
			}
		}
		return arr;
	}

	@Override
	public List_ScopeIdentifier0060c7a5 reverse() {
		IStrategoTerm sterm = getStrategoTerm();
		List_ScopeIdentifier0060c7a5 result = new Nil_ScopeIdentifier0060c7a5();
		org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier[] elems = toArray();
		for (int idx = 0; idx < elems.length; idx++) {
			result = new Cons_ScopeIdentifier0060c7a5(elems[idx], result, sterm);
		}
		return result;
	}

	@Override
	public Iterator<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier> iterator() {
		return new ConsNilIterator<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier>(this);
	}

	@Override
	@TruffleBoundary
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		List_ScopeIdentifier0060c7a5 head = this;
		while (head instanceof Cons_ScopeIdentifier0060c7a5) {
			str.append(head.elem());
			head = head.tail();
			if (head instanceof Cons_ScopeIdentifier0060c7a5) {
				str.append(", ");
			}
		}
		return str.append("]").toString();
	}

	public static final class Cons_ScopeIdentifier0060c7a5 extends List_ScopeIdentifier0060c7a5 {
		private final org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier elem;

		private final List_ScopeIdentifier0060c7a5 tail;

		public Cons_ScopeIdentifier0060c7a5(org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier elem,
				List_ScopeIdentifier0060c7a5 tail, IStrategoTerm strategoTerm) {
			super(strategoTerm, 1 + tail.size());
			this.elem = elem;
			this.tail = tail;
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier elem() {
			return elem;
		}

		@Override
		public List_ScopeIdentifier0060c7a5 tail() {
			return tail;
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier get(int idx) {
			if (idx == 0) {
				return elem;
			} else {
				return tail.get(idx - 1);
			}
		}

		@Override
		public List_ScopeIdentifier0060c7a5 drop(int numElems) {
			if (numElems == 0) {
				return this;
			} else {
				return tail.drop(numElems - 1);
			}
		}
	}

	public static final class Nil_ScopeIdentifier0060c7a5 extends List_ScopeIdentifier0060c7a5 {
		private Nil_ScopeIdentifier0060c7a5() {
			super(null, 0);
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier elem() {
			throw new IllegalStateException("No elem in a Nil");
		}

		public Nil_ScopeIdentifier0060c7a5 tail() {
			throw new IllegalStateException("No tail of a Nil");
		}

		@Override
		public List_ScopeIdentifier0060c7a5 reverse() {
			return this;
		}

		public List_ScopeIdentifier0060c7a5 drop(int idx) {
			if (idx == 0)
				return this;
			throw new IllegalStateException("Nothing to drop from a Nil");
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier get(int idx) {
			throw new IllegalStateException("No elems in a Nil");
		}
	}

	@TruffleBoundary
	public static List_ScopeIdentifier0060c7a5 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermList(term);
		IStrategoList l = (IStrategoList) term;
		List_ScopeIdentifier0060c7a5 res = EMPTY;
		for (int idx = l.size() - 1; idx >= 0; idx--) {
			final int final_idx = idx;
			res = new Cons_ScopeIdentifier0060c7a5(
					org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier.create(l.getSubterm(final_idx)),
					res, l);
		}
		return res;
	}
}