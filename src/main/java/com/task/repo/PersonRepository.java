package com.task.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.task.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

	public boolean existsByUserName(String userName);
	
	public boolean existsByPassword(String password);
	
	@Query(value =  "select count(case when user_type = 'IncomeTax' then 1 else null end)as GSTCOUNT from person",nativeQuery = true)
	public Integer incomeTaxCount();
	@Query(value =  "select count(case when user_type = 'GST' then 1 else null end)as GSTCOUNT from person",nativeQuery = true)
	public Integer gstCount();
	@Query(value = "select count(*) from person",nativeQuery = true)
	public Integer totalCount();
	
	@Query(value = "select * from person where user_type='GST'",nativeQuery = true)
	public List<Person> gstList();
	
	@Query(value = "select * from person where user_type='IncomeTax'",nativeQuery = true)
	public List<Person> incomeTaxList();
	@Query(value = "SELECT * FROM person ORDER BY name;",nativeQuery = true)
	public List<Person>sortList();
	
	public Person findByName(String name);
}
