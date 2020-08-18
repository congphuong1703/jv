package social.network.springboot.DTO;

import social.network.springboot.Validation.EmailValid;


public class EmailDTO {

	@EmailValid(message = "{email.valid}", messageNotEmpty = "{email.notEmpty}", notEmpty = true)
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
