package allmahVer4;
import java.util.ArrayList;
import java.util.Random;
import org.classicmayan.tools.*;
public class BlockAnnotation {
private String id;
private String comment;
private String typ;
private String subtyp;
private Activity ac;
private Dedication ded;
private Place pl;
private EpigraphicActor ea;
private EpigraphicGroup ep;

private ArrayList<String> blocIDs;
//private AllmahGUI interf;
public BlockAnnotation() {
    init();
}
	public BlockAnnotation(String docId, String typ) {
        init();
		Random r=new Random();
	id=docId+">Ann"+r.nextInt();
		this.typ=typ;
		//=tag;
	}
private void init() {
    blocIDs=new ArrayList<String>();
    ac=new Activity();ded=new Dedication();
    pl=new Place();ea=new EpigraphicActor();
    ep=new EpigraphicGroup();
}
public void setId(String s) {
    id=s;
}
public void setTyp(String s) {
    typ=s;
}
public void setSubTyp(String s) {
	subtyp=s;
}
public String getSubTyp() {
	return subtyp;
}
public String getId() {
	return id;
}
public String getTyp() {
	return typ;
}
public void setComment(String s) {
	comment=s;
}
public String getComment() {
	return comment;
}
public Activity getActivity() {
	return ac;
}
public void setActivity(Activity a) {
    if (a != null) ac=a;
}

public Dedication getDedication() {
	return ded;
}
public void setDedication(Dedication d) {
    if (d != null) ded=d;
}

public Place getPlace() {
	return pl;
}
public void setPlace(Place p) {
    if (p != null) pl=p;
}

public EpigraphicActor getEpigraphicActor() {
	return ea;
}
public void setEpigraphicActor(EpigraphicActor a) {
    if (a != null) ea=a;
}
public EpigraphicGroup getEpigraphicGroup() {
	return ep;
}
public void setEpigraphicGroup(EpigraphicGroup g) {
    if (g != null) ep=g;
}
public ArrayList<String> getAnnBorders(){
	return blocIDs;
}
public void setAnnBorders(ArrayList<String> bids) {
    if (bids == null) return;
	for (int i=0;i<bids.size();i++) {
		blocIDs.add(bids.get(i));
	}
}
}