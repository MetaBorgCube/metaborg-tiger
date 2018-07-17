package org.metaborg.lang.tiger.ninterpreter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.metaborg.lang.tiger.ninterpreter.terms.Module;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class TigerInterpreter {

	public final static String PARSE_TABLE = "src/main/resources/parsetable.tbl";

	public final static String START_SYMBOL = "Module";

	public static void main(String[] args) {
		String file = args[0];
		String workingDirectory = System.getProperty("user.dir");
		TigerHeap heap = new TigerHeap();
		TigerEnv env = new TigerEnv(null);
		
		Module mod = new TigerInterpreter(workingDirectory).getCallable(file);
		
		TigerObject result;
		try {
			result = (TigerObject) mod.evaluate(heap, env);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private final String workingDir;

	public TigerInterpreter(String workingDir) {
		this.workingDir = workingDir;
	}

	public Module getCallable(String file) {
		try {
			File f = new File(file);
			if (!f.isAbsolute() && !f.exists()) {
				f = new File(workingDir, file);
			}
			Parser parser = new Parser(new FileInputStream(PARSE_TABLE), START_SYMBOL);
			IStrategoTerm term = parser.parse(f, null);
			return Module.create(term);
		} catch (IOException ioex) {
			throw new RuntimeException("Eval failed", ioex);
		}
	}

}
