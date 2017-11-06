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