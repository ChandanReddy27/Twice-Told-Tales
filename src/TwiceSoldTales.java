/**
 * TwiceSoldTales.java
 * Note: the name of an awesome
 * used bookstore in Seattle, WA!
 * @author Ethan Ly
 * @author Chandan Reddy
 * CIS 36B, Lab 7.2
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public final class TwiceSoldTales implements Catalogue { //update the signature to inherit from Catalogue
    private ArrayList<Book> books = new ArrayList<Book>();
    private static final String filename = "books.txt";
    
    public static void main(String[] args) throws IOException {
        TwiceSoldTales t = new TwiceSoldTales();
        String title, author, isbn;
        double price;
        int numCopies;
        File file = new File(filename);
        Scanner input = new Scanner(file);
        t.populateCatalogue(input);
        input.close();
        Scanner userInput = new Scanner(System.in);
        String choice = "none";
        System.out.println("Welcome to Twice Sold Tales!");
        System.out.println("\nWe currently have " + Book.getNumBooks() + " books in stock!");
        while(!(choice.equalsIgnoreCase("X"))) {
        	t.printMenu();
        	System.out.print("\nEnter your choice: ");
        	choice = userInput.nextLine();
        	if(choice.equalsIgnoreCase("A")) {
        		System.out.println("\nEnter the book information below: ");
        		System.out.print("\nTitle: ");
        		title = userInput.nextLine();
        		System.out.print("Author: ");
        		author = userInput.nextLine();
        		Book b = new Book(title, author);
        		int index = t.binarySearch(b);
        		if(index != -1) {
        			System.out.println("\nWe have " + t.books.get(index).getTitle() + " in stock!");
        			System.out.println(t.books.get(index).toString());
        			System.out.print("\nWould you like to purchase it (y/n): ");
        			String purchase = userInput.nextLine();
        			if(purchase.equalsIgnoreCase("y")) {
        				t.books.get(index).decreaseCopies();
        				t.books.get(index).updatePrice();
        				System.out.println("\nThank you for your purchase!");
        			} else {
        				continue;
        			}
        		} else {
        			System.out.println("\nSorry! We don't carry that title right now.");
        		}
        	} else if(choice.equalsIgnoreCase("B")) {
        		System.out.println("\nEnter the book information below:");
        		System.out.print("\nTitle: ");
        		title = userInput.nextLine();
        		System.out.print("Author: ");
        		author = userInput.nextLine();
        		System.out.print("ISBN: ");
        		isbn = userInput.nextLine();
        		System.out.print("\nEnter the price you paid: ");
        		price = Double.parseDouble(userInput.nextLine());
        		DecimalFormat df = new DecimalFormat("#.##");
        		double sellPrice = 0.25 * price;
        		System.out.println("Thank you! We will purchase the book for $" + df.format(sellPrice));
        		Book b = new Book(title, author);
        		int index = t.binarySearch(b);
        		if(index != -1) {
        			t.books.get(index).updateNumCopies(1);
        		} else {
        			Book a = new Book(title, author, price, isbn, 1);
        			t.books.add(a);
        		}
        		
        	} else if(choice.equalsIgnoreCase("C")) {
        		t.printStock();
        	} else if(choice.equalsIgnoreCase("X")) {
        		break;
        	} else {
        		System.out.println("\nInvalid option!");
        	}
        }
        System.out.println("\nPlease come again!");
        
        
    }
    
    /**
     * Reads from a file and populates the catalogue
     * @param input the Scanner used to read in the data
     */
	@Override public void populateCatalogue(Scanner input) throws IOException {
		String title, author, isbn;
		double price;
		int numCopies;
		while (input.hasNextLine()) {
			title = input.nextLine();
			author = input.nextLine();
			price = Double.parseDouble(input.nextLine());
			isbn = input.nextLine();
			numCopies = Integer.parseInt(input.nextLine());
			if (input.hasNextLine()){
			    input.nextLine();
			}
			Book b = new Book(title, author, price, isbn, numCopies);
			Book.updateNumBooks(b.getNumCopies());
			books.add(b);
		}
	}
    
    /**
     * Searches for Media m in the catalogue
     * @param m the piece of media to locate
     * @return the location of the media in 
     * the catalogue
     */
    @Override public int binarySearch(Media m) {
    	Book b = (Book)m;
		int low = 0;
		int high = books.size() - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (books.get(mid).getTitle().equals(m.getTitle()) && books.get(mid).getAuthor().equals(b.getAuthor())) {
				return mid;
			} else if (books.get(mid).getTitle().compareTo(m.getTitle()) > 0 && books.get(mid).getAuthor().equals(b.getAuthor())) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;

	}
    
    /** 
     * Sorts the catalogue into
     * ascending order
     */
	@Override public void bubbleSort() {
		for (int i = 0; i <= books.size() - 2; i++) {
			for (int j = 0; j <= books.size() - 2; j++) {
				if (books.get(i).compareTo(books.get(i + 1)) > 0) {
					Book temp = books.get(i);
					books.set(i, books.get(i + 1));
					books.set(i + 1, temp);
				}
			}
		}
	}
    
    /**
     * Prints a menu of options to interact
     * with the catalogue
     */
    @Override public void printMenu() {
    	System.out.println("\nPlease select from one of the options: "
    			+ "\n\nA. Search for a book to purchase" 
    			+ "\nB. Sell a book" 
    			+ "\nC. Print catalogue"
    			+ "\nX. Exit");
    }
    
    /**
     * Prints out the current catalogue
     */
    @Override public void printStock() {
		for (int i = 0; i < books.size(); i++) {
			System.out.println(books.get(i).toString());
		}
	}
}