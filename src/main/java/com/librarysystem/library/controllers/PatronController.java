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
    public ResponseEntity createPatron(@RequestBody Patron patron){
        patronService.createPatron(patron);
        return new ResponseEntity("patron added to the library system", HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/patrons")
    public List<Patron> findAllPatrons(){
        return patronService.findAllPatrons();
    }

    @GetMapping("/api/patrons/{id}")
    public Patron findPatronById(@PathVariable int id){
        return patronService.findPatronById(id);
    }


    @PutMapping("/api/patrons/{id}")
    public ResponseEntity updatePatronDetails(@PathVariable int id,@RequestBody Patron patron){
        patronService.updatePatronDetails(id,patron);
        return new ResponseEntity("patron updated in the library system", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/patrons/{id}")
    public ResponseEntity deletePatron(@PathVariable int id){
        patronService.deletePatron(id);
        return new ResponseEntity("patron deleted from the library system", HttpStatus.ACCEPTED);
    }

}
