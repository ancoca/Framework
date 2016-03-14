package framework.classes;

import framework.module.config.model.classes.ClassConfig;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ClassDate")
public class ClassDate implements Serializable{

    /*
     * ATTRIBUTES
     * 
     */

    @XStreamAlias("day")
    private int day;
    @XStreamAlias("month")
    private int month;
    @XStreamAlias("year")
    private int year;
    @XStreamAlias("date")
    private String date;

    /*
     * BUILDING
     * When the user sign in a date
     * @param date: sign in date
     * @return ClassDate
     */
    public ClassDate (String date) {
        String [] datearray = new String [3];
        switch (ClassConfig.getInstance().getDate()) {
            case "dd/MM/yyyy":
                datearray = date.split("/");
                this.day=Integer.parseInt(datearray [0]);
                this.month=Integer.parseInt(datearray [1]);
                this.year=Integer.parseInt(datearray [2]);
                this.date=day+"/"+month+"/"+year;
                break;

            case "dd-MM-yyyy":
                datearray = date.split("-");
                this.day=Integer.parseInt(datearray [0]);
                this.month=Integer.parseInt(datearray [1]);
                this.year=Integer.parseInt(datearray [2]);
                this.date=day+"/"+month+"/"+year;
                break;

            case "yyyy/MM/dd":
                datearray = date.split("/");
                this.day=Integer.parseInt(datearray [2]);
                this.month=Integer.parseInt(datearray [1]);
                this.year=Integer.parseInt(datearray [0]);
                this.date=day+"/"+month+"/"+year;
                break;

            case "yyyy-MM-dd":
                datearray = date.split("-");
                this.day=Integer.parseInt(datearray [2]);
                this.month=Integer.parseInt(datearray [1]);
                this.year=Integer.parseInt(datearray [0]);
                this.date=day+"/"+month+"/"+year;
                break;
        }	
    }

    /*
     * BUILDING
     * Create ClassDate with system date
     * @return ClassDate
     */
    public ClassDate () {
        Calendar cal = Calendar.getInstance();
        String DateS = this.CalendartoString(cal);
        String [] datearray = DateS.split("/");

        this.day=Integer.parseInt(datearray [0]);
        this.month=Integer.parseInt(datearray [1]);
        this.year=Integer.parseInt(datearray [2]);
        this.date=day+"/"+month+"/"+year;

    }

    /* 
     * BUILDING
     * Create ClassDate for Dummies without config
     * @return ClassDate
     */
    public ClassDate (int day, int month, int year) {
        this.day=day;
        this.month=month;
        this.year=year;
        this.date=day+"/"+month+"/"+year;

    }


    /*
     * GETTERS & SETTERS
     * 
     */
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
        this.date = (day+"/"+this.month+"/"+this.year);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
        this.date = (this.day+"/"+month+"/"+this.year);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
        this.date = (this.day+"/"+this.month+"/"+year);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        String [] datearray = new String [3];
        switch (ClassConfig.getInstance().getDate()) {
            case "dd/MM/yyyy":
                datearray = date.split("/");
                this.day=Integer.parseInt(datearray [0]);
                this.month=Integer.parseInt(datearray [1]);
                this.year=Integer.parseInt(datearray [2]);
                this.date=day+"/"+month+"/"+year;
                break;

            case "dd-MM-yyyy":
                datearray = date.split("-");
                this.day=Integer.parseInt(datearray [0]);
                this.month=Integer.parseInt(datearray [1]);
                this.year=Integer.parseInt(datearray [2]);
                this.date=day+"/"+month+"/"+year;
                break;

            case "yyyy/MM/dd":
                datearray = date.split("/");
                this.day=Integer.parseInt(datearray [2]);
                this.month=Integer.parseInt(datearray [1]);
                this.year=Integer.parseInt(datearray [0]);
                this.date=day+"/"+month+"/"+year;
                break;

            case "yyyy-MM-dd":
                datearray = date.split("-");
                this.day=Integer.parseInt(datearray [2]);
                this.month=Integer.parseInt(datearray [1]);
                this.year=Integer.parseInt(datearray [0]);
                this.date=day+"/"+month+"/"+year;
                break;
        }
    }

    /*
     * CHECK
     * 
     */
    public boolean checkday () {
        int [] day = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        boolean confirm = false;

        if (this.getYear()%4 == 0){
            day[2] = 29;
        }

        if((this.getDay() >= 1) && (this.getDay() <= day[this.getMonth()])){
            confirm = true;
        }else{
            confirm = false;
        }

        return confirm;
    }

    public boolean checkmonth () {
        boolean confirm = false;

        if ((this.getMonth() >= 1) && (this.getMonth() <=12)) {
            confirm = true;
        }else{
            confirm = false;
        }

        return confirm;
    }

    public boolean checkyear () {
        boolean confirm = false;

        if ((this.getYear() >= 1900) && (this.getYear() <2100)){
            confirm = true;
        }else{
            confirm = false;
        }

        return confirm;
    }

    public boolean checkdate () {
        boolean confirm = true;

        if (this.checkday() == false) {
            confirm = false;
        }
        if (this.checkmonth() == false) {
            confirm = false;
        }
        if (this.checkyear() == false) {
            confirm = false;
        }

        return confirm;

    }

    /*
     * COMPARE
     * 
     */
    public int comparedate (ClassDate date) {
        Calendar date1 = null;
        Calendar date2 = null;

        date2 = date.StringtoCalendar();
        date1 = this.StringtoCalendar();
        int i = 0;
        if ( date1.before(date2) ) {        //when date1 is before to date2
            i = -1;
        }
        else if ( date1.after(date2) ) {        //when date1 is after to date2
            i = 1;
        }
        else if ( date1.compareTo(date2) == 0) {        //When date1 and date2 are equals
            i = 0;
        }

        return i;
    }

    public int comparesystemdate () {
        Calendar date1 = null;
        Calendar date2 = null;

        date2 = Calendar.getInstance();
        date1 = this.StringtoCalendar();
        int i = 0;
        if ( date1.before(date2) ) {        //Cuando date1 es posterior a date2
            i = -1;
        }
        else if ( date1.after(date2) ) {        //Cuando date1 es anterior a date2
            i = 1;
        }
        else if ( date1.compareTo(date2)==0) {      //Cuando date1 y date2 son iguales
            i = 0;
        }

        return i;
    }

    /*
     * SUBTRACT
     * 
     */
    public int subtractdateyear (ClassDate date) {
        int leastyear=0, year=0, completeyear=0, days=0, cont=0;
        GregorianCalendar date1 = new GregorianCalendar ();
        GregorianCalendar date2 = new GregorianCalendar ();

        date1.set(this.getYear(), this.getMonth()-1, this.getDay());
        date2.set(date.getYear(), date.getMonth()-1, date.getDay());
        long day = date2.getTimeInMillis() - date1.getTimeInMillis();
        days = (int) day / (1000 * 60 * 60 * 24); //convert to days
        for (int i=this.getYear(); i<=date.getYear(); i++) {
            if (i%4 == 0) {
                cont++;
            }
        }

        leastyear=cont*366;
        year=days-leastyear;
        completeyear=(year/365)+cont;

        return completeyear;
    }

    public int subtractdatemonth (ClassDate date) {
        Calendar date1 = null;
        Calendar date2 = null;
        int contrast=0;

        date2 = date.StringtoCalendar();
        date1 = this.StringtoCalendar();

        contrast=(date2.get(Calendar.MONTH))-(date1.get(Calendar.MONTH));

        return contrast;
    }

    public int subtractdateday (ClassDate date) {
        int  days=0;
        GregorianCalendar date1 = new GregorianCalendar ();
        GregorianCalendar date2 = new GregorianCalendar ();

        date1.set(this.getYear(), this.getMonth()-1, this.getDay());
        date2.set(date.getYear(), date.getMonth()-1, date.getDay());
        long day = date2.getTimeInMillis() - date1.getTimeInMillis();
        days = (int) day / (1000 * 60 * 60 * 24); //convert to days

        return days;
    }

    public int subtractsystemdateyear () {
        int leastyear=0, year=0, completeyear=0, cont=0;
        long days=0;
        GregorianCalendar cal1 = new GregorianCalendar ();
        GregorianCalendar cal2 = new GregorianCalendar ();

        cal1.set(this.getYear(), this.getMonth()-1, this.getDay());
        long day = cal2.getTimeInMillis() - cal1.getTimeInMillis();
        days = day / (1000 * 60 * 60 * 24); //convert to days
        for (int i=cal1.get(Calendar.YEAR)+1; i<=cal2.get(Calendar.YEAR); i++) {
            if (i%4 == 0) {
                cont++;
            }
        }

        leastyear=cont*366;
        year=(int)days-leastyear;
        completeyear=year/365;
        completeyear=completeyear+cont;

        return completeyear;
    }

    public int subtractsystemdatemonth () {
        Calendar date1 = null;
        Calendar date2 = null;
        int contrast=0;

        date2 = Calendar.getInstance();
        date1 = this.StringtoCalendar();

        contrast=(date2.get(Calendar.MONTH))-(date1.get(Calendar.MONTH));

        return contrast;
    }

    public int subtractsystemdateday () {
        int  days=0;
        GregorianCalendar date1 = new GregorianCalendar ();
        GregorianCalendar date2 = new GregorianCalendar ();

        date1.set(this.getYear(), this.getMonth()-1, this.getDay());
        long day = date2.getTimeInMillis() - date1.getTimeInMillis();
        days = (int) day / (1000 * 60 * 60 * 24); //convert to days

        return days;
    }

    /*
     * CONVERT CALENDAR TO STRING
     * 
     */
    public String CalendartoString (Calendar date) {
        int day, month, year;
        String dateS="", dateS2="XX/XX/XX";

        try {
        day=date.get(Calendar.DATE);
        month=((date.get(Calendar.MONTH))+1);
        year=date.get(Calendar.YEAR);
        dateS=day+"/"+month+"/"+year;
        }catch(Exception e) {
            return dateS2;
        }

        return dateS;
    }

    /*
     * CONVERT STRING TO CALENDAR
     * 
     */
    public Calendar StringtoCalendar () {
        Calendar date = Calendar.getInstance();

        try {
            date.set(this.getYear(), this.getMonth()-1, this.getDay());
        } catch (Exception e) {
            return date;
        }

        return date;
    }

    /*
     * TOSTRING(non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString () {
        StringBuffer string = new StringBuffer();
        switch (ClassConfig.getInstance().getDate()) {
            case "dd/MM/yyyy":
                string.append(String.format("%02d", this.getDay())+"/"+String.format("%02d", this.getMonth())+"/"+this.getYear());
                break;
                
            case "dd-MM-yyyy":
                string.append(String.format("%02d", this.getDay())+"-"+String.format("%02d", this.getMonth())+"-"+this.getYear());
                break;
                
            case "yyyy/MM/dd":
                string.append(this.getYear()+"/"+String.format("%02d", this.getMonth())+"/"+String.format("%02d", this.getDay()));
                break;
                
            case "yyyy-MM-dd":
                string.append(this.getYear()+"-"+String.format("%02d", this.getMonth())+"-"+String.format("%02d", this.getDay()));
                break;
        }
        
        return string.toString();
    }
}
