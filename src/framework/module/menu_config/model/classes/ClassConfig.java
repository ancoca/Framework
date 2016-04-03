package framework.module.menu_config.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import framework.classes.language.Language_general;
import framework.functions.Functions_menu;
import framework.functions.Functions_theme;
import framework.module.admin.model.BLL.BLL_dummies.Make_dummies_admin;
import framework.module.admin.model.classes.Admin;
import framework.module.admin.model.classes.Singleton_admin;
import framework.module.admin.model.classes.language.Language_admin;
import framework.module.admin.model.functions.json_auto_admin;
import framework.module.classes.language.Language_user;
import framework.module.client.model.BLL.BLL_dummies.Make_dummies_client;
import framework.module.client.model.classes.Client;
import framework.module.client.model.classes.Singleton_client;
import framework.module.client.model.classes.language.Language_client;
import framework.module.client.model.functions.json_auto_client;
import framework.module.menu_config.model.classes.language.Language_menu_config;
import framework.module.userregister.model.classes.User_register;
import framework.module.menu_config.model.functions.json_auto_config;
import framework.module.userregister.model.BLL.BLL_dummies.Make_dummies_userregister;
import framework.module.userregister.model.classes.Singleton_userregister;
import framework.module.userregister.model.classes.language.Language_userregister;
import framework.module.userregister.model.functions.json_auto_userregister;

@XStreamAlias("ClassConfig")
public class ClassConfig implements Serializable {

	/*
	 * ATTRIBUTES
	 * 
	 */
	
	@XStreamAlias("language")
	private String language;
	@XStreamAlias("decimal")
	private int decimal;
	@XStreamAlias("currency")
	private char currency;
	@XStreamAlias("date")
	private String date;
	@XStreamAlias("file")
	private String file;
	@XStreamAlias("theme")
	private String theme;
	@XStreamAlias("instance")
	private static ClassConfig instance;
	
	public ClassConfig (String language, String date, char currency, int decimal, String file, String theme) {
		this.language = language;
		this.date = date;
		this.currency = currency;
		this.decimal = decimal;
		this.file = file;
		this.theme = theme;
		
	}
	
	protected ClassConfig () {
		this.language = "en";
		this.date = "dd/MM/yyyy";
		this.currency = '€';
		this.decimal = 2;
		this.file = "json";
		this.theme = "GTK";
	}
	
	public static ClassConfig getInstance () {
		if (instance == null){
			instance = new ClassConfig ();
                        
                        json_auto_config.openjson_config();

			Functions_theme.theme();
			Language_user.getInstance();
                        Language_admin.getInstance();
                        Language_client.getInstance();
                        Language_userregister.getInstance();
                        Language_menu_config.getInstance();
                        Language_general.getInstance();
			
			Singleton_admin.useradmin = new ArrayList <Admin> ();
			Singleton_client.userclient = new ArrayList <Client> ();
			Singleton_userregister.userregister = new ArrayList <User_register> ();
			
			json_auto_admin.openjson_admin();
                        json_auto_client.openjson_client();
                        json_auto_userregister.openjson_userregister();
			boolean dummies = Functions_menu.YES_NO(Language_general.getInstance().getProperty("dummies"));
			if (dummies == true) {
				Make_dummies_admin.makedummies_admin();
                                json_auto_admin.savejson_admin();
				Make_dummies_client.makedummies_client();
                                json_auto_client.savejson_client();
				Make_dummies_userregister.makedummies_userregister();
                                json_auto_userregister.savejson_userregister();
			}
		}
		return instance;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
		Language_user.getInstance().setLanguage();
		Language_admin.getInstance().setLanguage();
                Language_client.getInstance().setLanguage();
                Language_userregister.getInstance().setLanguage();
                Language_menu_config.getInstance().setLanguage();
                Language_general.getInstance().setLanguage();
	}

	public int getDecimal() {
		return decimal;
	}

	public void setDecimal(int decimal) {
		this.decimal = decimal;
	}

	public char getCurrency() {
		return currency;
	}

	public void setCurrency(char currency) {
		this.currency = currency;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
		Functions_theme.theme();
	}
	
	public String toString (ClassConfig config) {
		StringBuffer string = new StringBuffer();
		string.append(Language_general.getInstance().getProperty("toStringlanguage")+this.getLanguage()+"\n");
		string.append(Language_general.getInstance().getProperty("toStringformatdate")+this.getDate()+"\n");
		string.append(Language_general.getInstance().getProperty("toStringcurrency")+this.getCurrency()+"\n");
		string.append(Language_general.getInstance().getProperty("toStringdecimal")+this.getDecimal()+"\n");
		string.append(Language_general.getInstance().getProperty("toStringfile")+this.getFile()+"\n");
		string.append(Language_general.getInstance().getProperty("toStringtheme")+this.getTheme()+"\n");
		return string.toString();
	}
}
