package com.chinese_dictation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef ="auditorAware")
public class ChineseDictationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChineseDictationApplication.class, args);
	}

}
