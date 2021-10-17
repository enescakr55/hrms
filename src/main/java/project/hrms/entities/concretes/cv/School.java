package project.hrms.entities.concretes.cv;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.hrms.entities.concretes.Jobseeker;
import project.hrms.entities.concretes.User;
@Table(name="schools")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="school_id")
	private int schoolId;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="jobseekerId")
	private Jobseeker jobseeker;
	@Column(name="school_name")
	private String schoolName;
	@Column(name="department_name")
	private String departmentName;
	@Column(name="start_year")
	private String startYear;
	@Column(name="graduation_year")
	@Nullable
	private String graduationYear;
	
	
}
