package com.example.pamiwpostapp.backend.services;

import com.example.pamiwpostapp.backend.entities.PostUser;
import com.example.pamiwpostapp.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Collection<PostUser> findAll(){
        return userRepository.findAll();
    }

    public PostUser save(PostUser user){
        if (user.getId() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide user without id");
        }
        return userRepository.save(user);
    }

    public PostUser findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }

    public PostUser update(Long id, PostUser user){
        if(!Objects.equals(id, user.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id and user.id is not equal");
        }
        return userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
