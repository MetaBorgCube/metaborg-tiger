package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public final class __Occurrence2Occ___1 extends Occ {
	public final static String CONSTRUCTOR = "__Occurrence2Occ__";

	public final static int ARITY = 1;

	public __Occurrence2Occ___1(org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence _1) {
		this(_1, null);
	}

	private __Occurrence2Occ___1(org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence _1,
			IStrategoTerm strategoTerm) {
		this._1 = _1;
		this.strategoTerm = strategoTerm;
	}

	private final org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence _1;

	public org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence get_1() {
		return _1;
	}

	@TruffleBoundary
	public static __Occurrence2Occ___1 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new __Occurrence2Occ___1(
				org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence.create(term.getSubterm(0)), term);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		__Occurrence2Occ___1 other = (__Occurrence2Occ___1) obj;
		if (_1 == null) {
			if (other._1 != null) {
				return false;
			}
		} else if (!_1.equals(other._1)) {
			return false;
		}
		return true;
	}

	private final IStrategoTerm strategoTerm;

	@Override
	public int size() {
		return ARITY;
	}

	@Override
	public boolean hasStrategoTerm() {
		return strategoTerm != null;
	}

	@Override
	public IStrategoTerm getStrategoTerm() {
		return strategoTerm;
	}

	@TruffleBoundary
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(CONSTRUCTOR);
		sb.append("(");
		sb.append(_1);
		sb.append(")");
		return sb.toString();
	}

	@CompilationFinal
	private boolean hashCodeComputed;

	@CompilationFinal
	private int hashcode;

	@Override
	public int hashCode() {
		if (!hashCodeComputed) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			hashcode = makeHashCode();
			hashCodeComputed = true;
		}
		return hashcode;
	}

	@TruffleBoundary
	private int makeHashCode() {
		return new HashCodeBuilder().append(_1).toHashCode();
	}
}