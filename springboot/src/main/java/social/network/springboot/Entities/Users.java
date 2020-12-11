package social.network.springboot.Entities;

import lombok.*;
import social.network.springboot.Enums.EnumStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {


	public Users(String username, String password, String email, String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public Users(String username, String email,String fullName) {
		super();
		this.username = username;
		this.email = email;
		this.fullName = fullName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "role")
	private String role;

	@OneToMany(mappedBy = "users",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@Column(name = "relationship")
	private List<Relationship> relationships;

	@Column(name = "isReported")
	private int isReported;

	@Column(name = "isAuth")
	private Boolean isAuth;

	@Column(name = "createdBy")
	private String createBy;

	@Column(name = "createdDate")
	private Timestamp createDate;

	@Column(name = "updatedBy")
	private String updateBy;

	@Column(name = "updatedDate")
	private Timestamp updateDate;

	@Column(name = "isDelete")
	private boolean isDelete;

	@Column(name = "is_active")
	private boolean isActive;
}
