package allmahVer4.rdf;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class AllmahRdfImporter {

    private static final String ALLMAH =
            "https://classicmayan.org/allmah/ontology#";

    private final Map<String, ImportedComponent> componentCache =
            new HashMap<>();

    public ImportedDocument importFromTurtleFile(File ttlFile) {

        Model model =
                RDFDataMgr.loadModel(ttlFile.getAbsolutePath());

        componentCache.clear();

        Resource documentClass =
                model.createResource(ALLMAH + "Document");

        Property hasDocumentReading =
                model.createProperty(ALLMAH + "hasDocumentReading");

        ResIterator docs =
                model.listResourcesWithProperty(
                        RDF.type,
                        documentClass
                );

        if (!docs.hasNext()) {
            throw new RuntimeException("No allmah:Document found.");
        }

        Resource doc =
                docs.nextResource();

        ImportedDocument imported =
                new ImportedDocument();

        imported.uri = doc.getURI();
        imported.label = cleanImportedLabel(getLiteral(model, doc, RDFS.label));
        imported.title = getLiteral(
                model,
                doc,
                model.createProperty(ALLMAH + "title")
        );

        if (imported.title == null || imported.title.trim().isEmpty()) {
            imported.title = imported.label;
        }

        if (imported.title == null || imported.title.trim().isEmpty()) {
            imported.title = doc.getURI();
        }

        StmtIterator readingStmts =
                doc.listProperties(hasDocumentReading);

        while (readingStmts.hasNext()) {

            RDFNode node =
                    readingStmts.nextStatement().getObject();

            if (!node.isResource()) {
                continue;
            }

            ImportedDocumentReading dr =
                    readDocumentReading(
                            model,
                            node.asResource()
                    );

            imported.readings.add(dr);
        }

        imported.readings.sort(
                Comparator.comparingInt(r -> r.orderIndex)
        );

        if (!imported.readings.isEmpty()) {
            imported.reading =
                    imported.readings.get(0);
        }
        return imported;
    }

    private ImportedDocumentReading readDocumentReading(
            Model model,
            Resource reading
    ) {

        ImportedDocumentReading result =
                new ImportedDocumentReading();

        result.uri = reading.getURI();
        result.label = cleanImportedLabel(getLiteral(model, reading, RDFS.label));
        result.internalId = getLiteral(
                model,
                reading,
                model.createProperty(ALLMAH + "internalId")
        );

        result.orderIndex =
                getOrderIndex(model, reading);

        result.confidence = getIntLiteral(
                model,
                reading,
                model.createProperty(ALLMAH + "confidence"),
                0
        );

        Property usesBlock =
                model.createProperty(ALLMAH + "usesBlock");

        StmtIterator blocks =
                reading.listProperties(usesBlock);

        while (blocks.hasNext()) {

            RDFNode node =
                    blocks.nextStatement().getObject();

            if (node.isResource()) {
                result.blocks.add(
                        readBlock(model, node.asResource())
                );
            }
        }

        result.blocks.sort(
                Comparator.comparingInt(b -> b.orderIndex)
        );

        return result;
    }

    private ImportedBlock readBlock(
            Model model,
            Resource block
    ) {

        ImportedBlock result =
                new ImportedBlock();

        result.uri = block.getURI();
        result.label = cleanImportedLabel(getLiteral(model, block, RDFS.label));

        result.internalId = getLiteral(
                model,
                block,
                model.createProperty(ALLMAH + "internalId")
        );

        result.teiXmlId = getLiteral(
                model,
                block,
                model.createProperty(ALLMAH + "teiXmlId")
        );

        result.blockLabel = getLiteral(
                model,
                block,
                model.createProperty(ALLMAH + "blockLabel")
        );

        result.blockExpression = getLiteral(
                model,
                block,
                model.createProperty(ALLMAH + "blockExpression")
        );

        /*
         * AllmahGUI passes importedBlock.label to
         * HieroglyphenBlock.createGraphStructure().  That method expects the
         * pure block expression (for example "229bl.[617st:126ex]"), not
         * the display label (for example "A1 229bl.[617st:126ex]").
         * Keep the display/coordinate information in blockLabel/teiXmlId, but
         * use blockExpression as the parser label.
         */
        if (result.blockExpression != null
                && !result.blockExpression.trim().isEmpty()) {
            result.label = cleanImportedLabel(result.blockExpression);
        }

        result.orderIndex =
                getOrderIndex(model, block);

        readGlyphs(model, block, result);
        readBlockAnnotations(model, block, result);
        readNT1(model, block, result);

        result.nt1.sort(
                Comparator.comparingInt(n -> n.orderIndex)
        );

        return result;
    }


    private void readBlockAnnotations(
            Model model,
            Resource block,
            ImportedBlock result
    ) {
        Property hasBlockAnnotation =
                model.createProperty(ALLMAH + "hasBlockAnnotation");

        StmtIterator annotations = block.listProperties(hasBlockAnnotation);
        while (annotations.hasNext()) {
            RDFNode node = annotations.nextStatement().getObject();
            if (node != null && node.isResource()) {
                result.annotations.add(
                        readBlockAnnotation(model, node.asResource())
                );
            }
        }
    }

    private ImportedBlockAnnotation readBlockAnnotation(
            Model model,
            Resource annotation
    ) {
        ImportedBlockAnnotation result = new ImportedBlockAnnotation();

        result.uri = annotation.getURI();
        result.label = cleanImportedLabel(getLiteral(model, annotation, RDFS.label));
        result.internalId = getLiteral(
                model,
                annotation,
                model.createProperty(ALLMAH + "internalId")
        );
        result.annotationType = getLiteral(
                model,
                annotation,
                model.createProperty(ALLMAH + "annotationType")
        );
        result.annotationSubType = getLiteral(
                model,
                annotation,
                model.createProperty(ALLMAH + "annotationSubType")
        );
        result.comment = getLiteral(
                model,
                annotation,
                model.createProperty(ALLMAH + "comment")
        );
        result.orderIndex = getOrderIndex(model, annotation);

        Property annotatesBlock = model.createProperty(ALLMAH + "annotatesBlock");
        StmtIterator blocks = annotation.listProperties(annotatesBlock);
        while (blocks.hasNext()) {
            RDFNode node = blocks.nextStatement().getObject();
            if (node != null && node.isResource()) {
                result.blockUris.add(node.asResource().getURI());
            }
        }

        Statement uriStmt = annotation.getProperty(
                model.createProperty(ALLMAH + "hasAnnotationURI")
        );
        if (uriStmt != null && uriStmt.getObject().isResource()) {
            result.annotationURI = readAnnotationURI(
                    model,
                    uriStmt.getObject().asResource()
            );
        }

        return result;
    }

    private ImportedAnnotationURI readAnnotationURI(
            Model model,
            Resource uriResource
    ) {
        ImportedAnnotationURI result = new ImportedAnnotationURI();

        result.uri = uriResource.getURI();
        result.label = cleanImportedLabel(getLiteral(model, uriResource, RDFS.label));
        result.uriType = getLiteral(
                model,
                uriResource,
                model.createProperty(ALLMAH + "uriType")
        );

        if (result.uriType == null || result.uriType.trim().isEmpty()) {
            Statement typeStmt = uriResource.getProperty(RDF.type);
            if (typeStmt != null && typeStmt.getObject().isResource()) {
                String localName = typeStmt.getObject().asResource().getLocalName();
                if (localName != null && localName.endsWith("URI")) {
                    result.uriType = localName.substring(0, localName.length() - 3);
                }
            }
        }

        result.activityDescription = getLiteral(model, uriResource,
                model.createProperty(ALLMAH + "activityDescription"));
        result.placeOfActivity = getLiteral(model, uriResource,
                model.createProperty(ALLMAH + "placeOfActivity"));
        result.dedicationTitle = getLiteral(model, uriResource,
                model.createProperty(ALLMAH + "dedicationTitle"));
        result.dedicationPlace = getLiteral(model, uriResource,
                model.createProperty(ALLMAH + "dedicationPlace"));
        result.placeName = getLiteral(model, uriResource,
                model.createProperty(ALLMAH + "placeName"));
        result.locatedIn = getLiteral(model, uriResource,
                model.createProperty(ALLMAH + "locatedIn"));
        result.personName = getLiteral(model, uriResource,
                model.createProperty(ALLMAH + "personName"));
        result.gender = getLiteral(model, uriResource,
                model.createProperty(ALLMAH + "gender"));
        result.groupName = getLiteral(model, uriResource,
                model.createProperty(ALLMAH + "groupName"));

        return result;
    }

    private void readGlyphs(
            Model model,
            Resource block,
            ImportedBlock result
    ) {

        Property hasGlyphElement =
                model.createProperty(ALLMAH + "hasGlyphElement");

        Property hasReadingAssignment =
                model.createProperty(ALLMAH + "hasReadingAssignment");

        Property assignedReading =
                model.createProperty(ALLMAH + "assignedReading");

        StmtIterator glyphs =
                block.listProperties(hasGlyphElement);

        while (glyphs.hasNext()) {

            RDFNode node =
                    glyphs.nextStatement().getObject();

            if (!node.isResource()) {
                continue;
            }

            Resource glyph =
                    node.asResource();

            ImportedGlyph g =
                    new ImportedGlyph();

            g.uri = glyph.getURI();
            g.label = cleanImportedLabel(getLiteral(model, glyph, RDFS.label));
            g.orderIndex = getOrderIndex(model, glyph);

            g.catalogNumber = getLiteral(
                    model,
                    glyph,
                    model.createProperty(ALLMAH + "catalogNumber")
            );

            g.imageUri = getLiteral(
                    model,
                    glyph,
                    model.createProperty(ALLMAH + "imageUri")
            );

            /*
             * GlyphElement carries only catalogue/import readings.
             * Document-specific selections are stored on NumericElement and
             * imported in readElement().
             */
            addImportedReadingsUnique(
                    g.readings,
                    readReadingAssignments(
                            model,
                            glyph,
                            "InitialReading",
                            "PossibleReading"
                    )
            );

            /* Compatibility for TTLs produced by the brief direct-catalogue
             * writer variant.  The ontology still uses ReadingAssignment; this
             * only makes those temporary files readable and does not change the
             * model. */
            addImportedReadingsUnique(
                    g.readings,
                    readDirectReadings(model, glyph, "hasInitialReading")
            );
            addImportedReadingsUnique(
                    g.readings,
                    readDirectReadings(model, glyph, "hasPossibleReading")
            );

            g.readings.sort(
                    Comparator.comparing(r -> r.label)
            );

            result.glyphs.add(g);
        }

        result.glyphs.sort(
                Comparator.comparingInt(g -> g.orderIndex)
        );
    }

    private void addImportedReadingsUnique(
            List<ImportedReading> target,
            List<ImportedReading> source
    ) {
        if (target == null || source == null) {
            return;
        }

        for (ImportedReading candidate : source) {
            if (candidate == null) {
                continue;
            }

            boolean exists = false;
            for (ImportedReading existing : target) {
                if (existing == null) {
                    continue;
                }
                if (safeEquals(existing.label, candidate.label)
                        && safeEquals(existing.readingType, candidate.readingType)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                target.add(candidate);
            }
        }
    }

    private boolean safeEquals(String a, String b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    private ArrayList<ImportedReading> readDirectReadings(
            Model model,
            Resource subject,
            String propertyLocalName
    ) {
        ArrayList<ImportedReading> result =
                new ArrayList<ImportedReading>();

        if (model == null || subject == null || propertyLocalName == null) {
            return result;
        }

        Property property =
                model.createProperty(ALLMAH + propertyLocalName);

        StmtIterator readings =
                subject.listProperties(property);

        while (readings.hasNext()) {
            RDFNode node = readings.nextStatement().getObject();
            if (node != null && node.isResource()) {
                result.add(readReading(model, node.asResource()));
            }
        }

        return result;
    }

    private ArrayList<ImportedReading> readReadingAssignments(
            Model model,
            Resource subject,
            String... acceptedRoleLocalNames
    ) {

        ArrayList<ImportedReading> result =
                new ArrayList<ImportedReading>();

        HashSet<String> accepted =
                new HashSet<String>();

        if (acceptedRoleLocalNames != null) {
            for (String role : acceptedRoleLocalNames) {
                if (role != null && !role.trim().isEmpty()) {
                    accepted.add(role.trim());
                }
            }
        }

        Property hasReadingAssignment =
                model.createProperty(ALLMAH + "hasReadingAssignment");

        Property assignedReading =
                model.createProperty(ALLMAH + "assignedReading");

        Property hasReadingRole =
                model.createProperty(ALLMAH + "hasReadingRole");

        StmtIterator assignments =
                subject.listProperties(hasReadingAssignment);

        while (assignments.hasNext()) {

            RDFNode aNode =
                    assignments.nextStatement().getObject();

            if (!aNode.isResource()) {
                continue;
            }

            Resource assignment =
                    aNode.asResource();

            Statement roleStmt =
                    assignment.getProperty(hasReadingRole);

            String roleLocalName = "";

            if (roleStmt != null && roleStmt.getObject().isResource()) {
                roleLocalName =
                        roleStmt.getObject()
                                .asResource()
                                .getLocalName();
            }

            if (!accepted.isEmpty()
                    && !accepted.contains(roleLocalName)) {
                continue;
            }

            Statement rStmt =
                    assignment.getProperty(assignedReading);

            if (rStmt == null
                    || !rStmt.getObject().isResource()) {
                continue;
            }

            Resource reading =
                    rStmt.getObject().asResource();

            ImportedReading ir =
                    readReading(model, reading);

            result.add(ir);
        }

        return result;
    }

    private ImportedReading readReading(
            Model model,
            Resource reading
    ) {

        ImportedReading ir =
                new ImportedReading();

        ir.uri = reading.getURI();
        ir.label = cleanImportedLabel(getLiteral(model, reading, RDFS.label));

        ir.literalValue = cleanImportedLabel(getLiteral(
                model,
                reading,
                model.createProperty(ALLMAH + "literalValue")
        ));

        ir.readingType = getLiteral(
                model,
                reading,
                model.createProperty(ALLMAH + "readingType")
        );

        int readingConfidence = getIntLiteral(
                model,
                reading,
                model.createProperty(ALLMAH + "confidence"),
                0
        );
        ir.confidence = readingConfidence > 0 ? "" + readingConfidence : "";

        ir.sourceUri = getLiteral(
                model,
                reading,
                model.createProperty(ALLMAH + "sourceUri")
        );

        if (ir.sourceUri == null || ir.sourceUri.isEmpty()) {
            ir.sourceUri = ir.uri;
        }

        return ir;
    }

    private void readNT1(
            Model model,
            Resource block,
            ImportedBlock result
    ) {

        Property hasNT1 =
                model.createProperty(
                        ALLMAH + "hasAlphanumericTransliteration"
                );

        StmtIterator nt1s =
                block.listProperties(hasNT1);

        while (nt1s.hasNext()) {

            RDFNode node =
                    nt1s.nextStatement().getObject();

            if (!node.isResource()) {
                continue;
            }

            Resource nt1 =
                    node.asResource();

            ImportedNT1 importedNT1 =
                    new ImportedNT1();

            importedNT1.uri = nt1.getURI();
            importedNT1.label = cleanImportedLabel(getLiteral(model, nt1, RDFS.label));
            importedNT1.internalId = getLiteral(
                    model,
                    nt1,
                    model.createProperty(ALLMAH + "internalId")
            );
            importedNT1.confidence =
                    getIntLiteral(
                            model,
                            nt1,
                            model.createProperty(ALLMAH + "confidence"),
                            0
                    );
            importedNT1.orderIndex =
                    getOrderIndex(model, nt1);

            readNT1Elements(model, nt1, importedNT1);
            readNT2(model, nt1, importedNT1);

            importedNT1.elements.sort(
                    Comparator.comparingInt(e -> e.orderIndex)
            );

            importedNT1.nt2.sort(
                    Comparator.comparingInt(n -> n.orderIndex)
            );

            result.nt1.add(importedNT1);
        }
    }

    private void readNT1Elements(
            Model model,
            Resource nt1,
            ImportedNT1 importedNT1
    ) {

        Property hasElement =
                model.createProperty(ALLMAH + "hasAlphanumericElement");

        StmtIterator elements =
                nt1.listProperties(hasElement);

        while (elements.hasNext()) {

            RDFNode node =
                    elements.nextStatement().getObject();

            if (node.isResource()) {
                importedNT1.elements.add(
                        readElement(model, node.asResource())
                );
            }
        }
    }

    private void readNT2(
            Model model,
            Resource nt1,
            ImportedNT1 importedNT1
    ) {

        Property hasNT2 =
                model.createProperty(ALLMAH + "hasNumericTransliteration");

        StmtIterator nt2s =
                nt1.listProperties(hasNT2);

        while (nt2s.hasNext()) {

            RDFNode node =
                    nt2s.nextStatement().getObject();

            if (!node.isResource()) {
                continue;
            }

            Resource nt2 =
                    node.asResource();

            ImportedNT2 importedNT2 =
                    new ImportedNT2();

            importedNT2.uri = nt2.getURI();
            importedNT2.label = cleanImportedLabel(getLiteral(model, nt2, RDFS.label));
            importedNT2.internalId = getLiteral(
                    model,
                    nt2,
                    model.createProperty(ALLMAH + "internalId")
            );
            importedNT2.confidence =
                    getIntLiteral(
                            model,
                            nt2,
                            model.createProperty(ALLMAH + "confidence"),
                            0
                    );
            importedNT2.orderIndex =
                    getOrderIndex(model, nt2);

            readNT2Elements(model, nt2, importedNT2);
            readGT1(model, nt2, importedNT2);

            importedNT2.elements.sort(
                    Comparator.comparingInt(e -> e.orderIndex)
            );

            importedNT2.gt1.sort(
                    Comparator.comparingInt(g -> g.orderIndex)
            );

            importedNT1.nt2.add(importedNT2);
        }
    }

    private void readNT2Elements(
            Model model,
            Resource nt2,
            ImportedNT2 importedNT2
    ) {

        Map<String, ImportedElement> byUri = new HashMap<String, ImportedElement>();

        Property hasElement =
                model.createProperty(ALLMAH + "hasNumericElement");

        StmtIterator elements =
                nt2.listProperties(hasElement);

        while (elements.hasNext()) {

            RDFNode node =
                    elements.nextStatement().getObject();

            if (node.isResource()) {
                ImportedElement element = readElement(model, node.asResource());
                if (!byUri.containsKey(element.uri)) {
                    byUri.put(element.uri, element);
                    importedNT2.elements.add(element);
                }
            }
        }

        Property hasNumericComponent =
                model.createProperty(ALLMAH + "hasNumericComponent");

        StmtIterator components =
                nt2.listProperties(hasNumericComponent);

        while (components.hasNext()) {

            RDFNode node =
                    components.nextStatement().getObject();

            if (!node.isResource()) {
                continue;
            }

            Resource componentResource = node.asResource();
            ImportedComponent component = readComponent(model, componentResource);

            ArrayList<ImportedElement> members =
                    readComponentMemberElements(
                            model,
                            componentResource,
                            component,
                            "hasNumericElement"
                    );

            for (ImportedElement member : members) {
                ImportedElement existing = byUri.get(member.uri);
                if (existing == null) {
                    byUri.put(member.uri, member);
                    importedNT2.elements.add(member);
                }
                else {
                    /* Preserve the existing element position, but attach the
                     * component metadata.  The writer stores component members
                     * both as regular elements and as component members; without
                     * this update the component information is lost on reload. */
                    existing.componentUri = component.uri;
                    existing.component = component;
                }
            }
        }
    }

    private void readGT1(
            Model model,
            Resource nt2,
            ImportedNT2 importedNT2
    ) {

        Property hasGT1 =
                model.createProperty(
                        ALLMAH + "hasGraphematicTransliteration"
                );

        StmtIterator gt1s =
                nt2.listProperties(hasGT1);

        while (gt1s.hasNext()) {

            RDFNode node =
                    gt1s.nextStatement().getObject();

            if (!node.isResource()) {
                continue;
            }

            Resource gt1 =
                    node.asResource();

            ImportedGT1 importedGT1 =
                    new ImportedGT1();

            importedGT1.uri = gt1.getURI();
            importedGT1.label = cleanImportedLabel(getLiteral(model, gt1, RDFS.label));
            importedGT1.internalId = getLiteral(
                    model,
                    gt1,
                    model.createProperty(ALLMAH + "internalId")
            );
            importedGT1.confidence =
                    getIntLiteral(
                            model,
                            gt1,
                            model.createProperty(ALLMAH + "confidence"),
                            0
                    );
            importedGT1.orderIndex =
                    getOrderIndex(model, gt1);

            readGT1Elements(model, gt1, importedGT1);
            readGT2(model, gt1, importedGT1);

            importedGT1.elements.sort(
                    Comparator.comparingInt(e -> e.orderIndex)
            );

            importedGT1.gt2.sort(
                    Comparator.comparingInt(g -> g.orderIndex)
            );

            importedNT2.gt1.add(importedGT1);
        }
    }

    private void readGT1Elements(
            Model model,
            Resource gt1,
            ImportedGT1 importedGT1
    ) {

        Map<String, ImportedElement> byUri = new HashMap<String, ImportedElement>();

        Property hasElement =
                model.createProperty(ALLMAH + "hasGraphematicElement");

        StmtIterator elements =
                gt1.listProperties(hasElement);

        while (elements.hasNext()) {

            RDFNode node =
                    elements.nextStatement().getObject();

            if (node.isResource()) {
                ImportedElement element = readElement(model, node.asResource());
                if (!byUri.containsKey(element.uri)) {
                    byUri.put(element.uri, element);
                    importedGT1.elements.add(element);
                }
            }
        }

        Property hasGraphematicComponent =
                model.createProperty(ALLMAH + "hasGraphematicComponent");

        StmtIterator components =
                gt1.listProperties(hasGraphematicComponent);

        while (components.hasNext()) {

            RDFNode node =
                    components.nextStatement().getObject();

            if (!node.isResource()) {
                continue;
            }

            Resource componentResource = node.asResource();
            ImportedComponent component = readComponent(model, componentResource);

            ArrayList<ImportedElement> members =
                    readComponentMemberElements(
                            model,
                            componentResource,
                            component,
                            "hasGraphematicElement"
                    );

            for (ImportedElement member : members) {
                ImportedElement existing = byUri.get(member.uri);
                if (existing == null) {
                    byUri.put(member.uri, member);
                    importedGT1.elements.add(member);
                }
                else {
                    existing.componentUri = component.uri;
                    existing.component = component;
                }
            }
        }
    }

    private void readGT2(
            Model model,
            Resource gt1,
            ImportedGT1 importedGT1
    ) {

        Property hasGT2 =
                model.createProperty(
                        ALLMAH + "hasGraphemicTransliteration"
                );

        StmtIterator gt2s =
                gt1.listProperties(hasGT2);

        while (gt2s.hasNext()) {

            RDFNode node =
                    gt2s.nextStatement().getObject();

            if (!node.isResource()) {
                continue;
            }

            Resource gt2 =
                    node.asResource();

            ImportedGT2 importedGT2 =
                    new ImportedGT2();

            importedGT2.uri = gt2.getURI();
            importedGT2.label = cleanImportedLabel(getLiteral(model, gt2, RDFS.label));
            importedGT2.internalId = getLiteral(
                    model,
                    gt2,
                    model.createProperty(ALLMAH + "internalId")
            );
            importedGT2.confidence =
                    getIntLiteral(
                            model,
                            gt2,
                            model.createProperty(ALLMAH + "confidence"),
                            0
                    );
            importedGT2.orderIndex =
                    getOrderIndex(model, gt2);

            importedGT2.frozen =
                    getBooleanLiteral(
                            model,
                            gt2,
                            model.createProperty(ALLMAH + "isFrozen")
                    );

            readGT2Elements(model, gt2, importedGT2);
            readGT2GraphRelations(model, gt2, importedGT2);
            readPhonemicTranscriptions(model, gt2, importedGT2);

            importedGT2.elements.sort(
                    Comparator.comparingInt(e -> e.orderIndex)
            );

            importedGT1.gt2.add(importedGT2);
        }
    }

    private void readGT2Elements(
            Model model,
            Resource gt2,
            ImportedGT2 importedGT2
    ) {

        HashSet<String> seen = new HashSet<String>();

        Property hasElement =
                model.createProperty(ALLMAH + "hasGraphemicElement");

        StmtIterator elements =
                gt2.listProperties(hasElement);

        while (elements.hasNext()) {

            RDFNode node =
                    elements.nextStatement().getObject();

            if (node.isResource()) {
                ImportedElement element = readElement(model, node.asResource());
                if (seen.add(element.uri)) {
                    importedGT2.elements.add(element);
                }
            }
        }

        /*
         * Graphemic components are graph nodes in their own right.
         * Relations point to the component URI, so we import a synthetic
         * ImportedElement with that URI rather than flattening it into a/ya.
         */
        Property hasGraphemicComponent =
                model.createProperty(ALLMAH + "hasGraphemicComponent");

        StmtIterator components =
                gt2.listProperties(hasGraphemicComponent);

        while (components.hasNext()) {

            RDFNode node =
                    components.nextStatement().getObject();

            if (!node.isResource()) {
                continue;
            }

            Resource componentResource = node.asResource();
            ImportedComponent component = readComponent(model, componentResource);

            ImportedElement componentElement = new ImportedElement();
            componentElement.uri = component.uri;
            componentElement.label = preferredComponentLabel(component);
            componentElement.literalValue = componentElement.label;
            componentElement.componentUri = component.uri;
            componentElement.component = component;
            componentElement.orderIndex = component.orderIndex;

            String parentUri = firstComponentMemberParentUri(
                    model,
                    componentResource,
                    "hasGraphemicElement"
            );
            componentElement.parentUri = parentUri;

            if (seen.add(componentElement.uri)) {
                importedGT2.elements.add(componentElement);
            }
        }
    }


    private void readPhonemicTranscriptions(
            Model model,
            Resource gt2,
            ImportedGT2 importedGT2
    ) {
        Property hasPhonemicTranscription =
                model.createProperty(ALLMAH + "hasPhonemicTranscription");

        StmtIterator phonemics =
                gt2.listProperties(hasPhonemicTranscription);

        while (phonemics.hasNext()) {
            RDFNode node = phonemics.nextStatement().getObject();
            if (!node.isResource()) {
                continue;
            }

            Resource phonemic = node.asResource();
            ImportedPhonemicTranscription importedPhonemic =
                    new ImportedPhonemicTranscription();

            importedPhonemic.uri = phonemic.getURI();
            importedPhonemic.label = cleanImportedLabel(getLiteral(model, phonemic, RDFS.label));
            importedPhonemic.internalId = getLiteral(
                    model,
                    phonemic,
                    model.createProperty(ALLMAH + "internalId")
            );
            importedPhonemic.confidence = getIntLiteral(
                    model,
                    phonemic,
                    model.createProperty(ALLMAH + "confidence"),
                    0
            );
            importedPhonemic.orderIndex = getOrderIndex(model, phonemic);
            importedPhonemic.frozen = getBooleanLiteral(
                    model,
                    phonemic,
                    model.createProperty(ALLMAH + "isFrozen")
            );

            readPhonemicElements(model, phonemic, importedPhonemic);
            readPhonemicRelations(model, phonemic, importedPhonemic);
            readMorphosyntacticTranscriptions(model, phonemic, importedPhonemic);

            importedPhonemic.elements.sort(
                    Comparator.comparingInt(e -> e.orderIndex)
            );
            importedPhonemic.relations.sort(
                    Comparator.comparingInt(r -> r.orderIndex)
            );

            importedGT2.phonemic.add(importedPhonemic);
        }

        importedGT2.phonemic.sort(
                Comparator.comparingInt(p -> p.orderIndex)
        );
    }

    private void readPhonemicElements(
            Model model,
            Resource phonemic,
            ImportedPhonemicTranscription importedPhonemic
    ) {
        Property hasPhonemicElement =
                model.createProperty(ALLMAH + "hasPhonemicElement");

        HashSet<String> seen = new HashSet<String>();
        StmtIterator elements = phonemic.listProperties(hasPhonemicElement);
        while (elements.hasNext()) {
            RDFNode node = elements.nextStatement().getObject();
            if (node.isResource()) {
                ImportedElement element = readElement(model, node.asResource());
                if (seen.add(element.uri)) {
                    importedPhonemic.elements.add(element);
                }
            }
        }
    }

    private void readPhonemicRelations(
            Model model,
            Resource phonemic,
            ImportedPhonemicTranscription importedPhonemic
    ) {
        Property hasRelation =
                model.createProperty(ALLMAH + "hasPhonemicRelation");

        StmtIterator relations = phonemic.listProperties(hasRelation);
        while (relations.hasNext()) {
            RDFNode node = relations.nextStatement().getObject();
            if (!node.isResource()) {
                continue;
            }

            Resource relationResource = node.asResource();
            ImportedRelation relation = new ImportedRelation();

            Statement sourceStmt = firstResourceProperty(
                    relationResource,
                    model.createProperty(ALLMAH + "hasSource"),
                    model.createProperty(ALLMAH + "sourcePhonemicElement")
            );
            if (sourceStmt != null) {
                relation.sourceUri = sourceStmt.getObject().asResource().getURI();
            }

            Statement targetStmt = firstResourceProperty(
                    relationResource,
                    model.createProperty(ALLMAH + "hasTarget"),
                    model.createProperty(ALLMAH + "targetPhonemicElement")
            );
            if (targetStmt != null) {
                relation.targetUri = targetStmt.getObject().asResource().getURI();
            }

            Statement operatorStmt = firstResourceProperty(
                    relationResource,
                    model.createProperty(ALLMAH + "hasOperator"),
                    model.createProperty(ALLMAH + "hasOperatorType")
            );
            if (operatorStmt != null) {
                relation.operatorUri = operatorStmt.getObject().asResource().getURI();
            }

            relation.orderIndex = getOrderIndex(model, relationResource);

            if (relation.sourceUri != null && relation.targetUri != null) {
                importedPhonemic.relations.add(relation);
            }
        }
    }


    private void readMorphosyntacticTranscriptions(
            Model model,
            Resource phonemic,
            ImportedPhonemicTranscription importedPhonemic
    ) {
        Property hasMorphosyntacticTranscription =
                model.createProperty(ALLMAH + "hasMorphosyntacticTranscription");

        StmtIterator transcriptions =
                phonemic.listProperties(hasMorphosyntacticTranscription);

        while (transcriptions.hasNext()) {
            RDFNode node = transcriptions.nextStatement().getObject();
            if (!node.isResource()) {
                continue;
            }

            Resource morpho = node.asResource();
            ImportedMorphosyntacticTranscription importedMorpho =
                    new ImportedMorphosyntacticTranscription();

            importedMorpho.uri = morpho.getURI();
            importedMorpho.label = cleanImportedLabel(getLiteral(model, morpho, RDFS.label));
            importedMorpho.internalId = getLiteral(
                    model,
                    morpho,
                    model.createProperty(ALLMAH + "internalId")
            );
            importedMorpho.literalValue = cleanImportedLabel(getLiteral(
                    model,
                    morpho,
                    model.createProperty(ALLMAH + "literalValue")
            ));
            importedMorpho.consolidatedLabel = cleanImportedLabel(getLiteral(
                    model,
                    morpho,
                    model.createProperty(ALLMAH + "consolidatedLabel")
            ));
            importedMorpho.finalConsolidatedLabel = cleanImportedLabel(getLiteral(
                    model,
                    morpho,
                    model.createProperty(ALLMAH + "finalConsolidatedLabel")
            ));
            importedMorpho.confidence = getIntLiteral(
                    model,
                    morpho,
                    model.createProperty(ALLMAH + "confidence"),
                    0
            );
            importedMorpho.orderIndex = getOrderIndex(model, morpho);

            readMorphosyntacticElements(model, morpho, importedMorpho);
            readMorphosyntacticGlossings(model, morpho, importedMorpho);
            importedMorpho.elements.sort(Comparator.comparingInt(e -> e.orderIndex));
            importedMorpho.glossings.sort(Comparator.comparingInt(g -> g.orderIndex));
            importedPhonemic.morphosyntactic.add(importedMorpho);
        }

        importedPhonemic.morphosyntactic.sort(
                Comparator.comparingInt(m -> m.orderIndex)
        );
    }

    private void readMorphosyntacticElements(
            Model model,
            Resource morpho,
            ImportedMorphosyntacticTranscription importedMorpho
    ) {
        Property hasMorphosyntacticElement =
                model.createProperty(ALLMAH + "hasMorphosyntacticElement");

        StmtIterator elements = morpho.listProperties(hasMorphosyntacticElement);
        while (elements.hasNext()) {
            RDFNode node = elements.nextStatement().getObject();
            if (node.isResource()) {
                importedMorpho.elements.add(readMorphoNode(model, node.asResource()));
            }
        }
    }

    private ImportedMorphoNode readMorphoNode(
            Model model,
            Resource resource
    ) {
        ImportedMorphoNode node = new ImportedMorphoNode();

        node.uri = resource.getURI();
        node.label = cleanImportedLabel(getLiteral(model, resource, RDFS.label));
        node.literalValue = cleanImportedLabel(getLiteral(
                model,
                resource,
                model.createProperty(ALLMAH + "literalValue")
        ));
        node.consolidatedLabel = cleanImportedLabel(getLiteral(
                model,
                resource,
                model.createProperty(ALLMAH + "consolidatedLabel")
        ));
        node.finalConsolidatedLabel = cleanImportedLabel(getLiteral(
                model,
                resource,
                model.createProperty(ALLMAH + "finalConsolidatedLabel")
        ));
        node.orderIndex = getOrderIndex(model, resource);
        node.level = getIntLiteral(
                model,
                resource,
                model.createProperty(ALLMAH + "morphoNodeLevel"),
                2
        );
        node.removed = getBooleanLiteral(
                model,
                resource,
                model.createProperty(ALLMAH + "isRemoved")
        );
        node.curly = getBooleanLiteral(
                model,
                resource,
                model.createProperty(ALLMAH + "hasCurlyBrackets")
        );
        node.square = getBooleanLiteral(
                model,
                resource,
                model.createProperty(ALLMAH + "hasSquareBrackets")
        );
        node.pipe = getBooleanLiteral(
                model,
                resource,
                model.createProperty(ALLMAH + "hasPipeBrackets")
        );
        node.abstractNode = getBooleanLiteral(
                model,
                resource,
                model.createProperty(ALLMAH + "isAbstract")
        );

        Statement parentStmt = resource.getProperty(model.createProperty(ALLMAH + "hasParentElement"));
        if (parentStmt != null && parentStmt.getObject().isResource()) {
            node.parentUri = parentStmt.getObject().asResource().getURI();
        }

        Statement oldParentStmt = resource.getProperty(model.createProperty(ALLMAH + "hasOldParentMorphosyntacticElement"));
        if (oldParentStmt != null && oldParentStmt.getObject().isResource()) {
            node.oldParentUri = oldParentStmt.getObject().asResource().getURI();
        }

        Statement newParentStmt = resource.getProperty(model.createProperty(ALLMAH + "hasNewParentMorphosyntacticElement"));
        if (newParentStmt != null && newParentStmt.getObject().isResource()) {
            node.newParentUri = newParentStmt.getObject().asResource().getURI();
        }

        Property hasLetter = model.createProperty(ALLMAH + "hasMorphosyntacticLetterElement");
        StmtIterator children = resource.listProperties(hasLetter);
        while (children.hasNext()) {
            RDFNode childNode = children.nextStatement().getObject();
            if (childNode.isResource()) {
                node.children.add(readMorphoNode(model, childNode.asResource()));
            }
        }
        node.children.sort(Comparator.comparingInt(c -> c.orderIndex));

        return node;
    }


    private void readMorphosyntacticGlossings(
            Model model,
            Resource morpho,
            ImportedMorphosyntacticTranscription importedMorpho
    ) {
        Property hasGlossing =
                model.createProperty(ALLMAH + "hasMorphosyntacticGlossing");

        StmtIterator glossings = morpho.listProperties(hasGlossing);
        while (glossings.hasNext()) {
            RDFNode node = glossings.nextStatement().getObject();
            if (!node.isResource()) {
                continue;
            }

            Resource glossingResource = node.asResource();
            ImportedMorphosyntacticGlossing glossing =
                    new ImportedMorphosyntacticGlossing();

            glossing.uri = glossingResource.getURI();
            glossing.label = cleanImportedLabel(getLiteral(model, glossingResource, RDFS.label));
            glossing.internalId = getLiteral(
                    model,
                    glossingResource,
                    model.createProperty(ALLMAH + "internalId")
            );
            glossing.literalValue = cleanImportedLabel(getLiteral(
                    model,
                    glossingResource,
                    model.createProperty(ALLMAH + "literalValue")
            ));
            glossing.confidence = getIntLiteral(
                    model,
                    glossingResource,
                    model.createProperty(ALLMAH + "confidence"),
                    0
            );
            glossing.orderIndex = getOrderIndex(model, glossingResource);

            readMorphosyntacticGlossingElements(model, glossingResource, glossing);
            glossing.elements.sort(Comparator.comparingInt(e -> e.orderIndex));
            readTranslationNodes(model, glossingResource, glossing);
            glossing.translationNodes.sort(Comparator.comparingInt(t -> t.orderIndex));
            importedMorpho.glossings.add(glossing);
        }
    }

    private void readMorphosyntacticGlossingElements(
            Model model,
            Resource glossingResource,
            ImportedMorphosyntacticGlossing glossing
    ) {
        Property hasGlossingElement =
                model.createProperty(ALLMAH + "hasGlossingElement");

        StmtIterator elements = glossingResource.listProperties(hasGlossingElement);
        while (elements.hasNext()) {
            RDFNode node = elements.nextStatement().getObject();
            if (!node.isResource()) {
                continue;
            }

            Resource elementResource = node.asResource();
            ImportedGlossingElement element = new ImportedGlossingElement();
            element.uri = elementResource.getURI();
            element.label = cleanImportedLabel(getLiteral(model, elementResource, RDFS.label));
            element.literalValue = cleanImportedLabel(getLiteral(
                    model,
                    elementResource,
                    model.createProperty(ALLMAH + "literalValue")
            ));
            element.semanticGloss = cleanImportedLabel(getLiteral(
                    model,
                    elementResource,
                    model.createProperty(ALLMAH + "semanticGloss")
            ));
            element.syntacticGloss = cleanImportedLabel(getLiteral(
                    model,
                    elementResource,
                    model.createProperty(ALLMAH + "syntacticGloss")
            ));
            element.posCluster = cleanImportedLabel(getLiteral(
                    model,
                    elementResource,
                    model.createProperty(ALLMAH + "posCluster")
            ));
            element.posTag = cleanImportedLabel(getLiteral(
                    model,
                    elementResource,
                    model.createProperty(ALLMAH + "posTag")
            ));
            element.wordNetSynsetId = cleanImportedLabel(getLiteral(
                    model,
                    elementResource,
                    model.createProperty(ALLMAH + "wordNetSynsetId")
            ));
            element.orderIndex = getOrderIndex(model, elementResource);

            Statement parentStmt = elementResource.getProperty(
                    model.createProperty(ALLMAH + "hasParentElement")
            );
            if (parentStmt != null && parentStmt.getObject().isResource()) {
                element.parentUri = parentStmt.getObject().asResource().getURI();
            }

            glossing.elements.add(element);
        }
    }

    private void readTranslationNodes(
            Model model,
            Resource glossingResource,
            ImportedMorphosyntacticGlossing glossing
    ) {
        Property hasTranslationNode =
                model.createProperty(ALLMAH + "hasTranslationNode");

        StmtIterator nodes = glossingResource.listProperties(hasTranslationNode);
        while (nodes.hasNext()) {
            RDFNode node = nodes.nextStatement().getObject();
            if (!node.isResource()) {
                continue;
            }

            Resource resource = node.asResource();
            ImportedTranslationNode importedNode = new ImportedTranslationNode();

            importedNode.uri = resource.getURI();
            importedNode.label = cleanImportedLabel(getLiteral(model, resource, RDFS.label));
            importedNode.internalId = getLiteral(
                    model,
                    resource,
                    model.createProperty(ALLMAH + "internalId")
            );
            importedNode.nodeKind = readTranslationNodeKind(model, resource);
            importedNode.originalText = cleanImportedLabel(getLiteral(
                    model,
                    resource,
                    model.createProperty(ALLMAH + "originalText")
            ));
            importedNode.translationText = cleanImportedLabel(getLiteral(
                    model,
                    resource,
                    model.createProperty(ALLMAH + "translationText")
            ));
            importedNode.elementLabel = cleanImportedLabel(getLiteral(
                    model,
                    resource,
                    model.createProperty(ALLMAH + "elementLabel")
            ));
            importedNode.semanticMeaning = cleanImportedLabel(getLiteral(
                    model,
                    resource,
                    model.createProperty(ALLMAH + "semanticMeaning")
            ));
            importedNode.syntacticFunction = cleanImportedLabel(getLiteral(
                    model,
                    resource,
                    model.createProperty(ALLMAH + "syntacticFunction")
            ));
            importedNode.wordNetSynsetId = cleanImportedLabel(getLiteral(
                    model,
                    resource,
                    model.createProperty(ALLMAH + "wordNetSynsetId")
            ));
            importedNode.explanation = cleanImportedLabel(getLiteral(
                    model,
                    resource,
                    model.createProperty(ALLMAH + "explanation")
            ));
            importedNode.dictionaryEntry = getBooleanLiteral(
                    model,
                    resource,
                    model.createProperty(ALLMAH + "isDictionaryEntry")
            );
            importedNode.orderIndex = getOrderIndex(model, resource);

            readTranslationNodeParts(model, resource, importedNode, "hasPart");
            readTranslationNodeParts(model, resource, importedNode, "composedOf");

            if (importedNode.internalId == null
                    || importedNode.internalId.trim().isEmpty()) {
                importedNode.internalId = importedNode.uri;
            }

            glossing.translationNodes.add(importedNode);
        }
    }

    private void readTranslationNodeParts(
            Model model,
            Resource resource,
            ImportedTranslationNode importedNode,
            String propertyLocalName
    ) {
        Property property = model.createProperty(ALLMAH + propertyLocalName);
        StmtIterator parts = resource.listProperties(property);
        while (parts.hasNext()) {
            RDFNode node = parts.nextStatement().getObject();
            if (node.isResource()) {
                String partId = getLiteral(
                        model,
                        node.asResource(),
                        model.createProperty(ALLMAH + "internalId")
                );
                if (partId == null || partId.trim().isEmpty()) {
                    partId = node.asResource().getURI();
                }
                importedNode.partIds.add(partId);
            }
        }
    }

    private String readTranslationNodeKind(Model model, Resource resource) {
        StmtIterator types = resource.listProperties(RDF.type);
        while (types.hasNext()) {
            RDFNode node = types.nextStatement().getObject();
            if (node.isResource()) {
                String local = node.asResource().getLocalName();
                if ("SimpleDictionaryEntry".equals(local)
                        || "ComposedDictionaryEntry".equals(local)
                        || "ConsolidatedTranslation".equals(local)
                        || "TranslationNode".equals(local)) {
                    return local;
                }
            }
        }
        return "TranslationNode";
    }

    private void readGT2GraphRelations(
            Model model,
            Resource gt2,
            ImportedGT2 importedGT2
    ) {

        Property hasRelation =
                model.createProperty(ALLMAH + "hasGraphemicRelation");

        StmtIterator relations =
                gt2.listProperties(hasRelation);

        while (relations.hasNext()) {
            RDFNode node = relations.nextStatement().getObject();

            if (!node.isResource()) {
                continue;
            }

            Resource relationResource = node.asResource();
            ImportedRelation relation = new ImportedRelation();

            /*
             * Current writer uses the generic relation model:
             *   relation allmah:hasSource   element-or-component
             *   relation allmah:hasTarget   element-or-component
             *   relation allmah:hasOperator operator-type
             *
             * Older TTLs may still contain the previous property names.
             * Keep both variants so old test files remain readable.
             */
            Statement sourceStmt = firstResourceProperty(
                    relationResource,
                    model.createProperty(ALLMAH + "hasSource"),
                    model.createProperty(ALLMAH + "sourceGraphemicElement")
            );
            if (sourceStmt != null) {
                relation.sourceUri = sourceStmt.getObject().asResource().getURI();
            }

            Statement targetStmt = firstResourceProperty(
                    relationResource,
                    model.createProperty(ALLMAH + "hasTarget"),
                    model.createProperty(ALLMAH + "targetGraphemicElement")
            );
            if (targetStmt != null) {
                relation.targetUri = targetStmt.getObject().asResource().getURI();
            }

            Statement operatorStmt = firstResourceProperty(
                    relationResource,
                    model.createProperty(ALLMAH + "hasOperator"),
                    model.createProperty(ALLMAH + "hasOperatorType")
            );
            if (operatorStmt != null) {
                relation.operatorUri = operatorStmt.getObject().asResource().getURI();
            }

            relation.orderIndex = getOrderIndex(model, relationResource);

            if (relation.sourceUri != null && relation.targetUri != null) {
                importedGT2.relations.add(relation);
            }
        }

        importedGT2.relations.sort(
                Comparator.comparingInt(r -> r.orderIndex)
        );
    }

    private Statement firstResourceProperty(
            Resource resource,
            Property primary,
            Property fallback
    ) {
        Statement stmt = resource.getProperty(primary);
        if (stmt != null && stmt.getObject().isResource()) {
            return stmt;
        }

        stmt = resource.getProperty(fallback);
        if (stmt != null && stmt.getObject().isResource()) {
            return stmt;
        }

        return null;
    }

    private ImportedElement readElement(
            Model model,
            Resource elem
    ) {

        ImportedElement e =
                new ImportedElement();

        e.uri = elem.getURI();
        e.label = cleanImportedLabel(getLiteral(model, elem, RDFS.label));

        e.internalId = getLiteral(
                model,
                elem,
                model.createProperty(ALLMAH + "internalId")
        );

        e.literalValue = cleanImportedLabel(getLiteral(
                model,
                elem,
                model.createProperty(ALLMAH + "literalValue")
        ));

        e.orderIndex =
                getOrderIndex(model, elem);

        Property hasParentElement =
                model.createProperty(ALLMAH + "hasParentElement");

        Statement parentStmt =
                elem.getProperty(hasParentElement);

        if (parentStmt != null
                && parentStmt.getObject().isResource()) {

            e.parentUri =
                    parentStmt.getObject().asResource().getURI();
        }

        Property hasGraphemicComponent =
                model.createProperty(ALLMAH + "hasGraphemicComponent");

        Statement componentStmt =
                elem.getProperty(hasGraphemicComponent);

        if (componentStmt != null
                && componentStmt.getObject().isResource()) {

            Resource componentResource =
                    componentStmt.getObject().asResource();

            e.componentUri =
                    componentResource.getURI();

            e.component =
                    readComponent(model, componentResource);
        }

        e.assignedReadings.addAll(
                readReadingAssignments(
                        model,
                        elem,
                        "SelectedReading"
                )
        );

        return e;
    }

    private ArrayList<ImportedElement> readComponentMemberElements(
            Model model,
            Resource componentResource,
            ImportedComponent component,
            String memberPropertyName
    ) {

        ArrayList<ImportedElement> result =
                new ArrayList<ImportedElement>();

        Property memberProperty =
                model.createProperty(ALLMAH + memberPropertyName);

        StmtIterator members =
                componentResource.listProperties(memberProperty);

        while (members.hasNext()) {

            RDFNode node =
                    members.nextStatement().getObject();

            if (!node.isResource()) {
                continue;
            }

            ImportedElement element =
                    readElement(model, node.asResource());

            element.componentUri = component.uri;
            element.component = component;

            result.add(element);
        }

        result.sort(Comparator.comparingInt(e -> e.orderIndex));
        return result;
    }

    private String preferredComponentLabel(ImportedComponent component) {
        if (component == null) {
            return "Component";
        }
        if (component.componentLabel != null
                && !component.componentLabel.trim().isEmpty()) {
            return component.componentLabel;
        }
        if (component.label != null
                && !component.label.trim().isEmpty()) {
            return component.label;
        }
        return "Component";
    }

    private String firstComponentMemberParentUri(
            Model model,
            Resource componentResource,
            String memberPropertyName
    ) {

        Property memberProperty =
                model.createProperty(ALLMAH + memberPropertyName);

        StmtIterator members =
                componentResource.listProperties(memberProperty);

        while (members.hasNext()) {

            RDFNode node =
                    members.nextStatement().getObject();

            if (!node.isResource()) {
                continue;
            }

            ImportedElement element =
                    readElement(model, node.asResource());

            if (element.parentUri != null
                    && !element.parentUri.isEmpty()) {
                return element.parentUri;
            }
        }

        /*
         * Current GraphemicComponent often stores only:
         *   GraphemicComponent allmah:hasSource GraphematicComponent .
         * In that case take the first member of the source GraphematicComponent
         * and use its parent GT1 element. This lets AllmahGUI create
         * new GT2Element(component) without falling back to the wrong GT1 parent.
         */
        Property hasSource = model.createProperty(ALLMAH + "hasSource");
        StmtIterator sources = componentResource.listProperties(hasSource);
        while (sources.hasNext()) {
            RDFNode sourceNode = sources.nextStatement().getObject();
            if (!sourceNode.isResource()) {
                continue;
            }
            Resource source = sourceNode.asResource();
            StmtIterator sourceMembers =
                    source.listProperties(model.createProperty(ALLMAH + "hasGraphematicElement"));
            while (sourceMembers.hasNext()) {
                RDFNode memberNode = sourceMembers.nextStatement().getObject();
                if (!memberNode.isResource()) {
                    continue;
                }
                ImportedElement element = readElement(model, memberNode.asResource());
                if (element.uri != null && !element.uri.isEmpty()) {
                    return element.uri;
                }
            }
        }

        return null;
    }

    private ImportedComponent readComponent(
            Model model,
            Resource component
    ) {

        String uri =
                component.getURI();

        ImportedComponent cached =
                componentCache.get(uri);

        if (cached != null) {
            return cached;
        }

        ImportedComponent result =
                new ImportedComponent();

        result.uri = uri;
        result.label = cleanImportedLabel(getLiteral(model, component, RDFS.label));
        result.componentLabel = cleanImportedLabel(getLiteral(
                model,
                component,
                model.createProperty(ALLMAH + "componentLabel")
        ));
        result.componentColor = getLiteral(
                model,
                component,
                model.createProperty(ALLMAH + "componentColor")
        );
        result.componentSourceLabel = cleanImportedLabel(getLiteral(
                model,
                component,
                model.createProperty(ALLMAH + "componentSourceLabel")
        ));
        result.orderIndex =
                getOrderIndex(model, component);

        Property hasSource =
                model.createProperty(ALLMAH + "hasSource");

        StmtIterator sources =
                component.listProperties(hasSource);

        while (sources.hasNext()) {
            RDFNode node = sources.nextStatement().getObject();
            if (node.isResource()) {
                result.sourceUris.add(node.asResource().getURI());
            }
        }

        Property hasComponentMember =
                model.createProperty(ALLMAH + "hasComponentMember");

        StmtIterator members =
                component.listProperties(hasComponentMember);

        while (members.hasNext()) {

            RDFNode node =
                    members.nextStatement().getObject();

            if (node.isResource()) {
                result.memberUris.add(
                        node.asResource().getURI()
                );
            }
        }

        componentCache.put(uri, result);
        return result;
    }

    private int getIntLiteral(
            Model model,
            Resource resource,
            Property property,
            int defaultValue
    ) {

        Statement stmt =
                resource.getProperty(property);

        if (stmt == null || !stmt.getObject().isLiteral()) {
            return defaultValue;
        }

        try {
            return stmt.getObject().asLiteral().getInt();
        } catch (Exception ex) {
            try {
                return Integer.parseInt(stmt.getObject().asLiteral().getString());
            } catch (Exception ex2) {
                return defaultValue;
            }
        }
    }

    private boolean getBooleanLiteral(
            Model model,
            Resource resource,
            Property property
    ) {

        Statement stmt =
                resource.getProperty(property);

        if (stmt == null || !stmt.getObject().isLiteral()) {
            return false;
        }

        try {
            return stmt.getObject().asLiteral().getBoolean();
        } catch (Exception ex) {
            String value =
                    stmt.getObject().asLiteral().getString();

            return "true".equalsIgnoreCase(value)
                    || "1".equals(value)
                    || "yes".equalsIgnoreCase(value);
        }
    }

    private int getOrderIndex(
            Model model,
            Resource resource
    ) {

        String order =
                getLiteral(
                        model,
                        resource,
                        model.createProperty(ALLMAH + "orderIndex")
                );

        if (order == null || order.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(order);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private String cleanImportedLabel(String value) {
        if (value == null) {
            return "";
        }

        String s = value;
        s = s.replace("&lt;", "<")
             .replace("&gt;", ">")
             .replace("&amp;", "&")
             .replace("&quot;", "\"")
             .replace("&#39;", "'")
             .replace("&nbsp;", " ");

        s = s.replaceAll("(?is)<[^>]*>", "");
        s = s.replaceAll("\\s+", " ").trim();
        return s;
    }

    private String getLiteral(
            Model model,
            Resource resource,
            Property property
    ) {

        Statement stmt =
                resource.getProperty(property);

        if (stmt == null || !stmt.getObject().isLiteral()) {
            return "";
        }

        return stmt.getObject().asLiteral().getString();
    }

    public static class ImportedDocument {
        public String uri;
        public String label;
        public String title;
        public List<ImportedDocumentReading> readings =
                new ArrayList<ImportedDocumentReading>();
        public ImportedDocumentReading reading;
    }

    public static class ImportedDocumentReading {
        public String uri;
        public String label;
        public String internalId;
        public int orderIndex = 0;
        public int confidence = 0;

        public List<ImportedBlock> blocks =
                new ArrayList<>();
    }

    public static class ImportedBlock {
        public String uri;
        public String label;
        public String blockLabel;
        public String blockExpression;
        public String internalId;
        public String teiXmlId;
        public int orderIndex = 0;

        public List<ImportedGlyph> glyphs =
                new ArrayList<>();

        public List<ImportedNT1> nt1 =
                new ArrayList<>();

        public List<ImportedBlockAnnotation> annotations =
                new ArrayList<ImportedBlockAnnotation>();
    }

    public static class ImportedBlockAnnotation {
        public String uri;
        public String label;
        public String internalId;
        public String annotationType;
        public String annotationSubType;
        public String comment;
        public int orderIndex = 0;
        public List<String> blockUris = new ArrayList<String>();
        public ImportedAnnotationURI annotationURI;
    }

    public static class ImportedAnnotationURI {
        public String uri;
        public String label;
        public String uriType;
        public String activityDescription;
        public String placeOfActivity;
        public String dedicationTitle;
        public String dedicationPlace;
        public String placeName;
        public String locatedIn;
        public String personName;
        public String gender;
        public String groupName;
    }

    public static class ImportedGlyph {
        public String uri;
        public String label;
        public String catalogNumber;
        public String imageUri;
        public int orderIndex = 0;

        public List<ImportedReading> readings =
                new ArrayList<>();
    }

    public static class ImportedReading {
        public String uri;
        public String label;
        public String literalValue;
        public String readingType;
        public String confidence = "";
        public String sourceUri;
    }

    public static class ImportedElement {
        public String uri;
        public String label;
        public String internalId;
        public String literalValue;
        public String parentUri;
        public String componentUri;
        public ImportedComponent component;
        public List<ImportedReading> assignedReadings =
                new ArrayList<ImportedReading>();
        public int orderIndex = 0;
    }

    public static class ImportedComponent {
        public String uri;
        public String label;
        public String componentLabel;
        public String componentSourceLabel;
        public String componentColor;
        public int orderIndex = 0;
        public List<String> memberUris =
                new ArrayList<>();
        public List<String> sourceUris =
                new ArrayList<>();
    }

    public static class ImportedNT1 {
        public String uri;
        public String label;
        public String internalId;
        public int orderIndex = 0;
        public int confidence = 0;

        public List<ImportedElement> elements =
                new ArrayList<>();

        public List<ImportedNT2> nt2 =
                new ArrayList<>();
    }

    public static class ImportedNT2 {
        public String uri;
        public String label;
        public String internalId;
        public int orderIndex = 0;
        public int confidence = 0;

        public List<ImportedElement> elements =
                new ArrayList<>();

        public List<ImportedGT1> gt1 =
                new ArrayList<>();
    }

    public static class ImportedGT1 {
        public String uri;
        public String label;
        public String internalId;
        public int orderIndex = 0;
        public int confidence = 0;

        public List<ImportedElement> elements =
                new ArrayList<>();

        public List<ImportedGT2> gt2 =
                new ArrayList<>();
    }

    public static class ImportedGT2 {
        public String uri;
        public String label;
        public String internalId;
        public int orderIndex = 0;
        public int confidence = 0;
        public boolean frozen = false;

        public List<ImportedElement> elements =
                new ArrayList<>();

        public List<ImportedRelation> relations =
                new ArrayList<>();

        public List<ImportedPhonemicTranscription> phonemic =
                new ArrayList<>();
    }

    public static class ImportedPhonemicTranscription {
        public String uri;
        public String label;
        public String internalId;
        public int orderIndex = 0;
        public int confidence = 0;
        public boolean frozen = false;

        public List<ImportedElement> elements =
                new ArrayList<>();

        public List<ImportedRelation> relations =
                new ArrayList<>();

        public List<ImportedMorphosyntacticTranscription> morphosyntactic =
                new ArrayList<>();
    }

    public static class ImportedMorphosyntacticTranscription {
        public String uri;
        public String label;
        public String internalId;
        public String literalValue;
        public String consolidatedLabel;
        public String finalConsolidatedLabel;
        public int orderIndex = 0;
        public int confidence = 0;
        public List<ImportedMorphoNode> elements = new ArrayList<>();
        public List<ImportedMorphosyntacticGlossing> glossings = new ArrayList<>();
    }

    public static class ImportedMorphoNode {
        public String uri;
        public String label;
        public String literalValue;
        public String consolidatedLabel;
        public String finalConsolidatedLabel;
        public String parentUri;
        public String newParentUri;
        public String oldParentUri;
        public int level = 2;
        public int orderIndex = 0;
        public boolean removed = false;
        public boolean curly = false;
        public boolean square = false;
        public boolean pipe = false;
        public boolean abstractNode = false;
        public List<ImportedMorphoNode> children = new ArrayList<>();
    }


    public static class ImportedMorphosyntacticGlossing {
        public String uri;
        public String label;
        public String internalId;
        public String literalValue;
        public int orderIndex = 0;
        public int confidence = 0;
        public List<ImportedGlossingElement> elements = new ArrayList<>();
        public List<ImportedTranslationNode> translationNodes = new ArrayList<>();
    }

    public static class ImportedGlossingElement {
        public String uri;
        public String label;
        public String literalValue;
        public String parentUri;
        public String semanticGloss;
        public String syntacticGloss;
        public String posCluster;
        public String posTag;
        public String wordNetSynsetId;
        public int orderIndex = 0;
    }

    public static class ImportedTranslationNode {
        public String uri;
        public String label;
        public String internalId;
        public String nodeKind = "TranslationNode";
        public String originalText;
        public String translationText;
        public String elementLabel;
        public String semanticMeaning;
        public String syntacticFunction;
        public String wordNetSynsetId;
        public String explanation;
        public boolean dictionaryEntry = false;
        public int orderIndex = 0;
        public List<String> partIds = new ArrayList<>();
    }

    public static class ImportedRelation {
        public String sourceUri;
        public String targetUri;
        public String operatorUri;
        public int orderIndex = 0;
    }
}