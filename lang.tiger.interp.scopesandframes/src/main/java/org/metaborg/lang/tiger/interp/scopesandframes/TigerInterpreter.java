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
	// @TruffleBoundary
	// private static DynSemRunner createRunner(Spoofax S) throws MetaborgException
	// {
	// return new DynSemRunner(S, "Tiger", TigerMain.createVM());
	// }
	//
	// @TruffleBoundary
	// private static void printStackTrace(MetaborgException mbe) {
	// mbe.printStackTrace(System.err);
	// }
	//
	//
	//
	// @TruffleBoundary
	// private static void closeSpoofax(Spoofax S) {
	// if (S != null) {
	// S.close();
	// }
	// }
	//
	// @TruffleBoundary
	// private static void printHelper(Object result) {
	// System.out.println(result);
	// }
	//
	// @TruffleBoundary
	// private static FileObject resolve(Spoofax S, String fileName) {
	// return S.resourceService.resolve(fileName);
	// }
	//
	// @TruffleBoundary
	// private static Spoofax createSpoofax() throws MetaborgException {
	// return new Spoofax(new TigerRunnerModule(), new Module[0]);
	// }
}
