package com.onboarding.chat;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@RequiredArgsConstructor
@SpringBootApplication
public class ChatApplication {

	private final HttpSession session;

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

}
