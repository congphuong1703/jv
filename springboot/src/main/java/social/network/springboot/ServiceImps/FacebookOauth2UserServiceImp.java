package social.network.springboot.ServiceImps;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import social.network.springboot.Oauth.FacebookOauth.FacebookOauth2User;
import social.network.springboot.Services.FacebookOauth2UserService;

@Service
public class FacebookOauth2UserServiceImp extends DefaultOAuth2UserService implements FacebookOauth2UserService {

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		return new FacebookOauth2User(super.loadUser(userRequest));
	}
}
