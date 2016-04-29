package framework.module.users.userregister.model.functions;

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
import framework.module.users.userregister.model.classes.User_register;
import framework.module.users.userregister.model.classes.Singleton_userregister;
import framework.module.users.userregister.model.classes.language.Language_userregister;

/**
 * 
 * @author angel
 */
public class xml_userregister {
    
    private static final String ENCODING = "UTF-8";
	
    /**
     * SAVE FILE XML
     */
    public static void savexml_userregister() {
        String PATH=null;
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
                JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("savexml"), "XML", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("errorsave_xml"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
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
 
            JFileChooser fileChooser = new JFileChooser();
            
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));
            
            int selection = fileChooser.showOpenDialog(null);
            if (selection == JFileChooser.APPROVE_OPTION) {
            	File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();
                Singleton_userregister.userregister = (ArrayList <User_register>)xstream.fromXML(new FileReader(PATH));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("erroropen_xml"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
