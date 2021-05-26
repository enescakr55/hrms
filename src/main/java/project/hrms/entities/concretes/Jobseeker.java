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

@Entity
@Data
@Table(name="jobseekers")
@AllArgsConstructor
@NoArgsConstructor
public class Jobseeker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="user_id")
	private int userId;
	@Column(name="tc_no")
	private String tcNo;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;

}
