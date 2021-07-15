package allmahVer4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTree;
import java.util.Enumeration;
import java.util.Collections;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;

public class MorphoSyntTranslit extends TreeNode{

	private String id;
	private ArrayList<MorphoTranscrNode> elem;
	private ArrayList<String> morphoGloss;
	private AllmahGUI tag;
	private JTree tree;
	private String typ;
	int novariants;
	private String label;
	private String labelKonsolid;
	private String finalKonsolid;
	
	public MorphoSyntTranslit(String id,String t,JTree tree,  AllmahGUI tag) {
		super(6);
		this.id=id;
		elem=new ArrayList<MorphoTranscrNode>();
		
		morphoGloss=new ArrayList<String>();
		this.tree=tree;
		this.tag=tag;
		typ=t;
		label=((SubElementNode)tree.getModel().getRoot()).toString();
		labelKonsolid=((MorphoTranscrNode)((SubElementNode)tree.getModel().getRoot()).getMyLetterTreeNode()).calculateLabel1();
		finalKonsolid=((MorphoTranscrNode)((SubElementNode)tree.getModel().getRoot()).getMyLetterTreeNode()).calculateLabel2();
		novariants=0;
	}
	

	public String getTyp() {
		return typ;
	}
	
/*	public ArrayList<MayaWord> calculateMayaWords() {
		ArrayList<MayaWord> maya=new ArrayList<MayaWord>();
		int i=0;
		int nr=0;
		MayaWord m=new MayaWord(id+">W_"+nr);
		maya.add(m);
			
		while(i<elem.size()) {
			System.out.println("Elem für Build Word " +elem.get(i).getLabel());
			if (elem.get(i).isDeleted())i=i+1;
			else if (elem.get(i).getLabel().equals("-")||elem.get(i).getLabel().equals(">")||elem.get(i).getLabel().equals("+")||elem.get(i).getLabel().equals("~")||elem.get(i).getLabel().equals("=")) i=i+1;
			else if (elem.get(i).getLabel().equals(" ")) {
				
				nr=nr+1;
				m=new MayaWord(id+">W_"+nr); i=i+1;maya.add(m);
			}
			else { maya.get(maya.size()-1).getElements().add(elem.get(i)); 
			elem.get(i).setMayaWord(maya.get(maya.size()-1));
			i=i+1;}
		}
		return maya;
	}*/
public ArrayList<MorphoTranscrNode> getElem(){
	return elem;
}
	public int getNoVariants() {
		return novariants;
	}
	public void setNoVariants(int n) {
		novariants=n;
	}

	public void setLabel(String s) {
		this.label=s;
	}
	public String getParent() {
		String s=id.substring(0,id.lastIndexOf(">"));
		return s ;
		}
	
	public void setId(String s) {
		this.id=s;
	}

	
public String calculateLabel() {
    

	return label+"$"+labelKonsolid+"#"+finalKonsolid;
}
public String getKonsolid() {
	return labelKonsolid;
}
	public String getId() {
		return id;
	}
public JTree getTreeTranslit() {
	return tree;
}
	

	public AllmahGUI getTagInterf() {
		return tag;
	}
	public ArrayList<MorphoTranscrNode> getTranscrElem(){
		ArrayList<MorphoTranscrNode> el=new ArrayList<MorphoTranscrNode>();
		for (int i=0;i<elem.size();i++) {
			System.out.println("Real Elements "+ elem.get(i).getLabel() ) ;
			if((! elem.get(i).getLabel().equals("+"))&&(! elem.get(i).getLabel().equals("="))&&(! elem.get(i).getLabel().equals("-"))&&(! elem.get(i).getLabel().equals(">"))&&(! elem.get(i).getLabel().equals("~"))&&(! elem.get(i).getLabel().equals(" "))) {
				
				el.add(elem.get(i));
			}
		}
		return el;
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
