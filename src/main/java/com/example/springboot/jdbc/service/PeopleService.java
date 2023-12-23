package com.example.springboot.jdbc.service;

import com.example.springboot.jdbc.model.Person;
import com.example.springboot.jdbc.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class PeopleService {
    private static Scanner sc = new Scanner(System.in);
    private PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Autowired
    public static void setSc(Scanner sc) {
        PeopleService.sc = sc;
    }

    public void printPeopleSchema() {
        if (peopleRepository.getPeopleList().isEmpty()) {
            System.out.println("\nNo records found\n");
            return;
        }
        System.out.println();
        System.out.printf("%-10s %-20s\n", "id", "first_name");
        System.out.println("---------------------");

        for (Person person : peopleRepository.getPeopleList()) {
            System.out.printf("%-10d %-20s\n", person.getId(), person.getFirstName());
        }
        System.out.println();
    }

    public void savePerson() {
        System.out.println("\n--------------------------");

        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();

        StringBuilder sql = new StringBuilder("");
        sql.append("INSERT INTO people (id, first_name) VALUES (?, ?) ");
        peopleRepository.save(sql.toString(), id, firstName);
        peopleRepository.refresh();
        System.out.println("--------------------------\n");
    }

    public void updatePerson() {
        System.out.println("\n--------------------------");
        System.out.print("Enter old ID: ");
        int oldID = Integer.parseInt(sc.nextLine());

        if (!containsID(peopleRepository.getPeopleList(), oldID)) {
            System.out.println("This person doesn't exist, try again");
            return;
        }

        System.out.print("Enter new first name: ");
        String newFirstName = sc.nextLine();
        System.out.print("Enter new ID: ");
        int newID = Integer.parseInt(sc.nextLine());

        StringBuilder sql = new StringBuilder("");
        sql.append("UPDATE people ");
        sql.append("SET first_name = ?, id = ? ");
        sql.append("WHERE id = ?;");

        peopleRepository.update(sql.toString(), newFirstName, newID, oldID);
        peopleRepository.refresh();
        System.out.println("--------------------------\n");
    }

    public void deletePerson() {
        System.out.println("\n--------------------------");
        System.out.print("Enter an ID to remove its record: ");
        int id = Integer.parseInt(sc.nextLine());
        if (!containsID(peopleRepository.getPeopleList(), id)) {
            System.out.println("ID doesn't exist, try again");
            return;
        }

        StringBuilder sql = new StringBuilder("");
        sql.append("DELETE FROM people ");
        sql.append("WHERE id = ? ;");

        peopleRepository.delete(sql.toString(), id);
        peopleRepository.refresh();
        System.out.println("--------------------------\n");

    }

    private boolean containsID(List<Person> people, int id) {
        for (Person person : people) {
            if (person.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
