package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.RoleService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
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
	public DataResult<List<Role>> getAll() {
		return new SuccessDataResult<List<Role>>(this.roleDao.findAll(),"Roller listelendi");
	}
	@Override
	public Result add(Role role) {
		boolean mevcutmu = false;
		roleDao.save(role);
		List<Role> roles = this.roleDao.findAll();
		for (Role rol : roles) {
			if(rol.getRoleName() == role.getRoleName()) {
				mevcutmu = true;
			}
		}
		if(mevcutmu == true) {
			return new ErrorResult("Aynı isimde bir rol mevcut");
		}
		return new SuccessResult("Başarıyla Eklendi");
	}

}
