package com.bookstore.backend;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.bookstore.backend.domain.model.BookModel;
import com.bookstore.backend.infrastructure.persistence.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner{

	@Autowired
	private Teste teste;
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

		
	}
	@Override
	public void run(String... args) throws Exception {
		teste.teste();
		
	}

}