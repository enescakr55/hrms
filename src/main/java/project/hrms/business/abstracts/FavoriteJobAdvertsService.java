package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.FavoriteJobAdvert;

public interface FavoriteJobAdvertsService {
	public Result add(FavoriteJobAdvert favoriteJobAdvert);
	public Result delete(FavoriteJobAdvert favoriteJobAdvert);
	public DataResult<FavoriteJobAdvert> getByAdvertIdAndUserId(int userId,int advertId);
	public DataResult<List<FavoriteJobAdvert>> getJobAdvertsWithUserId(int userId);
}
