package org.metaborg.lang.tiger.interp.scopesandframes;

import org.metaborg.lang.tiger.interpreter.generated.terms.ARRAY_2;
import org.metaborg.lang.tiger.interpreter.generated.terms.FUN_2;
import org.metaborg.lang.tiger.interpreter.generated.terms.INT_0;
import org.metaborg.lang.tiger.interpreter.generated.terms.IntV_1;
import org.metaborg.lang.tiger.interpreter.generated.terms.NilV_0;
import org.metaborg.lang.tiger.interpreter.generated.terms.RECORD_1;
import org.metaborg.lang.tiger.interpreter.generated.terms.STRING_0;
import org.metaborg.lang.tiger.interpreter.generated.terms.Ty;
import org.metaborg.lang.tiger.interpreter.generated.terms.UNIT_0;
import org.metaborg.lang.tiger.interpreter.generated.terms.UndefV_0;
import org.metaborg.lang.tiger.interpreter.generated.terms.UnitV_0;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.IDefaultValueProvider;

public class TigerDefaultValues implements IDefaultValueProvider{

	public TigerDefaultValues() {
	}
	
	public Object defaultValue(Ty ty) {
		if(ty instanceof ARRAY_2) {
			return UndefV_0.SINGLETON;
		}
		if(ty instanceof UNIT_0) {
			return UnitV_0.SINGLETON;
		}
		if(ty instanceof FUN_2) {
			return UndefV_0.SINGLETON;
		}
		if(ty instanceof INT_0) {
			return new IntV_1(0);
		}
		if(ty instanceof RECORD_1) {
			return NilV_0.SINGLETON;
		}
		if(ty instanceof STRING_0) {
			return UndefV_0.SINGLETON;
		}
		throw new IllegalArgumentException("Unknown/unsupported type: " + ty);
	}

	@Override
	public Object defaultValue(Object ty) {
		if(ty instanceof Ty){
			return defaultValue((Ty) ty);
		}
		throw new IllegalArgumentException("Unknown/unsupported type: " + ty);
	}

}
