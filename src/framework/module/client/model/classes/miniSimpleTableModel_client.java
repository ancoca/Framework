package framework.module.client.model.classes;
import framework.classes.ClassDate;
import framework.module.admin.model.functions.pagina_admin;
import framework.module.admin.model.classes.Admin;
import framework.module.admin.view.List_admin;
import framework.module.client.controller.Controller_client;
import framework.module.client.model.functions.json_auto_client;
import framework.module.client.view.List_client;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
//import static pager.modules.actores.interfaz_actor.combo;
//import static pager.modules.actores.singleton.AL_actores;
//import pager.modules.pager.pagina;
//import static pager.resources.fnes.fnes.*;

public class miniSimpleTableModel_client extends AbstractTableModel {
    public static ArrayList<Client> datos = new ArrayList<Client>();
    public static ArrayList<Client> datosaux = new ArrayList<Client>();
    String[] columnas = {"DNI", "Nombre", "Apellidos", "Fecha de alta", "Compras"};

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
        Client fila = (Client) datos.get(row);

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
                dev = fila.getDateup().toString();
                break;
                
            case 4:
                dev = new Float (fila.getShopping());

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
        Client fila = (Client) datos.get(row);

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
                fila.setDateup(new ClassDate(value.toString()));
                break;
                
            case 4:
                fila.setShopping(Float.parseFloat(value.toString()));
                break;

        }
        fireTableCellUpdated(row, col);
    }

    public void addRow(Client client) {
        datos.add(client);
        fireTableDataChanged();
    }

    public void cargar() {
        datos.clear();
        datosaux.clear();
        
        json_auto_client.openjson_client();
        
        Client client = null;
        java.util.Date date= new java.util.Date();
        for(int i=0;i<=(Singleton_client.userclient.size()-1);i++) {
            //admin = new Admin(i, getDNI(), getCadenaAleatoria2(8), new Timestamp(date.getTime()));
            client = Singleton_client.userclient.get(i);
            addRow(client);
            datosaux.add(client);
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
        
        String nom=(String) ((JComboBox)Controller_client.combo).getSelectedItem();   
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

    public Client buscar(String u) {
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

    public int buscaUsuario(Client client) {
        datos.clear();
        cargar();

        for (int i = 0; i < datos.size(); i++) {
            if (datos.get(i).equals(client)) {
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
