/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.config.controller;

import framework.module.admin.controller.Controller_admin;
import framework.module.admin.model.functions.json_auto_admin;
import framework.module.admin.view.List_admin;
import framework.module.client.controller.Controller_client;
import framework.module.client.model.functions.json_auto_client;
import framework.module.client.view.List_client;
import framework.module.config.model.BLL.BLL_config;
import framework.module.config.model.classes.Singleton_config;
import framework.module.config.view.Config;
import framework.module.menu.controller.Controller_menu;
import framework.module.menu.view.Menu;
import framework.module.userregister.controller.Controller_userregister;
import framework.module.userregister.model.functions.json_auto_userregister;
import framework.module.userregister.view.List_userregister;
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
public class Controller_config implements MouseListener, ActionListener{
    public static Config config;
    
    public Controller_config (JFrame jframe, int i){
        if (i==0){
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
        
        //VISTA CONFIGURACION
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
        btncancelar4
    }
    
    public void iniciar (int i){
        if (i==0){
            config.jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);

            this.config.setVisible(true);
            this.config.setTitle("Framework");
            this.config.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada
            //this.config.setLocationRelativeTo(null);
            //this.config.setSize(525,425);//ancho x alto
            //this.config.setResizable(false);
            //Image icono=Toolkit.getDefaultToolkit().getImage("p1.jpg");
            //this.config.setIconImage(icono);

            BLL_config.getConfig();

            this.config.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    this.config.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    json_auto_admin.savejson_admin();
                    json_auto_client.savejson_client();
                    json_auto_userregister.savejson_userregister();
                    JOptionPane.showMessageDialog(null,"Saliendo de la aplicación");
                    config.dispose();
                    System.exit(0);
                }
            });
            
            this.config.lblajustes.setName("lblajustes");
            this.config.lblajustes.addMouseListener(this);
            
            this.config.lblusuario.setName("lblusuario");
            this.config.lblusuario.addMouseListener(this);
            
            this.config.lblcliente.setName("lblcliente");
            this.config.lblcliente.addMouseListener(this);
            
            this.config.lbladministrador.setName("lbladministrador");
            this.config.lbladministrador.addMouseListener(this);
            
            this.config.lblinicio.setName("lblinicio");
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
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(Controller_config.action.valueOf(e.getComponent().getName())){
            case lblajustes:
                config.dispose();
                new Controller_config(new Config(), 0).iniciar(0);
                break;
            case lblusuario:
                config.dispose();
                new Controller_userregister(new List_userregister(), 0).iniciar(0);
                break;
            case lblcliente:
                config.dispose();
                new Controller_client(new List_client(), 0).iniciar(0);
                break;
            case lbladministrador:
                config.dispose();
                new Controller_admin(new List_admin(), 0).iniciar(0);
                break;
            case lblinicio:
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case lblvolver:
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case lblvolver1:
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case lblvolver2:
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case lblvolver3:
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case lblvolver4:
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
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
        switch(Controller_config.action.valueOf(e.getComponent().getName())){
            case lblajustes:
                config.lblajustes.setForeground(java.awt.Color.blue);
                break;
            case lblusuario:
                config.lblusuario.setForeground(java.awt.Color.blue);
                break;
            case lblcliente:
                config.lblcliente.setForeground(java.awt.Color.blue);
                break;
            case lbladministrador:
                config.lbladministrador.setForeground(java.awt.Color.blue);
                break;
            case lblinicio:
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
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch(Controller_config.action.valueOf(e.getComponent().getName())){
            case lblajustes:
                config.lblajustes.setForeground(java.awt.Color.cyan);
                break;
            case lblusuario:
                config.lblusuario.setForeground(java.awt.Color.cyan);
                break;
            case lblcliente:
                config.lblcliente.setForeground(java.awt.Color.cyan);
                break;
            case lbladministrador:
                config.lbladministrador.setForeground(java.awt.Color.cyan);
                break;
            case lblinicio:
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
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (action.valueOf(e.getActionCommand())) {
            case btnaceptar:
                BLL_config.setConfig();
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case btncancelar:
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case btnaceptar1:
                BLL_config.setConfig();
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case btncancelar1:
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case btnaceptar2:
                BLL_config.setConfig();
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case btncancelar2:
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case btnaceptar3:
                BLL_config.setConfig();
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case btncancelar3:
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case btnaceptar4:
                BLL_config.setConfig();
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
            case btncancelar4:
                config.dispose();
                new Controller_menu(new Menu(), 0).iniciar(0);
                break;
        }
    }
}
