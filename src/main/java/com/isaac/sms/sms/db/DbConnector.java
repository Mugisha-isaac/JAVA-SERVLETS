package com.isaac.sms.sms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
      private String url = "jdbc:mysql://localhost:3306/student_sms";
      private String username = "isaac";
      private String password = "Mugisha12!";
      private Connection connection = null;

      public DbConnector(){}

      public Connection getConnection() throws ClassNotFoundException, SQLException {
            if(connection != null){
                  return  connection;
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            return connection;
      }

      public void CloseConnection(){
            try{
                  connection.close();
            }
            catch (SQLException e){
                  System.out.println("Exception"+ e.getMessage());
            }
      }
}
