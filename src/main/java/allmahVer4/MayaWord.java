package allmahVer4;
import java.util.ArrayList;
public class MayaWord {
private String id;
private String translation;
private ArrayList<MorphoTranscrNode> parents;
	public MayaWord(ArrayList<MorphoTranscrNode> p) {
		this.id=id;
		translation="";
		
		parents=new ArrayList<MorphoTranscrNode>();
		for (int i=0;i<p.size();i++)
			parents.add(p.get(i));
	}
	public ArrayList<MorphoTranscrNode> getElements(){
		return parents;
	}
public String calculateLabel() {
	String s="";
	for (int i=0;i<parents.size();i++)
		s=s+parents.get(i).calculateLabel2();
	return s;
}
public String getTranslation() {
	return translation;
}
public void setTranslation(String s) {
	translation=s;
}
}
