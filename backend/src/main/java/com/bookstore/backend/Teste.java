package com.bookstore.backend;

import java.math.BigDecimal;
import java.util.List;

import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.productRepository;
import com.bookstore.backend.infrastructure.persistence.service.ProductRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Teste {
    @Autowired
	private productRepository productRepository;
	@Autowired
	private ProductRepositoryService bookRepositoryService;

	public void teste() throws NotFoundException{
		// BookModel book1 = new BookModel();
		// book1.setPrice(new BigDecimal(10));
		// BookModel book2 = new BookModel();
		// book2.setPrice(new BigDecimal(11));
		// BookModel book3 = new BookModel();
		// book3.setPrice(new BigDecimal(12));
		// BookModel book4 = new BookModel();
		// book4.setPrice(new BigDecimal(12.50));
		// BookModel book5 = new BookModel();
		// book5.setPrice(new BigDecimal(13));
		// BookModel book6 = new BookModel();
		// book6.setPrice(new BigDecimal(14));

		// bookRepository.save(book1);
		// bookRepository.save(book2);
		// bookRepository.save(book3);
		// bookRepository.save(book4);
		// bookRepository.save(book5);
		// bookRepository.save(book6);

		// List<BookModel> list = bookRepositoryService.findCheapests(5);
		// for (int i = 0; i < list.size(); i++) {
		// 	System.out.println(list.get(i).getPrice());
		// }

	}
}   
