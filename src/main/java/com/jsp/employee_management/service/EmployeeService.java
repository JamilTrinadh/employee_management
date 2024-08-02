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
	
	public String delete(int id) throws MessagingException {
		Employee ed = dao.fetchByid(id);
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
	}
	
	public ResponseEntity<ResponseStructure<EmployeeClone>> fetch(int id) throws MessagingException{
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
	}
	

}
