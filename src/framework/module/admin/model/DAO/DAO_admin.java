/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.admin.model.DAO;

import com.toedter.calendar.JTextFieldDateEditor;
import framework.classes.ClassDate;
import framework.functions.validate;
import framework.module.admin.model.BLL.BLL_admin.BLL_admin;
import framework.module.admin.model.classes.Admin;
import framework.module.admin.model.classes.Singleton_admin;
import framework.module.admin.view.Create_admin;
import framework.module.admin.view.Update_admin;

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
    
    public static ClassDate plusage (ClassDate param1, int param2) {
	
	return new ClassDate (param1.getDay(), param1.getMonth(), (param1.getYear()+param2));
	
    }
    
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
                    Create_admin.checkDNI.setIcon(Singleton_admin.cancel);
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
                        Create_admin.checkDNI.setIcon(Singleton_admin.ok);
                    }else{
                        check = false;
                        Create_admin.checkDNI.setIcon(Singleton_admin.cancel);
                    }
                }
        
        return check;
    }
    
    public static boolean user () {
        boolean check=true, confirm=false;
        
	confirm=validate.user(Create_admin.txtuser.getText());
	if (confirm==false) {
            check = false;
            Create_admin.checkuser.setIcon(Singleton_admin.cancel);
	}else{
            check = true;
            Create_admin.checkuser.setIcon(Singleton_admin.ok);
        }
        
        return check;
    }
    
    public static boolean pass () {
        boolean check=true, confirm=false;
		
	confirm=validate.pass(Create_admin.txtpass.getText());
	if (confirm==false) {
            check = false;
            Create_admin.checkpass.setIcon(Singleton_admin.cancel);
	}else{
            check = true;
            Create_admin.checkpass.setIcon(Singleton_admin.ok);
        }
                        
        return check;
    }
    
    public static boolean name () {
        boolean check=true, confirm=false;
        
        confirm=validate.name(Create_admin.txtname.getText());
	if (confirm==false) {
            check = false;
            Create_admin.checkname.setIcon(Singleton_admin.cancel);
	}else{
            check = true;
            Create_admin.checkname.setIcon(Singleton_admin.ok);
        }
        
        return check;
    }
    
    public static boolean surname () {
        boolean check=true, confirm=false;
        
        confirm=validate.surname(Create_admin.txtsurname.getText());
	if (confirm==false) {
            check = false;
            Create_admin.checksurname.setIcon(Singleton_admin.cancel);
	}else{
            check = true;
            Create_admin.checksurname.setIcon(Singleton_admin.ok);
        }
        
        return check;
    }
    
    public static boolean email () {
        boolean check=true, confirm=false;
        
        confirm=validate.email(Create_admin.txtemail.getText());
	if (confirm==false) {
            check = false;
            Create_admin.checkemail.setIcon(Singleton_admin.cancel);
	}else{
            check = true;
            Create_admin.checkemail.setIcon(Singleton_admin.ok);
        }
        
        return check;
    }
    
    public static boolean mobilephone () {
        boolean check=true, confirm=false;
        
        confirm=validate.mobilephone(Create_admin.txtmobilephone.getText());
	if (confirm==false) {
            check = false;
            Create_admin.checkmobilephone.setIcon(Singleton_admin.cancel);
	}else{
            check = true;
            Create_admin.checkmobilephone.setIcon(Singleton_admin.ok);
        }
        return check;
    }
    
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
    
    public static boolean datebirthday (int year) {
        boolean check=true, confirm=false;
        ClassDate Cdate=null;
        String date="";
        int age;
        
        confirm = validate.date(((JTextFieldDateEditor) Create_admin.txtdatebirthday.getDateEditor()).getText());
	if (confirm==false) {
            check = false;
            Create_admin.checkdatebirthday.setIcon(Singleton_admin.cancel);
	}else{
            Cdate = new ClassDate (((JTextFieldDateEditor) Create_admin.txtdatebirthday.getDateEditor()).getText());
            confirm=Cdate.checkdate();
            if (confirm==false) {
                check = false;
                Create_admin.checkdatebirthday.setIcon(Singleton_admin.cancel);
            }else{
                age = Cdate.subtractsystemdateyear();
		if (age < year) {
                    check = false;
                    Create_admin.checkdatebirthday.setIcon(Singleton_admin.cancel);
		}else{
                    check = true;
                    Create_admin.checkdatebirthday.setIcon(Singleton_admin.ok);
                }
            }
	}
        
        return check;
    }
    
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
            Create_admin.checkdatecontract.setIcon(Singleton_admin.cancel);
	}else{
            Cdate = new ClassDate (((JTextFieldDateEditor) Create_admin.txtdatecontract.getDateEditor()).getText());
            confirm=Cdate.checkdate();
            if (confirm==false) {
                check = false;
                Create_admin.checkdatecontract.setIcon(Singleton_admin.cancel);
            }else{
                datebirthday = new ClassDate (((JTextFieldDateEditor) Create_admin.txtdatebirthday.getDateEditor()).getText());
                years1 = plusage (datebirthday, age1);
                years2 = plusage (datebirthday, age2);
                i1 = years1.comparedate(Cdate);
                i2 = Cdate.comparesystemdate();
                i3 = years2.comparedate(Cdate);
                if (i1 == 1) {
                    check = false;
                    Create_admin.checkdatecontract.setIcon(Singleton_admin.cancel);
                }else{
                    if (i2 == 1) {
                        check = false;
                        Create_admin.checkdatecontract.setIcon(Singleton_admin.cancel);
                    }else{
                        if (i3 == -1) {
                            check = false;
                            Create_admin.checkdatecontract.setIcon(Singleton_admin.cancel);
                        }else{
                            check = true;
                            Create_admin.checkdatecontract.setIcon(Singleton_admin.ok);
                        }
                    }
                }
            }
        }
        
        return check;
    }
    
    public static boolean salary () {
        boolean check=true;
        float i;
        
        try{
            if(Create_admin.txtsalary.getText()==null){
                check = false;
                Create_admin.checksalary.setIcon(Singleton_admin.cancel);
            }else {
                i=Float.parseFloat(Create_admin.txtsalary.getText());
                check = true;
                Create_admin.checksalary.setIcon(Singleton_admin.ok);
            }
        }catch(Exception e){
            check = false;
            Create_admin.checksalary.setIcon(Singleton_admin.cancel);
        }
        
        return check;
    }
    
    public static boolean incentive () {
        boolean check=true;
        float i;
        
        try{
            if(Create_admin.txtincentive.getText()==null){
                check = false;
                Create_admin.checkincentive.setIcon(Singleton_admin.cancel);
            }else {
                i=Float.parseFloat(Create_admin.txtincentive.getText());
                check = true;
                Create_admin.checkincentive.setIcon(Singleton_admin.ok);
            }
        }catch(Exception e){
            check = false;
            Create_admin.checkincentive.setIcon(Singleton_admin.cancel);
        }
        
        return check;
    }
    
    public static boolean activity () {
        boolean check=true;
        int i;
        
        try{
            if(Create_admin.txtactivity.getText()==null){
                check = false;
                Create_admin.checkactivity.setIcon(Singleton_admin.cancel);
            }else {
                i=Integer.parseInt(Create_admin.txtactivity.getText());
                check = true;
                Create_admin.checkactivity.setIcon(Singleton_admin.ok);
            }
        }catch(Exception e){
            check = false;
            Create_admin.checkactivity.setIcon(Singleton_admin.cancel);
        }
        
        return check;
    }
    
            ////////////////////////////////////////////////////////////////////
            //                                                                //
            //                          UPDATE_ADMIN                          //
            //                                                                //
            ////////////////////////////////////////////////////////////////////
    
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
    
    public static ClassDate plusage_update (ClassDate param1, int param2) {
	
	return new ClassDate (param1.getDay(), param1.getMonth(), (param1.getYear()+param2));
	
    }
    
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
                    Update_admin.checkDNI.setIcon(Singleton_admin.cancel);
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
                        Update_admin.checkDNI.setIcon(Singleton_admin.ok);
                    }else{
                        check = false;
                        Update_admin.checkDNI.setIcon(Singleton_admin.cancel);
                    }
                }
        
        return check;
    }
    
    public static boolean user_update () {
        boolean check=true, confirm=false;
        
	confirm=validate.user(Update_admin.txtuser.getText());
	if (confirm==false) {
            check = false;
            Update_admin.checkuser.setIcon(Singleton_admin.cancel);
	}else{
            check = true;
            Update_admin.checkuser.setIcon(Singleton_admin.ok);
        }
        
        return check;
    }
    
    public static boolean pass_update () {
        boolean check=true, confirm=false;
		
	confirm=validate.pass(Update_admin.txtpass.getText());
	if (confirm==false) {
            check = false;
            Update_admin.checkpass.setIcon(Singleton_admin.cancel);
	}else{
            check = true;
            Update_admin.checkpass.setIcon(Singleton_admin.ok);
        }
                        
        return check;
    }
    
    public static boolean name_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.name(Update_admin.txtname.getText());
	if (confirm==false) {
            check = false;
            Update_admin.checkname.setIcon(Singleton_admin.cancel);
	}else{
            check = true;
            Update_admin.checkname.setIcon(Singleton_admin.ok);
        }
        
        return check;
    }
    
    public static boolean surname_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.surname(Update_admin.txtsurname.getText());
	if (confirm==false) {
            check = false;
            Update_admin.checksurname.setIcon(Singleton_admin.cancel);
	}else{
            check = true;
            Update_admin.checksurname.setIcon(Singleton_admin.ok);
        }
        
        return check;
    }
    
    public static boolean email_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.email(Update_admin.txtemail.getText());
	if (confirm==false) {
            check = false;
            Update_admin.checkemail.setIcon(Singleton_admin.cancel);
	}else{
            check = true;
            Update_admin.checkemail.setIcon(Singleton_admin.ok);
        }
        
        return check;
    }
    
    public static boolean mobilephone_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.mobilephone(Update_admin.txtmobilephone.getText());
	if (confirm==false) {
            check = false;
            Update_admin.checkmobilephone.setIcon(Singleton_admin.cancel);
	}else{
            check = true;
            Update_admin.checkmobilephone.setIcon(Singleton_admin.ok);
        }
        return check;
    }
    
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
    
    public static boolean datebirthday_update (int year) {
        boolean check=true, confirm=false;
        ClassDate Cdate=null;
        String date="";
        int age;
        
        confirm = validate.date(((JTextFieldDateEditor) Update_admin.txtdatebirthday.getDateEditor()).getText());
	if (confirm==false) {
            check = false;
            Update_admin.checkdatebirthday.setIcon(Singleton_admin.cancel);
	}else{
            Cdate = new ClassDate (((JTextFieldDateEditor) Update_admin.txtdatebirthday.getDateEditor()).getText());
            confirm=Cdate.checkdate();
            if (confirm==false) {
                check = false;
                Update_admin.checkdatebirthday.setIcon(Singleton_admin.cancel);
            }else{
                age = Cdate.subtractsystemdateyear();
		if (age < year) {
                    check = false;
                    Update_admin.checkdatebirthday.setIcon(Singleton_admin.cancel);
		}else{
                    check = true;
                    Update_admin.checkdatebirthday.setIcon(Singleton_admin.ok);
                }
            }
	}
        
        return check;
    }
    
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
            Update_admin.checkdatecontract.setIcon(Singleton_admin.cancel);
	}else{
            Cdate = new ClassDate (((JTextFieldDateEditor) Update_admin.txtdatecontract.getDateEditor()).getText());
            confirm=Cdate.checkdate();
            if (confirm==false) {
                check = false;
                Update_admin.checkdatecontract.setIcon(Singleton_admin.cancel);
            }else{
                datebirthday = new ClassDate (((JTextFieldDateEditor) Update_admin.txtdatebirthday.getDateEditor()).getText());
                years1 = plusage_update (datebirthday, age1);
                years2 = plusage_update (datebirthday, age2);
                i1 = years1.comparedate(Cdate);
                i2 = Cdate.comparesystemdate();
                i3 = years2.comparedate(Cdate);
                if (i1 == 1) {
                    check = false;
                    Update_admin.checkdatecontract.setIcon(Singleton_admin.cancel);
                }else{
                    if (i2 == 1) {
                        check = false;
                        Update_admin.checkdatecontract.setIcon(Singleton_admin.cancel);
                    }else{
                        if (i3 == -1) {
                            check = false;
                            Update_admin.checkdatecontract.setIcon(Singleton_admin.cancel);
                        }else{
                            check = true;
                            Update_admin.checkdatecontract.setIcon(Singleton_admin.ok);
                        }
                    }
                }
            }
        }
        
        return check;
    }
    
    public static boolean salary_update () {
        boolean check=true;
        float i;
        
        try{
            if(Update_admin.txtsalary.getText()==null){
                check = false;
                Update_admin.checksalary.setIcon(Singleton_admin.cancel);
            }else {
                i=Float.parseFloat(Update_admin.txtsalary.getText());
                check = true;
                Update_admin.checksalary.setIcon(Singleton_admin.ok);
            }
        }catch(Exception e){
            check = false;
            Update_admin.checksalary.setIcon(Singleton_admin.cancel);
        }
        
        return check;
    }
    
    public static boolean incentive_update () {
        boolean check=true;
        float i;
        
        try{
            if(Update_admin.txtincentive.getText()==null){
                check = false;
                Update_admin.checkincentive.setIcon(Singleton_admin.cancel);
            }else {
                i=Float.parseFloat(Update_admin.txtincentive.getText());
                check = true;
                Update_admin.checkincentive.setIcon(Singleton_admin.ok);
            }
        }catch(Exception e){
            check = false;
            Update_admin.checkincentive.setIcon(Singleton_admin.cancel);
        }
        
        return check;
    }
    
    public static boolean activity_update () {
        boolean check=true;
        int i;
        
        try{
            if(Update_admin.txtactivity.getText()==null){
                check = false;
                Update_admin.checkactivity.setIcon(Singleton_admin.cancel);
            }else {
                i=Integer.parseInt(Update_admin.txtactivity.getText());
                check = true;
                Update_admin.checkactivity.setIcon(Singleton_admin.ok);
            }
        }catch(Exception e){
            check = false;
            Update_admin.checkactivity.setIcon(Singleton_admin.cancel);
        }
        
        return check;
    }
}
