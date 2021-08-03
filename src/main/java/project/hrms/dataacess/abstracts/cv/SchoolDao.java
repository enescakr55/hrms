package project.hrms.dataacess.abstracts.cv;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.cv.School;

public interface SchoolDao extends JpaRepository<School, Integer>{

}
