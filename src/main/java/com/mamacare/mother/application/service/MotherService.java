package com.mamacare.mother.application.service;


import com.mamacare.mother.domain.entity.Mother;
import com.mamacare.mother.domain.repository.MotherRepository;
import com.mamacare.mother.domain.valueobject.MotherId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotherService {
    private final MotherRepository motherRepository;
    public MotherService(MotherRepository motherRepository) {
        this.motherRepository = motherRepository;
    }

    //Register Mother
    public Mother registerMother(Mother mother) {
        return motherRepository.save(mother);
    }

    //Find by ID
    public Mother getMother(MotherId id) {

        return motherRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Mother not found with ID: " + id
                        )
                );
    }

    //Get all mothers
    public List<Mother> getAllMothers() {
        return motherRepository.findAll();
    }

}
