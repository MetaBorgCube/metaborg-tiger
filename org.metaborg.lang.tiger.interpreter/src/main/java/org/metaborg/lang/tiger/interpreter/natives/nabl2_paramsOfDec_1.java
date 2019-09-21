package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.lang.tiger.interpreter.generated.terms.Dec;
import org.metaborg.lang.tiger.interpreter.generated.terms.Tuple_Scope_Scope;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@SuppressWarnings("unused") @NodeChild(value = "term", type = TermBuild.class) public abstract class nabl2_paramsOfDec_1 extends NativeOpBuild 
{ 
  public nabl2_paramsOfDec_1 (SourceSection source) 
  { 
    super(source);
  }

  @Specialization public Tuple_Scope_Scope doGet(Dec term)
  { 
    return Tuple_Scope_Scope.create(getAstProperty(term.getStrategoTerm(), getAstPropertyKey("Params")));
  }

  public static NativeOpBuild create(SourceSection source, TermBuild term)
  { 
    return nabl2_paramsOfDec_1NodeGen.create(source, term);
  }
}