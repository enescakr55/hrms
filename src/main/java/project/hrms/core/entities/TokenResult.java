package project.hrms.core.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResult {
	
	private String Token;
	private Date expiration;
	private String userType;
	private String email;
	private boolean isAdmin;
}
