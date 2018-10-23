package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions.stdlib;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.UnitV_0;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetFrameSlot;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetFrameSlotNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.ScopeOfFrame;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.TermIndex;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.DeclsOfScope;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.DeclsOfScopeNodeGen;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public class TigerPrintInt extends TigerBuiltinNode {

	@Child
	private ScopeOfFrame scopeOf;

	@Child
	private DeclsOfScope declsOf;

	@Child
	private GetFrameSlot getSlot;

	public TigerPrintInt(TermIndex topTrmIdx) {
		super(topTrmIdx, new Occurrence("Var", "printi", topTrmIdx),
				new Occurrence[] { OccurrenceUtils.freshPhantomOccurrence("Var", "i") });
		this.scopeOf = new ScopeOfFrame();
		this.declsOf = DeclsOfScopeNodeGen.create();
		this.getSlot = GetFrameSlotNodeGen.create();
	}

	@Override
	public V executeGeneric(VirtualFrame frame, DynamicObject callFrame) {
		Occurrence[] decs = declsOf.execute(frame, scopeOf.execute(frame, callFrame));
		int i = TigerTypesGen.asIntV_1(getSlot.execute(frame, callFrame, decs[0])).get_1();
		context().out().println(i);
		return UnitV_0.SINGLETON;
	}

}
