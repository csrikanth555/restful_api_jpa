package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.beans.User;

//MVC   THIS WILL BE A PART OF  MODEL 
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
