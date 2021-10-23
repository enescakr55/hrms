package project.hrms.business.concretes.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.GetAuthenticatedUserService;
import project.hrms.business.abstracts.JobseekerService;
import project.hrms.business.abstracts.cv.SocialService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.cv.SocialDao;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.cv.Language;
import project.hrms.entities.concretes.cv.Social;
@Service
public class SocialManager implements SocialService{
	SocialDao socialDao;
	JobseekerService jobseekerService;
	GetAuthenticatedUserService getAuthenticatedUserService;
	@Autowired
	public SocialManager(SocialDao socialDao,JobseekerService jobseekerService,GetAuthenticatedUserService getAuthenticatedUserService) {
		this.socialDao = socialDao;
		this.jobseekerService = jobseekerService;
		this.getAuthenticatedUserService = getAuthenticatedUserService;
	}
	
	@Override
	public Result add(Social social) {
		socialDao.save(social);
		return new SuccessResult();
	}

	@Override
	public Result delete(Social social) {
		socialDao.delete(social);
		return new SuccessResult();
	}


	@Override
	public DataResult<List<Social>> getAll() {
		return new SuccessDataResult<List<Social>>(socialDao.findAll());
	}

	@Override
	public Result update(Social social) {
		socialDao.save(social);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<Social>> getByJobseekerId(int jobseekerId) {
		return new SuccessDataResult<List<Social>>(socialDao.getByJobseeker_JobseekerId(jobseekerId));
	}

	@Override
	public DataResult<List<Social>> getByUserId(int userid) {
		Jobseeker jobseeker = jobseekerService.getById(userid).getData();
		return new SuccessDataResult<List<Social>>(socialDao.getByJobseeker_JobseekerId(jobseeker.getJobseekerId()));
	}

	@Override
	public Result addMySocial(Social social) {
		Jobseeker jobseeker = getAuthenticatedUserService.getJobseeker().getData();
		if(jobseeker != null) {
			social.setJobseeker(jobseeker);
			socialDao.save(social);
			return new SuccessResult();
		}
		return new ErrorResult();
	}

	@Override
	public Result deleteMySocial(int socialId) {
		Jobseeker jobseeker = getAuthenticatedUserService.getJobseeker().getData();
		if(jobseeker != null) {
			Social social = socialDao.getBySocialIdAndJobseeker_JobseekerId(socialId, jobseeker.getJobseekerId());
			socialDao.delete(social);
			return new SuccessResult();
		}
		return new ErrorResult();
	}


}
