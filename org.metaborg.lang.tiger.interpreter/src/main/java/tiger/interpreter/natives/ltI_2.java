package tiger.interpreter.natives;

import org.metaborg.lang.tiger.interpreter.natives.ltI_2NodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChildren({ @NodeChild(value = "left", type = TermBuild.class),
		@NodeChild(value = "right", type = TermBuild.class) })
public abstract class ltI_2 extends TermBuild {

	public ltI_2(SourceSection source) {
		super(source);
	}

	@Specialization
	public int doInt(int left, int right) {
		return left < right ? 1 : 0;
	}

	public static TermBuild create(SourceSection source, TermBuild left,
			TermBuild right) {
		return ltI_2NodeGen.create(source, left, right);
	}

}
