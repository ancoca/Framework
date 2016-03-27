/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.menu.controller;

import framework.module.admin.controller.Controller_admin;
import framework.module.admin.model.functions.json_auto_admin;
import framework.module.admin.view.List_admin;
import framework.module.client.controller.Controller_client;
import framework.module.client.model.functions.json_auto_client;
import framework.module.client.view.List_client;
import framework.module.config.controller.Controller_config;
import framework.module.config.view.Config;
import framework.module.menu.view.Menu;
import framework.module.menu.view.Start;
import framework.module.userregister.controller.Controller_userregister;
import framework.module.userregister.model.functions.json_auto_userregister;
import framework.module.userregister.view.List_userregister;
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
public class Controller_menu implements MouseListener {
    public static Menu inicio;
    public static Start start;
    
    public Controller_menu (JFrame jframe, int i) {
        if (i==0){
            inicio = (Menu) jframe;
        }
    }
    
    public enum action{
        //VISTA GENERAL
        lblajustes,
        lblusuario,
        lblcliente,
        lbladministrador,
        lblinicio
        
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
            
            this.inicio.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    this.inicio.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    json_auto_admin.savejson_admin();
                    json_auto_client.savejson_client();
                    json_auto_userregister.savejson_userregister();
                    JOptionPane.showMessageDialog(null,"Saliendo de la aplicación");
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
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        switch(Controller_menu.action.valueOf(e.getComponent().getName())){
            case lblajustes:
                inicio.dispose();
                new Controller_config(new Config(), 0).iniciar(0);
                break;
            case lblusuario:
                inicio.dispose();
                new Controller_userregister(new List_userregister(), 0).iniciar(0);
                break;
            case lblcliente:
                inicio.dispose();
                new Controller_client(new List_client(), 0).iniciar(0);
                break;
            case lbladministrador:
                inicio.dispose();
                new Controller_admin(new List_admin(), 0).iniciar(0);
                break;
            case lblinicio:
                inicio.dispose();
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
        switch(Controller_menu.action.valueOf(e.getComponent().getName())){
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
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch(Controller_menu.action.valueOf(e.getComponent().getName())){
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
        }
    }
}
