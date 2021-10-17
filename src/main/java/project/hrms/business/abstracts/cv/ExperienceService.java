package project.hrms.business.abstracts.cv;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;

import project.hrms.entities.concretes.cv.Experience;
import project.hrms.entities.concretes.dtos.ExperienceAddDto;

public interface ExperienceService {
	public Result add(Experience experience);
	public Result delete(Experience experience);
	public DataResult<List<Experience>> getByJobseekerId(int jobseekerId);
	public DataResult<List<Experience>> getByUserId(int userid);
	public DataResult<List<Experience>> getAll();
	public Result update(Experience experience);
	public Result deleteMyExperience(int experienceId);
	public Result addMyExperience(ExperienceAddDto experience);
}
