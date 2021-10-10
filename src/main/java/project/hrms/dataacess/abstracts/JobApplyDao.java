package project.hrms.dataacess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.hrms.entities.concretes.JobApply;

public interface JobApplyDao extends JpaRepository<JobApply, Integer>{
	@Query("From JobApply where jobadvert_id=:jobAdvertIds")
	List<JobApply> getJobAppliesWithJobAdvertId(int jobAdvertIds);
	
	
	JobApply getByJobseekerId_JobseekerIdAndJobAdvert_AdvertId(int jobseekerId,int jobAdvertId);
}
