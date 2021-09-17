package project.hrms.dataacess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.hrms.entities.concretes.FavoriteJobAdvert;
import project.hrms.entities.concretes.JobAdvert;

public interface FavoriteJobAdvertsDao extends JpaRepository<FavoriteJobAdvert, Integer>{
	@Query("From FavoriteJobAdvert where user_id=:userId and job_advert_id=:jobAdvertId")
	FavoriteJobAdvert getFavoriteJobAdvert(int userId,int jobAdvertId);
	
	//@Query("From FavoriteJobAdvert where user_id=:userId")
	//List<FavoriteJobAdvert> getFavoriteJobAdverts(int userId);
	List<FavoriteJobAdvert> findByUser_Id(int userId);
}
