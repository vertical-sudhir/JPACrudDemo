package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
//@Controller                 
public class EmpController {

	@Autowired
	EmployeeRepository emprepo;

	@RequestMapping("/viewemp")
	private List<Employee> viewAllEmployee() {
		return (List<Employee>) emprepo.findAll();
	}

	/*
	 * this method add new Employee but does not return any details about newly
	 * registerd epmloyee only just sent Text Msg
	 */

	@RequestMapping(value = "/addemp", method = RequestMethod.POST)
	private String addEmployee(@RequestBody Employee emp) {

		emprepo.save(emp);

		return "New Employee Added Sucessfully...";
	}

	/*
	 * this method add new Employee also send back to the registerd employee Details
	 */

	@RequestMapping(value = "/addemp1", method = RequestMethod.POST)
	private ResponseEntity<Employee> addAndGetEmployee(@RequestBody Employee emp) {

		Employee newlyaddedemp = emprepo.save(emp);

		ResponseEntity<Employee> newEmp = new ResponseEntity<Employee>(newlyaddedemp, HttpStatus.ACCEPTED);
		return newEmp;
	}

	@RequestMapping(value = "/updateemployee", method = RequestMethod.PATCH)
	private ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp) {

		Employee e1 = emprepo.save(emp);
		ResponseEntity<Employee> res = new ResponseEntity<Employee>(e1, HttpStatus.ACCEPTED);
		return res;
	}

	@RequestMapping(value = "/updateemployee/{eid}/{ename}", method = RequestMethod.PATCH)
	private ResponseEntity<Employee> updateEmployeeById(@PathVariable(name = "eid") Long Id,
			@PathVariable(name = "ename") String empname, @RequestBody Employee emp) {

		emp.setId(Id);
		emp.setEname(empname);

		Employee e1 = emprepo.save(emp);

		ResponseEntity<Employee> res = new ResponseEntity<Employee>(e1, HttpStatus.ACCEPTED);
		return res;
	}

	@RequestMapping(value = "/updateemployee1/{eid}/{ename}", method = RequestMethod.PATCH)
	private ResponseEntity<Employee> updateEmployeeByIdIfExist(@PathVariable(name = "eid") Long id,
			@PathVariable(name = "ename") String empname, @RequestBody Employee emp) {

		if (emprepo.existsById(id)) {

			System.out.println("in IF updateemployee1.............................");
			emp.setId(id);
			emp.setEname(empname);

			Employee e1 = emprepo.save(emp);

			ResponseEntity<Employee> res = new ResponseEntity<Employee>(e1, HttpStatus.ACCEPTED);
			return res;

		} else {

			System.out.println("in Else updateemployee1.............................");
			ResponseEntity<Employee> res = new ResponseEntity<Employee>(emp, HttpStatus.NOT_FOUND);
			return res;

		}
	}

	@RequestMapping(value = "/deleteemployeeById/{eid}", method = RequestMethod.DELETE)
	private ResponseEntity<String> deleteEmployeeById(@PathVariable(name = "eid") Long Id) {

		if (emprepo.existsById(Id)) {
			emprepo.deleteById(Id);

			ResponseEntity<String> res = new ResponseEntity<String>("Employee Deleted Successfully...", HttpStatus.OK);
			return res;

		} else {

			ResponseEntity<String> res = new ResponseEntity<String>("Employee Not Found...", HttpStatus.NOT_FOUND);
			return res;

		}
	}

}

/*
 * @RequestMapping("/updateemployee") private void updateEmpById() { // TODO
 * Auto-generated method stub
 * 
 * }
 * 
 * @RequestMapping("/deleteemp") private void deleteEmpById() { // TODO
 * Auto-generated method stub
 * 
 * }
 */
