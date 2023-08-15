package com.jpaprac.j1p;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing	// 엔티의 생성 및 수정 시점에 자동으로 일어나는 이벤트를 추적하고 처리하기 위한 기능을 활성화
public class J1pApplication {

	public static void main(String[] args) {
		SpringApplication.run(J1pApplication.class, args);
	}

}
