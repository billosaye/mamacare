package com.mamacare.mother.infrastructure.adapter.out.persistence.repository;

import com.mamacare.mother.infrastructure.adapter.out.persistence.entity.MotherJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMotherRepository extends JpaRepository <MotherJpaEntity, Long> {

}
