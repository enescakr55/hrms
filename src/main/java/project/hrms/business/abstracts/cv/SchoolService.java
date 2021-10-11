package project.hrms.business.abstracts.cv;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.cv.Language;
import project.hrms.entities.concretes.cv.School;

public interface SchoolService {
	public Result add(School school);
	public Result delete(School school);
	public DataResult<List<School>> getByJobseekerId(int jobseekerId);
	public DataResult<List<School>> getAll();
	public Result update(School school);
}
