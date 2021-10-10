package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.JobApplyService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.JobApplyDao;
import project.hrms.entities.concretes.JobAdvert;
import project.hrms.entities.concretes.JobApply;

@Service
public class JobApplyManager implements JobApplyService{
	
	JobApplyDao jobApplyDao;
	
	@Autowired
	public JobApplyManager(JobApplyDao jobApplyDao) {
		super();
		this.jobApplyDao = jobApplyDao;
	}

	@Override
	public DataResult<List<JobApply>> getAll() {
		return new SuccessDataResult<List<JobApply>>( jobApplyDao.findAll());
	}

	@Override
	public DataResult<List<JobApply>> getByJobAdvertId(int jobAdvertId) {
		return new SuccessDataResult<List<JobApply>>(jobApplyDao.getJobAppliesWithJobAdvertId(jobAdvertId));
	}

	@Override
	public Result add(JobApply jobApply) {
		jobApplyDao.save(jobApply);
		return new SuccessResult("Başvuru iletildi");
	}

	@Override
	public Result update(JobApply jobApply) {
		jobApplyDao.save(jobApply);
		
		return new SuccessResult("Başvuru güncellendi");
	}

	@Override
	public Result delete(int jobApplyId) {
		jobApplyDao.delete(null);
		return new SuccessResult("Başvuru silindi");
	}
	@Override
	public DataResult<JobApply> getJobApplyWithJobseekerIdAndJobAdvertId(int jobseekerId,int jobAdvertId){
		return new SuccessDataResult<JobApply>(jobApplyDao.getByJobseekerId_JobseekerIdAndJobAdvert_AdvertId(jobseekerId,jobAdvertId));
	}

	@Override
	public DataResult<Integer> getJobApplyCountByJobAdvert(JobAdvert jobAdvert) {
		return new SuccessDataResult<Integer>(Integer.parseInt(String.valueOf(jobApplyDao.getJobAppliesWithJobAdvertId(jobAdvert.getAdvertId()).size())));
		
	}
	


}
