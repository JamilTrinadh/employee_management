package com.jsp.employee_management.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.employee_management.dao.EmployeeDao;
import com.jsp.employee_management.dto.Employee;
import com.jsp.employee_management.entity.EmployeeClone;
import com.jsp.employee_management.exception.EmployeeNotFoundException;
import com.jsp.employee_management.util.ResponseStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeDao dao;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	JavaMailSender sender;
	
	public ResponseEntity<ResponseStructure<EmployeeClone>> save(Employee em) throws MessagingException{
		Employee ed = dao.save(em);
		ResponseStructure<EmployeeClone> rs = new ResponseStructure<EmployeeClone>();
		EmployeeClone ec = mapper.map(ed,EmployeeClone.class);
		rs.setData(ec);
		rs.setMsg("registration successful");
		rs.setStatuscode(HttpStatus.ACCEPTED.value());
		MimeMessage message = sender.createMimeMessage();
		message.setRecipients(MimeMessage.RecipientType.TO,ed.getEmail());
		message.setSubject("Verification Details");
		
		String htmlContent = "<h1>You have successfully registered for J10</h1>"
				+"<img src=\"https://template.canva.com/EAFhV_byuNw/1/0/311w-QF1wi5PupMc.jpg\" alt=\"beer picture\" width=\"500\" height=\"600\">"
				+ "<p>You can <strong>Party</strong> now.</p>";
		message.setContent(htmlContent, "text/html; charset=utf-8");
		sender.send(message);
		return new ResponseEntity<ResponseStructure<EmployeeClone>>(rs,HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<EmployeeClone>> update(Employee em) throws MessagingException{
		Employee ed = dao.save(em);
		ResponseStructure<EmployeeClone> rs = new ResponseStructure<EmployeeClone>();
		EmployeeClone ec = mapper.map(ed,EmployeeClone.class);
		rs.setData(ec);
		rs.setMsg("updation successful");
		rs.setStatuscode(HttpStatus.ACCEPTED.value());
		MimeMessage message = sender.createMimeMessage();
		message.setRecipients(MimeMessage.RecipientType.TO,ed.getEmail());
		message.setSubject("Updation status");
		
		String htmlContent = "<h1>You have successfully updated your profile on J10</h1>"
				+"<img src=\"https://template.canva.com/EAFhV_byuNw/1/0/311w-QF1wi5PupMc.jpg\" alt=\"beer picture\" width=\"500\" height=\"600\">"
				+ "<p>You can <strong>Party</strong> now.</p>";
		message.setContent(htmlContent, "text/html; charset=utf-8");
		sender.send(message);
		return new ResponseEntity<ResponseStructure<EmployeeClone>>(rs,HttpStatus.ACCEPTED);
	}
	
	public String delete(int id) throws MessagingException, EmployeeNotFoundException {
		Employee ed = dao.fetchByid(id);
		try {
			if(ed!=null) {
				dao.delete(id);
				MimeMessage message = sender.createMimeMessage();
				message.setRecipients(MimeMessage.RecipientType.TO,ed.getEmail());
				message.setSubject("Deletion Status");
				
				String htmlContent = "<h1>You have successfully Deleted your profile on J10</h1>"
						+"<img src=\"https://template.canva.com/EAFhV_byuNw/1/0/311w-QF1wi5PupMc.jpg\" alt=\"beer picture\" width=\"500\" height=\"600\">"
						+ "<p>You can <strong>Party</strong> now.</p>";
				message.setContent(htmlContent, "text/html; charset=utf-8");
				sender.send(message);
				return "your profile deleted "+id;
			}else {
				return "no profile exist with this "+id;
			}
			
		}catch(Exception ex) {
			throw new EmployeeNotFoundException();
		}
			
	}
	
	public ResponseEntity<ResponseStructure<EmployeeClone>> fetch(int id) throws MessagingException, EmployeeNotFoundException{
		try {
			Employee ed = dao.fetchByid(id);
			ResponseStructure<EmployeeClone> rs = new ResponseStructure<EmployeeClone>();
			EmployeeClone ec = mapper.map(ed,EmployeeClone.class);
			rs.setData(ec);
			rs.setMsg("fetch successful");
			rs.setStatuscode(HttpStatus.ACCEPTED.value());
			MimeMessage message = sender.createMimeMessage();
			message.setRecipients(MimeMessage.RecipientType.TO,ed.getEmail());
			message.setSubject("Fetch Details");
			
			String htmlContent = "<h1>You have successfully updated your profile on J10</h1>"
					+"<img src=\"https://template.canva.com/EAFhV_byuNw/1/0/311w-QF1wi5PupMc.jpg\" alt=\"beer picture\" width=\"500\" height=\"600\">"
					+ "<p>You can <strong>Party</strong> now.</p>"+ ed;
			message.setContent(htmlContent, "text/html; charset=utf-8");
			sender.send(message);
			return new ResponseEntity<ResponseStructure<EmployeeClone>>(rs,HttpStatus.ACCEPTED);
			
		}catch(Exception ex) {
			throw new EmployeeNotFoundException();
		}
		
	}

	public ResponseEntity<ResponseStructure<Employee>> login(String email, String pwd) throws MessagingException, EmployeeNotFoundException {
		Employee em = dao.fetchByEmail(email);
		ResponseStructure<Employee> rs = new ResponseStructure<Employee>();
		try {
			if(em.getPwd().equals(pwd)) {	
				rs.setData(em);
				rs.setMsg("login successful");
				MimeMessage message = sender.createMimeMessage();
				message.setRecipients(MimeMessage.RecipientType.TO,em.getEmail());
				message.setSubject("Login Details");
				
				String htmlContent = "<h1>You have successfully login on J10</h1>"
						+"<img src=\"https://template.canva.com/EAFhV_byuNw/1/0/311w-QF1wi5PupMc.jpg\" alt=\"beer picture\" width=\"500\" height=\"600\">"
						+ "<p>You can <strong>Party</strong> now.</p>"+ em;
				message.setContent(htmlContent, "text/html; charset=utf-8");
				sender.send(message);
				rs.setStatuscode(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<Employee>>(rs,HttpStatus.ACCEPTED);
			}else {
				rs.setData(null);
				rs.setMsg("login unsuccessful");
				rs.setStatuscode(HttpStatus.BAD_REQUEST.value());
				MimeMessage message = sender.createMimeMessage();
				message.setRecipients(MimeMessage.RecipientType.TO,email);
				message.setSubject("Login Details");
				
				String htmlContent = "<h1>login unsuccessful please try again on J10</h1>"
						+"<img src=\"https://template.canva.com/EAFhV_byuNw/1/0/311w-QF1wi5PupMc.jpg\" alt=\"beer picture\" width=\"500\" height=\"600\">"
						+ "<p>You can <strong>Party</strong> now.</p>"+ email;
				message.setContent(htmlContent, "text/html; charset=utf-8");
				sender.send(message);
				return new ResponseEntity<ResponseStructure<Employee>>(rs,HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception ex) {
			throw new EmployeeNotFoundException();
		}
		
		
	}

	

}
