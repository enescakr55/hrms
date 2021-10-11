package project.hrms.business.concretes.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.cv.SocialService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.cv.SocialDao;
import project.hrms.entities.concretes.cv.Social;
@Service
public class SocialManager implements SocialService{
	SocialDao socialDao;
	@Autowired
	public SocialManager(SocialDao socialDao) {
		this.socialDao = socialDao;
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
		return new SuccessDataResult<List<Social>>(socialDao.getByJobseeker(jobseekerId));
	}

}
