package iset.miniprj.vapeOrDie.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iset.miniprj.vapeOrDie.entities.User;
import iset.miniprj.vapeOrDie.repository.UserRepository;

@RequestMapping({"rest"})
@RestController
@CrossOrigin
public class UserRestController {
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/add")
	public User addUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
}
