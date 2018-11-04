package com.okta.springbootvue.SpringBootVueApplication;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootVueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVueApplication.class, args);
	}

	@Bean
	ApplicationRunner init(TodoRepository repository) {
		return args -> {
			Stream.of("Buy milk", "Eat pizza", "Write tutorial", "Study Tibetan", "Go kayaking").forEach(name -> {
				Todo todo = new Todo();
				todo.setText(name);
				repository.save(todo);
			});
			repository.findAll().forEach(System.out::println);
		};
	}
}
