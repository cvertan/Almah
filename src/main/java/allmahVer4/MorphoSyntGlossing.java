package allmahVer4;
import java.util.ArrayList;

import javax.swing.JTree;
public class MorphoSyntGlossing  extends TreeNode{
private ArrayList<GlossingVariant> elem;
private ArrayList<String> translations;
private String id;
private String label="";
AllmahGUI tag;
	public MorphoSyntGlossing(String id,  AllmahGUI tag) {
		super(7);
		this.tag=tag;
		this.id=id;
		elem=new ArrayList<GlossingVariant>();
		
	}
	public ArrayList<GlossingVariant> getElements(){
		return elem;
	}
	public String getParent() {
		System.out.println("ID MS-Glossing " +id);
		String s=id.substring(0,id.lastIndexOf(">"));
		return s ;
		}
	public void setId(String id) {
		this.id=id;
	}
	public String getId() {
		return id;
	}
	public MorphoSyntGlossing copy() {
		MorphoSyntGlossing x=new MorphoSyntGlossing(id,tag);
		for(int i=0;i<elem.size();i++)
			x.getElements().add(elem.get(i));
		return x;
	}
	/*public String calculateLabel() {
	    
         String s="";
         for (int i=0;i<elem.size();i++)
        	 s=s+ " "+elem.get(i).getSemantics().getMeaning();
         label=s;
		return label;
	}	*/
	
	
	
	public String calculateLabel() {
	    
        String s="";
        String p=getParent();
        int j=-1;
        ArrayList<MorphoTranscrNode> nodes= tag.mt.get(tag.mmt.get(p).intValue()).getElem();
        for (int i=0;i<nodes.size();i++) {
        	System.out.println("NODE "+nodes.get(i).calculateLabel());
        	if(( nodes.get(i).getLabel().equals("+"))||(nodes.get(i).getLabel().equals("="))||(nodes.get(i).getLabel().equals("-"))||(nodes.get(i).getLabel().equals(">"))||(nodes.get(i).getLabel().equals("~"))||( nodes.get(i).getLabel().equals(" "))||nodes.get(i).isDeleted())
        	{
        		
        	 if ( nodes.get(i).getLabel().equals(" ")) s=s+" ";
        	 else if (nodes.get(i).isDeleted()) s=s+"";
        	 else s=s+" - ";
        	
        	}
        else	{	
        	j=j+1;
        	if( (elem.get(j).getSyntax()!=null)&&(!elem.get(j).getSyntax().toString().equals("  ")) ){
       	 s=s+elem.get(j).getSyntax().getSyntFeature().getAbbreviation();
        if(!elem.get(j).getSemantics().getMeaning().equals(" "))
        		s=s+":"+elem.get(j).getSemantics().getMeaning();
        }
        	else {
        		 s=s+elem.get(j).getSemantics().getMeaning();
        	}
        }
        }
        
        label=s;
		return label;
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
