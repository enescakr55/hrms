package project.hrms.business.abstracts.cv;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.cv.Description;

public interface DescriptionService {
	public Result add(Description description);
	public Result delete(Description description);
	public DataResult<Description> getByJobseekerId(int jobseekerId);
	public DataResult<List<Description>> getAll();
	public Result update(Description description);
	public Result updateMyDescription(Description description);
}
