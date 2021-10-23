package project.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="Employees")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employeeid")
	private int employeeid;
	//@Column(name="user_id")
	//private int userId;
	//@Column(name="is_verified")
	//private boolean isVerified;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	
	@OneToOne()
	@JoinColumn(name="role_id")
	private Role role;
	@OneToOne()
	@JoinColumn(name="user_id")
	private User user;
}
