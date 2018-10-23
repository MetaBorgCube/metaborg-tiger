package org.metaborg.lang.tiger.interp.scopesandframes.nodes.records;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.TypeId;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.records.Record_2Factory.RecordHelperNodeGen;
import org.metaborg.lang.tiger.interp.scopesandframes.values.RecordV_1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameEdgeLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameEdgeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Framed;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.NewFrame;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.NewFrameNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.I;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.terms.ITerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
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
		this.helperNode = RecordHelperNodeGen.create(_2, strategoTerm);
		this.strategoTerm = strategoTerm;
	}

	@Child
	private TypeId typeId;

	@Child
	private RecordHelper helperNode;

	@Override
	public RecordV_1 executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		RECORD_1 recTy = (RECORD_1) typeId.execute(frame);
		ScopeIdentifier s_rec = recTy.get_1();
		return helperNode.execute(frame, currentFrame, s_rec);
	}

	public static abstract class RecordHelper extends TigerTruffleNode implements ITerm {
		@Children
		private final InitField[] fieldInits;

		@Child
		private NewFrame newFrame;

		@Child
		private Framed newFramed;

		private final IStrategoTerm strategoTerm;

		public RecordHelper(InitField[] fieldInits, IStrategoTerm strategoTerm) {
			this.fieldInits = fieldInits;
			this.strategoTerm = strategoTerm;
			this.newFrame = NewFrameNodeGen.create();
			this.newFramed = new Framed();
		}

		public abstract RecordV_1 execute(VirtualFrame frame, DynamicObject currentFrame, ScopeIdentifier s_rec);

		@Specialization
		@ExplodeLoop
		public RecordV_1 doCaching(VirtualFrame frame, DynamicObject currentFrame, ScopeIdentifier s_rec,
				@Cached("label()") ALabel label, @Cached("getEdgeIdent(label, s_rec)") FrameEdgeIdentifier edgeIdent) {
			DynamicObject f_rec = newFrame.execute(frame, s_rec);
			DynamicObject f_use = newFramed.execute(frame, this,
					new FLink[] { new FrameEdgeLink(I.SINGLETON, f_rec, edgeIdent) });
			for (int i = 0; i < fieldInits.length; i++) {
				fieldInits[i].execute(frame, f_use, currentFrame);
			}
			return new RecordV_1(f_rec);
		}

		protected FrameEdgeIdentifier getEdgeIdent(ALabel label, ScopeIdentifier sid) {
			return new FrameEdgeIdentifier(label, sid);
		}

		protected ALabel label() {
			return I.SINGLETON;
		}

		@Override
		public int size() {
			return 0;
		}

		@Override
		public boolean hasStrategoTerm() {
			return strategoTerm != null;
		}

		@Override
		public IStrategoTerm getStrategoTerm() {
			return strategoTerm;
		}
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

}