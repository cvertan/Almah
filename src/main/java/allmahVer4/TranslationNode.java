package allmahVer4;

public class TranslationNode {
private String id;	
private String original;
private String translation;
	public TranslationNode(String id, String o, String tr) {
		this.id=id;
		original=o;
		translation=tr;
	}
	
	public String getId() {
		return id;
	}
	
	public String getOriginal() {
		return original;
	}
	
	public String getTranslation() {
		return translation;
	}

}
