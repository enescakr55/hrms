package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.User;

public interface UserService {
	DataResult<List<User>> getAll();
	Result add(User user);
	DataResult<User> getByEmail(String email);
	DataResult<User> getById(int id);
	Result verifyUser(int userId);
	Result delete(User user);
}
