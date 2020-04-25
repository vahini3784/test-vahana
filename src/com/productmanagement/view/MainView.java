package com.productmanagement.view;

import java.util.Scanner;

import com.productmanagement.model.Category;
import com.productmanagement.model.Product;
import com.productmanagement.service.CategoryService;
import com.productmanagement.service.ProductService;

public class MainView {

	private static CategoryService categoryService = new CategoryService();
	private static ProductService productService = new ProductService();

	private static Scanner sc = new Scanner(System.in);

	public static final int CATEGORY_MANAGEMENT = 1;
	public static final int PRODUCT_MANAGEMENT = 2;

	public static final int ADD_CATEGORY = 1;
	public static final int VIEW_CATEGORY = 2;

	public static final int ADD_PRODUCT = 1;
	public static final int VIEW_PRODUCTS = 2;
	public static final int DELETE_PRODUCT = 3;

	public static void main(String[] args) {
		showMainMenu();
	}

	public static void showMainMenu() {

		System.out.println("Please Select any of the choice : ");
		System.out.println("1. CATEGORY MANAGE\n2. PRODUCT MANAGE");
		int enteredChoice = sc.nextInt();

		switch (enteredChoice) {
		case CATEGORY_MANAGEMENT:
			showCategoryMenu();
			break;
		case PRODUCT_MANAGEMENT:
			showProductMenu();
			break;
		default:
			showErrorMessage();
			break;
		}

	}

	public static void showCategoryMenu() {
		System.out.println("====== CATEGORY MANAGEMENT ==========");
		System.out.println("1. ADD CATEGORY \n2. VIEW CATEGORY");

		int enteredChoice = sc.nextInt();
		switch (enteredChoice) {
		case ADD_CATEGORY:
			addCategory();
			break;
		case VIEW_CATEGORY:
			viewCategory();
			break;
		default:
			showErrorMessage();
			break;
		}
	}

	public static void viewCategory() {
		printHeadingLine();
		System.out.format("| %15s | %32s |\n", "CATEGORY ID", "CATEGORY NAME");
		printHeadingLine();
		for (Category category : categoryService.getAllCategory()) {
			System.out.format("| %15d | %32s |\n", category.getId(), category.getName());
		}
		printHeadingLine();
	}

	public static void addCategory() {
		String name = null;
		System.out.println("======== ADD CATEGORY =========");
		System.out.println("ENTER THE NAME FOR CATEGORY");
		sc.nextLine();
		name = sc.nextLine();
		Category category = new Category(name);
		boolean isInserted = categoryService.insert(category);
		if (isInserted) {
			System.out.println("CATEGORY ADDED SUCCESSFULLY");
			viewCategory();
		} else {
			System.out.println("ERROR OCCURED DURING INSERTING");
		}
	}

	public static void showProductMenu() {
		System.out.println("====== PRODUCT MANAGEMENT ==========");
		System.out.println("1. ADD PRODUCT\n2. VIEW PRODUCT\n3. DELETE PRODUCT");
		System.out.println("Please Select any of the choice : ");
		int enteredChoice = sc.nextInt();

		switch (enteredChoice) {
		case ADD_PRODUCT:
			addProduct();
			break;
		case VIEW_PRODUCTS:
			viewProducts();
			break;
		case DELETE_PRODUCT:
			deleteProduct();
			break;
		default:
			showErrorMessage();
			break;
		}
	}

	private static void deleteProduct() {
		viewCategory();
		Category category = null;
		do {
			System.out.println("PLEASE SELECT THE CATEGORY (NOTE : ENTER THE VALID CATEGORY ID)");
			int categoryId = sc.nextInt();
			category = isValidCategory(categoryId);
		} while (category == null);
		showProducts(category);
		Product product = null;
		do {
			System.out.println("PLEASE SELECT THE PRODUCT TO DELETE (NOTE : ENETER THE VALID PRODUCT ID)");
			int productId = sc.nextInt();
			product = isValidProduct(productId, category);
		} while (product == null);
		if (productService.delete(product)) {
			System.out.println("PRODUCT DELETED SUCCESSFULLY");
			showProducts(category);
		} else {
			System.out.println("ERROR IN DELETING THE PRODUCT");
		}
	}

	private static void viewProducts() {
		viewCategory();
		Category category = null;
		do {
			System.out.println("PLEASE SELECT THE CATEGORY (NOTE : ENTER THE VALID CATEGORY ID)");
			int categoryId = sc.nextInt();
			category = isValidCategory(categoryId);
		} while (category == null);
		showProducts(category);
	}

	private static void addProduct() {
		System.out.println("ENTER THE NAME OF THE PRODUCT");
		sc.nextLine();
		String name = sc.nextLine();
		Category category = null;
		do {
			viewCategory();
			System.out.println("PLEASE SELECT THE CATEGORY (NOTE : ENTER THE CATEGORY ID)");
			int categoryId = sc.nextInt();
			category = isValidCategory(categoryId);
		} while (category == null);
		Product product = new Product(name, category);
		if (productService.insert(product)) {
			System.out.println("PRODUCT ADDED SUCCESSFULLY");
			showProducts(category);
		} else {
			System.out.println("ERROR IN ADDING THE PRODUCT");
		}
	}

	public static void showProducts(Category category) {
		boolean printHeading = true;
		for (Product product : productService.getAllProduct()) {
			if (product.getCategory().getId() == category.getId()) {
				if (printHeading) {
					System.out.println("SHOWING ITEMS OF CATEGORY : " + category.getName());
					printHeadingLine();
					System.out.format("| %15s | %32s |\n", "PRODUCT ID", "PRODUCT NAME");
					printHeadingLine();
					printHeading = false;
				}
				System.out.format("| %15d | %32s |\n", product.getId(), product.getName());
			}
		}
		if (printHeading) {
			printHeadingLine();
			System.out.println("THERE ARE NO ITEMS PRESENT IN CATEGORY : " + category.getName());
			printHeadingLine();
		}else {
			printHeadingLine();
		}
	}

	public static void showErrorMessage() {
		System.out.println("INVALID OPTION SELECTED");
	}

	// this method will check if the categoryId is valid and return category object
	// if it is valid else will return null
	public static Category isValidCategory(int categoryId) {
		Category category = null;
		for (Category tempCategory : categoryService.getAllCategory()) {
			if (tempCategory.getId() == categoryId) {
				category = tempCategory;
				break;
			}
		}
		return category;
	}

	// this method will check if the product selected with given productId and it's
	// category existing or not
	// if product is present it will return the product object else will return null
	private static Product isValidProduct(int productId, Category category) {
		Product product = null;
		for (Product tempProduct : productService.getAllProduct()) {
			if (tempProduct.getId() == productId && tempProduct.getCategory().getId() == category.getId()) {
				product = tempProduct;
			}
		}
		return product;
	}
	
	// this is a helper method helps in printing table format data
	private static void printHeadingLine() {
		System.out.println("+----------------------------------------------------+");
	}
}
