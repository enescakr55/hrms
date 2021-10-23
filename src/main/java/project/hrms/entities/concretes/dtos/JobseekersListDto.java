package project.hrms.entities.concretes.dtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.ProfileImage;
import project.hrms.entities.concretes.User;
import project.hrms.entities.concretes.cv.Social;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobseekersListDto {
	private User user;
	private Jobseeker jobseeker;
	private ProfileImage profileImage;

}
