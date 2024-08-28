/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameoflife;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rsautter
 */
public class BDConnection {
    private static BDConnection db;
    
    private Connection c;
    private BDConnection(){
        Connection c = null;
    }
    public static Connection getConnection() throws SQLException{
        if(db==null){
            db=new BDConnection();
            db.c = DriverManager.getConnection("jdbc:mysql://localhost/jogo-vida","root","");
        }
        return db.c;
    }
}
