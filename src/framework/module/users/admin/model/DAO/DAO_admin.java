/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.users.admin.model.DAO;

import com.toedter.calendar.JTextFieldDateEditor;
import framework.classes.ClassDate;
import framework.functions.validate;
import framework.module.users.admin.model.BLL.BLL_admin.BLL_admin;
import framework.module.users.admin.model.classes.Admin;
import framework.module.users.admin.model.classes.language.Language_admin;
import framework.module.users.admin.view.Create_admin;
import framework.module.users.admin.view.Read_admin;
import framework.module.users.admin.view.Update_admin;

/**
 *
 * @author angel
 */
public class DAO_admin {

            ////////////////////////////////////////////////////////////////////
            //                                                                //
            //                          CREATE_ADMIN                          //
            //                                                                //
            ////////////////////////////////////////////////////////////////////
    
    /**
     * CHECK AND CREATE DATA OF THE USER
     * @return 
     */
    public static Admin ask_admin () {
		
	//Check
	boolean checkDNI, checkname, checksurname, checkmobilephone, checkemail,
                checkuser, checkpass, checkavatar, checkdatebirthday, checkstate,
                checkdatecontract, checkactivity, checksalary, checkincentive;
        
        //Admin
        String DNI, user, pass, avatar, name, surname, email, mobilephone;
        boolean state;
        ClassDate datebirthday, datecontract;
        float salary, incentive;
        int activity;
        Admin admin = null;
	
	checkDNI=DNI();
	checkuser=user();
	checkpass=pass();
	//checkavatar=validaString();
	//checkstate=state();
	checkname=name();
	checksurname=surname();
	checkemail=email();
	checkmobilephone=mobilephone();
	checkdatebirthday=datebirthday(16);
	checkdatecontract=datecontract(16, 65);
	checksalary=salary();
	checkincentive=incentive();
	checkactivity=activity();
        
        if (checkDNI==true && checkname==true && checksurname==true && checkmobilephone==true &&
            checkemail==true && checkuser==true && checkpass==true && checkdatebirthday==true &&
            checkdatecontract==true && checkactivity==true && checksalary==true && checkincentive==true){
            DNI=Create_admin.txtDNI.getText();
            user=Create_admin.txtuser.getText();
            pass=Create_admin.txtpass.getText();
            avatar=Create_admin.txtavatar.getText();
            state=state();
            name=Create_admin.txtname.getText();
            surname=Create_admin.txtsurname.getText();
            email=Create_admin.txtemail.getText();
            mobilephone=Create_admin.txtmobilephone.getText();
            datebirthday=new ClassDate(((JTextFieldDateEditor) Create_admin.txtdatebirthday.getDateEditor()).getText());
            datecontract=new ClassDate(((JTextFieldDateEditor) Create_admin.txtdatecontract.getDateEditor()).getText());
            salary=Float.parseFloat(Create_admin.txtsalary.getText());
            incentive=Float.parseFloat(Create_admin.txtincentive.getText());
            activity=Integer.parseInt(Create_admin.txtactivity.getText());
            
            admin = new Admin (DNI, user, pass, avatar, state, name, surname, email, mobilephone,
            datebirthday, datecontract, salary, incentive, activity);
        }
        
        return admin;
    }
    
    /**
     * CREATE USER WIHT DNI ONLY
     * @return 
     */
    public static Admin ask_adminDNI () {
	boolean checkDNI;
        String DNI;
        Admin admin=null;
        
        checkDNI=DNI();
        if (checkDNI==true){
            admin = new Admin (Create_admin.txtDNI.getText());
        }
        
        return admin;
    }
    
    /**
     * CALCULATE DATE
     * @param param1
     * @param param2
     * @return 
     */
    public static ClassDate plusage (ClassDate param1, int param2) {
	
	return new ClassDate (param1.getDay(), param1.getMonth(), (param1.getYear()+param2));
	
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
		
		DNI=Create_admin.txtDNI.getText();
		confirm=validate.DNI(DNI);
		if (confirm==false) {
                    check = false;
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
                    }else{
                        check = false;
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
        
	confirm=validate.user(Create_admin.txtuser.getText());
	if (confirm==false) {
            check = false;
	}else{
            check = true;
        }
        
        return check;
    }
    
    /**
     * CHECK PASSWORD
     * @return 
     */
    public static boolean pass () {
        boolean check=true, confirm=false;
		
	confirm=validate.pass(Create_admin.txtpass.getText());
	if (confirm==false) {
            check = false;
	}else{
            check = true;
        }
                        
        return check;
    }
    
    /**
     * CHECK NAME
     * @return 
     */
    public static boolean name () {
        boolean check=true, confirm=false;
        
        confirm=validate.name(Create_admin.txtname.getText());
	if (confirm==false) {
            check = false;
	}else{
            check = true;
        }
        
        return check;
    }
    
    /**
     * CHECK SURNAME
     * @return 
     */
    public static boolean surname () {
        boolean check=true, confirm=false;
        
        confirm=validate.surname(Create_admin.txtsurname.getText());
	if (confirm==false) {
            check = false;
	}else{
            check = true;
        }
        
        return check;
    }
    
    /**
     * CHECK EMAIL
     * @return 
     */
    public static boolean email () {
        boolean check=true, confirm=false;
        
        confirm=validate.email(Create_admin.txtemail.getText());
	if (confirm==false) {
            check = false;
	}else{
            check = true;
        }
        
        return check;
    }
    
    /**
     * CHECK MOBILE TELEPHONE
     * @return 
     */
    public static boolean mobilephone () {
        boolean check=true, confirm=false;
        
        confirm=validate.mobilephone(Create_admin.txtmobilephone.getText());
	if (confirm==false) {
            check = false;
	}else{
            check = true;
        }
        return check;
    }
    
    /**
     * CHECK STATE
     * @return 
     */
    public static boolean state () {
        boolean check=true;
        
        if (Create_admin.statetrue.isSelected()){
            check=true;
        }
        if (Create_admin.statefalse.isSelected()){
            check=false;
        }
        
        return check;
    }
    
    /**
     * CHACK BIRTHDATE
     * @param year
     * @return 
     */
    public static boolean datebirthday (int year) {
        boolean check=true, confirm=false;
        ClassDate Cdate=null;
        String date="";
        int age;
        
        confirm = validate.date(((JTextFieldDateEditor) Create_admin.txtdatebirthday.getDateEditor()).getText());
	if (confirm==false) {
            check = false;
	}else{
            Cdate = new ClassDate (((JTextFieldDateEditor) Create_admin.txtdatebirthday.getDateEditor()).getText());
            confirm=Cdate.checkdate();
            if (confirm==false) {
                check = false;
            }else{
                age = Cdate.subtractsystemdateyear();
		if (age < year) {
                    check = false;
		}else{
                    check = true;
                }
            }
	}
        
        return check;
    }
    
    /**
     * CHECK DATECONTRACT
     * @param age1
     * @param age2
     * @return 
     */
    public static boolean datecontract (int age1, int age2) {
        boolean check=true, confirm=false;
        ClassDate Cdate = null;
	ClassDate years1 = null;
	ClassDate years2 = null;
        ClassDate datebirthday = null;
        int i1 = 0, i2= 0, i3=0;
        
        confirm = validate.date(((JTextFieldDateEditor) Create_admin.txtdatecontract.getDateEditor()).getText());
	if (confirm==false) {
            check = false;
	}else{
            Cdate = new ClassDate (((JTextFieldDateEditor) Create_admin.txtdatecontract.getDateEditor()).getText());
            confirm=Cdate.checkdate();
            if (confirm==false) {
                check = false;
            }else{
                datebirthday = new ClassDate (((JTextFieldDateEditor) Create_admin.txtdatebirthday.getDateEditor()).getText());
                years1 = plusage (datebirthday, age1);
                years2 = plusage (datebirthday, age2);
                i1 = years1.comparedate(Cdate);
                i2 = Cdate.comparesystemdate();
                i3 = years2.comparedate(Cdate);
                if (i1 == 1) {
                    check = false;
                }else{
                    if (i2 == 1) {
                        check = false;
                    }else{
                        if (i3 == -1) {
                            check = false;
                        }else{
                            check = true;
                        }
                    }
                }
            }
        }
        
        return check;
    }
    
    /**
     * CHECK SALARY
     * @return 
     */
    public static boolean salary () {
        boolean check=true;
        float i;
        
        try{
            if(Create_admin.txtsalary.getText()==null){
                check = false;
            }else {
                i=Float.parseFloat(Create_admin.txtsalary.getText());
                check = true;
            }
        }catch(Exception e){
            check = false;
        }
        
        return check;
    }
    
    /**
     * CHECK INCENTIVE
     * @return 
     */
    public static boolean incentive () {
        boolean check=true;
        float i;
        
        try{
            if(Create_admin.txtincentive.getText()==null){
                check = false;
            }else {
                i=Float.parseFloat(Create_admin.txtincentive.getText());
                check = true;
            }
        }catch(Exception e){
            check = false;
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
            if(Create_admin.txtactivity.getText()==null){
                check = false;
            }else {
                i=Integer.parseInt(Create_admin.txtactivity.getText());
                check = true;
            }
        }catch(Exception e){
            check = false;
        }
        
        return check;
    }
    
            ////////////////////////////////////////////////////////////////////
            //                                                                //
            //                          UPDATE_ADMIN                          //
            //                                                                //
            ////////////////////////////////////////////////////////////////////
    
    /**
     * GENERATE DATA OF THE USER
     */
    public static void generate_edit_admin (){
        Admin admin = BLL_admin.IDadmin();
        
        Update_admin.txtDNI.setText(admin.getDNI());
        Update_admin.txtuser.setText(admin.getUser());
        Update_admin.txtpass.setText(admin.getPass());
        Update_admin.txtavatar.setText(admin.getAvatar());
        if (admin.getState()==true){
        Update_admin.statetrue.setSelected(true);
        }else{
        Update_admin.statefalse.setSelected(true);
        }
        Update_admin.txtname.setText(admin.getName());
        Update_admin.txtsurname.setText(admin.getSurname());
        Update_admin.txtemail.setText(admin.getEmail());
        Update_admin.txtmobilephone.setText(admin.getMobilephone());
        Update_admin.txtdatebirthday.setCalendar(admin.getDatebirthday().StringtoCalendar());
        Update_admin.txtdatecontract.setCalendar(admin.getDatecontract().StringtoCalendar());
        Update_admin.txtsalary.setText(admin.getSalary()+"");
        Update_admin.txtincentive.setText(admin.getIncentive()+"");
        Update_admin.txtactivity.setText(admin.getActivity()+"");
    }
    
    /**
     * CHECK AND CREATE DATA OF DE USER
     * @return 
     */
    public static Admin ask_admin_update () {
		
	//Check
	boolean checkDNI, checkname, checksurname, checkmobilephone, checkemail,
            checkuser, checkpass, checkavatar, checkdatebirthday, checkstate,
            checkdatecontract, checkactivity, checksalary, checkincentive;
        
        //Admin
        String DNI, user, pass, avatar, name, surname, email, mobilephone;
        boolean state;
        ClassDate datebirthday, datecontract;
        float salary, incentive;
        int activity;
        Admin admin = null;
	
	checkDNI=DNI_update();
	checkuser=user_update();
	checkpass=pass_update();
	//checkavatar=validaString_update();
	//checkstate=state_update();
	checkname=name_update();
	checksurname=surname_update();
	checkemail=email_update();
	checkmobilephone=mobilephone_update();
	checkdatebirthday=datebirthday_update(16);
	checkdatecontract=datecontract_update(16, 65);
	checksalary=salary_update();
	checkincentive=incentive_update();
	checkactivity=activity_update();
        
        if (checkDNI==true && checkname==true && checksurname==true && checkmobilephone==true &&
            checkemail==true && checkuser==true && checkpass==true && checkdatebirthday==true &&
            checkdatecontract==true && checkactivity==true && checksalary==true && checkincentive==true){
            DNI=Update_admin.txtDNI.getText();
            user=Update_admin.txtuser.getText();
            pass=Update_admin.txtpass.getText();
            avatar=Update_admin.txtavatar.getText();
            state=state_update();
            name=Update_admin.txtname.getText();
            surname=Update_admin.txtsurname.getText();
            email=Update_admin.txtemail.getText();
            mobilephone=Update_admin.txtmobilephone.getText();
            datebirthday=new ClassDate(((JTextFieldDateEditor) Update_admin.txtdatebirthday.getDateEditor()).getText());
            datecontract=new ClassDate(((JTextFieldDateEditor) Update_admin.txtdatecontract.getDateEditor()).getText());
            salary=Float.parseFloat(Update_admin.txtsalary.getText());
            incentive=Float.parseFloat(Update_admin.txtincentive.getText());
            activity=Integer.parseInt(Update_admin.txtactivity.getText());
            
            admin = new Admin (DNI, user, pass, avatar, state, name, surname, email, mobilephone,
            datebirthday, datecontract, salary, incentive, activity);
        }
        
        return admin;
    }
    
    /**
     * CREATE USER WITH DNI ONLY
     * @return 
     */
    public static Admin ask_adminDNI_update () {
	boolean checkDNI;
        String DNI;
        Admin admin=null;
        
        checkDNI=DNI_update();
        if (checkDNI==true){
            admin = new Admin (Update_admin.txtDNI.getText());
        }
        
        return admin;
    }
    
    /**
     * CALCULATE DATE
     * @param param1
     * @param param2
     * @return 
     */
    public static ClassDate plusage_update (ClassDate param1, int param2) {
	
	return new ClassDate (param1.getDay(), param1.getMonth(), (param1.getYear()+param2));
	
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
		
		DNI=Update_admin.txtDNI.getText();
		confirm=validate.DNI(DNI);
		if (confirm==false) {
                    check = false;
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
                    }else{
                        check = false;
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
        
	confirm=validate.user(Update_admin.txtuser.getText());
	if (confirm==false) {
            check = false;
	}else{
            check = true;
        }
        
        return check;
    }
    
    /**
     * CHECK PASSWORD
     * @return 
     */
    public static boolean pass_update () {
        boolean check=true, confirm=false;
		
	confirm=validate.pass(Update_admin.txtpass.getText());
	if (confirm==false) {
            check = false;
	}else{
            check = true;
        }
                        
        return check;
    }
    
    /**
     * CHECK NAME
     * @return 
     */
    public static boolean name_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.name(Update_admin.txtname.getText());
	if (confirm==false) {
            check = false;
	}else{
            check = true;
        }
        
        return check;
    }
    
    /**
     * CHECK SURNAME
     * @return 
     */
    public static boolean surname_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.surname(Update_admin.txtsurname.getText());
	if (confirm==false) {
            check = false;
	}else{
            check = true;
        }
        
        return check;
    }
    
    /**
     * CHECK EMAIL
     * @return 
     */
    public static boolean email_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.email(Update_admin.txtemail.getText());
	if (confirm==false) {
            check = false;
	}else{
            check = true;
        }
        
        return check;
    }
    
    /**
     * CHECK MOBILE TELEPHONE
     * @return 
     */
    public static boolean mobilephone_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.mobilephone(Update_admin.txtmobilephone.getText());
	if (confirm==false) {
            check = false;
	}else{
            check = true;
        }
        return check;
    }
    
    /**
     * CHECK STATE
     * @return 
     */
    public static boolean state_update () {
        boolean check=true;
        
        if (Update_admin.statetrue.isSelected()){
            check=true;
        }
        if (Update_admin.statefalse.isSelected()){
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
        
        confirm = validate.date(((JTextFieldDateEditor) Update_admin.txtdatebirthday.getDateEditor()).getText());
	if (confirm==false) {
            check = false;
	}else{
            Cdate = new ClassDate (((JTextFieldDateEditor) Update_admin.txtdatebirthday.getDateEditor()).getText());
            confirm=Cdate.checkdate();
            if (confirm==false) {
                check = false;
            }else{
                age = Cdate.subtractsystemdateyear();
		if (age < year) {
                    check = false;
		}else{
                    check = true;
                }
            }
	}
        
        return check;
    }
    
    /**
     * CHECK DATE CONTRACT
     * @param age1
     * @param age2
     * @return 
     */
    public static boolean datecontract_update (int age1, int age2) {
        boolean check=true, confirm=false;
        ClassDate Cdate = null;
	ClassDate years1 = null;
	ClassDate years2 = null;
        ClassDate datebirthday = null;
        int i1 = 0, i2= 0, i3=0;
        
        confirm = validate.date(((JTextFieldDateEditor) Update_admin.txtdatecontract.getDateEditor()).getText());
	if (confirm==false) {
            check = false;
	}else{
            Cdate = new ClassDate (((JTextFieldDateEditor) Update_admin.txtdatecontract.getDateEditor()).getText());
            confirm=Cdate.checkdate();
            if (confirm==false) {
                check = false;
            }else{
                datebirthday = new ClassDate (((JTextFieldDateEditor) Update_admin.txtdatebirthday.getDateEditor()).getText());
                years1 = plusage_update (datebirthday, age1);
                years2 = plusage_update (datebirthday, age2);
                i1 = years1.comparedate(Cdate);
                i2 = Cdate.comparesystemdate();
                i3 = years2.comparedate(Cdate);
                if (i1 == 1) {
                    check = false;
                }else{
                    if (i2 == 1) {
                        check = false;
                    }else{
                        if (i3 == -1) {
                            check = false;
                        }else{
                            check = true;
                        }
                    }
                }
            }
        }
        
        return check;
    }
    
    /**
     * CHECK SALARY
     * @return 
     */
    public static boolean salary_update () {
        boolean check=true;
        float i;
        
        try{
            if(Update_admin.txtsalary.getText()==null){
                check = false;
            }else {
                i=Float.parseFloat(Update_admin.txtsalary.getText());
                check = true;
            }
        }catch(Exception e){
            check = false;
        }
        
        return check;
    }
    
    /**
     * CHECK INCENTIVE
     * @return 
     */
    public static boolean incentive_update () {
        boolean check=true;
        float i;
        
        try{
            if(Update_admin.txtincentive.getText()==null){
                check = false;
            }else {
                i=Float.parseFloat(Update_admin.txtincentive.getText());
                check = true;
            }
        }catch(Exception e){
            check = false;
        }
        
        return check;
    }
    
    /**
     * CEHCK ACTIVITY
     * @return 
     */
    public static boolean activity_update () {
        boolean check=true;
        int i;
        
        try{
            if(Update_admin.txtactivity.getText()==null){
                check = false;
            }else {
                i=Integer.parseInt(Update_admin.txtactivity.getText());
                check = true;
            }
        }catch(Exception e){
            check = false;
        }
        
        return check;
    }
        
            ////////////////////////////////////////////////////////////////////
            //                                                                //
            //                            READ_ADMIN                          //
            //                                                                //
            ////////////////////////////////////////////////////////////////////

    /**
     * GENERATE DATA OF THE USER
     */
    public static void generate_read_admin () {
        Admin admin = BLL_admin.IDadmin();
        
        Read_admin.txtDNI.setText(Language_admin.getInstance().getProperty("DNI")+": "+admin.getDNI());
        Read_admin.txtuser.setText(Language_admin.getInstance().getProperty("user")+": "+admin.getUser());
        Read_admin.txtpass.setText(Language_admin.getInstance().getProperty("password")+": "+admin.getPass());
        Read_admin.txtavatar.setText(Language_admin.getInstance().getProperty("avatar")+": "+admin.getAvatar());
        Read_admin.txtstate.setText(Language_admin.getInstance().getProperty("state")+": "+admin.toStringstate());
        Read_admin.txtname.setText(Language_admin.getInstance().getProperty("name")+": "+admin.getName());
        Read_admin.txtsurname.setText(Language_admin.getInstance().getProperty("surname")+": "+admin.getSurname());
        Read_admin.txtemail.setText(Language_admin.getInstance().getProperty("email")+": "+admin.getEmail());
        Read_admin.txtmobilephone.setText(Language_admin.getInstance().getProperty("mobilephone")+": "+admin.getMobilephone());
        Read_admin.txtdatebirthday.setText(Language_admin.getInstance().getProperty("datebirthday")+": "+admin.getDatebirthday().toString());
        Read_admin.txtage.setText(Language_admin.getInstance().getProperty("age")+": "+admin.getAge());
        Read_admin.txtbenefits.setText(Language_admin.getInstance().getProperty("benefits")+": "+admin.getBenefits());
        Read_admin.txtdatecontract.setText(Language_admin.getInstance().getProperty("datecontract")+": "+admin.getDatecontract().toString());
        Read_admin.txtold.setText(Language_admin.getInstance().getProperty("old")+": "+admin.getOld()+"");
        Read_admin.txtsalary.setText(Language_admin.getInstance().getProperty("salary")+": "+admin.getSalary()+"");
        Read_admin.txtincentive.setText(Language_admin.getInstance().getProperty("incentive")+": "+admin.getIncentive()+"");
        Read_admin.txtactivity.setText(Language_admin.getInstance().getProperty("activity")+": "+admin.getActivity()+"");
        
    }
}
