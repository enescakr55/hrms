package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.JobTypeService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.JobType;

@RestController
@RequestMapping("/api/jobtype")
@CrossOrigin
public class JobTypeController {
	JobTypeService jobTypeService;
	@Autowired
	public JobTypeController(JobTypeService jobTypeService) {
		super();
		this.jobTypeService = jobTypeService;
	}
	@PostMapping("/add")
	public Result add(@RequestBody JobType jobType) {
		return jobTypeService.add(jobType);
	}
	@GetMapping("/delete")
	public Result delete(int id) {
		return jobTypeService.delete(id);
	}
	@GetMapping("/getall")
	public DataResult<List<JobType>> getAll(){
		return jobTypeService.getAll();
	}
}
