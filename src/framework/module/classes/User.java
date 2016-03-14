package framework.module.classes;

import java.io.Serializable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import framework.module.config.model.classes.ClassConfig;
import framework.classes.ClassDate;
import framework.functions.Format;
import framework.module.config.model.classes.language2.Language_user;

@XStreamAlias("User")
public abstract class User implements Comparable<User>, Serializable{

	/*
	 * ATTRIBUTES
	 * 
	 */
	
	@XStreamAlias("DNI")
	private String DNI;
	@XStreamAlias("user")
	private String user;
	@XStreamAlias("password")
	private String pass;
	@XStreamAlias("avatar")
	private String avatar;
	@XStreamAlias("state")
	private boolean state;
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("surname")
	private String surname;
	@XStreamAlias("email")
	private String email;
	@XStreamAlias("mobilephone")
	private String mobilephone;
	@XStreamAlias("datebirthday")
	private ClassDate datebirthday;
	@XStreamAlias("age")
	private int age;
	@XStreamAlias("benefits")
	private float benefits;
	
	/*
	 * BUILDING
	 * 
	 */
	
		/*
		 * building
		 * 
		 */
		public User(String DNI, String user, String pass, String avatar, boolean state, String name, String surname,
				String email, String mobilephone, ClassDate datebirthday, float benefits) {
			
			this.DNI = DNI;
			this.user = user;
			this.pass = pass;
			this.avatar = avatar;
			this.state = state;
			this.name = name;
			this.surname = surname;
			this.email = email;
			this.mobilephone = mobilephone;
			this.datebirthday = datebirthday;
			this.setAge(calculate_age());
			this.benefits = benefits;
		}
		
		/*
		 * building default
		 * 
		 */
		public User() {
		}
		
		/*
		 * primary key building
		 * 
		 */
		public User(String DNI) {
			this.DNI = DNI;
		}
		
		/*
		 * customized building
		 * 
		 */
		public User(int i, Object param) {
						
			switch (i){
				case 0:
					this.DNI = (String) param;
					break;
				case 1:
					this.user = (String) param;
					break;
				case 2:
					this.pass = (String) param;
					break;
				case 3:
					this.avatar = (String) param;
					break;
				case 4:
					this.state = (boolean) param;
					break;
				case 5:
					this.name = (String) param;
					break;
				case 6:
					this.surname = (String) param;
					break;
				case 7:
					this.email = (String) param;
					break;
				case 8:
					this.mobilephone = (String) param;
					break;
				case 9:
					this.datebirthday = (ClassDate) param;
					break;
				case 10:
					this.benefits= (float) param;
					break;
			}
		}
		
	/*
	 * GUETTERS & SETTERS
	 * 
	 */
		
		public String getDNI() {
			return DNI;
		}

		public void setDNI(String dNI) {
			DNI = dNI;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public boolean getState() {
			return state;
		}

		public void setState(boolean state) {
			this.state = state;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobilephone() {
			return mobilephone;
		}

		public void setMobilephone(String mobilephone) {
			this.mobilephone = mobilephone;
		}

		public ClassDate getDatebirthday() {
			return datebirthday;
		}

		public void setDatebirthday(ClassDate datebirthday) {
			this.datebirthday = datebirthday;
			this.setAge(calculate_age());
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public float getBenefits() {
			return benefits;
		}

		public void setBenefits(float benefits) {
			this.benefits = benefits;
		}
		
		public int compareTo(User param) {//para ordenar los empleados por nombre
			if(this.getDNI().compareTo(param.getDNI())>0)
				return 1;
			if(this.getDNI().compareTo(param.getDNI())<0)
				return -1;
			return 0;
		  }
		
		public boolean equals(Object param){
			return getDNI().equals(((User)param).getDNI());
		}		
		
		/*
		 * TOSTRING(non-Javadoc)
		 * @see java.lang.Object#toString()
		 * show all information(non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			StringBuffer string = new StringBuffer();
			string.append(Language_user.getInstance().getProperty("toStringDNI")+this.getDNI()+"\n");
			string.append(Language_user.getInstance().getProperty("toStringuser")+this.getUser()+"\n");
			string.append(Language_user.getInstance().getProperty("toStringpass")+this.getPass()+"\n");
			string.append(Language_user.getInstance().getProperty("toStringavatar")+this.getAvatar()+"\n");
			string.append(Language_user.getInstance().getProperty("toStringstate")+toStringstate()+"\n");
			string.append(Language_user.getInstance().getProperty("toStringname")+this.getName()+"\n");
			string.append(Language_user.getInstance().getProperty("toStringsurname")+this.getSurname()+"\n");
			string.append(Language_user.getInstance().getProperty("toStringemail")+this.getEmail()+"\n");
			string.append(Language_user.getInstance().getProperty("toStringphone")+this.getMobilephone()+"\n");
			string.append(Language_user.getInstance().getProperty("toStringdatebirthday")+this.getDatebirthday().toString()+"\n");
			string.append(Language_user.getInstance().getProperty("toStringage")+this.getAge()+" years\n");
			string.append(Language_user.getInstance().getProperty("toStringbenefits")+Format.formatCurrency(this.getBenefits())+"\n");
			
			return string.toString();
		}
		
		/*
		 * show primary key information
		 * 
		 */
		public String toString (String DNI) {
			StringBuffer string = new StringBuffer();
			
			string.append(Language_user.getInstance().getProperty("toStringDNI")+this.getDNI()+"\n");
			
			return string.toString();
		}
		
		/*
		 * show customized information
		 * 
		 */
		public String toString(int i) {
			StringBuffer string = new StringBuffer();
				
			switch (i){
				case 0:
					string.append(Language_user.getInstance().getProperty("toStringDNI")+this.getDNI()+"\n");
					break;
				case 1:
					string.append(Language_user.getInstance().getProperty("toStringuser")+this.getUser()+"\n");
					break;
				case 2:
					string.append(Language_user.getInstance().getProperty("toStringpass")+this.getPass()+"\n");
					break;
				case 3:
					string.append(Language_user.getInstance().getProperty("toStringavatar")+this.getAvatar()+"\n");
					break;
				case 4:
					string.append(Language_user.getInstance().getProperty("toStringstate")+toStringstate()+"\n");
					break;
				case 5:
					string.append(Language_user.getInstance().getProperty("toStringname")+this.getName()+"\n");
					break;
				case 6:
					string.append(Language_user.getInstance().getProperty("toStringsurname")+this.getSurname()+"\n");
					break;
				case 7:
					string.append(Language_user.getInstance().getProperty("toStringemail")+this.getEmail()+"\n");
					break;
				case 8:
					string.append(Language_user.getInstance().getProperty("toStringphone")+this.getMobilephone()+"\n");
					break;
				case 9:
					string.append(Language_user.getInstance().getProperty("toStringdatebirthday")+this.getDatebirthday().toString()+"\n");
					break;
				case 10:
					string.append(Language_user.getInstance().getProperty("toStringage")+this.getAge()+" years\n");
					break;
				case 11:
					string.append(Language_user.getInstance().getProperty("toStringbenefits")+Format.formatCurrency(this.getBenefits())+"\n");
					break;
			}
			
			return string.toString();
		}
		
	public abstract float calculate_benefits ();
	
	public int calculate_age (){
		int i = 0;
		
		i= this.getDatebirthday().subtractsystemdateyear();
		
		return i;
	}
	
	public String toStringstate (){
		String state="";
		if (this.getState()==true){
			state = Language_user.getInstance().getProperty("conected");
		}
		if (this.getState()==false){
			state = Language_user.getInstance().getProperty("noconected");
		}
		
		return state;
	}
}
