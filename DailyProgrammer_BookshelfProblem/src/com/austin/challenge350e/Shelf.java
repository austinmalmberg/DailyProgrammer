package com.austin.challenge350e;

import java.util.ArrayList;
import java.util.List;

public class Shelf {

	int width;
	List<Book> books;
	
	public Shelf(int width) {
		this(width, new ArrayList<Book>());
	}
	
	public Shelf(int width, List<Book> books) {
		this.width = width;
		this.books = books;
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void addBooks(List<Book> books) {
		books.addAll(books);
	}
	
	public int widthOfBooks() {
		return widthOfBooks(books);
	}
	
	public static int widthOfBooks(List<Book> books) {
		return books.stream().mapToInt(book -> book.width).sum();
	}
	
	public String toString() {
		return width + ": " + books;
	}
	
	public boolean isEmpty() { return books.isEmpty(); }
}
