/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.client.model.DAO;

import com.toedter.calendar.JTextFieldDateEditor;
import framework.classes.ClassDate;
import framework.functions.validate;
import framework.module.client.model.BLL.BLL_client.BLL_client;
import framework.module.client.model.classes.Client;
import framework.module.client.model.classes.Singleton_client;
import framework.module.client.view.Create_client;
import framework.module.client.view.Read_client;
import framework.module.client.view.Update_client;

/**
 *
 * @author angel
 */
public class DAO_client {
    
            ////////////////////////////////////////////////////////////////////
            //                                                                //
            //                          CREATE_CLIENT                         //
            //                                                                //
            ////////////////////////////////////////////////////////////////////
  
    public static Client ask_client () {
		
	//Check
	boolean checkDNI, checkname, checksurname, checkmobilephone, checkemail,
                checkuser, checkpass, checkavatar, checkdatebirthday, checkstate,
                checkshopping, checkdtos, checkpremium,checktypeclient;
		
        //Client
	String DNI="", name="",surname="", mobilephone="", email="", user="", pass="", avatar="", typeclient="";
	int dtos=0;
	float shopping=0.0f;
	boolean state, premium;
        ClassDate datebirthday=null;
        Client client = null;
        
        checkDNI=DNI();
	checkuser=user();
	checkpass=pass();
	//checkavatar=validaString();
	//checkstate=state();
	checkname=name();
	checksurname=surname();
	checkemail=email();
	checkmobilephone=mobilephone();
	checkdatebirthday=datebirthday(18);
        checkshopping=shopping();
        checkdtos=dtos();
        //checkpremium=premium();
        checktypeclient=typeclient();
        
        if (checkDNI==true && checkname==true && checksurname==true && checkmobilephone==true &&
            checkemail==true && checkuser==true && checkpass==true && checkdatebirthday==true &&
            checkshopping==true && checkdtos==true && checktypeclient==true){
            
            DNI=Create_client.txtDNI.getText();
            user=Create_client.txtuser.getText();
            pass=Create_client.txtpass.getText();
            avatar=Create_client.txtavatar.getText();
            state=state();
            name=Create_client.txtname.getText();
            surname=Create_client.txtsurname.getText();
            email=Create_client.txtemail.getText();
            mobilephone=Create_client.txtmobilephone.getText();
            datebirthday=new ClassDate (((JTextFieldDateEditor) Create_client.txtdatebirthday.getDateEditor()).getText());
            shopping=Float.parseFloat(Create_client.txtshopping.getText());
            dtos=Integer.parseInt(Create_client.txtdtos.getText());
            premium=premium();
            typeclient=Create_client.txttype_client.getText();
            
            client = new Client(DNI, user, pass, avatar, state, name, surname, email, mobilephone,
            datebirthday, shopping, dtos, premium, typeclient);
        }
        
        return client;
    }
    
    public static Client ask_clientDNI () {	
        boolean checkDNI;
        String DNI;
        Client client = null;
	
        checkDNI=DNI();
        if (checkDNI==true){
            client = new Client (Create_client.txtDNI.getText());
        }
	
        return client;
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
		
		DNI=Create_client.txtDNI.getText();
		confirm=validate.DNI(DNI);
		if (confirm==false) {
                    check = false;
                    Create_client.checkDNI.setIcon(Singleton_client.cancel);
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
                        Create_client.checkDNI.setIcon(Singleton_client.ok);
                    }else{
                        check = false;
                        Create_client.checkDNI.setIcon(Singleton_client.cancel);
                    }
                }
        
        return check;
    }
    
    public static boolean user () {
        boolean check=true, confirm=false;
        
	confirm=validate.user(Create_client.txtuser.getText());
	if (confirm==false) {
            check = false;
            Create_client.checkuser.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Create_client.checkuser.setIcon(Singleton_client.ok);
        }
        
        return check;
    }
    
    public static boolean pass () {
        boolean check=true, confirm=false;
		
	confirm=validate.pass(Create_client.txtpass.getText());
	if (confirm==false) {
            check = false;
            Create_client.checkpass.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Create_client.checkpass.setIcon(Singleton_client.ok);
        }
                        
        return check;
    }
    
    public static boolean name () {
        boolean check=true, confirm=false;
        
        confirm=validate.name(Create_client.txtname.getText());
	if (confirm==false) {
            check = false;
            Create_client.checkname.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Create_client.checkname.setIcon(Singleton_client.ok);
        }
        
        return check;
    }
    
    public static boolean surname () {
        boolean check=true, confirm=false;
        
        confirm=validate.surname(Create_client.txtsurname.getText());
	if (confirm==false) {
            check = false;
            Create_client.checksurname.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Create_client.checksurname.setIcon(Singleton_client.ok);
        }
        
        return check;
    }
    
    public static boolean email () {
        boolean check=true, confirm=false;
        
        confirm=validate.email(Create_client.txtemail.getText());
	if (confirm==false) {
            check = false;
            Create_client.checkemail.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Create_client.checkemail.setIcon(Singleton_client.ok);
        }
        
        return check;
    }
    
    public static boolean mobilephone () {
        boolean check=true, confirm=false;
        
        confirm=validate.mobilephone(Create_client.txtmobilephone.getText());
	if (confirm==false) {
            check = false;
            Create_client.checkmobilephone.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Create_client.checkmobilephone.setIcon(Singleton_client.ok);
        }
        return check;
    }
    
    public static boolean state () {
        boolean check=true;
        
        if (Create_client.statetrue.isSelected()){
            check=true;
        }
        if (Create_client.statefalse.isSelected()){
            check=false;
        }
        
        return check;
    }
    
    public static boolean datebirthday (int year) {
        boolean check=true, confirm=false;
        ClassDate Cdate=null;
        String date="";
        int age;
        
        confirm = validate.date(((JTextFieldDateEditor) Create_client.txtdatebirthday.getDateEditor()).getText());
	if (confirm==false) {
            check = false;
            Create_client.checkdatebirthday.setIcon(Singleton_client.cancel);
	}else{
            Cdate = new ClassDate (((JTextFieldDateEditor) Create_client.txtdatebirthday.getDateEditor()).getText());
            confirm=Cdate.checkdate();
            if (confirm==false) {
                check = false;
                Create_client.checkdatebirthday.setIcon(Singleton_client.cancel);
            }else{
                age = Cdate.subtractsystemdateyear();
		if (age < year) {
                    check = false;
                    Create_client.checkdatebirthday.setIcon(Singleton_client.cancel);
		}else{
                    check = true;
                    Create_client.checkdatebirthday.setIcon(Singleton_client.ok);
                }
            }
	}
        
        return check;
    }
    
    public static boolean shopping () {
        boolean check=true;
        float i;
        
        try{
            if(Create_client.txtshopping.getText()==null){
                check = false;
                Create_client.checkshopping.setIcon(Singleton_client.cancel);
            }else {
                i=Float.parseFloat(Create_client.txtshopping.getText());
                check = true;
                Create_client.checkshopping.setIcon(Singleton_client.ok);
            }
        }catch(Exception e){
            check = false;
            Create_client.checkshopping.setIcon(Singleton_client.cancel);
        }
        
        return check;
    }
    
    public static boolean dtos () {
        boolean check=true;
        int i;
        
        try{
            if(Create_client.txtdtos.getText()==null){
                check = false;
                Create_client.checkdtos.setIcon(Singleton_client.cancel);
            }else {
                i=Integer.parseInt(Create_client.txtdtos.getText());
                check = true;
                Create_client.checkdtos.setIcon(Singleton_client.ok);
            }
        }catch(Exception e){
            check = false;
            Create_client.checkdtos.setIcon(Singleton_client.cancel);
        }
        
        return check;
    }
    
    public static boolean premium () {
        boolean check=true;
        
        if (Create_client.premiumtrue.isSelected()){
            check=true;
        }
        if (Create_client.premiumfalse.isSelected()){
            check=false;
        }
        
        return check;
    }
    
    public static boolean typeclient () {
        boolean check=true, confirm=false;
        
	confirm=validate.typeclient(Create_client.txttype_client.getText());
	if (confirm==false) {
            check = false;
            Create_client.checktype_client.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Create_client.checktype_client.setIcon(Singleton_client.ok);
        }
        
        return check;
    }
    
            ////////////////////////////////////////////////////////////////////
            //                                                                //
            //                          UPDATE_CLIENT                         //
            //                                                                //
            ////////////////////////////////////////////////////////////////////
 
    public static void generate_edit_client (){
            Client client = BLL_client.IDclient();
            
            Update_client.txtDNI.setText(client.getDNI());
            Update_client.txtuser.setText(client.getUser());
            Update_client.txtpass.setText(client.getPass());
            Update_client.txtavatar.setText(client.getAvatar());
            if (client.getState()==true){
                    Update_client.statetrue.setSelected(true);
            }else{
                    Update_client.statefalse.setSelected(true);
            }
            Update_client.txtname.setText(client.getName());
            Update_client.txtsurname.setText(client.getSurname());
            Update_client.txtemail.setText(client.getEmail());
            Update_client.txtmobilephone.setText(client.getMobilephone());
            Update_client.txtdatebirthday.setCalendar(client.getDatebirthday().StringtoCalendar());
            Update_client.txtshopping.setText(client.getShopping()+"");
            Update_client.txtdtos.setText(client.getDtos()+"");
            if (client.getPremium()==true){
                    Update_client.premiumtrue.setSelected(true);
            }else{
                    Update_client.premiumfalse.setSelected(true);
            }
            Update_client.txttype_client.setText(client.getTypeclient());
    }
    
    public static Client ask_client_update () {
		
	//Check
	boolean checkDNI, checkname, checksurname, checkmobilephone, checkemail,
                checkuser, checkpass, checkavatar, checkdatebirthday, checkstate,
                checkshopping, checkdtos, checkpremium,checktypeclient;
		
        //Client
	String DNI="", name="",surname="", mobilephone="", email="", user="", pass="", avatar="", typeclient="";
	int dtos=0;
	float shopping=0.0f;
	boolean state, premium;
        ClassDate datebirthday=null;
        Client client = null;
        
        checkDNI=DNI_update();
	checkuser=user_update();
	checkpass=pass_update();
	//checkavatar=validaString_update();
	//checkstate=state_update();
	checkname=name_update();
	checksurname=surname_update();
	checkemail=email_update();
	checkmobilephone=mobilephone_update();
	checkdatebirthday=datebirthday_update(18);
        checkshopping=shopping_update();
        checkdtos=dtos_update();
        //checkpremium=premium_update();
        checktypeclient=typeclient_update();
        
        if (checkDNI==true && checkname==true && checksurname==true && checkmobilephone==true &&
            checkemail==true && checkuser==true && checkpass==true && checkdatebirthday==true &&
            checkshopping==true && checkdtos==true && checktypeclient==true){
            DNI=Update_client.txtDNI.getText();
            user=Update_client.txtuser.getText();
            pass=Update_client.txtpass.getText();
            avatar=Update_client.txtavatar.getText();
            state=state_update();
            name=Update_client.txtname.getText();
            surname=Update_client.txtsurname.getText();
            email=Update_client.txtemail.getText();
            mobilephone=Update_client.txtmobilephone.getText();
            datebirthday=new ClassDate (((JTextFieldDateEditor) Update_client.txtdatebirthday.getDateEditor()).getText());
            shopping=Float.parseFloat(Update_client.txtshopping.getText());
            dtos=Integer.parseInt(Update_client.txtdtos.getText());
            premium=premium_update();
            typeclient=Update_client.txttype_client.getText();
            
            client = new Client(DNI, user, pass, avatar, state, name, surname, email, mobilephone,
            datebirthday, shopping, dtos, premium, typeclient);
        }
        
        return client;
    }
    
    public static Client ask_clientDNI_update () {	
        boolean checkDNI;
        String DNI;
        Client client = null;
	
        checkDNI=DNI();
        if (checkDNI==true){
            client = new Client (Update_client.txtDNI.getText());
        }
	
        return client;
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
		
		DNI=Update_client.txtDNI.getText();
		confirm=validate.DNI(DNI);
		if (confirm==false) {
                    check = false;
                    Update_client.checkDNI.setIcon(Singleton_client.cancel);
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
                        Update_client.checkDNI.setIcon(Singleton_client.ok);
                    }else{
                        check = false;
                        Update_client.checkDNI.setIcon(Singleton_client.cancel);
                    }
                }
        
        return check;
    }
    
    public static boolean user_update () {
        boolean check=true, confirm=false;
        
	confirm=validate.user(Update_client.txtuser.getText());
	if (confirm==false) {
            check = false;
            Update_client.checkuser.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Update_client.checkuser.setIcon(Singleton_client.ok);
        }
        
        return check;
    }
    
    public static boolean pass_update () {
        boolean check=true, confirm=false;
		
	confirm=validate.pass(Update_client.txtpass.getText());
	if (confirm==false) {
            check = false;
            Update_client.checkpass.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Update_client.checkpass.setIcon(Singleton_client.ok);
        }
                        
        return check;
    }
    
    public static boolean name_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.name(Update_client.txtname.getText());
	if (confirm==false) {
            check = false;
            Update_client.checkname.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Update_client.checkname.setIcon(Singleton_client.ok);
        }
        
        return check;
    }
    
    public static boolean surname_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.surname(Update_client.txtsurname.getText());
	if (confirm==false) {
            check = false;
            Update_client.checksurname.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Update_client.checksurname.setIcon(Singleton_client.ok);
        }
        
        return check;
    }
    
    public static boolean email_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.email(Update_client.txtemail.getText());
	if (confirm==false) {
            check = false;
            Update_client.checkemail.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Update_client.checkemail.setIcon(Singleton_client.ok);
        }
        
        return check;
    }
    
    public static boolean mobilephone_update () {
        boolean check=true, confirm=false;
        
        confirm=validate.mobilephone(Update_client.txtmobilephone.getText());
	if (confirm==false) {
            check = false;
            Update_client.checkmobilephone.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Update_client.checkmobilephone.setIcon(Singleton_client.ok);
        }
        return check;
    }
    
    public static boolean state_update () {
        boolean check=true;
        
        if (Update_client.statetrue.isSelected()){
            check=true;
        }
        if (Update_client.statefalse.isSelected()){
            check=false;
        }
        
        return check;
    }
    
    public static boolean datebirthday_update (int year) {
        boolean check=true, confirm=false;
        ClassDate Cdate=null;
        String date="";
        int age;
        
        confirm = validate.date(((JTextFieldDateEditor) Update_client.txtdatebirthday.getDateEditor()).getText());
	if (confirm==false) {
            check = false;
            Update_client.checkdatebirthday.setIcon(Singleton_client.cancel);
	}else{
            Cdate = new ClassDate (((JTextFieldDateEditor) Update_client.txtdatebirthday.getDateEditor()).getText());
            confirm=Cdate.checkdate();
            if (confirm==false) {
                check = false;
                Update_client.checkdatebirthday.setIcon(Singleton_client.cancel);
            }else{
                age = Cdate.subtractsystemdateyear();
		if (age < year) {
                    check = false;
                    Update_client.checkdatebirthday.setIcon(Singleton_client.cancel);
		}else{
                    check = true;
                    Update_client.checkdatebirthday.setIcon(Singleton_client.ok);
                }
            }
	}
        
        return check;
    }
    
    public static boolean shopping_update () {
        boolean check=true;
        float i;
        
        try{
            if(Update_client.txtshopping.getText()==null){
                check = false;
                Update_client.checkshopping.setIcon(Singleton_client.cancel);
            }else {
                i=Float.parseFloat(Update_client.txtshopping.getText());
                check = true;
                Update_client.checkshopping.setIcon(Singleton_client.ok);
            }
        }catch(Exception e){
            check = false;
            Update_client.checkshopping.setIcon(Singleton_client.cancel);
        }
        
        return check;
    }
    
    public static boolean dtos_update () {
        boolean check=true;
        int i;
        
        try{
            if(Update_client.txtdtos.getText()==null){
                check = false;
                Update_client.checkdtos.setIcon(Singleton_client.cancel);
            }else {
                i=Integer.parseInt(Update_client.txtdtos.getText());
                check = true;
                Update_client.checkdtos.setIcon(Singleton_client.ok);
            }
        }catch(Exception e){
            check = false;
            Update_client.checkdtos.setIcon(Singleton_client.cancel);
        }
        
        return check;
    }
    
    public static boolean premium_update () {
        boolean check=true;
        
        if (Update_client.premiumtrue.isSelected()){
            check=true;
        }
        if (Update_client.premiumfalse.isSelected()){
            check=false;
        }
        
        return check;
    }
    
    public static boolean typeclient_update () {
        boolean check=true, confirm=false;
        
	confirm=validate.typeclient(Update_client.txttype_client.getText());
	if (confirm==false) {
            check = false;
            Update_client.checktype_client.setIcon(Singleton_client.cancel);
	}else{
            check = true;
            Update_client.checktype_client.setIcon(Singleton_client.ok);
        }
        
        return check;
    }
            
            ////////////////////////////////////////////////////////////////////
            //                                                                //
            //                           READ_CLIENT                          //
            //                                                                //
            ////////////////////////////////////////////////////////////////////

    public static void generate_read_client () {
        Client client = BLL_client.IDclient();
        
        Read_client.txtDNI.setText("DNI: "+client.getDNI());
        Read_client.txtuser.setText("Usuario: "+client.getUser());
        Read_client.txtpass.setText("Contraseña: "+client.getPass());
        Read_client.txtavatar.setText("Avatar: "+client.getAvatar());
        Read_client.txtstate.setText("Estado: "+client.toStringstate());
        Read_client.txtname.setText("Nombre: "+client.getName());
        Read_client.txtsurname.setText("Apellidos: "+client.getSurname());
        Read_client.txtemail.setText("Correo electronico: "+client.getEmail());
        Read_client.txtmobilephone.setText("Telefono movil: "+client.getMobilephone());
        Read_client.txtdatebirthday.setText("Fecha de nacimiento: "+client.getDatebirthday().toString());
        Read_client.txtage.setText("Edad: "+client.getAge());
        Read_client.txtbenefits.setText("Beneficios: "+client.getBenefits());
        Read_client.txtdateup.setText("Fecha de alta: "+client.getDateup().toString());
        Read_client.txtantique.setText("Antigüedad: "+client.getAntique());
        Read_client.txtshopping.setText("Compras: "+client.getShopping());
        Read_client.txtdtos.setText("Descuentos: "+client.getDtos());
        Read_client.txtpremium.setText("Premium: "+client.toStringpremium());
        Read_client.txttypeclient.setText("Tipo de cliente: "+client.getTypeclient());
        
    }
}
