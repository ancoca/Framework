/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.login.model.DAO;

import framework.module.admin.model.BLL.BLL_admin.BLL_BD_admin;
import framework.module.admin.model.classes.Singleton_admin;
import framework.module.client.model.BLL.BLL_client.BLL_BD_client;
import framework.module.client.model.classes.Singleton_client;
import framework.module.login.view.Login;
import framework.module.userregister.model.classes.Singleton_userregister;
import framework.module.userregister.model.classes.User_register;
import framework.module.userregister.model.functions.json_auto_userregister;

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

        json_auto_userregister.openjson_userregister();
        int arraylist =Singleton_userregister.userregister.size();
        for (int i = 0; i<arraylist; i++) {
            u1 = (User_register) Singleton_userregister.userregister.get(i);
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
