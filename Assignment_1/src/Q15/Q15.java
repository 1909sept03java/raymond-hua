package Q15;
/*Write a program that defines an interface having the following methods: addition,
subtraction, multiplication, and division. Create a class that implements this interface
and provides appropriate functionality to carry out the required operations. Hard code
two operands in a test class having a main method that calls the implementing class.
*/

public class Q15 implements mathStuff{
	public int addition(int x, int y) {
		return x + y;
	}
	public int subtraction(int x, int y) {
		return x - y;
	}
	public int multiplication(int x, int y) {
		return x * y;
	}
	public int division(int x, int y) {
		return x / y;
	}	
}


