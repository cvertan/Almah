package allmahVer4;
import java.util.ArrayList;
import java.util.List;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import java.lang.Integer;
public class HyerogliphenDocument extends TreeNode {

	private String title;
	private String id;
	private boolean state;
	private ArrayList<String> b;
	private AllmahGUI interf;
	
	public HyerogliphenDocument( String num, AllmahGUI interf) {
		super(0);
		this.id=num;
		state=true;
		this.interf=interf;
		b=new ArrayList<String>();
	}
	public HyerogliphenDocument() {
		super(0);
		this.id="";
		state=true;
		this.interf=null;
		b=new ArrayList<String>();
	}
	public HyerogliphenDocument(OElement doc) {
		super(0);	
			this.id=doc.getProperty("id");
			System.out.println("ID "+id);
			this.title=doc.getProperty("title");
			this.state=doc.getProperty("state");	
			
	}
	
	public void insertInDB() {
		ODatabaseSession db =  interf.getDatabase();
		String stm = "SELECT FROM HyerogliphenDocument WHERE id = ? ";
		OResultSet  rs = db.query(stm, this.id);
		if(!rs.hasNext()) {
		     OElement doc = db.newInstance("HyerogliphenDocument");
		     doc.setProperty("id",this.id, OType.STRING);
		     doc.setProperty("title",this.id.substring(0, this.id.lastIndexOf(".")), OType.STRING);
		     doc.setProperty("state",this.state, OType.BOOLEAN);
		     List<OElement> listReadings=new ArrayList<OElement>();
		      for(int i=0;i<b.size();i++) {
		    	  String stm1 = "SELECT FROM DocumentReading WHERE id = ? ";
		    	  OResultSet  rs1 = db.query(stm1, b.get(i));
		    	  DocumentReading docr=interf.docr.get(interf.mdocr.get(b.get(i)).intValue());
		    	 if (!rs1.hasNext()) {
		    		 OElement lesung= docr.toDB(db.newInstance("DocumentReading"));
		    		//lesung.setProperty("id",b.get(i), OType.STRING);
		    		 listReadings.add(lesung);
		    		lesung.setProperty("hasParent",doc, OType.LINK);
		    		lesung.save(); db.commit();
		    		
		    	 }
		    	 else {
		    		 OElement lesung= docr.toDB(rs1.next().toElement());
		    		 lesung.save(); db.commit();
		    	 }
		       }
		      doc.setProperty("hasDocLesung", listReadings,OType.LINKLIST);
	    		doc.save();
		  	db.commit();
		  }
		else {
			OElement doc=rs.next().toElement();
			doc.setProperty("id",this.id, OType.STRING); doc.save();
		    doc.setProperty("title",this.title, OType.STRING); doc.save();
		    doc.setProperty("state",this.state, OType.BOOLEAN); doc.save();
		    doc.setProperty ("type","HyeroglyphenDocument",OType.STRING);
		    List<OElement> listReadings=new ArrayList<OElement>();
		    for(int i=0;i<b.size();i++) {
		    	  String stm1 = "SELECT FROM DocumentReading WHERE id = ? ";
		    	  OResultSet  rs1 = db.query(stm1, b.get(i));
		    	  DocumentReading docr=interf.docr.get(interf.mdocr.get(b.get(i)).intValue());
		    	 if (!rs1.hasNext()) {
		    		 OElement lesung= docr.toDB(db.newInstance("DocumentReading"));
		    		//lesung.setProperty("id",b.get(i), OType.STRING);
		    		 listReadings.add(lesung);
		    		lesung.setProperty("hasParent",doc, OType.LINK);
		    		lesung.save(); db.commit();
		    		
		    	 }
		    	 else {
		    		 OElement lesung= docr.toDB(rs1.next().toElement());
		    		 lesung.save(); db.commit();
		    	 }
		       }
		    doc.setProperty("hasDocLesung", listReadings,OType.LINKLIST); doc.save();
		    db.commit();
		}
	}
	public String calculateLabel() {
		return id;
	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean b) {
		state=b;
	}
	public String getParent() {
		return null;
	}
	 public  ArrayList<TreeNode> listNodes(){
		 ArrayList <TreeNode> t=new ArrayList<TreeNode>();
		 for(int i=0;i<b.size();i++) {
			 t.add(interf.docr.get(interf.mdocr.get(b.get(i)).intValue()));
		 }
		 return t;
	 }	
	 public String getTitle() {
		return title;
	}
	public String getId() {
		return id;
	}
	
	public void setId(String s) {
		id=s;
	}
	
	public ArrayList<String> getDocReadings() {
		return b;
	}
	public void setDocumentReadings(ArrayList<String> bid) {
		for(int i=0;i<bid.size();i++)
               b.add(bid.get(i));
	}


}
