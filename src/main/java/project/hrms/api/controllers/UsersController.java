package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.UserService;
import project.hrms.business.abstracts.UserTypeService;
import project.hrms.business.services.SMTPMailSender;
import project.hrms.business.services.config.JwtUtil;
import project.hrms.core.entities.AuthenticatedEmployerInfo;
import project.hrms.core.entities.AuthenticatedUserInfo;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.User;
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {
	UserService userService;
	UserTypeService userTypeService;
	private JwtUtil jwtUtil;
	@Autowired
	public UsersController(UserService userService,UserTypeService userTypeService,JwtUtil jwtUtil) {
		super();
		this.userService = userService;
		this.userTypeService =userTypeService;
		this.jwtUtil = jwtUtil;
	}
	@GetMapping("/getall")
	public DataResult<List<User>> getAll(){
		return userService.getAll();
	}
	@PostMapping("/add")
	public Result add(@RequestBody User user) {
		return userService.add(user);
	}
	@GetMapping("/getbyemail")
	public DataResult<User> getByEmail(@RequestParam String email){
		return userService.getByEmail(email);
	}
	@GetMapping("/getbyid")
	public DataResult<User> getById(@RequestParam int id){
		return userService.getById(id);
	}
	@GetMapping("/getuserinfo")
	public DataResult<AuthenticatedUserInfo> getUserInfo(@RequestHeader("Authorization") String token){
    	String currenttoken = token.split(" ")[1];
    	final String mail = jwtUtil.extractUsername(currenttoken);
    	User user = userService.getByEmail(mail).getData();
		return userTypeService.GetUserInfo(user.getId());
	}
	@GetMapping("/getemployerinfo")
	public DataResult<AuthenticatedEmployerInfo> getEmployerInfo(@RequestHeader("Authorization") String token){
    	String currenttoken = token.split(" ")[1];
    	final String mail = jwtUtil.extractUsername(currenttoken);
    	User user = userService.getByEmail(mail).getData();
		return userTypeService.GetEmployerInfo(user.getId());
	}
}
