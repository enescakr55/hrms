package project.hrms.business.concretes;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.EmployeeService;
import project.hrms.business.abstracts.EmployerService;
import project.hrms.business.abstracts.JobseekerService;
import project.hrms.business.abstracts.MailVerifyService;
import project.hrms.business.abstracts.UserService;
import project.hrms.business.services.MailSenderInterface;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.MailVerifyDao;
import project.hrms.entities.concretes.MailVerify;
import project.hrms.entities.concretes.User;
@Service
public class MailVerifyManager implements MailVerifyService {

	MailSenderInterface mailSender;
	MailVerifyDao mailDao;
	UserService userService;
	EmployerService employerService;
	EmployeeService employeeService;
	JobseekerService jobseekerService;
	@Autowired
	public MailVerifyManager(MailSenderInterface mailSender,MailVerifyDao mailDao,UserService userService,EmployerService employerService,EmployeeService employeeService,JobseekerService jobseekerService) {
		super();
		this.mailSender = mailSender;
		this.mailDao = mailDao;
		this.userService = userService;
		this.employerService = employerService;
		this.employeeService = employeeService;
		this.jobseekerService = jobseekerService;
	}
	@Override
	public Result SendVerificationCode(User user) {
		User currentUser = userService.getByEmail(user.getEmail()).getData();
		String kod = getSaltString();
		MailVerify entity = new MailVerify();
		entity.setUserId(currentUser.getId());
		entity.setVerificationCode(kod);
		mailDao.save(entity);
		boolean isSend = mailSender.SendVerificationMail(user,kod);
		return new Result(isSend);
	}

	@Override
	public Result VerifyAccount(String code) {
		MailVerify isCode = mailDao.getByverificationCode(code);
		if(isCode != null) {
			System.out.println(isCode.getId());
			return userService.verifyUser(isCode.userId);
		}
		return new ErrorResult();
	}
	protected String getSaltString() {
        String SALTCHARS = "abcdefghijklmnoprstyuvzxqw1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }


	

}
