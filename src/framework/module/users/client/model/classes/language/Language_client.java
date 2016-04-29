package framework.module.users.client.model.classes.language;

import framework.module.menu_config.model.classes.ClassConfig;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author angel
 */
public class Language_client extends Properties{
	
    private static Language_client instance;

    public Language_client () {
        switch (ClassConfig.getInstance().getLanguage()) {
            case "en":
                getProperties("English_client.properties");
                break;
            case "es":
                getProperties("Español_client.properties");
                break;
            case "val":
                getProperties("Valencia_client.properties");
                break;
        }
    }

    public static Language_client getInstance () {
        if (instance==null){
            instance = new Language_client();
        }
        return instance;
    }

    public void setLanguage () {
        switch (ClassConfig.getInstance().getLanguage()) {
            case "en":
                getProperties("English_client.properties");
                break;
            case "es":
                getProperties("Español_client.properties");
                break;
            case "val":
                getProperties("Valencia_client.properties");
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
