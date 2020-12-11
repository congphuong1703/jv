package social.network.springboot.Oauth.FacebookOauth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class FacebookOauth2User implements OAuth2User {

	private OAuth2User oAuth2User;

	public FacebookOauth2User(OAuth2User oAuth2User){
		this.oAuth2User = oAuth2User;
	}
	/**
	 * Get the OAuth 2.0 token attributes
	 *
	 * @return the OAuth 2.0 token attributes
	 */
	@Override
	public Map<String, Object> getAttributes() {
		return oAuth2User.getAttributes();
	}

	/**
	 * Get the {@link Collection} of {@link GrantedAuthority}s associated
	 * with this OAuth 2.0 token
	 *
	 * @return the OAuth 2.0 token authorities
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return oAuth2User.getAuthorities();
	}

	/**
	 * Returns the name of the authenticated <code>Principal</code>. Never <code>null</code>.
	 *
	 * @return the name of the authenticated <code>Principal</code>
	 */
	@Override
	public String getName() {
		return oAuth2User.getAttribute("name");
	}

	public String getFullName(){
		return oAuth2User.getAttribute("name");
	}

	public String getEmail(){
		return oAuth2User.getAttribute("email");
	}

	public String getUsername(){
		return oAuth2User.getAttribute("id");
	}
}
