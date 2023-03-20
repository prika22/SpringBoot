package net.javaguides.springboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRespository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRespository) {
		super();
		this.employeeRespository = employeeRespository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRespository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRespository.findAll();
		
	}

	@Override
	public Employee getEmployeeByID(long id) {
//      java.util.Optional<Employee> employee= employeeRespository.findById(id);
//      if(employee.isPresent()) {
//    	  return employee.get();
//      }else {
//    	  throw new ResourceNotFoundException("Employee","Id",id);
//      }
		return employeeRespository.findById(id).orElseThrow(()->
		                 new ResourceNotFoundException("Employee", "Id",id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		//we need to check whether employee with given id is  exist in DB or not
		Employee existingEmployee =employeeRespository.findById(id).orElseThrow(() 
				-> new ResourceNotFoundException("Employee", "Id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail (employee.getEmail());
		//save existing employee to DB
		employeeRespository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		
		//check whether a employee exist in DB or not
		employeeRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",id));
		
		employeeRespository.deleteById(id);
	}
	
	
	}

	

