package allmahVer4;

public class WordTranslationNode {
private MorphoGlossNode parent;
private String translation;
	public WordTranslationNode(MorphoGlossNode p) {
		parent= p;
	translation="";
	}
	public WordTranslationNode(MorphoGlossNode p, String s) {
		parent= p;
	translation=s;
	}
	
	public void setTranslation(String s) {
		translation=s;
	}
	
	public String getTranslation(String s) {
		return translation;
	}
	
	public MorphoGlossNode getParent() {
		return parent;
	}
}
