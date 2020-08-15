package social.network.springboot.DTO;


import social.network.springboot.Validation.*;

@FieldMatch.List({
	   @FieldMatch(first = "matchingPassword", second = "password", message = "{password.notMatching}")})
public class UserDTO {
	@UsernameValid(message = "{username.valid}",
		   messageNotEmpty = "{username.notEmpty}",
		   messageLength = "{username.size}",
		   notEmpty = true)
	private String userName;

	@PasswordValid(message = "{password.valid}",
		   messageNotEmpty = "{password.notEmpty}",
		   notEmpty = true)
	private String password;

	private String matchingPassword;

	@FullNameValid(notEmpty = true,
		   messageLength = "{fullName.size}",
		   messageNotEmpty = "{fullName.notEmpty",
		   message = "{fullName.valid}")
	private String fullName;

	@EmailValid(message = "{email.valid}", messageNotEmpty = "{email.notEmpty}", notEmpty = true)
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
