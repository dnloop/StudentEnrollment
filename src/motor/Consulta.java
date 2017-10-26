/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dnloop
 */

public class Consulta extends Conector{
    private Statement objStmt;
    private ResultSet objResultSet;
    
    /* Constructor */
    public Consulta() {
        objStmt = null;
        objResultSet = null;
    }
    
    public Consulta(Statement objStmt) {
        this.objStmt = objStmt;
    }
    
    /* Getters & Setters */
    
    public Statement getObjstmt() {
        return objStmt;
    }

    public void setObjstmt(Statement objStmt) {
        this.objStmt = objStmt;
    }
    
    public ResultSet consulta(String sql){
        try {
            objResultSet = objStmt.executeQuery(sql);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return objResultSet;
    }
    
    public boolean cargarTabla(ResultSet objResultSet, JTable objTabla ) throws SQLException {
        /* Se le pasan los objetos ResultSet y JTable
         * 
         */
        boolean status = false;
        /* Necesaria la conversión de tipo para evitar incompatibilidad de objetos */
        DefaultTableModel modelo =  (DefaultTableModel) objTabla.getModel();
        objTabla.setModel(modelo);
        /* Extraer numero de columnas */
        ResultSetMetaData metaData = objResultSet.getMetaData();
        int numCol = metaData.getColumnCount();
        /***/
        if (objResultSet.isBeforeFirst()) {
            while(objResultSet.next()){
                 Object [] columna = new Object[numCol];
                 for (int i = 0; i < numCol; i++) {
                    columna[i] = objResultSet.getObject(i + 1);
                }
                 modelo.addRow(columna);
            }
            status = true;
        }
        return status;
    } // public void cargarTabla(ResultSet objResultSet, JTable objTabla ) throws SQLException
}