package project.hrms.business.concretes;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import project.hrms.business.abstracts.ImageUploadService;
import project.hrms.business.abstracts.UserService;
import project.hrms.business.services.CloudinarySystem;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataacess.abstracts.ProfileImageDao;
import project.hrms.entities.concretes.ProfileImage;
import project.hrms.entities.concretes.User;
@Service
public class ImageUploadManager implements ImageUploadService{
	CloudinarySystem cloudinary;
	ProfileImageDao profileImageDao;
	@Autowired
	public ImageUploadManager(ProfileImageDao profileImageDao) {
		super();
		this.cloudinary = new CloudinarySystem();
		this.profileImageDao = profileImageDao;
	}
	
	@Override
	public Result UploadImage(User user, byte[] file) {
		Map<String, Object> result = cloudinary.Upload(file);
		String resimUrl = (String) result.get("secure_url");
		String cloudinaryKey = (String) result.get("public_id");
		ProfileImage profileImage = new ProfileImage();
		profileImage.setImageUrl(resimUrl);
		profileImage.setSaveKey(cloudinaryKey);
		profileImage.setUser(user);
		List<ProfileImage> profileImages = profileImageDao.findAll();
		for (ProfileImage pp : profileImages) {
			if(pp.getUser().getId() == user.getId()) {
				profileImageDao.delete(pp);
			}
		}
		profileImageDao.save(profileImage);
		return new SuccessResult();
	}
	public DataResult<ProfileImage> getMyProfilePicture(User user){
		ProfileImage image = profileImageDao.getByUser_Id(user.getId());
		if(image != null) {
			return new SuccessDataResult<ProfileImage>(image);
		}
		ProfileImage defaultimage = new ProfileImage();
		defaultimage.setImageUrl("https://res.cloudinary.com/ikysproject/image/upload/v1632430217/defaultimage.jpg");
		return new SuccessDataResult<ProfileImage>(defaultimage);
	}

	@Override
	public DataResult<ProfileImage> getProfilePictureByUserId(int userId) {
		ProfileImage image = profileImageDao.getByUser_Id(userId);
		if(image != null) {
			return new SuccessDataResult<ProfileImage>(image);
		}
		ProfileImage defaultimage = new ProfileImage();
		defaultimage.setImageUrl("https://res.cloudinary.com/ikysproject/image/upload/v1632430217/defaultimage.jpg");
		return new SuccessDataResult<ProfileImage>(defaultimage);
	}




}
