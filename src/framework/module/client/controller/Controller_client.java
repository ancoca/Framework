/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.client.controller;

import framework.classes.Mongo_BD;
import framework.module.admin.controller.Controller_admin;
import framework.module.admin.view.List_admin;
import framework.module.client.model.BLL.BLL_client.BLL_client;
import framework.module.client.model.DAO.DAO_client;
import framework.module.client.model.classes.Singleton_client;
import framework.module.client.model.classes.language.Language_client;
import framework.module.client.model.classes.miniSimpleTableModel_client;
import framework.module.client.model.functions.autocomplete.AutocompleteJComboBox_client;
import framework.module.client.model.functions.autocomplete.StringSearchable_client;
import framework.module.client.model.functions.pagina_client;
import framework.module.client.view.Create_client;
import framework.module.client.view.List_client;
import framework.module.client.view.Read_client;
import framework.module.client.view.Update_client;
import framework.module.menu_config.view.Config;
import framework.module.menu_config.controller.Controller_menu_config;
import framework.module.menu_config.view.Menu;
import framework.module.userregister.controller.Controller_userregister;
import framework.module.userregister.model.functions.json_auto_userregister;
import framework.module.userregister.view.List_userregister;
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
public class Controller_client implements MouseListener, ActionListener, FocusListener, KeyListener{
    
    public static Create_client create;
    public static List_client list;
    public static Read_client read;
    public static Update_client update;
    public static TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(new miniSimpleTableModel_client());
    public static AutocompleteJComboBox_client combo = null;
    
    public Controller_client (JFrame jframe, int i){
        switch (i){
            case 0:
                list = (List_client) jframe;
                break;
            case 1:
                create = (Create_client) jframe;
                break;
            case 2:
                update = (Update_client) jframe;
                break;
            case 3:
                read = (Read_client) jframe;
                break;
        }
    }
    
    public enum action{
        //VISTA LIST_CLIENT
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
        
        //VISTA CREATE_CLIENT
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
        txtshopping_create,
        txtdtos_create,
        txttype_client_create,
        lblvolver_create,
        btnaceptar_create,
        btncancelar_create,
        
        //VISTA UPDATE_CLIENT
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
        txtshopping_update,
        txtdtos_update,
        txttype_client_update,
        lblvolver_update,
        btnaceptar_update,
        btncancelar_update,
        
        //VISTA READ_CLIENT
        lblajustes_read,
        lblusuario_read,
        lblcliente_read,
        lbladministrador_read,
        lblinicio_read,
        lblvolver_read
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

                list.TABLA.setModel( new miniSimpleTableModel_client() );
                ((miniSimpleTableModel_client)list.TABLA.getModel()).cargar();
                list.TABLA.setFillsViewportHeight(true);
                list.TABLA.setRowSorter(sorter);

                pagina_client.inicializa();
                pagina_client.initLinkBox();
                pagina_client.itemsPerPage=Integer.parseInt(list.jComboBox2.getSelectedItem().toString());
                pagina_client.currentPageIndex = 1;
                pagina_client.initLinkBox();

                list.lblsize.setText(String.valueOf(Singleton_client.userclient.size()));

                this.list.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.list.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        Mongo_BD.disconnect();
                        json_auto_userregister.savejson_userregister();
                        JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("exit"));
                        list.dispose();
                        System.exit(0);
                    }
                });

                List<String> myWords = new ArrayList<String>();
                for(int n=0;n<=Singleton_client.userclient.size()-1;n++) {
                    myWords.add(Singleton_client.userclient.get(n).getName());
                }

                StringSearchable_client searchable = new StringSearchable_client(myWords);
                combo = new AutocompleteJComboBox_client(searchable);
                //JPanel5 se utiliza solamente para que JPanel3 que contendrá combo, no se redimensione
                list.jPanel3.setLayout(new java.awt.BorderLayout());
                list.jPanel3.add(combo);

                combo.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        System.out.println("word selected: " + ((JComboBox)combo).getSelectedItem());
                        pagina_client.currentPageIndex = 1;
                        ((miniSimpleTableModel_client)list.TABLA.getModel()).filtrar();
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

                this.create.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.create.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        Mongo_BD.disconnect();
                        json_auto_userregister.savejson_userregister();
                        JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("exit"));
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
                
                this.create.txtshopping.setName("txtshopping_create");
                this.create.txtshopping.addFocusListener(this);
                this.create.txtshopping.addKeyListener(this);
                this.create.txtshopping.addMouseListener(this);
                
                this.create.txtdtos.setName("txtdtos_create");
                this.create.txtdtos.addFocusListener(this);
                this.create.txtdtos.addKeyListener(this);
                this.create.txtdtos.addMouseListener(this);
                
                this.create.txttype_client.setName("txttype_client_create");
                this.create.txttype_client.addFocusListener(this);
                this.create.txttype_client.addKeyListener(this);
                this.create.txttype_client.addMouseListener(this);
                
                this.create.lblvolver.setName("lblvolver_create");
                this.create.lblvolver.addMouseListener(this);
                
                this.create.btnaceptar.setActionCommand("btnaceptar_create");
                this.create.btnaceptar.addActionListener(this);
                
                this.create.btncancelar.setActionCommand("btncancelar_create");
                this.create.btncancelar.addActionListener(this);
                break;
                
            case 2:
                update.jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);

                this.update.setVisible(true);
                
                DAO_client.generate_edit_client();
                update.DNI = update.txtDNI.getText();

                this.update.setTitle("Framework");
                this.update.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada
                //this.update.setLocationRelativeTo(null);
                //this.update.setSize(525,425);//ancho x alto
                //this.update.setResizable(false);
                //Image icono=Toolkit.getDefaultToolkit().getImage("p1.jpg");
                //this.update.setIconImage(icono);

                this.update.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.update.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        Mongo_BD.disconnect();
                        json_auto_userregister.savejson_userregister();
                        JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("exit"));
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
                
                this.update.txtshopping.setName("txtshopping_update");
                this.update.txtshopping.addFocusListener(this);
                this.update.txtshopping.addKeyListener(this);
                this.update.txtshopping.addMouseListener(this);
                
                this.update.txtdtos.setName("txtdtos_update");
                this.update.txtdtos.addFocusListener(this);
                this.update.txtdtos.addKeyListener(this);
                this.update.txtdtos.addMouseListener(this);
                
                this.update.txttype_client.setName("txttype_client_update");
                this.update.txttype_client.addFocusListener(this);
                this.update.txttype_client.addKeyListener(this);
                this.update.txttype_client.addMouseListener(this);
                
                this.update.lblvolver.setName("lblvolver_update");
                this.update.lblvolver.addMouseListener(this);
                
                this.update.btnaceptar.setActionCommand("btnaceptar_update");
                this.update.btnaceptar.addActionListener(this);
                
                this.update.btncancelar.setActionCommand("btncancelar_update");
                this.update.btncancelar.addActionListener(this);
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

                DAO_client.generate_read_client();

                this.read.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.read.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        Mongo_BD.disconnect();
                        json_auto_userregister.savejson_userregister();
                        JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("exit"));
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
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(Controller_client.action.valueOf(e.getComponent().getName())){
            case lblajustes:
                list.dispose();
                new Controller_menu_config(new Config(), 1).iniciar(1);
                break;
            case lblusuario:
                list.dispose();
                new Controller_userregister(new List_userregister(), 0).iniciar(0);
                break;
            case lblcliente:
                list.dispose();
                new Controller_client(new List_client(), 0).iniciar(0);
                break;
            case lbladministrador:
                list.dispose();
                new Controller_admin(new List_admin(), 0).iniciar(0);
                break;
            case lblinicio:
                list.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case lblcreate:
                list.dispose();
                new Controller_client(new Create_client(), 1).iniciar(1);
                break;
            case lblupdate:
                int select = -1;
                select = list.TABLA.getSelectedRow();
                if (select == -1){
                    JOptionPane.showMessageDialog(null, Language_client.getInstance().getProperty("usernotselect"));
                }else{
                    list.dispose();
                    new Controller_client(new Update_client(), 2).iniciar(2);
                }
                break;
            case lbldelete:
                BLL_client.delete_client();
                break;
            case TABLA:
                if (e.getClickCount() == 2) {
                    list.dispose();
                    new Controller_client(new Read_client(), 3).iniciar(3);
                }
                break;
            case lbljson:
                BLL_client.save_client_json();
                break;
            case lblxml:
                BLL_client.save_client_xml();
                break;
            case lbltxt:
                BLL_client.save_client_txt();
                break;
            case lblajustes_create:
                create.dispose();
                new Controller_menu_config(new Config(), 1).iniciar(1);
                break;
            case lblusuario_create:
                create.dispose();
                new Controller_userregister(new List_userregister(), 0).iniciar(0);
                break;
            case lblcliente_create:
                create.dispose();
                new Controller_client(new List_client(), 0).iniciar(0);
                break;
            case lbladministrador_create:
                create.dispose();
                new Controller_admin(new List_admin(), 0).iniciar(0);
                break;
            case lblinicio_create:
                create.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case lblvolver_create:
                create.dispose();
                new Controller_client(new List_client(), 0).iniciar(0);
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
            case txtshopping_create:
                create.txtshopping.selectAll();
                break;
            case txtdtos_create:
                create.txtdtos.selectAll();
                break;
            case txttype_client_create:
                create.txttype_client.selectAll();
                break;
            case lblajustes_update:
                update.dispose();
                new Controller_menu_config(new Config(), 1).iniciar(1);
                break;
            case lblusuario_update:
                update.dispose();
                new Controller_userregister(new List_userregister(), 0).iniciar(0);
                break;
            case lblcliente_update:
                update.dispose();
                new Controller_client(new List_client(), 0).iniciar(0);
                break;
            case lbladministrador_update:
                update.dispose();
                new Controller_admin(new List_admin(), 0).iniciar(0);
                break;
            case lblinicio_update:
                update.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case lblvolver_update:
                update.dispose();
                new Controller_client(new List_client(), 0).iniciar(0);
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
            case txtshopping_update:
                update.txtshopping.selectAll();
                break;
            case txtdtos_update:
                update.txtdtos.selectAll();
                break;
            case txttype_client_update:
                update.txttype_client.selectAll();
                break;
            case lblajustes_read:
                read.dispose();
                new Controller_menu_config(new Config(), 1).iniciar(1);
                break;
            case lblusuario_read:
                read.dispose();
                new Controller_userregister(new List_userregister(), 0).iniciar(0);
                break;
            case lblcliente_read:
                read.dispose();
                new Controller_client(new List_client(), 0).iniciar(0);
                break;
            case lbladministrador_read:
                read.dispose();
                new Controller_admin(new List_admin(), 0).iniciar(0);
                break;
            case lblinicio_read:
                read.dispose();
                new Controller_menu_config(new Menu(), 0).iniciar(0);
                break;
            case lblvolver_read:
                read.dispose();
                new Controller_client(new List_client(), 0).iniciar(0);
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
        switch(Controller_client.action.valueOf(e.getComponent().getName())){
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
                list.lblcreate.setIcon(Singleton_client.add_bn);
                break;
            case lblupdate:
                list.lblupdate.setIcon(Singleton_client.line_bn);
                break;
            case lbldelete:
                list.lbldelete.setIcon(Singleton_client.remove_bn);
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
                create.lblvolver.setIcon(Singleton_client.volver);
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
                update.lblvolver.setIcon(Singleton_client.volver);
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
                read.lblvolver.setIcon(Singleton_client.volver);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch(Controller_client.action.valueOf(e.getComponent().getName())){
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
                list.lblcreate.setIcon(Singleton_client.add);
                break;
            case lblupdate:
                list.lblupdate.setIcon(Singleton_client.line);
                break;
            case lbldelete:
                list.lbldelete.setIcon(Singleton_client.remove);
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
                create.lblvolver.setIcon(Singleton_client.volver_bn);
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
                update.lblvolver.setIcon(Singleton_client.volver_bn);
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
                read.lblvolver.setIcon(Singleton_client.volver_bn);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (action.valueOf(e.getActionCommand())) {
            case ANTERIOR:
                pagina_client.currentPageIndex -= 1;
                pagina_client.initLinkBox();
                break;
            case SIGUIENTE:
                pagina_client.currentPageIndex += 1;
                pagina_client.initLinkBox();
                break;
            case primero:
                pagina_client.currentPageIndex = 1;
                pagina_client.initLinkBox();
                break;
            case ultimo:
                pagina_client.currentPageIndex = pagina_client.maxPageIndex;
                pagina_client.initLinkBox();
                break;
            case jComboBox2:
                pagina_client.itemsPerPage=Integer.parseInt(list.jComboBox2.getSelectedItem().toString());
                pagina_client.currentPageIndex = 1;
                pagina_client.initLinkBox();
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
                BLL_client.create_client();
                if (BLL_client.check){
                    create.jPanel5.setBackground(Color.green);
                    create.txtconfirm.setForeground(Color.black);
                    create.txtconfirm.setText(Language_client.getInstance().getProperty("usercreate"));
                    BLL_client.timer(create);

                }else{
                    create.jPanel5.setBackground(Color.red);
                    create.txtconfirm.setForeground(Color.white);
                    create.txtconfirm.setText(Language_client.getInstance().getProperty("usernotcreate"));
                    create.txtconfirm.requestFocus();
                }
                break;
            case btncancelar_create:
                create.dispose();
                new Controller_client(new List_client(), 0).iniciar(0);
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
                BLL_client.update_client();
                if (BLL_client.check){
                    update.jPanel5.setBackground(Color.green);
                    update.txtconfirm.setForeground(Color.black);
                    update.txtconfirm.setText(Language_client.getInstance().getProperty("userupdate"));
                    BLL_client.timer(update);

                }else{
                    update.jPanel5.setBackground(Color.red);
                    update.txtconfirm.setForeground(Color.white);
                    update.txtconfirm.setText(Language_client.getInstance().getProperty("usernotupdate"));
                    update.txtconfirm.requestFocus();
                }
                break;
            case btncancelar_update:
                update.dispose();
                new Controller_client(new List_client(), 0).iniciar(0);
                break;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        switch(Controller_client.action.valueOf(e.getComponent().getName())){
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
            case txtshopping_create:
                create.txtshopping.selectAll();
                break;
            case txtdtos_create:
                create.txtdtos.selectAll();
                break;
            case txttype_client_create:
                create.txttype_client.selectAll();
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
            case txtshopping_update:
                update.txtshopping.selectAll();
                break;
            case txtdtos_update:
                update.txtdtos.selectAll();
                break;
            case txttype_client_update:
                update.txttype_client.selectAll();
                break;
                
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        switch(Controller_client.action.valueOf(e.getComponent().getName())){
            case txtDNI_create:
                BLL_client.DNI_create();
                break;
            case txtuser_create:
                BLL_client.user_create();
                break;
            case txtpass_create:
                BLL_client.pass_create();
                break;
            case txtname_create:
                BLL_client.name_create();
                break;
            case txtsurname_create:
                BLL_client.surname_create();
                break;
            case txtemail_create:
                BLL_client.email_create();
                break;
            case txtmobilephone_create:
                BLL_client.mobilephone_create();
                break;
            case txtshopping_create:
                BLL_client.shopping_create();
                break;
            case txtdtos_create:
                BLL_client.dtos_create();
                break;
            case txttype_client_create:
                BLL_client.typeclient_create();
                break;
            case txtDNI_update:
                BLL_client.DNI_update();
                break;
            case txtuser_update:
                BLL_client.user_update();
                break;
            case txtpass_update:
                BLL_client.pass_update();
                break;
            case txtname_update:
                BLL_client.name_update();
                break;
            case txtsurname_update:
                BLL_client.surname_update();
                break;
            case txtemail_update:
                BLL_client.email_update();
                break;
            case txtmobilephone_update:
                BLL_client.mobilephone_update();
                break;
            case txtshopping_update:
                BLL_client.shopping_update();
                break;
            case txtdtos_update:
                BLL_client.dtos_update();
                break;
            case txttype_client_update:
                BLL_client.typeclient_update();
                break;
                
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(Controller_client.action.valueOf(e.getComponent().getName())){
            case txtDNI_create:
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtuser.requestFocus();
                }else{
                    BLL_client.DNI_create();
                }
                break;
            case txtuser_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtpass.requestFocus();
                }else{
                    BLL_client.user_create();
                }
                break;
            case txtpass_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtname.requestFocus();
                }else{
                    BLL_client.pass_create();
                }
                break;
            case txtname_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtsurname.requestFocus();
                }else{
                    BLL_client.name_create();
                }
                break;
            case txtsurname_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtemail.requestFocus();
                }else{
                    BLL_client.surname_create();
                }
                break;
            case txtemail_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtmobilephone.requestFocus();
                }else{
                    BLL_client.email_create();
                }
                break;
            case txtmobilephone_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtshopping.requestFocus();
                }else{
                    BLL_client.mobilephone_create();
                }
                break;
            case txtshopping_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtdtos.requestFocus();
                }else{
                    BLL_client.shopping_create();
                }
                break;
            case txtdtos_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txttype_client.requestFocus();
                }else{
                    BLL_client.dtos_create();
                }
                break;
            case txttype_client_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.btnaceptar.requestFocus();
                }else{
                    BLL_client.typeclient_create();
                }
                break;
            case txtDNI_update:
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtuser.requestFocus();
                }else{
                    BLL_client.DNI_update();
                }
                break;
            case txtuser_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtpass.requestFocus();
                }else{
                    BLL_client.user_update();
                }
                break;
            case txtpass_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtname.requestFocus();
                }else{
                    BLL_client.pass_update();
                }
                break;
            case txtname_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtsurname.requestFocus();
                }else{
                    BLL_client.name_update();
                }
                break;
            case txtsurname_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtemail.requestFocus();
                }else{
                    BLL_client.surname_update();
                }
                break;
            case txtemail_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtmobilephone.requestFocus();
                }else{
                    BLL_client.email_update();
                }
                break;
            case txtmobilephone_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtshopping.requestFocus();
                }else{
                    BLL_client.mobilephone_update();
                }
                break;
            case txtshopping_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtdtos.requestFocus();
                }else{
                    BLL_client.shopping_update();
                }
                break;
            case txtdtos_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txttype_client.requestFocus();
                }else{
                    BLL_client.dtos_update();
                }
                break;
            case txttype_client_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.btnaceptar.requestFocus();
                }else{
                    BLL_client.typeclient_update();
                }
                break;
                
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(Controller_client.action.valueOf(e.getComponent().getName())){
            case txtDNI_create:
                BLL_client.DNI_create();
                break;
            case txtuser_create:
                BLL_client.user_create();
                break;
            case txtpass_create:
                BLL_client.pass_create();
                break;
            case txtname_create:
                BLL_client.name_create();
                break;
            case txtsurname_create:
                BLL_client.surname_create();
                break;
            case txtemail_create:
                BLL_client.email_create();
                break;
            case txtmobilephone_create:
                BLL_client.mobilephone_create();
                break;
            case txtshopping_create:
                BLL_client.shopping_create();
                break;
            case txtdtos_create:
                BLL_client.dtos_create();
                break;
            case txttype_client_create:
                BLL_client.typeclient_create();
                break;
            case txtDNI_update:
                BLL_client.DNI_update();
                break;
            case txtuser_update:
                BLL_client.user_update();
                break;
            case txtpass_update:
                BLL_client.pass_update();
                break;
            case txtname_update:
                BLL_client.name_update();
                break;
            case txtsurname_update:
                BLL_client.surname_update();
                break;
            case txtemail_update:
                BLL_client.email_update();
                break;
            case txtmobilephone_update:
                BLL_client.mobilephone_update();
                break;
            case txtshopping_update:
                BLL_client.shopping_update();
                break;
            case txtdtos_update:
                BLL_client.dtos_update();
                break;
            case txttype_client_update:
                BLL_client.typeclient_update();
                break;
                
        }
            
    }
}
