package project.hrms.business.concretes.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.GetAuthenticatedUserService;
import project.hrms.business.abstracts.JobseekerService;
import project.hrms.business.abstracts.RoleService;
import project.hrms.business.abstracts.cv.ExperienceService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.cv.ExperienceDao;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.cv.Experience;
import project.hrms.entities.concretes.dtos.ExperienceAddDto;
@Service
public class ExperienceManager implements ExperienceService{
		ExperienceDao experienceDao;
		JobseekerService jobseekerService;
		GetAuthenticatedUserService getAuthenticatedUserService;
		RoleService roleService;
		@Autowired
		public ExperienceManager(ExperienceDao experienceDao,JobseekerService jobseekerService,GetAuthenticatedUserService getAuthenticatedUserService,RoleService roleService) {
			// TODO Auto-generated constructor stub
			super();
			this.roleService = roleService;
			this.experienceDao = experienceDao;
			this.jobseekerService = jobseekerService;
			this.getAuthenticatedUserService = getAuthenticatedUserService;
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
		return new SuccessDataResult<List<Experience>>(experienceDao.getByJobseeker_JobseekerId(jobseekerId));
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
	@Override
	public DataResult<List<Experience>> getByUserId(int userid) {
		Jobseeker jobseeker = jobseekerService.getById(userid).getData();
		return new SuccessDataResult<List<Experience>>(experienceDao.getByJobseeker_JobseekerId(jobseeker.getJobseekerId()));
	}
	@Override
	public Result deleteMyExperience(int experienceId) {
		Jobseeker jobseeker = getAuthenticatedUserService.getJobseeker().getData();
		Experience exp = experienceDao.getByExperienceIdAndJobseeker_JobseekerId(experienceId, jobseeker.getJobseekerId());
		experienceDao.delete(exp);
		return new SuccessResult();
	}
	@Override
	public Result addMyExperience(ExperienceAddDto experience) {
		Jobseeker jobseeker = getAuthenticatedUserService.getJobseeker().getData();
		if(jobseeker != null) {
			Experience exp = new Experience();
			exp.setJobseeker(jobseeker);
			exp.setCompanyName(experience.getCompanyName());
			exp.setEndDate(experience.getEndDate());
			exp.setStartDate(experience.getStartDate());
			exp.setRole(roleService.getRoleByRoleId(experience.getRoleId()).getData());
			experienceDao.save(exp);
			return new SuccessResult();
		}
		return new ErrorResult();
	}

}
