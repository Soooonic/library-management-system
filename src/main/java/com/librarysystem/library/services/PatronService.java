package com.librarysystem.library.services;

import com.librarysystem.library.models.Patron;
import com.librarysystem.library.repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {
    @Autowired
    PatronRepository patronRepository;
    public void createPatron(Patron patron){
        patronRepository.save(patron);
    }
    public void updatePatronEmail(String oldEmail, String newEmail){
        patronRepository.updatePatronEmail(oldEmail,newEmail);
    }
    public List<Patron> findAllPatrons(){
        return patronRepository.findAllPatrons();
    }
    public Patron findPatronById( int id){
        return patronRepository.findPatronById(id);
    }

    public List<Patron> findByMail(String mail){
        return patronRepository.findByMail(mail);
    }
    public void updatePatronDetails(int id,Patron patron){
        patronRepository.updatePatronDetails(id,patron);
    }
    public void deletePatron(int id){
        patronRepository.deletePatron(id);
    }


}
