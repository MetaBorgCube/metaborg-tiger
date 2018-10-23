package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import java.util.EnumSet;
import java.util.Map;
import java.util.Map.Entry;

import org.metaborg.lang.tiger.interpreter.generated.terms.Ty;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesContext;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.InitProtoFrame;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.InitProtoFrameNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.NaBL2LayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.ScopeEdges;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.ScopeEntryLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.ScopeGraphLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.ScopeImports;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.LocationModifier;
import com.oracle.truffle.api.object.Property;
import com.oracle.truffle.api.object.Shape;
import com.oracle.truffle.api.object.Shape.Allocator;

//@NodeChildren({ @NodeChild(value = "scopeIdent", type = TermBuild.class),
//		@NodeChild(value = "decs", type = TermBuild.class), @NodeChild(value = "decTypes", type = TermBuild.class),
//		@NodeChild(value = "refs", type = TermBuild.class), @NodeChild(value = "edges", type = TermBuild.class),
//		@NodeChild(value = "imports", type = TermBuild.class) })
public final class CreateScope extends ScopesAndFramesNode {

	@Child
	private InitProtoFrame protoFrameInit;

	public CreateScope() {
		super();
		this.protoFrameInit = InitProtoFrameNodeGen.create();
	}

	public ScopeIdentifier execute(VirtualFrame frame, ScopeIdentifier scopeIdent, Occurrence[] decs, Ty[] decTypes,
			Occurrence[] refs, Map<ALabel, ScopeIdentifier[]> edgesMap, Map<ALabel, Occurrence[]> importsMap) {
		ScopesAndFramesContext ctx = context();

		DynamicObject edges = createScopeEdges(edgesMap);

		DynamicObject imports = createImports(importsMap);

		DynamicObject scopeEntry = ScopeEntryLayoutImpl.INSTANCE.createScopeEntry(scopeIdent, decs, refs, edges,
				imports);

		DynamicObject nabl2 = ctx.getNaBL2Solution();
		DynamicObject types = NaBL2LayoutImpl.INSTANCE.getTypes(nabl2);
		DynamicObject sg = NaBL2LayoutImpl.INSTANCE.getScopeGraph(nabl2);
		DynamicObject scopes = ScopeGraphLayoutImpl.INSTANCE.getScopes(sg);

		scopes.define(scopeIdent, scopeEntry);

		for (int i = 0; i < decs.length; i++) {
			types.define(decs[i], decTypes[i]);
		}

		protoFrameInit.execute(frame, scopeEntry);

		return scopeIdent;
	}

	@TruffleBoundary
	private DynamicObject createImports(Map<ALabel, Occurrence[]> importsMap) {
		Shape importsShape = ScopeImports.SINGLETON.createShape();
		Allocator importAllocator = ScopeImports.SINGLETON.allocator();
		Occurrence[][] importedOccs = new Occurrence[importsMap.size()][];
		int j = 0;
		for (Entry<ALabel, Occurrence[]> importEntry : importsMap.entrySet()) {
			ALabel importLabel = importEntry.getKey();
			importsShape = importsShape
					.addProperty(Property.create(importLabel, importAllocator.locationForType(Occurrence[].class,
							EnumSet.of(LocationModifier.NonNull, LocationModifier.Final)), 0));
			importedOccs[j] = importEntry.getValue();
			j++;
		}
		DynamicObject imports = importsShape.createFactory().newInstance((Object[]) importedOccs);
		return imports;
	}

	@TruffleBoundary
	private DynamicObject createScopeEdges(Map<ALabel, ScopeIdentifier[]> edgesMap) {
		Shape edgesShape = ScopeEdges.SINGLETON.createShape();
		Allocator edgeAllocator = ScopeEdges.SINGLETON.allocator();
		ScopeIdentifier[][] edgeScopes = new ScopeIdentifier[edgesMap.size()][];
		int i = 0;
		for (Entry<ALabel, ScopeIdentifier[]> edgeEntry : edgesMap.entrySet()) {
			ALabel edgeLabel = edgeEntry.getKey();
			edgesShape = edgesShape
					.addProperty(Property.create(edgeLabel, edgeAllocator.locationForType(ScopeIdentifier[].class,
							EnumSet.of(LocationModifier.NonNull, LocationModifier.Final)), 0));
			edgeScopes[i] = edgeEntry.getValue();
			i++;
		}
		DynamicObject edges = edgesShape.createFactory().newInstance((Object[]) edgeScopes);
		return edges;
	}

}
