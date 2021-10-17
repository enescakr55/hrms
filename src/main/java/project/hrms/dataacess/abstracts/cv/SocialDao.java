package project.hrms.dataacess.abstracts.cv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.cv.School;
import project.hrms.entities.concretes.cv.Social;

public interface SocialDao extends JpaRepository<Social, Integer>{
	List<Social> getByJobseeker_JobseekerId(int jobseekerId);
	Social getBySocialIdAndJobseeker_JobseekerId(int socialId,int jobseekerId);
}
