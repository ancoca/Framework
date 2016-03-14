package framework.module.client.model.functions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;

import framework.module.admin.model.classes.Admin;
import framework.module.admin.model.classes.Singleton_admin;
import framework.module.client.model.classes.Client;
import framework.module.client.model.classes.Singleton_client;
import framework.module.userregister.model.classes.User_register;
import framework.module.config.model.classes.language2.Language_user;
import framework.module.userregister.model.classes.Singleton_userregister;

public class xml_client {
	private static final String ENCODING = "UTF-8";
	
	public static void savexml_client() {
        String PATH=null;
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
	    
            JFileChooser fileChooser = new JFileChooser();
            
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));
            
            int selection = fileChooser.showSaveDialog(null);
            if (selection == JFileChooser.APPROVE_OPTION) {
            	File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();
                PATH = PATH+".xml";
                
                FileWriter fileXml = new FileWriter(PATH);
                fileXml.write(xml.toString());
                fileXml.close();
                OSW.close();
                OS.close();
                JOptionPane.showMessageDialog(null, "Archivo XML guardado con exito", "Archivo XML", JOptionPane.INFORMATION_MESSAGE);
            }
	    }catch (Exception e){
	    	JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("errorsave_xml"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
	    } 
    }
	
	public static void openxml_client() {
    	String PATH=null;
    	try {
            XStream xstream = new XStream();
            Annotations.configureAliases(xstream, Client.class);
 
            JFileChooser fileChooser = new JFileChooser();
            
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));
            
            int selection = fileChooser.showOpenDialog(null);
            if (selection == JFileChooser.APPROVE_OPTION) {
            	File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();
                Singleton_client.userclient = (ArrayList <Client>)xstream.fromXML(new FileReader(PATH));
            }
            
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("erroropen_xml"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
