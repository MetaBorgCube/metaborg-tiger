package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public abstract class Let_2 extends Exp {
	public final static String CONSTRUCTOR = "Let";

	public final static int ARITY = 2;

	@Children
	final Dec[] _1;

	@Children
	final Exp[] _2;

	protected Let_2(Dec[] _1, Exp[] _2) {
		this._1 = _1;
		this._2 = _2;
	}

	@CompilationFinal
	private FrameDescriptor boundDescriptor;

	private FrameDescriptor descriptor() {
		if (boundDescriptor == null) {
			boundDescriptor = ctx().baseDescriptor.copy();
			for (int i = 0; i < _1.length; i++) {
				_1[i].fillFrameDescriptor(boundDescriptor);
			}
		}
		return boundDescriptor;
	}

	@Specialization
	@ExplodeLoop
	public TigerObject doEval(VirtualFrame frame) {
		VirtualFrame newFrame = Truffle.getRuntime().createVirtualFrame(null, descriptor());
		newFrame.setObject(ctx().parentFrameSlot, frame);
		// evaluate declarations
		for (int i = 0; i < _1.length; i++) {
			_1[i].executeDeclaration(newFrame);
		}

		// evaluate body expressions
		TigerObject res = null;
		for (int j = 0; j < _2.length; j++) {
			res = _2[j].executeGeneric(newFrame);
		}
		return res;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(CONSTRUCTOR);
		sb.append("(");
		sb.append(_1);
		sb.append(", ");
		sb.append(_2);
		sb.append(")");
		return sb.toString();
	}

	public static Let_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList decsT = Tools.listAt(term, 0);
		Dec[] decs = new Dec[decsT.size()];
		for (int i = 0; i < decs.length; i++) {
			decs[i] = Dec.create(decsT.getSubterm(i));
		}
		IStrategoList esT = Tools.listAt(term, 1);
		Exp[] es = new Exp[esT.size()];
		for (int i = 0; i < es.length; i++) {
			es[i] = Exp.create(esT.getSubterm(i));
		}
		return Let_2NodeGen.create(decs, es);
	}
}