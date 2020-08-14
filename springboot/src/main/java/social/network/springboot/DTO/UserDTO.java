package social.network.springboot.DTO;


import social.network.springboot.Validation.EmailValid;
import social.network.springboot.Validation.FieldMatch;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
	   @FieldMatch(first = "password", second = "matchingPassword", message = "{password.notMatching}")})
public class UserDTO {
	@NotNull(message = "{username.notNull}")
	@NotEmpty(message = "{username.notEmpty}")
	@Size(min = 6, max = 20, message = "{username.size}")
	private String userName;

	@NotNull(message = "{password.notNull}")
	@NotEmpty(message = "{password.notEmpty}")
	@Size(min = 6, max = 30, message = "{password.size}")
	private String password;

	@NotNull(message = "{matchingPassword.notNull}")
	@NotEmpty(message = "{matchingPassword.notEmpty}")
	@Size(min = 6, max = 30, message = "{matchingPassword.size}")
	private String matchingPassword;

	@NotNull(message = "{fullName.notNull}")
	@NotEmpty(message = "{fullName.notEmpty}")
	@Size(min = 6,max = 30, message = "{fullName.size}")
	private String fullName;

	@EmailValid(message = "{email.valid}")
	@NotEmpty(message = "{email.notEmpty}")
	@NotNull(message = "{email.notNull}")
	private String email;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
