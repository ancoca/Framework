package framework.module.admin.model.functions;

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
import framework.module.admin.model.classes.Admin;
import framework.module.admin.model.classes.Singleton_admin;
import framework.module.admin.model.classes.language.Language_admin;

public class xml_auto_admin {
	private static final String ENCODING = "UTF-8";
	
	public static void savexml_admin() {
        String PATH=null;
		
        try {
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/admin/model/functions/files/xml/admin.xml";
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Singleton_admin.useradmin.size() > 0) {
	        try {
				OutputStream OS = new ByteArrayOutputStream();
				OutputStreamWriter OSW = new OutputStreamWriter(OS);
				XStream xstream = new XStream();
				Annotations.configureAliases(xstream, Admin.class);
	
	            String header = "<?xml version=\"1.0\" encoding=\"" + ENCODING + "\"?>\n";
	            xstream.toXML(Singleton_admin.useradmin, OSW);
	            StringBuffer xml = new StringBuffer();
	            xml.append(header);
	            xml.append(OS.toString());
	            
	            FileWriter fileXml = new FileWriter(PATH);
	            fileXml.write(xml.toString());
	            fileXml.close();
	            OSW.close();
	            OS.close();
	            
		    }catch (Exception e){
		    	JOptionPane.showMessageDialog(null, Language_admin.getInstance().getProperty("errorsave_xml"), Language_admin.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
		    }
        } else {
            File path = new File(PATH);

            path.delete();
        }
    }
	
	public static void openxml_admin() {
    	String PATH=null;
    	try {
            XStream xstream = new XStream();
            Annotations.configureAliases(xstream, Admin.class);
 
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/admin/model/functions/files/xml/admin.xml";
            
            Singleton_admin.useradmin = (ArrayList <Admin>)xstream.fromXML(new FileReader(PATH));
            
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_admin.getInstance().getProperty("erroropen_xml"), Language_admin.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
