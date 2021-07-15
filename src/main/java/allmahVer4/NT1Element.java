package allmahVer4;
import java.util.ArrayList;
public class NT1Element {
private String label;
private GlyphElementNode parent;
private ArrayList<Reading> selectedReadings;
private ArrayList<Reading>initialReadings;
	public NT1Element(GlyphElementNode g) {
	
	label=g.getLabel();
		if (!label.equals("??"))
		     label=process(label); 
		parent=g;
		selectedReadings=new ArrayList<Reading>();
		initialReadings=new ArrayList<Reading>();
		ArrayList<Reading> r=getReading();
	System.out.println("REading  "+label+" "+r);
	//if (!label.equals("??"))
	if(r!=null) {
		     for (int i=0;i<r.size();i++) selectedReadings.add(r.get(i).copy());
	for (int i=0;i<r.size();i++)initialReadings.add(r.get(i));
	}
	}
	public NT1Element(GlyphElementNode g, String s) {
	
		label=s;
		parent=g;
	}
	public ArrayList<Reading> getSelectedReadings(){
		return selectedReadings;
	}
	public ArrayList<Reading> getInitialReadings(){
		return initialReadings;
	}
	
	public ArrayList<Reading> getSelectedReadings(String s){
		ArrayList<Reading> aux=new ArrayList<Reading>();
		for(int i=0;i<selectedReadings.size();i++)
			aux.add(selectedReadings.get(i).copy());
		return aux;
	}
	public ArrayList<Reading> setSelectedReadings(ArrayList<Reading> r){
		selectedReadings=new ArrayList<Reading>();
		if(r!=null)
				for (int i=0;i<r.size();i++)
					selectedReadings.add(r.get(i).copy());
		return selectedReadings;
	}	
	public ArrayList<Reading> setInitialReadings(ArrayList<Reading> r){
		initialReadings=new ArrayList<Reading>();
		if(r!=null)
				for (int i=0;i<r.size();i++)
					initialReadings.add(r.get(i).copy());
		return initialReadings;
	}
	
	public ArrayList<Reading> setSelectedReadingsCopy(ArrayList<Reading> r){
		selectedReadings=new ArrayList<Reading>();
		if(r!=null)
				for (int i=0;i<r.size();i++)
					selectedReadings.add(r.get(i).copy());
		return selectedReadings;
	}
	public NT1Element copy() {
		NT1Element x= new NT1Element(parent);
		x.setLabel(this.label);
		x.setInitialReadings(this.initialReadings);
		x.setSelectedReadings(this.selectedReadings);
		return x;
	}
	public ArrayList<Reading> getReading(){
		ArrayList<Reading> rr=new ArrayList<Reading>();
		if( label.equals("??")) {
			 rr.add(new Reading("??"));
			 return rr;
		}
		else {
			System.out.println("Find reading for "+label);
			if(! parent.getReadings().isEmpty()) return parent.getReadings();
			else if(!parent.getAlternatives().isEmpty())
				
			{
				String aux=label;
				   if (aux.length()>=3) {
					   String x=aux.substring(aux.length()-2);
					   if (x.matches("[a-zA-Z]+")) aux=aux.substring(0,aux.length()-2);
				   }
				return parent.getAlternatives().get(aux);
			}
			else {
				String aux=label;
				   if (aux.length()>=3) {
					   String x=aux.substring(aux.length()-2);
					   if (x.matches("[a-zA-Z]+")) aux=aux.substring(0,aux.length()-2);
				   }
				 rr.add(new Reading(aux));
				 return rr;
			}
 
		}
	}
	public String toString() {
		return label;
	}
	
	public GlyphElementNode  getParent() {
		return parent;
	}
	public String process (String s) {
		String aux=s;
		if (aux.length()>1) {
		if((aux.indexOf("*")==0)||(aux.indexOf("?")==0))aux=aux.substring(1);
		if((aux.indexOf("*")==aux.length()-1)||(aux.indexOf("?"))==aux.length()-1)aux=aux.substring(0,aux.length()-1);
		}
		return aux;
	}
	public void setLabel(String s) {
		label=s;
		
	}
	public String getLabel() {
		return label;
	}
}
