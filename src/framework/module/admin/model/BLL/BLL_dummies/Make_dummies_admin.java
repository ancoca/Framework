/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.admin.model.BLL.BLL_dummies;

import com.mysql.jdbc.Connection;
import framework.classes.ClassDate;
import framework.classes.ConexionBD;
import framework.module.admin.model.classes.Admin;
import framework.module.admin.model.classes.Singleton_admin;
import java.sql.PreparedStatement;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author angel
 */
public class Make_dummies_admin {
    
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
	
	/* Admin
	 * 
	 */
	public static ClassDate datecontract (){
		String [] years = {"19", "20"};
		int day = (int) (Math.random() * (1 - 31) + 31);
		int month = (int) (Math.random() * (1 - 12) + 12);
		int year3 = (int) (Math.random() * (0 - 99) + 99);
		int position = (int) (Math.random() * 222) % 2;
		String year2 = years[position]+year3;
		int year = Integer.parseInt(year2);
		
		return new ClassDate (day, month, year);
	}
	
	public static float salary (){
		float salary = (float) (Math.random() * (600 - 2000) + 2000);
		return (float) Math.rint(salary * 100) / 100;
	}
	
	public static float incentive (){
		float incentive = (float) (Math.random() * (200 - 500) + 500);
		return (float) Math.rint(incentive * 100) / 100;
	}
	
	public static int activity (){
		int activity = (int) (Math.random() * (10 - 100) + 100);
		return (int) Math.rint(activity * 100) / 100;
	}
        
        /* BUILDER
	 * 
	 */
	public static void makedummies_admin () {
            Connection connection = null;
            ConexionBD conexion_DB = new ConexionBD();
            PreparedStatement statement = null;
            int resultado = 0, state = 0;
 	
            try{
                for (int i=0; i<5; i++) {
                    connection = conexion_DB.AbrirConexion();
                        Admin a1 = new Admin (DNI(), user(), pass(), avatar(), state(), name(), surname(), email(), mobilephone(),
                                        datebirthday(), datecontract(), salary(), incentive(), activity());
                        Singleton_admin.useradmin.add(a1);
                        statement = connection.prepareStatement("INSERT INTO admin "
                                + "(DNI, user, pass, avatar, state, name, surname, email, mobilephone, "
                                + "datebirthday, age, benefits, datecontract, old, salary, incentive, activity) "
                                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                        statement.setString(1, a1.getDNI());
                        statement.setString(2, a1.getUser());
                        statement.setString(3, a1.getPass());
                        statement.setString(4, a1.getAvatar());
                        if (a1.getState()==true){
                            state = 1;
                        }else{
                            state = 0;
                        }
                        statement.setInt(5, state);
                        statement.setString(6, a1.getName());
                        statement.setString(7, a1.getSurname());
                        statement.setString(8, a1.getEmail());
                        statement.setString(9, a1.getMobilephone());
                        statement.setString(10, a1.getDatebirthday().toStringBD());
                        statement.setInt(11, a1.getAge());
                        statement.setFloat(12, a1.getBenefits());
                        statement.setString(13, a1.getDatecontract().toStringBD());
                        statement.setInt(14, a1.getOld());
                        statement.setFloat(15, a1.getSalary());
                        statement.setFloat(16, a1.getIncentive());
                        statement.setInt(17, a1.getActivity());
                        int correct=statement.executeUpdate();
                        System.out.println(correct);
                    }
                conexion_DB.CerrarConexion((com.mysql.jdbc.Connection) connection);
                resultado = 1;
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Ha habido un error al cargar los dummies");
                resultado = 0;
            }
            
            if (resultado == 1){
                JOptionPane.showMessageDialog(null, "Dummies guardados correctamente en la Base de Datos");
            }
 	}
}

