package framework.module.menu_config.model.classes.language;

import framework.module.menu_config.model.classes.ClassConfig;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author angel
 */
public class Language_menu_config extends Properties{
	
    private static Language_menu_config instance;

    public Language_menu_config () {
        switch (ClassConfig.getInstance().getLanguage()) {
            case "en":
                getProperties("English_menu_config.properties");
                break;
            case "es":
                getProperties("Español_menu_config.properties");
                break;
            case "val":
                getProperties("Valencia_menu_config.properties");
                break;
        }
    }
	
    public static Language_menu_config getInstance () {
        if (instance==null){
            instance = new Language_menu_config();
        }
        return instance;
    }

    public void setLanguage () {
        switch (ClassConfig.getInstance().getLanguage()) {
            case "en":
                getProperties("English_menu_config.properties");
                break;
            case "es":
                getProperties("Español_menu_config.properties");
                break;
            case "val":
                getProperties("Valencia_menu_config.properties");
                break;
        }
    }

    public void getProperties(String idioma) {
        try {
            this.load( getClass().getResourceAsStream(idioma) );
        } catch (IOException ex) {
        }
    }
}
