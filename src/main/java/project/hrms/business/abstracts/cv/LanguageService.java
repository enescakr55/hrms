package project.hrms.business.abstracts.cv;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.cv.Experience;
import project.hrms.entities.concretes.cv.Language;

public interface LanguageService {
	public Result add(Language language);
	public Result delete(Language language);
	public DataResult<List<Language>> getByJobseekerId(int jobseekerId);
	public DataResult<List<Language>> getAll();
	public Result update(Language language);
}
