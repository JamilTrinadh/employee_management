package com.jsp.employee_management.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.employee_management.dto.Employee;
import com.jsp.employee_management.repo.EmployeeRepo;

@Repository
public class EmployeeDao {
	
	@Autowired
	EmployeeRepo repo;
	
	public Employee save(Employee em) {
		Employee e =repo.save(em);
		return e;
	}
	
	public Employee update(Employee em) {
		Employee e = repo.save(em);
		return e;
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
	
	public Employee fetchByid(int id) {
		java.util.Optional<Employee> em = repo.findById(id);
		return em.get();
	}

}
