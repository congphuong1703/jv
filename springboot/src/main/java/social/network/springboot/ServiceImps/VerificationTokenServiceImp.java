package social.network.springboot.ServiceImps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.network.springboot.Entities.Users;
import social.network.springboot.Entities.VerificationToken;
import social.network.springboot.Repositories.VerificationTokenRepository;
import social.network.springboot.Services.VerificationTokenService;

@Service
public class VerificationTokenServiceImp implements VerificationTokenService {

	private final VerificationTokenRepository verificationTokenRepository;

	@Autowired
	public VerificationTokenServiceImp(VerificationTokenRepository verificationTokenRepository) {
		this.verificationTokenRepository = verificationTokenRepository;
	}

	@Override
	public void createVerificationToken(Users user, String token) {
		VerificationToken verificationToken = new VerificationToken(token, user);
		verificationTokenRepository.save(verificationToken);
	}

	@Override
	public VerificationToken findByToken(String token) {
		return verificationTokenRepository.findByToken(token);
	}

}
