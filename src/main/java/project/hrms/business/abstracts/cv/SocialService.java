package project.hrms.business.abstracts.cv;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.cv.Experience;
import project.hrms.entities.concretes.cv.Language;
import project.hrms.entities.concretes.cv.Social;

public interface SocialService {
	public Result add(Social social);
	public Result delete(Social social);
	public DataResult<List<Social>> getByJobseekerId(int jobseekerId);
	public DataResult<List<Social>> getAll();
	public DataResult<List<Social>> getByUserId(int userid);
	public Result update(Social social);
}
