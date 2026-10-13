package com.mamacare.mother.application.service;

import com.mamacare.mother.application.dto.CorrectDateOfBirthRequest;
import com.mamacare.mother.application.dto.MotherResponse;
import com.mamacare.mother.application.dto.RegisterMotherRequest;
import com.mamacare.mother.application.dto.UpdateMotherNameRequest;
import com.mamacare.mother.application.dto.UpdatePhoneNumberRequest;
import com.mamacare.mother.application.mapper.MotherMapper;
import com.mamacare.mother.domain.entity.Mother;
import com.mamacare.mother.domain.exception.MotherNotFoundException;
import com.mamacare.mother.domain.repository.MotherRepository;
import com.mamacare.mother.domain.valueobject.DateOfBirth;
import com.mamacare.mother.domain.valueobject.MotherId;
import com.mamacare.mother.domain.valueobject.PhoneNumber;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MotherService {

    private final MotherRepository motherRepository;
    private final MotherMapper motherMapper;

    public MotherService(MotherRepository motherRepository, MotherMapper motherMapper) {
        this.motherRepository = motherRepository;
        this.motherMapper = motherMapper;
    }

    public MotherResponse registerMother(RegisterMotherRequest request) {
        Mother mother = Mother.register(
                request.firstName(),
                request.lastName(),
                new PhoneNumber(request.phoneNumber()),
                new DateOfBirth(request.dateOfBirth())
        );
        Mother saved = motherRepository.save(mother);
        return motherMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public MotherResponse getMotherById(MotherId id) {
        return motherMapper.toResponse(findOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<MotherResponse> getAllMothers() {
        return motherRepository.findAll().stream()
                .map(motherMapper::toResponse)
                .toList();
    }

    public MotherResponse updateName(MotherId id, UpdateMotherNameRequest request) {
        Mother mother = findOrThrow(id);
        mother.updateName(request.firstName(), request.lastName());
        return motherMapper.toResponse(motherRepository.save(mother));
    }

    public MotherResponse updatePhoneNumber(MotherId id, UpdatePhoneNumberRequest request) {
        Mother mother = findOrThrow(id);
        mother.updatePhoneNumber(new PhoneNumber(request.phoneNumber()));
        return motherMapper.toResponse(motherRepository.save(mother));
    }

    public MotherResponse correctDateOfBirth(MotherId id, CorrectDateOfBirthRequest request) {
        Mother mother = findOrThrow(id);
        mother.correctDateOfBirth(new DateOfBirth(request.dateOfBirth()));
        return motherMapper.toResponse(motherRepository.save(mother));
    }

    private Mother findOrThrow(MotherId id) {
        return motherRepository.findById(id)
                .orElseThrow(() -> new MotherNotFoundException(
                        "Mother not found: " + id.value()));
    }
}