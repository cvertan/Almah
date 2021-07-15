package allmahVer4;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import info.textgrid.clients.SearchClient;
import info.textgrid.clients.tgsearch.SearchQueryBuilder;
import info.textgrid.namespaces.middleware.tgsearch.Response;
import info.textgrid.namespaces.middleware.tgsearch.ResultType;
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
		TGSearchQueries t = new TGSearchQueries();
	
		System.out.println("****");
        HashMap<String, String> test = t.getTitleWithURI();
        System.out.println("**** " + test.size());
        for (String name: test.keySet()){
            String key =name.toString();
            String value = test.get(name).toString(); 
      //      namesFiles.add(key + " || " + value);
         //   System.out.println(key + " || " + value);
        }
		}
        catch(Exception e) {System.out.println("Exception");}
		 SearchClient searchClient = new
				 SearchClient(SearchClient.DEFAULT_NONPUBLIC_ENDPOINT)
				                     .enableGzipCompression();           			            
				               try  {
				               /*    InputStream input = new
				 FileInputStream("src/main/resources/idiomQueries.properties");

				                   Properties prop = new Properties();
				                   prop.load(input);*/
                           
				            if(searchClient!=null)    {  
				            	SearchQueryBuilder sb=searchClient.searchQuery();
				          
				            	//sb.setSid(prop.getProperty("tg.sessionID"));
				            	sb.setSid(sid);
				            	System.out.println("Session id "+sb.getSid());
				            	//sb.setQuery( "project.id:" +prop.getProperty("tg.projectID"));
				            	sb.setQuery( "project.id:" +"TGPR-0e926f53-1aba-d415-ecf6-539edcd8a318");
				            	//sb.addFilter(prop.getProperty("tg.formatToFilter"));
				            	sb.addFilter("format:text/xml");
				            	//sb.setLimit(Integer.parseInt(prop.getProperty("tg.querySize")));
				            	sb.setLimit(1000);
				            	 System.out.println("Query "+sb.getQuery());
				            	 System.out.println("Limit "+sb.getLimit());
				            	 System.out.println("Target "+sb.getTarget() +"  "+ sb.toString());
				            	
				            	 Response response=sb.execute();
				            	
				            System.out.println("Response "+response.getResult());
				            	// Response response=sb.execute();
				            	              
				    	         System.out.println("Response "+response.toString()+" ## "+response.getSession());
				                  System.out.println("*****  " + response.getResult()+" "+response.getHits()+" "+response.getLimit());
				                    for(ResultType result : response.getResult()) {
				                      String tguri = result.getObject().getGeneric()
				                                     
				 .getGenerated().getTextgridUri().getValue();
				                      String title =result.getObject().getGeneric()
				                                      .getProvided().getTitle().get(0);
				                   //   if(title.startsWith("Uxul")) {
				                          System.out.println(tguri + " : " + title);
				                          namesFiles.put(tguri,title);
				                      //}
				                     // else System.out.println("Nothing found");
				              }
				               }
				               
				              
				               
				     else System.out.println("search client null");
				               }
				             catch (Exception e) {
				                   // TODO Auto-generated catch block
				                         System.out.println("No config file found");
				                        e.printStackTrace();
				                    }
		return namesFiles;
	}

}
