package project.hrms.api.controllers.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.cv.ExperienceService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.entities.concretes.cv.Description;
import project.hrms.entities.concretes.cv.Experience;
import project.hrms.entities.concretes.dtos.ExperienceAddDto;
@CrossOrigin
@RestController
@RequestMapping("/api/experiences")
	
public class ExperienceController {
	ExperienceService experienceService;
	@Autowired
	public ExperienceController(ExperienceService experienceService) {
		this.experienceService = experienceService;
	}
	@PostMapping("/add")
	public Result add(@RequestBody Experience experience) {
		return experienceService.add(experience);
	}
	@PostMapping("/update")
	public Result update(@RequestBody Experience experience) {
		return experienceService.update(experience);
	}
	@GetMapping("/getbyjobseekerid")
	public DataResult<List<Experience>> getByJobseekerId(int jobseekerid){
		return experienceService.getByJobseekerId(jobseekerid);
	}
	@GetMapping("/getbyuserid")
	public DataResult<List<Experience>> getByUserId(int userid){
		return experienceService.getByUserId(userid);
	}
	@GetMapping("/getall")
	public DataResult<List<Experience>> getAll(){
		return experienceService.getAll();
	}
	@PostMapping("/delete")
	public Result delete(@RequestBody Experience experience) {
		return experienceService.delete(experience);
	}
	@GetMapping("/deletemyexperience")
	public Result deleteMyExperience(int experienceid) {
		return experienceService.deleteMyExperience(experienceid);
	}
	@PostMapping("/addmyexperience")
	public Result addMyExperience(@RequestBody ExperienceAddDto experience) {

		return experienceService.addMyExperience(experience);
	}
}
