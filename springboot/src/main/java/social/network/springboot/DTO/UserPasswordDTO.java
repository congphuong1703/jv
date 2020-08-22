package social.network.springboot.DTO;

import social.network.springboot.Validation.FieldMatch;
import social.network.springboot.Validation.PasswordValid;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
	   @FieldMatch(first = "matchingPassword", second = "password", message = "The password fields must match") })
public class UserPasswordDTO {

	private String username;

	@PasswordValid(message = "{password.valid}",
		   messageNotEmpty = "{password.notEmpty}",
		   notEmpty = true)
	private String password;

	@NotNull(message = "notNull")
	private String matchingPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
}
