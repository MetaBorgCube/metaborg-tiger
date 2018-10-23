package org.metaborg.meta.lang.dynsem.interpreter.nabl2;

import java.util.Objects;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FramePrototypesLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.NaBL2LayoutImpl;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.object.DynamicObject;

public class ScopesAndFramesContext {

	private final NaBL2Context nabl2context;
	private final IDefaultValueProvider defaultValues;

	public ScopesAndFramesContext(NaBL2Context nabl2context, IDefaultValueProvider defaultValues) {
		this.nabl2context = nabl2context;
		this.defaultValues = defaultValues;
	}

	public NaBL2Context getNaBL2Context() {
		return nabl2context;
	}

	@CompilationFinal
	private DynamicObject nabl2solution;

	public boolean hasNaBL2Solution() {
		return nabl2solution != null;
	}

	public void setNabl2Solution(DynamicObject nabl2) {
		CompilerDirectives.transferToInterpreterAndInvalidate();
		assert NaBL2LayoutImpl.INSTANCE.isNaBL2(nabl2);
		this.nabl2solution = nabl2;
	}

	public DynamicObject getNaBL2Solution() {
		return Objects.requireNonNull(nabl2solution, "No NaBL2 context available. Does the language use NaBL2?");
	}

	public IDefaultValueProvider getDefaultValues() {
		return this.defaultValues;
	}

	private final DynamicObject protoFrames = FramePrototypesLayoutImpl.INSTANCE.createFramePrototypes();

	public void addProtoFrame(ScopeIdentifier ident, DynamicObject frameProto) {
		assert FrameLayoutImpl.INSTANCE.isFrame(frameProto);
		protoFrames.define(ident, frameProto);
	}

	public DynamicObject getProtoFrame(ScopeIdentifier ident) {
		return (DynamicObject) protoFrames.get(ident);
	}

}
