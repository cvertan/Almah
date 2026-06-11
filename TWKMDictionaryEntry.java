package allmahVer4;

public class TWKMDictionaryEntry {
	protected String id;
	protected String lemma;
	protected String translation;
	
	public TWKMDictionaryEntry(String id, String lemma, String translation) {
		this.id=id;
		this.lemma=lemma;
		this.translation=translation;
	}
	
	public String getId() {
		return id;
	}
	
	public String getLemma() {
		return lemma;
	}
	
	public String getTranslation(){
		return translation;
	}

}
