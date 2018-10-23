package org.metaborg.lang.tiger.interp.scopesandframes.nodes.records;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.LValue;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.records.FieldVar_2Factory.FieldVarHelperNodeGen;
import org.metaborg.lang.tiger.interpreter.generated.terms.Occ;
import org.metaborg.lang.tiger.interpreter.generated.terms.__Occurrence2Occ___1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.Addr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameEdgeLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameEdgeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Framed;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddrNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Lookup;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.I;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
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
import com.oracle.truffle.api.object.DynamicObject;

public final class FieldVar_2 extends LValue {
	public final static String CONSTRUCTOR = "FieldVar";

	public final static int ARITY = 2;

	public FieldVar_2(LValue _1, Occ _2) {
		this(_1, _2, null);
	}

	private FieldVar_2(LValue _1, Occ _2, IStrategoTerm strategoTerm) {
		this.recordExp = _1;
		this.helperNode = FieldVarHelperNodeGen.create(((__Occurrence2Occ___1) _2).get_1(), strategoTerm);
		this.getNode = GetAtAddrNodeGen.create();
		this.strategoTerm = strategoTerm;
	}

	@Child
	private LValue recordExp;

	@Child
	private GetAtAddr getNode;

	@Child
	private FieldVarHelper helperNode;

	@Override
	public Addr execute(VirtualFrame frame, DynamicObject currentFrame) {
		Addr addr_rec = recordExp.execute(frame, currentFrame);
		DynamicObject f_rec = TigerTypesGen.asRecordV_1(getNode.execute(frame, addr_rec)).get_1();
		return helperNode.execute(frame, f_rec);

	}

	public static abstract class FieldVarHelper extends TigerTruffleNode implements ITerm {

		@Child
		private Framed newFramedNode;

		@Child
		private Lookup lookupNode;

		private final Occurrence fieldRef;

		private final IStrategoTerm strategoTerm;

		public FieldVarHelper(Occurrence fieldRef, IStrategoTerm strategoTerm) {
			this.fieldRef = fieldRef;
			this.strategoTerm = strategoTerm;
			this.newFramedNode = new Framed();
			this.lookupNode = Lookup.create();
		}

		public abstract Addr execute(VirtualFrame frame, DynamicObject recordFrame);

		@Specialization(guards = { "getFrameScope(f_rec) == s_rec" }, limit = "1")
		public Addr doCaching(VirtualFrame frame, DynamicObject f_rec,
				@Cached("getFrameScope(f_rec)") ScopeIdentifier s_rec, @Cached("label()") ALabel linkLabel,
				@Cached("getEdgeIdent(linkLabel, s_rec)") FrameEdgeIdentifier edgeIdent) {
			DynamicObject f_use = newFramedNode.execute(frame, this,
					new FLink[] { new FrameEdgeLink(I.SINGLETON, f_rec, edgeIdent) });
			return lookupNode.execute(frame, f_use, fieldRef);
		}

		protected ScopeIdentifier getFrameScope(DynamicObject frame) {
			return FrameLayoutImpl.INSTANCE.getScope(frame);
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
	public static FieldVar_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new FieldVar_2(LValue.create(term.getSubterm(0)), Occ.create(term.getSubterm(1)), term);
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