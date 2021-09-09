package project.hrms.api.controllers;

import java.net.http.HttpRequest;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;

import project.hrms.business.abstracts.UserService;
import project.hrms.business.services.CustomUserDetailsService;
import project.hrms.business.services.config.JwtUtil;
import project.hrms.core.entities.AuthRequest;
import project.hrms.core.entities.TokenResult;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.entities.concretes.User;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService userDetailsService;
    private UserService userService;
    
    @Autowired
	public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager,
			CustomUserDetailsService userDetailsService, UserService userService) {
		super();
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.userService = userService;
	}
	
    @PostMapping("/login")
    public DataResult<TokenResult> creteToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Kullanıcı adı veya şifre yanlış", ex);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        final Date expiration = jwtUtil.extractExpiration(jwt);
        final User userInfo = userService.getByEmail(authRequest.getUsername()).getData();
        DataResult<TokenResult> token = new DataResult<TokenResult>(new TokenResult(jwt,expiration, "type", userInfo.getEmail(), false), true);
        return token;
    }
    @GetMapping("/renewtoken")
    public DataResult<TokenResult> renewToken(@RequestHeader("Authorization") String lang) {
    	String currenttoken = lang.split(" ")[1];
    	final String username = jwtUtil.extractUsername(currenttoken);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails);
        final Date expiration = jwtUtil.extractExpiration(jwt);
        final User userInfo = userService.getByEmail(username).getData();
        DataResult<TokenResult> token = new DataResult<TokenResult>(new TokenResult(jwt,expiration, "type", userInfo.getEmail(), false), true);
    	return token;
    	
    }



    
}
