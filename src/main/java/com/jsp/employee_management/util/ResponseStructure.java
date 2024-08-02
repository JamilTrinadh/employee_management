package com.jsp.employee_management.util;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	int statuscode;
	String msg;
	T data;
	LocalDateTime t = LocalDateTime.now();
}
