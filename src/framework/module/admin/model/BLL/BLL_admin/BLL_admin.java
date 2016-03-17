/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.admin.model.BLL.BLL_admin;

import framework.module.admin.model.functions.pagina_admin;
import framework.module.admin.model.DAO.DAO_admin;
import framework.module.admin.model.classes.Admin;
import framework.module.admin.model.classes.Singleton_admin;
import framework.module.admin.model.classes.miniSimpleTableModel_admin;
import framework.module.admin.model.functions.json_admin;
import framework.module.admin.model.functions.json_auto_admin;
import framework.module.admin.model.functions.txt_admin;
import framework.module.admin.model.functions.xml_admin;
import framework.module.config.model.classes.language2.Language_user;
import framework.module.admin.view.Create_admin;
import framework.module.admin.view.List_admin;
import framework.module.admin.view.Update_admin;
import java.awt.Color;
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
public class BLL_admin {
    public static boolean check;
    
    public static void create_admin () {
	int location = -1;
	Admin a1 = null;
        check=true;
	
	a1 = DAO_admin.ask_adminDNI();
            if (a1==null){
                Create_admin.checkDNI.setIcon(Singleton_admin.cancel);
                check=false;
            }else{
                location = BLL_admin.find_admin(a1);
                if (location != -1) {
                        Create_admin.checkDNI.setIcon(Singleton_admin.cancel);
                        check=false;
                } else {
                    a1 = DAO_admin.ask_admin();
                        if (a1==null){
                            check=false;
                        } else {
                                Singleton_admin.useradmin.add(a1);
                                check=true;

                        }
                }
            }
	}
    
    public static void read_admin_all (){
		
		if(Singleton_admin.useradmin.isEmpty()){
			JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("mainerror"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
		}else{
			for (int i = 0; i <Singleton_admin.useradmin.size();i++){
				String cad = "";
				cad = cad + (Singleton_admin.useradmin.get(i).toString());
				JOptionPane.showMessageDialog(null, cad);
			}
		}
	}
    
    public static void read_admin (){
		int location = -1;
		Admin a1 = null;
		
		if(Singleton_admin.useradmin.isEmpty()){
			JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("mainerror"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
		}else{
			location = BLL_admin.find_admin(a1);
			if (location != -1) {
				a1 = Singleton_admin.useradmin.get(location);
				JOptionPane.showMessageDialog(null, a1.toString());
			}else {
				JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("usererror"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
    
    public static void update_admin () {
		int location1 = -1, location2 = -1;
                String dni = Update_admin.DNI;
		Admin a1 = new Admin (dni);
		
		location1 = BLL_admin.find_admin(a1);
		if (location1 == -1) {
                        check=false;
                }else{
                        if (Update_admin.txtDNI.getText().equals(dni)){
                                a1 = DAO_admin.ask_admin_update();
                                    if (a1==null){
                                        check=false;
                                    } else {
                                        Singleton_admin.useradmin.set(location1, a1);
                                        check=true;
                                   }
                        }else{
                                a1 = DAO_admin.ask_adminDNI_update();
                                if (a1==null){
                                        Update_admin.checkDNI.setIcon(Singleton_admin.cancel);
                                        check=false;
                                }else{
                                        location2 = BLL_admin.find_admin(a1);
                                        if (location2 != -1) {
                                                Update_admin.checkDNI.setIcon(Singleton_admin.cancel);
                                                check=false;
                                        } else {
                                                a1 = DAO_admin.ask_admin_update();
                                                if (a1==null){
                                                    check=false;
                                                } else {
                                                        Singleton_admin.useradmin.set(location1, a1);
                                                        check=true;
                                                }
                                        }
                                }
                        }
                        
                }
       }
    
    public static void delete_admin () {
	String dni;
        int pos;
        int inicio, selection1;
        int n = ((miniSimpleTableModel_admin) List_admin.TABLA.getModel()).getRowCount();
        Admin admin = null;
        if (n != 0) {

            inicio = (pagina_admin.currentPageIndex - 1) * pagina_admin.itemsPerPage;
            int selec = List_admin.TABLA.getSelectedRow();
            selection1 = inicio + selec;

            if (selec == -1) {
                JOptionPane.showMessageDialog(null, "No hay una persona seleccionada", "Error!", 2);
            } else {
                dni = (String) List_admin.TABLA.getModel().getValueAt(selection1, 0);
                admin = new Admin(dni);
                pos = find_admin(admin);
                int opc = JOptionPane.showConfirmDialog(null, "Deseas borrar a la persona con DNI: " + dni,
                        "Info", JOptionPane.WARNING_MESSAGE);

                if (opc == 0) {
                    ((miniSimpleTableModel_admin) List_admin.TABLA.getModel()).removeRow(selection1);
                    admin = Singleton_admin.useradmin.get(pos);

                    Singleton_admin.useradmin.remove(pos);
                    miniSimpleTableModel_admin.datosaux.remove(pos);
                    json_auto_admin.savejson_admin();
                    ((miniSimpleTableModel_admin) List_admin.TABLA.getModel()).cargar();
                    List_admin.jLabel3.setText(String.valueOf(Singleton_admin.useradmin.size()));
                    pagina_admin.initLinkBox();
                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "lista vacía", "Error!", 2);
        }
    }
    
    public static int find_admin(Admin admin) { 
		for (int i = 0; i<=(Singleton_admin.useradmin.size()-1); i++){
			if((Singleton_admin.useradmin.get(i)).equals(admin) )
				return i;
		}
		return -1;
	}
    
    public static void open_admin_json () {
		if(Singleton_admin.useradmin.isEmpty()){
			json_admin.openjson_admin();
		}else{
			json_admin.savejson_admin();
                        json_admin.openjson_admin();
		}
	}
    
    public static void save_admin_json () {
		if(Singleton_admin.useradmin.isEmpty()){
			JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("mainerror"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
		}else{
			json_admin.savejson_admin();
		}
	}
    
    public static void open_admin_xml () {
		if(Singleton_admin.useradmin.isEmpty()){
			xml_admin.openxml_admin();
		}else{
			xml_admin.savexml_admin();
                        xml_admin.openxml_admin();
		}
	}
    
    public static void save_admin_xml () {
		if(Singleton_admin.useradmin.isEmpty()){
			JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("mainerror"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
		}else{
			xml_admin.savexml_admin();
		}
	}
    
    public static void open_admin_txt () {
		if(Singleton_admin.useradmin.isEmpty()){
			txt_admin.opentxt_admin();
		}else{
			txt_admin.savetxt_admin();
                        txt_admin.opentxt_admin();
		}
	}
    
    public static void save_admin_txt () {
		if(Singleton_admin.useradmin.isEmpty()){
			JOptionPane.showMessageDialog(null, Language_user.getInstance().getProperty("mainerror"), Language_user.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
		}else{
			txt_admin.savetxt_admin();
		}
	}
    
    public static DefaultComboBoxModel generate_vector_admin () {
		Admin a1 = null;
		String s = "";
                
		int arraylist =Singleton_admin.useradmin.size();
                DefaultComboBoxModel object = new DefaultComboBoxModel();
		for (int i = 0; i<arraylist; i++) {
			a1 = (Admin) Singleton_admin.useradmin.get(i);
			s=a1.getDNI()+"-----"+a1.getName()+" "+a1.getSurname();
			object.addElement(s);
		}
		
		return object;
	}
    
    public static Admin IDadmin () {
		Admin a1 = null;
		String ID = "";
                int location1 = -1, selection, inicio, selection1;
                inicio=(pagina_admin.currentPageIndex-1)*pagina_admin.itemsPerPage; //nos situamos al inicio de la página en cuestión
                selection=List_admin.TABLA.getSelectedRow(); //nos situamos en la fila
                selection1=inicio+selection; //nos situamos en la fila correspondiente de esa página
		ID = (String) List_admin.TABLA.getModel().getValueAt(selection1, 0);
		a1 = new Admin (ID);
                location1 = -1;
                location1 = BLL_admin.find_admin(a1);
                if (location1 != -1) {
                    a1 = Singleton_admin.useradmin.get(location1);
                }
		return a1;
	}
        
    public static void timer(JFrame jframe) {
        Timer timer = new Timer (3000, new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
                json_auto_admin.savejson_admin();
                jframe.dispose();
                new List_admin().setVisible(true);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                           VALIDATE ADMIN CREATE                        //
    //                                                                        //
    ////////////////////////////////////////////////////////////////////////////
    
    public static void DNI_create () {
        if (DAO_admin.DNI()==true){
            Create_admin.checkDNI.setIcon(Singleton_admin.ok);
        }else{
            Create_admin.checkDNI.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void user_create () {
        if (DAO_admin.user()==true){
            Create_admin.checkuser.setIcon(Singleton_admin.ok);
        }else{
            Create_admin.checkuser.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void pass_create () {
        if (DAO_admin.pass()==true){
            Create_admin.checkpass.setIcon(Singleton_admin.ok);
        }else{
            Create_admin.checkpass.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void name_create () {
        if (DAO_admin.name()==true){
            Create_admin.checkname.setIcon(Singleton_admin.ok);
        }else{
            Create_admin.checkname.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void surname_create () {
        if (DAO_admin.surname()==true){
            Create_admin.checksurname.setIcon(Singleton_admin.ok);
        }else{
            Create_admin.checksurname.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void email_create () {
        if (DAO_admin.email()==true){
            Create_admin.checkemail.setIcon(Singleton_admin.ok);
        }else{
            Create_admin.checkemail.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void mobilephone_create () {
        if (DAO_admin.mobilephone()==true){
            Create_admin.checkmobilephone.setIcon(Singleton_admin.ok);
        }else{
            Create_admin.checkmobilephone.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void salary_create () {
        if (DAO_admin.salary()==true){
            Create_admin.checksalary.setIcon(Singleton_admin.ok);
        }else{
            Create_admin.checksalary.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void incentive_create () {
        if (DAO_admin.incentive()==true){
            Create_admin.checkincentive.setIcon(Singleton_admin.ok);
        }else{
            Create_admin.checkincentive.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void activity_create () {
        if (DAO_admin.activity()==true){
            Create_admin.checkactivity.setIcon(Singleton_admin.ok);
        }else{
            Create_admin.checkactivity.setIcon(Singleton_admin.cancel);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                           VALIDATE ADMIN UPDATE                        //
    //                                                                        //
    ////////////////////////////////////////////////////////////////////////////
    
    public static void DNI_update () {
        if (DAO_admin.DNI_update()==true){
            Update_admin.checkDNI.setIcon(Singleton_admin.ok);
        }else{
            Update_admin.checkDNI.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void user_update () {
        if (DAO_admin.user_update()==true){
            Update_admin.checkuser.setIcon(Singleton_admin.ok);
        }else{
            Update_admin.checkuser.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void pass_update () {
        if (DAO_admin.pass_update()==true){
            Update_admin.checkpass.setIcon(Singleton_admin.ok);
        }else{
            Update_admin.checkpass.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void name_update () {
        if (DAO_admin.name_update()==true){
            Update_admin.checkname.setIcon(Singleton_admin.ok);
        }else{
            Update_admin.checkname.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void surname_update () {
        if (DAO_admin.surname_update()==true){
            Update_admin.checksurname.setIcon(Singleton_admin.ok);
        }else{
            Update_admin.checksurname.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void email_update () {
        if (DAO_admin.email_update()==true){
            Update_admin.checkemail.setIcon(Singleton_admin.ok);
        }else{
            Update_admin.checkemail.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void mobilephone_update () {
        if (DAO_admin.mobilephone_update()==true){
            Update_admin.checkmobilephone.setIcon(Singleton_admin.ok);
        }else{
            Update_admin.checkmobilephone.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void salary_update () {
        if (DAO_admin.salary_update()==true){
            Update_admin.checksalary.setIcon(Singleton_admin.ok);
        }else{
            Update_admin.checksalary.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void incentive_update () {
        if (DAO_admin.incentive_update()==true){
            Update_admin.checkincentive.setIcon(Singleton_admin.ok);
        }else{
            Update_admin.checkincentive.setIcon(Singleton_admin.cancel);
        }
    }
    
    public static void activity_update () {
        if (DAO_admin.activity_update()==true){
            Update_admin.checkactivity.setIcon(Singleton_admin.ok);
        }else{
            Update_admin.checkactivity.setIcon(Singleton_admin.cancel);
        }
    }
}
