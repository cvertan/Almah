package allmahVer4;

import java.util.ArrayList;

public class TranslationNode {
private String id;
private String original;
private String translation;
private String nodeKind = "TranslationNode";
private String parentMgId = "";
private String elementLabel = "";
private String semanticMeaning = "";
private String syntacticFunction = "";
private String wordNetSynsetId = "";
private String explanation = "";
private String label = "";
private boolean dictionaryEntry = false;
private ArrayList<String> partIds = new ArrayList<String>();

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

    public String getNodeKind() {
        return nodeKind;
    }

    public void setNodeKind(String nodeKind) {
        this.nodeKind = nodeKind == null ? "TranslationNode" : nodeKind;
    }

    public String getParentMgId() {
        return parentMgId;
    }

    public void setParentMgId(String parentMgId) {
        this.parentMgId = parentMgId == null ? "" : parentMgId;
    }

    public String getElementLabel() {
        return elementLabel;
    }

    public void setElementLabel(String elementLabel) {
        this.elementLabel = elementLabel == null ? "" : elementLabel;
    }

    public String getSemanticMeaning() {
        return semanticMeaning;
    }

    public void setSemanticMeaning(String semanticMeaning) {
        this.semanticMeaning = semanticMeaning == null ? "" : semanticMeaning;
    }

    public String getSyntacticFunction() {
        return syntacticFunction;
    }

    public void setSyntacticFunction(String syntacticFunction) {
        this.syntacticFunction = syntacticFunction == null ? "" : syntacticFunction;
    }

    public String getWordNetSynsetId() {
        return wordNetSynsetId;
    }

    public void setWordNetSynsetId(String wordNetSynsetId) {
        this.wordNetSynsetId = wordNetSynsetId == null ? "" : wordNetSynsetId;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation == null ? "" : explanation;
    }

    public String getLabel() {
        if (label == null || label.trim().isEmpty()) {
            return original + " = " + translation;
        }
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? "" : label;
    }

    public boolean isDictionaryEntry() {
        return dictionaryEntry;
    }

    public void setDictionaryEntry(boolean dictionaryEntry) {
        this.dictionaryEntry = dictionaryEntry;
    }

    public ArrayList<String> getPartIds() {
        return partIds;
    }

    public void setPartIds(ArrayList<String> partIds) {
        this.partIds = partIds == null ? new ArrayList<String>() : partIds;
    }

    public void addPartId(String partId) {
        if (partId == null || partId.trim().isEmpty()) {
            return;
        }
        if (partIds == null) {
            partIds = new ArrayList<String>();
        }
        if (!partIds.contains(partId)) {
            partIds.add(partId);
        }
    }
}