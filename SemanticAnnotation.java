package allmahVer4;

public class SemanticAnnotation {
protected String meaning;
protected String linkWN;
protected String twkmId;

	public SemanticAnnotation(String l, String m) {
		meaning=m;
		linkWN=l;
		
	}
	public SemanticAnnotation(String m) {
		meaning=m;
		linkWN="";
		
	}
	
	public SemanticAnnotation (String meaning, String dict, String WN) {
		this.meaning=meaning;
		this.twkmId=dict;
		linkWN=WN;
		
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String m) {
		meaning=m;
	}
	
	
	
	
	public String getLinkWN() {
		return linkWN;
	}
	public void setLinkWN(String m) {
		linkWN=m;
	}
	
	public String getTWKMID() {
		return twkmId;
	}
	public void setTWKMId(String t) {
		twkmId=t;
	}
}
