package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions.stdlib;

import java.util.HashMap;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerLanguage;
import org.metaborg.lang.tiger.interp.scopesandframes.TigerRootNode;
import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.lang.tiger.interp.scopesandframes.values.FunV;
import org.metaborg.lang.tiger.interp.scopesandframes.values.FunV.CacheableFunV;
import org.metaborg.lang.tiger.interpreter.generated.terms.FUN_2;
import org.metaborg.lang.tiger.interpreter.generated.terms.Ty;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.ScopeOfFrame;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetFrameSlot;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetFrameSlotNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.P;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.TermIndex;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.CreateScope;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.TypeOfDec;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.TypeOfDecNodeGen;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public class StdLib extends TigerTruffleNode {

	@Child
	private TypeOfDec typeOf;

	@Child
	private CreateScope createScope;

	@Child
	private SetFrameSlot setSlot;

	@Child
	private ScopeOfFrame scopeOf;

	public StdLib() {
		this.typeOf = TypeOfDecNodeGen.create();
		this.createScope = new CreateScope();
		this.setSlot = SetFrameSlotNodeGen.create();
		this.scopeOf = new ScopeOfFrame();
	}

	public void installBuiltins(VirtualFrame frame, DynamicObject currentFrame, TermIndex topTrmIdx) {
		installBuiltin(frame, currentFrame, new TigerPrintString(topTrmIdx));
		installBuiltin(frame, currentFrame, new TigerPrintInt(topTrmIdx));
		installBuiltin(frame, currentFrame, new TigerTimeGo(topTrmIdx));
		installBuiltin(frame, currentFrame, new TigerTimeStop(topTrmIdx));
		installBuiltin(frame, currentFrame, new TigerFlush(topTrmIdx));
	}

	private void installBuiltin(VirtualFrame frame, DynamicObject currentFrame, TigerBuiltinNode builtin) {
		Occurrence dec = builtin.getDeclarationOccurrence();
		Occurrence[] arguments = builtin.getArguments();
		FUN_2 funTy = (FUN_2) typeOf.execute(frame, dec);
		Ty[] fArgTys = funTy.getArgumentTypes();
		ScopeIdentifier functionScope = new ScopeIdentifier("<:implicit:fun:>", dec.name());
		HashMap<ALabel, ScopeIdentifier[]> scopeEdges = new HashMap<>();
		scopeEdges.put(P.SINGLETON, new ScopeIdentifier[] { scopeOf.execute(frame, currentFrame) });
		createScope.execute(frame, functionScope, arguments, fArgTys, new Occurrence[] {}, scopeEdges, new HashMap<>());

		TigerRootNode functionRoot = new TigerRootNode(getRootNode().getLanguage(TigerLanguage.class),
				new FrameDescriptor(), dec.name(), builtin);
		CacheableFunV function = new CacheableFunV(functionScope, arguments,
				Truffle.getRuntime().createCallTarget(functionRoot));
		FunV clos = new FunV(currentFrame, function);
		setSlot.execute(frame, currentFrame, dec, clos);
	}

}
