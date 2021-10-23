package project.hrms.entities.concretes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.hrms.entities.concretes.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRegisterDto {
	private String email;
	private String password;
	private boolean isVerified;
	private int employeeId;
	private User user;
}
