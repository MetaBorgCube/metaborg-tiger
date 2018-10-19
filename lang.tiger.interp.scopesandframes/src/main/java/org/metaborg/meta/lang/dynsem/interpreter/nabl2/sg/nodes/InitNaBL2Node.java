package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.IWithScopesAndFramesContext;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.NaBL2Context;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.NaBL2SolutionUtils;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ObjectFactories;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesContext;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.spoofax.interpreter.terms.IStrategoAppl;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public class InitNaBL2Node extends ScopesAndFramesNode {

	public InitNaBL2Node(TruffleLanguage<? extends IWithScopesAndFramesContext> language) {
		super(language);
	}

	public void execute(VirtualFrame frame) {
		ScopesAndFramesContext ctx = context();
		NaBL2Context nabl2ctx = ctx.getNaBL2Context();
		assert nabl2ctx != null;
		IStrategoAppl solution = (IStrategoAppl) NaBL2SolutionUtils.getSolution(nabl2ctx);
		DynamicObject nabl2solution = ObjectFactories.createNaBL2(solution, ctx, language());
		ctx.setNabl2Solution(nabl2solution);
	}

}
