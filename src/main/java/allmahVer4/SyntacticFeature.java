package allmahVer4;
import java.util.ArrayList;
public class SyntacticFeature{
 protected ExplanationPair explanation;
 protected ArrayList<FeaturePair> feat;
 protected String abbrev;
 protected String key;
 protected boolean stem;
	public SyntacticFeature(String a, ArrayList<FeaturePair> f,ExplanationPair ex) {
		
		abbrev=a;feat=new ArrayList<FeaturePair>();
		key=abbrev;
		for (int i=0;i<f.size();i++) {
			feat.add(new FeaturePair(f.get(i).getName(),f.get(i).getValue()));
		}
		explanation=new ExplanationPair(ex.getExplanation());
		key=key+ex.getExplanation();
		if (ex.getExamples().size()>0) {
			for (int i=0;i<ex.getExamples().size();i++) {
				explanation.getExamples().add(ex.getExamples().get(i));
				key=key+ex.getExamples().get(i);
			}
		}
	}

	public SyntacticFeature(String a, ExplanationPair ex) {
		abbrev=a;
		key=abbrev;
		feat=new ArrayList<FeaturePair>();
		explanation=new ExplanationPair(ex.getExplanation());
		key=key+ex.getExplanation();
		if (ex.getExamples().size()>0) {
			for (int i=0;i<ex.getExamples().size();i++) {
				explanation.getExamples().add(ex.getExamples().get(i));
				key=key+ex.getExamples().get(i);
			}
		}
	}
	public SyntacticFeature(String a) {
		abbrev=a;
		key=abbrev;
		feat=new ArrayList<FeaturePair>();
		explanation=null;
		//key=key+ex.getExplanation();
		/*if (ex.getExamples().size()>0) {
			for (int i=0;i<ex.getExamples().size();i++) {
				explanation.getExamples().add(ex.getExamples().get(i));
				key=key+ex.getExamples().get(i);
			}
		}*/
	}
	
	public String getAbbreviation() {
		return abbrev;
	}
	public String getKey() {
		return key;
	}
	public void generateKey() {
		key=abbrev+explanation.getExplanation();
		if(explanation.getExamples().size()>0) {
			for(int i=0;i<explanation.getExamples().size();i++) {
				key=key+explanation.getExamples().get(i);
			}
		}
	}
	public void setAbbreviation(String s) {
		abbrev=s;
	}

	public ExplanationPair getExplanation() {
		return explanation;
	}
	public void setExplanationPair(ExplanationPair ex) {
		explanation=new ExplanationPair(ex.getExplanation());
		if (ex.getExamples().size()>0) {
			for (int i=0;i<ex.getExamples().size();i++) {
				explanation.getExamples().add(ex.getExamples().get(i));
			}
		}
	}
	
	public ArrayList<FeaturePair> getFeatures(){
		return feat;
	}
	public void setFeaturePair(ArrayList<FeaturePair> f) {
		feat.clear();
		for(int i=0;i<f.size();i++) {
			feat.add(new FeaturePair(f.get(i).getName(),f.get(i).getValue()));
		}
	}
	
	public void setStem(boolean b) {
		stem=b;
	}

}
