package com.fppproject.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Person {
     // Instance variables
     private int id;
     private String firstname;
     private String lastname;
     private String ssn;
}
