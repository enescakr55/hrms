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
@Table(name = "favorite_job_adverts")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteJobAdvert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="favorite_id")
	private int favoriteId;
	
	@JoinColumn(name="user_id")
	@OneToOne
	private User user;
	
	@JoinColumn(name="job_advert_id")
	@OneToOne
	private JobAdvert jobAdvert;
}
