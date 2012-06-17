package com.smartpants.artwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.smartpants.artwork.dao.PersonDao;
import com.smartpants.artwork.domain.People;
import com.smartpants.artwork.domain.Person;


@Controller
@RequestMapping("/people")
@Transactional()
public class PersonController {

	private PersonDao personDao;
	
	@Autowired
	public void setPersonDao(PersonDao personDao){
		this.personDao = personDao;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public People getAll(){
		System.out.print("hihihihiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii~***************************");
		
		return new People(personDao.getPeople());
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	@ResponseBody
	public Person getPerson(@PathVariable("`id") Long personId){
		return personDao.getPerson(personId);
	}
	
	
	/*
	@RequestMapping(method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public View savePerson(@RequestBody Person person){
		personDao.savePerson(person);
		return new RedirectView("/people/" + person.getId());
	}
	
	*/
	
	
	
}
