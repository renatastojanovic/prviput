package jwts.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblUsers")
public class User {
	@Id
	@Column(name="userAccountID", nullable=false) 
	private String userAccountID;
	@Column(name="username", nullable=false) 
	private String username;
	@Column(name="password", nullable=false) 
	private String password;
	@Column(name="deleted", nullable=false) 
	private boolean deleted;
	
	
	public User() {
		super();
	}
	public User(String userAccountID, String username, String password,
			boolean deleted) {
		super();
		this.userAccountID = userAccountID;
		this.username = username;
		this.password = password;
		this.deleted = deleted;
	}
	public String getUserAccountID() {
		return userAccountID;
	}
	public void setUserAccountID(String userAccountID) {
		this.userAccountID = userAccountID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	

}
