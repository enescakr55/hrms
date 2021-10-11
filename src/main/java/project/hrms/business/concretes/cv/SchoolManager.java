package project.hrms.business.concretes.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.cv.SchoolService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.cv.SchoolDao;
import project.hrms.entities.concretes.cv.School;
@Service
public class SchoolManager implements SchoolService{
	SchoolDao schoolDao;
	@Autowired
	public SchoolManager(SchoolDao schoolDao) {
		// TODO Auto-generated constructor stub
		this.schoolDao = schoolDao;
	}
	@Override
	public Result add(School school) {
		schoolDao.save(school);
		return new SuccessResult();
	}

	@Override
	public Result delete(School school) {
		schoolDao.delete(school);
		return new SuccessResult();
	}


	@Override
	public DataResult<List<School>> getAll() {
		return new SuccessDataResult<List<School>>(schoolDao.findAll());
	}

	@Override
	public Result update(School school) {
		schoolDao.save(school);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<School>> getByJobseekerId(int jobseekerId) {
		return new SuccessDataResult<List<School>>(schoolDao.getByJobseeker(jobseekerId));
	}

}
