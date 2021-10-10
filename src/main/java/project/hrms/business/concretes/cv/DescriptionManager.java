package project.hrms.business.concretes.cv;

import java.util.List;

import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.cv.DescriptionService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.cv.DescriptionDao;
import project.hrms.entities.concretes.cv.Description;
@Service
public class DescriptionManager implements DescriptionService{
	
	DescriptionDao descriptionDao;
	public DescriptionManager(DescriptionDao descriptionDao) {
		// TODO Auto-generated constructor stub
		super();
		this.descriptionDao = descriptionDao;
	}
	
	@Override
	public Result add(Description description) {
		descriptionDao.save(description);
		return new SuccessResult();
	}

	@Override
	public Result delete(Description description) {
		descriptionDao.delete(description);
		return new SuccessResult();
	}

	@Override
	public DataResult<Description> getByJobseekerId(int jobseekerId) {
		return new SuccessDataResult<Description>(descriptionDao.getByJobseeker(jobseekerId));
		}

	@Override
	public DataResult<List<Description>> getAll() {
		return new SuccessDataResult<List<Description>>(descriptionDao.findAll());
	}

	@Override
	public Result update(Description description) {
		descriptionDao.save(description);
		return new SuccessResult();
	}

}
