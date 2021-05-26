package project.hrms.dataacess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer>{

}
