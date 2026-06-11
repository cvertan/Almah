package allmahVer4.rdf;

import java.io.File;

public class RdfImportTest {

    public static void main(String[] args) {

        AllmahRdfImporter importer =
                new AllmahRdfImporter();

        AllmahRdfImporter.ImportedDocument doc =
                importer.importFromTurtleFile(
                        new File("allmah-real-export-NT1.ttl")
                );

        System.out.println("Document: " + doc.title);
        System.out.println("URI: " + doc.uri);

        if (doc.reading != null) {
            System.out.println("Reading: " + doc.reading.internalId);
            System.out.println("Blocks: " + doc.reading.blocks.size());

            for (AllmahRdfImporter.ImportedBlock block : doc.reading.blocks) {

                System.out.println("\nBLOCK: " + block.blockLabel);
                System.out.println("  Glyphs: " + block.glyphs.size());

                for (AllmahRdfImporter.ImportedGlyph glyph : block.glyphs) {
                    System.out.println("    Glyph: " + glyph.label);

                    for (AllmahRdfImporter.ImportedReading r : glyph.readings) {
                        System.out.println("      Reading: " + r.label + " [" + r.readingType + "]");
                    }
                }

                for (AllmahRdfImporter.ImportedNT1 nt1 : block.nt1) {
                    System.out.println("  NT1: " + nt1.label);

                    for (AllmahRdfImporter.ImportedElement e1 : nt1.elements) {
                        System.out.println("    NT1Element: " + e1.label);
                    }

                    for (AllmahRdfImporter.ImportedNT2 nt2 : nt1.nt2) {
                        System.out.println("    NT2: " + nt2.label);

                        for (AllmahRdfImporter.ImportedElement e2 : nt2.elements) {
                            System.out.println("      NT2Element: " + e2.label);
                        }

                        for (AllmahRdfImporter.ImportedGT1 gt1 : nt2.gt1) {
                            System.out.println("      GT1: " + gt1.label);

                            for (AllmahRdfImporter.ImportedElement e3 : gt1.elements) {
                                System.out.println("        GT1Element: " + e3.label);
                            }

                            for (AllmahRdfImporter.ImportedGT2 gt2 : gt1.gt2) {
                                System.out.println("        GT2: " + gt2.label);

                                for (AllmahRdfImporter.ImportedElement e4 : gt2.elements) {
                                    System.out.println("          GT2Element: " + e4.label);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}