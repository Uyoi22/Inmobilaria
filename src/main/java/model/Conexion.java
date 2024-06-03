package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection conn;
    private String host = "localhost";
    private String port = "3306";
    private String dbName = "Inmobilaria";
    private String userName = "root";
    private String password = "";
    
    public Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbName;
            System.out.println(url);
            conn = DriverManager.getConnection(url, this.userName, this.password);
            System.out.println("Conexion exitosa");
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error, no se conecto");
            System.out.println(e);
        }
    }
    
    public static void main(String[]args){
        Conexion c = new Conexion();
        
    }
}
