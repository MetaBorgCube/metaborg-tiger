package org.metaborg.lang.tiger.interpreter.generated.terms;

import java.util.Iterator;

import org.metaborg.meta.lang.dynsem.interpreter.terms.ConsNilIterator;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class List_List_ScopeIdentifier0060c7a5419607b5 implements IListTerm<List_ScopeIdentifier0060c7a5> {
	private final IStrategoTerm strategoTerm;

	protected final int size;

	public final static Nil_List_ScopeIdentifier0060c7a5419607b5 EMPTY = new Nil_List_ScopeIdentifier0060c7a5419607b5();

	public List_List_ScopeIdentifier0060c7a5419607b5(IStrategoTerm strategoTerm, int size) {
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
	public abstract List_ScopeIdentifier0060c7a5 elem();

	@Override
	public abstract List_List_ScopeIdentifier0060c7a5419607b5 tail();

	@Override
	public abstract List_List_ScopeIdentifier0060c7a5419607b5 drop(int numElems);

	@Override
	public Cons_List_ScopeIdentifier0060c7a5419607b5 prefix(List_ScopeIdentifier0060c7a5 prefix) {
		return new Cons_List_ScopeIdentifier0060c7a5419607b5(prefix, this, this.getStrategoTerm());
	}

	@Override
	public IListTerm<List_ScopeIdentifier0060c7a5> prefixAll(IListTerm<List_ScopeIdentifier0060c7a5> prefix) {
		List_List_ScopeIdentifier0060c7a5419607b5 head = this;
		List_ScopeIdentifier0060c7a5[] prefixElems = prefix.toArray();
		for (int idx = prefixElems.length - 1; idx >= 0; idx--) {
			head = new Cons_List_ScopeIdentifier0060c7a5419607b5(prefixElems[idx], head, head.getStrategoTerm());
		}
		return head;
	}

	public static final List_List_ScopeIdentifier0060c7a5419607b5 fromArray(List_ScopeIdentifier0060c7a5[] elems) {
		List_List_ScopeIdentifier0060c7a5419607b5 tail = new Nil_List_ScopeIdentifier0060c7a5419607b5();
		for (int idx = elems.length - 1; idx >= 0; idx--) {
			tail = new Cons_List_ScopeIdentifier0060c7a5419607b5(elems[idx], tail, null);
		}
		return tail;
	}

	@Override
	public List_ScopeIdentifier0060c7a5[] toArray() {
		if (size == 0) {
			return new List_ScopeIdentifier0060c7a5[0];
		}
		List_ScopeIdentifier0060c7a5[] arr = new List_ScopeIdentifier0060c7a5[size()];
		Cons_List_ScopeIdentifier0060c7a5419607b5 head = (Cons_List_ScopeIdentifier0060c7a5419607b5) this;
		for (int idx = 0; idx < arr.length; idx++) {
			arr[idx] = head.elem;
			List_List_ScopeIdentifier0060c7a5419607b5 tail = head.tail;
			if (tail instanceof Cons_List_ScopeIdentifier0060c7a5419607b5) {
				head = (Cons_List_ScopeIdentifier0060c7a5419607b5) tail;
			}
		}
		return arr;
	}

	@Override
	public List_List_ScopeIdentifier0060c7a5419607b5 reverse() {
		IStrategoTerm sterm = getStrategoTerm();
		List_List_ScopeIdentifier0060c7a5419607b5 result = new Nil_List_ScopeIdentifier0060c7a5419607b5();
		List_ScopeIdentifier0060c7a5[] elems = toArray();
		for (int idx = 0; idx < elems.length; idx++) {
			result = new Cons_List_ScopeIdentifier0060c7a5419607b5(elems[idx], result, sterm);
		}
		return result;
	}

	@Override
	public Iterator<List_ScopeIdentifier0060c7a5> iterator() {
		return new ConsNilIterator<List_ScopeIdentifier0060c7a5>(this);
	}

	@Override
	@TruffleBoundary
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		List_List_ScopeIdentifier0060c7a5419607b5 head = this;
		while (head instanceof Cons_List_ScopeIdentifier0060c7a5419607b5) {
			str.append(head.elem());
			head = head.tail();
			if (head instanceof Cons_List_ScopeIdentifier0060c7a5419607b5) {
				str.append(", ");
			}
		}
		return str.append("]").toString();
	}

	public static final class Cons_List_ScopeIdentifier0060c7a5419607b5
			extends List_List_ScopeIdentifier0060c7a5419607b5 {
		private final List_ScopeIdentifier0060c7a5 elem;

		private final List_List_ScopeIdentifier0060c7a5419607b5 tail;

		public Cons_List_ScopeIdentifier0060c7a5419607b5(List_ScopeIdentifier0060c7a5 elem,
				List_List_ScopeIdentifier0060c7a5419607b5 tail, IStrategoTerm strategoTerm) {
			super(strategoTerm, 1 + tail.size());
			this.elem = elem;
			this.tail = tail;
		}

		@Override
		public List_ScopeIdentifier0060c7a5 elem() {
			return elem;
		}

		@Override
		public List_List_ScopeIdentifier0060c7a5419607b5 tail() {
			return tail;
		}

		@Override
		public List_ScopeIdentifier0060c7a5 get(int idx) {
			if (idx == 0) {
				return elem;
			} else {
				return tail.get(idx - 1);
			}
		}

		@Override
		public List_List_ScopeIdentifier0060c7a5419607b5 drop(int numElems) {
			if (numElems == 0) {
				return this;
			} else {
				return tail.drop(numElems - 1);
			}
		}
	}

	public static final class Nil_List_ScopeIdentifier0060c7a5419607b5
			extends List_List_ScopeIdentifier0060c7a5419607b5 {
		private Nil_List_ScopeIdentifier0060c7a5419607b5() {
			super(null, 0);
		}

		@Override
		public List_ScopeIdentifier0060c7a5 elem() {
			throw new IllegalStateException("No elem in a Nil");
		}

		public Nil_List_ScopeIdentifier0060c7a5419607b5 tail() {
			throw new IllegalStateException("No tail of a Nil");
		}

		@Override
		public List_List_ScopeIdentifier0060c7a5419607b5 reverse() {
			return this;
		}

		public List_List_ScopeIdentifier0060c7a5419607b5 drop(int idx) {
			if (idx == 0)
				return this;
			throw new IllegalStateException("Nothing to drop from a Nil");
		}

		@Override
		public List_ScopeIdentifier0060c7a5 get(int idx) {
			throw new IllegalStateException("No elems in a Nil");
		}
	}

	@TruffleBoundary
	public static List_List_ScopeIdentifier0060c7a5419607b5 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermList(term);
		IStrategoList l = (IStrategoList) term;
		List_List_ScopeIdentifier0060c7a5419607b5 res = EMPTY;
		for (int idx = l.size() - 1; idx >= 0; idx--) {
			final int final_idx = idx;
			res = new Cons_List_ScopeIdentifier0060c7a5419607b5(
					List_ScopeIdentifier0060c7a5.create(l.getSubterm(final_idx)), res, l);
		}
		return res;
	}
}