package project.hrms.dataacess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.hrms.entities.concretes.JobAdvert;

public interface JobAdvertDao extends JpaRepository<JobAdvert,Integer>{
	JobAdvert getByAdvertId(int advertId);
	@Query("From JobAdvert where active=true")
	List<JobAdvert> getByActiveTrue();
	
	@Query("From JobAdvert where active=false")
	List<JobAdvert> getByActiveFalse();
	
	@Query("From JobAdvert where active=true order by lastDate desc")
	List<JobAdvert> getByActiveTrueOrderByDate();
	
	@Query("From JobAdvert where active=true and employerId=:employerId")
	List<JobAdvert> getByEmployerId(int employerId);
	
	
}
