package framework.module.userregister.model.classes;

import framework.module.classes.User;
import java.io.Serializable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import framework.classes.ClassDate;
import framework.module.userregister.model.classes.language.Language_userregister;

/**
 * 
 * @author angel
 */
@XStreamAlias("User_register")
public class User_register extends User implements Serializable {

    /**
     * ATTRIBUTES
     */

    @XStreamAlias("movement")
    private int movement;
    @XStreamAlias("reputation")
    private String reputation;
    @XStreamAlias("point")
    private int point;

    /**
     * BUILDING
     * @param DNI
     * @param user
     * @param pass
     * @param avatar
     * @param state
     * @param name
     * @param surname
     * @param email
     * @param mobilephone
     * @param datebirthday
     * @param movement
     * @param point 
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

    /**
     * BUILDING DEFAULT
     */
    public User_register() {
        super();
    }

    /**
     * BUILDING
     * @param DNI 
     */
    public User_register(String DNI) {
        super(DNI);
    }

    /**
     * BUILDING
     * @param i
     * @param param 
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

    /**
     * GETS AND SETS
     * @return 
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
            s=(Language_userregister.getInstance().getProperty("low"));
        }
        if ((this.getMovement()>10) && (this.getMovement()<=50)){
            s=(Language_userregister.getInstance().getProperty("medium"));
        }
        if (this.getMovement()>50){
            s=(Language_userregister.getInstance().getProperty("high"));
        }

        return s;
    }

    /**
     * TO STRING
     * @return 
     */
    public String toString() {
        StringBuffer string = new StringBuffer();
        string.append(super.toString()+"\n");
        string.append(Language_userregister.getInstance().getProperty("activity")+": "+this.getMovement()+"\n");
        string.append(Language_userregister.getInstance().getProperty("reputation")+": "+this.calc_reputation()+"\n");
        string.append(Language_userregister.getInstance().getProperty("point")+": "+this.getPoint()+"\n");

        return string.toString();
    }

    /**
     * TO STRING
     * @param DNI
     * @return 
     */
    public String toString (String DNI) {
        StringBuffer string = new StringBuffer();

        string.append(Language_userregister.getInstance().getProperty("DNI")+": "+super.getDNI()+"\n");

        return string.toString();
    }

    /**
     * TO STRING
     * @param i
     * @return 
     */
    public String toString(int i) {
        StringBuffer string = new StringBuffer();

        if (i<12){
            string.append(super.toString(i)+"\n");
        }else{
            switch (i){
                case 12:
                    string.append(Language_userregister.getInstance().getProperty("activity")+": "+this.getMovement()+"\n");
                    break;
                case 13:
                    string.append(Language_userregister.getInstance().getProperty("reputation")+": "+this.getReputation()+"\n");
                    break;
                case 14:
                    string.append(Language_userregister.getInstance().getProperty("point")+": "+this.getPoint()+"\n");
                    break;
            }
        }

        return string.toString();
    }
}
