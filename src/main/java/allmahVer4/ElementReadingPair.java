package allmahVer4;
import java.util.ArrayList;
public class ElementReadingPair {
  private String elem;
  private ArrayList<Reading> readings;
  
	public ElementReadingPair(String elem, ArrayList<Reading> r) {
		this.elem=elem;
		readings=new ArrayList<Reading>();
		for(int i=0;i<r.size();i++) {
			readings.add(new Reading(r.get(i).getReading(),r.get(i).getConfidence(),r.get(i).getTyp(),r.get(i).getState()));
		}
	}

	public String getElement() {
		return elem;
	}
	
	public ArrayList<Reading> gerReadings(){
		return readings;
	}
	
	
}
