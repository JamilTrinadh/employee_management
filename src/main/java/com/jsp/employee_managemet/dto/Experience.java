package com.jsp.employee_managemet.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Experience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int exid;
	private String cName;
	private int yoe;
	private int moe;
	private String desig;
	public Experience(String cName, int yoe, int moe, String desig) {
		super();
		this.cName = cName;
		this.yoe = yoe;
		this.moe = moe;
		this.desig = desig;
	}
	
	
}
