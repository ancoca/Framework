package framework.module.userregister.model.functions;

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
import framework.module.client.model.classes.Client;
import framework.module.client.model.classes.Singleton_client;
import framework.module.userregister.model.classes.User_register;
import framework.module.config.model.classes.language2.Language_user;
import framework.module.userregister.model.classes.Singleton_userregister;

public class txt_userregister {

	public static void savetxt_userregister() {
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
				OOS.writeObject(Singleton_userregister.userregister);
				OOS.close();
                JOptionPane.showMessageDialog(null, "Archivo TXT guardado con exito", "Archivo TXT", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("errorsave_txt"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public static void opentxt_userregister() {
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
    			Singleton_userregister.userregister = (ArrayList<User_register>)oi.readObject();
    			oi.close();
            }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("erroropen_txt"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
