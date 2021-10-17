package project.hrms.entities.concretes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.hrms.entities.concretes.User;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobseekerRegisterDto {
	private String email;
	private String password;
	private boolean isVerified;
	private int jobseekerId;
	private String firstName;
	private String lastName;
	private String tcNo;
	private String birthYear;
	private String phoneNumber;
	private User user;
}
