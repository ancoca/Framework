/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.login.model.BLL;

import framework.classes.Singleton_general;
import framework.module.admin.controller.Controller_admin;
import framework.module.admin.view.List_admin;
import framework.module.client.controller.Controller_client;
import framework.module.client.view.List_client;
import framework.module.client.view.Update_client;
import framework.module.login.model.DAO.DAO_login;
import framework.module.login.model.classes.language.Language_login;
import framework.module.login.view.Login;
import framework.module.userregister.controller.Controller_userregister;
import framework.module.userregister.view.List_userregister;
import framework.module.userregister.view.Update_userregister;
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
            Singleton_general.admin = true;
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
            Singleton_general.admin = false;
            Singleton_general.dni = Login.txtDNI.getText();
            Singleton_general.tabla = false;
            jframe.dispose();
            new Controller_client(new Update_client(), 2).iniciar(2);
        }else{
            boolean correcto2 = DAO_login.login_admin();
            if (correcto2 == true){
                Singleton_general.admin = true;
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
            Singleton_general.admin = false;
            Singleton_general.dni = Login.txtDNI.getText();
            Singleton_general.tabla = false;
            jframe.dispose();
            new Controller_userregister(new Update_userregister(), 2).iniciar(2);
        }else{
            boolean correcto2 = DAO_login.login_admin();
            if (correcto2 == true){
                Singleton_general.admin = true;
                jframe.dispose();
                new Controller_userregister(new List_userregister(), 0).iniciar(0);
            }else{
                Login.error_login.setText(Language_login.getInstance().getProperty("error_login"));
            }
        }
    }
}
