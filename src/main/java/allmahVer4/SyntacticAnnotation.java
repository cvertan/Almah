package allmahVer4;

public class SyntacticAnnotation {
protected String posCluster;
protected String pos;
protected SyntacticFeature feat;
String comment;
	public SyntacticAnnotation(String c, String p, SyntacticFeature f) {
		posCluster=c;
		pos=p;
		if (f.getFeatures()!=null)
			if(f.getExplanation()!=null)  feat=new SyntacticFeature(f.getAbbreviation(),f.getExplanation());
			else  feat=new SyntacticFeature(f.getAbbreviation(),f.getFeatures(),f.getExplanation());
		 else feat=new SyntacticFeature(f.getAbbreviation());
		comment="";
	}
	public SyntacticAnnotation(String c) {
		posCluster="";
		pos="";
		
		feat=new SyntacticFeature(c);
		
		comment="";
	}
	public String getPosCluster() {
		return posCluster;
	}
	public void setPosCluster(String s) {
		posCluster=s;
	}
	
	public String getPos() {
		return pos;
	}
	public String toString() {
		//return posCluster+"  "+pos+"  "+ feat.getAbbreviation();
		return  feat.getAbbreviation();
	}
	
	public void setPos(String s) {
		pos=s;
	}
	public SyntacticFeature getSyntFeature() {
		return feat;
	}
	public void setSyntFeature(SyntacticFeature f) {
		if (f.getFeatures()!=null)
			feat=new SyntacticFeature(f.getAbbreviation(),f.getFeatures(),f.getExplanation());
			else feat=new SyntacticFeature(f.getAbbreviation(),f.getExplanation());
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String m) {
		comment=m;
	}
	
}
