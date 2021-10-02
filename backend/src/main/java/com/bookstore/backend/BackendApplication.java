package com.bookstore.backend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bookstore.backend.domain.model.AuthorModel;
import com.bookstore.backend.domain.model.CategoryModel;
import com.bookstore.backend.domain.model.InventoryModel;
import com.bookstore.backend.domain.model.PublishingCompanyModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.domain.model.user.PersonModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.AuthorRepository;
import com.bookstore.backend.infrastructure.persistence.repository.CategoryRepository;
import com.bookstore.backend.infrastructure.persistence.repository.InventoryRepository;
import com.bookstore.backend.infrastructure.persistence.repository.PublishingCompanyRepository;
import com.bookstore.backend.infrastructure.persistence.repository.person.AdminRepository;
import com.bookstore.backend.infrastructure.persistence.repository.person.UserRepository;
import com.bookstore.backend.infrastructure.persistence.repository.product.BookRepository;
import com.bookstore.backend.infrastructure.persistence.service.BookRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;


@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
	//Respositories
	@Autowired
	UserRepository userRepository;

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	PublishingCompanyRepository companyRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	InventoryRepository inventoryRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookRepositoryService bookRepositoryService;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner input = new Scanner(System.in);


		boolean isRun = true;

		while(isRun){
			clearConsole();
			System.out.print(
				"0 - register a new user;"+
				"\n1 - find user by email, id, username;"+
				"\n2 - register author;"+
				"\n3 - book crud (the book must be related to an author and inventory);"+
				"\n4 - register category;"+
				"\n5 - delete category;"+
				"\n6 - register a new Company"+
				"\n7 - find the 5 cheapest books available in inventory;"+
				"\n8 - add a book to an order (shopping cart);"+
				"\n9 - show all admins;"+
				"\n10 - change user to admin;"+
				"\n11 - sair: ");

			int op = Integer.parseInt(input.nextLine());

			// opção para registrar usuario
			if(op == 0){
				System.out.println("User fields");
				System.out.println("Username: ");
				String username = input.nextLine();
				System.out.println("Email: ");
				String email = input.nextLine();
				System.out.println("password: ");
				String password = input.nextLine();
				
				UserModel user = new UserModel(0l, username, email, password, null, null, null);

				userRepository.save(user);
			// opção para encontrar usuario.
			}else if(op == 1){
				System.out.println("What option do you desire?");
				System.out.print("0 - Email\n1 - id\n2 - username: ");
				String option = input.nextLine();
				if(option.equals("0")){
					System.out.print("Type your email: ");
					System.out.println(userRepository.findByEmail(input.nextLine()).toString());

				}else if(option.equals("1")){
					System.out.println("type your id: ");
					System.out.println(userRepository.findById(Long.parseLong(input.nextLine())).get().toString());
					
				}else if(option.equals("2")){
					System.out.println("type your Username: ");
					System.out.println(userRepository.findByUsername(input.nextLine()).toString());
				}

			// opção para registrar o autor.
			}else if(op == 2){
				System.out.println("Register author");
				System.out.println("Name: ");
				String name = input.nextLine();

				AuthorModel author = new AuthorModel(0l, name, null);

				authorRepository.save(author);

			// opção para CRUD do livro.
			}else if(op == 3){
				clearConsole();
				System.out.println("0 - register book" + 
									"\n1 - read book" + 
									"\n2 - delete book");

				op = Integer.parseInt(input.nextLine());
				clearConsole();
				// registrar livro
				if(op == 0){
					System.out.println("Book Options");
					System.out.println("title: ");
					String title = input.nextLine();
					System.out.println("Escolha o autor:");
					// percorre a lista de autores.
					List<AuthorModel> listAuthors = authorRepository.findAll();
					for(int i = 0;i < listAuthors.size();i++){
						System.out.println(listAuthors.get(i).toString());
					}
					List<AuthorModel> authorList = new ArrayList<>();
					while(true){
						System.out.println("Type the ID Author(Type 0 for exit): ");
						Long id = Long.parseLong(input.nextLine());
						if(id==0){
							break;
						}
						authorList.add(authorRepository.findById(id).get());
					}
					System.out.println("description: ");
					String description = input.nextLine();
					System.out.println("yearLaunch: ");
					int yearLaunch = Integer.parseInt(input.nextLine());
					System.out.println("pages: ");
					int pages = Integer.parseInt(input.nextLine());
					List<CategoryModel> categoryList = categoryRepository.findAll();
					for(int i = 0;i < categoryList.size();i++){
						System.out.println(categoryList.get(i).toString());
					}
					List<CategoryModel> cateList = new ArrayList<>();
					while(true){
						System.out.println("Type the ID Category(Type 0 for exit): ");
						Long id = Long.parseLong(input.nextLine());
						if(id==0){
							break;
						}
						cateList.add(categoryRepository.findById(id).get());
					}
					System.out.println("price: ");
					BigDecimal price = new BigDecimal(input.nextLine());
					List<PublishingCompanyModel> listCompanys = companyRepository.findAll();
					for(int i = 0;i < listCompanys.size();i++){
						System.out.println(listCompanys.get(i).toString());
					}
					System.out.println("Type the ID company: ");
					Long companyId = Long.parseLong(input.nextLine());
					PublishingCompanyModel companyModel = companyRepository.findById(companyId).get();
					System.out.println("Inventory: ");
					int qtd = Integer.parseInt(input.nextLine());
					BookModel book = new BookModel(0l, title, description, yearLaunch, pages, price, null, null, categoryList, null, companyModel, authorList);
					book = bookRepository.save(book);
					InventoryModel inventory = new InventoryModel(0l, qtd, book);

					inventoryRepository.save(inventory);
					
				// alterar campos ou campo do livro
				}else if(op == 1){
					int contadorDePaginas = 0;

					while(true){
						System.out.print("0..n - page number\na - left page \nd - right page\ns - exit\nWhat paging do you desire? ");
						
						String option = input.nextLine();
						clearConsole();

						//se a opção digitada for vazia começa da pagina 0;
						if(option.equals("")){
							//percorre a lista de paginas
							contadorDePaginas = 0;
							buscaPagina(contadorDePaginas);
						//proxima pagina
						}else if(option.equals("a")){
							buscaPagina(--contadorDePaginas);
						}else if(option.equals("d")){
							buscaPagina(++contadorDePaginas);
						}else if(option.equals("s")){
							break;
						}else{
							contadorDePaginas = Integer.parseInt(option);
							buscaPagina(contadorDePaginas);
						}
					}

				// excluir o livro
				}else if(op == 2){
					int contadorDePaginas = 0;

					while(true){
						buscaPagina(contadorDePaginas);
						System.out.print("0..n - ID\na - left page \nd - right page\ns - exit\nType the ID books: ");
						
						String option = input.nextLine();
						clearConsole();

						//se a opção digitada for vazia começa da pagina 0;
						if(!option.equals("")){
							if(option.equals("a")){
								--contadorDePaginas;
							}else if(option.equals("d")){
								++contadorDePaginas;
							}else if(option.equals("s")){
								break;
							}else{
								Long id = Long.parseLong(option);
								bookRepository.deleteById(id);
							}
						}
					}
				}
			// opção para registrar categoria.
			}else if(op == 4){
				System.out.print("category name: ");
				String name = input.nextLine();

				categoryRepository.save(new CategoryModel(0l, name, null));
			// opção para excluir categoria
			}else if(op == 5){
				System.out.println("List of Categories");
				List<CategoryModel> categoryList = categoryRepository.findAll();
				for(int i = 0;i < categoryList.size();i++){
					System.out.println(categoryList.get(i).toString());
				}
				
				while(true){
					System.out.println("Type the ID Category(Type 0 for exit): ");
					Long id = Long.parseLong(input.nextLine());
					if(id==0){
						break;
					}

					categoryRepository.deleteById(id);
				}

			// opção para registrar uma editora
			}else if(op == 6){
				System.out.println("Type the Company name: ");
				companyRepository.save(new PublishingCompanyModel(0l, input.nextLine(), null));
				
			// opção para encontrar os 5 livros mais baratos disponiveis no inventario
			}else if(op == 7){
				// for para percorrer lista dos livros mais baratos
				List<BookModel> lista = bookRepositoryService.findCheapests(5);
				for(int i = 0;i < lista.size();i++){
					System.out.println(lista.get(i).toString());
				}

			// opção para encontre todos os livros classificados por título em ordem alfabética por título na forma paginada. 
			// O usuário pode inserir o localizador de página que deseja
			}else if(op == 8){
				int contadorDePaginas = 0;
				// Carrinho carrinho = ;
				while(true){
					System.out.println("What paging do you desire?");
					System.out.print("0 - remover\n1 - adicionar\n2 - sair: ");
					op = Integer.parseInt(input.nextLine());
					//Carrinho.toString();
					//opção para remover livro do carrinho
					if(op==0){
						System.out.print("Id Book: ");
						int id = Integer.parseInt(input.nextLine());

					//opção para adionar livro no carrinho
					}else if(op == 1){
						System.out.print("What paging do you desire? ");

						String option = input.nextLine();

						//se a opção digitada for vazia começa da pagina 0;
						if(option.equals("")){
							//percorre a lista de paginas
							contadorDePaginas = 0;

							buscaPagina(contadorDePaginas);
						//proxima pagina
						}else if(option.equals("a")){
							buscaPagina(++contadorDePaginas);
						}else if(option.equals("d")){
							buscaPagina(--contadorDePaginas);
						}else if(option.equals("s")){
							break;
						}else{
							buscaPagina(contadorDePaginas);
						}
						System.out.print("What Id of book do you desire? ");
						int id = Integer.parseInt(input.nextLine());
						if(id >= 1){
							//carrinho.add(id);
						}
					}else if(op == 2){
						break;
					}
				}
			// show all admins
			}else if(op == 9){
				List<AdminModel> adminList = adminRepository.findAll();
				for(AdminModel admin : adminList) {
					System.out.println(admin.toString());
				}
				input.nextLine();
				// opção 
				//User admin;
			}else if(op == 10){
				while(true) {
					clearConsole();
					List<UserModel> userList = userRepository.findAll();
					for(UserModel model : userList) {
						System.out.println(model.toString());
					}
					System.out.print("Type 0 to exit or user ID: ");
					Long id = Long.parseLong(input.nextLine());

					if(id == 0) break;

					UserModel user = userRepository.findById(id).get();
					AdminModel admin = new AdminModel(user.getId(),
						user.getUsername(),
						user.getEmail(),
						user.getPassword(),
						user.getAddressList(),
						user.getProductForSaleList(),
						user.getSaleHistory());

					userRepository.delete(user);
					adminRepository.save(admin);

				}
			// sair.
			}else if(op == 11){
				isRun = false;
			}

		}
		input.close();
	}

	public void buscaPagina(int pagina){
		try {
			List<BookModel> bookList = bookRepositoryService.findAll(pagina);

			for(BookModel book : bookList) {
				System.out.println(book.toString());
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	public void clearConsole(){
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}