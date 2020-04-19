/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author Laci
 */
public class Connect {
    public Connection conn;
    public Connect() {
        try{
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/Menu";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myUrl, "root", "");
            /*
            String query = "SELECT * FROM asztalok";
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("id");
                int ferohely = rs.getInt("ferohely");
                System.out.format("%d, %d\n", id, ferohely);
            }
            st.close();
            */
        }
        catch (Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
     }
    
    public ResultSet getData(String select, String from, String where, String other){
        /*if ($where != "") {
            $where = " WHERE " . $where;
        }*/
        where = " WHERE " + where;

        try {
            String query = "SELECT " + select + " FROM `" + from + "`" + where + other;
            //$query = "SELECT " . $select . " FROM `" . $from . "`" . $where.((! empty($other)) ? " ".$other: "");
            //$result = $this->con->prepare($query);
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ///$result->execute();
            st.close();
            return rs;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
    
    public ResultSet getData(String select, String from, String where){
        /*if ($where != "") {
            $where = " WHERE " . $where;
        }*/
        where = " WHERE " + where;

        try {
            String query = "SELECT " + select + " FROM `" + from + "`" + where;
            //$query = "SELECT " . $select . " FROM `" . $from . "`" . $where.((! empty($other)) ? " ".$other: "");
            //$result = $this->con->prepare($query);
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ///$result->execute();
            st.close();
            return rs;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
    
    public ResultSet getData(String select, String from){
        /*if ($where != "") {
            $where = " WHERE " . $where;
        }*/
        //where = " WHERE " + where;

        try {
            String query = "SELECT " + select + " FROM `" + from + "`";
            //$query = "SELECT " . $select . " FROM `" . $from . "`" . $where.((! empty($other)) ? " ".$other: "");
            //$result = $this->con->prepare($query);
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ///$result->execute();
            
            return rs;
            //st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
    
}
