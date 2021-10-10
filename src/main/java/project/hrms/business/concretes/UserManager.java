package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.EmployeeService;
import project.hrms.business.abstracts.EmployerService;
import project.hrms.business.abstracts.JobseekerService;
import project.hrms.business.abstracts.UserService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.UserDao;
import project.hrms.entities.concretes.User;
@Service
public class UserManager implements UserService{
	UserDao userDao;
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}
	@Override
	public DataResult<List<User>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<User>>(this.userDao.findAll(),"Kullanıcılar Listelendi");
	}
	@Override
	public Result add(User user) {
		if(userDao.getByEmail(user.getEmail()) != null ) {
			return new ErrorResult("Bu e posta adresi zaten kayıtlı");
		}
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userDao.save(user);
		return new SuccessResult("Başarıyla Eklendi");
	}
	@Override
	public DataResult<User> getByEmail(String email) {
		return new SuccessDataResult<User>(userDao.getByEmail(email));
	}
	public DataResult<User> getById(int id){
		return new SuccessDataResult<User>(userDao.getById(id));
	}
	
	public Result verifyUser(int userId) {
		User getUser = userDao.getById(userId);
		if(getUser != null) {
			getUser.setVerified(true);
			userDao.save(getUser);
			return new SuccessResult("Kullanıcı doğrulandı");		
		}else {
			return new ErrorResult("Kullanıcı doğrulanamadı");
		}
	}
	
	@Override
	public Result delete(User user) {
		userDao.delete(user);
		return new SuccessResult("Kullanıcı silindi");
	}



}
