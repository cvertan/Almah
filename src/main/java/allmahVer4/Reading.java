package allmahVer4;

public class Reading {
 private String confidence;
 private String reading;
 private String typ;
 private boolean state;
	public Reading() {
		reading="";
	  	confidence="";	
	  	typ="";
	  	state=true;
		
	}
	public Reading(String s, String n,String t) {
		reading=s;
	  	confidence=n;	
	  	typ=t;
	  	state=true;
	}
	public Reading(String s, String n,String t,boolean b) {
		reading=s;
	  	confidence=n;	
	  	typ=t;
	  	state=b;
	}
	public Reading(String s) {
		reading=s;
	  	confidence="-1";	
	  	typ="no";
	  	state=true;
	}
	public Reading copy() {
		return new Reading(reading,confidence,typ,state);
	}
	public String getReading() {
		return reading;
	}
	public String getConfidence() {
		return confidence;
	}
	public String getTyp() {
		return typ;
	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean b) {
		state=b;
	}
	public void setTyp(String s) {
		typ=s;
	}
	public void setReading(String s) {
		reading=s;
	}
	public void setConfidence(String n) {
		confidence=n;
	}

}
