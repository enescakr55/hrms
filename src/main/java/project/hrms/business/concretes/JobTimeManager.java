package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.JobTimeService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.JobTimeDao;
import project.hrms.entities.concretes.JobTime;
@Service
public class JobTimeManager implements JobTimeService{
	JobTimeDao jobTimeDao;
	@Autowired
	public JobTimeManager(JobTimeDao jobTimeDao) {
		super();
		this.jobTimeDao = jobTimeDao;
	}
	@Override
	public Result add(JobTime jobTime) {
		jobTimeDao.save(jobTime);
		return new SuccessResult("Başarıyla Eklendi");
	}

	@Override
	public Result delete(int jobTimeId) {
		jobTimeDao.delete(jobTimeDao.getOne(jobTimeId));
		return new SuccessResult("Başarıyla Silindi");
	}

	@Override
	public Result update(JobTime jobTime) {
		jobTimeDao.save(jobTime);
		return new SuccessResult("Başarıyla Güncellendi");
	}

	@Override
	public DataResult<List<JobTime>> getAll() {
		return new SuccessDataResult<List<JobTime>>(jobTimeDao.findAll());

	}

	@Override
	public DataResult<JobTime> getById(int id) {
		return new SuccessDataResult<JobTime>(jobTimeDao.getOne(id));

	}



}
