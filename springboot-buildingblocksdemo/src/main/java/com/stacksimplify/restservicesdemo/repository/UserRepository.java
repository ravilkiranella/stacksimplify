package com.stacksimplify.restservicesdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.restservicesdemo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username); 
}
