package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.JobAdvertService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.JobAdvert;
@RestController
@RequestMapping("/api/jobadverts")
public class JobAdvertsController {
	JobAdvertService jobAdvertService;

	@Autowired
	public JobAdvertsController(JobAdvertService jobAdvertService) {
		super();
		this.jobAdvertService = jobAdvertService;
	}
	@GetMapping("/getall")
	public DataResult<List<JobAdvert>> getAll(){
		return jobAdvertService.getAll();
	}
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvert jobAdvert) {
		return jobAdvertService.add(jobAdvert);
	}
	@GetMapping("getisactive")
	public DataResult<List<JobAdvert>> getIsActive(){
		return jobAdvertService.getIsActive();
	}
	@GetMapping("getisactiveorderbydate")
	public DataResult<List<JobAdvert>> getIsActiveOrderByDate(){
		return jobAdvertService.getIsActiveOrderByDate();
	}
	@GetMapping("getbyemployerid")
	public DataResult<List<JobAdvert>> getByEmployerId(@RequestParam int employerId){
		return jobAdvertService.getByEmployerId(employerId);
	}
	@GetMapping("closeAdvert")
	public Result closeAdvert(@RequestParam int advertId) {
		return jobAdvertService.closeAdvert(advertId);
	}

}
