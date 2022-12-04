package com.example.pamiwpostapp.backend.repositories.imp;

import com.example.pamiwpostapp.backend.entities.Locker;
import com.example.pamiwpostapp.backend.repositories.LockerRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Component
public class LockerRepositoryImpl implements LockerRepository {
    private final Map<Long, Locker> lockers = new TreeMap<>();
    private Long maxId = 0L;

    @Override
    public Collection<Locker> findAll(){
        return lockers.values();
    }

    @Override
    public Locker save(Locker locker){
        if(locker.getId()== null){
            locker.setId((maxId+1));
        }
        maxId = Math.max(maxId, locker.getId());
        lockers.put(locker.getId(), locker);
        return locker;
    }

    @Override
    public Optional<Locker> findById(Long id){
        return Optional.ofNullable(lockers.get(id));
    }

    @Override
    public void deleteById(Long id){
        lockers.remove(id);
    }
}
