package com.austin.challenge366.funnels;

import java.io.IOException;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.austin.challenge366.WordMap;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/98ufvz/20180820_challenge_366_easy_word_funnel_1/
 * 
 * @author Austin Malmberg
 *
 */

public class WordFunnel_Easy extends WordFunnel {
	
	public WordFunnel_Easy() throws IOException {
		super();
	}
	
	public WordFunnel_Easy(WordMap wordMap) {
		super(wordMap);
	}
	
	@Override
	public void printChallenges() {
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
	
	private void printMostSubwords() {
		wordMap.entrySetAsStream()
				.collect(Collectors.groupingBy(e -> e.getValue().size(), TreeMap::new, Collectors.toList()))
				.lastEntry().getValue().forEach(System.out::println);
	}
	
	private void printSubwords(String word) {
		System.out.printf("%s => %s%n", word, wordMap.get(word));
	}
	
	private void printfunnel(String word, String subword) {
		System.out.printf("%s -> %s => %s%n", word, subword, isSubword(word, subword));
	}
	
	private boolean isSubword(String word, String subword) {
		return wordMap.get(word).contains(subword);
	}
}
