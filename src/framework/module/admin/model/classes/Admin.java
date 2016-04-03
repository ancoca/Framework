package framework.module.admin.model.classes;

import framework.module.classes.User;
import java.io.Serializable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import framework.module.menu_config.model.classes.ClassConfig;
import framework.classes.ClassDate;
import framework.functions.Format;
import framework.module.admin.model.classes.language.Language_admin;

@XStreamAlias("Admin")
public class Admin extends User implements Serializable {

	/*
	 * ATTRIBUTES
	 * 
	 */
	
	@XStreamAlias("datecontract")
	private ClassDate datecontract;
	@XStreamAlias("old")
	private int old;
	@XStreamAlias("salary")
	private float salary;
	@XStreamAlias("incentive")
	private float incentive;
	@XStreamAlias("activity")
	private int activity;
	
	/*
	 * BUILDING
	 * 
	 */
	
		/*
		 * building
		 * 
		 */
		public Admin(String DNI, String user, String pass, String avatar, boolean state, String name, String surname,
				String email, String mobilephone, ClassDate datebirthday, ClassDate datecontract,
				float salary, float incentive, int activity) {
			
			super.setDNI(DNI);
			super.setUser(user);
			super.setPass(pass);
			super.setAvatar(avatar);
			super.setState(state);
			super.setName(name);
			super.setSurname(surname);
			super.setEmail(email);
			super.setMobilephone(mobilephone);
			super.setDatebirthday(datebirthday);
			this.datecontract = datecontract;
			this.setOld(calculate_old());
			this.salary = this.calculate_currency (salary);
			this.incentive = this.calculate_currency (incentive);
			this.activity = activity;
			super.setBenefits(this.calculate_benefits());
		}
		
		/*
		 * building default
		 * 
		 */
		public Admin() {
			super();
		}
		
		/*
		 * primary key building
		 * 
		 */
		public Admin(String DNI) {
			super(DNI);
		}
		
		/*
		 * customized building
		 * 
		 */
		public Admin(int i, Object param) {
			
			switch (i){
				case 11:
					this.datecontract=(ClassDate) param;
					break;
				case 12:
					this.salary=this.calculate_currency ((float) param);
					break;
				case 13:
					this.incentive=this.calculate_currency ((float) param);
					break;
				case 14:
					this.activity=(int) param;
					break;
			}
		}
		
	/*
	 * GETTERS & SETTERS
	 * 
	 */

		public ClassDate getDatecontract() {
			return datecontract;
		}

		public void setDate_contract(ClassDate datecontract) {
			this.datecontract = datecontract;
			this.setOld(calculate_old());
		}

		public int getOld() {
			return old;
		}

		public void setOld(int old) {
			this.old = old;
			super.setBenefits(calculate_benefits());
		}

		public float getSalary() {
			return salary;
		}

		public void setSalary(float salary) {
			this.salary = this.calculate_currency (salary);
		}

		public float getIncentive() {
			return incentive;
		}

		public void setIncentive(float incentive) {
			this.incentive = this.calculate_currency (incentive);
			super.setBenefits(calculate_benefits());
		}

		public int getActivity() {
			return activity;
		}

		public void setActivity(int activity) {
			this.activity = activity;
			super.setBenefits(calculate_benefits());
		}
		
	/*
	 * TOSTRING(non-Javadoc)
	 * @see framework.module.classes.User#toString()
	 */
		
		/*
		 * show all the information(non-Javadoc)
		 * @see framework.module.classes.User#toString()
		 */
		public String toString() {
			StringBuffer string = new StringBuffer();
			string.append(super.toString()+"\n");
			string.append(Language_admin.getInstance().getProperty("datecontract")+": "+this.getDatecontract().toString()+"\n");
			string.append(Language_admin.getInstance().getProperty("old")+": "+this.getOld()+"\n");
			string.append(Language_admin.getInstance().getProperty("salary")+": "+Format.formatCurrency(this.getSalary())+"\n");
			string.append(Language_admin.getInstance().getProperty("incentive")+": "+Format.formatCurrency(this.getIncentive())+"\n");
			string.append(Language_admin.getInstance().getProperty("activity")+": "+this.getActivity()+"\n");
			
			return string.toString();
		}
		
		/*
		 * show primary key information(non-Javadoc)
		 * @see framework.module.classes.User#toString(java.lang.String)
		 */
		public String toString (String DNI) {
			StringBuffer string = new StringBuffer();
			
			string.append(Language_admin.getInstance().getProperty("DNI")+": "+super.getDNI()+"\n");
			
			return string.toString();
		}
		
		/*
		 * show customized information(non-Javadoc)
		 * @see framework.module.classes.User#toString(int)
		 */
		public String toString(int i) {
			StringBuffer string = new StringBuffer();
			
			if (i<12){
				string.append(super.toString(i)+"\n");
			}else{
				switch (i){
					case 12:
						string.append(Language_admin.getInstance().getProperty("datecontract")+": "+this.getDatecontract().toString()+"\n");
						break;
					case 13:
						string.append(Language_admin.getInstance().getProperty("old")+": "+this.getOld()+"\n");
						break;
					case 14:
						string.append(Language_admin.getInstance().getProperty("salary")+": "+Format.formatCurrency(this.getSalary())+"\n");
						break;
					case 15:
						string.append(Language_admin.getInstance().getProperty("incentive")+": "+Format.formatCurrency(this.getIncentive())+"\n");
						break;
					case 16:
						string.append(Language_admin.getInstance().getProperty("activity")+": "+this.getActivity()+"\n");
						break;
				}
			}
			
			return string.toString();
		}
		
	public float calculate_benefits() {
		float n=0.0f;
		
		n=this.getOld()*5+this.getIncentive()*1+this.getActivity()*0.5f;
		
		return n;
	}
	
	public int calculate_old () {
			int i = 0;
			
			i = this.getDatecontract().subtractsystemdateyear();
			
			return i;
	}
	
	public float calculate_currency (float money) {
		float value = 0.0f;
		switch (ClassConfig.getInstance().getCurrency()) {
			case '$':
				value = money*0.92221f;
				break;
			case '£':
				value = money*1.37580f;
				break;
			case '€':
				value = money;
				break;
		}
		
		return value;
	}
}
