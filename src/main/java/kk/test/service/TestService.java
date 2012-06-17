package kk.test.service;

import java.util.List;

import kk.test.bean.Employee;
import kk.test.dao.EmployeeDao;

public class TestService {
	
	private EmployeeDao employeeDao;
	
	public List<Employee> showEmployees(){
		return employeeDao.getEmployeeList();
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	

}
