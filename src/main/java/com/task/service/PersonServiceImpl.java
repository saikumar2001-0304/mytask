package com.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.entity.Person;
import com.task.enums.UserType;
import com.task.exception.PersonException;
import com.task.repo.PersonRepository;


@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonRepository repo;
	
	@Override
	public Person addPerson(Person person,UserType userType) throws PersonException {
		
		if(repo.existsByUserName(person.getUserName())&& repo.existsByPassword(person.getPassword())) {
			throw new PersonException("person userName and password already exst");
		}else {
		person.setUserType(userType);
		Person save = repo.save(person);
		return save;
		}
	}

	@Override
	public Person personById(Integer personId) throws PersonException{
		
		Person person = repo.findById(personId).get();
		if(person.getId()==personId) {
			return person;
		}else {
			throw new PersonException("person id not found ");
		}
	}

	@Override
	public List<Person> getLsit() {
		
		List<Person> list = repo.sortList();
		return list;
	}

	@Override
	public Person updatePerson(Person person, Integer personId)throws PersonException {
		
		Person updatePerson = repo.findById(personId).get();
		if(updatePerson.getId()==personId) {
			updatePerson.setUserName(person.getUserName());
			updatePerson.setPassword(person.getPassword());
			Person save = repo.save(updatePerson);
			return save;
		}else {
			throw new PersonException("person id not found ");
		}
	}

	@Override
	public String deletePerson(Integer personId) throws PersonException{
		Person person = repo.findById(personId).get();
		if(person.getId()==personId) {
			repo.deleteById(personId);
			return "deleted successfully";
		}else {
			throw new PersonException("person id not found ");
		}
	}

	@Override
	public Integer incomeTaxCount() {
		Integer incomeTaxCount = repo.incomeTaxCount();
		return incomeTaxCount;
	}

	@Override
	public Integer gstCount() {
		Integer gstCount = repo.gstCount();
		return gstCount;
	}

	@Override
	public Integer totalCount() {
		Integer totalCount = repo.totalCount();
		return totalCount;
	}

	@Override
	public List<Person> gstList() {
		List<Person> gstList = repo.gstList();
		return gstList;
	}

	@Override
	public List<Person> incomeList() {
		List<Person> taxList = repo.incomeTaxList();
		return taxList;
	}

	@Override
	public Person byName(String name) throws PersonException {
		Person person = repo.findByName(name);
		if(person==null) {
			throw new PersonException("given person name is invalid");
		}else {
			return person;
		}
	}

}
