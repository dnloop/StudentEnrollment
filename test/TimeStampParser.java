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

/**
 *
 * @author dnloop
 */

import java.sql.Connection;
import java.sql.Date;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import motor.Conector;
import motor.Consulta;

/**
 *
 * @author Emiliano Rodriguez
 */
public class TimeStampParser {
    public static void main(String[] args) throws SQLException {
        String sql = "SELECT fechaMesa FROM mesas WHERE idMesa=1";
        //String timeSql = null;
        Conector con = new Conector();
        Connection db = con.conexion();
        try {
            Statement stmt = db.createStatement();
        
            Consulta query = new Consulta(stmt);
            ResultSet rs = query.consulta(sql);
        
        Timestamp time1 = null; // db time
        Timestamp time2 = new Timestamp(System.currentTimeMillis()); // actual time
        Timestamp timeRes = null;
        //System.out.println(timeStamp);
        if (rs.next()) {
            time1 = rs.getTimestamp(1); // param = columna rs
        }
        
        //query results
        System.out.println(time1); // from sql
        System.out.println(time2); // sys time

        //long diff = time1.getTime() - time2.getTime();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /* Tuve que convertir la fecha del formato date a sql.Date, de lo
         * contrario no me iba a tomar el dato para convertir, ahora me falta
         * pasar a dias para saber la diferencia para saber si puedo inscribir o no
         */
        //Date fecha1 = (Date) sdf.parse(time1.toString());
        java.sql.Date sqlDate1 = new java.sql.Date(sdf.parse(time1.toString()).getTime());
        //Date fecha2 = (Date) sdf.parse(time2.toString());
        java.sql.Date sqlDate2 = new java.sql.Date(sdf.parse(time2.toString()).getTime());
        //java.util.Date utilDate = new java.util.Date();

        System.out.println(sqlDate1.getTime() - sqlDate2.getTime()); // long to date =)


//        timeRes = new Timestamp(diff); 
//        
//        System.out.println(diff);
//        System.out.println(timeRes);
        /* BEGIN Tracer */
        //sql = "SELECT idMesa FROM mesas WHERE fechaMesa= (?)";
        //PreparedStatement ps = con.prepareStatement(sql);
        //ps.setTimestamp(1, time1);
//        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time1);
//        sql = "SELECT idMesa FROM mesas WHERE fechaMesa="+ '"' + timeStamp + '"';
//        // query again
//        rs = query.consulta(sql);
//        if (rs.next()) {
//            System.out.printf("%-8s\t", rs.getObject(1));
//        }
//        
//        } catch(SQLException ex){
//            System.out.println(ex.getMessage());
        }catch(Exception e){System.out.println(e.getMessage());}
        /* END Tracer */
    }
}
