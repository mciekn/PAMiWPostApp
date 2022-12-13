package com.example.pamiwpostapp.backend.repositories;

import com.example.pamiwpostapp.backend.entities.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository  extends JpaRepository<PostUser, Long> {

}
