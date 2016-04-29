package framework.module.users.client.model.functions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import framework.module.users.client.model.classes.Client;
import framework.module.users.client.model.classes.Singleton_client;
import framework.module.users.client.model.classes.language.Language_client;

/**
 * 
 * @author angel
 */
public class xml_auto_client {
    
    private static final String ENCODING = "UTF-8";
	
    /**
     * SAVE FILE XML
     */
    public static void savexml_client() {
        String PATH=null;
		
        try {
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/users/client/model/functions/files/xml/client.xml";
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Singleton_client.userclient.size() > 0) {
            try {
                OutputStream OS = new ByteArrayOutputStream();
                OutputStreamWriter OSW = new OutputStreamWriter(OS);
                XStream xstream = new XStream();
                Annotations.configureAliases(xstream, Client.class);

                String header = "<?xml version=\"1.0\" encoding=\"" + ENCODING + "\"?>\n";
                xstream.toXML(Singleton_client.userclient, OSW);
                StringBuffer xml = new StringBuffer();
                xml.append(header);
                xml.append(OS.toString());

                FileWriter fileXml = new FileWriter(PATH);
                fileXml.write(xml.toString());
                fileXml.close();
                OSW.close();
                OS.close();

            }catch (Exception e){
                JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("errorsave_xml"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
            }
        } else {
            File path = new File(PATH);

            path.delete();
        }
    }
	
    /**
     * OPEN FILE XML
     */
    public static void openxml_client() {
    	String PATH=null;
    	try {
            XStream xstream = new XStream();
            Annotations.configureAliases(xstream, Client.class);
 
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/users/client/model/functions/files/xml/client.xml";
            
            Singleton_client.userclient = (ArrayList <Client>)xstream.fromXML(new FileReader(PATH));
            
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("erroropen_xml"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
