package project.hrms.entities.concretes.cv;

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
import project.hrms.entities.concretes.Jobseeker;

@Data
@Entity
@Table(name="cvdescription")
@AllArgsConstructor
@NoArgsConstructor
public class Description {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="description_id")
	private int descriptionId;
	
	@OneToOne
	@JoinColumn(name="jobseeker_id")
	private Jobseeker jobseeker;
	
	@Column(name="user_description")
	private String userdescription;
	
}
