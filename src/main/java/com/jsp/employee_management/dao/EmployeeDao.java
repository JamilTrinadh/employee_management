package com.jsp.employee_management.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.employee_management.dto.Employee;
import com.jsp.employee_management.exception.EmployeeNotFoundException;
import com.jsp.employee_management.repo.EmployeeRepo;

@Repository
public class EmployeeDao {
	
	@Autowired
	EmployeeRepo repo;
	
	public Employee save(Employee em) {
		Employee e = repo.save(em);
		return e;
	}
	
	public Employee update(Employee em) {
		Employee e = repo.save(em);
		return e;
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
	
	public Employee fetchByid(int id) throws EmployeeNotFoundException {
		java.util.Optional<Employee> em = repo.findById(id);
		try {
			return em.get();
		}catch(Exception ex) {
			throw new EmployeeNotFoundException();
		}
		
	}
	
	public Employee fetchByEmail(String email) {
		Employee em = repo.fetchByEmail(email);
		return em;
	}
	
	

}
