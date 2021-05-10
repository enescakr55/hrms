package project.hrms.business.abstracts;

import java.util.List;


import project.hrms.entities.concretes.Role;

public interface RoleService {
	List<Role> getAll();
}
