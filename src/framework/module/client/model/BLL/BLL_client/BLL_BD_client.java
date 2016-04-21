/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.client.model.BLL.BLL_client;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import framework.classes.Mongo_BD;
import framework.classes.PoolConexion;
import framework.module.client.model.DAO.DAO_BD_client;
import framework.module.client.model.classes.Client;
import java.sql.Connection;


/**
 *
 * @author angel
 */
public class BLL_BD_client {
    
    public static void create_BD (Client c1) {
        DB db = Mongo_BD.getDb();
        DBCollection table = Mongo_BD.getCollection();
        DAO_BD_client.create_BD(db, table, c1);
    }
    
    public static void update_BD (Client c1) {
        DB db = Mongo_BD.getDb();
        DBCollection table = Mongo_BD.getCollection();
        DAO_BD_client.update_BD(db, table, c1);
    }
    
    public static void delete_BD (Client c1) {
        DB db = Mongo_BD.getDb();
        DBCollection table = Mongo_BD.getCollection();
        DAO_BD_client.delete_BD(db, table, c1);
    }
    
    public static void delete_BD_update (String dni) {
        DB db = Mongo_BD.getDb();
        DBCollection table = Mongo_BD.getCollection();
        DAO_BD_client.delete_BD_update(db, table, dni);
    }
    
    public static void BDtoArrayList () {
        DB db = Mongo_BD.getDb();
        DBCollection table = Mongo_BD.getCollection();
        DAO_BD_client.BDtoArray(db, table);
    }
}
