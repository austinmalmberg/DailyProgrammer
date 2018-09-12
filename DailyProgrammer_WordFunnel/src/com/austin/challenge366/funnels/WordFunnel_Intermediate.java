package com.austin.challenge366.funnels;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.austin.challenge366.WordMap;

public class WordFunnel_Intermediate extends WordFunnel {

	private Map<String, List<ArrayDeque<String>>> funnelMap;
	
	public WordFunnel_Intermediate() throws IOException {
		super();
		
		funnelMap = wordMap.keySetAsStream()
				.map(word -> new ArrayDeque<String>(Arrays.asList(word)))
				.flatMap(this::createBranch)
				.collect(Collectors.groupingBy(ArrayDeque::peekFirst, HashMap::new, Collectors.toList()));
	}
	
	public WordFunnel_Intermediate(WordMap wordMap) {
		super(wordMap);
		
		funnelMap = wordMap.keySetAsStream()
				.map(word -> new ArrayDeque<String>(Arrays.asList(word)))
				.flatMap(this::createBranch)
				.collect(Collectors.groupingBy(ArrayDeque::peekFirst, HashMap::new, Collectors.toList()));
	}

	@Override
	public void printChallenges() {
		// examples
		System.out.println("\n-----Examples-----");
		printFunnels("gnash");
		printFunnels("princesses");
//		printFunnels("turntables");
		printFunnels("implosive");
		printFunnels("programmer");
		
		// bonus 1
		System.out.println("\n-----Bonus 1-----");
//		printFunnelsOfLength(10);
		
//		getLongestFunnel();
		
	}
	
	private void printFunnels(String word) {
		funnelMap.get(word).forEach(System.out::println);
		
//		TreeMap<Integer, List<ArrayDeque<String>>> temp = funnelMap.get(word).stream()
//				.collect(Collectors.groupingBy(ArrayDeque::size, TreeMap::new, Collectors.toList()));
//		
//		System.out.printf("%s => %d%n", word, temp.lastKey());
//		temp.lastEntry().getValue().forEach(System.out::println);
	}
	
	private void printFunnelOfLength(int i) {
		
	}
	
	private void getLongestFunnel() {
		funnelMap.entrySet().stream()
				.map(entry -> entry.getValue().subList(0, entry.getValue().size() < 10 ? entry.getValue().size() : 10))
				.collect(Collectors.toList()).forEach(System.out::println);
	}
	
	private Stream<ArrayDeque<String>> createBranch(ArrayDeque<String> branch) {
		return wordMap.contains(branch.peekLast()) ?
				wordMap.get(branch.peekLast()).stream()
						.map(leaf -> growBranch(branch, leaf))
						.flatMap(this::createBranch) :
				Stream.of(branch);
	}
	
	private ArrayDeque<String> growBranch(ArrayDeque<String> branch, String leaf) {
		return Stream.concat(branch.stream(), Stream.of(leaf)).collect(Collectors.toCollection(ArrayDeque::new));
	}
}
