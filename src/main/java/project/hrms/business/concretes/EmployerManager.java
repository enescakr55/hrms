package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.EmployerService;
import project.hrms.business.abstracts.UserService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.EmployerDao;
import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.User;
@Service
public class EmployerManager implements EmployerService{
	EmployerDao employerDao;
	UserService userService;
	@Autowired
	public EmployerManager(EmployerDao employerDao,UserService userService) {
		super();
		this.userService = userService;
		this.employerDao = employerDao;
	}
	@Override
	public DataResult<List<Employer>> getAll() {
			return new SuccessDataResult<List<Employer>>(employerDao.findAll());
	}

	@Override
	public Result add(Employer employer) {
		String email = userService.getById(employer.getUser().getId()).getData().getEmail();
		boolean equalsMailDomainAndWebsite = isEqualsMailDomainAndWebsite(email, employer.getWebsite());
		if(!equalsMailDomainAndWebsite) {
			return new ErrorResult("E-Posta adres uzantınız website adresiniz ile aynı olmalıdır");
		}
		employerDao.save(employer);
		return new SuccessResult("Kayıt başarılı");

	}
	private boolean isEqualsMailDomainAndWebsite(String email,String website) {
		String mailDomain = email.substring(email.indexOf("@") + 1);
		if(website.toLowerCase().equals(mailDomain)) {
			return true;
		}
		return false;
	}



}
