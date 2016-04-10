/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.admin.model.BLL.BLL_admin;

import com.mysql.jdbc.Connection;
import framework.classes.ConexionBD;
import framework.module.admin.model.DAO.DAO_BD;
import framework.module.admin.model.classes.Admin;

/**
 *
 * @author angel
 */
public class BLL_BD {
    
    public static boolean create_BD (Admin a1) {
        boolean correcto = false;
        Connection connection = null;
        ConexionBD conexionBD = new ConexionBD();
        
        connection = ConexionBD.AbrirConexion();
        
        correcto = DAO_BD.create_BD(connection, a1);
        
        ConexionBD.CerrarConexion((com.mysql.jdbc.Connection) connection);
        
        return correcto;
    }
    
    public static boolean update_BD (Admin a1) {
        boolean correcto = false;
        Connection connection = null;
        ConexionBD conexionBD = new ConexionBD();
        
        connection = ConexionBD.AbrirConexion();
        
        correcto = DAO_BD.update_BD(connection, a1);
        
        ConexionBD.CerrarConexion((com.mysql.jdbc.Connection) connection);
        
        return correcto;
    }
    
    public static boolean delete_BD (Admin a1) {
        boolean correcto = false;
        Connection connection = null;
        ConexionBD conexionBD = new ConexionBD();
        
        connection = ConexionBD.AbrirConexion();
        
        correcto = DAO_BD.delete_BD(connection, a1);
        
        ConexionBD.CerrarConexion((com.mysql.jdbc.Connection) connection);
        
        return correcto;
    }
    
    public static boolean delete_BD_update (String dni) {
        boolean correcto = false;
        Connection connection = null;
        ConexionBD conexionBD = new ConexionBD();
        
        connection = ConexionBD.AbrirConexion();
        
        correcto = DAO_BD.delete_BD_update(connection, dni);
        
        ConexionBD.CerrarConexion((com.mysql.jdbc.Connection) connection);
        
        return correcto;
    }
    
    public static boolean BDtoArrayList () {
        boolean correcto = false;
        Connection connection = null;
        ConexionBD conexionBD = new ConexionBD();
        
        connection = ConexionBD.AbrirConexion();
        
        correcto = DAO_BD.BDtoArrayList(connection);
        
        ConexionBD.CerrarConexion((com.mysql.jdbc.Connection) connection);
        
        return correcto;
    }
    
    public static boolean find_BD (Admin a1) {
        boolean correcto = false;
        Connection connection = null;
        ConexionBD conexionBD = new ConexionBD();
        
        connection = ConexionBD.AbrirConexion();
        
        correcto = DAO_BD.find_BD(connection, a1);
        
        ConexionBD.CerrarConexion((com.mysql.jdbc.Connection) connection);
        
        return correcto;
    }
}
