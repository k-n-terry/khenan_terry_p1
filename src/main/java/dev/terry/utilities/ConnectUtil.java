package dev.terry.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectUtil{
    public static Connection createConnect(){
        try{
            Connection conn = DriverManager.getConnection(System.getenv("KHENAN_TERRY_P1"));
            return conn;
        }catch(SQLException e){
            Logger.log(e.getMessage(), LogLevel.ERROR);
            System.out.println("Connection failed!!!");
            return null;
        }
    }
}
