package com.jsp.employee_management.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.employee_management.dao.EmployeeDao;
import com.jsp.employee_management.dto.Employee;
import com.jsp.employee_management.entity.EmployeeClone;
import com.jsp.employee_management.exception.EmployeeNotFoundException;
import com.jsp.employee_management.service.EmployeeService;
import com.jsp.employee_management.util.ResponseStructure;

import jakarta.mail.MessagingException;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@Autowired
	EmployeeDao dao;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<EmployeeClone>> save(@RequestBody Employee em) throws MessagingException{
		return service.save(em);
	}
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<ResponseStructure<EmployeeClone>> fetch(@PathVariable int id) throws MessagingException, EmployeeNotFoundException{
		return service.fetch(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<EmployeeClone>> update(@RequestBody Employee em ,@PathVariable int id) throws MessagingException, EmployeeNotFoundException{
		Employee ed = dao.fetchByid(id);
		em.setEid(ed.getEid());
		return service.update(em);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) throws MessagingException, EmployeeNotFoundException {
		return service.delete(id);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<Employee>> login(@RequestParam String email,@RequestParam String pwd) throws MessagingException, EmployeeNotFoundException{
		return service.login(email,pwd);
	}
	
	@GetMapping("/updateimage/{id}")
	public ResponseEntity<ResponseStructure<EmployeeClone>> login(@RequestBody MultipartFile file,@PathVariable int id) throws MessagingException, EmployeeNotFoundException{
		Employee ed = dao.fetchByid(id);
		if(ed!=null) {
			try {
				ed.setImage(file.getBytes());
			} catch (IOException e) {
				
			}
		}else {
			throw new EmployeeNotFoundException();
		}
		return service.update(ed);
	}
	
	

}
