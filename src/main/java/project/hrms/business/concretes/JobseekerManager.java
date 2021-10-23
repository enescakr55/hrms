package project.hrms.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.hrms.business.abstracts.ImageUploadService;
import project.hrms.business.abstracts.JobApplyService;
import project.hrms.business.abstracts.JobseekerService;
import project.hrms.business.abstracts.UserService;
import project.hrms.business.abstracts.cv.SocialService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.core.utilities.services.FakeMernisService;
import project.hrms.dataacess.abstracts.JobseekerDao;
import project.hrms.entities.concretes.JobApply;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.ProfileImage;
import project.hrms.entities.concretes.User;
import project.hrms.entities.concretes.cv.Social;
import project.hrms.entities.concretes.dtos.JobseekerRegisterDto;
import project.hrms.entities.concretes.dtos.JobseekersListDto;
@Service
public class JobseekerManager implements JobseekerService{


	JobseekerDao jobseekerDao;
	UserService userService;
	ImageUploadService imageService;
	JobApplyService jobApplyService;
	@Autowired
	public JobseekerManager(JobseekerDao jobseekerDao,UserService userService,ImageUploadService imageService,JobApplyService jobApplyService) {
		super();
		this.userService = userService;
		this.jobseekerDao = jobseekerDao;
		this.imageService = imageService;
		this.jobApplyService = jobApplyService;
	}
	@Override
	public DataResult<List<Jobseeker>> getAll() {
		
		return new SuccessDataResult<List<Jobseeker>>(jobseekerDao.findAll());
	}

	@Override
	public Result add(Jobseeker jobseeker) {
		if(jobseekerDao.getByTcNo(jobseeker.getTcNo()) != null) {
			return new ErrorResult("Girilen TC No sisteme kayıtlı");
		}
		Locale trlocale= Locale.forLanguageTag("tr-TR");
		if(!FakeMernisService.isValid(jobseeker.getFirstName().toUpperCase(trlocale), jobseeker.getLastName().toUpperCase(trlocale), jobseeker.getTcNo(),jobseeker.getBirthYear())) {
			return new ErrorResult("Mernis doğrulaması başarısız oldu");
		}
		jobseekerDao.save(jobseeker);
		return new SuccessResult("Kayıt başarılı");
	}
	@Override
	public DataResult<Jobseeker> getByTcNo(String tcNo) {
		return new SuccessDataResult<Jobseeker>(jobseekerDao.getByTcNo(tcNo));
	}
	@Override
	public boolean isJobseeker(int userId) {
		Jobseeker jobseeker = jobseekerDao.getByUser_Id(userId);
		if(jobseeker != null) {
			return true;
		}
		return false;
	}
	@Override
	public DataResult<Jobseeker> getById(int id) {
		return new SuccessDataResult<Jobseeker>( jobseekerDao.getByUser_Id(id));
	}
	@Override
	@Transactional
	public Result jobseekerRegister(JobseekerRegisterDto jobseekerRegister) {
		User newUser = new User();
		newUser.setEmail(jobseekerRegister.getEmail());
		newUser.setPassword(jobseekerRegister.getPassword());
		newUser.setVerified(false);
		Jobseeker newJobseeker = new Jobseeker();
		newJobseeker.setBirthYear(jobseekerRegister.getBirthYear());
		newJobseeker.setFirstName(jobseekerRegister.getFirstName());
		newJobseeker.setLastName(jobseekerRegister.getLastName());
		newJobseeker.setTcNo(jobseekerRegister.getTcNo());
		newJobseeker.setPhoneNumber(jobseekerRegister.getPhoneNumber());
		Result userAdd = userService.add(newUser);
		if(userAdd.isSuccess()) {
			newJobseeker.setUser(newUser);
			Result addJobseekerResult = add(newJobseeker);
			if(addJobseekerResult.isSuccess()) {
				return addJobseekerResult;
			}else {
				userService.delete(newUser);
				return addJobseekerResult;
			}
		}else {
			return userAdd;
		}
		
	}
	@Override
	public DataResult<List<JobseekersListDto>> getJobseekerDetails(List<Jobseeker> jobseekerList) {
		List<JobseekersListDto> jobseekerDetails = new ArrayList<JobseekersListDto>();
		for (Jobseeker js : jobseekerList) {
			Jobseeker jobseeker = jobseekerDao.getByJobseekerId(js.getJobseekerId());
			User user = jobseeker.getUser();
			ProfileImage profileImage = imageService.getProfilePictureByUserId(user.getId()).getData();
			JobseekersListDto jsld = new JobseekersListDto(user, jobseeker, profileImage);
			jobseekerDetails.add(jsld);
			
		}
		return new SuccessDataResult<List<JobseekersListDto>>(jobseekerDetails);
	}
	@Override
	public DataResult<List<JobseekersListDto>> getAllJobseekerDetails() {
		return getJobseekerDetails(jobseekerDao.findAll());
	}
	public DataResult<List<JobseekersListDto>> getJobseekersIfJobAdvertApply(int jobAdvertId){
		List<JobseekersListDto> jobseekerDetails = new ArrayList<JobseekersListDto>();
		List<JobApply> jobApplies = jobApplyService.getByJobAdvertId(jobAdvertId).getData();
		for (JobApply jobApply : jobApplies) {
			Jobseeker jobseeker = jobApply.getJobseekerId();
			User user = jobseeker.getUser();
			ProfileImage profileImage = imageService.getProfilePictureByUserId(user.getId()).getData();
			JobseekersListDto jsld = new JobseekersListDto(user, jobseeker, profileImage);
			jobseekerDetails.add(jsld);
		}
		return new SuccessDataResult<List<JobseekersListDto>>(jobseekerDetails);
	}


}
