package project.hrms.api.controllers.cv;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.cv.SchoolService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.cv.School;
@RestController
@RequestMapping("/api/schools")
public class SchoolController {
	SchoolService schoolService;
	public SchoolController(SchoolService schoolService) {
		this.schoolService = schoolService;
	}
	@PostMapping("/add")
	public Result add(@RequestBody School school) {
		return schoolService.add(school);
	}
	@PostMapping("/update")
	public Result update(@RequestBody School school) {
		return schoolService.update(school);
	}
	@PostMapping("/delete")
	public Result delete(@RequestBody School school) {
		return schoolService.delete(school);
	}
	@GetMapping("/getall")
	public DataResult<List<School>> getAll() {
		return schoolService.getAll();
	}
	@GetMapping("getbyjobseekerid")
	public DataResult<List<School>> getByJobseekerId(int jobseekerid) {
		return schoolService.getByJobseekerId(jobseekerid);
	}
	
	
}
