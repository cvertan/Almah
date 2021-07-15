package allmahVer4;
import java.util.ArrayList;
import java.util.List;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import java.lang.Integer;
public class DocumentReading extends TreeNode {

	
	private String id;
	private boolean state;
	private ArrayList<String> b;
	private AllmahGUI interf;
	private String parent;
	public DocumentReading( String num, AllmahGUI in) {
		super(0);
		this.interf=in;
		this.id=num+">R"+ interf.docr.size();
		parent=num;
		state=true;
		b=new ArrayList<String>();
	}
	
	public DocumentReading(OElement lesung, AllmahGUI in) {
		super(0);	
		this.interf=in;
			this.id=lesung.getProperty("id");
			this.parent=lesung.getProperty("parent");
			this.state=lesung.getProperty("state");	
			
	}
	public OElement toDB(OElement el) {
		ODatabaseSession db =  this.interf.getDatabase();
		 el.setProperty("id",this.id, OType.STRING);
		 el.setProperty("state",this.state,OType.BOOLEAN);
	     List<OElement> listHBlocks=new ArrayList<OElement>();
	      for(int i=0;i<b.size();i++) {
	    	  String stm1 = "SELECT FROM HieroglyphenBlock WHERE id = ? ";
	    	  OResultSet  rs1 = db.query(stm1, b.get(i));
	    	  HieroglyphenBlock hbl=interf.hb.get(interf.mhb.get(b.get(i)).intValue());
	    	 if (!rs1.hasNext()) {
	    		 OElement block= hbl.toDB(db.newInstance("HieroglyphenBlock"));
	    		//lesung.setProperty("id",b.get(i), OType.STRING);
	    		 listHBlocks.add(block);
	    		block.setProperty("hasParent", el, OType.LINK);
	    		block.save(); db.commit();	    		
	    	 }
	    	 else {
	    		 OElement block= hbl.toDB(rs1.next().toElement());
	    		block.save(); db.commit();
	    	 }
	       }
	     el.setProperty("hasHBlocks", listHBlocks,OType.LINKLIST);
		 
		return el;
	}
	public String calculateLabel() {
		return id.substring(id.indexOf(">")+1);
	}
	public String getParent() {
		return parent;
	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean b) {
		state=b;
	}
	 public  ArrayList<TreeNode> listNodes(){
		 ArrayList <TreeNode> t=new ArrayList<TreeNode>();
		 for(int i=0;i<b.size();i++) {
			 t.add(interf.hb.get(interf.mhb.get(b.get(i)).intValue()));
		 }
		 return t;
	 }	

	public String getId(){
		return id;
	}
	
	public void setId(String s) {
		id=s;
	}
	
	public ArrayList<String> getBlocks() {
		return b;
	}
	public void setBlocks(ArrayList<String> bid) {
		for(int i=0;i<bid.size();i++)
               b.add(bid.get(i));
	}

}
