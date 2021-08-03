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
@Table(name = "job_times")
@AllArgsConstructor
@NoArgsConstructor
public class JobTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="job_time_id")
	private int jobTimeId;
	@Column(name="job_time_name")
	private String jobTimeName;
}
