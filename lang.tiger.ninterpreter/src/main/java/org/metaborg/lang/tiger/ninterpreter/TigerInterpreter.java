package org.metaborg.lang.tiger.ninterpreter;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.metaborg.lang.tiger.ninterpreter.terms.Module;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class TigerInterpreter {

	public final static String PARSE_TABLE = "src/main/resources/parsetable.tbl";

	public final static String START_SYMBOL = "Module";

	public static void main(String[] args) {
		String file = args[0];
		String workingDirectory = System.getProperty("user.dir");
		TigerHeap heap = new TigerMutableHeap();
		TigerEnv env = new TigerMutableEnv(null);
		// TigerHeap heap = new TigerPersistentHeap();
		// TigerEnv env = new TigerPersistentEnv(null);

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
		File f = new File(file);
		if (!f.isAbsolute() && !f.exists()) {
			f = new File(workingDir, file);
		}
		Parser parser = new Parser(
				TigerInterpreter.class.getClassLoader().getResourceAsStream(FilenameUtils.getName(PARSE_TABLE)),
				START_SYMBOL);
		IStrategoTerm term = parser.parse(f, null);
		return Module.create(term);
	}

}
