package org.metaborg.lang.tiger.ninterpreter.truffle;

import org.metaborg.lang.tiger.ninterpreter.truffle.objects.ArrayV;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.ClosureV;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.IntV;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.NilV;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.RecordV;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.StringV;

import com.oracle.truffle.api.dsl.TypeSystem;

@TypeSystem({ ArrayV.class, ClosureV.class, IntV.class, NilV.class, RecordV.class, StringV.class })
public abstract class TigerTypes {

}
