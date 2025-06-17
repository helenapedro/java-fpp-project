package com.fppproject.users;

import java.io.InputStream;
import java.security.Permission;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseActions {
     private static final String PROPERTIES_FILE = "db.properties";

     // Registering the Driver and Getting the Connection
     private static Connection() throws SQLException {
          Properties props = new Properties();
          try (InputStream input = DatabaseActions.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
               props.load(input);

          } catch (Exception e) {
               throw new RuntimeException("❌ Could not load db.properties", e);
          }
          
          String url = props.getProperty("db.url");
          String user = props.getProperty("db.user");
          String password = props.getProperty("db.password");

          return DriverManager.getConnection(url, user, password)
     }

     // 1. Insert a person (to be used by Swing GUI later)
     public static void insertPerson(String firstName, String lastName, String ssn) {
          String sql = "INSERT INTO person (firstname, lastname, ssn) VALUES (?, ?, ?)";
          try (Connection conn = getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql)) {
               stmt.setString(1, firstName);
               stmt.setString(2, lastName);
               stmt.setString(3, ssn);
               stmt.executeUpdate();
               System.out.println("✅ Inserted: " + firstName + " " + lastName);
          } catch (SQLException e) {
               System.out.println("❌ Insert failed.");
               e.printStackTrace();
          }
     }

     // 2. Get all persons as a List (for GUI to display)
     public static List<Person> getAllPersons() {
          List<Person> list = new ArrayList<>();
          String sql = "SELECT * FROM person";

          try (Connection conn = getConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

               while (rs.next()) {
                    Person p = new Person(
                              rs.getInt("id"),
                              rs.getString("firstname"),
                              rs.getString("lastname"),
                              rs.getString("ssn"));
                    list.add(p);
               }

          } catch (SQLException e) {
               System.out.println("❌ Failed to retrieve persons.");
               e.printStackTrace();
          }

          return list;
     }

}
