package project.hrms.dataacess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.UserPermission;

public interface UserPermissionDao extends JpaRepository<UserPermission, Integer>{
	List<UserPermission> getByUser_Id(int userId);
}
