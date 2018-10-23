package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions.stdlib;

import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.UnitV_0;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.TermIndex;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public class TigerTimeStop extends TigerBuiltinNode {

	public TigerTimeStop(TermIndex topTrmIdx) {
		this(topTrmIdx, new Occurrence("Var", "timeStop", topTrmIdx), new Occurrence[] {});
	}

	public TigerTimeStop(TermIndex topTrmIdx, Occurrence dec, Occurrence[] fArgDecs) {
		super(topTrmIdx, dec, fArgDecs);
	}

	@Override
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		long endtime = System.nanoTime();
		long duration = endtime - TigerTimeGo.starttime;
		context().out().println(duration);
		return UnitV_0.SINGLETON;
	}

}
