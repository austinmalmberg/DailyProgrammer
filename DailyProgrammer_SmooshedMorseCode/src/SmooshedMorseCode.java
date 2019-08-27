import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SmooshedMorseCode {

	final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	final String[] MORSE_CODE = ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..".split("\\s+");
	
	public SmooshedMorseCode() {
		
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
	
	/* PRIVATE METHODS */
	
}
