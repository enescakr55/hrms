package project.hrms.entities.concretes.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.hrms.entities.concretes.cv.Description;
import project.hrms.entities.concretes.cv.Experience;
import project.hrms.entities.concretes.cv.Language;
import project.hrms.entities.concretes.cv.School;
import project.hrms.entities.concretes.cv.Social;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class showCvDto {
	private Description description;
	private List<Experience> experiences;
	private List<Language> languages;
	private List<School> schools;
	private List<Social> socialMediaAccounts;
}
