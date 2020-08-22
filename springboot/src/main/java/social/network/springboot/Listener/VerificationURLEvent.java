package social.network.springboot.Listener;

import org.springframework.context.ApplicationEvent;
import social.network.springboot.Entities.Users;

import java.util.Locale;

public class VerificationURLEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private String appUrl;
	private Locale locale;
	private Users users;

	public VerificationURLEvent(Users users, Locale locale, String appUrl) {
		super(users);
		this.appUrl = appUrl;
		this.locale = locale;
		this.users = users;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
}
