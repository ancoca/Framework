package framework.module.userregister.model.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import framework.module.userregister.model.classes.User_register;
import framework.module.userregister.model.classes.Singleton_userregister;
import framework.module.userregister.model.classes.language.Language_userregister;

public class txt_auto_userregister {

	public static void savetxt_userregister() {
        String PATH = null;
        
        try {
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/userregister/model/functions/files/txt/user_register.txt";
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Singleton_userregister.userregister.size() > 0) {
	        try {
	            File file;
	            
	            file = new File(PATH);
	            
	            FileOutputStream FOS=new FileOutputStream(file);
				ObjectOutputStream OOS=new ObjectOutputStream(FOS);
				OOS.writeObject(Singleton_userregister.userregister);
				OOS.close();
	            
	        } catch (Exception e) {
	        	JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("errorsave_txt"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
	        }
        } else {
            File path = new File(PATH);

            path.delete();
        }
    }
	
	public static void opentxt_userregister() {
    	String PATH = null;
        try {
            File file;
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/userregister/model/functions/files/txt/user_register.txt";
            file = new File(PATH);
            
            FileInputStream FIS=new FileInputStream(file);
    		ObjectInputStream OIS=new ObjectInputStream(FIS);
    		Singleton_userregister.userregister = (ArrayList<User_register>)OIS.readObject();
    		OIS.close();
            
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("erroropen_txt"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
