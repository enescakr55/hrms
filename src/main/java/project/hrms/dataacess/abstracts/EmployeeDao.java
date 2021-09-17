package project.hrms.dataacess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.Employee;
import project.hrms.entities.concretes.Employer;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{
	Employee getByUser_Id(int userId);
}
