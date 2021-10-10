package project.hrms.api.controllers.cv;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.hrms.business.abstracts.cv.DescriptionService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.cv.Description;

@RestController
@RequestMapping("/api/descriptions")
public class DescriptionController {
	DescriptionService descriptionService;
	public DescriptionController(DescriptionService descriptionService) {
		// TODO Auto-generated constructor stub
		super();
		this.descriptionService = descriptionService;
	}
	@PostMapping("/add")
	public Result add(@RequestBody Description description) {
		return descriptionService.add(description);
	}
	@GetMapping("/getbyjobseekerid")
	public DataResult<Description> getByJobseekerId(int jobseekerId){
		return descriptionService.getByJobseekerId(jobseekerId);
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
}
