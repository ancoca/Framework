/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.config.model.DAO;

import framework.module.config.view.Config;

/**
 *
 * @author angel
 */
public class DAO_config {
    
    public static String language () {
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
    
    public static String date () {
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
    
    public static char currency () {
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
    
    public static String theme () {
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
    
    public static int decimal () {
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
}
