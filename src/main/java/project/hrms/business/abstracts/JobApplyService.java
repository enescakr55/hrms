package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.JobAdvert;
import project.hrms.entities.concretes.JobApply;

public interface JobApplyService {
	DataResult<List<JobApply>> getAll();
	DataResult<List<JobApply>> getByJobAdvertId(int jobAdvertId);
	Result add(JobApply jobApply);
	Result update(JobApply jobApply);
	Result delete(int jobApplyId);
	DataResult<JobApply> getJobApplyWithJobseekerIdAndJobAdvertId(int jobseekerId,int jobAdvetId);
	DataResult<Integer> getJobApplyCountByJobAdvert(JobAdvert jobAdvert);
}
