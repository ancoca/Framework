/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.userregister.model.BLL.BLL_dummies;

import framework.classes.ClassDate;
import framework.module.userregister.model.classes.Singleton_userregister;
import framework.module.userregister.model.classes.User_register;
import java.util.Random;

/**
 *
 * @author angel
 */
public class Make_dummies_userregister {
    
    /* User
	 * MotherClass
	 */
	public static String dni(int dni) {
		String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";
        return String.valueOf(dni) + NIF_STRING_ASOCIATION.charAt(dni % 23);
    }
	
	public static String DNI (){
		String cad = "";
        long time = new java.util.GregorianCalendar().getTimeInMillis();
        Random random = new Random(time);
        while (cad.length() < 8) {
            char c = (char) random.nextInt(255);
            if ((c >= '0' && c <= '9')) {
                cad += c;
            }
        }
        int numberdni = Integer.parseInt(cad);
        return dni(numberdni);
	}
	
	public static String user (){
		String user = "";
		String[] users = {"vicent", "walter", "joan", "maria", "daniel", "josep", "sara", "pepa", "felipe",
	            "jaume", "sergio", "paco", "emilio", "nando", "alfons", "eduard", "atalia", "veronica", "fina",
	            "pepe", "oscar", "lorelay", "jim", "lola", "laia", "ismael", "jordi", "ramon", "javier", "nuria",
	            "elena", "beltran", "pablo", "juanjo"};
		
		int position = (int) (Math.random() * 222) % 10;
		return user = users[position];
	}
	
	public static String pass (){
		String pass = "";
		String[] passwords = {"vicent", "walter", "joan", "maria", "daniel", "josep", "sara", "pepa", "felipe",
	            "jaume", "sergio", "paco", "emilio", "nando", "alfons", "eduard", "atalia", "veronica", "fina",
	            "pepe", "oscar", "lorelay", "jim", "lola", "laia", "ismael", "jordi", "ramon", "javier", "nuria",
	            "elena", "beltran", "pablo", "juanjo"};
		
		int position = (int) (Math.random() * 222) % 10;
		return pass = passwords[position];
	}
	
	public static String avatar (){
		String avatar = "";
		String[] avatars = {"vicent", "walter", "joan", "maria", "daniel", "josep", "sara", "pepa", "felipe",
	            "jaume", "sergio", "paco", "emilio", "nando", "alfons", "eduard", "atalia", "veronica", "fina",
	            "pepe", "oscar", "lorelay", "jim", "lola", "laia", "ismael", "jordi", "ramon", "javier", "nuria",
	            "elena", "beltran", "pablo", "juanjo"};
		
		int position = (int) (Math.random() * 222) % 10;
		return avatar = avatars[position]+".jpeg";
	}
	
	public static boolean state (){
		boolean state;
		boolean [] states = {true, false};
		
		int position = (int) (Math.random() * 222) % 2;
		return state = states[position];
	}
	
	public static String name (){
		String name = "";
		String[] names = {"vicent", "walter", "joan", "maria", "daniel", "josep", "sara", "pepa", "felipe",
	            "jaume", "sergio", "paco", "emilio", "nando", "alfons", "eduard", "atalia", "veronica", "fina",
	            "pepe", "oscar", "lorelay", "jim", "lola", "laia", "ismael", "jordi", "ramon", "javier", "nuria",
	            "elena", "beltran", "pablo", "juanjo"};
		
		int position = (int) (Math.random() * 222) % 10;
		return name = names[position];
	}
	
	public static String surname (){
		String surname = "";
		String [] surnames = {"alfonso", "aliaga", "bas", "bataller", "boluda", "conesa", "camarena", "cucart", "doria",
					"ferrero", "garcia", "figuera", "tormo", "montagud", "gramage", "ubeda", "revert", "cordoba", "sempere",
					"martinez", "lopez", "albuixech", "torro", "belda", "mateu", "ribera", "satorres", "rubio", "cifuentes",
					"frances", "gomez", "mico", "ferrandiz", "casanova"};
		
		int position = (int) (Math.random() * 222) % 10;
		return surname = surnames[position];
	}
	
	public static String email (){
		String email = "";
		String[] emails = {"vicent", "walter", "joan", "maria", "daniel", "josep", "sara", "pepa", "felipe",
	            "jaume", "sergio", "paco", "emilio", "nando", "alfons", "eduard", "atalia", "veronica", "fina",
	            "pepe", "oscar", "lorelay", "jim", "lola", "laia", "ismael", "jordi", "ramon", "javier", "nuria",
	            "elena", "beltran", "pablo", "juanjo"};
		
		int position = (int) (Math.random() * 222) % 10;
		return email = emails[position]+"@gmail.com";
	}
	
	public static String mobilephone (){
		String mobilephone = "";
        long time = new java.util.GregorianCalendar().getTimeInMillis();
        Random random = new Random(time);
        while (mobilephone.length() < 9) {
            char c = (char) random.nextInt(255);
            if ((c >= '0' && c <= '9')) {
            	mobilephone += c;
            }
        }
        return mobilephone;
	}
	
	public static ClassDate datebirthday (){
		String [] years = {"19", "20"};
		int day = (int) (Math.random() * (1 - 31) + 31);
		int month = (int) (Math.random() * (1 - 12) + 12);
		int year3 = (int) (Math.random() * (0 - 99) + 99);
		int position = (int) (Math.random() * 222) % 2;
		String year2 = years[position]+year3;
		int year = Integer.parseInt(year2);
		
		return new ClassDate (day, month, year);
		
	}
        
        /* User_register
	 * 
	 */
	public static int movement (){
		int movement = (int) (Math.random() * (10 - 100) + 100);
		return (int) Math.rint(movement * 100) / 100;
	}
	
	public static int point (){
		int point = (int) (Math.random() * (5 - 50) + 50);
		return (int) Math.rint(point * 100) / 100;
	}
	
	/* BUILDER
	 * 
	 */
        public static void makedummies_userregister () {
		for (int i=0; i<5; i++) {
			User_register u1 = new User_register(DNI(), user(), pass(), avatar(), state(), name(), surname(),
					email(), mobilephone(), datebirthday(), movement(), point());
			Singleton_userregister.userregister.add(u1);
		}
	}
}
