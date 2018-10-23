package org.metaborg.lang.tiger.interp.scopesandframes.nodes.numbers;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.IntV_1;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class Divide_2 extends Exp {
	public final static String CONSTRUCTOR = "Divide";

	public final static int ARITY = 2;

	public Divide_2(Exp _1, Exp _2) {
		this(_1, _2, null);
	}

	private Divide_2(Exp _1, Exp _2, IStrategoTerm strategoTerm) {
		this.left = _1;
		this.right = _2;
		this.strategoTerm = strategoTerm;
	}

	@Child
	private Exp left;

	@Child
	private Exp right;

	@Override
	public IntV_1 executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		IntV_1 left = TigerTypesGen.asIntV_1(this.left.executeGeneric(frame, currentFrame));
		IntV_1 right = TigerTypesGen.asIntV_1(this.right.executeGeneric(frame, currentFrame));
		return new IntV_1(left.get_1() / right.get_1());
	}

	public Exp get_1() {
		return left;
	}

	public Exp get_2() {
		return right;
	}

	@TruffleBoundary
	public static Divide_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Divide_2(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), term);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Divide_2 other = (Divide_2) obj;
		if (left == null) {
			if (other.left != null) {
				return false;
			}
		} else if (!left.equals(other.left)) {
			return false;
		}
		if (right == null) {
			if (other.right != null) {
				return false;
			}
		} else if (!right.equals(other.right)) {
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
		sb.append(left);
		sb.append(", ");
		sb.append(right);
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
		return new HashCodeBuilder().append(right).append(left).toHashCode();
	}
}