package project.hrms.business.concretes.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.GetAuthenticatedUserService;
import project.hrms.business.abstracts.JobseekerService;
import project.hrms.business.abstracts.cv.LanguageService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.cv.LanguageDao;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.cv.Experience;
import project.hrms.entities.concretes.cv.Language;

@Service
public class LanguageManager implements LanguageService {
	LanguageDao languageDao;
	JobseekerService jobseekerService;
	GetAuthenticatedUserService getAuthenticatedUserService;

	@Autowired
	public LanguageManager(LanguageDao languageDao, JobseekerService jobseekerService,
			GetAuthenticatedUserService getAuthenticatedUserService) {
		this.languageDao = languageDao;
		this.jobseekerService = jobseekerService;
		this.getAuthenticatedUserService = getAuthenticatedUserService;
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
		return new SuccessDataResult<List<Language>>(languageDao.getByJobseeker_JobseekerId(jobseekerId));
	}

	@Override
	public DataResult<List<Language>> getByUserId(int userid) {
		Jobseeker jobseeker = jobseekerService.getById(userid).getData();
		return new SuccessDataResult<List<Language>>(
				languageDao.getByJobseeker_JobseekerId(jobseeker.getJobseekerId()));
	}

	@Override
	public Result addMyLanguage(Language language) {
		if (language.getLanguageLevel() > 0 && language.getLanguageLevel() < 6) {

			DataResult<Jobseeker> jobseekerResult = getAuthenticatedUserService.getJobseeker();
			Jobseeker jobseeker = jobseekerResult.getData();
			if (jobseeker != null) {
				language.setJobseeker(jobseeker);
				language.setLanguageId(0);
				languageDao.save(language);
				return new SuccessResult();
			}
		}
		return new ErrorResult();

	}

	@Override
	public Result deleteMyLanguage(int languageId) {
		DataResult<Jobseeker> jobseekerResult = getAuthenticatedUserService.getJobseeker();
		Jobseeker jobseeker = jobseekerResult.getData();
		if (jobseeker != null) {

			Language language = languageDao.getByLanguageIdAndJobseeker_JobseekerId(languageId,
					jobseeker.getJobseekerId());
			languageDao.delete(language);
			return new SuccessResult();
		}
		return new ErrorResult();
	}

}
