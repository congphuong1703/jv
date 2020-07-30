package social.network.springboot.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.springboot.Enums.EnumRelationship;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relationship {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

//	@OneToOne
//	@Column(name = "user_one")
//	private Users userOne;
//
//	@OneToOne
//	@Column(name = "user_two")
//	private Users userTwo;

	@Column(name = "status")
	private EnumRelationship enumRelationship;
}
