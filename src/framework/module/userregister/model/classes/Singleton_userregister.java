package framework.module.userregister.model.classes;

import framework.module.userregister.model.classes.User_register;
import framework.module.client.model.classes.Client;
import framework.module.admin.model.classes.Admin;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angel
 */
public class Singleton_userregister {
    
    public static ArrayList <User_register> userregister;
    public static ImageIcon ok = new ImageIcon("src/framework/img/ok.png");
    public static ImageIcon cancel = new ImageIcon("src/framework/img/cancel.png");
    public static ImageIcon add = new ImageIcon("src/framework/img/edit_add.png");
    public static ImageIcon add_bn = new ImageIcon("src/framework/img/edit_add_bn.png");
    public static ImageIcon line = new ImageIcon("src/framework/img/color_line.png");
    public static ImageIcon line_bn = new ImageIcon("src/framework/img/color_line_bn.png");
    public static ImageIcon remove = new ImageIcon("src/framework/img/edit_remove.png");
    public static ImageIcon remove_bn = new ImageIcon("src/framework/img/edit_remove_bn.png");
    public static ImageIcon volver = new ImageIcon("src/framework/img/volver.png");
    public static ImageIcon volver_bn = new ImageIcon("src/framework/img/volver_bn.png");
    public static ImageIcon presentation = new ImageIcon("src/framework/img/presentation.jpg");
    public static ImageIcon titulo = new ImageIcon("src/framework/img/titulo.JPG");
    public static ImageIcon json = new ImageIcon("src/framework/img/json.png");
    public static ImageIcon txt = new ImageIcon("src/framework/img/txt.jpg");
    public static ImageIcon xml = new ImageIcon("src/framework/img/xml.jpg");
}
