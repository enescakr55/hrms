package project.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Table(name="roles")
@Entity
public class Role {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="role_name")
	private String roleName;
	public Role() {
		
	}
	public Role(int id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}
}
