package project.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.MailVerifyService;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.User;


@RestController
@RequestMapping("/api/mail")
@CrossOrigin
public class MailController {
	MailVerifyService mailVerify;
	@Autowired
	public MailController(MailVerifyService mailVerify) {
		super();
		this.mailVerify = mailVerify;
	}
	@PostMapping("/send")
	public Result SendVerifyMail(@RequestBody User user) {
		return mailVerify.SendVerificationCode(user);
	}
	@GetMapping("/verify")
	public Result VerifyAccount(String verifyCode) {
		return mailVerify.VerifyAccount(verifyCode);
	}
}
