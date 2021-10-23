package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.User;
import project.hrms.entities.concretes.dtos.EmployerRegister;

public interface EmployerService {
	DataResult<List<Employer>> getAll();
	Result add(Employer employer);
	Result register(EmployerRegister employerRegister);
	boolean isEmployer(int userId);
	DataResult<Employer> getById(int id);
	Result update(Employer employer);
}
