package social.network.springboot.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.network.springboot.Entities.Relationship;
import social.network.springboot.Entities.Users;

import java.util.List;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship,Long> {

	public Relationship getById(Long id);

	public List<Relationship> findAllByIsActive(Boolean isActive);

	public Relationship findByUsers(Users users);

	public Relationship findByUsersAndUsersRelated(Users users,Users usersRelated);

	public int countByUsersRelated(Users users);

}
