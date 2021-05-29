package project.hrms.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="jobadverts")
@AllArgsConstructor()
@NoArgsConstructor()
public class JobAdvert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="advert_id")
	private int advertId;
	@Column(name="employer_id")
	private int employerId;
	@Column(name="role_id")
	private int roleId;
	@Column(name="city_id")
	private int cityId;
	@Column(name="max_salary")
	private double maxSalary;
	@Column(name="min_salary")
	private double minSalary;
	@Column(name="description")
	private String description;
	@Column(name="open_position_number")
	private int openPositionNumber;
	@Column(name="last_date")
	private Date lastDate;
	@Column(name="active")
	private boolean Active;
	
}
