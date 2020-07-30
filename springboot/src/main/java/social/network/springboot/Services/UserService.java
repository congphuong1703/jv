package social.network.springboot.Services;

import social.network.springboot.DTO.UserDTO;
import social.network.springboot.Entities.Users;

import java.util.List;

public interface UserService {

	public List<Users> getAllUser();

	public Users findByUsername(String username);

	public Users getUserById(Long id);

	public Users registerUser(UserDTO userDto);

	public void saveUser(Users user);

	public void deleteUser(Long id);

	public Users findByUsernameAndPassword(String username, String password);

	public Users findByEmail(String email);

	public Users findByPhoneNumber(String phoneNumber);

	public void deleteByUsername(String username);
}
