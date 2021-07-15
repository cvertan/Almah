package allmahVer4;
import java.util.ArrayList;
import java.util.Random;
public class BlockAnnotation {
private String id;
private String comment;
private String typ;
private String subtyp;
private ArrayList<String> blocIDs;
//private AllmahGUI interf;
public BlockAnnotation() {
	
}
	public BlockAnnotation(String docId, String typ) {
		Random r=new Random();
		id=docId+">Ann"+r.nextInt();
		this.typ=typ;
		blocIDs=new ArrayList<String>();
		//=tag;
	}	
public void setSubTyp(String s) {
	subtyp=s;
}
public String getSubTyp() {
	return subtyp;
}
public String getId() {
	return id;
}
public String getTyp() {
	return typ;
}
public void setComment(String s) {
	comment=s;
}
public String getComment() {
	return comment;
}
public ArrayList<String> getAnnBorders(){
	return blocIDs;
}
public void setAnnBorders(ArrayList<String> bids) {
	for (int i=0;i<bids.size();i++) {
		blocIDs.add(bids.get(i));
	}
}
}

	