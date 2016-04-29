/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.login.model.BLL;

import framework.classes.Singleton_general;
import framework.module.users.admin.controller.Controller_admin;
import framework.module.users.admin.model.classes.Singleton_admin;
import framework.module.users.admin.view.List_admin;
import framework.module.users.client.controller.Controller_client;
import framework.module.users.client.model.classes.Singleton_client;
import framework.module.users.client.view.List_client;
import framework.module.users.client.view.Update_client;
import framework.module.login.model.DAO.DAO_login;
import framework.module.login.model.classes.Singleton_login;
import framework.module.login.model.classes.language.Language_login;
import framework.module.login.view.Login;
import framework.module.users.userregister.controller.Controller_userregister;
import framework.module.users.userregister.model.classes.Singleton_userregister;
import framework.module.users.userregister.view.List_userregister;
import framework.module.users.userregister.view.Update_userregister;
import javax.swing.JFrame;

/**
 *
 * @author angel
 */
public class BLL_login {
    
    /**
     * LOGIN ADMIN
     * @param jframe 
     */
    public static void login_admin (JFrame jframe) {
        boolean correcto = DAO_login.login_admin();
        
        if (correcto == true){
            Singleton_login.admin = true;
            Singleton_login.dni = Login.txtDNI.getText();
            Singleton_login.tabla = false;
            Singleton_login.login = true;
            Singleton_login.name = Singleton_admin.admin.getName();
            Singleton_login.surname = Singleton_admin.admin.getSurname();
            Singleton_login.tipo = 'a';
            jframe.dispose();
            new Controller_admin(new List_admin(), 0).iniciar(0);
        }else{
            Login.error_login.setText(Language_login.getInstance().getProperty("error_login"));
        }
    }
    
    /**
     * LOGIN CLIENT
     * @param jframe 
     */
    public static void login_client (JFrame jframe) {
        boolean correcto1 = DAO_login.login_client();
        
        if (correcto1 == true){
            Singleton_login.admin = false;
            Singleton_login.dni = Login.txtDNI.getText();
            Singleton_login.tabla = false;
            Singleton_login.login = true;
            Singleton_login.name = Singleton_client.client.getName();
            Singleton_login.surname = Singleton_client.client.getSurname();
            Singleton_login.tipo = 'c';
            jframe.dispose();
            new Controller_client(new Update_client(), 2).iniciar(2);
        }else{
            boolean correcto2 = DAO_login.login_admin();
            if (correcto2 == true){
                Singleton_login.admin = true;
                Singleton_login.dni = Login.txtDNI.getText();
                Singleton_login.tabla = false;
                Singleton_login.login = true;
                Singleton_login.name = Singleton_admin.admin.getName();
                Singleton_login.surname = Singleton_admin.admin.getSurname();
                Singleton_login.tipo = 'a';
                jframe.dispose();
                new Controller_client(new List_client(), 0).iniciar(0);
            }else{
                Login.error_login.setText(Language_login.getInstance().getProperty("error_login"));
            }
        }
    }
    
    /**
     * LOGIN USER REGISTER
     * @param jframe 
     */
    public static void login_userregister (JFrame jframe) {
        boolean correcto1 = DAO_login.login_userregister();
        
        if (correcto1 == true){
            Singleton_login.admin = false;
            Singleton_login.dni = Login.txtDNI.getText();
            Singleton_login.tabla = false;
            Singleton_login.login = true;
            Singleton_login.name = Singleton_userregister.user_register.getName();
            Singleton_login.surname = Singleton_userregister.user_register.getSurname();
            Singleton_login.tipo = 'u';
            jframe.dispose();
            new Controller_userregister(new Update_userregister(), 2).iniciar(2);
        }else{
            boolean correcto2 = DAO_login.login_admin();
            if (correcto2 == true){
                Singleton_login.admin = true;
                Singleton_login.dni = Login.txtDNI.getText();
                Singleton_login.tabla = false;
                Singleton_login.login = true;
                Singleton_login.name = Singleton_admin.admin.getName();
                Singleton_login.surname = Singleton_admin.admin.getSurname();
                Singleton_login.tipo = 'a';
                jframe.dispose();
                new Controller_userregister(new List_userregister(), 0).iniciar(0);
            }else{
                Login.error_login.setText(Language_login.getInstance().getProperty("error_login"));
            }
        }
    }
}
