package project.hrms.business.concretes;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.JobseekerService;
import project.hrms.business.abstracts.UserService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.core.utilities.services.FakeMernisService;
import project.hrms.dataacess.abstracts.JobseekerDao;
import project.hrms.entities.concretes.Jobseeker;
@Service
public class JobseekerManager implements JobseekerService{


	JobseekerDao jobseekerDao;
	UserService userService;
	@Autowired
	public JobseekerManager(JobseekerDao jobseekerDao,UserService userService) {
		super();
		this.userService = userService;
		this.jobseekerDao = jobseekerDao;
	}
	@Override
	public DataResult<List<Jobseeker>> getAll() {
		
		return new SuccessDataResult<List<Jobseeker>>(jobseekerDao.findAll());
	}

	@Override
	public Result add(Jobseeker jobseeker) {
		if(jobseekerDao.getByTcNo(jobseeker.getTcNo()) != null) {
			return new ErrorResult("Girilen TC No sisteme kayıtlı");
		}
		Locale trlocale= Locale.forLanguageTag("tr-TR");
		if(!FakeMernisService.isValid(jobseeker.getFirstName().toUpperCase(trlocale), jobseeker.getLastName().toUpperCase(trlocale), jobseeker.getTcNo(),jobseeker.getBirthYear())) {
			return new ErrorResult("Mernis doğrulaması başarısız oldu");
		}
		jobseekerDao.save(jobseeker);
		return new SuccessResult("Kayıt başarılı");
	}
	@Override
	public DataResult<Jobseeker> getByTcNo(String tcNo) {
		return new SuccessDataResult<Jobseeker>(jobseekerDao.getByTcNo(tcNo));
	}

}
