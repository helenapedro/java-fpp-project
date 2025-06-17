package com.fppproject;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class MigrationRunner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("⚠️ Run database migration now? (yes/no): ");
            String inputResponse = scanner.nextLine();

            if (!inputResponse.equalsIgnoreCase("yes")) {
                System.out.println("🚫 Migration canceled.");
                return;
            }

            Properties props = new Properties();

            try (InputStream input = MigrationRunner.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties")) {

                if (input == null) {
                    System.out.println("❌ Could not find db.properties file.");
                    return;
                }

                props.load(input);
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");

                Flyway flyway = Flyway.configure()
                        .dataSource(url, user, password)
                        .load();

                flyway.migrate();
                System.out.println("✅ Migration completed successfully!");

            } catch (IOException e) {
                System.out.println("❌ Error reading db.properties file.");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("❌ Migration failed.");
                e.printStackTrace();
            }

            // ✅ Clean up lingering MySQL JDBC thread
            com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
        }
    }
}
