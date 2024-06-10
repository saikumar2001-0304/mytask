package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.entity.Person;
import com.task.enums.UserType;
import com.task.exception.PersonException;
import com.task.service.PersonServiceImpl;

@RestController
@RequestMapping("/person")
@CrossOrigin("*")
public class PersonController {

	@Autowired
	private PersonServiceImpl service;
	
	@PostMapping("/save/{userType}")
	public ResponseEntity<Person> addPerson(@RequestBody Person person,@PathVariable UserType userType ) throws PersonException{
		
		Person addPerson = service.addPerson(person,userType);
		
		return new ResponseEntity<>(addPerson,HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Person>getPersonById(@PathVariable(name = "id") Integer personId) throws PersonException{
		Person person = service.personById(personId);
		return new ResponseEntity<>(person,HttpStatus.OK);
	}
	@GetMapping("/get/{name}")
	public ResponseEntity<Person>getByName(@PathVariable(name = "name")String personName) throws PersonException{
		Person person = service.byName(personName);
		return new ResponseEntity<>(person,HttpStatus.OK);
	}
	@GetMapping("/list")
	public ResponseEntity<List<Person>> getList(){
		List<Person> list = service.getLsit();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person,@PathVariable(name = "id") Integer personId) throws PersonException{
		Person updatePerson = service.updatePerson(person, personId);
		return new ResponseEntity<>(updatePerson,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable(name = "id") Integer personId) throws PersonException{
		String deletePerson = service.deletePerson(personId);
		
		return new ResponseEntity<>(deletePerson,HttpStatus.OK);
	}                      
	
	@GetMapping("/income")
	public ResponseEntity<Integer>incomeTaxCount(){
		Integer incomeTaxCount = service.incomeTaxCount();
		return new ResponseEntity<>(incomeTaxCount,HttpStatus.OK);
	}
	@GetMapping("/gst")
	public ResponseEntity<Integer>gstCount(){
		Integer gstCount = service.gstCount();
		return new ResponseEntity<>(gstCount,HttpStatus.OK);
	}
	
	@GetMapping("/total")
	public ResponseEntity<Integer>totalCount(){
		Integer totalCount = service.totalCount();
		return new ResponseEntity<>(totalCount,HttpStatus.OK);
	}
	
	@GetMapping("/gstList")
	public ResponseEntity<List<Person>>gstTotal(){
		List<Person> gstList = service.gstList();
		return new ResponseEntity<>(gstList,HttpStatus.OK);
	}
	@GetMapping("/incomeList")
	public ResponseEntity<List<Person>>incomeList(){
		List<Person> incomeList = service.incomeList();
		return new ResponseEntity<>(incomeList,HttpStatus.OK);
	}
}
