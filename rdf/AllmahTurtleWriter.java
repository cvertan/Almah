package allmahVer4.rdf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collections;
import allmahVer4.AllmahGUI;
import allmahVer4.BlockAnnotation;
import allmahVer4.ComponentElement;
import allmahVer4.DocumentReading;
import allmahVer4.GT1Element;
import allmahVer4.GT2Element;
import allmahVer4.GlyphElementNode;
import allmahVer4.GlyphNode;
import allmahVer4.GraphTranslit1;
import allmahVer4.GraphGT2Node;
import allmahVer4.GraphTranslit2;
import allmahVer4.HieroglyphenBlock;
import allmahVer4.NT1Element;
import allmahVer4.NT2Element;
import allmahVer4.NumTranslit1;
import allmahVer4.NumTranslit2;
import allmahVer4.OperatorLink;
import allmahVer4.PhonemTranslit;
import allmahVer4.PhonemNode;
import allmahVer4.MorphoTranscrNode;
import allmahVer4.MorphoSyntTranslit;
import allmahVer4.SyntacticAnnotation;
import allmahVer4.SemanticAnnotation;
import allmahVer4.GlossingVariant;
import allmahVer4.MorphoSyntGlossing;
import allmahVer4.Reading;
import allmahVer4.TranslationNode;
import edu.uci.ics.jung.graph.Graph;

/**
 * Turtle writer for ALLMAH.
 *
 * Exports all DocumentReading objects, not only R0.
 */
public class AllmahTurtleWriter {

    private final StringBuilder ttl = new StringBuilder();

    private final StringBuilder deferredAnnotationUriTtl = new StringBuilder();

    private final Map<GlyphElementNode, String> glyphUris =
            new IdentityHashMap<>();

    private final Map<NT1Element, String> nt1ElementUris =
            new IdentityHashMap<>();

    private final Map<NT2Element, String> nt2ElementUris =
            new IdentityHashMap<>();

    private final Map<GT1Element, String> gt1ElementUris =
            new IdentityHashMap<>();

    /*
     * ComponentElement is a GUI object.  In RDF it must NOT become one
     * shared resource across all interpretation levels.  We therefore keep
     * three separate URI maps, one for NT2, one for GT1 and one for GT2.
     */
    private final Map<ComponentElement, String> numericComponentUris =
            new IdentityHashMap<>();

    private final Map<ComponentElement, String> graphematicComponentUris =
            new IdentityHashMap<>();

    private final Map<ComponentElement, String> graphemicComponentUris =
            new IdentityHashMap<>();

    private final Set<String> writtenComponentUris =
            new HashSet<String>();

    private final Set<String> writtenGlyphUris =
            new HashSet<String>();

    private final Map<String, List<String>> nt2ElementUrisByLabel =
            new HashMap<String, List<String>>();

    private final Set<String> writtenReadings =
            new HashSet<>();

    private final Map<String, ArrayList<Reading>> globalCatalogueReadingsByGlyphLabel =
            new HashMap<String, ArrayList<Reading>>();

    public void exportDocument(
            String docId,
            String title,
            ArrayList<HieroglyphenBlock> blocks,
            AllmahGUI interf,
            File outputFile
    ) throws IOException {

        ttl.setLength(0);
        deferredAnnotationUriTtl.setLength(0);
        glyphUris.clear();
        nt1ElementUris.clear();
        nt2ElementUris.clear();
        gt1ElementUris.clear();
        numericComponentUris.clear();
        graphematicComponentUris.clear();
        graphemicComponentUris.clear();
        writtenComponentUris.clear();
        writtenGlyphUris.clear();
        nt2ElementUrisByLabel.clear();
        writtenReadings.clear();
        globalCatalogueReadingsByGlyphLabel.clear();
        writtenOperatorTypes.clear();

        writePrefixes();

        String safeTitle = title;
        if (safeTitle == null || safeTitle.trim().isEmpty()) {
            safeTitle = docId;
        }

        String corpusUri =
                RdfUriFactory.ex("corpus-allmah");

        String docUri =
                RdfUriFactory.ex(
                        "doc-" + RdfUriFactory.sanitize(docId)
                );

        ttl.append(corpusUri).append("\n");
        ttl.append("    a allmah:Corpus ;\n");
        ttl.append("    rdfs:label ")
           .append(TurtleEscaper.literal("ALLMAH Corpus"))
           .append(" ;\n");
        ttl.append("    allmah:hasDocument ")
           .append(docUri)
           .append(" .\n\n");

        ttl.append(docUri).append("\n");
        ttl.append("    a allmah:Document ;\n");
        ttl.append("    rdfs:label ")
           .append(TurtleEscaper.literal(safeTitle))
           .append(" ;\n");
        ttl.append("    allmah:title ")
           .append(TurtleEscaper.literal(safeTitle))
           .append(" .\n\n");

        ArrayList<DocumentReading> docReadings =
                getDocReadings(interf);

        collectGlobalCatalogueReadings(blocks, docReadings, interf);

        if (docReadings == null || docReadings.isEmpty()) {
            writeFallbackR0(docId, docUri, blocks, interf);
        } else {
            writeAllDocumentReadings(docUri, docReadings, interf);
        }

        writeBlockAnnotations(interf);

        try (FileWriter writer =
                     new FileWriter(outputFile)) {
            writer.write(ttl.toString());
        }
    }

    private void writeFallbackR0(
            String docId,
            String docUri,
            ArrayList<HieroglyphenBlock> blocks,
            AllmahGUI interf
    ) {

        String readingId =
                docId + ">R0";

        writeDocumentReadingHeader(
                docUri,
                readingId,
                "R0",
                1,
                0
        );

        int blockIndex = 1;

        for (HieroglyphenBlock block : blocks) {
            writeBlock(
                    readingId,
                    RdfUriFactory.fromInternalId(readingId),
                    block,
                    interf,
                    blockIndex
            );
            blockIndex++;
        }
    }

    private void writeAllDocumentReadings(
            String docUri,
            ArrayList<DocumentReading> docReadings,
            AllmahGUI interf
    ) {

        int readingIndex = 1;

        for (DocumentReading dr : docReadings) {

            String readingId =
                    dr.getId();

            String readingLabel =
                    getReadingLocalLabel(readingId);

            writeDocumentReadingHeader(
                    docUri,
                    readingId,
                    readingLabel,
                    readingIndex,
                    dr.getInterpretationConfidence()
            );

            String readingUri =
                    RdfUriFactory.fromInternalId(readingId);

            int blockIndex = 1;

            if (dr.getBlocks() != null) {

                for (String blockId : dr.getBlocks()) {

                    HieroglyphenBlock block =
                            getBlockById(interf, blockId);

                    if (block == null) {
                        continue;
                    }

                    writeBlock(
                            readingId,
                            readingUri,
                            block,
                            interf,
                            blockIndex
                    );

                    blockIndex++;
                }
            }

            readingIndex++;
        }
    }

    private void writePrefixes() {

        ttl.append("@prefix allmah: <")
           .append(RdfUriFactory.ALLMAH)
           .append("> .\n");

        ttl.append("@prefix ex: <")
           .append(RdfUriFactory.DATA)
           .append("> .\n");

        ttl.append("@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .\n");
        ttl.append("@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .\n\n");
    }

    private void writeDocumentReadingHeader(
            String docUri,
            String readingId,
            String readingLabel,
            int readingIndex,
            int confidence
    ) {

        String readingUri =
                RdfUriFactory.fromInternalId(readingId);

        ttl.append(docUri)
           .append(" allmah:hasDocumentReading ")
           .append(readingUri)
           .append(" .\n\n");

        ttl.append(readingUri).append("\n");
        ttl.append("    a allmah:DocumentReading ;\n");
        ttl.append("    rdfs:label ")
           .append(TurtleEscaper.literal(readingLabel))
           .append(" ;\n");
        ttl.append("    allmah:internalId ")
           .append(TurtleEscaper.literal(readingId))
           .append(" ;\n");
        ttl.append("    allmah:isDocumentReadingOf ")
           .append(docUri)
           .append(" ;\n");
        ttl.append("    allmah:orderIndex ")
           .append(readingIndex);

        if (confidence > 0) {
            ttl.append(" ;\n")
               .append("    allmah:confidence ")
               .append(confidence)
               .append(" .\n\n");
        } else {
            ttl.append(" .\n\n");
        }
    }

    private void writeBlock(
            String readingId,
            String readingUri,
            HieroglyphenBlock block,
            AllmahGUI interf,
            int blockIndex
    ) {

        String blockId =
                block.getLabel();

        String blockExpression =
                block.getNumLabel();

        String blockInternalId =
                readingId + ">" + blockId;

        String blockUri =
                RdfUriFactory.fromInternalId(blockInternalId);

        ttl.append(readingUri)
           .append(" allmah:usesBlock ")
           .append(blockUri)
           .append(" .\n\n");

        String blockClass =
                "#".equals(blockExpression)
                        ? "allmah:GapBlock"
                        : "allmah:HieroglyphBlock";

        ttl.append(blockUri).append("\n");
        ttl.append("    a ")
           .append(blockClass)
           .append(" ;\n");

        ttl.append("    rdfs:label ")
           .append(TurtleEscaper.literal(blockDisplayLabel(blockId, blockExpression)))
           .append(" ;\n");

        ttl.append("    allmah:blockExpression ")
           .append(TurtleEscaper.literal(cleanPlainLabel(blockExpression)))
           .append(" ;\n");

        ttl.append("    allmah:internalId ")
           .append(TurtleEscaper.literal(blockInternalId))
           .append(" ;\n");

        ttl.append("    allmah:teiXmlId ")
           .append(TurtleEscaper.literal(blockId))
           .append(" ;\n");

        ttl.append("    allmah:blockPosition ")
           .append(TurtleEscaper.literal(blockId))
           .append(" ;\n");

        ttl.append("    allmah:blockLabel ")
           .append(TurtleEscaper.literal(blockId))
           .append(" ;\n");

        ttl.append("    allmah:orderIndex ")
           .append(blockIndex)
           .append(" .\n\n");

        writeGlyphs(blockUri, block, interf);
        writeGraphRelations(blockUri, block);
        writeNT1s(blockUri, blockInternalId, block, interf);
    }

    private void writeGlyphs(
            String blockUri,
            HieroglyphenBlock block,
            AllmahGUI interf
    ) {

        ArrayList<GlyphElementNode> glyphs =
                block.getElements();

        if (glyphs == null) {
            return;
        }

        /*
         * Glyph-level readings are catalogue/import readings. They must stay
         * available after a TTL/GraphDB roundtrip so that blocks with several
         * possible glyph readings can still be expanded.
         *
         * Do NOT derive these catalogue variants from the document-specific
         * SelectedReading on NT2Element.  SelectedReading belongs to the
         * concrete NumericElement and is written later in writeNumericElementNode().
         *
         * For bracketed/component blocks the structural GlyphElementNode reached
         * through the graph can be position-shifted.  Therefore the safest key
         * for catalogue variants is the glyph label stored in the alternatives
         * map itself.  NT1 initial readings are used only as a fallback.
         */
        Map<String, ArrayList<Reading>> catalogueReadingsByGlyphLabel =
                collectCatalogueReadingsByGlyphLabel(block, interf);

        int glyphIndex = 1;

        for (GlyphElementNode glyph : glyphs) {

            String glyphLabel = cleanGlyphLabel(glyph.getLabel());
            String glyphUri = RdfUriFactory.ex(
                    "glyph-" + RdfUriFactory.sanitize(glyphLabel)
            );

            glyphUris.put(glyph, glyphUri);

            ttl.append(blockUri)
               .append(" allmah:hasGlyphElement ")
               .append(glyphUri)
               .append(" .\n");

            if (!writtenGlyphUris.contains(glyphUri)) {
                writtenGlyphUris.add(glyphUri);

                ttl.append(glyphUri).append("\n");
                ttl.append("    a allmah:GlyphElement ;\n");
                ttl.append("    rdfs:label ")
                   .append(TurtleEscaper.literal(glyphLabel))
                   .append(" ;\n");
                ttl.append("    allmah:catalogNumber ")
                   .append(TurtleEscaper.literal(glyphLabel));

                String glyphType =
                        GlyphTypeMapper.toGlyphTypeIndividual(glyphLabel);

                if (glyphType != null) {
                    ttl.append(" ;\n");
                    ttl.append("    allmah:hasGlyphType ")
                       .append(glyphType);
                }

                if ("??".equals(glyphLabel)) {
                    ttl.append(" ;\n");
                    ttl.append("    allmah:literalValue ")
                       .append(TurtleEscaper.literal("??"));
                }

                String glyphImageUri = getGlyphImageUri(glyph);
                if (glyphImageUri != null && !glyphImageUri.isEmpty()) {
                    ttl.append(" ;\n");
                    ttl.append("    allmah:imageUri ")
                       .append(TurtleEscaper.literal(glyphImageUri));
                }

                ttl.append(" .\n\n");

                ArrayList<Reading> catalogueReadings =
                        globalCatalogueReadingsByGlyphLabel.get(glyphLabel);

                if (catalogueReadings == null || catalogueReadings.isEmpty()) {
                    catalogueReadings = catalogueReadingsByGlyphLabel.get(glyphLabel);
                }

                if (catalogueReadings != null && !catalogueReadings.isEmpty()) {
                    writeGlyphReadings(glyphUri, catalogueReadings);
                }
                else {
                    writeGlyphReadings(glyphUri, glyph);
                }
            }

            glyphIndex++;
        }
    }

    private void writeGlyphReadings(
            String glyphUri,
            ArrayList<Reading> readings
    ) {

        if (readings == null) {
            return;
        }

        int readingIndex = 1;

        for (Reading reading : readings) {
            if (reading == null) {
                continue;
            }
            writeSingleReading(
                    glyphUri,
                    glyphUri + "-reading-" + readingIndex,
                    reading,
                    "allmah:InitialReading",
                    readingIndex
            );
            readingIndex++;
        }
    }

    private void collectGlobalCatalogueReadings(
            ArrayList<HieroglyphenBlock> fallbackBlocks,
            ArrayList<DocumentReading> docReadings,
            AllmahGUI interf
    ) {
        if (docReadings != null && !docReadings.isEmpty()) {
            for (DocumentReading dr : docReadings) {
                if (dr == null || dr.getBlocks() == null) {
                    continue;
                }

                for (String blockId : dr.getBlocks()) {
                    HieroglyphenBlock block = getBlockById(interf, blockId);
                    mergeCatalogueReadings(
                            globalCatalogueReadingsByGlyphLabel,
                            collectCatalogueReadingsByGlyphLabel(block, interf)
                    );
                }
            }
        }
        else if (fallbackBlocks != null) {
            for (HieroglyphenBlock block : fallbackBlocks) {
                mergeCatalogueReadings(
                        globalCatalogueReadingsByGlyphLabel,
                        collectCatalogueReadingsByGlyphLabel(block, interf)
                );
            }
        }
    }

    private void mergeCatalogueReadings(
            Map<String, ArrayList<Reading>> target,
            Map<String, ArrayList<Reading>> source
    ) {
        if (target == null || source == null) {
            return;
        }

        for (Map.Entry<String, ArrayList<Reading>> entry : source.entrySet()) {
            addReadingsByGlyphLabel(
                    target,
                    entry.getKey(),
                    entry.getValue()
            );
        }
    }

    private Map<String, ArrayList<Reading>> collectCatalogueReadingsByGlyphLabel(
            HieroglyphenBlock block,
            AllmahGUI interf
    ) {
        Map<String, ArrayList<Reading>> result =
                new HashMap<String, ArrayList<Reading>>();

        if (block == null) {
            return result;
        }

        /*
         * Primary source: the glyph alternative map, keyed by the real glyph
         * token from the block expression (for example 24st, 585st). This keeps
         * all possible readings and is not affected by a shifted parent object.
         */
        if (block.getElements() != null) {
            for (GlyphElementNode glyph : block.getElements()) {
                if (glyph == null) {
                    continue;
                }

                if (glyph.getAlternatives() != null
                        && !glyph.getAlternatives().isEmpty()) {
                    for (Map.Entry<String, ArrayList<Reading>> entry
                            : glyph.getAlternatives().entrySet()) {
                        String key = cleanGlyphLabel(entry.getKey());
                        addReadingsByGlyphLabel(result, key, entry.getValue());
                    }
                }

                String ownLabel = cleanGlyphLabel(glyph.getLabel());
                addReadingsByGlyphLabel(result, ownLabel, glyph.getReadings());
            }
        }

        /*
         * Fallback: NT1 initial readings.  These are still catalogue-level
         * readings, unlike NT2 SelectedReading.  Never use selected readings
         * here; otherwise a document-specific choice would erase other variants
         * from the glyph catalogue on export.
         */
        if (block.getNumTranslit1() != null) {
            for (String nt1Id : block.getNumTranslit1()) {
                NumTranslit1 n1 =
                        getById(interf, "nt1", "mnt1", nt1Id);

                if (n1 == null || n1.getElements() == null) {
                    continue;
                }

                for (NT1Element element : n1.getElements()) {
                    if (element == null) {
                        continue;
                    }

                    String glyphLabel = cleanGlyphLabel(element.getLabel());
                    addReadingsByGlyphLabel(
                            result,
                            glyphLabel,
                            element.getInitialReadings()
                    );
                }
            }
        }

        return result;
    }

    private boolean isNumericParserFallbackReading(
            String glyphLabel,
            String readingValue,
            String readingType
    ) {
        String glyphKey = cleanGlyphLabel(glyphLabel);
        String readingKey = cleanGlyphLabel(readingValue);

        if (glyphKey == null || readingKey == null) {
            return false;
        }

        if (!glyphKey.equals(readingKey)) {
            return false;
        }

        if (readingType == null || readingType.trim().isEmpty()) {
            return true;
        }

        String typ = readingType.trim().toLowerCase();
        return "no".equals(typ)
                || "numeric".equals(typ)
                || "number".equals(typ);
    }

    private void addReadingsByGlyphLabel(
            Map<String, ArrayList<Reading>> result,
            String glyphLabel,
            ArrayList<Reading> readings
    ) {
        if (result == null || glyphLabel == null
                || glyphLabel.trim().isEmpty()
                || readings == null || readings.isEmpty()) {
            return;
        }

        ArrayList<Reading> target = result.get(glyphLabel);
        if (target == null) {
            target = new ArrayList<Reading>();
            result.put(glyphLabel, target);
        }

        for (Reading reading : readings) {
            if (reading == null) {
                continue;
            }
            String newValue = cleanPlainLabel(reading.getReading());
            String newType = cleanPlainLabel(reading.getTyp());

            if (isNumericParserFallbackReading(glyphLabel, newValue, newType)) {
                continue;
            }

            boolean exists = false;
            for (Reading existing : target) {
                if (existing == null) {
                    continue;
                }
                if (newValue.equals(cleanPlainLabel(existing.getReading()))
                        && newType.equals(cleanPlainLabel(existing.getTyp()))) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                target.add(reading);
            }
        }
    }

    private void writeGlyphReadings(
            String glyphUri,
            GlyphElementNode glyph
    ) {

        int readingIndex = 1;

        if (glyph.getReadings() != null) {
            for (Reading reading : glyph.getReadings()) {
                writeSingleReading(
                        glyphUri,
                        glyphUri + "-reading-" + readingIndex,
                        reading,
                        "allmah:InitialReading",
                        readingIndex
                );
                readingIndex++;
            }
        }

        if (glyph.getAlternatives() != null
                && !glyph.getAlternatives().isEmpty()) {

            for (Map.Entry<String, ArrayList<Reading>> entry
                    : glyph.getAlternatives().entrySet()) {

                String alternativeLabel = entry.getKey();
                ArrayList<Reading> readings = entry.getValue();

                if (readings == null) {
                    continue;
                }

                int alternativeReadingIndex = 1;

                for (Reading reading : readings) {
                    writeSingleReading(
                            glyphUri,
                            glyphUri
                                    + "-alternative-"
                                    + RdfUriFactory.sanitize(alternativeLabel)
                                    + "-reading-"
                                    + alternativeReadingIndex,
                            reading,
                            "allmah:PossibleReading",
                            alternativeReadingIndex
                    );
                    alternativeReadingIndex++;
                }
            }
        }
    }

    private void writeGraphRelations(
            String blockUri,
            HieroglyphenBlock block
    ) {

        Graph<GlyphNode, OperatorLink> graph =
                block.getEntireGraphHB();

        if (graph == null) {
            return;
        }

        int relationIndex = 1;

        for (OperatorLink edge : graph.getEdges()) {

            if (edge == null) {
                continue;
            }

            String operator =
                    operatorTypeForLevel(edge.getLabel(), "Structural");

            if (operator == null) {
                continue;
            }

            GlyphNode source =
                    graph.getSource(edge);

            GlyphNode target =
                    graph.getDest(edge);

            if (!(source instanceof GlyphElementNode)
                    || !(target instanceof GlyphElementNode)) {
                continue;
            }

            String sourceUri =
                    glyphUris.get((GlyphElementNode) source);

            String targetUri =
                    glyphUris.get((GlyphElementNode) target);

            if (sourceUri == null || targetUri == null) {
                continue;
            }

            String relationUri =
                    blockUri + "-rel-" + relationIndex;

            ttl.append(blockUri)
               .append(" allmah:hasGlyphRelation ")
               .append(relationUri)
               .append(" .\n");

            ttl.append(relationUri).append("\n");
            ttl.append("    a allmah:StructuralRelation ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(edge.getLabel())))
               .append(" ;\n");
            ttl.append("    allmah:hasSource ")
               .append(sourceUri)
               .append(" ;\n");
            ttl.append("    allmah:hasTarget ")
               .append(targetUri)
               .append(" ;\n");
            ttl.append("    allmah:hasOperator ")
               .append(operator)
               .append(" ;\n");
            ttl.append("    allmah:operatorSymbol ")
               .append(TurtleEscaper.literal(cleanPlainLabel(edge.getLabel())))
               .append(" ;\n");
            ttl.append("    allmah:orderIndex ")
               .append(relationIndex)
               .append(" .\n\n");

            writeOperatorType(operator, edge.getLabel(), "Structural");

            relationIndex++;
        }
    }

    private void writeSingleReading(
            String subjectUri,
            String assignmentBaseUri,
            Reading reading,
            String readingRole,
            int orderIndex
    ) {

        String readingValue = reading.getReading();
        String readingType = reading.getTyp();
        String readingKey = cleanPlainLabel(readingValue) + "-" + readingType;

        String readingUri =
                RdfUriFactory.ex("reading-" + RdfUriFactory.sanitize(readingKey));

        String assignmentUri = assignmentBaseUri + "-assignment";

        ttl.append(subjectUri)
           .append(" allmah:hasReadingAssignment ")
           .append(assignmentUri)
           .append(" .\n");

        ttl.append(assignmentUri).append("\n");
        ttl.append("    a allmah:ReadingAssignment ;\n");
        ttl.append("    allmah:assignedReading ")
           .append(readingUri)
           .append(" ;\n");
        ttl.append("    allmah:hasReadingRole ")
           .append(readingRole)
           .append(" ;\n");
        ttl.append("    allmah:orderIndex ")
           .append(orderIndex)
           .append(" .\n\n");

        if (writtenReadings.contains(readingUri)) {
            return;
        }

        writtenReadings.add(readingUri);

        ttl.append(readingUri).append("\n");
        ttl.append("    a allmah:Reading ;\n");
        ttl.append("    rdfs:label ")
           .append(TurtleEscaper.literal(cleanPlainLabel(readingValue)))
           .append(" ;\n");
        ttl.append("    allmah:literalValue ")
           .append(TurtleEscaper.literal(cleanPlainLabel(readingValue)))
           .append(" ;\n");

        int readingConfidence = normalizeConfidence(reading.getConfidence());
        if (readingConfidence > 0) {
            ttl.append("    allmah:confidence ")
               .append(readingConfidence)
               .append(" ;\n");
        }

        ttl.append("    allmah:state ")
           .append(reading.getState())
           .append(" ;\n");
        ttl.append("    allmah:readingType ")
           .append(TurtleEscaper.literal(readingType));

        String sourceReadingUri = reading.getSourceUri();
        if (sourceReadingUri != null && !sourceReadingUri.isEmpty()) {
            ttl.append(" ;\n");
            ttl.append("    allmah:sourceUri ")
               .append(TurtleEscaper.literal(sourceReadingUri));
        }

        ttl.append(" .\n\n");
    }





    private void writeNT1s(
            String blockUri,
            String blockInternalId,
            HieroglyphenBlock block,
            AllmahGUI interf
    ) {

        if (block.getNumTranslit1() == null) {
            return;
        }

        int nt1Index = 1;

        for (String nt1Id : block.getNumTranslit1()) {

            NumTranslit1 n1 =
                    getById(interf, "nt1", "mnt1", nt1Id);

            if (n1 == null) {
                nt1Index++;
                continue;
            }

            String nt1InternalId =
                    blockInternalId + ">NT1_" + (nt1Index - 1);

            String nt1Uri =
                    RdfUriFactory.fromInternalId(nt1InternalId);

            ttl.append(blockUri)
               .append(" allmah:hasAlphanumericTransliteration ")
               .append(nt1Uri)
               .append(" .\n");

            ttl.append(nt1Uri).append("\n");
            ttl.append("    a allmah:AlphanumericTransliteration ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(n1.calculateLabel())))
               .append(" ;\n");
            ttl.append("    allmah:internalId ")
               .append(TurtleEscaper.literal(nt1InternalId))
               .append(" ;\n");
            writeConfidenceProperty(n1.getInterpretationConfidence());
            ttl.append("    allmah:orderIndex ")
               .append(nt1Index)
               .append(" .\n\n");

            writeNT1Elements(nt1Uri, nt1InternalId, n1);
            writeNT2s(nt1Uri, nt1InternalId, n1, interf);

            nt1Index++;
        }
    }

    private void writeNT1Elements(
            String nt1Uri,
            String nt1InternalId,
            NumTranslit1 nt1
    ) {

        if (nt1.getElements() == null) {
            return;
        }

        int elementIndex = 1;

        for (NT1Element element : nt1.getElements()) {

            String elementInternalId =
                    nt1InternalId + ">NT1E_" + elementIndex;

            String elementUri =
                    RdfUriFactory.fromInternalId(elementInternalId);

            ttl.append(nt1Uri)
               .append(" allmah:hasAlphanumericElement ")
               .append(elementUri)
               .append(" .\n");

            ttl.append(elementUri).append("\n");
            ttl.append("    a allmah:AlphanumericElement ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(element.getLabel())))
               .append(" ;\n");
            ttl.append("    allmah:internalId ")
               .append(TurtleEscaper.literal(elementInternalId))
               .append(" ;\n");
            ttl.append("    allmah:literalValue ")
               .append(TurtleEscaper.literal(cleanPlainLabel(element.getLabel())))
               .append(" ;\n");
            ttl.append("    allmah:orderIndex ")
               .append(elementIndex);

            String parentGlyphUri =
                    RdfUriFactory.ex(
                            "glyph-" + RdfUriFactory.sanitize(
                                    cleanGlyphLabel(element.getLabel())
                            )
                    );

            if (parentGlyphUri == null || parentGlyphUri.endsWith("glyph-")) {
                parentGlyphUri = glyphUris.get(element.getParent());
            }

            if (parentGlyphUri != null) {
                ttl.append(" ;\n");
                ttl.append("    allmah:hasParentElement ")
                   .append(parentGlyphUri);
            }

            ttl.append(" .\n\n");

            nt1ElementUris.put(element, elementUri);

            elementIndex++;
        }
    }

    private void writeNT2s(
            String nt1Uri,
            String nt1InternalId,
            NumTranslit1 nt1,
            AllmahGUI interf
    ) {

        if (nt1.getNT2() == null) {
            return;
        }

        int nt2Index = 1;

        for (String nt2Id : nt1.getNT2()) {

            NumTranslit2 n2 =
                    getById(interf, "nt2", "mnt2", nt2Id);

            if (n2 == null) {
                nt2Index++;
                continue;
            }

            String nt2InternalId =
                    nt1InternalId + ">NT2_" + (nt2Index - 1);

            String nt2Uri =
                    RdfUriFactory.fromInternalId(nt2InternalId);

            ttl.append(nt1Uri)
               .append(" allmah:hasNumericTransliteration ")
               .append(nt2Uri)
               .append(" .\n");

            ttl.append(nt2Uri).append("\n");
            ttl.append("    a allmah:NumericTransliteration ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(buildNT2TransliterationLabel(n2)))
               .append(" ;\n");
            ttl.append("    allmah:internalId ")
               .append(TurtleEscaper.literal(nt2InternalId))
               .append(" ;\n");
            writeConfidenceProperty(n2.getInterpretationConfidence());
            ttl.append("    allmah:orderIndex ")
               .append(nt2Index)
               .append(" .\n\n");

            writeNT2Elements(nt2Uri, nt2InternalId, n2);
            writeGT1s(nt2Uri, nt2InternalId, n2, interf);

            nt2Index++;
        }
    }

    private void writeNT2Elements(
            String nt2Uri,
            String nt2InternalId,
            NumTranslit2 nt2
    ) {

        if (nt2.getElements() == null) {
            return;
        }

        Map<ComponentElement, ArrayList<NT2Element>> componentMembers =
                new IdentityHashMap<ComponentElement, ArrayList<NT2Element>>();

        int elementIndex = 1;

        for (NT2Element element : nt2.getElements()) {

            String elementInternalId = nt2InternalId + ">NT2E_" + elementIndex;
            String elementUri = RdfUriFactory.fromInternalId(elementInternalId);

            ComponentElement component = element.getComponentElement();

            if (component == null) {
                ttl.append(nt2Uri)
                   .append(" allmah:hasNumericElement ")
                   .append(elementUri)
                   .append(" .\n");
            }

            writeNumericElementNode(elementUri, elementInternalId, element, elementIndex);

            nt2ElementUris.put(element, elementUri);
            rememberNT2ElementUriByLabel(element, elementUri);

            if (component != null) {
                ArrayList<NT2Element> members = componentMembers.get(component);
                if (members == null) {
                    members = new ArrayList<NT2Element>();
                    componentMembers.put(component, members);
                }
                members.add(element);
            }

            elementIndex++;
        }

        int componentIndex = 1;
        for (Map.Entry<ComponentElement, ArrayList<NT2Element>> entry
                : componentMembers.entrySet()) {

            ComponentElement component = entry.getKey();
            ArrayList<NT2Element> members = entry.getValue();
            String componentUri = getLevelComponentUri(
                    numericComponentUris,
                    component,
                    nt2InternalId + ">NC_" + componentIndex
            );

            ttl.append(nt2Uri)
               .append(" allmah:hasNumericComponent ")
               .append(componentUri)
               .append(" .\n");

            writeNumericComponent(componentUri, component, members, componentIndex);
            componentIndex++;
        }
    }

    private void writeNumericElementNode(
            String elementUri,
            String elementInternalId,
            NT2Element element,
            int elementIndex
    ) {
        ttl.append(elementUri).append("\n");
        ttl.append("    a allmah:NumericElement ;\n");
        ttl.append("    rdfs:label ")
           .append(TurtleEscaper.literal(cleanPlainLabel(element.getSimpleLabel())))
           .append(" ;\n");
        ttl.append("    allmah:internalId ")
           .append(TurtleEscaper.literal(elementInternalId))
           .append(" ;\n");
        ttl.append("    allmah:literalValue ")
           .append(TurtleEscaper.literal(cleanPlainLabel(element.getSimpleLabel())))
           .append(" ;\n");
        ttl.append("    allmah:orderIndex ")
           .append(elementIndex);

        String parentUri = nt1ElementUris.get(element.getParent());
        if (parentUri != null) {
            ttl.append(" ;\n");
            ttl.append("    allmah:hasParentElement ")
               .append(parentUri);
        }

        ttl.append(" .\n\n");

        writeSelectedReadingAssignmentsForNumericElement(
                elementUri,
                element,
                elementIndex
        );
    }

    private void writeSelectedReadingAssignmentsForNumericElement(
            String numericElementUri,
            NT2Element element,
            int elementIndex
    ) {
        if (numericElementUri == null || element == null
                || element.getParent() == null) {
            return;
        }

        ArrayList<Reading> selected = element.getReadings("selected");

        if (selected == null || selected.isEmpty()) {
            return;
        }

        int readingIndex = 1;

        for (Reading reading : selected) {
            if (reading == null) {
                continue;
            }

            writeSingleReading(
                    numericElementUri,
                    numericElementUri
                            + "-selected-reading-"
                            + readingIndex,
                    reading,
                    "allmah:SelectedReading",
                    readingIndex
            );

            readingIndex++;
        }
    }

    private void writeNumericComponent(
            String componentUri,
            ComponentElement component,
            ArrayList<NT2Element> members,
            int orderIndex
    ) {
        if (componentUri == null || writtenComponentUris.contains(componentUri)) {
            return;
        }
        writtenComponentUris.add(componentUri);

        String sourceLabel = buildNT2SourceLabel(members);

        ttl.append(componentUri).append("\n");
        ttl.append("    a allmah:NumericComponent ;\n");
        ttl.append("    rdfs:label ")
           .append(TurtleEscaper.literal("Component " + sourceLabel))
           .append(" ;\n");
        ttl.append("    allmah:componentSourceLabel ")
           .append(TurtleEscaper.literal(sourceLabel))
           .append(" ;\n");
        ttl.append("    allmah:componentLabel ")
           .append(TurtleEscaper.literal(cleanComponentLabel(component)))
           .append(" ;\n");
        ttl.append("    allmah:componentColor ")
           .append(TurtleEscaper.literal(encodeColor(component.getcolor())))
           .append(" ;\n");
        ttl.append("    allmah:orderIndex ")
           .append(orderIndex);

        if (members != null) {
            for (NT2Element member : members) {
                String memberUri = nt2ElementUris.get(member);
                if (memberUri != null) {
                    ttl.append(" ;\n");
                    ttl.append("    allmah:hasSource ")
                       .append(memberUri);
                    ttl.append(" ;\n");
                    ttl.append("    allmah:hasNumericElement ")
                       .append(memberUri);
                }
            }
        }

        ttl.append(" .\n\n");
    }

    private void writeGT1s(
            String nt2Uri,
            String nt2InternalId,
            NumTranslit2 nt2,
            AllmahGUI interf
    ) {

        if (nt2.getGT1() == null) {
            return;
        }

        int gt1Index = 1;

        for (String gt1Id : nt2.getGT1()) {

            GraphTranslit1 g1 =
                    getById(interf, "gt1", "mgt1", gt1Id);

            if (g1 == null) {
                gt1Index++;
                continue;
            }

            String gt1InternalId =
                    nt2InternalId + ">GT1_" + (gt1Index - 1);

            String gt1Uri =
                    RdfUriFactory.fromInternalId(gt1InternalId);

            ttl.append(nt2Uri)
               .append(" allmah:hasGraphematicTransliteration ")
               .append(gt1Uri)
               .append(" .\n");

            ttl.append(gt1Uri).append("\n");
            ttl.append("    a allmah:GraphematicTransliteration ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(buildGT1TransliterationLabel(g1)))
               .append(" ;\n");
            ttl.append("    allmah:internalId ")
               .append(TurtleEscaper.literal(gt1InternalId))
               .append(" ;\n");
            writeConfidenceProperty(g1.getInterpretationConfidence());
            ttl.append("    allmah:orderIndex ")
               .append(gt1Index)
               .append(" .\n\n");

            writeGT1Elements(gt1Uri, gt1InternalId, g1);
            writeGT2s(gt1Uri, gt1InternalId, g1, interf);

            gt1Index++;
        }
    }

    private void writeGT1Elements(
            String gt1Uri,
            String gt1InternalId,
            GraphTranslit1 gt1
    ) {

        if (gt1.getElements() == null) {
            return;
        }

        Map<ComponentElement, ArrayList<GT1Element>> componentMembers =
                new IdentityHashMap<ComponentElement, ArrayList<GT1Element>>();

        int elementIndex = 1;

        for (GT1Element element : gt1.getElements()) {

            String elementInternalId = gt1InternalId + ">GT1E_" + elementIndex;
            String elementUri = RdfUriFactory.fromInternalId(elementInternalId);

            ComponentElement component = componentOfGT1Element(element);

            if (component == null) {
                ttl.append(gt1Uri)
                   .append(" allmah:hasGraphematicElement ")
                   .append(elementUri)
                   .append(" .\n");
            }

            writeGraphematicElementNode(elementUri, elementInternalId, element, elementIndex);

            gt1ElementUris.put(element, elementUri);

            if (component != null) {
                ArrayList<GT1Element> members = componentMembers.get(component);
                if (members == null) {
                    members = new ArrayList<GT1Element>();
                    componentMembers.put(component, members);
                }
                members.add(element);
            }

            elementIndex++;
        }

        int componentIndex = 1;
        for (Map.Entry<ComponentElement, ArrayList<GT1Element>> entry
                : componentMembers.entrySet()) {

            ComponentElement component = entry.getKey();
            ArrayList<GT1Element> members = entry.getValue();
            String componentUri = getLevelComponentUri(
                    graphematicComponentUris,
                    component,
                    gt1InternalId + ">GC_" + componentIndex
            );

            ttl.append(gt1Uri)
               .append(" allmah:hasGraphematicComponent ")
               .append(componentUri)
               .append(" .\n");

            writeGraphematicComponent(componentUri, component, members, componentIndex);
            componentIndex++;
        }
    }

    private void writeGraphematicElementNode(
            String elementUri,
            String elementInternalId,
            GT1Element element,
            int elementIndex
    ) {
        ttl.append(elementUri).append("\n");
        ttl.append("    a allmah:GraphematicElement ;\n");
        ttl.append("    rdfs:label ")
           .append(TurtleEscaper.literal(cleanPlainLabel(element.getSimpleLabel())))
           .append(" ;\n");
        ttl.append("    allmah:internalId ")
           .append(TurtleEscaper.literal(elementInternalId))
           .append(" ;\n");
        ttl.append("    allmah:literalValue ")
           .append(TurtleEscaper.literal(cleanPlainLabel(element.getSimpleLabel())))
           .append(" ;\n");
        ttl.append("    allmah:orderIndex ")
           .append(elementIndex);

        String parentUri = nt2ElementUris.get(element.getParent());
        if (parentUri != null) {
            ttl.append(" ;\n");
            ttl.append("    allmah:hasParentElement ")
               .append(parentUri);
        }

        ttl.append(" .\n\n");
    }

    private void writeGraphematicComponent(
            String componentUri,
            ComponentElement component,
            ArrayList<GT1Element> members,
            int orderIndex
    ) {
        if (componentUri == null || writtenComponentUris.contains(componentUri)) {
            return;
        }
        writtenComponentUris.add(componentUri);

        String sourceLabel = buildGT1SourceLabel(members);

        ttl.append(componentUri).append("\n");
        ttl.append("    a allmah:GraphematicComponent ;\n");
        ttl.append("    rdfs:label ")
           .append(TurtleEscaper.literal("Component " + sourceLabel))
           .append(" ;\n");
        ttl.append("    allmah:componentSourceLabel ")
           .append(TurtleEscaper.literal(sourceLabel))
           .append(" ;\n");
        ttl.append("    allmah:componentLabel ")
           .append(TurtleEscaper.literal(cleanComponentLabel(component)))
           .append(" ;\n");
        ttl.append("    allmah:componentColor ")
           .append(TurtleEscaper.literal(encodeColor(component.getcolor())))
           .append(" ;\n");
        ttl.append("    allmah:orderIndex ")
           .append(orderIndex);

        if (members != null) {
            for (GT1Element member : members) {
                String memberUri = gt1ElementUris.get(member);
                if (memberUri != null) {
                    ttl.append(" ;\n");
                    ttl.append("    allmah:hasGraphematicElement ")
                       .append(memberUri);
                }
            }
        }

        String numericComponentUri = numericComponentUris.get(component);
        if (numericComponentUri != null) {
            ttl.append(" ;\n");
            ttl.append("    allmah:hasSource ")
               .append(numericComponentUri);
        }

        ttl.append(" .\n\n");
    }

    private void writeGT2s(
            String gt1Uri,
            String gt1InternalId,
            GraphTranslit1 gt1,
            AllmahGUI interf
    ) {

        ArrayList<String> gt2Ids = gt1.getGraphTranslit2();
        if (gt2Ids == null) {
            return;
        }

        int gt2Index = 1;

        for (String gt2Id : gt2Ids) {

            GraphTranslit2 g2 = getById(interf, "gt2", "mgt2", gt2Id);
            if (g2 == null) {
                gt2Index++;
                continue;
            }

            String gt2InternalId = gt1InternalId + ">GT2_" + (gt2Index - 1);
            String gt2Uri = RdfUriFactory.fromInternalId(gt2InternalId);

            ttl.append(gt1Uri)
               .append(" allmah:hasGraphemicTransliteration ")
               .append(gt2Uri)
               .append(" .\n");

            ttl.append(gt2Uri).append("\n");
            ttl.append("    a allmah:GraphemicTransliteration ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(g2.calculateLabel())))
               .append(" ;\n");
            ttl.append("    allmah:internalId ")
               .append(TurtleEscaper.literal(gt2InternalId))
               .append(" ;\n");
            writeConfidenceProperty(g2.getInterpretationConfidence());
            ttl.append("    allmah:orderIndex ")
               .append(gt2Index);

            if (g2.getFinish()) {
                ttl.append(" ;\n");
                ttl.append("    allmah:isFrozen true");
            }

            ttl.append(" .\n\n");

            Map<GraphGT2Node, String> gt2ElementNodeUris =
                    writeGT2Elements(gt2Uri, gt2InternalId, g2);
            writePhonemicTranscriptions(
                    gt2Uri,
                    gt2InternalId,
                    g2,
                    interf,
                    gt2ElementNodeUris
            );

            gt2Index++;
        }
    }

    private Map<GraphGT2Node, String> writeGT2Elements(
            String gt2Uri,
            String gt2InternalId,
            GraphTranslit2 gt2
    ) {

        Map<GraphGT2Node, String> gt2ElementNodeUris =
                new IdentityHashMap<GraphGT2Node, String>();

        if (gt2.getElements() == null) {
            return gt2ElementNodeUris;
        }

        Map<GraphGT2Node, String> gt2NodeUris =
                new IdentityHashMap<GraphGT2Node, String>();

        Map<ComponentElement, ArrayList<GraphGT2Node>> componentMembers =
                new IdentityHashMap<ComponentElement, ArrayList<GraphGT2Node>>();

        int elementIndex = 1;

        for (GraphGT2Node graphNode : gt2.getElements()) {

            if (graphNode == null || graphNode.getGT2Element() == null) {
                continue;
            }

            GT2Element element = graphNode.getGT2Element();
            ComponentElement component = componentOfGT2Element(element);

            String elementInternalId = gt2InternalId + ">GT2E_" + elementIndex;
            String elementUri = RdfUriFactory.fromInternalId(elementInternalId);

            writeGraphemicElementNode(elementUri, elementInternalId, element, elementIndex);
            gt2ElementNodeUris.put(graphNode, elementUri);

            if (component == null) {
                ttl.append(gt2Uri)
                   .append(" allmah:hasGraphemicElement ")
                   .append(elementUri)
                   .append(" .\n");
                gt2NodeUris.put(graphNode, elementUri);
            } else {
                ArrayList<GraphGT2Node> members = componentMembers.get(component);
                if (members == null) {
                    members = new ArrayList<GraphGT2Node>();
                    componentMembers.put(component, members);
                }
                members.add(graphNode);
            }

            elementIndex++;
        }

        int componentIndex = 1;
        for (Map.Entry<ComponentElement, ArrayList<GraphGT2Node>> entry
                : componentMembers.entrySet()) {

            ComponentElement component = entry.getKey();
            ArrayList<GraphGT2Node> members = entry.getValue();
            String componentUri = getLevelComponentUri(
                    graphemicComponentUris,
                    component,
                    gt2InternalId + ">GMC_" + componentIndex
            );

            ttl.append(gt2Uri)
               .append(" allmah:hasGraphemicComponent ")
               .append(componentUri)
               .append(" .\n");

            writeGraphemicComponent(componentUri, component, members, componentIndex);

            /*
             * For graph relations, any edge that points to a member of a
             * component should point to the component node itself in RDF.
             */
            for (GraphGT2Node member : members) {
                gt2NodeUris.put(member, componentUri);
            }

            componentIndex++;
        }

        writeGT2GraphRelations(gt2Uri, gt2InternalId, gt2, gt2NodeUris);
        return gt2ElementNodeUris;
    }


    private void writePhonemicTranscriptions(
            String gt2Uri,
            String gt2InternalId,
            GraphTranslit2 gt2,
            AllmahGUI interf,
            Map<GraphGT2Node, String> gt2ElementNodeUris
    ) {
        if (gt2 == null || gt2.getPhonemicId() == null) {
            return;
        }

        int phonemicIndex = 1;
        for (String phonemicId : gt2.getPhonemicId()) {
            PhonemTranslit phonemic = getById(interf, "pt", "mpt", phonemicId);
            if (phonemic == null) {
                phonemicIndex++;
                continue;
            }

            String phonemicInternalId = gt2InternalId + ">PT_" + (phonemicIndex - 1);
            String phonemicUri = RdfUriFactory.fromInternalId(phonemicInternalId);

            ttl.append(gt2Uri)
               .append(" allmah:hasPhonemicTranscription ")
               .append(phonemicUri)
               .append(" .\n");

            ttl.append(phonemicUri).append("\n");
            ttl.append("    a allmah:PhonemicTranscription ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(phonemic.calculateLabel())))
               .append(" ;\n");
            ttl.append("    allmah:internalId ")
               .append(TurtleEscaper.literal(phonemicInternalId))
               .append(" ;\n");
            ttl.append("    allmah:hasParentInterpretationLevel ")
               .append(gt2Uri)
               .append(" ;\n");
            writeConfidenceProperty(phonemic.getInterpretationConfidence());
            ttl.append("    allmah:orderIndex ")
               .append(phonemicIndex);

            if (phonemic.getFinish()) {
                ttl.append(" ;\n");
                ttl.append("    allmah:isFrozen true");
            }

            ttl.append(" .\n\n");

            Map<PhonemNode, String> phonemicNodeUris =
                    writePhonemicElements(
                            phonemicUri,
                            phonemicInternalId,
                            phonemic,
                            gt2ElementNodeUris
                    );
            writePhonemicRelations(
                    phonemicUri,
                    phonemic,
                    phonemicNodeUris
            );
            writeMorphoSyntacticTranscriptions(
                    phonemicUri,
                    phonemicInternalId,
                    phonemic,
                    interf,
                    phonemicNodeUris
            );

            phonemicIndex++;
        }
    }

    private Map<PhonemNode, String> writePhonemicElements(
            String phonemicUri,
            String phonemicInternalId,
            PhonemTranslit phonemic,
            Map<GraphGT2Node, String> gt2ElementNodeUris
    ) {
        Map<PhonemNode, String> phonemicNodeUris =
                new IdentityHashMap<PhonemNode, String>();

        if (phonemic == null || phonemic.getElements() == null) {
            return phonemicNodeUris;
        }

        int elementIndex = 1;
        for (PhonemNode element : phonemic.getElements()) {
            if (element == null) {
                continue;
            }

            String elementInternalId = phonemicInternalId + ">PTE_" + elementIndex;
            String elementUri = RdfUriFactory.fromInternalId(elementInternalId);

            ttl.append(phonemicUri)
               .append(" allmah:hasPhonemicElement ")
               .append(elementUri)
               .append(" .\n");

            ttl.append(elementUri).append("\n");
            ttl.append("    a allmah:PhonemicElement ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(element.getLabel())))
               .append(" ;\n");
            ttl.append("    allmah:internalId ")
               .append(TurtleEscaper.literal(elementInternalId))
               .append(" ;\n");
            ttl.append("    allmah:literalValue ")
               .append(TurtleEscaper.literal(cleanPlainLabel(element.getLabel())))
               .append(" ;\n");
            ttl.append("    allmah:orderIndex ")
               .append(elementIndex);

            String parentUri = null;
            if (element.getParent() instanceof GraphGT2Node
                    && gt2ElementNodeUris != null) {
                parentUri = gt2ElementNodeUris.get((GraphGT2Node) element.getParent());
            }
            if (parentUri != null) {
                ttl.append(" ;\n");
                ttl.append("    allmah:hasParentElement ")
                   .append(parentUri);
            }

            ttl.append(" .\n\n");

            phonemicNodeUris.put(element, elementUri);
            elementIndex++;
        }

        return phonemicNodeUris;
    }

    private void writePhonemicRelations(
            String phonemicUri,
            PhonemTranslit phonemic,
            Map<PhonemNode, String> phonemicNodeUris
    ) {
        if (phonemic == null
                || phonemic.getGraphPhonem() == null
                || phonemicNodeUris == null
                || phonemicNodeUris.isEmpty()) {
            return;
        }

        int relationIndex = 1;
        Set<String> writtenRelationKeys = new HashSet<String>();

        for (OperatorLink edge : phonemic.getGraphPhonem().getEdges()) {
            if (edge == null) {
                continue;
            }

            String operator = operatorTypeForLevel(edge.getLabel(), "Phonemic");
            if (operator == null) {
                continue;
            }

            PhonemNode source = phonemic.getGraphPhonem().getSource(edge);
            PhonemNode target = phonemic.getGraphPhonem().getDest(edge);

            String sourceUri = phonemicNodeUris.get(source);
            String targetUri = phonemicNodeUris.get(target);

            if (sourceUri == null || targetUri == null || sourceUri.equals(targetUri)) {
                continue;
            }

            String key = sourceUri + "|" + edge.getLabel() + "|" + targetUri;
            if (!writtenRelationKeys.add(key)) {
                continue;
            }

            String relationUri = phonemicUri + "-phonrel-" + relationIndex;

            ttl.append(phonemicUri)
               .append(" allmah:hasPhonemicRelation ")
               .append(relationUri)
               .append(" .\n");

            ttl.append(relationUri).append("\n");
            ttl.append("    a allmah:PhonemicRelation ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(edge.getLabel())))
               .append(" ;\n");
            ttl.append("    allmah:hasSource ")
               .append(sourceUri)
               .append(" ;\n");
            ttl.append("    allmah:hasTarget ")
               .append(targetUri)
               .append(" ;\n");
            ttl.append("    allmah:hasOperator ")
               .append(operator)
               .append(" ;\n");
            ttl.append("    allmah:operatorSymbol ")
               .append(TurtleEscaper.literal(cleanPlainLabel(edge.getLabel())))
               .append(" ;\n");
            ttl.append("    allmah:orderIndex ")
               .append(relationIndex)
               .append(" .\n\n");

            writeOperatorType(operator, edge.getLabel(), "Phonemic");

            relationIndex++;
        }
    }


    private void writeMorphoSyntacticTranscriptions(
            String phonemicUri,
            String phonemicInternalId,
            PhonemTranslit phonemic,
            AllmahGUI interf,
            Map<PhonemNode, String> phonemicNodeUris
    ) {
        if (phonemic == null || phonemic.getMorphoTranscr() == null) {
            return;
        }

        int morphoIndex = 1;
        Set<String> writtenMorphoIds = new HashSet<String>();
        for (String morphoId : phonemic.getMorphoTranscr()) {
            if (morphoId == null || !writtenMorphoIds.add(morphoId)) {
                continue;
            }
            MorphoSyntTranslit morpho = getById(interf, "mt", "mmt", morphoId);
            if (morpho == null) {
                morphoIndex++;
                continue;
            }

            String morphoInternalId = phonemicInternalId + ">MT_" + (morphoIndex - 1);
            String morphoUri = RdfUriFactory.fromInternalId(morphoInternalId);

            String fullLabel = cleanPlainLabel(normalizeVisualMorphoLabel(getMorphoFullLabel(morpho)));
            String consolidatedLabel = cleanPlainLabel(normalizeVisualMorphoLabel(getMorphoConsolidatedLabel(morpho)));
            String finalConsolidatedLabel = cleanPlainLabel(normalizeVisualMorphoLabel(getMorphoFinalConsolidatedLabel(morpho)));

            ttl.append(phonemicUri)
               .append(" allmah:hasMorphosyntacticTranscription ")
               .append(morphoUri)
               .append(" .\n");

            ttl.append(morphoUri).append("\n");
            ttl.append("    a allmah:MorphosyntacticTranscription ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(fullLabel))
               .append(" ;\n");
            ttl.append("    allmah:internalId ")
               .append(TurtleEscaper.literal(morphoInternalId))
               .append(" ;\n");
            ttl.append("    allmah:literalValue ")
               .append(TurtleEscaper.literal(fullLabel))
               .append(" ;\n");
            ttl.append("    allmah:consolidatedLabel ")
               .append(TurtleEscaper.literal(consolidatedLabel))
               .append(" ;\n");
            ttl.append("    allmah:finalConsolidatedLabel ")
               .append(TurtleEscaper.literal(finalConsolidatedLabel))
               .append(" ;\n");
            ttl.append("    allmah:hasParentInterpretationLevel ")
               .append(phonemicUri)
               .append(" ;\n");
            writeConfidenceProperty(morpho.getInterpretationConfidence());
            ttl.append("    allmah:orderIndex ")
               .append(morphoIndex)
               .append(" .\n\n");

            Map<MorphoTranscrNode, String> morphoNodeUris =
                    writeMorphoSyntacticElements(
                            morphoUri,
                            morphoInternalId,
                            morpho,
                            phonemicNodeUris
                    );

            writeMorphosyntacticGlossings(
                    morphoUri,
                    morphoInternalId,
                    morpho,
                    interf,
                    morphoNodeUris
            );

            morphoIndex++;
        }
    }


    private String rawMorphoNodeLabel(MorphoTranscrNode node) {
        Object value = getFieldQuietly(node, "label");
        if (value == null) {
            return "";
        }
        return String.valueOf(value);
    }

    private String normalizeVisualMorphoLabel(String value) {
        if (value == null) {
            return "";
        }
        String s = value;
        String old;
        do {
            old = s;
            s = s.replaceAll("\\[\\[([^\\[\\]]+)\\]\\]", "[$1]");
            s = s.replaceAll("\\{\\{([^\\{\\}]+)\\}\\}", "{$1}");
            s = s.replaceAll("\\|\\|([^\\|]+)\\|\\|", "|$1|");
        } while (!s.equals(old));
        return s;
    }

    private Map<MorphoTranscrNode, String> writeMorphoSyntacticElements(
            String morphoUri,
            String morphoInternalId,
            MorphoSyntTranslit morpho,
            Map<PhonemNode, String> phonemicNodeUris
    ) {
        Map<MorphoTranscrNode, String> morphoNodeUris =
                new IdentityHashMap<MorphoTranscrNode, String>();

        if (morpho == null || morpho.getElem() == null) {
            return morphoNodeUris;
        }

        ArrayList<MorphoTranscrNode> allNodes =
                new ArrayList<MorphoTranscrNode>();

        int elementIndex = 1;
        for (MorphoTranscrNode node : morpho.getElem()) {
            if (node == null) {
                continue;
            }
            writeMorphoSyntacticNode(
                    morphoUri,
                    "allmah:hasMorphosyntacticElement",
                    morphoInternalId + ">MTE_" + elementIndex,
                    node,
                    elementIndex,
                    phonemicNodeUris,
                    morphoNodeUris,
                    allNodes
            );
            elementIndex++;
        }

        writeMorphoSyntacticParentLinks(morphoNodeUris, allNodes);
        return morphoNodeUris;
    }

    private void writeMorphoSyntacticNode(
            String ownerUri,
            String ownerProperty,
            String nodeInternalId,
            MorphoTranscrNode node,
            int orderIndex,
            Map<PhonemNode, String> phonemicNodeUris,
            Map<MorphoTranscrNode, String> morphoNodeUris,
            ArrayList<MorphoTranscrNode> allNodes
    ) {
        if (node == null) {
            return;
        }

        String nodeUri = RdfUriFactory.fromInternalId(nodeInternalId);

        ttl.append(ownerUri)
           .append(" ")
           .append(ownerProperty)
           .append(" ")
           .append(nodeUri)
           .append(" .\n");

        morphoNodeUris.put(node, nodeUri);
        allNodes.add(node);

        ttl.append(nodeUri).append("\n");
        ttl.append("    a allmah:MorphosyntacticElement ;\n");
        String nodeRawLabel = cleanPlainLabel(rawMorphoNodeLabel(node));
        String nodeDisplayLabel = cleanPlainLabel(normalizeVisualMorphoLabel(node.getLabel()));

        ttl.append("    rdfs:label ")
           .append(TurtleEscaper.literal(nodeDisplayLabel))
           .append(" ;\n");
        ttl.append("    allmah:internalId ")
           .append(TurtleEscaper.literal(nodeInternalId))
           .append(" ;\n");
        ttl.append("    allmah:literalValue ")
           .append(TurtleEscaper.literal(nodeRawLabel))
           .append(" ;\n");
        ttl.append("    allmah:consolidatedLabel ")
           .append(TurtleEscaper.literal(cleanPlainLabel(normalizeVisualMorphoLabel(node.calculateLabel1()))))
           .append(" ;\n");
        ttl.append("    allmah:finalConsolidatedLabel ")
           .append(TurtleEscaper.literal(cleanPlainLabel(normalizeVisualMorphoLabel(node.calculateLabel2()))))
           .append(" ;\n");
        ttl.append("    allmah:morphoNodeLevel ")
           .append(node.getLevelL())
           .append(" ;\n");
        ttl.append("    allmah:orderIndex ")
           .append(orderIndex);

        String phonemicParentUri = null;
        if (node.getPhonemParent() != null && phonemicNodeUris != null) {
            phonemicParentUri = phonemicNodeUris.get(node.getPhonemParent());
        }
        if (phonemicParentUri != null) {
            ttl.append(" ;\n");
            ttl.append("    allmah:hasParentElement ")
               .append(phonemicParentUri);
        }

        if (node.getDel()) {
            ttl.append(" ;\n");
            ttl.append("    allmah:isRemoved true");
        }
        if (node.getGeschw()) {
            ttl.append(" ;\n");
            ttl.append("    allmah:hasCurlyBrackets true");
        }
        if (node.getEckig()) {
            ttl.append(" ;\n");
            ttl.append("    allmah:hasSquareBrackets true");
        }
        if (node.getBar()) {
            ttl.append(" ;\n");
            ttl.append("    allmah:hasPipeBrackets true");
        }
        if (node.getAbstract()) {
            ttl.append(" ;\n");
            ttl.append("    allmah:isAbstract true");
        }

        ttl.append(" .\n\n");

        if (node.getChildren() != null) {
            int childIndex = 1;
            for (MorphoTranscrNode child : node.getChildren()) {
                if (child == null) {
                    continue;
                }
                writeMorphoSyntacticNode(
                        nodeUri,
                        "allmah:hasMorphosyntacticLetterElement",
                        nodeInternalId + ">L_" + childIndex,
                        child,
                        childIndex,
                        phonemicNodeUris,
                        morphoNodeUris,
                        allNodes
                );
                childIndex++;
            }
        }
    }

    private void writeMorphoSyntacticParentLinks(
            Map<MorphoTranscrNode, String> morphoNodeUris,
            ArrayList<MorphoTranscrNode> allNodes
    ) {
        if (morphoNodeUris == null || allNodes == null) {
            return;
        }

        for (MorphoTranscrNode node : allNodes) {
            String nodeUri = morphoNodeUris.get(node);
            if (nodeUri == null) {
                continue;
            }

            String newParentUri = morphoNodeUris.get(node.getParent());
            if (newParentUri != null) {
                ttl.append(nodeUri)
                   .append(" allmah:hasNewParentMorphosyntacticElement ")
                   .append(newParentUri)
                   .append(" .\n");
            }

            MorphoTranscrNode oldParent = getOldParentMorphoNode(node);
            String oldParentUri = morphoNodeUris.get(oldParent);
            if (oldParentUri != null) {
                ttl.append(nodeUri)
                   .append(" allmah:hasOldParentMorphosyntacticElement ")
                   .append(oldParentUri)
                   .append(" .\n");
            }
        }
        ttl.append("\n");
    }

    private String getMorphoFullLabel(MorphoSyntTranslit morpho) {
        if (morpho == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (morpho.getElem() != null) {
            for (MorphoTranscrNode node : morpho.getElem()) {
                if (node != null) {
                    sb.append(node.calculateLabel());
                }
            }
        }
        if (sb.length() > 0) {
            return sb.toString();
        }
        String label = morpho.calculateLabel();
        int dollar = label.indexOf("$");
        if (dollar >= 0) {
            return label.substring(0, dollar);
        }
        return label;
    }

    private String getMorphoConsolidatedLabel(MorphoSyntTranslit morpho) {
        if (morpho == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (morpho.getElem() != null) {
            for (MorphoTranscrNode node : morpho.getElem()) {
                if (node != null) {
                    sb.append(node.calculateLabel1());
                }
            }
        }
        if (sb.length() > 0) {
            return sb.toString();
        }
        String label = morpho.calculateLabel();
        int dollar = label.indexOf("$");
        int hash = label.indexOf("#");
        if (dollar >= 0 && hash > dollar) {
            return label.substring(dollar + 1, hash);
        }
        return morpho.getKonsolid();
    }

    private String getMorphoFinalConsolidatedLabel(MorphoSyntTranslit morpho) {
        if (morpho == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (morpho.getElem() != null) {
            for (MorphoTranscrNode node : morpho.getElem()) {
                if (node != null) {
                    sb.append(node.calculateLabel2());
                }
            }
        }
        if (sb.length() > 0) {
            return sb.toString();
        }
        Object value = getFieldQuietly(morpho, "finalKonsolid");
        if (value != null) {
            return String.valueOf(value);
        }

        String label = morpho.calculateLabel();
        if (label == null) {
            return "";
        }
        int hash = label.indexOf("#");
        if (hash >= 0 && hash + 1 < label.length()) {
            return label.substring(hash + 1);
        }
        return "";
    }

    private MorphoTranscrNode getOldParentMorphoNode(MorphoTranscrNode node) {
        Object value = getFieldQuietly(node, "oldparent");
        if (value instanceof MorphoTranscrNode) {
            return (MorphoTranscrNode) value;
        }
        return null;
    }


    private void writeMorphosyntacticGlossings(
            String morphoUri,
            String morphoInternalId,
            MorphoSyntTranslit morpho,
            AllmahGUI interf,
            Map<MorphoTranscrNode, String> morphoNodeUris
    ) {
        if (morpho == null || morpho.getMorphoGloss() == null
                || morpho.getMorphoGloss().isEmpty()) {
            return;
        }

        int glossIndex = 1;
        Set<String> writtenGlossIds = new HashSet<String>();

        for (String glossId : morpho.getMorphoGloss()) {
            if (glossId == null || !writtenGlossIds.add(glossId)) {
                continue;
            }

            MorphoSyntGlossing glossing = getById(interf, "mg", "mmg", glossId);
            if (glossing == null) {
                glossIndex++;
                continue;
            }

            String glossInternalId = morphoInternalId + ">MSG_" + (glossIndex - 1);
            String glossUri = RdfUriFactory.fromInternalId(glossInternalId);
            String glossLabel = cleanPlainLabel(glossing.calculateLabel());

            ttl.append(morphoUri)
               .append(" allmah:hasMorphosyntacticGlossing ")
               .append(glossUri)
               .append(" .\n");

            ttl.append(glossUri).append("\n");
            ttl.append("    a allmah:MorphosyntacticGlossing ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(glossLabel))
               .append(" ;\n");
            ttl.append("    allmah:internalId ")
               .append(TurtleEscaper.literal(glossInternalId))
               .append(" ;\n");
            ttl.append("    allmah:literalValue ")
               .append(TurtleEscaper.literal(glossLabel))
               .append(" ;\n");
            ttl.append("    allmah:hasParentInterpretationLevel ")
               .append(morphoUri)
               .append(" ;\n");
            writeConfidenceProperty(glossing.getInterpretationConfidence());
            ttl.append("    allmah:orderIndex ")
               .append(glossIndex)
               .append(" .\n\n");

            writeMorphosyntacticGlossingElements(
                    glossUri,
                    glossInternalId,
                    glossing,
                    morphoNodeUris
            );

            writeTranslationNodesForGlossing(
                    glossUri,
                    glossInternalId,
                    glossing,
                    interf
            );

            glossIndex++;
        }
    }


    private void writeTranslationNodesForGlossing(
            String glossUri,
            String glossInternalId,
            MorphoSyntGlossing glossing,
            AllmahGUI interf
    ) {
        ArrayList<TranslationNode> nodes = getTranslationNodesForGlossing(
                interf,
                glossing,
                glossInternalId
        );

        if (nodes == null || nodes.isEmpty()) {
            return;
        }

        int nodeIndex = 1;
        for (TranslationNode node : nodes) {
            if (node == null || node.getId() == null
                    || node.getId().trim().isEmpty()) {
                continue;
            }

            String nodeInternalId = node.getId();
            String nodeUri = RdfUriFactory.fromInternalId(nodeInternalId);
            String nodeKind = cleanTranslationNodeKind(node.getNodeKind());
            String label = cleanPlainLabel(node.getLabel());

            ttl.append(glossUri)
               .append(" allmah:hasTranslationNode ")
               .append(nodeUri)
               .append(" .\n");

            ttl.append(nodeUri).append("\n");
            ttl.append("    a allmah:")
               .append(nodeKind)
               .append(" ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(label))
               .append(" ;\n");
            ttl.append("    allmah:internalId ")
               .append(TurtleEscaper.literal(nodeInternalId))
               .append(" ;\n");
            ttl.append("    allmah:originalText ")
               .append(TurtleEscaper.literal(cleanPlainLabel(node.getOriginal())))
               .append(" ;\n");
            ttl.append("    allmah:translationText ")
               .append(TurtleEscaper.literal(cleanPlainLabel(node.getTranslation())))
               .append(" ;\n");
            ttl.append("    allmah:elementLabel ")
               .append(TurtleEscaper.literal(cleanPlainLabel(node.getElementLabel())))
               .append(" ;\n");
            ttl.append("    allmah:semanticMeaning ")
               .append(TurtleEscaper.literal(cleanPlainLabel(node.getSemanticMeaning())))
               .append(" ;\n");
            ttl.append("    allmah:syntacticFunction ")
               .append(TurtleEscaper.literal(cleanPlainLabel(node.getSyntacticFunction())))
               .append(" ;\n");
            ttl.append("    allmah:wordNetSynsetId ")
               .append(TurtleEscaper.literal(cleanPlainLabel(node.getWordNetSynsetId())))
               .append(" ;\n");
            ttl.append("    allmah:explanation ")
               .append(TurtleEscaper.literal(cleanPlainLabel(node.getExplanation())))
               .append(" ;\n");
            ttl.append("    allmah:isDictionaryEntry ")
               .append(node.isDictionaryEntry() ? "true" : "false")
               .append(" ;\n");
            ttl.append("    allmah:hasParentInterpretationLevel ")
               .append(glossUri)
               .append(" ;\n");
            ttl.append("    allmah:orderIndex ")
               .append(nodeIndex);

            ArrayList<String> partIds = node.getPartIds();
            if (partIds != null && !partIds.isEmpty()) {
                for (String partId : partIds) {
                    if (partId == null || partId.trim().isEmpty()) {
                        continue;
                    }
                    String partUri = RdfUriFactory.fromInternalId(partId);
                    if ("ConsolidatedTranslation".equals(nodeKind)) {
                        ttl.append(" ;\n")
                           .append("    allmah:composedOf ")
                           .append(partUri);
                    }
                    else {
                        ttl.append(" ;\n")
                           .append("    allmah:hasPart ")
                           .append(partUri);
                    }
                }
            }

            ttl.append(" .\n\n");
            nodeIndex++;
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<TranslationNode> getTranslationNodesForGlossing(
            AllmahGUI interf,
            MorphoSyntGlossing glossing,
            String glossInternalId
    ) {
        ArrayList<TranslationNode> result = new ArrayList<TranslationNode>();
        if (interf == null || glossing == null) {
            return result;
        }

        Object value = getFieldQuietly(interf, "translationNodes");
        if (!(value instanceof ArrayList)) {
            return result;
        }

        ArrayList<TranslationNode> allNodes = (ArrayList<TranslationNode>) value;
        String glossId = glossing.getId();

        for (TranslationNode node : allNodes) {
            if (node == null) {
                continue;
            }
            String parentId = node.getParentMgId();
            if (safeStringEquals(parentId, glossId)
                    || safeStringEquals(parentId, glossInternalId)) {
                result.add(node);
            }
        }
        return result;
    }

    private boolean safeStringEquals(String a, String b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    private String cleanTranslationNodeKind(String nodeKind) {
        if (nodeKind == null || nodeKind.trim().isEmpty()) {
            return "TranslationNode";
        }
        String kind = nodeKind.trim();
        if ("SimpleDictionaryEntry".equals(kind)
                || "ComposedDictionaryEntry".equals(kind)
                || "ConsolidatedTranslation".equals(kind)
                || "TranslationNode".equals(kind)) {
            return kind;
        }
        return "TranslationNode";
    }

    private void writeMorphosyntacticGlossingElements(
            String glossUri,
            String glossInternalId,
            MorphoSyntGlossing glossing,
            Map<MorphoTranscrNode, String> morphoNodeUris
    ) {
        if (glossing == null || glossing.getElements() == null) {
            return;
        }

        int elementIndex = 1;
        for (GlossingVariant variant : glossing.getElements()) {
            if (variant == null) {
                continue;
            }

            String elementInternalId = glossInternalId + ">MSGE_" + elementIndex;
            String elementUri = RdfUriFactory.fromInternalId(elementInternalId);
            String semantic = "";
            String wordnet = "";
            String syntax = "";
            String posCluster = "";
            String pos = "";

            SemanticAnnotation sem = variant.getSemantics();
            if (sem != null) {
                semantic = cleanPlainLabel(sem.getMeaning());
                wordnet = cleanPlainLabel(sem.getLinkWN());
            }

            SyntacticAnnotation syn = variant.getSyntax();
            if (syn != null) {
                posCluster = cleanPlainLabel(syn.getPosCluster());
                pos = cleanPlainLabel(syn.getPos());
                if (syn.getSyntFeature() != null) {
                    syntax = cleanPlainLabel(syn.getSyntFeature().getAbbreviation());
                }
            }

            String label;
            if (syntax != null && syntax.length() > 0) {
                label = syntax;
                if (semantic != null && semantic.length() > 0
                        && !" ".equals(semantic)) {
                    label = label + ":" + semantic;
                }
            }
            else {
                label = semantic;
            }

            ttl.append(glossUri)
               .append(" allmah:hasGlossingElement ")
               .append(elementUri)
               .append(" .\n");

            ttl.append(elementUri).append("\n");
            ttl.append("    a allmah:GlossingElement ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(label))
               .append(" ;\n");
            ttl.append("    allmah:internalId ")
               .append(TurtleEscaper.literal(elementInternalId))
               .append(" ;\n");
            ttl.append("    allmah:literalValue ")
               .append(TurtleEscaper.literal(label))
               .append(" ;\n");
            ttl.append("    allmah:semanticGloss ")
               .append(TurtleEscaper.literal(semantic))
               .append(" ;\n");
            ttl.append("    allmah:syntacticGloss ")
               .append(TurtleEscaper.literal(syntax))
               .append(" ;\n");
            ttl.append("    allmah:posCluster ")
               .append(TurtleEscaper.literal(posCluster))
               .append(" ;\n");
            ttl.append("    allmah:posTag ")
               .append(TurtleEscaper.literal(pos))
               .append(" ;\n");
            ttl.append("    allmah:orderIndex ")
               .append(elementIndex);

            String parentUri = null;
            if (morphoNodeUris != null && variant.getParent() != null) {
                parentUri = morphoNodeUris.get(variant.getParent());
            }
            if (parentUri != null) {
                ttl.append(" ;\n");
                ttl.append("    allmah:hasParentElement ")
                   .append(parentUri);
            }

            if (wordnet != null && wordnet.length() > 0) {
                ttl.append(" ;\n");
                ttl.append("    allmah:wordNetSynsetId ")
                   .append(TurtleEscaper.literal(wordnet));
            }

            ttl.append(" .\n\n");
            elementIndex++;
        }
    }

    private void writeGraphemicElementNode(
            String elementUri,
            String elementInternalId,
            GT2Element element,
            int elementIndex
    ) {
        ttl.append(elementUri).append("\n");
        ttl.append("    a allmah:GraphemicElement ;\n");
        ttl.append("    rdfs:label ")
           .append(TurtleEscaper.literal(cleanPlainLabel(element.getLabel())))
           .append(" ;\n");
        ttl.append("    allmah:internalId ")
           .append(TurtleEscaper.literal(elementInternalId))
           .append(" ;\n");
        ttl.append("    allmah:literalValue ")
           .append(TurtleEscaper.literal(cleanPlainLabel(element.getLabel())))
           .append(" ;\n");
        ttl.append("    allmah:orderIndex ")
           .append(elementIndex);

        String parentUri = null;
        if (element.getParentGT1() != null) {
            parentUri = gt1ElementUris.get(element.getParentGT1());
        }
        if (parentUri != null) {
            ttl.append(" ;\n");
            ttl.append("    allmah:hasParentElement ")
               .append(parentUri);
        }

        ttl.append(" .\n\n");
    }

    private void writeGraphemicComponent(
            String componentUri,
            ComponentElement component,
            ArrayList<GraphGT2Node> members,
            int orderIndex
    ) {
        if (componentUri == null || writtenComponentUris.contains(componentUri)) {
            return;
        }
        writtenComponentUris.add(componentUri);

        String componentLabel = cleanComponentLabel(component);
        String sourceLabel = buildGT2SourceLabel(members);

        ttl.append(componentUri).append("\n");
        ttl.append("    a allmah:GraphemicComponent ;\n");
        ttl.append("    rdfs:label ")
           .append(TurtleEscaper.literal(componentLabel))
           .append(" ;\n");
        ttl.append("    allmah:componentLabel ")
           .append(TurtleEscaper.literal(componentLabel))
           .append(" ;\n");
        ttl.append("    allmah:componentSourceLabel ")
           .append(TurtleEscaper.literal(sourceLabel))
           .append(" ;\n");
        ttl.append("    allmah:componentColor ")
           .append(TurtleEscaper.literal(encodeColor(component.getcolor())))
           .append(" ;\n");
        ttl.append("    allmah:orderIndex ")
           .append(orderIndex);

        if (members != null) {
            int memberIndex = 1;
            for (GraphGT2Node member : members) {
                if (member == null || member.getGT2Element() == null) {
                    continue;
                }
                String memberUri = RdfUriFactory.fromInternalId(
                        componentUri + "-member-" + memberIndex
                );
                /* Do not create a second standalone node here.  The member
                 * node was already written above.  We locate it through the
                 * deterministic GT2 element URI if available by label/position
                 * is not reliable.  Therefore the component primarily exposes
                 * its own label and source; GT2 graph relations point to the
                 * component. */
                memberIndex++;
            }
        }

        String graphematicComponentUri = graphematicComponentUris.get(component);
        if (graphematicComponentUri != null) {
            ttl.append(" ;\n");
            ttl.append("    allmah:hasSource ")
               .append(graphematicComponentUri);
        }

        ttl.append(" .\n\n");
    }

    private void writeGT2GraphRelations(
            String gt2Uri,
            String gt2InternalId,
            GraphTranslit2 gt2,
            Map<GraphGT2Node, String> gt2NodeUris
    ) {
        if (gt2 == null
                || gt2.getGraphGT2() == null
                || gt2NodeUris == null
                || gt2NodeUris.isEmpty()) {
            return;
        }

        int relationIndex = 1;
        Set<String> writtenRelationKeys = new HashSet<String>();

        for (OperatorLink edge : gt2.getGraphGT2().getEdges()) {
            if (edge == null) {
                continue;
            }

            String operator =
                    operatorTypeForLevel(edge.getLabel(), "Graphemic");

            if (operator == null) {
                continue;
            }

            GraphGT2Node source = gt2.getGraphGT2().getSource(edge);
            GraphGT2Node target = gt2.getGraphGT2().getDest(edge);

            String sourceUri = gt2NodeUris.get(source);
            String targetUri = gt2NodeUris.get(target);

            if (sourceUri == null || targetUri == null) {
                continue;
            }

            if (sourceUri.equals(targetUri)) {
                continue;
            }

            String key = sourceUri + "|" + edge.getLabel() + "|" + targetUri;
            if (!writtenRelationKeys.add(key)) {
                continue;
            }

            String relationUri = gt2Uri + "-gt2rel-" + relationIndex;

            ttl.append(gt2Uri)
               .append(" allmah:hasGraphemicRelation ")
               .append(relationUri)
               .append(" .\n");

            ttl.append(relationUri).append("\n");
            ttl.append("    a allmah:GraphemicRelation ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(edge.getLabel())))
               .append(" ;\n");
            ttl.append("    allmah:hasSource ")
               .append(sourceUri)
               .append(" ;\n");
            ttl.append("    allmah:hasTarget ")
               .append(targetUri)
               .append(" ;\n");
            ttl.append("    allmah:hasOperator ")
               .append(operator)
               .append(" ;\n");
            ttl.append("    allmah:operatorSymbol ")
               .append(TurtleEscaper.literal(cleanPlainLabel(edge.getLabel())))
               .append(" ;\n");
            ttl.append("    allmah:orderIndex ")
               .append(relationIndex)
               .append(" .\n\n");

            writeOperatorType(operator, edge.getLabel(), "Graphemic");

            relationIndex++;
        }
    }

    private String getLevelComponentUri(
            Map<ComponentElement, String> map,
            ComponentElement component,
            String internalId
    ) {
        String uri = map.get(component);
        if (uri != null) {
            return uri;
        }
        uri = RdfUriFactory.fromInternalId(internalId);
        map.put(component, uri);
        return uri;
    }

    private ComponentElement componentOfGT1Element(GT1Element element) {
        if (element == null || element.getParent() == null) {
            return null;
        }
        return element.getParent().getComponentElement();
    }

    private ComponentElement componentOfGT2Element(GT2Element element) {
        if (element == null) {
            return null;
        }
        if (element.getParenComponent() != null) {
            return element.getParenComponent();
        }
        if (element.getParentGT1() != null
                && element.getParentGT1().getParent() != null) {
            return element.getParentGT1().getParent().getComponentElement();
        }
        return null;
    }


    private String buildNT2TransliterationLabel(NumTranslit2 nt2) {
        String base = cleanPlainLabel(nt2.calculateLabel());
        String marker = buildNT2ComponentMarker(nt2);
        if (marker.length() == 0) {
            return base;
        }
        return base + " (with Component " + marker + ")";
    }

    private String buildNT2ComponentMarker(NumTranslit2 nt2) {
        if (nt2 == null || nt2.getElements() == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Set<ComponentElement> seen =
                Collections.newSetFromMap(
                        new IdentityHashMap<ComponentElement, Boolean>()
                );

        for (NT2Element element : nt2.getElements()) {
            if (element == null) {
                continue;
            }
            ComponentElement component = element.getComponentElement();
            if (component == null || !seen.add(component)) {
                continue;
            }
            String sourceLabel = buildNT2SourceLabelForComponent(nt2, component);
            if (sourceLabel.length() == 0) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("[").append(sourceLabel).append("]");
        }
        return sb.toString();
    }

    private String buildNT2SourceLabelForComponent(
            NumTranslit2 nt2,
            ComponentElement component
    ) {
        StringBuilder sb = new StringBuilder();
        if (nt2 == null || nt2.getElements() == null || component == null) {
            return "";
        }
        for (NT2Element element : nt2.getElements()) {
            if (element != null && element.getComponentElement() == component) {
                if (sb.length() > 0) {
                    sb.append(":");
                }
                sb.append(cleanPlainLabel(element.getSimpleLabel()));
            }
        }
        return sb.toString();
    }

    private String buildGT1TransliterationLabel(GraphTranslit1 gt1) {
        String base = cleanPlainLabel(gt1.calculateLabel());
        String marker = buildGT1ComponentMarker(gt1);
        if (marker.length() == 0) {
            return base;
        }
        return base + " (with Component " + marker + ")";
    }

    private String buildGT1ComponentMarker(GraphTranslit1 gt1) {
        if (gt1 == null || gt1.getElements() == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Set<ComponentElement> seen =
                Collections.newSetFromMap(
                        new IdentityHashMap<ComponentElement, Boolean>()
                );

        for (GT1Element element : gt1.getElements()) {
            if (element == null) {
                continue;
            }
            ComponentElement component = componentOfGT1Element(element);
            if (component == null || !seen.add(component)) {
                continue;
            }
            String sourceLabel = buildGT1SourceLabelForComponent(gt1, component);
            if (sourceLabel.length() == 0) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("[").append(sourceLabel).append("]");
        }
        return sb.toString();
    }

    private String buildGT1SourceLabelForComponent(
            GraphTranslit1 gt1,
            ComponentElement component
    ) {
        StringBuilder sb = new StringBuilder();
        if (gt1 == null || gt1.getElements() == null || component == null) {
            return "";
        }
        for (GT1Element element : gt1.getElements()) {
            if (element != null && componentOfGT1Element(element) == component) {
                if (sb.length() > 0) {
                    sb.append(":");
                }
                sb.append(cleanPlainLabel(element.getSimpleLabel()));
            }
        }
        return sb.toString();
    }

    private String buildNT2SourceLabel(ArrayList<NT2Element> members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (NT2Element member : members) {
                if (sb.length() > 0) {
                    sb.append(":");
                }
                sb.append(cleanPlainLabel(member.getSimpleLabel()));
            }
        }
        return sb.toString();
    }

    private void rememberNT2ElementUriByLabel(
            NT2Element element,
            String elementUri
    ) {
        if (element == null || elementUri == null) {
            return;
        }

        String label = cleanPlainLabel(element.getSimpleLabel());
        if (label == null || label.trim().isEmpty()) {
            return;
        }

        List<String> uris = nt2ElementUrisByLabel.get(label);
        if (uris == null) {
            uris = new ArrayList<String>();
            nt2ElementUrisByLabel.put(label, uris);
        }

        if (!uris.contains(elementUri)) {
            uris.add(elementUri);
        }
    }

    private String buildGT1SourceLabel(ArrayList<GT1Element> members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (GT1Element member : members) {
                if (sb.length() > 0) {
                    sb.append(":");
                }
                sb.append(cleanPlainLabel(member.getSimpleLabel()));
            }
        }
        return sb.toString();
    }

    private String buildGT2SourceLabel(ArrayList<GraphGT2Node> members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (GraphGT2Node member : members) {
                if (member == null || member.getGT2Element() == null) {
                    continue;
                }
                if (sb.length() > 0) {
                    sb.append(":");
                }
                sb.append(cleanPlainLabel(member.getGT2Element().getLabel()));
            }
        }
        return sb.toString();
    }

    private String cleanComponentLabel(ComponentElement component) {
        if (component == null) {
            return "Component";
        }
        String label = component.getLabel1();
        if (label == null || label.trim().isEmpty()) {
            label = component.getLabel();
        }
        label = cleanPlainLabel(label);
        if (label.startsWith("CGlyph~")) {
            label = label.substring("CGlyph~".length());
        }
        if (label.startsWith("CGlyph:")) {
            label = label.substring("CGlyph:".length());
        }
        if (label.trim().isEmpty()) {
            return "Component";
        }
        return label.trim();
    }

    private String cleanPlainLabel(String label) {
        if (label == null) {
            return "";
        }
        String s = label;
        s = s.replaceAll("(?is)<[^>]+>", "");
        s = s.replace("&nbsp;", " ");
        s = s.replace("&lt;", "<");
        s = s.replace("&gt;", ">");
        s = s.replace("&amp;", "&");
        s = s.replace("&quot;", "\"");
        s = s.replace("&#39;", "'");
        return s.trim();
    }

    private String cleanGlyphLabel(String label) {
        String s = cleanPlainLabel(label);
        if (!"??".equals(s)) {
            while (s.startsWith("?") && s.length() > 1) {
                s = s.substring(1);
            }
            while (s.endsWith("?") && s.length() > 1) {
                s = s.substring(0, s.length() - 1);
            }
        }
        while (s.startsWith("*") && s.length() > 1) {
            s = s.substring(1);
        }
        while (s.endsWith("*") && s.length() > 1) {
            s = s.substring(0, s.length() - 1);
        }
        if (s.matches("^[0-9]+[A-Za-z]{2}$")) {
            s = s.substring(0, s.length() - 2);
        }
        return s;
    }

    private String blockDisplayLabel(String blockId, String blockExpression) {
        String id = cleanPlainLabel(blockId);
        String expr = cleanPlainLabel(blockExpression);
        if (id == null || id.isEmpty()) {
            return expr;
        }
        if (expr == null || expr.isEmpty()) {
            return id;
        }
        if (expr.startsWith(id + " ")) {
            return expr;
        }
        return id + " " + expr;
    }

    private String encodeColor(java.awt.Color color) {
        if (color == null) {
            return "#000000";
        }
        return String.format(
                "#%02x%02x%02x",
                color.getRed(),
                color.getGreen(),
                color.getBlue()
        );
    }

    private String operatorTypeForLevel(String symbol, String level) {
        String suffix = operatorSuffix(symbol);
        if (suffix == null) {
            return null;
        }
        return "allmah:" + level + suffix + "Operator";
    }

    private String operatorSuffix(String symbol) {
        if (symbol == null || symbol.trim().isEmpty()) return "Empty";
        if (":".equals(symbol)) return "Colon";
        if (".".equals(symbol)) return "Dot";
        if ("+".equals(symbol)) return "Plus";
        if ("°".equals(symbol)) return "Degree";
        if ("-".equals(symbol)) return "Hyphen";
        if ("=".equals(symbol)) return "Equals";
        if ("E".equals(symbol)) return "GenericE";
        if ("↑".equals(symbol)) return "UpArrow";
        if ("~".equals(symbol)) return "Tilde";
        if ("[ ]".equals(symbol)) return "SquareBracket";
        if ("{ }".equals(symbol)) return "CurlyBracket";
        if ("| |".equals(symbol)) return "Pipe";
        if ("<".equals(symbol)) return "LessThan";
        if (">".equals(symbol)) return "GreaterThan";
        if ("C".equals(symbol)) return "Consonant";
        if ("V".equals(symbol)) return "Vowel";
        if ("H".equals(symbol)) return "H";
        if ("Ø".equals(symbol)) return "Zero";
        if ("^".equals(symbol)) return "Caret";
        if ("┌".equals(symbol)) return "TopLeft";
        if ("┐".equals(symbol)) return "TopRight";
        if ("╝".equals(symbol)) return "BottomRight";
        if ("╚".equals(symbol)) return "BottomLeft";
        if ("╗".equals(symbol)) return "UpperRightCorner";
        if ("╔".equals(symbol)) return "UpperLeftCorner";
        return null;
    }

    private final Set<String> writtenOperatorTypes = new HashSet<String>();

    private void writeOperatorType(String operatorUri, String symbol, String level) {
        if (operatorUri == null || writtenOperatorTypes.contains(operatorUri)) {
            return;
        }
        writtenOperatorTypes.add(operatorUri);
        ttl.append(operatorUri).append("\n");
        ttl.append("    a allmah:OperatorType ;\n");
        ttl.append("    rdfs:label ")
           .append(TurtleEscaper.literal(cleanPlainLabel(symbol)))
           .append(" ;\n");
        ttl.append("    allmah:operatorSymbol ")
           .append(TurtleEscaper.literal(cleanPlainLabel(symbol)))
           .append(" ;\n");
        ttl.append("    allmah:operatorLevel allmah:")
           .append(level)
           .append("Level .\n\n");
    }

    private void writeConfidenceProperty(int confidence) {
        if (confidence > 0) {
            ttl.append("    allmah:confidence ")
               .append(confidence)
               .append(" ;\n");
        }
    }

    private int normalizeConfidence(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }
        try {
            int v = Integer.parseInt(value.trim());
            if (v < 1 || v > 5) return 0;
            return v;
        } catch (Exception ex) {
            return 0;
        }
    }

    private String getGlyphImageUri(
            GlyphElementNode glyph
    ) {

        try {
            java.lang.reflect.Method method =
                    glyph.getClass().getMethod("getImageUri");

            Object value =
                    method.invoke(glyph);

            if (value instanceof String) {
                return (String) value;
            }
        } catch (Exception ex) {
            // Older GlyphElementNode versions do not have getImageUri().
        }

        try {
            Class<?> c = glyph.getClass();

            while (c != null) {
                try {
                    Field f =
                            c.getDeclaredField("imageUri");

                    f.setAccessible(true);

                    Object value =
                            f.get(glyph);

                    if (value instanceof String) {
                        return (String) value;
                    }

                    return null;

                } catch (NoSuchFieldException ex) {
                    c = c.getSuperclass();
                }
            }
        } catch (Exception ex) {
            // no imageUri field
        }

        return null;
    }

    private String getReadingLocalLabel(
            String readingId
    ) {

        int pos =
                readingId.lastIndexOf(">");

        if (pos >= 0 && pos < readingId.length() - 1) {
            return readingId.substring(pos + 1);
        }

        return readingId;
    }



    @SuppressWarnings("unchecked")
    private void writeBlockAnnotations(AllmahGUI interf) {
        Object value = getFieldQuietly(interf, "blockAnn");
        if (!(value instanceof ArrayList<?>)) {
            return;
        }

        ArrayList<BlockAnnotation> annotations = (ArrayList<BlockAnnotation>) value;
        if (annotations == null || annotations.isEmpty()) {
            return;
        }

        Map<String, ArrayList<String>> blockIdsByAnnotation =
                collectBlockIdsByAnnotationFromBlocks(interf);

        int annotationIndex = 1;
        for (BlockAnnotation annotation : annotations) {
            if (annotation == null || annotation.getId() == null
                    || annotation.getId().trim().isEmpty()) {
                continue;
            }

            String annotationUri = RdfUriFactory.fromInternalId(annotation.getId());
            ArrayList<String> blockIds = mergedAnnotationBlockIds(
                    annotation.getAnnBorders(),
                    blockIdsByAnnotation.get(annotation.getId())
            );

            if (blockIds != null) {
                for (String blockId : blockIds) {
                    if (blockId == null || blockId.trim().isEmpty()) {
                        continue;
                    }
                    String blockUri = RdfUriFactory.fromInternalId(blockId);
                    ttl.append(blockUri)
                       .append(" allmah:hasBlockAnnotation ")
                       .append(annotationUri)
                       .append(" .\n");
                }
                ttl.append("\n");
            }

            ttl.append(annotationUri).append("\n");
            ttl.append("    a allmah:BlockAnnotation ;\n");
            ttl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(annotation.getTyp())
                       + " " + cleanPlainLabel(annotation.getSubTyp())))
               .append(" ;\n");
            ttl.append("    allmah:internalId ")
               .append(TurtleEscaper.literal(annotation.getId()))
               .append(" ;\n");
            ttl.append("    allmah:annotationType ")
               .append(TurtleEscaper.literal(cleanPlainLabel(annotation.getTyp())))
               .append(" ;\n");
            ttl.append("    allmah:annotationSubType ")
               .append(TurtleEscaper.literal(cleanPlainLabel(annotation.getSubTyp())))
               .append(" ;\n");
            ttl.append("    allmah:orderIndex ")
               .append(annotationIndex);

            if (blockIds != null) {
                for (String blockId : blockIds) {
                    if (blockId == null || blockId.trim().isEmpty()) {
                        continue;
                    }
                    ttl.append(" ;\n")
                       .append("    allmah:annotatesBlock ")
                       .append(RdfUriFactory.fromInternalId(blockId));
                }
            }

            String annotationUriNode = writeAnnotationUriNode(annotationUri, annotation);
            if (annotationUriNode != null) {
                ttl.append(" ;\n")
                   .append("    allmah:hasAnnotationURI ")
                   .append(annotationUriNode);
            }

            ttl.append(" .\n\n");
            if (deferredAnnotationUriTtl.length() > 0) {
                ttl.append(deferredAnnotationUriTtl.toString());
                deferredAnnotationUriTtl.setLength(0);
            }
            annotationIndex++;
        }
    }


    @SuppressWarnings("unchecked")
    private Map<String, ArrayList<String>> collectBlockIdsByAnnotationFromBlocks(AllmahGUI interf) {
        Map<String, ArrayList<String>> result =
                new HashMap<String, ArrayList<String>>();

        Object value = getFieldQuietly(interf, "hb");
        if (!(value instanceof ArrayList<?>)) {
            return result;
        }

        ArrayList<HieroglyphenBlock> blocks =
                (ArrayList<HieroglyphenBlock>) value;

        for (HieroglyphenBlock block : blocks) {
            if (block == null || block.getBlockID() == null
                    || block.getAnnotations() == null) {
                continue;
            }

            for (String annotationId : block.getAnnotations()) {
                if (annotationId == null || annotationId.trim().isEmpty()) {
                    continue;
                }

                ArrayList<String> ids = result.get(annotationId);
                if (ids == null) {
                    ids = new ArrayList<String>();
                    result.put(annotationId, ids);
                }

                if (!ids.contains(block.getBlockID())) {
                    ids.add(block.getBlockID());
                }
            }
        }

        return result;
    }

    private ArrayList<String> mergedAnnotationBlockIds(
            ArrayList<String> fromAnnotation,
            ArrayList<String> fromBlocks
    ) {
        ArrayList<String> result = new ArrayList<String>();

        if (fromAnnotation != null) {
            for (String id : fromAnnotation) {
                if (id != null && !id.trim().isEmpty()
                        && !result.contains(id)) {
                    result.add(id);
                }
            }
        }

        if (fromBlocks != null) {
            for (String id : fromBlocks) {
                if (id != null && !id.trim().isEmpty()
                        && !result.contains(id)) {
                    result.add(id);
                }
            }
        }

        return result;
    }

    private String writeAnnotationUriNode(String annotationUri, BlockAnnotation annotation) {
        if (annotation == null) {
            return null;
        }

        Object activity = annotation.getActivity();
        if (hasStringValue(activity, "getTitle")) {
            String uri = annotationUri + "-uri";
            deferredAnnotationUriTtl.append(uri).append("\n");
            deferredAnnotationUriTtl.append("    a allmah:ActivityURI ;\n");
            deferredAnnotationUriTtl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(activity, "getTitle"))))
               .append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:uriType ").append(TurtleEscaper.literal("Activity")).append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:activityDescription ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(activity, "getTitle"))))
               .append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:placeOfActivity ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(activity, "getPlaceName"))))
               .append(" .\n\n");
            return uri;
        }

        Object dedication = annotation.getDedication();
        if (hasStringValue(dedication, "getTitle")) {
            String uri = annotationUri + "-uri";
            deferredAnnotationUriTtl.append(uri).append("\n");
            deferredAnnotationUriTtl.append("    a allmah:DedicationURI ;\n");
            deferredAnnotationUriTtl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(dedication, "getTitle"))))
               .append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:uriType ").append(TurtleEscaper.literal("Dedication")).append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:dedicationTitle ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(dedication, "getTitle"))))
               .append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:dedicationPlace ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(dedication, "getPlaceName"))))
               .append(" .\n\n");
            return uri;
        }

        Object place = annotation.getPlace();
        if (hasStringValue(place, "getPlaceName")) {
            String uri = annotationUri + "-uri";
            deferredAnnotationUriTtl.append(uri).append("\n");
            deferredAnnotationUriTtl.append("    a allmah:PlaceURI ;\n");
            deferredAnnotationUriTtl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(place, "getPlaceName"))))
               .append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:uriType ").append(TurtleEscaper.literal("Place")).append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:placeName ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(place, "getPlaceName"))))
               .append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:locatedIn ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(place, "getLocatedPlaceNames"))))
               .append(" .\n\n");
            return uri;
        }

        Object actor = annotation.getEpigraphicActor();
        if (hasStringValue(actor, "getActorName")) {
            String uri = annotationUri + "-uri";
            deferredAnnotationUriTtl.append(uri).append("\n");
            deferredAnnotationUriTtl.append("    a allmah:EpigraphicActorURI ;\n");
            deferredAnnotationUriTtl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(actor, "getActorName"))))
               .append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:uriType ").append(TurtleEscaper.literal("EpigraphicActor")).append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:personName ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(actor, "getActorName"))))
               .append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:gender ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(actor, "getDetails"))))
               .append(" .\n\n");
            return uri;
        }

        Object group = annotation.getEpigraphicGroup();
        if (hasStringValue(group, "getGroupName")) {
            String uri = annotationUri + "-uri";
            deferredAnnotationUriTtl.append(uri).append("\n");
            deferredAnnotationUriTtl.append("    a allmah:EpigraphicGroupURI ;\n");
            deferredAnnotationUriTtl.append("    rdfs:label ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(group, "getGroupName"))))
               .append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:uriType ").append(TurtleEscaper.literal("EpigraphicGroup")).append(" ;\n");
            deferredAnnotationUriTtl.append("    allmah:groupName ")
               .append(TurtleEscaper.literal(cleanPlainLabel(callStringGetter(group, "getGroupName"))))
               .append(" .\n\n");
            return uri;
        }

        return null;
    }

    private boolean hasStringValue(Object target, String getterName) {
        String value = callStringGetter(target, getterName);
        return value != null && !value.trim().isEmpty();
    }

    private String callStringGetter(Object target, String getterName) {
        if (target == null || getterName == null) {
            return "";
        }
        try {
            Object value = target.getClass().getMethod(getterName).invoke(target);
            return value == null ? "" : value.toString();
        }
        catch (Exception ex) {
            return "";
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<DocumentReading> getDocReadings(
            AllmahGUI gui
    ) {

        Object value =
                getFieldQuietly(gui, "docr");

        if (value instanceof ArrayList<?>) {
            return (ArrayList<DocumentReading>) value;
        }

        return null;
    }

    private HieroglyphenBlock getBlockById(
            AllmahGUI gui,
            String blockId
    ) {

        try {
            ArrayList<HieroglyphenBlock> list =
                    getArrayListField(gui, "hb");

            Map<String, Integer> map =
                    getMapField(gui, "mhb");

            if (list == null || map == null) {
                return null;
            }

            Integer index =
                    map.get(blockId);

            if (index == null) {
                return null;
            }

            return list.get(index.intValue());

        } catch (Exception ex) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T getById(
            AllmahGUI gui,
            String listFieldName,
            String mapFieldName,
            String id
    ) {

        try {
            ArrayList<T> list =
                    (ArrayList<T>) getField(gui, listFieldName);

            Map<String, Integer> map =
                    (Map<String, Integer>) getField(gui, mapFieldName);

            if (list == null || map == null) {
                return null;
            }

            Integer index =
                    map.get(id);

            if (index == null) {
                return null;
            }

            return list.get(index.intValue());

        } catch (Exception ex) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<HieroglyphenBlock> getArrayListField(
            AllmahGUI gui,
            String fieldName
    ) {

        Object value =
                getFieldQuietly(gui, fieldName);

        if (value instanceof ArrayList<?>) {
            return (ArrayList<HieroglyphenBlock>) value;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Integer> getMapField(
            AllmahGUI gui,
            String fieldName
    ) {

        Object value =
                getFieldQuietly(gui, fieldName);

        if (value instanceof Map<?, ?>) {
            return (Map<String, Integer>) value;
        }

        return null;
    }

    private Object getFieldQuietly(
            Object target,
            String fieldName
    ) {

        try {
            return getField(target, fieldName);
        } catch (Exception ex) {
            return null;
        }
    }

    private Object getField(
            Object target,
            String fieldName
    ) throws Exception {

        Class<?> c =
                target.getClass();

        while (c != null) {

            try {
                Field f =
                        c.getDeclaredField(fieldName);

                f.setAccessible(true);

                return f.get(target);

            } catch (NoSuchFieldException ex) {
                c = c.getSuperclass();
            }
        }

        return null;
    }
}