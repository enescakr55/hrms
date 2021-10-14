package project.hrms.api.controllers.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.cv.LanguageService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.cv.Language;
@CrossOrigin
@RestController
@RequestMapping("/api/languages")
public class LanguageController {
	LanguageService languageService;
	@Autowired
	public LanguageController(LanguageService languageService) {
		this.languageService = languageService;
	}
	@PostMapping("/add")
	public Result add(@RequestBody Language language) {
		return languageService.add(language);
	}
	@PostMapping("/update")
	public Result update(@RequestBody Language language) {
		return languageService.update(language);
	}
	@GetMapping("/getbyjobseeker")
	public DataResult<List<Language>> getByJobseekerId(int jobseekerid){
		return languageService.getByJobseekerId(jobseekerid);
	}
	public DataResult<List<Language>> getAll(){
		return languageService.getAll();
	}
	public Result delete(Language language) {
		return languageService.delete(language);
	}
}
