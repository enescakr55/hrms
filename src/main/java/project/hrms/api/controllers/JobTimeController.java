package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.JobTimeService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.entities.concretes.JobTime;

@RestController
@RequestMapping("/api/jobtime")
@CrossOrigin
public class JobTimeController {
	JobTimeService jobTimeService;
	@Autowired
	public JobTimeController(JobTimeService jobTimeService) {
		super();
		this.jobTimeService = jobTimeService;
	}
	@PostMapping("/add")
	public Result add(@RequestBody JobTime jobTime) {
		return jobTimeService.add(jobTime);
	}
	@GetMapping("/delete")
	public Result delete(int id) {
		return jobTimeService.delete(id);
	}
	@GetMapping("/getall")
	public DataResult<List<JobTime>> getAll(){
		return jobTimeService.getAll();
	}
}
