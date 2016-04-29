package framework.module.users.admin.model.functions;

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
import framework.module.users.admin.model.classes.Admin;
import framework.module.users.admin.model.classes.Singleton_admin;
import framework.module.users.admin.model.classes.language.Language_admin;

/**
 * 
 * @author angel
 */
public class json_auto_admin {

    public static void savejson_admin() {
        String PATH = null;

        try {
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/users/admin/model/functions/files/json/admin.json";
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Singleton_admin.useradmin.size() > 0) {
            try {
                XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
                xstreamjson.setMode(XStream.NO_REFERENCES);
                xstreamjson.alias("Admin", Admin.class);

                Gson gson = new Gson();
                    String json = gson.toJson(Singleton_admin.useradmin);
                    FileWriter fileXml = new FileWriter(PATH);
                fileXml.write(json.toString());
                fileXml.close(); 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, Language_admin.getInstance().getProperty("errorsave_json"), Language_admin.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
            }
        } else {
            File path = new File(PATH);

            path.delete();
        }
    }

    public static void openjson_admin() {
        String PATH = null;
        Admin a1=null;

        try {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            xstream.setMode(XStream.NO_REFERENCES);
            xstream.alias("Admin", Admin.class);

            PATH = new java.io.File(".").getCanonicalPath()
              + "/src/framework/module/users/admin/model/functions/files/json/admin.json";

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
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, Language_admin.getInstance().getProperty("erroropen_json"), Language_admin.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
