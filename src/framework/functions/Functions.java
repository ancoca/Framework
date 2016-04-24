package framework.functions;

import javax.swing.JOptionPane;
import framework.classes.language.Language_general;

/**
 * 
 * @author angel
 */
public class Functions {

    /**
     * CHECK INT
     * @param mensaje
     * @param titulo
     * @return 
     */
    public static int validaint(String mensaje, String titulo) {
        String s="";
        int n=0;
        boolean continuar=false;

        //bucle introducir y validar numero
        do{
            try{
                s=JOptionPane.showInputDialog(null, mensaje,titulo,JOptionPane.QUESTION_MESSAGE);
                if(s==null){
                    //JOptionPane.showMessageDialog(null, "Saliendo de la aplicación","Saliendo",JOptionPane.INFORMATION_MESSAGE);
                    //System.exit(0);//al usuario pulsar cancelar o cerrar la vtna del showinputdialog, acaba la ejecución
                }else {
                    n=Integer.parseInt(s);
                    continuar=true;
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, Language_general.getInstance().getProperty("interror"), Language_general.getInstance().getProperty("errortitle"),JOptionPane.ERROR_MESSAGE);
                continuar=false;
            }
        }while(continuar==false);

        return n;
    }

    /**
     * CHECK INT DISTINT 0
     * @param mensaje
     * @param titulo
     * @return 
     */
    public static int validaintdistint0(String mensaje, String titulo) {
        String s="";
        int n=0;
        boolean continuar=false;

        do{
        //bucle introducir y validar numero
            do{
                try{
                    s=JOptionPane.showInputDialog(null, mensaje,titulo,JOptionPane.QUESTION_MESSAGE);
                    if(s==null){
                        //JOptionPane.showMessageDialog(null, "Saliendo de la aplicación","Saliendo",JOptionPane.INFORMATION_MESSAGE);
                        //System.exit(0);//al usuario pulsar cancelar o cerrar la vtna del showinputdialog, acaba la ejecución
                    }else {
                        n=Integer.parseInt(s);
                        continuar=true;
                    }
                    if (n==0){
                        JOptionPane.showMessageDialog(null, Language_general.getInstance().getProperty("int0error"), Language_general.getInstance().getProperty("errortitle"), JOptionPane.ERROR_MESSAGE);
                        continuar=false;
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, Language_general.getInstance().getProperty("int0error"), Language_general.getInstance().getProperty("errortitle"),JOptionPane.ERROR_MESSAGE);
                    continuar=false;
                }
            }while(continuar==false);
        }while(n==0);
        
        return n;
    }

    /**
     * CHECK FLOAT
     * @param mensaje
     * @param titulo
     * @return 
     */
    public static float validafloat(String mensaje, String titulo) {
        String s="";
        float n=0.0f;
        boolean continuar=false;

        //bucle introducir y validar numero con decimales
        do{
            try{
                s=JOptionPane.showInputDialog(null, mensaje,titulo,JOptionPane.QUESTION_MESSAGE);
                if(s==null){
                    //JOptionPane.showMessageDialog(null, "Saliendo de la aplicación","Saliendo",JOptionPane.INFORMATION_MESSAGE);
                    //System.exit(0);//al usuario pulsar cancelar o cerrar la vtna del showinputdialog, acaba la ejecución
                }else {
                    n=Float.parseFloat(s);
                    continuar=true;
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, Language_general.getInstance().getProperty("floaterror"), Language_general.getInstance().getProperty("errortitle"),JOptionPane.ERROR_MESSAGE);
                continuar=false;
            }
        }while(continuar==false);

        return n;
    }

    /**
     * CHECK CHAR
     * @param mensaje
     * @param titulo
     * @return 
     */
    public static char validachar(String mensaje, String titulo) {
        String s="";
        char c=' ';
        boolean continuar=false;

        //bucle introducir y validar letra
        do{
            try{
                s=JOptionPane.showInputDialog(null, mensaje,titulo,JOptionPane.QUESTION_MESSAGE);
                if(s==null){
                    //JOptionPane.showMessageDialog(null, "Saliendo de la aplicación","Saliendo",JOptionPane.INFORMATION_MESSAGE);
                    //System.exit(0);//al usuario pulsar cancelar o cerrar la vtna del showinputdialog, acaba la ejecución
                }else {
                    c=s.charAt(0);
                    continuar=true;
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, Language_general.getInstance().getProperty("charerror"), Language_general.getInstance().getProperty("errortitle"),JOptionPane.ERROR_MESSAGE);
                continuar=false;
            }
        }while(continuar==false);

        return c;
    }

    /**
     * CHECK STRING
     * @param mensaje
     * @param titulo
     * @return 
     */
    public static String validaString(String mensaje, String titulo) {
        String s="";
        boolean continuar=false;

        //bucle introducir y validar palabra/s
        do{
            try{
                s=JOptionPane.showInputDialog(null, mensaje,titulo,JOptionPane.QUESTION_MESSAGE);
                continuar=true;
                if (s==null){
                    //JOptionPane.showMessageDialog(null, "Saliendo de la aplicación","Saliendo",JOptionPane.INFORMATION_MESSAGE);
                    //System.exit(0);//al usuario pulsar cancelar o cerrar la vtna del showinputdialog, acaba la ejecución
                }
                if(s.equals("")){
                    JOptionPane.showMessageDialog(null, Language_general.getInstance().getProperty("stringerror"), Language_general.getInstance().getProperty("errortitle"),JOptionPane.ERROR_MESSAGE);
                    continuar=false;
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, Language_general.getInstance().getProperty("stringerror"), Language_general.getInstance().getProperty("errortitle"),JOptionPane.ERROR_MESSAGE);
                continuar=false;
            }
        }while(continuar==false);

        return s;
    }	
    
    /**
     * DOUBLE TO FLOAT
     * @param num
     * @return 
     */
    public static float Double_to_Float (double num) {
        float n= 0.0f;
        
        n=Float.parseFloat(num+"");
        
        return n;
    }
}
