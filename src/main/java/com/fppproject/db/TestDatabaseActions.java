package com.fppproject.db;

import java.util.List;

import com.fppproject.users.DatabaseActions;
import com.fppproject.users.Person;

public class TestDatabaseActions {
     public static void insertPerson(String firstName, String lastName, String ssn) {
          // Insert test
          DatabaseActions.insertPerson("Alan", "Turing", "123456789");

          // Fetch and print
          List<Person> people = DatabaseActions.getAllPersons();
          people.forEach(System.out::println);
     }
}
