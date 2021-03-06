package project.hrms.business.abstracts;

import java.util.Map;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.ProfileImage;
import project.hrms.entities.concretes.User;

public interface ImageUploadService {
	public Result UploadImage(User user,byte[] file);
	DataResult<ProfileImage> getMyProfilePicture(User user);
	DataResult<ProfileImage> getProfilePictureByUserId(int userId);
}
