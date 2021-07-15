package allmahVer4;

public class AllmahLaunch {

	public AllmahLaunch() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {

	           @Override
	          
	           public void run() {
	        	  
       	   AllmahGUI desktop= new AllmahGUI();
       	  // desktop.buildInterface();
	       	//	desktop.setLocation(100,100);
	       		//desktop.setSize(1600,1000);
	       		//desktop.setVisible(true);
	           }
	       });
		   

	}

}
