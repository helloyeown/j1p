package com.jpaprac.j1p.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpaprac.j1p.domain.Sample;

public interface SampleRepository extends JpaRepository<Sample, String> {
    
}
