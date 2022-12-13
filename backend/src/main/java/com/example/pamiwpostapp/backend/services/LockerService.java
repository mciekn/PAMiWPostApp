package com.example.pamiwpostapp.backend.services;

import com.example.pamiwpostapp.backend.entities.Locker;
import com.example.pamiwpostapp.backend.repositories.LockerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LockerService {

    private final LockerRepository lockerRepository;

    public Collection<Locker> findAll(){
        return lockerRepository.findAll();
    }

    public Locker save(Locker locker){
        if(locker.getId()!= null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide locker without id");
        }

        return lockerRepository.save(locker);
    }


    public Locker findById(Long id){
        return lockerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Locker not found"));
    }

    public Locker update(Long id, Locker locker){
        if(!Objects.equals(id, locker.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id and locker.id is not equal");
        }
        return lockerRepository.save(locker);
    }

    public void deleteById(Long id){
        lockerRepository.deleteById(id);
    }

}
