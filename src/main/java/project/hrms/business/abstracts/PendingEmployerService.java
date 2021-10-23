package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.PendingEmployer;

public interface PendingEmployerService {
	Result update(PendingEmployer pendingEmployer);
	DataResult<Boolean> isPending();
	Result apply(int pendingEmployerId,int isOk);
	DataResult<List<PendingEmployer>> getAll();
}
