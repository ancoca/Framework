/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.login.model.functions;

import framework.module.users.admin.controller.Controller_admin;
import framework.module.users.admin.view.List_admin;
import framework.module.users.client.controller.Controller_client;
import framework.module.users.client.view.List_client;
import framework.module.users.client.view.Update_client;
import framework.module.login.controller.Controller_login;
import framework.module.login.model.classes.Singleton_login;
import framework.module.login.view.Login;
import framework.module.users.userregister.controller.Controller_userregister;
import framework.module.users.userregister.view.List_userregister;
import framework.module.users.userregister.view.Update_userregister;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author angel
 */
public class Function_login {
    
    public static void login_admin (JFrame jframe){
        if (Singleton_login.login==false){
            jframe.dispose();
            new Controller_login(new Login(), 0).iniciar(0);
        }else{
            if (Singleton_login.tipo=='a'){
                jframe.dispose();
                new Controller_admin(new List_admin(), 0).iniciar(0);
            }
        }
    }
    
    public static void login_client (JFrame jframe){
        if (Singleton_login.login==false){
            jframe.dispose();
            new Controller_login(new Login(), 1).iniciar(1);
        }else{
            if (Singleton_login.tipo=='a'){
                jframe.dispose();
                new Controller_client(new List_client(), 0).iniciar(0);
            }else if (Singleton_login.tipo=='c'){
                jframe.dispose();
                new Controller_client(new Update_client(), 2).iniciar(2);
            }
        }
    }
    
    public static void login_userregister (JFrame jframe){
        if (Singleton_login.login == false){
            jframe.dispose();
            new Controller_login(new Login(), 2).iniciar(2);
        }else{
            if (Singleton_login.tipo == 'a'){
                jframe.dispose();
                new Controller_userregister(new List_userregister(), 0).iniciar(0);
            }else if (Singleton_login.tipo == 'u'){
                jframe.dispose();
                new Controller_userregister(new Update_userregister(), 2).iniciar(2);
            }
        }
    }
}
