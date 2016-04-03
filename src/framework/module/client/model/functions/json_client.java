package framework.module.client.model.functions;

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
import framework.module.client.model.classes.Client;
import framework.module.client.model.classes.Singleton_client;
import framework.module.client.model.classes.language.Language_client;

public class json_client {

	public static void savejson_client() {
        String PATH = null;
	      try {
	          XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
	          xstreamjson.setMode(XStream.NO_REFERENCES);
	          xstreamjson.alias("Client", Client.class);
	          	          
	          JFileChooser fileChooser = new JFileChooser();
	          
	          fileChooser.setAcceptAllFileFilterUsed(false);
	            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
	          
	          int selection = fileChooser.showSaveDialog(null);
	          if (selection == JFileChooser.APPROVE_OPTION) {
	                File JFC = fileChooser.getSelectedFile();
	                PATH = JFC.getAbsolutePath();
	                PATH=PATH+ ".json";
	                
	                Gson gson = new Gson();
		            String json = gson.toJson(Singleton_client.userclient);
		            FileWriter fileXml = new FileWriter(PATH);
	                fileXml.write(json.toString());
	                fileXml.close(); 
	                
	                JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("savejson"), "JSON", JOptionPane.INFORMATION_MESSAGE);
	          }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("errorsave_json"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public static void openjson_client() {
    	String PATH = null;
    	Client c1=null;
    	
        try {
        	  XStream xstream = new XStream(new JettisonMappedXmlDriver());
	          xstream.setMode(XStream.NO_REFERENCES);
			  xstream.alias("Client", Client.class);
			  
			  JFileChooser fileChooser = new JFileChooser();
			  
			  fileChooser.setAcceptAllFileFilterUsed(false);
	            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
			  
	          int selection = fileChooser.showOpenDialog(null);
	          if (selection == JFileChooser.APPROVE_OPTION) {
	                File JFC = fileChooser.getSelectedFile();
	                PATH = JFC.getAbsolutePath();
	               
	                Singleton_client.userclient.clear();
	                	              
	                JsonReader reader = new JsonReader(new FileReader(PATH));
	                JsonParser parser = new JsonParser();
	                JsonElement root = parser.parse(reader);
	            		  
	            	Gson json = new Gson();
	            	JsonArray list = root.getAsJsonArray();
	            	for (JsonElement element : list) {
	            		c1 = json.fromJson(element, Client.class);
	            		Singleton_client.userclient.add(c1);
	            	}
	          } 
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("erroropen_json"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
