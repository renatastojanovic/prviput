package jwts.bookstore.web.dto;

import jwts.bookstore.model.User;


public class UserDTO {
	private String id;
	private String username;
	private String password;
	private String repeatedPassword;
	private boolean deleted;
	
	public UserDTO() {
		super();
	}

		
	public UserDTO(User user){
		this.id = user.getUserAccountID();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.repeatedPassword= user.getPassword();
		this.deleted = user.isDeleted();
	    
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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




	public String getRepeatedPassword() {
		return repeatedPassword;
	}


	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
