package jwts.bookstore.service;

import java.util.List;

import jwts.bookstore.model.User;

public interface UserService {
	User save(User user);
	List<User> findByUsernameAndDeletedFalse(String username);
	List<User> findByUsernameAndPassword(String username,String password);
	List<User> findByUsernameAndPasswordAndDeletedFalse(String username,String password);

}
