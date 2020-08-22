package social.network.springboot.ServiceImps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.network.springboot.Entities.Users;
import social.network.springboot.Entities.VerificationToken;
import social.network.springboot.Repositories.VerificationTokenRepository;
import social.network.springboot.Services.VerificationTokenService;

import java.util.List;

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

	@Override
	public void deleteByToken(String token) {
		VerificationToken verificationToken = this.findByToken(token);
		verificationToken.setDelete(true);
		verificationTokenRepository.save(verificationToken);
	}

	public List<VerificationToken> findAllByUserUsername(String username){
		return verificationTokenRepository.findAllByUserUsername(username.toLowerCase());
	}

	public List<VerificationToken> findAllByUserEmail(String email){
		return verificationTokenRepository.findAllByUserEmail(email.toLowerCase());
	}
}
