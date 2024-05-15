package com.librarysystem.library.controllers;

import com.librarysystem.library.models.Book;
import com.librarysystem.library.models.Patron;
import com.librarysystem.library.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatronController {
    @Autowired
    PatronService patronService;
    @PostMapping("/api/patrons")
    public ResponseEntity<String> createPatron(@RequestBody Patron patron){
        patronService.createPatron(patron);
        return new ResponseEntity("success", HttpStatus.CREATED);
    }

    @GetMapping("/api/patrons")
    public ResponseEntity<List<Patron>> findAllPatrons(){
        return new ResponseEntity<>(patronService.findAllPatrons(),HttpStatus.OK);
    }

    @GetMapping("/api/patrons/{id}")
    public ResponseEntity<Patron> findPatronById(@PathVariable int id){
        return new ResponseEntity<>(patronService.findPatronById(id),HttpStatus.OK);
    }


    @PutMapping("/api/patrons/{id}")
    public ResponseEntity<String> updatePatronDetails(@PathVariable int id,@RequestBody Patron patron){
        patronService.updatePatronDetails(id,patron);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @DeleteMapping("/api/patrons/{id}")
    public ResponseEntity<String> deletePatron(@PathVariable int id){
        patronService.deletePatron(id);
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
