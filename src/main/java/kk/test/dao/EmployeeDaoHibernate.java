package kk.test.dao;

import java.util.List;

import javax.sql.DataSource;

import kk.test.bean.Employee;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

public class EmployeeDaoHibernate implements EmployeeDao {

	
	
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	
	@Override
	public List<Employee> getEmployeeList() {
		return hibernateTemplate.find("from Employee");
	}

}
