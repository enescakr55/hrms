package project.hrms.dataacess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.Jobseeker;

public interface JobseekerDao extends JpaRepository<Jobseeker, Integer>{
	Jobseeker getByTcNo(String tcNo);

}
