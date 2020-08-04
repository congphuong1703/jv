package social.network.springboot.Entities;

import lombok.Data;
import social.network.springboot.Enums.EnumStatus;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Users {

	public Users() {
	}

	public Users(String username, String password, String email,String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name = "username",nullable = false)
	private String username;

	@Column(name = "password",nullable = false)
	private String password;

	@Column(name = "full_name")
	private String fullName;

	@Column(name ="middle_name")
	private String middleName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "phone_number" )
	private String phoneNumber;

	@Column(name = "role")
	private String role;

	@Column(name ="isActive")
	private EnumStatus status;

	@Column(name ="isReported")
	private int isReported;

	@Column(name = "isAuth")
	private Boolean isAuth;

	@Column(name ="createdBy")
	private String createBy;

	@Column(name ="createdDate")
	private Timestamp createDate;

	@Column(name ="updatedBy")
	private String updateBy;

	@Column(name ="updatedDate")
	private Timestamp updateDate;
}
