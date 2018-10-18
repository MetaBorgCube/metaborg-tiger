package org.metaborg.lang.tiger.ninterpreter.truffle;

import java.io.File;
import java.io.IOException;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.metaborg.lang.tiger.ninterpreter.truffle.runtime.TigerLanguage;

public class TigerInterpreter {

	public final static TigerContext ctx = new TigerContext();

	public static void main(String[] args) throws IOException {
		String file = args[0];
		String workingDirectory = System.getProperty("user.dir");

		Context ctx = Context.newBuilder().in(System.in).out(System.out).err(System.err).build();
		Source src = Source.newBuilder(TigerLanguage.NAME, new File(file)).build();
		Value result = ctx.eval(src);
		System.out.println(result.as(Object.class));
	}

}
