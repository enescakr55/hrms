package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.EmployerService;
import project.hrms.business.abstracts.GetAuthenticatedUserService;
import project.hrms.business.abstracts.PendingEmployerService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorDataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.PendingEmployerDao;
import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.PendingEmployer;
@Service
public class PendingEmployerManager implements PendingEmployerService{
	private PendingEmployerDao pendingEmployerDao;
	private EmployerService employerService;
	private GetAuthenticatedUserService getAuthenticatedUserService;
	@Autowired
	public PendingEmployerManager(PendingEmployerDao pendingEmployerDao,GetAuthenticatedUserService getAuthenticatedUserService,EmployerService employerService) {
		this.pendingEmployerDao = pendingEmployerDao;
		this.getAuthenticatedUserService = getAuthenticatedUserService;
		this.employerService = employerService;
	}
	
	@Override
	public Result update(PendingEmployer pendingEmployer) {
		Employer employer = getAuthenticatedUserService.getEmployer().getData();
		if(employer == null) {
			return new ErrorResult("Bir hata oluştu");
		}
		List<PendingEmployer> pendingEmployers = pendingEmployerDao.getByEmployerid(employer.getEmployerid());
		for (PendingEmployer pendingEmployer2 : pendingEmployers) {
			pendingEmployerDao.delete(pendingEmployer2);
		}
		pendingEmployer.setId(0);
		pendingEmployer.setEmployerid(employer.getEmployerid());
		pendingEmployer.setUser(employer.getUser());
		pendingEmployer.setVerified(employer.isVerified());
		pendingEmployerDao.save(pendingEmployer);
		return new SuccessResult("Şirket bilgileriniz için güncelleme talebiniz alındı. Sistem personeli tarafından onaylandıktan sonra değişiklikleriniz aktif olacaktır.");
		
		
	}

	@Override
	@PreAuthorize("hasRole('Admin')")
	public Result apply(int pendingEmployerId,int isOk) {
		PendingEmployer pendingEmployer = pendingEmployerDao.getById(pendingEmployerId);
		if(pendingEmployer == null) {
			return new ErrorResult();
		}
		if(isOk == 0) {
			pendingEmployerDao.delete(pendingEmployer);
			return new SuccessResult("Güncelleme talebi reddedildi");
		}else if(isOk == 1) {
			Employer employer = new Employer(pendingEmployer.getEmployerid(), pendingEmployer.getWebsite(), pendingEmployer.getPhoneNumber(), pendingEmployer.isVerified(), pendingEmployer.getCompanyName(), pendingEmployer.getUser());
			employerService.update(employer);
			pendingEmployerDao.delete(pendingEmployer);
			return new SuccessResult("Güncelleme talebi onaylandı");
		}
		return new ErrorResult();
	}

	@Override
	public DataResult<Boolean> isPending() {
		Employer employer = getAuthenticatedUserService.getEmployer().getData();
		if(employer == null) {
			return new ErrorDataResult<Boolean>("Bir hata oluştu");
		}
		List<PendingEmployer> pendingEmployers = pendingEmployerDao.getByEmployerid(employer.getEmployerid());
		if(pendingEmployers.size() > 0) {
			return new SuccessDataResult<Boolean>(true,"Son şirket güncelleme talebinizin geçerli olması için yetkili tarafından onaylanması gerekmektedir.");
		}else {
			return new SuccessDataResult<Boolean>(false,"Herhangi bir güncelleme talebi bulunmamaktadır.");
		}
	}

	@Override
	@PreAuthorize("hasRole('Admin')")
	public DataResult<List<PendingEmployer>> getAll() {
		return new SuccessDataResult<List<PendingEmployer>>(pendingEmployerDao.findAll());
	}

}
