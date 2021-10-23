package project.hrms.entities.concretes;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	@OneToOne()
	@JoinColumn(name="employer_id")
	private Employer employer;
	@OneToOne()
	@JoinColumn(name="role_id")
	private Role role;
	@OneToOne()
	@JoinColumn(name="city_id")
	private City city;
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
	@Column(name="closed")
	private boolean closed;
	@OneToOne
	@JoinColumn(name = "job_time_id")
	private JobTime jobTime;
	@OneToOne
	@JoinColumn(name="job_type_id")
	private JobType jobType;
	
	
}
