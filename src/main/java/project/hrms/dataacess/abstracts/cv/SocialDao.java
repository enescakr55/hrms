package project.hrms.dataacess.abstracts.cv;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.cv.Social;

public interface SocialDao extends JpaRepository<Social, Integer>{

}
