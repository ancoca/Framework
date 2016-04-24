/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.admin.controller;

import framework.classes.Mongo_BD;
import framework.module.admin.model.BLL.BLL_admin.BLL_admin;
import framework.module.admin.model.DAO.DAO_admin;
import framework.module.admin.model.classes.Singleton_admin;
import framework.module.admin.model.classes.language.Language_admin;
import framework.module.admin.model.classes.miniSimpleTableModel_admin;
import framework.module.admin.model.functions.autocomplete.AutocompleteJComboBox_admin;
import framework.module.admin.model.functions.autocomplete.StringSearchable_admin;
import framework.module.admin.model.functions.pagina_admin;
import framework.module.admin.view.Create_admin;
import framework.module.admin.view.List_admin;
import framework.module.admin.view.Read_admin;
import framework.module.admin.view.Update_admin;
import framework.module.client.controller.Controller_client;
import framework.module.client.view.List_client;
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
public class Controller_admin implements MouseListener, ActionListener, FocusListener, KeyListener{
    
    public static Create_admin create;
    public static List_admin list;
    public static Read_admin read;
    public static Update_admin update;
    public static TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(new miniSimpleTableModel_admin());
    public static AutocompleteJComboBox_admin combo = null;
    
    public Controller_admin (JFrame jframe, int i){
        switch (i){
            case 0:
                list = (List_admin) jframe;
                break;
            case 1:
                create = (Create_admin) jframe;
                break;
            case 2:
                update = (Update_admin) jframe;
                break;
            case 3:
                read = (Read_admin) jframe;
                break;
        }
    }
    
    public enum action{
        //VISTA LIST_ADMIN
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
        
        //VISTA CREATE_ADMIN
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
        txtsalary_create,
        txtincentive_create,
        txtactivity_create,
        lblvolver_create,
        btnaceptar_create,
        btncancelar_create,
        
        //VISTA UPDATE_ADMIN
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
        txtsalary_update,
        txtincentive_update,
        txtactivity_update,
        lblvolver_update,
        btnaceptar_update,
        btncancelar_update,
        
        //VISTA READ_ADMIN
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


                list.TABLA.setModel( new miniSimpleTableModel_admin() );
                ((miniSimpleTableModel_admin)list.TABLA.getModel()).cargar();
                list.TABLA.setFillsViewportHeight(true);
                list.TABLA.setRowSorter(sorter);

                pagina_admin.inicializa();
                pagina_admin.initLinkBox();
                pagina_admin.itemsPerPage=Integer.parseInt(list.jComboBox2.getSelectedItem().toString());
                pagina_admin.currentPageIndex = 1;
                pagina_admin.initLinkBox();

                list.lblsize.setText(String.valueOf(Singleton_admin.useradmin.size()));

                this.list.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.list.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        Mongo_BD.disconnect();
                        json_auto_userregister.savejson_userregister();
                        JOptionPane.showMessageDialog(null,Language_admin.getInstance().getProperty("exit"));
                        list.dispose();
                        System.exit(0);
                    }
                });

                List<String> myWords = new ArrayList<String>();
                for(int n=0;n<=Singleton_admin.useradmin.size()-1;n++) {
                    myWords.add(Singleton_admin.useradmin.get(n).getName());
                }

                StringSearchable_admin searchable = new StringSearchable_admin(myWords);
                combo = new AutocompleteJComboBox_admin(searchable);
                //JPanel5 se utiliza solamente para que JPanel3 que contendrá combo, no se redimensione
                list.jPanel3.setLayout(new java.awt.BorderLayout());
                list.jPanel3.add(combo);

                combo.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        System.out.println("word selected: " + ((JComboBox)combo).getSelectedItem());
                        pagina_admin.currentPageIndex = 1;
                        ((miniSimpleTableModel_admin)list.TABLA.getModel()).filtrar();
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
                        JOptionPane.showMessageDialog(null,Language_admin.getInstance().getProperty("exit"));
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
                
                this.create.txtsalary.setName("txtsalary_create");
                this.create.txtsalary.addFocusListener(this);
                this.create.txtsalary.addKeyListener(this);
                this.create.txtsalary.addMouseListener(this);
                
                this.create.txtincentive.setName("txtincentive_create");
                this.create.txtincentive.addFocusListener(this);
                this.create.txtincentive.addKeyListener(this);
                this.create.txtincentive.addMouseListener(this);
                
                this.create.txtactivity.setName("txtactivity_create");
                this.create.txtactivity.addFocusListener(this);
                this.create.txtactivity.addKeyListener(this);
                this.create.txtactivity.addMouseListener(this);
                
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
                
                DAO_admin.generate_edit_admin();
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
                        JOptionPane.showMessageDialog(null,Language_admin.getInstance().getProperty("exit"));
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
                
                this.update.txtsalary.setName("txtsalary_update");
                this.update.txtsalary.addFocusListener(this);
                this.update.txtsalary.addKeyListener(this);
                this.update.txtsalary.addMouseListener(this);
                
                this.update.txtincentive.setName("txtincentive_update");
                this.update.txtincentive.addFocusListener(this);
                this.update.txtincentive.addKeyListener(this);
                this.update.txtincentive.addMouseListener(this);
                
                this.update.txtactivity.setName("txtactivity_update");
                this.update.txtactivity.addFocusListener(this);
                this.update.txtactivity.addKeyListener(this);
                this.update.txtactivity.addMouseListener(this);
                
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

                DAO_admin.generate_read_admin();

                this.read.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.read.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        Mongo_BD.disconnect();
                        json_auto_userregister.savejson_userregister();
                        JOptionPane.showMessageDialog(null,Language_admin.getInstance().getProperty("exit"));
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
        switch(Controller_admin.action.valueOf(e.getComponent().getName())){
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
                new Controller_admin(new Create_admin(), 1).iniciar(1);
                break;
            case lblupdate:
                int select = -1;
                select = list.TABLA.getSelectedRow();
                if (select == -1){
                    JOptionPane.showMessageDialog(null, Language_admin.getInstance().getProperty("usernotselect"));
                }else{
                    list.dispose();
                    new Controller_admin(new Update_admin(), 2).iniciar(2);
                }
                break;
            case lbldelete:
                BLL_admin.delete_admin();
                break;
            case TABLA:
                if (e.getClickCount() == 2) {
                    list.dispose();
                    new Controller_admin(new Read_admin(), 3).iniciar(3);
                }
                break;
            case lbljson:
                BLL_admin.save_admin_json();
                break;
            case lblxml:
                BLL_admin.save_admin_xml();
                break;
            case lbltxt:
                BLL_admin.save_admin_txt();
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
                new Controller_admin(new List_admin(), 0).iniciar(0);
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
            case txtsalary_create:
                create.txtsalary.selectAll();
                break;
            case txtincentive_create:
                create.txtincentive.selectAll();
                break;
            case txtactivity_create:
                create.txtactivity.selectAll();
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
                new Controller_admin(new List_admin(), 0).iniciar(0);
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
            case txtsalary_update:
                update.txtsalary.selectAll();
                break;
            case txtincentive_update:
                update.txtincentive.selectAll();
                break;
            case txtactivity_update:
                update.txtactivity.selectAll();
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
                new Controller_admin(new List_admin(), 0).iniciar(0);
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
        switch(Controller_admin.action.valueOf(e.getComponent().getName())){
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
                list.lblcreate.setIcon(Singleton_admin.add_bn);
                break;
            case lblupdate:
                list.lblupdate.setIcon(Singleton_admin.line_bn);
                break;
            case lbldelete:
                list.lbldelete.setIcon(Singleton_admin.remove_bn);
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
                create.lblvolver.setIcon(Singleton_admin.volver);
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
                update.lblvolver.setIcon(Singleton_admin.volver);
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
                read.lblvolver.setIcon(Singleton_admin.volver);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch(Controller_admin.action.valueOf(e.getComponent().getName())){
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
                list.lblcreate.setIcon(Singleton_admin.add);
                break;
            case lblupdate:
                list.lblupdate.setIcon(Singleton_admin.line);
                break;
            case lbldelete:
                list.lbldelete.setIcon(Singleton_admin.remove);
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
                create.lblvolver.setIcon(Singleton_admin.volver_bn);
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
                update.lblvolver.setIcon(Singleton_admin.volver_bn);
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
                read.lblvolver.setIcon(Singleton_admin.volver_bn);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (action.valueOf(e.getActionCommand())) {
            case ANTERIOR:
                pagina_admin.currentPageIndex -= 1;
                pagina_admin.initLinkBox();
                break;
            case SIGUIENTE:
                pagina_admin.currentPageIndex += 1;
                pagina_admin.initLinkBox();
                break;
            case primero:
                pagina_admin.currentPageIndex = 1;
                pagina_admin.initLinkBox();
                break;
            case ultimo:
                pagina_admin.currentPageIndex = pagina_admin.maxPageIndex;
                pagina_admin.initLinkBox();
                break;
            case jComboBox2:
                pagina_admin.itemsPerPage=Integer.parseInt(list.jComboBox2.getSelectedItem().toString());
                pagina_admin.currentPageIndex = 1;
                pagina_admin.initLinkBox();
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
                BLL_admin.create_admin();
                if (BLL_admin.check){
                    create.jPanel5.setBackground(Color.green);
                    create.txtconfirm.setForeground(Color.black);
                    create.txtconfirm.setText(Language_admin.getInstance().getProperty("usercreate"));
                    BLL_admin.timer(create);

                }else{
                    create.jPanel5.setBackground(Color.red);
                    create.txtconfirm.setForeground(Color.white);
                    create.txtconfirm.setText(Language_admin.getInstance().getProperty("usernotcreate"));
                    create.txtconfirm.requestFocus();
                }
                break;
            case btncancelar_create:
                create.dispose();
                new Controller_admin(new List_admin(), 0).iniciar(0);
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
                BLL_admin.update_admin();
                if (BLL_admin.check){
                    update.jPanel5.setBackground(Color.green);
                    update.txtconfirm.setForeground(Color.black);
                    update.txtconfirm.setText(Language_admin.getInstance().getProperty("userupdate"));
                    BLL_admin.timer(update);

                }else{
                    update.jPanel5.setBackground(Color.red);
                    update.txtconfirm.setForeground(Color.white);
                    update.txtconfirm.setText(Language_admin.getInstance().getProperty("usernotupdate"));
                    update.txtconfirm.requestFocus();
                }
                break;
            case btncancelar_update:
                update.dispose();
                new Controller_admin(new List_admin(), 0).iniciar(0);
                break;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        switch(Controller_admin.action.valueOf(e.getComponent().getName())){
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
            case txtsalary_create:
                create.txtsalary.selectAll();
                break;
            case txtincentive_create:
                create.txtincentive.selectAll();
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
            case txtsalary_update:
                update.txtsalary.selectAll();
                break;
            case txtincentive_update:
                update.txtincentive.selectAll();
                break;
            case txtactivity_update:
                update.txtactivity.selectAll();
                break;
                
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        switch(Controller_admin.action.valueOf(e.getComponent().getName())){
            case txtDNI_create:
                BLL_admin.DNI_create();
                break;
            case txtuser_create:
                BLL_admin.user_create();
                break;
            case txtpass_create:
                BLL_admin.pass_create();
                break;
            case txtname_create:
                BLL_admin.name_create();
                break;
            case txtsurname_create:
                BLL_admin.surname_create();
                break;
            case txtemail_create:
                BLL_admin.email_create();
                break;
            case txtmobilephone_create:
                BLL_admin.mobilephone_create();
                break;
            case txtsalary_create:
                BLL_admin.salary_create();
                break;
            case txtincentive_create:
                BLL_admin.incentive_create();
                break;
            case txtactivity_create:
                BLL_admin.activity_create();
                break;
            case txtDNI_update:
                BLL_admin.DNI_update();
                break;
            case txtuser_update:
                BLL_admin.user_update();
                break;
            case txtpass_update:
                BLL_admin.pass_update();
                break;
            case txtname_update:
                BLL_admin.name_update();
                break;
            case txtsurname_update:
                BLL_admin.surname_update();
                break;
            case txtemail_update:
                BLL_admin.email_update();
                break;
            case txtmobilephone_update:
                BLL_admin.mobilephone_update();
                break;
            case txtsalary_update:
                BLL_admin.salary_update();
                break;
            case txtincentive_update:
                BLL_admin.incentive_update();
                break;
            case txtactivity_update:
                BLL_admin.activity_update();
                break;
                
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(Controller_admin.action.valueOf(e.getComponent().getName())){
            case txtDNI_create:
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtuser.requestFocus();
                }else{
                    BLL_admin.DNI_create();
                }
                break;
            case txtuser_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtpass.requestFocus();
                }else{
                    BLL_admin.user_create();
                }
                break;
            case txtpass_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtname.requestFocus();
                }else{
                    BLL_admin.pass_create();
                }
                break;
            case txtname_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtsurname.requestFocus();
                }else{
                    BLL_admin.name_create();
                }
                break;
            case txtsurname_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtemail.requestFocus();
                }else{
                    BLL_admin.surname_create();
                }
                break;
            case txtemail_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtmobilephone.requestFocus();
                }else{
                    BLL_admin.email_create();
                }
                break;
            case txtmobilephone_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtsalary.requestFocus();
                }else{
                    BLL_admin.mobilephone_create();
                }
                break;
            case txtsalary_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtincentive.requestFocus();
                }else{
                    BLL_admin.salary_create();
                }
                break;
            case txtincentive_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.txtactivity.requestFocus();
                }else{
                    BLL_admin.incentive_create();
                }
                break;
            case txtactivity_create:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    create.btnaceptar.requestFocus();
                }else{
                    BLL_admin.activity_create();
                }
                break;
            case txtDNI_update:
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtuser.requestFocus();
                }else{
                    BLL_admin.DNI_update();
                }
                break;
            case txtuser_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtpass.requestFocus();
                }else{
                    BLL_admin.user_update();
                }
                break;
            case txtpass_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtname.requestFocus();
                }else{
                    BLL_admin.pass_update();
                }
                break;
            case txtname_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtsurname.requestFocus();
                }else{
                    BLL_admin.name_update();
                }
                break;
            case txtsurname_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtemail.requestFocus();
                }else{
                    BLL_admin.surname_update();
                }
                break;
            case txtemail_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtmobilephone.requestFocus();
                }else{
                    BLL_admin.email_update();
                }
                break;
            case txtmobilephone_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtsalary.requestFocus();
                }else{
                    BLL_admin.mobilephone_update();
                }
                break;
            case txtsalary_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtincentive.requestFocus();
                }else{
                    BLL_admin.salary_update();
                }
                break;
            case txtincentive_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.txtactivity.requestFocus();
                }else{
                    BLL_admin.incentive_update();
                }
                break;
            case txtactivity_update:
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    update.btnaceptar.requestFocus();
                }else{
                    BLL_admin.activity_update();
                }
                break;
                
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(Controller_admin.action.valueOf(e.getComponent().getName())){
            case txtDNI_create:
                BLL_admin.DNI_create();
                break;
            case txtuser_create:
                BLL_admin.user_create();
                break;
            case txtpass_create:
                BLL_admin.pass_create();
                break;
            case txtname_create:
                BLL_admin.name_create();
                break;
            case txtsurname_create:
                BLL_admin.surname_create();
                break;
            case txtemail_create:
                BLL_admin.email_create();
                break;
            case txtmobilephone_create:
                BLL_admin.mobilephone_create();
                break;
            case txtsalary_create:
                BLL_admin.salary_create();
                break;
            case txtincentive_create:
                BLL_admin.incentive_create();
                break;
            case txtactivity_create:
                BLL_admin.activity_create();
                break;
            case txtDNI_update:
                BLL_admin.DNI_update();
                break;
            case txtuser_update:
                BLL_admin.user_update();
                break;
            case txtpass_update:
                BLL_admin.pass_update();
                break;
            case txtname_update:
                BLL_admin.name_update();
                break;
            case txtsurname_update:
                BLL_admin.surname_update();
                break;
            case txtemail_update:
                BLL_admin.email_update();
                break;
            case txtmobilephone_update:
                BLL_admin.mobilephone_update();
                break;
            case txtsalary_update:
                BLL_admin.salary_update();
                break;
            case txtincentive_update:
                BLL_admin.incentive_update();
                break;
            case txtactivity_update:
                BLL_admin.activity_update();
                break;
                
        }
            
    }
}
