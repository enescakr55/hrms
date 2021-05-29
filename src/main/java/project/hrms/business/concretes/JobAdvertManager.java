package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.JobAdvertService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.JobAdvertDao;
import project.hrms.entities.concretes.JobAdvert;

@Service
public class JobAdvertManager implements JobAdvertService{
	JobAdvertDao jobAdvertDao;
	
	@Autowired
	public JobAdvertManager(JobAdvertDao jobAdvertDao) {
		super();
		this.jobAdvertDao = jobAdvertDao;
	}
	@Override
	public DataResult<List<JobAdvert>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<JobAdvert>>( jobAdvertDao.findAll());
	}

	@Override
	public Result add(JobAdvert jobAdvert) {
		// TODO Auto-generated method stub
		jobAdvertDao.save(jobAdvert);
		return new SuccessResult("Başarıyla kayıt edildi");
	}
	@Override
	public DataResult<List<JobAdvert>> getIsActive() {
		return new SuccessDataResult<List<JobAdvert>>(jobAdvertDao.getByActiveTrue());
	}
	@Override
	public DataResult<List<JobAdvert>> getByEmployerId(int employerId) {
		return new SuccessDataResult<List<JobAdvert>>(jobAdvertDao.getByEmployerId(employerId));
		
	}
	public Result closeAdvert(int advertId) {
		JobAdvert jobAdvert = jobAdvertDao.getByAdvertId(advertId);
		jobAdvert.setActive(false);
		jobAdvertDao.save(jobAdvert);
		return new SuccessResult("İş ilanı alıma kapatıldı");
	}
	@Override
	public DataResult<List<JobAdvert>> getIsActiveOrderByDate() {
		return new SuccessDataResult<List<JobAdvert>>(jobAdvertDao.getByActiveTrueOrderByDate());
	}


	

}
