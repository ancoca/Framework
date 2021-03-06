package framework.module.users.userregister.model.functions;

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
import framework.module.users.userregister.model.classes.User_register;
import framework.module.users.userregister.model.classes.Singleton_userregister;
import framework.module.users.userregister.model.classes.language.Language_userregister;

/**
 * 
 * @author angel
 */
public class json_userregister {

    /**
     * SAVE FILE JSON
     */
    public static void savejson_userregister() {
        String PATH = null;
        try {
            XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
            xstreamjson.setMode(XStream.NO_REFERENCES);
            xstreamjson.alias("User_register", User_register.class);

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));

            int selection = fileChooser.showSaveDialog(null);
            if (selection == JFileChooser.APPROVE_OPTION) {
                File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();
                PATH=PATH+ ".json";

                Gson gson = new Gson();
                    String json = gson.toJson(Singleton_userregister.userregister);
                    FileWriter fileXml = new FileWriter(PATH);
                fileXml.write(json.toString());
                fileXml.close(); 

                JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("savejson"), "JSON", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("errorsave_json"), Language_userregister.getInstance().getProperty("error "), JOptionPane.ERROR_MESSAGE);
        }
    }
	
    /**
     * OPEN FILE JSON
     */
    public static void openjson_userregister() {
    	String PATH = null;
    	User_register u1=null;
    	
        try {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            xstream.setMode(XStream.NO_REFERENCES);
            xstream.alias("User_register", User_register.class);

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));

            int selection = fileChooser.showOpenDialog(null);
            if (selection == JFileChooser.APPROVE_OPTION) {
                File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();

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
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("erroropen_json"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
