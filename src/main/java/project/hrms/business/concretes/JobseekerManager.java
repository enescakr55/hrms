package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.JobseekerService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.JobseekerDao;
import project.hrms.entities.concretes.Jobseeker;
@Service
public class JobseekerManager implements JobseekerService{


	JobseekerDao jobseekerDao;
	@Autowired
	public JobseekerManager(JobseekerDao jobseekerDao) {
		super();
		this.jobseekerDao = jobseekerDao;
	}
	@Override
	public DataResult<List<Jobseeker>> getAll() {
		
		return new SuccessDataResult<List<Jobseeker>>(jobseekerDao.findAll());
	}

	@Override
	public Result add(Jobseeker jobseeker) {
		jobseekerDao.save(jobseeker);
		return new SuccessResult("Kayıt başarılı");
	}

}
