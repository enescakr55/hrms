package project.hrms.dataacess.abstracts.cv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.cv.School;

public interface SchoolDao extends JpaRepository<School, Integer>{
	List<School> getByJobseeker_JobseekerId(int jonseekerId);
	School getBySchoolIdAndJobseeker_JobseekerId(int schoolId,int jobseekerId);
}
