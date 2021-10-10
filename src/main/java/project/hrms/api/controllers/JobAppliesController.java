package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.JobAdvertService;
import project.hrms.business.abstracts.JobApplyService;
import project.hrms.business.abstracts.JobseekerService;
import project.hrms.business.abstracts.UserService;
import project.hrms.business.services.config.JwtUtil;
import project.hrms.business.tools.TokenInfo;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.JobAdvert;
import project.hrms.entities.concretes.JobApply;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.User;

@RestController
@RequestMapping("/api/jobapplies")
@CrossOrigin
public class JobAppliesController {
	
	JobApplyService jobApplyService;
	JwtUtil jwtUtil;
	UserService userService;
	JobAdvertService jobAdvertService;
	JobseekerService jobseekerService;
	TokenInfo tokenInfo;
	
	@Autowired
	public JobAppliesController(JobApplyService jobApplyService,JwtUtil jwtUtil,JobseekerService jobseekerService,JobAdvertService jobAdvertService,UserService userService,TokenInfo tokenInfo) {
		super();
		this.jobApplyService = jobApplyService;
		this.jwtUtil = jwtUtil;
		this.userService = userService;
		this.jobseekerService = jobseekerService;
		this.jobAdvertService = jobAdvertService;
		this.tokenInfo = tokenInfo;
	}

	@GetMapping("/getall")
	DataResult<List<JobApply>> getAll(){
		return jobApplyService.getAll();
	}
	@GetMapping("/getbyjobadvertadd")
	DataResult<List<JobApply>> getByJobAdvertId(int jobAdvertId){
		return jobApplyService.getByJobAdvertId(jobAdvertId);
	}
	@PostMapping("/add")
	Result add(@RequestBody JobApply jobApply) {
		return jobApplyService.add(jobApply);
	}
	@PostMapping("/update")
	Result update(@RequestBody JobApply jobApply){
		return jobApplyService.update(jobApply);
	}
	@PostMapping("/applyjob")
	Result applyJob(@RequestHeader("Authorization") String token,@RequestBody JobAdvert jobAdvert) {
    	//String currenttoken = token.split(" ")[1];
    	//final String mail = jwtUtil.extractUsername(currenttoken);
		final String mail = tokenInfo.getAuthenticatedUserEmail(token);
    	User user = userService.getByEmail(mail).getData();
    	if(jobseekerService.isJobseeker(user.getId()) == false) {
    		return new ErrorResult("Sistemde iş arayan olarak kaydınız bulunmamaktadır. İş başvurusu yapılamadı.");
    	}
    	Jobseeker jobseeker = jobseekerService.getById(user.getId()).getData();
    	if(jobApplyService.getJobApplyWithJobseekerIdAndJobAdvertId(jobseeker.getJobseekerId(), jobAdvert.getAdvertId()).getData() != null){
    		return new ErrorResult("Bu ilan için başvurunuz bulunmaktadır.");
    	}
    	JobApply jobApply = new JobApply();
    	jobApply.setJobAdvert(jobAdvert);
    	jobApply.setJobseekerId(jobseeker);
    	return jobApplyService.add(jobApply);
	}
	@GetMapping("/getjobapplyjobseekerandjobadvert")
		DataResult<JobApply> getjobapplyjobseekerandjobadvert(int jobAdvert,int jobSeeker) {
			return jobApplyService.getJobApplyWithJobseekerIdAndJobAdvertId(jobSeeker, jobAdvert);
	}
	@PostMapping("/getjobapplycountbyjobadvert")
	DataResult<Integer> getJobApplyCountByJobAdvert(@RequestBody JobAdvert jobAdvert){
		return jobApplyService.getJobApplyCountByJobAdvert(jobAdvert);
	}
}
