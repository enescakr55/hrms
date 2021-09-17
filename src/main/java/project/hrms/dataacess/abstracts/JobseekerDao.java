package project.hrms.dataacess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.Jobseeker;

public interface JobseekerDao extends JpaRepository<Jobseeker, Integer>{
	Jobseeker getByTcNo(String tcNo);
	Jobseeker getByUser_Id(int userId);

}
