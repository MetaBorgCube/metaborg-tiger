package org.metaborg.lang.tiger.interpreter.generated.terms;

import java.util.Iterator;

import org.metaborg.meta.lang.dynsem.interpreter.terms.ConsNilIterator;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class List_Occurrence2c7e4fe8
		implements IListTerm<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence> {
	private final IStrategoTerm strategoTerm;

	protected final int size;

	public final static Nil_Occurrence2c7e4fe8 EMPTY = new Nil_Occurrence2c7e4fe8();

	public List_Occurrence2c7e4fe8(IStrategoTerm strategoTerm, int size) {
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
	public abstract org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence elem();

	@Override
	public abstract List_Occurrence2c7e4fe8 tail();

	@Override
	public abstract List_Occurrence2c7e4fe8 drop(int numElems);

	@Override
	public Cons_Occurrence2c7e4fe8 prefix(org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence prefix) {
		return new Cons_Occurrence2c7e4fe8(prefix, this, this.getStrategoTerm());
	}

	@Override
	public IListTerm<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence> prefixAll(
			IListTerm<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence> prefix) {
		List_Occurrence2c7e4fe8 head = this;
		org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence[] prefixElems = prefix.toArray();
		for (int idx = prefixElems.length - 1; idx >= 0; idx--) {
			head = new Cons_Occurrence2c7e4fe8(prefixElems[idx], head, head.getStrategoTerm());
		}
		return head;
	}

	public static final List_Occurrence2c7e4fe8 fromArray(
			org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence[] elems) {
		List_Occurrence2c7e4fe8 tail = new Nil_Occurrence2c7e4fe8();
		for (int idx = elems.length - 1; idx >= 0; idx--) {
			tail = new Cons_Occurrence2c7e4fe8(elems[idx], tail, null);
		}
		return tail;
	}

	@Override
	public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence[] toArray() {
		if (size == 0) {
			return new org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence[0];
		}
		org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence[] arr = new org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence[size()];
		Cons_Occurrence2c7e4fe8 head = (Cons_Occurrence2c7e4fe8) this;
		for (int idx = 0; idx < arr.length; idx++) {
			arr[idx] = head.elem;
			List_Occurrence2c7e4fe8 tail = head.tail;
			if (tail instanceof Cons_Occurrence2c7e4fe8) {
				head = (Cons_Occurrence2c7e4fe8) tail;
			}
		}
		return arr;
	}

	@Override
	public List_Occurrence2c7e4fe8 reverse() {
		IStrategoTerm sterm = getStrategoTerm();
		List_Occurrence2c7e4fe8 result = new Nil_Occurrence2c7e4fe8();
		org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence[] elems = toArray();
		for (int idx = 0; idx < elems.length; idx++) {
			result = new Cons_Occurrence2c7e4fe8(elems[idx], result, sterm);
		}
		return result;
	}

	@Override
	public Iterator<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence> iterator() {
		return new ConsNilIterator<org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence>(this);
	}

	@Override
	@TruffleBoundary
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		List_Occurrence2c7e4fe8 head = this;
		while (head instanceof Cons_Occurrence2c7e4fe8) {
			str.append(head.elem());
			head = head.tail();
			if (head instanceof Cons_Occurrence2c7e4fe8) {
				str.append(", ");
			}
		}
		return str.append("]").toString();
	}

	public static final class Cons_Occurrence2c7e4fe8 extends List_Occurrence2c7e4fe8 {
		private final org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence elem;

		private final List_Occurrence2c7e4fe8 tail;

		public Cons_Occurrence2c7e4fe8(org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence elem,
				List_Occurrence2c7e4fe8 tail, IStrategoTerm strategoTerm) {
			super(strategoTerm, 1 + tail.size());
			this.elem = elem;
			this.tail = tail;
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence elem() {
			return elem;
		}

		@Override
		public List_Occurrence2c7e4fe8 tail() {
			return tail;
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence get(int idx) {
			if (idx == 0) {
				return elem;
			} else {
				return tail.get(idx - 1);
			}
		}

		@Override
		public List_Occurrence2c7e4fe8 drop(int numElems) {
			if (numElems == 0) {
				return this;
			} else {
				return tail.drop(numElems - 1);
			}
		}
	}

	public static final class Nil_Occurrence2c7e4fe8 extends List_Occurrence2c7e4fe8 {
		private Nil_Occurrence2c7e4fe8() {
			super(null, 0);
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence elem() {
			throw new IllegalStateException("No elem in a Nil");
		}

		public Nil_Occurrence2c7e4fe8 tail() {
			throw new IllegalStateException("No tail of a Nil");
		}

		@Override
		public List_Occurrence2c7e4fe8 reverse() {
			return this;
		}

		public List_Occurrence2c7e4fe8 drop(int idx) {
			if (idx == 0)
				return this;
			throw new IllegalStateException("Nothing to drop from a Nil");
		}

		@Override
		public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence get(int idx) {
			throw new IllegalStateException("No elems in a Nil");
		}
	}

	@TruffleBoundary
	public static List_Occurrence2c7e4fe8 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermList(term);
		IStrategoList l = (IStrategoList) term;
		List_Occurrence2c7e4fe8 res = EMPTY;
		for (int idx = l.size() - 1; idx >= 0; idx--) {
			final int final_idx = idx;
			res = new Cons_Occurrence2c7e4fe8(
					org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence.create(l.getSubterm(final_idx)), res,
					l);
		}
		return res;
	}
}