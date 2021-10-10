package project.hrms.business.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.services.config.JwtUtil;
@Service
public class TokenInfo {
	@Autowired
	JwtUtil jwtUtil;
	public String getAuthenticatedUserEmail(String token) {
    	String currenttoken = token.split(" ")[1];
    	final String mail = jwtUtil.extractUsername(currenttoken);
		return mail;
	}
}
