package allmahVer4;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class DBInitialisation {

	public DBInitialisation() {
		// TODO Auto-generated constructor stub
	}
	public void initializeDB() {
		OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
		    ODatabaseSession db = orient.open("almah", "admin", "admin");
         //document
		    OClass document = db.getClass("HyerogliphenDocument");
		    if (document == null) {
		     document= db.createVertexClass("HyerogliphenDocument");
		    }
		    if (document.getProperty("docId") == null) {
		     document.createProperty("docId", OType.STRING);
		      document.createIndex("Document_id_index", OClass.INDEX_TYPE.UNIQUE, "docId");
		    } 
		    if (document.getProperty("docState") == null) {
			     document.createProperty("docState", OType.BOOLEAN);
			      document.createIndex("Document_state_index", OClass.INDEX_TYPE.NOTUNIQUE, "docState");
			    }
		    if (db.getClass("HasBlock") == null) {
		        db.createEdgeClass("HasBlock");
		      }
		    // reading
		    OClass reading= db.getClass("Reading");
		    if (reading== null) {
			     reading= db.createVertexClass("Reading");
			    }
		    if (reading.getProperty("readingConfidence") == null) {
			     reading.createProperty("readingConfidence", OType.STRING);
			    } 
		    if (reading.getProperty("readingReading") == null) {
			     reading.createProperty("readingReading", OType.STRING);
			    } 
		    if (reading.getProperty("readingTyp") == null) {
			     reading.createProperty("readingTyp", OType.STRING);
			    } 
		    if (reading.getProperty("readingState") == null) {
			     reading.createProperty("readingState", OType.BOOLEAN);
			    } 
		    //GlyphElementNode
		    //blocks
		    OClass block = db.getClass("HyerogliphenBlock");
		    if (block == null) {
		     block= db.createVertexClass("HyerogliphenBlock");
		    }
		    if (block.getProperty("blockId") == null) {
		     block.createProperty("blockId", OType.STRING);
		      block.createIndex("Block_id_index", OClass.INDEX_TYPE.UNIQUE, "blockId");
		    } 
		    if (document.getProperty("blockState") == null) {
			     document.createProperty("blockState", OType.BOOLEAN);
			      document.createIndex("Block_state_index", OClass.INDEX_TYPE.NOTUNIQUE, "blockState");
			    }
		    db.close();    
		    orient.close();
	}
	public static void main(String[] args) {


	}

}
