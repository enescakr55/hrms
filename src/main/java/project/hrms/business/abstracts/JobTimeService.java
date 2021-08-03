package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.JobTime;

public interface JobTimeService {
	Result add(JobTime jobTime);
	Result delete (int jobTimeId);
	Result update(JobTime jobTime);
	DataResult<List<JobTime>> getAll();
	DataResult<JobTime> getById(int id);
}
