package project.hrms.business.abstracts;

import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.User;

public interface MailVerifyService {
	public Result SendVerificationCode(User user);
	public Result VerifyAccount(String code);
}
