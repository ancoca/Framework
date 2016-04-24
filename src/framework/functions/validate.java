package framework.functions;

import framework.module.menu_config.model.classes.ClassConfig;

/**
 * 
 * @author angel
 */
public class validate {

    private static final String pattern_DNI = "^[0-9]{8}[A-Z]$";
    private static final String pattern_user = "^[a-zA-Z0-9]{4,}$";
    private static final String pattern_password = "^(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).*$";
    private static final String pattern_name = "^[a-zA-Z\\s]*$";
    private static final String pattern_surname = "^[a-zA-Z\\s]*$";
    private static final String pattern_email = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String pattern_mobilephone = "^[0-9]{9}$";
    private static final String pattern_date1 = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2}$";
    private static final String pattern_date2 = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19|20)\\d{2}$";
    private static final String pattern_date3 = "^(19|20)\\d{2}/(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])$";
    private static final String pattern_date4 = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
    private static final String pattern_age = "^[0-9]{1,2}$";
    private static final String pattern_typeclient = "^[A-Za-z0-9-\\s]*$";

    /**
     * CHECK DNI
     * @param DNI
     * @return 
     */
    public static boolean DNI (String DNI) {
        return DNI.matches(pattern_DNI);
    }

    /**
     * CHECK USER
     * @param user
     * @return 
     */
    public static boolean user (String user) {
        return user.matches(pattern_user);
    }

    /**
     * CHECK PASS
     * @param pass
     * @return 
     */
    public static boolean pass (String pass) {
        return pass.matches(pattern_password);
    }

    /**
     * CHECK NAME
     * @param name
     * @return 
     */
    public static boolean name (String name) {
        return name.matches(pattern_name);
    }

    /**
     * CHECK SURNAME
     * @param surname
     * @return 
     */
    public static boolean surname (String surname) {
        return surname.matches(pattern_surname);
    }

    /**
     * CHECK EMAIL
     * @param email
     * @return 
     */
    public static boolean email (String email) {
        return email.matches(pattern_email);
    }

    /**
     * CHECK MOBILEPHONE
     * @param mobilephone
     * @return 
     */
    public static boolean mobilephone (String mobilephone) {
        return mobilephone.matches(pattern_mobilephone);
    }

    /**
     * CHECK DATE
     * @param date
     * @return 
     */
    public static boolean date (String date) {
        String s="";
        switch (ClassConfig.getInstance().getDate()) {
            case "dd/MM/yyyy":
                s = pattern_date1;
                break;
                
            case "dd-MM-yyyy":
                s = pattern_date2;
                break;
                
            case "yyyy/MM/dd":
                s = pattern_date3;
                break;
                
            case "yyyy-MM-dd":
                s = pattern_date4;
                break;
        }
        
        return date.matches(s);
    }

    /**
     * CHECK AGE
     * @param age
     * @return 
     */
    public static boolean age (String age) {
        return age.matches(pattern_age);
    }

    /**
     * CHECK CLIENT'S TYPE
     * @param typeclient
     * @return 
     */
    public static boolean typeclient (String typeclient) {
        return typeclient.matches(pattern_typeclient);
    }
}
