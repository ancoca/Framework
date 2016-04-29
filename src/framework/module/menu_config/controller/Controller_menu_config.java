/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.menu_config.controller;

import static framework.classes.Singleton_general.mongo;
import framework.module.login.model.classes.Singleton_login;
import framework.module.login.model.functions.Function_login;
import framework.module.login.model.functions.Function_logout;
import framework.module.menu_config.model.BLL.BLL_config;
import framework.module.menu_config.model.classes.Singleton_config;
import framework.module.menu_config.model.classes.language.Language_menu_config;
import framework.module.menu_config.view.Config;
import framework.module.menu_config.view.Menu;
import framework.module.users.userregister.model.functions.json_auto_userregister;
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
public class Controller_menu_config implements MouseListener, ActionListener{
    
    public static Menu inicio;
    public static Config config;
    
    public Controller_menu_config (JFrame jframe, int i) {
        if (i==0){
            inicio = (Menu) jframe;
        }
        if (i==1){
            config = (Config) jframe;
        }
    }
    
    public enum action{
        //VISTA GENERAL
        lblajustes,
        lblusuario,
        lblcliente,
        lbladministrador,
        lblinicio,
        logout,
        
        //VISTA CONFIGURACION
        lblajustes_config,
        lblusuario_config,
        lblcliente_config,
        lbladministrador_config,
        lblinicio_config,
        lblvolver,
        btnaceptar,
        btncancelar,
        lblvolver1,
        btnaceptar1,
        btncancelar1,
        lblvolver2,
        btnaceptar2,
        btncancelar2,
        lblvolver3,
        btnaceptar3,
        btncancelar3,
        lblvolver4,
        btnaceptar4,
        btncancelar4,
        logout_config
        
    }
    
    public void iniciar (int i) {
        //VISTA DE MENU
        if (i==0){
            inicio.setVisible(true);
            
            
            inicio.jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);
            
            inicio.setTitle("Framework");
            inicio.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada
            //inicio.setLocationRelativeTo(null);
            //inicio.setSize(525,425);//ancho x alto
            //inicio.setResizable(false);
            //Image icono=Toolkit.getDefaultToolkit().getImage("p1.jpg");
            //inicio.setIconImage(icono);
            
            logout_inicio();
            this.inicio.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    this.inicio.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    mongo.disconnect();
                    json_auto_userregister.savejson_userregister();
                    JOptionPane.showMessageDialog(null,Language_menu_config.getInstance().getProperty("exit"));
                    inicio.dispose();
                    System.exit(0);
                }
            });
            
            this.inicio.lblajustes.setName("lblajustes");
            this.inicio.lblajustes.addMouseListener(this);
            
            this.inicio.lblusuario.setName("lblusuario");
            this.inicio.lblusuario.addMouseListener(this);
            
            this.inicio.lblcliente.setName("lblcliente");
            this.inicio.lblcliente.addMouseListener(this);
            
            this.inicio.lbladministrador.setName("lbladministrador");
            this.inicio.lbladministrador.addMouseListener(this);
            
            this.inicio.lblinicio.setName("lblinicio");
            this.inicio.lblinicio.addMouseListener(this);
            
            this.inicio.logout.setName("logout");
            this.inicio.logout.addMouseListener(this);
        }
        
        if (i==1){
            config.jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);

            this.config.setVisible(true);
            this.config.setTitle("Framework");
            this.config.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada
            //this.config.setLocationRelativeTo(null);
            //this.config.setSize(525,425);//ancho x alto
            //this.config.setResizable(false);
            //Image icono=Toolkit.getDefaultToolkit().getImage("p1.jpg");
            //this.config.setIconImage(icono);

            logout_config();
            BLL_config.getConfig();

            this.config.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    this.config.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    mongo.disconnect();
                    json_auto_userregister.savejson_userregister();
                    JOptionPane.showMessageDialog(null,Language_menu_config.getInstance().getProperty("exit"));
                    config.dispose();
                    System.exit(0);
                }
            });
            
            this.config.lblajustes.setName("lblajustes_config");
            this.config.lblajustes.addMouseListener(this);
            
            this.config.lblusuario.setName("lblusuario_config");
            this.config.lblusuario.addMouseListener(this);
            
            this.config.lblcliente.setName("lblcliente_config");
            this.config.lblcliente.addMouseListener(this);
            
            this.config.lbladministrador.setName("lbladministrador_config");
            this.config.lbladministrador.addMouseListener(this);
            
            this.config.lblinicio.setName("lblinicio_config");
            this.config.lblinicio.addMouseListener(this);
            
            this.config.lblvolver.setName("lblvolver");
            this.config.lblvolver.addMouseListener(this);
            
            this.config.btnaceptar.setActionCommand("btnaceptar");
            this.config.btnaceptar.addActionListener(this);
            
            this.config.btncancelar.setActionCommand("btncancelar");
            this.config.btncancelar.addActionListener(this);
            
            this.config.lblvolver1.setName("lblvolver1");
            this.config.lblvolver1.addMouseListener(this);
            
            this.config.btnaceptar1.setActionCommand("btnaceptar1");
            this.config.btnaceptar1.addActionListener(this);
            
            this.config.btncancelar1.setActionCommand("btncancelar1");
            this.config.btncancelar1.addActionListener(this);
            
            this.config.lblvolver2.setName("lblvolver2");
            this.config.lblvolver2.addMouseListener(this);
            
            this.config.btnaceptar2.setActionCommand("btnaceptar2");
            this.config.btnaceptar2.addActionListener(this);
            
            this.config.btncancelar2.setActionCommand("btncancelar2");
            this.config.btncancelar2.addActionListener(this);
            
            this.config.lblvolver3.setName("lblvolver3");
            this.config.lblvolver3.addMouseListener(this);
            
            this.config.btnaceptar3.setActionCommand("btnaceptar3");
            this.config.btnaceptar3.addActionListener(this);
            
            this.config.btncancelar3.setActionCommand("btncancelar3");
            this.config.btncancelar3.addActionListener(this);
            
            this.config.lblvolver4.setName("lblvolver4");
            this.config.lblvolver4.addMouseListener(this);
            
            this.config.btnaceptar4.setActionCommand("btnaceptar4");
            this.config.btnaceptar4.addActionListener(this);
            
            this.config.btncancelar4.setActionCommand("btncancelar4");
            this.config.btncancelar4.addActionListener(this);
            
            this.config.logout.setName("logout_config");
            this.config.logout.addMouseListener(this);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        switch(Controller_menu_config.action.valueOf(e.getComponent().getName())){
            case lblajustes:
                inicio.dispose();
                new Controller_menu_config(new Config(), 1).iniciar(1);
                break;
            case lblusuario:
                Function_login.login_userregister(inicio);
                break;
            case lblcliente:
                Function_login.login_client(inicio);
                break;
            case lbladministrador:
                Function_login.login_admin(inicio);
                break;
            case lblinicio:
                inicio.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case logout:
                Function_logout.logout(inicio);
                break;
            case lblajustes_config:
                config.dispose();
                new Controller_menu_config(new Config(), 1).iniciar(1);
                break;
            case lblusuario_config:
                Function_login.login_userregister(config);
                break;
            case lblcliente_config:
                Function_login.login_client(config);
                break;
            case lbladministrador_config:
                Function_login.login_admin(config);
                break;
            case lblinicio_config:
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case lblvolver:
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case lblvolver1:
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case lblvolver2:
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case lblvolver3:
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case lblvolver4:
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case logout_config:
                Function_logout.logout(config);
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
        switch(Controller_menu_config.action.valueOf(e.getComponent().getName())){
            case lblajustes:
                inicio.lblajustes.setForeground(java.awt.Color.blue);
                break;
            case lblusuario:
                inicio.lblusuario.setForeground(java.awt.Color.blue);
                break;
            case lblcliente:
                inicio.lblcliente.setForeground(java.awt.Color.blue);
                break;
            case lbladministrador:
                inicio.lbladministrador.setForeground(java.awt.Color.blue);
                break;
            case lblinicio:
                inicio.lblinicio.setForeground(java.awt.Color.blue);
                break;
            case logout:
                inicio.logout.setForeground(java.awt.Color.blue);
                break;
            case lblajustes_config:
                config.lblajustes.setForeground(java.awt.Color.blue);
                break;
            case lblusuario_config:
                config.lblusuario.setForeground(java.awt.Color.blue);
                break;
            case lblcliente_config:
                config.lblcliente.setForeground(java.awt.Color.blue);
                break;
            case lbladministrador_config:
                config.lbladministrador.setForeground(java.awt.Color.blue);
                break;
            case lblinicio_config:
                config.lblinicio.setForeground(java.awt.Color.blue);
                break;
            case lblvolver:
                config.lblvolver.setIcon(Singleton_config.volver);
                break;
            case lblvolver1:
                config.lblvolver1.setIcon(Singleton_config.volver);
                break;
            case lblvolver2:
                config.lblvolver2.setIcon(Singleton_config.volver);
                break;
            case lblvolver3:
                config.lblvolver3.setIcon(Singleton_config.volver);
                break;
            case lblvolver4:
                config.lblvolver4.setIcon(Singleton_config.volver);
                break;
            case logout_config:
                config.logout.setForeground(java.awt.Color.blue);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch(Controller_menu_config.action.valueOf(e.getComponent().getName())){
            case lblajustes:
                inicio.lblajustes.setForeground(java.awt.Color.cyan);
                break;
            case lblusuario:
                inicio.lblusuario.setForeground(java.awt.Color.cyan);
                break;
            case lblcliente:
                inicio.lblcliente.setForeground(java.awt.Color.cyan);
                break;
            case lbladministrador:
                inicio.lbladministrador.setForeground(java.awt.Color.cyan);
                break;
            case lblinicio:
                inicio.lblinicio.setForeground(java.awt.Color.cyan);
                break;
            case logout:
                inicio.logout.setForeground(java.awt.Color.cyan);
                break;
            case lblajustes_config:
                config.lblajustes.setForeground(java.awt.Color.cyan);
                break;
            case lblusuario_config:
                config.lblusuario.setForeground(java.awt.Color.cyan);
                break;
            case lblcliente_config:
                config.lblcliente.setForeground(java.awt.Color.cyan);
                break;
            case lbladministrador_config:
                config.lbladministrador.setForeground(java.awt.Color.cyan);
                break;
            case lblinicio_config:
                config.lblinicio.setForeground(java.awt.Color.cyan);
                break;
            case lblvolver:
                config.lblvolver.setIcon(Singleton_config.volver_bn);
                break;
            case lblvolver1:
                config.lblvolver1.setIcon(Singleton_config.volver_bn);
                break;
            case lblvolver2:
                config.lblvolver2.setIcon(Singleton_config.volver_bn);
                break;
            case lblvolver3:
                config.lblvolver3.setIcon(Singleton_config.volver_bn);
                break;
            case lblvolver4:
                config.lblvolver4.setIcon(Singleton_config.volver_bn);
                break;
            case logout_config:
                config.logout.setForeground(java.awt.Color.cyan);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Controller_menu_config.action.valueOf(e.getActionCommand())) {
            case btnaceptar:
                BLL_config.setConfig();
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case btncancelar:
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case btnaceptar1:
                BLL_config.setConfig();
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case btncancelar1:
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case btnaceptar2:
                BLL_config.setConfig();
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case btncancelar2:
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case btnaceptar3:
                BLL_config.setConfig();
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case btncancelar3:
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case btnaceptar4:
                BLL_config.setConfig();
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case btncancelar4:
                config.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
        }
    }
    
    public static void logout_inicio () {
        if (Singleton_login.login==false) {
            inicio.lbladministrador.setEnabled(true);
            inicio.lblcliente.setEnabled(true);
            inicio.lblusuario.setEnabled(true);
            inicio.user_name.setVisible(false);
            inicio.logout.setVisible(false);
        }else{
            inicio.user_name.setVisible(true);
            inicio.user_name.setText(Singleton_login.name+" "+Singleton_login.surname);
            inicio.logout.setVisible(true);
            if (Singleton_login.tipo=='a'){
                inicio.lbladministrador.setEnabled(true);
                inicio.lblcliente.setEnabled(true);
                inicio.lblusuario.setEnabled(true);
            }
            if (Singleton_login.tipo=='c'){
                inicio.lbladministrador.setEnabled(false);
                inicio.lblcliente.setEnabled(true);
                inicio.lblusuario.setEnabled(false);
            }
            if (Singleton_login.tipo=='u'){
                inicio.lbladministrador.setEnabled(false);
                inicio.lblcliente.setEnabled(false);
                inicio.lblusuario.setEnabled(true);
            }
        }
    }
    
    public static void logout_config () {
        if (Singleton_login.login==false) {
            config.lbladministrador.setEnabled(true);
            config.lblcliente.setEnabled(true);
            config.lblusuario.setEnabled(true);
            config.user_name.setVisible(false);
            config.logout.setVisible(false);
        }else{
            config.user_name.setVisible(true);
            config.user_name.setText(Singleton_login.name+" "+Singleton_login.surname);
            config.logout.setVisible(true);
            if (Singleton_login.tipo=='a'){
                config.lbladministrador.setEnabled(true);
                config.lblcliente.setEnabled(true);
                config.lblusuario.setEnabled(true);
            }
            if (Singleton_login.tipo=='c'){
                config.lbladministrador.setEnabled(false);
                config.lblcliente.setEnabled(true);
                config.lblusuario.setEnabled(false);
            }
            if (Singleton_login.tipo=='u'){
                config.lbladministrador.setEnabled(false);
                config.lblcliente.setEnabled(false);
                config.lblusuario.setEnabled(true);
            }
        }
    }
}
