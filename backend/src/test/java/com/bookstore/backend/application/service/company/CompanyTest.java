package com.bookstore.backend.application.service.company;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.infrastructure.persistence.repository.company.PublishingCompanyRepository;
import com.bookstore.backend.infrastructure.persistence.service.company.PublishingCompanyRepositoryService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CompanyTest {
	
	@Mock
	private PublishingCompanyRepository companyRepository;
	
	@InjectMocks
	private PublishingCompanyRepositoryService companyRepositoryService;
	
	@Mock
	private PublishingCompanyService companyService;
	
	@Test
	public void verifyDeleteCompany() {
		PublishingCompanyModel model = new PublishingCompanyModel(1L, "editora test");
		
		companyRepositoryService.getInstance().delete(model);
		
		verify(companyRepository,times(1)).delete(model);
	}
}
