package com.bookstore.backend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.domain.model.evaluation.EvaluateModel;
import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.domain.model.sale.ItemOrderModel;
import com.bookstore.backend.domain.model.sale.shoppingCartModel;
import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.AuthorRepository;
import com.bookstore.backend.infrastructure.persistence.repository.CategoryRepository;
import com.bookstore.backend.infrastructure.persistence.repository.InventoryRepository;
import com.bookstore.backend.infrastructure.persistence.repository.PublishingCompanyRepository;
import com.bookstore.backend.infrastructure.persistence.repository.evaluate.EvaluateRepository;
import com.bookstore.backend.infrastructure.persistence.repository.person.AdminRepository;
import com.bookstore.backend.infrastructure.persistence.repository.person.UserRepository;
import com.bookstore.backend.infrastructure.persistence.repository.product.BookRepository;
import com.bookstore.backend.infrastructure.persistence.repository.sale.ItemOrderRepository;
import com.bookstore.backend.infrastructure.persistence.repository.sale.ShoppingCartRepository;
import com.bookstore.backend.infrastructure.persistence.service.BookRepositoryService;

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

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Autowired
	ItemOrderRepository itemOrderRepository;

	@Autowired
	EvaluateRepository evaluateRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner input = new Scanner(System.in);

		boolean isRun = true;

		UserModel userModel = login();

		while(isRun){
			
			clearConsole();
			System.out.println("1 - User"+
								"\n2 - Admin"+
								"\n3 - Author"+
								"\n4 - Category"+
								"\n5 - Company"+
								"\n6 - ShoppingCart"+
								"\n7 - Book"+
								"\n8 - Logout"+
								"\ns - Exit:");

			String op = input.nextLine();

			if(op.equals("1")){
				while(true){
					System.out.println("User options:"+
										"\n1 - Register User"+
										"\n2 - Find user by email, id, username"+
										"\n3 - Change user to admin"+
										"\ns - Exit:");
					op = input.nextLine();

					if(op.equals("1")){
	
						System.out.println("User fields");
						System.out.println("Username: ");
						String username = input.nextLine();
						System.out.println("Email: ");
						String email = input.nextLine();
						System.out.println("password: ");
						String password = input.nextLine();
						
						UserModel user = new UserModel(0l, username, email, password, null, null, null, null, null);
		
						userRepository.save(user);
					}else if(op.equals("2")){
						System.out.println("What option do you desire?");
						System.out.print("1 - Email\n2 - id\n3 - username: ");
						String option = input.nextLine();
						if(option.equals("1")){
							System.out.print("Type your email: ");
							System.out.println(userRepository.findByEmail(input.nextLine()).toString());
	
						}else if(option.equals("2")){
							System.out.println("type your id: ");
							System.out.println(userRepository.findById(Long.parseLong(input.nextLine())).get().toString());
							
						}else if(option.equals("3")){
							System.out.println("type your Username: ");
							System.out.println(userRepository.findByUsername(input.nextLine()).toString());
						}
					}else if(op.equals("3")){
						System.out.println("Users:");
						while(true) {
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
					}else if(op.equals("s")){
						break;
					}
				}
		
			}else if(op.equals("2")){
				while(true){
					System.out.println("Admin options:"+
										"\n1 - Show all admins"+
										"\ns - Exit:");
					op = input.nextLine();

					if(op.equals("1")){
						List<AdminModel> adminList = adminRepository.findAll();
						for(AdminModel admin : adminList) {
							System.out.println(admin.toString());
						}
					}else if(op.equals("s")){
						break;
					}
				}
			}else if(op.equals("3")){
				while(true){
					System.out.println("Author options:"+
										"\n1 - Register author"+
										"\n2 - Show All authors"+
										"\ns - Exit:");
					op = input.nextLine();

					if(op.equals("1")){
						System.out.println("Register author");
						System.out.println("Name: ");
						String name = input.nextLine();

						AuthorModel author = new AuthorModel(0l, name, null);

						authorRepository.save(author);
					}else if(op.equals("2")){
						for(AuthorModel author: authorRepository.findAll()){
							System.out.println(author.toString());
						}
					}else if(op.equals("s")){
						break;
					}
				}
			}else if(op.equals("4")){
				while(true){
					System.out.println("Category options:"+
										"\n1 - Register category"+
										"\n2 - Show all categorys"+
										"\n3 - Delete category"+
										"\ns - Exit:");
					op = input.nextLine();

					if(op.equals("1")){
						System.out.print("category name: ");
						String name = input.nextLine();

						categoryRepository.save(new CategoryModel(0l, name, null));
					}else if(op.equals("2")){
						List<CategoryModel> categoryList = categoryRepository.findAll();
						for(CategoryModel category : categoryList) {
							System.out.println(category.toString());
						}
					}else if(op.equals("3")){
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
					}else if(op.equals("s")){
						break;
					}
				}

			}else if(op.equals("5")){
				while(true){
					System.out.println("Company options:"+
										"\n1 - Register a new Company"+
										"\n2 - Show all companys"+
										"\ns - Exit:");
					op = input.nextLine();

					if(op.equals("1")){
						System.out.println("Type the Company name: ");
						companyRepository.save(new PublishingCompanyModel(0l, input.nextLine(), null));
					}else if(op.equals("2")){
						for(PublishingCompanyModel company:companyRepository.findAll()){
							System.out.println(company.toString());
						}
					}else if(op.equals("s")){
						break;
					}

				}
			}else if(op.equals("6")){
				
				shoppingCartModel shoppingCart = new shoppingCartModel(0l, null, userModel);
				// Carrinho carrinho = ;
				while(true){
					System.out.println("ShoppingCart options:");

					System.out.println("What option do you desire?");
					System.out.println("1 - remove\n2 - add\n3 - exit: ");
					op = input.nextLine();
					//Carrinho.toString();
					//opção para remover livro do carrinho
					if(op.equals("1")){
						shoppingCart = shoppingCartRepository.findById(userModel.getShoppingCart().getId()).get();
						while(true){
							System.out.println("Items Oders:");
							for(ItemOrderModel item: shoppingCart.getItemList()){
								System.out.println(item.getProduct().toString() + 
								" Amount: " + item.getAmount()+ 
								" Price: " + item.getTotalPrice());
							}
	
							System.out.print("Id Book for remove(Type 0 for exit): ");
							Long id = Long.parseLong(input.nextLine());

							if(id == 0){
								break;
							}
							shoppingCart.removeItemOrderFromItemListByProductId(id);
						}

					//opção para adionar livro no carrinho
					}else if(op.equals("2")){
						int contadorDePaginas = 0;
						List<ItemOrderModel> itemOrderList = new ArrayList<>();
						while(true){
							buscaPagina(contadorDePaginas);
							System.out.print("a - left page \nd - right page\ns - exit\nWhat option do you desire? ");
	
							String option = input.nextLine();
	
							//se a opção digitada for vazia começa da pagina 0;
							if(!option.equals("")){
								//percorre a lista de paginas
								//proxima pagina
								if(option.equals("a")){
									if(contadorDePaginas > 0) {
										buscaPagina(--contadorDePaginas);
									}
								}else if(option.equals("d")){
									buscaPagina(++contadorDePaginas);
								}else if(option.equals("s")){
									List<ItemOrderModel> list = new ArrayList<>();
									for(ItemOrderModel item: itemOrderList){
										list.add(itemOrderRepository.save(item));
									}
									shoppingCart.setItemList(list);
									shoppingCartRepository.save(shoppingCart);
									break;
								}else{
									System.out.println("how many books you desire to buy?");
									int qtd = Integer.parseInt(input.nextLine());
									ItemOrderModel itemOrder = new ItemOrderModel(0l, qtd, bookRepository.findById(Long.parseLong(option)).get(), null);
									itemOrderList.add(itemOrder);
								}
							}
						}
					}else if(op.equals("s")){
						break;
					}
				}
			}else if(op.equals("7")){
				while(true){
					System.out.println("Book options:");
					clearConsole();
					System.out.println("1 - Register book" + 
										"\n2 - Read book" + 
										"\n3 - Delete book"+
										"\n4 - Find the 5 cheapest books available in inventory"+
										"\n5 - Evaluete"+
										"\ns - Exit:");
	
					op = input.nextLine();
					clearConsole();
					// registrar livro
					if(op.equals("1")){
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
						BookModel book = new BookModel(0l, title, description, yearLaunch, pages, price, null, null, categoryList, userModel, companyModel, authorList, null);
						book = bookRepository.save(book);
						InventoryModel inventory = new InventoryModel(0l, qtd, book);
						inventoryRepository.save(inventory);
						
					// alterar campos ou campo do livro
					}else if(op.equals("2")){
						int contadorDePaginas = 0;
	
						while(true){
							System.out.print("0..n - page number\na - left page \nd - right page\ns - exit\nWhat option do you desire? ");
							
							String option = input.nextLine();
							clearConsole();
	
							//se a opção digitada for vazia começa da pagina 0;
							if(option.equals("")){
								//percorre a lista de paginas
								contadorDePaginas = 0;
								buscaPagina(contadorDePaginas);
							//proxima pagina
							}else if(option.equals("a")){
								if(contadorDePaginas > 0) {
									buscaPagina(--contadorDePaginas);
	
								}
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
					}else if(op.equals("3")){
						int contadorDePaginas = 0;
	
						while(true){
							buscaPagina(contadorDePaginas);
							System.out.print("0..n - ID\na - left page \nd - right page\ns - exit\nType the ID books: ");
							
							String option = input.nextLine();
							clearConsole();
	
							//se a opção digitada for vazia começa da pagina 0;
							if(!option.equals("")){
								if(option.equals("a")){
									if(contadorDePaginas > 0) {
										--contadorDePaginas;
									}
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
					}else if(op.equals("4")){
						// for para percorrer lista dos livros mais baratos
						List<BookModel> lista = bookRepositoryService.findCheapests(5);
						for(int i = 0;i < lista.size();i++){
							System.out.println(lista.get(i).toString());
						}
					}else if(op.equals("5")){
						while(true){
							
							System.out.println("Evaluate options:");
							System.out.println("1 - Add Evaluate" + 
												"\n2 - Show all Evaluetes" + 
												"\n3 - Remover Evaluate"+
												"\ns - Exit:");
	
							op = input.nextLine();

							if(op.equals("1")){
								int contadorDePaginas = 0;
								while(true){

									buscaPagina(contadorDePaginas);
									System.out.print("a - left page \nd - right page\ns - exit\nWhat option do you desire? ");
			
									String option = input.nextLine();
			
									//se a opção digitada for vazia começa da pagina 0;
									if(!option.equals("")){
										//percorre a lista de paginas
										//proxima pagina
										if(option.equals("a")){
											if(contadorDePaginas > 0) {
												buscaPagina(--contadorDePaginas);
											}
										}else if(option.equals("d")){
											buscaPagina(++contadorDePaginas);
										}else if(option.equals("s")){
											break;
										}else{
											System.out.println("how many stars(1 - 5)?");
											int stars = Integer.parseInt(input.nextLine());
											System.out.println("Comment:");
											String comment = input.nextLine();
											EvaluateModel evaluateModel = new EvaluateModel(0l, stars, comment, userModel, bookRepository.findById(Long.parseLong(option)).get());
											evaluateRepository.save(evaluateModel);
										}
									}
								}
							}else if(op.equals("2")){
								System.out.println("Evaluetes:");
								for(EvaluateModel evaluete:evaluateRepository.findAll()){
									System.out.println(evaluete.toString());
								}
								input.nextLine();
							}else if(op.equals("3")){
								while (true) {
									userModel = userRepository.findById(userModel.getId()).get();
									
									for (EvaluateModel evaluate : userModel.getEvaluateList()) {
										System.out.println(evaluate.toString());
									}
									System.out.println("Type the ID Evaluate: ");
									String option = input.nextLine();
									if(option.equals("s")){
										break;
									}
									evaluateRepository.deleteById(Long.parseLong(option));
									userModel = userRepository.findById(userModel.getId()).get();
								}
							}else if(op.equals("s")){
								break;
							}
						}
					}else if(op.equals("s")){
						break;
					}
				}
			}else if(op.equals("8")){
				userModel = login();
			}else if(op.equals("s")){
				isRun = false;
			}
		}
	}

	public void buscaPagina(int pagina){
		try {
			List<BookModel> bookList = bookRepositoryService.findAll(pagina);

			for(BookModel book : bookList) {
				System.out.println(book.toString());
			}
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());;
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

	public UserModel login(){
		Scanner input = new Scanner(System.in);
		UserModel userModel = null;
		List<UserModel> users = userRepository.findAll();
		if(users.size() > 0){
			for(UserModel user : users){
				System.out.println(user.toString());
			}

			System.out.println("Type the User ID: ");
			userModel = userRepository.findById(Long.parseLong(input.nextLine())).get();
		}else{
			System.out.println("User fields");
			System.out.println("Username: ");
			String username = input.nextLine();
			System.out.println("Email: ");
			String email = input.nextLine();
			System.out.println("password: ");
			String password = input.nextLine();
			
			UserModel user = new UserModel(0l, username, email, password, null, null, null, null, null);

			userModel = userRepository.save(user);
		}
		return userModel;
	}
}