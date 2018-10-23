package org.metaborg.lang.tiger.interp.scopesandframes.nodes.bindings;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.metaborg.lang.tiger.interpreter.generated.terms.TypeDec;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class TypeDecs_1 extends Dec {
	public final static String CONSTRUCTOR = "TypeDecs";

	public final static int ARITY = 1;

	public TypeDecs_1(TypeDec[] _1) {
		this(_1, null);
	}

	private TypeDecs_1(TypeDec[] _1, IStrategoTerm strategoTerm) {
		this.typeDecs = _1;
		this.strategoTerm = strategoTerm;
	}

	@Children
	private final TypeDec[] typeDecs;

	@Override
	public void execute(VirtualFrame frame, DynamicObject f, DynamicObject f_outer) {
		// we're not doing anything for type declarations
	}

	@TruffleBoundary
	public static TypeDecs_1 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoTerm[] tyDecTerms = Tools.listAt(term, 0).getAllSubterms();
		TypeDec[] typeDecs = new TypeDec[tyDecTerms.length];
		for (int i = 0; i < typeDecs.length; i++) {
			typeDecs[i] = TypeDec.create(tyDecTerms[i]);
		}
		return new TypeDecs_1(typeDecs, term);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeDecs_1 other = (TypeDecs_1) obj;
		if (typeDecs == null) {
			if (other.typeDecs != null) {
				return false;
			}
		} else if (!typeDecs.equals(other.typeDecs)) {
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
		sb.append(typeDecs);
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
		return new HashCodeBuilder().append(typeDecs).toHashCode();
	}
}