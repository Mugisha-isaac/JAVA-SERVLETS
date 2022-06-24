package com.isaac.sms.sms.repositories;

import com.isaac.sms.sms.db.DbConnector;
import com.isaac.sms.sms.models.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminRepository extends DbConnector {
      public Optional<Admin> save(Admin admin)  {
          try {
              String sql = "INSERT INTO admins(firstName,lastName,email,password) values (?,?,?,?)";
              PreparedStatement statement = getConnection().prepareStatement(sql);
              statement.setString(1, admin.getFirstName());
              statement.setString(2, admin.getLastName());
              statement.setString(3, admin.getEmail());
              statement.setString(4, admin.getPassword());

              int insertedCount = statement.executeUpdate();

              if(insertedCount <1){
                  return Optional.empty();
              }

              return findByEmail(admin.getEmail());

          }
          catch (Exception e){
              System.out.println("Exception" + e.getMessage());
              return Optional.empty();
          }

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
             String sql = "SELECT id,firstName,lastName,email FROM admins WHERE id=?;";
             PreparedStatement statement = getConnection().prepareStatement(sql);
             statement.setInt(1,id);
              ResultSet resultSet = statement.executeQuery(sql);
              Admin admin = null;
              while (resultSet.next()){
                  admin = new Admin();
                  admin.setId(resultSet.getInt(1));
                  admin.setFirstName(resultSet.getString(2));
                  admin.setLastName(resultSet.getString(3));
                  admin.setEmail(resultSet.getString(4));
              }

              if(admin.getFirstName() == null){
                   return  Optional.empty();
              }

              return Optional.of(admin);

          }
          catch (Exception e){
              System.out.println("Exception" + e.getMessage());
              return Optional.empty();
          }
      }

      public Optional<Admin> findByEmail(String email){
          try{
              String sql = "SELECT id,firstName,lastName,email FROM admins WHERE email=?;";
              PreparedStatement statement = getConnection().prepareStatement(sql);
              statement.setString(1,email);
              ResultSet resultSet = statement.executeQuery();
              Admin admin = null;
              while (resultSet.next()){
                  admin = new Admin();
                  admin.setId(resultSet.getInt(1));
                  admin.setFirstName(resultSet.getString(2));
                  admin.setLastName(resultSet.getString(3));
                  admin.setEmail(resultSet.getString(4));
              }

              if(admin.getFirstName() == null){
                  return  Optional.empty();
              }

              return Optional.of(admin);

          }
          catch (Exception e){
              System.out.println("Exception" + e.getMessage());
              return Optional.empty();
          }
      }

      public int deleteById(int id){

          try{
              String sql = "DELETE FROM admins WHERE id=?;";
              PreparedStatement statement = getConnection().prepareStatement(sql);
              statement.setInt(1,id);
              int count = statement.executeUpdate();
              return count;
          }
          catch (Exception e){
              System.out.println("Exception" + e.getMessage());
              return 0;
          }
      }

      public int updateById(int id, Admin admin){
          try{
              String sql = "UPDATE admins SET firstName=?,lastName=?,email=? WHERE id=?;";
              PreparedStatement statement = getConnection().prepareStatement(sql);
              statement.setInt(4,id);
              statement.setString(1,admin.getFirstName());
              statement.setString(1,admin.getLastName());
              statement.setString(3,admin.getEmail());

              int updateCount = statement.executeUpdate();
              return updateCount;
          }
          catch (Exception e){
              System.out.println("Exception" + e.getMessage());
              return 0;
          }
      }
}
