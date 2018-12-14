import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TrieNode {
	
	char ch;
	Map<Character, TrieNode> children;
	
	boolean completesString;
	
	public TrieNode(char ch) {
		this.ch = ch;
		
		children = new HashMap<>();
	}
	
	public TrieNode(char ch, String word) {
		this(ch);
		
		insert(word);
	}
	
	public void insert(String word) {		
		if(word.isEmpty()) {
			return;
		}
		
		char next = word.charAt(0);
		String remainingWord = word.substring(1);
		
		if(children.containsKey(next)) {
			
			children.get(next).insert(remainingWord);
			
		} else {
			
			children.put(next, new TrieNode(next, remainingWord));
		}
	}
	
	public Set<String> get(String word) {
		Set<String> set = new HashSet<>();
		
		char next = word.charAt(0);
		
		if(children.containsKey(next)) {
			return children.get(next).get(word.substring(1));
		}
		
		
		for(TrieNode t : children.values()) {
			
			for(String s : t.g)
			
		}
	}
	
	public boolean find(String word) {
		return false;
	}
	
	public boolean remove(String word) {
		return false;
	}
	
	@Override
	public String toString() {
		return ch + (children.isEmpty() ? "" : " -> " + children.values().toString());
	}
}