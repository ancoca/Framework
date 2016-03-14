package framework.module.admin.model.functions;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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

public class json_admin {

	public static void savejson_admin() {
        String PATH = null;
	      try {
	          XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
	          xstreamjson.setMode(XStream.NO_REFERENCES);
	          xstreamjson.alias("Admin", Admin.class);
	          	          
	          JFileChooser fileChooser = new JFileChooser();
	          
	          fileChooser.setAcceptAllFileFilterUsed(false);
	            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
	            
	          int selection = fileChooser.showSaveDialog(null);
	          if (selection == JFileChooser.APPROVE_OPTION) {
	                File JFC = fileChooser.getSelectedFile();
	                PATH = JFC.getAbsolutePath();
	                PATH=PATH+ ".json";
	                
	                Gson gson = new Gson();
		            String json = gson.toJson(Singleton_admin.useradmin);
		            FileWriter fileXml = new FileWriter(PATH);
	                fileXml.write(json.toString());
	                fileXml.close(); 
	                
	                JOptionPane.showMessageDialog(null, "Archivo JSON guardado con exito", "Archivo JSON", JOptionPane.INFORMATION_MESSAGE);
	          }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("errorsave_json"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public static void openjson_admin() {
    	String PATH = null;
    	Admin a1=null;
    	
        try {
        	  XStream xstream = new XStream(new JettisonMappedXmlDriver());
	          xstream.setMode(XStream.NO_REFERENCES);
			  xstream.alias("Admin", Admin.class);
			  
			  JFileChooser fileChooser = new JFileChooser();
			  
			  fileChooser.setAcceptAllFileFilterUsed(false);
	            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
			  
	          int selection = fileChooser.showOpenDialog(null);
	          if (selection == JFileChooser.APPROVE_OPTION) {
	                File JFC = fileChooser.getSelectedFile();
	                PATH = JFC.getAbsolutePath();
	               
	                Singleton_admin.useradmin.clear();
	                	              
	                JsonReader reader = new JsonReader(new FileReader(PATH));
	                JsonParser parser = new JsonParser();
	                JsonElement root = parser.parse(reader);
	            		  
	            	Gson json = new Gson();
	            	JsonArray list = root.getAsJsonArray();
	            	for (JsonElement element : list) {
	            		a1 = json.fromJson(element, Admin.class);
	            		Singleton_admin.useradmin.add(a1);
	            	}
	          } 
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("erroropen_json"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
