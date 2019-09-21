package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.lang.tiger.interpreter.generated.terms.Exp;
import org.metaborg.lang.tiger.interpreter.generated.terms.Scope;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@SuppressWarnings("unused") @NodeChild(value = "term", type = TermBuild.class) public abstract class nabl2_paramsOfExp_1 extends NativeOpBuild 
{ 
  public nabl2_paramsOfExp_1 (SourceSection source) 
  { 
    super(source);
  }

  @Specialization public Scope doGet(Exp term)
  { 
    return Scope.create(getAstProperty(term.getStrategoTerm(), getAstPropertyKey("Params")));
  }

  public static NativeOpBuild create(SourceSection source, TermBuild term)
  { 
    return nabl2_paramsOfExp_1NodeGen.create(source, term);
  }
}