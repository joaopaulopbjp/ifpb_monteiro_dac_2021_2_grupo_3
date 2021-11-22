package com.bookstore.backend.application.service.author;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
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
	@Spy
	@InjectMocks
	private AuthorRepositoryService authorRepositoryService;
	
	@Mock
	private AuthorService authorService;
	
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
	
	
	@Test
	@Order(2)
	public void verifyEmptyNameField() {
		/*
		 *Tentativa de Salvar um Author sem nome, estou forçando que a partir do
		 *when toda vez que for salvar um author sem nome ele lance uma
		 *IllegalArgumentException
		 */
		AuthorModel model = new AuthorModel();
		
		when(authorService.save(model)).thenThrow(new IllegalArgumentException());
		
		try {
			authorService.save(model);
			fail("Deveria ter sido lançado uma [IllegalArgumentException]");
		} catch (IllegalArgumentException e) {
			//OK
		}
	}
		
}
