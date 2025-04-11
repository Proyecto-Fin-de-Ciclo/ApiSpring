package com.cdh.apilibreria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdh.apilibreria.model.Autor;
import com.cdh.apilibreria.model.User;
import com.cdh.apilibreria.repository.UserRepository;
import com.cdh.apilibreria.unimplemented.controller.GenericController;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class UserController implements GenericController<User> {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/api/user")
	@Override
	public ResponseEntity<List<User>> get() {
		 return ResponseEntity.ok(userRepository.findAll());
	}
	
	@GetMapping("/api/user/{id}")
	@ResponseBody
	public ResponseEntity<User> getById(@PathVariable int id) {
		 return ResponseEntity.ok(userRepository.findById(id));
	}

	@PostMapping("/api/user")
	public ResponseEntity<User> post(@RequestBody User user) {
	    System.out.println("User recibido en la API: " + user);  // Verificar que el DNI no está nulo

	    if (userRepository.existsById(user.getId())) {
	        return ResponseEntity.badRequest().build();
	    }
	    return ResponseEntity.ok(userRepository.save(user));
	}
	

	@PutMapping("/api/user")
	@Override
	public ResponseEntity<User> put(@RequestBody User user) {
		if(userRepository.existsById(user.getId())){
			userRepository.save(user);
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.notFound().build();
	}
    
    @DeleteMapping("/api/user/{id}")
	@Override
	public ResponseEntity<User> delete(@PathVariable int id) {
    	if(userRepository.existsById(id)) {
    		userRepository.deleteById(id);
    		return ResponseEntity.ok().build();
    	}
		return ResponseEntity.notFound().build();
	}

}
