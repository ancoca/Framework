package framework.functions;

import javax.swing.JOptionPane;
import framework.classes.language.Language_general;

/**
 * 
 * @author angel
 */
public class Functions_menu {

    /**
     * CHECK COMBOBOX
     * @param mensdesp
     * @param titldesp
     * @param opciones
     * @return 
     */
    public static String combobox(String mensdesp, String titldesp, String [] opciones) {
        String op="";

        //menu desplegable
        Object seleccion = JOptionPane.showInputDialog(null, mensdesp, titldesp, JOptionPane.QUESTION_MESSAGE, null, opciones, 0);  // null para icono defecto

        if(seleccion==null){
            op="";
        }else{
            op=(String) seleccion;
        }
        
        return op;
    }


    /**
     * CHECK BOTON
     * @param tipo
     * @param mensboton
     * @param titlboton
     * @return 
     */
    public static int botones(String[] tipo, String mensboton, String titlboton) {
        int op=0;

        op=JOptionPane.showOptionDialog(null, mensboton, titlboton, 0, JOptionPane.QUESTION_MESSAGE,null,tipo,tipo[0]);

        return op;
    }


    /**
     * CHECK CONTINUE
     * @param s
     * @return 
     */
    public static int continuar(String s){
        int op = 0;
        String [] menu_continue= { s, Language_general.getInstance().getProperty("return"), Language_general.getInstance().getProperty("exit") };

        op=JOptionPane.showOptionDialog(null,Language_general.getInstance().getProperty("askcontinue"), Language_general.getInstance().getProperty("continuetitle"),0, JOptionPane.QUESTION_MESSAGE,null,
                    menu_continue, menu_continue[0]);
        switch(op){
            case 0:		//Continuar
                op = 0;
                break;
                
            case 1:		//Volver
                op = 1;
                break;
                
            case 2:		//Terminar o salir
                JOptionPane.showMessageDialog(null, Language_general.getInstance().getProperty("goodbye"));
                System.exit(0);
                break;
                
            default:	//Si el usuario pulsa cerrar ventana
                JOptionPane.showMessageDialog(null, Language_general.getInstance().getProperty("goodbye"));
                System.exit(0);
                break;
        }
        
        return op;
    }


    /**
     * YES OR NO
     * @param mensboton
     * @return 
     */
    public static boolean YES_NO(String mensboton) {
        boolean valor=false;
        int option=0;
        String [] menu_yes_no= { Language_general.getInstance().getProperty("YES"),
                    Language_general.getInstance().getProperty("NO") };

        option=Functions_menu.botones(menu_yes_no, mensboton, Language_general.getInstance().getProperty("asktitle"));
        if (option==0){
            valor=true;
        }
        if (option==1){
            valor=false;
        }

        return valor;
    }
}
