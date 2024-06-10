package com.task.service;

import java.util.*;


import com.task.entity.Person;
import com.task.enums.UserType;
import com.task.exception.PersonException;
public interface PersonService {

	Person addPerson(Person person,UserType userType)throws PersonException;
	Person personById(Integer personId)throws PersonException;
	List<Person> getLsit();
	Person updatePerson(Person person,Integer personId)throws PersonException;
	String deletePerson(Integer personId)throws PersonException;
	
	Integer incomeTaxCount();
	Integer gstCount();
	Integer totalCount();
	
	List<Person> gstList();
	List<Person> incomeList();
	Person byName(String name) throws PersonException;
}
