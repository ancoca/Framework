/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.client.model.BLL.BLL_client;

import framework.classes.PoolConexion;
import framework.module.client.model.DAO.DAO_BD_client;
import framework.module.client.model.classes.Client;
import java.sql.Connection;


/**
 *
 * @author angel
 */
public class BLL_BD_client {
    
    public static boolean create_BD (Client c1) {
        boolean correcto = false;
        Connection connection = null;
        
        connection = (Connection) PoolConexion.OcuparConexion();
        
        correcto = DAO_BD_client.create_BD(connection, c1);
        
        PoolConexion.LiberarConexion(connection);
        
        return correcto;
    }
    
    public static boolean update_BD (Client c1) {
        boolean correcto = false;
        Connection connection = null;
        
        connection = (Connection) PoolConexion.OcuparConexion();
        
        correcto = DAO_BD_client.update_BD(connection, c1);
        
        PoolConexion.LiberarConexion(connection);
        
        return correcto;
    }
    
    public static boolean delete_BD (Client c1) {
        boolean correcto = false;
        Connection connection = null;
        
        connection = (Connection) PoolConexion.OcuparConexion();
        
        correcto = DAO_BD_client.delete_BD(connection, c1);
        
        PoolConexion.LiberarConexion(connection);
        
        return correcto;
    }
    
    public static boolean delete_BD_update (String dni) {
        boolean correcto = false;
        Connection connection = null;
        
        connection = (Connection) PoolConexion.OcuparConexion();
        
        correcto = DAO_BD_client.delete_BD_update(connection, dni);
        
        PoolConexion.LiberarConexion(connection);
        
        return correcto;
    }
    
    public static boolean BDtoArrayList () {
        boolean correcto = false;
        Connection connection = null;
        
        connection = (Connection) PoolConexion.OcuparConexion();
        
        correcto = DAO_BD_client.BDtoArrayList(connection);
        
        PoolConexion.LiberarConexion(connection);
        
        return correcto;
    }
    
    public static boolean find_BD (Client c1) {
        boolean correcto = false;
        Connection connection = null;
        
        connection = (Connection) PoolConexion.OcuparConexion();
        
        correcto = DAO_BD_client.find_BD(connection, c1);
        
        PoolConexion.LiberarConexion(connection);
        
        return correcto;
    }
}
