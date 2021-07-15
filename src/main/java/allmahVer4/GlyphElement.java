package allmahVer4;
import java.util.ArrayList;

public class GlyphElement  {
   private String label;
   private String type;
   private ArrayList<String> readings;
	public GlyphElement(String label, String type, ArrayList<String> r) {
		this.label=label;
		this.type=type;
		readings=new ArrayList<String>();
		for (int i=0;i<r.size();i++) {
			readings.add(r.get(i));
		}
	}
	public GlyphElement(String label, String type) {
		this.label=label;
		this.type=type;
		readings=new ArrayList<String>();
	}
	
	public String getType() {
		return type;
	}
	public String getLabel() {
		return label;
	}

     public ArrayList<String> getReadings(){
    	 return readings;
     }
     
     public void setLabel(String s) {
    	 label=s;
     }
     
     public void setType(String t) {
    	 type=t;
     }
     
     public void setReadings(ArrayList<String> r) {
    		 readings.clear();
    		 for (int i=0;i<r.size();i++) {
    			 readings.add(r.get(i));
    	 }
     }
     
     public ArrayList<String> copyReadings(){
    	 ArrayList<String> r= new ArrayList<String>();
    	 for(int i=0;i<readings.size();i++) {
    		 r.add(readings.get(i));
    	 }
    	 return r;
     }
}
