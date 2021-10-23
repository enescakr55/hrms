package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.JobAdvertService;
import project.hrms.business.abstracts.JobApplyService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.JobAdvertDao;
import project.hrms.entities.concretes.City;
import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.JobAdvert;
import project.hrms.entities.concretes.JobTime;
import project.hrms.entities.concretes.JobType;
import project.hrms.entities.concretes.Role;
import project.hrms.entities.concretes.dtos.JobAdvertAddDto;

@Service
public class JobAdvertManager implements JobAdvertService{
	JobAdvertDao jobAdvertDao;
	JobApplyService jobApplyService;
	
	@Autowired
	public JobAdvertManager(JobAdvertDao jobAdvertDao) {
		super();
		this.jobAdvertDao = jobAdvertDao;
	}
	@Override
	public DataResult<List<JobAdvert>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<JobAdvert>>( jobAdvertDao.findAll());
	}

	@Override
	public Result add(JobAdvert jobAdvert) {
		// TODO Auto-generated method stub
		jobAdvertDao.save(jobAdvert);
		return new SuccessResult("Başarıyla kayıt edildi");
	}
	@Override
	public DataResult<List<JobAdvert>> getIsActive() {
		return new SuccessDataResult<List<JobAdvert>>(jobAdvertDao.getByActiveTrue());
	}
	@Override
	@PreAuthorize("hasRole('Admin')")
	public DataResult<List<JobAdvert>> getIsNotActive() {
		return new SuccessDataResult<List<JobAdvert>>(jobAdvertDao.getByActiveFalse());
	}
	@Override
	public DataResult<List<JobAdvert>> getByEmployerId(int employerId) {
		return new SuccessDataResult<List<JobAdvert>>(jobAdvertDao.getByEmployerId(employerId));
		
	}
	public Result closeAdvert(int advertId) {
		JobAdvert jobAdvert = jobAdvertDao.getByAdvertId(advertId);
		jobAdvert.setActive(false);
		jobAdvertDao.save(jobAdvert);
		return new SuccessResult("İş ilanı alıma kapatıldı");
	}
	@Override
	public DataResult<List<JobAdvert>> getIsActiveOrderByDate() {
		return new SuccessDataResult<List<JobAdvert>>(jobAdvertDao.getByActiveTrueOrderByDate());
	}

	@Override
	public Result addwithdto(JobAdvertAddDto jobAdvertAddDto) {
		// TODO Auto-generated method stub
		Employer employer = new Employer(); employer.setEmployerid(jobAdvertAddDto.getEmployerid());
		City city = new City(); city.setCityId(jobAdvertAddDto.getCityId());
		Role role = new Role(); role.setId(jobAdvertAddDto.getRoleId());
		JobTime jobTime = new JobTime(); jobTime.setJobTimeId(jobAdvertAddDto.getJobTimeId());
		JobType jobType = new JobType(); jobType.setJobTypeId(jobAdvertAddDto.getJobTypeId());
		//JobAdvert jobAdvert = new JobAdvert(0, employer, role, city, jobAdvertAddDto.getMaxSalary(), jobAdvertAddDto.getMinSalary(), jobAdvertAddDto.getDescription(), jobAdvertAddDto.getOpenPositionNumber(), jobAdvertAddDto.getLastDate(), false, jobTime, jobType);
		JobAdvert jobAdvert = new JobAdvert(0, employer, role, city, jobAdvertAddDto.getMaxSalary(), jobAdvertAddDto.getMinSalary(), jobAdvertAddDto.getDescription(), jobAdvertAddDto.getOpenPositionNumber(), jobAdvertAddDto.getLastDate(), false, false, jobTime, jobType);
		jobAdvertDao.save(jobAdvert);
		return new SuccessResult("İş ilanı eklendi");
	}
	@Override
	@PreAuthorize("hasRole('Admin')")
	public Result jobAdvertApprove(JobAdvert jobadvert) {
		jobadvert.setActive(true);
		jobAdvertDao.save(jobadvert);
		return new SuccessResult("İlan onaylandı");
	}
	public DataResult<JobAdvert> getByAdvertId(int advertId){
		JobAdvert jobadvert = jobAdvertDao.getByAdvertId(advertId);
		return new SuccessDataResult<JobAdvert>(jobadvert);
	}


	


	

}
