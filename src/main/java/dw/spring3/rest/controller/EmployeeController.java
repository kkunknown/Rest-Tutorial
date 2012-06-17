package dw.spring3.rest.controller;

import java.io.StringReader;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dw.spring3.rest.bean.Employee;
import dw.spring3.rest.bean.EmployeeList;
import dw.spring3.rest.ds.EmployeeDS;

@Controller
public class EmployeeController {
	
	private EmployeeDS employeeDS;
	private Jaxb2Marshaller jaxb2Marshaller;
	
	private static final String XML_VIEW_NAME = "employees";
	
	public void setEmployeeDS(EmployeeDS ds){
		this.employeeDS = ds;
	}
	
	
	public void setJaxb2Marshaller(Jaxb2Marshaller jaxb2Marshaller){
		this.jaxb2Marshaller = jaxb2Marshaller;
	}
	
	@RequestMapping(value="/employee/{id}", method=RequestMethod.GET)
	public @ResponseBody Employee getEmployee(@PathVariable String id){
		return employeeDS.get(Long.parseLong(id));
	}
	
	@RequestMapping(value="/employee/{id}", method=RequestMethod.PUT)
	public Employee updateEmployee(@RequestBody String body){
		Source source = new StreamSource(new StringReader(body));
		Employee e = (Employee) jaxb2Marshaller.unmarshal(source);
		employeeDS.update(e);
		return e;
	}
	
	
	@RequestMapping(value="/employee", method=RequestMethod.POST)
	public Employee addEmployee(@RequestBody String body){
		Source source = new StreamSource(new StringReader(body));
		Employee e = (Employee) jaxb2Marshaller.unmarshal(source);
		return e;
	}
	
	@RequestMapping(value="/employee/{id}", method=RequestMethod.DELETE)
	public EmployeeList removeEmployee(@PathVariable String id){
		employeeDS.remove(Long.parseLong(id));
		List<Employee> employees = employeeDS.getAll();
		EmployeeList list = new EmployeeList(employees);
		return list;
	}
	
	@RequestMapping(value="/employees", method=RequestMethod.GET)
	public EmployeeList getEmployees(){
		List<Employee> employees = employeeDS.getAll();
		EmployeeList list = new EmployeeList(employees);
		return list;
	}
	
	
	
	
	
	
	
}
