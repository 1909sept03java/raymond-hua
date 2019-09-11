package Q3;
//Reverse a string without using a temporary variable.  Do NOTuse reverse() in the StringBuffer 
//or the StringBuilder APIs

public class Q3 {
	public String function(String input) {
		StringBuilder str = new StringBuilder(input);
		//System.out.println(str);
		for (int i = str.length()-1; i >= 0 ; i--)
			str.append(str.charAt(i));
		//System.out.println(str);
		str.delete(0, str.length()/2);
		//System.out.println(str);
		return str.toString();
	}
}
