package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.functions;

import org.metaborg.lang.tiger.ninterpreter.terms.FunDec;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings.Dec;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class FunDecs_1 extends Dec {
	public final static String CONSTRUCTOR = "FunDecs";

	public final static int ARITY = 1;

	@Children
	final FunDec[] funDecs;

	public FunDecs_1(FunDec[] funDecs) {
		this.funDecs = funDecs;
	}

	@Override
	@ExplodeLoop
	public void fillFrameDescriptor(FrameDescriptor fd) {
		for (int i = 0; i < funDecs.length; i++) {
			funDecs[i].fillFrameDescriptor(fd);
		}
	}

	@Override
	@ExplodeLoop
	public void executeDeclaration(VirtualFrame frame) {
		for (int i = 0; i < funDecs.length; i++) {
			funDecs[i].executeDeclaration(frame);
		}
	}

	public static FunDecs_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList tsT = Tools.listAt(term, 0);
		FunDec[] ts = new FunDec[tsT.size()];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = FunDec.create(tsT.getSubterm(i));
		}
		return new FunDecs_1(ts);
	}
}