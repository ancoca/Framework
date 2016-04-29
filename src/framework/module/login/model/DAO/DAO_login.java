/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.login.model.DAO;

import framework.module.login.model.classes.Singleton_login;
import framework.module.users.admin.model.BLL.BLL_admin.BLL_BD_admin;
import framework.module.users.admin.model.classes.Singleton_admin;
import framework.module.users.client.model.BLL.BLL_client.BLL_BD_client;
import framework.module.users.client.model.classes.Singleton_client;
import framework.module.login.view.Login;
import framework.module.users.userregister.model.BLL.BLL_userregister.BLL_userregister;
import framework.module.users.userregister.model.classes.Singleton_userregister;
import framework.module.users.userregister.model.classes.User_register;
import framework.module.users.userregister.model.functions.json_auto_userregister;

/**
 *
 * @author angel
 */
public class DAO_login {
    
    /**
     * LOGIN ADMIN
     * @return 
     */
    public static boolean login_admin () {
        boolean correcto = false;
        boolean correct = BLL_BD_admin.find_BD(Login.txtDNI.getText());
        if (Singleton_admin.admin==null){
            correcto=false;
        }else{
            if (Singleton_admin.admin.getDNI().equals(Login.txtDNI.getText())) {
                correcto=true;
                if (Singleton_admin.admin.getPass().equals(Login.txtpass.getText())) {
                    correcto=true;
                }else{
                    correcto=false;
                }
            }
        }
        return correcto;
    }
    
    /**
     * LOGIN CLIENT
     * @return 
     */
    public static boolean login_client () {
        boolean correcto = false;
        BLL_BD_client.find_BD(Login.txtDNI.getText());
        if (Singleton_client.client==null){
            correcto=false;
        }else{
            if (Singleton_client.client.getDNI().equals(Login.txtDNI.getText())) {
                correcto=true;
                if (Singleton_client.client.getPass().equals(Login.txtpass.getText())) {
                    correcto=true;
                }else{
                    correcto=false;
                }
            }
        }
        return correcto;
    }
    
    /**
     * LOGIN USER REGISTER
     * @return 
     */
    public static boolean login_userregister () {
        boolean correcto = false;
        User_register u1 = null;
        String s = "";

        Singleton_login.tabla=false;
        Singleton_login.dni = Login.txtDNI.getText();
        u1 = BLL_userregister.IDuserregister();
        Singleton_userregister.user_register = u1;
        if (u1==null){
            correcto=false;
        }else{
            if (u1.getDNI().equals(Login.txtDNI.getText())) {
                correcto=true;
                if (u1.getPass().equals(Login.txtpass.getText())) {
                    correcto=true;
                }else{
                    correcto=false;
                }
            }
        }

        return correcto;
    }
}
