package com.smartpants.artwork.dao;

import java.util.List;

import com.smartpants.artwork.domain.Person;
import com.smartpants.artwork.exception.AuthenticationException;

public interface PersonDao {

	public Person getPerson(Long personId);
	
	public void savePerson(Person person);
	
	public List<Person> getPeople();
	
	public Person getPersonByUsername(String username);
	
	public Person authenticatePerson(String username, String password) throws AuthenticationException;
}
