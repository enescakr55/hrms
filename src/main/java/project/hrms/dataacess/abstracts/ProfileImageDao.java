package project.hrms.dataacess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.ProfileImage;

public interface ProfileImageDao extends JpaRepository<ProfileImage, Integer>{
	ProfileImage getByUser_Id(int userId);
}
