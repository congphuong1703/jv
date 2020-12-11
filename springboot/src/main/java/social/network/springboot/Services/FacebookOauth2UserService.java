package social.network.springboot.Services;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface FacebookOauth2UserService  {

	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException;

}
