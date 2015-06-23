package jwts.bookstore.repository;

import java.util.List;

import jwts.bookstore.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	List<User> findByUsernameAndDeletedFalse( String username);
	
	List<User> findByUsernameAndPassword(String username,String password);
	List<User> findByUsernameAndPasswordAndDeletedFalse(String username,String password);
}
