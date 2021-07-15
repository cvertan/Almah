package allmahVer4;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.db.object.ODatabaseObject;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.object.db.*;

import edu.uci.ics.jung.graph.Graph;

import com.orientechnologies.orient.core.db.OrientDBConfig;

import com.orientechnologies.orient.core.record.OEdge;
import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.record.OVertex;
public class ConfigureDB {

	public ConfigureDB() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		  OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
		    ODatabaseSession db = orient.open("allmahDB", "admin", "admin");
		    
		    OElement transl = db.newInstance("FinalTranslation");
		   OElement mgloss = db.newInstance("MorphoGloss");
		   OElement msyTr = db.newInstance("MorphoSyntTranslit");
		  OElement phonemTr = db.newInstance("PhonemTranslit");
		   OElement grTr2 = db.newInstance("GraphTranslit2");
		  OElement grTr1 = db.newInstance("GraphTranslit1");
		  OElement numTr2 = db.newInstance("NumTranslit2");
		  OElement numTr1 = db.newInstance("NumTranslit1");
		  OElement  hblock = db.newInstance("HieroglyphenBlock");
		  OElement  docReading = db.newInstance("DocumentReading");
		  OElement doc = db.newInstance("HyerogliphenDocument");

		 //   OClass transl = db.createClass("FinalTranslation");
			   transl.setProperty("id", OType.STRING);
			
		  
		   //OClass mgloss = db.createClass("MorphoGloss");
		/*  mgloss.createProperty("id", OType.STRING);
		  mgloss.createProperty("linkMorphoGloss",OType.LINKLIST,transl);
		  */
		  // OClass msyTr = db.createClass("MorphoSyntTranslit");
			/*  msyTr.createProperty("id", OType.STRING);
			  msyTr.createProperty("linkMorphoGloss",OType.LINKLIST,mgloss);
		  */
		 // OClass phonemTr = db.createClass("PhonemTranslit");
		  /* phonemTr.createProperty("id", OType.STRING);
		   phonemTr.createProperty("linkMorphoSynt",OType.LINKLIST,msyTr);
		  */
		   //OClass grTr2 = db.createClass("GraphTranslit2");
		  /* grTr2.createProperty("id", OType.STRING);
		   grTr2.createProperty("linkPhonmeTr",OType.LINKLIST,phonemTr);
		  */
		  //OClass grTr1 = db.createClass("GraphTranslit1");
		  /* grTr1.createProperty("id", OType.STRING);
		   grTr1.createProperty("linkGraphTr2",OType.LINKLIST,grTr2);
		  */
		 // OClass numTr2 = db.createClass("NumTranslit2");
		   /*numTr2.createProperty("id", OType.STRING);
		   numTr2.createProperty("linkGraphTr1",OType.LINKLIST,grTr1);
		  */
		 // OClass numTr1 = db.createClass("NumTranslit1");
		 /*  numTr1.createProperty("id", OType.STRING);
		   numTr1.createProperty("linkNumTr2",OType.LINKLIST,numTr2);
		  */
		  //OClass  hblock = db.createClass("HieroglyphenBlock");
		 /*  hblock.createProperty("id", OType.STRING);
		   hblock.createProperty("label", OType.STRING);
		   hblock.createProperty("numerical", OType.STRING);
		   hblock.createProperty("state", OType.BOOLEAN);
		   hblock.createProperty("imagepath", OType.STRING);
		   hblock.createProperty("linkNumTr1",OType.LINKLIST,numTr1);
		   hblock.createProperty("linkNumTr1",OType.LINK,docReading);*/
		 //  hblock.createProperty("annotations",OType.LINKLIST,blockAnnot);
		   // private ArrayList<Object> teile;
		/*   hblock.createProperty("parts",OType.EMBEDDEDLIST);*/
		   
		   /*
		    * 	
	private ArrayList<GlyphElementNode> elemNodes;
	private Graph<GlyphNode,OperatorLink> g ;
	private Graph<GlyphNode,OperatorLink> t; 

		    */
		   
		 // OClass  docReading = db.createClass("DocumentReading");
	/*	   docReading.createProperty("id", OType.STRING);
		   docReading.createProperty("state", OType.BOOLEAN);
		   docReading.createProperty("linkBlock",OType.LINKLIST,hblock);
		   docReading.createProperty("parent",OType.LINK, doc);*/

		   // OClass doc = db.createClass("HyerogliphenDocument");
		/*    doc.createProperty("id", OType.INTEGER);
		    doc.createProperty("state", OType.BOOLEAN);
		    doc.createProperty("linkDocR",OType.LINKLIST,docReading);*/
		    
		   /*
		   
		    */
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		  /*  OClass reading = db.getClass("Reading");
		    if (reading == null) {
		      reading = db.createVertexClass("Reading");
		    }		    
		    if (reading.getProperty("label") == null) {
		          reading.createProperty("label", OType.STRING);
		          reading.createIndex("Reading_label_index", OClass.INDEX_TYPE.NOTUNIQUE, "label");
		        }
		    if (reading.getProperty("confidence") == null) {
		          reading.createProperty("confidence", OType.STRING);
		          reading.createIndex("Reading_confidence_index", OClass.INDEX_TYPE.NOTUNIQUE, "confidence");
		        }
		    if (reading.getProperty("typ") == null) {
		          reading.createProperty("typ", OType.STRING);
		        } 
		    if (reading.getProperty("confidence") == null) {
		          reading.createProperty("confidence", OType.STRING);
		        }
		    if (reading.getProperty("key") == null) {
		          reading.createProperty("key", OType.STRING);
		          reading.createIndex("Reading_key_index", OClass.INDEX_TYPE.UNIQUE, "key");
		        }
		    OClass hr = db.getClass("HasReading"); 
		    if (hr== null) {
		        hr=db.createEdgeClass("HasReading");
		      }
		    if (hr.getProperty("selected") == null) {
		          hr.createProperty("selected", OType.BOOLEAN);
		        }
		

		    OVertex r1 = db.newVertex("Reading");
		   r1.setProperty("label", "ab");
		    r1.setProperty("confidence", "3");
		    r1.setProperty("typ", "yes");
		 r1.setProperty("key","ab3yes");
	
		    OVertex r2 = db.newVertex("Reading");
			   r2.setProperty("label", "abc");
			    r2.setProperty("confidence", "1");
			    r2.setProperty("typ", "no");
			    r2.setProperty("key","abc1no");
			    OVertex r3 = db.newVertex("Reading");
				   r3.setProperty("label", "bb");
				    r3.setProperty("confidence", "2");
				    r3.setProperty("typ", "yes");
				    r3.setProperty("key","bb2yes");
		  try {
		   r1.save();   
		   
		   r2.save(); 
		  
		   r3.save(); 
		   OEdge edge1 = r1.addEdge(r2, "HasReading");
		   edge1.setProperty("selected", true);
		    edge1.save();
		    OEdge edge2 = r1.addEdge(r3,"HasReading");
		    edge2.setProperty("selected", false);
		    edge2.save();
		    db.commit();
		    r1.setProperty("confidence","2");
		    r1.setProperty("key","ab2yes");
		    r1.save();
		    
		  }
		  catch(Exception e) {System.out.println("Element  already in DB");}*/
		 

		    db.close();    
		    orient.close();

	}

}
