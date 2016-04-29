/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.users.userregister.model.DAO;

import com.toedter.calendar.JTextFieldDateEditor;
import framework.classes.ClassDate;
import framework.functions.validate;
import framework.module.users.userregister.model.BLL.BLL_userregister.BLL_userregister;
import framework.module.users.userregister.model.classes.User_register;
import framework.module.users.userregister.model.classes.Singleton_userregister;
import framework.module.users.userregister.model.classes.language.Language_userregister;
import framework.module.users.userregister.view.Create_userregister;
import framework.module.users.userregister.view.Read_userregister;
import framework.module.users.userregister.view.Update_userregister;

/**
 *
 * @author angel
 */
public class DAO_userregister {
    
            ////////////////////////////////////////////////////////////////////
            //                                                                //
            //                      CREATE_USER_REGISTER                      //
            //                                                                //
            ////////////////////////////////////////////////////////////////////
  
    /**
     * CHECK AND CREATE DATA OF THE USER
     * @return 
     */
    public static User_register ask_user_register () {
		
        //Check
        boolean checkDNI, checkname, checksurname, checkmobilephone, checkemail,
                checkuser, checkpass, checkavatar, checkdatebirthday, checkstate,
                checkmovement, checkpoint;

        //User_register
        String DNI="", name="",surname="", mobilephone="", email="", user="", pass="", avatar="";
        ClassDate datebirthday=null;
        boolean state;
        int movement=0, point=0;
        User_register user_register=null;

        checkDNI=DNI();
        checkuser=user();
        checkpass=pass();
        //checkavatar=avatar();
        //checkstate=state();
        checkname=name();
        checksurname=surname();
        checkemail=email();
        checkmobilephone=mobilephone();
        checkdatebirthday=datebirthday(12);
        checkmovement=activity();
        checkpoint=point();

        if (checkDNI==true && checkname==true && checksurname==true && checkmobilephone==true &&
            checkemail==true && checkuser==true && checkpass==true && checkdatebirthday==true &&
            checkmovement==true && checkpoint==true){

            DNI=Create_userregister.txtDNI.getText();
            user=Create_userregister.txtuser.getText();
            pass=Create_userregister.txtpass.getText();
            avatar=Create_userregister.txtavatar.getText();
            state=state();
            name=Create_userregister.txtname.getText();
            surname=Create_userregister.txtsurname.getText();
            email=Create_userregister.txtemail.getText();
            mobilephone=Create_userregister.txtmobilephone.getText();
            datebirthday=new ClassDate (((JTextFieldDateEditor) Create_userregister.txtdatebirthday.getDateEditor()).getText());
            movement=Integer.parseInt(Create_userregister.txtactivity.getText());
            point=Integer.parseInt(Create_userregister.txtpoint.getText());

            user_register = new User_register(DNI, user, pass, avatar, state, name, surname,
                        email, mobilephone, datebirthday, movement, point);
        }

        return user_register;
    }
	
    /**
     * CREATE USER WITH DNI ONLY
     * @return 
     */
    public static User_register ask_user_registerDNI () {
        boolean checkDNI;
        String DNI;
        User_register user_register = null;
	
        checkDNI=DNI();
        if (checkDNI==true){
            user_register = new User_register (Create_userregister.txtDNI.getText());
        }
	
        return user_register;
    }
        
    /**
     * CHECK DNI
     * @return 
     */
    public static boolean DNI () {
        boolean check=true;
        
        String DNI = "", aux = "", caracteres = "TRWAGMYFPDXBNJZSQVHLCKET";
        boolean confirm;
        int number = 0, module = 0;
        char character = ' ', control = ' ';

        DNI=Create_userregister.txtDNI.getText();
        confirm=validate.DNI(DNI);
        if (confirm==false) {
            check = false;
            Create_userregister.checkDNI.setIcon(Singleton_userregister.cancel);
        }else{
            aux = "";
            for(int i=0; i<8; i++){
                aux += DNI.charAt(i);
            }	
            character = DNI.charAt(8);			
            number = Integer.parseInt(aux);
            module= number % 23;
            control = caracteres.charAt(module);
            if(control == character){
                check = true;
                Create_userregister.checkDNI.setIcon(Singleton_userregister.ok);
            }else{
                check = false;
                Create_userregister.checkDNI.setIcon(Singleton_userregister.cancel);
            }
        }
        
        return check;
    }
    
    /**
     * CHECK USER
     * @return 
     */
    public static boolean user () {
        boolean check=true, confirm=false;
        
	confirm=validate.user(Create_userregister.txtuser.getText());
	if (confirm==false) {
            check = false;
            Create_userregister.checkuser.setIcon(Singleton_userregister.cancel);
	}else{
            check = true;
            Create_userregister.checkuser.setIcon(Singleton_userregister.ok);
        }
        
        return check;
    }
    
    /**
     * CHECK PASSWORD
     * @return 
     */
    public static boolean pass () {
        boolean check=true, confirm=false;
		
	confirm=validate.pass(Create_userregister.txtpass.getText());
	if (confirm==false) {
            check = false;
            Create_userregister.checkpass.setIcon(Singleton_userregister.cancel);
	}else{
            check = true;
            Create_userregister.checkpass.setIcon(Singleton_userregister.ok);
        }
                        
        return check;
    }
    
    /**
     * CHECK NAME
     * @return 
     */
    public static boolean name () {
        boolean check=true, confirm=false;
        
        confirm=validate.name(Create_userregister.txtname.getText());
	if (confirm==false) {
            check = false;
            Create_userregister.checkname.setIcon(Singleton_userregister.cancel);
	}else{
            check = true;
            Create_userregister.checkname.setIcon(Singleton_userregister.ok);
        }
        
        return check;
    }
    
    /**
     * CHECK SURNAME
     * @return 
     */
    public static boolean surname () {
        boolean check=true, confirm=false;
        
        confirm=validate.surname(Create_userregister.txtsurname.getText());
	if (confirm==false) {
            check = false;
            Create_userregister.checksurname.setIcon(Singleton_userregister.cancel);
	}else{
            check = true;
            Create_userregister.checksurname.setIcon(Singleton_userregister.ok);
        }
        
        return check;
    }
    
    /**
     * CHECK EMAIL
     * @return 
     */
    public static boolean email () {
        boolean check=true, confirm=false;
        
        confirm=validate.email(Create_userregister.txtemail.getText());
	if (confirm==false) {
            check = false;
            Create_userregister.checkemail.setIcon(Singleton_userregister.cancel);
	}else{
            check = true;
            Create_userregister.checkemail.setIcon(Singleton_userregister.ok);
        }
        
        return check;
    }
    
    /**
     * CHECK MOBILE TELEPHONE
     * @return 
     */
    public static boolean mobilephone () {
        boolean check=true, confirm=false;
        
        confirm=validate.mobilephone(Create_userregister.txtmobilephone.getText());
	if (confirm==false) {
            check = false;
            Create_userregister.checkmobilephone.setIcon(Singleton_userregister.cancel);
	}else{
            check = true;
            Create_userregister.checkmobilephone.setIcon(Singleton_userregister.ok);
        }
        return check;
    }
    
    /**
     * CHECK STATE
     * @return 
     */
    public static boolean state () {
        boolean check=true;
        
        if (Create_userregister.statetrue.isSelected()){
            check=true;
        }
        if (Create_userregister.statefalse.isSelected()){
            check=false;
        }
        
        return check;
    }
    
    /**
     * CHECK BIRTHDATE
     * @param year
     * @return 
     */
    public static boolean datebirthday (int year) {
        boolean check=true, confirm=false;
        ClassDate Cdate=null;
        String date="";
        int age;
        
        confirm = validate.date(((JTextFieldDateEditor) Create_userregister.txtdatebirthday.getDateEditor()).getText());
	if (confirm==false) {
            check = false;
            Create_userregister.checkdatebirthday.setIcon(Singleton_userregister.cancel);
	}else{
            Cdate = new ClassDate (((JTextFieldDateEditor) Create_userregister.txtdatebirthday.getDateEditor()).getText());
            confirm=Cdate.checkdate();
            if (confirm==false) {
                check = false;
                Create_userregister.checkdatebirthday.setIcon(Singleton_userregister.cancel);
            }else{
                age = Cdate.subtractsystemdateyear();
		if (age < year) {
                    check = false;
                    Create_userregister.checkdatebirthday.setIcon(Singleton_userregister.cancel);
		}else{
                    check = true;
                    Create_userregister.checkdatebirthday.setIcon(Singleton_userregister.ok);
                }
            }
	}
        
        return check;
    }
    
    /**
     * CHECK ACTIVITY
     * @return 
     */
    public static boolean activity () {
        boolean check=true;
        int i;
        
        try{
            if(Create_userregister.txtactivity.getText()==null){
                check = false;
                Create_userregister.checkactivity.setIcon(Singleton_userregister.cancel);
            }else {
                i=Integer.parseInt(Create_userregister.txtactivity.getText());
                check = true;
                Create_userregister.checkactivity.setIcon(Singleton_userregister.ok);
            }
        }catch(Exception e){
            check = false;
            Create_userregister.checkactivity.setIcon(Singleton_userregister.cancel);
        }
        
        return check;
    }
    
    /**
     * CHECK POINT
     * @return 
     */
    public static boolean point () {
        boolean check=true;
        int i;
        
        try{
            if(Create_userregister.txtpoint.getText()==null){
                check = false;
                Create_userregister.checkpoint.setIcon(Singleton_userregister.cancel);
            }else {
                i=Integer.parseInt(Create_userregister.txtpoint.getText());
                check = true;
                Create_userregister.checkpoint.setIcon(Singleton_userregister.ok);
            }
        }catch(Exception e){
            check = false;
            Create_userregister.checkpoint.setIcon(Singleton_userregister.cancel);
        }
        
        return check;
    }
        
        
            ////////////////////////////////////////////////////////////////////
            //                                                                //
            //                      UPDATE_USER_REGISTER                      //
            //                                                                //
            ////////////////////////////////////////////////////////////////////
  
    /**
     * GENERATE DATA OF THE USER
     */
    public static void generate_edit_userregister (){
        User_register user_register = BLL_userregister.IDuserregister();

        Update_userregister.txtDNI.setText(user_register.getDNI());
        Update_userregister.txtuser.setText(user_register.getUser());
        Update_userregister.txtpass.setText(user_register.getPass());
        Update_userregister.txtavatar.setText(user_register.getAvatar());
        if (user_register.getState()==true){
                Update_userregister.statetrue.setSelected(true);
        }else{
                Update_userregister.statefalse.setSelected(true);
        }
        Update_userregister.txtname.setText(user_register.getName());
        Update_userregister.txtsurname.setText(user_register.getSurname());
        Update_userregister.txtemail.setText(user_register.getEmail());
        Update_userregister.txtmobilephone.setText(user_register.getMobilephone());
        Update_userregister.txtdatebirthday.setCalendar(user_register.getDatebirthday().StringtoCalendar());
        Update_userregister.txtactivity.setText(user_register.getMovement()+"");
        Update_userregister.txtpoint.setText(user_register.getPoint()+"");
    }
    
    /**
     * CHECK AND CREATE DATA OF THE USER
     * @return 
     */
    public static User_register ask_user_register_update () {
		
        //Check
        boolean checkDNI, checkname, checksurname, checkmobilephone, checkemail,
                checkuser, checkpass, checkavatar, checkdatebirthday, checkstate,
                checkmovement, checkpoint;

        //User_register
        String DNI="", name="",surname="", mobilephone="", email="", user="", pass="", avatar="";
        ClassDate datebirthday=null;
        boolean state;
        int movement=0, point=0;
        User_register user_register=null;

        checkDNI=DNI_update();
        checkuser=user_update();
        checkpass=pass_update();
        //checkavatar=avatar_update();
        //checkstate=state_update();
        checkname=name_update();
        checksurname=surname_update();
        checkemail=email_update();
        checkmobilephone=mobilephone_update();
        checkdatebirthday=datebirthday_update(12);
        checkmovement=activity_update();
        checkpoint=point_update();

        if (checkDNI==true && checkname==true && checksurname==true && checkmobilephone==true &&
            checkemail==true && checkuser==true && checkpass==true && checkdatebirthday==true &&
            checkmovement==true && checkpoint==true){

            DNI=Update_userregister.txtDNI.getText();
            user=Update_userregister.txtuser.getText();
            pass=Update_userregister.txtpass.getText();
            avatar=Update_userregister.txtavatar.getText();
            state=state_update();
            name=Update_userregister.txtname.getText();
            surname=Update_userregister.txtsurname.getText();
            email=Update_userregister.txtemail.getText();
            mobilephone=Update_userregister.txtmobilephone.getText();
            datebirthday=new ClassDate (((JTextFieldDateEditor) Update_userregister.txtdatebirthday.getDateEditor()).getText());
            movement=Integer.parseInt(Update_userregister.txtactivity.getText());
            point=Integer.parseInt(Update_userregister.txtpoint.getText());

            user_register = new User_register(DNI, user, pass, avatar, state, name, surname,
                        email, mobilephone, datebirthday, movement, point);
        }

        return user_register;
    }
	
    /**
     * CREATE USER WITH DNI ONLY
     * @return 
     */
    public static User_register ask_user_registerDNI_update () {
        boolean checkDNI;
        String DNI;
        User_register user_register = null;
	
        checkDNI=DNI();
        if (checkDNI==true){
            user_register = new User_register (Update_userregister.txtDNI.getText());
        }
	
        return user_register;
    }
     
    /**
     * CHECK DNI
     * @return 
     */
    public static boolean DNI_update () {
        boolean check=true;
        
        String DNI = "", aux = "", caracteres = "TRWAGMYFPDXBNJZSQVHLCKET";
        boolean confirm;
        int number = 0, module = 0;
        char character = ' ', control = ' ';

        DNI=Update_userregister.txtDNI.getText();
        confirm=validate.DNI(DNI);
        if (confirm==false) {
            check = false;
            Update_userregister.checkDNI.setIcon(Singleton_userregister.cancel);
        }else{
            aux = "";
            for(int i=0; i<8; i++){
                aux += DNI.charAt(i);
            }	
            character = DNI.charAt(8);			
            number = Integer.parseInt(aux);
            module= number % 23;
            control = caracteres.charAt(module);
            if(control == character){
                check = true;
                Update_userregister.checkDNI.setIcon(Singleton_userregister.ok);
            }else{
                check = false;
                Update_userregister.checkDNI.setIcon(Singleton_userregister.cancel);
            }
        }
        
        return check;
    }
    
    /**
     * CHECK USER
     * @return 
     */
    public static boolean user_update () {
        boolean check=true, confirm=false;
        
	confirm=validate.user(Update_userregister.txtuser.getText());
	if (confirm==false) {
            check = false;
            Update_userregister.checkuser.setIcon(Singleton_userregister.cancel);
	}else{
            check = true;
            Update_userregister.checkuser.setIcon(Singleton_userregister.ok);
        }
        
        return check;
    }
    
    /**
     * CHECK PASSWORD
     * @return 
     */
    public static boolean pass_update () {
        boolean check=true, confirm=false;
		
	confirm=validate.pass(Update_userregister.txtpass.getText());
	if (confirm==false) {
            check = false;
            Update_userregister.checkpass.setIcon(Singleton_userregister.cancel);
	}else{
            check = true;
            Update_userregister.checkpass.setIcon(Singleton_userregister.ok);
        }
                        
        return check;
    }
    
    /**
     * CHECK NAME
     * @return 
     */
    public static boolean name_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.name(Update_userregister.txtname.getText());
	if (confirm==false) {
            check = false;
            Update_userregister.checkname.setIcon(Singleton_userregister.cancel);
	}else{
            check = true;
            Update_userregister.checkname.setIcon(Singleton_userregister.ok);
        }
        
        return check;
    }
    
    /**
     * CHECK SURNAME
     * @return 
     */
    public static boolean surname_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.surname(Update_userregister.txtsurname.getText());
	if (confirm==false) {
            check = false;
            Update_userregister.checksurname.setIcon(Singleton_userregister.cancel);
	}else{
            check = true;
            Update_userregister.checksurname.setIcon(Singleton_userregister.ok);
        }
        
        return check;
    }
    
    /**
     * CHECK EMAIL
     * @return 
     */
    public static boolean email_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.email(Update_userregister.txtemail.getText());
	if (confirm==false) {
            check = false;
            Update_userregister.checkemail.setIcon(Singleton_userregister.cancel);
	}else{
            check = true;
            Update_userregister.checkemail.setIcon(Singleton_userregister.ok);
        }
        
        return check;
    }
    
    /**
     * CHECK MOBILE TELEPHONE
     * @return 
     */
    public static boolean mobilephone_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.mobilephone(Update_userregister.txtmobilephone.getText());
	if (confirm==false) {
            check = false;
            Update_userregister.checkmobilephone.setIcon(Singleton_userregister.cancel);
	}else{
            check = true;
            Update_userregister.checkmobilephone.setIcon(Singleton_userregister.ok);
        }
        return check;
    }
    
    /**
     * CHECK STATE
     * @return 
     */
    public static boolean state_update () {
        boolean check=true;
        
        if (Update_userregister.statetrue.isSelected()){
            check=true;
        }
        if (Update_userregister.statefalse.isSelected()){
            check=false;
        }
        
        return check;
    }
    
    /**
     * CHECK BIRTHDATE
     * @param year
     * @return 
     */
    public static boolean datebirthday_update (int year) {
        boolean check=true, confirm=false;
        ClassDate Cdate=null;
        String date="";
        int age;
        
        confirm = validate.date(((JTextFieldDateEditor) Update_userregister.txtdatebirthday.getDateEditor()).getText());
	if (confirm==false) {
            check = false;
            Update_userregister.checkdatebirthday.setIcon(Singleton_userregister.cancel);
	}else{
            Cdate = new ClassDate (((JTextFieldDateEditor) Update_userregister.txtdatebirthday.getDateEditor()).getText());
            confirm=Cdate.checkdate();
            if (confirm==false) {
                check = false;
                Update_userregister.checkdatebirthday.setIcon(Singleton_userregister.cancel);
            }else{
                age = Cdate.subtractsystemdateyear();
		if (age < year) {
                    check = false;
                    Update_userregister.checkdatebirthday.setIcon(Singleton_userregister.cancel);
		}else{
                    check = true;
                    Update_userregister.checkdatebirthday.setIcon(Singleton_userregister.ok);
                }
            }
	}
        
        return check;
    }
    
    /**
     * CHECK ACTIVITY
     * @return 
     */
    public static boolean activity_update () {
        boolean check=true;
        int i;
        
        try{
            if(Update_userregister.txtactivity.getText()==null){
                check = false;
                Update_userregister.checkactivity.setIcon(Singleton_userregister.cancel);
            }else {
                i=Integer.parseInt(Update_userregister.txtactivity.getText());
                check = true;
                Update_userregister.checkactivity.setIcon(Singleton_userregister.ok);
            }
        }catch(Exception e){
            check = false;
            Update_userregister.checkactivity.setIcon(Singleton_userregister.cancel);
        }
        
        return check;
    }
    
    /**
     * CHECK POINT
     * @return 
     */
    public static boolean point_update () {
        boolean check=true;
        int i;
        
        try{
            if(Update_userregister.txtpoint.getText()==null){
                check = false;
                Update_userregister.checkpoint.setIcon(Singleton_userregister.cancel);
            }else {
                i=Integer.parseInt(Update_userregister.txtpoint.getText());
                check = true;
                Update_userregister.checkpoint.setIcon(Singleton_userregister.ok);
            }
        }catch(Exception e){
            check = false;
            Update_userregister.checkpoint.setIcon(Singleton_userregister.cancel);
        }
        
        return check;
    }
    
            ////////////////////////////////////////////////////////////////////
            //                                                                //
            //                        READ_USER_REGISTER                      //
            //                                                                //
            ////////////////////////////////////////////////////////////////////

    /**
     * GENERATE DATA OF THE USER
     */
    public static void generate_read_client () {
        User_register userregister = BLL_userregister.IDuserregister();
        
        Read_userregister.txtDNI.setText(Language_userregister.getInstance().getProperty("DNI")+": "+userregister.getDNI());
        Read_userregister.txtuser.setText(Language_userregister.getInstance().getProperty("user")+": "+userregister.getUser());
        Read_userregister.txtpass.setText(Language_userregister.getInstance().getProperty("password")+": "+userregister.getPass());
        Read_userregister.txtavatar.setText(Language_userregister.getInstance().getProperty("avatar")+": "+userregister.getAvatar());
        Read_userregister.txtstate.setText(Language_userregister.getInstance().getProperty("state")+": "+userregister.toStringstate());
        Read_userregister.txtname.setText(Language_userregister.getInstance().getProperty("name")+": "+userregister.getName());
        Read_userregister.txtsurname.setText(Language_userregister.getInstance().getProperty("surname")+": "+userregister.getSurname());
        Read_userregister.txtemail.setText(Language_userregister.getInstance().getProperty("email")+": "+userregister.getEmail());
        Read_userregister.txtmobilephone.setText(Language_userregister.getInstance().getProperty("mobilephone")+": "+userregister.getMobilephone());
        Read_userregister.txtdatebirthday.setText(Language_userregister.getInstance().getProperty("datebirthday")+": "+userregister.getDatebirthday().toString());
        Read_userregister.txtage.setText(Language_userregister.getInstance().getProperty("age")+": "+userregister.getAge());
        Read_userregister.txtbenefits.setText(Language_userregister.getInstance().getProperty("benefits")+": "+userregister.getBenefits());
        Read_userregister.txtmovement.setText(Language_userregister.getInstance().getProperty("activity")+": "+userregister.getMovement());
        Read_userregister.txtreputation.setText(Language_userregister.getInstance().getProperty("reputation")+": "+userregister.calc_reputation());
        Read_userregister.txtpoint.setText(Language_userregister.getInstance().getProperty("point")+": "+userregister.getPoint());
        
    }
}
