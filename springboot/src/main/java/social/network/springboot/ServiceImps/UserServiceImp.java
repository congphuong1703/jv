package social.network.springboot.ServiceImps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;
import social.network.springboot.DTO.UserDTO;
import social.network.springboot.DTO.UserPasswordDTO;
import social.network.springboot.Entities.Users;
import social.network.springboot.Entities.VerificationToken;
import social.network.springboot.Enums.EnumStatus;
import social.network.springboot.Repositories.UserRepository;
import social.network.springboot.Services.UserService;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<Users> getAllUser() {
		return userRepository.findAll();
	}

	//dang ky thieu phone number OTP
	@Override
	@Transactional
	public Users registerUser(UserDTO userDto) {
		Users user = new Users();
		String hashedPassword = passwordEncoder.encode(userDto.getPassword());
//		String fullName = userDto.getFullName();
//		user.setFirstName(fullName.substring(0,fullName.indexOf(" ")));
//		user.setLastName(fullName.substring(fullName.indexOf(" ") + 1, fullName.length()));
		user.setFullName(userDto.getFullName());
		user.setUsername(userDto.getUserName());
		user.setPassword(hashedPassword);
		user.setEmail(userDto.getEmail());
		user.setRole("USER");
		userRepository.save(user);
//		user.setRoles(Arrays.asList(roleDAO.findByRoleName("ROLE_CANDIDATE")));
		return user;
	}

	@Override
	public Users findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public Users findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Users findByPhoneNumber(String phoneNumber) {
		return userRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public void deleteByUsername(String username) {
		userRepository.deleteByUsername(username);
	}

	@Override
	public Users findByUsername(String username) {
		return userRepository.findByUsername(username.toLowerCase());
	}

	@Override
	public Users getUserById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public void saveUser(Users user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Kiểm tra xem user có tồn tại trong database không?
		Users users = this.findByUsername(username);
		if (users == null || users.isActive() == false) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		try {
			if (users.isActive() == false) {
				throw new UsernameNotFoundException("Please enable your account.");
			}
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
		}
//		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//		for (Role role : user.getRoles()){
//			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//		}
		UserDetails user = new org.springframework.security.core.userdetails.User(username, users.getPassword(), true,
			   true, true, true, AuthorityUtils.createAuthorityList(users.getRole()));
		System.out.println("ROLE: " + users.getRole());
		return user;
	}

	@Override
	public boolean confirmEmail(VerificationToken verificationToken) {
		Calendar calendar = Calendar.getInstance();
		if (verificationToken == null) {
			return false;
		}
		if ((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
			verificationToken.setDelete(true);
			return false;
		}
		if (verificationToken.isDelete())
			return false;
		return true;
	}

	@Override
	public void updatePassword(UserPasswordDTO userPasswordDTO) {
		Users users = this.findByUsername(userPasswordDTO.getUsername());
		users.setPassword(passwordEncoder.encode(userPasswordDTO.getPassword()));
		this.saveUser(users);
	}


}
