package framework.classes.language;

import java.io.IOException;
import java.util.Properties;

import framework.module.menu_config.model.classes.ClassConfig;

public class Language_general extends Properties{
	
	private static Language_general instance;
	
	public Language_general () {
		switch (ClassConfig.getInstance().getLanguage()) {
			case "en":
				getProperties("English_general.properties");
				break;
			case "es":
				getProperties("Español_general.properties");
				break;
			case "val":
				getProperties("Valencia_general.properties");
				break;
		}
	}
	
	public static Language_general getInstance () {
		if (instance==null){
			instance = new Language_general();
		}
		return instance;
	}
	
	public void setLanguage () {
		switch (ClassConfig.getInstance().getLanguage()) {
			case "en":
				getProperties("English_general.properties");
				break;
			case "es":
				getProperties("Español_general.properties");
				break;
			case "val":
				getProperties("Valencia_general.properties");
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
