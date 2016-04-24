/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.admin.model.BLL.BLL_admin;

import framework.classes.PoolConexion;
import framework.module.admin.model.DAO.DAO_BD_admin;
import framework.module.admin.model.classes.Admin;
import java.sql.Connection;

/**
 *
 * @author angel
 */
public class BLL_BD_admin {
    
    /**
     * CREATE USER
     * @param a1
     * @return 
     */
    public static boolean create_BD (Admin a1) {
        boolean correcto = false;
        Connection connection = null;
        
        connection = PoolConexion.OcuparConexion();
        
        correcto = DAO_BD_admin.create_BD(connection, a1);
        
        PoolConexion.LiberarConexion(connection);
        
        return correcto;
    }
    
    /**
     * UPDATE USER
     * @param a1
     * @return 
     */
    public static boolean update_BD (Admin a1) {
        boolean correcto = false;
        Connection connection = null;
        
        connection = PoolConexion.OcuparConexion();
        
        correcto = DAO_BD_admin.update_BD(connection, a1);
        
        PoolConexion.LiberarConexion(connection);
        
        return correcto;
    }
    
    /**
     * DELETE USER
     * @param a1
     * @return 
     */
    public static boolean delete_BD (Admin a1) {
        boolean correcto = false;
        Connection connection = null;
        
        connection = PoolConexion.OcuparConexion();
        
        correcto = DAO_BD_admin.delete_BD(connection, a1);
        
        PoolConexion.LiberarConexion(connection);
        
        return correcto;
    }
    
    /**
     * DELETE USER UPDATE
     * @param dni
     * @return 
     */
    public static boolean delete_BD_update (String dni) {
        boolean correcto = false;
        Connection connection = null;
        
        connection = PoolConexion.OcuparConexion();
        
        correcto = DAO_BD_admin.delete_BD_update(connection, dni);
        
        PoolConexion.LiberarConexion(connection);
        
        return correcto;
    }
    
    /**
     * BD TO ARRAYLIST
     * @return 
     */
    public static boolean BDtoArrayList () {
        boolean correcto = false;
        Connection connection = null;
        
        connection = PoolConexion.OcuparConexion();
        
        correcto = DAO_BD_admin.BDtoArrayList(connection);
        
        PoolConexion.LiberarConexion(connection);
        
        return correcto;
    }
    
    /**
     * FIND USER IN THE DATA BASE
     * @param a1
     * @return 
     */
    public static boolean find_BD (Admin a1) {
        boolean correcto = false;
        Connection connection = null;
        
        connection = PoolConexion.OcuparConexion();
        
        correcto = DAO_BD_admin.find_BD(connection, a1);
        
        PoolConexion.LiberarConexion(connection);
        
        return correcto;
    }
}
