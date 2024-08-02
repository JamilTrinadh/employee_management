package com.jsp.employee_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeClone {
	
	private int eid;
	private String fname;
	private String lname;
	private String gender;
	private String email;
	private String pwd;
	
	
	
}
