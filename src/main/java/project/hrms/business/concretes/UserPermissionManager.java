package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.UserPermissionService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.dataacess.abstracts.UserPermissionDao;
import project.hrms.entities.concretes.UserPermission;
@Service
public class UserPermissionManager implements UserPermissionService{

		private UserPermissionDao userPermissionDao;
	@Autowired
	public UserPermissionManager(UserPermissionDao userPermissionDao) {
		super();
		this.userPermissionDao = userPermissionDao;
	}
	@Override
	public DataResult<List<UserPermission>> getAllByUserId(int userId) {
		List<UserPermission> userPermissionList = userPermissionDao.getByUser_Id(userId);
		return new SuccessDataResult<List<UserPermission>>(userPermissionList);
	}
	@Override
	public Result add(UserPermission userPermission) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Result update(UserPermission userPermission) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Result delete(int userPermissionId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
