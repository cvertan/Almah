package allmahVer4;
import java.util.ArrayList;
import java.util.Map;
import org.jsoup.*;
public abstract class TreeNode {
    private String label;
    private int level;
    private boolean finish;
    private int interpretationConfidence;
   private String fin;
	public TreeNode(int n) {
		level=n;
		finish=false;
		interpretationConfidence=0;
		fin="";
	}
    public String  getNodeLabel() {
    	String s=calculateLabel();
        if (level == 0) {
            return s;
        }
        s = s + getConfidenceMarkup();
    	if (finish) {
    	if( level==1) return "<html>"+fin+"<font color=\"red\"> <b>Num Tr 1: </b> </font>"+s +"</html>";
    	else if( level==2) return  "<html>"+fin+" <font color=\"blue\"> <b>Num Tr 2:</b>  </font>"+s +"</html>";
    	else if( level==3) return  "<html>"+fin+" <font color=\"gray\"> <b>Graphical Tr 1:  </b></font>"+s +"</html>";
    	else if( level==4) return  "<html>"+fin+" <font color=\"black\"> <b>Graphical Tr 2: </b> </font>"+s +"</html>";
    	else if( level==5) return "<html>"+fin+" <font color=\"green\"> <b>Phonemic Tr: </b> </font>"+s +"</html>";
    	else if( level==6) return  "<html>"+fin+" <font color=\"#FF00FF\"> <b>Morphosynt. Tr: </b> </font>"+s.substring(0,s.indexOf("$")) +" \t\t <font color=\"#c63678\"> <b>Morphosynt. Consolidated Tr: </b> </font>"+s.substring(s.indexOf("$")+1,s.indexOf("#") )+"</html>";
    	//#c63678
    	else if( level==7) return "<html>"+fin+" <font color=\"#00FF00\"> <b>MorphoSyntGlosing: </b> </font>"+s +"</html>";
    	else if( level==8) return "<html>"+fin+" <font color=\"#681703\"> <b>Consolidated Transcription: </b> </font>"+s.substring(0,s.indexOf("#")) +"\t\t <font color=\"#681600\"> <b>Translation: </b> </font>"+s.substring(s.indexOf("#")+1)+ "</html>";
    	else return s;
    	}
    	else {
    		
    		if( level==1) return "<html><font color=\"red\"> <b>Num Tr 1: </b> </font>"+s +"</html>";
        	else if( level==2) return  "<html> <font color=\"blue\"> <b>Num Tr 2:</b>  </font>"+s +"</html>";
        	else if( level==3) return  "<html> <font color=\"gray\"> <b>Graphical Tr 1:  </b></font>"+s +"</html>";
        	else if( level==4) return  "<html> <font color=\"black\"> <b>Graphical Tr 2: </b> </font>"+s +"</html>";
        	else if( level==5) return "<html> <font color=\"green\"> <b>Phonemic Tr: </b> </font>"+s +"</html>";
        	else if( level==6) return  "<html>"+fin+" <font color=\"#FF00FF\"> <b>Morphosynt. Tr: </b> </font>"+s.substring(0,s.indexOf("$")) +" \t\t <font color=\"#c63678\"> <b>Morphosynt. Consolidated Tr: </b> </font>"+s.substring(s.indexOf("$")+1,s.indexOf("#") )+"</html>";
        	else if( level==7) return "<html>"+fin+" <font color=\"yellow\"> <b>MorphoSyntGlosing: </b> </font>"+s +"</html>";
        	else if( level==8) return "<html>"+fin+" <font color=\"#681703\"> <b>Consolidated Transcription: </b> </font>"+s.substring(0,s.indexOf("#")) +"\t\t<font color=\"black\"> <b>Translation: </b> </font>"+s.substring(s.indexOf("#")+1)+ "</html>";
        	else return s;
    	}
    }
    public void setFinish(boolean b) {
    	finish=b;
    	if (!b) fin="";
    	else fin="<font color=\"red\"><b>X</b></font>";
    }
    public boolean getFinish() {
    	return finish;
    }
    public int getLevel() {
    	return level;
    }

    private String getConfidenceMarkup() {
        if (interpretationConfidence <= 0) {
            return "";
        }
        return " <font color=\"#7a3db8\"><i>[conf: " + interpretationConfidence + "]</i></font>";
    }
    public int getInterpretationConfidence() {
        return interpretationConfidence;
    }
    public int getConfidence() {
        return interpretationConfidence;
    }
    public void setInterpretationConfidence(int c) {
        if (c < 0) c = 0;
        if (c > 5) c = 5;
        interpretationConfidence = c;
    }
    public void setConfidence(int c) {
        setInterpretationConfidence(c);
    }
    public void setInterpretationConfidence(String c) {
        try {
            setInterpretationConfidence(Integer.parseInt(c));
        } catch (Exception ex) {
            interpretationConfidence = 0;
        }
    }

   
    public abstract String calculateLabel();
	 public  abstract ArrayList<TreeNode> listNodes();
	public abstract String getParent();
}