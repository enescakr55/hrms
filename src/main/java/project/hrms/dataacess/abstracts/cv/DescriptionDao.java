package project.hrms.dataacess.abstracts.cv;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.cv.Description;

public interface DescriptionDao extends JpaRepository<Description, Integer>{
	Description getByJobseeker(int jobseekerId);
}
