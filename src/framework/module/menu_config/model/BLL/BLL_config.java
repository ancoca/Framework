/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.menu_config.model.BLL;

import framework.module.menu_config.model.DAO.DAO_config;
import framework.module.menu_config.model.classes.ClassConfig;
import framework.module.menu_config.model.functions.json_auto_config;

/**
 *
 * @author angel
 */
public class BLL_config {
    
    public static void setConfig () {
        ClassConfig.getInstance().setLanguage(DAO_config.setlanguage());
        ClassConfig.getInstance().setDate(DAO_config.setdate());
        ClassConfig.getInstance().setCurrency(DAO_config.setcurrency());
        ClassConfig.getInstance().setTheme(DAO_config.settheme());
        ClassConfig.getInstance().setDecimal(DAO_config.setdecimal());
        json_auto_config.savejson_config();
    }
    
    public static void getConfig () {
        DAO_config.getlanguage();
        DAO_config.getdate();
        DAO_config.getcurrency();
        DAO_config.gettheme();
        DAO_config.getdecimal();
    }
}
