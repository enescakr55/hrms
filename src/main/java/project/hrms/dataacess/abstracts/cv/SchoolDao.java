package project.hrms.dataacess.abstracts.cv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.cv.School;

public interface SchoolDao extends JpaRepository<School, Integer>{
	List<School> getByJobseeker(int jonseekerId);
}
