package project.hrms.dataacess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.Permission;

public interface PermissionDao extends JpaRepository<Permission, Integer>{

}
