package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.dtos.JobseekerRegisterDto;

public interface JobseekerService {

		DataResult<List<Jobseeker>> getAll();
		Result add(Jobseeker jobseeker);
		DataResult<Jobseeker> getByTcNo(String tcNo);
		DataResult<Jobseeker> getById(int id);
		boolean isJobseeker(int userId);
		Result jobseekerRegister(JobseekerRegisterDto jobseekerRegister);
}
