package social.network.springboot.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import social.network.springboot.Entities.VerificationToken;

import java.util.List;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {


	@Query("from VerificationToken where token = :token")
	public VerificationToken findByToken(@Param("token") String token);

	public List<VerificationToken> findAllByUserUsername(String username);

	public List<VerificationToken> findAllByUserEmail(String username);

}
