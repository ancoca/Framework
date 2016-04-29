/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.login.model.functions;

import framework.module.login.model.classes.Singleton_login;
import framework.module.menu_config.controller.Controller_menu_config;
import framework.module.menu_config.view.Menu;
import javax.swing.JFrame;

/**
 *
 * @author angel
 */
public class Function_logout {
    
    public static void logout (JFrame jframe) {
        Singleton_login.login=false;
        jframe.dispose();
        new Controller_menu_config(new Menu(), 0).iniciar(0);
    }
}
