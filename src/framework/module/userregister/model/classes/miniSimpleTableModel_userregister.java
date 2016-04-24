package framework.module.userregister.model.classes;

import framework.classes.ClassDate;
import framework.module.admin.model.functions.pagina_admin;
import framework.module.admin.view.List_admin;
import framework.module.userregister.controller.Controller_userregister;
import framework.module.userregister.model.classes.language.Language_userregister;
import framework.module.userregister.model.functions.json_auto_userregister;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author angel
 */
public class miniSimpleTableModel_userregister extends AbstractTableModel {
    
    public static ArrayList<User_register> datos = new ArrayList<User_register>();
    public static ArrayList<User_register> datosaux = new ArrayList<User_register>();
    String[] columnas = {Language_userregister.getInstance().getProperty("DNI"), Language_userregister.getInstance().getProperty("name"), 
        Language_userregister.getInstance().getProperty("surname"), Language_userregister.getInstance().getProperty("datebirthday"), 
        Language_userregister.getInstance().getProperty("point")};

    ////////////////////estos métodos son necesarios para que jtable funcione/////////////////////
    @Override
    public String getColumnName(int col) {
        return columnas[col].toString();
    }

    //Devuelve el numero de filas
    @Override
    public int getRowCount() {
        return datos.size();
    }

    //Devuelve el numero de columnas
    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    //Devuelve el valor del objeto en la fila y columna
    @Override
    public Object getValueAt(int row, int col) {

        Object dev = null;
        User_register fila = (User_register) datos.get(row);

        switch (col) {
            case 0:
                dev = fila.getDNI();
                break;

            case 1:
                dev = fila.getName();
                break;

            case 2:
                dev = fila.getSurname();
                break;

            case 3:
                dev = fila.getDatebirthday().toString();
                break;
                
            case 4:
                dev = new Integer (fila.getPoint());

        }
        return dev;
    }

    //Determina si una fila y columna ha de ser editable
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    //Actualiza un objeto de una fila y columna
    @Override
    public void setValueAt(Object value, int row, int col) {
        User_register fila = (User_register) datos.get(row);

        switch (col) {
            case 0:
                fila.setDNI(value.toString());
                break;

            case 1:
                fila.setName(value.toString());
                break;

            case 2:
                fila.setSurname(value.toString());
                break;
            
            case 3:
                fila.setDatebirthday(new ClassDate(value.toString()));
                break;
                
            case 4:
                fila.setPoint(Integer.parseInt(value.toString()));
                break;

        }
        fireTableCellUpdated(row, col);
    }

    public void addRow(User_register userregister) {
        datos.add(userregister);
        fireTableDataChanged();
    }

    public void cargar() {
        datos.clear();
        datosaux.clear();
        
        json_auto_userregister.openjson_userregister();
        
        User_register userregister = null;
        java.util.Date date= new java.util.Date();
        for(int i=0;i<=(Singleton_userregister.userregister.size()-1);i++) {
            //admin = new Admin(i, getDNI(), getCadenaAleatoria2(8), new Timestamp(date.getTime()));
            userregister = Singleton_userregister.userregister.get(i);
            addRow(userregister);
            datosaux.add(userregister);
            //Singleton_user.useradmin.add(admin);
            try {
                Thread.sleep(1); //1 milliseconds
            } catch (Exception e) {
              System.out.println(e);
            }
        }
    }

    public void filtrar() {
        datos.clear();
        int cont=0;
        
        String nom=(String) ((JComboBox)Controller_userregister.combo).getSelectedItem();   
        if(nom!=null){
            for(int i=0;i<datosaux.size();i++) {
                //if(datosaux.get(i).getFirst_name().contains(nom)){
                if(datosaux.get(i).getName().toLowerCase().startsWith(nom.toLowerCase())){
                    addRow(datosaux.get(i));
                    cont++;
                }
            }
            List_admin.lblsize.setText(String.valueOf(cont));
            System.out.println("word selected: " + nom);
            pagina_admin.initLinkBox();
        }
    }

    public User_register buscar(String u) {
        datos.clear();
        cargar();

        String res;
        for (int i = 0; i < datos.size(); i++) {
            res = datos.get(i).toString();
            if (res.contains(u)) {
                return datos.get(i);
            }
        }
        return null;
    }

    public int buscaUsuario(User_register userregister) {
        datos.clear();
        cargar();

        for (int i = 0; i < datos.size(); i++) {
            if (datos.get(i).equals(userregister)) {
                return i;
            }
        }
        return -1;
    }

    public void removeRow(int fila) {
        datos.remove(fila);
        fireTableDataChanged();
    }
}
