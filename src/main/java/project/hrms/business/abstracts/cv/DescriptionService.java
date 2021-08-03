package project.hrms.business.abstracts.cv;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.cv.Description;

public interface DescriptionService {
	public Result add(Description description);
	public Result delete(Description description);
	public DataResult<Description> getByUserId(int userId);
	public DataResult<List<Description>> getAll();
	public Result Update(Description description);
}
