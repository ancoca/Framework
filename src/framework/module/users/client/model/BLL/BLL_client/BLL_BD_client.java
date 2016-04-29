/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.users.client.model.BLL.BLL_client;

import framework.module.users.client.model.DAO.DAO_BD_client;
import framework.module.users.client.model.classes.Client;

/**
 *
 * @author angel
 */
public class BLL_BD_client {
    
    /**
     * CREATE USER
     * @param c1 
     */
    public static void create_BD (Client c1) {
        DAO_BD_client.create_BD(c1);
    }
    
    /**
     * UPDATE USER
     * @param c1 
     */
    public static void update_BD (Client c1) {
        DAO_BD_client.update_BD(c1);
    }
    
    /**
     * DELETE USER
     * @param c1 
     */
    public static void delete_BD (Client c1) {
        DAO_BD_client.delete_BD(c1);
    }
    
    /**
     * DELETE USER UPDATE
     * @param dni 
     */
    public static void delete_BD_update (String dni) {
        DAO_BD_client.delete_BD_update(dni);
    }
    
    /**
     * BD TO ARRAYLIST
     */
    public static void BDtoArrayList () {
        DAO_BD_client.BDtoArray();
    }
    
    /**
     * FIND USER
     */
    public static void find_BD (String dni) {
        DAO_BD_client.find_BD(dni);
    }
}
