package project.hrms.dataacess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.PendingEmployer;

public interface PendingEmployerDao extends JpaRepository<PendingEmployer, Integer>{
	PendingEmployer getById(int id);
	List<PendingEmployer> getByEmployerid(int employerId);
}
