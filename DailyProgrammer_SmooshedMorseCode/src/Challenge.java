import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * Easy: https://www.reddit.com/r/dailyprogrammer/comments/cmd1hb/20190805_challenge_380_easy_smooshed_morse_code_1/
 * Intermediate: https://www.reddit.com/r/dailyprogrammer/comments/cn6gz5/20190807_challenge_380_intermediate_smooshed/
 * 
 * @author Austin Malmberg
 *
 */

public class Challenge {
	
	private SmooshedMorseCode smorse;
	
	private List<String> wordList;
	private Map<String, List<String>> mcsMap;
	
	public Challenge() {
		
		smorse = new SmooshedMorseCode();
		
		System.out.print("Initializing word list...");
		initWordList("enable1.txt");
		System.out.println("done!");		
		
		System.out.print("Initializing morse code map...");
		mcsMap = wordList.stream()
				.map(word -> new SimpleEntry<String, String>(smorse.asMorseCode(word), word))
				.collect(Collectors.groupingBy(SimpleEntry::getKey, Collectors.mapping(SimpleEntry::getValue, Collectors.toList())));
		System.out.println("done!");
	}
	
	
	/* PUBLIC METHODS */
	
	/* EASY */
	
	public void printEasyChallenges() {
		
		examplesEasy();
		
		/* OPTIONAL BONUSES */
		
		nDifferentWords(13);
		
		nConsecutiveDashes(15);
		
		perfectlyBalancedWords(21);
		
		nLengthPalindrome(13);
		
		findUnusedSubsequences(13);
	}
	
	public void examplesEasy() {
		System.out.println("\n\n>>>>>> EXAMPLES >>>>>>\n");
		
		String[] examples = { "sos", "daily", "programmer", "bits", "three"	};
		
		for (String ex : examples) {
			System.out.printf("%s -> %s%n", ex, smorse.asMorseCode(ex));
		}
	}
	
	/**
	 * Finds and prints (if present) the first Morse code sequence (mcs) that's used by n different words
	 * 
	 * @param i the number of words that use the same mcs 
	 */
	public void nDifferentWords(int i) {
		System.out.println("\n\n>>>>>> MORSE CODE SEQUENCE FOR " + i + " DIFFERENT WORDS >>>>>>\n");
		mcsMap.entrySet().stream()
				.filter(entry -> entry.getValue().size() == i)
				.forEach(this::formatAndPrintEntry);
	}
	
	/**
	 * Finds and prints (if present) a Morse code sequence (mcs) with 15 consecutive dashes.
	 * 
	 * @param i the number of consecutive dashes
	 */
	public void nConsecutiveDashes(int i) {
		System.out.println("\n\n>>>>>> MORSE CODE SEQUENCE WITH " + i + " CONSECUTIVE DASHES >>>>>>\n");
		mcsMap.entrySet().stream()
				.filter(entry -> consecutiveDashes(entry.getKey()) == i)
				.forEach(this::formatAndPrintEntry);;
	}
	
	/**
	 * Finds and print n-length words with Morse code sequences (mcs) that are perfectly balanced (have the same number of dots and dashes).
	 * 
	 * @param len the length of the word to find
	 */
	public void perfectlyBalancedWords(int len) {
		System.out.println("\n\n>>>>>> " + len + "-LETTER WORDS WITH PERFECTLY BALANCED MORSE CODE SEQUENCES >>>>>>\n");
		mcsMap.keySet().stream()
			.filter(this::perfectlybalanced)
			.flatMap(key -> mcsMap.get(key).stream()
					.filter(word -> word.length() == len)
					.map(word -> new SimpleEntry<String, String>(key, word)))
			.map(entry -> String.format("%s (%s)", entry.getValue(), entry.getKey()))
			.forEach(System.out::println);
	}
	
	/**
	 * Find and prints words of n length whose Morse code sequence (mcs) is a palindrome
	 * 
	 * @param len the length of the word to find
	 */
	public void nLengthPalindrome(int len) {
		System.out.println("\n\n>>>>>> " + len + "-LETTER WORDS WHOSE MORSE CODE SEQUENCE IS A PALINDROME >>>>>>\n");
		mcsMap.keySet().stream()
			.filter(this::isPalindrome)
			.flatMap(key -> mcsMap.get(key).stream()
					.filter(word -> word.length() == len)
					.map(word -> new SimpleEntry<String, String>(key, word)))
			.map(entry -> String.format("%s (%s)", entry.getValue(), entry.getKey()))
			.forEach(System.out::println);
	}
	
	/**
	 * Finds and prints any unique subsequences of n length that are not used in a Morse code sequence (mcs).
	 * 
	 * @param len the length of the subsequence
	 */
	public void findUnusedSubsequences(int len) {
		
		System.out.println("\n\n>>>>>> UNUSED DOT/DASH SUB-SEQUENCES WITH " + len + " CHARACTERS >>>>>>\n");
		generateSequencesAsStream(len)
				.filter(seq -> !inMCSKeySet(seq))
				.forEach(System.out::println);
	}
	
	
	/* PRIVATE METHODS */
	
	private int consecutiveDashes(String morseCode) {
		return Stream.of(morseCode.split("\\.+")).mapToInt(String::length).max().orElse(0);
	}

	private boolean perfectlybalanced(String morseCode) {
		int offset = 0;
		
		for (int i = 0; i < morseCode.length(); i++) {
			
			if (morseCode.charAt(i) == '-')
				++offset;
			else if (morseCode.charAt(i) == '.')
				--offset;
			
		}
		
		return offset == 0;
	}
	
	private boolean isPalindrome(String word) {
		
		for (int i = 0; i < Math.floor(word.length() / 2); i++) {
			
			char c1 = word.charAt(i);
			char c2 = word.charAt(word.length() - 1 - i);
			
			if (c1 != c2) return false;
		}
		
		return true;
	}
	
	private Stream<String> generateSequencesAsStream(int len) {
		if (len <= 0)
			return Stream.of("");
		
		return generateSequencesAsStream(len - 1).flatMap(curr -> Stream.of(curr + ".", curr + "-"));
	}
	
	private boolean inMCSKeySet(String s) {
		
		for (String key : mcsMap.keySet()) {
			
			if (key.contains(s))
				return true;
		}
		
		return false;
	}

	private void formatAndPrintEntry(Entry<String, List<String>> entry) {
		System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue().toString());
	}
	
	private void initWordList(String filename) {
		
		try {
			
			wordList = Files.lines(new File(filename).toPath())
					.map(String::trim)
					.collect(Collectors.toList());
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/* INTERMEDIATE */
	
	public void printIntermediateChallenges() {

		smorse.alphaResultsAsStream(".----...---.-....--.-.....")
				.findFirst().ifPresent(System.out::println);
		
//		examplesIntermediate();
	}
	
	public void examplesIntermediate() {
		
//		String[] examples = { ".--...-.-.-.....-.--........----.-.-..---.---.--.--.-.-....-..-...-.---..--.----..", // => "wirnbfzehatqlojpgcvusyxkmd"
//				".----...---.-....--.-........-----....--.-..-.-..--.--...--..-.---.--..-.-...--..-", // => "wzjlepdsvothqfxkbgrmyicuna"
//				"..-...-..-....--.---.---.---..-..--....-.....-..-.--.-.-.--.-..--.--..--.----..-.." // => "uvfsqmjazxthbidyrkcwegponl"
//		};
		
		String[] examples = {
				".--...-.-.-.....-.--........----.-."
		};
		
		for (String ex : examples) {
			System.out.printf("\n\n>>>>>> EXAMPLES >>>>>>\n", ex);
			smorse.asAlpha(ex).stream().forEach(System.out::println);
		}
	}
	
	/* MAIN METHOD */
	
	public static void main(String[] args) {
		Challenge challenge = new Challenge();
		
		/* EASY */
		
//		challenge.printEasyChallenges();
		
		/* INTERMEDIATE */
		
		challenge.printIntermediateChallenges();
		
		/* INTERMEDIATE OPTIONAL BONUS 2 */
		
		/* INTERMEDIATE OPTIONAL BONUS 2 */
	}
	
}
