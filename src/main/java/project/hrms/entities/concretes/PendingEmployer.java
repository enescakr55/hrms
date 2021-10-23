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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pending_employers")
public class PendingEmployer {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="employerid")
	private int employerid;
	@Column(name="website")
	private String website;
	@Column(name="phone_number")
	private String phoneNumber;
	@Column(name="is_verified")
	private boolean isVerified;
	@Column(name="company_name")
	private String companyName;
	//@Column(name="user_id")
	//private int userId;
	@OneToOne()
	@JoinColumn(name="user_id")
	private User user;
}
