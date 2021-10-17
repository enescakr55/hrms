package project.hrms.dataacess.abstracts.cv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.cv.Description;
import project.hrms.entities.concretes.cv.Experience;

public interface ExperienceDao extends JpaRepository<Experience, Integer>{
	List<Experience> getByJobseeker_JobseekerId(int jobseekerId);
	Experience getByExperienceIdAndJobseeker_JobseekerId(int descriptionId,int jobseekerId);
	Experience getByExperienceId(int experienceId);
}
