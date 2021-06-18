/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Utilisateurs;
import Util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thepoet
 */
public class UtilisateursService {
    
    Connection connection;

    public UtilisateursService() {
        connection = DataSource.getInstance().getCnx();
    }
    
    
    
    public void add(Utilisateurs u) {
      String req = "INSERT INTO `user`(`username`, `email`, `password`, `date`, `lastname`,`image`, `phone`,`enabled` ,`roles`) VALUES(?,?,?,?,?,?,?,?,?)"; 
       PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, u.getFIRST_NAME());          
            preparedStatement.setString(2, u.getEMAIL());
            preparedStatement.setString(3, u.getPASSWORD());
            preparedStatement.setDate  (4, (u.getDate()));
            preparedStatement.setString(5, u.getLAST_NAME());
            preparedStatement.setString(6, u.getIMAGE());
            preparedStatement.setString(7, u.getPHONE());
            preparedStatement.setInt   (8, 0);
            preparedStatement.setString(9, "a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
          
            preparedStatement.executeUpdate();
            
             System.out.println("User added successfully");
        } catch (SQLException ex) {
             System.out.println(ex);
        }
    }

  
    public void update(Utilisateurs u) {
        String req = "UPDATE user set username=?,email=?,password=?,date=?,lastname=?,image=?,phone=?,enabled=? WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, u.getFIRST_NAME());
            preparedStatement.setString(2, u.getEMAIL());
            preparedStatement.setString(3, u.getPASSWORD());
            preparedStatement.setDate  (4, (u.getDate()));
            preparedStatement.setString(5, u.getLAST_NAME());
            preparedStatement.setString(6, u.getIMAGE());
            preparedStatement.setString(7, u.getPHONE());
            preparedStatement.setInt   (8, u.getENABLED());
            preparedStatement.setString(9, u.getROLE());
            
            
            preparedStatement.executeUpdate();
            System.out.println("User updated successfully");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

 
    public int remove(Utilisateurs u)throws SQLException {
        
        String req = "DELETE FROM user WHERE EMAIL =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.executeUpdate();
            System.out.println("User Deleted successfully");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }
    
    public List<Utilisateurs> getAll() {
        List<Utilisateurs> usersList = new ArrayList<>();
        String req = "SELECT * FROM user";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 Utilisateurs u = new Utilisateurs(
                         resultSet.getInt   ("ID"),
                         resultSet.getInt   ("ENABLED"),
                         resultSet.getString("PHONE"),
                         resultSet.getString("USERNAME"),
                         resultSet.getString("LASTNAME"),
                         resultSet.getString("EMAIL"),
                         resultSet.getString("PASSWORD"),
                         resultSet.getString("IMAGE"),
                         resultSet.getDate  ("date"));
               
                usersList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return usersList;
    }
    
    
    
  

    public Utilisateurs findByMail(String email) {
       Utilisateurs u = null;
        String req = "SELECT * FROM user WHERE EMAIL =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 u = new Utilisateurs(
                         resultSet.getInt   ("ID"),
                         resultSet.getInt   ("ENABLED"),
                         resultSet.getString("USERNAME"),
                         resultSet.getString("LASTNAME"),
                         resultSet.getString("EMAIL"),
                         resultSet.getString("PASSWORD")

                         
                      
                         );
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return u;
    }
    public Utilisateurs findByROLE(int status) {
        Utilisateurs u = null;
        String req = "SELECT * FROM user WHERE ENABLED =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 u = new Utilisateurs(
                         resultSet.getString("USERNAME"),
                         resultSet.getString("EMAIL"),
                         resultSet.getString("PASSWORD"),
                         resultSet.getDate  ("date"),
                         resultSet.getString("LASTNAME"),
                         resultSet.getString("PHONE"),
                         resultSet.getString("IMAGE"),
                         resultSet.getInt   ("ENABLED"));
                               
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return u;
    }
    
    
    
    public List<Utilisateurs> getEmail(String email) {
        List<Utilisateurs> usersList = new ArrayList<>();
        String req = "SELECT * FROM user where EMAIL = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Utilisateurs u = new Utilisateurs(
                         resultSet.getString("FIRST_NAME"),
                         resultSet.getString("EMAIL"),
                         resultSet.getString("PASSWORD"),
                         resultSet.getDate  ("date"),
                         resultSet.getString("LAST_NAME"),
                         resultSet.getString("PHONE"),
                         resultSet.getString("IMAGE"),
                         resultSet.getInt   ("ENABLED"));
                usersList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return usersList;
    }
    
    
     public List<Utilisateurs> getRole(int ENABLED) {
        List<Utilisateurs> usersList = new ArrayList<>();
        String req = "SELECT * FROM user where ENABLED =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, ENABLED);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 Utilisateurs u = new Utilisateurs(
                         resultSet.getString("FIRST_NAME"),
                         resultSet.getString("EMAIL"),
                         resultSet.getString("PASSWORD"),
                         resultSet.getDate  ("date"),
                         resultSet.getString("LAST_NAME"),
                         resultSet.getString("PHONE"),
                         resultSet.getString("IMAGE"),
                         resultSet.getInt   ("ENABLED"));
                usersList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return usersList;
    }
  
   
}
