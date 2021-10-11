package project.hrms.business.concretes.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.cv.ExperienceService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.cv.ExperienceDao;
import project.hrms.entities.concretes.cv.Experience;
@Service
public class ExperienceManager implements ExperienceService{
		ExperienceDao experienceDao;
		@Autowired
		public ExperienceManager(ExperienceDao experienceDao) {
			// TODO Auto-generated constructor stub
			super();
			this.experienceDao = experienceDao;
		}
	@Override
	public Result add(Experience experience) {
		experienceDao.save(experience);
		return new SuccessResult();
	}

	@Override
	public Result delete(Experience experience) {
		experienceDao.delete(experience);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<Experience>> getByJobseekerId(int jobseekerId) {
		return new SuccessDataResult<List<Experience>>(experienceDao.getByJobseeker(jobseekerId));
	}

	@Override
	public DataResult<List<Experience>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<Experience>>(experienceDao.findAll());
	}

	@Override
	public Result update(Experience experience) {
		experienceDao.save(experience);
		return new SuccessResult();
	}

}
