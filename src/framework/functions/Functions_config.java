package framework.functions;

import framework.module.config.model.classes.ClassConfig;
import framework.module.config.model.classes.language.Language_general;

public class Functions_config {

    public static void language () {
        String [] menu_language = { "English", "Español", "Valencià" };
        int menu = 0;

        menu = Functions_menu.botones(menu_language, Language_general.getInstance().getProperty("languagetitle"), Language_general.getInstance().getProperty("asktitle"));

        switch (menu) {
            case 0:
                ClassConfig.getInstance().setLanguage("en");
                //Language_general.getInstance().setLanguage();
                //Language_user.getInstance().setLanguage();
                break;
                
            case 1:
                ClassConfig.getInstance().setLanguage("es");
                //Language_general.getInstance().setLanguage();
                //Language_user.getInstance().setLanguage();
                break;
                
            case 2:
                ClassConfig.getInstance().setLanguage("val");
                //Language_general.getInstance().setLanguage();
                //Language_user.getInstance().setLanguage();
                break;
                
        }
    }

    public static void date () {
        String [] menu_date = { "dd/mm/yyyy", "dd-mm-yyyy", "yyyy/mm/dd", "yyyy-mm-dd" };
        int menu = 0;

        menu = Functions_menu.botones(menu_date, Language_general.getInstance().getProperty("datetitle"), Language_general.getInstance().getProperty("asktitle"));

        switch (menu) {
            case 0:
                ClassConfig.getInstance().setDate("dd/mm/yyyy");
                break;
                
            case 1:
                ClassConfig.getInstance().setDate("dd-mm-yyyy");
                break;
                
            case 2:
                ClassConfig.getInstance().setDate("yyyy/mm/dd");
                break;
                
            case 3:
                ClassConfig.getInstance().setDate("yyyy-mm-dd");
                break;
        }
    }

    public static void currency () {
        String [] menu_currency = { "€", "$", "£" };
        int menu = 0;

        menu = Functions_menu.botones(menu_currency, Language_general.getInstance().getProperty("currencytitle"), Language_general.getInstance().getProperty("asktitle"));

        switch (menu) {
            case 0:
                ClassConfig.getInstance().setCurrency('€');
                break;
                
            case 1:
                ClassConfig.getInstance().setCurrency('$');
                break;
                
            case 2:
                ClassConfig.getInstance().setCurrency('£');
                break;
        }
    }

    public static void decimal () {
        int menu = 0;
        String [] menu_decimal = { "1", "2", "3" };

        menu = Functions_menu.botones(menu_decimal, Language_general.getInstance().getProperty("decimaltitle"), Language_general.getInstance().getProperty("asktitle"));

        switch (menu) {
            case 0:
                ClassConfig.getInstance().setDecimal(1);
                break;
                
            case 1:
                ClassConfig.getInstance().setDecimal(2);
                break;
                
            case 2:
                ClassConfig.getInstance().setDecimal(3);
                break;
        }
    }
    public static void file () {
        String [] menu_file = { "JSON", "XML", "TXT" };
        int menu = 0;

        menu = Functions_menu.botones(menu_file, Language_general.getInstance().getProperty("filetitle"), Language_general.getInstance().getProperty("asktitle"));

        switch (menu) {
            case 0:
                ClassConfig.getInstance().setFile("json");
                break;
                
            case 1:
                ClassConfig.getInstance().setFile("xml");
                break;
                
            case 2:
                ClassConfig.getInstance().setFile("txt");
                break;
        }
    }

    public static void theme () {
        String [] options = { "METAL", "GTK", "MOTIF", "NINBUS", "WINDOWS95", "WINDOWS", "MAC OS", "MAC OS X" };
        int menu = 0;

        menu = Functions_menu.botones(options, Language_general.getInstance().getProperty("themetitle"), Language_general.getInstance().getProperty("asktitle") );
        switch (menu){
            case 0:// Metal - Predeterminado JAVA
                ClassConfig.getInstance().setTheme("METAL");
                //Functions_theme.theme();
                break;

            case 1:// GTK
                ClassConfig.getInstance().setTheme("GTK");
                //Functions_theme.theme();
                break;

            case 2:// Motif
                ClassConfig.getInstance().setTheme("MOTIF");
                //Functions_theme.theme();
                break;

            case 3:// Nimbus - JAVA
                ClassConfig.getInstance().setTheme("NINBUS");
                //Functions_theme.theme();
                break;	

            case 4:// WINDOWS 95
                ClassConfig.getInstance().setTheme("WINDOWS95");
                //Functions_theme.theme();
                break;

            case 5:// WINDOWS
                ClassConfig.getInstance().setTheme("WINDOWS");
                //Functions_theme.theme();
                break;

            case 6:// MAC OS
                ClassConfig.getInstance().setTheme("MAC OS");
                //Functions_theme.theme();
                break;

            case 7:// MAC OS X
                ClassConfig.getInstance().setTheme("MAC OS X");
                //Functions_theme.theme();
                break;
        }
    }
}
