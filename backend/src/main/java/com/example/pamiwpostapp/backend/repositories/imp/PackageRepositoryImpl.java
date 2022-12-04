package com.example.pamiwpostapp.backend.repositories.imp;

import com.example.pamiwpostapp.backend.entities.Package;
import com.example.pamiwpostapp.backend.repositories.PackageRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Component
public class PackageRepositoryImpl implements PackageRepository {

    private final Map<Long, Package> packages = new TreeMap<>();
    private Long maxId = 0L;

    @Override
    public Collection<Package> findAll(){
        return packages.values();
    }

    @Override
    public Package save(Package aPackage){
        if(aPackage.getId()== null){
            aPackage.setId((maxId+1));
        }
        maxId = Math.max(maxId, aPackage.getId());
        packages.put(aPackage.getId(), aPackage);
        return aPackage;
    }

    @Override
    public Optional<Package> findById(Long id){
        return Optional.ofNullable(packages.get(id));
    }

    @Override
    public void deleteById(Long id){
        packages.remove(id);
    }

}
