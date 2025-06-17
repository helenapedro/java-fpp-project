package com.fppproject.users;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseActions {
     public static void main(String[] args) {
          Properties props = new Properties();

          try (InputStream input = DatabaseActions
                    .getClassLoader()
                    .getResourceAsStream("db.properties")) {
               props.load(input);
          } catch (Exception e) {
               System.out.println("❌ Could not load db.properties");
               return;
          }

          String url = props.getProperty("db.url");
          String user = props.getProperty("db.user");
          String password = props.getProperty("db.password");

          // Registering the Driver and Getting the Connection
          try (Connection conn = DriverManager.getConnection(url, user, password)) {
               System.out.println("✅ Connected to DB");

               // Creating a PreparedStatement (INSERT)
               String insertSQL = "INSERT INTO person (firstname, lastname, ssn) VALUES (?, ?, ?)";
               PreparedStatement insertStmt = conn.prepareStatement(insertSQL);
               insertStmt.setString(1, "Ada");
               insertStmt.setString(2, "Lovelace");
               insertStmt.setString(3, "1010101010");
               insertStmt.executeUpdate();
               System.out.println("✅ Inserted record");

               //
               ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM person");
               System.out.println("📄 People in the database:");
               while (rs.next()) {
                    System.out.printf("  %d: %s %s | SSN: %s%n",
                              rs.getInt("id"),
                              rs.getString("firstname"),
                              rs.getString("lastname"),
                              rs.getString("ssn"));
               }

          } catch (SQLException e) {
               System.out.println("❌ Database error");
               e.printStackTrace();
          }
     }

}
