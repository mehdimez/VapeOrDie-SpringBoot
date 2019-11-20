package iset.miniprj.vapeOrDie.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import iset.miniprj.vapeOrDie.entities.User;
import iset.miniprj.vapeOrDie.repository.UserRepository;

import java.util.List;

@RequestMapping({"/rest"})
@RestController
@CrossOrigin
public class UserRestController {
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/add")
	public User addUser(@Valid @RequestBody User user) {

		return userRepository.save(user);
	}
	 @GetMapping("/list")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	 }
}
