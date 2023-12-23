package com.example.springboot.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PeopleInstruction {
    public void getInstruction() {
        StringBuilder s = new StringBuilder("");
        s.append("1. Add a person to the database\n");
        s.append("2. Modify a person's information in the database\n");
        s.append("3. Delete a person's information in the database\n");
        s.append("4. Print list of people\n");
        s.append("5. Exit the program\n");
        s.append("Select an option: ");
        System.out.print(s);
    }
}
