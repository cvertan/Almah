package allmahVer4.rdf;

import java.io.File;
import java.awt.Font;
import java.util.ArrayList;

import allmahVer4.HieroglyphenBlock;
import allmahVer4.MayaDocumentParser;
import allmahVer4.AllmahGUI;
public class RdfExportTest {

    public static void main(String[] args) throws Exception {

        File xmlFile = new File("/home/corona/Maya/TEI/Altamira,_Stela_10.3vv47.0.xml");
        
       // Uxul,_Fragment_300.3rx74.0
        //Altamira,_Stela_10.3vv47.0
        //Uxul,_Altar_7.3rx6w.0
        
        AllmahGUI gui = new AllmahGUI();
        MayaDocumentParser parser =
                new MayaDocumentParser(
                        gui,
                        "test",
                        xmlFile,
                        new Font("Serif", Font.PLAIN, 12)
                );

        parser.getMayaDocumentParts();

        ArrayList<HieroglyphenBlock> blocks =
                parser.getParsedBlocks();

        AllmahTurtleWriter writer =
                new AllmahTurtleWriter();

        writer.exportDocument(
                parser.getDocID(),
                parser.getMayaDocumentTitle(),
                blocks,
                gui,
                new File("allmah-real-export-1.ttl")
        );

        System.out.println("Export done: allmah-real-export.ttl");
        System.out.println("Blocks exported: " + blocks.size());
    }
}
