/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.classes;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author angel
 */
public class Singleton_general {
    
    public static Properties prop = new Properties();
    public static InputStream input = null;
    public static String machine = null;
    public static String port = null;
    public static Mongo client = null;
    public static DB db = null;
    public static String nom_bd = null;
    public static DBCollection collection = null;
    public static String nom_table = null;
    public static Mongo_BD mongo = null;
    
}
