package project.hrms.dataacess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.Role;

public interface RoleDao extends JpaRepository<Role,Integer>{
	
}
