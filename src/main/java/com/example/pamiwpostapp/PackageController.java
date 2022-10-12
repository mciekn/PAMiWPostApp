package com.example.pamiwpostapp;

import lombok.Value;
import org.springframework.web.bind.annotation.*;

@Value
class Package{
    String id;
}

@RestController
@RequestMapping("package")
public class PackageController {

    private Package aPackage = new Package("Hello world");

    @GetMapping
    public Package getPackage(){
        return(aPackage);
    }

    @PostMapping
    public Package setPackage(@RequestBody String newWord){
        aPackage = new Package(newWord);
        return aPackage;
    }

    @PutMapping
    public Package putPackage(@RequestBody String newWord){
        //aPackage = new Package(newWord);
        return aPackage;
    }

    @DeleteMapping
    public Package deletePackage(@RequestBody String newWord){
        //word = new Word(newWord);
        return aPackage;
    }
}
