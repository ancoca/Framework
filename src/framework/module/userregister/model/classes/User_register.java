package framework.module.userregister.model.classes;

import framework.module.classes.User;
import java.io.Serializable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import framework.module.config.model.classes.ClassConfig;
import framework.classes.ClassDate;
import framework.module.config.model.classes.language2.Language_user;

@XStreamAlias("User_register")
public class User_register extends User implements Serializable {

	/*
	 * ATTRIBUTES
	 * 
	 */
	
	@XStreamAlias("movement")
	private int movement;
	@XStreamAlias("reputation")
	private String reputation;
	@XStreamAlias("point")
	private int point;
	
	/*
	 * BUILDING
	 * 
	 */
	
		/*
		 * building
		 * 
		 */
		public User_register(String DNI, String user, String pass, String avatar, boolean state, String name, String surname,
				String email, String mobilephone, ClassDate datebirthday, int movement, int point) {
			
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
			this.movement = movement;
			this.setReputation(calc_reputation());
			this.point = point;
			super.setBenefits(calculate_benefits ());
		}
		
		/*
		 * building default
		 * 
		 */
		public User_register() {
			super();
		}
		
		/*
		 * primary key building
		 * 
		 */
		public User_register(String DNI) {
			super(DNI);
		}
		
		/*
		 * customized building
		 * 
		 */
		public User_register(int i, Object param) {
			
			switch (i) {
				case 11:
					this.movement = (int) param;
					break;
				case 12:
					this.point = (int) param;
					break;
			}
		}
		
	/*
	 * GETTERS & SETTERS
	 * 
	 */
		
		public int getMovement() {
			return movement;
		}

		public void setMovement(int movement) {
			this.movement = movement;
			super.setBenefits(calculate_benefits());
			this.setReputation(calc_reputation());
		}

		public String getReputation() {
			return reputation;
		}

		public void setReputation(String reputation) {
			this.reputation = reputation;
		}

		public int getPoint() {
			return point;
		}

		public void setPoint(int point) {
			this.point = point;
			super.setBenefits(calculate_benefits());
		}
		
		public float calculate_benefits() {
			float n=0.0f;
			
			n=this.getMovement()*0.001f+(this.getPoint()/100)*1;
			
			return n;
		}
		
		public String calc_reputation (){
			String s="";			
			
			if (this.getMovement()<=10){
				s=("Low");
			}
			if ((this.getMovement()>10) && (this.getMovement()<=50)){
				s=("Medium");
			}
			if (this.getMovement()>50){
				s=("High");
			}
			
			return s;
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
			string.append(Language_user.getInstance().getProperty("toStringactivity")+this.getMovement()+"\n");
			string.append(Language_user.getInstance().getProperty("toStringreputation")+this.getReputation()+"\n");
			string.append(Language_user.getInstance().getProperty("toStringpoints")+this.getPoint()+"\n");
			
			return string.toString();
		}
		
		/*
		 * show primary key information(non-Javadoc)
		 * @see framework.module.classes.User#toString(java.lang.String)
		 */
		public String toString (String DNI) {
			StringBuffer string = new StringBuffer();
			
			string.append(Language_user.getInstance().getProperty("toStringDNI")+super.getDNI()+"\n");
			
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
						string.append(Language_user.getInstance().getProperty("toStringactivity")+this.getMovement()+"\n");
						break;
					case 13:
						string.append(Language_user.getInstance().getProperty("toStringreputation")+this.getReputation()+"\n");
						break;
					case 14:
						string.append(Language_user.getInstance().getProperty("toStringpoints")+this.getPoint()+"\n");
						break;
				}
			}
			
			return string.toString();
		}
}
