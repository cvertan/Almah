package allmahVer4;

public class SemanticAnnotation {
protected String meaning;
protected String linkWN;

	public SemanticAnnotation(String l, String m) {
		meaning=m;
		linkWN=l;
		
	}
	public SemanticAnnotation(String m) {
		meaning=m;
		linkWN="";
		
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
}
