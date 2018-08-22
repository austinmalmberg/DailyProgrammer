package com.austin.challenge366;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/98ufvz/20180820_challenge_366_easy_word_funnel_1/
 * 
 * @author Austin Malmberg
 *
 */

public class WordFunnel {

	public static final String DICT_FILENAME = "enable1.txt";
	public static Map<String, Set<String>> wordMap;
	
	public static void main(String[] args) throws IOException {
		
		Set<String> wordset = Files.lines(Paths.get(DICT_FILENAME)).collect(Collectors.toCollection(HashSet::new));
		
		initWordMap(wordset);
		
		System.out.println("\n-----Is a subword?-----");
		printfunnel("leave", "eave");
		printfunnel("reset", "rest");
		printfunnel("dragoon", "dragon");
		printfunnel("eave", "leave");
		printfunnel("sleet", "lets");
		printfunnel("skiff", "ski");
		
		System.out.println("\n-----Subwords-----");
		printSubwords("dragoon");
		printSubwords("boats");
		printSubwords("affidavit");
		
		System.out.println("\n-----Most subwords-----");
		printMostSubwords();
	}
	
	private static void printMostSubwords() {
		wordMap.entrySet().stream()
				.collect(Collectors.groupingBy(e -> e.getValue().size(), TreeMap::new, Collectors.toList()))
				.lastEntry().getValue().forEach(System.out::println);
	}
	
	private static void printSubwords(String word) {
		System.out.printf("%s => %s%n", word, wordMap.containsKey(word) ? wordMap.get(word) : Collections.emptyList());
	}
	
	private static void printfunnel(String word, String subword) {
		System.out.printf("%s -> %s => %s%n", word, subword, funnel(word, subword));
	}
	
	private static boolean funnel(String word, String subword) {
		if(wordMap.containsKey(word))
			return wordMap.get(word).contains(subword);
		
		return false;
	}
	
	private static void initWordMap(Set<String> wordset) {
		wordMap = wordset.stream()
				.flatMap(word -> subwords(word)
						.map(subword -> new SimpleEntry<>(word, subword)))
				.filter(e -> wordset.contains(e.getValue()))
				.collect(Collectors.groupingBy(entry -> entry.getKey(), Collectors.mapping(entry -> entry.getValue(), Collectors.toSet())));
	}
	
	private static Stream<String> subwords(String word) {
		return IntStream.range(0, word.length())
				.mapToObj(i -> word.substring(0, i) + word.substring(i+1));
	}
}
