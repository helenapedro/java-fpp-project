package com.fppproject.users;

public class Person {
     // Instance variables
     private int id;
     private String firstname;
     private String lastname;
     private String ssn;

     // Constructor
     public Person(int id, String firstname, String lastname, String ssn) {
          this.id = id;
          this.firstname = firstname;
          this.lastname = lastname;
          this.ssn = ssn;
     }

     @Override
     public String toString() {
          return String.format("%d: %s %s (%s)", id, firstname, lastname, ssn);
     }

     // Getters (needed for future GUI usage)
     public int getId() {
          return id;
     }

     public String getFirstname() {
          return firstname;
     }

     public String getLastname() {
          return lastname;
     }

     public String getSsn() {
          return ssn;
     }
}
