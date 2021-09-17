package project.hrms.business.abstracts;

import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Permission;

public interface PermissionService {
	Result add(Permission permission);
	Result update(Permission permission);
	Result delete(int permissionId);
}
