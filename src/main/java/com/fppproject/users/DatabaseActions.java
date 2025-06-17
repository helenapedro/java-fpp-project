package com.fppproject.users;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseActions {
     public static void main(String[] args) {
          Properties props = new Properties();

          try (InputStream input = DatabaseActions
                    .getClassLoader()
                    .getResourceAsStream("db.properties")) {
               props.load(input);
          }
     }
}
