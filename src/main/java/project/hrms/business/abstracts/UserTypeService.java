package project.hrms.business.abstracts;

import project.hrms.core.entities.AuthenticatedEmployerInfo;
import project.hrms.core.entities.AuthenticatedUserInfo;
import project.hrms.core.utilities.results.DataResult;

public interface UserTypeService {
	DataResult<String> getUserType(int userid);
	DataResult<AuthenticatedEmployerInfo> GetEmployerInfo(int userid);
	DataResult<AuthenticatedUserInfo> GetUserInfo(int userId);
}
