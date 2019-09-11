package Q8;

import java.util.ArrayList;
//HAD PROBLEMS WRITING JUNIT TEST CLASS AS COULDN'T ADD TO ARRAYLIST WITHIN THE TEST CLASS

//Write  a  program  that  stores  the  following  strings  in  an  ArrayList  and  saves  all  the palindromes in another ArrayList.
//“karan”, “madam”,”tom”, “civic”, “radar”, “sexes”, “jimmy”, “kayak”, “john”,  “refer”, “billy”, “did”
public class Q8 {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add("karan");
		list.add("madam");
		list.add("tom");
		list.add("civic");
		list.add("radar");
		list.add("sexes");
		list.add("jimmy");
		list.add("kayak");
		list.add("john");
		list.add("refer");
		list.add("billy");
		list.add("did");
		ArrayList palindromes = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			if (palindromeChecker((String)list.get(i)) == true) {
				palindromes.add(list.get(i));
				list.remove(i);
				i--;
			}
		}	
		System.out.println("Printing palindromes");
		arrayListPrinter(palindromes);
		System.out.println("Printing all other strings");
		arrayListPrinter(list);
	}	
	public static boolean palindromeChecker(String str) {
		for (int i = 0; i < str.length()/2 ; i++) {
			if (str.charAt(i) != str.charAt(str.length()-1-i))
				return false;
		}
		return true;
	}
	public static void arrayListPrinter(ArrayList list) {
		for (int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));
	}
}
