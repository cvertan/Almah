package allmahVer4;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.db.object.ODatabaseObject;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import com.orientechnologies.orient.object.db.ODatabaseObjectPool;
import com.orientechnologies.orient.object.db.*;
import net.atlanticbb.tantlinger.shef.*;
import java.awt.FontMetrics;
import java.awt.BasicStroke;
import edu.mit.jwi.*;
import edu.mit.jwi.item.*;
import java.net.URL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.util.*;
import java.io.OutputStream;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.border.EtchedBorder;
import javax.swing.JToggleButton.ToggleButtonModel;
import org.w3c.dom.Document;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.io.File;
import xmleditorkit.*;
//import org.apache.batik.util.gui.xmleditor.XMLEditorKit;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Point;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.*;
import org.classicmayan.tools.*;
import org.xml.sax.SAXException;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Supplier;

//import java.util.function.Supplier;
import org.jsoup.*;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.algorithms.shortestpath.MinimumSpanningForest2;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.renderers.DefaultEdgeLabelRenderer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.GraphMouseListener;
import edu.uci.ics.jung.visualization.control.LabelEditingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.decorators.PickableEdgePaintTransformer;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.uci.ics.jung.visualization.picking.PickedInfo;
import javax.swing.tree.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import info.textgrid.clients.SearchClient;
import info.textgrid.clients.tgcrud.CrudClientException;
import info.textgrid.clients.tgsearch.SearchQueryBuilder;
import info.textgrid.namespaces.middleware.tgsearch.Response;
import info.textgrid.namespaces.middleware.tgsearch.ResultType;
import xmleditorkit.XMLEditorKit;

import java.awt.Graphics2D;
import allmahVer4.rdf.AllmahTurtleWriter;
import allmahVer4.rdf.AllmahRdfImporter;
import allmahVer4.graphdb.GraphDBConfig;
import allmahVer4.graphdb.GraphDBConnectionDialog;
import allmahVer4.graphdb.GraphDBWriter;
import allmahVer4.graphdb.GraphDBReader;

public class AllmahGUI extends JFrame implements ActionListener{
	private JDesktopPane desk;
	private String versTool;
	protected Color colC,colO;
	private String inputDir;
	private String[] docs;
	private ArrayList<MorphoSyntGlossing> msg;
	private Random randomGenerator;
	private Font typFont1=new Font("Sans Serif",Font.PLAIN,16);
	private Font typFont1Menu1=typFont1.deriveFont(Font.BOLD,18);
	private Color mainFrameColor=Color.LIGHT_GRAY;
	private JTree letterTree, letterTreeM;
	private TreePoS trpos;
	private  String selPOS, sf;
	private  IDictionary dict;
	private String trs="";
	private int nrg=-1;
	private String selD;
	int kk=0;
	private JMenuItem saveFile;
	private JMenuItem openLocalTtlFile;
	private JMenuItem saveToGraphDBFile;
	private JMenuItem openFromGraphDBFile;
	private JMenuItem graphDBSettingsItem;
	private JMenuItem connectGraphDBItem;
	private GraphDBConfig graphDBConfig = GraphDBConfig.defaultTestConfig();
	private boolean graphDBConnected = false;
	private  ODatabaseSession db;
	private   JScrollPane scrollFeat;
	private ElemSyntaxPanel[] glossArray;
private  ChildFrame morphoGlossFrame;
ArrayList<ElemSyntaxPanel[]> allGlosses;
	private TreePoSFeatures trposf;
	private JMenuItem openNewLocalFile, openNewGridFile, openAnnotatedFile, configCallenderAnn,configOnomasticsAnn, session;
 private  VisualizationViewer<GlyphNode,OperatorLink>	vv2;
	protected HyerogliphenDocument doc;
	protected ArrayList<DocumentReading> docr;
	protected ArrayList<HieroglyphenBlock> hb;
	private BlockAnnotation selectedBlock;
	private DefaultListModel modelC,modelO;
	private org.jdom2.Document selectedDoc;
	private  JList listC, listO;
	protected ArrayList<NumTranslit1> nt1;
	private String refMeaning;
	private ArrayList<ComponentElement> ce=new ArrayList<ComponentElement>();
	protected ArrayList<NumTranslit2> nt2;
	protected ArrayList<GraphTranslit1> gt1;
	protected ArrayList<GraphTranslit2> gt2;
	protected ArrayList<PhonemTranslit> pt;
	protected ArrayList<MorphoSyntTranslit> mt;
	protected ArrayList<MorphoSyntGlossing> mg;
	protected ArrayList<FinalTranslation> mtr;
	private static GraphNT2Node n2new;
	JPanel custom;
	private SemanticAnnotation semA;
	private ArrayList<GraphNT2Node> compNT2Nodes=new ArrayList<GraphNT2Node>();
	protected ArrayList<BlockAnnotation> blockAnn =new ArrayList<BlockAnnotation>();
	protected ArrayList<String> calenderValues=new ArrayList<String>();
	protected ArrayList<String> onomasticValues=new ArrayList<String>();
	//protected ArrayList<GraphTranslit2> gt2;
	//protected ArrayList<PhonTranslit> pt;
	 JMenuItem defReading;
	 private JScrollPane sc;
	 private String abstrEl="";
	 private String newSubEl="";
	 private String sid="";
	 private  File file;
	 private URL u;
	 private String sessionID="";
	 private String loginDB="";
	 private String passwordDB="";
	 private String dirIns="";
	 private String selectedDocument="";
	protected Map <String,Integer> mdocr=new HashMap<String,Integer>();
	final LinesComponent comp = new LinesComponent();
	protected Map <String,Integer> mhb=new HashMap<String,Integer>();
	protected Map <String,Integer> mnt1=new HashMap<String,Integer>();
	protected Map <String,Integer> mnt2=new HashMap<String,Integer>();
	protected Map <String,Integer> mgt1=new HashMap<String,Integer>();
	protected Map <String,Integer> mgt2=new HashMap<String,Integer>();
	protected Map <String,Integer> mpt=new HashMap<String,Integer>();
	protected Map <String,Integer> mbla=new HashMap<String,Integer>();
	protected Map <String,Integer> mmt=new HashMap<String,Integer>();
	protected Map <String,Integer> mmg=new HashMap<String,Integer>();
	protected Map <String,Integer> mmtr=new HashMap<String,Integer>();
	protected ArrayList<TranslationNode> translationNodes = new ArrayList<TranslationNode>();
	protected Map<String,Integer> mtranslationNodes = new HashMap<String,Integer>();
	private ArrayList<ArrayList<GlossingVariant>> gset;
	private JTable actResults;
	private JTable placeResults;
	private JTable epigrActors;
	private JTable epigrGroups;
    private String[][] dataA;
    private String[][] dataD;
    private String[][] dataP;
    private String[][] dataEA;
    private String[][] dataEP;
     private String[] infoNamesA= {"Description","Place of activity"};
     private String[] infoNamesD= {"Title","Place"};
     private String[] infoNamesP= {"Place Name","Place is located in"};
     private String[] infoNamesEA= {"Person Name","Gender"};
     private String[] infoNamesEP= {"GroupName"};
    private JTable dedResults=new JTable();
private String selName;
  private boolean changed;
	private Color selC;
	private JTree blockTree;
	private Point p1,p2;
	public AllmahGUI() {
		super("Almah - Annotation of classical Maya Documents");
		String s="";
		AllmahSettings settings=new AllmahSettings();
		calenderValues=settings.getCallenderType();
		onomasticValues=settings.getOnomasticsType();
		colC=Color.MAGENTA;
		colO=Color.GREEN;
		 try{
			   File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
			   s=System.getProperty("java.class.path");
			   int pT=s.indexOf("allmah");
			   versTool=s.substring(pT+6);
			  
			   if(!(versTool.indexOf("bin")>=0)) { 
			       pT=versTool.indexOf(".jar");
			   }
			   else   pT=versTool.indexOf("\\target");
		versTool=versTool.substring(0,pT);
		   }
          catch(Exception e){}
		 randomGenerator = new Random();
		 this.setTitle(this.getTitle()+" "+versTool);
		   System.out.println("Version Tool "+versTool);	
		   buildInterface();
		  
	}
/*	public AllmahGUI getInterf() {
		return this;
	}*/
	
	
	//
	
	//
	private void showImportedRdfDocument(
	        AllmahRdfImporter.ImportedDocument doc
	) {

	    StringBuilder sb = new StringBuilder();

	    sb.append("Document: ")
	      .append(doc.title)
	      .append("\n\n");

	    if (doc.reading != null) {

	        sb.append("Reading: ")
	          .append(doc.reading.internalId)
	          .append("\n");

	        sb.append("Blocks: ")
	          .append(doc.reading.blocks.size())
	          .append("\n\n");

	        for (AllmahRdfImporter.ImportedBlock block
	                : doc.reading.blocks) {

	            sb.append("BLOCK: ")
	              .append(block.blockLabel)
	              .append("\n");

	            sb.append("  Glyphs: ")
	              .append(block.glyphs.size())
	              .append("\n");

	            for (AllmahRdfImporter.ImportedGlyph glyph
	                    : block.glyphs) {

	                sb.append("    Glyph: ")
	                  .append(glyph.label)
	                  .append("\n");

	                for (AllmahRdfImporter.ImportedReading r
	                        : glyph.readings) {

	                    sb.append("      Reading: ")
	                      .append(r.label)
	                      .append(" [")
	                      .append(r.readingType)
	                      .append("]\n");
	                }
	            }

	            for (AllmahRdfImporter.ImportedNT1 nt1
	                    : block.nt1) {

	                sb.append("  NT1: ")
	                  .append(nt1.label)
	                  .append("\n");

	                for (AllmahRdfImporter.ImportedNT2 nt2
	                        : nt1.nt2) {

	                    sb.append("    NT2: ")
	                      .append(nt2.label)
	                      .append("\n");

	                    for (AllmahRdfImporter.ImportedGT1 gt1
	                            : nt2.gt1) {

	                        sb.append("      GT1: ")
	                          .append(gt1.label)
	                          .append("\n");

	                        for (AllmahRdfImporter.ImportedGT2 gt2
	                                : gt1.gt2) {

	                            sb.append("        GT2: ")
	                              .append(gt2.label)
	                              .append("\n");
	                        }
	                    }
	                }
	            }

	            sb.append("\n");
	        }
	    }

	    JTextArea area = new JTextArea(sb.toString());
	    area.setEditable(false);

	    JScrollPane scroll =
	            new JScrollPane(area);

	    scroll.setPreferredSize(
	            new java.awt.Dimension(900, 600)
	    );

	    JOptionPane.showMessageDialog(
	            this,
	            scroll,
	            "Imported RDF Document",
	            JOptionPane.INFORMATION_MESSAGE
	    );
	}
	
	public NumTranslit1 getNT1ById(String id) {
	    Integer index = mnt1.get(id);
	    if (index == null) {
	        return null;
	    }
	    return nt1.get(index.intValue());
	}
	
	public NumTranslit2 getNT2ById(String id) {
	    Integer index = mnt2.get(id);
	    if (index == null) {
	        return null;
	    }
	    return nt2.get(index.intValue());
	}
	
	public GraphTranslit1 getGT1ById(String id) {
	    Integer index = mgt1.get(id);
	    if (index == null) {
	        return null;
	    }
	    return gt1.get(index.intValue());
	}

	public GraphTranslit2 getGT2ById(String id) {
	    Integer index = mgt2.get(id);
	    if (index == null) {
	        return null;
	    }
	    return gt2.get(index.intValue());
	}
	
	//

	private String getParentId(String id) {

	    if (id == null) {
	        return null;
	    }

	    int pos =
	            id.lastIndexOf(">");

	    if (pos <= 0) {
	        return null;
	    }

	    return id.substring(0, pos);
	}
	//
	private String cleanGlyphKey(String label) {

	    if (label == null) {
	        return "";
	    }

	    String key = label;

	    if (!key.equals("??")) {
	        if (key.startsWith("?")) {
	            key = key.substring(1);
	        }

	        if (key.endsWith("?")) {
	            key = key.substring(0, key.length() - 1);
	        }
	    }

	    if (key.startsWith("*")) {
	        key = key.substring(1);
	    }

	    if (key.endsWith("*")) {
	        key = key.substring(0, key.length() - 1);
	    }

	    return key;
	}
	//
	private String canonicalGlyphCatalogKey(String label) {

	    String key = cleanGlyphKey(stripImportedHtml(label));

	    if (key == null) {
	        return "";
	    }

	    /*
	     * GUI glyph tokens may keep the TEI/block suffix, for example
	     * 796st, 229bl, 126ex, while RDF GlyphElement resources are
	     * catalogue-level and therefore use 796, 229, 126.  For matching
	     * imported RDF glyph URIs back to the concrete GUI glyph node, compare
	     * by catalogue key, not by imported order.
	     */
	    if (!"??".equals(key) && key.matches("^[0-9]+[A-Za-z]{2}$")) {
	        key = key.substring(0, key.length() - 2);
	    }

	    return key;
	}

	private String findImportedGlyphUri(
	        AllmahRdfImporter.ImportedBlock block,
	        String glyphLabel
	) {

	    String key = canonicalGlyphCatalogKey(glyphLabel);

	    for (AllmahRdfImporter.ImportedGlyph glyph
	            : block.glyphs) {

	        if (key.equals(canonicalGlyphCatalogKey(glyph.label))) {
	            return glyph.uri;
	        }
	    }

	    return null;
	}
	//
	private void assignImportedGlyphUrisByPosition(
	        HieroglyphenBlock block,
	        AllmahRdfImporter.ImportedBlock importedBlock
	) {
	    if (block == null || importedBlock == null) {
	        return;
	    }

	    ArrayList<GlyphElementNode> glyphs =
	            block.getElements();

	    if (glyphs == null || importedBlock.glyphs == null) {
	        return;
	    }

	    Map<String, String> imageUrisByLabel =
	            new HashMap<String, String>();

	    int max = Math.min(
	            glyphs.size(),
	            importedBlock.glyphs.size()
	    );

	    for (int i = 0; i < max; i++) {
	        GlyphElementNode glyph = glyphs.get(i);
	        AllmahRdfImporter.ImportedGlyph importedGlyph =
	                importedBlock.glyphs.get(i);

	        if (glyph == null || importedGlyph == null) {
	            continue;
	        }

	        if (importedGlyph.imageUri != null
	                && !importedGlyph.imageUri.isEmpty()) {
	            glyph.setImageUri(importedGlyph.imageUri);
	            imageUrisByLabel.put(
	                    cleanGlyphKey(glyph.getLabel()),
	                    importedGlyph.imageUri
	            );
	        }
	    }

	    /*
	     * Keep the block-level map too, for older code paths.
	     * The positional assignment above is the authoritative one;
	     * this map is only a fallback and may collapse duplicate glyph labels.
	     */
	    block.setImageUris(imageUrisByLabel);
	}

	//
	
	private void addImportedGT2ToGT1Nodes() {

	    for (GraphTranslit1 g1 : gt1) {

	        for (String gt2Id : g1.getGraphTranslit2()) {

	            Integer index =
	                    mgt2.get(gt2Id);

	            if (index == null) {
	                System.out.println("GT2 missing: " + gt2Id);
	                continue;
	            }

	            GraphTranslit2 g2 =
	                    gt2.get(index.intValue());

	            if (!g1.listNodes().contains(g2)) {
	                g1.listNodes().add(g2);
	            }
	        }
	    }
	}
	//

	private Map<String, JLabel> buildPicturesFromImportedBlock(
	        HieroglyphenBlock block,
	        AllmahRdfImporter.ImportedBlock importedBlock
	) {

	    Map<String, JLabel> pictures =
	            new HashMap<String, JLabel>();

	    pictures.put("#", new JLabel("#"));
	    pictures.put("??", new JLabel("??"));
	    pictures.put("\u2026", new JLabel("\u2026"));

	    IdiomTextGridObject ob = null;

	    try {
	        ob = new IdiomTextGridObject(
	                "src/main/resources/idiomQueries-SECRET-prod.properties"
	        );
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }

	    ArrayList<GlyphElementNode> guiGlyphs = null;
	    if (block != null) {
	        guiGlyphs = block.getElements();
	    }

	    for (int i = 0; i < importedBlock.glyphs.size(); i++) {

	        AllmahRdfImporter.ImportedGlyph glyph =
	                importedBlock.glyphs.get(i);

	        String rdfKey = cleanGlyphKey(glyph.label);
	        String guiKey = rdfKey;

	        /*
	         * Global GlyphElement labels in RDF are suffix-free (for example
	         * "229"), while the block parser/GUI labels keep the original
	         * block expression token (for example "229bl").  calculateImage()
	         * looks up pictures by the GUI token, so store both keys.  Without
	         * this the imageUri is read correctly but the icon is never found.
	         */
	        if (guiGlyphs != null && i < guiGlyphs.size()
	                && guiGlyphs.get(i) != null) {
	            guiKey = cleanGlyphKey(guiGlyphs.get(i).getLabel());
	        }

	        JLabel label = new JLabel(guiKey);

	        if (glyph.imageUri != null
	                && !glyph.imageUri.isEmpty()
	                && ob != null) {

	            try {
	                BufferedImage picture =
	                        ob.getTextGridObjectByUri(
	                                glyph.imageUri,
	                                sid,
	                                40
	                        );

	                if (picture != null) {
	                    label.setIcon(
	                            new ImageIcon(picture)
	                    );
	                }

	            } catch (Exception ex) {
	                System.out.println(
	                        "Cannot read imported RDF image "
	                                + glyph.imageUri
	                );
	            }
	        }

	        pictures.put(guiKey, label);
	        pictures.put(rdfKey, label);
	    }

	    return pictures;
	}
	
	//
	
	
	private void rebuildGuiStateFromImportedRdf(
	        AllmahRdfImporter.ImportedDocument imported
	) {Map<String, GlyphElementNode> glyphByUri =
    new HashMap<String, GlyphElementNode>();

Map<String, NT1Element> nt1ElementByUri =
    new HashMap<String, NT1Element>();

Map<String, NT2Element> nt2ElementByUri =
    new HashMap<String, NT2Element>();

Map<String, GT1Element> gt1ElementByUri =
    new HashMap<String, GT1Element>();

Map<String, ComponentElement> componentByUri =
    new HashMap<String, ComponentElement>();

ArrayList<AllmahRdfImporter.ImportedDocumentReading> importedReadings =
    new ArrayList<AllmahRdfImporter.ImportedDocumentReading>();

if (imported.readings != null && !imported.readings.isEmpty()) {
importedReadings.addAll(imported.readings);
} else if (imported.reading != null) {
importedReadings.add(imported.reading);
}

String docId = imported.title;

if (!importedReadings.isEmpty()
    && importedReadings.get(0).internalId != null
    && importedReadings.get(0).internalId.contains(">R")) {

docId =
        importedReadings.get(0).internalId.substring(
                0,
                importedReadings.get(0).internalId.indexOf(">R")
        );
}

doc = new HyerogliphenDocument(docId, this);

docr = new ArrayList<DocumentReading>();
hb = new ArrayList<HieroglyphenBlock>();

nt1 = new ArrayList<NumTranslit1>();
nt2 = new ArrayList<NumTranslit2>();
gt1 = new ArrayList<GraphTranslit1>();
gt2 = new ArrayList<GraphTranslit2>();
pt = new ArrayList<PhonemTranslit>();
mt = new ArrayList<MorphoSyntTranslit>();
mg = new ArrayList<MorphoSyntGlossing>();
mtr = new ArrayList<FinalTranslation>();
translationNodes = new ArrayList<TranslationNode>();
blockAnn = new ArrayList<BlockAnnotation>();

mdocr = new HashMap<String, Integer>();
mhb = new HashMap<String, Integer>();
mnt1 = new HashMap<String, Integer>();
mnt2 = new HashMap<String, Integer>();
mgt1 = new HashMap<String, Integer>();
mgt2 = new HashMap<String, Integer>();
mpt = new HashMap<String, Integer>();
mmt = new HashMap<String, Integer>();
mmg = new HashMap<String, Integer>();
mmtr = new HashMap<String, Integer>();
mtranslationNodes = new HashMap<String, Integer>();
mbla = new HashMap<String, Integer>();

if (importedReadings.isEmpty()) {
return;
}

for (AllmahRdfImporter.ImportedDocumentReading importedReading
    : importedReadings) {

glyphByUri.clear();
nt1ElementByUri.clear();
nt2ElementByUri.clear();
gt1ElementByUri.clear();

DocumentReading dr =
        new DocumentReading(doc.getId(), this);

dr.setInterpretationConfidence(importedReading.confidence);

/*
 * Preserve the RDF reading ID if possible.
 * This is important for R0/R1/R2 roundtrips.
 */
if (importedReading.internalId != null
        && !importedReading.internalId.isEmpty()) {

    setPrivateField(
            dr,
            "id",
            importedReading.internalId
    );
}

docr.add(dr);

mdocr.put(
        dr.getId(),
        Integer.valueOf(docr.size() - 1)
);

doc.getDocReadings().add(
        dr.getId()
);

for (AllmahRdfImporter.ImportedBlock importedBlock
        : importedReading.blocks) {

    /*
     * RDF blocks have two labels:
     * - rdfs:label is the display label, e.g. "A1 229.[617:126]"
     * - allmah:blockExpression is the parser input, e.g. "229.[617:126]"
     * Never feed the display label into createGraphStructure().
     */
    String blockExpression = importedBlock.blockExpression;

    if (blockExpression == null || blockExpression.trim().isEmpty()) {
        blockExpression = importedBlock.label;
    }

    if (blockExpression == null || blockExpression.trim().isEmpty()) {
        blockExpression = importedBlock.blockLabel;
    }

    blockExpression = stripDisplayCoordinateFromBlockExpression(
            blockExpression,
            importedBlock
    );

    String blockLocalId =
            importedBlock.teiXmlId;

    if (blockLocalId == null || blockLocalId.isEmpty()) {
        blockLocalId = importedBlock.internalId;
    }

    if (blockLocalId != null && blockLocalId.contains(">")) {
        blockLocalId =
                blockLocalId.substring(
                        blockLocalId.lastIndexOf(">") + 1
                );
    }

    HieroglyphenBlock block =
            new HieroglyphenBlock(
                    dr.getId(),
                    blockExpression,
                    blockLocalId,
                    true,
                    this
            );

    Map<String, ArrayList<Reading>> elements =
            new HashMap<String, ArrayList<Reading>>();

    Map<String, ArrayList<String>> choiceList =
            new HashMap<String, ArrayList<String>>();

    ArrayList<String> guiGlyphTokens =
            extractGlyphTokensFromBlockExpression(blockExpression);

    for (AllmahRdfImporter.ImportedGlyph importedGlyph
            : importedBlock.glyphs) {

        ArrayList<Reading> readings =
                new ArrayList<Reading>();

        for (AllmahRdfImporter.ImportedReading importedGlyphReading
                : importedGlyph.readings) {

            Reading importedReadingValue =
                    new Reading(
                            importedGlyphReading.label,
                            importedGlyphReading.confidence,
                            importedGlyphReading.readingType,
                            true
                    );
            importedReadingValue.setSourceUri(importedGlyphReading.sourceUri);
            readings.add(importedReadingValue);
        }

        String rdfGlyphKey =
                cleanGlyphKey(importedGlyph.label);

        elements.put(
                rdfGlyphKey,
                readings
        );

        /*
         * Do not use RDF statement position here. In multi-glyph blocks Jena
         * may return hasGlyphElement triples in an order different from the
         * original block expression.  Map imported catalogue readings to every
         * GUI token with the same catalogue number instead, e.g. 585 -> 585st.
         */
        for (String guiToken : guiGlyphTokens) {
            String guiGlyphKey = cleanGlyphKey(guiToken);

            if (guiGlyphKey != null && !guiGlyphKey.isEmpty()
                    && !guiGlyphKey.equals(rdfGlyphKey)
                    && sameBaseGlyph(guiGlyphKey, rdfGlyphKey)) {
                elements.put(guiGlyphKey, readings);
            }
        }
    }

    block.createGraphStructure(
            blockExpression,
            elements,
            choiceList
    );

    assignImportedGlyphUrisByPosition(block, importedBlock);

    Map<String, JLabel> pictures =
            buildPicturesFromImportedBlock(block, importedBlock);

    block.calculateImage(pictures);

    /*
     * Map RDF glyph URI to the concrete GUI glyph node by catalogue label,
     * not by list position.  RDF glyphs are global resources and do not carry
     * a reliable block-local orderIndex; Jena may return them in arbitrary
     * order.  A positional map can therefore make e.g. ex:glyph-24 point to
     * the GUI node for 585, which later makes components generate GT1 labels
     * from the wrong readings.
     */
    Map<String, GlyphElementNode> guiGlyphByCatalogKey =
            new HashMap<String, GlyphElementNode>();

    for (GlyphElementNode glyph : block.getElements()) {
        if (glyph == null) {
            continue;
        }

        String key = canonicalGlyphCatalogKey(glyph.getLabel());
        if (key != null && !key.isEmpty()) {
            guiGlyphByCatalogKey.put(key, glyph);
        }
    }

    for (AllmahRdfImporter.ImportedGlyph importedGlyph
            : importedBlock.glyphs) {

        if (importedGlyph == null || importedGlyph.uri == null) {
            continue;
        }

        String key = canonicalGlyphCatalogKey(importedGlyph.label);
        GlyphElementNode glyph = guiGlyphByCatalogKey.get(key);

        if (glyph != null) {
            glyphByUri.put(importedGlyph.uri, glyph);
        }
    }

    hb.add(block);

    int blockIndex =
            hb.size() - 1;

    mhb.put(
            block.getBlockID(),
            Integer.valueOf(blockIndex)
    );

    if (importedBlock.internalId != null
            && !importedBlock.internalId.isEmpty()) {

        mhb.put(
                importedBlock.internalId,
                Integer.valueOf(blockIndex)
        );
    }

    dr.getBlocks().add(
            block.getBlockID()
    );

    if (importedBlock.annotations != null) {
        for (AllmahRdfImporter.ImportedBlockAnnotation importedAnnotation
                : importedBlock.annotations) {
            addImportedBlockAnnotationToGui(importedAnnotation, block);
        }
    }

    for (AllmahRdfImporter.ImportedNT1 importedNT1
            : importedBlock.nt1) {

        NumTranslit1 n1 =
                new NumTranslit1(
                        importedNT1.internalId,
                        this
                );
        n1.setInterpretationConfidence(importedNT1.confidence);

        nt1.add(n1);

        mnt1.put(
                n1.getId(),
                Integer.valueOf(nt1.size() - 1)
        );

        block.getNumTranslit1().add(
                n1.getId()
        );

        String nt1Parent =
                getParentId(importedNT1.internalId);

        if (nt1Parent != null) {
            mhb.put(
                    nt1Parent,
                    Integer.valueOf(blockIndex)
            );
        }

        for (AllmahRdfImporter.ImportedElement importedElement
                : importedNT1.elements) {

            GlyphElementNode parentGlyph =
                    glyphByUri.get(importedElement.parentUri);

            if (parentGlyph == null
                    && !block.getElements().isEmpty()) {
                parentGlyph =
                        block.getElements().get(0);
            }

            NT1Element e1 =
                    new NT1Element(parentGlyph);

            e1.setLabel(importedElement.label);

            n1.getElements().add(e1);

            if (parentGlyph != null) {
                parentGlyph.getNT1Elements().add(e1);
            }

            nt1ElementByUri.put(
                    importedElement.uri,
                    e1
            );
        }

        for (AllmahRdfImporter.ImportedNT2 importedNT2
                : importedNT1.nt2) {

            NumTranslit2 n2 =
                    new NumTranslit2(
                            importedNT2.internalId,
                            this,
                            "main"
                    );
            n2.setInterpretationConfidence(importedNT2.confidence);

            nt2.add(n2);

            mnt2.put(
                    n2.getId(),
                    Integer.valueOf(nt2.size() - 1)
            );

            n1.getNT2().add(
                    n2.getId()
            );

            for (AllmahRdfImporter.ImportedElement importedElement
                    : importedNT2.elements) {

                NT1Element parentNT1 =
                        nt1ElementByUri.get(
                                importedElement.parentUri
                        );

                if (parentNT1 == null
                        && !n1.getElements().isEmpty()) {
                    parentNT1 =
                            n1.getElements().get(0);
                }

                NT2Element e2 =
                        new NT2Element(parentNT1);

                e2.setLabel(importedElement.label);

                /*
                 * RDF stores document-specific selected/assigned readings on
                 * NumericElement.  Keep them on the NT2Element in the GUI model
                 * as well.  Do not overwrite the parent NT1Element here: the
                 * same NT1 glyph can occur in several NT2 variants, and using
                 * NT1 as storage makes later component generation pick stale or
                 * numeric fallback readings.
                 */
                if (importedElement.assignedReadings != null
                        && !importedElement.assignedReadings.isEmpty()) {

                    ArrayList<Reading> assignedReadings =
                            importedReadingsToGuiReadings(
                                    importedElement.assignedReadings
                            );

                    if (!assignedReadings.isEmpty()) {
                        e2.setAssignedReadings(assignedReadings);
                    }
                }

                if (importedElement.component != null) {
                    ComponentElement component =
                            getOrCreateImportedComponent(
                                    importedElement.component,
                                    componentByUri
                            );
                    if (component != null) {
                        e2.setComplexGlyph(component);
                    }
                }

                n2.getElements().add(e2);

                nt2ElementByUri.put(
                        importedElement.uri,
                        e2
                );
            }

            for (AllmahRdfImporter.ImportedGT1 importedGT1
                    : importedNT2.gt1) {

                GraphTranslit1 g1 =
                        new GraphTranslit1(
                                importedGT1.internalId,
                                this
                        );
                g1.setInterpretationConfidence(importedGT1.confidence);

                gt1.add(g1);

                mgt1.put(
                        g1.getId(),
                        Integer.valueOf(gt1.size() - 1)
                );

                n2.getGT1().add(
                        g1.getId()
                );

                for (AllmahRdfImporter.ImportedElement importedElement
                        : importedGT1.elements) {

                    NT2Element parentNT2 =
                            nt2ElementByUri.get(
                                    importedElement.parentUri
                            );

                    if (parentNT2 == null
                            && !n2.getElements().isEmpty()) {
                        parentNT2 =
                                n2.getElements().get(0);
                    }

                    if (importedElement.component != null
                            && parentNT2 != null
                            && parentNT2.getComponentElement() == null) {
                        ComponentElement component =
                                getOrCreateImportedComponent(
                                        importedElement.component,
                                        componentByUri
                                );
                        if (component != null) {
                            parentNT2.setComplexGlyph(component);
                        }
                    }

                    Reading r =
                            new Reading(importedElement.label);

                    GT1Element e3 =
                            new GT1Element(parentNT2, r);

                    e3.setLabel(importedElement.label);

                    g1.getElements().add(e3);

                    gt1ElementByUri.put(
                            importedElement.uri,
                            e3
                    );
                }

                /*
                 * Existing workflow:
                 * initialize GT2 automatically, then replace the generated
                 * GT2 content with the RDF GT2 content.
                 */
                g1.createTranslit2();

                if (!g1.getGraphTranslit2().isEmpty()
                        && !importedGT1.gt2.isEmpty()) {

                    String autoGt2Id =
                            g1.getGraphTranslit2().get(0);

                    Integer autoIndex =
                            mgt2.get(autoGt2Id);

                    if (autoIndex != null) {

                        GraphTranslit2 g2 =
                                gt2.get(autoIndex.intValue());

                        AllmahRdfImporter.ImportedGT2 importedGT2 =
                                importedGT1.gt2.get(0);

                        g2.setId(importedGT2.internalId);
                        g2.setInterpretationConfidence(importedGT2.confidence);
                        g2.setFinish(importedGT2.frozen);

                        mgt2.remove(autoGt2Id);

                        mgt2.put(
                                g2.getId(),
                                autoIndex
                        );

                        g1.getGraphTranslit2().set(
                                0,
                                g2.getId()
                        );

                        ArrayList<GraphGT2Node> importedGT2Nodes =
                                new ArrayList<GraphGT2Node>();

                        DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> importedGT2Graph =
                                new DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink>();

                        HashMap<String, GraphGT2Node> importedGT2NodeByUri =
                                new HashMap<String, GraphGT2Node>();

                        GraphGT2Node previousGT2Node = null;

                        for (AllmahRdfImporter.ImportedElement importedElement
                                : importedGT2.elements) {

                            GT1Element parentGT1 =
                                    gt1ElementByUri.get(
                                            importedElement.parentUri
                                    );

                            if (parentGT1 == null
                                    && !g1.getElements().isEmpty()) {
                                parentGT1 =
                                        g1.getElements().get(0);
                            }

                            GT2Element e4 = new GT2Element(parentGT1);
                            e4.setLabel(stripImportedHtml(importedElement.label));

                            GraphGT2Node graphNode =
                                    new GraphGT2Node(e4, null);

                            importedGT2Nodes.add(graphNode);
                            importedGT2Graph.addVertex(graphNode);
                            importedGT2NodeByUri.put(importedElement.uri, graphNode);

                            if (previousGT2Node != null
                                    && importedGT2.relations.isEmpty()) {
                                importedGT2Graph.addEdge(
                                        new OperatorLink(" ", 5),
                                        previousGT2Node,
                                        graphNode
                                );
                            }

                            previousGT2Node = graphNode;
                        }

                        addImportedGT2Relations(
                                importedGT2,
                                importedGT2Graph,
                                importedGT2NodeByUri
                        );

                        g2.setElements(importedGT2Nodes);
                        g2.setGraph(importedGT2Graph);
                        g2.setLabel(g2.calculateLabel());
                        addImportedPhonemicTranscriptions(
                                importedGT2,
                                g2,
                                importedGT2NodeByUri
                        );
                    }

                    for (int importedGT2Index = 1;
                            importedGT2Index < importedGT1.gt2.size();
                            importedGT2Index++) {

                        AllmahRdfImporter.ImportedGT2 importedGT2 =
                                importedGT1.gt2.get(importedGT2Index);

                        GraphTranslit2 extraGT2 =
                                new GraphTranslit2(
                                        importedGT2.internalId,
                                        "var",
                                        this
                                );
                        extraGT2.setInterpretationConfidence(importedGT2.confidence);
                        extraGT2.setFinish(importedGT2.frozen);

                        ArrayList<GraphGT2Node> importedGT2Nodes =
                                new ArrayList<GraphGT2Node>();

                        DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> importedGT2Graph =
                                new DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink>();

                        HashMap<String, GraphGT2Node> importedGT2NodeByUri =
                                new HashMap<String, GraphGT2Node>();

                        GraphGT2Node previousGT2Node = null;

                        for (AllmahRdfImporter.ImportedElement importedElement
                                : importedGT2.elements) {

                            GT1Element parentGT1 =
                                    gt1ElementByUri.get(
                                            importedElement.parentUri
                                    );

                            if (parentGT1 == null
                                    && !g1.getElements().isEmpty()) {
                                parentGT1 =
                                        g1.getElements().get(0);
                            }

                            GT2Element e4 = new GT2Element(parentGT1);
                            e4.setLabel(stripImportedHtml(importedElement.label));

                            GraphGT2Node graphNode =
                                    new GraphGT2Node(e4, null);

                            importedGT2Nodes.add(graphNode);
                            importedGT2Graph.addVertex(graphNode);
                            importedGT2NodeByUri.put(importedElement.uri, graphNode);

                            if (previousGT2Node != null
                                    && importedGT2.relations.isEmpty()) {
                                importedGT2Graph.addEdge(
                                        new OperatorLink(" ", 5),
                                        previousGT2Node,
                                        graphNode
                                );
                            }

                            previousGT2Node = graphNode;
                        }

                        addImportedGT2Relations(
                                importedGT2,
                                importedGT2Graph,
                                importedGT2NodeByUri
                        );

                        extraGT2.setElements(importedGT2Nodes);
                        extraGT2.setGraph(importedGT2Graph);
                        extraGT2.setLabel(extraGT2.calculateLabel());
                        addImportedPhonemicTranscriptions(
                                importedGT2,
                                extraGT2,
                                importedGT2NodeByUri
                        );

                        gt2.add(extraGT2);
                        mgt2.put(
                                extraGT2.getId(),
                                Integer.valueOf(gt2.size() - 1)
                        );
                        g1.getGraphTranslit2().add(extraGT2.getId());
                    }
                }
            }
        }
    }
}


    }
    }
    private void addImportedPhonemicTranscriptions(
            AllmahRdfImporter.ImportedGT2 importedGT2,
            GraphTranslit2 parentGT2,
            HashMap<String, GraphGT2Node> gt2NodeByUri
    ) {
        if (importedGT2 == null
                || importedGT2.phonemic == null
                || importedGT2.phonemic.isEmpty()
                || parentGT2 == null
                || gt2NodeByUri == null) {
            return;
        }

        for (AllmahRdfImporter.ImportedPhonemicTranscription importedPhonemic
                : importedGT2.phonemic) {

            PhonemTranslit phonemic =
                    new PhonemTranslit(
                            importedPhonemic.internalId,
                            "main",
                            this
                    );
            phonemic.setInterpretationConfidence(importedPhonemic.confidence);
            phonemic.setFinish(importedPhonemic.frozen);

            ArrayList<PhonemNode> phonemicNodes =
                    new ArrayList<PhonemNode>();
            DirectedOrderedSparseMultigraph<PhonemNode,OperatorLink> phonemicGraph =
                    new DirectedOrderedSparseMultigraph<PhonemNode,OperatorLink>();
            HashMap<String, PhonemNode> phonemicNodeByUri =
                    new HashMap<String, PhonemNode>();

            PhonemNode previousNode = null;
            for (AllmahRdfImporter.ImportedElement importedElement
                    : importedPhonemic.elements) {

                GraphGT2Node parentGT2Node =
                        gt2NodeByUri.get(importedElement.parentUri);

                if (parentGT2Node == null
                        && parentGT2.getElements() != null
                        && !parentGT2.getElements().isEmpty()) {
                    parentGT2Node = parentGT2.getElements().get(0);
                }

                if (parentGT2Node == null) {
                    continue;
                }

                PhonemNode phonemNode = new PhonemNode(parentGT2Node);
                phonemNode.setLabel(stripImportedHtml(importedElement.label));

                phonemicNodes.add(phonemNode);
                phonemicGraph.addVertex(phonemNode);
                phonemicNodeByUri.put(importedElement.uri, phonemNode);

                if (previousNode != null
                        && (importedPhonemic.relations == null
                        || importedPhonemic.relations.isEmpty())) {
                    phonemicGraph.addEdge(
                            new OperatorLink("E", 6),
                            previousNode,
                            phonemNode
                    );
                }

                previousNode = phonemNode;
            }

            addImportedPhonemicRelations(
                    importedPhonemic,
                    phonemicGraph,
                    phonemicNodeByUri
            );

            phonemic.setElements(phonemicNodes);
            phonemic.setGraph(phonemicGraph);
            phonemic.setLabel(phonemic.calculateLabel());
            addImportedMorphosyntacticTranscriptions(
                    importedPhonemic,
                    phonemic,
                    phonemicNodeByUri
            );

            pt.add(phonemic);
            mpt.put(
                    phonemic.getId(),
                    Integer.valueOf(pt.size() - 1)
            );
            parentGT2.getPhonemicId().add(phonemic.getId());
        }
    }

    private void addImportedPhonemicRelations(
            AllmahRdfImporter.ImportedPhonemicTranscription importedPhonemic,
            DirectedOrderedSparseMultigraph<PhonemNode,OperatorLink> graph,
            HashMap<String, PhonemNode> nodeByUri
    ) {
        if (importedPhonemic == null
                || importedPhonemic.relations == null
                || importedPhonemic.relations.isEmpty()
                || graph == null
                || nodeByUri == null) {
            return;
        }

        for (AllmahRdfImporter.ImportedRelation relation
                : importedPhonemic.relations) {

            PhonemNode source = nodeByUri.get(relation.sourceUri);
            PhonemNode target = nodeByUri.get(relation.targetUri);

            if (source == null || target == null) {
                continue;
            }

            graph.addEdge(
                    new OperatorLink(
                            operatorSymbolFromUri(relation.operatorUri),
                            6
                    ),
                    source,
                    target
            );
        }
    }

    private void addImportedMorphosyntacticTranscriptions(
            AllmahRdfImporter.ImportedPhonemicTranscription importedPhonemic,
            PhonemTranslit phonemic,
            HashMap<String, PhonemNode> phonemicNodeByUri
    ) {
        if (importedPhonemic == null
                || importedPhonemic.morphosyntactic == null
                || importedPhonemic.morphosyntactic.isEmpty()
                || phonemic == null) {
            return;
        }

        if (mt == null) {
            mt = new ArrayList<MorphoSyntTranslit>();
        }
        if (mmt == null) {
            mmt = new HashMap<String,Integer>();
        }
        if (mg == null) {
            mg = new ArrayList<MorphoSyntGlossing>();
        }
        if (mmg == null) {
            mmg = new HashMap<String,Integer>();
        }

        int importedIndex = 0;
        for (AllmahRdfImporter.ImportedMorphosyntacticTranscription importedMorpho
                : importedPhonemic.morphosyntactic) {

            if (importedMorpho == null) {
                continue;
            }

            String morphoId = importedMorpho.internalId;
            if (morphoId == null || morphoId.trim().isEmpty()) {
                morphoId = phonemic.getId() + ">MT_" + importedIndex;
            }

            MorphoTranscrNode rootNode = new MorphoTranscrNode(phonemic);
            rootNode.clearChildren();
            if (importedMorpho.label != null && !importedMorpho.label.trim().isEmpty()) {
                rootNode.setLabel(stripImportedHtml(importedMorpho.label));
            }

            HashMap<String, MorphoTranscrNode> morphoNodeByUri =
                    new HashMap<String, MorphoTranscrNode>();

            if (importedMorpho.elements != null) {
                for (AllmahRdfImporter.ImportedMorphoNode importedNode
                        : importedMorpho.elements) {
                    MorphoTranscrNode node = buildImportedMorphoNode(
                            importedNode,
                            rootNode,
                            phonemicNodeByUri,
                            morphoNodeByUri
                    );
                    if (node != null) {
                        node.setParent(rootNode);
                        rootNode.getChildren().add(node);
                    }
                }
            }

            applyImportedMorphoOldParents(
                    importedMorpho.elements,
                    morphoNodeByUri
            );

            SubElementNode rootTreeNode = new SubElementNode(rootNode);
            rootTreeNode.explore();
            JTree importedTree = new JTree(rootTreeNode);

            MorphoSyntTranslit morpho = new MorphoSyntTranslit(
                    morphoId,
                    "main",
                    importedTree,
                    this
            );

            if (importedMorpho.label != null && !importedMorpho.label.trim().isEmpty()) {
                morpho.setLabel(stripImportedHtml(importedMorpho.label));
            }
            if (importedMorpho.consolidatedLabel != null
                    && !importedMorpho.consolidatedLabel.trim().isEmpty()) {
                morpho.setKonsolid(stripImportedHtml(importedMorpho.consolidatedLabel));
            }
            if (importedMorpho.finalConsolidatedLabel != null
                    && !importedMorpho.finalConsolidatedLabel.trim().isEmpty()) {
                morpho.setFinalKonsolid(stripImportedHtml(importedMorpho.finalConsolidatedLabel));
            }

            morpho.getElem().clear();
            for (int i = 0; i < rootNode.getChildren().size(); i++) {
                morpho.getElem().add(rootNode.getChildren().get(i));
            }

            mt.add(morpho);
            mmt.put(morpho.getId(), Integer.valueOf(mt.size() - 1));
            if (!phonemic.getMorphoTranscr().contains(morpho.getId())) {
                phonemic.getMorphoTranscr().add(morpho.getId());
            }

            addImportedMorphosyntacticGlossings(
                    importedMorpho,
                    morpho,
                    morphoNodeByUri
            );

            importedIndex++;
        }
    }


    private void addImportedMorphosyntacticGlossings(
            AllmahRdfImporter.ImportedMorphosyntacticTranscription importedMorpho,
            MorphoSyntTranslit morpho,
            HashMap<String, MorphoTranscrNode> morphoNodeByUri
    ) {
        if (importedMorpho == null
                || importedMorpho.glossings == null
                || importedMorpho.glossings.isEmpty()
                || morpho == null
                || morphoNodeByUri == null) {
            return;
        }

        for (AllmahRdfImporter.ImportedMorphosyntacticGlossing importedGlossing
                : importedMorpho.glossings) {
            if (importedGlossing == null) {
                continue;
            }

            String glossId = importedGlossing.internalId;
            if (glossId == null || glossId.trim().isEmpty()) {
                glossId = morpho.getId() + ">MSG_" + morpho.getMorphoGloss().size();
            }

            MorphoSyntGlossing glossing = new MorphoSyntGlossing(glossId, this);

            if (importedGlossing.elements != null) {
                for (AllmahRdfImporter.ImportedGlossingElement importedElement
                        : importedGlossing.elements) {
                    if (importedElement == null) {
                        continue;
                    }

                    MorphoTranscrNode parent = morphoNodeByUri.get(importedElement.parentUri);
                    if (parent == null) {
                        continue;
                    }

                    String semanticValue = importedElement.semanticGloss;
                    if (semanticValue == null || semanticValue.trim().isEmpty()) {
                        semanticValue = importedElement.literalValue;
                    }
                    if (semanticValue == null) {
                        semanticValue = "";
                    }

                    SemanticAnnotation sem = new SemanticAnnotation(stripImportedHtml(semanticValue));
                    if (importedElement.wordNetSynsetId != null
                            && !importedElement.wordNetSynsetId.trim().isEmpty()) {
                        sem.setLinkWN(stripImportedHtml(importedElement.wordNetSynsetId));
                    }

                    String syntaxValue = stripImportedHtml(importedElement.syntacticGloss);
                    String posCluster = stripImportedHtml(importedElement.posCluster);
                    String posTag = stripImportedHtml(importedElement.posTag);

                    if (syntaxValue != null && !syntaxValue.trim().isEmpty()) {
                        SyntacticFeature importedFeature =
                                resolveImportedSyntacticFeature(
                                        posCluster,
                                        posTag,
                                        syntaxValue
                                );

                        SyntacticAnnotation syn = new SyntacticAnnotation(
                                posCluster,
                                posTag,
                                importedFeature
                        );

                        glossing.getElements().add(
                                new GlossingVariant(parent, syn, sem)
                        );
                    }
                    else {
                        glossing.getElements().add(
                                new GlossingVariant(parent, sem)
                        );
                    }
                }
            }

            mg.add(glossing);
            mmg.put(glossing.getId(), Integer.valueOf(mg.size() - 1));
            if (!morpho.getMorphoGloss().contains(glossing.getId())) {
                morpho.getMorphoGloss().add(glossing.getId());
            }

            addImportedTranslationNodesToGui(importedGlossing, glossing);
        }
    }


    private SyntacticFeature resolveImportedSyntacticFeature(
            String posCluster,
            String posTag,
            String syntaxValue
    ) {
        String cluster = posCluster == null ? "" : posCluster.trim();
        String tag = posTag == null ? "" : posTag.trim();
        String value = syntaxValue == null ? "" : syntaxValue.trim();

        SyntacsValues sv = new SyntacsValues();
        ArrayList<Map<String, SyntacticFeature>> maps =
                new ArrayList<Map<String, SyntacticFeature>>();

        if (cluster.indexOf("Absolutive") >= 0) maps.add(sv.ABSOLUTIVE_CASE);
        else if (cluster.indexOf("Ergative") >= 0) maps.add(sv.ERGATIVE_CASE);
        else if (cluster.indexOf("Demonstrative") >= 0) maps.add(sv.INDEPENDENT_OR_DEMONSTRATIVE_PRONOUN);
        else if (cluster.indexOf("Thematic") >= 0) maps.add(sv.THEMATIC_SUFFIXES);
        else if (cluster.indexOf("Verbal Clitics") >= 0) maps.add(sv.VERBAL_CLITICS);
        else if (cluster.indexOf("Other Particle") >= 0) maps.add(sv.PARTICLE);
        else if (cluster.indexOf("Valency") >= 0) maps.add(sv.VALENCY_DECREASING_INCREASING_SUFFIXES);
        else if (cluster.indexOf("Verb Lexical") >= 0) maps.add(sv.VERB_LEXICAL);
        else if (cluster.indexOf("Verbal Derivation") >= 0) maps.add(sv.VERBAL_DERIVATION);
        else if (cluster.indexOf("Adjective Lexical") >= 0) maps.add(sv.ADJECTIVE_LEXICAL);
        else if (cluster.indexOf("Adjectival Derivation") >= 0) maps.add(sv.ADJECTIVAL_DERIVATION);
        else if (cluster.indexOf("Adverb Lexical") >= 0) maps.add(sv.ADVERBS_LEXICAL);
        else if (cluster.indexOf("Adverb Bound") >= 0) maps.add(sv.ADVERBS_BOUND);
        else if (cluster.indexOf("Numeral Lexical") >= 0) maps.add(sv.NUMERAL_LEXICAL);
        else if (cluster.indexOf("Numeral Bound") >= 0) maps.add(sv.NUMERALS_BOUND);
        else if (cluster.indexOf("Noun Lexical") >= 0) maps.add(sv.NOUN_LEXICAL);
        else if (cluster.indexOf("Noun Inflection") >= 0) maps.add(sv.NOUN_INFLECTION);
        else if (cluster.indexOf("Noun Derivation") >= 0) maps.add(sv.NOUN_DERIVATION);
        else if (cluster.indexOf("Preposition") >= 0) maps.add(sv.PREPOSITION);

        if (maps.isEmpty()) {
            if (tag.indexOf("Particle") >= 0) maps.add(sv.PARTICLE);
            if (tag.indexOf("Noun") >= 0) {
                maps.add(sv.NOUN_LEXICAL);
                maps.add(sv.NOUN_INFLECTION);
                maps.add(sv.NOUN_DERIVATION);
            }
            if (tag.indexOf("Person Marker") >= 0 || tag.indexOf("Pronouns") >= 0) {
                maps.add(sv.ABSOLUTIVE_CASE);
                maps.add(sv.ERGATIVE_CASE);
                maps.add(sv.INDEPENDENT_OR_DEMONSTRATIVE_PRONOUN);
            }
            if (tag.indexOf("POS") >= 0 || tag.indexOf("Preposition") >= 0) maps.add(sv.PREPOSITION);
        }

        if (maps.isEmpty()) {
            maps.add(sv.ABSOLUTIVE_CASE);
            maps.add(sv.ERGATIVE_CASE);
            maps.add(sv.INDEPENDENT_OR_DEMONSTRATIVE_PRONOUN);
            maps.add(sv.NOUN_LEXICAL);
            maps.add(sv.NOUN_INFLECTION);
            maps.add(sv.NOUN_DERIVATION);
            maps.add(sv.PARTICLE);
            maps.add(sv.PREPOSITION);
            maps.add(sv.THEMATIC_SUFFIXES);
            maps.add(sv.VERBAL_CLITICS);
            maps.add(sv.VALENCY_DECREASING_INCREASING_SUFFIXES);
            maps.add(sv.VERB_LEXICAL);
            maps.add(sv.VERBAL_DERIVATION);
            maps.add(sv.ADJECTIVE_LEXICAL);
            maps.add(sv.ADJECTIVAL_DERIVATION);
            maps.add(sv.ADVERBS_LEXICAL);
            maps.add(sv.ADVERBS_BOUND);
            maps.add(sv.NUMERAL_LEXICAL);
            maps.add(sv.NUMERALS_BOUND);
        }

        for (Map<String, SyntacticFeature> map : maps) {
            SyntacticFeature byKey = map.get(value);
            if (byKey != null) {
                return byKey;
            }

            for (SyntacticFeature feature : map.values()) {
                if (feature != null
                        && feature.getAbbreviation() != null
                        && feature.getAbbreviation().equals(value)) {
                    return feature;
                }
            }
        }

        ArrayList<FeaturePair> emptyFeatures = new ArrayList<FeaturePair>();
        return new SyntacticFeature(
                value,
                emptyFeatures,
                new ExplanationPair("")
        );
    }

    private MorphoTranscrNode buildImportedMorphoNode(
            AllmahRdfImporter.ImportedMorphoNode importedNode,
            MorphoTranscrNode parentNode,
            HashMap<String, PhonemNode> phonemicNodeByUri,
            HashMap<String, MorphoTranscrNode> morphoNodeByUri
    ) {
        if (importedNode == null || parentNode == null) {
            return null;
        }

        PhonemNode phonemParent = null;
        if (phonemicNodeByUri != null) {
            phonemParent = phonemicNodeByUri.get(importedNode.parentUri);
            if (phonemParent == null) {
                phonemParent = phonemicNodeByUri.get(importedNode.oldParentUri);
            }
            if (phonemParent == null) {
                phonemParent = phonemicNodeByUri.get(importedNode.newParentUri);
            }
        }

        MorphoTranscrNode node;
        if (phonemParent != null && parentNode.getParent() == null) {
            node = new MorphoTranscrNode(phonemParent, parentNode);
            node.clearChildren();
        }
        else {
            node = new MorphoTranscrNode(
                    importedMorphoNodeRawLabel(importedNode),
                    parentNode,
                    importedNode.level
            );
            node.clearChildren();
        }

        node.setParent(parentNode);
        if (phonemParent != null) {
            node.setPhonemParent(phonemParent);
        }
        node.setLabel(importedMorphoNodeRawLabel(importedNode));
        node.setLevel(importedNode.level);
        node.setDel(importedNode.removed);
        node.setGeschw(importedNode.curly);
        node.setEckig(importedNode.square);
        node.setBar(importedNode.pipe);
        node.setAbstract(importedNode.abstractNode);

        if (importedNode.uri != null && !importedNode.uri.trim().isEmpty()) {
            morphoNodeByUri.put(importedNode.uri, node);
        }

        if (importedNode.children != null) {
            for (AllmahRdfImporter.ImportedMorphoNode importedChild
                    : importedNode.children) {
                MorphoTranscrNode child = buildImportedMorphoNode(
                        importedChild,
                        node,
                        phonemicNodeByUri,
                        morphoNodeByUri
                );
                if (child != null) {
                    child.setParent(node);
                    node.getChildren().add(child);
                }
            }
        }

        return node;
    }

    private String importedMorphoNodeLabel(
            AllmahRdfImporter.ImportedMorphoNode importedNode
    ) {
        if (importedNode == null) {
            return "";
        }
        if (importedNode.literalValue != null
                && !importedNode.literalValue.trim().isEmpty()) {
            return stripImportedHtml(importedNode.literalValue);
        }
        if (importedNode.label != null
                && !importedNode.label.trim().isEmpty()) {
            return stripImportedHtml(importedNode.label);
        }
        return "";
    }


    private String importedMorphoNodeRawLabel(
            AllmahRdfImporter.ImportedMorphoNode importedNode
    ) {
        String label = importedMorphoNodeLabel(importedNode);
        if (label == null) {
            return "";
        }
        label = label.trim();

        boolean changedWrapper = true;
        while (changedWrapper) {
            changedWrapper = false;
            if (importedNode != null && importedNode.square
                    && label.length() >= 2
                    && label.startsWith("[") && label.endsWith("]")) {
                label = label.substring(1, label.length() - 1).trim();
                changedWrapper = true;
            }
            if (importedNode != null && importedNode.curly
                    && label.length() >= 2
                    && label.startsWith("{") && label.endsWith("}")) {
                label = label.substring(1, label.length() - 1).trim();
                changedWrapper = true;
            }
            if (importedNode != null && importedNode.pipe
                    && label.length() >= 2
                    && label.startsWith("|") && label.endsWith("|")) {
                label = label.substring(1, label.length() - 1).trim();
                changedWrapper = true;
            }
        }

        return label;
    }

    private void applyImportedMorphoOldParents(
            List<AllmahRdfImporter.ImportedMorphoNode> importedNodes,
            HashMap<String, MorphoTranscrNode> morphoNodeByUri
    ) {
        if (importedNodes == null || morphoNodeByUri == null) {
            return;
        }

        for (AllmahRdfImporter.ImportedMorphoNode importedNode : importedNodes) {
            if (importedNode == null) {
                continue;
            }

            MorphoTranscrNode node = morphoNodeByUri.get(importedNode.uri);
            if (node != null
                    && importedNode.oldParentUri != null
                    && !importedNode.oldParentUri.trim().isEmpty()) {
                MorphoTranscrNode oldParent = morphoNodeByUri.get(importedNode.oldParentUri);
                node.setOldParent(oldParent);
            }

            applyImportedMorphoOldParents(
                    importedNode.children,
                    morphoNodeByUri
            );
        }
    }



    /**
     * Build a detached editor tree for an existing morphosyntactic transcription.
     * The edit tree must use raw labels; bracket/brace/pipe rendering comes from
     * the node flags. Otherwise labels such as [g] with eckig=true are displayed
     * as [[g]].
     */
    private JTree createEditableMorphoSyntTree(MorphoSyntTranslit currentNode) {
        if (currentNode == null
                || currentNode.getTreeTranslit() == null
                || currentNode.getTreeTranslit().getModel() == null
                || currentNode.getTreeTranslit().getModel().getRoot() == null) {
            return null;
        }

        Object rootObject = currentNode.getTreeTranslit().getModel().getRoot();
        if (!(rootObject instanceof SubElementNode)) {
            return currentNode.getTreeTranslit();
        }

        LetterTreeNode rootLetter = ((SubElementNode) rootObject).getMyLetterTreeNode();
        if (!(rootLetter instanceof MorphoTranscrNode)) {
            return currentNode.getTreeTranslit();
        }

        Map<MorphoTranscrNode, MorphoTranscrNode> copiedNodes =
                new IdentityHashMap<MorphoTranscrNode, MorphoTranscrNode>();

        MorphoTranscrNode rootCopy = copyMorphoNodeForEditor(
                (MorphoTranscrNode) rootLetter,
                null,
                copiedNodes
        );

        repairCopiedOldParentsForEditor(
                (MorphoTranscrNode) rootLetter,
                copiedNodes
        );

        SubElementNode rootTreeNode = new SubElementNode(rootCopy);
        rootTreeNode.explore();
        return new JTree(rootTreeNode);
    }

    private MorphoTranscrNode copyMorphoNodeForEditor(
            MorphoTranscrNode source,
            MorphoTranscrNode parentCopy,
            Map<MorphoTranscrNode, MorphoTranscrNode> copiedNodes
    ) {
        if (source == null) {
            return null;
        }

        String rawLabel = rawMorphoNodeLabelForEditor(source);
        int level = source.getLevel();

        MorphoTranscrNode copy;
        if (parentCopy == null || level == 0) {
            copy = new MorphoTranscrNode(rawLabel, null, 0);
            copy.setLevel(level);
            copy.setParent(null);
        }
        else {
            copy = new MorphoTranscrNode(rawLabel, parentCopy, level);
            copy.setLevel(level);
            copy.setParent(parentCopy);
        }

        copy.clearChildren();
        copy.setLabel(rawLabel);
        copy.setDel(source.getDel());
        copy.setGeschw(source.getGeschw());
        copy.setEckig(source.getEckig());
        copy.setBar(source.getBar());
        copy.setAbstract(source.getAbstract());
        copy.setPhonemParent(source.getPhonemParent());

        copiedNodes.put(source, copy);

        ArrayList<MorphoTranscrNode> children = source.getChildren();
        if (children != null) {
            for (MorphoTranscrNode child : children) {
                MorphoTranscrNode childCopy = copyMorphoNodeForEditor(
                        child,
                        copy,
                        copiedNodes
                );
                if (childCopy != null) {
                    childCopy.setParent(copy);
                    copy.getChildren().add(childCopy);
                }
            }
        }

        return copy;
    }

    private void repairCopiedOldParentsForEditor(
            MorphoTranscrNode source,
            Map<MorphoTranscrNode, MorphoTranscrNode> copiedNodes
    ) {
        if (source == null || copiedNodes == null) {
            return;
        }

        MorphoTranscrNode copy = copiedNodes.get(source);
        if (copy != null) {
            MorphoTranscrNode sourceOldParent = getMorphoOldParent(source);
            if (sourceOldParent != null) {
                MorphoTranscrNode copiedOldParent = copiedNodes.get(sourceOldParent);
                if (copiedOldParent != null) {
                    copy.setOldParent(copiedOldParent);
                }
            }
        }

        if (source.getChildren() != null) {
            for (MorphoTranscrNode child : source.getChildren()) {
                repairCopiedOldParentsForEditor(child, copiedNodes);
            }
        }
    }

    private MorphoTranscrNode getMorphoOldParent(MorphoTranscrNode node) {
        if (node == null) {
            return null;
        }
        try {
            java.lang.reflect.Field field = MorphoTranscrNode.class.getDeclaredField("oldparent");
            field.setAccessible(true);
            Object value = field.get(node);
            if (value instanceof MorphoTranscrNode) {
                return (MorphoTranscrNode) value;
            }
        }
        catch (Exception ex) {
            // keep null
        }
        return null;
    }

    private String rawMorphoNodeLabelForEditor(MorphoTranscrNode node) {
        if (node == null) {
            return "";
        }

        String label = null;
        try {
            java.lang.reflect.Field field = MorphoTranscrNode.class.getDeclaredField("label");
            field.setAccessible(true);
            Object value = field.get(node);
            if (value != null) {
                label = value.toString();
            }
        }
        catch (Exception ex) {
            label = node.getLabel();
        }

        if (label == null) {
            label = "";
        }
        label = stripImportedHtml(label).trim();

        boolean changedWrapper = true;
        while (changedWrapper) {
            changedWrapper = false;
            if (node.getEckig() && label.length() >= 2
                    && label.startsWith("[") && label.endsWith("]")) {
                label = label.substring(1, label.length() - 1).trim();
                changedWrapper = true;
            }
            if (node.getGeschw() && label.length() >= 2
                    && label.startsWith("{") && label.endsWith("}")) {
                label = label.substring(1, label.length() - 1).trim();
                changedWrapper = true;
            }
            if (node.getBar() && label.length() >= 2
                    && label.startsWith("|") && label.endsWith("|")) {
                label = label.substring(1, label.length() - 1).trim();
                changedWrapper = true;
            }
        }

        return label;
    }

    private void addImportedGT2Relations(
            AllmahRdfImporter.ImportedGT2 importedGT2,
            DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> graph,
            HashMap<String, GraphGT2Node> nodeByUri
    ) {
        if (importedGT2 == null
                || importedGT2.relations == null
                || importedGT2.relations.isEmpty()
                || graph == null
                || nodeByUri == null) {
            return;
        }

        for (AllmahRdfImporter.ImportedRelation relation
                : importedGT2.relations) {

            GraphGT2Node source = nodeByUri.get(relation.sourceUri);
            GraphGT2Node target = nodeByUri.get(relation.targetUri);

            if (source == null || target == null) {
                continue;
            }

            graph.addEdge(
                    new OperatorLink(
                            operatorSymbolFromUri(relation.operatorUri),
                            5
                    ),
                    source,
                    target
            );
        }
    }




    private void addImportedBlockAnnotationToGui(
            AllmahRdfImporter.ImportedBlockAnnotation importedAnnotation,
            HieroglyphenBlock block
    ) {
        if (importedAnnotation == null || block == null) {
            return;
        }

        if (blockAnn == null) {
            blockAnn = new ArrayList<BlockAnnotation>();
        }
        if (mbla == null) {
            mbla = new HashMap<String,Integer>();
        }

        String annotationId = stripImportedHtml(importedAnnotation.internalId);
        if (annotationId == null || annotationId.trim().isEmpty()) {
            annotationId = stripImportedHtml(importedAnnotation.uri);
        }
        if (annotationId == null || annotationId.trim().isEmpty()) {
            return;
        }

        BlockAnnotation annotation = null;
        Integer existingIndex = mbla.get(annotationId);
        if (existingIndex != null
                && existingIndex.intValue() >= 0
                && existingIndex.intValue() < blockAnn.size()) {
            annotation = blockAnn.get(existingIndex.intValue());
        }
        else {
            String typ = stripImportedHtml(importedAnnotation.annotationType);
            if (typ == null || typ.trim().isEmpty()) {
                typ = "annotation";
            }
            annotation = new BlockAnnotation(doc.getId(), typ);
            annotation.setId(annotationId);
            annotation.setTyp(typ);
            annotation.setSubTyp(stripImportedHtml(importedAnnotation.annotationSubType));
            annotation.setComment(stripImportedHtml(importedAnnotation.comment));
            applyImportedAnnotationURI(annotation, importedAnnotation.annotationURI);

            blockAnn.add(annotation);
            mbla.put(annotation.getId(), Integer.valueOf(blockAnn.size() - 1));
        }

        if (!annotation.getAnnBorders().contains(block.getBlockID())) {
            annotation.getAnnBorders().add(block.getBlockID());
        }
        if (!block.getAnnotations().contains(annotation.getId())) {
            block.getAnnotations().add(annotation.getId());
        }
    }

    private void applyImportedAnnotationURI(
            BlockAnnotation annotation,
            AllmahRdfImporter.ImportedAnnotationURI importedURI
    ) {
        if (annotation == null || importedURI == null) {
            return;
        }

        String type = stripImportedHtml(importedURI.uriType);
        if (type == null) {
            type = "";
        }

        if (type.equals("Activity") || type.equals("ActivityURI")) {
            annotation.getActivity().setTitle(stripImportedHtml(importedURI.activityDescription));
            annotation.getActivity().setPlaceName(stripImportedHtml(importedURI.placeOfActivity));
        }
        else if (type.equals("Dedication") || type.equals("DedicationURI")) {
            annotation.getDedication().setTitle(stripImportedHtml(importedURI.dedicationTitle));
            annotation.getDedication().setPlaceName(stripImportedHtml(importedURI.dedicationPlace));
        }
        else if (type.equals("Place") || type.equals("PlaceURI")) {
            annotation.getPlace().setPlaceName(stripImportedHtml(importedURI.placeName));
            annotation.getPlace().setLocatedPlaceNames(stripImportedHtml(importedURI.locatedIn));
        }
        else if (type.equals("EpigraphicActor") || type.equals("EpigraphicActorURI")) {
            annotation.getEpigraphicActor().setActorName(stripImportedHtml(importedURI.personName));
            annotation.getEpigraphicActor().setDetails(stripImportedHtml(importedURI.gender));
        }
        else if (type.equals("EpigraphicGroup") || type.equals("EpigraphicGroupURI")) {
            annotation.getEpigraphicGroup().setGroupName(stripImportedHtml(importedURI.groupName));
        }
    }

    private void ensureTranslationNodeModel() {
        if (translationNodes == null) {
            translationNodes = new ArrayList<TranslationNode>();
        }
        if (mtranslationNodes == null) {
            mtranslationNodes = new HashMap<String,Integer>();
        }
    }

    private void addOrReplaceTranslationNode(TranslationNode node) {
        if (node == null || node.getId() == null) {
            return;
        }
        ensureTranslationNodeModel();
        Integer index = mtranslationNodes.get(node.getId());
        if (index == null) {
            translationNodes.add(node);
            mtranslationNodes.put(node.getId(), Integer.valueOf(translationNodes.size() - 1));
        }
        else {
            translationNodes.set(index.intValue(), node);
        }
    }

    private void addImportedTranslationNodesToGui(
            AllmahRdfImporter.ImportedMorphosyntacticGlossing importedGlossing,
            MorphoSyntGlossing glossing
    ) {
        if (importedGlossing == null
                || importedGlossing.translationNodes == null
                || importedGlossing.translationNodes.isEmpty()
                || glossing == null) {
            return;
        }

        ensureTranslationNodeModel();
        if (mtr == null) {
            mtr = new ArrayList<FinalTranslation>();
        }
        if (mmtr == null) {
            mmtr = new HashMap<String,Integer>();
        }

        for (AllmahRdfImporter.ImportedTranslationNode importedNode
                : importedGlossing.translationNodes) {
            if (importedNode == null) {
                continue;
            }

            String nodeId = importedNode.internalId;
            if (nodeId == null || nodeId.trim().isEmpty()) {
                nodeId = importedNode.uri;
            }
            if (nodeId == null || nodeId.trim().isEmpty()) {
                continue;
            }

            TranslationNode node = new TranslationNode(
                    nodeId,
                    stripImportedHtml(importedNode.originalText),
                    stripImportedHtml(importedNode.translationText)
            );
            node.setNodeKind(importedNode.nodeKind);
            node.setParentMgId(glossing.getId());
            node.setLabel(stripImportedHtml(importedNode.label));
            node.setElementLabel(stripImportedHtml(importedNode.elementLabel));
            node.setSemanticMeaning(stripImportedHtml(importedNode.semanticMeaning));
            node.setSyntacticFunction(stripImportedHtml(importedNode.syntacticFunction));
            node.setWordNetSynsetId(stripImportedHtml(importedNode.wordNetSynsetId));
            node.setExplanation(stripImportedHtml(importedNode.explanation));
            node.setDictionaryEntry(importedNode.dictionaryEntry);

            if (importedNode.partIds != null) {
                for (String partId : importedNode.partIds) {
                    if (partId != null && !partId.trim().isEmpty()) {
                        node.addPartId(partId);
                    }
                }
            }

            if ((node.getLabel() == null || node.getLabel().trim().isEmpty())
                    && ("ConsolidatedTranslation".equals(node.getNodeKind())
                    || "ComposedDictionaryEntry".equals(node.getNodeKind())
                    || "SimpleDictionaryEntry".equals(node.getNodeKind()))) {
                node.setLabel(
                        "Maya: " + safeString(node.getOriginal())
                        + " | Translation: " + safeString(node.getTranslation())
                );
            }

            addOrReplaceTranslationNode(node);

            if ("ConsolidatedTranslation".equals(node.getNodeKind())) {
                if (!mmtr.containsKey(node.getId())) {
                    FinalTranslation ftr = new FinalTranslation(
                            node.getId(),
                            node.getOriginal(),
                            node.getTranslation()
                    );
                    mtr.add(ftr);
                    mmtr.put(ftr.getId(), Integer.valueOf(mtr.size() - 1));
                }
            }
        }
    }

    private String safeString(String value) {
        return value == null ? "" : value;
    }

    private void appendTranslationNodesToTurtle(File turtleFile) throws IOException {
        ensureTranslationNodeModel();
        if (turtleFile == null || translationNodes.isEmpty()) {
            return;
        }
        FileOutputStream out = new FileOutputStream(turtleFile, true);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("\n\n# ALLMAH translation and dictionary background nodes\n");
            sb.append("@prefix allmahtr: <https://allmah.org/translation/> .\n");
            sb.append("@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .\n");
            sb.append("@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n\n");
            for (TranslationNode node : translationNodes) {
                if (node == null || node.getId() == null) {
                    continue;
                }
                String uri = translationNodeUri(node.getId());
                sb.append("<").append(uri).append(">\n");
                sb.append("    rdf:type allmahtr:").append(ttlName(node.getNodeKind())).append(" ;\n");
                sb.append("    allmahtr:internalId \"").append(ttlEscape(node.getId())).append("\" ;\n");
                sb.append("    allmahtr:parentMgId \"").append(ttlEscape(node.getParentMgId())).append("\" ;\n");
                sb.append("    allmahtr:original \"").append(ttlEscape(node.getOriginal())).append("\" ;\n");
                sb.append("    allmahtr:translation \"").append(ttlEscape(node.getTranslation())).append("\" ;\n");
                sb.append("    allmahtr:elementLabel \"").append(ttlEscape(node.getElementLabel())).append("\" ;\n");
                sb.append("    allmahtr:semanticMeaning \"").append(ttlEscape(node.getSemanticMeaning())).append("\" ;\n");
                sb.append("    allmahtr:syntacticFunction \"").append(ttlEscape(node.getSyntacticFunction())).append("\" ;\n");
                sb.append("    allmahtr:wordNetSynsetId \"").append(ttlEscape(node.getWordNetSynsetId())).append("\" ;\n");
                sb.append("    allmahtr:explanation \"").append(ttlEscape(node.getExplanation())).append("\" ;\n");
                sb.append("    allmahtr:dictionaryEntry \"").append(node.isDictionaryEntry()).append("\" ;\n");
                sb.append("    rdfs:label \"").append(ttlEscape(node.getLabel())).append("\"");
                ArrayList<String> partIds = node.getPartIds();
                if (partIds != null && !partIds.isEmpty()) {
                    for (String partId : partIds) {
                        if (partId != null && !partId.trim().isEmpty()) {
                            sb.append(" ;\n    allmahtr:hasPart <")
                              .append(translationNodeUri(partId))
                              .append(">");
                        }
                    }
                }
                sb.append(" .\n\n");
            }
            out.write(sb.toString().getBytes("UTF-8"));
        }
        finally {
            out.close();
        }
    }

    private void readTranslationNodesFromTurtle(File turtleFile) {
        ensureTranslationNodeModel();
        if (turtleFile == null || !turtleFile.exists()) {
            return;
        }
        try {
            String content = new String(java.nio.file.Files.readAllBytes(turtleFile.toPath()), "UTF-8");
            java.util.regex.Pattern nodePattern = java.util.regex.Pattern.compile(
                    "<urn:allmah:translation-node:[^>]+>\\s*(.*?)\\s*\\.",
                    java.util.regex.Pattern.DOTALL
            );
            java.util.regex.Matcher matcher = nodePattern.matcher(content);
            while (matcher.find()) {
                String block = matcher.group(1);
                String id = turtleLiteral(block, "internalId");
                if (id == null || id.trim().isEmpty()) {
                    continue;
                }
                TranslationNode node = new TranslationNode(
                        id,
                        turtleLiteral(block, "original"),
                        turtleLiteral(block, "translation")
                );
                node.setNodeKind(turtleType(block));
                node.setParentMgId(turtleLiteral(block, "parentMgId"));
                node.setElementLabel(turtleLiteral(block, "elementLabel"));
                node.setSemanticMeaning(turtleLiteral(block, "semanticMeaning"));
                node.setSyntacticFunction(turtleLiteral(block, "syntacticFunction"));
                node.setWordNetSynsetId(turtleLiteral(block, "wordNetSynsetId"));
                node.setExplanation(turtleLiteral(block, "explanation"));
                node.setDictionaryEntry("true".equalsIgnoreCase(turtleLiteral(block, "dictionaryEntry")));
                node.setLabel(turtleRdfsLabel(block));

                java.util.regex.Matcher partMatcher = java.util.regex.Pattern
                        .compile("allmahtr:hasPart\\s+<urn:allmah:translation-node:([^>]+)>")
                        .matcher(block);
                while (partMatcher.find()) {
                    node.addPartId(uriDecodeSafe(partMatcher.group(1)));
                }
                addOrReplaceTranslationNode(node);
            }
        }
        catch (Exception ex) {
            System.out.println("Could not read ALLMAH translation background nodes: " + ex.getMessage());
        }
    }

    private String turtleType(String block) {
        java.util.regex.Matcher matcher = java.util.regex.Pattern
                .compile("rdf:type\\s+allmahtr:([A-Za-z0-9_]+)")
                .matcher(block == null ? "" : block);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "TranslationNode";
    }

    private String turtleLiteral(String block, String property) {
        java.util.regex.Matcher matcher = java.util.regex.Pattern
                .compile("allmahtr:" + property + "\\s+\\\"((?:\\\\.|[^\\\"])*)\\\"")
                .matcher(block == null ? "" : block);
        if (matcher.find()) {
            return ttlUnescape(matcher.group(1));
        }
        return "";
    }

    private String turtleRdfsLabel(String block) {
        java.util.regex.Matcher matcher = java.util.regex.Pattern
                .compile("rdfs:label\\s+\\\"((?:\\\\.|[^\\\"])*)\\\"")
                .matcher(block == null ? "" : block);
        if (matcher.find()) {
            return ttlUnescape(matcher.group(1));
        }
        return "";
    }

    private String translationNodeUri(String id) {
        return "urn:allmah:translation-node:" + uriEncodeSafe(id);
    }

    private String uriEncodeSafe(String value) {
        if (value == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if ((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9')
                    || ch == '_' || ch == '-' || ch == '.') {
                sb.append(ch);
            }
            else {
                sb.append('%');
                String hex = Integer.toHexString(ch).toUpperCase();
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
        }
        return sb.toString();
    }

    private String uriDecodeSafe(String value) {
        if (value == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (ch == '%' && i + 2 < value.length()) {
                try {
                    int code = Integer.parseInt(value.substring(i + 1, i + 3), 16);
                    sb.append((char) code);
                    i += 2;
                }
                catch (Exception ex) {
                    sb.append(ch);
                }
            }
            else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    private String ttlEscape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", "\\r")
                .replace("\n", "\\n");
    }

    private String ttlUnescape(String value) {
        if (value == null) {
            return "";
        }
        String result = value.replace("\\n", "\n").replace("\\r", "\r").replace("\\\"", "\"").replace("\\\\", "\\");
        return result;
    }

    private String ttlName(String value) {
        if (value == null || value.trim().isEmpty()) {
            return "TranslationNode";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (Character.isLetterOrDigit(ch) || ch == '_') {
                sb.append(ch);
            }
        }
        if (sb.length() == 0) {
            return "TranslationNode";
        }
        return sb.toString();
    }

    /**
     * Translation window must show exactly the WordNet gloss selected in the
     * Select Meaning window.  Do not shorten the gloss at semicolons and do
     * not remove the example sentences, because these are part of the WordNet
     * explanation shown to the user when the meaning is selected.
     */
    private String cleanWordNetGlossForTranslationWindow(String rawGloss) {
        if (rawGloss == null) {
            return "";
        }

        return stripImportedHtml(rawGloss).trim();
    }

    private String operatorSymbolFromUri(String operatorUri) {
        if (operatorUri == null) {
            return " ";
        }

        if (operatorUri.endsWith("EmptyOperator")) return " ";
        if (operatorUri.endsWith("ColonOperator")) return ":";
        if (operatorUri.endsWith("DotOperator")) return ".";
        if (operatorUri.endsWith("PlusOperator")) return "+";
        if (operatorUri.endsWith("DegreeOperator")) return "°";
        if (operatorUri.endsWith("TopLeftOperator")) return "┌";
        if (operatorUri.endsWith("TopRightOperator")) return "┐";
        if (operatorUri.endsWith("BottomRightOperator")) return "╝";
        if (operatorUri.endsWith("BottomLeftOperator")) return "╚";
        if (operatorUri.endsWith("UpperRightCornerOperator")) return "╗";
        if (operatorUri.endsWith("UpperLeftCornerOperator")) return "╔";
        if (operatorUri.endsWith("HyphenOperator")) return "-";
        if (operatorUri.endsWith("EqualsOperator")) return "=";
        if (operatorUri.endsWith("GenericEOperator")) return "E";
        if (operatorUri.endsWith("UpArrowOperator")) return "↑";
        if (operatorUri.endsWith("TildeOperator")) return "~";
        if (operatorUri.endsWith("SquareBracketOperator")) return "[ ]";
        if (operatorUri.endsWith("CurlyBracketOperator")) return "{ }";
        if (operatorUri.endsWith("PipeOperator")) return "| |";
        if (operatorUri.endsWith("LessThanOperator")) return "<";
        if (operatorUri.endsWith("GreaterThanOperator")) return ">";
        if (operatorUri.endsWith("ConsonantOperator")) return "C";
        if (operatorUri.endsWith("VowelOperator")) return "V";
        if (operatorUri.endsWith("HOperator")) return "H";
        if (operatorUri.endsWith("ZeroOperator")) return "Ø";
        if (operatorUri.endsWith("CaretOperator")) return "^";

        return " ";
    }



    private ArrayList<String> extractGlyphTokensFromBlockExpression(
            String blockExpression
    ) {
        ArrayList<String> result =
                new ArrayList<String>();

        if (blockExpression == null) {
            return result;
        }

        StringTokenizer tokenizer =
                new StringTokenizer(
                        blockExpression,
                        SpecialSymbols.Operators_L13,
                        true
                );

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (Arrays.stream(SpecialSymbols.OperatorsSet_L13)
                    .anyMatch(token::equals)) {
                continue;
            }

            String glyphToken = token;

            while (glyphToken.startsWith("[")) {
                glyphToken = glyphToken.substring(1);
            }

            while (glyphToken.endsWith("]")) {
                glyphToken = glyphToken.substring(0, glyphToken.length() - 1);
            }

            glyphToken = glyphToken.trim();

            if (!glyphToken.isEmpty()) {
                result.add(glyphToken);
            }
        }

        return result;
    }

    private boolean sameBaseGlyph(String guiGlyphKey, String rdfGlyphKey) {
        String guiBase = baseGlyphNumber(guiGlyphKey);
        String rdfBase = baseGlyphNumber(rdfGlyphKey);

        if (guiBase == null || rdfBase == null) {
            return false;
        }

        return guiBase.equals(rdfBase);
    }

    private String baseGlyphNumber(String glyphKey) {
        if (glyphKey == null) {
            return null;
        }

        String key = cleanGlyphKey(glyphKey).trim();
        if (key.isEmpty()) {
            return null;
        }

        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (Character.isDigit(ch)) {
                digits.append(ch);
            }
            else {
                break;
            }
        }

        if (digits.length() == 0) {
            return key;
        }

        return digits.toString();
    }

    private String stripDisplayCoordinateFromBlockExpression(
            String blockExpression,
            AllmahRdfImporter.ImportedBlock importedBlock
    ) {
        if (blockExpression == null) {
            return "";
        }

        String result = stripImportedHtml(blockExpression).trim();

        String coord = null;
        if (importedBlock != null) {
            if (importedBlock.teiXmlId != null
                    && !importedBlock.teiXmlId.trim().isEmpty()) {
                coord = importedBlock.teiXmlId.trim();
            }
            else if (importedBlock.blockLabel != null
                    && !importedBlock.blockLabel.trim().isEmpty()) {
                coord = importedBlock.blockLabel.trim();
            }
        }

        if (coord != null && result.startsWith(coord + " ")) {
            result = result.substring(coord.length()).trim();
        }

        /*
         * Fallback for older TTLs that did not yet write allmah:blockExpression.
         * This removes only a leading coordinate token followed by whitespace.
         */
        result = result.replaceFirst("^[A-Z][0-9]+\\s+", "").trim();

        return result;
    }

    private ComponentElement getOrCreateImportedComponent(
            AllmahRdfImporter.ImportedComponent importedComponent,
            Map<String, ComponentElement> componentByUri
    ) {
        if (importedComponent == null) {
            return null;
        }

        String key = importedComponent.uri;
        if (key == null || key.trim().isEmpty()) {
            key = importedComponent.componentLabel;
        }
        if (key == null || key.trim().isEmpty()) {
            key = importedComponent.label;
        }
        if (key == null || key.trim().isEmpty()) {
            key = "component-" + componentByUri.size();
        }

        ComponentElement existing = componentByUri.get(key);
        if (existing != null) {
            return existing;
        }

        ComponentElement created =
                new ComponentElement(
                        colorFromImportedComponent(importedComponent)
                );

        String label = importedComponent.componentLabel;
        if (label == null || label.trim().isEmpty()) {
            label = importedComponent.label;
        }
        if (label == null || label.trim().isEmpty()) {
            label = importedComponent.componentSourceLabel;
        }
        if (label == null || label.trim().isEmpty()) {
            label = "Component";
        }

        created.setLabel1(stripImportedHtml(label));
        componentByUri.put(key, created);
        return created;
    }

    private String coloredImportedLabel(
            String label,
            AllmahRdfImporter.ImportedComponent importedComponent
    ) {
        String clean = stripImportedHtml(label);
        if (clean == null || clean.trim().isEmpty()) {
            clean = "";
        }

        Color color = colorFromImportedComponent(importedComponent);
        String hex = String.format(
                "#%02x%02x%02x",
                color.getRed(),
                color.getGreen(),
                color.getBlue()
        );

        return "<html><font color=\"" + hex + "\">"
                + clean
                + "</font></html>";
    }

    private Color colorFromImportedComponent(
            AllmahRdfImporter.ImportedComponent importedComponent
    ) {
        if (importedComponent == null
                || importedComponent.componentColor == null) {
            return colC;
        }

        String value = importedComponent.componentColor.trim();
        if (value.isEmpty()) {
            return colC;
        }

        try {
            if (value.startsWith("#")) {
                return Color.decode(value);
            }

            String[] parts = value.split(",");
            if (parts.length == 3) {
                int r = Integer.parseInt(parts[0].trim());
                int g = Integer.parseInt(parts[1].trim());
                int b = Integer.parseInt(parts[2].trim());
                return new Color(r, g, b);
            }
        } catch (Exception ex) {
            // use default component color below
        }

        return colC;
    }

    private String stripImportedHtml(String label) {
        if (label == null) {
            return "";
        }

        return Jsoup.parse(label)
                .text()
                .replace("&nbsp;", " ")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&amp;", "&")
                .replace("&quot;", "\"")
                .replaceAll("\\s+", " ")
                .trim();
    }

    private ArrayList<Reading> importedReadingsToGuiReadings(
            List<AllmahRdfImporter.ImportedReading> importedReadings
    ) {
        ArrayList<Reading> result = new ArrayList<Reading>();

        if (importedReadings == null) {
            return result;
        }

        for (AllmahRdfImporter.ImportedReading importedReading
                : importedReadings) {

            if (importedReading == null) {
                continue;
            }

            String label = importedReading.label;
            if (label == null || label.trim().isEmpty()) {
                label = importedReading.literalValue;
            }
            if (label == null || label.trim().isEmpty()) {
                continue;
            }

            String type = importedReading.readingType;
            if (type == null || type.trim().isEmpty()) {
                type = "lr";
            }

            Reading reading = new Reading(
                    stripImportedHtml(label),
                    importedReading.confidence,
                    type,
                    true
            );

            if (importedReading.sourceUri != null
                    && !importedReading.sourceUri.isEmpty()) {
                reading.setSourceUri(importedReading.sourceUri);
            }
            else if (importedReading.uri != null
                    && !importedReading.uri.isEmpty()) {
                reading.setSourceUri(importedReading.uri);
            }

            result.add(reading);
        }

        return result;
    }

    private void generateNT2ForSameBlockInAllReadings(
            NumTranslit1 sourceNT1
    ) {

        ArrayList<NumTranslit1> targets =
                getSameBlockSameNT1InAllReadings(sourceNT1);

        for (NumTranslit1 targetNT1 : targets) {

            copyNT1ReadingSelections(sourceNT1, targetNT1);

            if (targetNT1.getNT2() == null
                    || targetNT1.getNT2().isEmpty()) {
                targetNT1.generateNT2();
            }
        }

        changed = true;
    }

    private ArrayList<NumTranslit1> getSameBlockSameNT1InAllReadings(
            NumTranslit1 sourceNT1
    ) {

        ArrayList<NumTranslit1> result =
                new ArrayList<NumTranslit1>();

        if (sourceNT1 == null || sourceNT1.getParent() == null) {
            return result;
        }

        Integer sourceBlockIndex =
                mhb.get(sourceNT1.getParent());

        if (sourceBlockIndex == null) {
            return result;
        }

        HieroglyphenBlock sourceBlock =
                hb.get(sourceBlockIndex.intValue());

        int sourceNT1Index =
                sourceBlock.getNumTranslit1().indexOf(sourceNT1.getId());

        if (sourceNT1Index < 0) {
            return result;
        }

        String sourceBlockLabel =
                sourceBlock.getLabel();

        String sourceBlockNumerical =
                sourceBlock.getNumLabel();

        HashSet<String> seen =
                new HashSet<String>();

        for (DocumentReading reading : docr) {

            for (String blockId : reading.getBlocks()) {

                Integer blockIndex =
                        mhb.get(blockId);

                if (blockIndex == null) {
                    continue;
                }

                HieroglyphenBlock block =
                        hb.get(blockIndex.intValue());

                if (!sourceBlockLabel.equals(block.getLabel())) {
                    continue;
                }

                if (!sourceBlockNumerical.equals(block.getNumLabel())) {
                    continue;
                }

                if (block.getNumTranslit1().size() <= sourceNT1Index) {
                    continue;
                }

                String nt1Id =
                        block.getNumTranslit1().get(sourceNT1Index);

                Integer nt1Index =
                        mnt1.get(nt1Id);

                if (nt1Index == null) {
                    continue;
                }

                if (seen.add(nt1Id)) {
                    result.add(
                            nt1.get(nt1Index.intValue())
                    );
                }
            }
        }

        return result;
    }

    private void copyNT1ReadingSelections(
            NumTranslit1 sourceNT1,
            NumTranslit1 targetNT1
    ) {

        int max =
                Math.min(
                        sourceNT1.getElements().size(),
                        targetNT1.getElements().size()
                );

        for (int i = 0; i < max; i++) {

            NT1Element sourceElement =
                    sourceNT1.getElements().get(i);

            NT1Element targetElement =
                    targetNT1.getElements().get(i);

            targetElement.setSelectedReadings(
                    new ArrayList<Reading>(sourceElement.getSelectedReadings())
            );

            targetElement.setInitialReadings(
                    new ArrayList<Reading>(sourceElement.getInitialReadings())
            );
        }
    }

    private void refreshVisibleNT1NodesForSameBlockInAllReadings(
            NumTranslit1 sourceNT1
    ) {

        ArrayList<NumTranslit1> targets =
                getSameBlockSameNT1InAllReadings(sourceNT1);

        for (NumTranslit1 targetNT1 : targets) {

            ArrayList<AnnotationNode> nodes =
                    findAnnotationNodesForTreeObject(targetNT1);

            for (AnnotationNode node : nodes) {
                rebuildNT1AnnotationChildren(targetNT1, node);
            }
        }
    }

    private ArrayList<AnnotationNode> findAnnotationNodesForTreeObject(
            TreeNode target
    ) {

        ArrayList<AnnotationNode> result =
                new ArrayList<AnnotationNode>();

        if (blockTree == null || blockTree.getModel() == null) {
            return result;
        }

        Object root =
                blockTree.getModel().getRoot();

        if (root instanceof AnnotationNode) {
            collectAnnotationNodesForTreeObject(
                    (AnnotationNode) root,
                    target,
                    result
            );
        }

        return result;
    }

    private void collectAnnotationNodesForTreeObject(
            AnnotationNode current,
            TreeNode target,
            ArrayList<AnnotationNode> result
    ) {

        if (current.getMyTreeNode() == target) {
            result.add(current);
        }

        for (int i = 0; i < current.getChildCount(); i++) {
            Object child =
                    current.getChildAt(i);

            if (child instanceof AnnotationNode) {
                collectAnnotationNodesForTreeObject(
                        (AnnotationNode) child,
                        target,
                        result
                );
            }
        }
    }

    private void rebuildNT1AnnotationChildren(
            NumTranslit1 n1,
            AnnotationNode obj
    ) {

        obj.removeAllChildren();
        obj.setExplored(false);

        for (int i = 0; i < n1.getNT2().size(); i++) {

            NumTranslit2 n2 =
                    nt2.get(
                            mnt2.get(n1.getNT2().get(i)).intValue()
                    );

            AnnotationNode newObj =
                    new AnnotationNode(n2);

            for (int j = 0; j < n2.getGT1().size(); j++) {

                GraphTranslit1 g1 =
                        gt1.get(
                                mgt1.get(n2.getGT1().get(j)).intValue()
                        );

                AnnotationNode newObj1 =
                        new AnnotationNode(g1);

                for (int k = 0; k < g1.getGraphTranslit2().size(); k++) {

                    GraphTranslit2 g2 =
                            gt2.get(
                                    mgt2.get(
                                            g1.getGraphTranslit2().get(k)
                                    ).intValue()
                            );

                    AnnotationNode newObj2 =
                            new AnnotationNode(g2);

                    newObj1.add(newObj2);
                }

                newObj.add(newObj1);
            }

            obj.add(newObj);
        }
    }


    /**
     * Repair for newly created NT2 component variants:
     * GT1 must be generated from the readings attached to the original glyphs,
     * not from the numeric NT2 labels.  After the later GraphDB changes this can
     * become empty for copied NT2 elements; then NumTranslit2.generateGT1s("var")
     * falls back to elem.get(i).getLabel(), which produces 126/229 instead of
     * the intended readings.  We restore the readings through
     * NT2Element -> NT1Element -> GlyphElementNode immediately before GT1
     * generation.  Existing non-empty user selections are left untouched.
     */
    private void restoreReadingsForNT2VariantBeforeGT1Generation(
            NumTranslit2 variant
    ) {
        if (variant == null || variant.getElements() == null) {
            return;
        }

        for (NT2Element nt2Element : variant.getElements()) {
            if (nt2Element == null || nt2Element.getParent() == null) {
                continue;
            }

            NT1Element nt1Element = nt2Element.getParent();

            ArrayList<Reading> assigned = nt2Element.getAssignedReadings();
            if (hasRealNonNumericReading(assigned, nt2Element, nt1Element)) {
                continue;
            }

            ArrayList<Reading> selected = nt1Element.getSelectedReadings();
            if (hasRealNonNumericReading(selected, nt2Element, nt1Element)) {
                nt2Element.setAssignedReadings(selected);
                continue;
            }

            GlyphElementNode glyph = nt1Element.getParent();
            ArrayList<Reading> restored = new ArrayList<Reading>();

            if (glyph != null && glyph.getReadings() != null
                    && !glyph.getReadings().isEmpty()) {
                for (Reading r : glyph.getReadings()) {
                    if (r != null) {
                        restored.add(r.copy());
                    }
                }
            }
            else if (glyph != null && glyph.getAlternatives() != null
                    && !glyph.getAlternatives().isEmpty()) {

                String key = nt1Element.getLabel();
                if (key != null && key.length() >= 3) {
                    String suffix = key.substring(key.length() - 2);
                    if (suffix.matches("[a-zA-Z]+")) {
                        key = key.substring(0, key.length() - 2);
                    }
                }

                ArrayList<Reading> alternatives = glyph.getAlternatives().get(key);
                if (alternatives != null) {
                    for (Reading r : alternatives) {
                        if (r != null) {
                            restored.add(r.copy());
                        }
                    }
                }
            }

            if (restored.isEmpty()
                    && nt1Element.getInitialReadings() != null
                    && !nt1Element.getInitialReadings().isEmpty()) {
                for (Reading r : nt1Element.getInitialReadings()) {
                    if (r != null) {
                        restored.add(r.copy());
                    }
                }
            }

            if (!restored.isEmpty()) {
                nt2Element.setAssignedReadings(restored);
            }
        }
    }

    private boolean hasRealNonNumericReading(
            ArrayList<Reading> readings,
            NT2Element nt2Element,
            NT1Element nt1Element
    ) {
        if (readings == null || readings.isEmpty()) {
            return false;
        }

        String numericLabel = "";
        if (nt2Element != null && nt2Element.getSimpleLabel() != null) {
            numericLabel = cleanGlyphKey(stripImportedHtml(nt2Element.getSimpleLabel()));
        }

        String nt1Label = "";
        if (nt1Element != null && nt1Element.getLabel() != null) {
            nt1Label = cleanGlyphKey(stripImportedHtml(nt1Element.getLabel()));
        }

        for (Reading reading : readings) {
            if (reading == null || reading.getReading() == null) {
                continue;
            }

            String value = cleanGlyphKey(stripImportedHtml(reading.getReading()));
            if (value.length() == 0) {
                continue;
            }

            /*
             * A reading that is merely the numeric glyph label is the old
             * fallback from NT1Element#getReading(), not a real graphematic
             * reading.  It must not block restoration before GT1 generation.
             */
            if (value.equals(numericLabel) || value.equals(nt1Label)) {
                continue;
            }

            if (value.matches("[0-9]+")) {
                continue;
            }

            return true;
        }

        return false;
    }



    private void propagateNT2VariantToSameBlockInAllReadings(
            NumTranslit2 sourceVariant,
            NumTranslit1 sourceNT1
    ) {

        if (sourceVariant == null || sourceNT1 == null) {
            return;
        }

        ArrayList<NumTranslit1> targets =
                getSameBlockSameNT1InAllReadings(sourceNT1);

        for (NumTranslit1 targetNT1 : targets) {

            if (targetNT1 == sourceNT1) {
                continue;
            }

            if (hasCorrespondingNT2Variant(targetNT1, sourceVariant)) {
                continue;
            }

            NumTranslit2 targetVariant =
                    createNT2VariantForTargetNT1(
                            sourceVariant,
                            targetNT1
                    );

            nt2.add(targetVariant);
            mnt2.put(
                    targetVariant.getId(),
                    new Integer(nt2.size() - 1)
            );

            targetNT1.getNT2().add(targetVariant.getId());
            targetVariant.generateGT1s("var");
        }
    }

    private boolean hasCorrespondingNT2Variant(
            NumTranslit1 targetNT1,
            NumTranslit2 sourceVariant
    ) {

        String sourceSignature =
                buildNT2ComponentSignature(sourceVariant);

        for (String nt2Id : targetNT1.getNT2()) {

            Integer nt2Index =
                    mnt2.get(nt2Id);

            if (nt2Index == null) {
                continue;
            }

            NumTranslit2 existing =
                    nt2.get(nt2Index.intValue());

            if (!"var".equals(existing.getTyp())) {
                continue;
            }

            if (sourceSignature.equals(
                    buildNT2ComponentSignature(existing)
            )) {
                return true;
            }
        }

        return false;
    }

    private String buildNT2ComponentSignature(
            NumTranslit2 variant
    ) {

        StringBuilder signature =
                new StringBuilder();

        for (int i = 0; i < variant.getElements().size(); i++) {

            NT2Element element =
                    variant.getElements().get(i);

            signature.append(element.getSimpleLabel());
            signature.append('|');

            ComponentElement component =
                    element.getComponentElement();

            if (component != null) {
                Color c = component.getcolor();
                signature.append(c.getRed()).append(',')
                        .append(c.getGreen()).append(',')
                        .append(c.getBlue()).append(':')
                        .append(component.getLabel1());
            }

            signature.append(';');
        }

        return signature.toString();
    }

    private NumTranslit2 createNT2VariantForTargetNT1(
            NumTranslit2 sourceVariant,
            NumTranslit1 targetNT1
    ) {

        String newNT2Id =
                targetNT1.getId()
                        + ">NT2_"
                        + targetNT1.getNT2().size();

        NumTranslit2 targetVariant =
                new NumTranslit2(newNT2Id, this, sourceVariant.getTyp());

        HashMap<ComponentElement, ComponentElement> componentMap =
                new HashMap<ComponentElement, ComponentElement>();

        int max =
                Math.min(
                        sourceVariant.getElements().size(),
                        targetNT1.getElements().size()
                );

        for (int i = 0; i < max; i++) {

            NT2Element sourceElement =
                    sourceVariant.getElements().get(i);

            NT2Element targetElement =
                    new NT2Element(
                            targetNT1.getElements().get(i)
                    );

            targetElement.setLabel(
                    sourceElement.getSimpleLabel()
            );

            ComponentElement sourceComponent =
                    sourceElement.getComponentElement();

            if (sourceComponent != null) {

                ComponentElement targetComponent =
                        componentMap.get(sourceComponent);

                if (targetComponent == null) {
                    targetComponent =
                            new ComponentElement(
                                    sourceComponent.getcolor()
                            );
                    targetComponent.setLabel1(
                            sourceComponent.getLabel1()
                    );
                    componentMap.put(
                            sourceComponent,
                            targetComponent
                    );
                }

                targetElement.setComplexGlyph(targetComponent);
            }

            targetVariant.getElements().add(targetElement);
        }

        targetVariant.createGraphStructure();
        attachTargetComponentElementsToNT2Graph(
                targetVariant,
                componentMap
        );

        return targetVariant;
    }

    private void attachTargetComponentElementsToNT2Graph(
            NumTranslit2 targetVariant,
            HashMap<ComponentElement, ComponentElement> componentMap
    ) {

        if (componentMap.isEmpty()
                || targetVariant.getEntireGraphNT2() == null) {
            return;
        }

        Collection<GlyphNode> vertices =
                targetVariant.getEntireGraphNT2().getVertices();

        for (GlyphNode vertex : vertices) {

            if (!(vertex instanceof GraphNT2Node)) {
                continue;
            }

            GraphNT2Node graphNode =
                    (GraphNT2Node) vertex;

            if (graphNode.getNT2Element() == null) {
                continue;
            }

            ComponentElement component =
                    graphNode.getNT2Element().getComponentElement();

            if (component != null) {
                component.getElements().add(graphNode);
                component.setLabel();
            }
        }
    }


    /**
     * Local delete for a single GT2 variant.
     *
     * Important: Save to TTL / GraphDB traverses GT1.getGraphTranslit2().
     * Therefore the GT2 id must disappear from that parent list.  Removing only
     * the visible tree node is not enough; removing only the global gt2 object
     * is also not the intended model change.
     *
     * This method is deliberately local and does not issue any GraphDB/SPARQL
     * delete.  The next Save to GraphDB writes the current document graph and
     * replaces the old graph.
     */
    private boolean deleteLocalGT2(
            GraphTranslit2 targetGT2,
            AnnotationNode visibleTreeNode
    ) {
        if (targetGT2 == null) {
            return false;
        }

        String targetId = targetGT2.getId();
        if (targetId == null) {
            return false;
        }

        boolean removed = false;

        /*
         * First use the visible tree parent.  This is the most reliable context
         * for a local delete, because it is exactly the GT1 under which the user
         * clicked the GT2 node.
         */
        if (visibleTreeNode != null
                && visibleTreeNode.getParent() instanceof AnnotationNode) {

            AnnotationNode visibleParent =
                    (AnnotationNode) visibleTreeNode.getParent();

            if (visibleParent.getMyTreeNode() instanceof GraphTranslit1) {
                GraphTranslit1 parentGT1 =
                        (GraphTranslit1) visibleParent.getMyTreeNode();

                if (parentGT1.getGraphTranslit2() != null) {
                    while (parentGT1.getGraphTranslit2().remove(targetId)) {
                        removed = true;
                    }
                }
            }
        }

        /*
         * Fallback via the internal parent id.
         */
        if (!removed && targetGT2.getParent() != null) {
            Integer parentGT1Index = mgt1.get(targetGT2.getParent());
            if (parentGT1Index != null) {
                GraphTranslit1 parentGT1 = gt1.get(parentGT1Index.intValue());
                if (parentGT1 != null && parentGT1.getGraphTranslit2() != null) {
                    while (parentGT1.getGraphTranslit2().remove(targetId)) {
                        removed = true;
                    }
                }
            }
        }

        /*
         * Last safety net: IDs are reading-specific, so removing the same id
         * from any accidental parent list is still local to this GT2 object.
         */
        if (!removed) {
            for (GraphTranslit1 g1 : gt1) {
                if (g1 == null || g1.getGraphTranslit2() == null) {
                    continue;
                }
                while (g1.getGraphTranslit2().remove(targetId)) {
                    removed = true;
                }
            }
        }

        if (removed) {
            /*
             * Prevent later save/traversal paths from resolving this stale GT2
             * id.  The object may remain in the global gt2 ArrayList, but it is
             * no longer reachable from the document tree.
             */
            mgt2.remove(targetId);
            changed = true;
        }

        return removed;
    }


    private String nextMorphoSyntTranslitId(PhonemTranslit parentPT) {
        String base = parentPT.getId() + ">MT_";
        int index = 0;
        if (parentPT.getMorphoTranscr() != null) {
            index = parentPT.getMorphoTranscr().size();
        }

        String candidate = base + index;
        while ((mmt != null && mmt.containsKey(candidate))
                || (parentPT.getMorphoTranscr() != null
                && parentPT.getMorphoTranscr().contains(candidate))) {
            index++;
            candidate = base + index;
        }
        return candidate;
    }

    private String nextMorphoGlossingId(MorphoSyntTranslit parentMT) {
        String base = parentMT.getId() + ">MSG_";
        int index = 0;
        if (parentMT.getMorphoGloss() != null) {
            index = parentMT.getMorphoGloss().size();
        }

        String candidate = base + index;
        while ((mmg != null && mmg.containsKey(candidate))
                || (parentMT.getMorphoGloss() != null
                && parentMT.getMorphoGloss().contains(candidate))) {
            index++;
            candidate = base + index;
        }
        return candidate;
    }


    private boolean deleteLocalMorphoGlossing(
            MorphoSyntGlossing targetGlossing,
            AnnotationNode visibleTreeNode) {

        if (targetGlossing == null) {
            return false;
        }

        String targetId = targetGlossing.getId();

        AnnotationNode parentNode =
                (AnnotationNode) visibleTreeNode.getParent();

        if (parentNode != null
                && parentNode.getMyTreeNode() instanceof MorphoSyntTranslit) {

            MorphoSyntTranslit parentMT =
                    (MorphoSyntTranslit) parentNode.getMyTreeNode();

            boolean removed =
                    parentMT.getMorphoGloss().remove(targetId);

            if (removed) {
                mmg.remove(targetId);
                changed = true;
            }

            return removed;
        }

        return false;
    }


    private boolean deleteLocalMorphoSyntTranslit(
            MorphoSyntTranslit targetMorpho,
            AnnotationNode visibleTreeNode
    ) {
        if (targetMorpho == null) {
            return false;
        }

        String targetId = targetMorpho.getId();
        if (targetId == null) {
            return false;
        }

        boolean removed = false;

        if (visibleTreeNode != null
                && visibleTreeNode.getParent() instanceof AnnotationNode) {
            AnnotationNode visibleParent =
                    (AnnotationNode) visibleTreeNode.getParent();
            if (visibleParent.getMyTreeNode() instanceof PhonemTranslit) {
                PhonemTranslit parentPT =
                        (PhonemTranslit) visibleParent.getMyTreeNode();
                if (parentPT.getMorphoTranscr() != null) {
                    while (parentPT.getMorphoTranscr().remove(targetId)) {
                        removed = true;
                    }
                }
            }
        }

        if (!removed && targetMorpho.getParent() != null) {
            Integer parentPTIndex = mpt.get(targetMorpho.getParent());
            if (parentPTIndex != null) {
                PhonemTranslit parentPT = pt.get(parentPTIndex.intValue());
                if (parentPT != null && parentPT.getMorphoTranscr() != null) {
                    while (parentPT.getMorphoTranscr().remove(targetId)) {
                        removed = true;
                    }
                }
            }
        }

        if (!removed) {
            for (PhonemTranslit p : pt) {
                if (p == null || p.getMorphoTranscr() == null) {
                    continue;
                }
                while (p.getMorphoTranscr().remove(targetId)) {
                    removed = true;
                }
            }
        }

        if (removed) {
            mmt.remove(targetId);
            changed = true;
        }

        return removed;
    }


    private boolean deleteLocalPhonemicTranslit(
            PhonemTranslit targetPhonemic,
            AnnotationNode visibleTreeNode
    ) {
        if (targetPhonemic == null) {
            return false;
        }

        String targetId = targetPhonemic.getId();
        if (targetId == null) {
            return false;
        }

        boolean removed = false;

        if (visibleTreeNode != null
                && visibleTreeNode.getParent() instanceof AnnotationNode) {
            AnnotationNode visibleParent =
                    (AnnotationNode) visibleTreeNode.getParent();
            if (visibleParent.getMyTreeNode() instanceof GraphTranslit2) {
                GraphTranslit2 parentGT2 =
                        (GraphTranslit2) visibleParent.getMyTreeNode();
                if (parentGT2.getPhonemicId() != null) {
                    while (parentGT2.getPhonemicId().remove(targetId)) {
                        removed = true;
                    }
                }
            }
        }

        if (!removed && targetPhonemic.getParent() != null) {
            Integer parentGT2Index = mgt2.get(targetPhonemic.getParent());
            if (parentGT2Index != null) {
                GraphTranslit2 parentGT2 = gt2.get(parentGT2Index.intValue());
                if (parentGT2 != null && parentGT2.getPhonemicId() != null) {
                    while (parentGT2.getPhonemicId().remove(targetId)) {
                        removed = true;
                    }
                }
            }
        }

        if (!removed) {
            for (GraphTranslit2 g2 : gt2) {
                if (g2 == null || g2.getPhonemicId() == null) {
                    continue;
                }
                while (g2.getPhonemicId().remove(targetId)) {
                    removed = true;
                }
            }
        }

        if (removed) {
            mpt.remove(targetId);
            changed = true;
        }

        return removed;
    }


    /**
     * Local delete for a single GT1 variant.
     *
     * Save to TTL / GraphDB traverses NT2.getGT1().  Therefore the GT1 id
     * must disappear from that parent list.  All GT2 variants below this GT1
     * become unreachable automatically.  No GraphDB/SPARQL delete is issued;
     * the next Save to GraphDB replaces the document graph.
     */
    private boolean deleteLocalGT1(
            GraphTranslit1 targetGT1,
            AnnotationNode visibleTreeNode
    ) {
        if (targetGT1 == null) {
            return false;
        }

        String targetId = targetGT1.getId();
        if (targetId == null) {
            return false;
        }

        boolean removed = false;

        /*
         * First use the visible tree parent.  This is the exact NT2 node under
         * which the user clicked this GT1.
         */
        if (visibleTreeNode != null
                && visibleTreeNode.getParent() instanceof AnnotationNode) {

            AnnotationNode visibleParent =
                    (AnnotationNode) visibleTreeNode.getParent();

            if (visibleParent.getMyTreeNode() instanceof NumTranslit2) {
                NumTranslit2 parentNT2 =
                        (NumTranslit2) visibleParent.getMyTreeNode();

                if (parentNT2.getGT1() != null) {
                    while (parentNT2.getGT1().remove(targetId)) {
                        removed = true;
                    }
                }
            }
        }

        /*
         * Fallback via the internal parent id.
         */
        if (!removed && targetGT1.getParent() != null) {
            Integer parentNT2Index = mnt2.get(targetGT1.getParent());
            if (parentNT2Index != null) {
                NumTranslit2 parentNT2 = nt2.get(parentNT2Index.intValue());
                if (parentNT2 != null && parentNT2.getGT1() != null) {
                    while (parentNT2.getGT1().remove(targetId)) {
                        removed = true;
                    }
                }
            }
        }

        /*
         * Last safety net: removing this exact reading-specific id from any
         * accidental parent list is still local to this GT1 object.
         */
        if (!removed) {
            for (NumTranslit2 n2 : nt2) {
                if (n2 == null || n2.getGT1() == null) {
                    continue;
                }
                while (n2.getGT1().remove(targetId)) {
                    removed = true;
                }
            }
        }

        if (removed) {
            if (targetGT1.getGraphTranslit2() != null) {
                for (String childGT2Id : targetGT1.getGraphTranslit2()) {
                    mgt2.remove(childGT2Id);
                }
            }
            mgt1.remove(targetId);
            changed = true;
        }

        return removed;
    }

    /**
     * Local delete for a single NT2 variant.
     *
     * Save to TTL / GraphDB traverses NT1.getNT2().  Therefore the NT2 id
     * must disappear from that parent list.  All GT1 and GT2 variants below
     * this NT2 become unreachable automatically.  No GraphDB/SPARQL delete is
     * issued; the next Save to GraphDB replaces the document graph.
     */
    private boolean deleteLocalNT2(
            NumTranslit2 targetNT2,
            AnnotationNode visibleTreeNode
    ) {
        if (targetNT2 == null) {
            return false;
        }

        String targetId = targetNT2.getId();
        if (targetId == null) {
            return false;
        }

        boolean removed = false;

        /*
         * First use the visible tree parent.  This is the exact NT1 node under
         * which the user clicked this NT2.
         */
        if (visibleTreeNode != null
                && visibleTreeNode.getParent() instanceof AnnotationNode) {

            AnnotationNode visibleParent =
                    (AnnotationNode) visibleTreeNode.getParent();

            if (visibleParent.getMyTreeNode() instanceof NumTranslit1) {
                NumTranslit1 parentNT1 =
                        (NumTranslit1) visibleParent.getMyTreeNode();

                if (parentNT1.getNT2() != null) {
                    while (parentNT1.getNT2().remove(targetId)) {
                        removed = true;
                    }
                }
            }
        }

        /*
         * Fallback via the internal parent id.
         */
        if (!removed && targetNT2.getParent() != null) {
            Integer parentNT1Index = mnt1.get(targetNT2.getParent());
            if (parentNT1Index != null) {
                NumTranslit1 parentNT1 = nt1.get(parentNT1Index.intValue());
                if (parentNT1 != null && parentNT1.getNT2() != null) {
                    while (parentNT1.getNT2().remove(targetId)) {
                        removed = true;
                    }
                }
            }
        }

        /*
         * Last safety net: removing this exact reading-specific id from any
         * accidental parent list is still local to this NT2 object.
         */
        if (!removed) {
            for (NumTranslit1 n1 : nt1) {
                if (n1 == null || n1.getNT2() == null) {
                    continue;
                }
                while (n1.getNT2().remove(targetId)) {
                    removed = true;
                }
            }
        }

        if (removed) {
            if (targetNT2.getGT1() != null) {
                for (String childGT1Id : targetNT2.getGT1()) {
                    Integer childGT1Index = mgt1.get(childGT1Id);
                    if (childGT1Index != null) {
                        GraphTranslit1 childGT1 =
                                gt1.get(childGT1Index.intValue());
                        if (childGT1 != null
                                && childGT1.getGraphTranslit2() != null) {
                            for (String childGT2Id : childGT1.getGraphTranslit2()) {
                                mgt2.remove(childGT2Id);
                            }
                        }
                    }
                    mgt1.remove(childGT1Id);
                }
            }
            mnt2.remove(targetId);
            changed = true;
        }

        return removed;
    }

    /**
     * Local delete for a complete DocumentReading.
     *
     * Allowed only while at least two DocumentReadings exist.  After removing
     * one reading, all remaining readings are renumbered densely (R0, R1, ...)
     * and all descendant ids/parent paths are rewritten accordingly.  Save to
     * TTL / GraphDB then writes the current document graph; no direct GraphDB
     * delete is issued here.
     */
    private boolean deleteLocalDocumentReading(
            DocumentReading targetReading
    ) {
        if (targetReading == null || docr == null || docr.size() < 2) {
            return false;
        }

        int removeIndex = -1;
        for (int i = 0; i < docr.size(); i++) {
            if (docr.get(i) == targetReading
                    || (docr.get(i) != null
                    && docr.get(i).getId() != null
                    && docr.get(i).getId().equals(targetReading.getId()))) {
                removeIndex = i;
                break;
            }
        }

        if (removeIndex < 0) {
            return false;
        }

        docr.remove(removeIndex);

        /*
         * Preserve the old maps while ids are rewritten.  The global object
         * arrays may still contain stale objects from the deleted reading, but
         * after rebuildReachableReadingMaps() they are no longer reachable from
         * the document tree or save/export traversal.
         */
        HashMap<String, Integer> oldMhb = new HashMap<String, Integer>(mhb);
        HashMap<String, Integer> oldMnt1 = new HashMap<String, Integer>(mnt1);
        HashMap<String, Integer> oldMnt2 = new HashMap<String, Integer>(mnt2);
        HashMap<String, Integer> oldMgt1 = new HashMap<String, Integer>(mgt1);
        HashMap<String, Integer> oldMgt2 = new HashMap<String, Integer>(mgt2);

        for (int i = 0; i < docr.size(); i++) {
            DocumentReading reading = docr.get(i);
            if (reading == null) {
                continue;
            }

            String oldReadingId = reading.getId();
            String newReadingId = doc.getId() + ">R" + i;

            if (oldReadingId == null || oldReadingId.equals(newReadingId)) {
                continue;
            }

            renumberDocumentReadingSubtree(
                    reading,
                    oldReadingId,
                    newReadingId,
                    oldMhb,
                    oldMnt1,
                    oldMnt2,
                    oldMgt1,
                    oldMgt2
            );
        }

        rebuildReachableReadingMaps();
        changed = true;
        return true;
    }

    private void renumberDocumentReadingSubtree(
            DocumentReading reading,
            String oldReadingId,
            String newReadingId,
            Map<String, Integer> oldMhb,
            Map<String, Integer> oldMnt1,
            Map<String, Integer> oldMnt2,
            Map<String, Integer> oldMgt1,
            Map<String, Integer> oldMgt2
    ) {
        if (reading == null || oldReadingId == null || newReadingId == null) {
            return;
        }

        reading.setId(newReadingId);

        ArrayList<String> blockIds = reading.getBlocks();
        if (blockIds == null) {
            return;
        }

        for (int b = 0; b < blockIds.size(); b++) {
            String oldBlockId = blockIds.get(b);
            String newBlockId = remapReadingPrefix(oldBlockId, oldReadingId, newReadingId);

            HieroglyphenBlock block = getBlockByOldId(oldBlockId, oldMhb);
            if (block == null) {
                blockIds.set(b, newBlockId);
                continue;
            }

            setPrivateField(block, "id", newBlockId);
            setPrivateField(block, "parent", newReadingId);
            blockIds.set(b, newBlockId);

            ArrayList<String> nt1Ids = block.getNumTranslit1();
            if (nt1Ids == null) {
                continue;
            }

            for (int n1Index = 0; n1Index < nt1Ids.size(); n1Index++) {
                String oldNT1Id = nt1Ids.get(n1Index);
                String newNT1Id = remapReadingPrefix(oldNT1Id, oldReadingId, newReadingId);

                NumTranslit1 n1 = getNT1ByOldId(oldNT1Id, oldMnt1);
                if (n1 == null) {
                    nt1Ids.set(n1Index, newNT1Id);
                    continue;
                }

                n1.setId(newNT1Id);
                setPrivateField(n1, "parent", newBlockId);
                nt1Ids.set(n1Index, newNT1Id);

                ArrayList<String> nt2Ids = n1.getNT2();
                if (nt2Ids == null) {
                    continue;
                }

                for (int n2Index = 0; n2Index < nt2Ids.size(); n2Index++) {
                    String oldNT2Id = nt2Ids.get(n2Index);
                    String newNT2Id = remapReadingPrefix(oldNT2Id, oldReadingId, newReadingId);

                    NumTranslit2 n2 = getNT2ByOldId(oldNT2Id, oldMnt2);
                    if (n2 == null) {
                        nt2Ids.set(n2Index, newNT2Id);
                        continue;
                    }

                    n2.setId(newNT2Id);
                    setPrivateField(n2, "parent", newNT1Id);
                    nt2Ids.set(n2Index, newNT2Id);

                    ArrayList<String> gt1Ids = n2.getGT1();
                    if (gt1Ids == null) {
                        continue;
                    }

                    for (int g1Index = 0; g1Index < gt1Ids.size(); g1Index++) {
                        String oldGT1Id = gt1Ids.get(g1Index);
                        String newGT1Id = remapReadingPrefix(oldGT1Id, oldReadingId, newReadingId);

                        GraphTranslit1 g1 = getGT1ByOldId(oldGT1Id, oldMgt1);
                        if (g1 == null) {
                            gt1Ids.set(g1Index, newGT1Id);
                            continue;
                        }

                        g1.setId(newGT1Id);
                        setPrivateField(g1, "parent", newNT2Id);
                        gt1Ids.set(g1Index, newGT1Id);

                        ArrayList<String> gt2Ids = g1.getGraphTranslit2();
                        if (gt2Ids == null) {
                            continue;
                        }

                        for (int g2Index = 0; g2Index < gt2Ids.size(); g2Index++) {
                            String oldGT2Id = gt2Ids.get(g2Index);
                            String newGT2Id = remapReadingPrefix(oldGT2Id, oldReadingId, newReadingId);

                            GraphTranslit2 g2 = getGT2ByOldId(oldGT2Id, oldMgt2);
                            if (g2 != null) {
                                g2.setId(newGT2Id);
                                setPrivateField(g2, "parent", newGT1Id);
                            }
                            gt2Ids.set(g2Index, newGT2Id);
                        }
                    }
                }
            }
        }
    }

    private String remapReadingPrefix(
            String id,
            String oldReadingId,
            String newReadingId
    ) {
        if (id == null || oldReadingId == null || newReadingId == null) {
            return id;
        }
        if (id.equals(oldReadingId)) {
            return newReadingId;
        }
        if (id.startsWith(oldReadingId + ">")) {
            return newReadingId + id.substring(oldReadingId.length());
        }
        return id;
    }

    private HieroglyphenBlock getBlockByOldId(
            String id,
            Map<String, Integer> oldMap
    ) {
        if (id == null || oldMap == null) {
            return null;
        }
        Integer index = oldMap.get(id);
        if (index == null || index.intValue() < 0 || index.intValue() >= hb.size()) {
            return null;
        }
        return hb.get(index.intValue());
    }

    private NumTranslit1 getNT1ByOldId(String id, Map<String, Integer> oldMap) {
        if (id == null || oldMap == null) return null;
        Integer index = oldMap.get(id);
        if (index == null || index.intValue() < 0 || index.intValue() >= nt1.size()) return null;
        return nt1.get(index.intValue());
    }

    private NumTranslit2 getNT2ByOldId(String id, Map<String, Integer> oldMap) {
        if (id == null || oldMap == null) return null;
        Integer index = oldMap.get(id);
        if (index == null || index.intValue() < 0 || index.intValue() >= nt2.size()) return null;
        return nt2.get(index.intValue());
    }

    private GraphTranslit1 getGT1ByOldId(String id, Map<String, Integer> oldMap) {
        if (id == null || oldMap == null) return null;
        Integer index = oldMap.get(id);
        if (index == null || index.intValue() < 0 || index.intValue() >= gt1.size()) return null;
        return gt1.get(index.intValue());
    }

    private GraphTranslit2 getGT2ByOldId(String id, Map<String, Integer> oldMap) {
        if (id == null || oldMap == null) return null;
        Integer index = oldMap.get(id);
        if (index == null || index.intValue() < 0 || index.intValue() >= gt2.size()) return null;
        return gt2.get(index.intValue());
    }

    private void rebuildReachableReadingMaps() {
        mdocr.clear();
        mhb.clear();
        mnt1.clear();
        mnt2.clear();
        mgt1.clear();
        mgt2.clear();

        if (doc.getDocReadings() != null) {
            doc.getDocReadings().clear();
        }

        for (int r = 0; r < docr.size(); r++) {
            DocumentReading reading = docr.get(r);
            if (reading == null) {
                continue;
            }

            mdocr.put(reading.getId(), Integer.valueOf(r));
            if (doc.getDocReadings() != null) {
                doc.getDocReadings().add(reading.getId());
            }

            if (reading.getBlocks() == null) {
                continue;
            }

            for (Iterator<String> blockIterator = reading.getBlocks().iterator();
                    blockIterator.hasNext();) {

                String blockId = blockIterator.next();
                HieroglyphenBlock block = findBlockObjectById(blockId);
                if (block == null) {
                    blockIterator.remove();
                    continue;
                }

                mhb.put(blockId, Integer.valueOf(hb.indexOf(block)));

                if (block.getNumTranslit1() == null) {
                    continue;
                }

                for (String nt1Id : block.getNumTranslit1()) {
                    NumTranslit1 n1 = findNT1ObjectById(nt1Id);
                    if (n1 == null) {
                        continue;
                    }
                    mnt1.put(nt1Id, Integer.valueOf(nt1.indexOf(n1)));

                    if (n1.getNT2() == null) {
                        continue;
                    }

                    for (String nt2Id : n1.getNT2()) {
                        NumTranslit2 n2 = findNT2ObjectById(nt2Id);
                        if (n2 == null) {
                            continue;
                        }
                        mnt2.put(nt2Id, Integer.valueOf(nt2.indexOf(n2)));

                        if (n2.getGT1() == null) {
                            continue;
                        }

                        for (String gt1Id : n2.getGT1()) {
                            GraphTranslit1 g1 = findGT1ObjectById(gt1Id);
                            if (g1 == null) {
                                continue;
                            }
                            mgt1.put(gt1Id, Integer.valueOf(gt1.indexOf(g1)));

                            if (g1.getGraphTranslit2() == null) {
                                continue;
                            }

                            for (String gt2Id : g1.getGraphTranslit2()) {
                                GraphTranslit2 g2 = findGT2ObjectById(gt2Id);
                                if (g2 != null) {
                                    mgt2.put(gt2Id, Integer.valueOf(gt2.indexOf(g2)));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private HieroglyphenBlock findBlockObjectById(String id) {
        if (id == null) return null;
        for (HieroglyphenBlock block : hb) {
            if (block != null && id.equals(block.getBlockID())) {
                return block;
            }
        }
        return null;
    }

    private NumTranslit1 findNT1ObjectById(String id) {
        if (id == null) return null;
        for (NumTranslit1 n1 : nt1) {
            if (n1 != null && id.equals(n1.getId())) {
                return n1;
            }
        }
        return null;
    }

    private NumTranslit2 findNT2ObjectById(String id) {
        if (id == null) return null;
        for (NumTranslit2 n2 : nt2) {
            if (n2 != null && id.equals(n2.getId())) {
                return n2;
            }
        }
        return null;
    }

    private GraphTranslit1 findGT1ObjectById(String id) {
        if (id == null) return null;
        for (GraphTranslit1 g1 : gt1) {
            if (g1 != null && id.equals(g1.getId())) {
                return g1;
            }
        }
        return null;
    }

    private GraphTranslit2 findGT2ObjectById(String id) {
        if (id == null) return null;
        for (GraphTranslit2 g2 : gt2) {
            if (g2 != null && id.equals(g2.getId())) {
                return g2;
            }
        }
        return null;
    }



    private void propagateGT2VariantToSameBlockInAllReadings(
            GraphTranslit2 sourceVariant,
            GraphTranslit1 sourceGT1
    ) {

        if (sourceVariant == null || sourceGT1 == null) {
            return;
        }

        Integer sourceNT2IndexObj = mnt2.get(sourceGT1.getParent());
        if (sourceNT2IndexObj == null) {
            return;
        }

        NumTranslit2 sourceNT2 = nt2.get(sourceNT2IndexObj.intValue());

        Integer sourceNT1IndexObj = mnt1.get(sourceNT2.getParent());
        if (sourceNT1IndexObj == null) {
            return;
        }

        NumTranslit1 sourceNT1 = nt1.get(sourceNT1IndexObj.intValue());
        int sourceNT2ListIndex = sourceNT1.getNT2().indexOf(sourceNT2.getId());
        int sourceGT1ListIndex = sourceNT2.getGT1().indexOf(sourceGT1.getId());

        if (sourceNT2ListIndex < 0 || sourceGT1ListIndex < 0) {
            return;
        }

        ArrayList<NumTranslit1> targetNT1s =
                getSameBlockSameNT1InAllReadings(sourceNT1);

        for (NumTranslit1 targetNT1 : targetNT1s) {

            if (targetNT1 == sourceNT1) {
                continue;
            }

            NumTranslit2 targetNT2 =
                    ensureCorrespondingNT2ForTargetNT1(
                            sourceNT2,
                            targetNT1,
                            sourceNT2ListIndex
                    );

            if (targetNT2 == null) {
                continue;
            }

            GraphTranslit1 targetGT1 =
                    getGT1AtSamePosition(targetNT2, sourceGT1ListIndex);

            if (targetGT1 == null) {
                continue;
            }

            if (hasCorrespondingGT2Variant(targetGT1, sourceVariant)) {
                continue;
            }

            GraphTranslit2 targetVariant =
                    createGT2VariantForTargetGT1(sourceVariant, targetGT1);

            gt2.add(targetVariant);
            mgt2.put(
                    targetVariant.getId(),
                    new Integer(gt2.size() - 1)
            );

            targetGT1.getGraphTranslit2().add(targetVariant.getId());
        }

        changed = true;
    }

    private void propagateGT2ConfidenceToSameBlockInAllReadings(
            GraphTranslit2 sourceVariant,
            int confidence
    ) {
        if (sourceVariant == null) {
            return;
        }

        Integer sourceGT1IndexObj = mgt1.get(sourceVariant.getParent());
        if (sourceGT1IndexObj == null) {
            return;
        }

        GraphTranslit1 sourceGT1 = gt1.get(sourceGT1IndexObj.intValue());
        Integer sourceNT2IndexObj = mnt2.get(sourceGT1.getParent());
        if (sourceNT2IndexObj == null) {
            return;
        }

        NumTranslit2 sourceNT2 = nt2.get(sourceNT2IndexObj.intValue());
        Integer sourceNT1IndexObj = mnt1.get(sourceNT2.getParent());
        if (sourceNT1IndexObj == null) {
            return;
        }

        NumTranslit1 sourceNT1 = nt1.get(sourceNT1IndexObj.intValue());
        int sourceNT2ListIndex = sourceNT1.getNT2().indexOf(sourceNT2.getId());
        int sourceGT1ListIndex = sourceNT2.getGT1().indexOf(sourceGT1.getId());
        String sourceSignature = buildGT2VariantSignature(sourceVariant);

        if (sourceNT2ListIndex < 0 || sourceGT1ListIndex < 0) {
            return;
        }

        ArrayList<NumTranslit1> targetNT1s =
                getSameBlockSameNT1InAllReadings(sourceNT1);

        for (NumTranslit1 targetNT1 : targetNT1s) {

            if (targetNT1 == null) {
                continue;
            }

            if (targetNT1.getNT2().size() <= sourceNT2ListIndex) {
                continue;
            }

            Integer targetNT2IndexObj =
                    mnt2.get(targetNT1.getNT2().get(sourceNT2ListIndex));
            if (targetNT2IndexObj == null) {
                continue;
            }

            NumTranslit2 targetNT2 = nt2.get(targetNT2IndexObj.intValue());
            GraphTranslit1 targetGT1 =
                    getGT1AtSamePosition(targetNT2, sourceGT1ListIndex);
            if (targetGT1 == null) {
                continue;
            }

            for (String gt2Id : targetGT1.getGraphTranslit2()) {
                Integer targetGT2IndexObj = mgt2.get(gt2Id);
                if (targetGT2IndexObj == null) {
                    continue;
                }

                GraphTranslit2 targetGT2 =
                        gt2.get(targetGT2IndexObj.intValue());
                if (sourceSignature.equals(buildGT2VariantSignature(targetGT2))) {
                    targetGT2.setInterpretationConfidence(confidence);
                }
            }
        }
    }


    /**
     * Replace or create the GT2 at the same NT2/GT1/GT2 position in all
     * identical blocks in the other DocumentReadings.
     *
     * This is deliberately position-based, not signature-based.  After a TTL
     * reload the GT2 object identities and some generated ids are different;
     * matching by graph signature fails exactly when the user edits the graph
     * or changes confidence.  Position is the only stable relation in the
     * ALLMAH tree: same block -> same NT1 index -> same NT2 index -> same GT1
     * index -> same GT2 variant index.
     */
    private void synchronizeEditedGT2ToSameBlockInAllReadings(
            GraphTranslit2 sourceGT2
    ) {
        if (sourceGT2 == null || sourceGT2.getParent() == null) {
            return;
        }

        Integer sourceGT1IndexObj = mgt1.get(sourceGT2.getParent());
        if (sourceGT1IndexObj == null) {
            return;
        }

        GraphTranslit1 sourceGT1 = gt1.get(sourceGT1IndexObj.intValue());
        if (sourceGT1 == null || sourceGT1.getParent() == null) {
            return;
        }

        Integer sourceNT2IndexObj = mnt2.get(sourceGT1.getParent());
        if (sourceNT2IndexObj == null) {
            return;
        }

        NumTranslit2 sourceNT2 = nt2.get(sourceNT2IndexObj.intValue());
        if (sourceNT2 == null || sourceNT2.getParent() == null) {
            return;
        }

        Integer sourceNT1IndexObj = mnt1.get(sourceNT2.getParent());
        if (sourceNT1IndexObj == null) {
            return;
        }

        NumTranslit1 sourceNT1 = nt1.get(sourceNT1IndexObj.intValue());

        int sourceNT2ListIndex = sourceNT1.getNT2().indexOf(sourceNT2.getId());
        int sourceGT1ListIndex = sourceNT2.getGT1().indexOf(sourceGT1.getId());
        int sourceGT2ListIndex = sourceGT1.getGraphTranslit2().indexOf(sourceGT2.getId());

        if (sourceNT2ListIndex < 0 || sourceGT1ListIndex < 0 || sourceGT2ListIndex < 0) {
            return;
        }

        ArrayList<NumTranslit1> targetNT1s =
                getSameBlockSameNT1InAllReadings(sourceNT1);

        for (NumTranslit1 targetNT1 : targetNT1s) {
            if (targetNT1 == null || targetNT1 == sourceNT1) {
                continue;
            }

            NumTranslit2 targetNT2 =
                    ensureCorrespondingNT2ForTargetNT1(
                            sourceNT2,
                            targetNT1,
                            sourceNT2ListIndex
                    );

            if (targetNT2 == null) {
                continue;
            }

            GraphTranslit1 targetGT1 =
                    getGT1AtSamePosition(targetNT2, sourceGT1ListIndex);

            if (targetGT1 == null) {
                continue;
            }

            GraphTranslit2 targetCopy =
                    createGT2VariantForTargetGT1(sourceGT2, targetGT1);

            String targetId;
            if (targetGT1.getGraphTranslit2().size() > sourceGT2ListIndex) {
                targetId = targetGT1.getGraphTranslit2().get(sourceGT2ListIndex);
            }
            else {
                targetId = targetGT1.getId() + ">GT2_" + sourceGT2ListIndex;
                targetGT1.getGraphTranslit2().add(targetId);
            }

            targetCopy.setId(targetId);
            targetCopy.setNoVariants(sourceGT2.getNoVariants());
            targetCopy.setPathImage(sourceGT2.getPathImage());
            targetCopy.setInterpretationConfidence(sourceGT2.getInterpretationConfidence());
            targetCopy.setFinish(sourceGT2.getFinish());

            Integer existingIndex = mgt2.get(targetId);
            if (existingIndex == null) {
                gt2.add(targetCopy);
                mgt2.put(targetId, Integer.valueOf(gt2.size() - 1));
            }
            else {
                /*
                 * Do not replace the object in gt2 with a new instance here.
                 * After a TTL reload the visible tree nodes keep references to
                 * the previously imported GraphTranslit2 objects.  Replacing
                 * the list entry makes later edits act on a stale tree object,
                 * so the second edit appears in the GUI but is not saved.
                 *
                 * Keep the existing object identity and update its content in
                 * place.  This keeps tree, global list, and save/export model
                 * pointing to the same GraphTranslit2 instance.
                 */
                GraphTranslit2 existingGT2 = gt2.get(existingIndex.intValue());
                copyGT2ContentIntoExistingNode(existingGT2, targetCopy);
            }
        }

        changed = true;
    }

    private void copyGT2ContentIntoExistingNode(
            GraphTranslit2 target,
            GraphTranslit2 source
    ) {
        if (target == null || source == null) {
            return;
        }

        target.setNoVariants(source.getNoVariants());
        target.setPathImage(source.getPathImage());
        target.setInterpretationConfidence(source.getInterpretationConfidence());
        target.setFinish(source.getFinish());
        target.setElements(source.getElements());
        target.setGraph(source.getGraphGT2());
        target.setLabel(source.calculateLabel());
    }

    private NumTranslit2 ensureCorrespondingNT2ForTargetNT1(
            NumTranslit2 sourceNT2,
            NumTranslit1 targetNT1,
            int sourceNT2ListIndex
    ) {

        if (targetNT1.getNT2().size() > sourceNT2ListIndex) {
            Integer existingIndex =
                    mnt2.get(targetNT1.getNT2().get(sourceNT2ListIndex));

            if (existingIndex != null) {
                return nt2.get(existingIndex.intValue());
            }
        }

        if (!"var".equals(sourceNT2.getTyp())) {
            return null;
        }

        NumTranslit2 created =
                createNT2VariantForTargetNT1(sourceNT2, targetNT1);

        nt2.add(created);
        mnt2.put(
                created.getId(),
                new Integer(nt2.size() - 1)
        );

        targetNT1.getNT2().add(created.getId());
        created.generateGT1s("var");

        return created;
    }

    private GraphTranslit1 getGT1AtSamePosition(
            NumTranslit2 targetNT2,
            int sourceGT1ListIndex
    ) {

        if (targetNT2.getGT1().size() <= sourceGT1ListIndex) {
            return null;
        }

        Integer targetGT1Index =
                mgt1.get(targetNT2.getGT1().get(sourceGT1ListIndex));

        if (targetGT1Index == null) {
            return null;
        }

        return gt1.get(targetGT1Index.intValue());
    }

    private boolean hasCorrespondingGT2Variant(
            GraphTranslit1 targetGT1,
            GraphTranslit2 sourceVariant
    ) {

        String sourceSignature = buildGT2VariantSignature(sourceVariant);

        for (String gt2Id : targetGT1.getGraphTranslit2()) {

            Integer existingIndex = mgt2.get(gt2Id);
            if (existingIndex == null) {
                continue;
            }

            GraphTranslit2 existing = gt2.get(existingIndex.intValue());
            if (!"var".equals(existing.getTyp())) {
                continue;
            }

            if (sourceSignature.equals(buildGT2VariantSignature(existing))) {
                return true;
            }
        }

        return false;
    }

    private String buildGT2VariantSignature(GraphTranslit2 variant) {

        StringBuilder signature = new StringBuilder();

        for (GraphGT2Node node : variant.getElements()) {
            if (node == null || node.getGT2Element() == null) {
                signature.append("null;");
                continue;
            }
            signature.append(node.getGT2Element().getLabel()).append('|');
        }

        if (variant.getGraphGT2() != null) {
            for (OperatorLink edge : variant.getGraphGT2().getEdges()) {
                GraphGT2Node source = variant.getGraphGT2().getSource(edge);
                GraphGT2Node dest = variant.getGraphGT2().getDest(edge);
                signature.append(indexOfGT2Node(variant, source))
                        .append(edge.toString())
                        .append(indexOfGT2Node(variant, dest))
                        .append(';');
            }
        }

        return signature.toString();
    }

    private int indexOfGT2Node(
            GraphTranslit2 variant,
            GraphGT2Node node
    ) {
        return variant.getElements().indexOf(node);
    }

    private GraphTranslit2 createGT2VariantForTargetGT1(
            GraphTranslit2 sourceVariant,
            GraphTranslit1 targetGT1
    ) {

        String newGT2Id =
                targetGT1.getId()
                        + ">GT2_"
                        + targetGT1.getGraphTranslit2().size();

        GraphTranslit2 targetVariant =
                new GraphTranslit2(newGT2Id, "var", this);

        targetVariant.setPathImage(sourceVariant.getPathImage());
        targetVariant.setNoVariants(sourceVariant.getNoVariants());
        targetVariant.setInterpretationConfidence(
                sourceVariant.getInterpretationConfidence()
        );

        HashMap<GraphGT2Node, GraphGT2Node> nodeMap =
                new HashMap<GraphGT2Node, GraphGT2Node>();

        ArrayList<GraphGT2Node> targetElements =
                new ArrayList<GraphGT2Node>();

        for (int i = 0; i < sourceVariant.getElements().size(); i++) {

            GraphGT2Node sourceNode = sourceVariant.getElements().get(i);
            GT2Element sourceElement = sourceNode.getGT2Element();
            GT2Element targetElement;

            if (sourceElement != null
                    && sourceElement.getParentGT1() != null) {

                int elementIndex =
                        sourceGT1ElementIndex(
                                sourceVariant,
                                sourceElement.getParentGT1(),
                                i
                        );

                if (elementIndex >= 0
                        && targetGT1.getElements().size() > elementIndex) {
                    targetElement =
                            new GT2Element(
                                    targetGT1.getElements().get(elementIndex)
                            );
                }
                else {
                    targetElement = new GT2Element();
                }

                targetElement.setLabel(sourceElement.getLabel());
            }
            else if (sourceElement != null
                    && sourceElement.getParenComponent() != null) {

                ComponentElement sourceComponent =
                        sourceElement.getParenComponent();
                ComponentElement targetComponent =
                        new ComponentElement(sourceComponent.getcolor());
                targetComponent.setLabel1(sourceComponent.getLabel1());
                targetElement = new GT2Element(targetComponent);
            }
            else {
                targetElement = new GT2Element();
                if (sourceElement != null) {
                    targetElement.setLabel(sourceElement.getLabel());
                }
            }

            GraphGT2Node targetNode =
                    new GraphGT2Node(targetElement, sourceNode.getParent());

            targetVariant.getGraphGT2().addVertex(targetNode);
            targetElements.add(targetNode);
            nodeMap.put(sourceNode, targetNode);
        }

        if (sourceVariant.getGraphGT2() != null) {
            for (OperatorLink edge : sourceVariant.getGraphGT2().getEdges()) {

                GraphGT2Node sourceNode =
                        sourceVariant.getGraphGT2().getSource(edge);
                GraphGT2Node destNode =
                        sourceVariant.getGraphGT2().getDest(edge);

                GraphGT2Node targetSource = nodeMap.get(sourceNode);
                GraphGT2Node targetDest = nodeMap.get(destNode);

                if (targetSource != null && targetDest != null) {
                    targetVariant.getGraphGT2().addEdge(
                            edge.copy(),
                            targetSource,
                            targetDest
                    );
                }
            }
        }

        targetVariant.setElements(targetElements);
        targetVariant.setLabel(targetVariant.calculateLabel());

        return targetVariant;
    }

    private int sourceGT1ElementIndex(
            GraphTranslit2 sourceVariant,
            GT1Element sourceGT1Element,
            int fallbackIndex
    ) {

        Integer sourceGT1Index =
                mgt1.get(sourceVariant.getParent());

        if (sourceGT1Index != null) {
            GraphTranslit1 sourceGT1 =
                    gt1.get(sourceGT1Index.intValue());
            int index =
                    sourceGT1.getElements().indexOf(sourceGT1Element);
            if (index >= 0) {
                return index;
            }
        }

        if (fallbackIndex >= 0) {
            return fallbackIndex;
        }

        return -1;
    }
	//
	
	
	//
	public JDesktopPane getDesktop() {
		return desk;
	}
	public void addChild(ChildFrame child, int x, int y,int w,int h){
		 child.setLocation(x,y);
		child.setSize(w,h);
		 child.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 desk.add(child);
		 child.setVisible(true);
		}
	public void buildInterface() {	  
		trpos=new TreePoS();
		   trposf=new TreePoSFeatures();
		   //WordNET
			   try {
   				String wnhome = System . getenv ("WNHOME") ;
   				System.out.println("wnhome "+wnhome);
   				// String path ="D:\\ERCAdmin\\WordNET\\WNdb-3.0.tar\\WNdb-3.0" + File . separator + "dict";
  	//String path= new File("src/main/resources/WNdb-3.0.tar/WNdb-3.0").getCanonicalPath()+File . separator + "dict";;
	//String path= new File("src/main/resources/WNdb-3.0").getCanonicalPath()+File . separator + "dict";;;
	//allmahVer4.AllmahGUI.class.getResource("WNdb-3.0.tar.gz").getFile();
   			//	 u= allmahVer4.AllmahGUI.class.getClassLoader().getResource("WNdb-3.0.tar.gz");
   				//System.out.println("Location "+u);
   				InputStream input= allmahVer4.AllmahGUI.class.getResourceAsStream("WNdb-3.0.tar.gz");
   			
   	       File file = File.createTempFile("WNdb-3.0", ".tar.gz");
   	        OutputStream out = new FileOutputStream(file);
   	        int read;
   	        byte[] bytes = new byte[1024];

   	        while ((read = input.read(bytes)) != -1) {
   	            out.write(bytes, 0, read);
   	        }
   	        out.close();
   	      //  file.deleteOnExit();
   				String inputPath= file.getCanonicalPath();
   				
	System.out.println("INPUT "+ inputPath);
	 inputDir= file.getAbsoluteFile().getParent();
	
	System.out.println("INPUT Dir"+ inputDir);
	//String TAR_FOLDER = inputDir.substring(0,inputDir.indexOf(File.separator));
			//+File.separator+"TarFile";;
	String TAR_FOLDER = inputDir;
	System.out.println("TAR folder "+ TAR_FOLDER);
    // After untar files will go to this folder
    String DESTINATION_FOLDER =  inputDir.substring(0,inputDir.indexOf(File.separator))+File.separator+"tmp";
    System.out.println("DEST Folder "+DESTINATION_FOLDER);
  	//String path= new File("WN").getCanonicalPath()+File . separator + "dict";;
   					//   System.out.println("PATH WORDNET "+ path);
   					   /*
   					    * PATH WORDNET src/main/resources/WNdb-3.0.tar/WNdb-3.0
URL WORDNET file:src/main/resources/WNdb-3.0.tar/WNdb-3.0
PATH WORDNET D:\ERCAdmin\workspace\allmahVer4\src\main\resources\WNdb-3.0.tar\WNdb-3.0
URL WORDNET file:D:\ERCAdmin\workspace\allmahVer4\src\main\resources\WNdb-3.0.tar\WNdb-3.0

   					    */
    String path= DESTINATION_FOLDER+File . separator + "dict";;
    File fileo = new File(path);
    
    if (!fileo.isDirectory()) {
      
   
    UnTarClass cl=new UnTarClass(inputPath,DESTINATION_FOLDER);
   					File inputFile = new File(inputPath);
   			      String outputFile = cl.getFileName(inputFile, TAR_FOLDER);
   			      System.out.println("outputFile " + outputFile);
   			      File tarFile = new File(outputFile);
   			      // Calling method to decompress file
   			     tarFile = cl.deCompressGZipFile(inputFile, tarFile);
   			      System.out.println("tar generated");
   			      File destFile = new File(DESTINATION_FOLDER);
   			      if(!destFile.exists()){
   			        destFile.mkdir();
   			      }
   			      // Calling method to untar file
   			      cl.unTarFile(tarFile, destFile);    
   					  tarFile.deleteOnExit(); 
    }	
    else {System.out.println("Wordnet installed");}
   				System.out.println("URL path "+path);
   				 URL url = new URL ("file", null , path ) ;
   					// File f= new File("src/main/resources/WNdb-3.0.tar/WNdb-3.0");
   				   System.out.println("URL WORDNET "+url);
   				 dict = new edu.mit.jwi.Dictionary (url ) ;
   				// dict = new edu.mit.jwi.Dictionary (f ) ;
   			 dict.open() ;file.deleteOnExit();
			   }
   			 catch (Exception ex) { JOptionPane.showMessageDialog(desk,"Problems by opening Wordnet+URL "+ inputDir);
   				}
		   //
		  
	    desk=new JDesktopPane();
		desk.putClientProperty("JDesktopPane.dragMode", "outline");
		desk.setAutoscrolls(true);
		desk.setDesktopManager(new DefaultDesktopManager());
		desk.setAutoscrolls(true); desk.setFont(typFont1Menu1);	
		
		setContentPane(desk);
	    setResizable(true);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setSize(screenSize.width,screenSize.height);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new WindowClosingAdapter(true));
	    addComponentListener(new ComponentAdapter() {  
	    	public void componentResized(ComponentEvent e)  
	    	{  
	    	mainWin_componentResized(e);  
	    	}  
	    	   
	    	});  
	    JMenuBar menubar=new JMenuBar();
	    menubar.add(createFileMenu());
	    menubar.add(createConfigurationMenu());
	    setJMenuBar(menubar);
	    
	  show();
	}
	 public JMenu createConfigurationMenu(){
		 JMenu dateien=new JMenu ("Settings");
		 dateien.setFont(typFont1);
	 	 dateien.setMnemonic('S');
         JMenu configBlockAnn=new JMenu ("Configure Block Annotations");
         configBlockAnn.setFont(typFont1);
         configCallenderAnn=new JMenuItem ("Configure Callender Annotations");
          configCallenderAnn.setFont(typFont1);
          configCallenderAnn.addActionListener(this);
          configOnomasticsAnn=new JMenuItem("Configure Onomastics Annotations");
          configOnomasticsAnn.setFont(typFont1);
          configOnomasticsAnn.addActionListener(this);
	          configBlockAnn.add(configCallenderAnn);
	          configBlockAnn.add(configOnomasticsAnn);
	          session=new JMenuItem("Configure Session ID and DB-Log-In");
	         session.setFont(typFont1);
	         session.addActionListener(this);
	         dateien.add(session);
	          dateien.add(configBlockAnn);

	          dateien.addSeparator();
	          graphDBSettingsItem = new JMenuItem("GraphDB Settings...");
	          graphDBSettingsItem.setFont(typFont1);
	          graphDBSettingsItem.addActionListener(this);
	          dateien.add(graphDBSettingsItem);

	          connectGraphDBItem = new JMenuItem("Connect to GraphDB...");
	          connectGraphDBItem.setFont(typFont1);
	          connectGraphDBItem.addActionListener(this);
	          dateien.add(connectGraphDBItem);
		 return dateien;
	 }
	
	 public JMenu createFileMenu(){
	 	   JMenu dateien=new JMenu ("File");
	 	  dateien.setFont(typFont1);
	 	 dateien.setMnemonic('F');

	      JMenu openFile=new JMenu("Open");
	                      openFile.setMnemonic('O');
	                      openFile.setFont(typFont1);

	         openNewLocalFile=new JMenuItem ("Open local file...");
	         openNewLocalFile.setActionCommand("Open new local file");
	         openNewLocalFile.setFont(typFont1);
	          openNewLocalFile.addActionListener(this);
	          openNewLocalFile.setEnabled(false);
	          openFile.add(openNewLocalFile);

	          openLocalTtlFile=new JMenuItem ("Open local TTL file...");
	          openLocalTtlFile.setActionCommand("Open local TTL file");
	          openLocalTtlFile.setFont(typFont1);
	          openLocalTtlFile.addActionListener(this);
	          openLocalTtlFile.setEnabled(true);
	          openFile.add(openLocalTtlFile);

	          openNewGridFile=new JMenuItem ("Open TextGrid file...");
	          openNewGridFile.setActionCommand("Open new grid file");
		         openNewGridFile.setFont(typFont1);
		          openNewGridFile.addActionListener(this);
		          openNewGridFile.setEnabled(false);
		          openFile.add(openNewGridFile);

		          openFromGraphDBFile=new JMenuItem ("Open from GraphDB...");
		          openFromGraphDBFile.setActionCommand("Open from GraphDB");
		          openFromGraphDBFile.setFont(typFont1);
		          openFromGraphDBFile.addActionListener(this);
		          openFromGraphDBFile.setEnabled(false);
		          openFile.add(openFromGraphDBFile);

	      JMenu saveMenu = new JMenu("Save");
	      saveMenu.setMnemonic('S');
	      saveMenu.setFont(typFont1);

	     saveFile=new JMenuItem("Save to local file...");
	     saveFile.setActionCommand("Save");
	     saveFile.setMnemonic('L');
	     saveFile.setFont(typFont1);
	     saveFile.addActionListener(this);
	     saveFile.setEnabled(false);
	     saveMenu.add(saveFile);

	     saveToGraphDBFile=new JMenuItem("Save to GraphDB...");
	     saveToGraphDBFile.setActionCommand("Save to GraphDB");
	     saveToGraphDBFile.setMnemonic('G');
	     saveToGraphDBFile.setFont(typFont1);
	     saveToGraphDBFile.addActionListener(this);
	     saveToGraphDBFile.setEnabled(false);
	     saveMenu.add(saveToGraphDBFile);

		          dateien.add(openFile);
		          dateien.add(saveMenu);
	 	   return dateien;
	 }	   
	
	 public void actionPerformed(ActionEvent event){
	 		
	 		String e=event.getActionCommand();
	 		if(e.equals("Configure Session ID and DB-Log-In")) {
	 			 sessionID = JOptionPane.showInputDialog("Insert Session ID");
	 			
		       
		          if (sessionID != null) {
		        	  sid = sessionID
		        		        .replace('\u00A0', ' ')
		        		        .replaceAll("\\s+", "")
		        		        .trim();
		            System.out.println("Session heute ID [" + sid + "]");
		            System.out.println("SID length: " + sid.length());
		            
		          //  while(db==null) {
		          //  loginDB = JOptionPane.showInputDialog("Enter OrientDB User name ");
		           // JPasswordField pf = new JPasswordField();
		            //int okCxl = JOptionPane.showConfirmDialog(desk, pf, "Enter  OrientDB Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		            //if (okCxl == JOptionPane.OK_OPTION) {
		            	//passwordDB = new String(pf.getPassword());
		              //System.out.println("You entered: " + passwordDB);
		           // }
		           // passwordDB= JOptionPane.showInputDialog("Insert OrientDB Password ");
		          //  OrientDB orient = new OrientDB("remote:classicmayan.org", OrientDBConfig.defaultConfig());
	 	              // db = orient.open("allmahDB", "admin", "admin");
	 	            // db = orient.open("classicmayan", loginDB, passwordDB);
	 	             //if(db==null) JOptionPane.showMessageDialog(desk,"User Name or Password wrong"); 
		           // }
		            openNewLocalFile.setEnabled(true);
		            openNewGridFile.setEnabled(true);
		            if (openFromGraphDBFile != null) openFromGraphDBFile.setEnabled(graphDBConnected);
		          }
		          else
		            return;
	 		}
	 		if (e.equals("GraphDB Settings...")) {
	 			showGraphDBSettingsDialog();
	 		}
	 		else if (e.equals("Connect to GraphDB...")) {
	 			GraphDBConnectionDialog dialog =
	 			        new GraphDBConnectionDialog(this, graphDBConfig);
	 			dialog.setVisible(true);
	 			graphDBConnected = dialog.isConnected();
	 			updateGraphDBMenuState();
	 		}
	 		else if(e.equals("Configure Onomastics Annotations")) {
	 			ChildFrame  owin= new ChildFrame("Configuration of Onomastics Annotation  " ,Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
				Container cfin=owin.getContentPane();
				cfin.setLayout(new BorderLayout());
				final JColorChooser colorChooser = new JColorChooser(); // default color is black
        		colorChooser.setBorder(null);
        		colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
        		    public void stateChanged(ChangeEvent e) {		    
        		    	colO=colorChooser.getSelectionModel().getSelectedColor();      		    	
        		    }
        		});
        		 modelO = new DefaultListModel();
        		  listO = new JList(modelO);
        		 for (int i=0;i<onomasticValues.size();i++)
        			 modelO.addElement(onomasticValues.get(i));      		 
        		  listO.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		 listO.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        		 listO.addMouseListener(new MouseAdapter() {
        		      public void mouseClicked(MouseEvent e) {
        		        if (e.getClickCount() == 2) {
        		          int index = listO.locationToIndex(e.getPoint());
        		          Object item = modelO.getElementAt(index);
        		          String text = JOptionPane.showInputDialog("Rename item", item);
        		          String newitem = "";
        		          if (text != null)
        		            newitem = text.trim();
        		          else
        		            return;

        		          if (!newitem.isEmpty()) {
        		            modelO.remove(index);
        		            onomasticValues.remove(index);
        		            modelO.add(index, newitem);
        		            onomasticValues.add(index,newitem);
        		            ListSelectionModel selmodel = listO.getSelectionModel();
        		            selmodel.setLeadSelectionIndex(index);
        		          }
        		        }
        		      }
        		    });
        		 final JButton insertO=new JButton("Insert Onomastic Item");
        		 final JTextField itemO=new JTextField(50);
        		 final JButton deleteO=new JButton("Delete Selected Item");
        		 JPanel operations =new JPanel(new GridLayout(3,1));
        		 operations.add(itemO);
        		 operations.add(insertO);operations.add(deleteO);
        		 insertO.addActionListener(new ActionListener() {
        			 public void actionPerformed(ActionEvent e) {
        				 String c=itemO.getText();
        				   if (!c.isEmpty()) {
           		            modelO.add(0, c);
           		            onomasticValues.add(0,c);
           		            ListSelectionModel selmodel = listO.getSelectionModel();
           		            selmodel.setLeadSelectionIndex(0);
           		          }
        			 }
        		 });
        		 deleteO.addActionListener(new ActionListener() {
        			 public void actionPerformed(ActionEvent e) {
        				int  index=listO.getSelectedIndex();
        						  modelO.remove(index);
    		            onomasticValues.remove(index);
    		            ListSelectionModel selmodel = listO.getSelectionModel();
    		            selmodel.setLeadSelectionIndex(index);
        			 }
        		 });
        		 cfin.add(new JScrollPane(listO), BorderLayout.WEST ); cfin.add(operations, BorderLayout.CENTER );cfin.add(colorChooser,BorderLayout.EAST);
        		 owin.pack();
 				owin.moveToFront();
 				this.addChild(owin,50, 10, 1000, 300);
 				
	 		
	 		}
	 		if (e.equals("Configure Callender Annotations")){
	 		
	 			ChildFrame  calwin= new ChildFrame("Configuration of Callender Annotation  " ,Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
				Container cfin=calwin.getContentPane();
				cfin.setLayout(new BorderLayout());
				final JColorChooser colorChooser = new JColorChooser(); // default color is black
        		colorChooser.setBorder(null);
        		colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
        		    public void stateChanged(ChangeEvent e) {		    
        		    	colC=colorChooser.getSelectionModel().getSelectedColor();      		    	
        		    }
        		});
        		 modelC = new DefaultListModel();
        		  listC = new JList(modelC);
        		 for (int i=0;i<calenderValues.size();i++)
        			 modelC.addElement(calenderValues.get(i));      		 
        		  listC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		 listC.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        		 listC.addMouseListener(new MouseAdapter() {
        		      public void mouseClicked(MouseEvent e) {
        		        if (e.getClickCount() == 2) {
        		          int index = listC.locationToIndex(e.getPoint());
        		          Object item = modelC.getElementAt(index);
        		          String text = JOptionPane.showInputDialog("Rename item", item);
        		          String newitem = "";
        		          if (text != null)
        		            newitem = text.trim();
        		          else
        		            return;

        		          if (!newitem.isEmpty()) {
        		            modelC.remove(index);
        		            calenderValues.remove(index);
        		            modelC.add(index, newitem);
        		            calenderValues.add(index,newitem);
        		            ListSelectionModel selmodel = listC.getSelectionModel();
        		            selmodel.setLeadSelectionIndex(index);
        		          }
        		        }
        		      }
        		    });
        		 final JButton insertC=new JButton("Insert Calender Item");
        		 final JTextField itemC=new JTextField(50);
        		 final JButton deleteC=new JButton("Delete Selected Item");
        		 JPanel operations =new JPanel(new GridLayout(3,1));
        		 operations.add(itemC);
        		 operations.add(insertC);operations.add(deleteC);
        		 insertC.addActionListener(new ActionListener() {
        			 public void actionPerformed(ActionEvent e) {
        				 String c=itemC.getText();
        				   if (!c.isEmpty()) {
           		            modelC.add(0, c);
           		            calenderValues.add(0,c);
           		            ListSelectionModel selmodel = listC.getSelectionModel();
           		            selmodel.setLeadSelectionIndex(0);
           		          }
        			 }
        		 });
        		 deleteC.addActionListener(new ActionListener() {
        			 public void actionPerformed(ActionEvent e) {
        				int  index=listC.getSelectedIndex();
        						  modelC.remove(index);
    		            calenderValues.remove(index);
    		            ListSelectionModel selmodel = listC.getSelectionModel();
    		            selmodel.setLeadSelectionIndex(index);
        			 }
        		 });
        		 cfin.add(new JScrollPane(listC), BorderLayout.WEST ); cfin.add(operations, BorderLayout.CENTER );cfin.add(colorChooser,BorderLayout.EAST);
        		 calwin.pack();
 				calwin.moveToFront();
 				this.addChild(calwin,50, 10, 1000, 300);
 				
	 		}
	 		else if (e.equals("Open local TTL file")){
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                final File rdfFile = chooser.getSelectedFile();

                runOpenTask(
                        "Opening local TTL/RDF file...",
                        new Runnable() {
                            public void run() {
                                try {
                                    AllmahRdfImporter importer =
                                            new AllmahRdfImporter();

                                    final AllmahRdfImporter.ImportedDocument imported =
                                            importer.importFromTurtleFile(rdfFile);

                                    rebuildGuiStateFromImportedRdf(imported);
                                    addImportedGT2ToGT1Nodes();

                                    SwingUtilities.invokeLater(
                                            new Runnable() {
                                                public void run() {
                                                    ChildFrame mainwin =
                                                            new ChildFrame(
                                                                    "Annotate " + imported.title,
                                                                    Color.gray,
                                                                    WindowConstants.DISPOSE_ON_CLOSE
                                                            );

                                                    Container cfin =
                                                            mainwin.getContentPane();

                                                    blockTree = builtAnnTree();
                                                    sc = new JScrollPane(blockTree);

                                                    cfin.add(sc);

                                                    mainwin.pack();
                                                    mainwin.moveToFront();

                                                    AllmahGUI.this.addChild(
                                                            mainwin,
                                                            20,
                                                            20,
                                                            desk.getWidth() - 400,
                                                            desk.getHeight() - 100
                                                    );

                                                    saveFile.setEnabled(true);
                                                    updateGraphDBMenuState();
                                                }
                                            }
                                    );

                                } catch (final Exception ex) {
                                    ex.printStackTrace();
                                    SwingUtilities.invokeLater(
                                            new Runnable() {
                                                public void run() {
                                                    JOptionPane.showMessageDialog(
                                                            AllmahGUI.this,
                                                            "Could not open local TTL/RDF file:\n" + ex.getMessage()
                                                    );
                                                }
                                            }
                                    );
                                }
                            }
                        }
                );
            }
        }
	 		else if (e.equals("Open from GraphDB")){
            openDocumentFromGraphDB();
        }
	 		else if (e.equals("Open new local file")){
            final LocalReadFile lrf = new LocalReadFile(this);
            final File chosenFile = lrf.getChoosedFile();

            runOpenTask(
                    "Opening local file...",
                    new Runnable() {
                        public void run() {
                            try {
                                final MayaDocumentParser parser =
                                        new MayaDocumentParser(
                                                AllmahGUI.this,
                                                sid,
                                                chosenFile,
                                                typFont1
                                        );

                                parser.getMayaDocumentTitle();
                                hb = parser.getMayaDocumentParts();
                                docr = new ArrayList<DocumentReading>();
                                gt1 = new ArrayList<GraphTranslit1>();
                                gt2 = new ArrayList<GraphTranslit2>();
                                pt = new ArrayList<PhonemTranslit>();
                                mt = new ArrayList<MorphoSyntTranslit>();
                                mtr = new ArrayList<FinalTranslation>();
                                translationNodes = new ArrayList<TranslationNode>();
                                mtranslationNodes = new HashMap<String,Integer>();
                                mg = new ArrayList<MorphoSyntGlossing>();
                                nt2 = new ArrayList<NumTranslit2>();
                                nt1 = new ArrayList<NumTranslit1>();
                                saveFile.setEnabled(true);
                                doc = new HyerogliphenDocument(parser.docID, AllmahGUI.this);
                                updateGraphDBMenuState();

                                String user = "unknown";
                                try {
                                    TGSearchQueries tgs =
                                            new TGSearchQueries(
                                                    "src/main/resources/idiomQueries-SECRET-prod.properties"
                                            );
                                    tgs.setSessionID(sid);
                                    user = tgs.getNameBySid(sid);
                                } catch (Exception exs) {
                                    System.out.println("USer not fund");
                                }

                                DocumentReading dr =
                                        new DocumentReading(doc.getId(), AllmahGUI.this);

                                docr.add(dr);
                                mdocr.put(dr.getId(), new Integer(0));
                                doc.getDocReadings().add(dr.getId());

                                for (int i = 0; i < hb.size(); i++) {
                                    mhb.put(hb.get(i).getBlockID(), new Integer(i));
                                    if (!(hb.get(i).getLabel()).equals("#")) {
                                        hb.get(i).generateNT1();
                                    }
                                    dr.getBlocks().add(hb.get(i).getBlockID());
                                }

                                final String userFinal = user;

                                SwingUtilities.invokeLater(
                                        new Runnable() {
                                            public void run() {
                                                ChildFrame mainwin =
                                                        new ChildFrame(
                                                                "Annotate "
                                                                + parser.getMayaDocumentTitle()
                                                                + "     User: "
                                                                + userFinal,
                                                                Color.gray,
                                                                WindowConstants.DISPOSE_ON_CLOSE
                                                        );

                                                Container cfin = mainwin.getContentPane();
                                                blockTree = builtAnnTree();
                                                sc = new JScrollPane(blockTree);

                                                cfin.add(sc);
                                                mainwin.pack();
                                                mainwin.moveToFront();

                                                AllmahGUI.this.addChild(
                                                        mainwin,
                                                        20,
                                                        20,
                                                        desk.getWidth() - 400,
                                                        desk.getHeight() - 100
                                                );

                                                saveFile.setEnabled(true);
                                                    updateGraphDBMenuState();
                                            }
                                        }
                                );

                            } catch (final Exception ex) {
                                ex.printStackTrace();
                                SwingUtilities.invokeLater(
                                        new Runnable() {
                                            public void run() {
                                                JOptionPane.showMessageDialog(
                                                        AllmahGUI.this,
                                                        "Could not open local file:\n" + ex.getMessage()
                                                );
                                            }
                                        }
                                );
                            }
                        }
                    }
            );
        }
	 		else if (e.equals("Open new grid file")){
			runOpenTask(
			        "Opening TextGrid file list...",
			        new Runnable() {
			            public void run() {

	 			
	 			TextGridReadFile tgr=new TextGridReadFile(sid);
	 			Map<String,String> nameFiles=tgr.fileList();
	 			System.out.println ("Map size "+ nameFiles.size());
	 			 int noFiles=nameFiles.size();
	 		      //System.out.println("No of fies "+noFiles);
	 		       final  JEditorPane editorPane=new JEditorPane();
	 		     // editorPane.setEditorKit(new XMLEditorKit());
	 		       // editorPane.setSize(100, noFiles);
	 		       final  ChildFrame fileFrame= new ChildFrame("Select File to Annotate", Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
	 		    final    JRadioButton [] fileNameRB =new JRadioButton[noFiles];

	 		      int   ind=0;
	 		       ButtonGroup bgrp=new ButtonGroup();
	 		       JPanel filesPanel=new JPanel(new GridBagLayout());
	 		        GridBagConstraints gbc=new GridBagConstraints();
	 	    		gbc.gridx=0;
	 	    		gbc.gridy=0;
	 	    		gbc.fill=GridBagConstraints.BOTH;
	 	    		gbc.anchor=GridBagConstraints.NORTHWEST;
	 	    		//gbc.insets.bottom=2;
	 		        for (String  s   : nameFiles.keySet()) {
	 		          //  System.out.println(s+ " "+test.get(s));  //get() is less efficient 
	 		            fileNameRB[ind] =new JRadioButton(s+ " "+nameFiles.get(s));
	 		         
	 		            bgrp.add(fileNameRB[ind]);
	 		            gbc.gridy=ind;
	 		            filesPanel.add(fileNameRB[ind],gbc);
	 		            ind=ind+1;
	 			
	 		}
	 		       final  Container c=fileFrame.getContentPane();
	 		        c.setLayout(new GridBagLayout());
	 		       JScrollPane filesPaneScroll=new JScrollPane(filesPanel);
	 		       
	 		       JScrollPane editPaneScroll=new JScrollPane(editorPane);
	 		       editPaneScroll.setVerticalScrollBarPolicy(
	 		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	 		editPaneScroll.setPreferredSize(new Dimension(350, 350));
	 		       GridBagConstraints gbc1=new GridBagConstraints();
	 		       
	 	   		gbc1.gridx=0;
	 	   		gbc1.gridy=0;
	 	   		gbc1.weightx=100;
	 	   	gbc1.weighty=100;
	 	   		
	 	   		gbc1.gridwidth=2;
	 	   		gbc1.anchor=GridBagConstraints.NORTHWEST;
	 	        gbc1.fill=GridBagConstraints.BOTH;
	 		       docs=new String[noFiles];
	 		       
	 		       c.add(filesPaneScroll,gbc1);
	 		      for ( int i=0;i<noFiles;i++) {
	 		        	//System.out.println(i);
	 		        //	System.out.println(fileNameRB[i].getText());
	 		    	
	 	               docs[i]= fileNameRB[i].getText().substring(0, fileNameRB[i].getText().indexOf(" "));
	 	               selName=docs[i];
	 	               // editorPane.setEditorKit(new XMLEditorKit());
	 		     	     final int x=i;
	 		            fileNameRB[i].addItemListener(new ItemListener() {
	 		            	public void itemStateChanged(ItemEvent e) {
	 		            		//   String  selectedDocument="";
	 		            		if (((JRadioButton) e.getSource()).isSelected()){
	 		            		   try {
	 		            			 
	 	                             //String  selectedDocument="";
	 		            		  IdiomTextGridObject textGridObject = new IdiomTextGridObject("src/main/resources/idiomQueries-SECRET-prod.properties");
	 		            		// IDIOMTextGridObject   textGridObject = new IDIOMTextGridObject();
	 		            			
	 		            	 //System.out.println("URI "+textGridObject.generateFakeTextGridURI());
	 		            		       selectedDocument= textGridObject.getDataAsString(docs[x]);	
	 		            		      System.out.println("Trying TextGrid URI: " + docs[x]);
	 		            		     System.out.println("Loaded length: " + selectedDocument.length());
	 		            		     System.out.println("START:");
	 		            		     System.out.println(selectedDocument.substring(0, Math.min(500, selectedDocument.length())));selectedDoc=textGridObject.getDataAsXMLObject(docs[x]);
	 		            		     System.out.println(selectedDocument);
	 		            		       //editorPane.setEditorKit(new XMLEditorKit());
	 		            			   editorPane.setText(selectedDocument); editorPane.revalidate();editorPane.repaint();
	 		            		        c.repaint();c.revalidate();
	 		            		        
	 		                	        }
	 		                	        catch(IOException e1) {System.out.println("File not found");}
	 		                	        catch(CrudClientException e2) {System.out.println("CRUD Error");}
	 		            		  catch(Exception e3) {System.out.println("SAX Parser Error"); e3.printStackTrace();}
	 		            		}
	 		            		else {
	 		            			
	 		            		}
	 		            	}
	 		            });
	 		        }
	 		       gbc1.gridx=2;
	 		 	     gbc1.gridheight=noFiles;
	 		 	    c.add(editPaneScroll,gbc1);
	 		 	    JButton okB=new JButton ("OK ");
	 		 	    JButton cancelB= new JButton("Cancel");
	 		 	    cancelB.addActionListener(new ActionListener() {
	 		 	    	public void actionPerformed(ActionEvent e) {
	 		 	    		fileFrame.doDefaultCloseAction();
	 		 	    	}
	 		 	    });
	 		 	    //
	 		 	 okB.addActionListener(new ActionListener() {
	 		 	    	public void actionPerformed(ActionEvent e) {
	 		 	    		fileFrame.doDefaultCloseAction();

	 		 	    		runOpenTask(
	 		 	    				"Opening selected TextGrid file...",
	 		 	    				new Runnable() {
	 		 	    					public void run() {
	 		 	    						try {
	 		 	    							final AllmahGUI interf = getInterf();

	 		 	    							final MayaDocumentParser parser =
	 		 	    									new MayaDocumentParser(
	 		 	    											interf,
	 		 	    											sid,
	 		 	    											(org.jdom2.Document) selectedDoc,
	 		 	    											selName,
	 		 	    											typFont1
	 		 	    									);

	 		 	    							parser.getMayaDocumentTitle();

	 		 	    							hb = parser.getMayaDocumentParts();
	 		 	    							docr = new ArrayList<DocumentReading>();
	 		 	    							gt1 = new ArrayList<GraphTranslit1>();
	 		 	    							gt2 = new ArrayList<GraphTranslit2>();
	 		 	    							pt = new ArrayList<PhonemTranslit>();
	 		 	    							mt = new ArrayList<MorphoSyntTranslit>();
	 		 	    							nt2 = new ArrayList<NumTranslit2>();
	 		 	    							nt1 = new ArrayList<NumTranslit1>();
	 		 	    							mtr = new ArrayList<FinalTranslation>();
	 		 	    							mg = new ArrayList<MorphoSyntGlossing>();

	 		 	    							doc = new HyerogliphenDocument(parser.docID, interf);

	 		 	    							DocumentReading dr =
	 		 	    									new DocumentReading(doc.getId(), interf);

	 		 	    							docr.add(dr);
	 		 	    							mdocr.put(dr.getId(), new Integer(0));
	 		 	    							doc.getDocReadings().add(dr.getId());

	 		 	    							for (int i = 0; i < hb.size(); i++) {
	 		 	    								mhb.put(hb.get(i).getBlockID(), new Integer(i));
	 		 	    								if (!(hb.get(i).getLabel()).equals("#")) {
	 		 	    									hb.get(i).generateNT1();
	 		 	    								}
	 		 	    								dr.getBlocks().add(hb.get(i).getBlockID());
	 		 	    							}

	 		 	    							SwingUtilities.invokeLater(
	 		 	    									new Runnable() {
	 		 	    										public void run() {
	 		 	    											ChildFrame mainwin =
	 		 	    													new ChildFrame(
	 		 	    															"Annotate " + parser.getMayaDocumentTitle(),
	 		 	    															Color.gray,
	 		 	    															WindowConstants.DISPOSE_ON_CLOSE
	 		 	    													);

	 		 	    											Container cfin = mainwin.getContentPane();

	 		 	    											blockTree = builtAnnTree();
	 		 	    											sc = new JScrollPane(blockTree);

	 		 	    											cfin.add(sc);
	 		 	    											mainwin.pack();
	 		 	    											mainwin.moveToFront();

	 		 	    											interf.addChild(
	 		 	    													mainwin,
	 		 	    													20,
	 		 	    													20,
	 		 	    													desk.getWidth() - 400,
	 		 	    													desk.getHeight() - 100
	 		 	    											);

	 		 	    											saveFile.setEnabled(true);
	 		 	    											updateGraphDBMenuState();
	 		 	    										}
	 		 	    									}
	 		 	    							);

	 		 	    						} catch (final Exception ex) {
	 		 	    							ex.printStackTrace();
	 		 	    							SwingUtilities.invokeLater(
	 		 	    									new Runnable() {
	 		 	    										public void run() {
	 		 	    											JOptionPane.showMessageDialog(
	 		 	    													AllmahGUI.this,
	 		 	    													"Could not open TextGrid file:\n"
	 		 	    															+ ex.getMessage()
	 		 	    											);
	 		 	    										}
	 		 	    									}
	 		 	    							);
	 		 	    						}
	 		 	    					}
	 		 	    				}
	 		 	    		);
	 		 	    	}
	 		 	    });

JPanel buttonPanel=new JPanel(new GridBagLayout());
	 		 	   GridBagConstraints gbc2=new GridBagConstraints();
	 		       
	 		   		gbc2.gridx=0;
	 		   		gbc2.gridy=0;
	 		   		gbc2.weightx=100;
	 		   	gbc2.weighty=100;
	 		   		
	 		   		gbc2.anchor=GridBagConstraints.CENTER;
	 		        gbc2.fill=GridBagConstraints.NONE;
	 		        buttonPanel.add(okB,gbc2); gbc2.gridx=1; buttonPanel.add(cancelB,gbc2);
	 			       gbc1.gridy=noFiles+1;   gbc1.gridx=0;
	 			       gbc1.gridwidth=4;;gbc1.gridheight=1;
	 			       gbc1.fill=GridBagConstraints.BOTH;
	 			       c.add(buttonPanel,gbc1);
	 		 	    
	 		       
	 		       fileFrame.pack();
	 	    		addChild(fileFrame,30,30,700,700);
	 	    		
	 	               fileFrame.setVisible(true);
	 	               fileFrame.moveToFront();
	 		
			            }
			        }
			);
		}
	 		else if (e.equals("Save to GraphDB")){
	 			saveCurrentDocumentToGraphDB();
	 		}
	 		else if (e.equals("Save")){
	 		//	OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
	 		   // ODatabaseSession db = orient.open("almah", "admin", "admin");
	 			//System.out.println("Save");
       // OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
        //OrientDB orient = new OrientDB("remote:classicmayan.org", OrientDBConfig.defaultConfig());
              // db = orient.open("allmahDB", "admin", "admin");
               //db = orient.open("classicmayan", "functionUser", "id!oM{4}Tg/4");
               //System.out.println("Opended "+db.getName());
               //doc.insertInDB();
	 		    //db.close();    
	 		//    orient.close();
	 			try {
	 			    synchronizeVisibleGT2TreeStateBackToModel();
	 			    AllmahTurtleWriter writer = new AllmahTurtleWriter();

	 			    writer.exportDocument(
	 			            doc.getId(),
	 			            doc.getTitle(),
	 			            hb,
	 			            this,
	 			            new File("allmah-real-export-GT2.ttl")
	 			    );

	 			    System.out.println("RDF export finished.");

	 			} catch (IOException e1) {
	 			    e1.printStackTrace();
	 			}
	 		}
	 }


    private void openDocumentFromGraphDB() {
        if (!graphDBConnected) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please connect to GraphDB first."
            );
            return;
        }

        try {
            final GraphDBReader reader = new GraphDBReader(graphDBConfig);
            final java.util.List<GraphDBReader.GraphDBDocumentInfo> documents =
                    reader.listDocuments();

            if (documents == null || documents.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "No ALLMAH documents found in GraphDB."
                );
                return;
            }

            final JList documentList = new JList(
                    documents.toArray(
                            new GraphDBReader.GraphDBDocumentInfo[documents.size()]
                    )
            );
            documentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            documentList.setVisibleRowCount(12);
            documentList.setSelectedIndex(0);

            JScrollPane scroll = new JScrollPane(documentList);
            scroll.setPreferredSize(new Dimension(650, 300));

            int result = JOptionPane.showConfirmDialog(
                    this,
                    scroll,
                    "Open document from GraphDB",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result != JOptionPane.OK_OPTION) {
                return;
            }

            final GraphDBReader.GraphDBDocumentInfo selected =
                    (GraphDBReader.GraphDBDocumentInfo) documentList.getSelectedValue();

            if (selected == null) {
                return;
            }

            runOpenTask(
                    "Opening document from GraphDB...",
                    new Runnable() {
                        public void run() {
                            try {
                                File rdfFile =
                                        reader.loadDocumentGraphAsTempTurtle(selected);

                                AllmahRdfImporter importer =
                                        new AllmahRdfImporter();

                                final AllmahRdfImporter.ImportedDocument imported =
                                        importer.importFromTurtleFile(rdfFile);

                                rebuildGuiStateFromImportedRdf(imported);
                                addImportedGT2ToGT1Nodes();

                                SwingUtilities.invokeLater(
                                        new Runnable() {
                                            public void run() {
                                                ChildFrame mainwin =
                                                        new ChildFrame(
                                                                "Annotate " + imported.title,
                                                                Color.gray,
                                                                WindowConstants.DISPOSE_ON_CLOSE
                                                        );

                                                Container cfin =
                                                        mainwin.getContentPane();

                                                blockTree = builtAnnTree();
                                                sc = new JScrollPane(blockTree);

                                                cfin.add(sc);

                                                mainwin.pack();
                                                mainwin.moveToFront();

                                                AllmahGUI.this.addChild(
                                                        mainwin,
                                                        20,
                                                        20,
                                                        desk.getWidth() - 400,
                                                        desk.getHeight() - 100
                                                );

                                                saveFile.setEnabled(true);
                                                updateGraphDBMenuState();
                                            }
                                        }
                                );

                            } catch (final Exception ex) {
                                ex.printStackTrace();
                                SwingUtilities.invokeLater(
                                        new Runnable() {
                                            public void run() {
                                                JOptionPane.showMessageDialog(
                                                        AllmahGUI.this,
                                                        "Could not open document from GraphDB:\n"
                                                                + ex.getMessage(),
                                                        "GraphDB open failed",
                                                        JOptionPane.ERROR_MESSAGE
                                                );
                                            }
                                        }
                                );
                            }
                        }
                    }
            );

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Could not list GraphDB documents:\n" + ex.getMessage(),
                    "GraphDB open failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    private void updateGraphDBMenuState() {
        boolean documentOpen = doc != null;
        if (openFromGraphDBFile != null) {
            openFromGraphDBFile.setEnabled(graphDBConnected);
        }
        if (saveToGraphDBFile != null) {
            saveToGraphDBFile.setEnabled(graphDBConnected && documentOpen);
        }
    }

    private void showGraphDBSettingsDialog() {
        JTextField urlField = new JTextField(graphDBConfig.getServerUrl(), 40);
        JTextField repoField = new JTextField(graphDBConfig.getRepositoryId(), 25);
        JTextField graphPrefixField = new JTextField(graphDBConfig.getDocumentGraphPrefix(), 40);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("GraphDB URL:"));
        panel.add(urlField);
        panel.add(new JLabel("Repository:"));
        panel.add(repoField);
        panel.add(new JLabel("Document graph prefix:"));
        panel.add(graphPrefixField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "GraphDB Settings",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            graphDBConfig.setServerUrl(urlField.getText());
            graphDBConfig.setRepositoryId(repoField.getText());
            graphDBConfig.setDocumentGraphPrefix(graphPrefixField.getText());
            graphDBConnected = false;
            updateGraphDBMenuState();
            JOptionPane.showMessageDialog(
                    this,
                    "GraphDB settings saved. Please connect again."
            );
        }
    }

    private void saveCurrentDocumentToGraphDB() {
        if (!graphDBConnected) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please connect to GraphDB first."
            );
            return;
        }
        if (doc == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No document is open."
            );
            return;
        }

        try {
            synchronizeVisibleGT2TreeStateBackToModel();

            File tempTtl = File.createTempFile("allmah-graphdb-export-", ".ttl");
            tempTtl.deleteOnExit();

            AllmahTurtleWriter writer = new AllmahTurtleWriter();
            writer.exportDocument(
                    doc.getId(),
                    doc.getTitle(),
                    hb,
                    this,
                    tempTtl
            );
            String graphUri = graphDBConfig.documentGraphUri(doc.getId());
            GraphDBWriter graphDBWriter = new GraphDBWriter(graphDBConfig);
            GraphDBWriter.UploadReport report =
                    graphDBWriter.uploadTurtleFile(tempTtl, graphUri);

            StringBuilder msg = new StringBuilder();
            msg.append("Saved document to GraphDB.\n\n");
            msg.append("Repository: ").append(graphDBConfig.getRepositoryId()).append("\n");
            msg.append("Named graph: ").append(report.getGraphUri()).append("\n");
            msg.append("Global reading URIs found in TTL: ")
               .append(report.getReadingUris().size())
               .append("\n");
            msg.append("Already present in GraphDB: ")
               .append(report.getExistingReadingUris().size())
               .append("\n");
            msg.append("New reading URIs in this upload: ")
               .append(report.getMissingReadingCount())
               .append("\n");
            if (report.getInspectionWarning() != null) {
                msg.append("\nPreflight warning: ")
                   .append(report.getInspectionWarning());
            }

            JOptionPane.showMessageDialog(this, msg.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Could not save to GraphDB:\n" + ex.getMessage(),
                    "GraphDB save failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

	private void runOpenTask(
	        final String message,
	        final Runnable task
	) {
	    final JDialog progressDialog =
	            createOpenProgressDialog(message);

	    SwingUtilities.invokeLater(
	            new Runnable() {
	                public void run() {
	                    progressDialog.setVisible(true);
	                }
	            }
	    );

	    Thread worker =
	            new Thread(
	                    new Runnable() {
	                        public void run() {
	                            try {
	                                try {
	                                    Thread.sleep(100);
	                                } catch (InterruptedException ignored) {
	                                }

	                                task.run();
	                            } finally {
	                                closeOpenProgressDialog(progressDialog);
	                            }
	                        }
	                    },
	                    "ALLMAH-open-worker"
	            );

	    worker.start();
	}



    private void maybeShowReadingConfidencePopup(
            MouseEvent ev,
            Reading reading,
            JCheckBox checkbox
    ) {
        if (!ev.isPopupTrigger()) {
            return;
        }
        JPopupMenu popup = new JPopupMenu();
        popup.add(createReadingConfidenceMenu(reading, checkbox));
        popup.show(ev.getComponent(), ev.getX(), ev.getY());
    }


    private String formatReadingCheckboxText(Reading reading) {
        String conf = reading.getConfidence();
        if (conf == null || conf.trim().isEmpty() || "-1".equals(conf.trim()) || "0".equals(conf.trim())) {
            conf = "not set";
        }
        return "<html>&nbsp;&nbsp;&nbsp;&nbsp;<font size=+1><b>Reading:</b> "
                + reading.getReading()
                + "<br/>&nbsp;&nbsp;&nbsp;&nbsp;<b>Confidence:</b> "
                + conf
                + "<br/>&nbsp;&nbsp;&nbsp;&nbsp;<b>Type:</b>"
                + reading.getTyp()
                + "</font></html>";
    }

    private JMenu createReadingConfidenceMenu(
            final Reading reading,
            final JCheckBox checkbox
    ) {
        JMenu menu = new JMenu("Confidence");
        ButtonGroup group = new ButtonGroup();
        int current = 0;
        try {
            current = Integer.parseInt(reading.getConfidence());
        } catch (Exception ex) {
            current = 0;
        }
        for (int i = 1; i <= 5; i++) {
            final int value = i;
            JRadioButtonMenuItem item = new JRadioButtonMenuItem("" + i);
            item.setSelected(current == i);
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    reading.setConfidence(value);
                    checkbox.setText(formatReadingCheckboxText(reading));
                    changed = true;
                }
            });
            group.add(item);
            menu.add(item);
        }
        return menu;
    }

    private JMenu createInterpretationConfidenceMenu(
            final AnnotationNode obj
    ) {
        JMenu menu = new JMenu("Confidence");
        if (obj == null || obj.getMyTreeNode() == null) {
            return menu;
        }
        final TreeNode node = obj.getMyTreeNode();
        int current = node.getInterpretationConfidence();
        ButtonGroup group = new ButtonGroup();
        for (int i = 1; i <= 5; i++) {
            final int value = i;
            JRadioButtonMenuItem item = new JRadioButtonMenuItem("" + i);
            item.setSelected(current == i);
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    node.setInterpretationConfidence(value);
                    if (node instanceof GraphTranslit2) {
                        synchronizeEditedGT2ToSameBlockInAllReadings((GraphTranslit2) node);
                        reloadBlockTree();
                    } else {
                        DefaultTreeModel model = (DefaultTreeModel) blockTree.getModel();
                        model.nodeChanged(obj);
                        blockTree.revalidate();
                        blockTree.repaint();
                    }
                    changed = true;
                }
            });
            group.add(item);
            menu.add(item);
        }
        return menu;
    }


	private void mainWin_componentResized(ComponentEvent e)  
	{  
	//System.out.println("\nmainWin_componentResized(ComponentEvent e) called.");  
	// TODO: Add any handling code here  
	   
	}

	private JDialog createOpenProgressDialog(String message) {
	    final JDialog dialog =
	            new JDialog(this, "Loading", false);

	    JPanel panel =
	            new JPanel(new BorderLayout(10, 10));

	    panel.setBorder(
	            BorderFactory.createEmptyBorder(15, 15, 15, 15)
	    );

	    JLabel label =
	            new JLabel(message);

	    JProgressBar progress =
	            new JProgressBar();

	    progress.setIndeterminate(true);
	    progress.setStringPainted(true);
	    progress.setString("Please wait...");
	    dialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

	    panel.add(label, BorderLayout.NORTH);
	    panel.add(progress, BorderLayout.CENTER);

	    dialog.getContentPane().add(panel);
	    dialog.setDefaultCloseOperation(
	            JDialog.DO_NOTHING_ON_CLOSE
	    );
	    dialog.pack();
	    dialog.setLocationRelativeTo(this);

	    return dialog;
	}

	private void closeOpenProgressDialog(
	        final JDialog dialog
	) {
	    if (dialog == null) {
	        return;
	    }

	    SwingUtilities.invokeLater(
	            new Runnable() {
	                public void run() {
	                    dialog.setVisible(false);
	                    dialog.dispose();
	                }
	            }
	    );
	}


	//
	private VisualizationViewer<GlyphNode,OperatorLink> getGraphNT2Viewer(Graph<GlyphNode,OperatorLink> g, Graph<GlyphNode,OperatorLink> t,NumTranslit2 ntr2,AnnotationNode obj){
		 DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
          gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
         
        Function<GlyphNode, Paint> vertexPaint = new Function<GlyphNode, Paint>() {
            public Paint apply(GlyphNode i) {
            	if(i.getTyp().equals("nt2")) {
            		
                  	 return  Color.WHITE;
                 
            	
            	}
            	
            	else if(i.getTyp().equals("segm")) return Color.GREEN;
            	else if(i.getTyp().equals("complex")) return ((GraphNT2Node)i).getColor();
            	else return Color.WHITE;
        }
    };
    
    Function<GlyphNode, Paint> vertexKonturPaint = new Function<GlyphNode, Paint>() {
        public Paint apply(GlyphNode i) {
        	if(i.getTyp().equals("nt2")) {
        		Color c= Color.BLACK;
        		if( g.getOutEdges(i).size()>0) {
        				  Collection<OperatorLink> edges = g.getOutEdges(i);
        				    for (OperatorLink ed : edges) {
        				    	if (ed.getTyp()==2) { c=ed.getColor();
        				    	 
        				    	}
        				    	
        				    }
        				return c;   
        			}
        			
        		else return Color.BLACK;
        		}
        		else return Color.BLACK;
        	
    }
};
        	 float dash[] = {10.0f};
             final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                  BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
             final Stroke edgeStroke1 = new BasicStroke();
             Function<OperatorLink, Stroke> edgeStrokeTransformer =
                     new Function<OperatorLink, Stroke>() {
                         public Stroke apply(OperatorLink s) {
                        	 if(s.getTyp()==0)
                             return edgeStroke1;
                        	 else   return edgeStroke;
                         }
                     };
                     
                     Function<OperatorLink, Paint> edgePaintTransformer =
                             new Function<OperatorLink,Paint>() {
                                 public Paint apply(OperatorLink s) {
                                	 if(s.getTyp()==2)
                                     return s.getColor();
                                	 else   return Color.BLACK;
                                 }
                             };
                     Function<GlyphNode, java.awt.Shape> vertexSize=
                    		 new Function<GlyphNode, java.awt.Shape>() {
                    	 public java.awt.geom.Ellipse2D.Double apply(GlyphNode s) {
                    		 double width = s.toString().length() * 10.0;
                             return new java.awt.geom.Ellipse2D.Double(-(width/2), -12.5, width, 25);
                         }
                     };
                    
                   
                     Function <OperatorLink, String> edgeLabel=
                         new Function<OperatorLink, String>() {
                         public String apply(OperatorLink e) {
                           if (e.getTyp()==1) 
                             return  e.toString();
                           
                           else return "  ";
                         }
                     };
                     Function <GlyphNode,Font> vertexFont = new Function<GlyphNode,Font>() {
                         public Font apply(GlyphNode e) {
                             Font font = new Font("Sans Serif", Font.BOLD, 16);
                             return font;
                         }
                           };
                   Function <OperatorLink,Font> edgeFont = new Function<OperatorLink,Font>() {
                         public Font apply(OperatorLink e) {
                             Font font = new Font("Sans Serif", Font.BOLD, 18);
                          
                             return font;
                         }
                           };
                      
		MinimumSpanningForest2<GlyphNode,OperatorLink> prim = 
              	new MinimumSpanningForest2<GlyphNode,OperatorLink>(t,
              		new DelegateForest<GlyphNode, OperatorLink>(), DelegateTree.<GlyphNode,OperatorLink>getFactory(),
              		Functions.<Double>constant(8.0));
    
  Forest<GlyphNode,OperatorLink> tree = prim.getForest();
  Layout<GlyphNode,OperatorLink> layout = new TreeLayout<GlyphNode,OperatorLink>(tree); 
      Layout<GlyphNode,OperatorLink> layout1 = new StaticLayout<GlyphNode,OperatorLink>(g, layout);
	layout1 . setSize ( new Dimension (550 , 550) );

			VisualizationViewer<GlyphNode,OperatorLink> vv =
					 new VisualizationViewer<GlyphNode,OperatorLink>(layout1, new Dimension (550 ,550));
			vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
	
			vv.addGraphMouseListener(new GraphMouseListener() {

 	            @Override
 	            public void graphClicked(Object v, MouseEvent me) {
 	            	System.out.println("Clicked "+ v.getClass());
 	            	String clicked=""+v.getClass();
 	            	
 	                
 	            	if(clicked.equals("class allmahVer4.GraphNT2Node")) {
 	            		//  gm.setMode(ModalGraphMouse.Mode.EDITING);
 	            //		 vv.setGraphMouse(gm); 
 	                     if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() ==1) {
                                     GraphNT2Node aux= compNT2Nodes.get(compNT2Nodes.size()-1);
                                      compNT2Nodes.get(compNT2Nodes.size()-1).setLabel( (compNT2Nodes.get(compNT2Nodes.size()-1)).getLabel()+( (GraphNT2Node)v).getLabel()+" ");
                                    
                                                g.addEdge(new OperatorLink(" ",2, ((GraphNT2Node)aux).getColor()), (GlyphNode)v,(GlyphNode)aux);
                                               ce.get(ce.size()-1).getElements().add((GraphNT2Node)v);
                                               ce.get(ce.size()-1).setLabel();
            	                       vv.repaint();	 
 	                     }
 	              
 	                me.consume();
 	            }
 	            }
 	            @Override
 	            public void graphPressed(Object v, MouseEvent me) {
 	              
 	          
 	            }

 	            @Override
 	            public void graphReleased(Object v, MouseEvent me) {
 	            	
 	            	
 	            }
 	        });
//  			vv.setGraphMouse(gm);
           //  vv . setPreferredSize ( new Dimension (550 ,550));
         vv.getRenderContext().setEdgeLabelTransformer(edgeLabel);
         vv.getRenderContext().setVertexShapeTransformer(vertexSize);
         vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
         vv.getRenderContext().setVertexDrawPaintTransformer(vertexKonturPaint);
         vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
         vv.getRenderContext().setEdgeDrawPaintTransformer(edgePaintTransformer);

         vv.getRenderContext().setEdgeShapeTransformer(EdgeShape.quadCurve(g));
         vv.getRenderContext().setEdgeFontTransformer(edgeFont);
         vv.getRenderContext().setVertexFontTransformer(vertexFont);
            vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
 		
         // vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
          vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);  
         // vv.setGraphMouse(gm); 
         
			return vv;
	}
	//
	
	private MyVisualizationViewer<GraphGT2Node,OperatorLink> getGT2Viewer(Graph<GraphGT2Node,OperatorLink> g, GraphTranslit2 gt2 ,AnnotationNode obj){
		 
        Function<GraphGT2Node, Paint> vertexPaint = new Function<GraphGT2Node, Paint>() {
            public Paint apply(GraphGT2Node i) {
            	if(i.getGT2Element().getParentGT1()!=null) {
            	if(i.getGT2Element().getParentGT1().getParent().getComponentElement()!=null)
            		return i.getGT2Element().getParentGT1().getParent().getComponentElement().getcolor();
            	else return Color.WHITE;
            	}
            	else return Color.LIGHT_GRAY;
        }
    };
        	 float dash[] = {10.0f};
             final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                  BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
             final Stroke edgeStroke1 = new BasicStroke();
             Function<OperatorLink, Stroke> edgeStrokeTransformer =
                     new Function<OperatorLink, Stroke>() {
                         public Stroke apply(OperatorLink s) {
                        	 if(s.toString().equals("-"))
                             return edgeStroke1;
                        	 else   return edgeStroke;
                         }
                     };
                     
                     Function<GraphGT2Node, java.awt.Shape> vertexSize=
                    		 new Function<GraphGT2Node, java.awt.Shape>() {
                    	 public java.awt.geom.Rectangle2D.Double apply(GraphGT2Node s) {
                    		 String s1=s.getLabel();
                    		 System.out.println("Label New Node "+s1);
                    		if(!s1.equals("NEW")) s1=Jsoup.parse( s.getLabel1()).text();
                    		//else s1="NEW";
                    		 double width =s1.length() * 20.0;
                             return new java.awt.geom.Rectangle2D.Double(-(width/2), -12.5, width, 25);
                         }
                     };
                    
                   
                     Function <OperatorLink, String> edgeLabel=
                         new Function<OperatorLink, String>() {
                         public String apply(OperatorLink e) {
                           if (e.toString().equals("-")) 
                             return  e.toString();
                           
                           else return "  ";
                         }
                     };
                     Function <GraphGT2Node,Font> vertexFont = new Function<GraphGT2Node,Font>() {
                         public Font apply(GraphGT2Node e) {
                             Font font = new Font("Sans Serif", Font.BOLD, 16);
                             return font;
                         }
                           };
                   Function <OperatorLink,Font> edgeFont = new Function<OperatorLink,Font>() {
                         public Font apply(OperatorLink e) {
                             Font font = new Font("Sans Serif", Font.BOLD, 18);
                          
                             return font;
                         }
                           };
                      
		
    
                           MinimumSpanningForest2<GraphGT2Node,OperatorLink> prim = 
                                 	new MinimumSpanningForest2<GraphGT2Node,OperatorLink>(g,
                                 		new DelegateForest<GraphGT2Node, OperatorLink>(), DelegateTree.<GraphGT2Node,OperatorLink>getFactory(),
                                 		Functions.<Double>constant(8.0));
                       
                 /*    Forest<GraphGT2Node,OperatorLink> tree = prim.getForest();
                     Layout<GraphGT2Node,OperatorLink> layout = new TreeLayout<GraphGT2Node,OperatorLink>(tree); 
                         Layout<GraphGT2Node,OperatorLink> layout1 = new StaticLayout<GraphGT2Node,OperatorLink>(g, layout);
                   	layout1 . setSize ( new Dimension (550 , 550) );
                    Layout<GraphGT2Node,OperatorLink> layout2 = new FRLayout<GraphGT2Node,OperatorLink>(g);
                    layout2 . setSize ( new Dimension (550 , 550) );
			VisualizationViewer<GraphGT2Node,OperatorLink> vv =
					 new VisualizationViewer<GraphGT2Node,OperatorLink>(layout2, new Dimension (550 ,550));
		     MyVisualizationViewer<GraphGT2Node,OperatorLink> vv1 =
					 new MyVisualizationViewer(vv.getModel(),new Dimension (550 ,550));*/
		     
		     Layout<GraphGT2Node,OperatorLink> layout2 = new MyListGT2Layout<GraphGT2Node,OperatorLink>(g);
	            //        layout2 . setSize ( new Dimension (350 , 350) );
				VisualizationViewer<GraphGT2Node,OperatorLink> vv =
						 new VisualizationViewer<GraphGT2Node,OperatorLink>(layout2, new Dimension (1050 ,550));
			     MyVisualizationViewer<GraphGT2Node,OperatorLink> vv1 =
						 new MyVisualizationViewer<GraphGT2Node,OperatorLink>(vv.getModel(),new Dimension (1050 ,550));
		   
			vv1.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		  vv1.getRenderContext().setEdgeDrawPaintTransformer(new PickableEdgePaintTransformer<OperatorLink>(vv1.getPickedEdgeState(), Color.BLACK, Color.RED));
		  vv1.getRenderContext().setEdgeLabelRenderer(new  DefaultEdgeLabelRenderer(Color.RED));
         vv1.getRenderContext().setEdgeLabelTransformer(edgeLabel);
         vv1.getRenderContext().setVertexShapeTransformer(vertexSize);
         vv1.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
         vv1.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
         vv1.getRenderContext().setEdgeShapeTransformer(EdgeShape.quadCurve(g));
         vv1.getRenderContext().setEdgeFontTransformer(edgeFont);
         vv1.getRenderContext().setVertexFontTransformer(vertexFont);
            vv1.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
 		
         // vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
          vv1.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);  
          final ScalingControl scaler = new CrossoverScalingControl();
 		 scaler.scale(vv1,1/1.1f, vv1.getCenter()); 
     //     DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
     
   //   gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
      //   vv.setGraphMouse(gm); 
			return vv1;
	}
	//PhonemViewer
	private MyVisualizationViewer<PhonemNode,OperatorLink> getPhonemViewer(DirectedOrderedSparseMultigraph<PhonemNode,OperatorLink> g, PhonemTranslit phon ,AnnotationNode obj){
		 
        Function<GlyphNode, Paint> vertexPaint = new Function<GlyphNode, Paint>() {
            public Paint apply(GlyphNode i) {
            	if(((GraphGT2Node)i.getParent()).getGT2Element().getParentGT1()!=null) {
            	if(((GraphGT2Node)i.getParent()).getGT2Element().getParentGT1().getParent().getComponentElement()!=null)
            		return ((GraphGT2Node)i.getParent()).getGT2Element().getParentGT1().getParent().getComponentElement().getcolor();
            	else return Color.WHITE;
            	}
            	else return Color.LIGHT_GRAY;
        }
    };
  
        	 float dash[] = {10.0f};
             final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                  BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
             final Stroke edgeStroke1 = new BasicStroke();
             Function<OperatorLink, Stroke> edgeStrokeTransformer =
                     new Function<OperatorLink, Stroke>() {
                         public Stroke apply(OperatorLink s) {
                        	 if(!s.toString().equals(" "))
                             return edgeStroke1;
                        	 else   return edgeStroke;
                         }
                     };
                    
                     Function<GlyphNode, java.awt.Shape> vertexSize=
                    		 new Function<GlyphNode, java.awt.Shape>() {
                    	 public java.awt.geom.Rectangle2D.Double apply(GlyphNode s) {
                    		 String s1=s.getLabel();
                    		 System.out.println("Label New Node "+s1);
                    		 double width =s1.length() * 20.0;
                             return new java.awt.geom.Rectangle2D.Double(-(width/2), -12.5, width, 25);
                         }
                     };
                    
                   
                     Function <OperatorLink, String> edgeLabel=
                         new Function<OperatorLink, String>() {
                         public String apply(OperatorLink e) {
                          // if (e.toString().equals("-")) 
                             return  e.toString();
                           
                         //  else return "  ";
                         }
                     };
                     Function <GlyphNode,Font> vertexFont = new Function<GlyphNode,Font>() {
                         public Font apply(GlyphNode e) {
                             Font font = new Font("Sans Serif", Font.BOLD, 16);
                             return font;
                         }
                           };
                   Function <OperatorLink,Font> edgeFont = new Function<OperatorLink,Font>() {
                         public Font apply(OperatorLink e) {
                             Font font = new Font("Sans Serif", Font.BOLD, 22);
                          
                             return font;
                         }
                           };
                      
               // Layout<PhonemNode,OperatorLink> layout2 = new MyFRLayout<PhonemNode,OperatorLink>(g,new Dimension(550,450));
                   // Layout<PhonemNode,OperatorLink> layout2 = new ISOMLayout<PhonemNode,OperatorLink>(g);
                Layout<PhonemNode,OperatorLink> layout2 = new MyListLayout<PhonemNode,OperatorLink>(g);
            //        layout2 . setSize ( new Dimension (350 , 350) );
			VisualizationViewer<PhonemNode,OperatorLink> vv =
					 new VisualizationViewer<PhonemNode,OperatorLink>(layout2, new Dimension (750 ,550));
		     MyVisualizationViewer<PhonemNode,OperatorLink> vv1 =
					 new MyVisualizationViewer<PhonemNode,OperatorLink>(vv.getModel(),new Dimension (750 ,550));
			vv1.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
			vv1.getRenderContext().setEdgeDrawPaintTransformer(new PickableEdgePaintTransformer<OperatorLink>(vv1.getPickedEdgeState(), Color.BLACK, Color.RED));
		//	PickableEdgePaintTransformer p=new Pickable
		//	vv1.getRenderContext().setEdgePaintTransformer(new PickableEdgePaintTransformer<OperatorLink>());
			
			 vv1.getRenderContext().setEdgeLabelRenderer(new  DefaultEdgeLabelRenderer(Color.RED));
         vv1.getRenderContext().setEdgeLabelTransformer(edgeLabel);
       //  vv1.getRenderer().setEdgeRenderer(pickableEdgePaintTransformer);
         //vv1.getRenderContext().setPickableEdgePaintTransformer(pickableEdgePaintTransformer);
         vv1.getRenderContext().setVertexShapeTransformer(vertexSize);
         vv1.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
         vv1.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
         vv1.getRenderContext().setEdgeShapeTransformer(EdgeShape.quadCurve(g));
         vv1.getRenderContext().setEdgeFontTransformer(edgeFont);
         vv1.getRenderContext().setVertexFontTransformer(vertexFont);
            vv1.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
 		
         // vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
          vv1.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);  
         final ScalingControl scaler = new CrossoverScalingControl();
		 scaler.scale(vv1,1/1.1f, vv1.getCenter()); 
     //     DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
     
   //   gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
      //   vv.setGraphMouse(gm); 
			return vv1;
	}

	//
	private VisualizationViewer<GlyphNode,OperatorLink> getGraphViewer(Graph<GlyphNode,OperatorLink> g, Graph<GlyphNode,OperatorLink> t,NumTranslit1 ntr1,AnnotationNode obj){
		 
        Function<GlyphNode, Paint> vertexPaint = new Function<GlyphNode, Paint>() {
            public Paint apply(GlyphNode i) {
            	if(i.getTyp().equals("nt1")) {
            		
                  	   ArrayList<Reading> r=((GraphNT1Node) i).getNT1Element().getInitialReadings();
                  	   if(r!=null)
                     	    if( r.size()>1) { return Color.YELLOW;}
                     	   else {
                     		   if(r.get(0).getTyp().equals("no")) return Color.LIGHT_GRAY;
                     		   else {return Color.WHITE;}
                     	   }
                  	    else  return Color.LIGHT_GRAY;
                 
            	
            	}
            	else if(i.getTyp().equals("segm")) return Color.GREEN;
            	else return Color.WHITE;
        }
    };
    
    
    
    
        	 float dash[] = {10.0f};
             final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                  BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
             final Stroke edgeStroke1 = new BasicStroke();
            
             Function<OperatorLink, Stroke> edgeStrokeTransformer =
                     new Function<OperatorLink, Stroke>() {
                         public Stroke apply(OperatorLink s) {
                        	 if(s.getTyp()==0)
                             return edgeStroke1;
                        	 else   return edgeStroke;
                         }
                     };
                     
                    
                   
                     Function <OperatorLink, String> edgeLabel=
                         new Function<OperatorLink, String>() {
                         public String apply(OperatorLink e) {
                           if (e.getTyp()==1) 
                             return  e.toString();
                           
                           else return "  ";
                         }
                     };
                     Function <GlyphNode,Font> vertexFont = new Function<GlyphNode,Font>() {
                         public Font apply(GlyphNode e) {
                             Font font = new Font("Sans Serif", Font.BOLD, 16);
                             return font;
                         }
                           };
                   Function <OperatorLink,Font> edgeFont = new Function<OperatorLink,Font>() {
                         public Font apply(OperatorLink e) {
                             Font font = new Font("Sans Serif", Font.BOLD, 18);
                          
                             return font;
                         }
                           };
                      
		MinimumSpanningForest2<GlyphNode,OperatorLink> prim = 
              	new MinimumSpanningForest2<GlyphNode,OperatorLink>(t,
              		new DelegateForest<GlyphNode, OperatorLink>(), DelegateTree.<GlyphNode,OperatorLink>getFactory(),
              		Functions.<Double>constant(8.0));
    
  Forest<GlyphNode,OperatorLink> tree = prim.getForest();
  Layout<GlyphNode,OperatorLink> layout = new TreeLayout<GlyphNode,OperatorLink>(tree); 
      Layout<GlyphNode,OperatorLink> layout1 = new StaticLayout<GlyphNode,OperatorLink>(g, layout);
	layout1 . setSize ( new Dimension (550 , 550) );

			VisualizationViewer<GlyphNode,OperatorLink> vv =
					 new VisualizationViewer<GlyphNode,OperatorLink>(layout1, new Dimension (550 ,550));
			
			 Function<GlyphNode, java.awt.Shape> vertexSize=
            		 new Function<GlyphNode, java.awt.Shape>() {
            	 public java.awt.geom.Ellipse2D.Double apply(GlyphNode s) {
            		 
            		 String label = s.toString();
            		 String[] lines = label.split("<br/>");
            		 double maxWidth = 0.0;
            	   
       
                     FontMetrics fontMetrics = vv.getFontMetrics(vv.getFont());
                     for (String line : lines) {
                         double lineWidth = fontMetrics.getStringBounds(line, vv.getGraphics()).getWidth();
                         if (lineWidth > maxWidth) {
                             maxWidth = lineWidth;
                         }
                     }
                   
            		
            		  double width = maxWidth + 25.0; // Add padding for better visual appearance
                     

                     return new java.awt.geom.Ellipse2D.Double(-(width/2), -12.5, width, 40);

                 }
             };
            
			
			
			vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
			
			
			 CrossoverScalingControl scalingControl = new CrossoverScalingControl();
			
			 scalingControl.scale(vv, (float)2.0, vv.getCenter());
			
			vv.addGraphMouseListener(new GraphMouseListener() {

 	            @Override
 	            public void graphClicked(Object v, MouseEvent me) {
 	            	System.out.println("Clicked "+ v.getClass());
 	            	String clicked=""+v.getClass();
 	            	if(clicked.equals("class allmahVer4.GraphNT1Node")) {
 	                     if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() ==1) {
 	                            System.out.println ("Clicked  "+me.getClickCount());
 	                    	NT1Element nt1=((GraphNT1Node) v).getNT1Element();
 	                    
 	                    	 System.out.println(" clicked "+nt1);
 	                    	if(!nt1.getLabel().equals("??")) {
 	                    	   ArrayList<Reading> r= nt1.getInitialReadings();
 	                    	   if(r!=null) {
 	                    		   if(!r.get(0).getTyp().equals("no")) {
 	                    	   JCheckBox check[]=new JCheckBox[r.size()];
 	                    	   final JButton select=new JButton("Confirm Selection");
 	                    	  JPanel optionsPanel=new JPanel();
	                    	   optionsPanel.setLayout(new GridLayout(r.size(),1));
 	                    	   for( int i=0;i<r.size();i++) {
 	                    		   check[i]=new JCheckBox();
 	                    		   String ss=r.get(i).getTyp();
 	                    		   if(ss.equals("lr")) ss="Logographic reading";
 	                    		   else if (ss.equals("df")) ss="Diacritic function";
 	                    		   else if (ss.equals("lm")) ss="Logographic meaning";
 	                    		   else if (ss.equals("nf")) ss="Numeric function";
 	                    		 if  (ss.equals("sr")) ss="Syllabic reading";
 	                    		   check[i].setText(formatReadingCheckboxText(r.get(i)));
	                    		   final Reading readingForConfidence = r.get(i);
	                    		   final JCheckBox checkForConfidence = check[i];
	                    		   check[i].addMouseListener(new MouseAdapter() {
	                    		       public void mousePressed(MouseEvent ev) {
	                    		           maybeShowReadingConfidencePopup(ev, readingForConfidence, checkForConfidence);
	                    		       }
	                    		       public void mouseReleased(MouseEvent ev) {
	                    		           maybeShowReadingConfidencePopup(ev, readingForConfidence, checkForConfidence);
	                    		       }
	                    		   });
 	                    		 check[i].setSelected(r.get(i).getState());
 	                    		   optionsPanel.add(check[i],1,i);
 	                    	   }
 	                    	  ChildFrame selFrame= new ChildFrame("Select Readings for "+nt1.getLabel() ,Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
 	                    	 optionsPanel.add(select,1,r.size());
 	                    	 select.addActionListener(new ActionListener() {
             		            public void actionPerformed(ActionEvent e) {
             		            	int n=0;
             		                     for (int i=0;i<check.length; i++)
             		                    	 if (check[i].isSelected()) n=n+1; 
             		                     System.out.println("SELECTED "+n);
             		                     if(n==0) {}
             		                     else {
             		                    	// nt1.getSelectedReadings().clear();
             		                    	 ArrayList<Reading> raux=new ArrayList<Reading>();
             		                    	 String finalLabel="";
             		                    	for (int i=0;i<check.length; i++) {
             		                    		if(check[i].isSelected()) {
             		                    		//	nt1.getSelectedReadings().add(r.get(i));
             		                 			finalLabel=finalLabel+r.get(i).getReading()+"|";
             		                    			raux.add(r.get(i).copy());
             		                    			System.out.println("Added "+r.get(i).getReading());
             		                               
             		                    			
             		                    		}
             		                    		else r.get(i).setState(false);
             		                    	}
             		                    	((GraphNT1Node) v).setLabel("<html>"+((GraphNT1Node) v).getNT1Element().getLabel()+"<br/>"+finalLabel.substring(0,finalLabel.length()-1)+"</html>");
             		                    	selFrame.doDefaultCloseAction(); nt1.setSelectedReadings(raux);
             		                  
             		            //      (DefaultTreeModel))blockTree.getModel().
             		                 
             		                     }
             		            }
             		        });
             		   
 	                    	
                   		  selFrame.getContentPane().add(new JScrollPane(optionsPanel));
                   		 selFrame.getContentPane().add(select,BorderLayout.SOUTH);
                 		   selFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                 		   selFrame.pack();
                		   addChild(selFrame,900, 10, 300, 300);		
                		   selFrame.setVisible(true); 
                		  selFrame.moveToFront();
 	                    	   }	}	}
 	                } 
 	            	}
 	                me.consume();
 	            }

 	            @Override
 	            public void graphPressed(Object v, MouseEvent me) {
 	            }

 	            @Override
 	            public void graphReleased(Object v, MouseEvent me) {
 	            }
 	        });
//  			vv.setGraphMouse(gm);
           //  vv . setPreferredSize ( new Dimension (550 ,550));
         vv.getRenderContext().setEdgeLabelTransformer(edgeLabel);
         vv.getRenderContext().setVertexShapeTransformer(vertexSize);
         vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
         vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
         vv.getRenderContext().setEdgeShapeTransformer(EdgeShape.quadCurve(g));
         vv.getRenderContext().setEdgeFontTransformer(edgeFont);
         vv.getRenderContext().setVertexFontTransformer(vertexFont);
        
            vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
 		
         // vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
          vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);  
          DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
          gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
          vv.setGraphMouse(gm); 
			return vv;
	}
	public Font getFontInterf() {
		return typFont1;
	}
	 public AllmahGUI getInterf(){
		 return this;
	 }
	 private DefaultTreeModel createTreeModel() {
			AnnotationNode root=new AnnotationNode(doc), node;
			root.explore();
            attachImportedFinalTranslationsToTree(root);
			return new DefaultTreeModel(root);
		}

    private void attachImportedFinalTranslationsToTree(AnnotationNode root) {
        if (root == null) {
            return;
        }
        if (translationNodes == null || translationNodes.isEmpty()) {
            return;
        }
        if (mtr == null) {
            mtr = new ArrayList<FinalTranslation>();
        }
        if (mmtr == null) {
            mmtr = new HashMap<String,Integer>();
        }
        attachImportedFinalTranslationsToTreeRecursive(root);
    }

    private void attachImportedFinalTranslationsToTreeRecursive(AnnotationNode node) {
        if (node == null) {
            return;
        }

        if (node.getMyTreeNode() instanceof MorphoSyntGlossing) {
            MorphoSyntGlossing glossing = (MorphoSyntGlossing) node.getMyTreeNode();
            attachFinalTranslationsForGlossingNode(glossing, node);
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            Object child = node.getChildAt(i);
            if (child instanceof AnnotationNode) {
                attachImportedFinalTranslationsToTreeRecursive((AnnotationNode) child);
            }
        }
    }

    private void attachFinalTranslationsForGlossingNode(
            MorphoSyntGlossing glossing,
            AnnotationNode glossingTreeNode
    ) {
        if (glossing == null || glossingTreeNode == null
                || translationNodes == null) {
            return;
        }

        String glossingId = glossing.getId();
        if (glossingId == null) {
            return;
        }

        for (TranslationNode translationNode : translationNodes) {
            if (translationNode == null) {
                continue;
            }
            if (!"ConsolidatedTranslation".equals(translationNode.getNodeKind())) {
                continue;
            }
            if (!glossingId.equals(translationNode.getParentMgId())) {
                continue;
            }

            FinalTranslation finalTranslation =
                    getOrCreateFinalTranslationFromTranslationNode(translationNode);
            if (finalTranslation == null) {
                continue;
            }

            if (!annotationNodeAlreadyHasTreeNode(glossingTreeNode, finalTranslation)) {
                glossingTreeNode.add(new AnnotationNode(finalTranslation));
            }
        }
    }

    private FinalTranslation getOrCreateFinalTranslationFromTranslationNode(
            TranslationNode translationNode
    ) {
        if (translationNode == null || translationNode.getId() == null) {
            return null;
        }
        if (mtr == null) {
            mtr = new ArrayList<FinalTranslation>();
        }
        if (mmtr == null) {
            mmtr = new HashMap<String,Integer>();
        }

        Integer existingIndex = mmtr.get(translationNode.getId());
        if (existingIndex != null
                && existingIndex.intValue() >= 0
                && existingIndex.intValue() < mtr.size()) {
            return mtr.get(existingIndex.intValue());
        }

        FinalTranslation finalTranslation = new FinalTranslation(
                translationNode.getId(),
                translationNode.getOriginal(),
                translationNode.getTranslation()
        );
        mtr.add(finalTranslation);
        mmtr.put(
                finalTranslation.getId(),
                Integer.valueOf(mtr.size() - 1)
        );
        return finalTranslation;
    }

    private boolean annotationNodeAlreadyHasTreeNode(
            AnnotationNode parent,
            TreeNode target
    ) {
        if (parent == null || target == null) {
            return false;
        }
        for (int i = 0; i < parent.getChildCount(); i++) {
            Object child = parent.getChildAt(i);
            if (child instanceof AnnotationNode) {
                AnnotationNode annotationChild = (AnnotationNode) child;
                if (annotationChild.getMyTreeNode() == target) {
                    return true;
                }
                if (annotationChild.getMyTreeNode() instanceof FinalTranslation
                        && target instanceof FinalTranslation) {
                    FinalTranslation a =
                            (FinalTranslation) annotationChild.getMyTreeNode();
                    FinalTranslation b = (FinalTranslation) target;
                    if (a.getId() != null && a.getId().equals(b.getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private TranslationNode getTranslationNodeById(String id) {
        if (id == null) {
            return null;
        }
        ensureTranslationNodeModel();
        Integer index = mtranslationNodes.get(id);
        if (index == null || index.intValue() < 0
                || index.intValue() >= translationNodes.size()) {
            return null;
        }
        return translationNodes.get(index.intValue());
    }

    private void rebuildTranslationNodeMap() {
        if (mtranslationNodes == null) {
            mtranslationNodes = new HashMap<String,Integer>();
        }
        else {
            mtranslationNodes.clear();
        }
        if (translationNodes == null) {
            translationNodes = new ArrayList<TranslationNode>();
            return;
        }
        for (int i = 0; i < translationNodes.size(); i++) {
            TranslationNode node = translationNodes.get(i);
            if (node != null && node.getId() != null) {
                mtranslationNodes.put(node.getId(), Integer.valueOf(i));
            }
        }
    }

    private void removeTranslationNodeAndParts(String id) {
        if (id == null) {
            return;
        }
        ensureTranslationNodeModel();
        TranslationNode node = getTranslationNodeById(id);
        if (node != null && node.getPartIds() != null) {
            ArrayList<String> copy = new ArrayList<String>(node.getPartIds());
            for (String partId : copy) {
                removeTranslationNodeAndParts(partId);
            }
        }
        for (int i = translationNodes.size() - 1; i >= 0; i--) {
            TranslationNode candidate = translationNodes.get(i);
            if (candidate != null && id.equals(candidate.getId())) {
                translationNodes.remove(i);
            }
        }
        rebuildTranslationNodeMap();
    }

    private boolean deleteLocalFinalTranslation(FinalTranslation finalTranslation) {
        if (finalTranslation == null || finalTranslation.getId() == null) {
            return false;
        }
        String id = finalTranslation.getId();
        removeTranslationNodeAndParts(id);
        if (mtr != null) {
            for (int i = mtr.size() - 1; i >= 0; i--) {
                FinalTranslation candidate = mtr.get(i);
                if (candidate != null && id.equals(candidate.getId())) {
                    mtr.remove(i);
                }
            }
        }
        if (mmtr == null) {
            mmtr = new HashMap<String,Integer>();
        }
        else {
            mmtr.clear();
        }
        if (mtr != null) {
            for (int i = 0; i < mtr.size(); i++) {
                FinalTranslation candidate = mtr.get(i);
                if (candidate != null && candidate.getId() != null) {
                    mmtr.put(candidate.getId(), Integer.valueOf(i));
                }
            }
        }
        changed = true;
        return true;
    }

    private void replaceFinalTranslationInGuiModel(
            String id,
            String mayaText,
            String translationText
    ) {
        if (id == null) {
            return;
        }
        if (mtr == null) {
            mtr = new ArrayList<FinalTranslation>();
        }
        if (mmtr == null) {
            mmtr = new HashMap<String,Integer>();
        }
        FinalTranslation replacement = new FinalTranslation(
                id,
                mayaText == null ? "" : mayaText,
                translationText == null ? "" : translationText
        );
        Integer index = mmtr.get(id);
        if (index != null && index.intValue() >= 0
                && index.intValue() < mtr.size()) {
            mtr.set(index.intValue(), replacement);
        }
        else {
            mtr.add(replacement);
            mmtr.put(id, Integer.valueOf(mtr.size() - 1));
        }
    }

    private void openEditFinalTranslationDialog(
            final FinalTranslation finalTranslation,
            final AnnotationNode translationTreeNode
    ) {
        if (finalTranslation == null) {
            return;
        }

        ensureTranslationNodeModel();

        final String translationId = finalTranslation.getId();
        final TranslationNode existingConsolidatedNode =
                getTranslationNodeById(translationId);

        String parentMgId = "";
        if (existingConsolidatedNode != null
                && existingConsolidatedNode.getParentMgId() != null) {
            parentMgId = existingConsolidatedNode.getParentMgId();
        }

        if ((parentMgId == null || parentMgId.trim().isEmpty())
                && translationTreeNode != null
                && translationTreeNode.getParent() instanceof AnnotationNode
                && ((AnnotationNode) translationTreeNode.getParent()).getMyTreeNode()
                        instanceof MorphoSyntGlossing) {
            parentMgId =
                    ((MorphoSyntGlossing)
                            ((AnnotationNode) translationTreeNode.getParent()).getMyTreeNode()
                    ).getId();
        }

        if (parentMgId == null || parentMgId.trim().isEmpty()
                || mmg == null || !mmg.containsKey(parentMgId)) {
            JOptionPane.showMessageDialog(
                    AllmahGUI.this,
                    "Cannot find parent MG node for this translation.",
                    "Edit Translation",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        final MorphoSyntGlossing currentNode =
                mg.get(mmg.get(parentMgId).intValue());

        final ArrayList<GlossingVariant> currentEl =
                currentNode.getElements();

        final ChildFrame translationFrame =
                new ChildFrame(
                        "Edit Translation for "
                                + Jsoup.parse(currentNode.getNodeLabel()).text(),
                        Color.GRAY,
                        WindowConstants.DISPOSE_ON_CLOSE
                );

        translationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content1 = translationFrame.getContentPane();
        content1.setLayout(new GridBagLayout());

        final JButton generateMU = new JButton("Generate Maya Unit");
        final JTextField mayaText = new JTextField(45);
        final JTextField translation = new JTextField(45);
        final JCheckBox inDictionary = new JCheckBox("Save in Dictionary");
        final JButton valid = new JButton("Validate");
        final JComboBox<String> composedEntrySelector = new JComboBox<String>();
        final JButton loadComposedEntry = new JButton("Load Entry");
        final JButton updateComposedEntry = new JButton("Update Entry");
        final int[] activeComposedEntryIndex = new int[] { -1 };

        JPanel panelMUnit = new JPanel(new GridBagLayout());
        panelMUnit.setBorder(BorderFactory.createTitledBorder("Selected Maya Unit"));

        GridBagConstraints gMU = new GridBagConstraints();
        gMU.insets.top = 3;
        gMU.insets.bottom = 3;
        gMU.insets.left = 5;
        gMU.insets.right = 5;
        gMU.anchor = GridBagConstraints.WEST;
        gMU.fill = GridBagConstraints.HORIZONTAL;
        gMU.weightx = 1.0;
        gMU.gridx = 0;
        gMU.gridy = 0;
        gMU.gridwidth = 2;
        panelMUnit.add(inDictionary, gMU);
        gMU.gridy = 1;
        panelMUnit.add(new JLabel("Maya text"), gMU);
        gMU.gridy = 2;
        panelMUnit.add(mayaText, gMU);
        gMU.gridy = 3;
        panelMUnit.add(new JLabel("Translation"), gMU);
        gMU.gridy = 4;
        panelMUnit.add(translation, gMU);

        JPanel mayaUnitButtonRow =
                new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        mayaUnitButtonRow.add(valid);
        mayaUnitButtonRow.add(generateMU);
        gMU.gridy = 5;
        gMU.gridwidth = 2;
        gMU.anchor = GridBagConstraints.WEST;
        gMU.fill = GridBagConstraints.NONE;
        panelMUnit.add(mayaUnitButtonRow, gMU);

        gMU.gridy = 6;
        gMU.gridwidth = 2;
        gMU.anchor = GridBagConstraints.WEST;
        gMU.fill = GridBagConstraints.HORIZONTAL;
        panelMUnit.add(new JLabel("Composed dictionary entries"), gMU);

        JPanel composedEntryRow =
                new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        composedEntrySelector.setPreferredSize(new Dimension(360, 24));
        composedEntryRow.add(composedEntrySelector);
        composedEntryRow.add(loadComposedEntry);
        composedEntryRow.add(updateComposedEntry);
        gMU.gridy = 7;
        gMU.fill = GridBagConstraints.HORIZONTAL;
        panelMUnit.add(composedEntryRow, gMU);

        final JTextArea konsolidTR = new JTextArea(4, 70);
        final JTextArea finalTr = new JTextArea(10, 70);

        konsolidTR.setLineWrap(true);
        konsolidTR.setWrapStyleWord(true);
        finalTr.setLineWrap(true);
        finalTr.setWrapStyleWord(true);
        konsolidTR.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        finalTr.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        if (existingConsolidatedNode != null) {
            konsolidTR.setText(
                    existingConsolidatedNode.getOriginal() == null
                            ? ""
                            : existingConsolidatedNode.getOriginal()
            );
            finalTr.setText(
                    existingConsolidatedNode.getTranslation() == null
                            ? ""
                            : existingConsolidatedNode.getTranslation()
            );
        }
        else {
            finalTr.setText(
                    Jsoup.parse(finalTranslation.getNodeLabel()).text()
            );
        }

        JPanel initCompon = new JPanel(new GridLayout(0, 2, 8, 8));
        initCompon.setBackground(Color.WHITE);

        GridBagConstraints g2 = new GridBagConstraints();
        g2.gridx = 0;
        g2.gridy = 0;
        g2.weightx = 1.0;
        g2.weighty = 0.60;
        g2.anchor = GridBagConstraints.NORTHWEST;
        g2.fill = GridBagConstraints.BOTH;
        g2.insets.top = 5;
        g2.insets.bottom = 3;
        g2.insets.left = 5;
        g2.insets.right = 5;
        g2.gridwidth = 2;

        final JCheckBox[] partOfMU = new JCheckBox[currentEl.size()];
        final JCheckBox[] dictionaryEntries = new JCheckBox[currentEl.size()];
        final JTextField[] semanticValues = new JTextField[currentEl.size()];
        final JLabel[] originalValues = new JLabel[currentEl.size()];
        final String[] syntacticValues = new String[currentEl.size()];
        final String[] wordNetValues = new String[currentEl.size()];
        final String[] explanationValues = new String[currentEl.size()];

        final ArrayList<int[]> composedEntryParts = new ArrayList<int[]>();
        final ArrayList<String> composedEntryOriginals = new ArrayList<String>();
        final ArrayList<String> composedEntryTranslations = new ArrayList<String>();

        HashMap<String, TranslationNode> simpleDictionaryNodeByElement =
                new HashMap<String, TranslationNode>();
        ArrayList<TranslationNode> existingComposedNodes =
                new ArrayList<TranslationNode>();

        if (existingConsolidatedNode != null
                && existingConsolidatedNode.getPartIds() != null) {
            for (String partId : existingConsolidatedNode.getPartIds()) {
                TranslationNode part = getTranslationNodeById(partId);
                if (part == null) {
                    continue;
                }
                if ("SimpleDictionaryEntry".equals(part.getNodeKind())) {
                    simpleDictionaryNodeByElement.put(
                            part.getElementLabel(),
                            part
                    );
                }
                else if ("ComposedDictionaryEntry".equals(part.getNodeKind())) {
                    existingComposedNodes.add(part);
                }
            }
        }

        for (int i = 0; i < currentEl.size(); i++) {
            final int localIndex = i;
            GlossingVariant variant = currentEl.get(i);

            JPanel card = new JPanel(new GridBagLayout());
            card.setPreferredSize(new Dimension(480, 210));
            card.setMinimumSize(new Dimension(420, 190));
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEtchedBorder(),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            card.setBackground(new Color(248, 248, 248));

            GridBagConstraints left = new GridBagConstraints();
            left.gridx = 0;
            left.gridy = 0;
            left.weightx = 0.45;
            left.weighty = 0.0;
            left.insets.top = 2;
            left.insets.bottom = 2;
            left.insets.left = 2;
            left.insets.right = 8;
            left.anchor = GridBagConstraints.WEST;
            left.fill = GridBagConstraints.HORIZONTAL;

            GridBagConstraints right = new GridBagConstraints();
            right.gridx = 1;
            right.gridy = 0;
            right.gridheight = 8;
            right.weightx = 0.55;
            right.weighty = 1.0;
            right.insets.top = 2;
            right.insets.bottom = 2;
            right.insets.left = 8;
            right.insets.right = 2;
            right.anchor = GridBagConstraints.NORTHWEST;
            right.fill = GridBagConstraints.BOTH;

            String originalText = "";
            if (variant.getParent() != null) {
                originalText = variant.getParent().calculateLabel2();
            }

            TranslationNode existingSimple =
                    simpleDictionaryNodeByElement.get(originalText);

            originalValues[i] = new JLabel(originalText);
            originalValues[i].setFont(typFont1.deriveFont(Font.BOLD, 14));

            JLabel elementLabel = new JLabel("Element:  " + originalText);
            elementLabel.setFont(typFont1.deriveFont(Font.BOLD, 13));
            card.add(elementLabel, left);

            String semanticText = "";
            if (existingSimple != null
                    && existingSimple.getSemanticMeaning() != null
                    && !existingSimple.getSemanticMeaning().trim().isEmpty()) {
                semanticText = existingSimple.getSemanticMeaning().trim();
            }
            else if (variant.getSemantics() != null
                    && variant.getSemantics().getMeaning() != null) {
                semanticText = variant.getSemantics().getMeaning().trim();
            }

            if (semanticText.equalsIgnoreCase("No Meaning")
                    || semanticText.trim().isEmpty()) {
                semanticText = originalText;
            }

            left.gridy++;
            JLabel semanticCaption = new JLabel("Semantic Meaning");
            semanticCaption.setFont(typFont1.deriveFont(Font.BOLD, 13));
            card.add(semanticCaption, left);

            left.gridy++;
            semanticValues[i] = new JTextField(semanticText, 16);
            semanticValues[i].setEditable(false);
            semanticValues[i].setFont(typFont1.deriveFont(Font.PLAIN, 13));
            card.add(semanticValues[i], left);

            String syntaxText = "";
            if (existingSimple != null
                    && existingSimple.getSyntacticFunction() != null
                    && !existingSimple.getSyntacticFunction().trim().isEmpty()) {
                syntaxText = existingSimple.getSyntacticFunction().trim();
            }
            else if (variant.getSyntax() != null
                    && variant.getSyntax().getSyntFeature() != null
                    && variant.getSyntax().getSyntFeature().getAbbreviation() != null) {
                syntaxText =
                        variant.getSyntax().getSyntFeature().getAbbreviation();
            }
            syntacticValues[i] = syntaxText;

            left.gridy++;
            JLabel syntaxCaption = new JLabel("Syntactic Function");
            syntaxCaption.setFont(typFont1.deriveFont(Font.BOLD, 13));
            card.add(syntaxCaption, left);

            left.gridy++;
            JLabel syntaxLabel = new JLabel(syntaxText);
            syntaxLabel.setFont(typFont1.deriveFont(Font.PLAIN, 13));
            card.add(syntaxLabel, left);

            left.gridy++;
            JCheckBox dictionaryEntry =
                    new JCheckBox("Entry in Dictionary");
            dictionaryEntry.setSelected(existingSimple != null);
            dictionaryEntry.setOpaque(false);
            dictionaryEntries[i] = dictionaryEntry;
            card.add(dictionaryEntry, left);

            String explanationText = "";
            if (existingSimple != null
                    && existingSimple.getExplanation() != null
                    && !existingSimple.getExplanation().trim().isEmpty()) {
                explanationText = existingSimple.getExplanation();
            }

            String wnText = "";
            if (existingSimple != null
                    && existingSimple.getWordNetSynsetId() != null) {
                wnText = existingSimple.getWordNetSynsetId().trim();
            }
            else if (variant.getSemantics() != null
                    && variant.getSemantics().getLinkWN() != null) {
                wnText =
                        stripImportedHtml(
                                variant.getSemantics().getLinkWN()
                        ).trim();
            }

            if ((explanationText == null || explanationText.trim().isEmpty())
                    && variant.getSemantics() != null
                    && variant.getSemantics().getLinkWN() != null) {
                /*
                 * Keep the already working generation-dialog behaviour:
                 * if the imported/background dictionary node has no explanation,
                 * use the explanation generated earlier for this MG element.
                 */
                explanationText = "";
            }

            explanationValues[i] =
                    explanationText == null ? "" : explanationText;
            wordNetValues[i] = wnText == null ? "" : wnText;

            JPanel explanationPanel = new JPanel(new BorderLayout(0, 4));
            explanationPanel.setOpaque(false);

            JLabel explanationCaption = new JLabel("Explanation");
            explanationCaption.setFont(typFont1.deriveFont(Font.BOLD, 13));
            explanationPanel.add(explanationCaption, BorderLayout.NORTH);

            JTextArea explanationArea =
                    new JTextArea(explanationValues[i], 5, 22);
            explanationArea.setEditable(false);
            explanationArea.setLineWrap(true);
            explanationArea.setWrapStyleWord(true);
            explanationArea.setFont(typFont1.deriveFont(Font.PLAIN, 12));

            JScrollPane explanationScroll =
                    new JScrollPane(explanationArea);
            explanationScroll.setPreferredSize(new Dimension(260, 125));
            explanationScroll.setMinimumSize(new Dimension(230, 105));
            explanationPanel.add(explanationScroll, BorderLayout.CENTER);
            card.add(explanationPanel, right);

            JPanel editRow =
                    new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 0));
            editRow.setOpaque(false);

            JButton editB = new JButton("Edit");
            JButton useB = new JButton("Use");

            final JTextField localSemanticField = semanticValues[i];

            editB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    localSemanticField.setEditable(
                            !localSemanticField.isEditable()
                    );
                    if (localSemanticField.isEditable()) {
                        localSemanticField.requestFocusInWindow();
                        localSemanticField.selectAll();
                    }
                }
            });

            useB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String mayaUnit = originalValues[localIndex].getText();
                    String translationUnit = localSemanticField.getText();

                    if (mayaUnit != null && !mayaUnit.trim().isEmpty()) {
                        String currentMaya = konsolidTR.getText();
                        if (currentMaya == null
                                || currentMaya.trim().isEmpty()) {
                            konsolidTR.setText(mayaUnit.trim());
                        }
                        else {
                            konsolidTR.setText(
                                    currentMaya.trim()
                                            + " "
                                            + mayaUnit.trim()
                            );
                        }
                    }

                    if (translationUnit != null
                            && !translationUnit.trim().isEmpty()) {
                        String currentTranslation = finalTr.getText();
                        if (currentTranslation == null
                                || currentTranslation.trim().isEmpty()) {
                            finalTr.setText(translationUnit.trim());
                        }
                        else {
                            finalTr.setText(
                                    currentTranslation.trim()
                                            + " "
                                            + translationUnit.trim()
                            );
                        }
                    }
                }
            });

            editRow.add(editB);
            editRow.add(useB);

            left.gridy++;
            left.fill = GridBagConstraints.NONE;
            left.anchor = GridBagConstraints.EAST;
            card.add(editRow, left);

            partOfMU[i] = new JCheckBox("part of");
            partOfMU[i].setSelected(false);
            partOfMU[i].setOpaque(false);
            left.gridy++;
            left.anchor = GridBagConstraints.WEST;
            card.add(partOfMU[i], left);

            initCompon.add(card);
        }

        /*
         * Keep existing composed dictionary entries unless the user explicitly
         * adds more via Save in Dictionary + Validate.  They are not rendered
         * as separate GUI nodes, but they remain in the same edit dialog state.
         */
        for (TranslationNode composedNode : existingComposedNodes) {
            if (composedNode == null) {
                continue;
            }
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            if (composedNode.getPartIds() != null) {
                for (String partId : composedNode.getPartIds()) {
                    TranslationNode part = getTranslationNodeById(partId);
                    if (part == null) {
                        continue;
                    }
                    for (int i = 0; i < currentEl.size(); i++) {
                        if (originalValues[i] != null
                                && part.getElementLabel() != null
                                && part.getElementLabel().equals(
                                        originalValues[i].getText()
                                )) {
                            indexes.add(Integer.valueOf(i));
                        }
                    }
                }
            }
            int[] selectedIndexes = new int[indexes.size()];
            for (int i = 0; i < indexes.size(); i++) {
                selectedIndexes[i] = indexes.get(i).intValue();
            }
            composedEntryParts.add(selectedIndexes);
            composedEntryOriginals.add(
                    composedNode.getOriginal() == null
                            ? ""
                            : composedNode.getOriginal()
            );
            composedEntryTranslations.add(
                    composedNode.getTranslation() == null
                            ? ""
                            : composedNode.getTranslation()
            );
            composedEntrySelector.addItem(
                    (composedNode.getOriginal() == null
                            ? ""
                            : composedNode.getOriginal())
                            + "  →  "
                            + (composedNode.getTranslation() == null
                            ? ""
                            : composedNode.getTranslation())
            );
        }

        loadComposedEntry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selected = composedEntrySelector.getSelectedIndex();
                if (selected < 0 || selected >= composedEntryOriginals.size()) {
                    return;
                }

                activeComposedEntryIndex[0] = selected;
                mayaText.setText(composedEntryOriginals.get(selected));
                translation.setText(composedEntryTranslations.get(selected));
                inDictionary.setSelected(true);

                for (int i = 0; i < partOfMU.length; i++) {
                    partOfMU[i].setSelected(false);
                }

                int[] selectedIndexes = composedEntryParts.get(selected);
                if (selectedIndexes != null) {
                    for (int i = 0; i < selectedIndexes.length; i++) {
                        int idx = selectedIndexes[i];
                        if (idx >= 0 && idx < partOfMU.length) {
                            partOfMU[idx].setSelected(true);
                        }
                    }
                }
            }
        });

        updateComposedEntry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selected = activeComposedEntryIndex[0];
                if (selected < 0) {
                    selected = composedEntrySelector.getSelectedIndex();
                }
                if (selected < 0 || selected >= composedEntryOriginals.size()) {
                    return;
                }

                ArrayList<Integer> selectedPartIndexes =
                        new ArrayList<Integer>();
                for (int i = 0; i < partOfMU.length; i++) {
                    if (partOfMU[i].isSelected()) {
                        selectedPartIndexes.add(Integer.valueOf(i));
                    }
                }

                int[] selectedIndexes = new int[selectedPartIndexes.size()];
                for (int sp = 0; sp < selectedPartIndexes.size(); sp++) {
                    selectedIndexes[sp] =
                            selectedPartIndexes.get(sp).intValue();
                }

                composedEntryParts.set(selected, selectedIndexes);
                composedEntryOriginals.set(
                        selected,
                        mayaText.getText() == null
                                ? ""
                                : mayaText.getText().trim()
                );
                composedEntryTranslations.set(
                        selected,
                        translation.getText() == null
                                ? ""
                                : translation.getText().trim()
                );

                String label = composedEntryOriginals.get(selected)
                        + "  →  "
                        + composedEntryTranslations.get(selected);
                composedEntrySelector.removeItemAt(selected);
                composedEntrySelector.insertItemAt(label, selected);
                composedEntrySelector.setSelectedIndex(selected);
                activeComposedEntryIndex[0] = selected;
            }
        });

        if (composedEntrySelector.getItemCount() > 0) {
            composedEntrySelector.setSelectedIndex(0);
            activeComposedEntryIndex[0] = 0;
            mayaText.setText(composedEntryOriginals.get(0));
            translation.setText(composedEntryTranslations.get(0));
            inDictionary.setSelected(true);

            for (int i = 0; i < partOfMU.length; i++) {
                partOfMU[i].setSelected(false);
            }
            int[] selectedIndexes = composedEntryParts.get(0);
            if (selectedIndexes != null) {
                for (int i = 0; i < selectedIndexes.length; i++) {
                    int idx = selectedIndexes[i];
                    if (idx >= 0 && idx < partOfMU.length) {
                        partOfMU[idx].setSelected(true);
                    }
                }
            }
        }

        JScrollPane initScroll = new JScrollPane(
                initCompon,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        initScroll.setPreferredSize(new Dimension(1060, 430));
        initScroll.setBorder(
                BorderFactory.createTitledBorder("Glossing elements")
        );
        content1.add(initScroll, g2);

        g2.gridy = 1;
        g2.gridwidth = 2;
        g2.gridx = 0;
        g2.weightx = 1.0;
        g2.weighty = 0.12;
        g2.fill = GridBagConstraints.BOTH;
        content1.add(panelMUnit, g2);

        generateMU.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s1 = "";
                String s2 = "";
                for (int i = 0; i < partOfMU.length; i++) {
                    if (partOfMU[i].isSelected()) {
                        s1 = s1 + originalValues[i].getText();
                        if (semanticValues[i].getText() != null
                                && !semanticValues[i].getText().trim().isEmpty()) {
                            if (!s2.trim().isEmpty()) {
                                s2 = s2 + " ";
                            }
                            s2 = s2 + semanticValues[i].getText().trim();
                        }
                    }
                }
                mayaText.setText(s1);
                translation.setText(s2);
            }
        });

        valid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s1 = konsolidTR.getText();
                String s2 = finalTr.getText();

                konsolidTR.setText(s1 + " " + mayaText.getText());
                finalTr.setText(s2 + " " + translation.getText());

                ArrayList<Integer> selectedPartIndexes =
                        new ArrayList<Integer>();

                for (int i = 0; i < partOfMU.length; i++) {
                    if (partOfMU[i].isSelected()) {
                        selectedPartIndexes.add(Integer.valueOf(i));
                    }
                    partOfMU[i].setSelected(false);
                }

                if (inDictionary.isSelected()
                        && mayaText.getText() != null
                        && !mayaText.getText().trim().isEmpty()) {
                    int[] selectedIndexes =
                            new int[selectedPartIndexes.size()];
                    for (int sp = 0; sp < selectedPartIndexes.size(); sp++) {
                        selectedIndexes[sp] =
                                selectedPartIndexes.get(sp).intValue();
                    }

                    String composedOriginal = mayaText.getText().trim();
                    String composedTranslation =
                            translation.getText() == null
                                    ? ""
                                    : translation.getText().trim();

                    int selectedComposed = activeComposedEntryIndex[0];
                    if (selectedComposed >= 0
                            && selectedComposed < composedEntryOriginals.size()) {
                        composedEntryParts.set(selectedComposed, selectedIndexes);
                        composedEntryOriginals.set(selectedComposed, composedOriginal);
                        composedEntryTranslations.set(selectedComposed, composedTranslation);

                        String label = composedOriginal + "  →  " + composedTranslation;
                        composedEntrySelector.removeItemAt(selectedComposed);
                        composedEntrySelector.insertItemAt(label, selectedComposed);
                        composedEntrySelector.setSelectedIndex(selectedComposed);
                    }
                    else {
                        composedEntryParts.add(selectedIndexes);
                        composedEntryOriginals.add(composedOriginal);
                        composedEntryTranslations.add(composedTranslation);
                        composedEntrySelector.addItem(
                                composedOriginal + "  →  " + composedTranslation
                        );
                        composedEntrySelector.setSelectedIndex(
                                composedEntrySelector.getItemCount() - 1
                        );
                    }
                }

                activeComposedEntryIndex[0] = -1;
                translation.setText("");
                mayaText.setText("");
            }
        });

        g2.gridy = 2;
        g2.gridx = 0;
        g2.gridwidth = 2;
        g2.weightx = 1.0;
        g2.weighty = 0.12;
        g2.fill = GridBagConstraints.BOTH;

        JScrollPane konsolidScroll = new JScrollPane(konsolidTR);
        konsolidScroll.setPreferredSize(new Dimension(1060, 90));
        konsolidScroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Maya text / consolidated translation"
                )
        );
        content1.add(konsolidScroll, g2);

        g2.gridx = 0;
        g2.gridy = 3;
        g2.weighty = 0.20;

        JScrollPane finalScroll = new JScrollPane(finalTr);
        finalScroll.setPreferredSize(new Dimension(1060, 145));
        finalScroll.setBorder(
                BorderFactory.createTitledBorder("Final translation")
        );
        content1.add(finalScroll, g2);

        JPanel bottomButtons =
                new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        JButton cancel = new JButton("Cancel");
        JButton save = new JButton("Save");

        bottomButtons.add(cancel);
        bottomButtons.add(save);

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                translationFrame.doDefaultCloseAction();
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ensureTranslationNodeModel();

                /*
                 * Replace this translation branch in the background model.
                 * This keeps the edit operation local to the selected Translation.
                 */
                removeTranslationNodeAndParts(translationId);

                ArrayList<String> dictionaryNodeIds =
                        new ArrayList<String>();
                HashMap<Integer, String> simpleNodeIdByIndex =
                        new HashMap<Integer, String>();

                for (int i = 0; i < currentEl.size(); i++) {
                    if (dictionaryEntries[i] != null
                            && dictionaryEntries[i].isSelected()) {
                        String simpleNodeId =
                                translationId + ">DE_SIMPLE_" + i;

                        TranslationNode simpleNode =
                                new TranslationNode(
                                        simpleNodeId,
                                        originalValues[i].getText(),
                                        semanticValues[i].getText()
                                );
                        simpleNode.setNodeKind("SimpleDictionaryEntry");
                        simpleNode.setParentMgId(currentNode.getId());
                        simpleNode.setElementLabel(originalValues[i].getText());
                        simpleNode.setSemanticMeaning(
                                semanticValues[i].getText()
                        );
                        simpleNode.setSyntacticFunction(syntacticValues[i]);
                        simpleNode.setWordNetSynsetId(wordNetValues[i]);
                        simpleNode.setExplanation(explanationValues[i]);
                        simpleNode.setDictionaryEntry(true);
                        simpleNode.setLabel(
                                "Element: " + originalValues[i].getText()
                                        + " | Meaning: "
                                        + semanticValues[i].getText()
                                        + " | Syntax: "
                                        + syntacticValues[i]
                        );

                        addOrReplaceTranslationNode(simpleNode);
                        dictionaryNodeIds.add(simpleNodeId);
                        simpleNodeIdByIndex.put(
                                Integer.valueOf(i),
                                simpleNodeId
                        );
                    }
                }

                for (int c = 0; c < composedEntryOriginals.size(); c++) {
                    String composedNodeId =
                            translationId + ">DE_COMPOSED_" + c;

                    TranslationNode composedNode =
                            new TranslationNode(
                                    composedNodeId,
                                    composedEntryOriginals.get(c),
                                    composedEntryTranslations.get(c)
                            );
                    composedNode.setNodeKind("ComposedDictionaryEntry");
                    composedNode.setParentMgId(currentNode.getId());
                    composedNode.setDictionaryEntry(true);
                    composedNode.setLabel(
                            "Maya: " + composedEntryOriginals.get(c)
                                    + " | Translation: "
                                    + composedEntryTranslations.get(c)
                    );

                    int[] selectedIndexes = composedEntryParts.get(c);
                    for (int pi = 0; pi < selectedIndexes.length; pi++) {
                        String partId =
                                simpleNodeIdByIndex.get(
                                        Integer.valueOf(
                                                selectedIndexes[pi]
                                        )
                                );
                        if (partId != null) {
                            composedNode.addPartId(partId);
                        }
                    }

                    addOrReplaceTranslationNode(composedNode);
                    dictionaryNodeIds.add(composedNodeId);
                }

                TranslationNode finalTranslationNode =
                        new TranslationNode(
                                translationId,
                                konsolidTR.getText(),
                                finalTr.getText()
                        );
                finalTranslationNode.setNodeKind("ConsolidatedTranslation");
                finalTranslationNode.setParentMgId(currentNode.getId());
                finalTranslationNode.setLabel(
                        "Maya: " + konsolidTR.getText()
                                + " | Translation: " + finalTr.getText()
                );

                for (String dictionaryNodeId : dictionaryNodeIds) {
                    finalTranslationNode.addPartId(dictionaryNodeId);
                }

                addOrReplaceTranslationNode(finalTranslationNode);

                replaceFinalTranslationInGuiModel(
                        translationId,
                        konsolidTR.getText(),
                        finalTr.getText()
                );

                changed = true;
                reloadBlockTree();
                translationFrame.doDefaultCloseAction();
            }
        });

        g2.fill = GridBagConstraints.HORIZONTAL;
        g2.weighty = 0.0;
        g2.gridy = 4;
        content1.add(bottomButtons, g2);

        translationFrame.pack();
        addChild(translationFrame, 650, 10, 1080, 840);
        translationFrame.setVisible(true);
        translationFrame.moveToFront();
    }
	 

	 public JTree builtAnnTree() {
			MyJTree blockTree=new MyJTree(createTreeModel());
			blockTree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		/*DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) blockTree.getCellRenderer();
		renderer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					
			renderer.setFont(typFont1);*/
			MyRenderer renderer =new MyRenderer();
		blockTree.setCellRenderer(renderer);
		UIManager.put("ToolTip.background", Color.DARK_GRAY);
		UIManager.put("ToolTip.foreground", Color.WHITE);
		UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 14));
		ToolTipManager.sharedInstance().registerComponent(blockTree);
		 UIManager.put("Tree.line", Color.DARK_GRAY);
		 //  blockTree.putClientProperty("JTree.lineStyle", "Horizontal");
	//	blockTree.putClientProperty("JTree.lineStyle", "Horizontal");
		
			expandTree(blockTree,true);
			blockTree.getModel().addTreeModelListener(new TreeModelListener() {
				public void treeStructureChanged(TreeModelEvent e) {
					blockTree.repaint();;
				//	 reloadBlockTree();
					// expandTree(blockTree,true);
				}
		      public void treeNodesChanged(TreeModelEvent e) {
		     blockTree.repaint();
		    		// reloadBlockTree();
		    	//  expandTree(blockTree,true);
				}
		      public void treeNodesInserted(TreeModelEvent e) {
		    	  reloadBlockTree();
				}
		      public void treeNodesRemoved(TreeModelEvent e) {
		    	 
		    	  reloadBlockTree();
		    	  
				}
			});
			blockTree.setFont(typFont1);
			
			DefaultMutableTreeNode root=(DefaultMutableTreeNode)blockTree.getModel().getRoot();
			

			blockTree.addTreeExpansionListener(new TreeExpansionListener() {
				public void treeCollapsed(TreeExpansionEvent e) {
					
				}
				public void treeExpanded(TreeExpansionEvent e) {
					
				}
			});
			
			blockTree.addMouseListener( new MouseAdapter() {
				private void myPopupEvent(MouseEvent e) {
		            int x = e.getX();
		            int y = e.getY();
		            JTree tree = (JTree)e.getSource();
		            TreePath path = tree.getPathForLocation(x, y);
            if (path == null) {
                return;
            }

            TreePath[] paths = tree.getSelectionPaths();
            boolean clickedPathIsSelected = false;
            if (paths != null) {
                for (int pIndex = 0; pIndex < paths.length; pIndex++) {
                    if (path.equals(paths[pIndex])) {
                        clickedPathIsSelected = true;
                    }
                }
            }
            if (paths == null || !clickedPathIsSelected) {
                tree.setSelectionPath(path);
                paths = new TreePath[] { path };
            }
            final TreePath[] selectedPaths = paths;

            for (TreePath path1 : selectedPaths) {
                System.out.println("You've selected: " + path1.getLastPathComponent());
            }
            System.out.println("You've selected: " + selectedPaths.length + " nodes");

            JPopupMenu popup = new JPopupMenu();

                   if (selectedPaths.length <= 1) {
                       final AnnotationNode confidenceObj = (AnnotationNode) path.getLastPathComponent();
                       if (confidenceObj.getMyTreeNode() instanceof TreeNode) {
                           popup.add(createInterpretationConfidenceMenu(confidenceObj));
                           popup.addSeparator();

                           final TreeNode frozenNode = confidenceObj.getMyTreeNode();
                           if (frozenNode.getFinish()) {
                               JMenuItem unfreezeOnly = new JMenuItem("Unfreeze branch");
                               unfreezeOnly.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent ev) {
                                       frozenNode.setFinish(false);
                                       changed = true;
                                       reloadBlockTree(confidenceObj);
                                   }
                               });
                               popup.add(unfreezeOnly);
                               popup.show(tree, x, y);
                               return;
                           }
                       }
                   }
                   if(selectedPaths.length>1) {
                	   int i=0; 
                	   boolean rightsel=true;
                	
                	   while((i<selectedPaths.length)&&rightsel) {
                		  AnnotationNode obj = ( AnnotationNode)selectedPaths[i].getLastPathComponent();
                		   String label= ""+obj.getMyTreeNode().getClass();
                		   if (!(label.equals("class allmahVer4.HieroglyphenBlock")))rightsel=false;
                		   i=i+1;
                	   }
                	   System.out.println("Selected "+path.getLastPathComponent()+ " nodes");
                	   if(rightsel) {
                		   final  JMenu menuAssign=new JMenu("Assign URI"); 
                		   final  JMenu menuAssignView=new JMenu("View assigned URI"); 
                		   final  JMenu menuDeleteAnnotation=new JMenu("Delete annotation"); 
                		//.setEnabled(false);
   	                    	 ArrayList<String> selection1=new ArrayList<String>();
   	                    	
   	                    	for(int j=0;j<selectedPaths.length;j++) {
   	                    	AnnotationNode obj = ( AnnotationNode)selectedPaths[j].getLastPathComponent();
   	                    	if (j==0) p1=blockTree.getPathBounds(selectedPaths[j]).getLocation();
   	                    	if(j==selectedPaths.length-1) p2=blockTree.getPathBounds(selectedPaths[j]).getLocation();
   	                    
                  			 HieroglyphenBlock currentNode= (HieroglyphenBlock)obj.getMyTreeNode();
                  			 selection1.add(currentNode.getId());        			
   	                    	}
   	                    	
   	                    	System.out.println("Selection1 "+ selection1);
   	                    	final JMenuItem menuJoinBlocks =
   	                    	        new JMenuItem("Join selected blocks");

   	                    	menuJoinBlocks.addActionListener(new ActionListener() {
   	                    	    public void actionPerformed(ActionEvent ev) {
   	                    	        joinSelectedBlocks(selectedPaths);
   	                    	    }
   	                    	});

   	                    	popup.add(menuJoinBlocks);
   	                    	
   	                    	ArrayList<BlockAnnotation> sela=new ArrayList<BlockAnnotation>();
   	                    	ArrayList<JMenuItem> selamenu=new ArrayList<JMenuItem>();
   	                    	ArrayList<JMenuItem> selamenuview=new ArrayList<JMenuItem>();
   	                    	for(int k=0;k<blockAnn.size();k++){
   	                    		System.out.println("Compare with "+ blockAnn.get(k).getAnnBorders());
   	                    		if(compareBorders(selection1,blockAnn.get(k).getAnnBorders())) {
   	                    			sela.add(blockAnn.get(k));
   	                    			selectedBlock=blockAnn.get(k);
   	                    			JMenuItem me=new JMenuItem(blockAnn.get(k).getTyp()+" "+blockAnn.get(k).getSubTyp());
   	                    			selamenu.add(me);
   	                    			menuAssign.add(me);
   	                    			JMenuItem mew=new JMenuItem(blockAnn.get(k).getTyp()+" "+blockAnn.get(k).getSubTyp());
   	                    			selamenuview.add(mew);
   	                    			menuAssignView.add(mew);

   	                    			final BlockAnnotation annotationToDelete = blockAnn.get(k);
   	                    			JMenuItem med = new JMenuItem(blockAnn.get(k).getTyp()+" "+blockAnn.get(k).getSubTyp());
   	                    			menuDeleteAnnotation.add(med);
   	                    			med.addActionListener(new ActionListener() {
   	                    				public void actionPerformed(ActionEvent e) {
   	                    					int answer = JOptionPane.showConfirmDialog(
   	                    							AllmahGUI.this,
   	                    							"Delete annotation "
   	                    									+ annotationToDelete.getTyp()
   	                    									+ " "
   	                    									+ annotationToDelete.getSubTyp()
   	                    									+ " and its assigned URI?",
   	                    							"Delete annotation",
   	                    							JOptionPane.YES_NO_OPTION
   	                    					);

   	                    					if (answer == JOptionPane.YES_OPTION) {
   	                    						deleteLocalBlockAnnotation(annotationToDelete);
   	                    					}
   	                    				}
   	                    			});
   	                    		   
   	                    			mew.addActionListener(new ActionListener() {
   	   	     	                    	public void actionPerformed(ActionEvent e) {
   	   	     	                    	ChildFrame  showURI= new ChildFrame("Show Assigne URI  " ,Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
   	   	     	                    	JEditorPane p=new JEditorPane();
   	   	     	                      p.setEditorKit(new HTMLEditorKit());
   	   	     	                      String s="<html>";
   	   	     	                    	if(selectedBlock.getActivity()!=null) {
   	   	     	                    		if (selectedBlock.getActivity().getTitle()!=null)
   	   	     	                    		s=s+"<b>Activity</b><br/>Description: "+selectedBlock.getActivity().getTitle()+"<br/>Place of Activity: "+selectedBlock.getActivity().getPlaceName();
   	   	     	                    	}
   	   	     	                     if(selectedBlock.getDedication()!=null) {
   	   	     	                      if (selectedBlock.getDedication().getTitle()!=null) {
   	   	     	                    	 if(s.indexOf("Activity")>=0) s=s+"<br/><br/>";
   	     	                    		s=s+"<b>Dedication</b><br/>Title: "+selectedBlock.getDedication().getTitle()+"<br/>Place of Activity: "+selectedBlock.getDedication().getPlaceName();
   	   	     	                      }
   	   	     	                      }
   	   	     	                 if(selectedBlock.getPlace()!=null) {
   	   	     	              if (selectedBlock.getPlace().getPlaceName()!=null) {
   	     	                    	 if((s.indexOf("Dedication")>=0)||(s.indexOf("Activity")>=0)) s=s+"<br/><br/>";
     	                    		s=s+"<b>Place</b><br/>Place Name: "+selectedBlock.getPlace().getPlaceName()+"<br/>Place is located in: "+selectedBlock.getPlace().getLocatedPlaceNames();
   	   	     	              }
   	   	     	              }
   	   	     	          if(selectedBlock.getEpigraphicActor()!=null) {
   	   	     	       if (selectedBlock.getEpigraphicActor().getActorName()!=null) {
     	                    	 if((s.indexOf("Place")>=0)|| (s.indexOf("Activity")>=0)||(s.indexOf("Dedication")>=0))s=s+"<br/><br/>";
	                    		s=s+"<b>Epigraphic Actor</b><br/>Person Name: "+selectedBlock.getEpigraphicActor().getActorName()+"<br/>Gender: "+selectedBlock.getEpigraphicActor().getDetails();
   	   	     	       } 	
   	   	     	       }
   	   	     	   if(selectedBlock.getEpigraphicGroup()!=null) {
   	   	     	if (selectedBlock.getEpigraphicGroup().getGroupName()!=null) {
                   	 if((s.indexOf("Place")>=0)|| (s.indexOf("Activity")>=0)||(s.indexOf("Actor")>=0)||(s.indexOf("Dedication")>=0)) s=s+"<br/><br/>";
              		s=s+"<b>Epigraphic Group</b><br/>Group Name: "+selectedBlock.getEpigraphicGroup().getGroupName();
   	   	     	}
   	   	     	}
   	   	     	   if(!s.equals("<html>")) s=s+"</html>";
   	   	     	   else s="";
   	   	     	   p.setText(s);
   	   	     	                    	showURI.getContentPane().add(new JScrollPane(p));
   	   	     	                 showURI.pack();
   	   	     	                   showURI.moveToFront();
             	 				addChild(showURI,500, 10, 500, 100);
   	   	     	                    	}
   	                    			});
   	   	     	                   me.addActionListener(new ActionListener() {
   	     	                    	public void actionPerformed(ActionEvent e) {
   	     	                    	ChildFrame  annuri= new ChildFrame("Assign URI  " ,Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
   	     	                    	JTextField searchField=new JTextField(20);
   	     	                    	JLabel hinweis=new JLabel ("Enter the name of an activity / monument name / date LC");
   	     	                    	JPanel resultsArea=new JPanel();
   	     	                    	
   	     	                    	
   	     	                    	JPanel p1=new JPanel(new GridBagLayout());
   	     	                    GridBagConstraints g2=new GridBagConstraints();    		
                    			g2.gridx=0;
                    	    	g2.gridy=0;
                    	    	g2.weightx=1.0;
                    	    	g2.weighty=0.1;
                    	    	g2.anchor=GridBagConstraints.NORTHWEST;
                    	    	g2.fill=GridBagConstraints.BOTH;
                    	    	g2.insets.top=5;
                    	    	g2.insets.bottom=5;
                    	    	g2.insets.left=5;
                    	    	g2.insets.right=5;
                    	    	g2.gridwidth=1;
                    	    	p1.add(hinweis,g2);
                    	    	g2.gridy=1;	g2.weighty=0.2;
   	     	                    	p1.add(searchField,g2);
   	     	                    g2.gridy=2;	g2.weighty=0.7;
   	     	                    	p1.add(new JScrollPane(resultsArea),g2);
   	     	                    	JButton search=new JButton("Search");
   	     	                   search.addActionListener(new ActionListener() {
   	     	                    	public void actionPerformed(ActionEvent e) {
   	     	                    		String s=searchField.getText();
   	     	                    		System.out.println(s);
   	     	                    	TripleStoreQuery query1=new TripleStoreQuery();
   	     	                  //  String ac="Activities+\n";
   	     	                    	try {
   	     	                       // HashMap<String,String> activ=query1.searchInActivities(s);
   	     	                    	List<Activity> activ=query1.searchInActivities(s);
   	     	                    	dataA=new String[activ.size()][2];
   	     	                    //Collection<String> c = activ.values();   
   	   	     	               // parse the collection
   	   	     	               Iterator<Activity> it = activ.iterator();
   	   	     	               int i=0;
   	   	     	               while(it.hasNext()) {
   	   	     	            	   Activity a=it.next();
   	   	     	                   dataA[i][0]=a.getTitle();
   	   	     	                 
   	   	     	                //   dataA[i][1]=a.getDatesInDifferentFormats().getBeginLongcount().toString();
   	   	     	            //dataA[i][2]=a.getDatesInDifferentFormats().getBeginCalenderRound().toString();
   	   	     	            dataA[i][1]=a.getPlaceName();
   	   	     	                   i=i+1;
   	   	     	               }
   	     	                    	}
   	     	                    	catch(Exception ex) {
   	     	                    	 dataA=new String[1][2];
   	     	                    		dataA[0][0]="Nothing found\n";};
   	     	                    //String ded="Dedications\n";
   	     	                       try {
   	     	                         List<Dedication> dedication=query1.searchInDedication(s);
   	     	                     dataD=new String[dedication.size()][2];
   	     	                         //Collection<String> c = dedication.values();   
   	     	               // parse the collection
   	     	               Iterator<Dedication> it = dedication.iterator();
   	     	              int  i=0;
   	     	               while(it.hasNext()) {
   	     	            	   Dedication d=it.next();
   	     	            	dataD[i][0]=d.getTitle();
   	     	                   dataD[i][1]=d.getPlaceName();
   	     	                   i=i+1;
   	     	               }
 	     	                    //   for(int i=0;i<dedication.size();i++) ded=ded+dedication.get(i)+"\n";
   	     	                       }
   	     	                   catch(Exception ex) {
   	     	                	 dataD=new String[1][2];
   	     	                	   dataD[0][0]="Nothing found\n";};
   	     	                   
   	     	                   
   	     	               try {
	     	                         List<Place> places=query1.searchInPlaces(s);
	     	                     dataP=new String[places.size()][2];
	     	                         //Collection<String> c = dedication.values();   
	     	               // parse the collection
	     	               Iterator<Place> it = places.iterator();
	     	              int  i=0;
	     	               while(it.hasNext()) {
	     	            	  Place d=it.next();
	     	            	 dataP[i][0]=d.getPlaceName();
	     	            	dataP[i][1]=d.getLocatedPlaceNames();
	     	                  
	     	                   i=i+1;
	     	               }
    	                    //   for(int i=0;i<dedication.size();i++) ded=ded+dedication.get(i)+"\n";
	     	                       }
	     	                   catch(Exception ex) {
	     	                	  dataP=new String[1][2];
	     	                	   dataP[0][0]="Nothing found\n";};
	     	                  try {
	     	                         List<EpigraphicActor> epa=query1.searchInEpigraphicActors(s);
	     	                     dataEA=new String[epa.size()][2];
	     	                         //Collection<String> c = dedication.values();   
	     	               // parse the collection
	     	               Iterator<EpigraphicActor> it = epa.iterator();
	     	              int  i=0;
	     	               while(it.hasNext()) {
	     	            	   EpigraphicActor d=it.next();
	     	            	dataEA[i][0]=d.getAltName();
	     	            	
	     	            
	     	                   dataEA[i][1]=d.getDetails();
	     	                   i=i+1;
	     	               }
	     	                    //   for(int i=0;i<dedication.size();i++) ded=ded+dedication.get(i)+"\n";
	     	                       }
	     	                   catch(Exception ex) {
	     	                	  dataEA=new String[1][2];
	     	                	   dataEA[0][0]="Nothing found\n";};     
	     	                  try {
	     	                         List<EpigraphicGroup> epg=query1.searchInEpigraphicGroups(s);
	     	                        
	     	                     dataEP=new String[epg.size()][1];
	     	                         //Collection<String> c = dedication.values();   
	     	               // parse the collection
	     	               Iterator<EpigraphicGroup> it = epg.iterator();
	     	              int  i=0;
	     	               while(it.hasNext()) {
	     	            	   EpigraphicGroup d=it.next();
	     	            	dataEP[i][0]=d.getGroupName();
	     	                   i=i+1;
	     	                  
	     	               }
	     	                         
	     	                        
	     	                    //   for(int i=0;i<dedication.size();i++) ded=ded+dedication.get(i)+"\n";
	     	                       }
	     	                   catch(Exception ex) {
	     	                	  dataEP=new String[1][2];
	     	                	  dataEP[0][0]="Nothing found\n";
	     	                	   };         
 	     	                        //System.out.println("Found " + ac+ded);
 	     	                        //resultsArea.setText(ac+ded);
 	     	                        actResults=new JTable(dataA,infoNamesA);
 	     	                      dedResults=new JTable(dataD,infoNamesD);
 	     	                    placeResults=new JTable(dataP,infoNamesP);
 	     	                 epigrActors=new JTable(dataEA,infoNamesEA);
 	     	               epigrGroups=new JTable(dataEP,infoNamesEP);
                                   resultsArea.setLayout(new GridLayout(1,4,1,1));
                                   JScrollPane scrollPaneA = new JScrollPane(actResults);
                                   scrollPaneA.setBorder(BorderFactory.createTitledBorder ("Activity"));
                                   resultsArea.add(scrollPaneA,0,0);
                                   JScrollPane scrollPaneD = new JScrollPane(dedResults);
                                   scrollPaneD.setBorder(BorderFactory.createTitledBorder ("Dedication"));
                                   resultsArea.add(scrollPaneD,0,1);
                                   JScrollPane scrollPaneP = new JScrollPane(placeResults);
                                   scrollPaneP.setBorder(BorderFactory.createTitledBorder ("Places"));
                                   resultsArea.add(scrollPaneP,0,2);
                                   JScrollPane scrollPaneEA = new JScrollPane(epigrActors);
                                   scrollPaneEA.setBorder(BorderFactory.createTitledBorder ("Epigraphic Actors"));
                                   resultsArea.add(scrollPaneEA,0,3);
                                   JScrollPane scrollPaneEP = new JScrollPane(epigrGroups);
                                   scrollPaneEP.setBorder(BorderFactory.createTitledBorder ("Epigraphic Groups"));
                                   resultsArea.add(scrollPaneEP,0,4);
                                   p1.revalidate();p1.repaint();
   	     	                    	}
   	     	                    });
   	     	                    	JButton assign=new JButton("Assign");
   	     	                    	
   	     	                    	JPanel p2=new JPanel(); p2.add(search);p2.add(assign);
   	     	                    assign.addActionListener(new ActionListener() {
   	     	                    	public void actionPerformed(ActionEvent e) {
   	     	                    		int kk=actResults.getSelectedRow();
   	     	                    	if (kk>=0) {
   	     	                    		selectedBlock.getActivity().setTitle(dataA[kk][0]);
   	     	                    	selectedBlock.getActivity().setPlaceName(dataA[kk][1]);
   	     	                    	}
   	     	                    kk=dedResults.getSelectedRow();
	     	                    	if (kk>=0) {
	     	                    		
	     	                    		selectedBlock.getDedication().setTitle(dataD[kk][0]);
	     	                    		selectedBlock.getDedication().setPlaceName(dataD[kk][1]);
	     	                    	}
	     	                    	  kk=placeResults.getSelectedRow();
		     	                    	if (kk>=0) {
		     	                           
		     	                    		selectedBlock.getPlace().setLocatedPlaceNames(dataP[kk][0]);	     	                    		
		     	                    		selectedBlock.getPlace().setPlaceName(dataP[kk][1]);
		     	                    	}	
		     	                    	  kk=epigrActors.getSelectedRow();
			     	                    	if (kk>=0) {
			     	                    		
			     	                    		selectedBlock.getEpigraphicActor().setActorName(dataEA[kk][0]);
			     	              
			     	                    		selectedBlock.getEpigraphicActor().setDetails(dataEA[kk][1]);
			     	                    	
			     	                    	}	
			     	                    	  kk=epigrGroups.getSelectedRow();
				     	                    	if (kk>=0) {
				     	                    		
				     	                    		selectedBlock.getEpigraphicGroup().setGroupName(dataEP[kk][0]);
				     	              
				     	                    						     	                    	
				     	                    	}
				     	                   menuAssignView.setEnabled(true);   	
   	     	                    		annuri.doDefaultCloseAction();
   	     	                    	}
   	     	                    });	
   	     	                    	annuri.getContentPane().add(p1);
   	     	                    	annuri.getContentPane().add(p2, BorderLayout.SOUTH);
   	     	                    annuri.pack();
            	 				annuri.moveToFront();
            	 				addChild(annuri,200, 10, 1500, 500);
   	     	                    	}
   	                    		 });
   	                    			//
   	                    		
   	                    		}
   	                    	
   	                    	}
   	                    
   	                    
                		   ArrayList<HieroglyphenBlock> selection=new ArrayList<HieroglyphenBlock>();
                		   final  JMenuItem menuOnomastics=new JMenuItem("Annotate Onomastics"); 
                		  
                		     menuOnomastics.addActionListener(new ActionListener() {
	                    	public void actionPerformed(ActionEvent e) {
	                    		
	                    		ChildFrame  annbwin= new ChildFrame("Onomastic Annotation  " ,Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
	            				Container ofin=annbwin.getContentPane();
	            				final JRadioButton onomasticOptions[] =new JRadioButton[onomasticValues.size()+1];
	            				ButtonGroup gr=new ButtonGroup();
	            				JPanel options=new JPanel(new GridLayout(onomasticValues.size(),1));
	            				for(int i=0;i<onomasticValues.size();i++) {
	            					onomasticOptions[i]=new JRadioButton(onomasticValues.get(i));
	            					onomasticOptions[i].setSelected(false);
	            					gr.add(onomasticOptions[i]);
	            					options.add(onomasticOptions[i]);
	            				}
	            				onomasticOptions[onomasticValues.size()]=new JRadioButton();
	            				onomasticOptions[onomasticValues.size()].setSelected(true);
	            				gr.add(onomasticOptions[onomasticValues.size()]);
	            				JButton ok=new JButton("Annotate");
	            				JButton cancel=new JButton("Cancel");
	            				JPanel buttons=new JPanel();
	            				buttons.add(ok);buttons.add(cancel);
	            				ofin.add(new JScrollPane(options));
	            				ofin.add(buttons, BorderLayout.SOUTH);
	            				annbwin.pack();
	            	 				annbwin.moveToFront();
	            	 				addChild(annbwin,200, 10, 300, 500);
	            				ok.addActionListener(new ActionListener() {
	            					public void actionPerformed(ActionEvent e) {
	            						int j=0; boolean found=false;
	            						int index=-1;
	            						while((j<onomasticValues.size())&&(!found)){
	            							if( onomasticOptions[j].isSelected()) {
	            								found=true; index=j;
	            							}
	            							j=j+1;
	            						}
	            						BlockAnnotation ba=new BlockAnnotation(doc.getId(),"onomastic");
	            						ba.setSubTyp(onomasticValues.get(index));
	            						for(int i=0;i<selectedPaths.length;i++) {
	   	                    			 AnnotationNode obj = ( AnnotationNode)selectedPaths[i].getLastPathComponent();
	   	                    			 HieroglyphenBlock currentNode= (HieroglyphenBlock)obj.getMyTreeNode();
	   	                    			// currentNode.setCalender("Lunar Series");
	   	                    			 ba.getAnnBorders().add(currentNode.getId());
	   	                    			 currentNode.getAnnotations().add(ba.getId());
	   	                    			  reloadBlockTree();
	   	                    			  obj.setExplored(true);
	   	                    		}
	            						blockAnn.add(ba);
	            						mbla.put(ba.getId(),new Integer(blockAnn.size()-1));
	            						annbwin.doDefaultCloseAction();
	            						expandTree(blockTree,true);
	            					}
	            					
	            				});
	            				cancel.addActionListener(new ActionListener() {
	            					public void actionPerformed(ActionEvent e) {
	            						annbwin.doDefaultCloseAction();
	            					}
	            				});
                		   
	                    	}
                      	   });	
                		   
                	   final  JMenuItem menuCalender=new JMenuItem("Annotate Calender"); 
                	   menuCalender.addActionListener(new ActionListener() {
	                    	public void actionPerformed(ActionEvent e) {
	                    		
	                    		ChildFrame  annbwin= new ChildFrame("Calender Annotation  " ,Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
	            				Container cfin=annbwin.getContentPane();
	            				final JRadioButton calenderOptions[] =new JRadioButton[calenderValues.size()+1];
	            				ButtonGroup gr=new ButtonGroup();
	            				JPanel options=new JPanel(new GridLayout(calenderValues.size(),1));
	            				for(int i=0;i<calenderValues.size();i++) {
	            					calenderOptions[i]=new JRadioButton(calenderValues.get(i));
	            					calenderOptions[i].setSelected(false);
	            					gr.add(calenderOptions[i]);
	            					options.add(calenderOptions[i]);
	            				}
	            				calenderOptions[calenderValues.size()]=new JRadioButton();
	            				calenderOptions[calenderValues.size()].setSelected(true);
	            				gr.add(calenderOptions[calenderValues.size()]);
	            				JButton ok=new JButton("Annotate");
	            				JButton cancel=new JButton("Cancel");
	            				JPanel buttons=new JPanel();
	            				buttons.add(ok);buttons.add(cancel);
	            				cfin.add(new JScrollPane(options));
	            				cfin.add(buttons, BorderLayout.SOUTH);
	            				annbwin.pack();
	            	 				annbwin.moveToFront();
	            	 				addChild(annbwin,200, 10, 300, 500);
	            				ok.addActionListener(new ActionListener() {
	            					public void actionPerformed(ActionEvent e) {
	            						int j=0; boolean found=false;
	            						int index=-1;
	            						while((j<calenderValues.size())&&(!found)){
	            							if( calenderOptions[j].isSelected()) {
	            								found=true; index=j;
	            							}
	            							j=j+1;
	            						}
	            						BlockAnnotation ba=new BlockAnnotation(doc.getId(),"calender");
	            						ba.setSubTyp(calenderValues.get(index));
	            						for(int i=0;i<selectedPaths.length;i++) {
	   	                    			 AnnotationNode obj = ( AnnotationNode)selectedPaths[i].getLastPathComponent();
	   	                    			 HieroglyphenBlock currentNode= (HieroglyphenBlock)obj.getMyTreeNode();
	   	                    			// currentNode.setCalender("Lunar Series");
	   	                    			 ba.getAnnBorders().add(currentNode.getId());
	   	                    			 currentNode.getAnnotations().add(ba.getId());
	   	                    			
	   	                    			  reloadBlockTree();
	   	                    			  obj.setExplored(true);
	   	                    		}
	            						blockAnn.add(ba);
	            						mbla.put(ba.getId(),new Integer(blockAnn.size()-1));
	            						annbwin.doDefaultCloseAction();
	            						expandTree(blockTree,true);
	            						
	   	    	         	   //   sc.repaint();
	            					}
	            					
	            				});
	            				cancel.addActionListener(new ActionListener() {
	            					public void actionPerformed(ActionEvent e) {
	            						annbwin.doDefaultCloseAction();
	            					}
	            				});
	                    	
	                    	}
                	   });
                	   popup.add(menuCalender);
                	   popup.add(menuOnomastics);
                	   popup.add(menuAssign);
                	   popup.add(menuAssignView);
                	   if (menuDeleteAnnotation.getItemCount() > 0) {
                		   popup.add(menuDeleteAnnotation);
                	   }

                	   }
                   }
                   else {
                	   tree.setSelectionPath(path);
		           AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
		           AnnotationNode prevobj= ( AnnotationNode)obj.getParent();
		//    System.out.println(""+obj.getMyTreeNode().getClass());
		            String label = ""+obj.getMyTreeNode().getClass();
		            
		          //  popup.add(new JMenuItem(label));
if(label.equals("class allmahVer4.HyerogliphenDocument")) {
	final  JMenuItem menuImageView=new JMenuItem("View Hieroglyph"); 
	   menuImageView.addActionListener(new ActionListener() {
     	public void actionPerformed(ActionEvent e) {
     		 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
     		HyerogliphenDocument currentNode= (HyerogliphenDocument)obj.getMyTreeNode();
     		try {
     		 File pathToFile = new File("D:\\ERCAdmin\\Maya\\Balakbal_Stela_Left.jpg");
     		 Image img = ImageIO.read(pathToFile);
     	
     		BufferedImage bimg=toBufferedImage( img);
     		ChildFrame  imgwin= new ChildFrame("Hieroglyph for " +currentNode.getTitle() ,Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
     		Container pen=imgwin.getContentPane();
     		//JLabel label = new JLabel(new ImageIcon("D:\\ERCAdmin\\Maya\\Balakbal_Stela_Left.jpg"));
     		MyImagePanel pane=new MyImagePanel(bimg);
     		 JScrollPane scrollPane = new JScrollPane(pane);
     	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
     	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
     	        scrollPane.setBounds(50, 30, 300, 50);
     		pen.add(scrollPane);
     		imgwin.pack();
				imgwin.moveToFront();
				addChild(imgwin,600, 10, 500, 500);
     		}
     		catch (IOException ex) {
     		    ex.printStackTrace();
     		}
     	}
	   }); 	
	   popup.add(menuImageView);

	
}

else if (label.equals("class allmahVer4.DocumentReading")) {
       if (docr != null && docr.size() >= 2) {
           final JMenuItem deleteReadingLocal = new JMenuItem("Delete local");
           deleteReadingLocal.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   AnnotationNode selectedNode = (AnnotationNode) path.getLastPathComponent();
                   DocumentReading currentReading = (DocumentReading) selectedNode.getMyTreeNode();

                   /*
                    * Important: detach the old JTree before renumbering maps.
                    * During setViewportView() Swing removes/revalidates the old
                    * tree and may call toString()/calculateLabel() on old nodes.
                    * If the maps have already been rebuilt without the deleted
                    * reading, those old nodes can no longer resolve their child
                    * ids and NumTranslit1.calculateLabel() throws NPE.
                    */
                   sc.setViewportView(new JPanel());
                   sc.revalidate();
                   sc.repaint();

                   boolean deleted = deleteLocalDocumentReading(currentReading);

                   if (!deleted) {
                       JOptionPane.showMessageDialog(
                               AllmahGUI.this,
                               "DocumentReading can be deleted only if at least two DocumentReadings exist.",
                               "Delete local",
                               JOptionPane.WARNING_MESSAGE
                       );
                       AllmahGUI.this.blockTree = builtAnnTree();
                       sc.setViewportView(AllmahGUI.this.blockTree);
                       return;
                   }

                   AllmahGUI.this.blockTree = builtAnnTree();
                   sc.setViewportView(AllmahGUI.this.blockTree);
                   sc.revalidate();
                   sc.repaint();
               }
           });
           popup.add(deleteReadingLocal);
       }
}

	
else  if (label.equals("class allmahVer4.HieroglyphenBlock")) {
		            
		            	 
                		   final  JMenuItem menuOnomastics=new JMenuItem("Annotate Onomastics"); 
                		  
                		     menuOnomastics.addActionListener(new ActionListener() {
	                    	public void actionPerformed(ActionEvent e) {
	                    		
	                    		ChildFrame  annbwin= new ChildFrame("Onomastic Annotation  " ,Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
	            				Container ofin=annbwin.getContentPane();
	            				final JRadioButton onomasticOptions[] =new JRadioButton[onomasticValues.size()+1];
	            				ButtonGroup gr=new ButtonGroup();
	            				JPanel options=new JPanel(new GridLayout(onomasticValues.size(),1));
	            				for(int i=0;i<onomasticValues.size();i++) {
	            					onomasticOptions[i]=new JRadioButton(onomasticValues.get(i));
	            					onomasticOptions[i].setSelected(false);
	            					gr.add(onomasticOptions[i]);
	            					options.add(onomasticOptions[i]);
	            				}
	            				onomasticOptions[onomasticValues.size()]=new JRadioButton();
	            				onomasticOptions[onomasticValues.size()].setSelected(true);
	            				gr.add(onomasticOptions[onomasticValues.size()]);
	            				JButton ok=new JButton("Annotate");
	            				JButton cancel=new JButton("Cancel");
	            				JPanel buttons=new JPanel();
	            				buttons.add(ok);buttons.add(cancel);
	            				ofin.add(new JScrollPane(options));
	            				ofin.add(buttons, BorderLayout.SOUTH);
	            				annbwin.pack();
	            	 				annbwin.moveToFront();
	            	 				addChild(annbwin,200, 10, 300, 500);
	            				ok.addActionListener(new ActionListener() {
	            					public void actionPerformed(ActionEvent e) {
	            						int j=0; boolean found=false;
	            						int index=-1;
	            						while((j<onomasticValues.size())&&(!found)){
	            							if( onomasticOptions[j].isSelected()) {
	            								found=true; index=j;
	            							}
	            							j=j+1;
	            						}
	            						BlockAnnotation ba=new BlockAnnotation(doc.getId(),"onomastic");
	            						ba.setSubTyp(onomasticValues.get(index));
	            						for(int i=0;i<selectedPaths.length;i++) {
	   	                    			 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
	   	                    			 HieroglyphenBlock currentNode= (HieroglyphenBlock)obj.getMyTreeNode();
	   	                    			// currentNode.setCalender("Lunar Series");
	   	                    			 ba.getAnnBorders().add(currentNode.getId());
	   	                    			 currentNode.getAnnotations().add(ba.getId());
	   	                    			  reloadBlockTree();
	   	                    			  obj.setExplored(true);
	   	                    		}
	            						blockAnn.add(ba);
	            						mbla.put(ba.getId(),new Integer(blockAnn.size()-1));
	            						annbwin.doDefaultCloseAction();
	            						expandTree(blockTree,true);
	            					}
	            					
	            				});
	            				cancel.addActionListener(new ActionListener() {
	            					public void actionPerformed(ActionEvent e) {
	            						annbwin.doDefaultCloseAction();
	            					}
	            				});
                		   
	                    	}
                      	   });	
                		   
                	   final  JMenuItem menuCalender=new JMenuItem("Annotate Calender"); 
                	   menuCalender.addActionListener(new ActionListener() {
	                    	public void actionPerformed(ActionEvent e) {
	                    		
	                    		ChildFrame  annbwin= new ChildFrame("Calender Annotation  " ,Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
	            				Container cfin=annbwin.getContentPane();
	            				final JRadioButton calenderOptions[] =new JRadioButton[calenderValues.size()+1];
	            				ButtonGroup gr=new ButtonGroup();
	            				JPanel options=new JPanel(new GridLayout(calenderValues.size(),1));
	            				for(int i=0;i<calenderValues.size();i++) {
	            					calenderOptions[i]=new JRadioButton(calenderValues.get(i));
	            					calenderOptions[i].setSelected(false);
	            					gr.add(calenderOptions[i]);
	            					options.add(calenderOptions[i]);
	            				}
	            				calenderOptions[calenderValues.size()]=new JRadioButton();
	            				calenderOptions[calenderValues.size()].setSelected(true);
	            				gr.add(calenderOptions[calenderValues.size()]);
	            				JButton ok=new JButton("Annotate");
	            				JButton cancel=new JButton("Cancel");
	            				JPanel buttons=new JPanel();
	            				buttons.add(ok);buttons.add(cancel);
	            				cfin.add(new JScrollPane(options));
	            				cfin.add(buttons, BorderLayout.SOUTH);
	            				annbwin.pack();
	            	 				annbwin.moveToFront();
	            	 				addChild(annbwin,200, 10, 300, 500);
	            				ok.addActionListener(new ActionListener() {
	            					public void actionPerformed(ActionEvent e) {
	            						int j=0; boolean found=false;
	            						int index=-1;
	            						while((j<calenderValues.size())&&(!found)){
	            							if( calenderOptions[j].isSelected()) {
	            								found=true; index=j;
	            							}
	            							j=j+1;
	            						}
	            						BlockAnnotation ba=new BlockAnnotation(doc.getId(),"calender");
	            						ba.setSubTyp(calenderValues.get(index));
	            						for(int i=0;i<selectedPaths.length;i++) {
	   	                    			 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
	   	                    			 HieroglyphenBlock currentNode= (HieroglyphenBlock)obj.getMyTreeNode();
	   	                    			// currentNode.setCalender("Lunar Series");
	   	                    			 ba.getAnnBorders().add(currentNode.getId());
	   	                    			 currentNode.getAnnotations().add(ba.getId());
	   	                    			  reloadBlockTree();
	   	                    			  obj.setExplored(true);
	   	                    		}
	            						blockAnn.add(ba);
	            						mbla.put(ba.getId(),new Integer(blockAnn.size()-1));
	            						annbwin.doDefaultCloseAction();
	            						expandTree(blockTree,true);
	            					}
	            					
	            				});
	            				cancel.addActionListener(new ActionListener() {
	            					public void actionPerformed(ActionEvent e) {
	            						annbwin.doDefaultCloseAction();
	            					}
	            				});
	                    	
	                    	}
                	   });
                	   popup.add(menuCalender);
                	   popup.add(menuOnomastics);

                	   
		            	 
		            	//
		            	if(((HieroglyphenBlock)obj.getMyTreeNode()).getState()) {
		                  final  JMenuItem menuHbI=new JMenuItem("View Graph Representation");
		                  menuHbI.addActionListener(new ActionListener() {
		                    	public void actionPerformed(ActionEvent e) {
		                    		HieroglyphenBlock currentNode= (HieroglyphenBlock)obj.getMyTreeNode();
		                    		VisualizationViewer<GlyphNode,OperatorLink>	vv=getGraphViewer(currentNode.getEntireGraphHB(), currentNode.getTreeGraphHB(),null,obj);
		                    		ChildFrame hbGFrame= new ChildFrame("Graph Representation for "+ Jsoup.parse(obj.getMyTreeNode().getNodeLabel()).text(),Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
		                    		 
		                    		   hbGFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                    		  
		                    	        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
		                    	     
		                    	        Container content =hbGFrame. getContentPane();
		                    		   content.add(panel); 
		                    		   final ScalingControl scaler = new CrossoverScalingControl();
		                    		  scaler.scale(vv,1/1.1f, vv.getCenter()); 
		                    		  //scaler.scale(vv, 1.1f, vv.getCenter()); scaler.scale(vv,1.1f, vv.getCenter());
		                    		//   scaler.scale(vv, 1.1f, vv.getCenter()); scaler.scale(vv, 1.1f, vv.getCenter()); scaler.scale(vv,1.1f, vv.getCenter());
		                    		//   hbGFrame.repaint();
		                    		     JButton plus = new JButton("+");
		                    		        plus.addActionListener(new ActionListener() {
		                    		            public void actionPerformed(ActionEvent e) {
		                    		                scaler.scale(vv, 1.1f, vv.getCenter());
		                    		            }
		                    		        });
		                    		        JButton minus = new JButton("-");
		                    		        minus.addActionListener(new ActionListener() {
		                    		            public void actionPerformed(ActionEvent e) {
		                    		                scaler.scale(vv, 1/1.1f, vv.getCenter());
		                    		            }
		                    		        });
		                    		   
		                    		        JPanel scaleGrid = new JPanel(new GridLayout(1,0));
		                    		        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));

		                    		        JPanel controls = new JPanel();
		                    		        scaleGrid.add(plus);
		                    		        scaleGrid.add(minus);
		                    		        controls.add(scaleGrid);
		                    		       content.add(controls, BorderLayout.SOUTH);
		                    		     
		                    		   hbGFrame.pack();
		                    		   addChild(hbGFrame,800, 10, 500, 600);		
		                    		   hbGFrame.setVisible(true); 
		                    		   hbGFrame.moveToFront();
		                    	}
		                 });
		                    popup.add(menuHbI);
		               
		            }
		                 
		            }
		            else if (label.equals("class allmahVer4.GraphTranslit1")) {
		                
		                  final  JMenu menuCertain=new JMenu("Certainty image");
                          final JMenuItem menuGr1D = new JMenuItem("Delete local");
		                 
                                final  JMenuItem certainLevel[]=new JMenuItem[8];
		              
		                 for (int i1=0;i1<8;i1++) {
		                	 final int j=i1+1;
		                	               certainLevel[i1]=new JMenuItem(""+(i1+1));
		                	 certainLevel[i1].addActionListener(new ActionListener() {
			                    	public void actionPerformed(ActionEvent e) {
			                    		
			                    		 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
				                    		GraphTranslit1 currentNode= (GraphTranslit1)obj.getMyTreeNode();
				                    		currentNode.setPathImage(SpecialSymbols.pathAuswertung+"GT1_"+j+".png");
				                    		reloadBlockTree(obj);
			                    	}
		                	 });
			                  menuCertain.add(certainLevel[i1]);  	
		                 }
		                 
		                   // popup.add(menuCertain); // removed: image certainty menu is no longer used

                          menuGr1D.addActionListener(new ActionListener() {
                              public void actionPerformed(ActionEvent e) {
                                  AnnotationNode obj = (AnnotationNode) path.getLastPathComponent();
                                  GraphTranslit1 currentNode = (GraphTranslit1) obj.getMyTreeNode();
                                  javax.swing.tree.TreeNode parentNode = obj.getParent();
                                  boolean deleted = deleteLocalGT1(currentNode, obj);

                                  if (!deleted) {
                                      JOptionPane.showMessageDialog(
                                              AllmahGUI.this,
                                              "GT1 could not be removed from its parent NT2 list.",
                                              "Delete local",
                                              JOptionPane.WARNING_MESSAGE
                                      );
                                      return;
                                  }

                                  if (blockTree != null
                                          && blockTree.getModel() instanceof DefaultTreeModel
                                          && parentNode instanceof AnnotationNode) {
                                      ((DefaultTreeModel) blockTree.getModel()).removeNodeFromParent(obj);
                                      reloadBlockTree(parentNode);
                                  }
                                  else {
                                      reloadBlockTree();
                                  }
                              }
                          });

                          AnnotationNode obj1 = (AnnotationNode) path.getLastPathComponent();
                          if (!obj1.getMyTreeNode().getFinish()) {
                              popup.add(menuGr1D);
                          }
		                 
		       
		            }
		            else if(label.equals("class allmahVer4.MorphoSyntGlossing")) {
		            	final  JMenuItem addTr=new JMenuItem("Add Translation");
	                    addTr.addActionListener(new ActionListener() {
	                    	public void actionPerformed(ActionEvent e) {
	                    		
	                    	 ChildFrame translationFrame= new ChildFrame("Translation for "+ Jsoup.parse(obj.getMyTreeNode().getNodeLabel()).text(),Color.GRAY,WindowConstants.DISPOSE_ON_CLOSE);
	                    		   translationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                    		   Container content1=translationFrame.getContentPane();
	                    		   AnnotationNode obj2 = ( AnnotationNode)path.getLastPathComponent();
		                    		  String idcurrentNode1= ((MorphoSyntGlossing)obj2.getMyTreeNode()).getId();
		                    		System.out.println("ID Sel Glossing "+idcurrentNode1);
		                    		  MorphoSyntGlossing currentNode=mg.get(mmg.get(idcurrentNode1).intValue());
		                    		  MorphoSyntTranslit currentNodeParent=mt.get(mmt.get(currentNode.getParent()).intValue());
		                    		  ArrayList<MayaWord> maya=new ArrayList<MayaWord>();
                 			  JButton generateMU=new JButton("Generate Maya Unit");
                 			  JTextField mayaText=new JTextField(45);
                 			  JTextField translation=new JTextField(45);
                 			  JCheckBox inDictionary =new JCheckBox("Save in Dictionary");
                 			  JButton valid=new JButton("Validate");
                 			  JPanel panelMUnit=new JPanel(new GridBagLayout());
                 			  panelMUnit.setBorder(BorderFactory.createTitledBorder("Selected Maya Unit"));
                 			  GridBagConstraints gMU=new GridBagConstraints();
                 			  gMU.insets.top=3;
                 			  gMU.insets.bottom=3;
                 			  gMU.insets.left=5;
                 			  gMU.insets.right=5;
                 			  gMU.anchor=GridBagConstraints.WEST;
                 			  gMU.fill=GridBagConstraints.HORIZONTAL;
                 			  gMU.weightx=1.0;
                 			  gMU.gridx=0;
                 			  gMU.gridy=0;
                 			  gMU.gridwidth=2;
                 			  panelMUnit.add(inDictionary,gMU);
                 			  gMU.gridy=1;
                 			  panelMUnit.add(new JLabel("Maya text"),gMU);
                 			  gMU.gridy=2;
                 			  panelMUnit.add(mayaText,gMU);
                 			  gMU.gridy=3;
                 			  panelMUnit.add(new JLabel("Translation"),gMU);
                 			  gMU.gridy=4;
                 			  panelMUnit.add(translation,gMU);
                 			  JPanel mayaUnitButtonRow = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
                 			  mayaUnitButtonRow.add(valid);
                 			  mayaUnitButtonRow.add(generateMU);
                 			  gMU.gridy=5;
                 			  gMU.gridwidth=2;
                 			  gMU.anchor=GridBagConstraints.WEST;
                 			  gMU.fill=GridBagConstraints.NONE;
                 			  panelMUnit.add(mayaUnitButtonRow,gMU);
		                    		  ArrayList<GlossingVariant>  currentEl= currentNode.getElements();
		                    		JTextArea konsolidTR= new JTextArea(4,70);
		                    		  JTextArea finalTr=new JTextArea(10,70);

		                    		konsolidTR.setLineWrap(true);
		                    		konsolidTR.setWrapStyleWord(true);
		                    		finalTr.setLineWrap(true);
		                    		finalTr.setWrapStyleWord(true);
		                    		konsolidTR.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
		                    		finalTr.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
		                    		content1.setLayout(new GridBagLayout());

		                JPanel initCompon = new JPanel(new GridLayout(0,2,8,8));
		                initCompon.setBackground(Color.WHITE);

		                GridBagConstraints g2=new GridBagConstraints();
		                g2.gridx=0;
		                g2.gridy=0;
		                g2.weightx=1.0;
		                g2.weighty=0.60;
		                g2.anchor=GridBagConstraints.NORTHWEST;
		                g2.fill=GridBagConstraints.BOTH;
		                g2.insets.top=5;
		                g2.insets.bottom=3;
		                g2.insets.left=5;
		                g2.insets.right=5;
		                g2.gridwidth=2;

		                final JCheckBox[] partOfMU = new JCheckBox[currentEl.size()];
		                final JCheckBox[] dictionaryEntries = new JCheckBox[currentEl.size()];
		                final JTextField[] semanticValues = new JTextField[currentEl.size()];
		                final JLabel[] originalValues = new JLabel[currentEl.size()];
		                final String[] syntacticValues = new String[currentEl.size()];
		                final String[] wordNetValues = new String[currentEl.size()];
		                final String[] explanationValues = new String[currentEl.size()];
		                final ArrayList<int[]> composedEntryParts = new ArrayList<int[]>();
		                final ArrayList<String> composedEntryOriginals = new ArrayList<String>();
		                final ArrayList<String> composedEntryTranslations = new ArrayList<String>();

		                for (int i=0;i<currentEl.size();i++) {
		                    final int localIndex = i;
		                    GlossingVariant variant = currentEl.get(i);

		                    JPanel card = new JPanel(new GridBagLayout());
		                    card.setPreferredSize(new Dimension(480, 210));
		                    card.setMinimumSize(new Dimension(420, 190));
		                    card.setBorder(BorderFactory.createCompoundBorder(
		                            BorderFactory.createEtchedBorder(),
		                            BorderFactory.createEmptyBorder(8,8,8,8)
		                    ));
		                    card.setBackground(new Color(248,248,248));

		                    GridBagConstraints left = new GridBagConstraints();
		                    left.gridx = 0;
		                    left.gridy = 0;
		                    left.weightx = 0.45;
		                    left.weighty = 0.0;
		                    left.insets.top = 2;
		                    left.insets.bottom = 2;
		                    left.insets.left = 2;
		                    left.insets.right = 8;
		                    left.anchor = GridBagConstraints.WEST;
		                    left.fill = GridBagConstraints.HORIZONTAL;

		                    GridBagConstraints right = new GridBagConstraints();
		                    right.gridx = 1;
		                    right.gridy = 0;
		                    right.gridheight = 8;
		                    right.weightx = 0.55;
		                    right.weighty = 1.0;
		                    right.insets.top = 2;
		                    right.insets.bottom = 2;
		                    right.insets.left = 8;
		                    right.insets.right = 2;
		                    right.anchor = GridBagConstraints.NORTHWEST;
		                    right.fill = GridBagConstraints.BOTH;

		                    String originalText = "";
		                    if (variant.getParent() != null) {
		                        originalText = variant.getParent().calculateLabel2();
		                    }
		                    originalValues[i] = new JLabel(originalText);
		                    originalValues[i].setFont(typFont1.deriveFont(Font.BOLD, 14));
		                    JLabel elementLabel = new JLabel("Element:  " + originalText);
		                    elementLabel.setFont(typFont1.deriveFont(Font.BOLD, 13));
		                    card.add(elementLabel, left);

		                    String semanticText = "";
		                    if (variant.getSemantics() != null
		                            && variant.getSemantics().getMeaning() != null) {
		                        semanticText = variant.getSemantics().getMeaning().trim();
		                    }
		                    if (semanticText.equalsIgnoreCase("No Meaning")
		                            || semanticText.trim().isEmpty()) {
		                        semanticText = originalText;
		                    }

		                    left.gridy++;
		                    JLabel semanticCaption = new JLabel("Semantic Meaning");
		                    semanticCaption.setFont(typFont1.deriveFont(Font.BOLD, 13));
		                    card.add(semanticCaption, left);

		                    left.gridy++;
		                    semanticValues[i] = new JTextField(semanticText, 16);
		                    semanticValues[i].setEditable(false);
		                    semanticValues[i].setFont(typFont1.deriveFont(Font.PLAIN, 13));
		                    card.add(semanticValues[i], left);

		                    String syntaxText = "";
		                    if (variant.getSyntax() != null
		                            && variant.getSyntax().getSyntFeature() != null
		                            && variant.getSyntax().getSyntFeature().getAbbreviation() != null) {
		                        syntaxText = variant.getSyntax().getSyntFeature().getAbbreviation();
		                    }
		                    syntacticValues[i] = syntaxText;
		                    left.gridy++;
		                    JLabel syntaxCaption = new JLabel("Syntactic Function");
		                    syntaxCaption.setFont(typFont1.deriveFont(Font.BOLD, 13));
		                    card.add(syntaxCaption, left);

		                    left.gridy++;
		                    JLabel syntaxLabel = new JLabel(syntaxText);
		                    syntaxLabel.setFont(typFont1.deriveFont(Font.PLAIN, 13));
		                    card.add(syntaxLabel, left);

		                    left.gridy++;
		                    JCheckBox dictionaryEntry = new JCheckBox("Entry in Dictionary");
		                    dictionaryEntry.setSelected(false);
		                    dictionaryEntry.setOpaque(false);
		                    dictionaryEntries[i] = dictionaryEntry;
		                    card.add(dictionaryEntry, left);

		                    String explanationText = "";
                    try {
                        if (variant.getSemantics() != null
                                && variant.getSemantics().getLinkWN() != null
                                && !variant.getSemantics().getLinkWN().trim().isEmpty()
                                && dict != null) {

                            String linkWN = stripImportedHtml(
                                    variant.getSemantics().getLinkWN()
                            ).trim();

                            String rawMeaning = "";
                            if (variant.getSemantics().getMeaning() != null) {
                                rawMeaning = stripImportedHtml(
                                        variant.getSemantics().getMeaning()
                                ).trim();
                            }

                            /*
                             * IMPORTANT:
                             * The visible semantic meaning may contain a final '?'
                             * because the semantic interpretation is uncertain.
                             * This '?' must remain visible in the Semantic Meaning field,
                             * but it is not part of a WordNet lemma and must not be used
                             * for WordNet lookup.
                             *
                             * WordNet explanation is resolved only from the stored WordNet
                             * reference.  The meaning is used only as the missing lemma part
                             * for old ALLMAH WID prefixes such as WID-01234567-V.
                             */
                            String lookupMeaning = rawMeaning;
                            if (lookupMeaning.endsWith("?")) {
                                lookupMeaning = lookupMeaning.substring(
                                        0,
                                        lookupMeaning.length() - 1
                                ).trim();
                            }
                            if (lookupMeaning.equalsIgnoreCase("No Meaning")) {
                                lookupMeaning = "";
                            }

                            ArrayList<String> wordIdCandidates =
                                    new ArrayList<String>();
                            ArrayList<String> synsetIdCandidates =
                                    new ArrayList<String>();

                            String normalizedLink = linkWN
                                    .replace("&lt;", "")
                                    .replace("&gt;", "")
                                    .replace("<", "")
                                    .replace(">", "")
                                    .trim();

                            /*
                             * 1. Try the value exactly as stored.  This preserves complete
                             *    JWI WordIDs such as WID-01234567-N-word.
                             */
                            wordIdCandidates.add(normalizedLink);
                            synsetIdCandidates.add(normalizedLink);

                            /*
                             * 2. Extract complete WordIDs from URI-like values.
                             */
                            java.util.regex.Matcher wordMatcher =
                                    java.util.regex.Pattern
                                            .compile("(?i)(WID-[0-9]{8}-[NVARSP]-[^\\s<>#/]+)")
                                            .matcher(normalizedLink);
                            while (wordMatcher.find()) {
                                wordIdCandidates.add(wordMatcher.group(1));
                            }

                            /*
                             * 3. Old ALLMAH data may store only WID-01234567-N and keep
                             *    the lemma separately as semantic meaning.  Reconstruct
                             *    a complete JWI WordID here.
                             */
                            java.util.regex.Matcher wordPrefixMatcher =
                                    java.util.regex.Pattern
                                            .compile("(?i)(WID-[0-9]{8}-[NVARSP])(?:$|[^A-Za-z0-9_])")
                                            .matcher(normalizedLink);
                            while (wordPrefixMatcher.find()) {
                                String widPrefix = wordPrefixMatcher.group(1).toUpperCase();
                                if (lookupMeaning != null
                                        && !lookupMeaning.trim().isEmpty()) {
                                    ArrayList<String> lemmaCandidates =
                                            new ArrayList<String>();
                                    lemmaCandidates.add(lookupMeaning.trim());
                                    lemmaCandidates.add(
                                            lookupMeaning.trim().toLowerCase()
                                    );
                                    lemmaCandidates.add(
                                            lookupMeaning.trim().replace(' ', '_')
                                    );
                                    lemmaCandidates.add(
                                            lookupMeaning.trim().toLowerCase().replace(' ', '_')
                                    );

                                    for (String lemmaCandidate : lemmaCandidates) {
                                        if (lemmaCandidate != null
                                                && !lemmaCandidate.trim().isEmpty()) {
                                            wordIdCandidates.add(
                                                    widPrefix + "-" + lemmaCandidate.trim()
                                            );
                                        }
                                    }
                                }
                            }

                            /*
                             * 4. Synset IDs can occur as SID-01234567-N,
                             *    eng-30-01234567-n, 01234567-n, or inside a URI.
                             */
                            java.util.regex.Matcher sidMatcher =
                                    java.util.regex.Pattern
                                            .compile("(?i)(SID-[0-9]{8}-[NVARSP])")
                                            .matcher(normalizedLink);
                            while (sidMatcher.find()) {
                                synsetIdCandidates.add(sidMatcher.group(1).toUpperCase());
                            }

                            java.util.regex.Matcher offsetMatcher =
                                    java.util.regex.Pattern
                                            .compile("(?i)([0-9]{8})[-_:/#]?([nvars])")
                                            .matcher(normalizedLink);
                            while (offsetMatcher.find()) {
                                String offsetText = offsetMatcher.group(1);
                                String posText = offsetMatcher.group(2).toLowerCase();
                                String posUpper = "N";
                                if ("v".equals(posText)) {
                                    posUpper = "V";
                                }
                                else if ("a".equals(posText) || "s".equals(posText)) {
                                    posUpper = "A";
                                }
                                else if ("r".equals(posText)) {
                                    posUpper = "R";
                                }
                                synsetIdCandidates.add(
                                        "SID-" + offsetText + "-" + posUpper
                                );
                            }

                            ISynset synset = null;

                            for (String candidateWordId : wordIdCandidates) {
                                if (synset != null) {
                                    break;
                                }
                                try {
                                    IWordID wordId = WordID.parseWordID(candidateWordId);
                                    IWord word = dict.getWord(wordId);
                                    if (word != null && word.getSynset() != null) {
                                        synset = word.getSynset();
                                    }
                                }
                                catch (Exception ignoreWordId) {
                                    // try next candidate
                                }
                            }

                            for (String candidateSynsetId : synsetIdCandidates) {
                                if (synset != null) {
                                    break;
                                }
                                try {
                                    ISynsetID synsetId = SynsetID.parseSynsetID(candidateSynsetId);
                                    synset = dict.getSynset(synsetId);
                                }
                                catch (Exception ignoreSynsetId) {
                                    // try next candidate
                                }
                            }

                            if (synset != null && synset.getGloss() != null) {
                                explanationText = cleanWordNetGlossForTranslationWindow(
                                        synset.getGloss()
                                );
                            }
                        }
                    }
                    catch (Exception exWordNet) {
                        explanationText = "";
                    }
                    if (explanationText == null) {
                        explanationText = "";
                    }
                    explanationValues[i] = explanationText;
                    if (variant.getSemantics() != null
                            && variant.getSemantics().getLinkWN() != null) {
                        wordNetValues[i] = stripImportedHtml(variant.getSemantics().getLinkWN()).trim();
                    }
                    else {
                        wordNetValues[i] = "";
                    }

                    JPanel explanationPanel = new JPanel(new BorderLayout(0,4));
		                    explanationPanel.setOpaque(false);
		                    JLabel explanationCaption = new JLabel("Explanation");
		                    explanationCaption.setFont(typFont1.deriveFont(Font.BOLD, 13));
		                    explanationPanel.add(explanationCaption, BorderLayout.NORTH);
		                    JTextArea explanationArea = new JTextArea(explanationText, 5, 22);
		                    explanationArea.setEditable(false);
		                    explanationArea.setLineWrap(true);
		                    explanationArea.setWrapStyleWord(true);
		                    explanationArea.setFont(typFont1.deriveFont(Font.PLAIN, 12));
		                    JScrollPane explanationScroll = new JScrollPane(explanationArea);
		                    explanationScroll.setPreferredSize(new Dimension(260, 125));
		                    explanationScroll.setMinimumSize(new Dimension(230, 105));
		                    explanationPanel.add(explanationScroll, BorderLayout.CENTER);
		                    card.add(explanationPanel, right);

		                    JPanel editRow = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 0));
		                    editRow.setOpaque(false);
		                    JButton editB = new JButton("Edit");
		                    JButton useB = new JButton("Use");
		                    final JTextField localSemanticField = semanticValues[i];
		                    editB.addActionListener(new ActionListener() {
		                        public void actionPerformed(ActionEvent e) {
		                            localSemanticField.setEditable(!localSemanticField.isEditable());
		                            if (localSemanticField.isEditable()) {
		                                localSemanticField.requestFocusInWindow();
		                                localSemanticField.selectAll();
		                            }
		                        }
		                    });
		                    useB.addActionListener(new ActionListener() {
		                        public void actionPerformed(ActionEvent e) {
		                            String mayaUnit = originalValues[localIndex].getText();
		                            String translationUnit = localSemanticField.getText();
		                            if (mayaUnit != null && !mayaUnit.trim().isEmpty()) {
		                                String currentMaya = konsolidTR.getText();
		                                if (currentMaya == null || currentMaya.trim().isEmpty()) {
		                                    konsolidTR.setText(mayaUnit.trim());
		                                }
		                                else {
		                                    konsolidTR.setText(currentMaya.trim() + " " + mayaUnit.trim());
		                                }
		                            }
		                            if (translationUnit != null && !translationUnit.trim().isEmpty()) {
		                                String currentTranslation = finalTr.getText();
		                                if (currentTranslation == null || currentTranslation.trim().isEmpty()) {
		                                    finalTr.setText(translationUnit.trim());
		                                }
		                                else {
		                                    finalTr.setText(currentTranslation.trim() + " " + translationUnit.trim());
		                                }
		                            }
		                        }
		                    });
		                    editRow.add(editB);
		                    editRow.add(useB);

		                    left.gridy++;
		                    left.fill = GridBagConstraints.NONE;
		                    left.anchor = GridBagConstraints.EAST;
		                    card.add(editRow, left);

		                    partOfMU[i] = new JCheckBox("part of");
		                    partOfMU[i].setSelected(false);
		                    partOfMU[i].setOpaque(false);
		                    left.gridy++;
		                    left.anchor = GridBagConstraints.WEST;
		                    card.add(partOfMU[i], left);

		                    initCompon.add(card);
		                }

		                JScrollPane initScroll = new JScrollPane(
		                        initCompon,
		                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		                );
		                initScroll.setPreferredSize(new Dimension(1060,430));
		                initScroll.setBorder(BorderFactory.createTitledBorder("Glossing elements"));
		                content1.add(initScroll,g2);

		                g2.gridy=1; g2.gridwidth=2; g2.gridx=0;
		                g2.weightx=1.0;
		                g2.weighty=0.12;
		                g2.fill=GridBagConstraints.BOTH;
		                content1.add(panelMUnit,g2);

		                generateMU.addActionListener(new ActionListener() {
		                    public void actionPerformed(ActionEvent e) {
		                        String s1="";
		                        String s2="";
		                        for (int i=0;i<partOfMU.length;i++) {
		                            if(partOfMU[i].isSelected()) {
		                                s1=s1+originalValues[i].getText();
		                                if (semanticValues[i].getText() != null
		                                        && !semanticValues[i].getText().trim().isEmpty()) {
		                                    if (!s2.trim().isEmpty()) {
		                                        s2=s2+" ";
		                                    }
		                                    s2=s2+semanticValues[i].getText().trim();
		                                }
		                            }
		                        }
		                        mayaText.setText(s1);
		                        translation.setText(s2);
		                    }
		                });

		                valid.addActionListener(new ActionListener() {
		        		   public void actionPerformed(ActionEvent e) {
		        			   String s1=konsolidTR.getText(); String s2=finalTr.getText();
		        			  konsolidTR.setText(s1+" "+mayaText.getText());
		        			  finalTr.setText(s2+" "+translation.getText());
		        			  ArrayList<GlossingVariant> parents=new ArrayList<GlossingVariant>();
		        			  ArrayList<Integer> selectedPartIndexes = new ArrayList<Integer>();
		        			  for(int i=0;i<partOfMU.length;i++) {
		        				  if(partOfMU[i].isSelected()) {
		        				      parents.add(currentEl.get(i));
		        				      selectedPartIndexes.add(Integer.valueOf(i));
		        				  }
		        				  partOfMU[i].setSelected(false);
		        			  }
		        			  if (inDictionary.isSelected()
		        			          && mayaText.getText() != null
		        			          && !mayaText.getText().trim().isEmpty()) {
		        			      int[] selectedIndexes = new int[selectedPartIndexes.size()];
		        			      for (int sp = 0; sp < selectedPartIndexes.size(); sp++) {
		        			          selectedIndexes[sp] = selectedPartIndexes.get(sp).intValue();
		        			      }
		        			      composedEntryParts.add(selectedIndexes);
		        			      composedEntryOriginals.add(mayaText.getText().trim());
		        			      composedEntryTranslations.add(translation.getText() == null ? "" : translation.getText().trim());
		        			  }
		        			  translation.setText("");
		        			  mayaText.setText("");
		        		   }
		        	   });
		        			g2.gridy=2;g2.gridx=0;
		        			g2.gridwidth=2;
		        			g2.weightx=1.0;
		        			g2.weighty=0.12;
		        		    	g2.fill=GridBagConstraints.BOTH;
		        			JScrollPane konsolidScroll = new JScrollPane(konsolidTR);
		        			konsolidScroll.setPreferredSize(new Dimension(1060,90));
		        			konsolidScroll.setBorder(BorderFactory.createTitledBorder("Maya text / consolidated translation"));
		        			content1.add(konsolidScroll,g2);
		        		    g2.gridx=0;g2.gridy=3;
		        		    g2.weighty=0.20;
		        		    JScrollPane finalScroll = new JScrollPane(finalTr);
		        		    finalScroll.setPreferredSize(new Dimension(1060,145));
		        		    finalScroll.setBorder(BorderFactory.createTitledBorder("Final translation"));
		        		    content1.add(finalScroll,g2);
		        		    JButton assign=new JButton("Assign");
		        		    assign.addActionListener(new ActionListener() {
		        		    	public void actionPerformed(ActionEvent e) {
		        		    		
		        		    		for(int i=0;i<maya.size();i++) {
		        		    			//maya.get(i).setTranslation();
		        		    		}
		        		    	
			        		    	if (mtr == null) {
			        		    	    mtr = new ArrayList<FinalTranslation>();
			        		    	}
			        		    	if (mmtr == null) {
			        		    	    mmtr = new HashMap<String,Integer>();
			        		    	}

			        		    	String translationId = currentNode.getId() + ">TR_" + mtr.size();
			        		    	int translationCounter = mtr.size();
			        		    	while (mmtr.containsKey(translationId)) {
			        		    	    translationCounter++;
			        		    	    translationId = currentNode.getId() + ">TR_" + translationCounter;
			        		    	}

			        		    	
                                ensureTranslationNodeModel();

                                ArrayList<String> dictionaryNodeIds = new ArrayList<String>();
                                HashMap<Integer,String> simpleNodeIdByIndex = new HashMap<Integer,String>();

                                for (int i = 0; i < currentEl.size(); i++) {
                                    if (dictionaryEntries[i] != null && dictionaryEntries[i].isSelected()) {
                                        String simpleNodeId = translationId + ">DE_SIMPLE_" + i;
                                        TranslationNode simpleNode = new TranslationNode(
                                                simpleNodeId,
                                                originalValues[i].getText(),
                                                semanticValues[i].getText()
                                        );
                                        simpleNode.setNodeKind("SimpleDictionaryEntry");
                                        simpleNode.setParentMgId(currentNode.getId());
                                        simpleNode.setElementLabel(originalValues[i].getText());
                                        simpleNode.setSemanticMeaning(semanticValues[i].getText());
                                        simpleNode.setSyntacticFunction(syntacticValues[i]);
                                        simpleNode.setWordNetSynsetId(wordNetValues[i]);
                                        simpleNode.setExplanation(explanationValues[i]);
                                        simpleNode.setDictionaryEntry(true);
                                        simpleNode.setLabel(
                                                "Element: " + originalValues[i].getText()
                                                + " | Meaning: " + semanticValues[i].getText()
                                                + " | Syntax: " + syntacticValues[i]
                                        );
                                        addOrReplaceTranslationNode(simpleNode);
                                        dictionaryNodeIds.add(simpleNodeId);
                                        simpleNodeIdByIndex.put(Integer.valueOf(i), simpleNodeId);
                                    }
                                }

                                for (int c = 0; c < composedEntryOriginals.size(); c++) {
                                    String composedNodeId = translationId + ">DE_COMPOSED_" + c;
                                    TranslationNode composedNode = new TranslationNode(
                                            composedNodeId,
                                            composedEntryOriginals.get(c),
                                            composedEntryTranslations.get(c)
                                    );
                                    composedNode.setNodeKind("ComposedDictionaryEntry");
                                    composedNode.setParentMgId(currentNode.getId());
                                    composedNode.setDictionaryEntry(true);
                                    composedNode.setLabel(
                                            "Maya: " + composedEntryOriginals.get(c)
                                            + " | Translation: " + composedEntryTranslations.get(c)
                                    );
                                    int[] selectedIndexes = composedEntryParts.get(c);
                                    for (int pi = 0; pi < selectedIndexes.length; pi++) {
                                        String partId = simpleNodeIdByIndex.get(Integer.valueOf(selectedIndexes[pi]));
                                        if (partId != null) {
                                            composedNode.addPartId(partId);
                                        }
                                    }
                                    addOrReplaceTranslationNode(composedNode);
                                    dictionaryNodeIds.add(composedNodeId);
                                }

                                TranslationNode finalTranslationNode = new TranslationNode(
                                        translationId,
                                        konsolidTR.getText(),
                                        finalTr.getText()
                                );
                                finalTranslationNode.setNodeKind("ConsolidatedTranslation");
                                finalTranslationNode.setParentMgId(currentNode.getId());
                                finalTranslationNode.setLabel(
                                        "Maya: " + konsolidTR.getText()
                                        + " | Translation: " + finalTr.getText()
                                );
                                for (String dictionaryNodeId : dictionaryNodeIds) {
                                    finalTranslationNode.addPartId(dictionaryNodeId);
                                }
                                addOrReplaceTranslationNode(finalTranslationNode);

FinalTranslation ftr = new FinalTranslation(
			        		    	        translationId,
			        		    	        konsolidTR.getText(),
			        		    	        finalTr.getText()
			        		    	);
			        		    	mtr.add(ftr);
			        		    	mmtr.put(ftr.getId(), Integer.valueOf(mtr.size()-1));
        		            	                       AnnotationNode obj1=new AnnotationNode(ftr),node;
					                    		        obj2.add(obj1);
					                    		        reloadBlockTree();
				                    				expandAll(blockTree,path,true);
				                    				translationFrame.doDefaultCloseAction();
		        		    	}
		        		    });
		        		    g2.fill=GridBagConstraints.HORIZONTAL;
		        		    g2.weighty=0.0;
		        		    g2.gridy=4;
		        		    content1.add(assign,g2);
		        		    translationFrame.pack();
                      		   addChild(translationFrame,650, 10, 1080, 840);
                      		   translationFrame.setVisible(true);
                      		   translationFrame.moveToFront();
		                    		  
	                    	}
	                    });
	                	final AnnotationNode objGloss = (AnnotationNode) path.getLastPathComponent();
	                	final MorphoSyntGlossing glossNode = (MorphoSyntGlossing) objGloss.getMyTreeNode();

	                	popup.add(createInterpretationConfidenceMenu(objGloss));

	                	final JMenuItem menuFreezeGloss = new JMenuItem("Freeze branch");
	                	menuFreezeGloss.addActionListener(new ActionListener() {
	                		public void actionPerformed(ActionEvent e) {
	                			glossNode.setFinish(true);
	                			changed = true;
	                			reloadBlockTree(objGloss);
	                		}
	                	});

	                	final JMenuItem menuUnfreezeGloss = new JMenuItem("Unfreeze branch");
	                	menuUnfreezeGloss.addActionListener(new ActionListener() {
	                		public void actionPerformed(ActionEvent e) {
	                			glossNode.setFinish(false);
	                			changed = true;
	                			reloadBlockTree(objGloss);
	                		}
	                	});

	                	if (glossNode.getFinish()) {
	                		popup.add(menuUnfreezeGloss);
	                	}
	                	else {
	                		final JMenuItem deleteGlossLocal = new JMenuItem("Delete local");
	                		deleteGlossLocal.addActionListener(new ActionListener() {
	                			public void actionPerformed(ActionEvent e) {
	                				javax.swing.tree.TreeNode parentNode = objGloss.getParent();
	                				boolean deleted = deleteLocalMorphoGlossing(glossNode, objGloss);

	                				if (!deleted) {
	                					JOptionPane.showMessageDialog(
	                						AllmahGUI.this,
	                						"Morphological glossing could not be removed from its parent morpho-syntactic transcription.",
	                						"Delete local",
	                						JOptionPane.WARNING_MESSAGE
	                					);
	                					return;
	                				}

	                				if (blockTree != null
	                						&& blockTree.getModel() instanceof DefaultTreeModel
	                						&& parentNode instanceof AnnotationNode) {
	                					((DefaultTreeModel) blockTree.getModel()).removeNodeFromParent(objGloss);
	                					reloadBlockTree(parentNode);
	                				}
	                				else {
	                					reloadBlockTree();
	                				}
	                			}
	                		});

	                		popup.add(addTr);
	                		popup.add(deleteGlossLocal);
	                		popup.add(menuFreezeGloss);
	                	}
		            }
		            else if(label.equals("class allmahVer4.FinalTranslation")) {
		                final AnnotationNode objTranslation = (AnnotationNode) path.getLastPathComponent();
		                final FinalTranslation finalTranslation = (FinalTranslation) objTranslation.getMyTreeNode();

		                final JMenuItem editTranslation = new JMenuItem("Edit");
		                editTranslation.addActionListener(new ActionListener() {
		                    public void actionPerformed(ActionEvent e) {
		                        openEditFinalTranslationDialog(finalTranslation, objTranslation);
		                    }
		                });

		                final JMenuItem deleteTranslationLocal = new JMenuItem("Delete local");
		                deleteTranslationLocal.addActionListener(new ActionListener() {
		                    public void actionPerformed(ActionEvent e) {
		                        javax.swing.tree.TreeNode parentNode = objTranslation.getParent();
		                        boolean deleted = deleteLocalFinalTranslation(finalTranslation);
		                        if (!deleted) {
		                            JOptionPane.showMessageDialog(
		                                    AllmahGUI.this,
		                                    "Translation could not be removed.",
		                                    "Delete local",
		                                    JOptionPane.WARNING_MESSAGE
		                            );
		                            return;
		                        }
		                        if (blockTree != null
		                                && blockTree.getModel() instanceof DefaultTreeModel
		                                && parentNode instanceof AnnotationNode) {
		                            ((DefaultTreeModel) blockTree.getModel()).removeNodeFromParent(objTranslation);
		                            reloadBlockTree(parentNode);
		                        }
		                        else {
		                            reloadBlockTree();
		                        }
		                    }
		                });

		                popup.add(editTranslation);
		                popup.add(deleteTranslationLocal);

		            }
		            else if(label.equals("class allmahVer4.MorphoSyntTranslit")) {
		            	System.out.println("Morpho Synt");
		            	 final JMenuItem editMorphoTranscr = new JMenuItem("Edit MorphoSyntaktische Transkription");
		            	 editMorphoTranscr.addActionListener(new ActionListener() {
		            		 public void actionPerformed(ActionEvent e) {
		            			 AnnotationNode obj2 = (AnnotationNode) path.getLastPathComponent();
		            			 String idcurrentNode1 = ((MorphoSyntTranslit)obj2.getMyTreeNode()).getId();
		            			 if (mmt == null || !mmt.containsKey(idcurrentNode1)) {
		            				 JOptionPane.showMessageDialog(AllmahGUI.this,
		            				         "Morpho-syntactic transcription not found in internal map.",
		            				         "Edit MorphoSyntaktische Transkription",
		            				         JOptionPane.WARNING_MESSAGE);
		            				 return;
		            			 }
		            			 MorphoSyntTranslit currentNode = mt.get(mmt.get(idcurrentNode1).intValue());
		            			 JTree editTree = createEditableMorphoSyntTree(currentNode);
		            			 if (editTree == null || editTree.getModel() == null || editTree.getModel().getRoot() == null) {
		            				 JOptionPane.showMessageDialog(AllmahGUI.this,
		            				         "No editable morpho-syntactic tree is available for this transcription.",
		            				         "Edit MorphoSyntaktische Transkription",
		            				         JOptionPane.WARNING_MESSAGE);
		            				 return;
		            			 }

		            			 ChildFrame morphoTranscrFrame = new ChildFrame("Edit MorphoSyntaktische Transkription for " + Jsoup.parse(obj2.getMyTreeNode().getNodeLabel()).text(), Color.BLUE, WindowConstants.DISPOSE_ON_CLOSE);
		            			 morphoTranscrFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		            			 Container content = morphoTranscrFrame.getContentPane();

		            			 if (editTree.getParent() != null) {
		            				 editTree.getParent().remove(editTree);
		            			 }
		            			 editTree.setCellRenderer(new MyLetterRenderer());
		            			 editTree.setDropMode(DropMode.INSERT);
		            			 editTree.setTransferHandler(new TreeTransferHandler());
		            			 editTree.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
		            			 expandTreeLetters(editTree, true);

		            			 JButton cancel = new JButton("Cancel");
		            			 cancel.addActionListener(new ActionListener() {
		            				 public void actionPerformed(ActionEvent e) {
		            					 morphoTranscrFrame.doDefaultCloseAction();
		            				 }
		            			 });

		            			 JButton assign = new JButton("Apply Changes");
		            			 assign.addActionListener(new ActionListener() {
		            				 public void actionPerformed(ActionEvent e) {
		            					 MorphoSyntTranslit updated = new MorphoSyntTranslit(currentNode.getId(), currentNode.getTyp(), editTree, currentNode.getTagInterf());
		            					 ArrayList<LetterTreeNode> nodes = ((MorphoTranscrNode)((SubElementNode) editTree.getModel().getRoot()).getMyLetterTreeNode()).listNodes();
		            					 for (int i = 0; i < nodes.size(); i++) {
		            						 if (nodes.get(i) instanceof MorphoTranscrNode) {
		            							 updated.getElem().add((MorphoTranscrNode) nodes.get(i));
		            						 }
		            					 }
		            					 updated.setNoVariants(currentNode.getNoVariants());
		            					 int idx = mmt.get(currentNode.getId()).intValue();
		            					 mt.set(idx, updated);
		            					 obj2.setUserObject(updated);
		            					 changed = true;
		            					 morphoTranscrFrame.dispose();
		            					 reloadBlockTree();
		            					 expandAll(blockTree, path, true);
		            				 }
		            			 });

		            			 content.add(new JScrollPane(editTree));
		            			 JPanel buttons = new JPanel();
		            			 buttons.add(assign);
		            			 buttons.add(cancel);
		            			 content.add(buttons, BorderLayout.SOUTH);
		            			 morphoTranscrFrame.pack();
		            			 addChild(morphoTranscrFrame, 800, 10, 800, 700);
		            			 morphoTranscrFrame.setVisible(true);
		            			 morphoTranscrFrame.moveToFront();
		            		 }
		            	 });
		            	 final  JMenuItem addMorphoGloss=new JMenuItem("Add Morpho Glossing");
		                    addMorphoGloss.addActionListener(new ActionListener() {
		                    	public void actionPerformed(ActionEvent e) {
		                    	 morphoGlossFrame= new ChildFrame("Morphological Glossing for "+ Jsoup.parse(obj.getMyTreeNode().getNodeLabel()).text(),Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
		                    		   morphoGlossFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                    		   Container content1=morphoGlossFrame.getContentPane();
		                    		   allGlosses=new ArrayList<ElemSyntaxPanel[]>();
                    		   content1.setLayout(new BorderLayout());
		                    		   JPanel content=new JPanel();
		                    		   content.setLayout(new GridBagLayout());
		                    		   blockTree.revalidate();
			                    		  blockTree.setSelectionPath(path);
			                    		
			                    		   AnnotationNode obj2 = ( AnnotationNode)path.getLastPathComponent();
			                    		  String idcurrentNode1= ((MorphoSyntTranslit)obj2.getMyTreeNode()).getId();
			                    		
			                    		  MorphoSyntTranslit currentNode=mt.get(mmt.get(idcurrentNode1).intValue());
			                    		  ArrayList<MorphoTranscrNode>  currentEl= currentNode.getTranscrElem();
			                    		  System.out.println("This Transcription has " + currentEl.size()+ " elements " );
			                    		 int nrpanels=0;
			                    		 for(int i=0;i<currentEl.size();i++) {
			                    			 if (!currentEl.get(i).isDeleted()) nrpanels=nrpanels+1;
			                    		 }
			                    		  GridBagConstraints g2=new GridBagConstraints();    		
			                    			g2.gridx=0;
			                    	    	g2.gridy=0;
			                    	    	g2.weightx=1.0;
			                    	    	g2.weighty=1.0;
			                    	    	g2.anchor=GridBagConstraints.NORTHWEST;
			                    	    	g2.fill=GridBagConstraints.HORIZONTAL;
			                    	    	g2.insets.top=5;
			                    	    	g2.insets.bottom=5;
			                    	    	g2.insets.left=5;
			                    	    	g2.insets.right=5;
			                    	    	g2.gridwidth=1;
			                    	    //	g2.gridwidth=currentEl.size()*2;
			                    	    
			                    	    	
			                    	    	JPanel semP=new JPanel(new GridLayout(1,nrpanels));
			                    	    	semP.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			                  
			                    	    	 ElemSemanticsPanel[] p =new  ElemSemanticsPanel[nrpanels];
			                    	    	 MorphoTranscrNode[] ce =new  MorphoTranscrNode[nrpanels];
			                    	    	 int j=0;
			                    		for (int i=0;i<currentEl.size();i++) {
			                    		      if(!currentEl.get(i).isDeleted()) {
			                    			   p[j] =new ElemSemanticsPanel(currentEl.get(i).calculateLabel3(), dict, getInterf(),morphoGlossFrame.getX(),morphoGlossFrame.getY());
			                    			   System.out.println(" Panel for "+currentEl.get(i).calculateLabel2());
			                    			   ce[j]=currentEl.get(i);
			                    			  semP.add(p[j].getPanel(),j);
			                    			  j=j+1;
			                    		}
			                    			 // g2.gridx=i+2;
			                    		}
			                    		
			                    		content.add(semP,g2);
			                    		g2.weighty=0.0;
			                    		g2.gridwidth=currentEl.size();
			                    		g2.gridy=1;
			                    	
			                    	 gset=new ArrayList<ArrayList<GlossingVariant>>();
			                    		JButton generateS=new JButton("Generate Semantic Variants");
			                    		generateS.addActionListener(new ActionListener() {
			                    			public void actionPerformed(ActionEvent e) {
			                    				boolean isvalid=true; 
			                    				for (int i=0;i<p.length;i++ ) {
			                    					 isvalid=isvalid && p[i].getStatus();
			                    				}
			                    				if(!isvalid){
                                                            JOptionPane.showMessageDialog(
                                                                    morphoGlossFrame,
                                                                    "Please validate all semantic panels before generating syntax variants.",
                                                                    "Generate Morpho Glossing",
                                                                    JOptionPane.WARNING_MESSAGE
                                                            );
                                                            return;
                                                        }
                                                        if(true){
			                    					//currentEl.get(i);
			                    					gset.clear();
			                    					for (int i=0;i<p.length;i++) {
			                    						ArrayList<GlossingVariant> gvar=new ArrayList<GlossingVariant>();
			                    						if(p[i].hasNoSemantics()) {
		                    								semA=new SemanticAnnotation(""," ");
		                    								GlossingVariant gnode=new GlossingVariant(ce[i],semA);
	                    							        gvar.add(gnode);	
		                    							}
			                    						else {
			                    						for (int j=0;j<p[i].getList().getModel().getSize();j++) {
			                    							
			                    							
			                    								if(!p[i].getList().getModel().getElementAt(j).equals("                             ")) {
			                    								String s=""+p[i].getList().getModel().getElementAt(j);
			                    								if(s.indexOf("-")>0) {
			                    									semA=new SemanticAnnotation(s.substring(0,s.lastIndexOf("-")),s.substring(s.lastIndexOf("-")+1));
			                    								}
			                    								else semA=new SemanticAnnotation("",s);
			                    									
			                    							GlossingVariant gnode=new GlossingVariant(ce[i],semA);
		                    							        gvar.add(gnode);	
			                    								}
			                    								
			                    						
			                    						
			                    					}
			                    						
			                    					}
			                    						gset.add(gvar);
			                    				}
			                    			//
			                    				
			                    					 msg=new ArrayList<MorphoSyntGlossing>();
			                    					int nr=0;
			                    					String id=currentNode.getId();
			                    					MorphoSyntGlossing msg1 = new MorphoSyntGlossing(id+">"+"MSG_0",getInterf());
			                    					msg.add(msg1);
			                    					for (int i=0;i<gset.size();i++) {
			                    						
			                    						if (gset.get(i).size()==1) {
			                    							for (int j=0;j<msg.size();j++) {
			                    								
			                    							msg.get(j).getElements().add(gset.get(i).get(0));
			                    							}
			                    						}
			                    						else {
			                    							ArrayList<GlossingVariant> elx=new ArrayList<GlossingVariant>();
			                    							for (int j=0;j<gset.get(i).size();j++) {
			                    								GlossingVariant nt1aux=gset.get(i).get(j);
			                    								
			                    							    elx.add(nt1aux);
			                    							}  
			                    							ArrayList<MorphoSyntGlossing> msgaux=new ArrayList<MorphoSyntGlossing>();
			                    							
			                    					        for (int k=0;k<msg.size();k++) {
			                    					       
			                    						        msg.get(k).getElements().add(elx.get(0));
			                    						        
			                    					        	
			                    					        }
			                    					        for(int l=1;l<elx.size(); l++) {
			                    					        
			                    					        	for(int k=0;k<msg.size();k++) {
			                    					        		nr=nr+1;
			                    					        		MorphoSyntGlossing nx=msg.get(k).copy();
			                    					        		nx.setId(id+">"+"MSG_"+nr);
			                    					        		nx.getElements().remove(nx.getElements().size()-1);
			                    					        		nx.getElements().add(elx.get(l));
			                    					       // 	System.out.println("Intermediate"+ nx.calculateLabel());
			                    					        		msgaux.add(nx);
			                    					        	}
			                    					        }
			                    					        
			                    					        if(!msgaux.isEmpty()) {
			                    					         for (int k=0;k<msgaux.size();k++) {
			                    					        	 msg.add(msgaux.get(k));
			                    					         }     
			                    					         msgaux.clear();
			                    					        }
			                    						}
			                    					}
			                    					for (int i=0;i<msg.size();i++) {
			                    						//ntr1.add(nt1.get(i).getId());
			                    						msg.get(i).setId(id+">"+"MSG_"+i);
			                    					System.out.println("MSG "+ msg.get(i).calculateLabel()+ " "+msg.get(i).getId());
			                    					//	interf.nt1.add(nt1.get(i));
			                    						//interf.mnt1.put(nt1.get(i).getId(),new Integer(interf.nt1.size()-1));
			                    					
			                    				
			                    					}
			                    					/*g2.weighty=1.0;
						                    		g2.gridwidth=1;
						                    		g2.gridy=3;*/
			                    					JPanel content2=new JPanel(new GridLayout(1,msg.size()));
                                                            content2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			                    					glossArray=new ElemSyntaxPanel[msg.size()];
						                    		for(int i=0;i<msg.size();i++) {
						                    			glossArray[i]=new ElemSyntaxPanel(msg.get(i),trpos, getInterf(), morphoGlossFrame);
						                    			
						                    			System.out.println("Glossing has "+msg.get(i).getElements().size()+ " elem");
						                    			g2.gridx=i;
						                    			content2.add(glossArray[i].getPanel());
						                    		}
						                    		
						                    		JScrollPane glossScrollPane = new JScrollPane(content2);
                                                            glossScrollPane.setPreferredSize(new Dimension(760, 420));
                                                            content1.add(glossScrollPane,BorderLayout.CENTER);
						                    		JButton assignS=new JButton("Assign Glossing");
						                    		content1.add(assignS,BorderLayout.SOUTH);
						                    		 nrg=-1;
						                    		
						                    		assignS.addActionListener(new ActionListener() {
						                    			public void actionPerformed(ActionEvent e) {
						                    		//	for(int i=0;i<msg.size();i++) {
						                    					for(int j=0;j<glossArray.length; j++) {
						                    						if (glossArray[j] == null) {
						                    							continue;
						                    						}
						                    						ArrayList<MorphoSyntGlossing> generatedGlossingsForPanel = null;
						                    						try {
						                    							generatedGlossingsForPanel = glossArray[j].getMorphoGlossing();
						                    						}
						                    						catch (Exception ex) {
						                    							generatedGlossingsForPanel = null;
						                    						}
						                    						if(generatedGlossingsForPanel!=null && !generatedGlossingsForPanel.isEmpty()) {
						                    						for (int k=0;k<generatedGlossingsForPanel.size();k++) {
					                    							MorphoSyntGlossing generatedGlossing =
					                    							        generatedGlossingsForPanel.get(k);
					                    							System.out.println("K = "+k + "J = "+j);
					                    							System.out.println("TO ADD "+generatedGlossing);
						                    					                      if (mg == null) {
                                                                            mg = new ArrayList<MorphoSyntGlossing>();
                                                                        }
                                                                        if (mmg == null) {
                                                                            mmg = new HashMap<String,Integer>();
                                                                        }

                                                                        mg.add(generatedGlossing);
					                    		            	                       mmg.put(generatedGlossing.getId(),new Integer(mg.size()-1));

                                                                        if (!currentNode.getMorphoGloss().contains(generatedGlossing.getId())) {
                                                                            currentNode.getMorphoGloss().add(generatedGlossing.getId());
                                                                        }

					                    		            	                       AnnotationNode obj1=new AnnotationNode(generatedGlossing),node;
					   							                    		        obj2.add(obj1);
						                    						}
						                    						}
						                    					}
						                    			//	}
						                    				
							                    		        reloadBlockTree();
						                     					expandAll(blockTree,path,true);
						                    				morphoGlossFrame.doDefaultCloseAction();
						                    				
						                    			}
						                    		});
						                    		morphoGlossFrame.revalidate();
						                    		morphoGlossFrame.repaint();
			                    			//		
			                    			}
			                    			}
			                    		});
			                    		content.add(generateS, g2);
			                    		
			                    	content1.add(content, BorderLayout.NORTH);
			                    		  morphoGlossFrame.pack();
			                     		   addChild(morphoGlossFrame,800, 10, 800, 700);		
			                     		   morphoGlossFrame.setVisible(true); 
			                     		   morphoGlossFrame.moveToFront(); 
		                    	}
		                    });
		          	final AnnotationNode objMorpho = (AnnotationNode) path.getLastPathComponent();
		          	final MorphoSyntTranslit morphoNode = (MorphoSyntTranslit) objMorpho.getMyTreeNode();

		          	popup.add(createInterpretationConfidenceMenu(objMorpho));

		          	final JMenuItem menuFreezeMorpho = new JMenuItem("Freeze branch");
		          	menuFreezeMorpho.addActionListener(new ActionListener() {
		          		public void actionPerformed(ActionEvent e) {
		          			morphoNode.setFinish(true);
		          			changed = true;
		          			reloadBlockTree(objMorpho);
		          		}
		          	});

		          	final JMenuItem menuUnfreezeMorpho = new JMenuItem("Unfreeze branch");
		          	menuUnfreezeMorpho.addActionListener(new ActionListener() {
		          		public void actionPerformed(ActionEvent e) {
		          			morphoNode.setFinish(false);
		          			changed = true;
		          			reloadBlockTree(objMorpho);
		          		}
		          	});

		          	if (morphoNode.getFinish()) {
		          		popup.add(menuUnfreezeMorpho);
		          	}
		          	else {
		          		final JMenuItem deleteMorphoLocal = new JMenuItem("Delete local");
		          		deleteMorphoLocal.addActionListener(new ActionListener() {
		          			public void actionPerformed(ActionEvent e) {
		          				javax.swing.tree.TreeNode parentNode = objMorpho.getParent();
		          				boolean deleted = deleteLocalMorphoSyntTranslit(morphoNode, objMorpho);

		          				if (!deleted) {
		          					JOptionPane.showMessageDialog(
		          						AllmahGUI.this,
		          						"Morpho-syntactic transcription could not be removed from its parent phonemic list.",
		          						"Delete local",
		          						JOptionPane.WARNING_MESSAGE
		          					);
		          					return;
		          				}

		          				if (blockTree != null
		          						&& blockTree.getModel() instanceof DefaultTreeModel
		          						&& parentNode instanceof AnnotationNode) {
		          					((DefaultTreeModel) blockTree.getModel()).removeNodeFromParent(objMorpho);
		          					reloadBlockTree(parentNode);
		          				}
		          				else {
		          					reloadBlockTree();
		          				}
		          			}
		          		});

		          		popup.add(editMorphoTranscr);
	          		popup.add(deleteMorphoLocal);
	          		popup.add(addMorphoGloss);
		          		popup.add(menuFreezeMorpho);
		          	}
		            }
		            else if(label.equals("class allmahVer4.PhonemTranslit")) {
		            	 final  JMenu menuCertain=new JMenu("Certainty image");
		                 
                         final  JMenuItem certainLevel[]=new JMenuItem[8];
	              
	                 for (int i1=0;i1<8;i1++) {
	                	 final int j=i1+1;
	                	               certainLevel[i1]=new JMenuItem(""+(i1+1));
	                	 certainLevel[i1].addActionListener(new ActionListener() {
		                    	public void actionPerformed(ActionEvent e) {
		                    		
		                    		 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
			                    		PhonemTranslit currentNode= (PhonemTranslit)obj.getMyTreeNode();
			                    		currentNode.setPathImage(SpecialSymbols.pathAuswertung+"Phon_"+j+".png");
			                    		reloadBlockTree(obj);
		                    	}
	                	 });
		                  menuCertain.add(certainLevel[i1]);  	
	                 }
	                 final  JMenuItem menuFinishState=new JMenuItem("Freeze branch");
	                    menuFinishState.addActionListener(new ActionListener() {
	                    	public void actionPerformed(ActionEvent e) {
	                    		
	                    			 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
	                    			 obj.getMyTreeNode().setFinish(true);
	                    			 reloadBlockTree(obj);
	                    			
	                    		 }
	                    	
	                    });
	                    final  JMenuItem menuUnFinishState=new JMenuItem("Unfreeze branch");
	                    menuUnFinishState.addActionListener(new ActionListener() {
	                    	public void actionPerformed(ActionEvent e) {
	                    		
	                    			 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
	                    			 System.out.println("LABEL TO UNFREEZE "+ obj.getMyTreeNode().getNodeLabel() );
	                    			 obj.getMyTreeNode().setFinish(false);
	                    			 System.out.println("UnFrozen "+ obj.getMyTreeNode().getNodeLabel() );
	                    			 reloadBlockTree(obj);
	                    			
	                    		 }
	                    	
	                    });
	                    final  JMenuItem addMorphoTranscr=new JMenuItem("Add Morpho Transcription");
	                    addMorphoTranscr.addActionListener(new ActionListener() {
	                    	public void actionPerformed(ActionEvent e) {
	                    		 ChildFrame morphoTranscrFrame= new ChildFrame("Morphological Transcription for "+ Jsoup.parse(obj.getMyTreeNode().getNodeLabel()).text(),Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
	                    		   morphoTranscrFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                    		   Container content=morphoTranscrFrame.getContentPane();
	                    		  // content.setLayout();
	                    		   JPanel left =new JPanel();
	                    		   JPanel right= new JPanel();
	                    		   blockTree.revalidate();
	                    		  blockTree.setSelectionPath(path);
	                    		
	                    		   AnnotationNode obj2 = ( AnnotationNode)path.getLastPathComponent();
	                    		  String idcurrentNode1= ((PhonemTranslit)obj2.getMyTreeNode()).getId();
	                    		  PhonemTranslit currentNode=pt.get(mpt.get(idcurrentNode1).intValue());
	                    		                                                                                                                                                                                                                                                                
	                    		   MorphoTranscrNode rootNode=new MorphoTranscrNode(currentNode);
	                    		   SubElementNode root=new SubElementNode(rootNode), node;
	                   			root.explore();
	                           
	                   			DefaultTreeModel model1=new DefaultTreeModel(root);
	                   			
	                   			letterTree=new JTree(model1);
	                   			MyLetterRenderer renderer1 =new MyLetterRenderer();
	                   			letterTree.setCellRenderer(renderer1);
	                   		//letterTree.setDragEnabled(true);
	                   		letterTree.addMouseMotionListener(new MouseMotionAdapter() {
	                            public void mouseDragged(final MouseEvent evt) {
	                                treeMouseDragged(evt);
	                            }
	                        });
	                   	 
	                   		letterTree.setDropMode(DropMode.INSERT);
	                   		letterTree.setTransferHandler(new TreeTransferHandler());
	                   		letterTree.getSelectionModel().setSelectionMode(
	                                 TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
	                   		letterTree.addMouseListener( new MouseAdapter() {
	            				private void myPopupEvent(MouseEvent e) {
	            		            int x = e.getX();
	            		            int y = e.getY();
	            		            JTree tree = (JTree)e.getSource();
	            		            TreePath path1 = letterTree.getPathForLocation(x, y);
	            		            if (path1 == null)
	            		                return;
	            		            SubElementNode selObj= (SubElementNode)path1.getLastPathComponent();
	            		           letterTree.setSelectionPath(path1);
	                               JPopupMenu popup= new JPopupMenu();
	                             //  String label= ""+selObj.getMyTreeNode().getClass();
	                    		   //if (!(label.equals("class allmahVer4.HieroglyphenBlock"))
	                               MorphoTranscrNode selNode=(MorphoTranscrNode)selObj.getMyLetterTreeNode();
	                               final JMenuItem removeElement1=new JMenuItem("Remove Element");
	                              final JMenuItem undoRemoveElement=new JMenuItem("Undo Remove Element");
	                          //     if((selNode.getLevel()==1) ||(selNode.getLevel()==2)){
	                            	   if(selNode.getLevel()!=0){
	                             	final  JMenu insertBefore=new JMenu("Insert Before");
	                            	  final JMenu insertAfter=new JMenu("Insert After");
	                            	  final JMenuItem insertOperator=new JMenuItem("Insert Operator");
	                            	  final JMenuItem insertAbstract=new JMenuItem("Insert Abstract");
	                            	  final JMenuItem insertText=new JMenuItem("Insert Text");
	                            	  final JMenuItem insertAAbstract=new JMenuItem("Insert Abstract");
	                            	  final JMenuItem insertAText=new JMenuItem("Insert Text");
	                            	  final JMenuItem deleteElement=new JMenuItem("Delete Element");
	                            	  final JMenuItem removeElement=new JMenuItem("Remove Element");
	                            	 insertBefore.add(insertAbstract);insertBefore.add(insertText);
	                             insertAfter.add(insertAAbstract);insertAfter.add(insertAText);
	                             removeElement.addActionListener(new ActionListener() {
		                        	  public void actionPerformed(ActionEvent e) {
		                        		  MorphoTranscrNode node=(MorphoTranscrNode)((SubElementNode)selObj).getMyLetterTreeNode();
		                        		  node.setDel(true);
		                        		  model1.reload(root);
	                                		 expandTreeLetters(letterTree,true);       
		                        	  }
	                             });
	                             deleteElement.addActionListener(new ActionListener() {
		                        	  public void actionPerformed(ActionEvent e) {		                        		
		                        		 
	                   				
	                   			/*	m.getChildren().clear();
	                   				for(int i=0;i<obj.getChildCount();i++) {
	                   					System.out.println("Child "+i+" "+obj.getChildAt(i).toString());
	                   					m.getChildren().add((MorphoTranscrNode) ((SubElementNode)obj.getChildAt(i)).getMyLetterTreeNode());
	                   					s=s+obj.getChildAt(i).toString();
	                   				}
	                   				System.out.println("NEW Label "+ s);
	                   				System.out.println("correspondent tree has "+((MorphoTranscrNode)obj.getMyLetterTreeNode()).getChildren().size()+" nodes");
	                   				obj.getMyLetterTreeNode().setModif(true);
	                   			//	((MorphoTranscrNode)obj.getMyLetterTreeNode()).calculateLabel();
	                   				//model1.nodeChanged(root);
	                   				//obj.getMyLetterTreeNode().setLabel(s);
	                   		model1.reload(root);
	                   		//letterTree.repaint();
	                   			
                   				 expandTreeLetters(letterTree,true);
		                        		  */
		                        		  MorphoTranscrNode node=(MorphoTranscrNode)((SubElementNode)selObj).getMyLetterTreeNode();
		                        		SubElementNode parentobj= (SubElementNode)selObj.getParent();
		                        		 MorphoTranscrNode parent=(MorphoTranscrNode)parentobj.getMyLetterTreeNode();
		                        		  parent.getChildren().remove(node);
		                        		selObj.removeFromParent();
		                        		  model1.reload(root);
	                                		 expandTreeLetters(letterTree,true);       
		                        	  }
	                             });
	                             insertOperator.addActionListener(new ActionListener() {
		                        	  public void actionPerformed(ActionEvent e) {
		                        		  JButton bMinus=new JButton("-");bMinus.setFont(typFont1Menu1);
			                            	 JButton bEgal=new JButton("=");bEgal.setFont(typFont1Menu1);
			                            	 JButton bTilde=new JButton("~");bTilde.setFont(typFont1Menu1);
			                            	 JButton bEmpty=new JButton("  ");bEmpty.setFont(typFont1Menu1);
			                            	 JButton bPlus=new JButton("+");bPlus.setFont(typFont1Menu1);
			                            	 JButton bGreater=new JButton(">");bGreater.setFont(typFont1Menu1);
			                            	 JPanel buttons=new JPanel();
			                            	 buttons.setLayout(new GridLayout(2,3));
			                            	 buttons.add(bMinus,0,0);buttons.add(bEgal,0,1);buttons.add(bTilde,0,2);
			                            	 buttons.add(bEmpty,1,0);
			                            	 buttons.add(bPlus,1,1);buttons.add(bGreater,1,2);
			                            	 final  ChildFrame opFrame= new ChildFrame("Insert Operator ", Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
				                              Container ct=opFrame.getContentPane();
				                              ct.add(buttons);		                           				                         
				                                bMinus.addActionListener(new ActionListener() {
				                                	public void actionPerformed(ActionEvent e) {		
				                                		MorphoTranscrNode parent=(MorphoTranscrNode)((SubElementNode)selObj.getParent()).getMyLetterTreeNode();
				                                		int pos=((SubElementNode)selObj.getParent()).getIndex(selObj);
				                                		MorphoTranscrNode newNode=new MorphoTranscrNode("-",parent,-1,pos+1);
				                                		newNode.setParent(parent);			                                		
				                                		SubElementNode newObj=new SubElementNode(newNode),node;	                                	
				                                		((SubElementNode)selObj.getParent()).insert(newObj, pos+1);
				                                		//add(newObj);
				                                	
				                                		model1.reload(root);
				                                		 expandTreeLetters(letterTree,true);                                		
				                                		opFrame.doDefaultCloseAction();
				                                	}
				                                	
				                                });
				                                bPlus.addActionListener(new ActionListener() {
				                                	public void actionPerformed(ActionEvent e) {		
				                                		MorphoTranscrNode parent=(MorphoTranscrNode)((SubElementNode)selObj.getParent()).getMyLetterTreeNode();
				                                		int pos=((SubElementNode)selObj.getParent()).getIndex(selObj);
				                                		MorphoTranscrNode newNode=new MorphoTranscrNode("+",parent,-1,pos+1);
				                                		newNode.setParent(parent);			                                		
				                                		SubElementNode newObj=new SubElementNode(newNode),node;	                                	
				                                		((SubElementNode)selObj.getParent()).insert(newObj,pos+1);
				                                		model1.reload(root);
				                                		 expandTreeLetters(letterTree,true);                                		
				                                		opFrame.doDefaultCloseAction();
				                                	}
				                                	
				                                });
				                                bTilde.addActionListener(new ActionListener() {
				                                	public void actionPerformed(ActionEvent e) {		
				                                		MorphoTranscrNode parent=(MorphoTranscrNode)((SubElementNode)selObj.getParent()).getMyLetterTreeNode();
				                                		int pos=((SubElementNode)selObj.getParent()).getIndex(selObj);
				                                		MorphoTranscrNode newNode=new MorphoTranscrNode("~",parent,-1,pos+1);
				                                		newNode.setParent(parent);			                                		
				                                		SubElementNode newObj=new SubElementNode(newNode),node;	                                	
				                                		((SubElementNode)selObj.getParent()).insert(newObj,pos+1);
				                                		model1.reload(root);
				                                		 expandTreeLetters(letterTree,true);                                		
				                                		opFrame.doDefaultCloseAction();
				                                	}
				                                	
				                                });
				                                bEmpty.addActionListener(new ActionListener() {
				                                	public void actionPerformed(ActionEvent e) {		
				                                		MorphoTranscrNode parent=(MorphoTranscrNode)((SubElementNode)selObj.getParent()).getMyLetterTreeNode();
				                                		int pos=((SubElementNode)selObj.getParent()).getIndex(selObj);
				                                		MorphoTranscrNode newNode=new MorphoTranscrNode(" ",parent,-1,pos+1);
				                                		newNode.setParent(parent);			                                		
				                                		SubElementNode newObj=new SubElementNode(newNode),node;	                                	
				                                		((SubElementNode)selObj.getParent()).insert(newObj,pos+1);
				                                		model1.reload(root);
				                                		 expandTreeLetters(letterTree,true);                                		
				                                		opFrame.doDefaultCloseAction();
				                                	}
				                                	
				                                });
				                                bEgal.addActionListener(new ActionListener() {
				                                	public void actionPerformed(ActionEvent e) {		
				                                		MorphoTranscrNode parent=(MorphoTranscrNode)((SubElementNode)selObj.getParent()).getMyLetterTreeNode();
				                                		int pos=((SubElementNode)selObj.getParent()).getIndex(selObj);
				                                		MorphoTranscrNode newNode=new MorphoTranscrNode("=",parent,-1,pos+1);
				                                		newNode.setParent(parent);			                                		
				                                		SubElementNode newObj=new SubElementNode(newNode),node;	                                	
				                                		((SubElementNode)selObj.getParent()).insert(newObj,pos+1);
				                                		model1.reload(root);
				                                		 expandTreeLetters(letterTree,true);                                		
				                                		opFrame.doDefaultCloseAction();
				                                	}
				                                	
				                                });
				                                bGreater.addActionListener(new ActionListener() {
				                                	public void actionPerformed(ActionEvent e) {		
				                                		MorphoTranscrNode parent=(MorphoTranscrNode)((SubElementNode)selObj.getParent()).getMyLetterTreeNode();
				                                		int pos=((SubElementNode)selObj.getParent()).getIndex(selObj);
				                                		MorphoTranscrNode newNode=new MorphoTranscrNode(">",parent,-1,pos+1);
				                                		newNode.setParent(parent);			                                		
				                                		SubElementNode newObj=new SubElementNode(newNode),node;	                                	
				                                		((SubElementNode)selObj.getParent()).insert(newObj,pos+1);
				                                		model1.reload(root);
				                                		 expandTreeLetters(letterTree,true);                                		
				                                		opFrame.doDefaultCloseAction();
				                                	}
				                                	
				                                });
				                         	opFrame.pack();
				                         	
				                    		   addChild(opFrame,morphoTranscrFrame.getX()+x+50, morphoTranscrFrame.getY()+y+50, 230, 230);		
				                    		  opFrame.setVisible(true); 
				                    		  opFrame.moveToFront(); 
		                        	  }
	                             });
	                          insertAbstract.addActionListener(new ActionListener() {
	                        	  public void actionPerformed(ActionEvent e) {
	                        		  JButton bC=new JButton("C");
		                            	 JButton bV=new JButton("V");
		                            	 JButton bH=new JButton("H");
		                            	 JButton bVoid=new JButton("\u2205");
		                            	 JButton bIns=new JButton("Insert");
		                            	 JButton bclear=new JButton("Cancel");
		                            	 JPanel buttons=new JPanel();
		                            	 abstrEl="";
		                            	 buttons.setLayout(new GridLayout(3,2));
		                            	 buttons.add(bV,2,0);buttons.add(bC,2,1);buttons.add(bH,1,0);buttons.add(bVoid,1,1);
		                            	 buttons.add(bIns,0,0);buttons.add(bclear,0,1);
		                            	JTextField text=new JTextField(20);
		                            	text.setEditable(false);
		                            	final  ChildFrame abstrBFrame= new ChildFrame("Insert Element ", Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
		                              Container ct=abstrBFrame.getContentPane();
		                                ct.add(text); ct.add(buttons,BorderLayout.SOUTH);
		                                 
		                                bC.addActionListener(new ActionListener() {
		                                	public void actionPerformed(ActionEvent e) {
		                                		abstrEl=abstrEl+"C";
		                                		text.setText(abstrEl);                  		
		                                	}
		                                	
		                                });
		                                bV.addActionListener(new ActionListener() {
		                                	public void actionPerformed(ActionEvent e) {
		                                		abstrEl=abstrEl+"V";
		                                		text.setText(abstrEl);                  		
		                                	}                         	
		                                });
		                                bH.addActionListener(new ActionListener() {
		                                	public void actionPerformed(ActionEvent e) {
		                                		abstrEl=abstrEl+"H";
		                                		text.setText(abstrEl);                  		
		                                	}
		                                	
		                                });
		                                bVoid.addActionListener(new ActionListener() {
		                                	public void actionPerformed(ActionEvent e) {
		                                		abstrEl=abstrEl+"\u2205";
		                                		text.setText(abstrEl);                  		
		                                	}
		                                	
		                                });
		                                bclear.addActionListener(new ActionListener() {
		                                	public void actionPerformed(ActionEvent e) {		                                	
		                                		text.setText("");                  		
		                                	}
		                                	
		                                });
		                                bIns.addActionListener(new ActionListener() {
		                                	public void actionPerformed(ActionEvent e) {		
		                                		MorphoTranscrNode parent=(MorphoTranscrNode)((SubElementNode)selObj.getParent()).getMyLetterTreeNode();
		                                		int pos=((SubElementNode)selObj.getParent()).getIndex(selObj);
		                                		MorphoTranscrNode newNode=new MorphoTranscrNode(abstrEl,parent,3,pos);
		                                		newNode.setAbstract(true);
		                                		newNode.setParent(parent);
		                                		
		                                		SubElementNode newObj=new SubElementNode(newNode),node;
		                                
		                                		((SubElementNode)selObj.getParent()).insert(newObj, pos);
		                                
		                                		model1.reload(root);
		                                		 expandTreeLetters(letterTree,true);                                		
		                                		abstrEl="";
		                                		dirIns="";
		                                		 abstrBFrame.doDefaultCloseAction();
		                                	}
		                                	
		                                });
		                         	abstrBFrame.pack();
		                    		   addChild(abstrBFrame,morphoTranscrFrame.getX()+x+30, morphoTranscrFrame.getY()+y+30, 230, 230);		
		                    		  abstrBFrame.setVisible(true); 
		                    		  abstrBFrame.moveToFront(); 
	                        		  
	                        	  }
	                          });
	                          insertText.addActionListener(new ActionListener() {
	                        	  public void actionPerformed(ActionEvent e) {
	                        		String  newLabel = JOptionPane.showInputDialog("New Morpho Transcr. Element ");
	                        		MorphoTranscrNode parent=(MorphoTranscrNode)((SubElementNode)selObj.getParent()).getMyLetterTreeNode();
	                        		int pos=((SubElementNode)selObj.getParent()).getIndex(selObj);
	                        		System.out.println("Position in tree "+pos);
	                        		MorphoTranscrNode newNode=new MorphoTranscrNode(newLabel,parent,4,pos);
                            		//String s,MorphoTranscrNode node,int typ,int x
                            		newNode.setLevel(4);
                            		newNode.setParent(parent);
                            		
                            		SubElementNode newObj=new SubElementNode(newNode),node;
                            
                            	
                            		((SubElementNode)selObj.getParent()).insert(newObj, pos);
                            		model1.reload(root);
                            		 expandTreeLetters(letterTree,true);      
	                        	  }
	                          });
	                          insertAText.addActionListener(new ActionListener() {
	                        	  public void actionPerformed(ActionEvent e) {
	                        		String  newLabel = JOptionPane.showInputDialog("New Morpho Transcr. Element ");
	                        		MorphoTranscrNode parent=(MorphoTranscrNode)((SubElementNode)selObj.getParent()).getMyLetterTreeNode();
	                        		int pos=((SubElementNode)selObj.getParent()).getIndex(selObj);
	                        		MorphoTranscrNode newNode=new MorphoTranscrNode(newLabel,parent,4,pos+1);
                            		newNode.setLevel(4);
                            		newNode.setParent(parent);
                            		SubElementNode newObj=new SubElementNode(newNode),node;
                            
                        	
                            		((SubElementNode)selObj.getParent()).insert(newObj, pos+1);
                            		model1.reload(root);
                            		 expandTreeLetters(letterTree,true);      
	                        	  }
	                          });
	                          
	                          insertAAbstract.addActionListener(new ActionListener() {
	                        	  public void actionPerformed(ActionEvent e) {
	                        		  JButton bC=new JButton("C");
		                            	 JButton bV=new JButton("V");
		                            	 JButton bH=new JButton("H");
		                            	 JButton bVoid=new JButton("\u2205");
		                            	 JButton bIns=new JButton("Insert");
		                            	 JButton bclear=new JButton("Cancel");
		                            	 JPanel buttons=new JPanel();
		                            	 abstrEl="";
		                            	 buttons.setLayout(new GridLayout(3,2));
		                            	 buttons.add(bV,2,0);buttons.add(bC,2,1);buttons.add(bH,1,0);buttons.add(bVoid,1,1);
		                            	 buttons.add(bIns,0,0);buttons.add(bclear,0,1);
		                            	JTextField text=new JTextField(20);
		                            	text.setEditable(false);
		                            	final  ChildFrame abstrBFrame= new ChildFrame("Insert Element ", Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
		                              Container ct=abstrBFrame.getContentPane();
		                                ct.add(text); ct.add(buttons,BorderLayout.SOUTH);
		                                 
		                                bC.addActionListener(new ActionListener() {
		                                	public void actionPerformed(ActionEvent e) {
		                                		abstrEl=abstrEl+"C";
		                                		text.setText(abstrEl);                  		
		                                	}
		                                	
		                                });
		                                bV.addActionListener(new ActionListener() {
		                                	public void actionPerformed(ActionEvent e) {
		                                		abstrEl=abstrEl+"V";
		                                		text.setText(abstrEl);                  		
		                                	}                         	
		                                });
		                                bH.addActionListener(new ActionListener() {
		                                	public void actionPerformed(ActionEvent e) {
		                                		abstrEl=abstrEl+"H";
		                                		text.setText(abstrEl);                  		
		                                	}
		                                	
		                                });
		                                bVoid.addActionListener(new ActionListener() {
		                                	public void actionPerformed(ActionEvent e) {
		                                		abstrEl=abstrEl+"\u2205";
		                                		text.setText(abstrEl);                  		
		                                	}
		                                	
		                                });
		                                bclear.addActionListener(new ActionListener() {
		                                	public void actionPerformed(ActionEvent e) {		                                	
		                                		text.setText("");                  		
		                                	}
		                                	
		                                });
		                                bIns.addActionListener(new ActionListener() {
		                                	public void actionPerformed(ActionEvent e) {		
		                                		MorphoTranscrNode parent=(MorphoTranscrNode)((SubElementNode)selObj.getParent()).getMyLetterTreeNode();
		                                		int pos=((SubElementNode)selObj.getParent()).getIndex(selObj);
		                                		MorphoTranscrNode newNode=new MorphoTranscrNode(abstrEl,parent,3,pos+1);
		                                		newNode.setParent(parent);
		                                		newNode.setAbstract(true);
		                                		SubElementNode newObj=new SubElementNode(newNode),node;
		                                
		                                	
		                                		((SubElementNode)selObj.getParent()).insert(newObj, pos+1);
		                                		model1.reload(root);
		                                		 expandTreeLetters(letterTree,true);                                		
		                                		abstrEl="";
		                                		dirIns="";
		                                		 abstrBFrame.doDefaultCloseAction();
		                                	}
		                                	
		                                });
		                         	abstrBFrame.pack();
		                    		   addChild(abstrBFrame,morphoTranscrFrame.getX()+x+30, morphoTranscrFrame.getY()+y+30, 230, 230);		
		                    		  abstrBFrame.setVisible(true); 
		                    		  abstrBFrame.moveToFront(); 
	                        		  
	                        	  }
	                          });
	                               popup.add(deleteElement);
	                               popup.add(insertBefore);
	                               if (model1.getIndexOfChild(selObj.getParent(), selObj)<selObj.getParent().getChildCount()-1) {
	                            		popup.add(insertAfter);
	                            		 if (selNode.getLevel()!=2) popup.add(insertOperator);
	                            	}
	                            	if (model1.getIndexOfChild(selObj.getParent(), selObj)==selObj.getParent().getChildCount()-1) {
	                            		popup.add(insertAfter);
	                            		
	                            	}
	                               }
	                               if (selNode.getLevel()==2) {
	                            	  
		                             removeElement1.addActionListener(new ActionListener() {
			                        	  public void actionPerformed(ActionEvent e) {
			                        		  MorphoTranscrNode node=(MorphoTranscrNode)((SubElementNode)selObj).getMyLetterTreeNode();
			                        		  node.setDel(true);
			                        		  node.setLevel(5);
			                        		  model1.reload(root);
		                                		 expandTreeLetters(letterTree,true);       
			                        	  }
		                             });
		                             MorphoTranscrNode node=(MorphoTranscrNode)((SubElementNode)selObj).getMyLetterTreeNode();
			                            if(!node.getDel()) popup.add(removeElement1);
			                            else  popup.add(undoRemoveElement);
	                               }
	                               else if (selNode.getLevel()==5) {
		                            
		                            undoRemoveElement.addActionListener(new ActionListener() {
			                        	  public void actionPerformed(ActionEvent e) {
			                        		  MorphoTranscrNode node=(MorphoTranscrNode)((SubElementNode)selObj).getMyLetterTreeNode();
			                        		  node.setDel(false); node.setLevel(2);
			                        		  model1.reload(root);
		                                		 expandTreeLetters(letterTree,true);       
			                        	  }
		                             });
		                            MorphoTranscrNode node=(MorphoTranscrNode)((SubElementNode)selObj).getMyLetterTreeNode();
		                            if(!node.getDel()) popup.add(removeElement1);
		                            else  popup.add(undoRemoveElement);
	                               }
		                           
	                               
	                               else {
	                            	   
	                               }
	                              final  JMenu insertBrackets= new JMenu("Insert Brackets");
	                              final JMenuItem overspell=new JMenuItem("Phonemic overspell { } ");  
	                              final JMenuItem reconstr=new JMenuItem("Reconstructed sound [ ] ");  
	                              final JMenuItem loss=new JMenuItem("Sound loss | | ");  
	                              overspell.addActionListener(new ActionListener() {
	                                	public void actionPerformed(ActionEvent e) {	
	                                		  MorphoTranscrNode node=(MorphoTranscrNode)((SubElementNode)selObj).getMyLetterTreeNode();
	                                		  node.setGeschw(true);
	                                		  model1.reload(root);
		                                		 expandTreeLetters(letterTree,true);  
	                                	}
	                              });
	                             reconstr.addActionListener(new ActionListener() {
	                                	public void actionPerformed(ActionEvent e) {	
	                                		  MorphoTranscrNode node=(MorphoTranscrNode)((SubElementNode)selObj).getMyLetterTreeNode();
	                                		  node.setEckig(true);
	                                		  model1.reload(root);
		                                		 expandTreeLetters(letterTree,true);  
	                                	}
	                              });
	                            loss.addActionListener(new ActionListener() {
	                                	public void actionPerformed(ActionEvent e) {	
	                                		  MorphoTranscrNode node=(MorphoTranscrNode)((SubElementNode)selObj).getMyLetterTreeNode();
	                                		  node.setBar(true);
	                                		  model1.reload(root);
		                                		 expandTreeLetters(letterTree,true);  
	                                	}
	                              });
	                            MorphoTranscrNode node=(MorphoTranscrNode)((SubElementNode)selObj).getMyLetterTreeNode();
	                              insertBrackets.add(overspell);
	                              insertBrackets.add(reconstr);
	                              insertBrackets.add(loss);
	                              final  JMenu removeBrackets= new JMenu("Remove Brackets");
	                              final JMenuItem roverspell=new JMenuItem("Phonemic overspell { } ");  
	                              final JMenuItem rreconstr=new JMenuItem("Reconstructed sound [ ] ");  
	                              final JMenuItem rloss=new JMenuItem("Sound loss | | ");  
	                              roverspell.addActionListener(new ActionListener() {
	                                	public void actionPerformed(ActionEvent e) {	
	                                		  MorphoTranscrNode node=(MorphoTranscrNode)((SubElementNode)selObj).getMyLetterTreeNode();
	                                		  node.setGeschw(false);
	                                		  model1.reload(root);
		                                		 expandTreeLetters(letterTree,true);  
	                                	}
	                              });
	                             rreconstr.addActionListener(new ActionListener() {
	                                	public void actionPerformed(ActionEvent e) {	
	                                		  MorphoTranscrNode node=(MorphoTranscrNode)((SubElementNode)selObj).getMyLetterTreeNode();
	                                		  node.setEckig(false);
	                                		  model1.reload(root);
		                                		 expandTreeLetters(letterTree,true);  
	                                	}
	                              });
	                            rloss.addActionListener(new ActionListener() {
	                                	public void actionPerformed(ActionEvent e) {	
	                                		  MorphoTranscrNode node=(MorphoTranscrNode)((SubElementNode)selObj).getMyLetterTreeNode();
	                                		  node.setBar(false);
	                                		  model1.reload(root);
		                                		 expandTreeLetters(letterTree,true);  
	                                	}
	                              });
	                              removeBrackets.add(roverspell);
	                              removeBrackets.add(rreconstr);
	                              removeBrackets.add(rloss);
	                              
	                             if (!node.getDel()) { popup.add(insertBrackets);
	                              if(node.getGeschw()||node.getEckig()||node.getBar())      
	                            	  popup.add(removeBrackets);
	                             }
	                               popup.show(letterTree, x, y);
	            		        }
	            				 public void mousePressed(MouseEvent e) {
	            			            if (e.isPopupTrigger()) myPopupEvent(e);
	            			        }
	            			        public void mouseReleased(MouseEvent e) {
	            			            if (e.isPopupTrigger()) myPopupEvent(e);
	            			        }   
	            				
	                   		});
                            
	                   	 letterTree.addMouseMotionListener(new MouseMotionAdapter() {
	                          public void mouseDragged(final MouseEvent evt) {
	                              treeMouseDragged(evt);
	                         
	                          }
	                      });
	                   
	                   		 expandTreeLetters(letterTree,true);
	                   		//	right.add(new JScrollPane(letterTree));
	                   		 model1.addTreeModelListener(new TreeModelListener() {
	                   			 public void treeNodesChanged(TreeModelEvent e) {
	                   				/*SubElementNode obj=(SubElementNode)e.getTreePath().getLastPathComponent();
	                   				model1.nodeChanged(obj);
	                   				*/
	                   			 }
	                   			public void treeNodesInserted(TreeModelEvent e) {
	                   				System.out.println("Inserted in "+ e.getTreePath().getLastPathComponent());
	                   				SubElementNode obj=(SubElementNode)e.getTreePath().getLastPathComponent();
	                   				System.out.println("this node has "+obj.toString()+obj.getChildCount());
	                   				String s="";
	                   				MorphoTranscrNode m=(MorphoTranscrNode)obj.getMyLetterTreeNode();
	                   				m.getChildren().clear();
	                   				for(int i=0;i<obj.getChildCount();i++) {
	                   					System.out.println("Child "+i+" "+obj.getChildAt(i).toString());
	                   					s=s+obj.getChildAt(i).toString();
	                   					m.getChildren().add((MorphoTranscrNode) ((SubElementNode)obj.getChildAt(i)).getMyLetterTreeNode());
	                   				}
	                   				System.out.println("NEW Label "+ s);
	                   				obj.getMyLetterTreeNode().setModif(true);
	                   			//	((MorphoTranscrNode)obj.getMyLetterTreeNode()).calculateLabel();
	                   				//model1.nodeChanged(root);
	                   			//	obj.getMyLetterTreeNode().setLabel(s);
	                   			 // model listener only synchronizes the backing MorphoTranscrNode children.
	                   			 // Explicit UI refresh is done by the action/drop that caused the change.
                   				
	                   				
	                   				 
	                   			 }
	                   			public void treeNodesRemoved(TreeModelEvent e) {
	                   				System.out.println("Removed From "+ e.getTreePath().getLastPathComponent());
	                   				SubElementNode obj=(SubElementNode)e.getTreePath().getLastPathComponent();
	                   				System.out.println("this node has "+obj.toString()+obj.getChildCount());
	                   				String s="";
	                   				MorphoTranscrNode m=(MorphoTranscrNode)obj.getMyLetterTreeNode();
	                   				m.getChildren().clear();
	                   				for(int i=0;i<obj.getChildCount();i++) {
	                   					System.out.println("Child "+i+" "+obj.getChildAt(i).toString());
	                   					m.getChildren().add((MorphoTranscrNode) ((SubElementNode)obj.getChildAt(i)).getMyLetterTreeNode());
	                   					s=s+obj.getChildAt(i).toString();
	                   				}
	                   				System.out.println("NEW Label "+ s);
	                   				System.out.println("correspondent tree has "+((MorphoTranscrNode)obj.getMyLetterTreeNode()).getChildren().size()+" nodes");
	                   				obj.getMyLetterTreeNode().setModif(true);
	                   			//	((MorphoTranscrNode)obj.getMyLetterTreeNode()).calculateLabel();
	                   				//model1.nodeChanged(root);
	                   				//obj.getMyLetterTreeNode().setLabel(s);
	                   		// model listener only synchronizes the backing MorphoTranscrNode children.
	                   		// Explicit UI refresh is done by the action/drop that caused the change.
                   				
                   				
	                   			 }
	                   			public void treeStructureChanged(TreeModelEvent e) {
	                   			
	                   			 }
	                   		 });
	                   		 JButton cancel=new JButton("Cancel");
	                   		 cancel.addActionListener(new ActionListener() {
	                   			 public void actionPerformed(ActionEvent e) {
	                   				 morphoTranscrFrame.doDefaultCloseAction();
	                   			 }
	                   		 });
	                    		   JButton assign=new JButton("Generate");
	                    		   assign.addActionListener(new ActionListener() {
	  	                   			 public void actionPerformed(ActionEvent e) {
	  	                   				 String id = nextMorphoSyntTranslitId(currentNode);
	  	                   	
	  	                   				 MorphoSyntTranslit morpho=new MorphoSyntTranslit(id,"main",letterTree, currentNode.getTagInterf());
	  	                for(int i=0;i< ((SubElementNode) letterTree.getModel().getRoot()).getMyLetterTreeNode().listNodes().size();i++) {
	  	                	morpho.getElem().add((MorphoTranscrNode)((SubElementNode) letterTree.getModel().getRoot()).getMyLetterTreeNode().listNodes().get(i));
	  	                }
	  	                   				 AnnotationNode obj1=new AnnotationNode(morpho),node;
	                    		        obj.add(obj1);
	                    		       MorphoSyntTranslit currentNode1= ( MorphoSyntTranslit)obj1.getMyTreeNode();
        
	                    	            	
	                    	            	if (!currentNode.getMorphoTranscr().contains(morpho.getId())) {
	                    	            		currentNode.getMorphoTranscr().add(morpho.getId());
	                    	            	}
	                    	            	if (mt == null) {
	                    	            		mt = new ArrayList<MorphoSyntTranslit>();
	                    	            	}
	                    	            	if (mmt == null) {
	                    	            		mmt = new HashMap<String,Integer>();
	                    	            	}
	                    	            	mt.add(morpho);
	                    	            	mmt.put(currentNode1.getId(),new Integer(mt.size()-1));
		                      				
	  	                    	 morphoTranscrFrame.dispose();
	                    		        reloadBlockTree();
                     					expandAll(blockTree,path,true);
	  	                   			 }
	  	                   		 });
	                    		//   content.add(left, BorderLayout.WEST);
	                    		   content.add(new JScrollPane(letterTree));
	                    		   JPanel buttons=new JPanel();
	                    		   buttons.add(assign); buttons.add(cancel);
	                    		   content.add(buttons, BorderLayout.SOUTH);
	                    		   morphoTranscrFrame.pack();
	                    		   addChild(morphoTranscrFrame,800, 10, 800, 700);		
	                    		   morphoTranscrFrame.setVisible(true); 
	                    		   morphoTranscrFrame.moveToFront();
	                    	}
	                    });
	                   	
	                    
	                    
			                   // popup.add(menuCertain); // removed: image certainty menu is no longer used
			                   final AnnotationNode obj1 = (AnnotationNode) path.getLastPathComponent();
			                   final PhonemTranslit phonemicNode = (PhonemTranslit) obj1.getMyTreeNode();

			                   popup.add(createInterpretationConfidenceMenu(obj1));

			                   if (!phonemicNode.getFinish()) {
			                       final JMenuItem deletePhonemicLocal = new JMenuItem("Delete local");
			                       deletePhonemicLocal.addActionListener(new ActionListener() {
			                           public void actionPerformed(ActionEvent e) {
			                               javax.swing.tree.TreeNode parentNode = obj1.getParent();
			                               boolean deleted = deleteLocalPhonemicTranslit(phonemicNode, obj1);

			                               if (!deleted) {
			                                   JOptionPane.showMessageDialog(
			                                           AllmahGUI.this,
			                                           "Phonemic transliteration could not be removed from its parent GT2 list.",
			                                           "Delete local",
			                                           JOptionPane.WARNING_MESSAGE
			                                   );
			                                   return;
			                               }

			                               if (blockTree != null
			                                       && blockTree.getModel() instanceof DefaultTreeModel
			                                       && parentNode instanceof AnnotationNode) {
			                                   ((DefaultTreeModel) blockTree.getModel()).removeNodeFromParent(obj1);
			                                   reloadBlockTree(parentNode);
			                               }
			                               else {
			                                   reloadBlockTree();
			                               }
			                           }
			                       });
			                       popup.add(deletePhonemicLocal);
			                       popup.add(addMorphoTranscr);
			                   }

			                   if (!phonemicNode.getFinish()) {
			                       popup.add(menuFinishState);
			                   }
			                   else {
			                       popup.add(menuUnFinishState);
			                   }
		            }
		            else  if (label.equals("class allmahVer4.GraphTranslit2")) {
		            	
		                  final  JMenuItem menuGr2E=new JMenuItem("Edit Graphical Translit 2");
		                  final  JMenuItem menuGr2V=new JMenuItem("Generate Graphical Translit 2 Variant");
		                  final  JMenuItem menuPhonem=new JMenuItem("Generate Phonemic Transliteration");
		                  final  JMenuItem menuGr2D=new JMenuItem("Delete local");
		                  final  JMenu menuCertain=new JMenu("Certainty image");
                          final JMenuItem menuFreezeGT2 = new JMenuItem("Freeze branch here");
                          final JMenuItem menuUnfreezeGT2 = new JMenuItem("Unfreeze branch");
			                 
                          final  JMenuItem certainLevel[]=new JMenuItem[8];
	              
	                 for (int i1=0;i1<8;i1++) {
	                	 final int j=i1+1;
	                	               certainLevel[i1]=new JMenuItem(""+(i1+1));
	                	 certainLevel[i1].addActionListener(new ActionListener() {
		                    	public void actionPerformed(ActionEvent e) {
		                    		
		                    		 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
			                    		GraphTranslit2 currentNode= (GraphTranslit2)obj.getMyTreeNode();
			                    		currentNode.setPathImage(SpecialSymbols.pathAuswertung+"GT2_"+j+".png");
			                    		reloadBlockTree(obj);
		                    	}
	                	 });
		                  menuCertain.add(certainLevel[i1]);  	
	                 }
	                 
	                  menuFreezeGT2.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        AnnotationNode obj = (AnnotationNode) path.getLastPathComponent();
	                        obj.getMyTreeNode().setFinish(true);
	                        reloadBlockTree(obj);
	                    }
	                  });
	                  menuUnfreezeGT2.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        AnnotationNode obj = (AnnotationNode) path.getLastPathComponent();
	                        obj.getMyTreeNode().setFinish(false);
	                        reloadBlockTree(obj);
	                    }
	                  });

	                  menuGr2D.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        AnnotationNode obj = (AnnotationNode) path.getLastPathComponent();
	                        GraphTranslit2 currentNode = (GraphTranslit2) obj.getMyTreeNode();
	                        javax.swing.tree.TreeNode parentNode = obj.getParent();
	                        boolean deleted = deleteLocalGT2(currentNode, obj);
	
	                        if (!deleted) {
	                            JOptionPane.showMessageDialog(
	                                    AllmahGUI.this,
	                                    "GT2 could not be removed from its parent GT1 list.",
	                                    "Delete local",
	                                    JOptionPane.WARNING_MESSAGE
	                            );
	                            return;
	                        }
	
	                        /*
	                         * The model list is now changed. Remove the already
	                         * visible AnnotationNode as well, so the GUI mirrors
	                         * exactly what Save to TTL / GraphDB will traverse.
	                         */
	                        if (blockTree != null
	                                && blockTree.getModel() instanceof DefaultTreeModel
	                                && parentNode instanceof AnnotationNode) {
	                            ((DefaultTreeModel) blockTree.getModel()).removeNodeFromParent(obj);
	                            reloadBlockTree(parentNode);
	                        }
	                        else {
	                            reloadBlockTree();
	                        }
	                    }
	                  });
	                   // popup.add(menuCertain); // removed: image certainty menu is no longer used
		                  
		                  
		                  
		                  menuPhonem.addActionListener(new ActionListener() {
		                    	public void actionPerformed(ActionEvent e) {
		                    		tree.setSelectionPath(path);
		 				           AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
		                    	//	AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
		                    		GraphTranslit2 currentNode= (GraphTranslit2)obj.getMyTreeNode();
		                    		System.out.println ("Phonem for GT2 : "+Jsoup.parse(currentNode.getNodeLabel()).text());
		                    		PhonemTranslit phonemTRNode=new PhonemTranslit(nextPhonemicTranslitId(currentNode),"main",currentNode.getTagInterf());
		                    		phonemTRNode.calculateGraph();
		                    		System.out.print("Graph vertices "+ phonemTRNode.getGraphPhonem().getVertices().size());
		                               MyVisualizationViewer<PhonemNode,OperatorLink>	vv=getPhonemViewer(phonemTRNode.getGraphPhonem(),phonemTRNode,obj);
		                               final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
		                               ChildFrame phonFrame= new ChildFrame("Edit Phonemic Transliteration for "+ Jsoup.parse(obj.getMyTreeNode().getNodeLabel()).text(),Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
		                    		   phonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                    		   MyDefaultModalGraphMouse graphMouse = new MyDefaultModalGraphMouse();
				                    	        Container content =phonFrame. getContentPane();
				                    		   content.add(panel); 
				                    		   final ScalingControl scaler = new CrossoverScalingControl();
				                    		     JButton plus = new JButton("+");
				                    		        plus.addActionListener(new ActionListener() {
				                    		            public void actionPerformed(ActionEvent e) {
				                    		                scaler.scale(vv, 1.1f, vv.getCenter());
				                    		            }
				                    		        });
				                    		        JButton minus = new JButton("-");
				                    		        minus.addActionListener(new ActionListener() {
				                    		            public void actionPerformed(ActionEvent e) {
				                    		                scaler.scale(vv, 1/1.1f, vv.getCenter());
				                    		            }
				                    		        });
				                    		        JButton save = new JButton("Save Phonemic Transliteration ");
				                    		        save.addActionListener(new ActionListener() {
				                    		            public void actionPerformed(ActionEvent e) {
           	
				                    		            	 
					                    		              AnnotationNode obj1=new AnnotationNode(phonemTRNode),node;
					                    		        obj.add(obj1);
					                    		        reloadBlockTree();
				                      					expandAll(blockTree,path,true);
					                    		       PhonemTranslit currentNode1= (PhonemTranslit)obj1.getMyTreeNode();
			                    		             
						                      				
						                      			//		 System.out.println("FIRST Phonem "+first.getLabel());
						                      				/*	phonemTRNode.setGraph(currentNode1.getGraphPhonem());
					                    		            	
					                    		            	 PhonemNode first=firstPhonemNode(phonemTRNode.getGraphPhonem());
					                    		            	 System.out.println("First phon "+first.getLabel());
			                    		            ArrayList<PhonemNode> newNodes=new ArrayList<PhonemNode>();
			                    		              PhonemNode current=first;
			                    		              while (!phonemTRNode.getGraphPhonem().getOutEdges(current).isEmpty()){
			                    		            	 newNodes.add(current);
			                    		            	 if(!phonemTRNode.getGraphPhonem().getOutEdges(current).isEmpty()) {
			                    		            	  OperatorLink o=(OperatorLink)phonemTRNode.getGraphPhonem().getOutEdges(current).toArray()[0];
			                    		            	  current=phonemTRNode.getGraphPhonem().getDest(o);
			                    		            	  System.out.println("Next phon "+current.getLabel());
			                    		            	 }
			                    		              }
			                    		              newNodes.add(current);*/
			                    		              /*for (int i=0;i<newNodes.size();i++) {
			                    		            	  phonemTRNode.getElements().get(i).setLabel(newNodes.get(i).getLabel());
			                    		              }*/
			                    		           //   phonemTRNode.setElements(newNodes);
					                    		            	
					                    		            	currentNode.getPhonemicId().add(phonemTRNode.getId());
					                    		            	pt.add(phonemTRNode);
					                    		            	mpt.put(currentNode1.getId(),new Integer(pt.size()-1));
						                      					phonFrame.doDefaultCloseAction();
						                      					
				                    		            }
				                    		        });
				                    		        graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
				                    	
				                    		        //MyPickingGraphMousePlugin<PhonemNode, OperatorLink> plug=new MyPickingGraphMousePlugin<PhonemNode, OperatorLink> ();     
				                    		   //     plug.setLensColor(Color.RED);
				                    		      //  graphMouse.add(plug);
				                    		
				                    		           vv.setGraphMouse(graphMouse); 
				                    		           JComboBox<String> modeBox =new JComboBox();
				                    		           modeBox.addItem("TRANSFORMING");
				                    		          // modeBox.addItem("EDITING");
				                    		           modeBox.addItem("PICKING");
				                    		           //modeBox.addItem("ANNOTATING");
				                    		           modeBox.setSelectedItem("PICKING");
				                    		         modeBox.addItemListener(new ItemListener() {
				                    		        	 public void itemStateChanged(ItemEvent e1) {
				                    		        		 String getItem=(String)modeBox.getSelectedItem();
				                    		        		 if(getItem.equals("TRANSFORMING")) {
				                    		        			 graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
				                    		        			// graphMouse.remove( labelEditingPlugin)  ;
				                    		        		 }
				                    		        		// else if(getItem.equals("EDITING"))  graphMouse.setMode(ModalGraphMouse.Mode.EDITING);
				                    		        		 else if(getItem.equals("PICKING"))  {
				                    		        			 graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
				                    		        			   // graphMouse.add( labelEditingPlugin)  ;
				                    		        			
				                    		        		 }
				                    		        		// else if(getItem.equals("ANNOTATING"))  graphMouse.setMode(ModalGraphMouse.Mode.ANNOTATING);
				                    		        		
				                    		        	 }
				                    		         });
				                    		         vv.addGraphEdgeMouseListener(new GraphEdgeMouseListener<OperatorLink>() {
					                    		        	
				                    		 	            @Override
				                    		 	            public void graphClicked(OperatorLink v, MouseEvent me) {
				                    		 	            	System.out.println("Clicked "+ v.getClass());
				                    		 	            	String clicked=""+v.getClass();
				                    		 	            	if(clicked.equals("class allmahVer4.OperatorLink")) {
				                    		 	                     if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() ==2) {
				                    		 	                    	if(clicked.equals("class allmahVer4.OperatorLink")){ 
				                    		 	                    	//OperatorLink o=(OperatorLink) v;
				                    		 	                  
				                    		 	                PickedState<OperatorLink> st= vv.getPickedEdgeState();
				                    		 	                if( st.isPicked(v)){
				                    		 	                     PickableEdgePaintTransformer p=new PickableEdgePaintTransformer( st, Color.BLACK, Color.RED);
				                    		 	                   p.apply((OperatorLink)v);
				                    		 	                }
				                    		 	                   JPopupMenu popup = new JPopupMenu();
				                    		 	                         final JMenuItem emptyEdge=new JMenuItem("Empty Operator");
				                    		 	                         popup.add(emptyEdge);
				                    		 	                        final JMenuItem minusEdge=new JMenuItem("-  Operator");
				                    		 	                         popup.add(minusEdge);
				                    		 	                        final JMenuItem equalEdge=new JMenuItem("=  Operator");
				                    		 	                         popup.add(equalEdge);
				                    		 	                        final JMenuItem plusEdge=new JMenuItem("+  Operator");
				                    		 	                         popup.add(plusEdge);
				                    		 	                        final JMenuItem superscriptEdge=new JMenuItem("Superscript  Operator");
				                    		 	                         popup.add(superscriptEdge);
				                    		 	                           popup.show(vv, me.getX(), me.getY());
				                    		 	                        
				                    		 	                             emptyEdge.addActionListener(new ActionListener() {
				                    		 	                            		 
				                    		 	                            		 public void actionPerformed(ActionEvent e) {
				                    		 	                            			( (OperatorLink)v).setLabel(" ");vv.repaint();
				                    		 	                            		 }
				                    		 	                            		 
				                    		 	                             });
				                    		 	                               
				                    		 	                            minusEdge.addActionListener(new ActionListener() {
			                    		 	                            		 
			                    		 	                            		 public void actionPerformed(ActionEvent e) {
			                    		 	                            			( (OperatorLink)v).setLabel("-");vv.repaint();
			                    		 	                            		 }
			                    		 	                            		 
			                    		 	                             });
				                    		 	                           equalEdge.addActionListener(new ActionListener() {
			                    		 	                            		 
			                    		 	                            		 public void actionPerformed(ActionEvent e) {
			                    		 	                            			( (OperatorLink)v).setLabel("=");vv.repaint();
			                    		 	                            		 }
			                    		 	                            		 
			                    		 	                             });
				                    		 	                          plusEdge.addActionListener(new ActionListener() {
			                    		 	                            		 
			                    		 	                            		 public void actionPerformed(ActionEvent e) {
			                    		 	                            			( (OperatorLink)v).setLabel("+");vv.repaint();
			                    		 	                            		 }
			                    		 	                            		 
			                    		 	                             });
				                    		 	                          superscriptEdge.addActionListener(new ActionListener() {
			                    		 	                            		 
			                    		 	                            		 public void actionPerformed(ActionEvent e) {
			                    		 	                            			( (OperatorLink)v).setLabel("↑");vv.repaint();
			                    		 	                            		 }
			                    		 	                            		 
			                    		 	                             });
				                    		 	                    	
				                    		 	                    	
				                    		 	                    	}
				                    		 	                    
				                    		 	                } 
			                    		 	                     
				                    		 	            	}
				                    		 	            
				                    		 	                me.consume();
				                    		 	            }

				                    		 	            @Override
				                    		 	            public void graphPressed(OperatorLink v, MouseEvent me) {
				                    		 	            }

				                    		 	            @Override
				                    		 	            public void graphReleased(OperatorLink v, MouseEvent me) {
				                    		 	            }
				                    		 	        });
				                    		         vv.addGraphMouseListener(new GraphMouseListener() {

				                    		 	            @Override
				                    		 	            public void graphClicked(Object v, MouseEvent me) {
				                    		 	            	System.out.println("Clicked "+ v.getClass());
				                    		 	            	String clicked=""+v.getClass();
				                    		 	            	if(clicked.equals("class allmahVer4.PhonemNode")) {
				                    		 	                     if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() ==2) {
				                    		 	                    	 
				                    		 	                    	PhonemNode phon=((PhonemNode) v);
				                    		 	                    	String newLabel =phon.getLabel();
				                    		 	    					newLabel = JOptionPane.showInputDialog("New Vertex Label for "+Jsoup.parse(phon.getLabel()).text());
				                    		 	    					if(newLabel != null) {
				                    		 	    						
				                    		 	    						phon.setLabel(newLabel); vv.repaint();
				                    		 	    						/*
				                    		 	    						 */
				                    		 	    						
				                    		 	    					}
				                    		 	    						//vv.repaint();
				                    		 	                    	 
				                    		 	                    
				                    		 	                } 
				                    		 	                    if (me.getButton() == MouseEvent.BUTTON1 && me.isShiftDown()) {
				                    		 	                    	if(clicked.equals("class allmahVer4.PhonemNode")) {
				                    		 	                    		PhonemNode phon=((PhonemNode) v);
				                    		 	                    		JPopupMenu popup=new JPopupMenu();
				                    		 	                    		 final JMenuItem superscript1Vertex=new JMenuItem("\u00B2");
				                    		 	                    		 final JMenuItem superscript2Vertex=new JMenuItem("\u00D0");
					                    		 	                         popup.add(superscript1Vertex);
					                    		 	                        popup.add(superscript2Vertex);
					                    		 	                           popup.show(vv, me.getX(), me.getY());
					                    		 	                        
					                    		 	                             superscript1Vertex.addActionListener(new ActionListener() {
					                    		 	                            		 
					                    		 	                            		 public void actionPerformed(ActionEvent e) {
					                    		 	                            			PhonemNode phon=((PhonemNode) v);
					                    		 	                            			phon.setLabel("\u00B2");vv.repaint();
					                    		 	                            		 }
					                    		 	                            		 
					                    		 	                             });
					                    		 	                            superscript2Vertex.addActionListener(new ActionListener() {
				                    		 	                            		 
				                    		 	                            		 public void actionPerformed(ActionEvent e) {
				                    		 	                            			PhonemNode phon=((PhonemNode) v);
				                    		 	                            			phon.setLabel("\u00D0");vv.repaint();
				                    		 	                            		 }
				                    		 	                            		 
				                    		 	                             });
				                    		 	                    		
				                    		 	                    	}
				                    		 	                    	
				                    		 	                    }
				                    		 	            	}
				                    		 	                me.consume();
				                    		 	            }

				                    		 	            @Override
				                    		 	            public void graphPressed(Object v, MouseEvent me) {
				                    		 	            }

				                    		 	            @Override
				                    		 	            public void graphReleased(Object v, MouseEvent me) {
				                    		 	            }
				                    		 	        });
				                    		         JPanel scaleGrid = new JPanel(new GridLayout(1,0));
					                    		        scaleGrid.setBorder(BorderFactory.createTitledBorder("Control"));

					                    		        JPanel controls = new JPanel();
					                    		        scaleGrid.add(plus);
					                    		        scaleGrid.add(minus);
					                    		       scaleGrid.add(modeBox);
					                    		        scaleGrid.add(save);				                    		        
					                    		        controls.add(scaleGrid);
					                    		        
					                    		       content.add(controls, BorderLayout.SOUTH);
					                    		     
					                    		 phonFrame.pack();
					                    		   addChild(phonFrame,800, 10, 800, 700);		
					                    		   phonFrame.setVisible(true); 
					                    		  phonFrame.moveToFront();     
		                    	}
		                  });
		                  menuGr2E.addActionListener(new ActionListener() {
		                    	public void actionPerformed(ActionEvent e) {
		                    		tree.setSelectionPath(path);
		 				           AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
		                    		//AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
		                    		GraphTranslit2 currentNode= (GraphTranslit2)obj.getMyTreeNode();
		                    		GraphTranslit2 currentNodeCopy=currentNode.copy("main");
		                    		  HashMap<Integer,String> componLabelCopy=new HashMap<Integer,String>();
		                    		 changed=false;
	                    		        for (int i=0;i<currentNode.getElements().size();i++) {
	                    		        	System.out.println("Current Node "+ currentNode.getElements().get(i) +" has Component "+ currentNode.getElements().get(i).getGT2Element().getParentGT1().getParent().getComponentElement()) ;
	                    		        	if (currentNode.getElements().get(i).getGT2Element().getParentGT1().getParent().getComponentElement()!=null)
	                    		        		componLabelCopy.put(new Integer(i),currentNode.getElements().get(i).getGT2Element().getParentGT1().getParent().getComponentElement().getLabel1());
	                    		        }
		                    		MyVisualizationViewer<GraphGT2Node,OperatorLink>	vv=getGT2Viewer(currentNodeCopy.getGraphGT2(),currentNodeCopy,obj);
		                    		ChildFrame gt2Frame= new ChildFrame("Edit Graphical Transliteration 2 "+ Jsoup.parse(obj.getMyTreeNode().getNodeLabel()).text(),Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
		                    		
		                    		   gt2Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                   			gt2Frame.addInternalFrameListener(new InternalFrameListener() {
		            					public void internalFrameActivated(InternalFrameEvent event) {
		            					 }
		            					public void internalFrameClosed(InternalFrameEvent event) {
		            					//	gt2Frame.doDefaultCloseAction();
		            						if(!changed) {
		            						for (Integer key   : componLabelCopy.keySet()) {
		            							System.out.println("KEY "+ key);
		            						    String value =componLabelCopy.get(key);  //get() is less efficient 
		            						    currentNode.getElements().get(key.intValue()).getGT2Element().getParentGT1().getParent().getComponentElement().setLabel1(value);
		            						}       
		            						
		            						 reloadBlockTree();
		                      					expandAll(blockTree,path,true);
		            						}
		            					}
		            					public void internalFrameOpened(InternalFrameEvent event) {
		            						
		            					}
		            					public void internalFrameClosing(InternalFrameEvent event) {
		            					
		            						
		            					}
		            					public void internalFrameDeiconified(InternalFrameEvent event) {
		            					}
		            					public void internalFrameDeactivated(InternalFrameEvent event) {
		            						

		            					}
		            					public void internalFrameIconified(InternalFrameEvent event) {
		            						
		            					}
		            					});
		                    		  
		                    	        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
		                    	     
		                    	        Supplier<GraphGT2Node> vertexFactory = new VertexFactory();
		                    	        Supplier<OperatorLink> edgeFactory = new EdgeFactory();
		                    	        DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();
		                    	  //  final EditingModalGraphMouse<GraphGT2Node,OperatorLink> graphMouse = new EditingModalGraphMouse<GraphGT2Node,OperatorLink>(vv.getRenderContext(), vertexFactory, edgeFactory);
		                    	 //   LabelEditingGraphMousePlugin<GraphGT2Node,OperatorLink> labelEditingPlugin =new LabelEditingGraphMousePlugin<GraphGT2Node,OperatorLink>();;
		                    	  //   graphMouse.add( labelEditingPlugin)       ;
		                    	        Container content =gt2Frame. getContentPane();
		                    		   content.add(panel); 
		                    		   final ScalingControl scaler = new CrossoverScalingControl();
		                    		//  scaler.scale(vv, 1.1f, vv.getCenter()); 
		                    		     JButton plus = new JButton("+");
		                    		        plus.addActionListener(new ActionListener() {
		                    		            public void actionPerformed(ActionEvent e) {
		                    		                scaler.scale(vv, 1.1f, vv.getCenter());
		                    		            }
		                    		        });
		                    		        JButton minus = new JButton("-");
		                    		        minus.addActionListener(new ActionListener() {
		                    		            public void actionPerformed(ActionEvent e) {
		                    		                scaler.scale(vv, 1/1.1f, vv.getCenter());
		                    		            }
		                    		        });
		                    		        JButton save = new JButton("Save Graphical Transliteration 2");
		                    		        save.addActionListener(new ActionListener() {
		                    		            public void actionPerformed(ActionEvent e) {
		                    		              commitGT2EditorCopyToExistingNode(currentNode, currentNodeCopy);
	                    	              gt2.set(mgt2.get(currentNode.getId()).intValue(), currentNode);
	                    	              synchronizeEditedGT2ToSameBlockInAllReadings(currentNode);
	                    	              AnnotationNode prevobj=(AnnotationNode) obj.getParent();
	                    	              AnnotationNode obj1=new AnnotationNode(currentNode),node1;
	                    	              prevobj.remove(obj);
		                    		              prevobj.add(obj1);
		                    		          
		                    		              reloadBlockTree();
			                      					expandAll(blockTree,path,true);
			                      					changed=true;
			                      					gt2Frame.doDefaultCloseAction();
		                    		            }
		                    		        });
		                    		        //DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		                    		        
		                    		        graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
		                    		           vv.setGraphMouse(graphMouse); 
		                    		           JComboBox<String> modeBox =new JComboBox();
		                    		           modeBox.addItem("TRANSFORMING");
		                    		          // modeBox.addItem("EDITING");
		                    		           modeBox.addItem("PICKING");
		                    		           //modeBox.addItem("ANNOTATING");
		                    		           modeBox.setSelectedItem("PICKING");
		                    		         modeBox.addItemListener(new ItemListener() {
		                    		        	 public void itemStateChanged(ItemEvent e1) {
		                    		        		 String getItem=(String)modeBox.getSelectedItem();
		                    		        		 if(getItem.equals("TRANSFORMING")) {
		                    		        			 graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		                    		        			// graphMouse.remove( labelEditingPlugin)  ;
		                    		        		 }
		                    		        		// else if(getItem.equals("EDITING"))  graphMouse.setMode(ModalGraphMouse.Mode.EDITING);
		                    		        		 else if(getItem.equals("PICKING"))  {
		                    		        			 graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
		                    		        			   // graphMouse.add( labelEditingPlugin)  ;
		                    		        		 }
		                    		        		// else if(getItem.equals("ANNOTATING"))  graphMouse.setMode(ModalGraphMouse.Mode.ANNOTATING);
		                    		        		
		                    		        	 }
		                    		         });
		                    		         //
		                    		         vv.addGraphEdgeMouseListener(new GraphEdgeMouseListener<OperatorLink>() {
		                    		        	

			                    		 	            @Override
			                    		 	            public void graphClicked(OperatorLink v, MouseEvent me) {
			                    		 	            	System.out.println("Clicked "+ v.getClass());
			                    		 	            	String clicked=""+v.getClass();
			                    		 	            	if(clicked.equals("class allmahVer4.OperatorLink")) {
			                    		 	                     if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() ==2) {
			                    		 	                    	if(clicked.equals("class allmahVer4.OperatorLink")){ 
			                    		 	                    	OperatorLink o=(OperatorLink) v;
			                    		 	                    	String newLabel =o.getLabel();
			                    		 	    					newLabel = JOptionPane.showInputDialog("Change Operator "+o.getLabel());
			                    		 	    					if(newLabel != null) {
			                    		 	    						if(newLabel.equals("-"))
			                    		 	    						o.setLabel(newLabel);
			                    		 	    						else o.setLabel(" ");
			                    		 	    					}
			                    		 	    						vv.repaint();
			                    		 	                    	   }
			                    		 	                    
			                    		 	                } 
			                    		 	                    if (me.getButton() == MouseEvent.BUTTON1 && me.isShiftDown() ) {
			                    		 	                    	System.out.println("Shift Down coordinates "+ me.getX()+" "+ me.getY());
			                    		 	                    	if(clicked.equals("class allmahVer4.OperatorLink")){
			                    		 	                    	  final PickedState<OperatorLink> pickedEdgeState = vv.getPickedEdgeState();			                    		 	                         
			                    		 	                         JPopupMenu popup = new JPopupMenu();
			                    		 	                         final JMenuItem delEdge=new JMenuItem("Delete Edge");
			                    		 	                         popup.add(delEdge);
			                    		 	                       
			                    		 	                           popup.show(vv, me.getX(), me.getY());
			                    		 	                         delEdge.addActionListener(new ActionListener() {
			                    		 	                           public void actionPerformed(ActionEvent e) {
			                    		 	                               pickedEdgeState.pick(v, false);
			                    		 	                              currentNodeCopy.getGraphGT2().removeEdge(v);
			                    		 	                               vv.repaint();
			                    		 	                           }
			                    		 	                           });
			                    		 	                     }
			                    		 	                     }
			                    		 	                     
			                    		 	            	}
			                    		 	            
			                    		 	                me.consume();
			                    		 	            }

			                    		 	            @Override
			                    		 	            public void graphPressed(OperatorLink v, MouseEvent me) {
			                    		 	            }

			                    		 	            @Override
			                    		 	            public void graphReleased(OperatorLink v, MouseEvent me) {
			                    		 	            }
			                    		 	        });
			                    		         
		                    		          ArrayList<GraphGT2Node> selNodes=new ArrayList<GraphGT2Node>();
		                    		      
		                    		         vv.addGraphMouseListener(new GraphMouseListener() {

		                    		 	            @Override
		                    		 	            public void graphClicked(Object v, MouseEvent me) {
		                    		 	            	System.out.println("Clicked "+ v.getClass());
		                    		 	            	String clicked=""+v.getClass();
		                    		 	            	if(clicked.equals("class allmahVer4.GraphGT2Node")) {
		                    		 	                     if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() ==2) {
		                    		 	                    	if(clicked.equals("class allmahVer4.GraphGT2Node")){ 
		                    		 	                    	GT2Element gt2=((GraphGT2Node) v).getGT2Element();
		                    		 	                    	String newLabel =gt2.getLabel();
		                    		 	    					newLabel = JOptionPane.showInputDialog("New Vertex Label for "+Jsoup.parse(gt2.getLabel()).text());
		                    		 	    					if(newLabel != null) {
		                    		 	    						if (gt2.getParentGT1().getParent().getComponentElement()==null)
		                    		 	    						gt2.setLabel(newLabel);
		                    		 	    						else
		                    		 	    							gt2.getParentGT1().getParent().getComponentElement().setLabel1(newLabel);
		                    		 	    					}
		                    		 	    						vv.repaint();
		                    		 	                    	   }
		                    		 	                    
		                    		 	                } 
		                    		 	                    if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() ==1){
		                    		 	                    	if(selNodes.size()>2) selNodes.clear();
		                    		 	                    	else selNodes.add((GraphGT2Node) v);
		                    		 	                    	  JPopupMenu popup = new JPopupMenu();
		                    		 	                    	  
			                    		 	                      
			                    		 	                        if (selNodes.size()==2) {
			                    		 	                        	final JMenuItem minusEdge=new JMenuItem("Add - Operator between "+selNodes.get(0)+" and "+selNodes.get(1));
			                    		 	                        	final JMenuItem emptyEdge=new JMenuItem("Add Empty Operator between "+selNodes.get(0)+" and "+selNodes.get(1));
			                    		 	                        	final JMenuItem clearSel=new JMenuItem("Clear Selection");
			                    		 	                        	popup.add(minusEdge);
			                    		 	                        popup.add(emptyEdge);
			                    		 	                       popup.add(clearSel);
			                    		 	                           popup.show(vv, me.getX(), me.getY());
			                    		 	                       
			                    		 	                       minusEdge.addActionListener(new ActionListener() {
			                    		 	                           public void actionPerformed(ActionEvent e) {
			                    		 	                           
			                    		 	                              currentNodeCopy.getGraphGT2().addEdge(new OperatorLink("-",5),selNodes.get(0),selNodes.get(1));
			                    		 	                              selNodes.clear();
			                    		 	                               vv.repaint();
			                    		 	                           }
			                    		 	                         });
			                    		 	                      clearSel.addActionListener(new ActionListener() {
			                    		 	                           public void actionPerformed(ActionEvent e) {
			                    		 	                           
			                    		 	                            
			                    		 	                              selNodes.clear();
			                    		 	                             
			                    		 	                           }
			                    		 	                         });
			                    		 	                      emptyEdge.addActionListener(new ActionListener() {
			                    		 	                           public void actionPerformed(ActionEvent e) {
			                    		 	                           
			                    		 	                              currentNodeCopy.getGraphGT2().addEdge(new OperatorLink(" ",5),selNodes.get(0),selNodes.get(1));
			                    		 	                              selNodes.clear();
			                    		 	                               vv.repaint();
			                    		 	                           }
			                    		 	                         });
		                    		 	                    }
		                    		 	                     }
		                    		 	            	}
		                    		 	                me.consume();
		                    		 	            }

		                    		 	            @Override
		                    		 	            public void graphPressed(Object v, MouseEvent me) {
		                    		 	            }

		                    		 	            @Override
		                    		 	            public void graphReleased(Object v, MouseEvent me) {
		                    		 	            }
		                    		 	        });
		                    		         
		                    		         //
		                    		     
		                    		     
		                    		        JPanel scaleGrid = new JPanel(new GridLayout(1,0));
		                    		        scaleGrid.setBorder(BorderFactory.createTitledBorder("Control"));

		                    		        JPanel controls = new JPanel();
		                    		        scaleGrid.add(plus);
		                    		        scaleGrid.add(minus);
		                    		        scaleGrid.add(save);
		                    		        controls.add(scaleGrid);
		                    		        controls.add(modeBox);
		                    		       content.add(controls, BorderLayout.SOUTH);
		                    		     
		                    		 gt2Frame.pack();
		                    		   addChild(gt2Frame,800, 10, 1200, 600);		
		                    		   gt2Frame.setVisible(true); 
		                    		  gt2Frame.moveToFront();
		                    		
		                    	}
		                  });
		                  
		                  //Insert Graphical Transliteration 2 Variant
		                  menuGr2V.addActionListener(new ActionListener() {
		                    	public void actionPerformed(ActionEvent e) {
		                    		tree.setSelectionPath(path);
		 				           AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
		                    	//	AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
		                    		GraphTranslit2 currentNode= (GraphTranslit2)obj.getMyTreeNode();
		                    		GraphTranslit2 currentNodeCopy=currentNode.copy("var");
		                    		 
		                    		MyVisualizationViewer<GraphGT2Node,OperatorLink>	vv=getGT2Viewer(currentNodeCopy.getGraphGT2(),currentNodeCopy,obj);
		                    		ChildFrame gt2Frame= new ChildFrame("Generate  Graphical Transliteration 2 "+ Jsoup.parse(obj.getMyTreeNode().getNodeLabel()).text(),Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
		                    		
		                    		   gt2Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                    		  
		                    	        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
		                    	        DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();;
		                    	        Container content =gt2Frame. getContentPane();
		                    		   content.add(panel); 
		                    		   final ScalingControl scaler = new CrossoverScalingControl();
		                    		     JButton plus = new JButton("+");
		                    		        plus.addActionListener(new ActionListener() {
		                    		            public void actionPerformed(ActionEvent e) {
		                    		                scaler.scale(vv, 1.1f, vv.getCenter());
		                    		            }
		                    		        });
		                    		        JButton minus = new JButton("-");
		                    		        minus.addActionListener(new ActionListener() {
		                    		            public void actionPerformed(ActionEvent e) {
		                    		                scaler.scale(vv, 1/1.1f, vv.getCenter());
		                    		            }
		                    		        });
		                    		        JButton save = new JButton("Save Graphical Transliteration 2");
		                    		        save.addActionListener(new ActionListener() {
		                    		            public void actionPerformed(ActionEvent e) {
		                    		              String idalt=currentNodeCopy.getId();
		                    		              int no=currentNode.getNoVariants();
		                    		              no=no+1;
		                    		              String idnew=idalt.substring(0,idalt.lastIndexOf("_")+1)+no;
		                    		              currentNodeCopy.setId(idnew);
		                    		              currentNode.setNoVariants(no);
		                    		      
		                    		              commitGT2EditorCopyToNewVariant(currentNodeCopy);
		                    		              System.out.println("Parent Id GT2"+currentNodeCopy.getId());
		                    		              String parentid=currentNodeCopy.getId().substring(0,currentNodeCopy.getId().lastIndexOf(">"));
		                    		              GraphTranslit1 gt1parent=gt1.get(mgt1.get(parentid).intValue());
		                    		              gt1parent.getGraphTranslit2().add(currentNodeCopy.getId());
		                    		          	//gt1.get(i).generateGT2();
		                    		          	gt2.add(currentNodeCopy);
		                    		          	mgt2.put(currentNodeCopy.getId(),new Integer(gt2.size()-1));
		                    		          	synchronizeEditedGT2ToSameBlockInAllReadings(currentNodeCopy);
		                    		          	NumTranslit2 parentNT2 = nt2.get(mnt2.get(gt1parent.getParent()).intValue());
		                    		          refreshVisibleNT1NodesForSameBlockInAllReadings(nt1.get(mnt1.get(parentNT2.getParent()).intValue()));
		                    		          	 AnnotationNode  newObj2=new AnnotationNode(currentNodeCopy), node2;  
		                    		                AnnotationNode prevobj=(AnnotationNode)obj.getParent();
		                    		          	prevobj.add(newObj2);
		                    		              reloadBlockTree();
			                      					expandAll(blockTree,path,true);
			                      					
			                      					gt2Frame.doDefaultCloseAction();
		                    		            }
		                    		        });
		                    		        //DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		                    		        
		                    		        graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
		                    		           vv.setGraphMouse(graphMouse); 
		                    		           JComboBox<String> modeBox =new JComboBox();
		                    		           modeBox.addItem("TRANSFORMING");
		                    		          // modeBox.addItem("EDITING");
		                    		           modeBox.addItem("PICKING");
		                    		           //modeBox.addItem("ANNOTATING");
		                    		           modeBox.setSelectedItem("PICKING");
		                    		         modeBox.addItemListener(new ItemListener() {
		                    		        	 public void itemStateChanged(ItemEvent e1) {
		                    		        		 String getItem=(String)modeBox.getSelectedItem();
		                    		        		 if(getItem.equals("TRANSFORMING")) {
		                    		        			 graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		                    		        			// graphMouse.remove( labelEditingPlugin)  ;
		                    		        		 }
		                    		        		// else if(getItem.equals("EDITING"))  graphMouse.setMode(ModalGraphMouse.Mode.EDITING);
		                    		        		 else if(getItem.equals("PICKING"))  {
		                    		        			 graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
		                    		        			   // graphMouse.add( labelEditingPlugin)  ;
		                    		        		 }
		                    		        		// else if(getItem.equals("ANNOTATING"))  graphMouse.setMode(ModalGraphMouse.Mode.ANNOTATING);
		                    		        		
		                    		        	 }
		                    		         });
		                    		         //
		                    		         vv.addGraphEdgeMouseListener(new GraphEdgeMouseListener<OperatorLink>() {
			                    		 	            @Override
			                    		 	            public void graphClicked(OperatorLink v, MouseEvent me) {
			                    		 	            	System.out.println("Clicked "+ v.getClass());
			                    		 	            	String clicked=""+v.getClass();
			                    		 	            	if(clicked.equals("class allmahVer4.OperatorLink")) {
			                    		 	                     if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() ==2) {
			                    		 	                    	if(clicked.equals("class allmahVer4.OperatorLink")){ 
			                    		 	                    	OperatorLink o=(OperatorLink) v;
			                    		 	                    	String newLabel =o.getLabel();
			                    		 	    					newLabel = JOptionPane.showInputDialog("Change Operator "+o.getLabel());
			                    		 	    					if(newLabel != null) {
			                    		 	    						if(newLabel.equals("-"))
			                    		 	    						o.setLabel(newLabel);
			                    		 	    						else o.setLabel(" ");
			                    		 	    					}
			                    		 	    						vv.repaint();
			                    		 	                    	   }
			                    		 	                    
			                    		 	                } 
			                    		 	                    if (me.getButton() == MouseEvent.BUTTON1 && me.isShiftDown() ) {
			                    		 	                    	System.out.println("coordinates "+ me.getX()+" "+ me.getY());
			                    		 	                    	if(clicked.equals("class allmahVer4.OperatorLink")){
			                    		 	                    	  final PickedState<OperatorLink> pickedEdgeState = vv.getPickedEdgeState();			                    		 	                         
			                    		 	                         JPopupMenu popup = new JPopupMenu();
			                    		 	                         final JMenuItem delEdge=new JMenuItem("Delete Edge");
			                    		 	                         popup.add(delEdge);
			                    		 	                       
			                    		 	                           popup.show(vv, me.getX(), me.getY());
			                    		 	                         delEdge.addActionListener(new ActionListener() {
			                    		 	                           public void actionPerformed(ActionEvent e) {
			                    		 	                               pickedEdgeState.pick(v, false);
			                    		 	                              currentNodeCopy.getGraphGT2().removeEdge(v);
			                    		 	                               vv.repaint();
			                    		 	                           }
			                    		 	                           });
			                    		 	                     }
			                    		 	                     }
			                    		 	                     
			                    		 	            	}
			                    		 	            
			                    		 	                me.consume();
			                    		 	            }

			                    		 	            @Override
			                    		 	            public void graphPressed(OperatorLink v, MouseEvent me) {
			                    		 	            }

			                    		 	            @Override
			                    		 	            public void graphReleased(OperatorLink v, MouseEvent me) {
			                    		 	            }
			                    		 	        });
			                    		         
		                    		          ArrayList<GraphGT2Node> selNodes=new ArrayList<GraphGT2Node>();
		                    		      
		                    		         vv.addGraphMouseListener(new GraphMouseListener() {

		                    		 	            @Override
		                    		 	            public void graphClicked(Object v, MouseEvent me) {
		                    		 	            	System.out.println("Clicked "+ v.getClass());
		                    		 	            	String clicked=""+v.getClass();
		                    		 	            	if(clicked.equals("class allmahVer4.GraphGT2Node")) {
		                    		 	                    if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() ==1){
		                    		 	                    	if(selNodes.size()>2) selNodes.clear();
		                    		 	                    	else selNodes.add((GraphGT2Node) v);
		                    		 	                    	  JPopupMenu popup = new JPopupMenu();
		                    		 	                    	  
			                    		 	                      
			                    		 	                        if (selNodes.size()==2) {
			                    		 	                        	final JMenuItem minusEdge=new JMenuItem("Add - Operator between "+selNodes.get(0)+" and "+selNodes.get(1));
			                    		 	                        	final JMenuItem emptyEdge=new JMenuItem("Add Empty Operator between "+selNodes.get(0)+" and "+selNodes.get(1));
			                    		 	                        	final JMenuItem clearSel=new JMenuItem("Clear Selection");
			                    		 	                        	popup.add(minusEdge);
			                    		 	                        popup.add(emptyEdge);
			                    		 	                       popup.add(clearSel);
			                    		 	                           popup.show(vv, me.getX(), me.getY());
			                    		 	                       
			                    		 	                       minusEdge.addActionListener(new ActionListener() {
			                    		 	                           public void actionPerformed(ActionEvent e) {
			                    		 	                           
			                    		 	                              currentNodeCopy.getGraphGT2().addEdge(new OperatorLink("-",5),selNodes.get(0),selNodes.get(1));
			                    		 	                              selNodes.clear();
			                    		 	                               vv.repaint();
			                    		 	                           }
			                    		 	                         });
			                    		 	                      clearSel.addActionListener(new ActionListener() {
			                    		 	                           public void actionPerformed(ActionEvent e) {
			                    		 	                           
			                    		 	                            
			                    		 	                              selNodes.clear();
			                    		 	                             
			                    		 	                           }
			                    		 	                         });
			                    		 	                      emptyEdge.addActionListener(new ActionListener() {
			                    		 	                           public void actionPerformed(ActionEvent e) {
			                    		 	                           
			                    		 	                              currentNodeCopy.getGraphGT2().addEdge(new OperatorLink(" ",5),selNodes.get(0),selNodes.get(1));
			                    		 	                              selNodes.clear();
			                    		 	                               vv.repaint();
			                    		 	                           }
			                    		 	                         });
		                    		 	                    }
		                    		 	                     }
		                    		 	            	}
		                    		 	                me.consume();
		                    		 	            }

		                    		 	            @Override
		                    		 	            public void graphPressed(Object v, MouseEvent me) {
		                    		 	            }

		                    		 	            @Override
		                    		 	            public void graphReleased(Object v, MouseEvent me) {
		                    		 	            }
		                    		 	        });
		                    		         
		                    		         //
		                    		     
		                    		     
		                    		        JPanel scaleGrid = new JPanel(new GridLayout(1,0));
		                    		        scaleGrid.setBorder(BorderFactory.createTitledBorder("Control"));

		                    		        JPanel controls = new JPanel();
		                    		        scaleGrid.add(plus);
		                    		        scaleGrid.add(minus);
		                    		        scaleGrid.add(save);
		                    		        controls.add(scaleGrid);
		                    		        controls.add(modeBox);
		                    		       content.add(controls, BorderLayout.SOUTH);
		                    		     
		                    		 gt2Frame.pack();
		                    		   addChild(gt2Frame,800, 10, 800, 800);		
		                    		   gt2Frame.setVisible(true); 
		                    		  gt2Frame.moveToFront();
		                    		
		                    	}
		                  });
		                  
		                  
		                  //
		                    popup.add(menuGr2E);
	                    GraphTranslit2 currentNode= (GraphTranslit2)obj.getMyTreeNode();
	                    if (!currentNode.getFinish()) {
	                        if (currentNode.getTyp().equals("main")) popup.add(menuGr2V);
	                        popup.add(menuPhonem);
	                        popup.add(menuGr2D);
	                        popup.add(menuFreezeGT2);
	                    } else {
	                        popup.add(menuUnfreezeGT2);
	                    }
		                 
		            }
		            else if (label.equals("class allmahVer4.NumTranslit1")) {
		                 defReading=new JMenuItem("Select Readings");
		               
		                  defReading.addActionListener(new ActionListener() {
		                    	public void actionPerformed(ActionEvent e) {
		                    	//	AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
		                    		NumTranslit1 currentNode= (NumTranslit1)obj.getMyTreeNode();
		                    		currentNode.createGraphStructure();
		                    		VisualizationViewer<GlyphNode,OperatorLink>	vv=getGraphViewer(currentNode.getEntireGraphNT1(), currentNode.getTreeGraphNT1(),currentNode,obj);
		                    		ChildFrame nt1Frame= new ChildFrame("Select Readings for "+ Jsoup.parse(obj.getMyTreeNode().getNodeLabel()).text(),Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
		                    		 
		                    		   nt1Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                    		  
		                    	        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
		                    	   
		                    	        Container content =nt1Frame. getContentPane();
		                    		   content.add(panel); 
		                    		   final ScalingControl scaler = new CrossoverScalingControl();
		                    		  scaler.scale(vv, 1.1f, vv.getCenter()); 
		                    		  //scaler.scale(vv, 1.1f, vv.getCenter()); scaler.scale(vv,1.1f, vv.getCenter());
		                    		//   scaler.scale(vv, 1.1f, vv.getCenter()); scaler.scale(vv, 1.1f, vv.getCenter()); scaler.scale(vv,1.1f, vv.getCenter());
		                    		//   hbGFrame.repaint();
		                    		     JButton plus = new JButton("+");
		                    		        plus.addActionListener(new ActionListener() {
		                    		            public void actionPerformed(ActionEvent e) {
		                    		                scaler.scale(vv, 1.1f, vv.getCenter());
		                    		            }
		                    		        });
		                    		        JButton minus = new JButton("-");
		                    		        minus.addActionListener(new ActionListener() {
		                    		            public void actionPerformed(ActionEvent e) {
		                    		                scaler.scale(vv, 1/1.1f, vv.getCenter());
		                    		            }
		                    		        });
		                    		   JButton generateNT2=new JButton("Generate NT2 and GT1");
		                    		   generateNT2.addActionListener(new ActionListener() {
	                    		            public void actionPerformed(ActionEvent e) {
	                    		             generateNT2ForSameBlockInAllReadings(currentNode);
	                    		             refreshVisibleNT1NodesForSameBlockInAllReadings(currentNode);
	                    		             reloadBlockTree();
	                      					expandAll(blockTree,path,true);
	                      		
	                      					defReading.setEnabled(false);nt1Frame.doDefaultCloseAction();
	                    
	                    		            }
	                    		        });
		                    		        JPanel scaleGrid = new JPanel(new GridLayout(1,0));
		                    		        scaleGrid.setBorder(BorderFactory.createTitledBorder("Control"));

		                    		        JPanel controls = new JPanel();
		                    		        scaleGrid.add(plus);
		                    		        scaleGrid.add(minus);
		                    		        scaleGrid.add(generateNT2);
		                    		        controls.add(scaleGrid);
		                    		       content.add(controls, BorderLayout.SOUTH);
		                    		       panel.getHorizontalScrollBar().setValue(panel.getHorizontalScrollBar().getMinimum());
				                    	     panel.getVerticalScrollBar().setValue(panel.getVerticalScrollBar().getMinimum());
		                    		  nt1Frame.pack();
		                    		   addChild(nt1Frame,700, 10, 500, 600);		
		                    		   nt1Frame.setVisible(true); 
		                    		  nt1Frame.moveToFront();
		                    	}
		                 });
		                   
		               
		                  popup.add(defReading);
		                 
		            
		                //
		            
		               
		        }
		            else if (label.equals("class allmahVer4.NumTranslit2")) {
		            	
                            final JMenuItem menuN2D = new JMenuItem("Delete local");

                            menuN2D.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    AnnotationNode obj = (AnnotationNode) path.getLastPathComponent();
                                    NumTranslit2 currentNode = (NumTranslit2) obj.getMyTreeNode();
                                    javax.swing.tree.TreeNode parentNode = obj.getParent();
                                    boolean deleted = deleteLocalNT2(currentNode, obj);

                                    if (!deleted) {
                                        JOptionPane.showMessageDialog(
                                                AllmahGUI.this,
                                                "NT2 could not be removed from its parent NT1 list.",
                                                "Delete local",
                                                JOptionPane.WARNING_MESSAGE
                                        );
                                        return;
                                    }

                                    if (blockTree != null
                                            && blockTree.getModel() instanceof DefaultTreeModel
                                            && parentNode instanceof AnnotationNode) {
                                        ((DefaultTreeModel) blockTree.getModel()).removeNodeFromParent(obj);
                                        reloadBlockTree(parentNode);
                                    }
                                    else {
                                        reloadBlockTree();
                                    }
                                }
                            });

		            	if( ((NumTranslit2)obj.getMyTreeNode()).getTyp().equals("main")) {
		            		
		            		//n2.generateGT1s();revalidate();repaint();
		            		 final JMenuItem insN2=new JMenuItem("Insert Num Translit.2");
		            		
		            		 
		            		   insN2.addActionListener(new ActionListener() {
			                    	public void actionPerformed(ActionEvent e) {
			                    		NumTranslit2 currentNode= (NumTranslit2)obj.getMyTreeNode();
			                    		NumTranslit2 n2=currentNode.copy();
			                    		String parentNT1Id=currentNode.getParent();
			                    		Integer parentNT1Index=mnt1.get(parentNT1Id);
			                    		if(parentNT1Index==null) {
			                    			JOptionPane.showMessageDialog(desk,"Cannot create NT2 variant: parent NT1 not found.");
			                    			return;
			                    		}
			                    		NumTranslit1 parentNT1=nt1.get(parentNT1Index.intValue());
			                    		String newNT2Id=parentNT1Id+">NT2_"+parentNT1.getNT2().size();
			                    		n2.setId(newNT2Id);
			                    		n2.setTyp("var");
			                    		ce.clear();
			                    		compNT2Nodes.clear();
			                    		n2.createGraphStructure();
			                    		//NumTranslit2 n2=((NumTranslit2)obj.getMyTreeNode()).copy();
			              	
			                    		vv2=getGraphNT2Viewer(n2.getEntireGraphNT2(), n2.getTreeGraphNT2(),n2,obj);
			                    		ChildFrame nt2Frame= new ChildFrame("Select Readings for "+ Jsoup.parse(obj.getMyTreeNode().getNodeLabel()).text(),Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
			                    		 
			                    		   nt2Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			                    		  
			                    	        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv2);
			                    	     
			                    	        Container content =nt2Frame. getContentPane();
			                    	        content.setLayout(new BorderLayout());
			                    		  content.add(panel, BorderLayout.CENTER); 
			                    		  // content.add(panel); 
			                    		   final ScalingControl scaler = new CrossoverScalingControl();
			                    		  scaler.scale(vv2, 1.1f, vv2.getCenter()); 
			                    		  //scaler.scale(vv, 1.1f, vv.getCenter()); scaler.scale(vv,1.1f, vv.getCenter());
			                    		//   scaler.scale(vv, 1.1f, vv.getCenter()); scaler.scale(vv, 1.1f, vv.getCenter()); scaler.scale(vv,1.1f, vv.getCenter());
			                    		//   hbGFrame.repaint();
			                    		     JButton plus = new JButton("+");
			                    		        plus.addActionListener(new ActionListener() {
			                    		            public void actionPerformed(ActionEvent e) {
			                    		                scaler.scale(vv2, 1.1f, vv2.getCenter());
			                    		            }
			                    		        });
			                    		        
			                    		        JButton minus = new JButton("-");
			                    		        minus.addActionListener(new ActionListener() {
			                    		            public void actionPerformed(ActionEvent e) {
			                    		                scaler.scale(vv2, 1/1.1f, vv2.getCenter());
			                    		            }
			                    		        });
			                    		   JButton generateNT2=new JButton("Generate NT2 Variant");
			                    		   generateNT2.addActionListener(new ActionListener() {
		                    		            public void actionPerformed(ActionEvent e) {
		                    		            	 
		                   		                //    obj.removeAllChildren();
		                   		                    for (int i=0;i<ce.size();i++) {
		                   		                    	for (int j=0;j<ce.get(i).getElements().size();j++) {
		                   		                    		((GraphNT2Node)ce.get(i).getElements().get(j)).getNT2Element().setComplexGlyph(ce.get(i));
		                   		                    		
		                   		                    	}
		                   		                    }
		                   		                  System.out.println("Parent "+prevobj.toString());
		                   		                  System.out.println("Generated n2" + n2.calculateLabel());
		                   		                 if(!mnt2.containsKey(n2.getId())) {
		                   		                 	nt2.add(n2);
		                   		                 	mnt2.put(n2.getId(),new Integer(nt2.size()-1));
		                   		                 }
		                   		                 if(!parentNT1.getNT2().contains(n2.getId())) {
		                   		                 	parentNT1.getNT2().add(n2.getId());
		                   		                 }
		                   		                 AnnotationNode  newObj1=new AnnotationNode(n2), node1;  
		                   		              prevobj.add(newObj1);
		                   		          prevobj.setExplored(false);
		                   		           System.out.println("Generated n2" + n2.calculateLabel());
		                   		              restoreReadingsForNT2VariantBeforeGT1Generation(n2);
                                      n2.generateGT1s("var");
                                      propagateNT2VariantToSameBlockInAllReadings(n2, parentNT1);
                                      refreshVisibleNT1NodesForSameBlockInAllReadings(parentNT1);
		                   		              System.out.println("N2 Child has "+n2.getGT1().size() + "GT1");
		                   		                      for (int j=0;j<n2.getGT1().size();j++) {
         		               	                          	GraphTranslit1 g1=gt1.get(mgt1.get(n2.getGT1().get(j)).intValue());
         		               	                    	GraphTranslit2 g2=gt2.get(mgt2.get(g1.getGraphTranslit2().get(0)).intValue());
            		               	                           AnnotationNode  newObj2=new AnnotationNode(g1), node2;   
            		               	                        AnnotationNode  newObj3=new AnnotationNode(g2), node3;   
            		               	                        System.out.println("GT1 Child "+newObj2.toString());
            		               	                     newObj2.add(newObj3);
            		               	                                         newObj1.add(newObj2);
         		               	                 }
		                   		                     
		                   		                
		                   		                // prevobj.explore();
		                   		          // ((DefaultTreeModel)blockTree.getModel()).nodeChanged(prevobj);;
		                   		          reloadBlockTree();	 
		                   		    //   reloadBlockTree();
                     					expandAll(blockTree,path.getParentPath(),true);
		                   		        //expandAll(blockTree,path,true);
		                      			
		                      				nt2Frame.doDefaultCloseAction();
		                      				ce.clear();
		                      				compNT2Nodes.clear();
		                      			//	prevobj.explore();
		                    		            }
		                    		        });
			                    		   //
			                    		   final JColorChooser colorChooser = new JColorChooser(); // default color is black
					                		colorChooser.setBorder(null);
					                		colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
					                		    public void stateChanged(ChangeEvent e) {
					                		    	
					                		    
					                		    	selC=colorChooser.getSelectionModel().getSelectedColor();
					                		    	
			                    		            	
					    		            		//n2.generateGT1s();revalidate();repaint();
					    		            		//n2.generateGT1s();revalidate();repaint();NumTranslit2 n2=((NumTranslit2)obj.getMyTreeNode()).copy();
					    		            		//n2.generateGT1s();revalidate();repaint();
					    		            		//n2.generateGT1s();revalidate();repaint();
					                		    }
					                		});
					                		//
					                		  DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();	
	                    		                graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
					                		   graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		                    		           vv2.setGraphMouse(graphMouse); 
		                    		           JComboBox<String> modeBox =new JComboBox();
		                    		           modeBox.addItem("TRANSFORMING");
		                    		          // modeBox.addItem("EDITING");
		                    		           modeBox.addItem("PICKING");
		                    		           //modeBox.addItem("ANNOTATING");
		                    		           modeBox.setSelectedItem("TRANSFORMING");
		                    		       
		                    		         modeBox.addItemListener(new ItemListener() {
		                    		        	 public void itemStateChanged(ItemEvent e1) {
		                    		        		 String getItem=(String)modeBox.getSelectedItem();
		                    		        		 if(getItem.equals("TRANSFORMING")) {
		                    		        			 graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		                    		        			// graphMouse.remove( labelEditingPlugin)  ;
		                    		        		 }
		                    		        		// else if(getItem.equals("EDITING"))  graphMouse.setMode(ModalGraphMouse.Mode.EDITING);
		                    		        		 else if(getItem.equals("PICKING"))  {
		                    		        			 graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
		                    		        			   // graphMouse.add( labelEditingPlugin)  ;
		                    		        		 }
		                    		        		// else if(getItem.equals("ANNOTATING"))  graphMouse.setMode(ModalGraphMouse.Mode.ANNOTATING);
		                    		        		
		                    		        	 }
		                    		         });
			                    		   //
			                    		   JButton generateComponent=new JButton("Generate Component");
			                    		   generateComponent.addActionListener(new ActionListener() {
		                    		            public void actionPerformed(ActionEvent e) {
		                    		            	
		                    		               
		                    		            	System.out.println("Selected Color "+ selC);
		                    		            	    
		              	                            	GraphNT2Node n2n=new GraphNT2Node(selC); 
		              	                            	vv2.setLocation(30, 30);
		              	                            	n2.getEntireGraphNT2().addVertex(n2n);
		              	                            	
		              	                            	ComponentElement compon=new ComponentElement(selC);
		              	                            	ce.add(compon);
		              	                            	compNT2Nodes.add(n2n);
		              	                            	
		              	                            	
        	                                        vv2.revalidate();
					                		    	vv2.repaint();
					                		      
		                    		            
		                    		            }
		                    		            });
			                    		   //     
			                    		   JPanel scaleGrid = new JPanel(new GridLayout(1,0));
			                    		        scaleGrid.setBorder(BorderFactory.createTitledBorder("Control"));

			                    		        JPanel controls = new JPanel();
			                    		        scaleGrid.add(plus);
			                    		        scaleGrid.add(minus);
			                    		        scaleGrid.add(modeBox);
			                    		        scaleGrid.add(generateComponent);
			                    		        scaleGrid.add(generateNT2);
			                    		        controls.add(scaleGrid);
			                    		        JPanel colors=new JPanel();
			                    		        colors.add(colorChooser);
			                    		       content.add(controls, BorderLayout.SOUTH);
			                    		       content.add(colors, BorderLayout.EAST);
			                    		      // content.add(colors);
			                    		     
			                    		  nt2Frame.pack();
			                    		   addChild(nt2Frame,800, 10, 800, 600);		
			                    		   nt2Frame.setVisible(true); 
			                    		  nt2Frame.moveToFront();
			                    	}
			                 });
		            		 
		            		 
		            		 //
		            			
		            			
		            		  popup.add(insN2);
		            		  
		            	}
		             //   final JMenuItem defCompon=new JMenuItem("Define Component");
		             
		                //final JMenuItem delN2=new JMenuItem("Delete Num Translit.2");
		            //    popup.add(defCompon);
		               // popup.addSeparator();
		              
		                //popup.add(delN2);
		                //

                                AnnotationNode objN2 = (AnnotationNode) path.getLastPathComponent();
                                if (!objN2.getMyTreeNode().getFinish()) {
                                    popup.add(menuN2D);
                                }
		            
		               
		        }
				}
		            popup.show(tree, x, y);
		        }
				 public void mousePressed(MouseEvent e) {
			            if (e.isPopupTrigger()) myPopupEvent(e);
			        }
			        public void mouseReleased(MouseEvent e) {
			            if (e.isPopupTrigger()) myPopupEvent(e);
			        }
			});
			return blockTree;
		}
	 private void treeMouseDragged(final MouseEvent evt) {
		
		if(((SubElementNode)letterTree.getLastSelectedPathComponent()).isLeaf()){
	            letterTree.getTransferHandler().exportAsDrag(letterTree, evt, TransferHandler.MOVE);
		}
	    }
	 private void expandAllLetters(JTree tree, TreePath path, boolean expand) {
	       SubElementNode node = (SubElementNode) path.getLastPathComponent();

	        if (node.getChildCount()>=0) {
	           Enumeration  enumeration = node.children();
	           while(enumeration.hasMoreElements()) {
	               SubElementNode n = (SubElementNode) enumeration.nextElement();
	           
	                TreePath p = path.pathByAddingChild(n);

	                expandAllLetters(tree, p, expand);
	             
	            }
	        }

	        if (expand) {
	            tree.expandPath(path);
	        } else {
	            tree.collapsePath(path);
	        }
	    }
	 private void expandAll(JTree tree, TreePath path, boolean expand) {
	        AnnotationNode node = (AnnotationNode) path.getLastPathComponent();

	        if (node.getChildCount()>=0) {
	           Enumeration  enumeration = node.children();
	           while(enumeration.hasMoreElements()) {
	                AnnotationNode n = (AnnotationNode) enumeration.nextElement();
	               if(!n.toString().contains("#")) {
	                TreePath p = path.pathByAddingChild(n);

	                expandAll(tree, p, expand);
	               }
	            }
	        }

	        if (expand) {
	            tree.expandPath(path);
	        } else {
	            tree.collapsePath(path);
	        }
	    }

      private void reloadBlockTree() {
          if (blockTree == null || blockTree.getModel() == null) {
              return;
          }

          ((DefaultTreeModel) blockTree.getModel()).reload();
          SwingUtilities.invokeLater(new Runnable() {
              public void run() {
                  expandTree(blockTree, true);
                  blockTree.revalidate();
                  blockTree.repaint();
              }
          });
      }

      private void reloadBlockTree(javax.swing.tree.TreeNode node) {
          if (blockTree == null || blockTree.getModel() == null) {
              return;
          }

          if (node == null) {
              reloadBlockTree();
              return;
          }

          ((DefaultTreeModel) blockTree.getModel()).reload(node);
          SwingUtilities.invokeLater(new Runnable() {
              public void run() {
                  expandTree(blockTree, true);
                  blockTree.revalidate();
                  blockTree.repaint();
              }
          });
      }

	  private void expandTreeLetters(JTree tree, boolean expand) {
	        SubElementNode root = (SubElementNode) tree.getModel().getRoot();
	        expandAllLetters(tree, new TreePath(root), expand);
	    }
	  private void expandTree(JTree tree, boolean expand) {
	        AnnotationNode root = (AnnotationNode) tree.getModel().getRoot();
	        expandAll(tree, new TreePath(root), expand);
	    }
	  

	  private void deleteLocalBlockAnnotation(BlockAnnotation annotation) {
	      if (annotation == null) {
	          return;
	      }

	      String annotationId = annotation.getId();
	      if (annotationId == null) {
	          return;
	      }

	      if (hb != null) {
	          for (HieroglyphenBlock block : hb) {
	              if (block != null
	                      && block.getAnnotations() != null) {
	                  block.getAnnotations().remove(annotationId);
	              }
	          }
	      }

	      if (blockAnn != null) {
	          for (int i = blockAnn.size() - 1; i >= 0; i--) {
	              BlockAnnotation candidate = blockAnn.get(i);
	              if (candidate != null
	                      && annotationId.equals(candidate.getId())) {
	                  blockAnn.remove(i);
	              }
	          }
	      }

	      mbla.clear();
	      if (blockAnn != null) {
	          for (int i = 0; i < blockAnn.size(); i++) {
	              BlockAnnotation candidate = blockAnn.get(i);
	              if (candidate != null
	                      && candidate.getId() != null) {
	                  mbla.put(candidate.getId(), Integer.valueOf(i));
	              }
	          }
	      }

	      changed = true;
	      reloadBlockTree();
	      if (blockTree != null) {
	          expandTree(blockTree, true);
	      }
	  }

	  private boolean compareBorders(ArrayList<String> a, ArrayList<String> b) {
	      if (a == null || b == null) {
	          return false;
	      }

	      System.out.println(" Selection borders " + a);
	      System.out.println(" Annotation borders " + b);

	      if (a.size() != b.size()) {
	          return false;
	      }

	      HashSet<String> sa = new HashSet<String>(a);
	      HashSet<String> sb = new HashSet<String>(b);

	      return sa.equals(sb);
	  }
	  public GraphGT2Node firstGT2Node(DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> g) {
			GraphGT2Node g2=null;
			Collection<GraphGT2Node> nodes=g.getVertices();
			Iterator<GraphGT2Node> it=nodes.iterator();
			while(it.hasNext()) {
				g2=it.next();
				if( g.getInEdges(g2).size()==0) return g2;
			}
			if(nodes.size()>0) return nodes.iterator().next();
			return null;
		}


	  /**
	   * Commit the graph edited in the GT2 editor back into an existing GT2 node.
	   *
	   * Important: the editor works on a copy. Do NOT put the editor graph/nodes
	   * directly into the persistent model, otherwise later actions such as
	   * Generate Component / Generate GT2 can see a mixture of editor-copy nodes
	   * and original model nodes. That is the source of the observed disappearing
	   * GT2 / reappearing GT1 behaviour.
	   */
	  private void commitGT2EditorCopyToExistingNode(GraphTranslit2 target, GraphTranslit2 editorCopy) {
	      ArrayList<GraphGT2Node> ordered = collectGT2NodesInSaveOrder(editorCopy.getGraphGT2());
	      editorCopy.setElements(ordered);
	      editorCopy.setLabel(editorCopy.calculateLabel());

	      GraphTranslit2 stableCopy = editorCopy.copy(target.getTyp());
	      stableCopy.setId(target.getId());
	      stableCopy.setNoVariants(target.getNoVariants());
	      stableCopy.setPathImage(target.getPathImage());
	      stableCopy.setInterpretationConfidence(target.getInterpretationConfidence());

	      target.setElements(stableCopy.getElements());
	      target.setGraph(stableCopy.getGraphGT2());
	      target.setLabel(stableCopy.calculateLabel());
	  }

	  /**
	   * Finalise a newly generated GT2 variant before it is inserted into the model.
	   * This uses the same deep-copy barrier as the edit-save path, so the stored
	   * variant is not the live editor graph instance.
	   */
	  private void commitGT2EditorCopyToNewVariant(GraphTranslit2 editorCopy) {
	      ArrayList<GraphGT2Node> ordered = collectGT2NodesInSaveOrder(editorCopy.getGraphGT2());
	      editorCopy.setElements(ordered);
	      editorCopy.setLabel(editorCopy.calculateLabel());

	      GraphTranslit2 stableCopy = editorCopy.copy(editorCopy.getTyp());
	      stableCopy.setId(editorCopy.getId());
	      stableCopy.setNoVariants(editorCopy.getNoVariants());
	      stableCopy.setPathImage(editorCopy.getPathImage());
	      stableCopy.setInterpretationConfidence(editorCopy.getInterpretationConfidence());

	      editorCopy.setElements(stableCopy.getElements());
	      editorCopy.setGraph(stableCopy.getGraphGT2());
	      editorCopy.setLabel(stableCopy.calculateLabel());
	  }

	  private ArrayList<GraphGT2Node> collectGT2NodesInSaveOrder(DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> g) {
		    ArrayList<GraphGT2Node> ordered = new ArrayList<GraphGT2Node>();
		    if (g == null || g.getVertices() == null || g.getVertices().isEmpty()) {
		        return ordered;
		    }

		    GraphGT2Node first = firstGT2Node(g);
		    GraphGT2Node current = first;
		    HashSet<GraphGT2Node> seen = new HashSet<GraphGT2Node>();

		    while (current != null && !seen.contains(current)) {
		        ordered.add(current);
		        seen.add(current);

		        GraphGT2Node next = null;
		        Iterator<OperatorLink> out = g.getOutEdges(current).iterator();
		        while (out.hasNext() && next == null) {
		            OperatorLink edge = out.next();
		            GraphGT2Node candidate = g.getDest(edge);
		            if (candidate != null && !seen.contains(candidate)) {
		                next = candidate;
		            }
		        }
		        current = next;
		    }

		    // Safety net: after manual graph editing it is easy to leave a node disconnected
		    // or to create a small branch. Never silently drop such GT2 nodes on save.
		    Iterator<GraphGT2Node> it = g.getVertices().iterator();
		    while (it.hasNext()) {
		        GraphGT2Node node = it.next();
		        if (!seen.contains(node)) {
		            ordered.add(node);
		            seen.add(node);
		        }
		    }

		    return ordered;
		}
      /**
       * Last defensive step before Turtle export.
       *
       * After opening an already exported TTL file, some edit paths can leave
       * the visible tree node and the global gt2/mgt2 model out of sync.  The
       * user then sees the edited GT2 in the GUI, but Save still exports the
       * older GraphTranslit2 object from the global list.  This method makes the
       * visible tree authoritative immediately before export: every expanded
       * GraphTranslit2 node is registered in gt2/mgt2, and every expanded
       * GraphTranslit1 node writes its visible GT2 children back into its
       * getGraphTranslit2() list in the visible order.
       */
      private void synchronizeVisibleGT2TreeStateBackToModel() {
          if (blockTree == null || blockTree.getModel() == null) {
              return;
          }

          Object rootObject = blockTree.getModel().getRoot();
          if (!(rootObject instanceof AnnotationNode)) {
              return;
          }

          synchronizeVisibleGT2TreeStateBackToModel((AnnotationNode) rootObject);
      }

      private void synchronizeVisibleGT2TreeStateBackToModel(AnnotationNode treeNode) {
          if (treeNode == null) {
              return;
          }

          TreeNode modelNode = treeNode.getMyTreeNode();

          if (modelNode instanceof GraphTranslit2) {
              registerVisibleGT2((GraphTranslit2) modelNode);
          }

          if (modelNode instanceof GraphTranslit1) {
              GraphTranslit1 visibleGT1 = (GraphTranslit1) modelNode;
              ArrayList<String> visibleGT2Ids = new ArrayList<String>();

              for (int i = 0; i < treeNode.getChildCount(); i++) {
                  Object childObject = treeNode.getChildAt(i);
                  if (!(childObject instanceof AnnotationNode)) {
                      continue;
                  }

                  TreeNode childModelNode =
                          ((AnnotationNode) childObject).getMyTreeNode();

                  if (childModelNode instanceof GraphTranslit2) {
                      GraphTranslit2 visibleGT2 = (GraphTranslit2) childModelNode;
                      registerVisibleGT2(visibleGT2);
                      visibleGT2Ids.add(visibleGT2.getId());
                  }
              }

              /*
               * Only overwrite the GT2 id list when the GT1 node is actually
               * expanded and shows GT2 children.  Collapsed, unexplored nodes
               * may legitimately have childCount()==0; clearing them would
               * destroy data.
               */
              if (!visibleGT2Ids.isEmpty()) {
                  visibleGT1.getGraphTranslit2().clear();
                  visibleGT1.getGraphTranslit2().addAll(visibleGT2Ids);
              }
          }

          for (int i = 0; i < treeNode.getChildCount(); i++) {
              Object childObject = treeNode.getChildAt(i);
              if (childObject instanceof AnnotationNode) {
                  synchronizeVisibleGT2TreeStateBackToModel(
                          (AnnotationNode) childObject
                  );
              }
          }
      }

      private void registerVisibleGT2(GraphTranslit2 visibleGT2) {
          if (visibleGT2 == null || visibleGT2.getId() == null) {
              return;
          }

          Integer existingIndex = mgt2.get(visibleGT2.getId());

          if (existingIndex == null) {
              gt2.add(visibleGT2);
              mgt2.put(
                      visibleGT2.getId(),
                      Integer.valueOf(gt2.size() - 1)
              );
          }
          else {
              gt2.set(existingIndex.intValue(), visibleGT2);
          }

          String parentGT1Id = visibleGT2.getParent();
          Integer parentGT1Index = mgt1.get(parentGT1Id);
          if (parentGT1Index != null) {
              GraphTranslit1 parentGT1 = gt1.get(parentGT1Index.intValue());
              if (!parentGT1.getGraphTranslit2().contains(visibleGT2.getId())) {
                  parentGT1.getGraphTranslit2().add(visibleGT2.getId());
              }
          }
      }


      private String nextPhonemicTranslitId(GraphTranslit2 parentGT2) {
          String parentId = parentGT2.getId();
          int index = 0;
          while (true) {
              String candidate = parentId + ">PT_" + index;
              if (!mpt.containsKey(candidate)
                      && !parentGT2.getPhonemicId().contains(candidate)) {
                  return candidate;
              }
              index++;
          }
      }

	  public PhonemNode firstPhonemNode(DirectedOrderedSparseMultigraph<PhonemNode,OperatorLink> g) {
			PhonemNode p=null;
			Collection<PhonemNode> nodes=g.getVertices();
			Iterator<PhonemNode> it=nodes.iterator();
			boolean found=false;
			while(it.hasNext() &&(!found)) {
				p=it.next();
				if( g.getInEdges(p).size()==0) found =true;
			}
			return p;
		}
	  public static BufferedImage toBufferedImage(Image img)
	  {
	      if (img instanceof BufferedImage)
	      {
	          return (BufferedImage) img;
	      }

	      // Create a buffered image with transparency
	      BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	      // Draw the image on to the buffered image
	      Graphics2D bGr = bimage.createGraphics();
	      bGr.drawImage(img, 0, 0, null);
	      bGr.dispose();

	      // Return the buffered image
	      return bimage;
	  }
	  private void expandTree(JTree tree) {
	        DefaultMutableTreeNode root =
	            (DefaultMutableTreeNode)tree.getModel().getRoot();
	        Enumeration e = root.breadthFirstEnumeration();
	        while(e.hasMoreElements()) {
	            DefaultMutableTreeNode node =
	                (DefaultMutableTreeNode)e.nextElement();
	            if(node.isLeaf()) continue;
	            int row = tree.getRowForPath(new TreePath(node.getPath()));
	            tree.expandRow(row);
	        }
	    }
	 
	  
	  
	  //
	 /* private void joinSelectedBlocks(TreePath[] paths) {

		   

		    ArrayList<HieroglyphenBlock> selected =
		            getSelectedBlocksInDocumentOrder(paths);

		    if (selected.size() < 2) {
		        JOptionPane.showMessageDialog(
		                this,
		                "Please select at least two consecutive blocks."
		        );
		        return;
		    }

		    if (!areConsecutiveBlocks(selected)) {
		        JOptionPane.showMessageDialog(
		                this,
		                "Join is only possible for consecutive blocks."
		        );
		        return;
		    }

		    if (!canJoinBlocks(selected)) {
		        JOptionPane.showMessageDialog(
		                this,
		                "Join is only possible if every selected block has exactly one alphanumeric transliteration and no multiple selected readings."
		        );
		        return;
		    }
		    if (sc != null) {
		        sc.setViewportView(new JPanel());
		        sc.revalidate();
		        sc.repaint();
		    }
		    DocumentReading newReading =
		            new DocumentReading(
		                    doc.getId(),
		                    this
		            );

		    docr.add(newReading);

		    mdocr.put(
		            newReading.getId(),
		            Integer.valueOf(docr.size() - 1)
		    );

		    doc.getDocReadings().add(
		            newReading.getId()
		    );

		    int firstSelectedIndex =
		            hb.indexOf(selected.get(0));

		    int lastSelectedIndex =
		            hb.indexOf(selected.get(selected.size() - 1));

		    int originalHbSize =
		            hb.size();

		    for (int i = 0; i < originalHbSize; i++) {

		        HieroglyphenBlock oldBlock =
		                hb.get(i);

		        if (i < firstSelectedIndex || i > lastSelectedIndex) {

		            HieroglyphenBlock copiedBlock =
		                    copyBlockToReading(
		                            oldBlock,
		                            newReading
		                    );

		            hb.add(copiedBlock);

		            mhb.put(
		                    copiedBlock.getBlockID(),
		                    Integer.valueOf(hb.size() - 1)
		            );

		            newReading.getBlocks().add(
		                    copiedBlock.getBlockID()
		            );
		            copiedBlock.generateNT1();

		        } else if (i == firstSelectedIndex) {

		            HieroglyphenBlock joinedBlock =
		                    createJoinedBlockForReading(
		                            selected,
		                            newReading
		                    );

		            hb.add(joinedBlock);

		            mhb.put(
		                    joinedBlock.getBlockID(),
		                    Integer.valueOf(hb.size() - 1)
		            );

		            newReading.getBlocks().add(
		                    joinedBlock.getBlockID()
		            );
		            joinedBlock.generateNT1();
		            i = lastSelectedIndex;
		        }
		    }

		    blockTree =
		            builtAnnTree();

		    sc.setViewportView(blockTree);
		    sc.revalidate();
		    sc.repaint();

		    changed = true;
		}*/
	  //
	  
private ArrayList<HieroglyphenBlock> getSelectedBlocksInDocumentOrder(
        TreePath[] paths
) {

    ArrayList<HieroglyphenBlock> selected =
            new ArrayList<HieroglyphenBlock>();

    final DocumentReading sourceReading =
            getSourceReadingFromSelection(paths);

    for (int i = 0; i < paths.length; i++) {

        AnnotationNode obj =
                (AnnotationNode) paths[i].getLastPathComponent();

        if (obj.getMyTreeNode() instanceof HieroglyphenBlock) {
            selected.add(
                    (HieroglyphenBlock) obj.getMyTreeNode()
            );
        }
    }

    Collections.sort(
            selected,
            new Comparator<HieroglyphenBlock>() {
                public int compare(
                        HieroglyphenBlock a,
                        HieroglyphenBlock b
                ) {
                    if (sourceReading != null) {
                        return Integer.compare(
                                sourceReading.getBlocks().indexOf(a.getBlockID()),
                                sourceReading.getBlocks().indexOf(b.getBlockID())
                        );
                    }

                    return Integer.compare(
                            hb.indexOf(a),
                            hb.indexOf(b)
                    );
                }
            }
    );

    return selected;
}

private DocumentReading getSourceReadingFromSelection(
        TreePath[] paths
) {

    if (paths == null || paths.length == 0) {
        return null;
    }

    Object[] pathObjects =
            paths[0].getPath();

    for (int i = pathObjects.length - 1; i >= 0; i--) {

        if (pathObjects[i] instanceof AnnotationNode) {

            AnnotationNode node =
                    (AnnotationNode) pathObjects[i];

            if (node.getMyTreeNode() instanceof DocumentReading) {
                return (DocumentReading) node.getMyTreeNode();
            }
        }
    }

    return null;
}

private boolean areConsecutiveBlocks(
        ArrayList<HieroglyphenBlock> selected,
        DocumentReading sourceReading
) {

    if (selected.isEmpty() || sourceReading == null) {
        return false;
    }

    ArrayList<String> sourceBlocks =
            sourceReading.getBlocks();

    int previous =
            sourceBlocks.indexOf(selected.get(0).getBlockID());

    if (previous < 0) {
        return false;
    }

    for (int i = 1; i < selected.size(); i++) {

        int current =
                sourceBlocks.indexOf(selected.get(i).getBlockID());

        if (current != previous + 1) {
            return false;
        }

        previous = current;
    }

    return true;
}

private boolean areConsecutiveBlocks(
        ArrayList<HieroglyphenBlock> selected
) {

    if (selected.isEmpty()) {
        return false;
    }

    int previous =
            hb.indexOf(selected.get(0));

    if (previous < 0) {
        return false;
    }

    for (int i = 1; i < selected.size(); i++) {

        int current =
                hb.indexOf(selected.get(i));

        if (current != previous + 1) {
            return false;
        }

        previous = current;
    }

    return true;
}

private boolean canJoinBlocks(
        ArrayList<HieroglyphenBlock> selected
) {

    for (HieroglyphenBlock b : selected) {

        if (!b.getState()) {
            return false;
        }

        ArrayList<ExplicitJoinBundle> bundles =
                getExplicitJoinBundles(b);

        if (bundles.isEmpty()) {
            return false;
        }
    }

    return true;
}

private ArrayList<ExplicitJoinBundle> getExplicitJoinBundles(
        HieroglyphenBlock block
) {

    ArrayList<ExplicitJoinBundle> result =
            new ArrayList<ExplicitJoinBundle>();

    if (block.getNumTranslit1() == null) {
        return result;
    }

    for (String nt1Id : block.getNumTranslit1()) {

        Integer nt1Index =
                mnt1.get(nt1Id);

        if (nt1Index == null) {
            continue;
        }

        NumTranslit1 n1 =
                nt1.get(nt1Index.intValue());

        if (n1.getNT2() == null || n1.getNT2().isEmpty()) {
            continue;
        }

        for (String nt2Id : n1.getNT2()) {

            Integer nt2Index =
                    mnt2.get(nt2Id);

            if (nt2Index == null) {
                continue;
            }

            NumTranslit2 n2 =
                    nt2.get(nt2Index.intValue());

            if (!hasCompatibleNT1NT2Sizes(block, n1, n2)) {
                continue;
            }

            ExplicitJoinBundle bundle =
                    new ExplicitJoinBundle(
                            block,
                            n1,
                            n2
                    );

            addExplicitGTVariantsToBundle(bundle);

            if (!bundle.gtVariants.isEmpty()) {
                result.add(bundle);
            }
        }
    }

    return result;
}

private boolean hasCompatibleNT1NT2Sizes(
        HieroglyphenBlock block,
        NumTranslit1 n1,
        NumTranslit2 n2
) {

    int glyphCount =
            block.getElements().size();

    if (n1.getElements() == null
            || n1.getElements().size() != glyphCount) {
        return false;
    }

    if (n2.getElements() == null
            || n2.getElements().size() != glyphCount) {
        return false;
    }

    return true;
}

private void addExplicitGTVariantsToBundle(
        ExplicitJoinBundle bundle
) {

    if (bundle.n2.getGT1() == null
            || bundle.n2.getGT1().isEmpty()) {
        return;
    }

    int glyphCount =
            bundle.block.getElements().size();

    for (String gt1Id : bundle.n2.getGT1()) {

        Integer gt1Index =
                mgt1.get(gt1Id);

        if (gt1Index == null) {
            continue;
        }

        GraphTranslit1 g1 =
                gt1.get(gt1Index.intValue());

        if (g1.getElements() == null
                || g1.getElements().size() != glyphCount) {
            continue;
        }

        if (g1.getGraphTranslit2() == null
                || g1.getGraphTranslit2().size() != 1) {
            continue;
        }

        String gt2Id =
                g1.getGraphTranslit2().get(0);

        Integer gt2Index =
                mgt2.get(gt2Id);

        if (gt2Index == null) {
            continue;
        }

        GraphTranslit2 g2 =
                gt2.get(gt2Index.intValue());

        if (g2 == null || g2.getGT2Elements() == null) {
            continue;
        }

        bundle.gtVariants.add(
                new ExplicitGTVariant(g1, g2)
        );
    }
}

private void joinSelectedBlocks(TreePath[] paths) {

    int choice =
            JOptionPane.showOptionDialog(
                    this,
                    "How should the join be applied?",
                    "Join selected blocks",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[] {
                            "Join in current reading",
                            "Create new reading",
                            "Cancel"
                    },
                    "Join in current reading"
            );

    if (choice == 0) {
        joinSelectedBlocks(paths, false);
    } else if (choice == 1) {
        joinSelectedBlocks(paths, true);
    }
}

private void joinSelectedBlocks(
        TreePath[] paths,
        boolean createNewReading
) {

    DocumentReading sourceReading =
            getSourceReadingFromSelection(paths);

    ArrayList<HieroglyphenBlock> selected =
            getSelectedBlocksInDocumentOrder(paths);

    if (sourceReading == null) {
        JOptionPane.showMessageDialog(
                this,
                "Join cannot determine the source DocumentReading."
        );
        return;
    }

    if (selected.size() < 2) {
        JOptionPane.showMessageDialog(
                this,
                "Please select at least two consecutive blocks."
        );
        return;
    }

    if (!areConsecutiveBlocks(selected, sourceReading)) {
        JOptionPane.showMessageDialog(
                this,
                "Join is only possible for consecutive blocks inside the same DocumentReading."
        );
        return;
    }

    if (!canJoinBlocks(selected)) {
        JOptionPane.showMessageDialog(
                this,
                "Join is only possible if every selected block has complete explicit NT1 → NT2 → GT1 → GT2 structures, with exactly one GT2 per GT1."
        );
        return;
    }

    if (sc != null) {
        sc.setViewportView(new JPanel());
        sc.revalidate();
        sc.repaint();
    }

    DocumentReading targetReading =
            sourceReading;

    if (createNewReading) {

        targetReading =
                new DocumentReading(
                        doc.getId(),
                        this
                );

        docr.add(targetReading);

        mdocr.put(
                targetReading.getId(),
                Integer.valueOf(docr.size() - 1)
        );

        doc.getDocReadings().add(
                targetReading.getId()
        );
    }

    ArrayList<String> sourceBlockIds =
            new ArrayList<String>(
                    sourceReading.getBlocks()
            );

    ArrayList<String> newBlockList =
            new ArrayList<String>();

    int firstSelectedIndex =
            sourceBlockIds.indexOf(selected.get(0).getBlockID());

    int lastSelectedIndex =
            sourceBlockIds.indexOf(selected.get(selected.size() - 1).getBlockID());

    for (int i = 0; i < sourceBlockIds.size(); i++) {

        String oldBlockId =
                sourceBlockIds.get(i);

        if (i < firstSelectedIndex || i > lastSelectedIndex) {

            newBlockList.add(
                    oldBlockId
            );

        } else if (i == firstSelectedIndex) {

            HieroglyphenBlock joinedBlock =
                    createJoinedBlockForReadingWithoutGeneratedNT1(
                            selected,
                            targetReading
                    );

            hb.add(joinedBlock);

            mhb.put(
                    joinedBlock.getBlockID(),
                    Integer.valueOf(hb.size() - 1)
            );

            newBlockList.add(
                    joinedBlock.getBlockID()
            );

            createExplicitJoinedBundles(
                    joinedBlock,
                    selected
            );

            i = lastSelectedIndex;
        }
    }

    targetReading.getBlocks().clear();
    targetReading.getBlocks().addAll(newBlockList);

    blockTree =
            builtAnnTree();

    sc.setViewportView(blockTree);
    sc.revalidate();
    sc.repaint();

    changed = true;
}

private HieroglyphenBlock createJoinedBlockForReadingWithoutGeneratedNT1(
        ArrayList<HieroglyphenBlock> selected,
        DocumentReading newReading
) {

    StringBuilder joinedExpression =
            new StringBuilder();

    StringBuilder joinedBlockLabel =
            new StringBuilder();

    Map<String, ArrayList<Reading>> elements =
            new HashMap<String, ArrayList<Reading>>();

    Map<String, ArrayList<String>> choiceList =
            new HashMap<String, ArrayList<String>>();

    for (int i = 0; i < selected.size(); i++) {

        HieroglyphenBlock block =
                selected.get(i);

        if (i > 0) {
            joinedExpression.append(".");
            joinedBlockLabel.append("_");
        }

        joinedExpression.append(
                block.getNumLabel()
        );

        joinedBlockLabel.append(
                block.getLabel()
        );

        for (GlyphElementNode glyph : block.getElements()) {

            ArrayList<Reading> readings =
                    new ArrayList<Reading>();

            if (glyph.getReadings() != null) {
                readings.addAll(glyph.getReadings());
            }

            elements.put(
                    cleanGlyphKey(glyph.getLabel()),
                    readings
            );
        }
    }

    HieroglyphenBlock joinedBlock =
            new HieroglyphenBlock(
                    newReading.getId(),
                    joinedExpression.toString(),
                    joinedBlockLabel.toString(),
                    true,
                    this
            );

    joinedBlock.createGraphStructure(
            joinedExpression.toString(),
            elements,
            choiceList
    );

    copyJoinedGlyphImageUris(
            joinedBlock,
            selected
    );

    rebuildJoinedBlockImage(
            joinedBlock,
            selected
    );

    return joinedBlock;
}

private void copyJoinedGlyphImageUris(
        HieroglyphenBlock joinedBlock,
        ArrayList<HieroglyphenBlock> selected
) {

    ArrayList<GlyphElementNode> sourceGlyphs =
            new ArrayList<GlyphElementNode>();

    for (HieroglyphenBlock block : selected) {
        sourceGlyphs.addAll(block.getElements());
    }

    ArrayList<GlyphElementNode> targetGlyphs =
            joinedBlock.getElements();

    int limit =
            Math.min(sourceGlyphs.size(), targetGlyphs.size());

    for (int i = 0; i < limit; i++) {

        String uri =
                getGlyphImageUri(sourceGlyphs.get(i));

        if (uri != null && !uri.isEmpty()) {
            setGlyphImageUri(targetGlyphs.get(i), uri);
        }
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

        if (value != null) {
            return value.toString();
        }
    } catch (Exception ex) {
        // older GlyphElementNode versions do not expose getImageUri()
    }

    try {
        java.lang.reflect.Field field =
                glyph.getClass().getDeclaredField("imageUri");

        field.setAccessible(true);

        Object value =
                field.get(glyph);

        if (value != null) {
            return value.toString();
        }
    } catch (Exception ex) {
        // no imageUri field
    }

    return "";
}

private void setGlyphImageUri(
        GlyphElementNode glyph,
        String uri
) {

    try {
        java.lang.reflect.Method method =
                glyph.getClass().getMethod(
                        "setImageUri",
                        String.class
                );

        method.invoke(glyph, uri);
        return;
    } catch (Exception ex) {
        // older GlyphElementNode versions do not expose setImageUri(String)
    }

    try {
        java.lang.reflect.Field field =
                glyph.getClass().getDeclaredField("imageUri");

        field.setAccessible(true);
        field.set(glyph, uri);
    } catch (Exception ex) {
        // no imageUri field
    }
}

private void createExplicitJoinedBundles(
        HieroglyphenBlock joinedBlock,
        ArrayList<HieroglyphenBlock> selected
) {

    ArrayList<ArrayList<ExplicitJoinBundle>> bundleGroups =
            new ArrayList<ArrayList<ExplicitJoinBundle>>();

    for (HieroglyphenBlock block : selected) {

        ArrayList<ExplicitJoinBundle> bundles =
                getExplicitJoinBundles(block);

        if (bundles.isEmpty()) {
            return;
        }

        bundleGroups.add(bundles);
    }

    ArrayList<ArrayList<ExplicitJoinBundle>> bundleCombinations =
            new ArrayList<ArrayList<ExplicitJoinBundle>>();

    buildExplicitBundleCombinations(
            bundleGroups,
            0,
            new ArrayList<ExplicitJoinBundle>(),
            bundleCombinations
    );

    int bundleVariantIndex = 0;

    for (ArrayList<ExplicitJoinBundle> bundleCombination
            : bundleCombinations) {

        createOneJoinedNT1NT2Bundle(
                joinedBlock,
                bundleCombination,
                bundleVariantIndex
        );

        bundleVariantIndex++;
    }
}

private void buildExplicitBundleCombinations(
        ArrayList<ArrayList<ExplicitJoinBundle>> groups,
        int groupIndex,
        ArrayList<ExplicitJoinBundle> current,
        ArrayList<ArrayList<ExplicitJoinBundle>> result
) {

    if (groupIndex == groups.size()) {

        result.add(
                new ArrayList<ExplicitJoinBundle>(current)
        );

        return;
    }

    ArrayList<ExplicitJoinBundle> group =
            groups.get(groupIndex);

    for (ExplicitJoinBundle bundle : group) {

        current.add(bundle);

        buildExplicitBundleCombinations(
                groups,
                groupIndex + 1,
                current,
                result
        );

        current.remove(current.size() - 1);
    }
}

private void createOneJoinedNT1NT2Bundle(
        HieroglyphenBlock joinedBlock,
        ArrayList<ExplicitJoinBundle> bundleCombination,
        int bundleVariantIndex
) {

    String baseId =
            joinedBlock.getBlockID();

    String nt1Id =
            baseId + ">NT1_" + bundleVariantIndex;

    String nt2Id =
            nt1Id + ">NT2_0";

    NumTranslit1 joinedNT1 =
            new NumTranslit1(
                    nt1Id,
                    this
            );

    NumTranslit2 joinedNT2 =
            new NumTranslit2(
                    nt2Id,
                    this,
                    "main"
            );

    ArrayList<NT1Element> joinedNT1Elements =
            new ArrayList<NT1Element>();

    ArrayList<NT2Element> joinedNT2Elements =
            new ArrayList<NT2Element>();

    copyJoinedNT1Elements(
            joinedBlock,
            bundleCombination,
            joinedNT1,
            joinedNT1Elements
    );

    copyJoinedNT2Elements(
            bundleCombination,
            joinedNT2,
            joinedNT1Elements,
            joinedNT2Elements
    );

    nt1.add(joinedNT1);

    mnt1.put(
            joinedNT1.getId(),
            Integer.valueOf(nt1.size() - 1)
    );

    joinedBlock.getNumTranslit1().add(
            joinedNT1.getId()
    );

    nt2.add(joinedNT2);

    mnt2.put(
            joinedNT2.getId(),
            Integer.valueOf(nt2.size() - 1)
    );

    joinedNT1.getNT2().add(
            joinedNT2.getId()
    );

    ArrayList<ArrayList<ExplicitGTVariant>> gtVariantGroups =
            new ArrayList<ArrayList<ExplicitGTVariant>>();

    for (ExplicitJoinBundle bundle : bundleCombination) {
        gtVariantGroups.add(bundle.gtVariants);
    }

    ArrayList<ArrayList<ExplicitGTVariant>> gtVariantCombinations =
            new ArrayList<ArrayList<ExplicitGTVariant>>();

    buildExplicitGTVariantCombinations(
            gtVariantGroups,
            0,
            new ArrayList<ExplicitGTVariant>(),
            gtVariantCombinations
    );

    int gtVariantIndex = 0;

    for (ArrayList<ExplicitGTVariant> gtCombination
            : gtVariantCombinations) {

        createOneJoinedGT1GT2Variant(
                joinedNT2,
                joinedNT2Elements,
                bundleCombination,
                gtCombination,
                gtVariantIndex
        );

        gtVariantIndex++;
    }
}

private void copyJoinedNT1Elements(
        HieroglyphenBlock joinedBlock,
        ArrayList<ExplicitJoinBundle> bundleCombination,
        NumTranslit1 joinedNT1,
        ArrayList<NT1Element> joinedNT1Elements
) {

    int glyphOffset = 0;

    for (ExplicitJoinBundle bundle : bundleCombination) {

        for (int i = 0; i < bundle.n1.getElements().size(); i++) {

            GlyphElementNode parentGlyph =
                    joinedBlock.getElements().get(glyphOffset);

            NT1Element oldElement =
                    bundle.n1.getElements().get(i);

            NT1Element newElement =
                    new NT1Element(parentGlyph);

            newElement.setLabel(
                    oldElement.getLabel()
            );

            newElement.setInitialReadings(
                    oldElement.getInitialReadings()
            );

            newElement.setSelectedReadings(
                    oldElement.getSelectedReadings()
            );

            joinedNT1.getElements().add(newElement);
            joinedNT1Elements.add(newElement);

            parentGlyph.getNT1Elements().add(newElement);

            glyphOffset++;
        }
    }
}

private void copyJoinedNT2Elements(
        ArrayList<ExplicitJoinBundle> bundleCombination,
        NumTranslit2 joinedNT2,
        ArrayList<NT1Element> joinedNT1Elements,
        ArrayList<NT2Element> joinedNT2Elements
) {

    int nt1Offset = 0;

    for (ExplicitJoinBundle bundle : bundleCombination) {

        for (int i = 0; i < bundle.n2.getElements().size(); i++) {

            NT2Element oldElement =
                    bundle.n2.getElements().get(i);

            NT1Element parentNT1 =
                    joinedNT1Elements.get(nt1Offset + i);

            NT2Element newElement =
                    new NT2Element(parentNT1);

            newElement.setLabel(
                    oldElement.getLabel()
            );

            if (oldElement.getComponentElement() != null) {
                newElement.setComplexGlyph(
                        oldElement.getComponentElement()
                );
            }

            joinedNT2.getElements().add(newElement);
            joinedNT2Elements.add(newElement);
        }

        nt1Offset += bundle.n2.getElements().size();
    }
}

private void buildExplicitGTVariantCombinations(
        ArrayList<ArrayList<ExplicitGTVariant>> groups,
        int groupIndex,
        ArrayList<ExplicitGTVariant> current,
        ArrayList<ArrayList<ExplicitGTVariant>> result
) {

    if (groupIndex == groups.size()) {

        result.add(
                new ArrayList<ExplicitGTVariant>(current)
        );

        return;
    }

    ArrayList<ExplicitGTVariant> group =
            groups.get(groupIndex);

    for (ExplicitGTVariant variant : group) {

        current.add(variant);

        buildExplicitGTVariantCombinations(
                groups,
                groupIndex + 1,
                current,
                result
        );

        current.remove(current.size() - 1);
    }
}

private void createOneJoinedGT1GT2Variant(
        NumTranslit2 joinedNT2,
        ArrayList<NT2Element> joinedNT2Elements,
        ArrayList<ExplicitJoinBundle> bundleCombination,
        ArrayList<ExplicitGTVariant> gtCombination,
        int gtVariantIndex
) {

    String gt1Id =
            joinedNT2.getId() + ">GT1_" + gtVariantIndex;

    String gt2Id =
            gt1Id + ">GT2_0";

    GraphTranslit1 joinedGT1 =
            new GraphTranslit1(
                    gt1Id,
                    this
            );

    GraphTranslit2 joinedGT2 =
            new GraphTranslit2(
                    gt2Id,
                    "main",
                    this
            );

    ArrayList<GT1Element> joinedGT1Elements =
            new ArrayList<GT1Element>();

    int nt2Offset = 0;

    for (int b = 0; b < bundleCombination.size(); b++) {

        ExplicitJoinBundle bundle =
                bundleCombination.get(b);

        ExplicitGTVariant variant =
                gtCombination.get(b);

        GraphTranslit1 sourceGT1 =
                variant.g1;

        for (int i = 0; i < sourceGT1.getElements().size(); i++) {

            GT1Element oldElement =
                    sourceGT1.getElements().get(i);

            NT2Element parentNT2 =
                    joinedNT2Elements.get(nt2Offset + i);

            Reading reading =
                    new Reading(
                            oldElement.getSimpleLabel()
                    );

            GT1Element newElement =
                    new GT1Element(
                            parentNT2,
                            reading
                    );

            newElement.setLabel(
                    oldElement.getLabel()
            );

            joinedGT1.getElements().add(newElement);
            joinedGT1Elements.add(newElement);
        }

        nt2Offset += bundle.n2.getElements().size();
    }

    copyJoinedGT2Elements(
            bundleCombination,
            gtCombination,
            joinedGT2,
            joinedGT1Elements
    );

    gt1.add(joinedGT1);

    mgt1.put(
            joinedGT1.getId(),
            Integer.valueOf(gt1.size() - 1)
    );

    joinedNT2.getGT1().add(
            joinedGT1.getId()
    );

    gt2.add(joinedGT2);

    mgt2.put(
            joinedGT2.getId(),
            Integer.valueOf(gt2.size() - 1)
    );

    joinedGT1.getGraphTranslit2().add(
            joinedGT2.getId()
    );
}

private void copyJoinedGT2Elements(
        ArrayList<ExplicitJoinBundle> bundleCombination,
        ArrayList<ExplicitGTVariant> gtCombination,
        GraphTranslit2 joinedGT2,
        ArrayList<GT1Element> joinedGT1Elements
) {

    int gt1Offset = 0;
    GraphGT2Node previousJoinedNode = null;

    for (int b = 0; b < bundleCombination.size(); b++) {

        ExplicitGTVariant variant = gtCombination.get(b);
        GraphTranslit1 sourceGT1 = variant.g1;
        GraphTranslit2 sourceGT2 = variant.g2;

        ArrayList<GraphGT2Node> sourceNodes = sourceGT2.getElements();
        GraphGT2Node previousSourceNode = null;

        for (GraphGT2Node oldNode : sourceNodes) {

            GT2Element oldElement = oldNode.getGT2Element();

            int localParentIndex = sourceGT1.getElements().indexOf(
                    oldElement.getParentGT1()
            );

            if (localParentIndex < 0) {
                localParentIndex = 0;
            }

            GT1Element parentGT1 = joinedGT1Elements.get(
                    gt1Offset + localParentIndex
            );

            GT2Element newElement = new GT2Element(parentGT1);
            newElement.setLabel(oldElement.getLabel());

            GraphGT2Node newNode = new GraphGT2Node(newElement, null);

            joinedGT2.getElements().add(newNode);
            joinedGT2.getGraphGT2().addVertex(newNode);

            if (previousJoinedNode != null) {
                OperatorLink link = null;

                if (previousSourceNode != null) {
                    link = sourceGT2.getGraphGT2().findEdge(
                            previousSourceNode,
                            oldNode
                    );
                }

                if (link == null) {
                    link = new OperatorLink(" ", 5);
                } else {
                    link = link.copy();
                }

                joinedGT2.getGraphGT2().addEdge(
                        link,
                        previousJoinedNode,
                        newNode
                );
            }

            previousJoinedNode = newNode;
            previousSourceNode = oldNode;
        }

        gt1Offset += sourceGT1.getElements().size();
    }

    joinedGT2.calculateLabel();
}

private void rebuildJoinedBlockImage(
		        HieroglyphenBlock target,
		        ArrayList<HieroglyphenBlock> blocks
		) {

		    int width = 0;
		    int height = 0;

		    for (int i = 0; i < blocks.size(); i++) {

		        BufferedImage img =
		                blocks.get(i).getImage();

		        if (img != null) {
		            width += img.getWidth();
		            height =
		                    Math.max(height, img.getHeight());
		        }

		        if (i > 0) {
		            width += 20;
		        }
		    }

		    if (width <= 0 || height <= 0) {
		        return;
		    }

		    BufferedImage joined =
		            new BufferedImage(
		                    width,
		                    height,
		                    BufferedImage.TYPE_INT_ARGB
		            );

		    Graphics2D g =
		            joined.createGraphics();

		    int x = 0;

		    for (int i = 0; i < blocks.size(); i++) {

		        BufferedImage img =
		                blocks.get(i).getImage();

		        if (i > 0) {
		            g.drawString(".", x + 5, height / 2);
		            x += 20;
		        }

		        if (img != null) {
		            g.drawImage(img, x, 0, null);
		            x += img.getWidth();
		        }
		    }

		    g.dispose();

		    setPrivateField(
		            target,
		            "img",
		            joined
		    );
		}
	  //
	  private void setPrivateField(
		        Object target,
		        String fieldName,
		        Object value
		) {

		    try {

		        Class<?> c =
		                target.getClass();

		        while (c != null) {

		            try {

		                java.lang.reflect.Field f =
		                        c.getDeclaredField(fieldName);

		                f.setAccessible(true);
		                f.set(target, value);
		                return;

		            } catch (NoSuchFieldException ex) {
		                c = c.getSuperclass();
		            }
		        }

		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}
	  //
	  public  ODatabaseSession  getDatabase() {
		  return db;
	  }

	  class VertexFactory implements Supplier<GraphGT2Node>{

		    @Override
		    public GraphGT2Node get() {
		     
		       return new GraphGT2Node();
		    }
		}
	 class EdgeFactory implements Supplier<OperatorLink> {


		    @Override
		    public OperatorLink get() {
		      OperatorLink o =new OperatorLink("NEW",5);
		       return o;
		    }
		}

}