package social.network.springboot.Services;

import social.network.springboot.Entities.Users;
import social.network.springboot.Entities.VerificationToken;

import java.util.List;

public interface VerificationTokenService {

	public void createVerificationToken(Users user, String token);

	public VerificationToken findByToken(String token);

	public void deleteByToken(String token);

	public List<VerificationToken> findAllByUserUsername(String username);

	public List<VerificationToken> findAllByUserEmail(String email);

}