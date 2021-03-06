/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.users.client.model.BLL.BLL_client;

import framework.classes.Singleton_general;
import framework.module.users.client.controller.Controller_client;
import framework.module.users.client.model.DAO.DAO_client;
import framework.module.users.client.model.classes.Client;
import framework.module.users.client.model.classes.Singleton_client;
import framework.module.users.client.model.classes.language.Language_client;
import framework.module.users.client.model.classes.miniSimpleTableModel_client;
import framework.module.users.client.model.functions.json_auto_client;
import framework.module.users.client.model.functions.json_client;
import framework.module.users.client.model.functions.pagina_client;
import framework.module.users.client.model.functions.txt_client;
import framework.module.users.client.model.functions.xml_client;
import framework.module.users.client.view.Create_client;
import framework.module.users.client.view.List_client;
import framework.module.users.client.view.Update_client;
import framework.module.login.model.classes.Singleton_login;
import framework.module.menu_config.controller.Controller_menu_config;
import framework.module.menu_config.view.Menu;
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
public class BLL_client {
    
    public static boolean check;
    
    /**
     * CREATE USER
     */
    public static void create_client () {
        int location = -1;
        Client c1 = null;
        check=false;

        c1 = DAO_client.ask_clientDNI();
        if (c1==null){
            Create_client.checkDNI.setIcon(Singleton_client.cancel);
            check=false;
        }else{
            location = BLL_client.find_client(c1);
            if (location != -1) {
                Create_client.checkDNI.setIcon(Singleton_client.cancel);
                check=false;
            } else {
                c1 = DAO_client.ask_client();
                if (c1==null){
                    check=false;
                } else {
                    BLL_BD_client.create_BD(c1);
                    Singleton_client.userclient.add(c1);
                    check=true;
                }
            }
        }
    }
    
    /**
     * SHOW ALL USERS
     */
    public static void read_client_all (){
		
        if(Singleton_client.userclient.isEmpty()){
            JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("user0"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }else{
            for (int i = 0; i <Singleton_client.userclient.size();i++){
                String cad = "";
                cad = cad + (Singleton_client.userclient.get(i).toString());
                JOptionPane.showMessageDialog(null, cad);
            }
        }
    }
    
    /**
     * SHOW USER
     */
    public static void read_client (){
        int location = -1;
        Client c1 = null;

        if(Singleton_client.userclient.isEmpty()){
            JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("user0"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }else{
            location = -1;
            c1 = BLL_client.IDclient();
            if (c1 == null) {
                JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("user0"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
            }else{
                location = BLL_client.find_client(c1);
                if (location != -1) {
                    c1 = Singleton_client.userclient.get(location);
                    JOptionPane.showMessageDialog(null, c1.toString());
                }else {
                    JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("user0"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    /**
     * UPDATE USER
     */
    public static void update_client () {
        int location1 = -1, location2 = -1;
        String dni = Update_client.DNI;
        Client c1 = new Client (dni);

        location1 = BLL_client.find_client(c1);
        if (location1 == -1) {
            check=false;
        }else{
            if (Update_client.txtDNI.getText().equals(dni)){
                c1 = DAO_client.ask_client_update();
                if (c1==null){
                    check=false;
                } else {
                    BLL_BD_client.update_BD(c1);
                    Singleton_client.userclient.set(location1, c1);
                    check=true;
               }
            }else{
                c1 = DAO_client.ask_clientDNI_update();
                if (c1==null){
                    Update_client.checkDNI.setIcon(Singleton_client.cancel);
                    check=false;
                }else{
                    location2 = BLL_client.find_client(c1);
                    if (location2 != -1) {
                        Update_client.checkDNI.setIcon(Singleton_client.cancel);
                        check=false;
                    } else {
                        c1 = DAO_client.ask_client_update();
                        if (c1==null){
                            check=false;
                        } else {
                            BLL_BD_client.delete_BD_update(dni);
                            BLL_BD_client.create_BD(c1);
                            Singleton_client.userclient.set(location1, c1);
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
    public static void delete_client () {
	String dni;
        int pos;
        int inicio, selection1;
        int n = ((miniSimpleTableModel_client) List_client.TABLA.getModel()).getRowCount();
        Client client = null;
        if (n != 0) {

            inicio = (pagina_client.currentPageIndex - 1) * pagina_client.itemsPerPage;
            int selec = List_client.TABLA.getSelectedRow();
            selection1 = inicio + selec;

            if (selec == -1) {
                JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("usernotselect"), Language_client.getInstance().getProperty("error"), 2);
            } else {
                dni = (String) List_client.TABLA.getModel().getValueAt(selection1, 0);
                client = new Client(dni);
                pos = find_client(client);
                int opc = JOptionPane.showConfirmDialog(null, Language_client.getInstance().getProperty("userdelete?") + dni,
                        "Info", JOptionPane.WARNING_MESSAGE);

                if (opc == 0) {
                    ((miniSimpleTableModel_client) List_client.TABLA.getModel()).removeRow(selection1);
                    client = Singleton_client.userclient.get(pos);

                    BLL_BD_client.delete_BD(client);
                    Singleton_client.userclient.remove(pos);
                    miniSimpleTableModel_client.datosaux.remove(pos);
                    ((miniSimpleTableModel_client) List_client.TABLA.getModel()).cargar();
                    List_client.lblsize.setText(String.valueOf(Singleton_client.userclient.size()));
                    pagina_client.initLinkBox();
                }
                
                if (List_client.TABLA.getRowCount()==0){
                    pagina_client.currentPageIndex -= 1;
                    pagina_client.initLinkBox();
                }

            }

        } else {
            JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("size0"), Language_client.getInstance().getProperty("error"), 2);
        }
    }
    
    /**
     * FIND USER
     * @param client
     * @return 
     */
    public static int find_client(Client client) { 
        for (int i = 0; i<=(Singleton_client.userclient.size()-1); i++){
            if((Singleton_client.userclient.get(i)).equals(client) ) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * OPEN FILE JSON
     */
    public static void open_client_json () {
        if(Singleton_client.userclient.isEmpty()){
            json_client.openjson_client();
        }else{
            json_client.savejson_client();
            json_client.openjson_client();
        }
    }
    
    /**
     * SAVE FILE JSON
     */
    public static void save_client_json () {
        if(Singleton_client.userclient.isEmpty()){
            JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("user0"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }else{
            json_client.savejson_client();
        }
    }
    
    /**
     * OPEN FILE XML
     */
    public static void open_client_xml () {
        if(Singleton_client.userclient.isEmpty()){
            xml_client.openxml_client();
        }else{
            xml_client.savexml_client();
            xml_client.openxml_client();
        }
    }
    
    /**
     * SAVE FILE XML
     */
    public static void save_client_xml () {
        if(Singleton_client.userclient.isEmpty()){
            JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("user0"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }else{
            xml_client.savexml_client();
        }
    }
    
    /**
     * OPEN FILE TXT
     */
    public static void open_client_txt () {
        if(Singleton_client.userclient.isEmpty()){
            txt_client.opentxt_client();
        }else{
            txt_client.savetxt_client();
            txt_client.opentxt_client();
        }
    }
    
    /**
     * SAVE FILE TXT
     */
    public static void save_client_txt () {
        if(Singleton_client.userclient.isEmpty()){
            JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("user0"), Language_client.getInstance().getProperty("error"), JOptionPane.ERROR_MESSAGE);
        }else{
            txt_client.savetxt_client();
        }
    }
    
    /**
     * GENERATE LIST USERS
     * @return 
     */
    public static DefaultComboBoxModel generate_vector_client () {
        Client c1 = null;
        String s = "";
        
        int arraylist =Singleton_client.userclient.size();
        DefaultComboBoxModel object = new DefaultComboBoxModel();
        for (int i = 0; i<arraylist; i++) {
            c1 = (Client) Singleton_client.userclient.get(i);
            s=c1.getDNI()+"-----"+c1.getName()+" "+c1.getSurname();
            object.addElement(s);
        }

        return object;
    }
    
    /**
     * FIND USER
     * @return 
     */
    public static Client IDclient () {
        Client c1 = null;
        String ID = "";
        int location1 = -1, selection, inicio, selection1;
        if (Singleton_login.tabla==true){
        inicio=(pagina_client.currentPageIndex-1)*pagina_client.itemsPerPage; //nos situamos al inicio de la página en cuestión
        selection=List_client.TABLA.getSelectedRow(); //nos situamos en la fila
        selection1=inicio+selection; //nos situamos en la fila correspondiente de esa página
        ID = (String) List_client.TABLA.getModel().getValueAt(selection1, 0);
        c1 = new Client (ID);
        }else{
            c1 = new Client (Singleton_login.dni);
        }
        location1 = -1;
        location1 = BLL_client.find_client(c1);
        if (location1 != -1) {
            c1 = Singleton_client.userclient.get(location1);
        }
        return c1;		
    }
    
    /**
     * TIMER CLIENT
     * @param jframe 
     */
    public static void timer(JFrame jframe) {
        Timer timer = new Timer (2000, new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
                jframe.dispose();
                if (Singleton_login.admin==true){
                    new Controller_client(new List_client(), 0).iniciar(0);
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
    //                           VALIDATE CLIENT CREATE                       //
    //                                                                        //
    ////////////////////////////////////////////////////////////////////////////
    
    public static void DNI_create () {
        if (DAO_client.DNI()==true){
            Create_client.checkDNI.setIcon(Singleton_client.ok);
        }else{
            Create_client.checkDNI.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void user_create () {
        if (DAO_client.user()==true){
            Create_client.checkuser.setIcon(Singleton_client.ok);
        }else{
            Create_client.checkuser.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void pass_create () {
        if (DAO_client.pass()==true){
            Create_client.checkpass.setIcon(Singleton_client.ok);
        }else{
            Create_client.checkpass.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void name_create () {
        if (DAO_client.name()==true){
            Create_client.checkname.setIcon(Singleton_client.ok);
        }else{
            Create_client.checkname.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void surname_create () {
        if (DAO_client.surname()==true){
            Create_client.checksurname.setIcon(Singleton_client.ok);
        }else{
            Create_client.checksurname.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void email_create () {
        if (DAO_client.email()==true){
            Create_client.checkemail.setIcon(Singleton_client.ok);
        }else{
            Create_client.checkemail.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void mobilephone_create () {
        if (DAO_client.mobilephone()==true){
            Create_client.checkmobilephone.setIcon(Singleton_client.ok);
        }else{
            Create_client.checkmobilephone.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void shopping_create () {
        if (DAO_client.shopping()==true){
            Create_client.checkshopping.setIcon(Singleton_client.ok);
        }else{
            Create_client.checkshopping.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void dtos_create () {
        if (DAO_client.dtos()==true){
            Create_client.checkdtos.setIcon(Singleton_client.ok);
        }else{
            Create_client.checkdtos.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void typeclient_create () {
        if (DAO_client.typeclient()==true){
            Create_client.checktype_client.setIcon(Singleton_client.ok);
        }else{
            Create_client.checktype_client.setIcon(Singleton_client.cancel);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                           VALIDATE CLIENT UPDATE                       //
    //                                                                        //
    ////////////////////////////////////////////////////////////////////////////
    
    public static void DNI_update () {
        if (DAO_client.DNI_update()==true){
            Update_client.checkDNI.setIcon(Singleton_client.ok);
        }else{
            Update_client.checkDNI.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void user_update () {
        if (DAO_client.user_update()==true){
            Update_client.checkuser.setIcon(Singleton_client.ok);
        }else{
            Update_client.checkuser.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void pass_update () {
        if (DAO_client.pass_update()==true){
            Update_client.checkpass.setIcon(Singleton_client.ok);
        }else{
            Update_client.checkpass.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void name_update () {
        if (DAO_client.name_update()==true){
            Update_client.checkname.setIcon(Singleton_client.ok);
        }else{
            Update_client.checkname.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void surname_update () {
        if (DAO_client.surname_update()==true){
            Update_client.checksurname.setIcon(Singleton_client.ok);
        }else{
            Update_client.checksurname.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void email_update () {
        if (DAO_client.email_update()==true){
            Update_client.checkemail.setIcon(Singleton_client.ok);
        }else{
            Update_client.checkemail.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void mobilephone_update () {
        if (DAO_client.mobilephone_update()==true){
            Update_client.checkmobilephone.setIcon(Singleton_client.ok);
        }else{
            Update_client.checkmobilephone.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void shopping_update () {
        if (DAO_client.shopping_update()==true){
            Update_client.checkshopping.setIcon(Singleton_client.ok);
        }else{
            Update_client.checkshopping.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void dtos_update () {
        if (DAO_client.dtos_update()==true){
            Update_client.checkdtos.setIcon(Singleton_client.ok);
        }else{
            Update_client.checkdtos.setIcon(Singleton_client.cancel);
        }
    }
    
    public static void typeclient_update () {
        if (DAO_client.typeclient_update()==true){
            Update_client.checktype_client.setIcon(Singleton_client.ok);
        }else{
            Update_client.checktype_client.setIcon(Singleton_client.cancel);
        }
    }
}
