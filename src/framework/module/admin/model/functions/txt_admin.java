package framework.module.admin.model.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import framework.module.admin.model.classes.Admin;
import framework.module.admin.model.classes.Singleton_admin;
import framework.module.admin.model.classes.language.Language_admin;

public class txt_admin {

	public static void savetxt_admin() {
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
				OOS.writeObject(Singleton_admin.useradmin);
				OOS.close();
                JOptionPane.showMessageDialog(null, Language_admin.getInstance().getProperty("savetxt"), "TXT", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_admin.getInstance().getProperty("errorsave_txt"), Language_admin.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public static void opentxt_admin() {
    	String PATH = null;
        try {
            File file;
            JFileChooser fileChooser = new JFileChooser();
            
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Texto (*.txt)", "txt"));
            
            int seleccion = fileChooser.showOpenDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();
                file = new File(PATH);
                
                FileInputStream FIS=new FileInputStream(file);
    			ObjectInputStream OIS=new ObjectInputStream(FIS);
    			Singleton_admin.useradmin = (ArrayList<Admin>)OIS.readObject();
    			OIS.close();
            }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_admin.getInstance().getProperty("erroropen_txt"), Language_admin.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
