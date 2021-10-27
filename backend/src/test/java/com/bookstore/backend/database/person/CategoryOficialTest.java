package com.bookstore.backend.database.person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookstore.backend.application.service.category.CategoryService;
import com.bookstore.backend.domain.model.category.CategoryModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryOficialTest {
		
		@Autowired
		private CategoryService service;
		
		@Test
		public void createCategoryTest() {
				String variavel = "classic";
				service.save(new CategoryModel(null, variavel));
				List<CategoryModel> categorys = service.findAll();
				assertEquals((String) categorys.get(categorys.size() -1).getName(), (String) variavel);
		}
		
}
