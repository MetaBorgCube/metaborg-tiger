package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public final class FUN_2 extends Ty {
	public final static String CONSTRUCTOR = "FUN";

	public final static int ARITY = 2;

	public FUN_2(Ty[] _1, Ty _2) {
		this(_1, _2, null);
	}

	private FUN_2(Ty[] _1, Ty _2, IStrategoTerm strategoTerm) {
		this.fArgTys = _1;
		this.returnTy = _2;
		this.strategoTerm = strategoTerm;
	}

	private final Ty[] fArgTys;

	public Ty[] getArgumentTypes() {
		return fArgTys;
	}
	
	private final Ty returnTy;
	
	public Ty getReturnType() {
		return returnTy;
	}

	@TruffleBoundary
	public static FUN_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoTerm[] fargTyTerms = Tools.listAt(term, 0).getAllSubterms();
		Ty[] fargtys = new Ty[fargTyTerms.length];
		for (int i = 0; i < fargTyTerms.length; i++) {
			fargtys[i] = Ty.create(fargTyTerms[i]);
		}
		return new FUN_2(fargtys, Ty.create(term.getSubterm(1)), term);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FUN_2 other = (FUN_2) obj;
		if (fArgTys == null) {
			if (other.fArgTys != null) {
				return false;
			}
		} else if (!fArgTys.equals(other.fArgTys)) {
			return false;
		}
		if (returnTy == null) {
			if (other.returnTy != null) {
				return false;
			}
		} else if (!returnTy.equals(other.returnTy)) {
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
		sb.append(fArgTys);
		sb.append(", ");
		sb.append(returnTy);
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
		return new HashCodeBuilder().append(returnTy).append(fArgTys).toHashCode();
	}
}