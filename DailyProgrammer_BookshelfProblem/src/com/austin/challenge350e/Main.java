package com.austin.challenge350e;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {
	
	private static Combinations combos = new Combinations();
	
	private static List<Book> books;	
	
	public static void main(String[] args) {
		
		String[] file = getFile("challenge.txt");
		
		Stack<Integer> availableShelves = Arrays.stream(file[0].split("\\s+"))
				.map(str -> Integer.parseInt(str))
				.sorted()
				.collect(Collectors.toCollection(Stack::new));
		
		books = Arrays.stream(file).skip(1)
				.map(raw -> raw.split("\\s+"))
				.map(raw_arr -> new Book(Integer.parseInt(raw_arr[0]), raw_arr[1]))
				.collect(Collectors.toList());
		
		List<Shelf> shelves = new ArrayList<>();
		
		while(!books.isEmpty()) {
			Shelf newShelf = fillShelf(availableShelves.pop(), books);
			
			if(newShelf.isEmpty()) {
				System.out.println("Impossible.");
				return;
			}
			
			shelves.add(newShelf);
		}
		
		
		// output
		shelves.forEach(System.out::println);
	}
	
	public static Shelf fillShelf(int shelfSize, List<Book> remainingBooks) {
		List<Book> booksOnShelf = combos.getAsStream(remainingBooks).filter(collection -> Shelf.widthOfBooks(collection) <= shelfSize)
				.max(Comparator.comparing(books -> Shelf.widthOfBooks(books))).orElse(Collections.emptyList());
		
		books.removeAll(booksOnShelf);
		
		return new Shelf(shelfSize, booksOnShelf);
	}
	
	public static String[] getFile(String fileName) {
		
		try {
			return Files.lines(new File(fileName).toPath()).toArray(String[]::new);
		} catch(IOException io) {
			io.printStackTrace();
		}
		
		return null;
	}
}