package allmahVer4;

import java.util.ArrayList;

import org.jsoup.Jsoup;

public class MorphoTranscrNode extends  LetterTreeNode {
	private ArrayList<MorphoTranscrNode> children;
	private String label;
	private String w2wtr;
	private MorphoTranscrNode parent;
	private MorphoTranscrNode oldparent;
	private PhonemNode phonemParent;
	private boolean geschw;
	private boolean bar;
	private boolean eckig;
	private boolean abstr;
	private MayaWord word;
	

	public MorphoTranscrNode(String s) {
		super(3,true);
		this.label=s;
		parent=null;
		word=null;
		children=new ArrayList<MorphoTranscrNode>();
		oldparent=null;
		phonemParent=null;
       geschw=false;
       eckig=false;
       bar=false;
       abstr=false;
       w2wtr="";
	}
	//root
	public MorphoTranscrNode(PhonemTranslit tr) {
		super(0,false);
		label=tr.calculateLabel();
		parent=null;
		oldparent=null;
		word=null;
		 geschw=false;
	       eckig=false;
	       bar=false;
	       abstr=false;
	       w2wtr="";
		phonemParent=null;
		children=new ArrayList<MorphoTranscrNode>();
		for(int i=0;i<tr.getElements().size();i++)
			if(i<tr.getElements().size()-1)
			children.add(new MorphoTranscrNode (tr.getElements().get(i),tr.getElements().get(i+1),this));
			else children.add(new MorphoTranscrNode (tr.getElements().get(i),null,this));
	}
	//
	public MorphoTranscrNode(PhonemNode node,PhonemNode nextNode,MorphoTranscrNode node1) {
		super(1,false);
		parent=node1;
		phonemParent=node;
		oldparent=null;
		 geschw=false;
	       eckig=false;
	       word=null;
	       bar=false;
	       abstr=false;
	       w2wtr="";
		label= Jsoup.parse(node.getLabel()).text();
		children=new ArrayList<MorphoTranscrNode>();
		String labelLow="";
		boolean isNumeric=false;
		if ( label.chars().allMatch( Character::isDigit )) isNumeric=true;
		if(!isNumeric) {
		if ((!label.equals("\u00B2"))&&(!label.equals("\u00D0"))) {
		for (int i=0;i<label.length();i++) {
			if ((""+label.charAt(i)).equals(((""+label.charAt(i)).toUpperCase()))) {
				labelLow=labelLow+(""+label.charAt(i)).toLowerCase();
			}
			else labelLow=labelLow+label.charAt(i);
		}
		label=labelLow;
		}
		}	
			 
		if (label.equals("\u00B2")){
			if (nextNode!=null) {
				String label1= Jsoup.parse(nextNode.getLabel()).text();
				int n=0;
				for (int i=0;i<label1.length();i++) {
					if ((""+label1.charAt(i)).equals(((""+label1.charAt(i)).toUpperCase()))) {
					n=n+1;
					}
			}
				if (n==0) label=label1;
		}
		}
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
	public MorphoTranscrNode(PhonemNode node,MorphoTranscrNode node1) {
		super(1,false);
		parent=node1;
		phonemParent=node;
		oldparent=null;
		 geschw=false;
	       eckig=false;
	       bar=false;
	       abstr=false;
	       word=null;
	       w2wtr="";
		label= Jsoup.parse(node.getLabel()).text();
		
		children=new ArrayList<MorphoTranscrNode>();
		String s=Jsoup.parse(node.getLabel()).text();
		if (s.length()>1) {
		        if(! s.equals("??")){
		        	String aux=startsWithGlotal(s);
		        	if(!aux.isEmpty()) {
		        	     if( s.length()>aux.length()) process(s,this);
		        	}
		        	else  process(s,this);
		        }
		}      		
				
	}
	public void setAbstract(boolean b) {
		abstr=b;
	}
	public void process(String s, MorphoTranscrNode parent) {
		MorphoTranscrNode node=null;
		
		String first=startsWithGlotal(s);
		if(!first.isEmpty()) {
			node=new MorphoTranscrNode(first,parent,1);
			parent.getChildren().add(node);
			s=s.substring(first.length());
		}
		else {
			node=new MorphoTranscrNode(""+s.charAt(0),parent,1);
			parent.getChildren().add(node);
			s=s.substring(1);
		}
		
		if(!s.isEmpty()) process(s,parent);
	}
	public ArrayList<MorphoTranscrNode> getChildren(){
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
public boolean getAbstract() {
	return abstr;
}
	public MorphoTranscrNode(String s,MorphoTranscrNode node,int typ,int x) {
		super(2,true);
		 geschw=false;
	       eckig=false;
	       word=null;
	       abstr=false;
	       bar=false;
	       w2wtr="";
		if((typ==0)||(typ==4)||(typ==4))
		phonemParent=null;
		else phonemParent=node.getPhonemParent();
		parent=node;
		if(typ==0)
		{ oldparent=null; parent=null;}
		else { oldparent=node; parent=node;}
		label=s;
		setLevel(typ);
		children=new ArrayList<MorphoTranscrNode>();
		if(x>=0)
		node.getChildren().add(x, this);
		else node.getChildren().add( this);
	}
	public MorphoTranscrNode(String s,MorphoTranscrNode node,int typ) {
		super(2,true);
		 geschw=false;
	       eckig=false;
	       bar=false;
	       abstr=false;
	       word=null;
	       w2wtr="";
		if((typ==0))
		phonemParent=null;
		else phonemParent=node.getPhonemParent();
	
		parent=node;
		if(typ==0)
		     oldparent=null;
		else oldparent=node;
		label=s;
	//	setLevel(typ);
		children=new ArrayList<MorphoTranscrNode>();
		
	}
	 public String toString() {
		
		 return calculateLabel();
	 }
	 public PhonemNode getPhonemParent() {
		 return phonemParent;
	 }
	 public MorphoTranscrNode getParent() {
		 return parent;
	 }
	 public String calculateLabel() {
		 String s="";
		 if( children.isEmpty()) {
			 if(!this.getDel()) {
				 if (geschw) s="{"+this.label+"}";
				 else if(eckig) s="["+this.label+"]";
				 else if(bar) s="|"+this.label+"|";
				 else  s=this.label;
			 }
			 else {
				 if (getLevel()==5) s="";
				 else s=this.label;
			 }
		 }
		 else {
			 for(int i=0;i<children.size();i++) {
				 if (children.get(i).getLevel()==5) s=s+"";
				 else  s=s+children.get(i).getLabel();
			 }
			
		 }
		 if (getLevel()==5) return this.label;
		 else return s;
	 }
	 public String calculateLabel1() {
		 String s="";
		 if( children.isEmpty()) {
			 if(!this.getDel()) {
				 if (geschw) s="";
				 else if(eckig) s=this.label;
				 else if(bar) s="";
				 else  s=this.label;
			 }
			 else {
				 if (getLevel()==5) s="";
				 else s=this.label;
			 }
		 }
		 else {
			 for(int i=0;i<children.size();i++) {
				 if (children.get(i).getLevel()==5) s=s+"";
				 else  s=s+children.get(i).calculateLabel1();
			 }
			
		 }
		 if (getLevel()==5) return this.label;
		 else return s;
	 }
	 public String calculateLabel2() {
		 String s="";
		 if( children.isEmpty()) {
			 if(!this.getDel()) {
				 if (geschw) s="";
				 else if(eckig) s=this.label;
				 else if(bar) s="";
				 else   if ((this.label.equals("-"))||(this.label.equals("="))||(this.label.equals("~"))||(this.label.equals(">"))||(this.label.equals("+"))) s="";
				 else if(abstr) s="";
				 else	 s=this.label;
			 }
			
			 else {
				 if (getLevel()==5) s="";
				 else s=this.label;
			 }
		 }
		 else {
			 for(int i=0;i<children.size();i++) {
				 if (children.get(i).getLevel()==5) s=s+"";
				 else  s=s+children.get(i).calculateLabel2();
			 }
			
		               }
		 if (getLevel()==5) {
			 if ((this.label.equals("-"))||(this.label.equals("="))||(this.label.equals("~"))||(this.label.equals(">"))||(this.label.equals("+")))
					 return " ";
			 else return this.label;
		 }
		 else return s;
	 }
	 public String calculateLabel3() {
		 String s="";
		 if( children.isEmpty()) {
			 if(!this.getDel()) {
				 if (geschw) s="";
				 else if(eckig) s=this.label;
				 else if(bar) s="";
				 else   if ((this.label.equals("-"))||(this.label.equals("="))||(this.label.equals("~"))||(this.label.equals(">"))||(this.label.equals("+"))) s="";
				 
				 else	 s=this.label;
			 }
			
			 else {
				 if (getLevel()==5) s="";
				 else s=this.label;
			 }
		 }
		 else {
			 for(int i=0;i<children.size();i++) {
				 if (children.get(i).getLevel()==5) s=s+"";
				 else  s=s+children.get(i).calculateLabel2();
			 }
			
		 }
		 if (getLevel()==5) {
			 if ((this.label.equals("-"))||(this.label.equals("="))||(this.label.equals("~"))||(this.label.equals(">"))||(this.label.equals("+")))
					 return " ";
			 else return this.label;
		 }
		 else return s;
	 }
	
		 public String getLabel() {
			 return calculateLabel();
		 }
		 public  ArrayList<LetterTreeNode> listNodes(){
		 ArrayList <LetterTreeNode> t=new ArrayList<LetterTreeNode>();
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
		public void setGeschw(boolean b) {
			geschw=b;
		}
		public boolean getGeschw() {
			return geschw;
		}
		public void setEckig(boolean b) {
			eckig=b;
		}
		public boolean getEckig() {
			return eckig;
		}
		public void setBar(boolean b) {
			bar=b;
		}
		public boolean getBar() {
			return bar;
		}
		 public void setParent(MorphoTranscrNode m) {
			 parent=m;
		 }
		 public boolean isDeleted() {
			 boolean b=false;
			if (children.size()==1) {
				if (children.get(0).getDel()) b=true;
			}
			 return b;
		 }
	public MayaWord getMayaWord() {
		return word;
	}
	public void setMayaWord(MayaWord m) {
		word=m;
	}
	public String getTranslation() {
		return w2wtr;
	}
	public void setTranslation(String s) {
		w2wtr=s;
	}
}
