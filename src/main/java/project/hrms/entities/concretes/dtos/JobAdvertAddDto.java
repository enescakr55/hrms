package project.hrms.entities.concretes.dtos;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertAddDto {


	private int employerid;

	private int roleId;
	private int cityId;

	private double maxSalary;

	private double minSalary;

	private String description;

	private int openPositionNumber;

	private Date lastDate;
	private boolean Active;
	private boolean closed;
	private int jobTimeId;

	private int jobTypeId;
}
