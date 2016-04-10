package framework.module.admin.model.classes;

import framework.classes.ClassDate;
import framework.module.admin.controller.Controller_admin;
import framework.module.admin.model.BLL.BLL_admin.BLL_BD;
import framework.module.admin.model.functions.pagina_admin;
import framework.module.admin.model.classes.Admin;
import framework.module.admin.model.classes.language.Language_admin;
import framework.module.admin.model.functions.json_auto_admin;
import framework.module.admin.view.List_admin;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
public class miniSimpleTableModel_admin extends AbstractTableModel {
    public static ArrayList<Admin> datos = new ArrayList<Admin>();
    public static ArrayList<Admin> datosaux = new ArrayList<Admin>();
    String[] columnas = {Language_admin.getInstance().getProperty("DNI"), Language_admin.getInstance().getProperty("name"),
        Language_admin.getInstance().getProperty("surname"), Language_admin.getInstance().getProperty("datecontract"), 
        Language_admin.getInstance().getProperty("salary")};

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
        Admin fila = (Admin) datos.get(row);

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
                dev = fila.getDatecontract().toString();
                break;
                
            case 4:
                dev = new Float (fila.getSalary());

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
        Admin fila = (Admin) datos.get(row);

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
                fila.setDate_contract(new ClassDate(value.toString()));
                break;
                
            case 4:
                fila.setSalary(Float.parseFloat(value.toString()));
                break;

        }
        fireTableCellUpdated(row, col);
    }

    public void addRow(Admin admin) {
        datos.add(admin);
        fireTableDataChanged();
    }

    public void cargar() {
        datos.clear();
        datosaux.clear();
        
        boolean correcto = BLL_BD.BDtoArrayList();
        
        if (correcto == true){
            Admin admin = null;
            java.util.Date date= new java.util.Date();
            for(int i=0;i<=(Singleton_admin.useradmin.size()-1);i++) {
                //admin = new Admin(i, getDNI(), getCadenaAleatoria2(8), new Timestamp(date.getTime()));
                admin = Singleton_admin.useradmin.get(i);
                addRow(admin);
                datosaux.add(admin);
                //Singleton_user.useradmin.add(admin);
                try {
                    Thread.sleep(1); //1 milliseconds
                } catch (Exception e) {
                  System.out.println(e);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Los usuarios no han podido ser cargados");
        }
    }

    public void filtrar() {
        datos.clear();
        int cont=0;
        
        String nom=(String) ((JComboBox)Controller_admin.combo).getSelectedItem();   
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

    public Admin buscar(String u) {
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

    public int buscaUsuario(Admin admin) {
        datos.clear();
        cargar();

        for (int i = 0; i < datos.size(); i++) {
            if (datos.get(i).equals(admin)) {
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
