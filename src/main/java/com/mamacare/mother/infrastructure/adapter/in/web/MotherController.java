package com.mamacare.mother.infrastructure.adapter.in.web;

import com.mamacare.mother.application.dto.CorrectDateOfBirthRequest;
import com.mamacare.mother.application.dto.MotherResponse;
import com.mamacare.mother.application.dto.RegisterMotherRequest;
import com.mamacare.mother.application.dto.UpdateMotherNameRequest;
import com.mamacare.mother.application.dto.UpdatePhoneNumberRequest;
import com.mamacare.mother.application.service.MotherService;
import com.mamacare.mother.domain.valueobject.MotherId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mothers")
public class MotherController {

    private final MotherService motherService;

    public MotherController(MotherService motherService) {
        this.motherService = motherService;
    }

    @PostMapping
    public ResponseEntity<MotherResponse> register(
            @RequestBody RegisterMotherRequest request) {

        MotherResponse created = motherService.registerMother(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotherResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(motherService.getMotherById(new MotherId(id)));
    }

    @GetMapping
    public ResponseEntity<List<MotherResponse>> getAll() {
        return ResponseEntity.ok(motherService.getAllMothers());
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<MotherResponse> updateName(
            @PathVariable UUID id,
            @RequestBody UpdateMotherNameRequest request) {

        return ResponseEntity.ok(motherService.updateName(new MotherId(id), request));
    }

    @PutMapping("/{id}/phone-number")
    public ResponseEntity<MotherResponse> updatePhoneNumber(
            @PathVariable UUID id,
            @RequestBody UpdatePhoneNumberRequest request) {

        return ResponseEntity.ok(motherService.updatePhoneNumber(new MotherId(id), request));
    }

    @PutMapping("/{id}/date-of-birth")
    public ResponseEntity<MotherResponse> correctDateOfBirth(
            @PathVariable UUID id,
            @RequestBody CorrectDateOfBirthRequest request) {

        return ResponseEntity.ok(motherService.correctDateOfBirth(new MotherId(id), request));
    }
}