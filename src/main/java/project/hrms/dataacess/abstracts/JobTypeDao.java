package project.hrms.dataacess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.JobType;

public interface JobTypeDao extends JpaRepository<JobType, Integer>{

}
