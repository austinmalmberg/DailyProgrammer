import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SmooshedMorseCode {

	final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	final String[] MORSE_CODE = ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..".split("\\s+");
	
	Map<String, Character> buildMap;
	
	public SmooshedMorseCode() {
		buildMap = IntStream.range(0, MORSE_CODE.length)
				.mapToObj(i -> new SimpleEntry<String, Character>(MORSE_CODE[i], ALPHABET.charAt(i)))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}
	
	/* PUBLIC METHODS */
	
	public String asMorseCode(String s) {
		
		return IntStream.range(0, s.length())
				.mapToObj(i -> {
					
					int alphaIndex = ALPHABET.indexOf(s.charAt(i));
					
					if (alphaIndex < 0) {
						return "";
					}
					
					return MORSE_CODE[alphaIndex];
					
				}).collect(Collectors.joining());
	}
	
	public Stream<String> alphaResultsAsStream(String input) {
		
		if (input.length() == 0)
			return Stream.of("");
		
		return alphaResultsAsStream(input, "");
	}	
	
	public Stream<String> alphaResultsAsStream(String input, String res) {
		
		if (input.isEmpty())
			return Stream.of(res);
		
		return buildMap.keySet().stream()
				.filter(input::startsWith)
				.flatMap(key -> {
					
					String remaining = input.substring(key.length());
					Character ch = buildMap.get(key);
					
					return alphaResultsAsStream(remaining, res + ch);
				});
	}
	
	public List<String> asAlpha(String s) {
		
		List<String> res = new ArrayList<>();
		
		asAlpha(s, "", res);
		
		return res;
	}
	
	
	/* PRIVATE METHODS */
	
	private void asAlpha(String input, String output, List<String> list) {
		
		if (input.length() == 0)
			list.add(output);
		
		for (int i = 0; i < MORSE_CODE.length; i++) {
			
			String morseLetter = MORSE_CODE[i]; 
			
			if (input.startsWith(morseLetter)) {
				asAlpha(input.substring(morseLetter.length()), output + ALPHABET.charAt(i), list);
			}
		}	
	}
}
