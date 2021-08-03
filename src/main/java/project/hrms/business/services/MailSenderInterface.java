package project.hrms.business.services;

import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.User;

public interface MailSenderInterface {
	public boolean SendVerificationMail(User user,String verificationCode);
}
