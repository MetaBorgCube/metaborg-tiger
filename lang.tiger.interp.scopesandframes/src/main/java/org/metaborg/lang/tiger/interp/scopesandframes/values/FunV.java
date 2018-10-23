package org.metaborg.lang.tiger.interp.scopesandframes.values;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.object.DynamicObject;

public final class FunV extends V {

	public static final class CacheableFunV {
		private final CallTarget target;
		private final ScopeIdentifier functionScope;
		@CompilationFinal(dimensions = 1)
		private final Occurrence[] fargs;

		public CacheableFunV(ScopeIdentifier functionScope, Occurrence[] fargs, CallTarget target) {
			this.target = target;
			this.functionScope = functionScope;
			this.fargs = fargs;
		}

		public CallTarget getCallTarget() {
			return target;
		}

		public ScopeIdentifier getFunctionScope() {
			return functionScope;
		}

		public Occurrence[] getArguments() {
			return fargs;
		}
	}

	private final DynamicObject parentFrame;
	private final CacheableFunV cacheablePart;

	public FunV(DynamicObject parentFrame, CacheableFunV cacheablePart) {
		this.parentFrame = parentFrame;
		this.cacheablePart = cacheablePart;
	}

	public DynamicObject getParentFrame() {
		return parentFrame;
	}

	public CacheableFunV getCacheablePart() {
		return cacheablePart;
	}

	@Override
	public int size() {
		return 2;
	}

	@Override
	public boolean hasStrategoTerm() {
		return false;
	}

	@Override
	public IStrategoTerm getStrategoTerm() {
		return null;
	}

}
