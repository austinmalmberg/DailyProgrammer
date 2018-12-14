import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AutoComplete {
	
	private static TrieNode root;
	
	public AutoComplete(Set<String> dict) {
		root = new TrieNode('\000');
		for(String word : dict) {
			root.insert(word);
		}
	}
	
	public Set<String> query(String s) {
		return root.get();
	}
	
	public static void main(String[] args) {
		
		Set<String> dict = new HashSet<>();
		dict.add("dog");
		dict.add("deer");
		dict.add("deal");
		
		System.out.println("Easy: " + easySolution(dict, "de"));
		
		AutoComplete auto = new AutoComplete(dict);
//		System.out.println(auto.query("de"));		
	}
	
	public static Set<String> easySolution(Set<String> dict, String query) {
		return dict.stream().filter(e -> e.startsWith(query)).collect(Collectors.toSet());
	}
}
