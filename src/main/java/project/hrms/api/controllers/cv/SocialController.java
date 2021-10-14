package project.hrms.api.controllers.cv;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.cv.SocialService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.cv.Social;
@CrossOrigin
@RestController
@RequestMapping("/api/socials")
public class SocialController {
	SocialService socialService;
	public SocialController(SocialService socialService) {
		this.socialService = socialService;
	}
	@PostMapping("/add")
	public Result add(@RequestBody Social social) {
		return socialService.add(social);
	}
	@PostMapping("/update")
	public Result update(@RequestBody Social social) {
		return socialService.update(social);
	}
	@PostMapping("/delete")
	public Result delete(@RequestBody Social social) {
		return socialService.delete(social);
	}
	@GetMapping("/getall")
	public DataResult<List<Social>> getAll(){
		return socialService.getAll();
	}
	@GetMapping("/getbyjobseekerid")
	public DataResult<List<Social>> getByJobseekerId(int jobseekerid){
		return socialService.getByJobseekerId(jobseekerid);
	}
}
