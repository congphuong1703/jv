package social.network.springboot.ServiceImps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import social.network.springboot.DTO.UserDTO;
import social.network.springboot.DTO.UserPasswordDTO;
import social.network.springboot.Entities.Users;
import social.network.springboot.Entities.VerificationToken;
import social.network.springboot.Repositories.UserRepository;
import social.network.springboot.Services.UserService;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.*;

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
		System.out.println(hashedPassword);
		user.setFullName(userDto.getFullName());
		user.setUsername(userDto.getUserName());
		user.setPassword(hashedPassword);
		user.setEmail(userDto.getEmail());
		user.setRole("ADMIN");
		userRepository.save(user);
//		user.setRoles(Arrays.asList(roleDAO.findByRoleName("ROLE_CANDIDATE")));
		return user;
	}

	public void registerUserByOauth(String username, String email, String fullname) {
		Users users = new Users(username, email, fullname);
		users.setActive(true);
		users.setRole("USER");
		users.setCreateBy("ADMIN");
		String hashedPassword = passwordEncoder.encode(username);
		users.setPassword(hashedPassword);
		users.setIsAuth(true);
		userRepository.save(users);
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
		return userRepository.findByUsername(username);
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
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(users.getRole()));

		UserDetails user = new org.springframework.security.core.userdetails.User(username, users.getPassword(), true,
			   true, true, true, grantedAuthorities);
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
