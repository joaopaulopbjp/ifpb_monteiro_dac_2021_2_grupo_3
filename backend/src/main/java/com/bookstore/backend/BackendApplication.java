package com.bookstore.backend;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.bookstore.backend.domain.model.AuthorModel;
import com.bookstore.backend.domain.model.PublishingCompanyModel;
import com.bookstore.backend.domain.model.product.ProductModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.persistence.repository.AuthorRepository;
import com.bookstore.backend.infrastructure.persistence.repository.PublishingCompanyRepository;
import com.bookstore.backend.infrastructure.persistence.repository.UserRepository;
import com.bookstore.backend.infrastructure.persistence.repository.productRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
	//Respositories
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	PublishingCompanyRepository companyRepository;

	@Autowired
	productRepository productRepository;

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
				"\n6 - Register a new Company"+
				"\n7 - find the 5 cheapest books available in inventory;"+
				"\n8 - find all books sorted by title alphabetically by title in paginated form. The user can enter the page finder they want;"+
				"\n9 - add a book to an order (shopping cart);"+
				"\n10 - show all admins;"+
				"\n11 - change user to admin;"+
				"\n12 - sair: ");

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
				
				UserModel user = new UserModel(3l, username, email, password, null);

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
				System.out.println("0 - Register book" + 
									"\n1 - read book" + 
									"\n2 - delete book");

				op = Integer.parseInt(input.nextLine());

				// registrar livro
				if(op == 0){
					System.out.println("Book Options");
					System.out.println("Name: ");
					String name = input.nextLine();
					System.out.println("Escolha o autor:");
					// percorre a lista de autores.
					List<AuthorModel> listAuthors = authorRepository.findAll();
					for(int i = 0;i < listAuthors.size();i++){
						System.out.println(listAuthors.get(i).toString());
					}
					System.out.println("Type the ID Author: ");
					int id = Integer.parseInt(input.nextLine());
					System.out.println("description: ");
					String descricao = input.nextLine();
					System.out.println("category: ");
					String category = input.nextLine();
					System.out.println("price: ");
					BigDecimal price = new BigDecimal(input.nextLine());
					List<PublishingCompanyModel> listCompanys = companyRepository.findAll();
					for(int i = 0;i < listCompanys.size();i++){
						System.out.println(listCompanys.get(i).toString());
					}
					System.out.println("Type the ID company: ");
					String Company = input.nextLine();
					System.out.println("Inventory: ");
					Integer inventory = Integer.parseInt(input.nextLine());



				// alterar campos ou campo do livro
				}else if(op == 1){

					System.out.println("If you don't want to change any field press enter");
					System.out.println("Name: ");
					String name = input.nextLine();
					System.out.println("choice the author: ");
					// percorre a lista de autores.
					String[] lista = new String[5];
					for(int i = 0;i < lista.length;i++){

					}
					System.out.print("Type the ID Author: ");
					int id = Integer.parseInt(input.nextLine());

					System.out.println("description: ");
					String descricao = input.nextLine();
					System.out.println("category: ");
					String category = input.nextLine();
					System.out.println("price: ");
					Double price = Double.parseDouble(input.nextLine());
					System.out.println("company: ");
					Double Company = Double.parseDouble(input.nextLine());

				// excluir o livro
				}else if(op == 2){
					System.out.println("List of books:");
					String[] lista = new String[5];
					for(int i = 0;i < lista.length;i++){

					}
					System.out.print("Type the ID books: ");
					int id = Integer.parseInt(input.nextLine());
				}
			// opção para registrar categoria.
			}else if(op == 4){
				System.out.print("category name: ");
				String name = input.nextLine();

			// opção para excluir categoria
			}else if(op == 5){
				System.out.println("List categorys: ");
				String[] lista = new String[5];
				for(int i = 0;i < lista.length;i++){

				}
				System.out.print("Type the ID category: ");
				int id = Integer.parseInt(input.nextLine());
			// opção para registrar uma editora
			}else if(op == 6){
				System.out.println("Type the Company name: ");
				companyRepository.save(new PublishingCompanyModel(0l, input.nextLine(), null));
				
			// opção para encontrar os 5 livros mais baratos disponiveis no inventario
			}else if(op == 7){
				// for para percorrer lista dos livros mais baratos
				String[] lista = new String[5];
				for(int i = 0;i < lista.length;i++){

				}

			// opção para encontre todos os livros classificados por título em ordem alfabética por título na forma paginada. 
			// O usuário pode inserir o localizador de página que deseja
			}else if(op == 8){
				int contadorDePaginas = 0;

				while(true){
					System.out.print("What paging do you desire? ");

					String option = input.nextLine();

					//se a opção digitada for vazia começa da pagina 0;
					if(option.equals("")){
						//percorre a lista de paginas
						contadorDePaginas = 0;
						buscaPagina(contadorDePaginas);
					//proxima pagina
					}else if(option.equals("a")){
						buscaPagina(contadorDePaginas++);
					}else if(option.equals("d")){
						buscaPagina(contadorDePaginas--);
					}else if(option.equals("s")){
						break;
					}else{
						buscaPagina(contadorDePaginas);
					}
				}
			// adiciona livro ao pedido.
			}else if(op == 9){
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
							buscaPagina(contadorDePaginas++);
						}else if(option.equals("d")){
							buscaPagina(contadorDePaginas--);
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
			}else if(op == 10){
				//User admin;
				for(int i = 0;i < 10;i++){
					System.out.println();
				}
			// opção 
			}else if(op == 11){
				//User admin;
				for(int i = 0;i < 10;i++){
					System.out.println();
				}
				System.out.print("Type the ID user: ");
				int id = Integer.parseInt(input.nextLine());
			// sair.
			}else if(op == 12){
				isRun = false;
			}

		}
		input.close();
	}

	public List<ProductModel> buscaPagina(int pagina){
		// buscar pagina no banco
		String[] lista = new String[5];
		for(int i = 0;i < lista.length;i++){

		}
		return null;
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