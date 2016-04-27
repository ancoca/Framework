package framework.module.login.model.classes.language;

import framework.module.menu_config.model.classes.ClassConfig;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author angel
 */
public class Language_login extends Properties{
	
    private static Language_login instance;

    public Language_login () {
        switch (ClassConfig.getInstance().getLanguage()) {
            case "en":
                getProperties("English_login.properties");
                break;
            case "es":
                getProperties("Español_login.properties");
                break;
            case "val":
                getProperties("Valencia_login.properties");
                break;
        }
    }
	
    public static Language_login getInstance () {
        if (instance==null){
            instance = new Language_login();
        }
        return instance;
    }

    public void setLanguage () {
        switch (ClassConfig.getInstance().getLanguage()) {
            case "en":
                getProperties("English_login.properties");
                break;
            case "es":
                getProperties("Español_login.properties");
                break;
            case "val":
                getProperties("Valencia_login.properties");
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
