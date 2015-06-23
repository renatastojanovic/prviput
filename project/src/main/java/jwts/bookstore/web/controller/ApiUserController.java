package jwts.bookstore.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jwts.bookstore.model.User;
import jwts.bookstore.service.UserService;
import jwts.bookstore.web.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/users")
public class ApiUserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(value="username",required=false) String username,
			@RequestParam(value="password",required=false) String password){
		List<User> users =null;
		
		if(password!=null && username!=null ){
			
			users = userService.findByUsernameAndPasswordAndDeletedFalse(username, password);
			
			
		}else if( username!=null){
			users=userService.findByUsernameAndDeletedFalse(username);
		
		}else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<UserDTO> usersDTO = new ArrayList<>();
		for(User user : users){
			usersDTO.add(new UserDTO(user));
		}
		
		return new ResponseEntity<>(usersDTO,HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
		
		User user = new User();
		user.setUserAccountID(UUID.randomUUID().toString());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());;
		user.setDeleted(userDTO.isDeleted());
		
		user = userService.save(user);
		
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED);
		
	}
	
}
