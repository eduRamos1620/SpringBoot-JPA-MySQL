package com.ramos.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.ramos.springboot.jpa.springboot_jpa.entities.Person;
import com.ramos.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		create();
	}

	@Transactional
	public void create(){
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		String lastname = sc.next();
		String programmingLanguage = sc.next();
		sc.close();

		Person person = new Person(null, name, lastname, programmingLanguage); 

		Person personNew = repository.save(person);
		System.out.println(personNew);
		repository.findById(personNew.getId()).ifPresent(p -> System.out.println(p));
	}

	@Transactional(readOnly = true)
	public void findOne(){
		Person person = null;
		Optional<Person> optionalPerson = repository.findOneLikeName("In");
		if (optionalPerson.isPresent()) {
			person = optionalPerson.get();
		}
		System.out.println(person);
	}

	@Transactional(readOnly = true)
	public void list(){
		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.findByProgrammingLanguage("Java");
		List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("Java", "Eduardo");

		persons.stream().forEach(person -> {
			System.out.println(person);
		});

		List<Object[]> personsValues = repository.obtenerPersonData();
		personsValues.stream().forEach(person -> {
			System.out.println(person[0] + " es experto en " + person[1]);
		});
	}
}
