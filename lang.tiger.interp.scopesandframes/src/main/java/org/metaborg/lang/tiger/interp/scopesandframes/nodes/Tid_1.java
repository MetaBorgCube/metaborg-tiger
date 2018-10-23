package org.metaborg.lang.tiger.interp.scopesandframes.nodes;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.metaborg.lang.tiger.interpreter.generated.terms.Occ;
import org.metaborg.lang.tiger.interpreter.generated.terms.Ty;
import org.metaborg.lang.tiger.interpreter.generated.terms.__Occurrence2Occ___1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.DecOfRef;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.DecOfRefNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.TypeOfDec;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.TypeOfDecNodeGen;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;

public final class Tid_1 extends TypeId {
	public final static String CONSTRUCTOR = "Tid";

	public final static int ARITY = 1;

	public Tid_1(Occ _1) {
		this(_1, null);
	}

	private Tid_1(Occ _1, IStrategoTerm strategoTerm) {
		this.refOcc = ((__Occurrence2Occ___1) _1).get_1();
		this.strategoTerm = strategoTerm;
		this.typeOf = TypeOfDecNodeGen.create();
		this.resolve = DecOfRefNodeGen.create();
	}

	private final Occurrence refOcc;

	@Child
	private TypeOfDec typeOf;
	@Child
	private DecOfRef resolve;

	@Override
	public Ty execute(VirtualFrame frame) {
		return typeOf.execute(frame, resolve.execute(frame, refOcc));
	}

	@TruffleBoundary
	public static Tid_1 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Tid_1(Occ.create(term.getSubterm(0)), term);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tid_1 other = (Tid_1) obj;
		if (refOcc == null) {
			if (other.refOcc != null) {
				return false;
			}
		} else if (!refOcc.equals(other.refOcc)) {
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
		sb.append(refOcc);
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
		return new HashCodeBuilder().append(refOcc).toHashCode();
	}
}