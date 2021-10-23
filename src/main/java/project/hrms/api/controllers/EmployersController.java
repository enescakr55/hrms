package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.EmployerService;
import project.hrms.business.abstracts.GetAuthenticatedUserService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.dtos.EmployerRegister;

@RestController
@RequestMapping("/api/employers")
@CrossOrigin
public class EmployersController {
	EmployerService employerService;
	GetAuthenticatedUserService getAuthenticatedUserService;
	@Autowired
	public EmployersController(EmployerService employerService,GetAuthenticatedUserService getAuthenticatedUserService) {
		super();
		this.employerService = employerService;
		this.getAuthenticatedUserService = getAuthenticatedUserService;
	}
	@GetMapping("/getall")
	public DataResult<List<Employer>> getAll(){
		return employerService.getAll();
	}
	@PostMapping("/add")
	public Result add(@RequestBody Employer employer) {
		return employerService.add(employer);
	}
	@PostMapping("registeremployer")
	public Result add(@RequestBody EmployerRegister employerRegister) {
		employerRegister.setVerified(false);
		return employerService.register(employerRegister);
	}
	@GetMapping("/isemployer")
	public boolean isEmployer(int userid) {
		return employerService.isEmployer(userid);
	}
	@GetMapping("/me")
	public DataResult<Employer> getMe(){
		return getAuthenticatedUserService.getEmployer();
	}

	
	
}
