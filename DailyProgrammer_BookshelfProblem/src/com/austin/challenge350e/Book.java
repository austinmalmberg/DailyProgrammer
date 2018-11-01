package com.austin.challenge350e;

public class Book implements Comparable<Book> {
	
	int width;
	String title;
	
	public Book(int width, String title) {
		this.width = width;
		this.title = title;
	}
	
	public String toString() {
		return width + " " + title;
	}

	@Override
	public int compareTo(Book o1) {
		return width - o1.width;
	}
}
