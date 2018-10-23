package org.metaborg.lang.tiger.interp.scopesandframes;

import org.metaborg.lang.tiger.interp.scopesandframes.values.IntV_1;
import org.metaborg.lang.tiger.interp.scopesandframes.values.NilV_0;
import org.metaborg.lang.tiger.interp.scopesandframes.values.RecordV_1;
import org.metaborg.lang.tiger.interp.scopesandframes.values.StringV_1;

import com.oracle.truffle.api.dsl.TypeSystem;

@TypeSystem({ RecordV_1.class, StringV_1.class, IntV_1.class, NilV_0.class })
public abstract class TigerTypes {

}
