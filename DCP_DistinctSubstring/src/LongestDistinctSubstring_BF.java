import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
public class LongestDistinctSubstring_BF {

	// solution is O(n^3) time complexity
	
	static List<Substring> substrings = new ArrayList<>();
	
	public static String getSubstring(String s, int k) {
		
		// populate list
		for(int i = 0; i < s.length()-1; i++) {
			for(int j = i+1; j < s.length(); j++) {
				
				substrings.add(new Substring(s.substring(i, j)));
			}
		}
		
		System.out.println(substrings.stream().map(sub -> sub.distinctCharacters()).collect(Collectors.toList()).toString());
		
		Optional<Substring> output = substrings.stream()
				.filter(sub -> sub.distinctCharacters() == k)
				.sorted().findFirst();
		
		if(!output.isPresent())
			return "Not found";
		
		return output.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getSubstring("asdfwbaeaeweawaweewvaewwr", 100));	// should return "bcb"
	}
}

class Substring implements Comparable<Substring> {
	
	String substring;
	int distinct;
	
	public Substring(String substring) {
		this.substring = substring;
		
		this.distinct = substring.chars().mapToObj(i -> (char) i).collect(Collectors.toSet()).size();
	}
	
	public int distinctCharacters() { return distinct; }
	public int length() { return substring.length(); }
	
	public int compareTo(Substring o) {
		return o.length() - length();
	}
	
	public String toString() { return substring; }
}
