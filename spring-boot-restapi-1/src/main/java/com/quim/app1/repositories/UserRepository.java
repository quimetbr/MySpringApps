package com.quim.app1.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quim.app1.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User save(Optional<User> user);
    

    
}