package social.network.springboot.ServiceImps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.network.springboot.Entities.Relationship;
import social.network.springboot.Entities.Users;
import social.network.springboot.Repositories.RelationshipRepository;
import social.network.springboot.Services.RelationshipService;

import java.util.List;

@Service
public class RelationshipServiceImp implements RelationshipService {

	private final RelationshipRepository relationshipRepository;

	@Autowired
	public RelationshipServiceImp(RelationshipRepository relationshipRepository) {
		this.relationshipRepository = relationshipRepository;
	}

	public void save(Relationship relationship) {
		relationshipRepository.save(relationship);
	}

	public List<Relationship> findAllByActive(boolean active) {
		return relationshipRepository.findAllByIsActive(active);
	}

	public void follow(Users user, Users userRelated) {
		Relationship relationship = new Relationship(user, userRelated, true);
		this.save(relationship);
	}

	public void unfollow(Users user, Users userRelated) {
		Relationship relationship = relationshipRepository.findByUsersAndUsersRelated(user, userRelated);
		relationship.setIsActive(false);
		this.save(relationship);
	}

	public int countByUserRelated(Users users) {
		return relationshipRepository.countByUsersRelated(users);
	}

}
