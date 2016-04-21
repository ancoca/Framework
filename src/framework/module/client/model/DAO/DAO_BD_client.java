/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.client.model.DAO;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import framework.module.client.model.classes.Client;
import framework.module.client.model.classes.Singleton_client;


/**
 *
 * @author angel
 */
public class DAO_BD_client {
    
    public static void create_BD (DB db, DBCollection table, Client c1) {
        table.insert(c1.Client_to_BD());
    }
    
    public static void update_BD (DB db, DBCollection table, Client c1) {
        //Prepara para insertar un nuevo campo
        BasicDBObject update = new BasicDBObject();
        update.append("$set", c1.Client_to_BD());
 
        //Busca el/los registro/s con el nombre indicado
        BasicDBObject searchById = new BasicDBObject();
        searchById.append("DNI", c1.getDNI());
 
        //Realiza la actualización
        table.updateMulti(searchById, update);
    }
    
    public static void delete_BD (DB db, DBCollection table, Client c1) {
        table.remove(new BasicDBObject().append("DNI", c1.getDNI()));
    }
    
    public static void delete_BD_update (DB db, DBCollection table, String dni) {
        table.remove(new BasicDBObject().append("DNI", dni));
    }
    
    public static void BDtoArray (DB db, DBCollection table){
        DBCursor cursor = null;
        String rdo = "";
        Client c1 = new Client();
        try {
            cursor = table.find().sort(new BasicDBObject("DNI", -1)).limit(10);
            if(cursor.count()!=0){
                while(cursor.hasNext()){
                    BasicDBObject document = (BasicDBObject) cursor.next();
                    c1 = c1.BD_to_Client(document);
                    Singleton_client.userclient.add(c1);
                }
            }else{
                System.out.println("NOT DATA"); 
            }
        } finally {
            if (cursor != null){
		cursor.close();
            }
	}
    }
}
