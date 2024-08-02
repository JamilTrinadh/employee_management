package com.jsp.employee_management.dto;

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
	private String cname;
	private int yoe;
	private int moe;
	private String desig;
	public Experience(String cname, int yoe, int moe, String desig) {
		super();
		this.cname = cname;
		this.yoe = yoe;
		this.moe = moe;
		this.desig = desig;
	}
	
	
}
