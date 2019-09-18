package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.lang.tiger.interpreter.generated.terms.NaBL2;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@SuppressWarnings("unused") @NodeChild(value = "term", type = TermBuild.class) public abstract class nabl2_init_1 extends NativeOpBuild 
{ 
  public nabl2_init_1 (SourceSection source) 
  { 
    super(source);
  }

  @Specialization public NaBL2 doGet(org.metaborg.meta.lang.dynsem.interpreter.terms.ITerm term)
  { 
    return NaBL2.create(getSolution());
  }

  public static NativeOpBuild create(SourceSection source, TermBuild term)
  { 
    return nabl2_init_1NodeGen.create(source, term);
  }
}