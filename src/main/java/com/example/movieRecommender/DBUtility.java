package com.example.movieRecommender;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {
	
	private static Connection connection = null;

    public static Connection getConnection() throws Exception {
            if (connection != null)
                    return connection;
            else {
                    // Store the database URL in a string
                    String serverName = "sdcjntor05";
                    String portNumber = "1521";
                    String sid = "dcjuat01";
                    String dbUrl = "jdbc:oracle:thin:@" + serverName + ":" + portNumber
                                    + ":" + sid;
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    // set the url, username and password for the databse
                    connection = DriverManager.getConnection(dbUrl, "CIECHECKPOINT", "CIECHECKPOINT");
                    return connection;
            }
    }

}
