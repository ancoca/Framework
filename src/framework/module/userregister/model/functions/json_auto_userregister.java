package framework.module.userregister.model.functions;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import framework.module.admin.model.classes.Admin;
import framework.module.admin.model.classes.Singleton_admin;
import framework.module.client.model.classes.Client;
import framework.module.client.model.classes.Singleton_client;
import framework.module.userregister.model.classes.User_register;
import framework.module.config.model.classes.language2.Language_user;
import framework.module.userregister.model.classes.Singleton_userregister;

public class json_auto_userregister {

	public static void savejson_userregister() {
        String PATH = null;
		      
        try {
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/userregister/model/functions/files/json/user_register.json";
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Singleton_userregister.userregister.size() > 0) {
        	try {
		          XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
		          xstreamjson.setMode(XStream.NO_REFERENCES);
		          xstreamjson.alias("User_register", User_register.class);
		                
		          Gson gson = new Gson();
			      String json = gson.toJson(Singleton_userregister.userregister);
			      FileWriter fileXml = new FileWriter(PATH);
		          fileXml.write(json.toString());
		          fileXml.close(); 
		          
	        } catch (Exception e) {
	        	JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("errorsave_json"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
	        }
		} else {
	        File path = new File(PATH);
	
	        path.delete();
		}
    }
	
	public static void openjson_userregister() {
    	String PATH = null;
    	User_register u1=null;
    	
        try {
        	  XStream xstream = new XStream(new JettisonMappedXmlDriver());
	          xstream.setMode(XStream.NO_REFERENCES);
			  xstream.alias("User_register", User_register.class);
			  
			  PATH = new java.io.File(".").getCanonicalPath()
	                    + "/src/framework/module/userregister/model/functions/files/json/user_register.json";
	               
	          Singleton_userregister.userregister.clear();
	          	              
	          JsonReader reader = new JsonReader(new FileReader(PATH));
	          JsonParser parser = new JsonParser();
	          JsonElement root = parser.parse(reader);
	          
	          Gson json = new Gson();
	          JsonArray list = root.getAsJsonArray();
	          for (JsonElement element : list) {
	          	u1 = json.fromJson(element, User_register.class);
	          	Singleton_userregister.userregister.add(u1);
	          }
	           
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("erroropen_json"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
