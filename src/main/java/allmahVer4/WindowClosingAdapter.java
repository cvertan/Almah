package allmahVer4;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

class WindowClosingAdapter extends WindowAdapter
{
private boolean exitSystem;
 
/**
  * Erzeugt einen WindowClosingAdapter zum Schliessen
  * des Fensters. Ist exitSystem true, wird das komplette
  * Programm beendet.
  */
public WindowClosingAdapter(boolean exitSystem)
{
this.exitSystem = exitSystem;
}
 
/**
  * Erzeugt einen WindowClosingAdapter zum Schliessen
  * des Fensters. Das Programm wird nicht beendet.
  */
public WindowClosingAdapter()
{
	this(true);
}
 
public void windowClosing(WindowEvent event, boolean isSaved, JDesktopPane desk )
{
	
	if(isSaved) System.exit(0);
	else{
		int confirm= JOptionPane.showOptionDialog(desk,
                "Last changes are not saved. Save and then Exit? ",
                "Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == JOptionPane.YES_OPTION) {
		//	if (saveFile.isEnabled()) saveToFile();
			//	else saveAsToFile();
		}
		else if (confirm == JOptionPane.NO_OPTION)System.exit(0);
	}


}
//


}
