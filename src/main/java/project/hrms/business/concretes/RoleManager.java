package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.RoleService;
import project.hrms.dataacess.abstracts.RoleDao;
import project.hrms.entities.concretes.Role;
@Service
public class RoleManager implements RoleService{
	
	public RoleDao roleDao;
	
	@Autowired
	public RoleManager(RoleDao roleDao) {
		super();
		this.roleDao = roleDao;
	}
	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return this.roleDao.findAll();
	}



}
