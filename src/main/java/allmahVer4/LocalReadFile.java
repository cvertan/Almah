package allmahVer4;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.classicmayan.tools.IDIOMTextGridObject;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class LocalReadFile {
     AllmahGUI interf;
	 private File fileRoot;
	 private String FileOpenedName;
	public LocalReadFile(AllmahGUI a) {
		// TODO Auto-generated constructor stub
		interf=a;
		FileOpenedName="";
	}
	
	public File getChoosedFile() {
		JFileChooser fc = Utils.getFileChooser();
 		String userhome = System.getProperty("user.home");
 		 fc = new JFileChooser(userhome);
 		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML FILES", "xml");
 		fc.setFileFilter(filter);
 		int returnVal = fc.showOpenDialog(interf);	
 		if(returnVal==JFileChooser.APPROVE_OPTION){
 			
 		fileRoot=fc.getSelectedFile();
 	//	FileOpenedName=fileRoot.getAbsolutePath();
 		}
 		return fileRoot;
	}
	
	

}
