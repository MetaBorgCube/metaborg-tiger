package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChild(value = "print", type = TermBuild.class)
public abstract class debug_1 extends NativeOpBuild {
    public debug_1(SourceSection source) {
        super(source);
    }

    @Specialization
    @TruffleBoundary
    public Object doInt(Object s) {
        System.err.println(s);
        
        return s;
    }

    public static NativeOpBuild create(SourceSection source, TermBuild print) {
        return debug_1NodeGen.create(source, print);
    }
}
