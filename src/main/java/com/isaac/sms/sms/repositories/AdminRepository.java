package com.isaac.sms.sms.repositories;

import com.isaac.sms.sms.db.DbConnector;
import com.isaac.sms.sms.models.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminRepository extends DbConnector {
      public Admin save(Admin admin){
           return null;
      }

      public ArrayList<Admin> getAllAdmins(){

          try{
              String sql ="SELECT id,firstName,lastName,email FROM Admins;";
              Statement statement = getConnection().createStatement();
              ResultSet resultSet = statement.executeQuery(sql);
              ArrayList<Admin> admins = new ArrayList<Admin>();

              while (resultSet.next()){
                  Admin admin = new Admin();
                  admin.setId(resultSet.getInt(1));
                  admin.setFirstName(resultSet.getString(2));
                  admin.setLastName(resultSet.getString(3));
                  admin.setEmail(resultSet.getString(4));
                  admins.add(admin);
              }

              return admins;
          }
          catch (Exception e){
              System.out.println("Exception" + e.getMessage());
              return null;
          }
      }

      public Optional<Admin> findById(int id){
          try{

          }
          catch (Exception e){
              System.out.println("Exception" + e.getMessage());
          }
           Optional<Admin> admin  = Optional.of(new Admin());
      }

      public Optional<Admin> findByEmail(String email){
          return null;
      }

      public int deleteById(int id){
          return null;
      }

      public int updateById(int id){
          return null;
      }
}
