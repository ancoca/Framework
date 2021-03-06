/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.users.userregister.model.BLL.BLL_userregister;

import framework.classes.Singleton_general;
import framework.module.login.model.classes.Singleton_login;
import framework.module.menu_config.controller.Controller_menu_config;
import framework.module.menu_config.view.Menu;
import framework.module.users.userregister.model.DAO.DAO_userregister;
import framework.module.users.userregister.model.classes.User_register;
import framework.module.users.userregister.controller.Controller_userregister;
import framework.module.users.userregister.model.classes.Singleton_userregister;
import framework.module.users.userregister.model.classes.language.Language_userregister;
import framework.module.users.userregister.model.classes.miniSimpleTableModel_userregister;
import framework.module.users.userregister.model.functions.json_auto_userregister;
import framework.module.users.userregister.model.functions.json_userregister;
import framework.module.users.userregister.model.functions.pagina_userregister;
import framework.module.users.userregister.model.functions.txt_userregister;
import framework.module.users.userregister.model.functions.xml_userregister;
import framework.module.users.userregister.view.Create_userregister;
import framework.module.users.userregister.view.List_userregister;
import framework.module.users.userregister.view.Update_userregister;
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
    
    /**
     * CREATE USER
     */
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
    
    /**
     * SHOW ALL USERS
     */
    public static void read_userregister_all (){

        if(Singleton_userregister.userregister.isEmpty()){
            JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("user0"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }else{
            for (int i = 0; i <Singleton_userregister.userregister.size();i++){
                String cad = "";
                cad = cad + (Singleton_userregister.userregister.get(i).toString());
                JOptionPane.showMessageDialog(null, cad);
            }
        }
    }
    
    /**
     * SHOW USER
     */
    public static void read_userregister (){
        int location = -1;
        User_register u1 = null;

        if(Singleton_userregister.userregister.isEmpty()){
            JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("user0"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }else{
            location = -1;
            u1 = BLL_userregister.IDuserregister();
            if (u1 == null) {
                JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("user0"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
            }else{
                location = BLL_userregister.find_user(u1);
                if (location != -1) {
                    u1 = Singleton_userregister.userregister.get(location);
                    JOptionPane.showMessageDialog(null, u1.toString());
                }else {
                    JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("user0"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    /**
     * UPDATE USER
     */
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
    
    /**
     * DELETE USER
     */
    public static void delete_userregister () {
	String dni;
        int pos;
        int inicio, selection1;
        int n = ((miniSimpleTableModel_userregister) List_userregister.TABLA.getModel()).getRowCount();
        User_register userregister = null;
        if (n != 0) {

            inicio = (pagina_userregister.currentPageIndex - 1) * pagina_userregister.itemsPerPage;
            int selec = List_userregister.TABLA.getSelectedRow();
            selection1 = inicio + selec;

            if (selec == -1) {
                JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("usernotselect"), Language_userregister.getInstance().getProperty("error"), 2);
            } else {
                dni = (String) List_userregister.TABLA.getModel().getValueAt(selection1, 0);
                userregister = new User_register(dni);
                pos = find_user(userregister);
                int opc = JOptionPane.showConfirmDialog(null, Language_userregister.getInstance().getProperty("userdelete?") + dni,
                        "Info", JOptionPane.WARNING_MESSAGE);

                if (opc == 0) {
                    ((miniSimpleTableModel_userregister) List_userregister.TABLA.getModel()).removeRow(selection1);
                    userregister = Singleton_userregister.userregister.get(pos);

                    Singleton_userregister.userregister.remove(pos);
                    miniSimpleTableModel_userregister.datosaux.remove(pos);
                    json_auto_userregister.savejson_userregister();
                    ((miniSimpleTableModel_userregister) List_userregister.TABLA.getModel()).cargar();
                    List_userregister.lblsize.setText(String.valueOf(Singleton_userregister.userregister.size()));
                    pagina_userregister.initLinkBox();
                }

                if (List_userregister.TABLA.getRowCount()==0){
                    pagina_userregister.currentPageIndex -= 1;
                    pagina_userregister.initLinkBox();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("size0"), Language_userregister.getInstance().getProperty("error"), 2);
        }
    }
    
    /**
     * FIND USER
     * @param user
     * @return 
     */
    public static int find_user(User_register user) { 
        for (int i = 0; i<=(Singleton_userregister.userregister.size()-1); i++){
            if((Singleton_userregister.userregister.get(i)).equals(user) ) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * OPEN FILE JSON
     */
    public static void open_userregister_json () {
        if(Singleton_userregister.userregister.isEmpty()){
            json_userregister.openjson_userregister();
        }else{
            json_userregister.savejson_userregister();
            json_userregister.openjson_userregister();
        }
    }
    
    /**
     * SAVE FILE JSON
     */
    public static void save_userregister_json () {
        if(Singleton_userregister.userregister.isEmpty()){
            JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("user0"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }else{
            json_userregister.savejson_userregister();
        }
    }
    
    /**
     * OPEN FILE XML
     */
    public static void open_userregister_xml () {
        if(Singleton_userregister.userregister.isEmpty()){
            xml_userregister.openxml_userregister();
        }else{
            xml_userregister.savexml_userregister();
            xml_userregister.openxml_userregister();
        }
    }
    
    /**
     * SAVE FILE XML
     */
    public static void save_userregister_xml () {
        if(Singleton_userregister.userregister.isEmpty()){
            JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("user0"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }else{
            xml_userregister.savexml_userregister();
        }
    }
    
    /**
     * OPEN FILE TXT
     */
    public static void open_userregister_txt () {
        if(Singleton_userregister.userregister.isEmpty()){
            txt_userregister.opentxt_userregister();
        }else{
            txt_userregister.savetxt_userregister();
            txt_userregister.opentxt_userregister();
        }
    }
    
    /**
     * SAVE FILE TXT
     */
    public static void save_userregister_txt () {
        if(Singleton_userregister.userregister.isEmpty()){
            JOptionPane.showMessageDialog(null, Language_userregister.getInstance().getProperty("user0"), Language_userregister.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }else{
            txt_userregister.savetxt_userregister();
        }
    }
    
    /**
     * GENERATE LIST USERS
     * @return 
     */
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
    
    /**
     * FIND USER
     * @return 
     */
    public static User_register IDuserregister () {
        User_register u1 = null;
        String ID = "";
        int location1 = -1, selection, inicio, selection1;
        if (Singleton_login.tabla==true){
            inicio=(pagina_userregister.currentPageIndex-1)*pagina_userregister.itemsPerPage; //nos situamos al inicio de la página en cuestión
            selection=List_userregister.TABLA.getSelectedRow(); //nos situamos en la fila
            selection1=inicio+selection; //nos situamos en la fila correspondiente de esa página
            ID = (String) List_userregister.TABLA.getModel().getValueAt(selection1, 0);
            u1 = new User_register (ID);
        }else{
            u1 = new User_register (Singleton_login.dni);
        }
        location1 = -1;
        location1 = BLL_userregister.find_user(u1);
        if (location1 != -1) {
            u1 = Singleton_userregister.userregister.get(location1);
        }else{
            u1 = null;
        }

        return u1;		
    }
    
    /**
     * TIMER USER REGISTER
     * @param jframe 
     */
    public static void timer(JFrame jframe) {
        Timer timer = new Timer (3000, new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
                json_auto_userregister.savejson_userregister();
                jframe.dispose();
                if (Singleton_login.admin==true){
                    new Controller_userregister(new List_userregister(), 0).iniciar(0);
                }else{
                    new Controller_menu_config(new Menu(), 0).iniciar(0);
                }
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
