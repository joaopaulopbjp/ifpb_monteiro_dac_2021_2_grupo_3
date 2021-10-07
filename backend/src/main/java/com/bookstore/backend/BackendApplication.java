package com.bookstore.backend;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.bookstore.backend.domain.model.sale.RevenuesModel;
import com.bookstore.backend.domain.model.sale.SaleModel;
import com.bookstore.backend.domain.model.sale.shoppingCartModel;
import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.author.AuthorRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.category.CategoryRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.company.PublishingCompanyRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.evaluate.EvaluateRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.inventory.InventoryRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.AdminRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.ItemOrderRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.RevenuesRepositoryServices;
import com.bookstore.backend.infrastructure.persistence.service.sale.SaleRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.ShoppingCartRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.util.converter.LocalDateStringConverter;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
	// Respositories
	@Autowired
	UserRepositoryService userRepositoryService;

	@Autowired
	AdminRepositoryService adminRepositoryService;

	@Autowired
	AuthorRepositoryService authorRepositoryService;

	@Autowired
	PublishingCompanyRepositoryService companyRepositoryService;

	@Autowired
	CategoryRepositoryService categoryRepositoryService;

	@Autowired
	InventoryRepositoryService inventoryRepositoryService;

	@Autowired
	BookRepositoryService bookRepositoryService;

	@Autowired
	ShoppingCartRepositoryService shoppingCartRepositoryService;

	@Autowired
	ItemOrderRepositoryService itemOrderRepositoryService;

	@Autowired
	EvaluateRepositoryService evaluateRepositoryService;

	@Autowired
	SaleRepositoryService saleRepositoryService;

	@Autowired
	RevenuesRepositoryServices revenuesRepositoryServices;

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
			clearConsole();

			if(op.equals("1")){
				while(true){
					System.out.println("User options:"+
										"\n1 - Register User"+
										"\n2 - Find user by email, id, username"+
										"\n3 - Change user to admin"+
										"\n4 - Update User"+
										"\n5 - Delete User"+
										"\ns - Exit:");
					op = input.nextLine();
					clearConsole();
					if(op.equals("1")){
	
						System.out.println("User fields");
						System.out.println("Username: ");
						String username = input.nextLine();
						clearConsole();
						System.out.println("Email: ");
						String email = input.nextLine();
						clearConsole();
						System.out.println("password: ");
						String password = input.nextLine();
						clearConsole();
						
						UserModel user = new UserModel(0l, username, email, password, null, null, null, null, null);
		
						userRepositoryService.getInstance().save(user);
						clearConsole();
					}else if(op.equals("2")){
						System.out.println("What option do you desire?");
						System.out.print("1 - Email\n2 - id\n3 - username: ");
						String option = input.nextLine();
						clearConsole();

						if(option.equals("1")){
							System.out.print("Type your email: ");
							System.out.println(userRepositoryService.getInstance().findByEmail(input.nextLine()).toString());
							input.nextLine();
							clearConsole();

						}else if(option.equals("2")){
							System.out.println("type your id: ");
							System.out.println(userRepositoryService.getInstance().findById(Long.parseLong(input.nextLine())).get().toString());
							input.nextLine();
							clearConsole();

						}else if(option.equals("3")){
							System.out.println("type your Username: ");
							System.out.println(userRepositoryService.getInstance().findByUsername(input.nextLine()).toString());
							input.nextLine();
							clearConsole();

						}
					}else if(op.equals("3")){
						while(true) {
							System.out.println("Users:");
							List<UserModel> userList = userRepositoryService.getInstance().findAll();
							for(UserModel model : userList) {
								System.out.println(model.toString());
							}
							System.out.print("Type s to exit or user ID: ");
							String id = input.nextLine();
		
							if(id.equals("s")) break;
		
							UserModel user = userRepositoryService.getInstance().findById(Long.parseLong(id)).get();
							AdminModel admin = new AdminModel(user.getId(),
								user.getUsername(),
								user.getEmail(),
								user.getPassword(),
								user.getAddressList(),
								user.getProductForSaleList(),
								user.getSaleHistory());
		
							userRepositoryService.getInstance().delete(user);
							adminRepositoryService.getInstance().save(admin);
							clearConsole();
						}
					}else if(op.equals("4")){
						List<UserModel>users = userRepositoryService.getInstance().findAll();
						for(int i = 0; i <= users.size(); i++){
							System.out.println(users.get((i)).toString());
						}
						System.out.println("enter the user ID");
						Long option = Long.parseLong(input.nextLine());

						System.out.println("Username: ");
						String username = input.nextLine();
						System.out.println("Email: ");
						String email = input.nextLine();
						System.out.println("Password: ");
						String password = input.nextLine();

						UserModel userDataBase = new UserModel(option, username, email, password, null, null, null, null, null);
						userRepositoryService.update(userDataBase);
						clearConsole();

					}else if(op.equals("5")){
						List<UserModel>users = userRepositoryService.getInstance().findAll();
						for(int i = 0; i <= users.size(); i++){
							System.out.println(users.get((i)).toString());
						}
						while(true){
							System.out.println("Type the ID user(Type 0 for exit): ");
							String id = input.nextLine();
							if(id.equals("s")){
								clearConsole();
								break;
							}

							userRepositoryService.getInstance().deleteById(Long.parseLong(id));
						}
					}else if(op.equals("s")){
						clearConsole();
						break;
					}
				}
		
			}else if(op.equals("2")){
				while(true){
					System.out.println("Admin options:"+
										"\n1 - Show all admins"+
										"\n2 - Update admin"+
										"\n3 - Delete admin"+
										"\ns - Exit:");
					op = input.nextLine();
					clearConsole();

					if(op.equals("1")){
						List<AdminModel> adminList = adminRepositoryService.getInstance().findAll();
						for(AdminModel admin : adminList) {
							System.out.println(admin.toString());
						}
						input.nextLine();
						clearConsole();

					}else if(op.equals("2")){
						List<AdminModel>adims = adminRepositoryService.getInstance().findAll();
						for(int i = 0; i <= adims.size(); i++){
							System.out.println(adims.get((i)).toString());
						}
						System.out.println("enter the admin ID");
						Long option = Long.parseLong(input.nextLine());

						System.out.println("Username: ");
						String username = input.nextLine();
						System.out.println("Email: ");
						String email = input.nextLine();
						System.out.println("Password: ");
						String password = input.nextLine();

						AdminModel adminDataBase = new AdminModel(option, username, email, password, null, null, null);
						adminRepositoryService.update(adminDataBase);
						clearConsole();
					}else if(op.equals("3")){
						List<AdminModel>adims = adminRepositoryService.getInstance().findAll();
						for(int i = 0; i <= adims.size(); i++){
							System.out.println(adims.get((i)).toString());
						}

						while(true){
							System.out.println("Type the ID admin(Type 0 for exit): ");
							String id = input.nextLine();
							if(id.equals("s")){
								clearConsole();
								break;
							}

							adminRepositoryService.getInstance().deleteById(Long.parseLong(id));
						}
					}
					else if(op.equals("s")){
						clearConsole();
						break;
					}
				}
			}else if(op.equals("3")){
				while(true){
					System.out.println("Author options:"+
										"\n1 - Register author"+
										"\n2 - Show All authors"+
										"\n3 - Update author"+
										"\n4 - Delete author"+
										"\ns - Exit:");
					op = input.nextLine();
					clearConsole();

					if(op.equals("1")){
						System.out.println("Register author");
						System.out.println("Name: ");
						String name = input.nextLine();

						AuthorModel author = new AuthorModel(0l, name, null);

						authorRepositoryService.getInstance().save(author);
						clearConsole();
					}else if(op.equals("2")){
						for(AuthorModel author: authorRepositoryService.getInstance().findAll()){
							System.out.println(author.toString());
						}
						input.nextLine();
						clearConsole();

					}else if(op.equals("3")){
						List<AuthorModel>authors = authorRepositoryService.getInstance().findAll();
						for(int i = 0; i <= authors.size(); i++){
							System.out.println(authors.get((i)).toString());
						}
						System.out.println("enter the author ID");
						Long option = Long.parseLong(input.nextLine());

						System.out.println("name: ");
						String username = input.nextLine();

						AuthorModel authorDataBase = new AuthorModel(option, username, null);
						authorRepositoryService.update(authorDataBase);
						clearConsole();	

					}else if(op.equals("4")){
						List<AuthorModel>authors = authorRepositoryService.getInstance().findAll();
						for(int i = 0; i <= authors.size(); i++){
							System.out.println(authors.get((i)).toString());
						}

						while(true){
							System.out.println("Type the ID author(Type 0 for exit): ");
							String id = input.nextLine();
							if(id.equals("s")){
								clearConsole();
								break;
							}

							authorRepositoryService.getInstance().deleteById(Long.parseLong(id));
						}

					}else if(op.equals("s")){
						clearConsole();
						break;
					}
				}
			}else if(op.equals("4")){
				while(true){
					System.out.println("Category options:"+
										"\n1 - Register category"+
										"\n2 - Show all categorys"+
										"\n3 - Delete category"+
										"\n4 - Update category"+
										"\ns - Exit:");
					op = input.nextLine();
					clearConsole();

					if(op.equals("1")){
						System.out.print("category name: ");
						String name = input.nextLine();

						categoryRepositoryService.getInstance().save(new CategoryModel(0l, name, null));
						clearConsole();

					}else if(op.equals("2")){
						List<CategoryModel> categoryList = categoryRepositoryService.getInstance().findAll();
						for(CategoryModel category : categoryList) {
							System.out.println(category.toString());
						}
						input.nextLine();
						clearConsole();

					}else if(op.equals("3")){
						System.out.println("List of Categories");
						List<CategoryModel> categoryList = categoryRepositoryService.getInstance().findAll();
						for(int i = 0;i < categoryList.size();i++){
							System.out.println(categoryList.get(i).toString());
						}
						
						while(true){
							System.out.println("Type the ID Category(Type 0 for exit): ");
							String id = input.nextLine();
							if(id.equals("s")){
								clearConsole();
								break;
							}

							categoryRepositoryService.getInstance().deleteById(Long.parseLong(id));
						}
					}else if (op.equals("4")){
						List<CategoryModel>categorys = categoryRepositoryService.getInstance().findAll();
						for(int i = 0; i <= categorys.size(); i++){
							System.out.println(categorys.get((i)).toString());
						}
						System.out.println("enter the category ID");
						Long option = Long.parseLong(input.nextLine());

						System.out.println("name: ");
						String name = input.nextLine();

						CategoryModel categoryDataBase = new CategoryModel(option, name, null);
						categoryRepositoryService.update(categoryDataBase);
						clearConsole();	
					
					}else if(op.equals("s")){
						clearConsole();
						break;
					}
				}

			}else if(op.equals("5")){
				while(true){
					System.out.println("Company options:"+
										"\n1 - Register a new Company"+
										"\n2 - Show all companys"+
										"\n3 - Update company"+
										"\n4 - Delete company"+
										"\ns - Exit:");
					op = input.nextLine();
					clearConsole();

					if(op.equals("1")){
						System.out.println("Type the Company name: ");
						companyRepositoryService.getInstance().save(new PublishingCompanyModel(0l, input.nextLine(), null));
						clearConsole();
					}else if(op.equals("2")){
						for(PublishingCompanyModel company:companyRepositoryService.getInstance().findAll()){
							System.out.println(company.toString());
						}
						input.nextLine();
						clearConsole();
					}else if(op.equals("3")){

						List<PublishingCompanyModel>companys = companyRepositoryService.getInstance().findAll();
						for(int i = 0; i <= companys.size(); i++){
							System.out.println(companys.get((i)).toString());
						}
						System.out.println("enter the company ID");
						Long option = Long.parseLong(input.nextLine());

						System.out.println("name: ");
						String name = input.nextLine();

						PublishingCompanyModel companyDataBase = new PublishingCompanyModel(option, name, null);
						companyRepositoryService.update(companyDataBase);
						clearConsole();	
					}else if(op.equals("4")){
						List<PublishingCompanyModel>companys = companyRepositoryService.getInstance().findAll();
						for(int i = 0; i <= companys.size(); i++){
							System.out.println(companys.get((i)).toString());
						}

						while(true){
							System.out.println("Type the ID company(Type 0 for exit): ");
							String id = input.nextLine();
							if(id.equals("s")){
								clearConsole();
								break;
							}

							companyRepositoryService.getInstance().deleteById(Long.parseLong(id));
						}
					}else if(op.equals("s")){
						clearConsole();
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
					clearConsole();

					//opção para remover livro do carrinho
					if(op.equals("1")){
						shoppingCart = shoppingCartRepositoryService.getInstance().findById(userModel.getShoppingCart().getId()).get();
						while(true){
							System.out.println("Items Oders:");
							for(ItemOrderModel item: shoppingCart.getItemList()){
								System.out.println(item.getProduct().toString() + 
								" Amount: " + item.getAmount()+ 
								" Price: " + item.getTotalPrice());
							}
	
							System.out.print("Id Book for remove(Type s for exit): ");
							String id = input.nextLine();

							if(id.equals("s")){
								clearConsole();
								break;
							}
							shoppingCart.removeItemOrderFromItemListByProductId(Long.parseLong(id));
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
										clearConsole();
										buscaPagina(--contadorDePaginas);
									}
								}else if(option.equals("d")){
									clearConsole();
									buscaPagina(++contadorDePaginas);
								}else if(option.equals("s")){
									List<ItemOrderModel> list = new ArrayList<>();
									for(ItemOrderModel item: itemOrderList){
										list.add(itemOrderRepositoryService.getInstance().save(item));
									}
									if(list.size() > 0) {
										shoppingCart.setItemList(list);
										shoppingCartRepositoryService.getInstance().save(shoppingCart);
									}
									clearConsole();
									break;
								}else{
									System.out.println("how many books you desire to buy?");
									int qtd = Integer.parseInt(input.nextLine());
									ItemOrderModel itemOrder = new ItemOrderModel(0l, qtd, bookRepositoryService.getInstance().findById(Long.parseLong(option)).get(), null);
									itemOrderList.add(itemOrder);
									clearConsole();
								}
							}
						}
					}else if(op.equals("s")){
						clearConsole();
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
										"\n6 - Update book"+
										"\ns - Exit:");
	
					op = input.nextLine();
					clearConsole();
					// registrar livro
					if(op.equals("1")){
						System.out.println("Book Options");
						System.out.println("title: ");
						String title = input.nextLine();
						clearConsole();
						System.out.println("Escolha o autor:");
						// percorre a lista de autores.
						List<AuthorModel> listAuthors = authorRepositoryService.getInstance().findAll();
						for(int i = 0;i < listAuthors.size();i++){
							System.out.println(listAuthors.get(i).toString());
						}
						List<AuthorModel> authorList = new ArrayList<>();
						while(true){
							System.out.println("Type the ID Author(Type s for exit): ");
							String id = input.nextLine();
							if(id.equals("s")){
								clearConsole();
								break;
							}
							authorList.add(authorRepositoryService.getInstance().findById(Long.parseLong(id)).get());
						}
						System.out.println("description: ");
						String description = input.nextLine();
						clearConsole();
						System.out.println("yearLaunch: ");
						int yearLaunch = Integer.parseInt(input.nextLine());
						clearConsole();
						System.out.println("pages: ");
						int pages = Integer.parseInt(input.nextLine());
						clearConsole();
						List<CategoryModel> categoryList = categoryRepositoryService.getInstance().findAll();
						for(int i = 0;i < categoryList.size();i++){
							System.out.println(categoryList.get(i).toString());
						}
						List<CategoryModel> cateList = new ArrayList<>();
						while(true){
							System.out.println("Type the ID Category(Type s for exit): ");
							String id = input.nextLine();
							if(id.equals("s")){
								clearConsole();
								break;
							}
							cateList.add(categoryRepositoryService.getInstance().findById(Long.parseLong(id)).get());
						}
						
						System.out.println("price: ");
						BigDecimal price = new BigDecimal(input.nextLine());
						clearConsole();
						List<PublishingCompanyModel> listCompanys = companyRepositoryService.getInstance().findAll();
						for(int i = 0;i < listCompanys.size();i++){
							System.out.println(listCompanys.get(i).toString());
						}
						System.out.println("Type the ID company: ");
						Long companyId = Long.parseLong(input.nextLine());
						clearConsole();
						PublishingCompanyModel companyModel = companyRepositoryService.getInstance().findById(companyId).get();
						System.out.println("Inventory: ");
						int qtd = Integer.parseInt(input.nextLine());
						BookModel book = new BookModel(0l, title, description, yearLaunch, pages, price, null, null, categoryList, userModel, companyModel, authorList, null);
						book = bookRepositoryService.getInstance().save(book);
						InventoryModel inventory = new InventoryModel(0l, qtd, book, null);
						inventoryRepositoryService.getInstance().save(inventory);
						clearConsole();
						
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
								clearConsole();
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
									bookRepositoryService.getInstance().deleteById(id);
								}
							}
						}
					}else if(op.equals("4")){
						// for para percorrer lista dos livros mais baratos
						List<BookModel> lista = bookRepositoryService.findCheapests(5);
						for(int i = 0;i < lista.size();i++){
							System.out.println(lista.get(i).toString());
						}
						input.nextLine();
						clearConsole();

					}else if(op.equals("5")){
						while(true){
							
							System.out.println("Evaluate options:");
							System.out.println("1 - Add Evaluate" + 
												"\n2 - Show all Evaluetes" + 
												"\n3 - Remover Evaluate"+
												"\ns - Exit:");
	
							op = input.nextLine();
							clearConsole();

							if(op.equals("1")){
								int contadorDePaginas = 0;
								while(true){

									buscaPagina(contadorDePaginas);
									System.out.print("a - left page \nd - right page\ns - exit\nWhat option do you desire? ");
			
									String option = input.nextLine();
			
									clearConsole();

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
											clearConsole();
											break;
										}else{
											System.out.println("how many stars(1 - 5)?");
											int stars = Integer.parseInt(input.nextLine());
											System.out.println("Comment:");
											String comment = input.nextLine();
											EvaluateModel evaluateModel = new EvaluateModel(0l, stars, comment, userModel, bookRepositoryService.getInstance().findById(Long.parseLong(option)).get());
											evaluateRepositoryService.getInstance().save(evaluateModel);
											clearConsole();
										}
									}
								}
							}else if(op.equals("2")){
								System.out.println("Evaluetes:");
								for(EvaluateModel evaluete:evaluateRepositoryService.getInstance().findAll()){
									System.out.println(evaluete.toString());
								}
								input.nextLine();
								clearConsole();
							}else if(op.equals("3")){
								while (true) {
									userModel = userRepositoryService.getInstance().findById(userModel.getId()).get();
									
									for (EvaluateModel evaluate : userModel.getEvaluateList()) {
										System.out.println(evaluate.toString());
									}
									System.out.println("Type the ID Evaluate: ");
									String option = input.nextLine();
									if(option.equals("s")){
										clearConsole();
										break;
									}

									evaluateRepositoryService.getInstance().deleteById(Long.parseLong(option));
									userModel = userRepositoryService.getInstance().findById(userModel.getId()).get();
								}
							}else if(op.equals("s")){
								clearConsole();
								break;
							}
						}
					}else if(op.equals("6")){
						int contadorDePaginas = 0;
						while(true){

							buscaPagina(contadorDePaginas);
							System.out.print("a - left page \nd - right page\ns - exit\nWhat option do you desire? ");
	
							String option = input.nextLine();
	
							clearConsole();

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
									clearConsole();
									break;
								}else{
									System.out.println("title");
									String title = input.nextLine();
									System.out.println("description:");
									String desc = input.nextLine();
									System.out.println("year_launch:");
									Integer year = Integer.parseInt(input.nextLine());
									System.out.println("pages: ");
									Integer pages = Integer.parseInt(input.nextLine());
									System.out.println("price: ");
									BigDecimal price = new BigDecimal(input.nextLine());
									BookModel bookDataBase = new BookModel(Long.parseLong(option), title, desc, year, pages, price, null, null, null, null, null, null, null);
									bookRepositoryService.update(bookDataBase);
									clearConsole();
								}
							}
					}
				}
					else if(op.equals("s")){
						clearConsole();
						break;
					}
				}
			}else if(op.equals("8")){
				userModel = login();
				clearConsole();
			}else if(op.equals("s")){
				isRun = false;
			}
		}
	}

	public void buscaPagina(int pagina) {
		try {
			List<BookModel> bookList = bookRepositoryService.findAll(pagina);

			for (BookModel book : bookList) {
				System.out.println(book.toString());
			}
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
			;
		}
	}

	public void clearConsole() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserModel login() {
		Scanner input = new Scanner(System.in);
		UserModel userModel = null;
		List<UserModel> users = userRepositoryService.getInstance().findAll();
		if (users.size() > 0) {
			for (UserModel user : users) {
				System.out.println(user.toString());
			}

			System.out.println("Type the User ID: ");
			userModel = userRepositoryService.getInstance().findById(Long.parseLong(input.nextLine())).get();
		} else {
			System.out.println("User fields");
			System.out.println("Username: ");
			String username = input.nextLine();
			System.out.println("Email: ");
			String email = input.nextLine();
			System.out.println("password: ");
			String password = input.nextLine();

			UserModel user = new UserModel(0l, username, email, password, null, null, null, null, null);

			userModel = userRepositoryService.getInstance().save(user);
		}
		return userModel;
	}
}