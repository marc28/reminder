package com.example.marc.sqlstatements;

/**
 * Created by marc on 03/10/2015.
 */
public class SqlStatements {
   public static String createDatabase(String tableName,String col_id,String col_context, String col_important){
       return "CREATE TABLE IF NOT EXISTS " + tableName +" ( "+
               col_id+" INTEGER PRIMARY KEY autoincrement, " +
               col_context + " TEXT, " +
               col_important + "INTEGER );";
   }
}
