package project.hrms.business.abstracts;

import java.util.List;


import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.JobAdvert;
import project.hrms.entities.concretes.dtos.JobAdvertAddDto;
public interface JobAdvertService {
	DataResult<List<JobAdvert>> getAll();
	Result add(JobAdvert jobAdvert);
	DataResult<List<JobAdvert>> getIsActive();
	DataResult<List<JobAdvert>> getIsActiveOrderByDate();
	DataResult<List<JobAdvert>> getByEmployerId(int employerId);
	Result closeAdvert(int advertId);
	Result addwithdto(JobAdvertAddDto jobAdvertAddDto);
}
