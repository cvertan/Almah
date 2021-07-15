package allmahVer4;
import java.util.ArrayList;
public class ExplanationPair {
 protected ArrayList<String> examples;
 protected String explanation;
	public ExplanationPair(ArrayList<String> ex, String expl) {
		examples=new ArrayList<String>();
		for(int i=0;i<ex.size();i++) {
			examples.add(ex.get(i));
		}
		explanation=expl;
	}
	public ExplanationPair( String expl) {
		examples=new ArrayList<String>();
		explanation=expl;
	}

	public String getExplanation() {
		return explanation;
	}
	
	public void setExplanation(String expl) {
		explanation=expl;
	}
	public ArrayList<String> getExamples(){
		return examples;
	}
	public void setExamples(ArrayList<String> ex) {
		examples.clear();
		for (int i=0;i<ex.size();i++) {
			examples.add(ex.get(i));
		}
	}
}
