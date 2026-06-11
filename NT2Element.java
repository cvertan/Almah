package allmahVer4;
import java.util.ArrayList;

import org.jsoup.Jsoup;
public class NT2Element {
private String label;
private NT1Element parent;
private ComponentElement cg;
private ArrayList<Reading> assignedReadings;

	public NT2Element(NT1Element n) {
		parent=n;
		label=process(n.getLabel());	
		assignedReadings=new ArrayList<Reading>();
		
	}

	public ComponentElement getComponentElement() {
		return cg;
	}
	public void setComplexGlyph(ComponentElement cg) {
		this.cg=cg;
	}
	public NT2Element copy() {
		NT2Element x= new NT2Element(parent);
		x.setComplexGlyph(this.cg);
		x.setLabel(this.label);
		x.setAssignedReadings(this.assignedReadings);
		return x;
	}
	public ArrayList<Reading> getAssignedReadings(){
		return assignedReadings;
	}
	public ArrayList<Reading> setAssignedReadings(ArrayList<Reading> r){
		assignedReadings=new ArrayList<Reading>();
		if(r!=null)
			for (int i=0;i<r.size();i++)
				if(r.get(i)!=null) assignedReadings.add(r.get(i).copy());
		return assignedReadings;
	}
	private ArrayList<Reading> copyReadings(ArrayList<Reading> source){
		ArrayList<Reading> aux=new ArrayList<Reading>();
		if(source!=null)
			for (int i=0;i<source.size();i++)
				if(source.get(i)!=null) aux.add(source.get(i).copy());
		return aux;
	}
	public ArrayList<Reading> getReadings(){
		if(assignedReadings!=null && !assignedReadings.isEmpty()) return copyReadings(assignedReadings);
		return parent.getSelectedReadings();
	}
	public ArrayList<Reading> getReadings(String s){
		if(assignedReadings!=null && !assignedReadings.isEmpty()) return copyReadings(assignedReadings);
		ArrayList<Reading> aux=new ArrayList<Reading>();
		for (int i=0;i<parent.getSelectedReadings().size();i++)
			aux.add(parent.getSelectedReadings().get(i).copy());
		return aux ;
	}
	public NT1Element getParent(){
		return parent;
	}
	public String process (String s) {
		String aux=s;
		   if (aux.length()>=3) {
			   String x=aux.substring(aux.length()-2);
			   if (x.matches("[a-zA-Z]+")) aux=aux.substring(0,aux.length()-2);
		   }
		return aux;
	}
	public void setLabel(String s) {
		label=s;
		
	}
	public String encodeComponentColor() {
		String hex=String.format("#%02x%02x%02x", cg.getcolor().getRed(), cg.getcolor().getGreen(), cg.getcolor().getBlue());
		return hex;
	}
	public String getLabel() {
		if (cg!=null) return "<html><font color=\""+encodeComponentColor()+"\">"+label+"</font><html>"; 
		return label;
	}
	public String getSimpleLabel() {
		String s=label;
		if( label.indexOf("<html>")==0) s=Jsoup.parse(label).text();
		return s;
	}
}