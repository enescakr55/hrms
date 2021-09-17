package project.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.EmployeeService;
import project.hrms.business.abstracts.EmployerService;
import project.hrms.business.abstracts.JobseekerService;
import project.hrms.business.abstracts.UserTypeService;
import project.hrms.core.entities.AuthenticatedEmployerInfo;
import project.hrms.core.entities.AuthenticatedUserInfo;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.entities.concretes.Employee;
import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.Jobseeker;
@Service
public class UserTypeManager implements UserTypeService{
	
	private EmployeeService employeeService;
	private EmployerService employerService;
	private JobseekerService jobseekerService;
	@Autowired
	public UserTypeManager(EmployeeService employeeService, EmployerService employerService,
			JobseekerService jobseekerService) {
		super();
		this.employeeService = employeeService;
		this.employerService = employerService;
		this.jobseekerService = jobseekerService;
	}
	@Override
	public DataResult<String> getUserType(int userid) {
		String userType = "";
		if(employeeService.isEmployee(userid)) {
			userType = "employee";
		}
		if(employerService.isEmployer(userid)) {
			userType = "employer";
		}
		if(jobseekerService.isJobseeker(userid)) {
			userType = "jobseeker";
		}
		return new DataResult<String>(userType, true);
	}
	@Override
	public DataResult<AuthenticatedEmployerInfo> GetEmployerInfo(int userid) {
		String type = getUserType(userid).getData();
		System.out.println(type);
		if(type == "employer") {
			Employer employer = employerService.getById(userid).getData();
			AuthenticatedEmployerInfo authenticatedEmployerInfo = new AuthenticatedEmployerInfo(employer.getCompanyName());
			return new SuccessDataResult<AuthenticatedEmployerInfo>( authenticatedEmployerInfo);
		}
		return null;
	}
	@Override
	public DataResult<AuthenticatedUserInfo> GetUserInfo(int userId) {
		String type = getUserType(userId).getData();
		if(type == "jobseeker") {
			Jobseeker jobseeker = jobseekerService.getById(userId).getData();
			AuthenticatedUserInfo authenticatedUserInfo = new AuthenticatedUserInfo(jobseeker.getFirstName(), jobseeker.getLastName());
			return new SuccessDataResult<AuthenticatedUserInfo>( authenticatedUserInfo);
		}else if(type=="employee") {
			Employee employee = employeeService.getById(userId).getData();
			AuthenticatedUserInfo authenticatedUserInfo = new AuthenticatedUserInfo(employee.getFirstName(), employee.getLastName());
			return new SuccessDataResult<AuthenticatedUserInfo>( authenticatedUserInfo);
		}
		return null;
	}


}
