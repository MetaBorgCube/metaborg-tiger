package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.types;

import org.metaborg.lang.tiger.ninterpreter.truffle.objects.RecordType;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.TypeV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;

public final class RecordTy_1 extends Type {
	public final static String CONSTRUCTOR = "RecordTy";

	public final static int ARITY = 1;

	private RecordTy_1(Field[] _1) {
		this.fields = _1;
	}

	private final Field[] fields;

	@CompilationFinal
	private RecordType recTy;

	private RecordType buildRecordType() {
		FrameDescriptor recDescriptor = new FrameDescriptor();
		for (Field field : fields) {
			recDescriptor.addFrameSlot(field.getId(), FrameSlotKind.Object);
		}
		return new RecordType(recDescriptor);
	}

	@Override
	public TypeV executeTypeDec(VirtualFrame frame) {
		if (recTy == null) {
			recTy = buildRecordType();
		}
		return recTy;
	}

	public static RecordTy_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList tsT = Tools.listAt(term, 0);
		Field[] ts = new Field[tsT.size()];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = Field.create(tsT.getSubterm(i));
		}
		return new RecordTy_1(ts);
	}
}