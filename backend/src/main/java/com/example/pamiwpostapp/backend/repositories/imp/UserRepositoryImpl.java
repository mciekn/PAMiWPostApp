package com.example.pamiwpostapp.backend.repositories.imp;

import com.example.pamiwpostapp.backend.entities.User;
import com.example.pamiwpostapp.backend.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final Map<Long, User> users = new TreeMap<>();
    private Long maxId = 0L;

    @Override
    public Collection<User> findAll(){
        return users.values();
    }

    @Override
    public User save(User user){
        if(user.getId()== null){
            user.setId((maxId+1));
        }
        maxId = Math.max(maxId, user.getId());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id){
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public void deleteById(Long id){
        users.remove(id);
    }
}
