package framework.module.users.client.model.classes;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import framework.module.users.classes.User;
import java.io.Serializable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import framework.module.menu_config.model.classes.ClassConfig;
import framework.classes.ClassDate;
import framework.functions.Format;
import framework.functions.Functions;
import framework.module.users.client.model.classes.language.Language_client;

/**
 * 
 * @author angel
 */
@XStreamAlias("Client")
public class Client extends User implements Serializable {

    /**
     * ATTRIBUTES
     */

    @XStreamAlias("dateup")
    private ClassDate dateup;
    @XStreamAlias("antique")
    private int antique;
    @XStreamAlias("shopping")
    private float shopping;
    @XStreamAlias("dtos")
    private int dtos;
    @XStreamAlias("premium")
    private boolean premium;
    @XStreamAlias("typeclient")
    private String typeclient;

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
     * @param shopping
     * @param dtos
     * @param premium
     * @param typeclient 
     */
    public Client(String DNI, String user, String pass, String avatar, boolean state, String name, String surname,
                    String email, String mobilephone, ClassDate datebirthday, float shopping, int dtos, boolean premium,
                    String typeclient) {

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
            this.dateup = new ClassDate();
            this.setAntique(calculate_antique());
            this.shopping = this.calculate_currency (shopping);
            this.dtos = dtos;
            this.premium = premium;
            this.typeclient = typeclient;
            super.setBenefits(calculate_benefits ());
    }

    /**
     * BUILDING DEFAULT
     */
    public Client(){
            super();
    }

    /**
     * BUILDING
     * @param DNI 
     */
    public Client(String DNI) {
            super(DNI);
    }

    /**
     * BUILDING
     * @param i
     * @param param 
     */
    public Client(int i, Object param) {

                    switch (i){
                            case 11:
                                    this.shopping =this.calculate_currency ((float) param);
                                    break;
                            case 12:
                                    this.dtos = (int) param;
                                    break;
                            case 13:
                                    this.premium = (boolean) param;
                                    break;
                            case 14:
                                    this.typeclient = (String) param;
                                    break;
                    }
    }

    /**
     * BD TO USER
     * @param dBObjectClient
     * @return 
     */
    public Client BD_to_Client (DBObject dBObjectClient){
        Client client = new Client();

        client.setDNI((String) dBObjectClient.get("DNI"));
        client.setUser((String) dBObjectClient.get("user"));
        client.setPass((String) dBObjectClient.get("pass"));
        client.setAvatar((String) dBObjectClient.get("avatar"));
        client.setState((boolean) dBObjectClient.get("state"));
        client.setName((String) dBObjectClient.get("name"));
        client.setSurname((String) dBObjectClient.get("surname"));
        client.setEmail((String) dBObjectClient.get("email"));
        client.setMobilephone((String) dBObjectClient.get("mobilephone"));
        client.setDatebirthday(new  ClassDate((String) dBObjectClient.get("datebirthday"), "Mongo"));
        client.setDateup(new ClassDate((String) dBObjectClient.get("dateup"), "Mongo"));
        client.setAntique((int) dBObjectClient.get("antique"));
        client.setShopping(Functions.Double_to_Float((double) dBObjectClient.get("shopping")));
        client.setDtos((int) dBObjectClient.get("dtos"));
        client.setPremium((boolean) dBObjectClient.get("premium"));
        client.setTypeclient((String) dBObjectClient.get("typeclient"));
        client.setBenefits(Functions.Double_to_Float((double) dBObjectClient.get("benefits")));

        return client;
    }

    /**
     * USER TO BD
     * @return 
     */
    public BasicDBObject Client_to_BD() {
        BasicDBObject dBObjectClient = new BasicDBObject();
        dBObjectClient.append("DNI", this.getDNI());
        dBObjectClient.append("user", this.getUser());
        dBObjectClient.append("pass", this.getPass());
        dBObjectClient.append("avatar", this.getAvatar());
        dBObjectClient.append("state", this.getState());
        dBObjectClient.append("name", this.getName());
        dBObjectClient.append("surname", this.getSurname());
        dBObjectClient.append("email", this.getEmail());
        dBObjectClient.append("mobilephone", this.getMobilephone());
        dBObjectClient.append("datebirthday", this.getDatebirthday().toStringBD());
        dBObjectClient.append("dateup", this.getDateup().toStringBD());
        dBObjectClient.append("antique", this.getAntique());
        dBObjectClient.append("shopping", this.getShopping());
        dBObjectClient.append("dtos", this.getDtos());
        dBObjectClient.append("premium", this.getPremium());
        dBObjectClient.append("typeclient", this.getTypeclient());
        dBObjectClient.append("benefits", this.getBenefits());

        return dBObjectClient;

    }

    /**
     * GETS AND SETS
     * @return 
     */
    public ClassDate getDateup() {
        return dateup;
    }

    public void setDateup(ClassDate dateup) {
        this.dateup = dateup;
        this.setAntique(calculate_antique());
    }

    public int getAntique() {
        return antique;
    }

    public void setAntique(int antique) {
        this.antique = antique;
        super.setBenefits(calculate_benefits());
    }

    public float getShopping() {
        return shopping;
    }

    public void setShopping(float shopping) {
        this.shopping = this.calculate_currency (shopping);
        super.setBenefits(calculate_benefits());
    }

    public int getDtos() {
        return dtos;
    }

    public void setDtos(int dtos) {
        this.dtos = dtos;
        super.setBenefits(calculate_benefits());
    }

    public boolean getPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public String getTypeclient() {
        return typeclient;
    }

    public void setTypeclient(String typeclient) {
        this.typeclient = typeclient;
    }

    /**
     * TO STRING
     * @return 
     */
    public String toString () {
        StringBuffer string = new StringBuffer();
        string.append(super.toString()+"\n");
        string.append(Language_client.getInstance().getProperty("dateup")+": "+this.getDateup().toString()+"\n");
        string.append(Language_client.getInstance().getProperty("old")+": "+this.getAntique()+"\n");
        string.append(Language_client.getInstance().getProperty("shopping")+": "+Format.formatCurrency(this.getShopping())+"\n");
        string.append(Language_client.getInstance().getProperty("dtos")+": "+this.getDtos()+"\n");
        string.append(Language_client.getInstance().getProperty("premium")+": "+toStringpremium()+"\n");
        string.append(Language_client.getInstance().getProperty("typeclient")+": "+this.getTypeclient()+"\n");

        return string.toString();
    }

    /**
     * TO STRING
     * @param DNI
     * @return 
     */
    public String toString (String DNI) {
        StringBuffer string = new StringBuffer();

        string.append(Language_client.getInstance().getProperty("DNI")+": "+super.getDNI()+"\n");

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
                    string.append(Language_client.getInstance().getProperty("dateup")+": "+this.getDateup().toString()+"\n");
                    break;
                case 13:
                    string.append(Language_client.getInstance().getProperty("old")+": "+this.getAntique()+"\n");
                    break;
                case 14:
                    string.append(Language_client.getInstance().getProperty("shopping")+": "+Format.formatCurrency(this.getShopping())+"\n");
                    break;
                case 15:
                    string.append(Language_client.getInstance().getProperty("dtos")+": "+this.getDtos()+"\n");
                    break;
                case 16:
                    string.append(Language_client.getInstance().getProperty("premium")+": "+toStringpremium()+"\n");
                    break;
                case 17:
                    string.append(Language_client.getInstance().getProperty("typeclient")+": "+this.getTypeclient()+"\n");
                    break;
            }
        }

        return string.toString();
    }

    /**
     * CALCULATE BENEFITS
     * @return 
     */
    public float calculate_benefits() {
        float n=0.0f;

        n=this.getAntique()*1+this.getDtos()+this.getShopping()*0.05f;

        return n;
    }

    /**
     * CALCULATE OLD
     * @return 
     */
    public int calculate_antique () {
        int i = 0;

        i = this.getDateup().subtractsystemdateyear();

        return i;
    }

    /**
     * CALCULATE CURRENCY VALUE
     * @param money
     * @return 
     */
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

    /**
     * CONVERT PREMIUM BOOLEAN TO STRING
     * @return 
     */
    public String toStringpremium (){
        String premium="";
        if (this.getState()==true){
            premium = Language_client.getInstance().getProperty("premium");
        }
        if (this.getState()==false){
            premium = Language_client.getInstance().getProperty("notpremium");
        }

        return premium;
    }
}