/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.client.model.DAO;

import framework.classes.ClassDate;
import framework.module.client.model.classes.Client;
import framework.module.client.model.classes.Singleton_client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author angel
 */
public class DAO_BD_client {
    
    public static boolean create_BD (Connection connection, Client c1) {
        boolean correcto = false;
        PreparedStatement statement = null;
        int state = 0, premium = 0;
        
        try{
            statement=connection.prepareStatement("INSERT INTO client "
                    + "(DNI, user, pass, avatar, state, name, surname, email, mobilephone, "
                    + "datebirthday, age, benefits, dateup, old, shopping, dtos, premium, typeclient) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, c1.getDNI());
            statement.setString(2, c1.getUser());
            statement.setString(3, c1.getPass());
            statement.setString(4, c1.getAvatar());
            if (c1.getState()==true){
                state = 1;
            }else{
                state = 0;
            }
            statement.setInt(5, state);
            statement.setString(6, c1.getName());
            statement.setString(7, c1.getSurname());
            statement.setString(8, c1.getEmail());
            statement.setString(9, c1.getMobilephone());
            statement.setString(10, c1.getDatebirthday().toStringBD());
            statement.setInt(11, c1.getAge());
            statement.setFloat(12, c1.getBenefits());
            statement.setString(13, c1.getDateup().toStringBD());
            statement.setInt(14, c1.getAntique());
            statement.setFloat(15, c1.getShopping());
            statement.setInt(16, c1.getDtos());
            if (c1.getPremium()==true){
                premium = 1;
            }else{
                premium = 0;
            }
            statement.setInt(17, premium);
            statement.setString(18, c1.getTypeclient());

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
    
    public static boolean update_BD (Connection connection, Client c1) {
        boolean correcto = false;
        PreparedStatement statement = null;
        int state = 0, premium = 0;
        
        try{
            statement=connection.prepareStatement("UPDATE client SET "
                    + "DNI=?, user=?, pass=?, avatar=?, state=?, name=?, surname=?, "
                    + "email=?, mobilephone=?, datebirthday=?, age=?, benefits=?, "
                    + "dateup=?, old=?, shopping=?, dtos=?, premium=?, typeclient=? WHERE DNI=?");

            statement.setString(1, c1.getDNI());
            statement.setString(2, c1.getUser());
            statement.setString(3, c1.getPass());
            statement.setString(4, c1.getAvatar());
            if (c1.getState()==true){
                state = 1;
            }else{
                state = 0;
            }
            statement.setInt(5, state);
            statement.setString(6, c1.getName());
            statement.setString(7, c1.getSurname());
            statement.setString(8, c1.getEmail());
            statement.setString(9, c1.getMobilephone());
            statement.setString(10, c1.getDatebirthday().toStringBD());
            statement.setInt(11, c1.getAge());
            statement.setFloat(12, c1.getBenefits());
            statement.setString(13, c1.getDateup().toStringBD());
            statement.setInt(14, c1.getAntique());
            statement.setFloat(15, c1.getShopping());
            statement.setInt(16, c1.getDtos());
            if (c1.getPremium()==true){
                premium = 1;
            }else{
                premium = 0;
            }
            statement.setInt(17, premium);
            statement.setString(18, c1.getTypeclient());
            statement.setString(19, c1.getDNI());

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
    
    public static boolean delete_BD (Connection connection, Client c1) {
        boolean correcto = false;
        PreparedStatement statement = null;
        
        try{
            statement = connection.prepareStatement("DELETE FROM client WHERE DNI=?");
            statement.setString(1, c1.getDNI());
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
            statement = connection.prepareStatement("DELETE FROM client WHERE DNI=?");
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
        
        Singleton_client.userclient.clear();
        
        try {
            statement = connection.prepareStatement("SELECT * FROM client");
            result = statement.executeQuery();
            Client c1 = null;
            
            while (result.next()){
                c1 = new Client();
                boolean state = false, premium = false;
                
                c1.setDNI(result.getString("DNI"));
                c1.setUser(result.getString("user"));
                c1.setPass(result.getString("pass"));
                c1.setAvatar(result.getString("avatar"));
                if (result.getInt("state")==1){
                    state = true;
                }else{
                    state = false;
                }
                c1.setState(state);
                c1.setName(result.getString("name"));
                c1.setSurname(result.getString("surname"));
                c1.setEmail(result.getString("email"));
                c1.setMobilephone(result.getString("mobilephone"));
                c1.setDatebirthday(new ClassDate(result.getString("datebirthday"), "BD"));
                c1.setAge(result.getInt("age"));
                c1.setBenefits(result.getFloat("benefits"));
                c1.setDateup(new ClassDate(result.getString("dateup"), "BD"));
                c1.setAntique(result.getInt("old"));
                c1.setShopping(result.getFloat("shopping"));
                c1.setDtos(result.getInt("dtos"));
                if (result.getInt("premium")==1){
                    premium = true;
                }else{
                    premium = false;
                }
                c1.setPremium(premium);
                c1.setTypeclient(result.getString("typeclient"));
                Singleton_client.userclient.add(c1);
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
    
    public static boolean find_BD (Connection connection, Client c1) {
        boolean correcto = false;
        PreparedStatement statement = null;
        ResultSet result = null;
        
        try{
            statement = connection.prepareStatement("SELECT * FROM client WHERE DNI=?");
            statement.setString(1, c1.getDNI());
            result = statement.executeQuery();
            
            while (result.next()){
                show_BD(result, c1);
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
    
    private static boolean show_BD (ResultSet result, Client c1) {
        boolean correcto = false, state = false, premium = false;
        try {
            c1.setDNI(result.getString("DNI"));
            c1.setUser(result.getString("user"));
            c1.setPass(result.getString("pass"));
            c1.setAvatar(result.getString("avatar"));
            if (result.getInt("state")==1){
                state = true;
            }else{
                state = false;
            }
            c1.setState(state);
            c1.setName(result.getString("name"));
            c1.setSurname(result.getString("surname"));
            c1.setEmail(result.getString("email"));
            c1.setMobilephone(result.getString("mobilephone"));
            c1.setDatebirthday(new ClassDate(result.getString("datebirthday"), "BD"));
            c1.setAge(result.getInt("age"));
            c1.setBenefits(result.getFloat("benefits"));
            c1.setDateup(new ClassDate(result.getString("datecontract"), "BD"));
            c1.setAntique(result.getInt("old"));
            c1.setShopping(result.getFloat("shopping"));
            c1.setDtos(result.getInt("dtos"));
            if (result.getInt("premium")==1){
                premium = true;
            }else{
                premium = false;
            }
            c1.setPremium(premium);
            c1.setTypeclient(result.getString("typeclient"));
            correcto=true;
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Ha habido un error en la Base de Datos");
        }
        
        return correcto;
    }
}
