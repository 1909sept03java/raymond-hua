package Q21;
// Write a method to remove all repeated characters in a string.
public class Q21 {
	public static String function(String str) {
		int length = str.length();
		String result = "";
		while (length != 0)	{
			result += str.charAt(0);
			str = str.replaceAll(String.valueOf(str.charAt(0)), "");
			length = str.length();
		}
		return result;
	}
}
