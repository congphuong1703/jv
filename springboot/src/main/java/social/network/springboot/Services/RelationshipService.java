package social.network.springboot.Services;

import social.network.springboot.Entities.Relationship;
import social.network.springboot.Entities.Users;

import java.util.List;

public interface RelationshipService {

	public void save(Relationship relationship);

	public List<Relationship> findAllByActive(boolean active);

	public void follow(Users user, Users userRelated);

	public void unfollow(Users user, Users userRelated);

	public int countByUserRelated(Users users);

}
