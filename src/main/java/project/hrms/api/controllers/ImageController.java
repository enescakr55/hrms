package project.hrms.api.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.hrms.business.abstracts.ImageUploadService;
import project.hrms.business.abstracts.UserService;
import project.hrms.business.services.CloudinarySystem;
import project.hrms.business.tools.TokenInfo;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.ProfileImage;
import project.hrms.entities.concretes.User;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImageController {
	ImageUploadService imageUploadService;
	TokenInfo tokenInfo;
	UserService userService;
	@Autowired
	public ImageController(ImageUploadService imageUploadService,TokenInfo tokenInfo,UserService userService) {
		super();
		this.imageUploadService = imageUploadService;
		this.tokenInfo = tokenInfo;
		this.userService = userService;
	}
	
	@PostMapping("/uploadprofilepicture")
	public Result Upload(@RequestHeader("Authorization") String token ,@RequestParam("file") MultipartFile file) {
		String email = tokenInfo.getAuthenticatedUserEmail(token);
		User usr = userService.getByEmail(email).getData();
		if(usr == null) {
			return new ErrorResult();
		}
		try {
			return imageUploadService.UploadImage(usr,file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new ErrorResult();
		}
	}
	@GetMapping("/getmyprofilepicture")
	public DataResult<ProfileImage> getMyProfilePicture(@RequestHeader("Authorization") String token){
		String email = tokenInfo.getAuthenticatedUserEmail(token);
		User usr = userService.getByEmail(email).getData();
		return imageUploadService.getMyProfilePicture(usr);
	}
	@GetMapping("/getprofilepicturebyuserid")
	public DataResult<ProfileImage> getProfileImageByUserId(int userid){
		return imageUploadService.getProfilePictureByUserId(userid);
	}

}
