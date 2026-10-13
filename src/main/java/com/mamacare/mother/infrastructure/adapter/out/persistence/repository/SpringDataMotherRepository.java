package com.mamacare.mother.infrastructure.adapter.out.persistence.repository;

import com.mamacare.mother.infrastructure.adapter.out.persistence.entity.MotherJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataMotherRepository extends JpaRepository<MotherJpaEntity, UUID> {
}