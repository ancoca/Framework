package framework.module.users.admin.model.functions;

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
import framework.module.users.admin.model.classes.Admin;
import framework.module.users.admin.model.classes.Singleton_admin;
import framework.module.users.admin.model.classes.language.Language_admin;

/**
 * 
 * @author angel
 */
public class xml_admin {
    
    private static final String ENCODING = "UTF-8";
	
    /**
     * SAVE FILE XML
     */
    public static void savexml_admin() {
        String PATH=null;
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
                JOptionPane.showMessageDialog(null, Language_admin.getInstance().getProperty("savexml"), "XML", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, Language_admin.getInstance().getProperty("errorsave_xml"), Language_admin.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        } 
    }
	
    /**
     * OPEN FILE XML
     */
    public static void openxml_admin() {
    	String PATH=null;
    	try {
            XStream xstream = new XStream();
            Annotations.configureAliases(xstream, Admin.class);
 
            JFileChooser fileChooser = new JFileChooser();
            
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));
            
            int selection = fileChooser.showOpenDialog(null);
            if (selection == JFileChooser.APPROVE_OPTION) {
            	File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();
                Singleton_admin.useradmin = (ArrayList <Admin>)xstream.fromXML(new FileReader(PATH));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language_admin.getInstance().getProperty("erroropen_xml"), Language_admin.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
