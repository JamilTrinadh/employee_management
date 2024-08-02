package com.jsp.employee_managemet.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class EducationDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int edid;
	String degree;
	int yop;
	String iname; // institute name
	public EducationDetails(String degree, int yop, String iname) {
		super();
		this.degree = degree;
		this.yop = yop;
		this.iname = iname;
	}
	
	
}
