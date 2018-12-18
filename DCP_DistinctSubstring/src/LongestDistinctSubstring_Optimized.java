import java.util.HashMap;
import java.util.Map;

/**
 * This problem was asked by Amazon.
 * 
 * Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.
 * 
 * For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".
 * 
 * @author Austin Malmberg
 *
 */
public class LongestDistinctSubstring_Optimized {

	// solution is O(n) time complexity
	
	static Map<Character, Integer> map = new HashMap<>();
	static StringBuilder window = new StringBuilder();
	static String longestUniqueString = "";
	static int numberOfUniqueCharacters;
	
	public static String getSubstring(String s, int k) {
		resetVariables();
		
		for(char ch : s.toCharArray()) {
			expandWindow(ch);
			contractWindow(k);
			
			compareWithLongest(window.toString());
		}
		
		if(numberOfUniqueCharacters != k) {
			return "Not found!";
		}
		
		return longestUniqueString;
	}
	
	private static void compareWithLongest(String s) {
		if(s.length() > longestUniqueString.length()) {
			longestUniqueString = s;
			numberOfUniqueCharacters = mapSize();
		}
	}
	
	private static void expandWindow(char ch) {
		addToMap(ch);
		window.append(ch);
	}
	
	private static void addToMap(char ch) {
		
		if(map.containsKey(ch))
			map.put(ch, map.get(ch)+1);
		else
			map.put(ch, 1);
	}
	
	/**
	 * Contracts the window if the number of unique characters exceeds the number of allowed unique characters
	 * 
	 * @param k The maximum number of unique characters
	 */
	private static void contractWindow(int k) {
		
		while(mapSize() > k) {
			
			char ch = window.charAt(0);
			removeFromMap(ch);
			
			window.deleteCharAt(0);
		}
	}
	
	private static void removeFromMap(char ch) {
		
		if(map.containsKey(ch)) {
			
			int val = map.get(ch);
			
			if(val > 1)
				map.put(ch, val-1);
			else
				map.remove(ch);
		}
	}
	
	private static int mapSize() { return map.keySet().size(); }
	
	private static void resetVariables() {
		map.clear();
		window.setLength(0);
		longestUniqueString = "";
		numberOfUniqueCharacters = 0;
	}
	
	public static void main(String[] args) {
		viewSolution("abcba", 2);
		viewSolution("aabacbebebe", 3);
	}
	
	public static void viewSolution(String s, int k) {
		System.out.printf("(%s, %d) -> %s%n", s, k, getSubstring(s, k));
	}
}
