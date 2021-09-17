package project.hrms.api.controllers;

import java.util.Date;
import java.util.List;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.EmployeeService;
import project.hrms.business.abstracts.EmployerService;
import project.hrms.business.abstracts.FavoriteJobAdvertsService;
import project.hrms.business.abstracts.JobAdvertService;
import project.hrms.business.abstracts.JobTimeService;
import project.hrms.business.abstracts.JobTypeService;
import project.hrms.business.abstracts.UserService;
import project.hrms.business.services.config.JwtUtil;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorDataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.FavoriteJobAdvertsDao;
import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.FavoriteJobAdvert;
import project.hrms.entities.concretes.JobAdvert;
import project.hrms.entities.concretes.User;
import project.hrms.entities.concretes.dtos.JobAdvertAddDto;
@RestController
@RequestMapping("/api/jobadverts")
@CrossOrigin
public class JobAdvertsController {
	JobAdvertService jobAdvertService;
	JobTimeService jobTimeService;
	JobTypeService jobTypeService;
	UserService userService;
	EmployerService employerService;
	FavoriteJobAdvertsService favoriteJobAdvertsService;
	JwtUtil jwtUtil;
	@Autowired
	public JobAdvertsController(JobAdvertService jobAdvertService,JobTimeService jobTimeService,JobTypeService jobTypeService,EmployerService employerService,UserService userService,FavoriteJobAdvertsService favoriteJobAdvertsService,JwtUtil jwtUtil) {
		super();
		this.jobAdvertService = jobAdvertService;
		this.jobTimeService = jobTimeService;
		this.jobTypeService = jobTypeService;
		this.userService = userService;
		this.employerService = employerService;
		this.favoriteJobAdvertsService = favoriteJobAdvertsService;
		this.jwtUtil = jwtUtil;
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
	@PostMapping("jobadvertapprove")
	public Result jobAdvertApprove(@RequestBody JobAdvert jobadvert) {
		return jobAdvertService.jobAdvertApprove(jobadvert);
	}
	@PostMapping("/addjobadvertbyemployer")
	public Result addJobAdvertByEmployer(@RequestHeader("Authorization") String token,@RequestBody JobAdvertAddDto jobAdvertAddDto) {
    	String currenttoken = token.split(" ")[1];
    	final String mail = jwtUtil.extractUsername(currenttoken);
    	User user = userService.getByEmail(mail).getData();
    	Employer employer = employerService.getById(user.getId()).getData();
		jobAdvertAddDto.setEmployerid(employer.getEmployerid());
		Result res = addWithDto(jobAdvertAddDto);
		return res;
	}
	@GetMapping("/addfavorites")
	public Result addFavorites(@RequestHeader("Authorization") String token,@RequestParam int advertid) {
    	String currenttoken = token.split(" ")[1];
    	final String mail = jwtUtil.extractUsername(currenttoken);
    	User user = userService.getByEmail(mail).getData();
    	JobAdvert jobadvert = jobAdvertService.getByAdvertId(advertid).getData();
		FavoriteJobAdvert fav = new FavoriteJobAdvert();
		fav.setJobAdvert(jobadvert);
		fav.setUser(user);
		return favoriteJobAdvertsService.add(fav);
	}
	@GetMapping("/removefavorites")
	public Result removeFavorites(@RequestHeader("Authorization") String token,@RequestParam int advertid) {
    	String currenttoken = token.split(" ")[1];
    	final String mail = jwtUtil.extractUsername(currenttoken);
    	User user = userService.getByEmail(mail).getData();
		FavoriteJobAdvert fav = favoriteJobAdvertsService.getByAdvertIdAndUserId(user.getId(), advertid).getData();
		return favoriteJobAdvertsService.delete(fav);
	
	}
	@PostMapping("getfavoritejobadvert")
	public DataResult<FavoriteJobAdvert> getFavoriteJobAdvert(int userId,int jobAdvertId){
		return favoriteJobAdvertsService.getByAdvertIdAndUserId(userId, jobAdvertId);
	}
	@GetMapping("/getfavorites")
	public DataResult<List<FavoriteJobAdvert>> getFavorites(@RequestHeader("Authorization") String token){
    	String currenttoken = token.split(" ")[1];
    	final String mail = jwtUtil.extractUsername(currenttoken);
    	User user = userService.getByEmail(mail).getData();
    	return favoriteJobAdvertsService.getJobAdvertsWithUserId(user.getId());
	}
	@GetMapping("getmyjobadverts")
	public DataResult<List<JobAdvert>> getMyJobAdverts(@RequestHeader("Authorization") String token){
    	String currenttoken = token.split(" ")[1];
    	final String mail = jwtUtil.extractUsername(currenttoken);
    	User user = userService.getByEmail(mail).getData();
    	Employer employer = employerService.getById(user.getId()).getData();
    	if(employer == null) {
    		return new ErrorDataResult<List<JobAdvert>>("Bu kullanıcı bir işveren değil");
    	}
    	return jobAdvertService.getByEmployerId(employer.getEmployerid());
	}
}
