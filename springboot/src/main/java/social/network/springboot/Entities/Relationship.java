package social.network.springboot.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.springboot.Enums.EnumRelationship;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "relationship")
public class Relationship {

	public Relationship(Users users,Users userRelated,boolean isActive){
		this.users = users;
		this.usersRelated = userRelated;
		this.isActive = isActive;
		this.date =  new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "users")
	private Users users;

	@OneToOne
	@JoinColumn(name = "user_related")
	private Users usersRelated;

	@Column(name = "datetime")
	private Date date;

	@Column(name = "is_active")
	private Boolean isActive;
}
