package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.JobTypeService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.JobTypeDao;
import project.hrms.entities.concretes.JobType;
@Service
public class JobTypeManager implements JobTypeService{
	JobTypeDao jobTypeDao;
	@Autowired
	public JobTypeManager(JobTypeDao jobTypeDao) {
		super();
		this.jobTypeDao = jobTypeDao;
	}
	@Override
	public Result add(JobType jobType) {
		jobTypeDao.save(jobType);
		return new SuccessResult("Başarıyla Eklendi");
	}

	@Override
	public Result update(JobType jobType) {
		jobTypeDao.save(jobType);
		return new SuccessResult("Başarıyla Güncellendi");
	}

	@Override
	public Result delete(int jobTypeId) {
		jobTypeDao.delete(jobTypeDao.getOne(jobTypeId));
		return new SuccessResult("Başarıyla Silindi");
	}

	@Override
	public DataResult<List<JobType>> getAll() {
		return new SuccessDataResult<List<JobType>>(jobTypeDao.findAll());

	}

	@Override
	public DataResult<JobType> getById(int id) {
		return new SuccessDataResult<JobType>(jobTypeDao.getOne(id));

	}



}
