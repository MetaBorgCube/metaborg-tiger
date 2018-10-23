package org.metaborg.lang.tiger.interp.scopesandframes.nodes.records;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.TypeId;
import org.metaborg.lang.tiger.interp.scopesandframes.values.RecordV_1;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.Field;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameEdgeLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Framed;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.NewFrame;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.NewFrameNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.I;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.object.DynamicObject;

public final class Record_2 extends Exp {
	public final static String CONSTRUCTOR = "Record";

	public final static int ARITY = 2;

	public Record_2(TypeId _1, InitField[] _2) {
		this(_1, _2, null);
	}

	private Record_2(TypeId _1, InitField[] _2, IStrategoTerm strategoTerm) {
		this.typeId = _1;
		this.fieldInits = _2;
		this.newFrame = NewFrameNodeGen.create();
		this.newFramed = new Framed();
		this.strategoTerm = strategoTerm;
	}

	@Child
	private TypeId typeId;

	@Children
	private final InitField[] fieldInits;

	@Child
	private NewFrame newFrame;

	@Child
	private Framed newFramed;

	@Override
	@ExplodeLoop
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		// F F |- r@Record(t, inits) --> RecordV(F_rec)
		// where
		// F F |- t --> RECORD(s_rec);
		// // this is actually the static (shared) frame of the record
		// frame(s_rec, []) --> F_rec;
		// framed(r, [L(I(), F_rec)]) --> F_use;
		// Frames1 (F_use, F) |- Map2-InitField(inits) --> _
		//
		// Frames1 (F, F') |- Map2-InitField([]) --> U()
		//
		// Frames1 (F, F') |- Map2-InitField([x | xs]) --> U()
		// where
		// Frames1 (F, F') |- x --> _;
		// Frames1 (F, F') |- Map2-InitField(xs) --> _
		//
		RECORD_1 recTy = (RECORD_1) typeId.execute(frame);
		ScopeIdentifier s_rec = recTy.get_1();
		DynamicObject f_rec = newFrame.execute(frame, s_rec);
		DynamicObject f_use = newFramed.execute(frame, this, new FLink[] { new FrameEdgeLink(I.SINGLETON, f_rec) });
		for (int i = 0; i < fieldInits.length; i++) {
			fieldInits[i].execute(frame, f_use, currentFrame);
		}
		return new RecordV_1(f_rec);
	}

	@TruffleBoundary
	public static Record_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);

		IStrategoTerm[] initFieldTerms = Tools.listAt(term, 1).getAllSubterms();
		InitField[] initFields = new InitField[initFieldTerms.length];
		for (int i = 0; i < initFields.length; i++) {
			initFields[i] = InitField.create(initFieldTerms[i]);
		}
		return new Record_2(TypeId.create(term.getSubterm(0)), initFields, term);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record_2 other = (Record_2) obj;
		if (typeId == null) {
			if (other.typeId != null) {
				return false;
			}
		} else if (!typeId.equals(other.typeId)) {
			return false;
		}
		if (fieldInits == null) {
			if (other.fieldInits != null) {
				return false;
			}
		} else if (!fieldInits.equals(other.fieldInits)) {
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
		sb.append(typeId);
		sb.append(", ");
		sb.append(fieldInits);
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
		return new HashCodeBuilder().append(fieldInits).append(typeId).toHashCode();
	}
}