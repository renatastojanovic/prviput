package jwts.bookstore.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jwts.bookstore.model.User;
import jwts.bookstore.service.UserService;

import org.springframework.stereotype.Service;
@Service
public class UserServiceDB implements UserService {
	
		
	@Override
	public User save(User user) {
		User retVal= null;
		try {
			// ucitavanje MySQL drajvera
			Class.forName("com.mysql.jdbc.Driver");

			// konekcija
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jwts3", "jwts3", "jwts3");
			String update = "INSERT INTO tblusers (userAccountID ,deleted,username, password) values (?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(update);
			pstmt.setString(1, user.getUserAccountID());
			pstmt.setBoolean(2, user.isDeleted());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPassword());
			if (pstmt.executeUpdate() == 1) {
				return retVal = user;
			}
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			 e.printStackTrace();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public List<User> findByUsernameAndDeletedFalse(String username) {
		List<User> retVal= null;
		try {
			// ucitavanje MySQL drajvera
			Class.forName("com.mysql.jdbc.Driver");

			// konekcija
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jwts3", "jwts3", "jwts3");
			String selectSQL = "SELECT userAccountID ,deleted,username, password FROM tblusers WHERE username = ? AND deleted = false";
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);			
			ResultSet rset = preparedStatement.executeQuery();
			retVal = new ArrayList<User>();
			while (rset.next()) {
				String userAccountID = rset.getString(1);
				boolean deleted=rset.getBoolean(2);
			    username = rset.getString(3);
				String password = rset.getString(4);
				User u = new User(userAccountID , username, password,deleted);
				retVal.add(u);
			}
			rset.close();
			preparedStatement.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			 e.printStackTrace();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return retVal;
	}


	

	@Override
	public List<User> findByUsernameAndPasswordAndDeletedFalse(String username,
			String password) {
		List<User> retVal= null;
		try {
			// ucitavanje MySQL drajvera
			Class.forName("com.mysql.jdbc.Driver");

			// konekcija
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jwts3", "jwts3", "jwts3");
			String selectSQL = "SELECT userAccountID ,deleted,username, password FROM tblusers WHERE username = ? AND password=? AND deleted = false";
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rset = preparedStatement.executeQuery();
			retVal = new ArrayList<User>();
			while (rset.next()) {
				String userAccountID = rset.getString(1);
				boolean deleted=rset.getBoolean(2);
			    username = rset.getString(3);
				password = rset.getString(4);
				User u = new User(userAccountID , username, password,deleted);
				retVal.add(u);
			}
			rset.close();
			preparedStatement.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			 e.printStackTrace();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return retVal;
	}

}
