package allmahVer4;

import java.util.ArrayList;
import javax.swing.JTree;
import org.jsoup.Jsoup;

public class MorphoGlossNode extends  MLetterTreeNode {
	private ArrayList<MorphoGlossNode> children;
	private String label;
	private MorphoGlossNode parent;
	private MorphoTranscrNode parentlocal;


	public MorphoGlossNode(String s) {
		super(3,true);
		this.label=s;
		parent=null;
		children=new ArrayList<MorphoGlossNode>();
		parentlocal=null;
	}
	//root
	public MorphoGlossNode(MorphoSyntTranslit tr) {
		super(0,false);
		
		parent=null;
		parentlocal=null;
		children=new ArrayList<MorphoGlossNode>();
		JTree treetr=tr. getTreeTranslit();
	MorphoTranscrNode roottr=(MorphoTranscrNode)((SubElementNode)treetr.getModel().getRoot()).getMyLetterTreeNode();
	label=roottr.calculateLabel();	
	for(int i=0;i<roottr.getChildren().size();i++)
			
			children.add(new MorphoGlossNode (roottr.getChildren().get(i),this));
			
	}
	//
	public MorphoGlossNode(MorphoTranscrNode node,MorphoGlossNode node1) {
		super(1,false);
		parent=node1;
		parentlocal=node;
		label= Jsoup.parse(node.calculateLabel()).text();
		children=new ArrayList<MorphoGlossNode>();
		boolean isNumeric=false;
		if ( label.chars().allMatch( Character::isDigit )) isNumeric=true;
		String s=label;;
		if (s.length()>1) {
		        if((! s.equals("??"))&&(!isNumeric)){
		        	String aux=startsWithGlotal(s);
		        	if(!aux.isEmpty()) {
		        	     if( s.length()>aux.length()) process(s,this);
		        	}
		        	else  process(s,this);
		        }
		        //else setLevel(2);
		}      		
		//else setLevel(2);
	}
	//MorphoTrancr elem
	
	public void process(String s, MorphoGlossNode parent) {
		MorphoGlossNode node=null;
		
		String first=startsWithGlotal(s);
		if(!first.isEmpty()) {
			node=new MorphoGlossNode(first,parent,1);
			parent.getChildren().add(node);
			s=s.substring(first.length());
		}
		else {
			node=new MorphoGlossNode(""+s.charAt(0),parent,1);
			parent.getChildren().add(node);
			s=s.substring(1);
		}
		
		if(!s.isEmpty()) process(s,parent);
	}
	public ArrayList<MorphoGlossNode> getChildren(){
		return children;
	}
	public String startsWithGlotal(String s) {
		String x=""; boolean found=false; int i=0;
		while((i<SpecialSymbols.glotals.length)&&(!found)) {
			if (s.indexOf(SpecialSymbols.glotals[i])==0) {
				x=SpecialSymbols.glotals[i]; found=true;
			}
			else i=i+1;
		}
		return x;
	}
public void setLevel(int n) {
	super.setLevel(n);
}
	public MorphoGlossNode(String s,MorphoGlossNode node,int typ,int x) {
		super(2,true);
		
		if((typ==0))
		parentlocal=null;
		else parentlocal=node.getMorphoTranscrParent();
		parent=node;
		label=s;
		setLevel(typ);
		children=new ArrayList<MorphoGlossNode>();
		if(x>0)
		node.getChildren().add(x, this);
		else node.getChildren().add( this);
	}
	public MorphoGlossNode(String s,MorphoGlossNode node,int typ) {
		super(2,true);
	
		if((typ==0))
		parentlocal=null;
		else parentlocal=node.getMorphoTranscrParent();
	
		parent=node;
		label=s;
	//	setLevel(typ);
		children=new ArrayList<MorphoGlossNode>();
		
	}
	 public String toString() {
		
		 return calculateLabel();
	 }
	 public MorphoTranscrNode getMorphoTranscrParent() {
		 return parentlocal;
	 }
	 public MorphoGlossNode getParent() {
		 return parent;
	 }
	 public String calculateLabel() {
		 String s="";
		 if( children.isEmpty()) {
		  s=label;
		 }
		 else {
			 for(int i=0;i<children.size();i++) {
				 if (children.get(i).getLevel()==5) s=s+"";
				 else  s=s+children.get(i).getLabel();
			 }
			
		 }
		return s;
	 }
	 public String calculateLabel1() {
		 String s="";
		 if( children.isEmpty()) {
			
		 }
		 else {
			 for(int i=0;i<children.size();i++) {
				 if (children.get(i).getLevel()==5) s=s+"";
				 else  s=s+children.get(i).calculateLabel1();
			 }
			
		 }
		 return s;
	 }
		 public String getLabel() {
			 return calculateLabel();
		 }
		 public  ArrayList<MLetterTreeNode> listNodes(){
		 ArrayList <MLetterTreeNode> t=new ArrayList<MLetterTreeNode>();
		 if(children.size()>0) {
			 for(int i=0;i<children.size();i++) {
				 t.add(children.get(i));
			 }
			 }
		 return t;
		 }
		public void setLabel(String s) {
			label=s;
		}
		public int getLevel() {
			return super.getLevel();
		}
		

		 public void setParent(MorphoGlossNode m) {
			 parent=m;
		 }
	
}

