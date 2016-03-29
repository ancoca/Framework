/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.config.model.DAO;

import framework.module.config.model.classes.ClassConfig;
import framework.module.config.view.Config;

/**
 *
 * @author angel
 */
public class DAO_config {
    
    public static String setlanguage () {
        String language = "";
        
        if (Config.english.isSelected()){
            language = "en";
        }
        if (Config.castellano.isSelected()){
            language = "es";
        }
        if (Config.valencia.isSelected()){
            language = "val";
        }
        
        return language;
    }
    
    public static String setdate () {
        String date = "";
        
        if (Config.date1.isSelected()){
            date = "dd/MM/yyyy";
        }
        if (Config.date2.isSelected()){
            date = "dd-MM-yyyy";
        }
        if (Config.date3.isSelected()){
            date = "yyyy/MM/dd";
        }
        if (Config.date4.isSelected()){
            date = "yyyy-MM-dd";
        }
        
        return date;
    }
    
    public static char setcurrency () {
        char currency = ' ';
        
        if (Config.€.isSelected()){
            currency = '€';
        }
        if (Config.$.isSelected()){
            currency = '$';
        }
        if (Config.£.isSelected()){
            currency = '£';
        }
        
        return currency;
    }
    
    public static String settheme () {
        String theme = "";
        
        if (Config.metal.isSelected()){
            theme = "METAL";
        }
        if (Config.gtk.isSelected()){
            theme = "GTK";
        }
        if (Config.motif.isSelected()){
            theme = "MOTIF";
        }
        if (Config.ninbus.isSelected()){
            theme = "NINBUS";
        }
        if (Config.windowsclassic.isSelected()){
            theme = "WINDOWS95";
        }
        if (Config.windows.isSelected()){
            theme = "WINDOWS";
        }
        if (Config.mac.isSelected()){
            theme = "MAC OS";
        }
        if (Config.aqua.isSelected()){
            theme = "MAC OS X";
        }
        
        return theme;
    }
    
    public static int setdecimal () {
        int decimal = 0;
        
        if (Config.decimal1.isSelected()){
            decimal = 1;
        }
        if (Config.decimal2.isSelected()){
            decimal = 2;
        }
        if (Config.decimal3.isSelected()){
            decimal = 3;
        }
        
        return decimal;
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
