package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Jobseeker;

public interface JobseekerService {

		DataResult<List<Jobseeker>> getAll();
		Result add(Jobseeker jobseeker);
		DataResult<Jobseeker> getByTcNo(String tcNo);
}
