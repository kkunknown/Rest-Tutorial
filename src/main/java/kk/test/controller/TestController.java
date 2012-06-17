package kk.test.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kk.test.bean.Employee;
import kk.test.service.TestService;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	
	
	
	// get log4j handler
	private static final Logger log = Logger.getLogger(TestController.class);
	
	private TestService testService;
	
	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public @ResponseBody String testMain(){
	
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("mysqlDataSource");
		
		log.debug("driverClassName: " );
		log.debug("url: " + dataSource.getUrl());
		log.debug("username: " + dataSource.getUsername());
		log.debug("password: " + dataSource.getPassword());
		
		
		try {
			Connection c = dataSource.getConnection();
			log.debug("is closed? " + c.isClosed() );
		} catch (SQLException e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		
		log.debug("logginggggggggggggggggggggg");
		
		List<Employee> employees = testService.showEmployees();
		
		return "Welcom to 'Home page' " + employees.get(0).getName() + " ~~~ " + employees.get(1).getName();
	}
	
	
	@RequestMapping(value="/employee-list", method=RequestMethod.GET)
	public @ResponseBody List showEmployeeList(){
		return testService.showEmployees();
	}
	
	
	@RequestMapping(value="/http-basic-test", method=RequestMethod.GET)
	public @ResponseBody String httpBasicTest(){
		return "sccessful";
	}
	
}
