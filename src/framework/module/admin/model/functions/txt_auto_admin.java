package framework.module.admin.model.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import framework.module.admin.model.classes.Admin;
import framework.module.admin.model.classes.Singleton_admin;
import framework.module.admin.model.classes.language.Language_admin;

public class txt_auto_admin {

	public static void savetxt_admin() {
        String PATH = null;
        
        try {
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/admin/model/functions/files/txt/admin.txt";
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Singleton_admin.useradmin.size() > 0) {
	        try {
	            File file;
	            
	            file = new File(PATH);
	            
	            FileOutputStream FOS=new FileOutputStream(file);
				ObjectOutputStream OOS=new ObjectOutputStream(FOS);
				OOS.writeObject(Singleton_admin.useradmin);
				OOS.close();
	            
	        } catch (Exception e) {
	        	JOptionPane.showMessageDialog(null, Language_admin.getInstance().getProperty("errorsave_txt"), Language_admin.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
	        }
        } else {
            File path = new File(PATH);

            path.delete();
        }
    }
	
	public static void opentxt_admin() {
    	String PATH = null;
        try {
            File file;
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/admin/model/functions/files/txt/admin.txt";
            file = new File(PATH);
            
            FileInputStream FIS=new FileInputStream(file);
    		ObjectInputStream OIS=new ObjectInputStream(FIS);
    		Singleton_admin.useradmin = (ArrayList<Admin>)OIS.readObject();
    		OIS.close();
            
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_admin.getInstance().getProperty("erroropen_txt"), Language_admin.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
