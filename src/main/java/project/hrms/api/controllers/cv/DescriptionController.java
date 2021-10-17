package project.hrms.api.controllers.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.JobseekerService;
import project.hrms.business.abstracts.cv.DescriptionService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.cv.Description;
@CrossOrigin
@RestController
@RequestMapping("/api/descriptions")
public class DescriptionController {
	DescriptionService descriptionService;
	JobseekerService jobseekerService;
	@Autowired
	public DescriptionController(DescriptionService descriptionService,JobseekerService jobseekerService) {
		super();
		this.descriptionService = descriptionService;
		this.jobseekerService = jobseekerService;
	}
	@PostMapping("/add")
	public Result add(@RequestBody Description description) {
		return descriptionService.add(description);
	}
	@GetMapping("/getbyjobseekerid")
	public DataResult<Description> getByJobseekerId(int jsid){
		return descriptionService.getByJobseekerId(jsid);
	}
	@GetMapping("/getbyuserid")
	public DataResult<Description> getByUserId(int userid){
		Jobseeker jobseeker = jobseekerService.getById(userid).getData();
		return descriptionService.getByJobseekerId(jobseeker.getJobseekerId());
	}
	@GetMapping("/getall")
	public DataResult<List<Description>> getAll(){
		return descriptionService.getAll();
	}
	@PostMapping("/update")
	public Result update(@RequestBody Description description) {
		return descriptionService.update(description);
	}
	@PostMapping("/delete")
	public Result delete(@RequestBody Description description) {
		return descriptionService.delete(description);
	}
	@PostMapping("/updatemydescription")
	public Result updateMyDescription(@RequestBody Description description) {
		System.out.println("Açıklama Oku");
		System.out.println(description);
		return descriptionService.updateMyDescription(description);
	}
}
