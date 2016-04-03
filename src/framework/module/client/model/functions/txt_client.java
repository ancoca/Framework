package framework.module.client.model.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import framework.module.client.model.classes.Client;
import framework.module.client.model.classes.Singleton_client;
import framework.module.client.model.classes.language.Language_client;

public class txt_client {

	public static void savetxt_client() {
        String PATH = null;
        try {
            File file;
            JFileChooser fileChooser = new JFileChooser();
            
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Texto (*.txt)", "txt"));
            
            int selection = fileChooser.showSaveDialog(null);
            if (selection == JFileChooser.APPROVE_OPTION) {
                File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();
                PATH=PATH+ ".txt";
                file = new File(PATH);
                
                FileOutputStream FOS=new FileOutputStream(file);
				ObjectOutputStream OOS=new ObjectOutputStream(FOS);
				OOS.writeObject(Singleton_client.userclient);
				OOS.close();
                JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("savetxt"), "TXT", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("errorsave_txt"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public static void opentxt_client() {
    	String PATH = null;
        try {
            File f;
            JFileChooser fileChooser = new JFileChooser();
            
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Texto (*.txt)", "txt"));
            
            int seleccion = fileChooser.showOpenDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();
                f = new File(PATH);
                
                FileInputStream fi=new FileInputStream(f);
    			ObjectInputStream oi=new ObjectInputStream(fi);
    			Singleton_client.userclient = (ArrayList<Client>)oi.readObject();
    			oi.close();
            }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("erroropen_txt"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
