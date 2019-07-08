/**
 * Book.java
 * @author Ethan Ly
 * @author Chandan Reddy
 * CIS 36B, Lab 7.2
 */

import java.text.DecimalFormat;

public class Book extends Media { // update this signature!
	private String author;
	private String isbn;
	private static int numBooks = 0;

	/**
	 * Default constructor for Book. Calls the 5 argument constructor Sets title
	 * default to "title unknown" Sets author default to "author unknown" Sets price
	 * to 0.0 Sets isbn to "0"
	 */
	public Book() {
		this("title unkown", "author unknown", 0.0, "0", 0);
	}

	/**
	 * Two argument constructor for the Book class. Calls the 5 argument constructor
	 * Sets price to a default of 0.0, isbn to a default of "0" and numCopies to a
	 * default of 0
	 * 
	 * @param title  the book's title
	 * @param author the book's author
	 */
	public Book(String title, String author) {
		this(title, author, 0.0, "0", 0);
	}

	/**
	 * Constructor for book. Calls media constructor
	 * 
	 * @param title     the book's title
	 * @param author    the book's author
	 * @param price     the book's price
	 * @param isbn      the book's 7-digit isbn
	 * @param numCopies the current num copies
	 */
	public Book(String title, String author, double price, String isbn, int numCopies) {
		super(title, price, numCopies);
		this.author = author;
		this.isbn = isbn;
	}

	/**
	 * Returns the first and last names of the author
	 * 
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Returns the 7 digit ISBN number
	 * 
	 * @return the ISBN number
	 */
	public String getISBN() {
		return isbn;
	}

	/**
	 * Returns the total number of books
	 * 
	 * @return the value of numBooks
	 */
	public static int getNumBooks() {
		return numBooks;
	}

	/**
	 * Increases the price of the book by $0.25 each time a copy is sold
	 */
	@Override public void updatePrice() {
		setPrice(getPrice() + 0.25);
	}

	/**
	 * Updates numBooks variable by a specified amount
	 * 
	 * @param n the number of books to add
	 */
	public static void updateNumBooks(int n) {
		numBooks += n;
	}

	/**
	 * Overrides equals for Object using the formula given in class. For the
	 * purposes of this assignment, we will consider two books to be equal on the
	 * basis of title and author only
	 * 
	 * @return whether two books have the same title and author
	 */
	@Override public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (!(o instanceof Book)) {
			return false;
		} else {
			Book b = (Book) o;
			return this.getTitle().equals(b.getTitle()) && this.getAuthor().equals(b.getAuthor());
		}
	}

	/**
	 * Creates a book String in the following format Title: <title> Author: <author>
	 * Price: $<price to two decimal places> ISBN #: <isbn> Copies: <numCopies> Note
	 * that there are no <> around the values The <> are standard in programming to
	 * indicate fill in here
	 */
	@Override public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "\nTitle: " + getTitle() 
          	 + "\nAuthor: " + author 
          	 + "\nPrice: $" + df.format(getPrice()) 
         	 + "\nISBN #: " + isbn
        	 + "\nCopies: " + getNumCopies();
    }

	/**
	 * Compares two Books. Returns 0 if the two Books are equal If the two books
	 * have the same title returns compareTo of the authors Otherwise, returns
	 * compareTo of the titles
	 */
	public int compareTo(Book b) {
		if (this.equals(b)) {
			return 0;
		} else if (!(getTitle().equals(b.getTitle()))) {
			return getTitle().compareTo(b.getTitle());
		} else {
			return author.compareTo(b.author);
		}
	}

}