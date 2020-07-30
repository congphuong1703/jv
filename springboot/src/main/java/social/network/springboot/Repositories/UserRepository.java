package social.network.springboot.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import social.network.springboot.Entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	@Query("from Users where username = :username and password = :password")
	public Users findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	public Users findByUsername(String username);

	public Users findByEmail(String email);

	public Users findByPhoneNumber(String phoneNumber);

	public void deleteByUsername(String username);
}
