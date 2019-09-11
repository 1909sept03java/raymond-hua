package Q10;
//Find the minimum of two numbers using ternary operators.

public class Q10 {
	public static void main(String[] args) {
		/* [expression] ? [val for T] : [val for F]
		 		static String ternary(int x) {
		 			return x > 0 ? "X is greater than 0" : "X is less than 0";
		 		}
		*/
		System.out.println(min(1 , 2));
		
	}
	
	public static int min(int x, int y) {
		return (x < y ? x : y);
	}
}
