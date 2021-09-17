package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Permission;
import project.hrms.entities.concretes.UserPermission;

public interface UserPermissionService {
	public DataResult<List<UserPermission>> getAllByUserId(int userId);
	Result add(UserPermission userPermission);
	Result update(UserPermission userPermission);
	Result delete(int userPermissionId);
}
