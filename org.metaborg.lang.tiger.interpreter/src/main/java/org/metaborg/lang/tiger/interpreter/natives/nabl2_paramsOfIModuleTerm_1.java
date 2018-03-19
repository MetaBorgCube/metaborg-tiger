package org.metaborg.lang.tiger.interpreter.natives;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;
import org.metaborg.lang.tiger.interpreter.generated.terms.*;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.NaBL2TermBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;
import mb.nabl2.interpreter.*;
import mb.nabl2.stratego.*;
import mb.nabl2.terms.*;
import mb.nabl2.terms.build.*;
import org.spoofax.interpreter.terms.IStrategoTerm;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@SuppressWarnings("unused") @NodeChild(value = "term", type = TermBuild.class) public abstract class nabl2_paramsOfIModuleTerm_1 extends NaBL2TermBuild 
{ 
  public nabl2_paramsOfIModuleTerm_1 (SourceSection source) 
  { 
    super(source);
  }

  @Specialization public IScopeTerm doGet(IModuleTerm term)
  { 
    return IScopeTerm.create(getAstProperty(term.getStrategoTerm(), "Params"));
  }

  public static TermBuild create(SourceSection source, TermBuild term)
  { 
    return nabl2_paramsOfIModuleTerm_1NodeGen.create(source, term);
  }
}