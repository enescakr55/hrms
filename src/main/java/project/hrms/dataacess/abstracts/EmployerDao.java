package project.hrms.dataacess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.User;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	Employer getByUser_Id(int userId);
}
