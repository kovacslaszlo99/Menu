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

    public Connect() {
        try{
            // create our mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/Menu";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM asztalok";

            // create the java statement
            java.sql.Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs;
            rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()){
                int id = rs.getInt("id");
                int ferohely = rs.getInt("ferohely");
                System.out.format("%d, %d\n", id, ferohely);
            }
            st.close();
        }
        catch (Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
     }
    
}
