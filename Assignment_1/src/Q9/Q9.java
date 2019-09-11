package Q9;
//HAD PROBLEMS WRITING JUNIT TEST CLASS AS COULDN'T ADD TO ARRAYLIST WITHIN THE TEST CLASS

import java.util.ArrayList;
//Create an ArrayList which stores numbers from 1 to 100 and prints out all the prime
//numbers to the console.
public class Q9 {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		for (int i = 1; i <= 100; i++)
			list.add(i);
		//System.out.println("Printing all elements in ArrayList");
		//arrayListPrinter(list);
		for (int i = 0; i < list.size(); i++) {
			int x = (int) list.get(i);
			System.out.print(primeNumberPrinter(x) ? x + "\n" : "");
		}
	}
	
	public static void arrayListPrinter(ArrayList list) {
		for (int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));
	}
	public static boolean primeNumberPrinter(int x) {
		if (x ==1)
			return false;
		if (x == 2 | x == 3 | x == 5 | x == 7 | x == 11 | x == 13)
			return true;
		else if (!((x%2 == 0) | (x%3 == 0) | (x%5 == 0) | (x%7 == 0) | (x%11 == 0) | (x%13 == 0)))					//not divisible by 2
			return true;
		else
			return false;
	}
}
