package project.hrms.business.concretes.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.cv.LanguageService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.cv.LanguageDao;
import project.hrms.entities.concretes.cv.Language;
@Service
public class LanguageManager implements LanguageService{
	LanguageDao languageDao;
	@Autowired
	public LanguageManager(LanguageDao languageDao) {
		this.languageDao = languageDao;
	}
	@Override
	public Result add(Language language) {
		languageDao.save(language);
		return new SuccessResult();
	}

	@Override
	public Result delete(Language language) {
		languageDao.delete(language);
		return new SuccessResult();
	}


	@Override
	public DataResult<List<Language>> getAll() {
		return new SuccessDataResult<List<Language>>(languageDao.findAll());
	}

	@Override
	public Result update(Language language) {
		languageDao.save(language);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<Language>> getByJobseekerId(int jobseekerId) {
		return new SuccessDataResult<List<Language>>(languageDao.getByJobseeker(jobseekerId));
	}

}
