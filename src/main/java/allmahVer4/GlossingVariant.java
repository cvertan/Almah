package allmahVer4;

public class GlossingVariant {
private  MorphoTranscrNode parent;
private SyntacticAnnotation syntax;
private SemanticAnnotation semantic;
	public GlossingVariant(MorphoTranscrNode p,SyntacticAnnotation syn, SemanticAnnotation sem) {
		parent=p;
		syntax=new SyntacticAnnotation(syn.getPosCluster(),syn.getPos(),syn.getSyntFeature());
		//syntax=new SyntacticAnnotation(syn.getSyntFeature().getAbbreviation());
		semantic=new SemanticAnnotation(sem.getMeaning());
		if (sem.getLinkWN()!=null)
			semantic.setLinkWN(sem.getLinkWN());
	}
	
	public GlossingVariant(MorphoTranscrNode p, SemanticAnnotation sem) {
		parent=p;
		syntax=null;
		semantic=new SemanticAnnotation(sem.getMeaning());
		if (sem.getLinkWN()!=null)
			semantic.setLinkWN(sem.getLinkWN());
	}
	public GlossingVariant copy() {
		GlossingVariant x=new GlossingVariant(this.parent, this.syntax, this.semantic);
		return x;
	}
public SyntacticAnnotation getSyntax() {
	return syntax;
}
public SemanticAnnotation getSemantics() {
	return semantic;
}
public MorphoTranscrNode getParent() {
	return parent;
}
public void setSyntax(SyntacticAnnotation syn) {
	syntax=new SyntacticAnnotation(syn.getPosCluster(),syn.getPos(),syn.getSyntFeature());
}
public void setSemanticAnnotation(SemanticAnnotation sem ) {
	semantic=new SemanticAnnotation(sem.getMeaning());
	if (sem.getLinkWN()!=null)
		semantic.setLinkWN(sem.getLinkWN());
}
}
