package project.hrms.api.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.JobAdvertService;
import project.hrms.business.abstracts.JobTimeService;
import project.hrms.business.abstracts.JobTypeService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.JobAdvert;
import project.hrms.entities.concretes.dtos.JobAdvertAddDto;
@RestController
@RequestMapping("/api/jobadverts")
@CrossOrigin
public class JobAdvertsController {
	JobAdvertService jobAdvertService;
	JobTimeService jobTimeService;
	JobTypeService jobTypeService;
	@Autowired
	public JobAdvertsController(JobAdvertService jobAdvertService,JobTimeService jobTimeService,JobTypeService jobTypeService) {
		super();
		this.jobAdvertService = jobAdvertService;
		this.jobTimeService = jobTimeService;
		this.jobTypeService = jobTypeService;
	}
	@GetMapping("/getall")
	public DataResult<List<JobAdvert>> getAll(){
		return jobAdvertService.getAll();
	}
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvert jobAdvert) {
		jobAdvert.setActive(false);
		return jobAdvertService.add(jobAdvert);
	}
	@GetMapping("getisactive")
	public DataResult<List<JobAdvert>> getIsActive(){
		return jobAdvertService.getIsActive();
	}
	@GetMapping("getisnotactive")
	public DataResult<List<JobAdvert>> getIsNotActive(){
		return jobAdvertService.getIsNotActive();
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
	@PostMapping("/addwithdto")
	public Result addWithDto(@RequestBody JobAdvertAddDto jobAdvertAddDto) {
		jobAdvertAddDto.setLastDate(new Date());
		return jobAdvertService.addwithdto(jobAdvertAddDto);
	}

}
