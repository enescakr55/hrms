package project.hrms.business.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.EmployeeService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.EmployeeDao;
import project.hrms.entities.concretes.Employee;
@Service
public class EmployeeManager implements EmployeeService{
	EmployeeDao employeeDao;
	@Autowired
	public EmployeeManager(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}
	
	@Override
	public DataResult<List<Employee>> getAll() {
		return new SuccessDataResult<List<Employee>>(employeeDao.findAll());
	}

	@Override
	public Result add(Employee employee) {
		employeeDao.save(employee);
		return new SuccessResult("Kayıt başarılı");
	}

	



}
