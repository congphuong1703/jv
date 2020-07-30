package social.network.springboot.ServiceImps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import social.network.springboot.DTO.UserDTO;
import social.network.springboot.Entities.Users;
import social.network.springboot.Enums.EnumStatus;
import social.network.springboot.Repositories.UserRepository;
import social.network.springboot.Services.UserService;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.List;

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
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setFullName(userDto.getFirstName() + " " + userDto.getLastName());
		user.setUsername(userDto.getUserName());
		String hashedPassword = passwordEncoder.encode(userDto.getPassword());
		user.setPassword(hashedPassword);
		user.setStatus(EnumStatus.ACTIVE);
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
		if (users == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		try {
			if (users.getStatus() == EnumStatus.INACTIVE) {
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
}
