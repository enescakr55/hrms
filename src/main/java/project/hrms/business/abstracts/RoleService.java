package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Role;

public interface RoleService {
	DataResult<List<Role>> getAll();
	Result add(Role role);
	Result delete(int roleId);
}
