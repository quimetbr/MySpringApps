package com.quim.app1.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quim.app1.entities.User;
import com.quim.app1.repositories.UserRepository;

import io.swagger.annotations.ApiOperation;

// api rest CRUD using mysql
// swagger added. URL http://localhost:8080/swagger-ui.html  to get api info provided by swagger 
// added hikari support 

@RestController
@RequestMapping("/api")
public class AppController {
	
	@Autowired
    UserRepository userRepository;
	
	//Testing app with basic Hello!
   @GetMapping("/hello")
   @ApiOperation(value="Without values", notes = "Hello world to test app")
   public String hello() {
      return "Hello!";
    }
   
   // Get all users 
    @GetMapping("/users")
    public Iterable<User> listUsers() {       
        return userRepository.findAll();
    }
    
    // Create a new user
    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    // Get a Single user
    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable(value = "id") Long userid) {
        return userRepository.findById(userid);
              
    }

    // Update a user
    @PutMapping("/user")
    public User updateUser(@RequestBody @Valid User userDetails) {
		return userRepository.save(userDetails);
	}

    // Update a user with an id
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable(value = "id") Long userid,
                                            @Valid @RequestBody User userDetails) {

        Optional<User> user = userRepository.findById(userid);

        System.out.print("Encontrado: "+ user.toString());
        System.out.print("Mudar para: "+ userDetails.toString()+"com nome"+userDetails.getName()+"e email: "+userDetails.getEmail());
        
        user.get().setName(userDetails.getName());
        user.get().setEmail(userDetails.getEmail());
        
        //User updatedUser = userRepository.save(user);
        //return updatedUser;	
        return userRepository.save(user);
        
    	
        
    }
    
    // Delete a user
    
 // Delete a Note
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userid) {
        Optional<User> user = userRepository.findById(userid);

        userRepository.deleteById(user.get().getId());

        return ResponseEntity.ok().build();
    }
}