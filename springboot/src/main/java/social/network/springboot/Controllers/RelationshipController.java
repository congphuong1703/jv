package social.network.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import social.network.springboot.Entities.Users;
import social.network.springboot.Services.RelationshipService;
import social.network.springboot.Services.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RelationshipController {

	@Autowired
	private RelationshipService relationshipService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/follow/{userId}", method = RequestMethod.GET)
	public void follow(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
		Users userRelated = userService.getUserById(userId);
		String currentUser = request.getUserPrincipal().getName();
		Users user = userService.findByUsername(currentUser);
		relationshipService.follow(user, userRelated);
	}

	@RequestMapping(value = "/unfollow/{userId}", method = RequestMethod.GET)
	public void unfollow(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
		Users userRelated = userService.getUserById(userId);
		String currentUser = request.getUserPrincipal().getName();
		Users user = userService.findByUsername(currentUser);
		relationshipService.unfollow(user, userRelated);
	}
}
