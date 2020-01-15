package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class MotorPrepareSQL {

    /*Objetos necesarios para hablar con la BD*/
//1º--> Conexión - Connection
//2º--> Hablar en SQL (evitar inyección SQL) - PreparedStatement
//3º--> Recoger datos - Resultset
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
// ¿Dónde está la Base de Datos?
    private static final String URL
            = "jdbc:mysql://localhost/Ecommerce";
    private static final String CONTROLADOR
            = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "";

// Métodos de lectura y manipulación de 
// la Base de Datos
    public Connection connect() {
        try {
//¿Qué controlador necesito?
            Class.forName(CONTROLADOR);
// ¿Dónde está la BD, user y pass?
            conn = DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        //Devuelve el objeto conexión
        return conn;
    }

// Consultas DDL - Data Definition Language
// Generally DROP TABLE or DATABASE, INSERT into TABLE, UPDATE TABLE, DELETE from TABLE statements 
    public int execute(PreparedStatement pst) {
        // Con el objeto conexión me creo un objeto PrepareStatement

        int resp = 0;
        try {
            // El PrepareStatement se pasa por parámetro
//            pst = conn.prepareStatement(sql);
            resp = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resp;
    }
// Consultas DML - Data Manipulation Language
// Generally SELECT statement is used. 

    public ResultSet executeQuery(PreparedStatement pst) {
        try {
            // El PrepareStatement se pasa por parámetro
//            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }

    public void disconnect() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {

        }
    }
}
