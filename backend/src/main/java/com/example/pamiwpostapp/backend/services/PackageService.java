package com.example.pamiwpostapp.backend.services;

import com.example.pamiwpostapp.backend.entities.Package;
import com.example.pamiwpostapp.backend.repositories.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PackageService {

    private final PackageRepository packageRepository;

    public Collection<Package> findAll(){
        return packageRepository.findAll();
    }

    public Package save(Package aPackage){
        if(aPackage.getId()!= null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide package without id");
        }

        return packageRepository.save(aPackage);
    }


    public Package findById(Long id){
        return packageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Package not found"));
    }

    public Package update(Long id, Package aPackage){
        if(!Objects.equals(id, aPackage.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id and package.id is not equal");
        }
        return packageRepository.save(aPackage);
    }

    public void deleteById(Long id){
        packageRepository.deleteById(id);
    }

}
