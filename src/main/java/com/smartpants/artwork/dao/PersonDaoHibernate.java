package com.smartpants.artwork.dao;

import static java.lang.String.format;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.smartpants.artwork.domain.Person;
import com.smartpants.artwork.exception.AuthenticationException;



@Repository
@Transactional
@SuppressWarnings("unchecked")
public class PersonDaoHibernate extends HibernateDaoSupport implements PersonDao {

	
	@Autowired
	public void setupSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	
	public Person getPerson(Long personId) throws DataAccessException{
		return this.getHibernateTemplate().load(Person.class, personId);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePerson(Person person) {
		this.getHibernateTemplate().saveOrUpdate(person);
	}
	
	public List<Person> getPeople() throws DataAccessException {
		return this.getHibernateTemplate().find("select people from Person people");
	}

	@Override
	public Person getPersonByUsername(String username) {
		List<Person> people = this.getHibernateTemplate().findByNamedParam(
				"select p from Person p " +
				"where p.username = :username", "username", username);
		Person person = getFirst(people);
		if(person != null)
			getHibernateTemplate().evict(person);
		return person;
	}

	@Override
	public Person authenticatePerson(String username, String password) throws AuthenticationException {
		List<Person> validUsers = this.getHibernateTemplate().findByNamedParam(
				"select p from Person p " +
				"where p.username = :username " +
				"and p.password = :password",
			new String[] {"username", "password"},
			new String[] { username, password} );
		
		if (validUsers.isEmpty()) 
			throw new AuthenticationException(format("Could not authenticate %s", username));
		return getFirst(validUsers);
	}
	 
	private static <T>T getFirst(List<T> list){
		return CollectionUtils.isEmpty(list) ? null: list.get(0);
	}

}
