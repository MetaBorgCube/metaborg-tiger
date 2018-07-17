package org.metaborg.lang.tiger.ninterpreter.objects;

public class BreakException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8245263293581518079L;

	public BreakException() {
	}

	public BreakException(String message) {
		super(message);
	}

	public BreakException(Throwable cause) {
		super(cause);
	}

	public BreakException(String message, Throwable cause) {
		super(message, cause);
	}

	public BreakException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
