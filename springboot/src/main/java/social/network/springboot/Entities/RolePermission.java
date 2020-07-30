package social.network.springboot.Entities;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

//	@OneToOne
//	@JoinColumn(name = "permission_id")
//	private Permission permission;
//
//	@OneToOne
//	@JoinColumn(name = "role_id")
//	private Roles roles;

}
