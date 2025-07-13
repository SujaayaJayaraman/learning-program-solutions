package com.example.CountryApp;

import com.example.CountryApp.model.Country;
import com.example.CountryApp.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CountryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountryAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CountryRepository repo) {
		return args -> {
			repo.save(new Country("IN", "India"));
			repo.save(new Country("US", "United States"));
			repo.save(new Country("UK", "United Kingdom"));

			System.out.println("🌍 List of Countries in H2:");
			repo.findAll().forEach(System.out::println);
		};
	}
}
