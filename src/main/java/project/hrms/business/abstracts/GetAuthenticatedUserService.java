package project.hrms.business.abstracts;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.User;

public interface GetAuthenticatedUserService {
	DataResult<User> getUser();
	DataResult<Jobseeker> getJobseeker();
}
