package allmahVer4;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import info.textgrid.clients.SearchClient;
import info.textgrid.clients.tgsearch.SearchQueryBuilder;
import info.textgrid.namespaces.middleware.tgsearch.Response;
import info.textgrid.namespaces.middleware.tgsearch.ResultType;

import org.classicmayan.tools.IdiomTextGridObject;
import org.classicmayan.tools.TGSearchQueries;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class TextGridReadFile {
     private Map<String,String> namesFiles;
     private String sid;
	public TextGridReadFile(String sid) {
		namesFiles=new HashMap<String,String>();
		this.sid=sid;
	}
	public Map<String,String>  fileList() {
		try {
		TGSearchQueries t = new TGSearchQueries("src/main/resources/idiomQueries-SECRET-prod.properties");
		t.setSessionID(sid);
		System.out.println("TextGridReadFile SID [" + sid + "]");
		System.out.println("TGSearchQueries SID [" + t.getSessionID() + "]");
		System.out.println("****" + t.getTextgridSearchEndpoint());
       // HashMap<String, String> test = t.getTitleWithURI();
		// HashMap<String, String> test = t. getTextCarrierFiles();
		namesFiles=t.getTextCarrierFiles();
       for (String name: namesFiles.keySet()){
           String key =name.toString();
            String value = namesFiles.get(name).toString(); 
      //      namesFiles.add(key + " || " + value);
           System.out.println(key + " || " + value);
        }
		}
        catch(Exception e) {System.out.println("Exception"); e.printStackTrace();}
		
		return namesFiles;
	}

}
