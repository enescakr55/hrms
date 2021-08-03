package project.hrms.dataacess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.MailVerify;

public interface MailVerifyDao extends JpaRepository<MailVerify, Integer> {
	MailVerify getByverificationCode(String verificationCode);
}
