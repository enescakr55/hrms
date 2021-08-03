package project.hrms.dataacess.abstracts.cv;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.cv.Experience;

public interface ExperienceDao extends JpaRepository<Experience, Integer>{

}
