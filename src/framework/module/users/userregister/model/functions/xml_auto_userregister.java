package framework.module.users.userregister.model.functions;

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
import framework.module.users.userregister.model.classes.User_register;
import framework.module.users.userregister.model.classes.Singleton_userregister;
import framework.module.users.userregister.model.classes.language.Language_userregister;

/**
 * 
 * @author angel
 */
public class xml_auto_userregister {
    
    private static final String ENCODING = "UTF-8";

    /**
     * SAVE FILE XML
     */
    public static void savexml_userregister() {
        String PATH=null;
		
        try {
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/users/userregister/model/functions/files/xml/user_register.xml";
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Singleton_userregister.userregister.size() > 0) {
            try {
                OutputStream OS = new ByteArrayOutputStream();
                OutputStreamWriter OSW = new OutputStreamWriter(OS);
                XStream xstream = new XStream();
                Annotations.configureAliases(xstream, User_register.class);

                String header = "<?xml version=\"1.0\" encoding=\"" + ENCODING + "\"?>\n";
                xstream.toXML(Singleton_userregister.userregister, OSW);
                StringBuffer xml = new StringBuffer();
                xml.append(header);
                xml.append(OS.toString());

                FileWriter fileXml = new FileWriter(PATH);
                fileXml.write(xml.toString());
                fileXml.close();
                OSW.close();
                OS.close();

            }catch (Exception e){
                JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("errorsave_xml"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
            }
        } else {
            File path = new File(PATH);
            path.delete();
        }
    }
	
    /**
     * OPEN FILE XML
     */
    public static void openxml_userregister() {
    	String PATH=null;
    	try {
            XStream xstream = new XStream();
            Annotations.configureAliases(xstream, User_register.class);
 
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/users/userregister/model/functions/files/user_register.xml";
            
            Singleton_userregister.userregister = (ArrayList <User_register>)xstream.fromXML(new FileReader(PATH));
            
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("erroropen_xml"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
