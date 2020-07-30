package social.network.springboot.Services;

import social.network.springboot.Entities.Users;
import social.network.springboot.Entities.VerificationToken;

public interface VerificationTokenService {

	public void createVerificationToken(Users user, String token);

	public VerificationToken findByToken(String token);

}
