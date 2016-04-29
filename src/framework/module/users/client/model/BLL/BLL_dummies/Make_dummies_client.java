/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.users.client.model.BLL.BLL_dummies;

import com.mongodb.DBCollection;
import framework.classes.ClassDate;
import framework.classes.Mongo_BD;
import framework.module.users.client.model.classes.Client;
import framework.module.users.client.model.classes.Singleton_client;
import java.util.Random;

/**
 *
 * @author angel
 */
public class Make_dummies_client {
    
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

    /* Client
     * 
     */
    public static float shopping (){
        float shopping = (float) (Math.random() * (50 - 1000) + 1000);
        return (float) Math.rint(shopping * 100) / 100;
    }

    public static int dtos (){
        int dtos = (int) (Math.random() * (5 - 100) + 100);
        return (int) Math.rint(dtos * 100) / 100;
    }

    public static boolean premium (){
        boolean premium;
        boolean [] premiums = {true, false};

        int position = (int) (Math.random() * 222) % 2;
        return premium = premiums[position];
    }

    public static String typeclient (){
        String typeclient = "";
        String [] typeclients = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10",
                                "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10",
                                "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10",
                                "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10",};

        int position = (int) (Math.random() * 222) % 10;
        return typeclient = typeclients[position];
    }

    /* BUILDER
     * 
     */
    public static void makedummies_client () {
        DBCollection table = Mongo_BD.getCollection();

        for (int i=0; i<5; i++) {
            Client c1 = new Client(DNI(), user(), pass(), avatar(), state(), name(), surname(), email(), mobilephone(),
                            datebirthday(), shopping(), dtos(), premium(), typeclient());
            Singleton_client.userclient.add(c1);
            table.insert(c1.Client_to_BD());
        }
    }
}
