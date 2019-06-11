
/**
 * Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.

For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.

You can assume that the messages are decodable. For example, '001' is not allowed.
 * 
 * @author Austin Malmberg
 *
 */
public class Main {

	public static void main(String[] args) {
		
		System.out.println(count("13111"));
		
	}
	
	public static int count(String message) {
		return count(message, "");
	}
	
	private static int count(String message, String decoded) {
		if(message.isEmpty()) {
			System.out.println(decoded);
			return 1;
		}
		
		if(message.length() > 1 && isAlpha(message.substring(0, 2))) {
			return count(message.substring(2), decoded + getLetter(message.substring(0, 2)))
					+ count(message.substring(1), decoded + getLetter(message.substring(0, 1)));
		} else if(message.length() > 0 && isAlpha(message.substring(0, 1))) {
			return count(message.substring(1), decoded + getLetter(message.substring(0, 1)));
		}
		
		return 0;
	}
	
	private static boolean isAlpha(String s) {
		if(s.charAt(0) == '0') return false;
		
		int i = Integer.parseInt(s);
		return i > 0 && i <= 26;
	}
	
	private static char getLetter(String s) {
		return (char) (Integer.parseInt(s) + 96);
	}
}
