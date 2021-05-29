package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.UserService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.User;
@RestController
@RequestMapping("/api/users")
public class UsersController {
	UserService userService;
	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
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
}
