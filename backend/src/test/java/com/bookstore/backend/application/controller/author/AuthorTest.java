package com.bookstore.backend.application.controller.author;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.infrastructure.persistence.repository.author.AuthorRepository;
import com.bookstore.backend.infrastructure.persistence.service.author.AuthorRepositoryService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AuthorTest {
	
	@Mock
	private AuthorRepository authorRepository;
	
	@InjectMocks
	private AuthorRepositoryService authorRepositoryService;
	
	@Test
	@Order(1)
	public void verifySaveAuthor() {
		//Tentativa de salvar um author a partir do AuthorRepositoryService
		//que possuí o comportamento de intermediador das classes AuthorRepository
		//e AuthorService
		
		AuthorModel model = new AuthorModel(1L, "Josias Silva");
		
		authorRepositoryService.getInstance().save(model);
		
		verify(authorRepository,times(1)).save(model);
	}
		
}
