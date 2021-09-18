package project.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.JobApplyService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.entities.concretes.JobApply;

@Service
public class JobApplyManager implements JobApplyService{

	@Override
	public DataResult<List<JobApply>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
