package com.jsp.employee_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jsp.employee_management.dto.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
	
	
	@Query("select a from Employee a where email=?1")
	public Employee fetchByEmail(String email);
}
