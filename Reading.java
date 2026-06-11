package allmahVer4;

public class Reading {
 private String confidence;
 private String reading;
 private String typ;
 private String sourceUri;
 private boolean state;
	public Reading() {
		reading="";
	  	confidence="";	
	  	typ="";
	  	sourceUri="";
	  	state=true;
		
	}
	public Reading(String s, String n,String t) {
		reading=s;
	  	confidence=n;	
	  	typ=t;
	  	sourceUri="";
	  	state=true;
	}
	public Reading(String s, String n,String t,boolean b) {
		reading=s;
	  	confidence=n;	
	  	typ=t;
	  	sourceUri="";
	  	state=b;
	}
	public Reading(String s) {
		reading=s;
	  	confidence="-1";	
	  	typ="no";
	  	sourceUri="";
	  	state=true;
	}
	public Reading copy() {
		Reading r = new Reading(reading, confidence, typ, state);
		r.setSourceUri(sourceUri);
		return r;
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
        if (n == null) {
            confidence = "";
        } else {
            confidence=n;
        }
	}
    public void setConfidence(int n) {
        if (n < 0) n = 0;
        if (n > 5) n = 5;
        confidence = "" + n;
    }
    public int getConfidenceInt() {
        try {
            return Integer.parseInt(confidence);
        } catch (Exception ex) {
            return 0;
        }
    }


    public String getSourceUri() {
        return sourceUri;
    }

    public void setSourceUri(String sourceUri) {
        if (sourceUri == null) {
            this.sourceUri = "";
        } else {
            this.sourceUri = sourceUri;
        }
    }

}