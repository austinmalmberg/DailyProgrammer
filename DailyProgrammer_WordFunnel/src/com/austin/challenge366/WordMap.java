package com.austin.challenge366;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordMap {

	public final String DICTIONARY_PATH = "enable1.txt";
	
	private final Map<String, Set<String>> wordMap;
	
	public WordMap() throws IOException {
		Set<String> wordset = Files.lines(Paths.get(DICTIONARY_PATH)).collect(Collectors.toCollection(HashSet::new));
		
		wordMap = wordset.stream()
				.flatMap(word -> getSubwordsAsStream(word)
						.map(subword -> new SimpleEntry<>(word, subword)))
				.filter(entry -> wordset.contains(entry.getValue()))
				.collect(Collectors.groupingBy(entry -> entry.getKey(), Collectors.mapping(entry -> entry.getValue(), Collectors.toSet())));
	}
	
	private Stream<String> getSubwordsAsStream(String word) {
		return IntStream.range(0, word.length())
				.mapToObj(i -> word.substring(0, i) + word.substring(i+1));
	}
	
	/**
	 * 
	 * @param word any word
	 * @return a set of subwords, or an empty set if the wordMap does not contain the word
	 */
	public Set<String> get(String word) {
		if(wordMap.containsKey(word))
			return wordMap.get(word);
		
		return Collections.emptySet();
	}
	
	public boolean contains(String word) {
		return wordMap.containsKey(word);
	}
	
	public Stream<Entry<String, Set<String>>> entrySetAsStream() {
		return wordMap.entrySet().stream();
	}
}
