package project.hrms.entities.concretes.dtos;


import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.hrms.entities.concretes.User;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerRegister {
	
	private String email;
	private String password;
	private boolean isVerified;
	private int employerid;
	private String website;
	private String phoneNumber;
	private String companyName;
	private User user;
}
