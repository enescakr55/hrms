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
@Table(name="profile_images")
@Entity

@AllArgsConstructor
@NoArgsConstructor
public class ProfileImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="image_id")
	private int imageId;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="savekey")
	private String saveKey;
	
	@OneToOne()
	@JoinColumn(name="user_id")
	private User user;
	
	

}
