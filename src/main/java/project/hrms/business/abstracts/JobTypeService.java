package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.JobType;

public interface JobTypeService {
	Result add(JobType jobType);
	Result update(JobType jobType);
	Result delete(int jobTypeId);
	DataResult<List<JobType>> getAll();
	DataResult<JobType> getById(int id);
	
}
