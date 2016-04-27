/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.login.controller;

import framework.classes.Singleton_general;
import static framework.classes.Singleton_general.mongo;
import framework.module.admin.controller.Controller_admin;
import framework.module.admin.view.Create_admin;
import framework.module.client.controller.Controller_client;
import framework.module.client.view.Create_client;
import framework.module.login.model.BLL.BLL_login;
import framework.module.login.model.classes.Singleton_login;
import framework.module.login.model.classes.language.Language_login;
import framework.module.login.view.Login;
import framework.module.menu_config.controller.Controller_menu_config;
import framework.module.menu_config.view.Config;
import framework.module.menu_config.view.Menu;
import framework.module.userregister.controller.Controller_userregister;
import framework.module.userregister.model.functions.json_auto_userregister;
import framework.module.userregister.view.Create_userregister;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author angel
 */
public class Controller_login implements MouseListener, ActionListener{
    
    public static Login login_admin;
    public static Login login_client;
    public static Login login_userregister;
    
    public Controller_login (JFrame jframe, int i){
        switch (i) {
            case 0:
                login_admin = (Login) jframe;
                break;
            case 1:
                login_client = (Login) jframe;
                break;
            case 2:
                login_userregister = (Login) jframe;
                break;
        }
    }
    
    public enum action{
        //VISTA ADMIN
        acept_admin,
        cancel_admin,
        lblajustes_admin,
        lblusuario_admin,
        lblcliente_admin,
        lbladministrador_admin,
        lblinicio_admin,
        new_user_admin,
        
        //VISTA CLIENT
        acept_client,
        cancel_client,
        lblajustes_client,
        lblusuario_client,
        lblcliente_client,
        lbladministrador_client,
        lblinicio_client,
        new_user_client,
        
        //VISTA USER REGISTER
        acept_userregister,
        cancel_userregister,
        lblajustes_userregister,
        lblusuario_userregister,
        lblcliente_userregister,
        lbladministrador_userregister,
        lblinicio_userregister,
        new_user_userregister
    }
    
    public void iniciar (int i) {
        switch (i) {
            case 0:
                this.login_admin.setVisible(true);
                this.login_admin.setTitle("Framework");
                this.login_admin.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada
                //this.login_admin.setLocationRelativeTo(null);
                //this.login_admin.setSize(525,425);//ancho x alto
                //this.login_admin.setResizable(false);
                //Image icono=Toolkit.getDefaultToolkit().getImage("p1.jpg");
                //this.login_admin.setIconImage(icono);
                
                this.login_admin.new_user.setVisible(false);
                
                this.login_admin.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.login_admin.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        mongo.disconnect();
                        json_auto_userregister.savejson_userregister();
                        JOptionPane.showMessageDialog(null,Language_login.getInstance().getProperty("exit"));
                        login_admin.dispose();
                        System.exit(0);
                    }
                });

                this.login_admin.lblajustes.setName("lblajustes_admin");
                this.login_admin.lblajustes.addMouseListener(this);

                this.login_admin.lblusuario.setName("lblusuario_admin");
                this.login_admin.lblusuario.addMouseListener(this);

                this.login_admin.lblcliente.setName("lblcliente_admin");
                this.login_admin.lblcliente.addMouseListener(this);

                this.login_admin.lbladministrador.setName("lbladministrador_admin");
                this.login_admin.lbladministrador.addMouseListener(this);

                this.login_admin.lblinicio.setName("lblinicio_admin");
                this.login_admin.lblinicio.addMouseListener(this);
                
                this.login_admin.acept.setActionCommand("acept_admin");
                this.login_admin.acept.addActionListener(this);
                
                this.login_admin.cancel.setActionCommand("cancel_admin");
                this.login_admin.cancel.addActionListener(this);
                
                this.login_admin.new_user.setName("new_user_admin");
                this.login_admin.new_user.addMouseListener(this);
                break;
                
            case 1:
                this.login_client.setVisible(true);
                this.login_client.setTitle("Framework");
                this.login_client.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada
                //this.login_client.setLocationRelativeTo(null);
                //this.login_client.setSize(525,425);//ancho x alto
                //this.login_client.setResizable(false);
                //Image icono=Toolkit.getDefaultToolkit().getImage("p1.jpg");
                //this.login_client.setIconImage(icono);
                
                this.login_client.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.login_client.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        mongo.disconnect();
                        json_auto_userregister.savejson_userregister();
                        JOptionPane.showMessageDialog(null,Language_login.getInstance().getProperty("exit"));
                        login_client.dispose();
                        System.exit(0);
                    }
                });

                this.login_client.lblajustes.setName("lblajustes_client");
                this.login_client.lblajustes.addMouseListener(this);

                this.login_client.lblusuario.setName("lblusuario_client");
                this.login_client.lblusuario.addMouseListener(this);

                this.login_client.lblcliente.setName("lblcliente_client");
                this.login_client.lblcliente.addMouseListener(this);

                this.login_client.lbladministrador.setName("lbladministrador_client");
                this.login_client.lbladministrador.addMouseListener(this);

                this.login_client.lblinicio.setName("lblinicio_client");
                this.login_client.lblinicio.addMouseListener(this);
                
                this.login_client.acept.setActionCommand("acept_client");
                this.login_client.acept.addActionListener(this);
                
                this.login_client.cancel.setActionCommand("cancel_client");
                this.login_client.cancel.addActionListener(this);
                
                this.login_client.new_user.setName("new_user_client");
                this.login_client.new_user.addMouseListener(this);
                break;
                
            case 2:
                this.login_userregister.setVisible(true);
                this.login_userregister.setTitle("Framework");
                this.login_userregister.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada
                //this.login_userregister.setLocationRelativeTo(null);
                //this.login_userregister.setSize(525,425);//ancho x alto
                //this.login_userregister.setResizable(false);
                //Image icono=Toolkit.getDefaultToolkit().getImage("p1.jpg");
                //this.login_userregister.setIconImage(icono);
                
                this.login_userregister.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.login_userregister.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        mongo.disconnect();
                        json_auto_userregister.savejson_userregister();
                        JOptionPane.showMessageDialog(null,Language_login.getInstance().getProperty("exit"));
                        login_userregister.dispose();
                        System.exit(0);
                    }
                });

                this.login_userregister.lblajustes.setName("lblajustes_userregister");
                this.login_userregister.lblajustes.addMouseListener(this);

                this.login_userregister.lblusuario.setName("lblusuario_userregister");
                this.login_userregister.lblusuario.addMouseListener(this);

                this.login_userregister.lblcliente.setName("lblcliente_userregister");
                this.login_userregister.lblcliente.addMouseListener(this);

                this.login_userregister.lbladministrador.setName("lbladministrador_userregister");
                this.login_userregister.lbladministrador.addMouseListener(this);

                this.login_userregister.lblinicio.setName("lblinicio_userregister");
                this.login_userregister.lblinicio.addMouseListener(this);
                
                this.login_userregister.acept.setActionCommand("acept_userregister");
                this.login_userregister.acept.addActionListener(this);
                
                this.login_userregister.cancel.setActionCommand("cancel_userregister");
                this.login_userregister.cancel.addActionListener(this);
                
                this.login_userregister.new_user.setName("new_user_userregister");
                this.login_userregister.new_user.addMouseListener(this);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
        switch(Controller_login.action.valueOf(e.getComponent().getName())){
            case lblajustes_admin:
                login_admin.dispose();
                new Controller_menu_config(new Config(), 1).iniciar(1);
                break;
            case lblusuario_admin:
                login_admin.dispose();
                new Controller_login(new Login(), 2).iniciar(2);
                break;
            case lblcliente_admin:
                login_admin.dispose();
                new Controller_login(new Login(), 1).iniciar(1);
                break;
            case lbladministrador_admin:
                login_admin.dispose();
                new Controller_login(new Login(), 0).iniciar(0);
                break;
            case lblinicio_admin:
                login_admin.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case new_user_admin:
                login_admin.dispose();
                new Controller_admin(new Create_admin(), 1).iniciar(1);
                break;
            case lblajustes_client:
                login_client.dispose();
                new Controller_menu_config(new Config(), 1).iniciar(1);
                break;
            case lblusuario_client:
                login_client.dispose();
                new Controller_login(new Login(), 2).iniciar(2);
                break;
            case lblcliente_client:
                login_client.dispose();
                new Controller_login(new Login(), 1).iniciar(1);
                break;
            case lbladministrador_client:
                login_client.dispose();
                new Controller_login(new Login(), 0).iniciar(0);
                break;
            case lblinicio_client:
                login_client.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case new_user_client:
                login_client.dispose();
                new Controller_client(new Create_client(), 1).iniciar(1);
                break;
            case lblajustes_userregister:
                login_userregister.dispose();
                new Controller_menu_config(new Config(), 1).iniciar(1);
                break;
            case lblusuario_userregister:
                login_userregister.dispose();
                new Controller_login(new Login(), 2).iniciar(2);
                break;
            case lblcliente_userregister:
                login_userregister.dispose();
                new Controller_login(new Login(), 1).iniciar(1);
                break;
            case lbladministrador_userregister:
                login_userregister.dispose();
                new Controller_login(new Login(), 0).iniciar(0);
                break;
            case lblinicio_userregister:
                login_userregister.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case new_user_userregister:
                login_userregister.dispose();
                new Controller_userregister(new Create_userregister(), 1).iniciar(1);
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        switch(Controller_login.action.valueOf(e.getComponent().getName())){
            case lblajustes_admin:
                login_admin.lblajustes.setForeground(java.awt.Color.blue);
                break;
            case lblusuario_admin:
                login_admin.lblusuario.setForeground(java.awt.Color.blue);
                break;
            case lblcliente_admin:
                login_admin.lblcliente.setForeground(java.awt.Color.blue);
                break;
            case lbladministrador_admin:
                login_admin.lbladministrador.setForeground(java.awt.Color.blue);
                break;
            case lblinicio_admin:
                login_admin.lblinicio.setForeground(java.awt.Color.blue);
                break;
            case new_user_admin:
                login_admin.new_user.setForeground(java.awt.Color.blue);
                break;
            case lblajustes_client:
                login_client.lblajustes.setForeground(java.awt.Color.blue);
                break;
            case lblusuario_client:
                login_client.lblusuario.setForeground(java.awt.Color.blue);
                break;
            case lblcliente_client:
                login_client.lblcliente.setForeground(java.awt.Color.blue);
                break;
            case lbladministrador_client:
                login_client.lbladministrador.setForeground(java.awt.Color.blue);
                break;
            case lblinicio_client:
                login_client.lblinicio.setForeground(java.awt.Color.blue);
                break;
            case new_user_client:
                login_client.new_user.setForeground(java.awt.Color.blue);
                break;
            case lblajustes_userregister:
                login_userregister.lblajustes.setForeground(java.awt.Color.blue);
                break;
            case lblusuario_userregister:
                login_userregister.lblusuario.setForeground(java.awt.Color.blue);
                break;
            case lblcliente_userregister:
                login_userregister.lblcliente.setForeground(java.awt.Color.blue);
                break;
            case lbladministrador_userregister:
                login_userregister.lbladministrador.setForeground(java.awt.Color.blue);
                break;
            case lblinicio_userregister:
                login_userregister.lblinicio.setForeground(java.awt.Color.blue);
                break;
            case new_user_userregister:
                login_userregister.new_user.setForeground(java.awt.Color.blue);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch(Controller_login.action.valueOf(e.getComponent().getName())){
            case lblajustes_admin:
                login_admin.lblajustes.setForeground(java.awt.Color.cyan);
                break;
            case lblusuario_admin:
                login_admin.lblusuario.setForeground(java.awt.Color.cyan);
                break;
            case lblcliente_admin:
                login_admin.lblcliente.setForeground(java.awt.Color.cyan);
                break;
            case lbladministrador_admin:
                login_admin.lbladministrador.setForeground(java.awt.Color.cyan);
                break;
            case lblinicio_admin:
                login_admin.lblinicio.setForeground(java.awt.Color.cyan);
                break;
            case new_user_admin:
                login_admin.new_user.setForeground(java.awt.Color.cyan);
                break;
            case lblajustes_client:
                login_client.lblajustes.setForeground(java.awt.Color.cyan);
                break;
            case lblusuario_client:
                login_client.lblusuario.setForeground(java.awt.Color.cyan);
                break;
            case lblcliente_client:
                login_client.lblcliente.setForeground(java.awt.Color.cyan);
                break;
            case lbladministrador_client:
                login_client.lbladministrador.setForeground(java.awt.Color.cyan);
                break;
            case lblinicio_client:
                login_client.lblinicio.setForeground(java.awt.Color.cyan);
                break;
            case new_user_client:
                login_client.new_user.setForeground(java.awt.Color.cyan);
                break;
            case lblajustes_userregister:
                login_userregister.lblajustes.setForeground(java.awt.Color.cyan);
                break;
            case lblusuario_userregister:
                login_userregister.lblusuario.setForeground(java.awt.Color.cyan);
                break;
            case lblcliente_userregister:
                login_userregister.lblcliente.setForeground(java.awt.Color.cyan);
                break;
            case lbladministrador_userregister:
                login_userregister.lbladministrador.setForeground(java.awt.Color.cyan);
                break;
            case lblinicio_userregister:
                login_userregister.lblinicio.setForeground(java.awt.Color.cyan);
                break;
            case new_user_userregister:
                login_userregister.new_user.setForeground(java.awt.Color.cyan);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (action.valueOf(e.getActionCommand())) {
            case acept_admin:
                Singleton_login.tabla = false;
                BLL_login.login_admin(login_admin);
                break;
            case cancel_admin:
                login_admin.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case acept_client:
                Singleton_login.tabla = false;
                BLL_login.login_client(login_client);
                break;
            case cancel_client:
                login_client.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case acept_userregister:
                Singleton_login.tabla = false;
                BLL_login.login_userregister(login_userregister);
                break;
            case cancel_userregister:
                login_userregister.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
        }
    }
}
