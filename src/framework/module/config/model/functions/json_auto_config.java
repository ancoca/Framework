package framework.module.config.model.functions;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import framework.module.config.model.classes.ClassConfig;
import framework.module.config.model.classes.language.Language_general;
import framework.functions.Functions_theme;
import framework.module.admin.model.classes.Admin;

public class json_auto_config {

	public static void savejson_config() {
        String PATH = null;
	     
        try {
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/config/model/functions/files/config.json";
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	    try {
		    XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
		    xstreamjson.setMode(XStream.NO_REFERENCES);
		    xstreamjson.alias("ClassConfig", ClassConfig.class);
		    Gson gson = new Gson();
		    String json = gson.toJson(ClassConfig.getInstance());
		    FileWriter fileXml = new FileWriter(PATH);
		    fileXml.write(json.toString());
		    fileXml.close();
		    
		    
	    } catch (Exception e) {
	     	JOptionPane.showMessageDialog(null, Language_general.getInstance().getProperty("errorsave_json"), Language_general.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public static void openjson_config() {
    	String PATH = null;
    	ClassConfig config = null;
    	
        try {
        	XStream xstream = new XStream(new JettisonMappedXmlDriver());
	        xstream.setMode(XStream.NO_REFERENCES);
                xstream.alias("Admin", Admin.class);

                PATH = new java.io.File(".").getCanonicalPath()
                                + "/src/framework/module/config/model/functions/files/config.json";
	        
	        JsonReader reader = new JsonReader(new FileReader(PATH));
	        JsonParser parser = new JsonParser();
	        JsonElement root = parser.parse(reader);
	        
	        Gson json = new Gson();
	        config = json.fromJson(root, ClassConfig.class);
	        ClassConfig.getInstance().setCurrency(config.getCurrency());
	        ClassConfig.getInstance().setDate(config.getDate());
	        ClassConfig.getInstance().setDecimal(config.getDecimal());
	        ClassConfig.getInstance().setFile(config.getFile());
	        ClassConfig.getInstance().setLanguage(config.getLanguage());
	        ClassConfig.getInstance().setTheme(config.getTheme());
	        
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_general.getInstance().getProperty("erroropen_json"), Language_general.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
