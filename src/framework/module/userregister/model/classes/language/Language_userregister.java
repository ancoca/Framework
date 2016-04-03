package framework.module.userregister.model.classes.language;

import framework.module.menu_config.model.classes.ClassConfig;
import java.io.IOException;
import java.util.Properties;

public class Language_userregister extends Properties{
	
	private static Language_userregister instance;
	
	public Language_userregister () {
		switch (ClassConfig.getInstance().getLanguage()) {
			case "en":
				getProperties("English_userregister.properties");
				break;
			case "es":
				getProperties("Español_userregister.properties");
				break;
			case "val":
				getProperties("Valencia_userregister.properties");
				break;
		}
	}
	
	public static Language_userregister getInstance () {
		if (instance==null){
                    instance = new Language_userregister();
                }
		return instance;
	}
	
	public void setLanguage () {
		switch (ClassConfig.getInstance().getLanguage()) {
			case "en":
				getProperties("English_userregister.properties");
				break;
			case "es":
				getProperties("Español_userregister.properties");
				break;
			case "val":
				getProperties("Valencia_userregister.properties");
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
