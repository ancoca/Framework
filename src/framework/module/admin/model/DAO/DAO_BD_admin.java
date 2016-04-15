/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.admin.model.DAO;

import com.mysql.jdbc.Connection;
import framework.classes.ClassDate;
import framework.module.admin.model.classes.Admin;
import framework.module.admin.model.classes.Singleton_admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author angel
 */
public class DAO_BD_admin {
    
    public static boolean create_BD (Connection connection, Admin a1) {
        boolean correcto = false;
        PreparedStatement statement = null;
        int state = 0;
        
        try{
            statement=connection.prepareStatement("INSERT INTO admin "
                    + "(DNI, user, pass, avatar, state, name, surname, email, mobilephone, "
                    + "datebirthday, age, benefits, datecontract, old, salary, incentive, activity) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, a1.getDNI());
            statement.setString(2, a1.getUser());
            statement.setString(3, a1.getPass());
            statement.setString(4, a1.getAvatar());
            if (a1.getState()==true){
                state = 1;
            }else{
                state = 0;
            }
            statement.setInt(5, state);
            statement.setString(6, a1.getName());
            statement.setString(7, a1.getSurname());
            statement.setString(8, a1.getEmail());
            statement.setString(9, a1.getMobilephone());
            statement.setString(10, a1.getDatebirthday().toStringBD());
            statement.setInt(11, a1.getAge());
            statement.setFloat(12, a1.getBenefits());
            statement.setString(13, a1.getDatecontract().toStringBD());
            statement.setInt(14, a1.getOld());
            statement.setFloat(15, a1.getSalary());
            statement.setFloat(16, a1.getIncentive());
            statement.setInt(17, a1.getActivity());

            statement.executeUpdate();
            correcto = true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Ha habido un problema al insertar un nuevo usuario!");
            correcto = false;
        }finally{
            if (statement != null){
                try{
                    statement.close();
                    correcto = true;
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "Ha habido un error en la Base de Datos");
                    correcto = false;
                }
            }
        }
        return correcto;
    }
    
    public static boolean update_BD (Connection connection, Admin a1) {
        boolean correcto = false;
        PreparedStatement statement = null;
        int state = 0;
        
        try{
            statement=connection.prepareStatement("UPDATE admin SET "
                    + "DNI=?, user=?, pass=?, avatar=?, state=?, name=?, surname=?, "
                    + "email=?, mobilephone=?, datebirthday=?, age=?, benefits=?, "
                    + "datecontract=?, old=?, salary=?, incentive=?, activity=? WHERE DNI=?");

            statement.setString(1, a1.getDNI());
            statement.setString(2, a1.getUser());
            statement.setString(3, a1.getPass());
            statement.setString(4, a1.getAvatar());
            if (a1.getState()==true){
                state = 1;
            }else{
                state = 0;
            }
            statement.setInt(5, state);
            statement.setString(6, a1.getName());
            statement.setString(7, a1.getSurname());
            statement.setString(8, a1.getEmail());
            statement.setString(9, a1.getMobilephone());
            statement.setString(10, a1.getDatebirthday().toStringBD());
            statement.setInt(11, a1.getAge());
            statement.setFloat(12, a1.getBenefits());
            statement.setString(13, a1.getDatecontract().toStringBD());
            statement.setInt(14, a1.getOld());
            statement.setFloat(15, a1.getSalary());
            statement.setFloat(16, a1.getIncentive());
            statement.setInt(17, a1.getActivity());
            statement.setString(18, a1.getDNI());

            statement.executeUpdate();
            correcto = true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Ha habido un problema al modificar un usuario!");
            correcto = false;
        }finally{
            if (statement != null){
                try{
                    statement.close();
                    correcto = true;
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "Ha habido un error en la Base de Datos");
                    correcto = false;
                }
            }
        }
        return correcto;
    }
    
    public static boolean delete_BD (Connection connection, Admin a1) {
        boolean correcto = false;
        PreparedStatement statement = null;
        
        try{
            statement = connection.prepareStatement("DELETE FROM admin WHERE DNI=?");
            statement.setString(1, a1.getDNI());
            statement.executeUpdate();
            correcto = true;
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Ha habido un error al eliminar el usuario!");
            correcto = false;
        }finally{
            if (statement != null){
                try{
                    statement.close();
                    correcto = true;
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Ha habido un error en la Base de Datos");
                    correcto = false;
                }
            }
        }
        return correcto;
    }
    
    public static boolean delete_BD_update (Connection connection, String dni) {
        boolean correcto = false;
        PreparedStatement statement = null;
        
        try{
            statement = connection.prepareStatement("DELETE FROM admin WHERE DNI=?");
            statement.setString(1, dni);
            statement.executeUpdate();
            correcto = true;
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Ha habido un error al eliminar el usuario!");
            correcto = false;
        }finally{
            if (statement != null){
                try{
                    statement.close();
                    correcto = true;
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Ha habido un error en la Base de Datos");
                    correcto = false;
                }
            }
        }
        return correcto;
    }
    
    public static boolean BDtoArrayList (Connection connection) {
        boolean correcto = false;
        ResultSet result = null;
        PreparedStatement statement = null;
        
        Singleton_admin.useradmin.clear();
        
        try {
            statement = connection.prepareStatement("SELECT * FROM admin");
            result = statement.executeQuery();
            Admin a1 = null;
            
            while (result.next()){
                a1 = new Admin();
                boolean state = false;
                
                a1.setDNI(result.getString("DNI"));
                a1.setUser(result.getString("user"));
                a1.setPass(result.getString("pass"));
                a1.setAvatar(result.getString("avatar"));
                if (result.getInt("state")==1){
                    state = true;
                }else{
                    state = false;
                }
                a1.setState(state);
                a1.setName(result.getString("name"));
                a1.setSurname(result.getString("surname"));
                a1.setEmail(result.getString("email"));
                a1.setMobilephone(result.getString("mobilephone"));
                a1.setDatebirthday(new ClassDate(result.getString("datebirthday"), "BD"));
                a1.setAge(result.getInt("age"));
                a1.setBenefits(result.getFloat("benefits"));
                a1.setDate_contract(new ClassDate(result.getString("datecontract"), "BD"));
                a1.setOld(result.getInt("old"));
                a1.setSalary(result.getFloat("salary"));
                a1.setIncentive(result.getFloat("incentive"));
                a1.setActivity(result.getInt("activity"));
                Singleton_admin.useradmin.add(a1);
                correcto = true;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Ha habido un problema al obtener los usuarios!");
            correcto = false;
        }finally{
            if (statement != null){
                try{
                    statement.close();
                    correcto = true;
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Ha habido un error en la Base de Datos");
                    correcto = false;
                }
            }
        }
        
        return correcto;
    }
    
    public static boolean find_BD (Connection connection, Admin a1) {
        boolean correcto = false;
        PreparedStatement statement = null;
        ResultSet result = null;
        
        try{
            statement = connection.prepareStatement("SELECT * FROM admin WHERE DNI=?");
            statement.setString(1, a1.getDNI());
            result = statement.executeQuery();
            
            while (result.next()){
                show_BD(result, a1);
            }
            
            correcto = true;
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Ha habido un error al eliminar el usuario!");
            correcto = false;
        }finally{
            if (result != null){
                try{
                    result.close();
                    correcto = true;
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Ha habido un error en la Base de Datos");
                    correcto = false;
                }
            }
            if (statement != null){
                try{
                    statement.close();
                    correcto = true;
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Ha habido un error en la Base de Datos");
                    correcto = false;
                }
            }
        }
        return correcto;
    }
    
    private static boolean show_BD (ResultSet result, Admin a1) {
        boolean correcto = false, state = false;
        try {
            a1.setDNI(result.getString("DNI"));
            a1.setUser(result.getString("user"));
            a1.setPass(result.getString("pass"));
            a1.setAvatar(result.getString("avatar"));
            if (result.getInt("state")==1){
                state = true;
            }else{
                state = false;
            }
            a1.setState(state);
            a1.setName(result.getString("name"));
            a1.setSurname(result.getString("surname"));
            a1.setEmail(result.getString("email"));
            a1.setMobilephone(result.getString("mobilephone"));
            a1.setDatebirthday(new ClassDate(result.getString("datebirthday"), "BD"));
            a1.setAge(result.getInt("age"));
            a1.setBenefits(result.getFloat("benefits"));
            a1.setDate_contract(new ClassDate(result.getString("datecontract"), "BD"));
            a1.setOld(result.getInt("old"));
            a1.setSalary(result.getFloat("salary"));
            a1.setIncentive(result.getFloat("incentive"));
            a1.setActivity(result.getInt("activity"));
            correcto=true;
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Ha habido un error en la Base de Datos");
        }
        
        return correcto;
    }
}
