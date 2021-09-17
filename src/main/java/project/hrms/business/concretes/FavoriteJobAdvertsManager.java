package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.FavoriteJobAdvertsService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.FavoriteJobAdvertsDao;
import project.hrms.entities.concretes.FavoriteJobAdvert;
import project.hrms.entities.concretes.JobAdvert;
@Service
public class FavoriteJobAdvertsManager implements FavoriteJobAdvertsService{


	FavoriteJobAdvertsDao favoriteJobAdvertsDao;
	
	@Autowired
	public FavoriteJobAdvertsManager(project.hrms.dataacess.abstracts.FavoriteJobAdvertsDao favoriteJobAdvertsDao) {
		super();
		this.favoriteJobAdvertsDao = favoriteJobAdvertsDao;
	}
	@Override
	public Result add(FavoriteJobAdvert favoriteJobAdvert) {
		if(getByAdvertIdAndUserId(favoriteJobAdvert.getUser().getId(), favoriteJobAdvert.getJobAdvert().getAdvertId()).getData() != null) {
			return new ErrorResult("Bu ilan zaten favorilerinize eklenmiş");
		}
		favoriteJobAdvertsDao.save(favoriteJobAdvert);
		return new SuccessResult("Favorilere Eklendi");
	}

	@Override
	public Result delete(FavoriteJobAdvert favoriteJobAdvert) {
		favoriteJobAdvertsDao.delete(favoriteJobAdvert);
		return new SuccessResult("Favorilerden Silindi");
	}
	@Override
	public DataResult<FavoriteJobAdvert> getByAdvertIdAndUserId(int userId, int advertId) {
		FavoriteJobAdvert favoriteJobAdvert = favoriteJobAdvertsDao.getFavoriteJobAdvert(userId, advertId);
		return new SuccessDataResult<FavoriteJobAdvert>(favoriteJobAdvert);
		
		
	}
	public DataResult<Boolean> isFavoriteJobAdvert(int userId,int jobAdvertId){
		FavoriteJobAdvert favoriteJobAdvert = favoriteJobAdvertsDao.getFavoriteJobAdvert(userId, jobAdvertId);
		Boolean sonuc = false;
		if(favoriteJobAdvert != null) {
			sonuc = true;
		}
		return new SuccessDataResult<Boolean>(sonuc);
	}
	@Override
	public DataResult<List<FavoriteJobAdvert>> getJobAdvertsWithUserId(int userId) {
		//return new SuccessDataResult<List<FavoriteJobAdvert>>(favoriteJobAdvertsDao.getFavoriteJobAdverts(userId));
		return new SuccessDataResult<List<FavoriteJobAdvert>>(favoriteJobAdvertsDao.findByUser_Id(userId));
		
	}
	

}
