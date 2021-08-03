package project.hrms.entities.concretes.cv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.hrms.entities.concretes.Jobseeker;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="socials")
public class Social {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="social_id")
	private int socialId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="jobseekerId")
	private Jobseeker jobseeker;
	
	
	@Column(name="social_media_name")
	private String socialMediaName;
	
	@Column(name="social_media_link")
	private String socialMediaLink;
}
