package com.jsp.employee_management.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.employee_management.util.ResponseStructure;

@RestControllerAdvice
public class EmployeeManagementException {
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> sqlIntegrity(SQLIntegrityConstraintViolationException ex){
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.BAD_REQUEST.value());
		rs.setData(ex.getMessage());
		rs.setMsg("employee details not saved successfully");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
		
	}
	
}
