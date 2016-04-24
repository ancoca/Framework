package framework.module.admin.model.classes.language;

import framework.module.menu_config.model.classes.ClassConfig;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author angel
 */
public class Language_admin extends Properties{
	
    private static Language_admin instance;

    public Language_admin () {
        switch (ClassConfig.getInstance().getLanguage()) {
            case "en":
                getProperties("English_admin.properties");
                break;
            case "es":
                getProperties("Español_admin.properties");
                break;
            case "val":
                getProperties("Valencia_admin.properties");
                break;
        }
    }

    public static Language_admin getInstance () {
        if (instance==null){
            instance = new Language_admin();
        }
        return instance;
    }

    public void setLanguage () {
        switch (ClassConfig.getInstance().getLanguage()) {
            case "en":
                getProperties("English_admin.properties");
                break;
            case "es":
                getProperties("Español_admin.properties");
                break;
            case "val":
                getProperties("Valencia_admin.properties");
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
