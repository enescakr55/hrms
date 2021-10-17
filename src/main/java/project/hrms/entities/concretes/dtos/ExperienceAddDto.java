package project.hrms.entities.concretes.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.hrms.entities.concretes.User;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceAddDto {
	private int roleId;
	private String companyName;
	private Date startDate;
	private Date endDate;
}
