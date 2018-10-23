package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions.stdlib;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.TermIndex;

public class OccurrenceUtils {

	private OccurrenceUtils() {
	}

	private static int freshCounter = Integer.MIN_VALUE;

	public static Occurrence freshPhantomOccurrence(String namespace, String name) {
		return new Occurrence(namespace, name, new TermIndex("<phantom>", freshCounter++));
	}

}
