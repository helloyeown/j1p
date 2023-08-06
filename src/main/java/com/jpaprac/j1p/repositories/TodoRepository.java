package com.jpaprac.j1p.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpaprac.j1p.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    
}
