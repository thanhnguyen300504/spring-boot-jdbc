package com.example.springboot.jdbc;

import com.example.springboot.jdbc.service.PeopleInstruction;
import com.example.springboot.jdbc.service.PeopleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

import static java.lang.System.exit;

@SpringBootApplication
public class MainApplication {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MainApplication.class, args);
		PeopleService peopleService = context.getBean(PeopleService.class);
		int option;
		do {
			PeopleInstruction instruction = context.getBean(PeopleInstruction.class);
			instruction.getInstruction();
			option = sc.nextInt();
			switch (option) {
				case 1:
					peopleService.savePerson();
					break;
				case 2:
					peopleService.updatePerson();
					break;
				case 3:
					peopleService.deletePerson();
					break;
				case 4:
					peopleService.printPeopleSchema();
					break;
				case 5:
					System.out.println("exit");
					System.exit(0);
			}
		} while (option != 5);

	}

}
