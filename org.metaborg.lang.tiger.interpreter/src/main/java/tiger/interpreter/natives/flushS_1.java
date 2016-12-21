package tiger.interpreter.natives;

import java.io.IOException;

import org.metaborg.lang.tiger.interpreter.natives.printS_1NodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChild(value = "stringbuild", type = TermBuild.class)
public abstract class flushS_1 extends TermBuild {

	public flushS_1(SourceSection source) {
		super(source);
	}

	/**
	 * Flush the output buffer.
	 * 
	 * This is utterly useless, since Tiger's print is mapped to Java's
	 * System.out.println, which by default flushes whenever a newline is
	 * written. Should Tiger's print buffer until a newline? Or buffer until
	 * flush is called? Man, we need formal semantics...
	 * 
	 * @param s Dummy parameter; I don't know how to hook up DynSem otherwise
	 * @return
	 */
	@Specialization
	public int doString(String s) {
		System.out.flush();
		
		// TODO: I don't know how to return void in DynSem
		return 0;
	}

	public static TermBuild create(SourceSection source, TermBuild stringbuild) {
		return flushS_1NodeGen.create(source, stringbuild);
	}
}
