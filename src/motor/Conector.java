/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author dnloop
 */
public class Conector {
    
    protected String url;
    protected String usr;
    protected String pass;
    protected String srv;
    protected String port;
    protected String db;
    protected Connection conexion;

    /**
     * Conector a la base de datos
     * a través de la clase DriverManager 
     */
    
    /* Constructores */
    
    public Conector() {
        this.url = "jdbc:mysql://localhost:3306/instituto";
        this.usr="instituto";
        this.pass="pa0jiRUJmSlyKNLI";
    } // localhost
    
    public Conector(
            String srv, 
            String port, 
            String db, 
            String url, 
            String usr, 
            String pass
        ) {
        this.url = "jdbc:mysql://" + srv + ":" + port + "/" + db;
        this.usr= usr;
        this.pass= pass;
    } // remote
    
    /* Getters & Setters */

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSrv() {
        return srv;
    }

    public void setSrv(String srv) {
        this.srv = srv;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    public Connection conexion() {
        if(conexion == null){
            System.out.println("[Estableciendo conexión]");
            try{
                conexion = DriverManager.getConnection(this.url, this.usr, this.pass);
                System.out.println("[Conexión establecida]");
            } catch(SQLException e){
                System.out.println(e.getMessage());
                conexion=null;
            }
        }
        return conexion;
        
    } // public Connection conexion();
    
        public void desconectar() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
                System.out.println("[Conexión Terminada]");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    } // public void disconnect();
        
    static Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
