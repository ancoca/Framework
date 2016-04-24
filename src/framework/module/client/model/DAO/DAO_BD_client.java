/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.client.model.DAO;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import framework.module.client.model.classes.Client;
import framework.module.client.model.classes.Singleton_client;
import static framework.classes.Singleton_general.*;

/**
 *
 * @author angel
 */
public class DAO_BD_client {
    
    /**
     * INSERT USER
     * @param c1 
     */
    public static void create_BD (Client c1) {
        collection.insert(c1.Client_to_BD());
    }
    
    /**
     * UPDATE USER
     * @param c1 
     */
    public static void update_BD (Client c1) {
        //Prepara para insertar un nuevo campo
        BasicDBObject update = new BasicDBObject();
        update.append("$set", c1.Client_to_BD());
 
        //Busca el/los registro/s con el nombre indicado
        BasicDBObject searchById = new BasicDBObject();
        searchById.append("DNI", c1.getDNI());
 
        //Realiza la actualización
        collection.updateMulti(searchById, update);
    }
    
    /**
     * DELETE USER
     * @param c1 
     */
    public static void delete_BD (Client c1) {
        collection.remove(new BasicDBObject().append("DNI", c1.getDNI()));
    }
    
    /**
     * DELETE USER UPDATE
     * @param dni 
     */
    public static void delete_BD_update (String dni) {
        collection.remove(new BasicDBObject().append("DNI", dni));
    }
    
    /**
     * BD TO ARRAYLIST
     */
    public static void BDtoArray (){
        DBCursor cursor = null;
        String rdo = "";
        Client c1 = new Client();
        try {
            cursor = collection.find().sort(new BasicDBObject("DNI", -1)).limit(10);
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
