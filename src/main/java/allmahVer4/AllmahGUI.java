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
import java.awt.BasicStroke;
import edu.mit.jwi.*;
import edu.mit.jwi.item.*;
import java.net.URL;
import java.awt.BorderLayout;
import java.awt.Color;
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
	private ArrayList<ArrayList<GlossingVariant>> gset;
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
	    setSize(screenSize.width, screenSize.height);
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
	          session=new JMenuItem("Configure Session ID");       
	         session.setFont(typFont1);
	         session.addActionListener(this);
	         dateien.add(session);
	          dateien.add(configBlockAnn);
		 return dateien;
	 }
	
	 public JMenu createFileMenu(){
	 	   JMenu dateien=new JMenu ("File");
	 	  dateien.setFont(typFont1);
	 	 dateien.setMnemonic('F');
	      JMenu openFile=new JMenu("Open");
	                      openFile.setMnemonic('O');
	                      openFile.setFont(typFont1);
	     saveFile=new JMenuItem("Save");
	                      saveFile.setMnemonic('S');
	                     saveFile.setFont(typFont1);
	         openNewLocalFile=new JMenuItem ("Open new local file");
	         openNewLocalFile.setFont(typFont1);
	          openNewLocalFile.addActionListener(this);
	          openNewLocalFile.setEnabled(false);
	          openFile.add(openNewLocalFile);
	          openNewGridFile=new JMenuItem ("Open new grid file");
		         openNewGridFile.setFont(typFont1);
		          openNewGridFile.addActionListener(this);
		          openNewGridFile.setEnabled(false);
		          openFile.add(openNewGridFile);
		          openAnnotatedFile=new JMenuItem ("Open DB file");
			         openAnnotatedFile.setFont(typFont1);
			          openAnnotatedFile.addActionListener(this);
			          openAnnotatedFile.setEnabled(false);
			          openFile.add(openAnnotatedFile);
			         saveFile.addActionListener(this);
			         saveFile.setEnabled(false);
			          dateien.add(openFile);
			          dateien.add(saveFile);
	 	   return dateien;
	 }	   
	 public void actionPerformed(ActionEvent event){
	 		
	 		String e=event.getActionCommand();
	 		if(e.equals("Configure Session ID")) {
	 			 sessionID = JOptionPane.showInputDialog("Insert Session ID");
	 			 
		       
		          if (sessionID != null) {
		            sid = sessionID.trim();
		            System.out.println("Session ID"+sid);
		            openNewLocalFile.setEnabled(true);
		            openNewGridFile.setEnabled(true);
		            openAnnotatedFile.setEnabled(true);
		          }
		          else
		            return;
	 		}
	 		if(e.equals("Configure Onomastics Annotations")) {
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
	 		else if (e.equals("Open DB file")){
	 			docr=new ArrayList<DocumentReading>();
	 			gt1=new ArrayList<GraphTranslit1>();
	 			gt2=new ArrayList<GraphTranslit2>();
	 			pt=new ArrayList<PhonemTranslit>();
	 			mt=new ArrayList<MorphoSyntTranslit>();
	 			mtr=new ArrayList<FinalTranslation>();
	 			mg=new ArrayList<MorphoSyntGlossing>();
	 			nt2=new ArrayList<NumTranslit2>();
	 			nt1=new ArrayList<NumTranslit1>();
	 			saveFile.setEnabled(true);
	 			
	 		 OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
	 	       // OrientDB orient = new OrientDB("remote:classicmayan.org", OrientDBConfig.defaultConfig());
	 	               db = orient.open("allmahDB", "admin", "admin");
	 	            //   db = orient.open("classicmayan", "functionUser", "id!oM{4}Tg/4");
	 	               System.out.println("Opended "+db.getName());
	 	              String stm = "SELECT * FROM HyerogliphenDocument ";
	 	     		OResultSet  rs = db.query(stm);       
	 	     		ChildFrame  dbfile= new ChildFrame("Select Files from DB " ,Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
					Container cfin=dbfile.getContentPane();
					cfin.setLayout(new GridBagLayout());
					 GridBagConstraints gbc=new GridBagConstraints();
		 	    		gbc.gridx=0;
		 	    		gbc.gridy=0;
		 	    		gbc.weightx=1.0;
		 	    		gbc.weighty=1.0;
		 	    		gbc.fill=GridBagConstraints.BOTH;
		 	    		gbc.anchor=GridBagConstraints.NORTHWEST;
		 	    		gbc.gridwidth=2;
		 	    		gbc.ipadx=5; gbc.ipady=5;
					JPanel pane=new JPanel();
					pane.setLayout(new GridLayout(0,1));
					JButton selB=new JButton("Select");
					JButton disB=new JButton("Dismiss");
					selD="";
	 	     	while (rs.hasNext()) {
	 	         		System.out.println("***");
		 				OElement doc=rs.next().toElement();	
		 				
		 				System.out.println(doc.getProperty("id").toString());
		 				ButtonGroup grp=new ButtonGroup();
						JRadioButton fn=new JRadioButton(doc.getProperty("id").toString());
						 fn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(fn.isSelected()) {
									selD=fn.getText();
						            System.out.println("Selected "+selD);
						            db.close();
					
								}
								
							}
						});
						fn.setSelected(false);
						grp.add(fn);
						 pane.add(fn);		 				
	 	         	}
	 	     	selB.addActionListener(new ActionListener(){
	 	     		public void actionPerformed(ActionEvent ex) {
	 	     			db = orient.open("allmahDB", "admin", "admin");
	 	     			String stm = "SELECT FROM HyerogliphenDocument WHERE id = ? ";
	 	     			OResultSet  rs = db.query(stm,selD );
	 	     			if(rs.hasNext()) {
	 	     				OElement el=rs.next().toElement();
	 	     			     doc = new HyerogliphenDocument(el);
	 	     			     List<OElement> listRead=el.getProperty("hasDocLesung");		
	 	     			     for(int i=0;i<listRead.size();i++) {
	 	     			    	 System.out.println("REading id "+ listRead.get(i).getProperty("id"));
	 	     			    	DocumentReading dr=new DocumentReading(listRead.get(i),getInterf());
	 	     			    	docr.add(dr);
	 	     			    	mdocr.put( listRead.get(i).getProperty("id"),new Integer((docr.size()-1)));
	 	     			     }
	 	     			 db.close();    
	 	     			dbfile.doDefaultCloseAction();
	 	     		}
	 	     		}
	 	     		
	 	     	});
	 	     	disB.addActionListener(new ActionListener(){
	 	     		public void actionPerformed(ActionEvent ex) {
	 	     			db.close();
	 	     			dbfile.doDefaultCloseAction();
	 	     		}
	 	     		
	 	     	});
	 	     	
	 	     	cfin.add(new JScrollPane(pane),gbc);
	 	     	gbc.fill=GridBagConstraints.NONE;
	 	     	gbc.anchor=GridBagConstraints.CENTER;
	 	     	gbc.gridy=1; gbc.gridwidth=1;
	 	     	cfin.add(selB,gbc);
	 	     	gbc.gridx=1; cfin.add(disB,gbc);
	 	     	dbfile.pack(); 
				dbfile.moveToFront();
				this.addChild(dbfile,20,20,500,300);
	 	     	
	 	     	//this.doc=new HyerogliphenDocument(doc);
	 		 		   
	 		}
	 		else if (e.equals("Open new local file")){
	 			LocalReadFile lrf=new LocalReadFile(this);
	 			
	 			MayaDocumentParser parser=new MayaDocumentParser(this,sid,lrf.getChoosedFile(),typFont1);
	 			parser.getMayaDocumentTitle();
	 			hb=parser.getMayaDocumentParts();
	 			docr=new ArrayList<DocumentReading>();
	 			gt1=new ArrayList<GraphTranslit1>();
	 			gt2=new ArrayList<GraphTranslit2>();
	 			pt=new ArrayList<PhonemTranslit>();
	 			mt=new ArrayList<MorphoSyntTranslit>();
	 			mtr=new ArrayList<FinalTranslation>();
	 			mg=new ArrayList<MorphoSyntGlossing>();
	 			nt2=new ArrayList<NumTranslit2>();
	 			nt1=new ArrayList<NumTranslit1>();
	 			saveFile.setEnabled(true);
	 			doc=new HyerogliphenDocument(parser.docID,this);
	 			
	 			DocumentReading dr=new DocumentReading(doc.getId(),this);
	 			
	 			docr.add(dr);mdocr.put(dr.getId(),new Integer(0));
	 			doc.getDocReadings().add(dr.getId());
	 			for (int i=0;i<hb.size();i++) {
	 				mhb.put(hb.get(i).getBlockID(),new Integer(i));
	 				if (!(hb.get(i).getLabel()).equals("#")) hb.get(i).generateNT1();
	 				dr.getBlocks().add(hb.get(i).getBlockID());
	 			}	
	 			
	 			
	 			ChildFrame  mainwin= new ChildFrame("Annotate "+ parser.getMayaDocumentTitle() ,Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
				Container cfin=mainwin.getContentPane();
				 blockTree=builtAnnTree();
			sc =new JScrollPane(blockTree);
				
				cfin.add(sc);
				mainwin.pack();
				mainwin.moveToFront();
				this.addChild(mainwin,20,20,desk.getWidth()-400,desk.getHeight()-100);
				saveFile.setEnabled(true);
	 		}
	 		else if (e.equals("Open new grid file")){
	 			
	 			TextGridReadFile tgr=new TextGridReadFile(sid);
	 			Map<String,String> nameFiles=tgr.fileList();
	 			 int noFiles=nameFiles.size();
	 		      //System.out.println("No of fies "+noFiles);
	 		       final  JEditorPane editorPane=new JEditorPane();
	 		      editorPane.setEditorKit(new XMLEditorKit());
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
	 		            		  IDIOMTextGridObject   textGridObject = new IDIOMTextGridObject();
	 		            		// IDIOMTextGridObject   textGridObject = new IDIOMTextGridObject();
	 		            			
	 		            	 System.out.println("URI "+textGridObject.generateTextGridUri());
	 		            		       selectedDocument= textGridObject.getDataAsString(docs[x]);
	 		            		       selectedDoc=textGridObject.getDataAsXMLObject(docs[x]);
	 		            		     System.out.println(selectedDocument);
	 		            		       editorPane.setEditorKit(new XMLEditorKit());
	 		            			   editorPane.setText(selectedDocument); editorPane.revalidate();editorPane.repaint();
	 		            		        c.repaint();c.revalidate();
	 		            		        
	 		                	        }
	 		                	        catch(IOException e1) {System.out.println("File not found");}
	 		                	        catch(CrudClientException e2) {System.out.println("CRUD Error");}
	 		            		  catch(Exception e3) {System.out.println("SAX Parser Error");}
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
	 		 	    		//MayaDocumentParser(AllmahGUI interf,org.jdom2.Document doc, String docId,Font ft)
	 		 	   		fileFrame.doDefaultCloseAction();
 		 	    		AllmahGUI interf=getInterf();
	 		 	    		MayaDocumentParser parser=new MayaDocumentParser(interf,sid,(org.jdom2.Document) selectedDoc, selName,typFont1);
	 			 			parser.getMayaDocumentTitle();
	 			 			hb=parser.getMayaDocumentParts();
	 			 			docr=new ArrayList<DocumentReading>();
	 			 			gt1=new ArrayList<GraphTranslit1>();
	 			 			gt2=new ArrayList<GraphTranslit2>();
	 			 			pt=new ArrayList<PhonemTranslit>();
	 			 			mt=new ArrayList<MorphoSyntTranslit>();
	 			 			nt2=new ArrayList<NumTranslit2>();
	 			 			nt1=new ArrayList<NumTranslit1>();
	 			 			saveFile.setEnabled(true);
	 			 			doc=new HyerogliphenDocument(parser.docID,interf);
	 			 			
	 			 			DocumentReading dr=new DocumentReading(doc.getId(),interf);
	 			 			
	 			 			docr.add(dr);mdocr.put(dr.getId(),new Integer(0));
	 			 			doc.getDocReadings().add(dr.getId());
	 			 			for (int i=0;i<hb.size();i++) {
	 			 				mhb.put(hb.get(i).getBlockID(),new Integer(i));
	 			 				if (!(hb.get(i).getLabel()).equals("#")) hb.get(i).generateNT1();
	 			 				dr.getBlocks().add(hb.get(i).getBlockID());
	 			 			}	
	 			 			
	 			 			
	 			 			ChildFrame  mainwin= new ChildFrame("Annotate "+ parser.getMayaDocumentTitle() ,Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
	 						Container cfin=mainwin.getContentPane();
	 						 blockTree=builtAnnTree();
	 					sc =new JScrollPane(blockTree);
	 						
	 						cfin.add(sc);
	 						mainwin.pack();
	 						mainwin.moveToFront();
	 						interf.addChild(mainwin,20,20,desk.getWidth()-400,desk.getHeight()-100);	 	  	
	 		 	    			 	    		
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
	 		else if (e.equals("Save")){
	 		//	OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
	 		   // ODatabaseSession db = orient.open("almah", "admin", "admin");
	 			System.out.println("Save");
        OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
       // OrientDB orient = new OrientDB("remote:classicmayan.org", OrientDBConfig.defaultConfig());
               db = orient.open("allmahDB", "admin", "admin");
            //   db = orient.open("classicmayan", "functionUser", "id!oM{4}Tg/4");
               System.out.println("Opended "+db.getName());
               doc.insertInDB();
	 		    db.close();    
	 		//    orient.close();
	 		}
	 }
	private void mainWin_componentResized(ComponentEvent e)  
	{  
	//System.out.println("\nmainWin_componentResized(ComponentEvent e) called.");  
	// TODO: Add any handling code here  
	   
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
                     	    if( r.size()>1) return Color.YELLOW;
                     	   else {
                     		   if(r.get(0).getTyp().equals("no")) return Color.LIGHT_GRAY;
                     		   else return Color.WHITE;
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
 	            	if(clicked.equals("class allmahVer4.GraphNT1Node")) {
 	                     if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() ==1) {
 	                            
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
 	                    		   check[i].setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;<font size=+1><b>Reading:</b> "+r.get(i).getReading() +"<br/>&nbsp;&nbsp;&nbsp;&nbsp;<b>Confidence:</b> "+r.get(i).getConfidence()+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;<b>Type:</b>"+r.get(i).getTyp()+"</font></html>");
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
             		                    	for (int i=0;i<check.length; i++) {
             		                    		if(check[i].isSelected()) {
             		                    		//	nt1.getSelectedReadings().add(r.get(i));
             		                    			
             		                    			raux.add(r.get(i).copy());
             		                    			System.out.println("Added "+r.get(i).getReading());
             		                               
             		                    			
             		                    		}
             		                    		else r.get(i).setState(false);
             		                    	}
             		                    		
             		                    	selFrame.doDefaultCloseAction(); nt1.setSelectedReadings(raux);
             		                  
             		            //      (DefaultTreeModel))blockTree.getModel().
             		                 
             		                     }
             		            }
             		        });
             		   
 	                    	
                   		  selFrame.getContentPane().add(new JScrollPane(optionsPanel));
                   		 selFrame.getContentPane().add(select,BorderLayout.SOUTH);
                 		   selFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                 		   selFrame.pack();
                		   addChild(selFrame,1350, 10, 300, 300);		
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
            
			return new DefaultTreeModel(root);
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
				//	 ((DefaultTreeModel)blockTree.getModel()).reload();
					// expandTree(blockTree,true);
				}
		      public void treeNodesChanged(TreeModelEvent e) {
		     blockTree.repaint();
		    		// ((DefaultTreeModel)blockTree.getModel()).reload();
		    	//  expandTree(blockTree,true);
				}
		      public void treeNodesInserted(TreeModelEvent e) {
		    	  ((DefaultTreeModel)blockTree.getModel()).reload();
				}
		      public void treeNodesRemoved(TreeModelEvent e) {
		    	 
		    	  ((DefaultTreeModel)blockTree.getModel()).reload();
		    	  
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
		            TreePath[] paths = tree.getSelectionPaths();
                
	                for (TreePath path1 : paths) {
	                    System.out.println("You've selected: " + path1.getLastPathComponent());
	                
	                }
	                System.out.println("You've selected: " + paths.length+ " nodes");
		            if (path== null)
		                return; 
                 
		           tree.setSelectionPath(path);
                //   if (tree.isSelectionEmpty()) {
                	//    tree.setSelectionPath(path);
                	//}
                   JPopupMenu popup= new JPopupMenu();
                   if(paths.length>1) {
                	   int i=0; 
                	   boolean rightsel=true;
                	
                	   while((i<paths.length)&&rightsel) {
                		  AnnotationNode obj = ( AnnotationNode)paths[i].getLastPathComponent();
                		   String label= ""+obj.getMyTreeNode().getClass();
                		   if (!(label.equals("class allmahVer4.HieroglyphenBlock")))rightsel=false;
                		   i=i+1;
                	   }
                	   System.out.println("Selected "+path.getLastPathComponent()+ " nodes");
                	   if(rightsel) {
                		   final  JMenu menuAssign=new JMenu("Assign URI"); 
                		  
   	                    	 ArrayList<String> selection1=new ArrayList<String>();
   	                    	
   	                    	for(int j=0;j<paths.length;j++) {
   	                    	AnnotationNode obj = ( AnnotationNode)paths[j].getLastPathComponent();
   	                    	if (j==0) p1=blockTree.getPathBounds(paths[j]).getLocation();
   	                    	if(j==paths.length-1) p2=blockTree.getPathBounds(paths[j]).getLocation();
   	                    
                  			 HieroglyphenBlock currentNode= (HieroglyphenBlock)obj.getMyTreeNode();
                  			 selection1.add(currentNode.getId());        			
   	                    	}
   	                    	
   	                    	System.out.println("Selection1 "+ selection1);
   	                    	ArrayList<BlockAnnotation> sela=new ArrayList<BlockAnnotation>();
   	                    	ArrayList<JMenuItem> selamenu=new ArrayList<JMenuItem>();
   	                    	for(int k=0;k<blockAnn.size();k++){
   	                    		System.out.println("Compare with "+ blockAnn.get(k).getAnnBorders());
   	                    		if(compareBorders(selection1,blockAnn.get(k).getAnnBorders())) {
   	                    			sela.add(blockAnn.get(k));
   	                    			JMenuItem me=new JMenuItem(blockAnn.get(k).getTyp()+" "+blockAnn.get(k).getSubTyp());
   	                    			selamenu.add(me);
   	                    			menuAssign.add(me);
   	                    		   //
   	                    		 me.addActionListener(new ActionListener() {
   	     	                    	public void actionPerformed(ActionEvent e) {
   	     	                    	ChildFrame  annuri= new ChildFrame("Assign URI  " ,Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
   	     	                    	JTextField searchField=new JTextField(20);
   	     	                    	JTextPane resultsArea=new JTextPane();
   	     	                    	JPanel p1=new JPanel(new GridLayout(2,1));
   	     	                    	p1.add(searchField);p1.add(new JScrollPane(resultsArea));
   	     	                    	JButton search=new JButton("Search");
   	     	                   search.addActionListener(new ActionListener() {
   	     	                    	public void actionPerformed(ActionEvent e) {
   	     	                    		String s=searchField.getText();
   	     	                    		System.out.println(s);
   	     	                    	TripleStoreQuery query1=new TripleStoreQuery();
   	     	                    String ac="Activities+\n";
   	     	                    	try {
   	     	                        HashMap<String,String> activ=query1.searchInActivities(s);
   	     	                    Collection<String> c = activ.values();   
   	   	     	               // parse the collection
   	   	     	               Iterator<String> it = c.iterator();
   	   	     	               while(it.hasNext())
   	   	     	                ac= ac+it.next()+"\n";
   	     	                    	}
   	     	                    	catch(Exception ex) {ac=ac +"Nothing found\n";};
   	     	                    String ded="Dedications\n";
   	     	                       try {
   	     	                          HashMap<String,String> dedication=query1.searchInDedication(s);
   	     	                      Collection<String> c = dedication.values();   
   	     	               // parse the collection
   	     	               Iterator<String> it = c.iterator();
   	     	               while(it.hasNext())
   	     	                ded = ded+it.next()+"\n";
   	     	               
 	     	                    //   for(int i=0;i<dedication.size();i++) ded=ded+dedication.get(i)+"\n";
   	     	                       }
   	     	                   catch(Exception ex) {ded=ded +"Nothing found\n";};
 	     	                        System.out.println("Found " + ac+ded);
 	     	                        resultsArea.setText(ac+ded);

   	     	                    	}
   	     	                    });
   	     	                    	JButton assign=new JButton("Assign");
   	     	                    	JPanel p2=new JPanel(); p2.add(search);p2.add(assign);
   	     	                    	annuri.getContentPane().add(p1);
   	     	                    	annuri.getContentPane().add(p2, BorderLayout.SOUTH);
   	     	                    annuri.pack();
            	 				annuri.moveToFront();
            	 				addChild(annuri,200, 10, 300, 500);
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
	            						for(int i=0;i<paths.length;i++) {
	   	                    			 AnnotationNode obj = ( AnnotationNode)paths[i].getLastPathComponent();
	   	                    			 HieroglyphenBlock currentNode= (HieroglyphenBlock)obj.getMyTreeNode();
	   	                    			// currentNode.setCalender("Lunar Series");
	   	                    			 ba.getAnnBorders().add(currentNode.getId());
	   	                    			 currentNode.getAnnotations().add(ba.getId());
	   	                    			  ((DefaultTreeModel)blockTree.getModel()).reload();
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
	            						for(int i=0;i<paths.length;i++) {
	   	                    			 AnnotationNode obj = ( AnnotationNode)paths[i].getLastPathComponent();
	   	                    			 HieroglyphenBlock currentNode= (HieroglyphenBlock)obj.getMyTreeNode();
	   	                    			// currentNode.setCalender("Lunar Series");
	   	                    			 ba.getAnnBorders().add(currentNode.getId());
	   	                    			 currentNode.getAnnotations().add(ba.getId());
	   	                    			
	   	                    			  ((DefaultTreeModel)blockTree.getModel()).reload();
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
	            						for(int i=0;i<paths.length;i++) {
	   	                    			 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
	   	                    			 HieroglyphenBlock currentNode= (HieroglyphenBlock)obj.getMyTreeNode();
	   	                    			// currentNode.setCalender("Lunar Series");
	   	                    			 ba.getAnnBorders().add(currentNode.getId());
	   	                    			 currentNode.getAnnotations().add(ba.getId());
	   	                    			  ((DefaultTreeModel)blockTree.getModel()).reload();
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
	            						for(int i=0;i<paths.length;i++) {
	   	                    			 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
	   	                    			 HieroglyphenBlock currentNode= (HieroglyphenBlock)obj.getMyTreeNode();
	   	                    			// currentNode.setCalender("Lunar Series");
	   	                    			 ba.getAnnBorders().add(currentNode.getId());
	   	                    			 currentNode.getAnnotations().add(ba.getId());
	   	                    			  ((DefaultTreeModel)blockTree.getModel()).reload();
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
		                
		                  final  JMenu menuCertain=new JMenu("Certainity");
		                 
                                final  JMenuItem certainLevel[]=new JMenuItem[8];
		              
		                 for (int i1=0;i1<8;i1++) {
		                	 final int j=i1+1;
		                	               certainLevel[i1]=new JMenuItem(""+(i1+1));
		                	 certainLevel[i1].addActionListener(new ActionListener() {
			                    	public void actionPerformed(ActionEvent e) {
			                    		
			                    		 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
				                    		GraphTranslit1 currentNode= (GraphTranslit1)obj.getMyTreeNode();
				                    		currentNode.setPathImage(SpecialSymbols.pathAuswertung+"GT1_"+j+".png");
				                    		((DefaultTreeModel)blockTree.getModel()).reload(obj);
			                    	}
		                	 });
			                  menuCertain.add(certainLevel[i1]);  	
		                 }
		                 
		                   popup.add(menuCertain);
		                 
		       
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
                			  JTextField mayaText=new JTextField(20);
                			  JTextField translation=new JTextField(20);
                			  JCheckBox inDictionary =new JCheckBox("Save in Dictionary");
                			  JButton valid=new JButton("Validate");
                			  JPanel panelMUnit=new JPanel(new GridLayout(4,0));
                			  panelMUnit.add(inDictionary);panelMUnit.add(mayaText);panelMUnit.add(translation);
                			  panelMUnit.add(valid);
		                    		  ArrayList<GlossingVariant>  currentEl= currentNode.getElements();
		                    		JTextArea konsolidTR= new JTextArea();
		                    		  JTextArea finalTr=new JTextArea();
		                    		content1.setLayout(new GridBagLayout());
		                    		JPanel initCompon=new JPanel(new GridLayout(0,currentEl.size()));
		                    		GridBagConstraints g2=new GridBagConstraints();
		                    		g2.gridx=0;
		        			    	g2.gridy=0;
		        			    	g2.weightx=1.0;
		        			    	g2.weighty=1.0;
		        			    	g2.anchor=GridBagConstraints.CENTER;
		        			    	g2.fill=GridBagConstraints.BOTH;
		        			    	g2.insets.top=5;
		        			    	g2.insets.bottom=5;
		        			    	g2.insets.left=5;
		        			    	g2.insets.right=5;
		        			    	g2.gridwidth=2;
		        			    	TranslationPanel[] trp=new TranslationPanel[currentEl.size()];
		        			    for (int i=0;i<currentEl.size();i++) {
		        			    	//g2.gridx=i;
		        			    	 trp[i]=new TranslationPanel(currentEl.get(i),dict, konsolidTR,finalTr);
		        			    	initCompon.add(trp[i].getPanelTr());
		        			    }
		        			   content1.add(initCompon,g2);
		        			   g2.gridy=1; g2.gridwidth=1; g2.gridx=0;
		        			   content1.add(generateMU,g2); g2.gridx=1; content1.add(panelMUnit,g2);
		        			   
		        			   generateMU.addActionListener(new ActionListener() {
		        				   public void actionPerformed(ActionEvent e) {
		        					   String s1=""; String s2="";
		        					   for (int i=0;i<trp.length;i++) {
		        						   if(trp[i].getPartMUState()) {
		        							   s1=s1+trp[i].getOrig();
		        							   s2=s2+" "+trp[i].getTranslation();
		        						   }
		        						   mayaText.setText(s1);
		        						   translation.setText(s2);
		        					   }
		        				   }
		        			   });
		        			   
		        			   valid.addActionListener(new ActionListener() {
		        				   public void actionPerformed(ActionEvent e) {
		        					   String s1=konsolidTR.getText(); String s2=finalTr.getText();
		        					  konsolidTR.setText(s1+" "+mayaText.getText());
		        					  finalTr.setText(s2+" "+translation.getText());
		        					  ArrayList<GlossingVariant> parents=new ArrayList<GlossingVariant>();
		        					  for(int i=0;i<trp.length;i++) {
		        						  if(trp[i].getPartMUState()) parents.add(currentEl.get(i));
		        						  trp[i].setPartMU(false);
		        					  }
		        					  translation.setText("");
		        					  mayaText.setText("");
		        				   }
		        			   });
		        				g2.gridy=2;g2.gridx=0;
		        				g2.gridwidth=2;
		        				content1.add(new JScrollPane(konsolidTR),g2);
		        			   	g2.fill=GridBagConstraints.BOTH;
		        			    g2.gridx=0;g2.gridy=3;
		        			    content1.add(new JScrollPane(finalTr),g2);
		        			    JButton assign=new JButton("Assign");
		        			    assign.addActionListener(new ActionListener() {
		        			    	public void actionPerformed(ActionEvent e) {
		        			    		
		        			    		for(int i=0;i<maya.size();i++) {
		        			    			//maya.get(i).setTranslation();
		        			    		}
		        			    	
		        			    	FinalTranslation ftr=new FinalTranslation(currentNode.getId()+">"+"TR_0",konsolidTR.getText(),finalTr.getText());
            					                      mtr.add(ftr);
        		            	                       mmtr.put(ftr.getId(),new Integer(mtr.size()-1));
        		            	                       AnnotationNode obj1=new AnnotationNode(ftr),node;
					                    		        obj2.add(obj1);
					                    		        ((DefaultTreeModel)blockTree.getModel()).reload();
				                     					expandAll(blockTree,path,true);
				                    				translationFrame.doDefaultCloseAction();
		        			    	}
		        			    });
		        			    g2.fill=GridBagConstraints.HORIZONTAL;
		        			    g2.gridy=4;
		        			    content1.add(assign,g2);
		        			    translationFrame.pack();
	                     		   addChild(translationFrame,800, 10, 800, 700);		
	                     		   translationFrame.setVisible(true); 
	                     		   translationFrame.moveToFront(); 
		                    		  
	                    	}
	                    });
	                	popup.add(addTr);
		            }
		            else if(label.equals("class allmahVer4.MorphoSyntTranslit")) {
		            	System.out.println("Morpho Synt");
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
			                    				if(isvalid){
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
			                    					glossArray=new ElemSyntaxPanel[msg.size()];
						                    		for(int i=0;i<msg.size();i++) {
						                    			glossArray[i]=new ElemSyntaxPanel(msg.get(i),trpos, getInterf(), morphoGlossFrame);
						                    			
						                    			System.out.println("Glossing has "+msg.get(i).getElements().size()+ " elem");
						                    			g2.gridx=i;
						                    			content2.add(glossArray[i].getPanel(),0,i);
						                    		}
						                    		
						                    		content1.add(content2,BorderLayout.CENTER);
						                    		JButton assignS=new JButton("Assign Glossing");
						                    		content1.add(assignS,BorderLayout.SOUTH);
						                    		 nrg=-1;
						                    		
						                    		assignS.addActionListener(new ActionListener() {
						                    			public void actionPerformed(ActionEvent e) {
						                    		//	for(int i=0;i<msg.size();i++) {
						                    					for(int j=0;j<glossArray.length; j++) {
						                    						if(glossArray[j].getMorphoGlossing()!=null) {
						                    						for (int k=0;k<glossArray[j].getMorphoGlossing().size();k++) {
						                    							nrg=nrg+1;
						                    							glossArray[j].getMorphoGlossing().get(k).setId(id+">"+"MSG_"+nrg);
						                    					                      mg.add(glossArray[j].getMorphoGlossing().get(k));
					                    		            	                       mmg.put(glossArray[j].getMorphoGlossing().get(k).getId(),new Integer(mg.size()-1));
					                    		            	                       AnnotationNode obj1=new AnnotationNode(glossArray[j].getMorphoGlossing().get(k)),node;
					   							                    		        obj2.add(obj1);
						                    						}
						                    						}
						                    					}
						                    			//	}
						                    				
							                    		        ((DefaultTreeModel)blockTree.getModel()).reload();
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
		          	popup.add(addMorphoGloss);
		            }
		            else if(label.equals("class allmahVer4.PhonemTranslit")) {
		            	 final  JMenu menuCertain=new JMenu("Certainity");
		                 
                         final  JMenuItem certainLevel[]=new JMenuItem[8];
	              
	                 for (int i1=0;i1<8;i1++) {
	                	 final int j=i1+1;
	                	               certainLevel[i1]=new JMenuItem(""+(i1+1));
	                	 certainLevel[i1].addActionListener(new ActionListener() {
		                    	public void actionPerformed(ActionEvent e) {
		                    		
		                    		 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
			                    		PhonemTranslit currentNode= (PhonemTranslit)obj.getMyTreeNode();
			                    		currentNode.setPathImage(SpecialSymbols.pathAuswertung+"Phon_"+j+".png");
			                    		((DefaultTreeModel)blockTree.getModel()).reload(obj);
		                    	}
	                	 });
		                  menuCertain.add(certainLevel[i1]);  	
	                 }
	                 final  JMenuItem menuFinishState=new JMenuItem("Freeze branch");
	                    menuFinishState.addActionListener(new ActionListener() {
	                    	public void actionPerformed(ActionEvent e) {
	                    		
	                    			 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
	                    			 obj.getMyTreeNode().setFinish(true);
	                    			 ((DefaultTreeModel)blockTree.getModel()).reload(obj);
	                    			
	                    		 }
	                    	
	                    });
	                    final  JMenuItem menuUnFinishState=new JMenuItem("Unfreeze branch");
	                    menuUnFinishState.addActionListener(new ActionListener() {
	                    	public void actionPerformed(ActionEvent e) {
	                    		
	                    			 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
	                    			 System.out.println("LABEL TO UNFREEZE "+ obj.getMyTreeNode().getNodeLabel() );
	                    			 obj.getMyTreeNode().setFinish(false);
	                    			 System.out.println("UnFrozen "+ obj.getMyTreeNode().getNodeLabel() );
	                    			 ((DefaultTreeModel)blockTree.getModel()).reload(obj);
	                    			
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
	                   		letterTree.setDragEnabled(true);
	                   		letterTree.setDropMode(DropMode.ON_OR_INSERT);
	                   		letterTree.setTransferHandler(new TreeTransferHandler());
	                   		letterTree.getSelectionModel().setSelectionMode(
	                                 TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
	                   		letterTree.addMouseListener( new MouseAdapter() {
	            				private void myPopupEvent(MouseEvent e) {
	            		            int x = e.getX();
	            		            int y = e.getY();
	            		            JTree tree = (JTree)e.getSource();
	            		            TreePath path1 = letterTree.getPathForLocation(x, y);
	            		            SubElementNode selObj= (SubElementNode)path1.getLastPathComponent();
	            		            if (path1== null)
	            		                return;   
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
	                        		MorphoTranscrNode newNode=new MorphoTranscrNode(newLabel,parent,4,-1);
                            		newNode.setLevel(4);
                            		newNode.setParent(parent);
                            		int pos=((SubElementNode)selObj.getParent()).getIndex(selObj);
                            		SubElementNode newObj=new SubElementNode(newNode),node;
                            
                        	
                            		((SubElementNode)selObj.getParent()).add(newObj);
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
		                                		MorphoTranscrNode newNode=new MorphoTranscrNode(abstrEl,parent,3,-1);
		                                		newNode.setParent(parent);
		                                		newNode.setAbstract(true);
		                                		SubElementNode newObj=new SubElementNode(newNode),node;
		                                
		                                	
		                                		((SubElementNode)selObj.getParent()).add(newObj);
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
	                   			 model1.reload(root);
	                   		//	letterTree.repaint();
                   				 expandTreeLetters(letterTree,true);
                   				
	                   				
	                   				 
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
	                   		model1.reload(root);
	                   		//letterTree.repaint();
	                   			
                   				 expandTreeLetters(letterTree,true);
                   				
                   				
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
	  	                   				 String id= currentNode.getId()+">MT_0";
	  	                   	
	  	                   				 MorphoSyntTranslit morpho=new MorphoSyntTranslit(id,"main",letterTree, currentNode.getTagInterf());
	  	                for(int i=0;i< ((SubElementNode) letterTree.getModel().getRoot()).getMyLetterTreeNode().listNodes().size();i++) {
	  	                	morpho.getElem().add((MorphoTranscrNode)((SubElementNode) letterTree.getModel().getRoot()).getMyLetterTreeNode().listNodes().get(i));
	  	                }
	  	                   				 AnnotationNode obj1=new AnnotationNode(morpho),node;
	                    		        obj.add(obj1);
	                    		        ((DefaultTreeModel)blockTree.getModel()).reload();
                     					expandAll(blockTree,path,true);
	                    		       MorphoSyntTranslit currentNode1= ( MorphoSyntTranslit)obj1.getMyTreeNode();
        
	                    		            	
	                    		            	currentNode.getMorphoTranscr().add(morpho.getId());
	                    		            	mt.add(morpho);
	                    		            	mmt.put(currentNode1.getId(),new Integer(mt.size()-1));
		                      				
	  	                   				 morphoTranscrFrame.doDefaultCloseAction();
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
	                   	
	                    
	                    
	                   popup.add(menuCertain);
	                   AnnotationNode obj1 = ( AnnotationNode)path.getLastPathComponent();
	                   if (!obj1.getMyTreeNode().getFinish()) popup.add(addMorphoTranscr);
	                   if(obj1.getChildCount()<=0) {
	                   if (!obj1.getMyTreeNode().getFinish())popup.add(menuFinishState);
	                   else popup.add(menuUnFinishState);
	                   }
		            }
		            else  if (label.equals("class allmahVer4.GraphTranslit2")) {
		            	
		                  final  JMenuItem menuGr2E=new JMenuItem("Edit Graphical Translit 2");
		                  final  JMenuItem menuGr2V=new JMenuItem("Generate Graphical Translit 2 Variant");
		                  final  JMenuItem menuPhonem=new JMenuItem("Generate Phonemic Transliteration");
		                  final  JMenuItem menuGr2D=new JMenuItem("Delete Graphical Translit 2");
		                  final  JMenu menuCertain=new JMenu("Certainity");
			                 
                          final  JMenuItem certainLevel[]=new JMenuItem[8];
	              
	                 for (int i1=0;i1<8;i1++) {
	                	 final int j=i1+1;
	                	               certainLevel[i1]=new JMenuItem(""+(i1+1));
	                	 certainLevel[i1].addActionListener(new ActionListener() {
		                    	public void actionPerformed(ActionEvent e) {
		                    		
		                    		 AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
			                    		GraphTranslit2 currentNode= (GraphTranslit2)obj.getMyTreeNode();
			                    		currentNode.setPathImage(SpecialSymbols.pathAuswertung+"GT2_"+j+".png");
			                    		((DefaultTreeModel)blockTree.getModel()).reload(obj);
		                    	}
	                	 });
		                  menuCertain.add(certainLevel[i1]);  	
	                 }
	                 
	                   popup.add(menuCertain);
		                  
		                  
		                  
		                  menuPhonem.addActionListener(new ActionListener() {
		                    	public void actionPerformed(ActionEvent e) {
		                    		tree.setSelectionPath(path);
		 				           AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
		                    	//	AnnotationNode obj = ( AnnotationNode)path.getLastPathComponent();
		                    		GraphTranslit2 currentNode= (GraphTranslit2)obj.getMyTreeNode();
		                    		System.out.println ("Phonem for GT2 : "+Jsoup.parse(currentNode.getNodeLabel()).text());
		                    		PhonemTranslit phonemTRNode=new PhonemTranslit(currentNode.getId()+">PT_0","main",currentNode.getTagInterf());
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
					                    		        ((DefaultTreeModel)blockTree.getModel()).reload();
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
		            						
		            						 ((DefaultTreeModel)blockTree.getModel()).reload();
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
		                    		              GraphGT2Node first=firstGT2Node(currentNodeCopy.getGraphGT2());
		                    		            ArrayList<GraphGT2Node> newNodes=new ArrayList<GraphGT2Node>();
		                    		              GraphGT2Node current=first;
		                    		              while (!currentNodeCopy.getGraphGT2().getOutEdges(current).isEmpty()){
		                    		            	 newNodes.add(current);
		                    		            	 System.out.println("Next Node "+current.getLabel1());
		                    		            	 if(!currentNodeCopy.getGraphGT2().getOutEdges(current).isEmpty()) {
		                    		            		 System.out.println(current.getLabel1() +" has "+currentNodeCopy.getGraphGT2().getOutEdges(current)+" out" );
		                    		            	  OperatorLink o=(OperatorLink)currentNodeCopy.getGraphGT2().getOutEdges(current).toArray()[0];
		                    		            	  current=currentNodeCopy.getGraphGT2().getDest(o);
		                    		            	 }
		                    		              }
		                    		              newNodes.add(current);
		                    		              currentNodeCopy.setElements(newNodes);
		                    		            /*  currentNode.getElements().clear();
		                    		              for(int j=0;j<newNodes.size();j++) {
		                    		            	  currentNode.getElements().add(newNodes.get(j));
		                    		              }
		                    		              currentNode.setGraph(currentNodeCopy.getGraphGT2());*/
		                    		            //  currentNode=currentNodeCopy;
		                    		             gt2.set( mgt2.get(currentNodeCopy.getId()).intValue(),currentNodeCopy);
		                    		              AnnotationNode prevobj=(AnnotationNode) obj.getParent();
		                    		              AnnotationNode obj1=new AnnotationNode(currentNodeCopy),node1;
		                    		              prevobj.remove(obj);
		                    		              prevobj.add(obj1);
		                    		          
		                    		              ((DefaultTreeModel)blockTree.getModel()).reload();
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
		                    		              GraphGT2Node first=firstGT2Node(currentNodeCopy.getGraphGT2());
		                    		              String idalt=currentNodeCopy.getId();
		                    		              int no=currentNode.getNoVariants();
		                    		              no=no+1;
		                    		              String idnew=idalt.substring(0,idalt.lastIndexOf("_")+1)+no;
		                    		              currentNodeCopy.setId(idnew);
		                    		              currentNode.setNoVariants(no);
		                    		      
		                    		            ArrayList<GraphGT2Node> newNodes=new ArrayList<GraphGT2Node>();
		                    		              GraphGT2Node current=first;
		                    		              while (!currentNodeCopy.getGraphGT2().getOutEdges(current).isEmpty()){
		                    		            	 newNodes.add(current);
		                    		            	 System.out.println("Next Node "+current.getLabel1());
		                    		            	 if(!currentNodeCopy.getGraphGT2().getOutEdges(current).isEmpty()) {
		                    		            		 System.out.println(current.getLabel1() +" has "+currentNodeCopy.getGraphGT2().getOutEdges(current)+" out" );
		                    		            	  OperatorLink o=(OperatorLink)currentNodeCopy.getGraphGT2().getOutEdges(current).toArray()[0];
		                    		            	  current=currentNodeCopy.getGraphGT2().getDest(o);
		                    		            	 }
		                    		              }
		                    		              newNodes.add(current);
		                    		//hier Generate GT2
		                    		              currentNodeCopy.setElements(newNodes);
		                    		              System.out.println("Parent Id GT2"+currentNodeCopy.getId());
		                    		              String parentid=currentNodeCopy.getId().substring(0,currentNodeCopy.getId().lastIndexOf(">"));
		                    		              GraphTranslit1 gt1parent=gt1.get(mgt1.get(parentid).intValue());
		                    		              gt1parent.getGraphTranslit2().add(currentNodeCopy.getId());
		                    		          	//gt1.get(i).generateGT2();
		                    		          	gt2.add(currentNodeCopy);
		                    		          	mgt2.put(currentNodeCopy.getId(),new Integer(gt2.size()-1));
		                    		          	 AnnotationNode  newObj2=new AnnotationNode(currentNodeCopy), node2;  
		                    		                AnnotationNode prevobj=(AnnotationNode)obj.getParent();
		                    		          	prevobj.add(newObj2);
		                    		              ((DefaultTreeModel)blockTree.getModel()).reload();
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
		                    if (currentNode.getTyp().equals("main")) popup.add(menuGr2V);
		                   popup.add(menuPhonem);
		                 
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
	                    		            	 currentNode.generateNT2();
	                   		                    obj.removeAllChildren();obj.setExplored(false);
	                   		                    for (int i=0;i<currentNode.getNT2().size();i++) {
	                   		                    	       NumTranslit2 n2=nt2.get(mnt2.get(currentNode.getNT2().get(i)).intValue());
	                   		               	                 AnnotationNode  newObj=new AnnotationNode(n2), node;    
	                   		               	                 for (int j=0;j<n2.getGT1().size();j++) {
	                   		               	                	GraphTranslit1 g1=gt1.get(mgt1.get(n2.getGT1().get(j)).intValue());
	                   		               	                    GraphTranslit2 g2=gt2.get(mgt2.get(g1.getGraphTranslit2().get(0)).intValue());
	                      		               	                 AnnotationNode  newObj1=new AnnotationNode(g1), node1;   
	                      		               	                  AnnotationNode  newObj2=new AnnotationNode(g2), node2;
	                      		               	                  newObj1.add(newObj2);
	                      		               	                 newObj.add(newObj1);
	                   		               	                 }
	                      					                  obj.add(newObj);
	                   		                    }
	                   		               // obj.explore(); 
	                   		                    
	                      					//((DefaultTreeModel)blockTree.getModel()).nodeChanged(obj);;
	                   		                 ((DefaultTreeModel)blockTree.getModel()).reload();
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
		                    		     
		                    		  nt1Frame.pack();
		                    		   addChild(nt1Frame,800, 10, 500, 600);		
		                    		   nt1Frame.setVisible(true); 
		                    		  nt1Frame.moveToFront();
		                    	}
		                 });
		                   
		               
		                  popup.add(defReading);
		                 
		            
		                //
		            
		               
		        }
		            else if (label.equals("class allmahVer4.NumTranslit2")) {
		            	
		            	if( ((NumTranslit2)obj.getMyTreeNode()).getTyp().equals("main")) {
		            		
		            		NumTranslit2 n2=((NumTranslit2)obj.getMyTreeNode()).copy();
		            		//n2.generateGT1s();revalidate();repaint();
		            		 final JMenuItem insN2=new JMenuItem("Insert Num Translit.2");
		            		
		            		 
		            		   insN2.addActionListener(new ActionListener() {
			                    	public void actionPerformed(ActionEvent e) {
			                    		NumTranslit2 currentNode= (NumTranslit2)obj.getMyTreeNode();
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
		                   		                 AnnotationNode  newObj1=new AnnotationNode(n2), node1;  
		                   		              prevobj.add(newObj1);
		                   		          prevobj.setExplored(false);
		                   		           System.out.println("Generated n2" + n2.calculateLabel());
		                   		              n2.generateGT1s("var");
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
		                   		          ((DefaultTreeModel)blockTree.getModel()).reload();	 
		                   		    //   ((DefaultTreeModel)blockTree.getModel()).reload();
                     					expandAll(blockTree,path.getParentPath(),true);
		                   		        //expandAll(blockTree,path,true);
		                      			
		                      			//	nt2Frame.doDefaultCloseAction();
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
	  private void expandTreeLetters(JTree tree, boolean expand) {
	        SubElementNode root = (SubElementNode) tree.getModel().getRoot();
	        expandAllLetters(tree, new TreePath(root), expand);
	    }
	  private void expandTree(JTree tree, boolean expand) {
	        AnnotationNode root = (AnnotationNode) tree.getModel().getRoot();
	        expandAll(tree, new TreePath(root), expand);
	    }
	  
	  private boolean compareBorders(ArrayList<String> a, ArrayList<String> b) {
		  boolean gleich=true;
		  String s1="";String s2="";
		 for(int i=0;i<a.size();i++) s1=s1+a.get(i);
		 for(int i=0;i<b.size();i++) s2=s2+b.get(i);
		 System.out.println(" String 1 "+ s1);
		 System.out.println(" String 2 "+ s2);
		 if(!s1.equals(s2)) gleich=false;
		  return gleich;
	  }
	  public GraphGT2Node firstGT2Node(DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> g) {
			GraphGT2Node g2=null;
			Collection<GraphGT2Node> nodes=g.getVertices();
			Iterator<GraphGT2Node> it=nodes.iterator();
			boolean found=false;
			while(it.hasNext() &&(!found)) {
				g2=it.next();
				if( g.getInEdges(g2).size()==0) found =true;
			}
			return g2;
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
