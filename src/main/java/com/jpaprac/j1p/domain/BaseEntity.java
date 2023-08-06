package com.jpaprac.j1p.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass       // 테이블로 만들지 않음
@Getter
@EntityListeners(value = (AuditingEntityListener.class))
public class BaseEntity {
    
}
