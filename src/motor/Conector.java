/* 
 * Copyright (C) 2017 dnloop
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package motor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    
    public PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("prepareStatemet exception.");
    } // Consulta parametrizada me trae problemas, hoy no es el dia.
}
