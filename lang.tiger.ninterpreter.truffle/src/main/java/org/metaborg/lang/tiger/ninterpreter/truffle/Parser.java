package org.metaborg.lang.tiger.ninterpreter.truffle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.client.Disambiguator;
import org.spoofax.jsglr.client.ParseTable;
import org.spoofax.jsglr.client.SGLR;
import org.spoofax.jsglr.client.SGLRParseResult;
import org.spoofax.jsglr.client.imploder.ImploderOriginTermFactory;
import org.spoofax.jsglr.client.imploder.TermTreeFactory;
import org.spoofax.jsglr.client.imploder.TreeBuilder;
import org.spoofax.jsglr.shared.SGLRException;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.io.binary.TermReader;

public class Parser {
	private InputStream parsetableInput;
	private String startSymbol;
	private SGLR parser;

	public Parser(InputStream parsetableInput, String startSymbol) {
		this.parsetableInput = parsetableInput;
		this.startSymbol = startSymbol;
	}

	public IStrategoTerm parse(File src, String overridingStartSymbol) {
		if (parser == null) {
			createParser();
		}
		String startSymbol = this.startSymbol;
		if (overridingStartSymbol != null) {
			startSymbol = overridingStartSymbol;
		}

		try {
			final Disambiguator disambiguator = parser.getDisambiguator();
			disambiguator.setFilterPriorities(false);
			SGLRParseResult parseResult = parser.parse(
					IOUtils.toString(new FileInputStream(src), Charset.defaultCharset()), src.getPath(), startSymbol);
			IStrategoTerm term = (IStrategoTerm) parseResult.output;
			return term;
		} catch (SGLRException | InterruptedException | IOException e) {
			throw new IllegalStateException("File failed to parse", e);
		}

	}

	private void createParser() {
		TreeBuilder treebuilder = new TreeBuilder(
				new TermTreeFactory(new ImploderOriginTermFactory(new TermFactory())));

		parser = new SGLR(treebuilder, loadPT());

		parser.setUseStructureRecovery(false);
	}

	private ParseTable loadPT() {
		TermFactory factory = new TermFactory();
		try {
			TermReader termReader = new TermReader(factory);
			IStrategoTerm parseTableTerm = termReader.parseFromStream(parsetableInput);
			parsetableInput.close();
			return new ParseTable(parseTableTerm, factory);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
