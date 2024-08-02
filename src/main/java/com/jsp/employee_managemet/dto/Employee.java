package com.jsp.employee_managemet.dto;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eid;
	private String fname;
	private String mname;
	private String lname;
	private int age;
	private String gender;
	@Column(unique = true)
	private Date dob;
	private String email;
	private String pwd;
	@Lob
	@Column(columnDefinition = "LONGBLOB",length =Integer.MAX_VALUE)
	private byte[] image;
	@OneToMany(cascade = CascadeType.ALL)
	List<Experience> ex;
	@OneToMany(cascade = CascadeType.ALL)
	List<EducationDetails> ed;
	public Employee(String fname, String mname, String lname, int age, String gender, Date dob, String email,
			String pwd, byte[] image, List<Experience> ex, List<EducationDetails> ed) {
		super();
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.age = age;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.pwd = pwd;
		this.image = image;
		this.ex = ex;
		this.ed = ed;
	}
	
}
