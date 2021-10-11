package project.hrms.dataacess.abstracts.cv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.cv.Experience;

public interface ExperienceDao extends JpaRepository<Experience, Integer>{
	List<Experience> getByJobseeker(int jobseekerId);
}
