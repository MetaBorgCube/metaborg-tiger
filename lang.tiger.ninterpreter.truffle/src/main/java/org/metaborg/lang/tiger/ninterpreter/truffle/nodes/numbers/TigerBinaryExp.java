package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.numbers;

import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;

@NodeChildren({ @NodeChild(value = "left", type = Exp.class), @NodeChild(value = "right", type = Exp.class) })
public abstract class TigerBinaryExp extends Exp {

}
