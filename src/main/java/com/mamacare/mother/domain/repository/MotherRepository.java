package com.mamacare.mother.domain.repository;

import com.mamacare.mother.domain.entity.Mother;
import com.mamacare.mother.domain.valueobject.MotherId;

import java.util.List;
import java.util.Optional;

public interface MotherRepository {
    Mother save(Mother mother);
    Optional<Mother> findById(MotherId id);
    List<Mother> findAll();
    boolean existsById(MotherId id);
}
