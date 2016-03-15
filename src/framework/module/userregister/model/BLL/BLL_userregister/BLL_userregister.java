/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.userregister.model.BLL.BLL_userregister;

import framework.functions.Functions_menu;
import framework.module.userregister.model.DAO.DAO_userregister;
import framework.module.userregister.model.classes.User_register;
import framework.module.config.model.classes.language2.Language_user;
import framework.module.userregister.model.classes.Singleton_userregister;
import framework.module.userregister.model.functions.json_auto_userregister;
import framework.module.userregister.model.functions.json_userregister;
import framework.module.userregister.model.functions.pagina_userregister;
import framework.module.userregister.model.functions.txt_userregister;
import framework.module.userregister.model.functions.xml_userregister;
import framework.module.userregister.view.Create_userregister;
import framework.module.userregister.view.List_userregister;
import framework.module.userregister.view.Update_userregister;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author angel
 */
public class BLL_userregister {
    public static boolean check;
    
    public static void create_userregister () {
	int location = -1;
	User_register u1 = null;
        check=true;
	
	u1 = DAO_userregister.ask_user_registerDNI();
            if (u1==null){
                Create_userregister.checkDNI.setIcon(Singleton_userregister.cancel);
                check=false;
            }else{
                location = BLL_userregister.find_user(u1);
                if (location != -1) {
                        Create_userregister.checkDNI.setIcon(Singleton_userregister.cancel);
                        check=false;
                } else {
                    u1 = DAO_userregister.ask_user_register();
                        if (u1==null){
                            check=false;
                        } else {
                                Singleton_userregister.userregister.add(u1);
                                check=true;

                        }
                }
            }
	}
    
    public static void read_userregister_all (){
		
		if(Singleton_userregister.userregister.isEmpty()){
			JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("mainerror"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
		}else{
			for (int i = 0; i <Singleton_userregister.userregister.size();i++){
				String cad = "";
				cad = cad + (Singleton_userregister.userregister.get(i).toString());
				JOptionPane.showMessageDialog(null, cad);
			}
		}
	}
    
    public static void read_userregister (){
		int location = -1;
		User_register u1 = null;
		
		if(Singleton_userregister.userregister.isEmpty()){
			JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("mainerror"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
		}else{
			location = -1;
			u1 = BLL_userregister.IDuserregister();
			if (u1 == null) {
				JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("usererror"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
			}else{
				location = BLL_userregister.find_user(u1);
				if (location != -1) {
					u1 = Singleton_userregister.userregister.get(location);
					JOptionPane.showMessageDialog(null, u1.toString());
				}else {
					JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("usererror"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
    
    public static void update_userregister () {
		int location1 = -1, location2 = -1;
                String dni = Update_userregister.DNI;
		User_register u1 = new User_register (dni);
		
		location1 = BLL_userregister.find_user(u1);
		if (location1 == -1) {
                        check=false;
                }else{
                        if (Update_userregister.txtDNI.getText().equals(dni)){
                                u1 = DAO_userregister.ask_user_register_update();
                                    if (u1==null){
                                        check=false;
                                    } else {
                                        Singleton_userregister.userregister.set(location1, u1);
                                        check=true;
                                   }
                        }else{
                                u1 = DAO_userregister.ask_user_registerDNI_update();
                                if (u1==null){
                                        Update_userregister.checkDNI.setIcon(Singleton_userregister.cancel);
                                        check=false;
                                }else{
                                        location2 = BLL_userregister.find_user(u1);
                                        if (location2 != -1) {
                                                Update_userregister.checkDNI.setIcon(Singleton_userregister.cancel);
                                                check=false;
                                        } else {
                                                u1 = DAO_userregister.ask_user_register_update();
                                                if (u1==null){
                                                    check=false;
                                                } else {
                                                        Singleton_userregister.userregister.set(location1, u1);
                                                        check=true;
                                                }
                                        }
                                }
                        }
                        
                }
	}
    
    public static void delete_userregister () {
		int location1 = -1, location2 = -1;
                String dni = Update_userregister.DNI;
		User_register u1 = new User_register (dni);
		
		location1 = BLL_userregister.find_user(u1);
		if (location1 == -1) {
                        check=false;
                }else{
                        if (Update_userregister.txtDNI.getText().equals(dni)){
                                u1 = DAO_userregister.ask_user_register_update();
                                    if (u1==null){
                                        check=false;
                                    } else {
                                        Singleton_userregister.userregister.set(location1, u1);
                                        check=true;
                                   }
                        }else{
                                u1 = DAO_userregister.ask_user_registerDNI_update();
                                if (u1==null){
                                        Update_userregister.checkDNI.setIcon(Singleton_userregister.cancel);
                                        check=false;
                                }else{
                                        location2 = BLL_userregister.find_user(u1);
                                        if (location2 != -1) {
                                                Update_userregister.checkDNI.setIcon(Singleton_userregister.cancel);
                                                check=false;
                                        } else {
                                                u1 = DAO_userregister.ask_user_register_update();
                                                if (u1==null){
                                                    check=false;
                                                } else {
                                                        Singleton_userregister.userregister.set(location1, u1);
                                                        check=true;
                                                }
                                        }
                                }
                        }
                        
                }
	}
    
    public static int find_user(User_register user) { 
		for (int i = 0; i<=(Singleton_userregister.userregister.size()-1); i++){
			if((Singleton_userregister.userregister.get(i)).equals(user) )
				return i;
		}
		return -1;
	}
    
    public static void open_userregister_json () {
		if(Singleton_userregister.userregister.isEmpty()){
			json_userregister.openjson_userregister();
		}else{
			json_userregister.savejson_userregister();
                        json_userregister.openjson_userregister();
		}
	}
    
    public static void save_userregister_json () {
		if(Singleton_userregister.userregister.isEmpty()){
			JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("mainerror"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
		}else{
			json_userregister.savejson_userregister();
		}
	}
    
    public static void open_userregister_xml () {
		if(Singleton_userregister.userregister.isEmpty()){
			xml_userregister.openxml_userregister();
		}else{
			xml_userregister.savexml_userregister();
                        xml_userregister.openxml_userregister();
		}
	}
    
    public static void save_userregister_xml () {
		if(Singleton_userregister.userregister.isEmpty()){
			JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("mainerror"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
		}else{
			xml_userregister.savexml_userregister();
		}
	}
    
    public static void open_userregister_txt () {
		if(Singleton_userregister.userregister.isEmpty()){
			txt_userregister.opentxt_userregister();
		}else{
			txt_userregister.savetxt_userregister();
                        txt_userregister.opentxt_userregister();
		}
	}
    
    public static void save_userregister_txt () {
		if(Singleton_userregister.userregister.isEmpty()){
			JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("mainerror"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
		}else{
			txt_userregister.savetxt_userregister();
		}
	}
    
    public static DefaultComboBoxModel generate_vector_userregister () {
		User_register u1 = null;
		String s = "";
		
		int arraylist =Singleton_userregister.userregister.size();
                DefaultComboBoxModel object = new DefaultComboBoxModel();
		for (int i = 0; i<arraylist; i++) {
			u1 = (User_register) Singleton_userregister.userregister.get(i);
			s=u1.getDNI()+"-----"+u1.getName()+" "+u1.getSurname();
			object.addElement(s);
		}
                
		return object;
	}
    
    public static User_register IDuserregister () {
		User_register u1 = null;
		String ID = "";
		int location1 = -1, selection, inicio, selection1;
                inicio=(pagina_userregister.currentPageIndex-1)*pagina_userregister.itemsPerPage; //nos situamos al inicio de la página en cuestión
                selection=List_userregister.TABLA.getSelectedRow(); //nos situamos en la fila
                selection1=inicio+selection; //nos situamos en la fila correspondiente de esa página
		String search = (String) List_userregister.TABLA.getModel().getValueAt(selection1, 0);
		if (search != ""){
			for (int i = 0; i<9; i++) {
				ID += search.charAt(i);
			}
			u1 = new User_register (ID);
                        location1 = -1;
                        location1 = BLL_userregister.find_user(u1);
                        if (location1 != -1) {
                            u1 = Singleton_userregister.userregister.get(location1);
                        }
		}
                
		return u1;		
	}
    
    public static void timer(JFrame jframe) {
        Timer timer = new Timer (3000, new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
                json_auto_userregister.savejson_userregister();
                jframe.dispose();
                new List_userregister().setVisible(true);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                       VALIDATE USER REGISTER CREATE                    //
    //                                                                        //
    ////////////////////////////////////////////////////////////////////////////
    
    public static void DNI_create () {
        if (DAO_userregister.DNI()==true){
            Create_userregister.checkDNI.setIcon(Singleton_userregister.ok);
        }else{
            Create_userregister.checkDNI.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void user_create () {
        if (DAO_userregister.user()==true){
            Create_userregister.checkuser.setIcon(Singleton_userregister.ok);
        }else{
            Create_userregister.checkuser.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void pass_create () {
        if (DAO_userregister.pass()==true){
            Create_userregister.checkpass.setIcon(Singleton_userregister.ok);
        }else{
            Create_userregister.checkpass.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void name_create () {
        if (DAO_userregister.name()==true){
            Create_userregister.checkname.setIcon(Singleton_userregister.ok);
        }else{
            Create_userregister.checkname.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void surname_create () {
        if (DAO_userregister.surname()==true){
            Create_userregister.checksurname.setIcon(Singleton_userregister.ok);
        }else{
            Create_userregister.checksurname.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void email_create () {
        if (DAO_userregister.email()==true){
            Create_userregister.checkemail.setIcon(Singleton_userregister.ok);
        }else{
            Create_userregister.checkemail.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void mobilephone_create () {
        if (DAO_userregister.mobilephone()==true){
            Create_userregister.checkmobilephone.setIcon(Singleton_userregister.ok);
        }else{
            Create_userregister.checkmobilephone.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void activity_create () {
        if (DAO_userregister.activity()==true){
            Create_userregister.checkactivity.setIcon(Singleton_userregister.ok);
        }else{
            Create_userregister.checkactivity.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void point_create () {
        if (DAO_userregister.point()==true){
            Create_userregister.checkpoint.setIcon(Singleton_userregister.ok);
        }else{
            Create_userregister.checkpoint.setIcon(Singleton_userregister.cancel);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                      VALIDATE USER REGISTER UPDATE                     //
    //                                                                        //
    ////////////////////////////////////////////////////////////////////////////
    
    public static void DNI_update () {
        if (DAO_userregister.DNI_update()==true){
            Update_userregister.checkDNI.setIcon(Singleton_userregister.ok);
        }else{
            Update_userregister.checkDNI.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void user_update () {
        if (DAO_userregister.user_update()==true){
            Update_userregister.checkuser.setIcon(Singleton_userregister.ok);
        }else{
            Update_userregister.checkuser.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void pass_update () {
        if (DAO_userregister.pass_update()==true){
            Update_userregister.checkpass.setIcon(Singleton_userregister.ok);
        }else{
            Update_userregister.checkpass.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void name_update () {
        if (DAO_userregister.name_update()==true){
            Update_userregister.checkname.setIcon(Singleton_userregister.ok);
        }else{
            Update_userregister.checkname.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void surname_update () {
        if (DAO_userregister.surname_update()==true){
            Update_userregister.checksurname.setIcon(Singleton_userregister.ok);
        }else{
            Update_userregister.checksurname.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void email_update () {
        if (DAO_userregister.email_update()==true){
            Update_userregister.checkemail.setIcon(Singleton_userregister.ok);
        }else{
            Update_userregister.checkemail.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void mobilephone_update () {
        if (DAO_userregister.mobilephone_update()==true){
            Update_userregister.checkmobilephone.setIcon(Singleton_userregister.ok);
        }else{
            Update_userregister.checkmobilephone.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void activity_update () {
        if (DAO_userregister.activity_update()==true){
            Update_userregister.checkactivity.setIcon(Singleton_userregister.ok);
        }else{
            Update_userregister.checkactivity.setIcon(Singleton_userregister.cancel);
        }
    }
    
    public static void point_update () {
        if (DAO_userregister.point_update()==true){
            Update_userregister.checkpoint.setIcon(Singleton_userregister.ok);
        }else{
            Update_userregister.checkpoint.setIcon(Singleton_userregister.cancel);
        }
    }
}
