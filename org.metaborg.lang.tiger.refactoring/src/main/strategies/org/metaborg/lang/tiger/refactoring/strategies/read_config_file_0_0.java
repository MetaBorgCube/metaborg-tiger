package org.metaborg.lang.tiger.refactoring.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

public class read_config_file_0_0 extends Strategy {

	public static read_config_file_0_0 instance = new read_config_file_0_0();

	@Override
	public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		final ITermFactory factory = context.getFactory();
		IStrategoTerm after = factory.makeString("foo");
		return after;
	}
}