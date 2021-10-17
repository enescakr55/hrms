package project.hrms.business.concretes.cv;

import java.util.List;

import javax.security.auth.login.AccountExpiredException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.swagger.annotations.Authorization;
import project.hrms.business.abstracts.GetAuthenticatedUserService;
import project.hrms.business.abstracts.cv.DescriptionService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.cv.DescriptionDao;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.cv.Description;
@Service
public class DescriptionManager implements DescriptionService{
	
	DescriptionDao descriptionDao;
	GetAuthenticatedUserService getAuthenticatedUserService;
	@Autowired
	public DescriptionManager(DescriptionDao descriptionDao,GetAuthenticatedUserService getAuthenticatedUserService) {
		// TODO Auto-generated constructor stub
		super();
		this.descriptionDao = descriptionDao;
		this.getAuthenticatedUserService = getAuthenticatedUserService;
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
		return new SuccessDataResult<Description>(descriptionDao.getByJobseeker_JobseekerId(jobseekerId));
		}

	@Override
	public DataResult<List<Description>> getAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println(authentication);
		System.out.println("asdqwerty");
		System.out.println(authentication.getName());
		System.out.println(authentication.getAuthorities());
		return new SuccessDataResult<List<Description>>(descriptionDao.findAll());
	}

	@Override
	
	public Result update(Description description) {
		descriptionDao.save(description);
		return new SuccessResult();
	}

	@Override
	public Result updateMyDescription(Description description) {
		DataResult<Jobseeker> jobseekerResult = getAuthenticatedUserService.getJobseeker();
		if(jobseekerResult != null) {
			Jobseeker jobseeker = jobseekerResult.getData();
			Description desc = descriptionDao.getByJobseeker_JobseekerId(jobseeker.getJobseekerId());
			if(desc != null) {
				desc.setJobseeker(jobseeker);
				desc.setUserdescription(description.getUserdescription());
				descriptionDao.save(desc);
			}else {
				Description newdesc = new Description(0, jobseeker, description.getUserdescription());
				descriptionDao.save(newdesc);
			}
			return new SuccessResult();
		}
		return new ErrorResult();

		
		
		
	}


}
