package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@SpringBootApplication
public class JpaCrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaCrudDemoApplication.class, args);
	}

	@Autowired
	EmployeeRepository Emprepo;
	
	@PostConstruct
	public void postConstructMethod() {

		
		Employee e1 = new Employee("Sudhir Atkire", "sudhir@vertical.com","1");
		Employee e2 = new Employee("Priyanka Kad", "priyanka@vertical.com","1");
		
		Emprepo.save(e1);
		Emprepo.save(e2);
		
	}
}
