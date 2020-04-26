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
import java.sql.ResultSetMetaData;
import javafx.scene.control.Alert;

/**
 *
 * @author Laci
 */
public class Connect {
    public Connection conn;
    public ResultSet rs;
    private java.sql.Statement st;
    public Connect() {
        try{
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/menu";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myUrl, "root", "");
        }
        catch (Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setHeaderText(null);
            alert.setContentText("Hiba történt az adatbázishoz valo csatlakozáskor: " + e.getMessage());
            alert.showAndWait();
        }
     }
    
    public String[] getData(String select, String from, String where, String other){
        where = " WHERE " + where;
        try {
            String query = "SELECT " + select + " FROM `" + from + "`" + where + other;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            String columnName[] = new String[count];
            for (int i = 1; i <= count; i++)
            {
               columnName[i-1] = metaData.getColumnLabel(i);
            }
            return columnName;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public String[] getData(String select, String from, String where){
        where = " WHERE " + where;
        try {
            String query = "SELECT " + select + " FROM `" + from + "`" + where;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            String columnName[] = new String[count];
            for (int i = 1; i <= count; i++)
            {
               columnName[i-1] = metaData.getColumnLabel(i);
            }
            return columnName;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public String[] getData(String select, String from){
        try {
            String query = "SELECT " + select + " FROM `" + from + "`";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            String columnName[] = new String[count];
            for (int i = 1; i <= count; i++)
            {
               columnName[i-1] = metaData.getColumnLabel(i);
            }
            return columnName;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public boolean insertData(String to, String columns, String values){
	try {
            String query = "INSERT INTO `" + to + "` (" + columns + ") VALUES (" + values + ")";
            st = conn.createStatement();
            st.executeUpdate(query);
            return true;
	} catch (Exception e) {
            System.err.println(e.getMessage());
	}
	return false;
    }
    
    public boolean updateData(String to, String set, String where) {
	try {
            String query = "UPDATE `" + to + "` SET " + set + " WHERE " + where;
            st = conn.createStatement();
            st.executeUpdate(query);
            return true;
	} catch (Exception e) {
            System.err.println(e.getMessage());
	}
	return false;
    }
    
    public String[] getSQL(String query){
        String[] ki = new String[1];
	try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            String columnName[] = new String[count];
            for (int i = 1; i <= count; i++)
            {
               columnName[i-1] = metaData.getColumnLabel(i);
            }
            return columnName;
	} catch (Exception e) {
		System.err.println(e.getMessage());
	}

	return ki;
    }

    @Override
    protected void finalize() throws Throwable {
        st.close();
    }
}
