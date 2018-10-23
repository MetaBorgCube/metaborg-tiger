package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public final class RecordTy_1 extends Ty {
	public final static String CONSTRUCTOR = "RecordTy";

	public final static int ARITY = 1;

	public RecordTy_1(Field[] _1) {
		this(_1, null);
	}

	private RecordTy_1(Field[] _1, IStrategoTerm strategoTerm) {
		this._1 = _1;
		this.strategoTerm = strategoTerm;
	}

	private final Field[] _1;

	@TruffleBoundary
	public static RecordTy_1 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoTerm[] fieldTerms = Tools.listAt(term, 0).getAllSubterms();
		Field[] fields = new Field[fieldTerms.length];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = Field.create(fieldTerms[i]);
		}
		return new RecordTy_1(fields, term);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecordTy_1 other = (RecordTy_1) obj;
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