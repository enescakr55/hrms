package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Employee;

public interface EmployeeService {
	DataResult<List<Employee>> getAll();
	Result add(Employee employee);
	DataResult<Employee> getById(int id);
	boolean isEmployee(int userId);
}
