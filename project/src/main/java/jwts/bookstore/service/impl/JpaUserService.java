package jwts.bookstore.service.impl;

import java.util.List;

import jwts.bookstore.model.User;
import jwts.bookstore.repository.UserRepository;
import jwts.bookstore.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class JpaUserService implements UserService {
    @Autowired
	private UserRepository userRepository;
	@Override
	public User save(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public List<User> findByUsernameAndDeletedFalse(String username) {
		
		return userRepository.findByUsernameAndDeletedFalse(username);
	}

	@Override
	public List<User> findByUsernameAndPassword(String username, String password) {
		
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<User> findByUsernameAndPasswordAndDeletedFalse(String username,
			String password) {
		return userRepository.findByUsernameAndPasswordAndDeletedFalse(username, password);
	}

}
