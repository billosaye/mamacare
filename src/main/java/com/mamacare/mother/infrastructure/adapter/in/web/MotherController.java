
package com.mamacare.mother.infrastructure.adapter.in.web;

import com.mamacare.mother.application.dto.CreateMotherRequest;
import com.mamacare.mother.application.dto.MotherResponse;
import com.mamacare.mother.application.service.MotherService;

import com.mamacare.mother.domain.entity.Mother;
import com.mamacare.mother.domain.valueobject.MotherId;
import com.mamacare.mother.domain.valueobject.PhoneNumber;
import com.mamacare.mother.domain.valueobject.DateOfBirth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mothers")
public class MotherController {

    private final MotherService motherService;

    public MotherController(MotherService motherService) {
        this.motherService = motherService;
    }

    // Register a new mother
    @PostMapping
    public ResponseEntity<MotherResponse> registerMother(
            @RequestBody CreateMotherRequest request
    ) {

        Mother mother = Mother.register(
                request.firstName(),
                request.lastName(),
                new PhoneNumber(request.phoneNumber()),
                new DateOfBirth(request.dateOfBirth())
        );

        Mother savedMother = motherService.registerMother(mother);

        MotherResponse response = MotherResponse.from(savedMother);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Retrieve all mothers
    @GetMapping
    public List<MotherResponse> getAllMothers() {

        return motherService.getAllMothers()
                .stream()
                .map(MotherResponse::from)
                .toList();
    }

    // Retrieve one mother by ID
    @GetMapping("/{id}")
    public MotherResponse getMotherById(
            @PathVariable UUID id
    ) {

        MotherId motherId = new MotherId(id);

        Mother mother = motherService.getMother(motherId);

        return MotherResponse.from(mother);
    }
}
