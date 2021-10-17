package project.hrms.dataacess.abstracts.cv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.cv.Language;
import project.hrms.entities.concretes.cv.School;

public interface LanguageDao extends JpaRepository<Language, Integer>{
	List<Language> getByJobseeker_JobseekerId(int jobseekerId);
	Language getByLanguageIdAndJobseeker_JobseekerId(int languageId,int jobseekerId);
}
