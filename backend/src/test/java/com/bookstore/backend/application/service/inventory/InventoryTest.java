package com.bookstore.backend.application.service.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.infrastructure.enumerator.InventoryStatus;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.inventory.InventoryRepository;
import com.bookstore.backend.infrastructure.persistence.service.inventory.InventoryRepositoryService;
import com.bookstore.backend.infrastructure.utils.AdminVerify;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class InventoryTest {
	
	@Mock
	private InventoryRepositoryService inventoryRepositoryService;
	@Mock
	private InventoryRepository inventoryRepository;
	@Mock
	private AdminVerify adminVerify;
	
	@Spy
	@InjectMocks
	private InventoryService inventoryService;
	
	@Test
	@Order(1)
	public void verifyInventoryInBookList1() throws NotFoundException {
		InventoryModel model = mock(InventoryModel.class);
		
		when(model.getId()).thenReturn(1L);
		when(model.getStatus()).thenReturn(InventoryStatus.AVAILABLE);
		when(model.getAmount()).thenReturn(20);
		
		when(inventoryService.update(model)).thenReturn(model);
		
		model = inventoryService.update(model);
		
		assertNotNull(model);
		assertEquals(InventoryStatus.AVAILABLE, model.getStatus());
		assertEquals(20, model.getAmount());
		
		
		verify(inventoryService, times(2)).update(model);
		verify(inventoryRepositoryService, times(1)).update(model);
		
	}
	
	@Test
	@Order(2)
	public void verifyInventoryInBookList2() throws NotFoundException {
		List<BookModel> bookList = mock(List.class);
		
		InventoryModel inventory1 = mock(InventoryModel.class);
		when(inventory1.getId()).thenReturn(1L);
		when(inventory1.getStatus()).thenReturn(InventoryStatus.AVAILABLE);
		when(inventory1.getAmount()).thenReturn(120);
		InventoryModel inventory2 = mock(InventoryModel.class);
		when(inventory1.getId()).thenReturn(2L);
		when(inventory1.getStatus()).thenReturn(InventoryStatus.UNAVAILABLE);
		when(inventory2.getAmount()).thenReturn(60);
		
		BookModel book1 = mock(BookModel.class);
		when(book1.getId()).thenReturn(1L);
		when(book1.getTitle()).thenReturn("A Garota do lago");
		when(book1.getInventory()).thenReturn(inventory1);
		
		BookModel book2 = mock(BookModel.class);
		when(book1.getId()).thenReturn(2L);
		when(book2.getTitle()).thenReturn("As crônicas de Nárnia");
		when(book2.getInventory()).thenReturn(inventory2);
		
		BookModel book3 = mock(BookModel.class);
		
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book2);
		bookList.add(book2);
		
		when(bookList.get(anyInt())).thenReturn(book1, book2, book3);

		assertEquals(120, bookList.get(0).getInventory().getAmount());
		assertEquals(60, bookList.get(1).getInventory().getAmount());
		
		verify(bookList, times(1)).add(book1);
		verify(bookList, times(3)).add(book2);
		verify(bookList, never()).add(book3);
			
	}
	
}




