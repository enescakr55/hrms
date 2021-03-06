package project.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.EmployeeService;
import project.hrms.business.abstracts.EmployerService;
import project.hrms.business.abstracts.GetAuthenticatedUserService;
import project.hrms.business.abstracts.JobseekerService;
import project.hrms.business.abstracts.UserService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorDataResult;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.User;
@Service
public class GetAuthenticatedUserManager implements GetAuthenticatedUserService{
	UserService userService;
	JobseekerService jobseekerService;
	EmployerService employerService;
	@Autowired
	public GetAuthenticatedUserManager(UserService userService,JobseekerService jobseekerService,EmployerService employerService) {
		this.userService = userService;
		this.jobseekerService = jobseekerService;
		this.employerService = employerService;
		
	}
	@Override
	public DataResult<User> getUser() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final User currentUser = userService.getByEmail(auth.getName()).getData();
		return new SuccessDataResult<User>(currentUser);
	}
	public DataResult<Jobseeker> getJobseeker(){
		User currentUser = getUser().getData();
		Jobseeker jobseeker = jobseekerService.getById(currentUser.getId()).getData();
		return new SuccessDataResult<Jobseeker>(jobseeker);
	}
	public DataResult<Employer> getEmployer(){
		User currentUser = getUser().getData();
		Employer employer = employerService.getById(currentUser.getId()).getData();
		return new SuccessDataResult<Employer>(employer);
	}
}
