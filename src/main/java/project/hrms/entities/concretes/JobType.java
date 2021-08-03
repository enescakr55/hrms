package project.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_types")
public class JobType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="job_type_id")
	private int jobTypeId;
	@Column(name="job_type_name")
	private String jobTypeName;
}
