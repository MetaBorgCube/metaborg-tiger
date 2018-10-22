package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import java.util.Map;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.InitProtoFrame;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.InitProtoFrameNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IListTerm;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

//@NodeChildren({ @NodeChild(value = "scopeIdent", type = TermBuild.class),
//		@NodeChild(value = "decs", type = TermBuild.class), @NodeChild(value = "decTypes", type = TermBuild.class),
//		@NodeChild(value = "refs", type = TermBuild.class), @NodeChild(value = "edges", type = TermBuild.class),
//		@NodeChild(value = "imports", type = TermBuild.class) })
public final class CreateScope extends ScopesAndFramesNode {

	@Child private InitProtoFrame protoFrameInit;

	public CreateScope() {
		super();
		this.protoFrameInit = InitProtoFrameNodeGen.create();
	}

	public ScopeIdentifier execute(VirtualFrame frame, ScopeIdentifier scopeIdent, IListTerm<?> _decs,
			IListTerm<?> decTypes, IListTerm<?> _refs, Map<?, ?> edgesMap, Map<?, ?> importsMap) {
		throw new RuntimeException("Reimplementation needed");
//		ScopesAndFramesContext ctx = context();
//		IListTerm<Occurrence> decs = ctx.getTermRegistry().getListClass(Occurrence.class).cast(_decs);
//		IListTerm<Occurrence> refs = ctx.getTermRegistry().getListClass(Occurrence.class).cast(_refs);
//
//		DynamicObject edges = createScopeEdges(edgesMap);
//
//		DynamicObject imports = createImports(importsMap);
//
//		DynamicObject scopeEntry = ScopeEntryLayoutImpl.INSTANCE.createScopeEntry(scopeIdent, decs.toArray(),
//				refs.toArray(), edges, imports);
//
//		DynamicObject nabl2 = ctx.getNaBL2Solution();
//		DynamicObject types = NaBL2LayoutImpl.INSTANCE.getTypes(nabl2);
//		DynamicObject sg = NaBL2LayoutImpl.INSTANCE.getScopeGraph(nabl2);
//		DynamicObject scopes = ScopeGraphLayoutImpl.INSTANCE.getScopes(sg);
//
//		scopes.define(scopeIdent, scopeEntry);
//
//		IListTerm<?> decTypesHead = decTypes;
//		for (Occurrence dec : decs) {
//			types.define(dec, decTypesHead.elem());
//			decTypesHead = decTypesHead.tail();
//		}
//
//		protoFrameInit.execute(frame, scopeEntry);
//
//		return scopeIdent;
	}

	@TruffleBoundary
	private DynamicObject createImports(Map<?, ?> importsMap) {
		throw new RuntimeException("Reimplementation needed");
//		Shape importsShape = ScopeImports.SINGLETON.createShape();
//		Allocator importAllocator = ScopeImports.SINGLETON.allocator();
//		Occurrence[][] importedOccs = new Occurrence[importsMap.size()][];
//		int j = 0;
//		for (Entry<?, ?> importEntry : importsMap.entrySet()) {
//			ALabel importLabel = (ALabel) importEntry.getKey();
//			importsShape = importsShape
//					.addProperty(Property.create(importLabel, importAllocator.locationForType(Occurrence[].class,
//							EnumSet.of(LocationModifier.NonNull, LocationModifier.Final)), 0));
//			importedOccs[j] = ((IListTerm<Occurrence>) importEntry.getValue()).toArray();
//			j++;
//		}
//		DynamicObject imports = importsShape.createFactory().newInstance((Object[]) importedOccs);
//		return imports;
	}

	@TruffleBoundary
	private DynamicObject createScopeEdges(Map<?, ?> edgesMap) {
		throw new RuntimeException("Reimplementation needed");
//		Shape edgesShape = ScopeEdges.SINGLETON.createShape();
//		Allocator edgeAllocator = ScopeEdges.SINGLETON.allocator();
//		ScopeIdentifier[][] edgeScopes = new ScopeIdentifier[edgesMap.size()][];
//		int i = 0;
//		for (Entry<?, ?> edgeEntry : edgesMap.entrySet()) {
//			ALabel edgeLabel = (ALabel) edgeEntry.getKey();
//			edgesShape = edgesShape
//					.addProperty(Property.create(edgeLabel, edgeAllocator.locationForType(ScopeIdentifier[].class,
//							EnumSet.of(LocationModifier.NonNull, LocationModifier.Final)), 0));
//			edgeScopes[i] = ((IListTerm<ScopeIdentifier>) edgeEntry.getValue()).toArray();
//			i++;
//		}
//		DynamicObject edges = edgesShape.createFactory().newInstance((Object[]) edgeScopes);
//		return edges;
	}

}
