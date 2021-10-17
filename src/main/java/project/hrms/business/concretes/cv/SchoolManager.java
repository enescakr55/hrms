package project.hrms.business.concretes.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.GetAuthenticatedUserService;
import project.hrms.business.abstracts.JobseekerService;
import project.hrms.business.abstracts.cv.SchoolService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.cv.SchoolDao;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.cv.Language;
import project.hrms.entities.concretes.cv.School;
@Service
public class SchoolManager implements SchoolService{
	SchoolDao schoolDao;
	JobseekerService jobseekerService;
	GetAuthenticatedUserService getAuthenticatedUserService;
	@Autowired
	public SchoolManager(SchoolDao schoolDao,JobseekerService jobseekerService,GetAuthenticatedUserService getAuthenticatedUserService) {
		// TODO Auto-generated constructor stub
		this.schoolDao = schoolDao;
		this.jobseekerService = jobseekerService;
		this.getAuthenticatedUserService = getAuthenticatedUserService;
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
		return new SuccessDataResult<List<School>>(schoolDao.getByJobseeker_JobseekerId(jobseekerId));
	}
	@Override
	public DataResult<List<School>> getByUserId(int userid) {
		Jobseeker jobseeker = jobseekerService.getById(userid).getData();
		return new SuccessDataResult<List<School>>(schoolDao.getByJobseeker_JobseekerId(jobseeker.getJobseekerId()));
	}
	@Override
	public Result addMySchool(School school) {
		DataResult<Jobseeker> jobseekerResult = getAuthenticatedUserService.getJobseeker();
		if(jobseekerResult != null) {
			Jobseeker jobseeker = jobseekerResult.getData();
			if(school.getSchoolId() == 0) {
				school.setJobseeker(jobseeker);
				schoolDao.save(school);
				return new SuccessResult();
			}else {
				School s = schoolDao.getBySchoolIdAndJobseeker_JobseekerId(school.getSchoolId(),jobseekerResult.getData().getJobseekerId());
				if(s != null) {
					s.setDepartmentName(school.getDepartmentName());
					s.setGraduationYear(school.getGraduationYear());
					s.setSchoolName(school.getSchoolName());
					s.setStartYear(school.getStartYear());
					schoolDao.save(s);
					return new SuccessResult();
				}else {
					return new ErrorResult();
				}
			}
		}
		return new ErrorResult();
	}
	@Override
	public Result deleteMySchool(int schoolId) {
		DataResult<Jobseeker> jobseekerResult = getAuthenticatedUserService.getJobseeker();
		if(jobseekerResult != null) {
			Jobseeker jobseeker = jobseekerResult.getData();
			School sch = schoolDao.getBySchoolIdAndJobseeker_JobseekerId(schoolId, jobseeker.getJobseekerId());
			schoolDao.delete(sch);
			return new SuccessResult();
		}
		return new ErrorResult();
	
		
	}

}
