package framework.module.client.model.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import framework.module.client.model.classes.Client;
import framework.module.client.model.classes.Singleton_client;
import framework.module.client.model.classes.language.Language_client;

public class txt_auto_client {

	public static void savetxt_client() {
        String PATH = null;
        
        try {
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/client/model/functions/files/txt/client.txt";
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Singleton_client.userclient.size() > 0) {
	        try {
	            File file;
	            
	            file = new File(PATH);
	            
	            FileOutputStream FOS=new FileOutputStream(file);
				ObjectOutputStream OOS=new ObjectOutputStream(FOS);
				OOS.writeObject(Singleton_client.userclient);
				OOS.close();
	            
	        } catch (Exception e) {
	        	JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("errorsave_txt"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
	        }
        } else {
            File path = new File(PATH);

            path.delete();
        }
    }
	
	public static void opentxt_client() {
    	String PATH = null;
        try {
            File file;
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/framework/module/client/model/functions/files/txt/client.txt";
            file = new File(PATH);
            
            FileInputStream FIS=new FileInputStream(file);
    		ObjectInputStream OIS=new ObjectInputStream(FIS);
    		Singleton_client.userclient = (ArrayList<Client>)OIS.readObject();
    		OIS.close();
            
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("erroropen_txt"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
