package allmahVer4;

public class GT2Element {
public GT1Element parentElement;
public ComponentElement componentParent;
public String label;
	public GT2Element(GT1Element gt1) {
		parentElement=gt1;
		label=parentElement.getLabel();
		componentParent=null;
	}
	public GT2Element(ComponentElement comp) {
		parentElement=null;
		label=comp.toString();
		componentParent=comp;
	}
	public GT2Element() {
		parentElement=null;
		label="NEW";
		componentParent=null;
	}
	public GT2Element copy(String s) {
		GT2Element ge;
		if  ( parentElement!=null) {
		ge=new GT2Element(parentElement);
		ge.setLabel(s);
		}
		
		else ge=new GT2Element(componentParent);
		return ge;
	}

public GT1Element getParentGT1() {
	return parentElement;
}
public ComponentElement getParenComponent() {
	return componentParent;
}
public void setLabel(String s) {
	label=s;
}
public String getLabel() {
	return label;
}
}
