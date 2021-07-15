package allmahVer4;
import java.util.ArrayList;
public class FinalTranslation extends TreeNode {
private String id;
private ArrayList<MayaWord> elem;
private String original;
private String translation;
	public FinalTranslation(String id, ArrayList<MayaWord> el, String t) {
		super(8);
		this.id=id;
		elem=el;
		original="";
		for (int i=0;i<elem.size();i++) original=original+" "+elem.get(i).calculateLabel();
		translation=t;
	}
	public FinalTranslation(String id, String konsolid, String t) {
		super(8);
		this.id=id;
		original=konsolid;
		translation=t;
	}
	public String getParent() {
		
		String s=id.substring(0,id.lastIndexOf(">"));
		return s ;
		}
  public String calculateLabel() {
	  return original+"#"+translation;
  }
  public String getId() {
	  return id;
  }
  public  ArrayList<TreeNode> listNodes(){
		 ArrayList <TreeNode> t=new ArrayList<TreeNode>();
		/* if(morphoTranscr.size()>0) {
		 for(int i=0;i<morphoTranscr.size();i++) {
		//	 t.add(tag.num2.get(Integer.parseInt(tag.mapnum2.get(nt2.get(i)))));
		 }
		 }*/
		 return t;
	 }
}
