package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.JobApplyService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.dataacess.abstracts.JobApplyDao;
import project.hrms.entities.concretes.JobApply;

@Service
public class JobApplyManager implements JobApplyService{
	
	JobApplyDao jobApplyDao;
	
	@Autowired
	public JobApplyManager(JobApplyDao jobApplyDao) {
		super();
		this.jobApplyDao = jobApplyDao;
	}

	@Override
	public DataResult<List<JobApply>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
