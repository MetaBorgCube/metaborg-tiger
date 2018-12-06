package org.metaborg.lang.tiger.interp.scopesandframes;

import java.io.IOException;
import java.net.URL;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public class TigerInterpreter {

	public TigerInterpreter() {
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			printUsage();
			return;
		}
		Context ctx = Context.newBuilder(TigerLanguage.NAME).in(System.in).out(System.out).err(System.err).build();
		Source src = Source.newBuilder(TigerLanguage.NAME, new URL("file://" + args[0])).build();
		Value result = ctx.eval(src);
		System.out.println(result.as(Object.class));
	}

	@TruffleBoundary
	private static void printUsage() {
		System.err.println("Usage: " + TigerInterpreter.class.getName() + " FILES");
	}
	
}
