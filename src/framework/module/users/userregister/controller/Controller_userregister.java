/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.users.userregister.controller;

import static framework.classes.Singleton_general.mongo;
import framework.module.login.model.classes.Singleton_login;
import framework.module.login.model.functions.Function_login;
import framework.module.login.model.functions.Function_logout;
import framework.module.menu_config.view.Config;
import framework.module.menu_config.controller.Controller_menu_config;
import framework.module.menu_config.view.Menu;
import framework.module.users.userregister.model.BLL.BLL_userregister.BLL_userregister;
import framework.module.users.userregister.model.DAO.DAO_userregister;
import framework.module.users.userregister.model.classes.Singleton_userregister;
import framework.module.users.userregister.model.classes.language.Language_userregister;
import framework.module.users.userregister.model.classes.miniSimpleTableModel_userregister;
import framework.module.users.userregister.model.functions.autocomplete.AutocompleteJComboBox_userregister;
import framework.module.users.userregister.model.functions.autocomplete.StringSearchable_userregister;
import framework.module.users.userregister.model.functions.json_auto_userregister;
import framework.module.users.userregister.model.functions.pagina_userregister;
import framework.module.users.userregister.view.Create_userregister;
import framework.module.users.userregister.view.List_userregister;
import framework.module.users.userregister.view.Read_userregister;
import framework.module.users.userregister.view.Update_userregister;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author angel
 */
public class Controller_userregister implements MouseListener, ActionListener, FocusListener, KeyListener{
    
    public static Create_userregister create;
    public static List_userregister list;
    public static Read_userregister read;
    public static Update_userregister update;
    public static TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(new miniSimpleTableModel_userregister());
    public static AutocompleteJComboBox_userregister combo = null;
    
    public Controller_userregister (JFrame jframe, int i){
        switch (i){
            case 0:
                list = (List_userregister) jframe;
                break;
            case 1:
                create = (Create_userregister) jframe;
                break;
            case 2:
                update = (Update_userregister) jframe;
                break;
            case 3:
                read = (Read_userregister) jframe;
                break;
        }
    }
    
    public enum action{
        //VISTA LIST_USERREGISTER
        lblajustes,
        lblusuario,
        lblcliente,
        lbladministrador,
        lblinicio,
        lblcreate,
        lblupdate,
        lbldelete,
        TABLA,
        ANTERIOR,
        SIGUIENTE,
        primero,
        ultimo,
        jComboBox2,
        lbljson,
        lblxml,
        lbltxt,
        logout,
        
        //VISTA CREATE_USERREGISTER
        lblajustes_create,
        lblusuario_create,
        lblcliente_create,
        lbladministrador_create,
        lblinicio_create,
        txtDNI_create,
        txtuser_create,
        txtpass_create,
        showpass_create,
        btnavatar_create,
        txtname_create,
        txtsurname_create,
        txtemail_create,
        txtmobilephone_create,
        txtactivity_create,
        txtpoint_create,
        lblvolver_create,
        btnaceptar_create,
        btncancelar_create,
        logout_create,
        
        //VISTA UPDATE_USERREGISTER
        lblajustes_update,
        lblusuario_update,
        lblcliente_update,
        lbladministrador_update,
        lblinicio_update,
        txtDNI_update,
        txtuser_update,
        txtpass_update,
        showpass_update,
        btnavatar_update,
        txtname_update,
        txtsurname_update,
        txtemail_update,
        txtmobilephone_update,
        txtactivity_update,
        txtpoint_update,
        lblvolver_update,
        btnaceptar_update,
        btncancelar_update,
        logout_update,
        
        //VISTA READ_USERREGISTER
        lblajustes_read,
        lblusuario_read,
        lblcliente_read,
        lbladministrador_read,
        lblinicio_read,
        lblvolver_read,
        logout_read
    }
    
    public void iniciar (int i) {
        switch (i){
            case 0:
                list.jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);

                this.list.setVisible(true);
                this.list.setTitle("Framework");
                this.list.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada
                //this.list.setLocationRelativeTo(null);
                //this.list.setSize(525,425);//ancho x alto
                //this.list.setResizable(false);
                //Image icono=Toolkit.getDefaultToolkit().getImage("p1.jpg");
                //this.list.setIconImage(icono);

                logout_list();
                list.TABLA.setModel( new miniSimpleTableModel_userregister() );
                ((miniSimpleTableModel_userregister)list.TABLA.getModel()).cargar();
                list.TABLA.setFillsViewportHeight(true);
                list.TABLA.setRowSorter(sorter);

                pagina_userregister.inicializa();
                pagina_userregister.initLinkBox();
                pagina_userregister.itemsPerPage=Integer.parseInt(list.jComboBox2.getSelectedItem().toString());
                pagina_userregister.currentPageIndex = 1;
                pagina_userregister.initLinkBox();

                list.lblsize.setText(String.valueOf(Singleton_userregister.userregister.size()));

                this.list.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.list.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        mongo.disconnect();
                        json_auto_userregister.savejson_userregister();
                        JOptionPane.showMessageDialog(null,"Saliendo de la aplicación");
                        list.dispose();
                        System.exit(0);
                    }
                });

                List<String> myWords = new ArrayList<String>();
                for(int n=0;n<=Singleton_userregister.userregister.size()-1;n++) {
                    myWords.add(Singleton_userregister.userregister.get(n).getName());
                }

                StringSearchable_userregister searchable = new StringSearchable_userregister(myWords);
                combo = new AutocompleteJComboBox_userregister(searchable);
                //JPanel5 se utiliza solamente para que JPanel3 que contendrá combo, no se redimensione
                list.jPanel3.setLayout(new java.awt.BorderLayout());
                list.jPanel3.add(combo);

                combo.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        System.out.println("word selected: " + ((JComboBox)combo).getSelectedItem());
                        pagina_userregister.currentPageIndex = 1;
                        ((miniSimpleTableModel_userregister)list.TABLA.getModel()).filtrar();
                        combo.requestFocus();
                    }
                });

                this.list.lblajustes.setName("lblajustes");
                this.list.lblajustes.addMouseListener(this);

                this.list.lblusuario.setName("lblusuario");
                this.list.lblusuario.addMouseListener(this);

                this.list.lblcliente.setName("lblcliente");
                this.list.lblcliente.addMouseListener(this);

                this.list.lbladministrador.setName("lbladministrador");
                this.list.lbladministrador.addMouseListener(this);

                this.list.lblinicio.setName("lblinicio");
                this.list.lblinicio.addMouseListener(this);
                
                this.list.lblcreate.setName("lblcreate");
                this.list.lblcreate.addMouseListener(this);
                
                this.list.lblupdate.setName("lblupdate");
                this.list.lblupdate.addMouseListener(this);
                
                this.list.lbldelete.setName("lbldelete");
                this.list.lbldelete.addMouseListener(this);
                
                this.list.TABLA.setName("TABLA");
                this.list.TABLA.addMouseListener(this);
                
                this.list.lbljson.setName("lbljson");
                this.list.lbljson.addMouseListener(this);
                
                this.list.lblxml.setName("lblxml");
                this.list.lblxml.addMouseListener(this);
                
                this.list.lbltxt.setName("lbltxt");
                this.list.lbltxt.addMouseListener(this);
                
                this.list.ANTERIOR.setActionCommand("ANTERIOR");
                this.list.ANTERIOR.addActionListener(this);
                
                this.list.SIGUIENTE.setActionCommand("SIGUIENTE");
                this.list.SIGUIENTE.addActionListener(this);
                
                this.list.primero.setActionCommand("primero");
                this.list.primero.addActionListener(this);
                
                this.list.ultimo.setActionCommand("ultimo");
                this.list.ultimo.addActionListener(this);
                
                this.list.jComboBox2.setActionCommand("jComboBox2");
                this.list.jComboBox2.addActionListener(this);
                
                this.list.logout.setName("logout");
                this.list.logout.addMouseListener(this);
                break;
                
            case 1:
                create.jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);

                this.create.setVisible(true);
                this.create.setTitle("Framework");
                this.create.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada
                //this.create.setLocationRelativeTo(null);
                //this.create.setSize(525,425);//ancho x alto
                //this.create.setResizable(false);
                //Image icono=Toolkit.getDefaultToolkit().getImage("p1.jpg");
                //this.create.setIconImage(icono);

                logout_create();
                this.create.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.create.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        mongo.disconnect();
                        json_auto_userregister.savejson_userregister();
                        JOptionPane.showMessageDialog(null,"Saliendo de la aplicación");
                        create.dispose();
                        System.exit(0);
                    }
                });

                this.create.lblajustes.setName("lblajustes_create");
                this.create.lblajustes.addMouseListener(this);

                this.create.lblusuario.setName("lblusuario_create");
                this.create.lblusuario.addMouseListener(this);

                this.create.lblcliente.setName("lblcliente_create");
                this.create.lblcliente.addMouseListener(this);

                this.create.lbladministrador.setName("lbladministrador_create");
                this.create.lbladministrador.addMouseListener(this);

                this.create.lblinicio.setName("lblinicio_create");
                this.create.lblinicio.addMouseListener(this);
                
                this.create.txtDNI.setName("txtDNI_create");
                this.create.txtDNI.addFocusListener(this);
                this.create.txtDNI.addKeyListener(this);
                this.create.txtDNI.addMouseListener(this);
                
                this.create.txtuser.setName("txtuser_create");
                this.create.txtuser.addFocusListener(this);
                this.create.txtuser.addKeyListener(this);
                this.create.txtuser.addMouseListener(this);
                
                this.create.txtpass.setName("txtpass_create");
                this.create.txtpass.addFocusListener(this);
                this.create.txtpass.addKeyListener(this);
                this.create.txtpass.addMouseListener(this);
                
                this.create.showpass.setName("showpass_create");
                this.create.showpass.addMouseListener(this);
                
                this.create.btnavatar.setActionCommand("btnavatar_create");
                this.create.btnavatar.addActionListener(this);
                
                this.create.txtname.setName("txtname_create");
                this.create.txtname.addFocusListener(this);
                this.create.txtname.addKeyListener(this);
                this.create.txtname.addMouseListener(this);
                
                this.create.txtsurname.setName("txtsurname_create");
                this.create.txtsurname.addFocusListener(this);
                this.create.txtsurname.addKeyListener(this);
                this.create.txtsurname.addMouseListener(this);
                
                this.create.txtemail.setName("txtemail_create");
                this.create.txtemail.addFocusListener(this);
                this.create.txtemail.addKeyListener(this);
                this.create.txtemail.addMouseListener(this);
                
                this.create.txtmobilephone.setName("txtmobilephone_create");
                this.create.txtmobilephone.addFocusListener(this);
                this.create.txtmobilephone.addKeyListener(this);
                this.create.txtmobilephone.addMouseListener(this);
                
                this.create.txtactivity.setName("txtactivity_create");
                this.create.txtactivity.addFocusListener(this);
                this.create.txtactivity.addKeyListener(this);
                this.create.txtactivity.addMouseListener(this);
                
                this.create.txtpoint.setName("txtpoint_create");
                this.create.txtpoint.addFocusListener(this);
                this.create.txtpoint.addKeyListener(this);
                this.create.txtpoint.addMouseListener(this);
                
                this.create.lblvolver.setName("lblvolver_create");
                this.create.lblvolver.addMouseListener(this);
                
                this.create.btnaceptar.setActionCommand("btnaceptar_create");
                this.create.btnaceptar.addActionListener(this);
                
                this.create.btncancelar.setActionCommand("btncancelar_create");
                this.create.btncancelar.addActionListener(this);
                
                this.create.logout.setName("logout_create");
                this.create.logout.addMouseListener(this);
                break;
                
            case 2:
                update.jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);

                DAO_userregister.generate_edit_userregister();
                update.DNI = update.txtDNI.getText();

                this.update.setVisible(true);
                this.update.setTitle("Framework");
                this.update.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada
                //this.update.setLocationRelativeTo(null);
                //this.update.setSize(525,425);//ancho x alto
                //this.update.setResizable(false);
                //Image icono=Toolkit.getDefaultToolkit().getImage("p1.jpg");
                //this.update.setIconImage(icono);

                logout_update();
                this.update.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.update.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        mongo.disconnect();
                        json_auto_userregister.savejson_userregister();
                        JOptionPane.showMessageDialog(null,"Saliendo de la aplicación");
                        update.dispose();
                        System.exit(0);
                    }
                });

                this.update.lblajustes.setName("lblajustes_update");
                this.update.lblajustes.addMouseListener(this);

                this.update.lblusuario.setName("lblusuario_update");
                this.update.lblusuario.addMouseListener(this);

                this.update.lblcliente.setName("lblcliente_update");
                this.update.lblcliente.addMouseListener(this);

                this.update.lbladministrador.setName("lbladministrador_update");
                this.update.lbladministrador.addMouseListener(this);

                this.update.lblinicio.setName("lblinicio_update");
                this.update.lblinicio.addMouseListener(this);
                
                this.update.txtDNI.setName("txtDNI_update");
                this.update.txtDNI.addFocusListener(this);
                this.update.txtDNI.addKeyListener(this);
                this.update.txtDNI.addMouseListener(this);
                
                this.update.txtuser.setName("txtuser_update");
                this.update.txtuser.addFocusListener(this);
                this.update.txtuser.addKeyListener(this);
                this.update.txtuser.addMouseListener(this);
                
                this.update.txtpass.setName("txtpass_update");
                this.update.txtpass.addFocusListener(this);
                this.update.txtpass.addKeyListener(this);
                this.update.txtpass.addMouseListener(this);
                
                this.update.showpass.setName("showpass_update");
                this.update.showpass.addMouseListener(this);
                
                this.update.btnavatar.setActionCommand("btnavatar_update");
                this.update.btnavatar.addActionListener(this);
                
                this.update.txtname.setName("txtname_update");
                this.update.txtname.addFocusListener(this);
                this.update.txtname.addKeyListener(this);
                this.update.txtname.addMouseListener(this);
                
                this.update.txtsurname.setName("txtsurname_update");
                this.update.txtsurname.addFocusListener(this);
                this.update.txtsurname.addKeyListener(this);
                this.update.txtsurname.addMouseListener(this);
                
                this.update.txtemail.setName("txtemail_update");
                this.update.txtemail.addFocusListener(this);
                this.update.txtemail.addKeyListener(this);
                this.update.txtemail.addMouseListener(this);
                
                this.update.txtmobilephone.setName("txtmobilephone_update");
                this.update.txtmobilephone.addFocusListener(this);
                this.update.txtmobilephone.addKeyListener(this);
                this.update.txtmobilephone.addMouseListener(this);
                
                this.update.txtactivity.setName("txtactivity_update");
                this.update.txtactivity.addFocusListener(this);
                this.update.txtactivity.addKeyListener(this);
                this.update.txtactivity.addMouseListener(this);
                
                this.update.txtpoint.setName("txtpoint_update");
                this.update.txtpoint.addFocusListener(this);
                this.update.txtpoint.addKeyListener(this);
                this.update.txtpoint.addMouseListener(this);
                
                this.update.lblvolver.setName("lblvolver_update");
                this.update.lblvolver.addMouseListener(this);
                
                this.update.btnaceptar.setActionCommand("btnaceptar_update");
                this.update.btnaceptar.addActionListener(this);
                
                this.update.btncancelar.setActionCommand("btncancelar_update");
                this.update.btncancelar.addActionListener(this);
                
                this.update.logout.setName("logout_update");
                this.update.logout.addMouseListener(this);
                break;
                
            case 3:
                read.jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);

                this.read.setVisible(true);
                this.read.setTitle("Framework");
                this.read.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada
                //this.read.setLocationRelativeTo(null);
                //this.read.setSize(525,425);//ancho x alto
                //this.read.setResizable(false);
                //Image icono=Toolkit.getDefaultToolkit().getImage("p1.jpg");
                //this.read.setIconImage(icono);

                logout_read();
                DAO_userregister.generate_read_client();

                this.read.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.read.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        mongo.disconnect();
                        json_auto_userregister.savejson_userregister();
                        JOptionPane.showMessageDialog(null,"Saliendo de la aplicación");
                        read.dispose();
                        System.exit(0);
                    }
                });

                this.read.lblajustes.setName("lblajustes_read");
                this.read.lblajustes.addMouseListener(this);

                this.read.lblusuario.setName("lblusuario_read");
                this.read.lblusuario.addMouseListener(this);

                this.read.lblcliente.setName("lblcliente_read");
                this.read.lblcliente.addMouseListener(this);

                this.read.lbladministrador.setName("lbladministrador_read");
                this.read.lbladministrador.addMouseListener(this);

                this.read.lblinicio.setName("lblinicio_read");
                this.read.lblinicio.addMouseListener(this);
                
                this.read.lblvolver.setName("lblvolver_read");
                this.read.lblvolver.addMouseListener(this);
                
                this.read.logout.setName("logout_read");
                this.read.logout.addMouseListener(this);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(Controller_userregister.action.valueOf(e.getComponent().getName())){
            case lblajustes:
                list.dispose();
                new Controller_menu_config(new Config(), 1).iniciar(1);
                break;
            case lblusuario:
                Function_login.login_userregister(list);
                break;
            case lblcliente:
                Function_login.login_client(list);
                break;
            case lbladministrador:
                Function_login.login_admin(list);
                break;
            case lblinicio:
                list.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case lblcreate:
                list.dispose();
                new Controller_userregister(new Create_userregister(), 1).iniciar(1);
                break;
            case lblupdate:
                int select = -1;
                select = list.TABLA.getSelectedRow();
                if (select == -1){
                    JOptionPane.showMessageDialog(null, "Administrador no seleccionado");
                }else{
                    Singleton_login.tabla = true;
                    list.dispose();
                    new Controller_userregister(new Update_userregister(), 2).iniciar(2);
                }
                break;
            case lbldelete:
                BLL_userregister.delete_userregister();
                break;
            case TABLA:
                if (e.getClickCount() == 2) {
                    Singleton_login.tabla = true;
                    list.dispose();
                    new Controller_userregister(new Read_userregister(), 3).iniciar(3);
                }
                break;
            case lbljson:
                BLL_userregister.save_userregister_json();
                break;
            case lblxml:
                BLL_userregister.save_userregister_xml();
                break;
            case lbltxt:
                BLL_userregister.save_userregister_txt();
                break;
            case logout:
                Function_logout.logout(list);
                break;
            case lblajustes_create:
                create.dispose();
                new Controller_menu_config(new Config(), 1).iniciar(1);
                break;
            case lblusuario_create:
                Function_login.login_userregister(create);
                break;
            case lblcliente_create:
                Function_login.login_client(create);
                break;
            case lbladministrador_create:
                Function_login.login_admin(create);
                break;
            case lblinicio_create:
                create.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case lblvolver_create:
                create.dispose();
                if (Singleton_login.admin==true){
                    new Controller_userregister(new List_userregister(), 0).iniciar(0);
                }else{
                    new Controller_menu_config(new Menu(), 0).iniciar(0);
                }
                break;
            case txtDNI_create:
                create.txtDNI.selectAll();
                break;
            case txtuser_create:
                create.txtuser.selectAll();
                break;
            case txtpass_create:
                create.txtpass.selectAll();
                break;
            case txtname_create:
                create.txtname.selectAll();
                break;
            case txtsurname_create:
                create.txtsurname.selectAll();
                break;
            case txtemail_create:
                create.txtemail.selectAll();
                break;
            case txtmobilephone_create:
                create.txtmobilephone.selectAll();
                break;
            case txtpoint_create:
                create.txtpoint.selectAll();
                break;
            case txtactivity_create:
                create.txtactivity.selectAll();
                break;
            case logout_create:
                Function_logout.logout(create);
                break;
            case lblajustes_update:
                update.dispose();
                new Controller_menu_config(new Config(), 1).iniciar(1);
                break;
            case lblusuario_update:
                Function_login.login_userregister(update);
                break;
            case lblcliente_update:
                Function_login.login_client(update);
                break;
            case lbladministrador_update:
                Function_login.login_admin(update);
                break;
            case lblinicio_update:
                update.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case lblvolver_update:
                update.dispose();
                if (Singleton_login.admin==true){
                    new Controller_userregister(new List_userregister(), 0).iniciar(0);
                }else{
                    new Controller_menu_config(new Menu(), 0).iniciar(0);
                }
                break;
            case txtDNI_update:
                update.txtDNI.selectAll();
                break;
            case txtuser_update:
                update.txtuser.selectAll();
                break;
            case txtpass_update:
                update.txtpass.selectAll();
                break;
            case txtname_update:
                update.txtname.selectAll();
                break;
            case txtsurname_update:
                update.txtsurname.selectAll();
                break;
            case txtemail_update:
                update.txtemail.selectAll();
                break;
            case txtmobilephone_update:
                update.txtmobilephone.selectAll();
                break;
            case txtpoint_update:
                update.txtpoint.selectAll();
                break;
            case txtactivity_update:
                update.txtactivity.selectAll();
                break;
            case logout_update:
                Function_logout.logout(update);
                break;
            case lblajustes_read:
                read.dispose();
                new Controller_menu_config(new Config(), 1).iniciar(1);
                break;
            case lblusuario_read:
                Function_login.login_userregister(read);
                break;
            case lblcliente_read:
                Function_login.login_client(read);
                break;
            case lbladministrador_read:
                Function_login.login_admin(read);
                break;
            case lblinicio_read:
                read.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case lblvolver_read:
                read.dispose();
                if (Singleton_login.admin==true){
                    new Controller_userregister(new List_userregister(), 0).iniciar(0);
                }else{
                    new Controller_menu_config(new Menu(), 0).iniciar(0);
                }
                break;
            case logout_read:
                Function_logout.logout(read);
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
        switch(Controller_userregister.action.valueOf(e.getComponent().getName())){
            case lblajustes:
                list.lblajustes.setForeground(java.awt.Color.blue);
                break;
            case lblusuario:
                list.lblusuario.setForeground(java.awt.Color.blue);
                break;
            case lblcliente:
                list.lblcliente.setForeground(java.awt.Color.blue);
                break;
            case lbladministrador:
                list.lbladministrador.setForeground(java.awt.Color.blue);
                break;
            case lblinicio:
                list.lblinicio.setForeground(java.awt.Color.blue);
                break;
            case lblcreate:
                list.lblcreate.setIcon(Singleton_userregister.add_bn);
                break;
            case lblupdate:
                list.lblupdate.setIcon(Singleton_userregister.line_bn);
                break;
            case lbldelete:
                list.lbldelete.setIcon(Singleton_userregister.remove_bn);
                break;
            case logout:
                list.logout.setForeground(java.awt.Color.blue);
                break;
            case lblajustes_create:
                create.lblajustes.setForeground(java.awt.Color.blue);
                break;
            case lblusuario_create:
                create.lblusuario.setForeground(java.awt.Color.blue);
                break;
            case lblcliente_create:
                create.lblcliente.setForeground(java.awt.Color.blue);
                break;
            case lbladministrador_create:
                create.lbladministrador.setForeground(java.awt.Color.blue);
                break;
            case lblinicio_create:
                create.lblinicio.setForeground(java.awt.Color.blue);
                break;
            case showpass_create:
                create.txtpass.setEchoChar((char)0);
                break;
            case lblvolver_create:
                create.lblvolver.setIcon(Singleton_userregister.volver);
                break;
            case logout_create:
                create.logout.setForeground(java.awt.Color.blue);
                break;
            case lblajustes_update:
                update.lblajustes.setForeground(java.awt.Color.blue);
                break;
            case lblusuario_update:
                update.lblusuario.setForeground(java.awt.Color.blue);
                break;
            case lblcliente_update:
                update.lblcliente.setForeground(java.awt.Color.blue);
                break;
            case lbladministrador_update:
                update.lbladministrador.setForeground(java.awt.Color.blue);
                break;
            case lblinicio_update:
                update.lblinicio.setForeground(java.awt.Color.blue);
                break;
            case showpass_update:
                update.txtpass.setEchoChar((char)0);
                break;
            case lblvolver_update:
                update.lblvolver.setIcon(Singleton_userregister.volver);
                break;
            case logout_update:
                update.logout.setForeground(java.awt.Color.blue);
                break;
            case lblajustes_read:
                read.lblajustes.setForeground(java.awt.Color.blue);
                break;
            case lblusuario_read:
                read.lblusuario.setForeground(java.awt.Color.blue);
                break;
            case lblcliente_read:
                read.lblcliente.setForeground(java.awt.Color.blue);
                break;
            case lbladministrador_read:
                read.lbladministrador.setForeground(java.awt.Color.blue);
                break;
            case lblinicio_read:
                read.lblinicio.setForeground(java.awt.Color.blue);
                break;
            case lblvolver_read:
                read.lblvolver.setIcon(Singleton_userregister.volver);
                break;
            case logout_read:
                read.logout.setForeground(java.awt.Color.blue);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch(Controller_userregister.action.valueOf(e.getComponent().getName())){
            case lblajustes:
                list.lblajustes.setForeground(java.awt.Color.cyan);
                break;
            case lblusuario:
                list.lblusuario.setForeground(java.awt.Color.cyan);
                break;
            case lblcliente:
                list.lblcliente.setForeground(java.awt.Color.cyan);
                break;
            case lbladministrador:
                list.lbladministrador.setForeground(java.awt.Color.cyan);
                break;
            case lblinicio:
                list.lblinicio.setForeground(java.awt.Color.cyan);
                break;
            case lblcreate:
                list.lblcreate.setIcon(Singleton_userregister.add);
                break;
            case lblupdate:
                list.lblupdate.setIcon(Singleton_userregister.line);
                break;
            case lbldelete:
                list.lbldelete.setIcon(Singleton_userregister.remove);
                break;
            case logout:
                list.logout.setForeground(java.awt.Color.cyan);
                break;
            case lblajustes_create:
                create.lblajustes.setForeground(java.awt.Color.cyan);
                break;
            case lblusuario_create:
                create.lblusuario.setForeground(java.awt.Color.cyan);
                break;
            case lblcliente_create:
                create.lblcliente.setForeground(java.awt.Color.cyan);
                break;
            case lbladministrador_create:
                create.lbladministrador.setForeground(java.awt.Color.cyan);
                break;
            case lblinicio_create:
                create.lblinicio.setForeground(java.awt.Color.cyan);
                break;
            case showpass_create:
                create.txtpass.setEchoChar('*');
                break;
            case lblvolver_create:
                create.lblvolver.setIcon(Singleton_userregister.volver_bn);
                break;
            case logout_create:
                create.logout.setForeground(java.awt.Color.cyan);
                break;
            case lblajustes_update:
                update.lblajustes.setForeground(java.awt.Color.cyan);
                break;
            case lblusuario_update:
                update.lblusuario.setForeground(java.awt.Color.cyan);
                break;
            case lblcliente_update:
                update.lblcliente.setForeground(java.awt.Color.cyan);
                break;
            case lbladministrador_update:
                update.lbladministrador.setForeground(java.awt.Color.cyan);
                break;
            case lblinicio_update:
                update.lblinicio.setForeground(java.awt.Color.cyan);
                break;
            case showpass_update:
                update.txtpass.setEchoChar('*');
                break;
            case lblvolver_update:
                update.lblvolver.setIcon(Singleton_userregister.volver_bn);
                break;
            case logout_update:
                update.logout.setForeground(java.awt.Color.cyan);
                break;
            case lblajustes_read:
                read.lblajustes.setForeground(java.awt.Color.cyan);
                break;
            case lblusuario_read:
                read.lblusuario.setForeground(java.awt.Color.cyan);
                break;
            case lblcliente_read:
                read.lblcliente.setForeground(java.awt.Color.cyan);
                break;
            case lbladministrador_read:
                read.lbladministrador.setForeground(java.awt.Color.cyan);
                break;
            case lblinicio_read:
                read.lblinicio.setForeground(java.awt.Color.cyan);
                break;
            case lblvolver_read:
                read.lblvolver.setIcon(Singleton_userregister.volver_bn);
                break;
            case logout_read:
                read.logout.setForeground(java.awt.Color.cyan);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (action.valueOf(e.getActionCommand())) {
            case ANTERIOR:
                pagina_userregister.currentPageIndex -= 1;
                pagina_userregister.initLinkBox();
                break;
            case SIGUIENTE:
                pagina_userregister.currentPageIndex += 1;
                pagina_userregister.initLinkBox();
                break;
            case primero:
                pagina_userregister.currentPageIndex = 1;
                pagina_userregister.initLinkBox();
                break;
            case ultimo:
                pagina_userregister.currentPageIndex = pagina_userregister.maxPageIndex;
                pagina_userregister.initLinkBox();
                break;
            case jComboBox2:
                pagina_userregister.itemsPerPage=Integer.parseInt(list.jComboBox2.getSelectedItem().toString());
                pagina_userregister.currentPageIndex = 1;
                pagina_userregister.initLinkBox();
                break;
            case btnavatar_create:
                int resp;
                
                resp=create.JFC_avatar.showOpenDialog(create);
                if (resp==create.JFC_avatar.APPROVE_OPTION) {
                       create.txtavatar.setText(create.JFC_avatar.getSelectedFile().toString());
                } else if (resp==create.JFC_avatar.CANCEL_OPTION) {          
                }
                break;
            case btnaceptar_create:
                BLL_userregister.create_userregister();
                if (BLL_userregister.check){
                    create.jPanel5.setBackground(Color.green);
                    create.txtconfirm.setForeground(Color.black);
                    create.txtconfirm.setText(Language_userregister.getInstance().getProperty("usercreate"));
                    create.txtconfirm.requestFocus();
                    BLL_userregister.timer(create);

                }else{
                    create.jPanel5.setBackground(Color.red);
                    create.txtconfirm.setForeground(Color.white);
                    create.txtconfirm.setText(Language_userregister.getInstance().getProperty("usernotcreate"));
                    create.txtconfirm.requestFocus();
                }
                break;
            case btncancelar_create:
                create.dispose();
                if (Singleton_login.admin==true){
                    new Controller_userregister(new List_userregister(), 0).iniciar(0);
                }else{
                    new Controller_menu_config(new Menu(), 0).iniciar(0);
                }
                break;
            case btnavatar_update:
                int respon;
                
                respon=update.JFC_avatar.showOpenDialog(update);
                if (respon==update.JFC_avatar.APPROVE_OPTION) {
                       update.txtavatar.setText(update.JFC_avatar.getSelectedFile().toString());
                } else if (respon==update.JFC_avatar.CANCEL_OPTION) {          
                }
                break;
            case btnaceptar_update:
                BLL_userregister.update_userregister();
                if (BLL_userregister.check){
                    update.jPanel5.setBackground(Color.green);
                    update.txtconfirm.setForeground(Color.black);
                    update.txtconfirm.setText(Language_userregister.getInstance().getProperty("userupdate"));
                    update.txtconfirm.requestFocus();
                    BLL_userregister.timer(update);

                }else{
                    update.jPanel5.setBackground(Color.red);
                    update.txtconfirm.setForeground(Color.white);
                    update.txtconfirm.setText(Language_userregister.getInstance().getProperty("usernotupdate"));
                    update.txtconfirm.requestFocus();
                }
                break;
            case btncancelar_update:
                update.dispose();
                if (Singleton_login.admin==true){
                    new Controller_userregister(new List_userregister(), 0).iniciar(0);
                }else{
                    new Controller_menu_config(new Menu(), 0).iniciar(0);
                }
                break;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        switch(Controller_userregister.action.valueOf(e.getComponent().getName())){
            case txtDNI_create:
                create.txtDNI.selectAll();
                break;
            case txtuser_create:
                create.txtuser.selectAll();
                break;
            case txtpass_create:
                create.txtpass.selectAll();
                break;
            case txtname_create:
                create.txtname.selectAll();
                break;
            case txtsurname_create:
                create.txtsurname.selectAll();
                break;
            case txtemail_create:
                create.txtemail.selectAll();
                break;
            case txtmobilephone_create:
                create.txtmobilephone.selectAll();
                break;
            case txtpoint_create:
                create.txtpoint.selectAll();
                break;
            case txtactivity_create:
                create.txtactivity.selectAll();
                break;
            case txtDNI_update:
                update.txtDNI.selectAll();
                break;
            case txtuser_update:
                update.txtuser.selectAll();
                break;
            case txtpass_update:
                update.txtpass.selectAll();
                break;
            case txtname_update:
                update.txtname.selectAll();
                break;
            case txtsurname_update:
                update.txtsurname.selectAll();
                break;
            case txtemail_update:
                update.txtemail.selectAll();
                break;
            case txtmobilephone_update:
                update.txtmobilephone.selectAll();
                break;
            case txtpoint_update:
                update.txtpoint.selectAll();
                break;
            case txtactivity_update:
                update.txtactivity.selectAll();
                break;
                
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        switch(Controller_userregister.action.valueOf(e.getComponent().getName())){
            case txtDNI_create:
                BLL_userregister.DNI_create();
                break;
            case txtuser_create:
                BLL_userregister.user_create();
                break;
            case txtpass_create:
                BLL_userregister.pass_create();
                break;
            case txtname_create:
                BLL_userregister.name_create();
                break;
            case txtsurname_create:
                BLL_userregister.surname_create();
                break;
            case txtemail_create:
                BLL_userregister.email_create();
                break;
            case txtmobilephone_create:
                BLL_userregister.mobilephone_create();
                break;
            case txtpoint_create:
                BLL_userregister.point_create();
                break;
            case txtactivity_create:
                BLL_userregister.activity_create();
                break;
            case txtDNI_update:
                BLL_userregister.DNI_update();
                break;
            case txtuser_update:
                BLL_userregister.user_update();
                break;
            case txtpass_update:
                BLL_userregister.pass_update();
                break;
            case txtname_update:
                BLL_userregister.name_update();
                break;
            case txtsurname_update:
                BLL_userregister.surname_update();
                break;
            case txtemail_update:
                BLL_userregister.email_update();
                break;
            case txtmobilephone_update:
                BLL_userregister.mobilephone_update();
                break;
            case txtpoint_update:
                BLL_userregister.point_update();
                break;
            case txtactivity_update:
                BLL_userregister.activity_update();
                break;
                
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(Controller_userregister.action.valueOf(e.getComponent().getName())){
            case txtDNI_create:
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtuser.requestFocus();
                }else{
                    BLL_userregister.DNI_create();
                }
                break;
            case txtuser_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtpass.requestFocus();
                }else{
                    BLL_userregister.user_create();
                }
                break;
            case txtpass_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtname.requestFocus();
                }else{
                    BLL_userregister.pass_create();
                }
                break;
            case txtname_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtsurname.requestFocus();
                }else{
                    BLL_userregister.name_create();
                }
                break;
            case txtsurname_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtemail.requestFocus();
                }else{
                    BLL_userregister.surname_create();
                }
                break;
            case txtemail_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtmobilephone.requestFocus();
                }else{
                    BLL_userregister.email_create();
                }
                break;
            case txtmobilephone_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtactivity.requestFocus();
                }else{
                    BLL_userregister.mobilephone_create();
                }
                break;
            case txtactivity_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtpoint.requestFocus();
                }else{
                    BLL_userregister.activity_create();
                }
                break;
            case txtpoint_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.btnaceptar.requestFocus();
                }else{
                    BLL_userregister.point_create();
                }
                break;
            case txtDNI_update:
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtuser.requestFocus();
                }else{
                    BLL_userregister.DNI_update();
                }
                break;
            case txtuser_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtpass.requestFocus();
                }else{
                    BLL_userregister.user_update();
                }
                break;
            case txtpass_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtname.requestFocus();
                }else{
                    BLL_userregister.pass_update();
                }
                break;
            case txtname_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtsurname.requestFocus();
                }else{
                    BLL_userregister.name_update();
                }
                break;
            case txtsurname_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtemail.requestFocus();
                }else{
                    BLL_userregister.surname_update();
                }
                break;
            case txtemail_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtmobilephone.requestFocus();
                }else{
                    BLL_userregister.email_update();
                }
                break;
            case txtmobilephone_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtactivity.requestFocus();
                }else{
                    BLL_userregister.mobilephone_update();
                }
                break;
            case txtactivity_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtpoint.requestFocus();
                }else{
                    BLL_userregister.activity_update();
                }
                break;
            case txtpoint_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.btnaceptar.requestFocus();
                }else{
                    BLL_userregister.point_update();
                }
                break;
                
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(Controller_userregister.action.valueOf(e.getComponent().getName())){
            case txtDNI_create:
                BLL_userregister.DNI_create();
                break;
            case txtuser_create:
                BLL_userregister.user_create();
                break;
            case txtpass_create:
                BLL_userregister.pass_create();
                break;
            case txtname_create:
                BLL_userregister.name_create();
                break;
            case txtsurname_create:
                BLL_userregister.surname_create();
                break;
            case txtemail_create:
                BLL_userregister.email_create();
                break;
            case txtmobilephone_create:
                BLL_userregister.mobilephone_create();
                break;
            case txtpoint_create:
                BLL_userregister.point_create();
                break;
            case txtactivity_create:
                BLL_userregister.activity_create();
                break;
            case txtDNI_update:
                BLL_userregister.DNI_update();
                break;
            case txtuser_update:
                BLL_userregister.user_update();
                break;
            case txtpass_update:
                BLL_userregister.pass_update();
                break;
            case txtname_update:
                BLL_userregister.name_update();
                break;
            case txtsurname_update:
                BLL_userregister.surname_update();
                break;
            case txtemail_update:
                BLL_userregister.email_update();
                break;
            case txtmobilephone_update:
                BLL_userregister.mobilephone_update();
                break;
            case txtpoint_update:
                BLL_userregister.point_update();
                break;
            case txtactivity_update:
                BLL_userregister.activity_update();
                break;
                
        }
            
    }
    
    public static void logout_list () {
        if (Singleton_login.login==false) {
            list.lbladministrador.setEnabled(true);
            list.lblcliente.setEnabled(true);
            list.lblusuario.setEnabled(true);
            list.user_name.setVisible(false);
            list.logout.setVisible(false);
        }else{
            list.user_name.setVisible(true);
            list.user_name.setText(Singleton_login.name+" "+Singleton_login.surname);
            list.logout.setVisible(true);
            if (Singleton_login.tipo=='a'){
                list.lbladministrador.setEnabled(true);
                list.lblcliente.setEnabled(true);
                list.lblusuario.setEnabled(true);
            }
            if (Singleton_login.tipo=='c'){
                list.lbladministrador.setEnabled(false);
                list.lblcliente.setEnabled(true);
                list.lblusuario.setEnabled(false);
            }
            if (Singleton_login.tipo=='u'){
                list.lbladministrador.setEnabled(false);
                list.lblcliente.setEnabled(false);
                list.lblusuario.setEnabled(true);
            }
        }
    }
    
    public static void logout_create () {
        if (Singleton_login.login==false) {
            create.lbladministrador.setEnabled(true);
            create.lblcliente.setEnabled(true);
            create.lblusuario.setEnabled(true);
            create.user_name.setVisible(false);
            create.logout.setVisible(false);
        }else{
            create.user_name.setVisible(true);
            create.user_name.setText(Singleton_login.name+" "+Singleton_login.surname);
            create.logout.setVisible(true);
            if (Singleton_login.tipo=='a'){
                create.lbladministrador.setEnabled(true);
                create.lblcliente.setEnabled(true);
                create.lblusuario.setEnabled(true);
            }
            if (Singleton_login.tipo=='c'){
                create.lbladministrador.setEnabled(false);
                create.lblcliente.setEnabled(true);
                create.lblusuario.setEnabled(false);
            }
            if (Singleton_login.tipo=='u'){
                create.lbladministrador.setEnabled(false);
                create.lblcliente.setEnabled(false);
                create.lblusuario.setEnabled(true);
            }
        }
    }
    
    public static void logout_update () {
        if (Singleton_login.login==false) {
            update.lbladministrador.setEnabled(true);
            update.lblcliente.setEnabled(true);
            update.lblusuario.setEnabled(true);
            update.user_name.setVisible(false);
            update.logout.setVisible(false);
        }else{
            update.user_name.setVisible(true);
            update.user_name.setText(Singleton_login.name+" "+Singleton_login.surname);
            update.logout.setVisible(true);
            if (Singleton_login.tipo=='a'){
                update.lbladministrador.setEnabled(true);
                update.lblcliente.setEnabled(true);
                update.lblusuario.setEnabled(true);
            }
            if (Singleton_login.tipo=='c'){
                update.lbladministrador.setEnabled(false);
                update.lblcliente.setEnabled(true);
                update.lblusuario.setEnabled(false);
            }
            if (Singleton_login.tipo=='u'){
                update.lbladministrador.setEnabled(false);
                update.lblcliente.setEnabled(false);
                update.lblusuario.setEnabled(true);
            }
        }
    }
    
    public static void logout_read () {
        if (Singleton_login.login==false) {
            read.lbladministrador.setEnabled(true);
            read.lblcliente.setEnabled(true);
            read.lblusuario.setEnabled(true);
            read.user_name.setVisible(false);
            read.logout.setVisible(false);
        }else{
            read.user_name.setVisible(true);
            read.user_name.setText(Singleton_login.name+" "+Singleton_login.surname);
            read.logout.setVisible(true);
            if (Singleton_login.tipo=='a'){
                read.lbladministrador.setEnabled(true);
                read.lblcliente.setEnabled(true);
                read.lblusuario.setEnabled(true);
            }
            if (Singleton_login.tipo=='c'){
                read.lbladministrador.setEnabled(false);
                read.lblcliente.setEnabled(true);
                read.lblusuario.setEnabled(false);
            }
            if (Singleton_login.tipo=='u'){
                read.lbladministrador.setEnabled(false);
                read.lblcliente.setEnabled(false);
                read.lblusuario.setEnabled(true);
            }
        }
    }
}
