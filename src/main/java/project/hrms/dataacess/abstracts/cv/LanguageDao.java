package project.hrms.dataacess.abstracts.cv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.cv.Language;

public interface LanguageDao extends JpaRepository<Language, Integer>{
	List<Language> getByJobseeker(int jobseekerId);
}
