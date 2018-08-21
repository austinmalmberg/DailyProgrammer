package com.austin.challenge366;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
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

	public static final String DICTIONARY = "enable1.txt";
	public static Map<String, Set<String>> wordMap;
	
	public static void main(String[] args) throws IOException {
		Set<String> words = Files.lines(Paths.get(DICTIONARY)).collect(Collectors.toCollection(TreeSet::new));
		
		initWordMap(words);
		
		printfunnel("leave", "eave");
		printfunnel("reset", "rest");
		printfunnel("dragoon", "dragon");
		printfunnel("eave", "leave");
		printfunnel("sleet", "lets");
		printfunnel("skiff", "ski");
		
		printAllSubwords("dragoon");
		printAllSubwords("boats");
		printAllSubwords("affidavit");
		
		printMostSubwords();
	}
	
	private static void printMostSubwords() {
//		int max = wordMap.values().stream().mapToInt(set -> set.size()).max().getAsInt();
//		
//		wordMap.entrySet().stream().filter(entry -> entry.getValue().size() == max).forEach(System.out::println);
		
		wordMap.entrySet().stream()
				.max(Comparator.comparingInt(i -> i.getValue().size()))
				.FINISH THIS
	}
	
	private static void printAllSubwords(String word) {
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
	
	private static void initWordMap(Set<String> words) {
		wordMap = words.stream()
				.flatMap(word -> subwords(word)
						.map(subword -> new SimpleEntry<>(word, subword)))
				.filter(e -> words.contains(e.getValue()))
				.collect(Collectors.groupingBy(e -> e.getKey(), Collectors.mapping(e -> e.getValue(), Collectors.toSet())));
	}
	
	private static Stream<String> subwords(String word) {
		return IntStream.range(0, word.length())
				.mapToObj(i -> word.substring(0, i) + word.substring(i+1));
	}
}
