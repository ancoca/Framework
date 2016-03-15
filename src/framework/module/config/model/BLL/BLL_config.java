/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.config.model.BLL;

import framework.module.config.model.DAO.DAO_config;
import framework.module.config.model.classes.ClassConfig;
import framework.module.config.model.functions.json_auto_config;
import framework.module.config.view.Config;

/**
 *
 * @author angel
 */
public class BLL_config {
    
    public static void setConfig () {
        ClassConfig.getInstance().setLanguage(DAO_config.language());
        ClassConfig.getInstance().setDate(DAO_config.date());
        ClassConfig.getInstance().setCurrency(DAO_config.currency());
        ClassConfig.getInstance().setTheme(DAO_config.theme());
        ClassConfig.getInstance().setDecimal(DAO_config.decimal());
        json_auto_config.savejson_config();
    }
    
    public static void getConfig () {
        BLL_config.getlanguage();
        BLL_config.getdate();
        BLL_config.getcurrency();
        BLL_config.gettheme();
        BLL_config.getdecimal();
    }
    
    public static void getlanguage () {
        switch (ClassConfig.getInstance().getLanguage()){
            case "en":
                Config.english.setSelected(true);
                break;
            case "es":
                Config.castellano.setSelected(true);
                break;
            case "val":
                Config.valencia.setSelected(true);
                break;
        }
    }
    
    public static void getdate () {
        switch (ClassConfig.getInstance().getDate()){
            case "dd/MM/yyyy":
                Config.date1.setSelected(true);
                break;
            case "dd-MM-yyyy":
                Config.date2.setSelected(true);
                break;
            case "yyyy/MM/dd":
                Config.date3.setSelected(true);
                break;
            case "yyyy-MM--dd":
                Config.date4.setSelected(true);
                break;
        }
    }
    
    public static void getcurrency () {
        switch (ClassConfig.getInstance().getCurrency()){
            case '€':
                Config.€.setSelected(true);
                break;
            case '$':
                Config.$.setSelected(true);
                break;
            case '£':
                Config.£.setSelected(true);
                break;
        }
    }
    
    public static void gettheme () {
        switch (ClassConfig.getInstance().getTheme()){
            case "METAL":// Metal - Predeterminado JAVA
                Config.metal.setSelected(true);
                break;
            case "GTK":// GTK
                Config.gtk.setSelected(true);
                break;
            case "MOTIF":// Motif
                Config.motif.setSelected(true);
                break;
            case "NINBUS":// Nimbus - JAVA
                Config.ninbus.setSelected(true);
                break;	
            case "WINDOWS95":// WINDOWS 95
                Config.windowsclassic.setSelected(true);
                break;
            case "WINDOWS":// WINDOWS
                Config.windows.setSelected(true);
                break;
            case "MAC OS":// MAC OS
                Config.mac.setSelected(true);
                break;
            case "MAC OS X":// MAC OS X
                Config.aqua.setSelected(true);
                break;
        }
    }
    
    public static void getdecimal () {
        switch (ClassConfig.getInstance().getDecimal()){
            case 1:
                Config.decimal1.setSelected(true);
                break;
            case 2:
                Config.decimal2.setSelected(true);
                break;
            case 3:
                Config.decimal3.setSelected(true);
                break;
        }
    }
}
