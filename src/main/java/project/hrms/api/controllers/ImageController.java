package project.hrms.api.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.hrms.business.abstracts.ImageUploadService;
import project.hrms.business.services.CloudinarySystem;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.User;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImageController {
	ImageUploadService imageUploadService;
	@Autowired
	public ImageController(ImageUploadService imageUploadService) {
		super();
		this.imageUploadService = imageUploadService;
	}
	
	@PostMapping("/uploadProfilePicture")
	public Result Upload(@RequestParam("file") MultipartFile file) {
		User usr = null;
		try {
			return imageUploadService.UploadImage(usr,file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new ErrorResult();
		}
	}

}
