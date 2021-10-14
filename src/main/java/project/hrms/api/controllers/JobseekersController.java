package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.JobseekerService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.dtos.JobseekerRegisterDto;

@RestController
@RequestMapping("/api/jobseekers")
@CrossOrigin

public class JobseekersController {
	JobseekerService jobseekerService;
	@Autowired
	public JobseekersController(JobseekerService jobseekerService) {
		super();
		this.jobseekerService = jobseekerService;
	}
	@GetMapping("/getall")
	public DataResult<List<Jobseeker>> getAll(){
		return jobseekerService.getAll();
	}
	@PostMapping("/add")
	public Result add(@RequestBody Jobseeker jobseeker) {
		return jobseekerService.add(jobseeker);
	}
	@PostMapping("/registerjobseeker")
	public Result jobseekerRegister(@RequestBody JobseekerRegisterDto jobseekerRegisterDto) {
		return jobseekerService.jobseekerRegister(jobseekerRegisterDto);
	}
	@GetMapping("/getjobseekerbyuserid")
	public DataResult<Jobseeker> getJobseekerByUserId(int userid){
		return jobseekerService.getById(userid);
	}
	
}
