package framework.module.users.classes.language;

import framework.module.menu_config.model.classes.ClassConfig;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author angel
 */
public class Language_user extends Properties{
	
    private static Language_user instance;

    public Language_user () {
        switch (ClassConfig.getInstance().getLanguage()) {
            case "en":
                getProperties("English_user.properties");
                break;
            case "es":
                getProperties("Español_user.properties");
                break;
            case "val":
                getProperties("Valencia_user.properties");
                break;
        }
    }

    public static Language_user getInstance () {
        if (instance==null){
            instance = new Language_user();
        }
        return instance;
    }

    public void setLanguage () {
        switch (ClassConfig.getInstance().getLanguage()) {
            case "en":
                getProperties("English_user.properties");
                break;
            case "es":
                getProperties("Español_user.properties");
                break;
            case "val":
                getProperties("Valencia_user.properties");
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
