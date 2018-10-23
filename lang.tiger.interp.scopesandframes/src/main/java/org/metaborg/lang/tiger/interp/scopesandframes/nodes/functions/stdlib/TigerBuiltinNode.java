package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions.stdlib;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerEvalNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.TermIndex;

public abstract class TigerBuiltinNode extends TigerEvalNode {

	private final Occurrence dec;

	private final Occurrence[] fArgDecs;
	
	public TigerBuiltinNode(TermIndex topTrmIdx, Occurrence dec, Occurrence[] fArgDecs) {
		this.dec = dec;
		this.fArgDecs = fArgDecs;
	}
	
	public final Occurrence getDeclarationOccurrence() {
		return dec;
	}

	public final Occurrence[] getArguments() {
		return fArgDecs;
	}
}
